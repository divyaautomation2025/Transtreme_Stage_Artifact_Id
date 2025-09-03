package com.transtreme.framework.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
        "com.transtreme.framework.stepdefinitions",
        "com.transtreme.framework.hooks"
    },
    plugin = {
        "pretty",
        "html:target/cucumber-html-report.html",  // optional
        "json:target/cucumber-report.json",       // needed for extent
        "testng:target/cucumber-testng.xml",      // optional
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"  // ✅ only one extent adapter
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}


