package crs.model;

import java.util.List;

public class AcademicReport {

    private Student student;
    private List<Grade> grades;
    private double cgpa;

    public AcademicReport(Student student, List<Grade> grades, double cgpa) {
        this.student = student;
        this.grades = grades;
        this.cgpa = cgpa;
    }

    public Student getStudent() { 
        return student; 
    }

    public List<Grade> getGrades() { 
        return grades; 
    }

    public double getCgpa() { 
        return cgpa; 
    }
}
