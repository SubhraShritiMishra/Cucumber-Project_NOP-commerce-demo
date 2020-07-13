package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.BaseClass;
import utilities.WaitHelper;

public class SearchCustomerPage extends BaseClass{
	public WebDriver ldriver;
	WaitHelper waitHelper;
	public SearchCustomerPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
		waitHelper = new WaitHelper(ldriver); 
	}
	
//	@FindBy(how=How.ID, using="SearchEmail")
//	@CacheLookup
//	WebElement txtEmail;
//	
//	@FindBy(how=How.ID, using="SearchFirstName")
//	@CacheLookup
//	WebElement txtFirstName;
//	
//	@FindBy(how=How.ID, using="SearchLastName")
//	@CacheLookup
//	WebElement txtLastName;
//	
//	@FindBy(how=How.ID, using="search-customers")
//	@CacheLookup
//	WebElement searchBtn;
//
//	@FindBy(how=How.XPATH, using="//div[@class='dataTables_scrollBody']//table//tbody//tr")
//	@CacheLookup
//	List<WebElement>  tableRows;
//	
//	@FindBy(how=How.XPATH, using="//div[@class='dataTables_scrollBody']//table//tbody//tr[1]//td")
//	@CacheLookup
//	List<WebElement> tableColumns; 
	
	@FindBy(how = How.ID, using ="SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	

	@FindBy(how = How.ID, using ="SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy(how = How.ID, using ="SearchLastName")
	@CacheLookup
	WebElement txtLastName;
	

/*	@FindBy(how = How.ID, using ="SearchMonthOfBirth")
	@CacheLookup
	WebElement drpdopMonth;
	
	@FindBy(how = How.ID, using ="SearchDayOfBirth")
	@CacheLookup
	WebElement drpdobDay;
	@FindBy(how = How.ID, using ="SearchCompany")
	@CacheLookup
	WebElement txtCompany;
	@FindBy(how = How.XPATH, using ="//div[@class='k-multiselect-wrap k-floatwrap']")
	@CacheLookup
	WebElement txtcustomerRoles;
	
	@FindBy(how = How.XPATH, using ="//li[contains(text(),'Administrators')]")
	@CacheLookup
	WebElement lstitemAdministrations;
	
	@FindBy(how = How.XPATH, using ="//li[contains(text(),'Registered')]")
	@CacheLookup
	WebElement lstitemRegistered;
	
	@FindBy(how = How.XPATH, using ="//li[contains(text(),'Guests')]")
	@CacheLookup
	WebElement lstitemGuests;
	
	@FindBy(how = How.XPATH, using ="//li[contains(text(),'Vendors')]")
	@CacheLookup
	WebElement lstitemVendors;*/
	

	@FindBy(how = How.XPATH, using ="//button[@id='search-customers']")
	@CacheLookup
	WebElement btnSearch;
	

	@FindBy(how = How.XPATH, using ="//table[@role='grid']")
	@CacheLookup
	WebElement tblSearchResults;

	@FindBy(how = How.XPATH, using ="//table[@id='customers-grid']")
	@CacheLookup
	WebElement table;
	
	@FindBy(how = How.XPATH, using ="//table[@id='customers-grid']//tbody/tr")
	@CacheLookup
	List<WebElement> tableRows;
	
	@FindBy(how = How.XPATH, using ="//table[@id='customers-grid']//tbody/tr/td")
	@CacheLookup
	List<WebElement> tableColumns;
	
	public void setEmail(String email){
		waitHelper.WaitForElement(txtEmail, 10);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void setFirstname(String fname){
		waitHelper.WaitForElement(txtFirstName, 10);
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastname(String lname){
		waitHelper.WaitForElement(txtLastName, 10);
		txtLastName.clear();
		txtLastName.sendKeys(lname);
	}
	
	public void clickSearch(){
		btnSearch.click();
		waitHelper.WaitForElement(btnSearch, 20);
	}
	
	public int getNoOfRows(){
		return(tableRows.size());
	}
	
	public int getNoOfColumns(){
		return(tableColumns.size());
	}
	
	public boolean searchCustomerByEmail(String email){
		boolean flag = false;
		
		for(int i=1; i<=getNoOfRows();i++){
			String emailid = table.findElement(By.xpath("//td[contains(text(),'brenda_lindgren@nopCommerce.com')]")).getText();
			
			System.out.println(emailid);
			if(emailid.equalsIgnoreCase(email)){
				flag = true;
			}
		}
		return flag;
	}
	
	public boolean searchCustomerByName(String Name){
		boolean flag = false;
		
		for(int i=1; i<=getNoOfRows();i++){
			String name = table.findElement(By.xpath("//td[contains(text(),'Brenda Lindgren')]")).getText();
			
			String names[] = name.split(" ");
			String Names[] = Name.split(" ");
			if(names[0].equals(Names[0]) && names[1].equals(Names[1])){
				flag = true;
			}
		}
		return flag;
	}
}
