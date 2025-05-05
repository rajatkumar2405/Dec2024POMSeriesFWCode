package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class SearchTest extends BaseTest{
	
	@BeforeClass
	public void searchSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Description("Checking search feature test..")
	@Severity(SeverityLevel.MINOR)
	@Owner("Rajat")
	@Test
	public void searchTest() {
		searchResultsPage=accpage.doSearch("macbook");
		int actualResultsCount= searchResultsPage.getResultsProductCount();
		Assert.assertEquals(actualResultsCount, 3);
	}

}
