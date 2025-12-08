package crs.controller;

import crs.model.*;
import crs.util.*;

import java.util.ArrayList;
import java.util.List;

public class AcademicReportController {

    public AcademicReportController() {}

    public boolean generateReport(Student student, String semester, int year) {

        if (student == null) {
            System.out.println("[ERROR] Student is null.");
            return false;
        }

        // 1. Load all courses
        ArrayList<Course> allCourses = CourseDataHelper.loadCourses("course_assessment_information.csv");

        // 2. Load all grades
        ArrayList<Grade> allGrades = GradeDataHelper.loadGrades("student_course_grades.csv");

        if (allCourses == null || allCourses.isEmpty()) {
            System.out.println("[ERROR] No course data found.");
            return false;
        }

        if (allGrades == null || allGrades.isEmpty()) {
            System.out.println("[ERROR] No grade data found.");
            return false;
        }

        // 3. Filter grades for this student
        List<Grade> studentGrades = new ArrayList<>();
        for (Grade g : allGrades) {
            if (g.getStudentId().equals(student.getStudentId())) {
                studentGrades.add(g);
            }
        }

        // 4. Get courses the student has taken
        List<Course> studentCourses = new ArrayList<>();
        for (Grade g : studentGrades) {
            for (Course c : allCourses) {
                if (c.getCourseId().equals(g.getCourseId())) {
                    if (!studentCourses.contains(c)) {
                        studentCourses.add(c);
                    }
                }
            }
        }

        // 5. Calculate CGPA using your actual method
        double cgpa = CgpaHelper.calculateCgpa(
                student.getStudentId(),
                allGrades,
                allCourses
        );

        // 6. Create the report object
        AcademicReport report = new AcademicReport(
                student,
                semester,
                year,
                studentCourses,
                studentGrades,
                cgpa
        );

        // 7. Generate PDF
        PdfGenerator.generateAcademicReport(
                report,
                parseSemesterNumber(semester),
                year
        );

        System.out.println("[SUCCESS] Academic Report generated for " + student.getStudentId());
        return true;
    }

    // Convert "Semester 1" → 1
    private int parseSemesterNumber(String semester) {
        String numeric = semester.replaceAll("[^0-9]", "");
        return Integer.parseInt(numeric);
    }
}
