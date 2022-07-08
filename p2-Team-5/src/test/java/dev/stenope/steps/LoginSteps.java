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
	public static LoginPage productsPage = LoginRunner.loginPage;
	
	@Given("the User is on the LoginPage")
	public void the_user_is_on_the_login_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	/*
	 * Scenario 1
	 */
	@When("the User inputs a correct <username> and <password>")
	public void the_user_inputs_a_correct_username_and_password() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the User is logged in and directed to the HomePage")
	public void the_user_is_logged_in_and_directed_to_the_home_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	/*
	 * Scenario 2
	 */
	@When("the User inputs an incorrect testName or testPass")
	public void the_user_inputs_an_incorrect_test_name_or_test_pass() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the User is given an error message")
	public void the_user_is_given_an_error_message() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the User inputs an incorrect notName or notPass")
	public void the_user_inputs_an_incorrect_not_name_or_not_pass() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
