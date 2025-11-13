package crs.model;

public class AcademicOfficer extends User {

    public AcademicOfficer(String userId, String name, String email,
                           String username, String password) {

        super(userId, name, email, username, password, "Academic Officer");
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Academic Officer");
    }
}
