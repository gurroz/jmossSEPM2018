package com.rmit.jmoss.services;

public class AuthService implements IAuthService{

    private String allowedUser = "123";

    public boolean login(String username) {
        if(allowedUser.equals(username)) {
            return true;
        } else {
            return false;
        }
    }
}
