package dev.stenope.runners;

import java.io.File;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

import dev.stenope.pages.LoginPage;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources"}, glue = {"dev.stenope.steps"})
public class LoginRunner {
	public static WebDriver realHumanBeing;
	public static LoginPage loginPage;
	
	@BeforeAll
	public static void setup() {
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		
		realHumanBeing = new ChromeDriver(); 
		
		loginPage = new LoginPage(realHumanBeing);
	}

	@AfterAll
	public static void teardown() {
		realHumanBeing.quit();
	}
}
