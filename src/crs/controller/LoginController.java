package crs.controller;

import crs.model.User;

public class LoginController {
    private UserController userController;
    private User currentUser;
    
    public LoginController(UserController userController) {
        this.userController = userController;
    }
    
    public boolean login(String username, String password) {
        if (userController.validateUser(username, password)) {
            currentUser = userController.getUser(username);
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