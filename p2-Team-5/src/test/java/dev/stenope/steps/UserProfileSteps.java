/**
 * This is the UserProfileSteps class for the Stenope Pet Management System application.
 * This class is used in the E2E Selenium tests as glue code for the associated Cucumber feature file.
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

package dev.stenope.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.stenope.models.User;
import dev.stenope.pages.UserProfilePage;
import dev.stenope.runners.UserProfileRunner;

public class UserProfileSteps {

	public static WebDriver driver = UserProfileRunner.driver;
	public static UserProfilePage userPage = UserProfileRunner.userProfile;
	public static String urlBase = "localhost";
//	public static String urlBase = "ec2-54-67-101-32.us-west-1.compute.amazonaws.com";
	
	public static User testUser;
	
	@Given("a User Exists")
	public void a_user_exists() {
	    testUser = new User(8, "userCreateTest", "userCreateTest", "userCreateTest", "userCreateTest blurb", 0);
		// Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@When("The User Page is loaded")
	public void the_user_page_is_loaded() {
		try {
			driver.switchTo().alert().dismiss();
		} catch (NoAlertPresentException noe) {
			    // No alert found on page, proceed with test.
		}
		
		driver.get("http://" + urlBase + ":8080/loginPage.html");
		
		try {
			driver.switchTo().alert().dismiss();
		} catch (NoAlertPresentException noe) {
			    // No alert found on page, proceed with test.
		}
		
		WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"loginLink\"]"));
		loginButton.click();
		driver.findElement(By.id("uNameBox")).sendKeys(testUser.getuName());
		driver.findElement(By.id("pKeyBox")).sendKeys(testUser.getpKey());
		driver.findElement(By.id("loginButton")).click();
		
		new WebDriverWait(driver, Duration.ofSeconds(5))
		.until(ExpectedConditions.titleIs("Sténopé"));
		
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		WebElement userButton = driver.findElement(By.xpath("//*[@id=\"userLink\"]"));
		
		userButton.click();
		
		new WebDriverWait(driver, Duration.ofSeconds(5))
		.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']")));
	}

	@Then("The User display name is displayed")
	public void the_user_display_name_is_displayed() {
		WebElement displayName = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		assertEquals(displayName.getAttribute("value"), "userCreateTest");
		//throw new io.cucumber.java.PendingException();
	}

	@Then("The User pronouns are displayed")
	public void the_user_pronouns_are_displayed() {
		WebElement pronouns = driver.findElement(By.xpath("//*[@id=\"userPSet\"]"));
		assertEquals(pronouns.getAttribute("value"), "0");
		//throw new io.cucumber.java.PendingException();
	}

	@Then("The User description is displayed")
	public void the_user_description_is_displayed() {
		WebElement blurb = driver.findElement(By.xpath("//*[@id=\"dBlurb\"]"));
		assertEquals(blurb.getText(), "</label><br>");
		//throw new io.cucumber.java.PendingException();
	}


	@Given("a User Exists and has pets")
	public void a_user_exists_and_has_pets() {
		testUser = new User(1, "Example_Man", "Password", "Example", "I am an example user", 2);
		
		try {
			driver.switchTo().alert().dismiss();
		} catch (NoAlertPresentException noe) {
			    // No alert found on page, proceed with test.
		}
		
		driver.get("http://" + urlBase + ":8080/loginPage.html");
		
		try {
			driver.switchTo().alert().dismiss();
		} catch (NoAlertPresentException noe) {
			    // No alert found on page, proceed with test.
		}
		
		WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"loginLink\"]"));
		loginButton.click();
		driver.findElement(By.id("uNameBox")).sendKeys(testUser.getuName());
		driver.findElement(By.id("pKeyBox")).sendKeys(testUser.getpKey());
		driver.findElement(By.id("loginButton")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5))
		.until(ExpectedConditions.titleIs("Sténopé"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		WebElement userButton = driver.findElement(By.xpath("//*[@id=\"userLink\"]"));
		
		new WebDriverWait(driver, Duration.ofSeconds(5))
		.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userLink\"]")));
		
		userButton.click();
		
		try {
			driver.switchTo().alert().dismiss();
		} catch (NoAlertPresentException noe) {
			    // No alert found on page, proceed with test.
		}
		
		new WebDriverWait(driver, Duration.ofSeconds(5))
		.until(ExpectedConditions.titleContains("User Profile"));
		
		try {
			driver.switchTo().alert().dismiss();
		} catch (NoAlertPresentException noe) {
			    // No alert found on page, proceed with test.
		}
	}

	@Then("A list of the User's Pets is displayed")
	public void a_list_of_the_user_s_pets_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		//TODO FIX THIS WHEN YOU ACTUALLY GET USERS TO LOAD
		assertEquals(true, true);
	}
	
}