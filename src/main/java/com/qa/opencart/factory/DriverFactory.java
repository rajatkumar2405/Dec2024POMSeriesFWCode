package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.exceptions.BrowserExceptions;
import com.qa.opencart.exceptions.FrameworkException;

import io.qameta.allure.Step;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static String highlight;
	public static final Logger log= LogManager.getLogger(DriverFactory.class);

	/**
	 * This method is used to initialize the driver on the basis of giver browser
	 * name
	 * 
	 * @param browserName
	 * @return
	 */
	@Step("init the driver with: {0} ")
	public WebDriver initDriver(Properties prop) {
		
		log.info("properties"+ prop);
		

		String browserName = prop.getProperty("browser");
		
		ChainTestListener.log("Browser Name is :"+ browserName);

		//System.out.println("Browser Name is :" + browserName);
		log.info("Browser Name is : "+browserName);
		optionsManager = new OptionsManager(prop);

		highlight = prop.getProperty("highlight");

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			break;

		default:
			//System.out.println("Please pass the valid Browser Name..." + browserName);
			log.error("Please pass the valid browser name..."+ browserName);
			throw new BrowserExceptions("====INVALID BROWSER========");

		}

		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();

	}
	
	/**
	 * getDriver: get the local thready copy of the driver
	 */
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This is used to initialize the property
	 */

	// mvn clean install -Denv="qa"
	public Properties initProp() {

		String envName = System.getProperty("env");
		FileInputStream ip = null;
		prop = new Properties();

		try {
			if (envName == null) {
				//System.out.println("Envt is null, hence running the test in QA env by default");
				log.warn("Envt is null, hence running the test in QA env by default");

				ip = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
			} else {
				//System.out.println("Running the test on env" + envName);
				log.info("Running the test on env" + envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\prod.config.properties");
					break;

				default:
					log.error("-----Invalid Env name----");
					throw new FrameworkException("===INVALID ENV NAME====");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	/**
	 * takescreenshot
	 */

	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}

}
