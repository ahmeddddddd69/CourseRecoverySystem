package crs.controller;

import crs.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private List<User> users;
    
    public UserController() {
        this.users = new ArrayList<>();
        // Default admin user
        users.add(new User("admin", "admin123", "administrator"));
    }
    
    public boolean addUser(String username, String password, String role) {
        if (getUser(username) != null) {
            return false; // User exists
        }
        User user = new User(username, password, role);
        users.add(user);
        return true;
    }
    
    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    
    public boolean validateUser(String username, String password) {
        User user = getUser(username);
        return user != null && user.getPassword().equals(password);
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}