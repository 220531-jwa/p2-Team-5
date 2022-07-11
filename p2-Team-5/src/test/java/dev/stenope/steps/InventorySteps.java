package dev.stenope.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import dev.stenope.pages.InventoryPage;
import dev.stenope.respositories.PetDAO;
import dev.stenope.runners.InventoryRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InventorySteps {
	public static WebDriver realHumanBeing = InventoryRunner.realHumanBeing;
	public static InventoryPage inventoryPage = InventoryRunner.inventoryPage;
	/**
	 * Scenario 1
	 */
	@Given("the User {int} has Pets")
	public void the_user_has_pets(int uId) {
	    PetDAO pd = new PetDAO();
	    assertNotEquals(pd.getPetListByUserID(uId).size(), 0);
	    assertNotEquals(pd.getPetListByUserID(uId), null);
	}

	@When("the User loads the InventoryPage")
	public void the_user_loads_the_inventory_page() {
	    realHumanBeing.get("http://localhost:8080/inventory.html");
	}

	@Then("the subheadings dividing the inventory should appear")
	public void the_subheadings_dividing_the_inventory_should_appear() {
	    assertEquals(inventoryPage.userInventory.getText(), "User Inventory");
	    assertEquals(inventoryPage.petsInventory.getText(), "Pets Inventory");
	}

	/**
	 * Scenario 2
	 */
	@Given("the User is on the InventoryPage")
	public void the_user_is_on_the_inventory_page() {
		realHumanBeing.get("http://localhost:8080/inventory.html");
	}

	@When("the User clicks on and Item")
	public void the_user_clicks_on_and_item() {
	    inventoryPage.userItemDropdown.click();
	}

	@Then("a dropdown or popup should appear that allows the Item to be assigned to places")
	public void a_dropdown_or_popup_should_appear_that_allows_the_item_to_be_assigned_to_places() {
		assertEquals(inventoryPage.userItemDropdownSelect.getOptions().size(), 2);
	}

	/**
	 * Scenario 3
	 */
	@Given("an Item dropdown is loaded")
	public void an_item_dropdown_is_loaded() {
		inventoryPage.userItemDropdown.click();
	}

	@When("the User selects an option and presses the submit button")
	public void the_user_selects_an_option_and_presses_the_submit_button() {
		inventoryPage.userItemDropdownSelect.selectByVisibleText("Use");
	}

	@Then("the option�s associated action should be executed")
	public void the_option_s_associated_action_should_be_executed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	/**
	 * Scenario 4
	 */
	@Given("the Pet {int} exists")
	public void the_pet_exists(int pId) {
		PetDAO pd = new PetDAO();
		pd.getPetByID(pId);
	}

	@When("an Item is assigned to the Pet inventory")
	public void an_item_is_assigned_to_the_pet_inventory() {
		inventoryPage.userItemDropdown.click();
		inventoryPage.userItemDropdownSelect.selectByVisibleText("Give to Pet");
	}

	@Then("the Item appears in the Pet inventory")
	public void the_item_appears_in_the_pet_inventory() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
