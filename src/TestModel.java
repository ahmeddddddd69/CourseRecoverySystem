import crs.model.*;

public class TestModel {
    public static void main(String[] args) {

        // ------------------------
        // Test Course Administrator
        CourseAdministrator admin = new CourseAdministrator(
                "CA001", "Duke Admin", "admin@apu.edu",
                "adminUser", "admin123"
        );
        admin.displayRole();
        System.out.println("Admin Email: " + admin.getEmail());

        // ------------------------
        // Test Academic Officer
        AcademicOfficer officer = new AcademicOfficer(
                "AO001", "Oroni Saha", "oroni@apu.edu",
                "oroniUser", "oroni123"
        );
        officer.displayRole();
        System.out.println("Officer Username: " + officer.getUsername());

        // ------------------------
        // Test Recovery Task
        RecoveryTask task = new RecoveryTask(1, "Review lecture topics");
        System.out.println("Recovery Task: " + task.getDescription() + " | Week " + task.getWeek());

        // ------------------------
        // Test Recovery Plan
        RecoveryPlan plan = new RecoveryPlan("S001", "C205");
        plan.addTask(task);
        System.out.println("Recovery Plan for Student: " + plan.getStudentId() +
                " | First Task: " + plan.getTasks().get(0).getDescription());

        // ------------------------
        // Test Academic Report
        Student student = new Student("S001", "Fiona", "Smith",
                "Computer Science", "Senior", "fiona@apu.edu");

        Course course = new Course("C205", "Mathematics", 4, "Fall", "Prof. Chen");

        AcademicReport report = new AcademicReport(student, "Semester 1");
        report.addCourse(course);
        report.setGpa(3.50);
        report.setCgpa(3.25);

        System.out.println("Academic Report for: " + report.getStudent().getFirstName() +
                " | GPA: " + report.getGpa() + " | CGPA: " + report.getCgpa());
    }
}
