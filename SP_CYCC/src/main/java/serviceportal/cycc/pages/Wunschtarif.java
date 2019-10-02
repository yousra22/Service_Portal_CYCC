package serviceportal.cycc.pages;
//16-9
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shaft.element.ElementActions;

public class Wunschtarif {

	// variables
	WebDriver driver;

	// locators
	By wunschtarifTab = By.xpath("(//div[@class='ng-isolate-scope']//li)[2]");

	By minsmsValue = By.xpath("(//*[@name='radioUnit'])[2]");
	By datenValue = By.xpath("(//*[@name='radioData'])[2]");
	By activeButton = By.id("updateDIYBtn");
	By jaButton = By.xpath("//button[@ng-click='ok()']");
	By successMessage = By.xpath("//div[@class='alert success']//p");
	By activeButtonDisabled = By.xpath("(//a[@ng-disabled='activateButtonDisabled'])[1]");

	// Esraa
	By elementOfTab2 = By.xpath("//div[@id='updateDiy']//h3");

	// constructor
	public Wunschtarif(WebDriver driver) {
		this.driver = driver;
	}

	// Functions

	// Nashwa 26-6
	public String Change_tariff() {
		ElementActions.click(driver, wunschtarifTab);
		ElementActions.waitForElementToBePresent(driver, minsmsValue, 4, true);
		ElementActions.click(driver, minsmsValue);
		ElementActions.click(driver, datenValue);
		ElementActions.click(driver, activeButton);
		ElementActions.click(driver, jaButton);

		ElementActions.waitForElementToBePresent(driver, successMessage, 5, true);
		WebElement Success_message = driver.findElement(successMessage);
		String Success_Message = Success_message.getText();

		return Success_Message;
	}

	// Nashwa 02-07
	public By Check_Active_button() {
		ElementActions.click(driver, wunschtarifTab);

		return activeButtonDisabled;
	}

	// Esraa 17/7
	public boolean wunschtarif_AssertElementISDisplayed() throws Exception {

		Thread.sleep(8000);
		driver.findElement(wunschtarifTab).click();
		Thread.sleep(8000);
		boolean Ispresent = driver.findElement(elementOfTab2).isDisplayed();

		return Ispresent;

	}

}
