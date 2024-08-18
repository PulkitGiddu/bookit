package com.hsbc.bookit;

import com.hsbc.bookit.dao.UserDAO;
import com.hsbc.bookit.dao.UserDAOImpl;
import com.hsbc.bookit.domain.Users;
import com.hsbc.bookit.services.LoginService;
import com.hsbc.bookit.services.MeetingService;
import com.hsbc.bookit.services.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//testing all the methods for now
public class MainApp {
    public static void main(String[] args) {
        System.out.println("Building an automated booking system!");

        LoginService ls = new LoginService();
        Users authenticatedUser = ls.login("janesmith","password456");
        ls.resetCredits(authenticatedUser);
        UserService us = new UserService(authenticatedUser);
        MeetingService ms = new MeetingService(authenticatedUser);

        us.addUserdata();
        us.deleteUserdata();

        us.getAllUsers();
        System.out.println("---------------------");
        us.getUsersByUsername();


        LocalDateTime startDateTime = LocalDateTime.of(2024, 8, 20, 9, 0);  // '2024-08-20 09:00:00'
        LocalDateTime endDateTime = LocalDateTime.of(2024, 8, 20, 10, 0);  // '2024-08-20 10:00:00
        Timestamp startTime = Timestamp.valueOf(startDateTime);
        Timestamp endTime = Timestamp.valueOf(endDateTime);

        List<String> selectedAmenities = Arrays.asList("hja", "Coffee Machine");

        ms.bookMeeting(13,1, startTime, endTime, selectedAmenities, 10);


}}
