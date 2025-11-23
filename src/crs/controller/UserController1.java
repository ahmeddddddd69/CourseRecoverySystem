package crs.controller;

import crs.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserController1 {
    private List<User> users;
    
    public UserController1() {
        this.users = new ArrayList<>();
    }
    
    public void addExistingUser(User user) {
        users.add(user);
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
}