package serviceportal.cycc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Tarifwechsel {

	// Variables
	WebDriver driver;

	// Element Locators

	// Esraa
	By tarifwechselTab = By.xpath("(//div[@class='ng-isolate-scope']//li)[3]");
	By elementOfTab3 = By.xpath("//div[@id='changeTariff']//h3[@class='header-title']");

	// Constructor
	public Tarifwechsel(WebDriver driver) {
		this.driver = driver;
	}

	// Esraa 17/7
	public boolean Tarifwechsel_AssertElementISDisplayed() throws Exception {

		Thread.sleep(5000);
		driver.findElement(tarifwechselTab).click();
		Thread.sleep(8000);
		boolean Ispresent = driver.findElement(elementOfTab3).isDisplayed();

		return Ispresent;

	}
}
