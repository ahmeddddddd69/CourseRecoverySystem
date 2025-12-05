package crs.util;

import crs.model.Course;
import java.util.ArrayList;
import java.util.List;

public class CourseDataHelper {

    public static ArrayList<Course> loadCourses(String path) {

        ArrayList<Course> courses = new ArrayList<>();
        List<String[]> rows = FileHelper.readCSV(path);

        for (String[] r : rows) {
            if (r.length < 7) continue;

            courses.add(new Course(
                    r[0].trim(),                     // CourseID
                    r[1].trim(),                     // CourseName
                    Integer.parseInt(r[2].trim()),   // Credits
                    r[3].trim(),                     // Semester
                    r[4].trim(),                     // Instructor
                    Integer.parseInt(r[5].trim()),   // ExamWeight
                    Integer.parseInt(r[6].trim())    // Assignment Weight
            ));
        }

        return courses;
    }
}