#Author: TC_Prater
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

Feature: petprofile page

  Scenario Outline: Pet data is properly displayed
    Given pet <id> exists
    When browser navigates to petpage <id>
    Then pet data is displayed 
    
    Examples: 
    	| id |
    	| 1  | 
    	| 2  | 
    	| 5  | 
    #	| 10 | 
    #	| 25 | 
    #	| 100| 

  Scenario Outline: Pet data is properly edited 
  	Given pet <id> exists 
  	And the user is logged in as the pet <id>`s owner 
  	And browser is on petpage <id>
  	When user types <petname> into pet data 
  	And user submits pet data
  	And user refreshes 
  	Then <petname> is displayed
  	
  	Examples: 
    	| id | petname |
    	| 1  | "asdf"  |
    	| 2  | "sjgkl" |
    	| 5  | "test"  |
    #	| 10 | "ababa" | 
    #	| 25 | "Merman"|
    #	| 100| "Dragon"| 
  
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
