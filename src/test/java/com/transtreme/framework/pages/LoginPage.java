package com.transtreme.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

	private final WebDriver driver;
	private final WebDriverWait wait;

	// Fixed locators (as per your request)
	private final By emailField = By.xpath("//input[@type='email']");
	private final By passwordField = By.xpath("//input[@type='password' and not(@id='inputOTP')]");
	private final By signInButton = By.xpath("//button[contains(.,'Sign In')]");
	private final By otpField = By.xpath("//input[@id='inputOTP']");
	private final By submitButton = By.xpath("//button[.//span[normalize-space()='Submit']]");
	private final By avatarIcon = By.xpath("//img[contains(@class,'avatar-img')]");
	private final By logoutButton = By.xpath("//a[normalize-space()='Logout' and contains(@class, 'dropdown-item')]");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void navigateToLoginPage() {
		driver.get("https://transtreme-stage.curiticshealth.com/login");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	public void enterEmail(String email) {
		WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
		emailInput.clear();
		emailInput.sendKeys(email);
	}

	public void enterPassword(String password) {
		WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
		passwordInput.clear();
		passwordInput.sendKeys(password);
	}

	public void clickSignIn() {
		WebElement signInBtn = wait.until(ExpectedConditions.elementToBeClickable(signInButton));
		signInBtn.click();
	}

	public void enterOtp(String otp) {
		WebElement otpInput = wait.until(ExpectedConditions.visibilityOfElementLocated(otpField));
		otpInput.clear();
		otpInput.sendKeys(otp);
	}

	public void clickSubmitButton() {
		WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		submitBtn.click();
		wait.until(ExpectedConditions.urlContains("/dashboard")); // optional but recommended
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void logout() {
		WebElement avatar = wait.until(ExpectedConditions.elementToBeClickable(avatarIcon));
		avatar.click();
		WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		logout.click();
	}

}
