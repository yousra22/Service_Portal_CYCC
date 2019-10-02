package serviceportal.cycc.pages;
//16-9
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.element.ElementActions;

public class NachrichtSenden {

	// Bishoy 16-07-2019
	WebDriver driver;
	// By firstRadioButton = By.xpath("//*[@id=\"sendmessage\"]/form/div[1]/label");
	By firstRadioButton = By.xpath("(//div[@id='sendmessage']//label)[1]"); // yousra
	// By secondRadioButton =
	// By.xpath("//*[@id=\"sendmessage\"]/form/div[2]/label");
	By secondRadioButton = By.xpath("(//div[@id='sendmessage']//label)[2]"); // yousra

	// By AppPushChannel =
	// By.xpath("//*[@id=\"sendmessage\"]/form/div[3]/label[1]/input");
	By appPushChannel = By.xpath("(//div[@id='sendmessage']//label)[3]"); // yousra
	// By InboxChannel =
	// By.xpath("//*[@id=\"sendmessage\"]/form/div[3]/label[2]/input");
	By inboxChannel = By.xpath("(//div[@id='sendmessage']//label)[4]");
	// By SMSchannel =
	// By.xpath("//*[@id=\"sendmessage\"]/form/div[3]/label[3]/input");
	By smsChannel = By.xpath("(//div[@id='sendmessage']//label)[5]");

	By sendenButton = By.id("sendMessage");

	// Esraa
	By nachrichtSendenTab = By.xpath("(//div[@class='ng-isolate-scope']//li)[9]");
	By elementOfTab9 = By.xpath("//div[@id='headingOne']/h4");

	// Bishoy 21-08-2019
	By message = By.xpath("//*[@id=\"pesonalInfoView\"]//*[@class=\"alert success\"]//p");

	// Bishoy 16-07-2019
	// Constructor
	public NachrichtSenden(WebDriver driver) {
		this.driver = driver;
	}

	// Bishoy 16-07-2019
	public String getFirstRadioButtonText() {
		ElementActions.waitForElementToBePresent(driver, firstRadioButton, 4, true);
		return driver.findElement(firstRadioButton).getText();
	}

	// Bishoy 16-07-2019
	public String getSecondRadioButtonText() {
		ElementActions.waitForElementToBePresent(driver, secondRadioButton, 4, true);
		return driver.findElement(secondRadioButton).getText();
	}

	// Bishoy 16-07-2019
	public void selectSMSchannel() {
		ElementActions.waitForElementToBePresent(driver, smsChannel, 4, true);
		ElementActions.click(driver, smsChannel);
	}

	// Bishoy 16-07-2019
	public void selectAppPushChannel() {
		ElementActions.waitForElementToBePresent(driver, appPushChannel, 4, true);
		ElementActions.click(driver, appPushChannel);
	}

	// Bishoy 16-07-2019
	public void selectInboxChannel() {
		ElementActions.waitForElementToBePresent(driver, inboxChannel, 4, true);
		ElementActions.click(driver, inboxChannel);
	}

	// Bishoy 16-07-2019
	public void clickSendenButton() {
		ElementActions.waitForElementToBePresent(driver, sendenButton, 4, true);
		ElementActions.click(driver, sendenButton);
	}

	// Esraa 17/7
	public boolean nachricht_senden_AssertElementISDisplayed() throws Exception {

		Thread.sleep(9000);
		Thread.sleep(5000);
		driver.findElement(nachrichtSendenTab).click();
		Thread.sleep(8000);
		boolean Ispresent = driver.findElement(elementOfTab9).isDisplayed();

		return Ispresent;

	}

	// Bishoy 16-07-2019
	public void clickNachrichtSendenTab() throws InterruptedException {
		ElementActions.waitForElementToBePresent(driver, nachrichtSendenTab, 4, true);
		ElementActions.click(driver, nachrichtSendenTab);
	}

	// Bishoy 21-08-2019
	public void selectFirstRadioButton() {
		ElementActions.waitForElementToBePresent(driver, firstRadioButton, 4, true);
		ElementActions.click(driver, firstRadioButton);
	}

	// Bishoy 21-08-2019
	public String getMessageText() {
		ElementActions.waitForElementToBePresent(driver, message, 4, true);
		return ElementActions.getText(driver, message);
	}

}
