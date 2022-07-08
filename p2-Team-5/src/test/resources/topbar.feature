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
@TopBar
Feature: Top Bar

  @HomePageUserProfile
  Scenario: Home Page to User Profile
    Given The User is logged in
    And The User is on the Home Page
    When The User clicks on the User Profile
    Then The User navigates to the User Profile page

  @HomePageInventory
  Scenario: Home Page to Inventory
    Given The User is logged in
    And The User is on the Home Page
    When The User clicks on the Inventory
    Then The User navigates to the Inventory page
    
  @HomePageHomePage
  Scenario: Home Page to Home Page
    Given The User is logged in
    And The User is on the Home Page
    When The User clicks on the Home Page
    Then The User navigates to the Home Page page
    
  @UserProfileUserProfile
  Scenario: User Profile to User Profile
    Given The User is logged in
    And The User is on the User Profile
    When The User clicks on the User Profile
    Then The User navigates to the User Profile page

  @UserProfileInventory
  Scenario: User Profile to Inventory
    Given The User is logged in
    And The User is on the User Profile
    When The User clicks on the Inventory
    Then The User navigates to the Inventory page
    
  @UserProfileHomePage
  Scenario: User Profile to Home Page
    Given The User is logged in
    And The User is on the User Profile
    When The User clicks on the Home Page
    Then The User navigates to the Home Page page
    
  @PetProfileUserProfile
  Scenario: Pet Profile to User Profile
    Given The User is logged in
    And The User is on the Pet Profile
    When The User clicks on the User Profile
    Then The User navigates to the User Profile page

  @PetProfileInventory
  Scenario: Pet Profile to Inventory
    Given The User is logged in
    And The User is on the Pet Profile
    When The User clicks on the Inventory
    Then The User navigates to the Inventory page
    
  @PetProfileHomePage
  Scenario: Pet Profile to Home Page
    Given The User is logged in
    And The User is on the Pet Profile
    When The User clicks on the Home Page
    Then The User navigates to the Home Page page
    
    
  @InventoryUserProfile
  Scenario: Inventory to User Profile
    Given The User is logged in
    And The User is on the Inventory
    When The User clicks on the User Profile
    Then The User navigates to the User Profile page

  @InventoryInventory
  Scenario: Inventory to Inventory
    Given The User is logged in
    And The User is on the Inventory
    When The User clicks on the Inventory
    Then The User navigates to the Inventory page
    
  @InventoryHomePage
  Scenario: Inventory to Home Page
    Given The User is logged in
    And The User is on the Inventory
    When The User clicks on the Home Page
    Then The User navigates to the Inventory page            
    