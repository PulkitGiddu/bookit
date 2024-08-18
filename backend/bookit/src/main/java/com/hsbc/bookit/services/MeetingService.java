package com.hsbc.bookit.services;

import com.hsbc.bookit.dao.*;
import com.hsbc.bookit.domain.Meetings;
import com.hsbc.bookit.domain.Users;
import com.hsbc.bookit.exceptions.AccessDeniedException;
import com.hsbc.bookit.exceptions.NotEnoughCreditsException;

import java.sql.Timestamp;
import java.util.List;

public class MeetingService {

    private int seatingcost;
    private final UserDAO userDAO = new UserDAOImpl();
    private final MeetingDAO meetingDAO = new MeetingDAOImpl();
    private final AmenityService amenityService= new AmenityService();

    Users authenticatedUser;

    public MeetingService(Users authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }
    protected boolean adminAccess() {
        if (authenticatedUser == null || !authenticatedUser.getRole().equalsIgnoreCase("admin")){
            return false;
        }
        return true;
    }


    protected boolean checkAccess() {
        if (authenticatedUser == null ||
                (!authenticatedUser.getRole().equalsIgnoreCase("manager") &&
                        !authenticatedUser.getRole().equalsIgnoreCase("admin"))) {
            throw new AccessDeniedException("You do not have access to this feature!");

        }
        return true;
    }

//    // Credits cost for seating capacity and amenities
//    private final Map<String, Integer> amenityCostMap = new HashMap<String, Integer>() {{
//        put("Projector", 5);
//        put("Wifi", 10);
//        put("Conference Call", 15);
//        put("Whiteboard", 5);
//        put("Water Dispenser", 5);
//        put("TV", 10);
//        put("Coffee Machine", 10);
//    }};
//
//    private final Map<Integer, Integer> seatingCostMap = new HashMap<Integer, Integer>() {{
//        put(5, 0);  // seating capacity <= 5
//        put(10, 10);  // seating capacity <= 10
//        put(20, 20);  // seating capacity <= 20
//    }};

//
//    public List<Amenities> viewAmenities() {
//        checkAccess();
//        return amenityDAO.getAllAmenities(); // Retrieves available amenities from the DB
//    }


    public void bookMeeting(int id,int roomId, Timestamp startTime, Timestamp endTime, List<String> selectedAmenities, int seatingCapacity) {
        checkAccess();

        int totalCostOfAmenities = amenityService.chooseAmenitiesAndCalculateCredits(selectedAmenities);
        if(seatingCapacity <=5)
            seatingcost = 0;
        if(seatingCapacity>5 && seatingCapacity<= 10)
            seatingcost = 10;
        if(seatingCapacity > 10)
            seatingcost= 20;

        int totalCost = seatingcost + totalCostOfAmenities;

        Meetings meeting = new Meetings();
        meeting.setId(id);
        meeting.setRoomId(roomId);
        meeting.setManagerId(authenticatedUser.getId());
        meeting.setStartTime(startTime);
        meeting.setEndTime(endTime);
        meeting.setStatus("Scheduled");

        int credits_remaining = (authenticatedUser.getCredits() - totalCost);
        if(authenticatedUser.getCredits() < 0){
            throw new NotEnoughCreditsException("You do not have enough credits!");
        }
        if(adminAccess()){
            meetingDAO.addMeeting(meeting);
            System.out.println("Meeting booked successfully, Remaining credits: " + authenticatedUser.getCredits());
        } else {
            meetingDAO.addMeeting(meeting);
            userDAO.updateUserCredits(authenticatedUser, credits_remaining);
            System.out.println("Meeting booked successfully with total cost: " + totalCost + " credits. Remaining credits: " + credits_remaining);
        }

    }

    public void deleteMeeting(String id){
        adminAccess();
        meetingDAO.removeMeeting(id);

    }
}
