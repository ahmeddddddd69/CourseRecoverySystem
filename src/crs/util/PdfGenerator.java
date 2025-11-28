/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crs.util;
import crs.model.AcademicReport;
import crs.model.Course;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
/**
 *
 * @author ejaz
 */
public class PdfGenerator {
     public static void generateAcademicReport(AcademicReport report, int sem, int year) {

        try {
            String path = PdfPathHelper.generateReportPath(
                    report.getStudent().getStudentId(), sem, year);

            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.open();

            // Title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            doc.add(new Paragraph("Academic Performance Report", titleFont));
            doc.add(new Paragraph(" "));

            // Student Info
            doc.add(new Paragraph("Student Name : " +
                    report.getStudent().getFirstName() + " " +
                    report.getStudent().getLastName()));
            doc.add(new Paragraph("Student ID   : " +
                    report.getStudent().getStudentId()));
            doc.add(new Paragraph("Program      : " +
                    report.getStudent().getMajor()));
            doc.add(new Paragraph("Semester     : " + report.getSemester()));
            doc.add(new Paragraph(" "));

            // Table Header
            PdfPTable table = new PdfPTable(5);
            table.addCell("Course Code");
            table.addCell("Course Title");
            table.addCell("Credit Hours");
            table.addCell("Grade");
            table.addCell("Grade Point");

            for (Course c : report.getCourses()) {
                table.addCell(c.getCourseId());
                table.addCell(c.getCourseName());
                table.addCell(String.valueOf(c.getCredits()));
                table.addCell("N/A");         // Grades can be added later
                table.addCell("N/A");         // GP calculation optional
            }

            doc.add(table);
            doc.add(new Paragraph(" "));

            // CGPA
            // title of the pdf fonts, etc
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            doc.add(new Paragraph("Academic Performance Report", titleFont));
            doc.add(new Paragraph(" "));

            // Student informationnnnn
            doc.add(new Paragraph("Student Name : " +
                    report.getStudent().getFirstName() + " " +
                    report.getStudent().getLastName()));
            doc.add(new Paragraph("Student ID   : " +
                    report.getStudent().getStudentId()));
            doc.add(new Paragraph("Program      : " +
                    report.getStudent().getMajor()));
            doc.add(new Paragraph("Semester     : " + report.getSemester()));
            doc.add(new Paragraph(" "));

            // Table headerrrr
            PdfPTable table = new PdfPTable(5);
            table.addCell("Course Code");
            table.addCell("Course Title");
            table.addCell("Credit Hours");
            table.addCell("Grade");
            table.addCell("Grade Point");

            for (Course c : report.getCourses()) {
                table.addCell(c.getCourseId());
                table.addCell(c.getCourseName());
                table.addCell(String.valueOf(c.getCredits()));
                table.addCell("N/A");         // Grades can be added later
                table.addCell("N/A");         // GP calculation optional
            }

            doc.add(table);
            doc.add(new Paragraph(" "));

            // gradess
            doc.add(new Paragraph("GPA  : " + report.getGpa()));
            doc.add(new Paragraph("CGPA : " + report.getCgpa()));

            doc.close();
            System.out.println("PDF Generated Successfully → " + path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
