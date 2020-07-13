package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {
	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage addCustomer;
	public SearchCustomerPage searchCustomer;
	public static Logger logger;
	public Properties configProperties;
	//Created for Random generation of unique EmailId
	public static String randomString(){
		String generateRandom = RandomStringUtils.randomAlphabetic(5);
		return generateRandom;
	}
	
}
