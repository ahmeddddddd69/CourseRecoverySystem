/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crs.controller;

import crs.util.EmailSender;
import crs.model.Student;

public class EmailNotifController {

    private EmailSender emailSender;

    public EmailNotifController() {
        this.emailSender = new EmailSender();
    }

    /**
     * Send a generic notification email to a student.
     * UI-triggered function.
     */
    public boolean sendNotification(Student student, String subject, String message) {
        if (student == null || student.getEmail() == null) {
        return false;
    }

    try {
        emailSender.sendEmail(
                student.getEmail(),
                subject,
                message
        );
        return true;   // success
    } catch (Exception ex) {
        System.out.println("Email sending failed: " + ex.getMessage());
        return false;  // failure
    }
}

    /**
     * Send account activation or system login notifications.
     */
    public boolean sendAccountStatusUpdate(Student student, String statusMessage) {
        String subject = "Account Status Update";
        return sendNotification(student, subject, statusMessage);
    }

    /**
     * Send course recovery plan notification.
     */
    public boolean sendRecoveryPlanNotification(Student student, String planDetails) {
        String subject = "Course Recovery Plan Enrollment";
        return sendNotification(student, subject, planDetails);
    }
}
