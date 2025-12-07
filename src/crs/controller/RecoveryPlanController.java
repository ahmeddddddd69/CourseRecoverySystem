package crs.controller;

import crs.model.RecoveryPlan;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class RecoveryPlanController {
    private List<RecoveryPlan> recoveryPlans;
    private final String FILE_NAME = "recovery_plans.txt";
    
    public RecoveryPlanController() {
        this.recoveryPlans = new ArrayList<>();
        loadFromFile();
    }
    
    public boolean createRecoveryPlan(String studentId, String courseCode) {
        RecoveryPlan plan = new RecoveryPlan(studentId, courseCode);
        recoveryPlans.add(plan);
        saveToFile();
        return true;
    }
    
    public List<RecoveryPlan> getRecoveryPlansByStudent(String studentId) {
        List<RecoveryPlan> result = new ArrayList<>();
        for (RecoveryPlan plan : recoveryPlans) {
            if (plan.getStudentId().equals(studentId)) {
                result.add(plan);
            }
        }
        return result;
    }
    
    public boolean updatePlanStatus(String studentId, String courseCode, String status) {
        RecoveryPlan plan = getRecoveryPlan(studentId, courseCode);
        if (plan != null) {
            plan.setStatus(status);
            saveToFile();
            return true;
        }
        return false; 
    }
    
    private RecoveryPlan getRecoveryPlan(String studentId, String courseCode) {
        for (RecoveryPlan plan : recoveryPlans) {
            if (plan.getStudentId().equals(studentId) && plan.getCourseCode().equals(courseCode)) {
                return plan;
            }
        }
        return null;
    }

    private void saveToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            for (RecoveryPlan plan : recoveryPlans) {
                String line = plan.getStudentId() + "," + 
                              plan.getCourseCode() + "," + 
                              plan.getStatus();
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
                if (parts.length == 3) {
                    RecoveryPlan plan = new RecoveryPlan(parts[0], parts[1]);
                    plan.setStatus(parts[2]);
                    recoveryPlans.add(plan);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}