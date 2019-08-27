package serviceportal.cycc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.element.ElementActions;
import com.shaft.validation.Assertions;
import com.shaft.validation.Verifications;

public class Nutzungsubersicht {

	// Variables
	WebDriver driver;

	// WebElements
	By nutzungsübersichtTab = By.xpath("//a[contains(text(),'Nutzungsübersicht')]");
	By usageTab = By.xpath("//h3[contains(text(),'Usage History')]");

	// Esraa
	By elementOfTab5 = By.xpath("//div[@id='usagehistory']/h3");
	By monatübersicht = By.xpath("(//ul[@role='tablist']/li/a[@aria-controls='Month View'])[1]");

	// Hadeer
	By list_day_month_view = By.xpath("//div[@id='usagehistory_data']//ul[@class='nav nav-tabs']");
	By tagesübersichtTab = By.xpath("(//ul[@role='tablist']/li/a[@aria-controls='Day View'])[1]");
	By monatübersichtTab = By.xpath("(//ul[@role='tablist']/li/a[@aria-controls='Month View'])[1]");

	// Constructor
	public Nutzungsubersicht(WebDriver driver) {
		this.driver = driver;
	}

	// Functions

	// hadeer 18-7-2019
	public void Assert_Usage_history() throws InterruptedException {
		ElementActions.waitForElementToBePresent(driver, nutzungsübersichtTab, 1, true);

		Verifications.verifyElementExists(driver, nutzungsübersichtTab, true);
		Thread.sleep(2000);

		Assertions.assertElementExists(driver, usageTab, true);
		Assertions.assertElementAttribute(driver, usageTab, "text", "Usage History", 1, true);
	}

	// Esraa 17/7
	public boolean nutzungsubersicht_AssertElementISDisplayed() throws Exception {

		Thread.sleep(9000);
		Thread.sleep(5000);
		driver.findElement(nutzungsübersichtTab).click();
		Thread.sleep(8000);
		boolean Ispresent = driver.findElement(elementOfTab5).isDisplayed();

		return Ispresent;

	}

	// Hadeer 06-8-2019
	public void Assert_Weekview_PUA() throws InterruptedException {
		ElementActions.waitForElementToBePresent(driver, usageTab, 1, true);
		Thread.sleep(8000);

		Assertions.assertElementAttribute(driver, list_day_month_view, "size", "(939, 39)", 1, true);
		Assertions.assertElementAttribute(driver, tagesübersichtTab, "text", "Tagesübersicht", 1, true);

		ElementActions.click(driver, monatübersichtTab);
		Assertions.assertElementAttribute(driver, monatübersichtTab, "text", "Monatübersicht", 1, true);

	}

	// Hadeer 06-8-2019
	public void Assert_Weekview_MMO() throws InterruptedException {
		ElementActions.waitForElementToBePresent(driver, usageTab, 1, true);
		Thread.sleep(8000);

		Assertions.assertElementAttribute(driver, list_day_month_view, "size", "(939, 39)", 1, true);
		Assertions.assertElementAttribute(driver, tagesübersichtTab, "text", "Tagesübersicht", 1, true);

		ElementActions.click(driver, monatübersichtTab);
		Assertions.assertElementAttribute(driver, monatübersichtTab, "text", "Monatübersicht", 1, true);
	}

}
