package crs.model;

import java.util.List;

public class AcademicReport {

    private Student student;          // Student information
    private String semester;          // Semester (e.g., "Semester 1")
    private int year;                 // Academic year
    private List<Course> courses;     // Courses taken in this semester
    private List<Grade> grades;       // Grades for those courses

    private double gpa;               // GPA for this semester
    private double cgpa;              // Cumulative GPA

    // ---------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------
    public AcademicReport(Student student, String semester, int year,
                          List<Course> courses, List<Grade> grades,
                          double gpa, double cgpa) {

        this.student = student;
        this.semester = semester;
        this.year = year;
        this.courses = courses;
        this.grades = grades;
        this.gpa = gpa;
        this.cgpa = cgpa;
    }

    // ---------------------------------------------------------
    // Getters
    // ---------------------------------------------------------
    public Student getStudent() {
        return student;
    }

    public String getSemester() {
        return semester;
    }

    public int getYear() {
        return year;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public double getGpa() {
        return gpa;
    }

    public double getCgpa() {
        return cgpa;
    }

    // ---------------------------------------------------------
    // GPA Calculation (optional helper)
    // ---------------------------------------------------------
    public double calculateGpa() {
        double totalPoints = 0;
        int totalCredits = 0;

        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);

            // Find grade for this course
            Grade g = grades.stream()
                    .filter(gr -> gr.getCourseId().equals(c.getCourseId()))
                    .findFirst()
                    .orElse(null);

            if (g != null) {
                totalPoints += g.getGradePoint() * c.getCredits();
                totalCredits += c.getCredits();
            }
        }

        if (totalCredits == 0) return 0;
        this.gpa = totalPoints / totalCredits;
        return this.gpa;
    }

    // ---------------------------------------------------------
    // CGPA Setter (useful if controller calculates it)
    // ---------------------------------------------------------
    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
}
