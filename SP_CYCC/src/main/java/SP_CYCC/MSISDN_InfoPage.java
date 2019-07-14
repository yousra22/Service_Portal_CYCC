package SP_CYCC;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shaft.element.ElementActions;
import com.shaft.validation.Assertions;

public class MSISDN_InfoPage {

	// Variables
	WebDriver driver;

	// WebElements
	By kais_popup = By.xpath("//div[@id = 'myModal']//div[@class = 'modal-content']");
	By txt_kaisId = By.id("user");
	By txt_KaisPwd = By.id("pwd");
	By button_Stornieren = By.xpath("//button[contains(.,'Stornieren')]");
	By button_Enigeben = By.id("closeKias");
	By ButtonCloseKias = By.xpath("//div[@id = 'myModal']//div[@class = 'modal-content']//button[@class='close']");

	By neue_Suche_link = By.xpath("//a[@href = 'callyaCustomerInfo.do']");
	By MSISDNerneutLaden_link = By.xpath("//button[@ng-click='reloadPage()']");
	By sessionTimer = By.id("session_counter");
	By MSISDN_link = By.xpath("//button[@ng-click='genesysReload()']");

	By list_fields_msisdn = By
			.xpath("//div[@id = 'personalInfoDiv']//div[@class = 'result']//span[not(contains (@class, 'ng-hide'))]");
	By list_tabs_msisdn = By.xpath("//table[@class= 'pull-right']//u");

	By Mit_Vermerk_CheckBox = By.xpath("//label[text()='Mit Vermerk']");

	// Constructor
	public MSISDN_InfoPage(WebDriver driver) {
		this.driver = driver;
	}

	// Functions

	// yousra 13-6-2019
	public void verfiy_KaisPopup_notDisplayed() throws InterruptedException {
		ElementActions.waitForElementToBePresent(driver, neue_Suche_link, 10, true);
		Thread.sleep(4000);
		// Verifications.verifyElementExists(driver, kais_popup, false);
		Assertions.assertElementExists(driver, kais_popup, false); // mesh sh3'la

	}

	public void Assert_KaisPopup() {
		ElementActions.waitForElementToBePresent(driver, kais_popup, 3, true);
		Assertions.assertElementExists(driver, kais_popup, true);
	}

	public void close_KaisPop_WithNoCred() throws InterruptedException {
		ElementActions.waitForElementToBePresent(driver, kais_popup, 10, true);
		Thread.sleep(1000);

		ElementActions.click(driver, button_Stornieren);
	}

	// yousra 13-6-2019
	public void enter_KaisCredintails(String username, String password) throws InterruptedException {
		ElementActions.waitForElementToBePresent(driver, kais_popup, 10, true);
		Thread.sleep(1000);
		ElementActions.type(driver, txt_kaisId, username);
		ElementActions.type(driver, txt_KaisPwd, password);
		ElementActions.click(driver, button_Enigeben);

		Thread.sleep(4000);

	}

	public void assert_MsisdnNumber_Displayed(String MSISDN) {
		By MSISDNValue = By
				.xpath("//div[@class ='white-content']//div[@class = 'result']//li[text()='" + MSISDN + "']");

		Assertions.assertElementExists(driver, MSISDNValue, true);
	}

	public boolean verify_NeueSuche_link() {

		Boolean isDisplayed = ElementActions.isElementDisplayed(driver, neue_Suche_link);

		return isDisplayed;

	}

	public void click_NeueSuche_link() {
		ElementActions.click(driver, neue_Suche_link);
	}

	public By assert_SessionCounterTime() {

		return sessionTimer;
	}

	public void assert_GensisMSISDN() {
		By Gensis_MSISDN = By.xpath("//li[text()='491620491952']");
		Assertions.assertElementExists(driver, Gensis_MSISDN, true);

	}

	public void click_MSISDNerneutLaden_link() {
		ElementActions.click(driver, MSISDNerneutLaden_link);
	}

	public void click_MSISDN_link() {
		ElementActions.click(driver, MSISDN_link);
	}

	// yousra 6-9-2019
	public String[] check_MSISDNInfo_Fields() {
		
		ElementActions.waitForElementToBePresent(driver, MSISDN_link, 4, true);
		
		java.util.List<WebElement> fields = driver.findElements(list_fields_msisdn);
		String[] arr = new String[12];
		arr[0] = fields.get(0).getText();
		arr[1] = fields.get(1).getText();
		arr[2] = fields.get(2).getText();
		arr[3] = fields.get(3).getText();
		arr[4] = fields.get(4).getText();
		arr[5] = fields.get(5).getText();
		arr[6] = fields.get(6).getText();
		arr[7] = fields.get(7).getText();
		arr[8] = fields.get(8).getText();
		arr[9] = fields.get(9).getText();
		arr[10] = fields.get(10).getText();
		arr[11] = fields.get(11).getText();

		return arr;
	}

	// yousra 6-9-2019
	public String[] check_MSISDNInfo_tabs() {
		
		ElementActions.waitForElementToBePresent(driver, MSISDN_link, 4, true);
		
		java.util.List<WebElement> tabs = driver.findElements(list_tabs_msisdn);

		String[] arr = new String[5];
		arr[0] = tabs.get(0).getText();
		arr[1] = tabs.get(1).getText();
		arr[2] = tabs.get(2).getText();
		arr[3] = tabs.get(3).getText();

		return arr;

	}

	// no need for this function
	// public void assert_CheckMSISDN_opened() {
	// Assertions.assertBrowserAttribute(driver, "currenturl",
	// "cycc/serviceportal/callyaCustomerInfo.do", 3, true);
	// }

	// by yousra
//	public void navigateToURL() {
	// BrowserActions.navigateToURL(driver,
	// "https://ecycc8.stweb.elabs.svcs.hpe.com/cycc/serviceportal/getCustomerType.do?MSISDN=491620491952&clearsession=true");
//	}

}
