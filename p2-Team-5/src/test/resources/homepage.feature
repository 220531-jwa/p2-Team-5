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
Feature: Title of your feature
	Background:
		Given the user is on the homePage
		
#	  @tag1
#	  Scenario: Create Pet Positive
#	    When the User clicks create a pet and fills out the form
#	    Then the Pet shows up in the User pets list
	  
	  @tag1
	  Scenario: Search Positive
	    When they type in a query in the search bar
	    Then the query result is displayed
	    
#	  @tag3
#	  Scenario: Search Negative
#	    When they type in an incorrect query in the search bar
#	    Then the query result is not displayed
