package com.transtreme.framework.runners;

import com.transtreme.framework.reports.PDFExporter;
import org.testng.annotations.AfterSuite;

public class ReportFinalizer {

    @AfterSuite
    public void generatePDFReport() {
        
        
        PDFExporter.convertExtentHtmlToPdf("test-output/ExtentSpark.pdf");

    }
}
