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
	
	@FindBy(id = "uName")
	public WebElement uName;
	
	@FindBy(id = "pKey")
	public WebElement pKey;
	
	@FindBy(id = "loginButton")
	public WebElement loginButton;
}
