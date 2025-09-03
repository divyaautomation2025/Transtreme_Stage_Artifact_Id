package com.transtreme.framework.reports;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class PDFExporter {

    public static void convertExtentHtmlToPdf(String outputPdfPath) {
        String inputHtmlPath = "test-output/ExtentSpark.html";
        File htmlFile = new File(inputHtmlPath);

        if (!htmlFile.exists()) {
            System.err.println("❌ HTML report not found at: " + inputHtmlPath);
            return;
        }

        try {
            // 🔄 Wait until file is not empty (max 5 sec)
            int retries = 0;
            while (htmlFile.length() == 0 && retries < 10) {
                Thread.sleep(500);
                retries++;
            }

            if (htmlFile.length() == 0) {
                System.err.println("❌ HTML file is empty, skipping PDF conversion.");
                return;
            }

            String htmlContent = Files.readString(htmlFile.toPath());

            // Fix self-closing tags
            String[] voidTags = {"link", "meta", "img", "br", "hr", "input"};
            for (String tag : voidTags) {
                htmlContent = htmlContent.replaceAll("(<" + tag + "[^>]*)(?<!/)>", "$1/>");
            }

            // Replace unsupported entities
            htmlContent = htmlContent.replace("&middot;", "·");
            htmlContent = htmlContent.replace("&nbsp;", " ");

            try (OutputStream os = new FileOutputStream(outputPdfPath)) {
                PdfRendererBuilder builder = new PdfRendererBuilder();
                builder.useFastMode();
                builder.withHtmlContent(htmlContent, htmlFile.toURI().toString());
                builder.toStream(os);
                builder.run();
            }

            System.out.println("✅ PDF successfully created at: " + outputPdfPath);
        } catch (Exception e) {
            System.err.println("❌ PDF conversion failed: " + e.getMessage());
        }
    }

    public static void convertExtentHtmlToPdf() {
        convertExtentHtmlToPdf("test-output/ExtentReport.pdf");
    }
}
