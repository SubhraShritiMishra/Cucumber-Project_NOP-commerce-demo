Feature: Login

@sanity
Scenario: Successful Login with valid credentials
	Given User Launch Chrome Browser
	When User opens URL "https://admin-demo.nopcommerce.com/login"
	And User enters email as "admin@yourstore.com" and Password as "admin"
	And Click on Login
	Then Page Title should be "Dashboard / nopCommerce administration"
	When User click on Logout link
	Then Page Title should be "Your store. Login"
	And close Browser

@regression	
Scenario Outline: Login Data Driven
	Given User Launch Chrome Browser
	When User opens URL "https://admin-demo.nopcommerce.com/login"
	And User enters email as "<email>" and Password as "<password>"
	And Click on Login
	Then Page Title should be "Dashboard / nopCommerce administration"
	When User click on Logout link
	Then Page Title should be "Your store. Login"
	And close Browser
	
	Examples:
		| email | password |
		| admin@yourstore.com | admin |
		| admin1@yourstore.com | admin123 |