/**
 * This is the PetProfilePage class for the Stenope Pet Management System application.
 * This class is used in the E2E Selenium tests.
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

package dev.stenope.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PetProfilePage {
	WebDriver driver;
	
	@FindBy(id="pDataHere")
	public WebElement dataDiv;
	
	public PetProfilePage(WebDriver d) {driver = d; PageFactory.initElements(d,this);}
	
	public String readPetData(String a)	{return dataDiv.findElement(By.id(a)).getText();}
}
