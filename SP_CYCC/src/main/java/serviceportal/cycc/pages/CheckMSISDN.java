package serviceportal.cycc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.element.ElementActions;

public class CheckMSISDN {

	// Variables
	WebDriver driver;

	// WebElements
	By msisdnTextBox = By.id("exampleInputEmail3");
	By checkMSISDNButton = By.xpath("//button[@ng-click='validateMSISDN()']");
	By mSISDNAbrufenButton =By.xpath("(//form[@id ='checkNumberForm']/button)[2]"); 
	By hintTextAbrufen = By.xpath("//form[@id='checkNumberForm']/preceding-sibling::div/p");
	
	// Constructor
	public CheckMSISDN(WebDriver driver) {
		this.driver = driver;
	}

	// Functions

	public void enterMSISDN(String MSISDN) {
		ElementActions.type(driver, this.msisdnTextBox, MSISDN);

		ElementActions.keyPress(driver, checkMSISDNButton, "Enter");
	}


	public void click_MSISDNAbrufen() {
		ElementActions.keyPress(driver, mSISDNAbrufenButton, "Enter");

	}
	
	public String get_hintText_MSISDN_Abrufen()
	{	
		ElementActions.waitForElementToBePresent(driver, hintTextAbrufen, 10, true);
		return ElementActions.getAttribute(driver, hintTextAbrufen, "text");
	}

}
