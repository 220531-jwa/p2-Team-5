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
@UserProfile
Feature: User Profile

  @ProfileLoads
  Scenario: The Profile Loads
    Given a User Exists
    When The User Page is loaded
    Then The User display name is displayed
    And The User pronouns are displayed
    And The User description is displayed

  @DisplayNameEdit
  Scenario: The Profile Can Be Edited
    Given The User is logged in
    And The User is on their own profile
    When The User changes their display name
    And The User presses submit
    And The User refreshes their profile
    Then The User display name is displayed
    
  @PronounEdit
  Scenario: The Profile Can Be Edited
    Given The User is logged in
    And The User is on their own profile
    When The User changes their pronouns
    And The User presses submit
    And The User refreshes their profile
    Then The User pronouns are displayed
    
  @BlurbEdit
  Scenario: The Profile Can Be Edited
    Given The User is logged in
    And The User is on their own profile
    When The User changes their description
    And The User presses submit
    And The User refreshes their profile
    Then The User description is displayed
    
  @PetList
  Scenario: The Pets Load
    Given a User Exists and has pets
    When The User Page is loaded
    Then A list of the User's Pets is displayed