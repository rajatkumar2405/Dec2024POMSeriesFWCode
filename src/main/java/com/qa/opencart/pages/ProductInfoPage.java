package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.MEDIUM_DEFAULT_TIMEOUT;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private final By productHeader = By.tagName("h1");
	private final By productImages = By.cssSelector("a.thumbnail");
	private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		String header = eleUtil.waitForElementVisible(productHeader, MEDIUM_DEFAULT_TIMEOUT).getText();
		System.out.println("Product header : " + header);
		return header;
	}

	public int getProductImagesCount() {
		int imageCount = eleUtil.waitForAllElementsVisible(productImages, MEDIUM_DEFAULT_TIMEOUT).size();
		System.out.println("Total number of product images" + imageCount);
		return imageCount;
	}

	public Map<String, String> getProductDetailsMap() {

		//productMap = new HashMap<>();
		productMap = new LinkedHashMap<>();
		//productMap = new TreeMap<>();

		productMap.put("productheader", getProductHeader());
		productMap.put("productimages", String.valueOf(getProductImagesCount()));

		getProductMetaData();
		getProductPriceData();
		System.out.println("Full product details: "+ productMap);
		return productMap;
	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock

	private void getProductMetaData() {

		List<WebElement> metaList = eleUtil.waitForAllElementsVisible(productMetaData, DEFAULT_TIMEOUT);
		for (WebElement e : metaList) {
			String metaData = e.getText();
			String[] meta = metaData.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}

//	$2,000.00
//	Ex Tax: $2,000.00
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.waitForAllElementsVisible(productPriceData, DEFAULT_TIMEOUT);

		String productPrice = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productprice", productPrice);
		productMap.put("extaxprice", exTaxPrice);

	}

}
