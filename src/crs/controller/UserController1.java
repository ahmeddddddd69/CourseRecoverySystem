package crs.controller;

import crs.model.User;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserController1 {
    private List<User> users;
    
    private final String FILE_NAME = "users.csv"; 
    
    public UserController1() {
        this.users = new ArrayList<>();
        loadFromFile(); 
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
        
        if (user != null && user.getPassword().equals(password)) {
            updateLoginTime(user);
            return true;
        }
        return false;
    }

    public void updateLoginTime(User user) {
        String timeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        user.setLastLogin(timeNow);
        saveToFile();
    }

    private void saveToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            for (User u : users) {
                String line = u.getUsername() + "," + 
                              u.getPassword() + "," + 
                              u.getRole() + "," + 
                              u.isActive() + "," + 
                              u.getLastLogin();
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    boolean active = Boolean.parseBoolean(parts[3]);
                    // Creates the user and adds it to the list
                    User u = new User(parts[0], parts[1], parts[2], active, parts[4]) {
                        @Override
                        public void displayRole() {
                           // Empty implementation just to make it compile
                        }
                    };
                    users.add(u);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}