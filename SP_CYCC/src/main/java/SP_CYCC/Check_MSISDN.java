package SP_CYCC;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.element.ElementActions;

public class Check_MSISDN {

	// Variables
	WebDriver driver;

	// WebElements
	By Msisdn_TextBox = By.id("exampleInputEmail3");
	By button_CheckMSISDN = By.xpath("//button[@ng-click='validateMSISDN()']");
	By button_MSISDNabrufen= By.xpath("//form[@id= 'checkNumberForm']/button[@ng-click='getGensisMSISDN()']");

	// Constructor
	public Check_MSISDN(WebDriver driver) {
		this.driver = driver;
	}

	// Functions

	public void enterMSISDN(String MSISDN) {
		ElementActions.type(driver, this.Msisdn_TextBox, MSISDN);

		ElementActions.keyPress(driver, button_CheckMSISDN, "Enter");
	}



	public void genesis_MSISDN() {
		ElementActions.keyPress(driver, button_MSISDNabrufen, "Enter");

	}

}
