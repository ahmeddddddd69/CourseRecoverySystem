/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crs.controller;

import java.util.List;
import java.util.ArrayList;
import crs.model.Student;
import crs.util.StudentDataHelper;

public class EnrollmentController {

    private StudentDataHelper studentDataHelper;

    public EnrollmentController() {
        this.studentDataHelper = new StudentDataHelper();
    }

    /**
     * Check if the student meets the progression rules:
     * CGPA >= 2.0
     * Failed courses <= 3
     */
    public boolean isEligible(Student student) {
        if (student == null) {
            return false;
        }

        double cgpa = calculateCGPA(student);
        int failedCourses = getFailedCourseCount(student);

        return cgpa >= 2.0 && failedCourses <= 3;
    }

    /**
     * Returns all students who are NOT eligible to progress.
     */
    public List<Student> getStudentsNotEligible() {
        /*List<Student> students = studentDataHelper.loadAllStudents(); (A recommended function to add to the student data helper util */
        List<Student> result = new ArrayList<>();

        for (Student s : students) {
            if (!isEligible(s)) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Approves enrollment if rules are met.
     */
    public boolean enrollIfEligible(Student student) {
        if (!isEligible(student)) {
            return false;
        }

        // In a real system, you'd persist the change
        // e.g. student.setEnrolled(true);
        /*studentDataHelper.saveStudent(student); (This too) */
        return true;
    }

    // --------------------------------------------------
    // NEW: CGPA CALCULATION METHOD (REPLACES student.calculateCGPA())
    // --------------------------------------------------
    private double calculateCGPA(Student student) {

        // TODO:
        // Replace this placeholder with real grade calculations
        // once assessment or course grade data is integrated.
        
        // For now, return a test CGPA value:
        return 3.00;
    }

    // --------------------------------------------------
    // NEW: Failed Courses Count
    // This can be updated based on your data storage.
    // --------------------------------------------------
    private int getFailedCourseCount(Student student) {

        // TODO:
        // Replace with real logic (reading assessments or course records)

        // For now, assume the student has 0 failed courses.
        return 0;
    }
}
