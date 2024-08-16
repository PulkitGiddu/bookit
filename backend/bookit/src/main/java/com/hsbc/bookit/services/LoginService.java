package com.hsbc.bookit.services;

import com.hsbc.bookit.dao.LoginDAO;
import com.hsbc.bookit.dao.LoginDAOImpl;
import com.hsbc.bookit.domain.Users;

public class LoginService {
    public Users login(String username, String password) {

        LoginDAO logindao = new LoginDAOImpl();

        Users authenticatedUser = logindao.authenticate(username, password);

        return authenticatedUser;
    }
}
