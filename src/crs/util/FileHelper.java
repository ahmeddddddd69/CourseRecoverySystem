package crs.util;

import java.io.*;
import java.util.*;

public class FileHelper {

    // -------------------------
    // 1. Read entire file (your original)
    // -------------------------
    public static List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }

    // -------------------------
    // 2. Write a line (your original)
    // -------------------------
    public static void writeLine(String path, String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(data);
            bw.newLine();
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // -------------------------
    // 3. NEW : CSV READER (Required by CourseDataHelper, GradeDataHelper)
    // -------------------------
    public static List<String[]> readCSV(String path) {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            boolean skipHeader = true;

            while ((line = br.readLine()) != null) {

                // Skip header row
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                if (line.trim().isEmpty()) continue;

                // Split CSV into array
                rows.add(line.split(","));
            }
        } catch (Exception e) {
            System.out.println("Error reading CSV: " + path + " → " + e.getMessage());
        }

        return rows;
    }
}
