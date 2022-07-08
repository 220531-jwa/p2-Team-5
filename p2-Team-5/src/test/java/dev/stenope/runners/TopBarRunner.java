package dev.stenope.runners;

import java.io.File;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import dev.stenope.pages.TopBarPage;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources"}, glue = {"dev.stenope.steps"})
public class TopBarRunner {

	public static WebDriver driver;
	public static TopBarPage topBar;
	
	@BeforeAll
	public static void setup() {
		File edge = new File("src/test/resources/msedgedriver.exe");
		System.setProperty("webdriver.edge.driver", edge.getAbsolutePath());
		
		driver = new EdgeDriver(); 
		
		topBar = new TopBarPage(driver);
		
		System.out.println("inside setup method");
	}

	@AfterAll
	public static void teardown() {
		driver.quit();
	}
	
}
