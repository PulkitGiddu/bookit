package com.hsbc.bookit.services;

import com.hsbc.bookit.dao.UserDAO;
import com.hsbc.bookit.dao.UserDAOImpl;
import com.hsbc.bookit.domain.Users;
import com.hsbc.bookit.exceptions.AccessDeniedException;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    List<Users> users = new ArrayList<>();
    private final Users authenticatedUser;

    UserDAO userdao = new UserDAOImpl();

    public UserServiceImpl(Users authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    protected void checkAdminAccess() {
        if (authenticatedUser == null || !authenticatedUser.getRole().equalsIgnoreCase("admin")) {
            throw new AccessDeniedException("You do not have access to this feature!");
        }
    }

    public void addUserdata(){
        checkAdminAccess();
        Users newUser = new Users("14", "kunalu", "324323", "Kunal", "k@gmail.com", "944923759", "manager", 0);
        userdao.addUsers(authenticatedUser, newUser);
    }

    public void deleteUserdata(){
        checkAdminAccess();
        userdao.deleteUsers(authenticatedUser, "kunalu");
    }

    public void getAllUsers(){
        checkAdminAccess();
        users = userdao.findUsers();
        users.forEach(System.out::println);
        users.clear();
    }

    public void getUsersByUsername(){
        checkAdminAccess();
        users = userdao.getUsersbyusername("janesmith");
        users.forEach(System.out::println);
        users.clear();

    }

}
