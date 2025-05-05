package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.qa.opencart.constants.AppConstants.*;

@Feature("F 50: Opencart Login feature")
@Epic("Epic 100: design Login Page for opencart application")
@Story("US 101: Implement login page for opencart application")
public class LoginPageTest extends BaseTest{
	
	@Description("checking opencart login title...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Rajat")
	@Test
	public void loginPageTitleTest() {
		String actTitle= loginpage.getLoginPageTitle();
		ChainTestListener.log("checking login page title" + actTitle);
		Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE);

	}
	
	@Description("checking opencart login url")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Rajat")
	@Test
	public void loginPageURLTest() {
		
		String actURL= loginpage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(LOGIN_PAGE_FRACTION_URL));
		
	}
	
	@Description("checking opencart has forgot password link")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Rajat")
	@Test
	public void forgotPwdLinkExistsTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExists());
	}
	
	
	@Description("check user is able to login with valid user credentials ")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Rajat")
	@Test(priority=Short.MAX_VALUE)
	public void loginTest() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accpage.getAccPageTitle(), HOME_PAGE_TITLE);
	}
	
	@Test(enabled = true, description = "WIP--forgot pwd check")
	public void forgetPwd() {
		System.out.println("forgotPwd");
	}
	

}
