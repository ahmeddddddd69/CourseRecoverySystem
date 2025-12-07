/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crs.controller;

import crs.model.AcademicReport;
import crs.model.Course;
import crs.model.Student;
import crs.model.Grade;

import crs.util.PdfGenerator;
import crs.util.CgpaHelper;
import crs.util.CourseDataHelper;
import crs.util.GradeDataHelper;

import java.util.*;

public class AcademicReportController {

    public AcademicReportController() {}

    
    
    public boolean generateReport(Student student, String semester, int year) {

        if (student == null) {
            System.out.println("Student is null.");
            return false;
        }

        try {
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
            List<Course> studentCourses = new ArrayList<>();
            List<Grade> studentGrades = new ArrayList<>();

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

            // Create AcademicReport object
            AcademicReport report = new AcademicReport(student, semester, year, studentCourses, studentGrades, 0);
            //Calculate CGPA 
            double cgpa = CgpaHelper.calculateCgpa(
                    student.getStudentId(),
                    allGrades,
                    allCourses
            );

            report.setCgpa(cgpa);

            // Call PDF Generator
            PdfGenerator.generateAcademicReport(
                    report,
                    parseSemesterNumber(semester),
                    year
            );

            System.out.println("Academic Report generated successfully for student " 
                               + student.getStudentId());

            return true;

        } catch (Exception e) {
            System.out.println("Report generation failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Extracts semester number from something like "Semester 2"
     */
    private int parseSemesterNumber(String semester) {
        String numeric = semester.replaceAll("[^0-9]", "");
        return Integer.parseInt(numeric);
    }
}
