package dev.stenope.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import dev.stenope.models.Pet;
import dev.stenope.pages.PetProfilePage;
import dev.stenope.respositories.PetDAO;
import dev.stenope.respositories.UserDAO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PetProfileSteps {
	public static PetProfilePage p3;
	public static WebDriver driver;
	
	@Given("pet {int} exists")
	public void the_pet_exists(int id) {
		PetDAO pDAO = new PetDAO();
		assertNotEquals(pDAO.getPetByID(id),null);
	}

	@When("browser navigates to petpage")
	public void browser_navigates_to_petpage() {
		driver.get("https://localhost:8080/petPage.html");
	}

	@Then("pet data is displayed")
	public void pet_data_is_displayed() {
		assertNotEquals(p3.readPetData("pName"),null);
	}

	@Given("the user is logged in as the pet`s owner")
	public void the_user_is_logged_in_as_the_pet_s_owner(int id) {
		UserDAO uDAO = new UserDAO();
		PetDAO pDAO = new PetDAO();
		String ownerName = driver.findElement(By.id("userLink")).getText().substring(2);
		int ownerID = uDAO.getUserByUserName(ownerName).getId();
		boolean success = false;
		if (pDAO.getPetListByUserID(ownerID).contains(pDAO.getPetByID(id))) {success=true;}
		assertEquals(success,true);
	}
	
	@Given("browser is on petpage")
	public void browser_is_on_petpage() {
		driver.get("https://localhost:8080/petPage.html");
	}

	@When("user types into pet data")
	public void user_types_into_pet_data() {
		Pet p = new Pet();
		//bit where each property of p is typed into some field. 
	}

	@When("user submits pet data")
	public void user_submits_pet_data() {
		driver.findElement(By.id("pDataSubmitButton")).click();
	}

	@When("user refreshes")
	public void user_refreshes() {
		driver.get(driver.getCurrentUrl());
	}
}
