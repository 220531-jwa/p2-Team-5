/**
 * This is the UserProfilePage class for the Stenope Pet Management System application.
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
import org.openqa.selenium.support.PageFactory;

public class UserProfilePage {
	WebDriver driver;
	
	public UserProfilePage(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

}
