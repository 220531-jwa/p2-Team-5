/**
 * This is the HomePageRunner class for the Stenope Pet Management System application.
 * This class is used in the E2E Selenium tests.
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

package dev.stenope.runners;

import java.io.File;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

import dev.stenope.pages.HomePage;
import dev.stenope.pages.InventoryPage;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources"}, glue = {"dev.stenope.steps"})
public class HomePageRunner {
	public static WebDriver realHumanBeing;
	public static HomePage homePage;
	
	@BeforeAll
	public static void setup() {
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		
		realHumanBeing = new ChromeDriver(); 
		
		homePage = new HomePage(realHumanBeing);
	}

	@AfterAll
	public static void teardown() {
		realHumanBeing.quit();
	}
}
