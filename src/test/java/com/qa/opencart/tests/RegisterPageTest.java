package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void registrationSetup() {
		registerpage=loginpage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getUserRegistrationData(){
		return new Object[][] {
			{"Rajat", "Kumar", "7894561211","Selenium@123", "Yes"},
			{"Amisha", "Singh", "7894561221", "Selenium@123", "no"},
			{"Sunita", "Devi", "789456811", "Selenium@1222", "No"}			
		};
	}
	
	@DataProvider
	public Object[][] getuserRegistrationdataExcel() {
		Object regData[][]=ExcelUtil.getTestData(REGISTER_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider = "getuserRegistrationdataExcel")
	public void userRegistrationTest(String firstName, String lastName, String telephone, String password,
			String subscribe) {
		Assert.assertTrue(registerpage.userRegistration(firstName, lastName, telephone, password,subscribe));
	}

}
