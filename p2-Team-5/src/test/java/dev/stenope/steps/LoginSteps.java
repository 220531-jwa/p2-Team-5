package dev.stenope.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import dev.stenope.pages.LoginPage;
import dev.stenope.runners.LoginRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	public static WebDriver realHumanBeing = LoginRunner.realHumanBeing;
	public static LoginPage loginPage = LoginRunner.loginPage;
	
	@Given("the User is on the LoginPage")
	public void the_user_is_on_the_login_page() {
	    realHumanBeing.get("http://localhost:8080/loginPage.html");
	}

	/*
	 * Scenario 1
	 */
	@When("the User inputs a correct {string} and {string} and clicks login")
	public void the_user_inputs_a_correct_username_and_password(String uName, String pKey) {
	    loginPage.uName.sendKeys(uName);
	    loginPage.pKey.sendKeys(pKey);
	    loginPage.loginButton.click();
	}

	@Then("the User is logged in and directed to the HomePage")
	public void the_user_is_logged_in_and_directed_to_the_home_page() {
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("homePage"));
		
		assertEquals("http://localhost:8080/homePage.html", realHumanBeing.getCurrentUrl());
	}

	/*
	 * Scenario 2
	 */
	@When("the User inputs an incorrect {string} or {string} and clicks login")
	public void the_user_inputs_an_incorrect_combo() {
		loginPage.uName.sendKeys("asdcgvadfvgaedfg");
	    loginPage.pKey.sendKeys("dfbgvsfgnh");
	    loginPage.loginButton.click();
	}

	@Then("the User is given an error message")
	public void the_user_is_given_an_error_message() {
		Alert a = new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.alertIsPresent());
		
		assertEquals(a.getText(), "Login failed");
	}
}
