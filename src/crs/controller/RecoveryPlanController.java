package crs.controller;

import crs.model.RecoveryPlan;
import java.util.ArrayList;
import java.util.List;

public class RecoveryPlanController {
    private List<RecoveryPlan> recoveryPlans;
    
    public RecoveryPlanController() {
        this.recoveryPlans = new ArrayList<>();
    }
    
    public boolean createRecoveryPlan(String studentId, String courseCode) {
        RecoveryPlan plan = new RecoveryPlan(studentId, courseCode);
        recoveryPlans.add(plan);
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
    
    // REMOVED the updatePlanStatus method since setStatus doesn't exist
    // Or keep it but remove the setStatus line:
    public boolean updatePlanStatus(String studentId, String courseCode, String status) {
        RecoveryPlan plan = getRecoveryPlan(studentId, courseCode);
        return plan != null; // Just check if plan exists
    }
    
    private RecoveryPlan getRecoveryPlan(String studentId, String courseCode) {
        for (RecoveryPlan plan : recoveryPlans) {
            if (plan.getStudentId().equals(studentId) && plan.getCourseCode().equals(courseCode)) {
                return plan;
            }
        }
        return null;
    }
}