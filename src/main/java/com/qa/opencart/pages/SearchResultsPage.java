package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private final By resultsProduct= By.cssSelector("div.product-thumb");
	
	
	public SearchResultsPage(WebDriver driver) {
			this.driver = driver;
			eleUtil= new ElementUtil(driver);
	}
	
	@Step("getting the product count on results page")
	public int getResultsProductCount() {
		int searchCount= eleUtil.waitForAllElementsVisible(resultsProduct, DEFAULT_TIMEOUT).size();
		System.out.println("Total no of serach count: "+ searchCount);
		return searchCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Product name: "+ productName);
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
	
	

}
