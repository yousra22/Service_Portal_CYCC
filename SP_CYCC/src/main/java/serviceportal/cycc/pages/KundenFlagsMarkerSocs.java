package serviceportal.cycc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shaft.element.ElementActions;

public class KundenFlagsMarkerSocs {

	// variables
	WebDriver driver;

	// locators
	By kundenFlagsTab = By.xpath("//li[@heading = 'KundenFlags/MarkerSocs']");
	By kundenFlagsFieldsList = By.xpath("//div[@id= 'flagSocs']//span");
	By firstField = By.xpath("(//div[@id= 'flagSocs']//span)[1]");

	// constructor
	public KundenFlagsMarkerSocs(WebDriver driver) {
		this.driver = driver;
	}

	// functions

	public String[] assert_KunderFlags_Fields() {

		boolean isClickable;

		do {
			isClickable = ElementActions.isElementClickable(driver, kundenFlagsTab);

		} while (isClickable == false);

		ElementActions.click(driver, kundenFlagsTab);

		ElementActions.waitForElementToBePresent(driver, firstField, 2, true);
		String[] arr = new String[7];
		java.util.List<WebElement> fields_displayed = driver.findElements(kundenFlagsFieldsList);
		arr[0] = fields_displayed.get(0).getText().trim();
		arr[1] = fields_displayed.get(1).getText().trim();
		arr[2] = fields_displayed.get(2).getText().trim();
		arr[3] = fields_displayed.get(3).getText().trim();
		arr[4] = fields_displayed.get(4).getText().trim();
		arr[5] = fields_displayed.get(5).getText().trim();
		arr[6] = fields_displayed.get(6).getText().trim();

		return arr;

	}

	// esraa
	public boolean kundenFlags_MarkerSocs_AssertElementISDisplayed() throws Exception {

		By elementoftab12 = By.xpath("(//div[@id='flagSocs']//span)[1]");
		Thread.sleep(9000);
		Thread.sleep(5000);
		driver.findElement(kundenFlagsTab).click();
		Thread.sleep(8000);
		boolean Ispresent = driver.findElement(elementoftab12).isDisplayed();

		return Ispresent;

	}
}
