package crs.model;

public class Grade {

    private String studentId;
    private String courseId;
    private String gradeLetter;
    private double gradePoint;

    public Grade(String studentId, String courseId, String gradeLetter, double gradePoint) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.gradeLetter = gradeLetter;
        this.gradePoint = gradePoint;
    }

    public String getStudentId() { return studentId; }
    public String getCourseId() { return courseId; }
    public String getGradeLetter() { return gradeLetter; }
    public double getGradePoint() { return gradePoint; }
}
