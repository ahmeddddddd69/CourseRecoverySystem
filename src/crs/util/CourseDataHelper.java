/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crs.util;
import crs.model.Assessment;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ejaz
 */
public class CourseDataHelper {
    public static ArrayList<Assessment> loadAssessments(String path) {

        ArrayList<Assessment> list = new ArrayList<>();

        List<String> lines = FileHelper.readLines(path);

        // Skip header
        for (int i = 1; i < lines.size(); i++) {
            String[] t = lines.get(i).split(",");

            if (t.length >= 7) {
                Assessment a = new Assessment(
                        t[0].trim(),               // CourseID
                        t[1].trim(),               // CourseName
                        Integer.parseInt(t[2].trim()), // Credits
                        t[3].trim(),               // Semester
                        t[4].trim(),               // Instructor
                        Integer.parseInt(t[5].trim()), // ExamWeight
                        Integer.parseInt(t[6].trim())  // AssignmentWeight
                );
                list.add(a);
            }
        }

        return list;
    }
}
