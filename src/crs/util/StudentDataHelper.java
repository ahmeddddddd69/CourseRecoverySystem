/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crs.util;


import crs.model.Student;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ejaz
 */
public class StudentDataHelper {
    public static ArrayList<Student> loadStudents(String path) {
        ArrayList<Student> students = new ArrayList<>();

        List<String> lines = FileHelper.readLines(path);

        // Skip header
        for (int i = 1; i < lines.size(); i++) {
            String[] t = lines.get(i).split(",");

            if (t.length >= 6) {
                Student s = new Student(
                        t[0].trim(),   // StudentID
                        t[1].trim(),   // FirstName
                        t[2].trim(),   // LastName
                        t[3].trim(),   // Major
                        t[4].trim(),   // Year
                        t[5].trim()    // Email
                );
                students.add(s);
            }
        }

        return students;
    }
    
}
