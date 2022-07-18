#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Login
		
		@tag1
		Scenario: Negative
			Given the User is on the LoginPage
			When the User inputs an incorrect combination and clicks the login button
			Then the User is given an error message

    @tag2
		Scenario Outline: Positive
			Given the User is on the LoginPage again
			When the User inputs a correct <username> and <password> and clicks the login button
			Then the User is logged in and directed to the HomePage
			
			
		Examples: 
      | username        | password     |
			| "Example_Man"   | "Password"   |
			| "TEST_OSTERONE" | "STEROIDS"   |
			| "Testrogen"     | "OpenSesame" |
			| "Testbot785"    | "jg33:L$@"   |	
