/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crs.util;

import crs.model.Student;
import crs.model.Assessment;
import crs.model.AcademicReport;
import crs.model.Course;

import java.util.ArrayList;

/**
 *
 * @author ahmed and ejazzzz
 */
public class TestUtil {
    
    public static void main(String[] args) {

        

        //testLoadStudents();
        // testLoadCourses();
        // testPdfGeneration();
        // testEmailSending();
        // testDateHelper();
        // testValidation();
        // testPdfPathHelper();
    }

    // ----------------------------------------------------------
    // 1. TEST STUDENT CSV LOADING
    // ----------------------------------------------------------
    public static void testLoadStudents() {
        System.out.println("=== TEST: Loading Students ===");

        ArrayList<Student> list = StudentDataHelper.loadStudents("src/data/student_information.csv");

        for (Student s : list) {
            System.out.println(s.getStudentId() + " - " + s.getFirstName() + " " + s.getLastName());
        }
    }

    // ----------------------------------------------------------
    // 2. TEST COURSE / ASSESSMENT CSV LOADING
    // ----------------------------------------------------------
    public static void testLoadCourses() {
        System.out.println("=== TEST: Loading Course Assessments ===");

        ArrayList<Assessment> list = CourseDataHelper.loadAssessments("src/data/course_assessment_information.csv");

        for (Assessment a : list) {
            System.out.println(a.getCourseId() + " - " + a.getCourseName() + 
                               " (" + a.getCredits() + " credits)");
        }
    }

    // ----------------------------------------------------------
    // 3. TEST PDF GENERATION
    // ----------------------------------------------------------
    public static void testPdfGeneration() {
        System.out.println("=== TEST: Generating Academic Report PDF ===");

        Student s = new Student(
                "S001",
                "Ali",
                "Rahman",
                "Computer Science",
                "Year 2",
                "ali@example.com"
        );

        AcademicReport report = new AcademicReport();
        report.setStudent(s);
        report.setSemester(1);
        report.setYear(2024);
        report.setGpa(3.21);
        report.setCgpa(3.40);

        ArrayList<Course> dummyCourses = new ArrayList<>();
        dummyCourses.add(new Course("CT0101", "Java Programming", 3, "Semester 1", "Dr. Shila"));
        dummyCourses.add(new Course("CT0110", "Database Systems", 4, "Semester 1", "Dr. Nisha"));
        report.setCourses(dummyCourses);

        PdfGenerator.generateAcademicReport(report, 1, 2024);
    }

    // ----------------------------------------------------------
    // 4. TEST EMAIL SENDING
    // ----------------------------------------------------------
    public static void testEmailSending() {
        System.out.println("=== TEST: Sending Test Email ===");

        EmailSender.sendEmail(
                "yourEmail@gmail.com",
                "CRS Test Email",
                "This is a test email from TestUtil."
        );
    }

    // ----------------------------------------------------------
    // 5. TEST DATE HELPER
    // ----------------------------------------------------------
    public static void testDateHelper() {
        System.out.println("=== TEST: Date Helper ===");
        System.out.println("Current Timestamp: " + DateHelper.now());
    }

    // ----------------------------------------------------------
    // 6. TEST VALIDATION
    // ----------------------------------------------------------
    public static void testValidation() {
        System.out.println("=== TEST: Validation Helper ===");
        System.out.println("Valid email? " + ValidationHelper.isValidEmail("abc@gmail.com"));
        System.out.println("Empty string? " + ValidationHelper.isEmpty(""));
    }

    // ----------------------------------------------------------
    // 7. TEST PDF PATH HELPER
    // ----------------------------------------------------------
    public static void testPdfPathHelper() {
        System.out.println("=== TEST: PDF Path Helper ===");
        String path = PdfPathHelper.generateReportPath("S001", 1, 2024);
        System.out.println("Generated Path: " + path);
    }
   
    }
