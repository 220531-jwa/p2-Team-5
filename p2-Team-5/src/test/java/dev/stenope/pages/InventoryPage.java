package dev.stenope.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage {
private WebDriver realHumanBeing;
	
	public InventoryPage(WebDriver realHumanBeing) {
		this.realHumanBeing = realHumanBeing;
		PageFactory.initElements(realHumanBeing, this);
	}
	
	@FindBy(id = "backpack")
	public WebElement userInventory;
	
	@FindBy(id = "petBackpack")
	public WebElement petsInventory;
	
	@FindBy(name = "userItemDropdown1")
	public WebElement userItemDropdown1;
	public Select userItemDropdownSelect1 = new Select(userItemDropdown1);
	
	@FindBy(name = "userItemDropdown2")
	public WebElement userItemDropdown2;
	public Select userItemDropdownSelect2 = new Select(userItemDropdown2);
	
	@FindBy(name = "petItemDropdown")
	public WebElement petItemDropdown;
	public Select petItemDropdownSelect = new Select(petItemDropdown);
	
	@FindBy(xpath = "/html/body/div[2]/ul/li")
	public WebElement UserItemTest1;
	
	@FindBy(xpath = "/html/body/div[2]/ul/li[2]")
	public WebElement UserItemTest2;
	
	@FindBy(xpath = "/html/body/div[3]/ul/li")
	public WebElement PetItemTest1;
}
