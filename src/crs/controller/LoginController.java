package crs.controller;

import crs.model.User;

public class LoginController {
    private UserController1 userController1;
    private User currentUser;
    
    public LoginController(UserController1 userController1) {
        this.userController1 = userController1;
    }
    
    public boolean login(String username, String password) {
        User u = userController1.getUser(username);

        if (u != null && u.password.equals(password)) {
            
            if (u.isActive) {
                currentUser = u;
                userController1.updateLoginTime(u);
                
                
                System.out.println("[LOG] Login Success: " + username);
                return true;
            } else {
                System.out.println("[LOG] Login Failed: Account is blocked/inactive.");
            }
        } else {
            System.out.println("[LOG] Login Failed: Invalid username or password.");
        }
        
        return false;
    }
    
    public void logout() {
        if (currentUser != null) {
            System.out.println("[LOG] Logout: " + currentUser.username);
        }
        currentUser = null;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public boolean isLoggedIn() {
        return currentUser != null;
    }
}