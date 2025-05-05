package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_TITLE;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private final By headers = By.cssSelector("div#content>h2");
	private final By search= By.name("search");
	private final By searchIcon= By.cssSelector(".input-group-btn>button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	@Step("getting the account page title")
	public String getAccPageTitle() {
		String title = eleUtil.waitFotTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("Home Page Title" + title);
		return title;

	}
	@Step("getting the account page url")
	public String getAccPageURL() {
		String url = eleUtil.waitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("Home Page URL" + url);
		return url;

	}

	@Step("getting the account page headers")
	public List<String> getAccPageHeaders() {
		List<WebElement> headerList = eleUtil.getElements(headers);
		List<String> headerValueList = new ArrayList<>();
		for (WebElement e : headerList) {
			String text = e.getText();
			headerValueList.add(text);

		}
		System.out.println("Acc Page headers List :"+ headerValueList);
		return headerValueList;
	}
	
	@Step("perform serach: {0}")
	public SearchResultsPage doSearch(String searchKey) {
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}

}
