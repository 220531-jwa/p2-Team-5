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
Feature: Inventory
		@tag1
		Scenario: Pet inventory subheadings
			Given the User has Pets
			When the User loads the InventoryPage
			Then the subheadings dividing the inventory should appear
			
		@tag2
		Scenario: Item click popup/dropdown
			Given the User is on the InventoryPage 
			When the User clicks on and Item
			Then a dropdown or popup should appear that allows the Item to be assigned to places
			
		@tag3
		Scenario: Item dropdown option submitted
			Given an Item dropdown is loaded
			When the User selects an option and presses the submit button
			Then the option associated action should be executed
			
		@tag4
		Scenario: Items in Pet inventories
			Given the Pet exists
			And the User is on the InventoryPage
			When an Item is assigned to the Pet inventory
			Then the Item appears in the Pet inventory
