package com.hsbc.bookit.services;

import com.hsbc.bookit.dao.UserDAO;
import com.hsbc.bookit.dao.UserDAOImpl;
import com.hsbc.bookit.domain.Users;
import com.hsbc.bookit.exceptions.AccessDeniedException;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO userdao;
    private final Users authenticatedUser;

    public UserServiceImpl(Users authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        this.userdao = new UserDAOImpl();
    }

    protected void checkAdminAccess() {
        if (authenticatedUser == null || !authenticatedUser.getRole().equalsIgnoreCase("admin")) {
            throw new AccessDeniedException("You do not have access to this feature!");
        }
    }

    public void addUserdata(String id, String username, String password, String name, String email, String phone, String role, int credits) {
        checkAdminAccess();
        Users newUser = new Users(id, username, password, name, email, phone, role, credits);
        userdao.addUsers(authenticatedUser, newUser);
        System.out.println("User added: " + name);
    }

    public void deleteUserdata(String username) {
        checkAdminAccess();
        userdao.deleteUsers(authenticatedUser, username);
        System.out.println("User deleted: " + username);
    }

    public void getAllUsers() {
        checkAdminAccess();
        List<Users> users = userdao.findUsers();
        users.forEach(System.out::println);
    }

    public void getUsersByUsername(String username) {
        checkAdminAccess();
        List<Users> users = userdao.getUsersbyusername(username);
        users.forEach(System.out::println);
    }
}
