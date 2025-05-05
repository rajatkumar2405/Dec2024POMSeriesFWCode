package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. private By locator
	final private By email = By.id("input-email");
	final private By password = By.id("input-password");
	final private By loginBtn = By.xpath("//input[@value='Login']");
	final private By forgotPwdLink = By.xpath("//div[@class='form-group']//a[normalize-space()='Forgotten Password']");
	final private By registerLink= By.linkText("Register");

	// 2. public page constructor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. public page actions/methods
	@Step("getting the login page title")
	public String getLoginPageTitle() {

		String title = eleUtil.waitFotTitleIs(LOGIN_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("Login Page Title" + title);
		return title;

	}

	@Step("getting the login page url")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("Login Page URL" + url);
		return url;

	}

	@Step("checking forgot pwd link exists or not")
	public boolean isForgotPwdLinkExists() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}

	@Step("login with valid username: {0} and password: {1}")
	public AccountsPage doLogin(String userName, String pwd) {
		System.out.println("USer credentials: " + userName + ": " + pwd);
		eleUtil.waitForElementVisible(email, DEFAULT_TIMEOUT).sendKeys(userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);

		return new AccountsPage(driver);
	}

	@Step("navigating to user registration page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.clickWhenReady(registerLink, DEFAULT_TIMEOUT);
		return new RegisterPage(driver);
	}

}
