package dev.stenope.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dev.stenope.pages.UserProfilePage;
import dev.stenope.runners.UserProfileRunner;

public class UserProfileSteps {

	public static WebDriver driver = UserProfileRunner.driver;
	public static UserProfilePage userPage = UserProfileRunner.userProfile;
	
	@Given("a User Exists")
	public void a_user_exists() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("The User Page is loaded")
	public void the_user_page_is_loaded() {
		//S3 link http://p2-t5-stenope-bucket.s3-website-us-west-1.amazonaws.com
		driver.get("http://ec2-54-67-101-32.us-west-1.compute.amazonaws.com:8080/userPage.html");
	    //throw new io.cucumber.java.PendingException();
	}

	@Then("The User display name is displayed")
	public void the_user_display_name_is_displayed() {
		WebElement displayName = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		assertEquals(displayName.getAttribute("value"), "User");
		throw new io.cucumber.java.PendingException();
	}

	@Then("The User pronouns are displayed")
	public void the_user_pronouns_are_displayed() {
		WebElement pronouns = driver.findElement(By.xpath("//*[@id=\"userPSet\"]"));
		assertEquals(pronouns.getAttribute("value"), 0);
		throw new io.cucumber.java.PendingException();
	}

	@Then("The User description is displayed")
	public void the_user_description_is_displayed() {
		WebElement blurb = driver.findElement(By.xpath("//*[@id=\"dBlurb\"]"));
		assertEquals(blurb.getText(), "Blurb");
		throw new io.cucumber.java.PendingException();
	}

	@Given("The User is on their own profile")
	public void the_user_is_on_their_own_profile() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("The User changes their display name")
	public void the_user_changes_their_display_name() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("The User presses submit")
	public void the_user_presses_submit() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("The User refreshes their profile")
	public void the_user_refreshes_their_profile() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("The User changes their pronouns")
	public void the_user_changes_their_pronouns() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("The User changes their description")
	public void the_user_changes_their_description() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("a User Exists and has pets")
	public void a_user_exists_and_has_pets() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("A list of the User's Pets is displayed")
	public void a_list_of_the_user_s_pets_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
}
