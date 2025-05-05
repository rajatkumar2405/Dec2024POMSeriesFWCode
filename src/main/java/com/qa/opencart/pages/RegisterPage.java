package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtils;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	final private By firstName = By.id("input-firstname");
	final private By lastName = By.id("input-lastname");
	final private By email = By.id("input-email");
	final private By telephone = By.id("input-telephone");
	final private By password = By.id("input-password");
	final private By confirmpassword = By.id("input-confirm");

	final private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[1]/input[@type='radio']");
	final private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[2]/input[@type='radio']");

	final private By agreeCheckBox = By.name("agree");
	final private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	final private By successMesg = By.cssSelector("div#content h1");
	final private By logoutLink = By.linkText("Logout");
	final private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean userRegistration(String firstName, String lastName, String telephone, String password,
			String subscribe) {

		eleUtil.waitForElementVisible(this.firstName, DEFAULT_TIMEOUT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, StringUtils.getRandomEmailId());
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		if(eleUtil.waitForElementVisible(successMesg, DEFAULT_TIMEOUT).getText().contains(REGISTER_SUCCESS_MESG)){
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		else
			return false;

	}

}
