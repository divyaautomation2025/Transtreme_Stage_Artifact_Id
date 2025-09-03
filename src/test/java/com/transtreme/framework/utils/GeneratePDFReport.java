package com.transtreme.framework.utils;

import com.transtreme.framework.reports.PDFExporter;

public class GeneratePDFReport {
    public static void main(String[] args) {
        try {
            PDFExporter.convertExtentHtmlToPdf("test-output/ExtentSpark.pdf");
            System.out.println("✅ PDF report generated successfully.");
        } catch (Exception e) {
            System.err.println("❌ Failed to generate PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
