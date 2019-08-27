package serviceportal.cycc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shaft.element.ElementActions;

public class PersonlicheDaten {

	// Bishoy 21-07-2019
	WebDriver driver;

	// Element Locators
	By personlicheDatenTab = By.xpath("//div[@class='ng-isolate-scope']/ul/li[1]");
	By kundenkennwort = By.xpath("//li[@class='ng-binding ng-scope']/span");
	By allLabels = By.xpath("//div[@id='personal']//li/span");

	// Esraa
	By elementoftab1 = By.xpath("//div[@class='ng-isolate-scope']//div[@ng-hide='isPUCustomer']//h3");

	// Bishoy 21-07-2019
	// constructor
	public PersonlicheDaten(WebDriver driver) {
		this.driver = driver;
	}

	// Bishoy 21-07-2019
	public String getKundenkennwortText() {
		return ElementActions.getText(driver, kundenkennwort);
	}

	// Bishoy 25-07-2019

	public String getLabelText(int i) {
		java.util.List<WebElement> fields = driver.findElements(allLabels);
		return fields.get(i).getText();
	}

	// esraa
	public boolean personliche_Daten_AssertElementISDisplayed() throws Exception {

		Thread.sleep(5000);
		driver.findElement(personlicheDatenTab).click();
		Thread.sleep(9000);
		boolean Ispresent = driver.findElement(elementoftab1).isDisplayed();

		return Ispresent;

	}
}
