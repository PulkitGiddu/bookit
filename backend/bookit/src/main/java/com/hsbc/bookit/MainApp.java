package com.hsbc.bookit;

import com.hsbc.bookit.dao.UserDAO;
import com.hsbc.bookit.dao.UserDAOImpl;
import com.hsbc.bookit.domain.Users;
import com.hsbc.bookit.services.LoginService;
import com.hsbc.bookit.services.UserService;

//testing all the methods for now
public class MainApp {
    public static void main(String[] args) {
        System.out.println("Building an automated booking system!");

        UserService us = new UserService();

        us.addUserdata();
        us.deleteUserdata();

        us.getAllUsers();
        System.out.println("---------------------");
        us.getUsersByUsername();

    }

}
