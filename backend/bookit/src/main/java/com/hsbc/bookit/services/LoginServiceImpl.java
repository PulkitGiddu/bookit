package com.hsbc.bookit.services;

import com.hsbc.bookit.dao.LoginDAO;
import com.hsbc.bookit.dao.LoginDAOImpl;
import com.hsbc.bookit.domain.Users;

import java.util.Calendar;

public class LoginServiceImpl implements LoginService {
    LoginDAO logindao = new LoginDAOImpl();
    private Calendar calendar;
    public LoginServiceImpl(Calendar calendar) {
        this.calendar = calendar;
    }

    // ======================================================================= //
    //authenticate the user
    public Users login(String username, String password) {
        Users authenticatedUser = logindao.authenticate(username, password);

        return authenticatedUser;
    }

    // ======================================================================= //
    //reset the credits of the manager if it is monday, otherwise only display the credits
    public void resetCredits(Users user){
        Calendar calendar= Calendar.getInstance();
        if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY){
            logindao.resetCredits(user);
            System.out.println("Manager Credits reset to 2000");
        }
        else{
            System.out.println("Current Available Credits: "+ user.getCredits());
        }
    }

}
