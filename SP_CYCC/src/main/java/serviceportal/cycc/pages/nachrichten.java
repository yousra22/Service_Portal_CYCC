package serviceportal.cycc.pages;
//16-9
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shaft.element.ElementActions;

public class nachrichten {

	// Variables
	WebDriver driver;

	// locators
	By nachrichtenTab = By.xpath("//li[@heading = 'Nachrichten']");

	By msgDaysDropdown = By.id("msg-days");
	By msgDaysList = By.xpath("//select[@id= 'msg-days']/option");

	By msgBodyDropdown = By.id("msg-body");
	By msgBodyList = By.xpath("//select[@id= 'msg-body']/option");

	// Constructor
	public nachrichten(WebDriver driver) {
		this.driver = driver;
	}

	// Functions

	// Yousra 1-7-2019
	public String[] assert_NachrichtenTab_dropdownFields() throws InterruptedException {
		Thread.sleep(3000);
		boolean isClickable;
		String[] arr1 = new String[10];
		do {
			isClickable = ElementActions.isElementClickable(driver, nachrichtenTab);

		} while (isClickable == false);

		ElementActions.click(driver, nachrichtenTab);

		java.util.List<WebElement> optionsMsgDays = driver.findElements(msgDaysList);
		arr1[0] = optionsMsgDays.get(1).getText().trim();
		arr1[1] = optionsMsgDays.get(2).getText().trim();
		arr1[2] = optionsMsgDays.get(3).getText().trim();
		arr1[3] = optionsMsgDays.get(4).getText().trim();
		arr1[4] = optionsMsgDays.get(5).getText().trim();

		java.util.List<WebElement> optionsMsgBody = driver.findElements(msgBodyList);
		arr1[5] = optionsMsgBody.get(1).getText().trim();
		arr1[6] = optionsMsgBody.get(2).getText().trim();
		arr1[7] = optionsMsgBody.get(3).getText().trim();
		arr1[8] = optionsMsgBody.get(4).getText().trim();
		arr1[9] = optionsMsgBody.get(5).getText().trim();

		return arr1;

	}

	// esraa
	public boolean nachrichten_AssertElementISDisplayed() throws Exception {
		By elementoftab10 = By.xpath("//select[@id='msg-days']");

		Thread.sleep(9000);
		Thread.sleep(5000);
		driver.findElement(nachrichtenTab).click();
		Thread.sleep(8000);
		boolean Ispresent = driver.findElement(elementoftab10).isDisplayed();

		return Ispresent;

	}
}
