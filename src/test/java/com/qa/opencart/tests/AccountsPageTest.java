package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.qa.opencart.constants.AppConstants.*;
import static org.testng.Assert.assertEquals;

import java.util.List;

@Feature("F 60: Opencart Account feature")
@Epic("Epic 200: design Account Page for opencart application")
@Story("US 201: Implement Account page for opencart application")
public class AccountsPageTest extends BaseTest{
	
	
	@BeforeClass
	public void accPageSetup() {
		accpage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("checking opencart account page title")
	@Severity(SeverityLevel.MINOR)
	@Owner("Rajat")
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accpage.getAccPageTitle(), HOME_PAGE_TITLE);
	}
	
	@Description("checking opencart accountpage url")
	@Severity(SeverityLevel.MINOR)
	@Owner("Rajat")
	@Test
	public void accPageURLTest() {
		Assert.assertTrue(accpage.getAccPageURL().contains(HOME_PAGE_FRACTION_URL));
	}
	
	@Description("checking opencart account page header")
	@Severity(SeverityLevel.MINOR)
	@Owner("Rajat")
	@Test
	public void accPageHeaderTest() {
		List<String> accPageHeaderList = accpage.getAccPageHeaders();
		Assert.assertEquals(accPageHeaderList, expectedAccPageHeader);
	}
	

}
