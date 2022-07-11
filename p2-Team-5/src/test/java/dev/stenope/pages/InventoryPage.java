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
	
	@FindBy(name = "userItemDropdown")
	public WebElement userItemDropdown;
	public Select userItemDropdownSelect = new Select(userItemDropdown);
	
	@FindBy(name = "petItemDropdown")
	public WebElement petItemDropdown;
	public Select petItemDropdownSelect = new Select(petItemDropdown);
}
