package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

	public WebDriver ldriver;
	public AddCustomerPage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	By lnkCustomer_menu = By.xpath("//a[@href='#']//span[contains(text(), 'Customers')]");
	By lnkCustomer_menuItem = By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");
	
	By btnAddCustomer = By.xpath("//a[@class='btn bg-blue']");
	By txtEmail = By.xpath("//input[@id='Email']");
	By txtPassword = By.xpath("//input[@id='Password']");
	By txtFirstname = By.xpath("//input[@id='FirstName']");
	By txtLastname = By.xpath("//input[@id='LastName']");
	
	By txtCutomerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']//input[@class='k-input']");
	
	By lstItemAdministrator = By.xpath("//li[contains(text(),'Administrators')]");
	By lstItemForum_Moderator = By.xpath("//li[contains(text(),'Forum Moderators')]");
	By lstItemGuests = By.xpath("//li[contains(text(),'Guests')]");
	By lstItemRegistered = By.xpath("//li[contains(text(),'Registered')]");
	By lstItemVendors = By.xpath("//li[contains(text(),'Vendors')]");
	
	By drpmgrVendor = By.xpath("//select[@id='VendorId']");
	By rdMale = By.xpath("//input[@id='Gender_Male']");
	By rdFemale = By.xpath("//input[@id='Gender_Female']");
	
	By txtDOB = By.xpath("//input[@id='DateOfBirth']");
	By txtCompany = By.xpath("//input[@id='Company']");
	//By txtDispName = By.xpath("//input[@name='customer_attribute_1']");
	
	By txtAdminComment = By.xpath("//textarea[@name='AdminComment']");
	
	By btnSave = By.xpath("//button[@name='save']");
	
	public String getPageTitle(){
		return ldriver.getTitle();
	}
	
	//Action Methods
	public void clickOnCustomersMenu(){
		ldriver.findElement(lnkCustomer_menu).click();
	}
	
	public void clickOnCustomersMenu_Item(){
		ldriver.findElement(lnkCustomer_menuItem).click();
	}
	
	public void clickOnAddNew(){
		ldriver.findElement(btnAddCustomer).click();
	}
	
	public void setEmail(String email){
		ldriver.findElement(txtEmail).sendKeys(email);
	}
	public void setPassword(String password){
		ldriver.findElement(txtPassword).sendKeys(password);
	}
	
	public void setCustomerRoles(String role) throws InterruptedException{
		
		ldriver.findElement(txtCutomerRoles).click();
		
		WebElement listItem;
		Thread.sleep(3000);
		
		if(role.equals("Administrators")){
			listItem = ldriver.findElement(lstItemAdministrator);
		}
		else if(role.equals("Guests")){
			ldriver.findElement(By.xpath("//li[@class='k-button']//span[@class='k-select']")).click();
			Thread.sleep(3000);
			ldriver.findElement(txtCutomerRoles).click();
			Thread.sleep(3000);
			listItem = ldriver.findElement(lstItemGuests);
		}
		else if(role.equals("Registered")){
			listItem = ldriver.findElement(lstItemRegistered);
		}
		else if(role.equals("Vendors")){
			listItem = ldriver.findElement(lstItemVendors);
		}
		else if(role.equals("Forum Moderator")){
			listItem = ldriver.findElement(lstItemForum_Moderator);
		}
		else{
			ldriver.findElement(By.xpath("//li[@class='k-button']//span[@class='k-select']")).click();
			Thread.sleep(3000);
			ldriver.findElement(txtCutomerRoles).click();
			Thread.sleep(3000);
			listItem = ldriver.findElement(lstItemGuests);
		}
		
		listItem.click();
		
//		JavascriptExecutor js = (JavascriptExecutor)ldriver;
//		js.executeScript("aruments[0].click();", listItem);
		
	}
	
	public void setManagerOfVendor(String mgr){
		Select drp = new Select(ldriver.findElement(drpmgrVendor));
		drp.selectByVisibleText(mgr);
	}
	
	public void setGender(String gender){
		if(gender.equals("Male")){
			ldriver.findElement(rdMale).click();
		}
		else if(gender.equals("Male")){
			ldriver.findElement(rdFemale).click();
		}
		else{
			ldriver.findElement(rdFemale).click();
		}
	}
	
	public void setFirstName(String fname){
		ldriver.findElement(txtFirstname).sendKeys(fname);
	}
	
	public void setLastName(String lname){
		ldriver.findElement(txtLastname).sendKeys(lname);
	}
	public void setDOB(String dob){
		ldriver.findElement(txtDOB).sendKeys(dob);
	}
	public void setCompanyName(String company){
		ldriver.findElement(txtCompany).sendKeys(company);
	}
	public void setAdminComment(String adminComment){
		ldriver.findElement(txtAdminComment).sendKeys(adminComment);
	}
	public void clickOnSave(){
		ldriver.findElement(btnSave).click();
	}
}
