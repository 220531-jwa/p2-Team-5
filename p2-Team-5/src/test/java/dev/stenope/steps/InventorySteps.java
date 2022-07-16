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
	@Given("the User has Pets")
	public void the_user_has_pets() {
		//S3 link http://p2-t5-stenope-bucket.s3-website-us-west-1.amazonaws.com
		realHumanBeing.get("http://localhost:8080/loginPage.html");
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("loginPage"));
		inventoryPage.uName.sendKeys("Example_Man");
	    inventoryPage.pKey.sendKeys("Password");
	    inventoryPage.loginButton.click();
		PetDAO pd = new PetDAO();
	    assertNotEquals(pd.getPetListByUserID(1).size(), 0);
	    assertNotEquals(pd.getPetListByUserID(1), null);
	}

	@When("the User loads the InventoryPage")
	public void the_user_loads_the_inventory_page() {
		//S3 link http://p2-t5-stenope-bucket.s3-website-us-west-1.amazonaws.com
	    //realHumanBeing.get("http://ec2-54-67-101-32.us-west-1.compute.amazonaws.com:8080/inventory.html");
		inventoryPage.inventoryButton.click();
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("inventory"));
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
		//realHumanBeing.get("http://ec2-54-67-101-32.us-west-1.compute.amazonaws.com:8080/inventory.html");
		realHumanBeing.get("http://localhost:8080/loginPage.html");
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("loginPage"));
		inventoryPage.uName.sendKeys("Example_Man");
	    inventoryPage.pKey.sendKeys("Password");
	    inventoryPage.loginButton.click();
	    new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("homePage"));
		inventoryPage.inventoryButton.click();
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("inventory"));
	}

	@When("the User clicks on an Item")
	public void the_user_clicks_on_and_item() {
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(inventoryPage.userSelector1));
		inventoryPage.userSelector1.click();
	}

	@Then("a dropdown or popup should appear that allows the Item to be assigned to places")
	public void a_dropdown_or_popup_should_appear_that_allows_the_item_to_be_assigned_to_places() {
		assertEquals(inventoryPage.userSelector1.isDisplayed(), true);
	}

	/**
	 * Scenario 3
	 */
	@Given("the Pet exists")
	public void the_pet_exists() {
		PetDAO pd = new PetDAO();
		pd.getPetByID(1);
	}

	@When("an Item is assigned to the Pet inventory")
	public void an_item_is_assigned_to_the_pet_inventory() {
		realHumanBeing.get("http://localhost:8080/loginPage.html");
		//realHumanBeing.get("http://ec2-54-67-101-32.us-west-1.compute.amazonaws.com:8080/loginPage.html");
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("loginPage"));
		inventoryPage.uName.sendKeys("Example_Man");
	    inventoryPage.pKey.sendKeys("Password");
	    inventoryPage.loginButton.click();
	    new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("homePage"));
		inventoryPage.inventoryButton.click();
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("inventory"));
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(inventoryPage.userSelector2));
		inventoryPage.userSelector2.click();
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(5));
		inventoryPage.userSelector2.findElement(By.xpath("/html/body/div[2]/div[2]/select/option[3]")).click();
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(inventoryPage.giveToPet));
		inventoryPage.giveToPet.click();
	}

	@Then("the Item appears in the Pet inventory")
	public void the_item_appears_in_the_pet_inventory() {
		realHumanBeing.navigate().refresh();
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("inventory"));
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(inventoryPage.petItemDropdown));
		inventoryPage.petItemDropdown.click();
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(inventoryPage.firstPet));
		inventoryPage.firstPet.click();
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(inventoryPage.userItemTest2));
		assertEquals(inventoryPage.userItemTest2.isDisplayed(), true);
	}
	/*
	@Given("an Item dropdown is loaded")
	public void an_item_dropdown_is_loaded() {
		realHumanBeing.get("http://localhost:8080/loginPage.html");
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("loginPage"));
		inventoryPage.uName.sendKeys("Example_Man");
	    inventoryPage.pKey.sendKeys("Password");
	    inventoryPage.loginButton.click();
	    new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("homePage"));
		inventoryPage.inventoryButton.click();
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("inventory"));
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(inventoryPage.userSelector1));
		inventoryPage.userSelector1.click();
	}

	@When("the User selects an option and presses the submit button")
	public void the_user_selects_an_option_and_presses_the_submit_button() {
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(5));
		inventoryPage.userSelector1.findElement(By.xpath("/html/body/div[2]/div[1]/select/option[1]")).click();
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(5));
	}

	@Then("the option associated action should be executed")
	public void the_option_s_associated_action_should_be_executed() {
	    assertNotEquals(inventoryPage.UserItemTest1.getText(), "bread");
	}
	*/
}
