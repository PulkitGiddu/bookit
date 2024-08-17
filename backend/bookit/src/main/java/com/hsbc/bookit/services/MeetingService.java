package com.hsbc.bookit.services;

import com.hsbc.bookit.dao.*;
import com.hsbc.bookit.domain.Meetings;
import com.hsbc.bookit.domain.Users;
import com.hsbc.bookit.exceptions.AccessDeniedException;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.put;

public class MeetingService {

    private final UserDAO userDAO = new UserDAOImpl();
    private final RoomDAO roomDAO = new RoomDAOImpl();
    private final MeetingDAO meetingDAO = new MeetingDAOImpl();
    private final AmenityDAO amenityDAO = (AmenityDAO) new AmenityDAOImpl();
    private final LoginService loginService = new LoginService();

    Users authenticatedUser;

    // Credits cost for seating capacity and amenities
    private final Map<String, Integer> amenityCostMap = new HashMap<String, Integer>() {{
        put("Projector", 5);
        put("Wifi", 10);
        put("Conference Call", 15);
        put("Whiteboard", 5);
        put("Water Dispenser", 5);
        put("TV", 10);
        put("Coffee Machine", 10);
    }};

    private final Map<Integer, Integer> seatingCostMap = new HashMap<Integer, Integer>() {{
        put(5, 0);  // seating capacity <= 5
        put(10, 10);  // seating capacity <= 10
        put(20, 20);  // seating capacity <= 20
    }};

    public MeetingService(String username, String password) {
        authenticatedUser = loginService.login(username, password);
        if (authenticatedUser == null || !"manager".equalsIgnoreCase(authenticatedUser.getRole())) {
            throw new AccessDeniedException("Only managers can perform this action!");
        }
    }

    // Method to calculate total cost and deduct credits
    private int calculateCredits(int seatingCapacity, List<String> amenities) {
        int totalCost = seatingCostMap.getOrDefault(seatingCapacity, 0);

        for (String amenity : amenities) {
            totalCost += amenityCostMap.getOrDefault(amenity, 0);
        }

        if (authenticatedUser.getCredits() < totalCost) {
            throw new AccessDeniedException("Not enough credits!");
        }

        authenticatedUser.setCredits(authenticatedUser.getCredits() - totalCost);
        userDAO.updateUserCredits(authenticatedUser); // Update the user's credits in the DB
        return totalCost;
    }

    public void bookMeeting(int roomId, Date startTime, Date endTime, List<String> amenities, int seatingCapacity) {
        int totalCost = calculateCredits(seatingCapacity, amenities);

        Meetings meeting = new Meetings();
        meeting.setRoomId(roomId);
        meeting.setManagerId(authenticatedUser.getId());
        meeting.setStartTime(startTime);
        meeting.setEndTime(endTime);
        meeting.setStatus("Scheduled");

        meetingDAO.bookMeeting(meeting);
        System.out.println("Meeting booked successfully with total cost: " + totalCost + " credits. Remaining credits: " + authenticatedUser.getCredits());
    }
}
