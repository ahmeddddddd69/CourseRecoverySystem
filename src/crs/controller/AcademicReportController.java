/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crs.controller;

import crs.model.AcademicReport;
import crs.model.Course;
import crs.model.Student;
import crs.util.PdfGenerator;
import java.util.ArrayList;

public class AcademicReportController {

    public AcademicReportController() {}

    /**
     * Generates a complete academic report object, fills it up,
     * and sends it to the PDF generator.
     *
     * @param student the student object
     * @param semester the semester name (e.g., "Semester 1")
     * @param year the academic year (e.g., 2025)
     * @return true if the report was generated successfully
     */
    public boolean generateReport(Student student, String semester, int year) {

        if (student == null) {
            return false;
        }

        try {
            // STEP 1: Create AcademicReport object
            AcademicReport report = new AcademicReport(student, semester);

            // STEP 2: Load courses for this student (placeholder)
            // This should be replaced with real data lookup from your helpers
            ArrayList<Course> enrolledCourses = loadCoursesForStudent(student);

            for (Course c : enrolledCourses) {
                report.addCourse(c);
            }

            // STEP 3: Calculate GPA and CGPA (placeholder logic)
            double gpa = calculateGPA(enrolledCourses);
            double cgpa = calculateCGPA(enrolledCourses);

            report.setGpa(gpa);
            report.setCgpa(cgpa);

            // STEP 4: Call PDF Generator
            PdfGenerator.generateAcademicReport(report, parseSemesterNumber(semester), year);

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

    /**
     * Placeholder: load the student's courses.
     * Replace this with actual persistence (binary/CSV).
     */
    private ArrayList<Course> loadCoursesForStudent(Student s) {
        ArrayList<Course> list = new ArrayList<>();

        // Sample placeholder data
        list.add(new Course("CS101", "Intro to CS", 3, "Semester 1", "Dr. Ahmed"));
        list.add(new Course("CS202", "Object Oriented Programming", 3, "Semester 1", "Prof. Elaine"));

        return list;
    }

    /**
     * Placeholder GPA calculation
     */
    private double calculateGPA(ArrayList<Course> courses) {
        // Real logic would use assessments/grades.
        return 3.50;
    }

    /**
     * Placeholder CGPA calculation
     */
    private double calculateCGPA(ArrayList<Course> courses) {
        return 3.40;
    }
}
