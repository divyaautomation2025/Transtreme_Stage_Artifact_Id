package com.transtreme.framework.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.transtreme.framework.reports.ExtentManager;
import com.transtreme.framework.reports.PDFExporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayInputStream;

public class Hooks {

    public static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest scenarioTest;

    @Before
    public void beforeScenario(Scenario scenario) {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Initialize ExtentReports
        extent = ExtentManager.getInstance();
        scenarioTest = extent.createTest(scenario.getName());
        scenarioTest.info("🔹 Scenario started: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            scenarioTest.fail("❌ Scenario failed");

            // 📸 Screenshot for Allure
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));

            // 📸 Screenshot for Extent
            scenarioTest.addScreenCaptureFromBase64String(
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64),
                    "Failed Screenshot"
            );
        } else {
            scenarioTest.pass("✅ Scenario passed");
        }

        // Flush Extent report
        extent.flush();

        // Convert Extent HTML to PDF
        String htmlPath = "test-output/ExtentSpark.html";
        PDFExporter.convertExtentHtmlToPdf(htmlPath);

        // Quit browser
        if (driver != null) {
            driver.quit();
        }
    }

    // ✅ Getter for ExtentTest
    public static ExtentTest getExtentTest() {
        return scenarioTest;
    }
}
