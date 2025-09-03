package com.transtreme.framework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance("test-output/ExtentSpark.html"); // Default location
        }
        return extent;
    }

    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter reporter = new ExtentSparkReporter(fileName);
        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("Transtreme Stage Automation Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Divya Porwal");
        return extent;
    }
}
