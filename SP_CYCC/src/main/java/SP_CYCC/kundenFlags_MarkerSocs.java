package SP_CYCC;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shaft.element.ElementActions;

public class kundenFlags_MarkerSocs {

	// variables
	WebDriver driver;

	// locators
	By tab_KundenFlags = By.xpath("//li[@heading = 'KundenFlags/MarkerSocs']");
	By list_fields_kundenFlags = By.xpath("//div[@id= 'flagSocs']//span");
	By first_field = By.xpath("(//div[@id= 'flagSocs']//span)[1]");

	// constructor
	public kundenFlags_MarkerSocs(WebDriver driver) {
		this.driver = driver;
	}

	// functions

	public String[] assert_KunderFlags_Fields() {
		
		
		boolean isClickable;

		do {
			isClickable = ElementActions.isElementClickable(driver, tab_KundenFlags);

		} while (isClickable == false);

		ElementActions.click(driver, tab_KundenFlags);
		
		ElementActions.waitForElementToBePresent(driver, first_field, 2, true);
		String[] arr = new String[7];
		java.util.List<WebElement> fields_displayed = driver.findElements(list_fields_kundenFlags);
		arr[0] = fields_displayed.get(0).getText().trim();
		arr[1] = fields_displayed.get(1).getText().trim();
		arr[2] = fields_displayed.get(2).getText().trim();
		arr[3] = fields_displayed.get(3).getText().trim();
		arr[4] = fields_displayed.get(4).getText().trim();
		arr[5] = fields_displayed.get(5).getText().trim();
		arr[6] = fields_displayed.get(6).getText().trim();

		return arr;

	}
}
