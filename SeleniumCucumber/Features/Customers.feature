Feature: Customers

 Background: Below are the common steps for each scenario
	Given User Launch Chrome Browser
	When User opens URL "https://admin-demo.nopcommerce.com/login"
	And User enters email as "admin@yourstore.com" and Password as "admin"
	And Click on Login
	Then User can view Dashboard
@sanity
Scenario: Add new Customer
	When User click on customers Menu
	And Click on Customer's Menu Item
	And Click on Add new button
	Then User can view Add new customer page
	When User enter customer info
	And Click on Save button
	Then User can view confirmation message "The new customer has been added successfully."
	And close Browser
	
	
@regression
Scenario: Search Customer by EmailId
	When User click on customers Menu
	And Click on Customer's Menu Item
	And Enter customer EMail
	When Click on search button
	Then User should find Email in the Search table
	And close Browser
	
@regression
Scenario: Search Customer by Name
	When User click on customers Menu
	And Click on Customer's Menu Item
	And Enter customer Firstname
	And Enter customer Lastname
	When Click on search button
	Then User should find Name in the Search table
	And close Browser
	
	