package crs.model;

import java.util.ArrayList;

public class AcademicReport {

    private Student student;
    private String semester;
    private ArrayList<Course> courses = new ArrayList<>();
    private double gpa;
    private double cgpa;

    public AcademicReport(Student student, String semester) {
        this.student = student;
        this.semester = semester;
    }

    public void addCourse(Course c) { courses.add(c); }

    // Setters
    public void setGpa(double gpa) { this.gpa = gpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    // Getters
    public Student getStudent() { return student; }
    public String getSemester() { return semester; }
    public ArrayList<Course> getCourses() { return courses; }
    public double getGpa() { return gpa; }
    public double getCgpa() { return cgpa; }
}
