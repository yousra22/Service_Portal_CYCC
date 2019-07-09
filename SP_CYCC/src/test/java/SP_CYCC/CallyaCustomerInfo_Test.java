package SP_CYCC;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.browser.BrowserFactory;
import com.shaft.io.ExcelFileManager;
import com.shaft.validation.Assertions;
import com.shaft.validation.Verifications;

import Base_Package.base_Layer;
import SP_CYCC.Check_MSISDN;
import SP_CYCC.Login;
import SP_CYCC.MSISDN_InfoPage;

public class CallyaCustomerInfo_Test {

	WebDriver driver;
	ExcelFileManager testDataReader;
	String MSISDNinMocks = "491620491952";
	String MSISDNwithout49 = "1620491952";
	String MSISDNwithSpace = "  491620491952  ";
	Check_MSISDN checkMSISDN_Object;
	MSISDN_InfoPage callyaCustomerInfo_Object;
	base_Layer baseLayer;

	Login loginObject;

	@BeforeClass // Set-up method, to be run once before the first test
	public void beforeClass() {
		// System.setProperty("testDataFilePath",
		// "src/test/resources/TestDataFiles/testSuite01/TestData.xlsx");
		// testDataReader = new
		// ExcelFileManager(System.getProperty("testDataFilePath"));
		driver = BrowserFactory.getBrowser();
		// BrowserActions.setWindowSize(driver, 3840, 2160);
	}

	@Test
	public void navigateToURLandLogin() {
		loginObject = new Login(driver); // initialize a new instance of the page

		loginObject.navigateToURL(); // Navigate to Page URL
		loginObject.enterLoginInfo("admin", "admin"); // Enter username & Password then click OK

		loginObject.click_CallYaCustomerInfo();

		// Nashwa 29-5-2019
		loginObject.focus_On_CheckMsisdnPage(); // focus on Check Msisdn Tab

	}

	// Yousra
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_SessionTimer() {

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page
		baseLayer = new base_Layer(driver);
		baseLayer.navigateToURL(); // Navigate to Page UR
		By sessionTimer = callyaCustomerInfo_Object.assert_SessionCounterTime();
		Assertions.assertElementAttribute(driver, sessionTimer, "text", "29:59  ", 1, true);

	}

	// Yousra
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_NeueSuche_link() {
		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page
		baseLayer = new base_Layer(driver);
		baseLayer.navigateToURL(); // Navigate to Page URL

		By neue_Suche_link = callyaCustomerInfo_Object.verify_NeueSuche_link();
		Verifications.verifyElementExists(driver, neue_Suche_link, true);
		callyaCustomerInfo_Object.click_NeueSuche_link();
		Assertions.assertBrowserAttribute(driver, "currenturl", "cycc/serviceportal/callyaCustomerInfo.do", 3, true);
		// callyaCustomerInfo_Object.assert_CheckMSISDN_opened();

	}

	// Nashwa 30-5-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void checkMSISDNmapping() throws InterruptedException {

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);
		checkMSISDN_Object = new Check_MSISDN(driver);

		baseLayer.navigateToCheckMsisdnURL();

		checkMSISDN_Object.enterMSISDN(MSISDNwithout49);

		Thread.sleep(20000);

		// msisdnInfo.closeKiasPopup();
		By MSISDNValue = callyaCustomerInfo_Object.checkMsisdn(MSISDNinMocks);

		Verifications.verifyElementExists(driver, MSISDNValue, true);
	}

	// Nashwa 30-5-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })

	public void VerifyKiasPopup() throws InterruptedException {
		checkMSISDN_Object = new Check_MSISDN(driver);
		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);

		checkMSISDN_Object.enterMSISDN(MSISDNinMocks);

		Thread.sleep(20000);

		System.out.print(driver.getTitle());
		// msisdnInfo.ValidateKiasPopUp_Exist();
		callyaCustomerInfo_Object.verfiy_KaisPopup();

	}

	// Nashwa 30-5-2019
	@Test(dependsOnMethods = { "VerifyKiasPopup" })

	public void GetMSISDN_Info() throws InterruptedException {

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);
		callyaCustomerInfo_Object.closeKiasPopup();
		callyaCustomerInfo_Object.checkMsisdn(MSISDNinMocks);
	}

//Nashwa 3-6-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void checkMSISDNTruncation() throws InterruptedException {

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);
		checkMSISDN_Object = new Check_MSISDN(driver);

		baseLayer.navigateToCheckMsisdnURL();

		checkMSISDN_Object.enterMSISDN(MSISDNwithSpace);

		Thread.sleep(20000);

		// msisdnInfo.closeKiasPopup();
		callyaCustomerInfo_Object.checkMsisdn(MSISDNinMocks);
	}

//Yousra 
	@Test(priority = 1, dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_GensisMSISDN() {
		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.genesis_MSISDN();

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);
		By Gensis_MSISDN = callyaCustomerInfo_Object.verify_GensisMSISDN();
		Verifications.verifyElementExists(driver, Gensis_MSISDN, true);

	}

//Nashwa 3-6-2019  ( to be updated )
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_MsisdnErnuetLaden_link() {
		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page
		baseLayer = new base_Layer(driver);
		baseLayer.navigateToURL(); // Navigate to Page UR
		callyaCustomerInfo_Object.click_MSISDNerneutLaden_link();

		callyaCustomerInfo_Object.assert_SessionCounterTime();

		callyaCustomerInfo_Object.checkMsisdn(MSISDNinMocks);

	}

//Nashwa 3-6-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_MSISDN_link() {
		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page
		baseLayer = new base_Layer(driver);
		baseLayer.navigateToURL(); // Navigate to Page UR
		callyaCustomerInfo_Object.click_MSISDN_link();
		callyaCustomerInfo_Object.verify_GensisMSISDN();
	}

//Yousra 9-6-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_MSISDNInfo_FieldsDisplayed() {
		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page
		baseLayer = new base_Layer(driver);
		baseLayer.navigateToURL(); // Navigate to Page UR
		String[] arr = callyaCustomerInfo_Object.check_MSISDNInfo_Fields();

		Assertions.assertEquals("MSISDN:", arr[0], 1, true);
		Assertions.assertEquals("Vorname:", arr[1], 1, true);
		Assertions.assertEquals("Kontostand:", arr[2], 1, true);
		Assertions.assertEquals("Bonus Guthaben:", arr[3], 1, true);
		Assertions.assertEquals("Tarifstatus:", arr[4], 1, true);
		Assertions.assertEquals("Nach 100% Datenverbrauch:", arr[5], 1, true);
		Assertions.assertEquals("Emergency Credit:", arr[6], 1, true);

		Assertions.assertEquals("Anrede:", arr[7], 1, true);
		Assertions.assertEquals("Nachname:", arr[8], 1, true);
		Assertions.assertEquals("Tarif:", arr[9], 1, true);
		Assertions.assertEquals("Markt:", arr[10], 1, true);
		Assertions.assertEquals("Teilnehmer-Status:", arr[11], 1, true);
	}

	// Yousra 9-6-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_MSISDNInfo_TabsDisplayed() {
		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page
		baseLayer = new base_Layer(driver);
		baseLayer.navigateToURL(); // Navigate to Page UR
		String[] arr = callyaCustomerInfo_Object.check_MSISDNInfo_tabs();
		Assertions.assertEquals("Special Instructions", arr[0], 1, true);
		Assertions.assertEquals("KIAS", arr[1], 1, true);
		Assertions.assertEquals("MSISDN erneut laden", arr[2], 1, true);
		Assertions.assertEquals("MSISDN", arr[3], 1, true);
	}

	// Yousra 13-6-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_EntrtMemoField() throws InterruptedException {

		checkMSISDN_Object = new Check_MSISDN(driver);
		baseLayer.navigateToCheckMsisdnURL();
		checkMSISDN_Object.enterMSISDN("491620491954");

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page

		By entryfield_memo = callyaCustomerInfo_Object.check_EntryTypeMemo();
		Assertions.assertElementExists(driver, entryfield_memo, true);
	}

	// Yousra 13-6-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_EntrtMemoField_notExits() throws InterruptedException {

		checkMSISDN_Object = new Check_MSISDN(driver);
		baseLayer.navigateToCheckMsisdnURL();
		checkMSISDN_Object.enterMSISDN("491620491954");

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page

		By entryfield_memo = callyaCustomerInfo_Object.check_EntryTypeMemo_notexists();

		Assertions.assertElementExists(driver, entryfield_memo, false);
	}

	// Yousra 13-6-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_neuSuche_KaisPopup() throws InterruptedException {

		checkMSISDN_Object = new Check_MSISDN(driver);
		baseLayer.navigateToCheckMsisdnURL();
		checkMSISDN_Object.enterMSISDN("491620491954");

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page

		callyaCustomerInfo_Object.check_EntryTypeMemo();

		callyaCustomerInfo_Object.click_NeueSuche_link();

		checkMSISDN_Object.enterMSISDN("491620491952");
		callyaCustomerInfo_Object.verfiy_KaisPopup_notDisplayed();

	}

	// Yousra 27-6-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_BalanceTransfers_Feilds() throws InterruptedException {

		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.enterMSISDN("491620491952");

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page
		Thread.sleep(7000);
		callyaCustomerInfo_Object.closeKiasPopup();
		Thread.sleep(8000);
		String[] arr = callyaCustomerInfo_Object.assert_BalanceTransfers_Fields();

		Assertions.assertEquals("Sperrsoc gesetzt", arr[0], 1, true);
		Assertions.assertEquals("Nutzungsvoraussetzungen nicht erfÃ¼llt", arr[1], 1, true);
		Assertions.assertEquals("maximaler Transferbetrag erreicht ( Geber)", arr[2], 1, true);
		Assertions.assertEquals("maximale tÃ¤gliche Transferanzahl erreicht", arr[3], 1, true);
		Assertions.assertEquals("maximale monatliche Transferanzahl erreicht", arr[4], 1, true);
		Assertions.assertEquals("keine Aufladung erfolgt", arr[5], 1, true);

		Assertions.assertEquals("Sperrsoc gesetzt", arr[6], 1, true);
		Assertions.assertEquals("Nutzungsvoraussetzungen nicht erfÃ¼llt", arr[7], 1, true);
		Assertions.assertEquals("maximaler Transferbetrag erreicht ( EmpfÃ¤nger )", arr[8], 1, true);
		Assertions.assertEquals("maximale tÃ¤gliche Transferanzahl erreicht", arr[9], 1, true);
		Assertions.assertEquals("maximale monatliche Transferanzahl erreicht", arr[10], 1, true);

		Assertions.assertEquals("Donor Subscriber", arr[11], 1, true);
		Assertions.assertEquals("Donor Market", arr[12], 1, true);
		Assertions.assertEquals("Recipient Subscriber", arr[13], 1, true);
		Assertions.assertEquals("Recipient Market", arr[14], 1, true);
		Assertions.assertEquals("Status", arr[15], 1, true);
		Assertions.assertEquals("Amount", arr[16], 1, true);
		Assertions.assertEquals("Initiating Date", arr[17], 1, true);
		Assertions.assertEquals("Executing Date", arr[18], 1, true);
		Assertions.assertEquals("Donor Charge", arr[19], 1, true);
		Assertions.assertEquals("Recipient Adjustment", arr[20], 1, true);

	}

	// Yousra 27-6-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_KunderFlags_Feilds() throws InterruptedException {

		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.enterMSISDN("491620491952");

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page
		Thread.sleep(7000);
		callyaCustomerInfo_Object.closeKiasPopup();
		Thread.sleep(8000);
		String[] arr = callyaCustomerInfo_Object.assert_KunderFlags_Fields();

		Assertions.assertEquals("Statement Erstellung:", arr[0], 1, true);
		Assertions.assertEquals("Telefonbucheintrag:", arr[1], 1, true);
		Assertions.assertEquals("DSL:", arr[2], 1, true);
		Assertions.assertEquals("Renunciation:", arr[3], 1, true);
		Assertions.assertEquals("Prepaid Replenish:", arr[4], 1, true);
		Assertions.assertEquals("Advertisement Indicator:", arr[5], 1, true);
		Assertions.assertEquals(
				"List of SOCs (FeatureSOC, FeatureSOCDescription) - (AgreementSOC, AgreementSOCDescription)", arr[6], 1,
				true);

	}

	// Yousra 01-07-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_Nachrichten_dropdownfields() throws InterruptedException {

		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.enterMSISDN("491620491952");

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page
		Thread.sleep(7000);
		callyaCustomerInfo_Object.closeKiasPopup();

		Thread.sleep(8000);
		String[] arr1 = callyaCustomerInfo_Object.assert_NachrichtenTab();

		Assertions.assertEquals("7", arr1[0], 1, true);
		Assertions.assertEquals("30", arr1[1], 1, true);
		Assertions.assertEquals("90", arr1[2], 1, true);
		Assertions.assertEquals("180", arr1[3], 1, true);
		Assertions.assertEquals("360", arr1[4], 1, true);

		Assertions.assertEquals("All Channel", arr1[5], 1, true);
		Assertions.assertEquals("SMS", arr1[6], 1, true);
		Assertions.assertEquals("Email", arr1[7], 1, true);
		Assertions.assertEquals("AppPush", arr1[8], 1, true);
		Assertions.assertEquals("Inbox", arr1[9], 1, true);

	}

	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void ChangeTarrif_checksuccesMessage() throws InterruptedException {

		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.enterMSISDN(MSISDNinMocks);
		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);
		Thread.sleep(20000);
		callyaCustomerInfo_Object.closeKiasPopup();

		String Success_message = callyaCustomerInfo_Object.Change_tariff();

		Assertions.assertEquals("Booking of offer '400 Min/SMS, 400 MB' was successful.", Success_message, 1, true);

	}

	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void Check_activeButton_Disabled() throws InterruptedException {

		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.enterMSISDN(MSISDNinMocks);
		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);
		Thread.sleep(20000);
		callyaCustomerInfo_Object.closeKiasPopup();

		By active_button_disabled = callyaCustomerInfo_Object.Check_Active_button();
		Assertions.assertElementExists(driver, active_button_disabled, true);

	}

	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void Check_Buchungsübersicht_tab() throws InterruptedException {

		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.enterMSISDN(MSISDNinMocks);
		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);
		Thread.sleep(20000);
		callyaCustomerInfo_Object.closeKiasPopup();

		String[] dropDownList_values = callyaCustomerInfo_Object.Check_Buchungsübersicht_tab();
		Assertions.assertEquals("Buchungen", dropDownList_values[0], 1, true);
		Assertions.assertEquals("Flex Einstellungen", dropDownList_values[1], 1, true);
		Assertions.assertEquals("Stornierungen", dropDownList_values[2], 1, true);
		Assertions.assertEquals("Tarifwechsel", dropDownList_values[3], 1, true);
		Assertions.assertEquals("Vermerke", dropDownList_values[4], 1, true);
		Assertions.assertEquals("Alle KanÃ¤le", dropDownList_values[5], 1, true);
		Assertions.assertEquals("App (1001)", dropDownList_values[6], 1, true);
		Assertions.assertEquals("CCC (14)", dropDownList_values[7], 1, true);
		Assertions.assertEquals("ECG (13)", dropDownList_values[8], 1, true);
		Assertions.assertEquals("Hotline Agent (1005)", dropDownList_values[9], 1, true);
		Assertions.assertEquals("Hotline Agent(Service Portal) (1012)", dropDownList_values[10], 1, true);
		Assertions.assertEquals("Hotline Agent Manager (1007)", dropDownList_values[11], 1, true);
		Assertions.assertEquals("IVR (1009)", dropDownList_values[12], 1, true);
		Assertions.assertEquals("KIAS UPS (11)", dropDownList_values[13], 1, true);
		Assertions.assertEquals("LIG (1008)", dropDownList_values[14], 1, true);
		Assertions.assertEquals("Mass Activity (1006)", dropDownList_values[15], 1, true);
		Assertions.assertEquals("SMS (1002)", dropDownList_values[16], 1, true);
		Assertions.assertEquals("Unknown (1000)", dropDownList_values[17], 1, true);
		Assertions.assertEquals("USSD (1004)", dropDownList_values[18], 1, true);
		Assertions.assertEquals("USSD (1011)", dropDownList_values[19], 1, true);
		Assertions.assertEquals("Webpage/Mobile Website (1010)", dropDownList_values[20], 1, true);
		Assertions.assertEquals("Gestern", dropDownList_values[21], 1, true);
		Assertions.assertEquals("Aktuelle Woche", dropDownList_values[22], 1, true);
		Assertions.assertEquals("Aktueller Monat", dropDownList_values[23], 1, true);
		Assertions.assertEquals("Aktuelles Quartal", dropDownList_values[24], 1, true);
		Assertions.assertEquals("Letzte Woche", dropDownList_values[25], 1, true);
		Assertions.assertEquals("Letzter Monat", dropDownList_values[26], 1, true);
		Assertions.assertEquals("Letztes Quartal", dropDownList_values[27], 1, true);
		Assertions.assertEquals("Aktuelles Jahr", dropDownList_values[28], 1, true);
		Assertions.assertEquals("Manuelle Auswahl", dropDownList_values[29], 1, true);

	}

}
