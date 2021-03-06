/**
 * This is the HomePageSteps class for the Stenope Pet Management System application.
 * This class is used in the E2E Selenium tests as glue code for the associated Cucumber feature file.
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

package dev.stenope.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import dev.stenope.pages.HomePage;
import dev.stenope.runners.HomePageRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageSteps {
	public static WebDriver realHumanBeing = HomePageRunner.realHumanBeing;
	public static HomePage homePage = HomePageRunner.homePage;
	
	@Given("the user is on the homePage")
	public void the_user_is_on_the_home_page() {
		realHumanBeing.navigate().refresh();
		realHumanBeing.get("http://localhost:8080/loginPage.html");
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("loginPage"));
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(4));
		homePage.uName.sendKeys("Example_Man");
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(4));
	    homePage.pKey.sendKeys("Password");
	    new WebDriverWait(realHumanBeing, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(homePage.loginButton));
	    homePage.loginButton.click();
	    new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("homePage"));
	}

	/**
	 * Scenario 1
	 */
	@When("they type in a query in the search bar")
	public void they_type_in_a_query_in_the_search_bar() {
	    homePage.searchBar.sendKeys("Tabitha");
	    new WebDriverWait(realHumanBeing, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(homePage.searchButton));
	    homePage.searchButton.click();
	}

	@Then("the query result is displayed")
	public void the_query_result_is_displayed() {
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(homePage.searchResult));
		assertEquals(homePage.searchResult.isDisplayed(), true);
	}
}
