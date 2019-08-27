package serviceportal.cycc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Zahlungsübersicht {

	// Variables
	WebDriver driver;

	// Element Locators
	By zahlungsübersichtTab = By.xpath("(//div[@class='ng-isolate-scope']//li)[8]");

	By elementOfTab8 = By.xpath("(//div[@id='headingThree']/h4)[2]");

	// Constructor
	public Zahlungsübersicht(WebDriver driver) {
		this.driver = driver;
	}

	// Esraa 17/7
	public boolean Zahlungsübersicht_AssertElementISDisplayed() throws Exception {

		Thread.sleep(9000);
		driver.findElement(zahlungsübersichtTab).click();
		Thread.sleep(8000);
		boolean Ispresent = driver.findElement(elementOfTab8).isDisplayed();

		return Ispresent;

	}
}
