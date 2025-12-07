/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crs.controller;

import crs.model.Course;
import crs.model.Grade;
import java.util.List;
import java.util.ArrayList;
import crs.model.Student;
import crs.util.StudentDataHelper;
import crs.util.CourseDataHelper;
import crs.util.GradeDataHelper;
import crs.util.CgpaHelper;

public class EnrollmentController {

    public boolean isEligible(Student student) {
        if (student == null) {
            return false;
        }
        
        
            // Load courses and grades from helpers
            ArrayList<Course> allCourses = CourseDataHelper.loadCourses("");
            ArrayList<Grade> allGrades = GradeDataHelper.loadGrades("");
            

            if (allCourses == null || allCourses.isEmpty()) {
                System.out.println("No course data found.");
                return false;
            }

            if (allGrades == null || allGrades.isEmpty()) {
                System.out.println("No grade data found.");
                return false;
            }

            // Add student's courses into the report
            ArrayList<Course> studentCourses = new ArrayList<>();
            ArrayList<Grade> studentGrades = new ArrayList<>();

            for (Grade g : allGrades) {
                if (g.getStudentId().equals(student.getStudentId())) {
                    studentGrades.add(g);
                    for (Course c : allCourses) {
                        if (c.getCourseId().equals(g.getCourseId())) {

                            if (!studentCourses.contains(c)) {
                                studentCourses.add(c);
                            }

                        }
                    }
                }
            }
        

        double cgpa = CgpaHelper.calculateCgpa(student.studentId, studentGrades, studentCourses);
        int failedCourses = getFailedCourseCount(student);

        return cgpa >= 2.0 && failedCourses <= 3;
    }

    /**
     * Returns all students who are NOT eligible to progress.
     */
    public List<Student> getStudentsNotEligible() {
        List<Student> result = new ArrayList<>();
        ArrayList<Student> students = StudentDataHelper.loadStudents("");

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
