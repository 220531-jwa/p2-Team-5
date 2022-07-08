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

  Scenario: Pet data is properly displayed
    Given the pet exists
    When browser navigates to petpage
    Then pet data is displayed 

  Scenario: Pet data is properly edited 
  	Given the pet exists 
  	And the user is logged in as the pet`s owner 
  	And browser is on petpage 
  	When user types into pet data 
  	And user submits pet data
  	And user refreshes 
  	Then pet data is displayed
  
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
