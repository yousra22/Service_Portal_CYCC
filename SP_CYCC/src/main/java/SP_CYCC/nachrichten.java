package SP_CYCC;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shaft.element.ElementActions;

public class nachrichten {

	// Variables
	WebDriver driver;

	// locators
	By tab_Nachrichten = By.xpath("//li[@heading = 'Nachrichten']");

	By dropdown_msgDays = By.id("msg-days");
	By list_msgDays = By.xpath("//select[@id= 'msg-days']/option");

	By dropdown_msgBody = By.id("msg-body");
	By list_msgBody = By.xpath("//select[@id= 'msg-body']/option");

	// Constructor
	public nachrichten(WebDriver driver) {
		this.driver = driver;
	}

	// Functions

	// Yousra 1-7-2019
	public String[] assert_NachrichtenTab_dropdownFields() {
		boolean isClickable;
		String[] arr1 = new String[10];
		do {
			isClickable = ElementActions.isElementClickable(driver, tab_Nachrichten);

		} while (isClickable == false);

		ElementActions.click(driver, tab_Nachrichten);

		java.util.List<WebElement> optionsMsgDays = driver.findElements(list_msgDays);
		arr1[0] = optionsMsgDays.get(1).getText().trim();
		arr1[1] = optionsMsgDays.get(2).getText().trim();
		arr1[2] = optionsMsgDays.get(3).getText().trim();
		arr1[3] = optionsMsgDays.get(4).getText().trim();
		arr1[4] = optionsMsgDays.get(5).getText().trim();

		java.util.List<WebElement> optionsMsgBody = driver.findElements(list_msgBody);
		arr1[5] = optionsMsgBody.get(1).getText().trim();
		arr1[6] = optionsMsgBody.get(2).getText().trim();
		arr1[7] = optionsMsgBody.get(3).getText().trim();
		arr1[8] = optionsMsgBody.get(4).getText().trim();
		arr1[9] = optionsMsgBody.get(5).getText().trim();

		return arr1;

	}
}
