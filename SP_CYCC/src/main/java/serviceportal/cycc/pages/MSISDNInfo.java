package serviceportal.cycc.pages;

//16-9
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.shaft.element.ElementActions;
import com.shaft.validation.Assertions;

public class MSISDNInfo {

	// Variables
	WebDriver driver;

	// WebElements
	By kaisPopup = By.xpath("//div[@id = 'myModal']//div[@class = 'modal-content']");
	By kaisIdTxt = By.id("user");
	By kaisPwdTxt = By.id("pwd");
	By stornierenButton = By.xpath("//button[contains(.,'Stornieren')]");
	By enigebenButton = By.id("closeKias");
	By closeKiasButton = By.xpath("//div[@id = 'myModal']//div[@class = 'modal-content']//button[@class='close']");

	By neueSucheLink = By.xpath("//a[@href = 'callyaCustomerInfo.do']");
	By msisdnErneutLadenLink = By.xpath("//button[@ng-click='reloadPage()']");
	By sessionTimer = By.id("session_counter");
	By msisdnLink = By.xpath("//button[@ng-click='genesysReload()']");

	By msisdnListFields_ = By
			.xpath("//div[@id = 'personalInfoDiv']//div[@class = 'result']//span[not(contains (@class, 'ng-hide'))]");
	By msisdnListTabs = By.xpath("//table[@class= 'pull-right']//u");

	By mitVermerkCheckBox = By.xpath("//label[text()='Mit Vermerk']");

	// Hadeer
	By listCancelCustomer = By.xpath("//div[@class='white-content']");
	By prepaidIdentInfoField = By.xpath("(//div[@id= 'additionalpesonalInfoView']//li)[7]");

	// Bishoy
	By zahlungsubersichtTab = By.xpath("//li[@heading='Zahlungsübersicht']");
	By personlicheDatenTab = By.xpath("//li[@heading='Persönliche  Daten']");
	By nachrichtSendenTab = By.xpath("//li[@heading='Nachricht senden']");
	// Bishoy 08-08-2019
	By specialInstructionsButton = By.className("special-instrucion");
	By popup = By.xpath("//*[@id=\"myModal\"]//div[@class=\"modal-content\"]");
	By textArea = By.id("inputComment");

	// Esraa
	By SpecialInstruction = By.xpath("(//tr[@class='pull-right']/td)[3]/button");
	By SubscriberStatus = By.xpath("(//div[@class='result']//li)[2]");

	// Hadeer 10-9-2019
    By error_msg = By.xpath ("//p[@id='errorMsg']");

    
    // Bishoy 18-09-2019 (for cancelled customer)
    By CancelledCustomer_SIMnumber = By.xpath("//li[span[text()='SIM number:']]");
    By CancelledCustomer_PUK = By.xpath("//li[span[text()='PUK:']]");
    By CancelledCustomer_SIMtype = By.xpath("//li[span[text()='SIM type:']]");

    
    
	// Constructor
	public MSISDNInfo(WebDriver driver) {
		this.driver = driver;
	}

	// Functions

	// yousra 13-6-2019
	public void verfiy_KaisPopup_notDisplayed() throws InterruptedException {
		ElementActions.waitForElementToBePresent(driver, neueSucheLink, 10, true);
		Thread.sleep(4000);
		// Verifications.verifyElementExists(driver, kais_popup, false);
		Assertions.assertElementExists(driver, kaisPopup, false); // mesh sh3'la

	}

	public void assert_KaisPopup_displayed() {
		ElementActions.waitForElementToBePresent(driver, kaisPopup, 3, true);
		Assertions.assertElementExists(driver, kaisPopup, true);
	}

	public void close_KaisPop_WithNoCred() throws InterruptedException {
		ElementActions.waitForElementToBePresent(driver, kaisPopup, 20, true);
		Thread.sleep(2000);
		ElementActions.click(driver, stornierenButton);
	}

	// yousra 13-6-2019
	public void enter_KaisCredintails(String username, String password) throws InterruptedException {
		ElementActions.waitForElementToBePresent(driver, kaisPopup, 10, true);
		Thread.sleep(1000);
		ElementActions.type(driver, kaisIdTxt, username);
		ElementActions.type(driver, kaisPwdTxt, password);
		ElementActions.click(driver, enigebenButton);

		Thread.sleep(4000);

	}

	// Yousra
	public boolean check_msisdn_link_Displayed() {
		return ElementActions.isElementDisplayed(driver, msisdnLink);
	}

	public void assert_MsisdnNumber_Displayed(String MSISDN) {
		By MSISDNValue = By
				.xpath("//div[@class ='white-content']//div[@class = 'result']//li[text()='" + MSISDN + "']");

		Assertions.assertElementExists(driver, MSISDNValue, true);
	}

	public boolean verify_NeueSuche_link() {

		Boolean isDisplayed = ElementActions.isElementDisplayed(driver, neueSucheLink);

		return isDisplayed;

	}

	public void click_NeueSuche_link() {
		ElementActions.click(driver, neueSucheLink);
	}

	public By assert_SessionCounterTime() {

		return sessionTimer;
	}

	public void assert_GensisMSISDN_displayed() {
		By Gensis_MSISDN = By.xpath("//li[text()='491620491952']");
		Assertions.assertElementExists(driver, Gensis_MSISDN, true);

	}

	public void click_MSISDNerneutLaden_link() {
		ElementActions.click(driver, msisdnErneutLadenLink);
	}

	public void click_MSISDN_link() {
		ElementActions.click(driver, msisdnLink);
	}

	// yousra 6-9-2019
	public String[] check_MSISDNInfo_Fields() {

		ElementActions.waitForElementToBePresent(driver, msisdnLink, 5, true);

		java.util.List<WebElement> fields = driver.findElements(msisdnListFields_);
		String[] arr = new String[15];
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
		arr[12] = fields.get(12).getText();
		arr[13] = fields.get(13).getText();
		arr[14] = fields.get(14).getText();

		return arr;
	}

	// yousra 6-9-2019
	public String[] check_MSISDNInfo_tabs() {

		ElementActions.waitForElementToBePresent(driver, msisdnLink, 4, true);

		java.util.List<WebElement> tabs = driver.findElements(msisdnListTabs);

		String[] arr = new String[5];
		arr[0] = tabs.get(0).getText();
		arr[1] = tabs.get(1).getText();
		arr[2] = tabs.get(2).getText();
		arr[3] = tabs.get(3).getText();

		return arr;

	}

	// Hadeer
	public void check_Prepaid_ident_Info_field() throws InterruptedException {

		Assertions.assertElementAttribute(driver, prepaidIdentInfoField, "text", "Prepaid ident Information:", 1, true);
	}

	// Bishoy 16-07-2019
	public void clickPersonlicheDatenTab() {
		ElementActions.waitForElementToBePresent(driver, personlicheDatenTab, 4, true);
		ElementActions.click(driver, personlicheDatenTab);
	}

	// Bishoy 16-07-2019
	public void clickNachrichtSendenTab() throws InterruptedException {
		ElementActions.waitForElementToBePresent(driver, nachrichtSendenTab, 4, true);
		ElementActions.click(driver, nachrichtSendenTab);
	}

	// Esraa ( to be added in page base)
	public boolean AssertButtonIsNotClickable(By Locator) throws Exception {

		Thread.sleep(5000);
		String ClassAttribute = driver.findElement(Locator).getAttribute("class");
		// System.out.println(ClassAttribute);
		boolean ClassContainsDisabled = ClassAttribute.contains("disable");
		return ClassContainsDisabled;

	}

	// Bishoy 16-07-2019
	public String getZahlungsubersichtTabText() {
		ElementActions.waitForElementToBePresent(driver, zahlungsubersichtTab, 4, true);
		return ElementActions.getText(driver, zahlungsubersichtTab);
	}

	// Bishoy 08-08-2019
	public String getSpecialInstructionsColor() {
		ElementActions.waitForElementToBePresent(driver, specialInstructionsButton, 4, true);
		return ElementActions.getCSSProperty(driver, specialInstructionsButton, "color");
	}

	// Bishoy 08-08-2019
	public void clickSpecialInstructionsButton() {
		ElementActions.click(driver, specialInstructionsButton);
	}

	// Bishoy 08-08-2019
	public void checkPopupExists() {
		ElementActions.waitForElementToBePresent(driver, popup, 4, true);
	}

	// Bishoy 08-08-2019
	public String getTextBackgroundColor() {
		return ElementActions.getCSSProperty(driver, textArea, "background-color");
	}

	// Bishoy 08-08-2019
	public String getTextColor() {
		return ElementActions.getCSSProperty(driver, textArea, "color");
	}

	// Bishoy 08-08-2019
	public String getTextFontWeight() {
		return ElementActions.getCSSProperty(driver, textArea, "font-weight");
	}

	// Bishoy 08-08-2019
	public String getText() {
		return ElementActions.getText(driver, textArea);
	}

	// Esraa ( to be added in page base)
	public boolean AssertButtonIsNotClickable() throws Exception {

		Thread.sleep(5000);
		String ClassAttribute = driver.findElement(SpecialInstruction).getAttribute("class");
		// System.out.println(ClassAttribute);
		boolean ClassContainsDisabled = ClassAttribute.contains("disable");
		return ClassContainsDisabled;

	}

	// Bishoy 18-09-2019
	public String getCancelledCustomer_SIMnumber() {
		return ElementActions.getText(driver, CancelledCustomer_SIMnumber);
	}

	// Bishoy 18-09-2019
	public String getCancelledCustomer_PUK() {
		return ElementActions.getText(driver, CancelledCustomer_PUK);
	}

	// Bishoy 18-09-2019
	public String getCancelledCustomer_SIMtype() {
		return ElementActions.getText(driver,CancelledCustomer_SIMtype);
	}

	// esraa 14-9-2019 //25570
	public void AssertStatusForValenciaCustomerIsCancelled() throws Exception {

		String Status = driver.findElement(SubscriberStatus).getText();

		Assert.assertTrue(Status.contains("Cancelled"));
	}

	// hdeer 10-9-2019 TC 25579

	public void enter_wrongKaisCredintails3times(String username, String password) throws InterruptedException {
		ElementActions.waitForElementToBePresent(driver, kaisPopup, 10, true);
		Thread.sleep(8000);
		ElementActions.type(driver, kaisIdTxt, username);
		ElementActions.type(driver, kaisPwdTxt, password);

		for (int i = 0; i <= 2; i++) {
			ElementActions.click(driver, enigebenButton);

			if (i < 2) {

				Assertions.assertElementAttribute(driver, error_msg, "text", "Benutzer Passwort stimmt nicht überein",
						1, true);
			}

		}

	}

}
