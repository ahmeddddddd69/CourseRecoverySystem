package crs.util;

import crs.model.*;
import java.util.ArrayList;

public class TestUtil {

    public static void main(String[] args) {
        
        //for the controller guys pls check the tests to see if the pdf and email work esp, uncomment to test them :)



         //testLoadStudents();
         //testLoadAssessments();
         //testPdfGeneration();
       //testEmailSending();
        //testDateHelper();
         //testValidation();
         //testPdfPath();
    }

    // ----------------------------------------------------------
    // 1. TEST STUDENT CSV LOADING
    // ----------------------------------------------------------
    public static void testLoadStudents() {
        System.out.println("=== TEST: Loading Students ===");

        ArrayList<Student> students =
                StudentDataHelper.loadStudents("data/student_information.csv");

        for (Student s : students) {
            System.out.println(s.getStudentId() + " - " +
                    s.getFirstName() + " " + s.getLastName());
        }
    }

    // ----------------------------------------------------------
    // 2. TEST COURSE ASSESSMENTS CSV LOADING
    // ----------------------------------------------------------
    public static void testLoadAssessments() {
        System.out.println("=== TEST: Loading Assessments ===");

        ArrayList<Assessment> assessments =
                CourseDataHelper.loadAssessments("data/course_assessment_information.csv");

        for (Assessment a : assessments) {
            System.out.println(a.getCourseId() + " - " + a.getCourseName() +
                    " (" + a.getCredits() + " credits)");
        }
    }

    // ----------------------------------------------------------
    // 3. TEST PDF GENERATION
    // ----------------------------------------------------------
    public static void testPdfGeneration() {
        System.out.println("=== TEST: Generating Academic Report PDF ===");

        Student student = new Student(
                "S001",
                "Ali",
                "Rahman",
                "Computer Science",
                "Year 2",
                "ali@example.com"
        );

        AcademicReport report = new AcademicReport(student, "Semester 1");      
        report.setYear(2024);
        report.setGpa(3.25);
        report.setCgpa(3.40);

        // Dummy courses for testing
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("CT001", "Programming", 3, "Sem 1", "Dr. Lim"));
        courses.add(new Course("CT002", "Database", 4, "Sem 1", "Dr. Tan"));

        report.setCourses(courses);

        PdfGenerator.generateAcademicReport(report, 1, 2024);
    }

    // ----------------------------------------------------------
    // 4. TEST EMAIL SENDING
    // ----------------------------------------------------------
    public static void testEmailSending() {
        System.out.println("=== TEST: Sending Email ===");

        EmailSender.sendEmail(
                "yourEmail@gmail.com",
                "CRS Test Email",
                "Hello! This is a test email from CRS TestUtil."
        );
    }

    // ----------------------------------------------------------
    // 5. TEST DATE HELPER
    // ----------------------------------------------------------
    public static void testDateHelper() {
        System.out.println("=== TEST: Date Helper ===");
        System.out.println("Current timestamp: " + DateHelper.now());
    }

    // ----------------------------------------------------------
    // 6. TEST VALIDATION
    // ----------------------------------------------------------
    public static void testValidation() {
        System.out.println("=== TEST: Validation Helper ===");

        System.out.println("Valid email (abc@gmail.com)? " +
                ValidationHelper.isValidEmail("abc@gmail.com"));

        System.out.println("Empty string? " +
                ValidationHelper.isEmpty(""));
    }

    // ----------------------------------------------------------
    // 7. TEST PDF PATH GENERATION
    // ----------------------------------------------------------
    public static void testPdfPath() {
        System.out.println("=== TEST: PDF Path Helper ===");
        String path = PdfPathHelper.generateReportPath("S001", 1, 2024);
        System.out.println(path);
    }
}

