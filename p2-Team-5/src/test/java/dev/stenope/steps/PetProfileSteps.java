package dev.stenope.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	public void the_pet_exists(int id) 
	{
		PetDAO pDAO = new PetDAO();
		assertNotEquals(pDAO.getPetByID(id),null);
	}

	@When("browser navigates to petpage {int}")
	public void browser_navigates_to_petpage(int id) 
	{
		//S3 link http://p2-t5-stenope-bucket.s3-website-us-west-1.amazonaws.com
		PetDAO pDAO = new PetDAO(); 
		Pet p = pDAO.getPetByID(id);
		driver.get("http://localhost:8080/homePage.html");
		driver.findElement(By.id("searchBar")).sendKeys(p.getpName());
		driver.findElement(By.id("searchButton")).click();
		WebElement targetPet = new WebDriverWait(driver, Duration.ofSeconds(4L))
				.until(ExpectedConditions.elementToBeClickable(By.id("searchResult_"+p.getId())));
		targetPet.click();
	}

	@Then("pet data is displayed")
	public void pet_data_is_displayed() 
	{
		WebElement ownerName = new WebDriverWait(driver, Duration.ofSeconds(4L))
				.until(ExpectedConditions.elementToBeClickable(By.id("ownerName")));
		assertNotEquals(ownerName,null);
		assertNotEquals(p3.readPetData("petName"),null);
	}
	
	@Given("the user is logged in as the pet`s owner")
	public void the_user_is_logged_in_as_the_pet_s_owner(int id) 
	{
		UserDAO uDAO = new UserDAO();
		PetDAO pDAO = new PetDAO();
		String ownerName = uDAO.getUserByID(pDAO.getPetByID(id).getuID()).getuName();
		String displayedName = driver.findElement(By.id("userLink")).getText().substring(2);
		if (! ownerName.equals(displayedName)) 
			{
				driver.findElement(By.id("loginLink")).click();
				driver.findElement(By.id("uNameBox")).sendKeys(ownerName);
				driver.findElement(By.id("pKeyBox")).sendKeys(uDAO.getUserByID(pDAO.getPetByID(id).getuID()).getpKey());
				driver.findElement(By.id("loginButton")).click();
			}
	}
	
	@Given("browser is on petpage {int}")
	public void browser_is_on_petpage(int id) 
	{
		PetDAO pDAO = new PetDAO(); 
		Pet p = pDAO.getPetByID(id);
		driver.get("http://localhost:8080/homePage.html");
		driver.findElement(By.id("searchBar")).sendKeys(p.getpName());
		driver.findElement(By.id("searchButton")).click();
		WebElement targetPet = new WebDriverWait(driver, Duration.ofSeconds(4L))
				.until(ExpectedConditions.elementToBeClickable(By.id("searchResult_"+p.getId())));
		targetPet.click();
	}

	@When("user types {string} into pet data")
	public void user_types_into_pet_data(String petname) 
	{
		WebElement ownerName = new WebDriverWait(driver, Duration.ofSeconds(4L))
				.until(ExpectedConditions.elementToBeClickable(By.id("ownerName")));
		assertNotEquals(ownerName,null);
		p3.dataDiv.findElement(By.id("petName")).sendKeys(petname);
		Select s = new Select(p3.dataDiv.findElement(By.id("petPSet")));
		s.selectByIndex(4); 
	}

	@When("user submits pet data")
	public void user_submits_pet_data() 
	{
		driver.findElement(By.id("pDataSubmitButton")).click();
	}

	@When("user refreshes")
	public void user_refreshes() 
	{
		driver.get(driver.getCurrentUrl());
	}
	
	@Then("{string} is displayed")
	public void new_petname_is_displayed(String petname) 
	{
		assertEquals(p3.readPetData("pName"),petname);
	}
}
