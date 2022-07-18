/**
 * This is the LoginPage class for the Stenope Pet Management System application.
 * This class is used in the E2E Selenium tests.
 * 
 * @author joshuacoombs
 * @author wlcross
 * @author TCPrater
 * 
 * @version 1.0
 */

package dev.stenope.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private WebDriver realHumanBeing;
	
	public LoginPage(WebDriver realHumanBeing) {
		this.realHumanBeing = realHumanBeing;
		PageFactory.initElements(realHumanBeing, this);
	}
	
	@FindBy(id = "uNameBox")
	public WebElement uName;
	
	@FindBy(id = "pKeyBox")
	public WebElement pKey;
	
	@FindBy(id = "loginButton")
	public WebElement loginButton;
}
