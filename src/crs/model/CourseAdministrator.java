package crs.model;

public class CourseAdministrator extends User {

    public CourseAdministrator(String userId, String name, String email, 
                               String username, String password) {
        super(userId, name, email, username, password, "Course Administrator");
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Course Administrator");
    }
}
