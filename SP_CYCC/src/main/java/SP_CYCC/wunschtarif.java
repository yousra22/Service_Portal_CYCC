package SP_CYCC;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shaft.element.ElementActions;

public class wunschtarif {

	// variables
	WebDriver driver;

	// locators
	By Wunschtarif_tab = By.xpath("(//div[@class='ng-isolate-scope']//li)[2]");

	By min_sms_value = By.xpath("(//*[@name='radioUnit'])[2]");
	By Daten_value = By.xpath("(//*[@name='radioData'])[2]");
	By active_button = By.id("updateDIYBtn");
	By Ja_Button = By.xpath("//button[@ng-click='ok()']");
	By success_Message = By.xpath("//div[@class='alert success']//p");
	By active_button_disabled = By.xpath("(//a[@ng-disabled='activateButtonDisabled'])[1]");

	// constructor
	public wunschtarif(WebDriver driver) {
		this.driver = driver;
	}

	// Functions

	// Nashwa 26-6
	public String Change_tariff() {
		ElementActions.click(driver, Wunschtarif_tab);
		ElementActions.click(driver, min_sms_value);
		ElementActions.click(driver, Daten_value);
		ElementActions.click(driver, active_button);
		ElementActions.click(driver, Ja_Button);
		
		ElementActions.waitForElementToBePresent(driver, success_Message, 5, true);
		WebElement Success_message = driver.findElement(success_Message);
		String Success_Message = Success_message.getText();

		return Success_Message;
	}

	// Nashwa 02-07
	public By Check_Active_button() {
		ElementActions.click(driver, Wunschtarif_tab);

		return active_button_disabled;
	}

}
