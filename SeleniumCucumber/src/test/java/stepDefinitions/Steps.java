package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	
	@Before
	public void setUp() throws IOException{
		//Reading properties file
		logger = Logger.getLogger("nopCommerce");
		PropertyConfigurator.configure("Log4j.properties");
		
		configProperties = new Properties();
		FileInputStream configPropetyFile = new FileInputStream("config.properties");
		configProperties.load(configPropetyFile);
		
		String br = configProperties.getProperty("browser");
		
		if(br.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", configProperties.getProperty("chromepath"));
			driver = new ChromeDriver();
		}
		else if(br.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.chrome.driver", configProperties.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		}
		else if(br.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.chrome.driver", configProperties.getProperty("iepath"));
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		logger.info("*******Launching Browser********");
	}

	@Given("User Launch Chrome Browser")
	public void user_launch_chrome_browser() {
		
		
		lp = new LoginPage(driver);
		
	}

	@When("User opens URL {string}")
	public void user_opens_url(String Url) {
		logger.info("*******Opening Url********");
		driver.get(Url);
	}

	@When("User enters email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("*******Providing login details********");
		lp.setUserName(email);
		lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() {
		logger.info("*******Started Login********");
		lp.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String Title) {
		if(driver.getPageSource().contains("Login was unsuccessful.")){
			driver.close();
			logger.info("*******Login fail********");
			Assert.assertTrue(false);
		}
		else{
			logger.info("*******Login pass********");
			Assert.assertEquals(Title, driver.getTitle());
		}
	}

	@When("User click on Logout link")
	public void user_click_on_logout_link() throws InterruptedException {
		logger.info("*******Click on Logout********");
		lp.clickLogout();
		Thread.sleep(2000);
	}

	@Then("close Browser")
	public void close_browser() {
		driver.close();
	}
	
	//Customer Feature Step Definitions
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
		addCustomer = new AddCustomerPage(driver);
	    Assert.assertEquals("Dashboard / nopCommerce administration", addCustomer.getPageTitle());
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(3000);
	    addCustomer.clickOnCustomersMenu();
	}

	@When("Click on Customer's Menu Item")
	public void click_on_customer_s_menu_item() throws InterruptedException {
		Thread.sleep(3000);
		addCustomer.clickOnCustomersMenu_Item();

	}

	@When("Click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		Thread.sleep(3000);
		addCustomer.clickOnAddNew();
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
	    Assert.assertEquals("Add a new customer / nopCommerce administration", addCustomer.getPageTitle());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("*******Adding new customer********");
	    String email = randomString() + "@gmail.com";
	    addCustomer.setEmail(email);
	    addCustomer.setPassword("test123");
	    //Registered - default
	    //The customer cannot be in both 'guests' and 'registered' customer roles
	    //Add the customer to 'guests' or 'registered' customer role
	    addCustomer.setCustomerRoles("Guests");
	    Thread.sleep(3000);
	    
	    addCustomer.setManagerOfVendor("Vendor 2");
	    addCustomer.setGender("Male");
	    addCustomer.setFirstName("pavan");
	    addCustomer.setLastName("kumar");
	    addCustomer.setDOB("12/12/56");
	    addCustomer.setCompanyName("busyqa");
	    addCustomer.setAdminComment("This is for testing..........");
	}

	@When("Click on Save button")
	public void click_on_save_button() throws InterruptedException {
	    addCustomer.clickOnSave();
	    Thread.sleep(2000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
	    Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
	    		.contains("The new customer has been added successfully"));
	}
	
	//STEPS FOR SEARCHING A CUSTOMER USING EMAILID
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
	    searchCustomer = new SearchCustomerPage(driver);
	    searchCustomer.setEmail("brenda_lindgren@nopCommerce.com");
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
	    searchCustomer.clickSearch();
	    Thread.sleep(2000);
	}

	@Then("User should find Email in the Search table")
	public void user_should_find_email_in_the_search_table() {
	    boolean flag = searchCustomer.searchCustomerByEmail("brenda_lindgren@nopCommerce.com");
	    Assert.assertEquals(true, flag);
	}
	
	//STEPS FOR SEARCHING A CUSTOMER USING FIRSTNAME AND LASTNAME
	@When("Enter customer Firstname")
	public void enter_customer_firstname() {
		searchCustomer = new SearchCustomerPage(driver);
		searchCustomer.setFirstname("Brenda");
	}

	@When("Enter customer Lastname")
	public void enter_customer_lastname() {
	    searchCustomer.setLastname("Lindgren");
	}

	@Then("User should find Name in the Search table")
	public void user_should_find_name_in_the_search_table() {
	    boolean status = searchCustomer.searchCustomerByName("Brenda Lindgren");
	    Assert.assertEquals(true, status);
	}
}
