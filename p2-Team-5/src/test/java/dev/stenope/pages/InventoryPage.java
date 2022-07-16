package dev.stenope.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {
	private WebDriver realHumanBeing;
	
	public InventoryPage(WebDriver realHumanBeing) {
		this.realHumanBeing = realHumanBeing;
		PageFactory.initElements(realHumanBeing, this);
	}
	
	@FindBy(id = "inventoryLink")
	public WebElement inventoryButton;
	
	@FindBy(xpath = "/html/body/h4[1]")
	public WebElement userInventory;
	
	@FindBy(xpath = "/html/body/h4[2]")
	public WebElement petsInventory;
	
	@FindBy(id = "petBackPack")
	public WebElement petBackPack;
	
	@FindBy(id = "userselector1")
	public WebElement userSelector1;
	//public Select uSelect1 = new Select(userSelector1);
	
	@FindBy(id = "userselector2")
	public WebElement userSelector2;
	//public Select uSelect2 = new Select(userSelector2);
	
	@FindBy(id = "userselector3")
	public WebElement userSelector3;
	//public Select uSelect3 = new Select(userSelector3);
	
	@FindBy(id = "petDrop")
	public WebElement petItemDropdown;
	//public Select pSelect1 = new Select(petItemDropdown);
	
	@FindBy(xpath = "/html/body/select/option[2]")
	public WebElement firstPet;
	
	@FindBy(xpath = "/html/body/div[2]/div[1]/div/select")
	public WebElement whichPet;
	//public Select whichPetSelect = new Select(whichPet);
	
	@FindBy(xpath = "/html/body/div[2]/div[2]/div/button")
	public WebElement giveToPet;
	
	@FindBy(xpath = "//*[@id=\"petitemselector2\"]")
	public WebElement userItemTest2;
	
	@FindBy(id = "uNameBox")
	public WebElement uName;
	
	@FindBy(id = "pKeyBox")
	public WebElement pKey;
	
	@FindBy(id = "loginButton")
	public WebElement loginButton;
}
