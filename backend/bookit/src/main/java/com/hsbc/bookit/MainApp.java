package com.hsbc.bookit;

import com.hsbc.bookit.dao.UserDAO;
import com.hsbc.bookit.dao.UserDAOImpl;
import com.hsbc.bookit.domain.Amenities;
import com.hsbc.bookit.domain.Meetings;
import com.hsbc.bookit.domain.Users;
import com.hsbc.bookit.services.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//testing all the methods for now
public class MainApp {
    public static void main(String[] args) {
        System.out.println("Building an automated booking system!");
        RoomService rs = new RoomService();
        LoginService ls = new LoginService();
        AmenityService as = new AmenityService();
        //this sends data to the login service and dao which checks if the username and pwd is correct otherwise throws exception
        Users authenticatedUser = ls.login("janesmith","password456"); //scanner in these
        ls.resetCredits(authenticatedUser); // resets the credits of the manager if it is monday, else just displays the total available credits.
        UserService us = new UserService(authenticatedUser);
        MeetingService ms = new MeetingService(authenticatedUser);


        us.addUserdata(); // admin method. In user service, scanner in the details
        us.deleteUserdata(); //admin method. Similarly ask for id to delete in user service

        us.getAllUsers(); // admin method
        System.out.println("---------------------");
        us.getUsersByUsername(); // admin method. Ask for username using scanner

        //other admin methods
        //all these inputs need to be taken in using scanners
        rs.addRoom("Conference Room C",25);
        rs.deleteRoom(1);
        rs.updateRoom(1,"Conference Room A",30); //checks id and updates name and seating capacity
        ms.deleteMeeting("1"); //admin method only
        //general methods(can be called by manager, admin or member)
        rs.getAllRooms();
        List<Amenities> amenities = as.viewAllAmenities();
        amenities.forEach(System.out::println);

        List<Meetings> allmeetings = ms.viewMeetings(); //method accessible to all
        allmeetings.forEach(System.out::println);



        //convert Date object to timestamp because in SQL the format is in that manner.
        //This time is given to the meeting object to book the meeting.
        //scanner in needed
        LocalDateTime startDateTime = LocalDateTime.of(2024, 8, 20, 9, 0);  // '2024-08-20 09:00:00'
        LocalDateTime endDateTime = LocalDateTime.of(2024, 8, 20, 10, 0);  // '2024-08-20 10:00:00
        Timestamp startTime = Timestamp.valueOf(startDateTime);
        Timestamp endTime = Timestamp.valueOf(endDateTime);

        //ask user and then store in list
        List<String> selectedAmenities = Arrays.asList("hja", "Coffee Machine"); // selected amenities of the user.
        //these need to be scanner in'd in the while loop. (Only accessible to the manager)
        //accessible to manager and admin

        ms.bookMeetingWithDefaultRoom(17,2,startTime,endTime, MeetingService.DefaultRoom.CLASSROOM_TRAINING);
//        ms.bookMeetingWithCustomRoom(13,1, startTime, endTime, selectedAmenities, 10);


}}
