package crs.controller;

import crs.model.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.LocalDateTime;

public class UserController1 {

    private List<User> users = new ArrayList<>();
    private final String FILE_NAME = "users.txt"; 

    public UserController1() {
        loadFromFile();
    }

    // =============================
    //  GET USER BY USERNAME
    // =============================
    public User getUser(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    // =============================
    //  VALIDATE LOGIN
    // =============================
    public boolean validateUser(String username, String password) {
        User user = getUser(username);

        if (user != null && user.getPassword().equals(password)) {
            updateLoginBinary(user);
            saveToFile();
            return true;
        }
        return false;
    }

    // =============================
    //  UPDATE LOGIN TIMESTAMP (BINARY)
    // =============================
    private void updateLoginBinary(User user) {
        String now = LocalDateTime.now().toString();
        user.setLastLoginBinary(toBinary(now));
    }

    // =============================
    //  UPDATE LOGOUT TIMESTAMP (BINARY)
    // =============================
    public void updateLogoutBinary(User user) {
        String now = LocalDateTime.now().toString();
        user.setLastLogoutBinary(toBinary(now));
        saveToFile();
    }

    // =============================
    //  CONVERT TEXT → BINARY
    // =============================
    private String toBinary(String text) {
        StringBuilder binary = new StringBuilder();
        for (char c : text.toCharArray()) {
            binary.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0')).append(" ");
        }
        return binary.toString().trim();
    }

    // =============================
    //  SAVE USERS TO FILE
    // =============================
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User u : users) {
                String line = u.getUsername() + "," +
                              u.getPassword() + "," +
                              u.getRole() + "," +
                              u.getLastLoginBinary() + "," +
                              u.getLastLogoutBinary();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    // =============================
    //  LOAD USERS FROM FILE
    // =============================
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",");

                if (p.length == 5) {
                    String username = p[0];
                    String password = p[1];
                    String role = p[2];
                    String lastLogin = p[3];
                    String lastLogout = p[4];

                    User u;

                    if (role.equals("AcademicOfficer")) {
                        u = new AcademicOfficer(username, password, lastLogin, lastLogout);
                    } else {
                        u = new CourseAdministrator(username, password, lastLogin, lastLogout);
                    }

                    users.add(u);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users: " + e.getMessage());
        }
    }
}
