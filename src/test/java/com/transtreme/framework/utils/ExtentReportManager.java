package com.transtreme.framework.utils;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getInstance() throws IOException {
        // Minimal HTML version (suitable for PDF conversion)
        ExtentSparkReporter basic = new ExtentSparkReporter("test-output/ExtentBasic.html");

        basic.config().setDocumentTitle("Extent Report - PDF Ready");
        basic.config().setReportName("Automation Report - Transtreme Stage");

        extentReports.attachReporter(basic); // ✅ Use only basic version
        return extentReports;
    }
}
