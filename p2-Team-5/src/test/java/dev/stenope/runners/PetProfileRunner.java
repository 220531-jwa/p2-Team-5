/**
 * This is the PetProfileRunner class for the Stenope Pet Management System application.
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
import org.openqa.selenium.edge.EdgeDriver;

import dev.stenope.pages.PetProfilePage;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith (Cucumber.class)
@CucumberOptions(features = {"src/test/resources"}, glue = {"dev.stenope.steps"})
public class PetProfileRunner {
	public static WebDriver wd40;
	public static PetProfilePage p3;
	
	@BeforeAll 
	public static void setup()
	{
		File edge = new File("src/test/resources/msedgedriver.exe");
		System.setProperty("webdriver.edge.driver", edge.getAbsolutePath());
		
		wd40 = new EdgeDriver(); 
		
		p3 = new PetProfilePage(wd40);
		
		System.out.println("inside setup method");
	}
	
	@AfterAll
	public static void teardown() {wd40.quit();}
	
}
