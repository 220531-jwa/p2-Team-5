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
	Background:
		Given the User is on the LoginPage
		
		@tag1
		Scenario Outline: Positive
			When the User inputs a correct <username> and <password>
			Then the User is logged in and directed to the HomePage
			
		@tag2
		Scenario Outline: Negative
			When the User inputs an incorrect <username> or <password>
			Then the User is given an error message

    Examples: 
      | username  | password |
      | testName  | testPass |
      | notName		| notPass  |

