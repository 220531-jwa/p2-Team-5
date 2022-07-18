/**
 * This is the LoginSteps class for the Stenope Pet Management System application.
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

import dev.stenope.pages.LoginPage;
import dev.stenope.runners.LoginRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	public static WebDriver realHumanBeing = LoginRunner.realHumanBeing;
	public static LoginPage loginPage = LoginRunner.loginPage;
	/*
	 * Scenario 1
	 */
	@Given("the User is on the LoginPage")
	public void the_user_is_on_the_login_page() {
		//S3 link http://p2-t5-stenope-bucket.s3-website-us-west-1.amazonaws.com
	    //realHumanBeing.get("http://ec2-54-67-101-32.us-west-1.compute.amazonaws.com:8080/loginPage.html");
		realHumanBeing.get("http://localhost:8080/loginPage.html");
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("loginPage"));
	}
	
	@When("the User inputs an incorrect combination and clicks the login button")
	public void the_user_inputs_an_incorrect_combo() {
		loginPage.uName.sendKeys("asdcgvadfvgaedfg");
	    loginPage.pKey.sendKeys("dfbgvsfgnh");
	    loginPage.loginButton.click();
	}

	@Then("the User is given an error message")
	public void the_user_is_given_an_error_message() {
		Alert a = new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.alertIsPresent());
		
		assertEquals(a.getText(), "Login unsuccessful");
		a.accept();
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(3));
	}
	
	/*
	 * Scenario 2
	 */
	@Given("the User is on the LoginPage again")
	public void the_user_is_on_the_login_page_again() {
		//S3 link http://p2-t5-stenope-bucket.s3-website-us-west-1.amazonaws.com
	    //realHumanBeing.get("http://ec2-54-67-101-32.us-west-1.compute.amazonaws.com:8080/loginPage.html");
		realHumanBeing.get("http://localhost:8080/loginPage.html");
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("loginPage"));
	}
	
	@When("the User inputs a correct {string} and {string} and clicks the login button")
	public void the_user_inputs_a_correct_username_and_password(String string, String string2) {
		realHumanBeing.navigate().refresh();
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(4));
		loginPage.uName.sendKeys(string);
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(4));
	    loginPage.pKey.sendKeys(string2);
	    new WebDriverWait(realHumanBeing, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(loginPage.loginButton));
	    loginPage.loginButton.click();
	}

	@Then("the User is logged in and directed to the HomePage")
	public void the_user_is_logged_in_and_directed_to_the_home_page() {
		new WebDriverWait(realHumanBeing, Duration.ofSeconds(10))
		.until(ExpectedConditions.urlContains("homePage"));
		
		//assertEquals("http://ec2-54-67-101-32.us-west-1.compute.amazonaws.com:8080/homePage.html", realHumanBeing.getCurrentUrl());
		assertEquals("http://localhost:8080/homePage.html", realHumanBeing.getCurrentUrl());
	}

	
}
