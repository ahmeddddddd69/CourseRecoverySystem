package crs.controller;

import crs.model.User;

public class LoginController {
    private UserController1 userController1;
    private User currentUser;
    
    public LoginController(UserController1 userController1) {
        this.userController1 = userController1;
    }
    
    public boolean login(String username, String password) {
        if (userController1.validateUser(username, password)) {
            currentUser = userController1.getUser(username);
            return true;
        }
        return false;
    }
    
    public void logout() {
        currentUser = null;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public boolean isLoggedIn() {
        return currentUser != null;
    }
}