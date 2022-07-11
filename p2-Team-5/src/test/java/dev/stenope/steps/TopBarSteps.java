package dev.stenope.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.stenope.pages.TopBarPage;
import dev.stenope.runners.TopBarRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class TopBarSteps {
	
	public static WebDriver driver = TopBarRunner.driver;
	public static TopBarPage topBar = TopBarRunner.topBar;

	@Given("The User is logged in")
	public void the_user_is_logged_in() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("The User is on the Home Page")
	public void the_user_is_on_the_home_page() {
		driver.get("http://localhost:8080/homePage.html");
	    //throw new io.cucumber.java.PendingException();
	}

	@When("The User clicks on the User Profile")
	public void the_user_clicks_on_the_user_profile() {
		WebElement userButton = driver.findElement(By.xpath("//*[@id=\"userLink\"]"));
		userButton.click();
	    //throw new io.cucumber.java.PendingException();
	}

	@Then("The User navigates to the User Profile page")
	public void the_user_navigates_to_the_user_profile_page() {
		
		new WebDriverWait(driver,Duration.ofSeconds(10))
		.until(ExpectedConditions.titleContains("User Page"));
		
		assertEquals("User Page", driver.getTitle());
	    //throw new io.cucumber.java.PendingException();
	}

	@When("The User clicks on the Inventory")
	public void the_user_clicks_on_the_inventory() {
		WebElement inventoryButton = driver.findElement(By.xpath("//*[@id=\"inventoryLink\"]"));
		inventoryButton.click();
	    //throw new io.cucumber.java.PendingException();
	}

	@Then("The User navigates to the Inventory page")
	public void the_user_navigates_to_the_inventory_page() {
		new WebDriverWait(driver,Duration.ofSeconds(10))
		.until(ExpectedConditions.titleContains("Inventory"));
		
		assertEquals("Inventory", driver.getTitle());
	    //throw new io.cucumber.java.PendingException();
	}

	@When("The User clicks on the Home Page")
	public void the_user_clicks_on_the_home_page() {
		WebElement homeButton = driver.findElement(By.xpath("//*[@id=\"homeLink\"]"));
		homeButton.click();
	    //throw new io.cucumber.java.PendingException();
	}

	@Then("The User navigates to the Home Page page")
	public void the_user_navigates_to_the_home_page_page() {
		new WebDriverWait(driver,Duration.ofSeconds(10))
		.until(ExpectedConditions.titleContains("Home Page"));
		
		assertEquals("Home Page", driver.getTitle());
	    //throw new io.cucumber.java.PendingException();
	}

	@Given("The User is on the User Profile")
	public void the_user_is_on_the_user_profile() {
		driver.get("http://localhost:8080/userPage.html");
	    //throw new io.cucumber.java.PendingException();
	}

	@Given("The User is on the Pet Profile")
	public void the_user_is_on_the_pet_profile() {
		driver.get("http://localhost:8080/petPage.html");
	    //throw new io.cucumber.java.PendingException();
	}

	@Given("The User is on the Inventory")
	public void the_user_is_on_the_inventory() {
		driver.get("http://localhost:8080/inventory.html");
	    //throw new io.cucumber.java.PendingException();
	}
	
	@When("The User clicks on the Login")
	public void the_user_clicks_on_the_login() {
		WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"loginLink\"]"));
		loginButton.click();
	    //throw new io.cucumber.java.PendingException();
	}

	@Then("The User navigates to the Login page")
	public void the_user_navigates_to_the_login_page() {
		new WebDriverWait(driver,Duration.ofSeconds(10))
		.until(ExpectedConditions.titleContains("Login"));
		
		assertEquals("Login", driver.getTitle());
	    //throw new io.cucumber.java.PendingException();
	}

	@Then("The User is logged out")
	public void the_user_is_logged_out() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("The User is on the log in page")
	public void the_user_is_on_the_log_in_page() {
		driver.get("http://localhost:8080/loginPage.html");
	    //throw new io.cucumber.java.PendingException();
	}

	@Given("The User is not logged in")
	public void the_user_is_not_logged_in() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("The User is given a logged out error")
	public void the_user_is_given_a_logged_out_error() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
}
