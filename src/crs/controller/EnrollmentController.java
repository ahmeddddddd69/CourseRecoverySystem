package crs.controller;

import crs.model.Course;
import crs.model.Grade;
import crs.model.Student;

import crs.util.StudentDataHelper;
import crs.util.CourseDataHelper;
import crs.util.GradeDataHelper;
import crs.util.CgpaHelper;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentController {

    // ==============================================================
    //  CHECK ELIGIBILITY (CGPA >= 2.0 AND failedCourses <= 3)
    // ==============================================================
    public boolean isEligible(Student student) {

        if (student == null) return false;

        // Load all data
        ArrayList<Course> allCourses = CourseDataHelper.loadCourses("course_assessment_information.csv");
        ArrayList<Grade> allGrades = GradeDataHelper.loadGrades("student_course_grades.csv");

        if (allCourses == null || allCourses.isEmpty()) {
            System.out.println("[ERROR] No course data found.");
            return false;
        }

        if (allGrades == null || allGrades.isEmpty()) {
            System.out.println("[ERROR] No grade data found.");
            return false;
        }

        // 1️⃣ Calculate CGPA using your real util file
        double cgpa = CgpaHelper.calculateCgpa(student.getStudentId(), allGrades, allCourses);

        // 2️⃣ Count failed courses
        int failedCourses = countFailedCourses(student.getStudentId(), allGrades);

        // 3️⃣ Eligibility Rule
        return cgpa >= 2.0 && failedCourses <= 3;
    }

    // ==============================================================
    //  GET ALL STUDENTS WHO ARE NOT ELIGIBLE
    // ==============================================================
    public List<Student> getStudentsNotEligible() {

        List<Student> result = new ArrayList<>();
        ArrayList<Student> allStudents = StudentDataHelper.loadStudents("student_information.csv");

        for (Student s : allStudents) {
            if (!isEligible(s)) {
                result.add(s);
            }
        }

        return result;
    }

    // ==============================================================
    //  ENROLL STUDENT ONLY IF ELIGIBLE
    // ==============================================================
    public boolean enrollIfEligible(Student student) {

        if (!isEligible(student)) {
            System.out.println("[INFO] Student " + student.getStudentId() + " is NOT eligible.");
            return false;
        }

        System.out.println("[SUCCESS] Student " + student.getStudentId() + " is eligible and enrolled.");
        return true;
    }

    // ==============================================================
    //  COUNT FAILED COURSES BASED ON GRADE POINT (< 2.0 = FAIL)
    // ==============================================================
    private int countFailedCourses(String studentId, ArrayList<Grade> allGrades) {

        int failed = 0;

        for (Grade g : allGrades) {
            if (g.getStudentId().equals(studentId)) {
                if (g.getGradePoint() < 2.0) {  // FAIL threshold
                    failed++;
                }
            }
        }

        return failed;
    }
}
