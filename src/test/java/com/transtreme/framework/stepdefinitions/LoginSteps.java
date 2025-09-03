package com.transtreme.framework.stepdefinitions;

import com.transtreme.framework.hooks.Hooks;
import com.transtreme.framework.pages.LoginPage;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.qameta.allure.Step;

public class LoginSteps {

	private final WebDriver driver;
	private final LoginPage loginPage;
	private final ExtentTest logger;

	public LoginSteps() {
		this.driver = Hooks.driver;
		this.loginPage = new LoginPage(driver);
		this.logger = Hooks.getExtentTest(); // ✅ Extent logger per scenario
	}

	@Step("User navigates to Transtreme login page")
	@Given("the user is on the Transtreme login page")
	public void the_user_is_on_the_transtreme_login_page() {
		loginPage.navigateToLoginPage();
		logger.info("Navigated to login page");
	}

	@Step("User enters email: {0} and password: {1}")
	@When("the user enters email {string} and password {string}")
	public void the_user_enters_email_and_password(String email, String password) {
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		logger.info("Entered email and password");
	}

	@Step("User clicks the Sign In button")
	@And("clicks the Sign In button")
	public void clicks_the_sign_in_button() {
		loginPage.clickSignIn();
		logger.info("Clicked on Sign In");
	}

	@Step("User enters OTP: {0}")
	@And("enters OTP {string}")
	public void enters_otp(String otp) {
		loginPage.enterOtp(otp);
		logger.info("Entered OTP: " + otp);
	}

	@Step("User clicks the Submit button")
	@And("clicks the Submit button")
	public void clicks_the_submit_button() {
		loginPage.clickSubmitButton();
		logger.info("Clicked on Submit");
	}

	@Step("User should land on dashboard page")
	@Then("the user should land on the dashboard")
	public void the_user_should_land_on_the_dashboard() {
		String currentUrl = loginPage.getCurrentUrl();
		boolean isDashboard = currentUrl.contains("/dashboard");
		logger.info("Current URL: " + currentUrl);
		Assert.assertTrue(isDashboard, "User did not land on dashboard. Actual URL: " + currentUrl);
		logger.pass("User successfully landed on dashboard");
	}

	@Step("User clicks Logout button")
	@And("the user clicks the Logout button")
	public void the_user_clicks_the_logout_button() {
		loginPage.logout();
		logger.info("User clicked Logout");
	}

	// Optional utility step (not used in feature steps)
	@Step("User enters credentials - Email: {0}, Password: {1}")
	public void enter_credentials(String email, String password) {
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
	}
}
