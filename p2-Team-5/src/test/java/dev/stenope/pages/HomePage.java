package dev.stenope.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	private WebDriver realHumanBeing;
	
	public HomePage(WebDriver realHumanBeing) {
		this.realHumanBeing = realHumanBeing;
		PageFactory.initElements(realHumanBeing, this);
	}
	
	@FindBy(xpath = "/html/body/button[1]")
	public WebElement createPetButton;
	
	@FindBy(id = "searchBar")
	public WebElement searchBar;
	
	@FindBy(id = "searchButton")
	public WebElement searchButton;
	
	@FindBy(xpath = "/html/body/div[2]/div")
	public WebElement searchResult;
	
	@FindBy(id = "petName")
	public WebElement petName;
	
	@FindBy(id = "submitNewPet")
	public WebElement submitNewPet;
	
	@FindBy(id = "uNameBox")
	public WebElement uName;
	
	@FindBy(id = "pKeyBox")
	public WebElement pKey;
	
	@FindBy(id = "loginButton")
	public WebElement loginButton;
}
