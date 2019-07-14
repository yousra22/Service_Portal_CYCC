//date 14.07.2019 marwaa
package SP_CYCC;

import static org.testng.Assert.assertEquals;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shaft.browser.BrowserFactory;
import com.shaft.io.ExcelFileManager;
import com.shaft.validation.Assertions;

import Base_Package.base_Layer;
import Base_Package.propertiesFile_CYCC;

public class MSISDN_Info_Test {

	WebDriver driver;
	ExcelFileManager testDataReader;

	String MSISDNinMocks = "491620491952";
	String MSISDNwithout49 = "1620491952";
	String MSISDNwithSpace = "  491620491952  ";

	base_Layer baseLayer;
	Login loginObject;
	Check_MSISDN checkMSISDN_Object;
	MSISDN_InfoPage callyaCustomerInfo_Object;
	wunschtarif wunschtarif_TabObj;
	buchungsubersicht buchungsubersicht_TabObj;
	balance_Transfers balance_Transfers_TabObj;
	kundenFlags_MarkerSocs kundenFlags_MarkerSocs_TabObj;
	nachrichten nachrichten_TabObj;
	
	propertiesFile_CYCC prop_file;
	Properties prop;

	@BeforeClass // Set-up method, to be run once before the first test
	public void beforeClass() {
		// System.setProperty("testDataFilePath","src/test/resources/TestDataFiles/testSuite01/TestData.xlsx");
		// testDataReader = new ExcelFileManager(System.getProperty("testDataFilePath"));
		driver = BrowserFactory.getBrowser();
		prop_file = new propertiesFile_CYCC();		
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
	public void assert_SessionTimer_dispalyed() throws Exception {

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page
		baseLayer = new base_Layer(driver);
		baseLayer.navigateToURL(); // Navigate to Page UR
		By sessionTimer = callyaCustomerInfo_Object.assert_SessionCounterTime();
		Assertions.assertElementAttribute(driver, sessionTimer, "text", prop_file.get_SessionTimer("sessionTimer"), 1, true);

	}

	// Yousra
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_NeueSuche_link_navigation() {
		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page
		baseLayer = new base_Layer(driver);
		baseLayer.navigateToURL(); // Navigate to Page URL

		boolean displayed = callyaCustomerInfo_Object.verify_NeueSuche_link();

		boolean expected = true;
		assertEquals(displayed, expected);

		callyaCustomerInfo_Object.click_NeueSuche_link();
		Assertions.assertBrowserAttribute(driver, "currenturl", prop_file.get_neuSuche_URL("neueSuche_URL"), 3, true);
	}

	// Nashwa 30-5-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_KiasPopup_displayed() throws InterruptedException {
		checkMSISDN_Object = new Check_MSISDN(driver);
		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);

		checkMSISDN_Object.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfo_Object.Assert_KaisPopup();

	}

	// Nashwa 30-5-2019
	@Test(dependsOnMethods = { "assert_KiasPopup_displayed" })
	public void assert_MSISDNnumber_Displayed() throws InterruptedException {

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);
		callyaCustomerInfo_Object.close_KaisPop_WithNoCred();
		callyaCustomerInfo_Object.assert_MsisdnNumber_Displayed(MSISDNinMocks);
	}

	// Nashwa 30-5-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_MSISDN_without49() throws InterruptedException {

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);
		checkMSISDN_Object = new Check_MSISDN(driver);

		checkMSISDN_Object.enterMSISDN(MSISDNwithout49);

		Thread.sleep(20000);

		callyaCustomerInfo_Object.close_KaisPop_WithNoCred();
		callyaCustomerInfo_Object.assert_MsisdnNumber_Displayed(MSISDNinMocks);

	}

	// Nashwa 3-6-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_MSISDN_Truncation() throws InterruptedException {

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);
		checkMSISDN_Object = new Check_MSISDN(driver);

		checkMSISDN_Object.enterMSISDN(MSISDNwithSpace);

		Thread.sleep(20000);

		callyaCustomerInfo_Object.close_KaisPop_WithNoCred();
		callyaCustomerInfo_Object.assert_MsisdnNumber_Displayed(MSISDNinMocks);
	}

	// Yousra
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_GensisMSISDN() {
		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.genesis_MSISDN();

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);
		callyaCustomerInfo_Object.assert_GensisMSISDN();
	}

	// Nashwa 3-6-2019 ( to be updated )
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_MsisdnErnuetLaden_link() throws InterruptedException {

		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page
		callyaCustomerInfo_Object.close_KaisPop_WithNoCred();
		callyaCustomerInfo_Object.click_MSISDNerneutLaden_link();
		callyaCustomerInfo_Object.assert_SessionCounterTime();

	}

	// Nashwa 3-6-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_GensisMSISDN_link() {

		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page

		callyaCustomerInfo_Object.click_MSISDN_link();
		callyaCustomerInfo_Object.assert_GensisMSISDN();
	}

	// Nashwa
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_wunschtarif_ChangeTariff_checksuccesMessage() throws InterruptedException {

		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);
		Thread.sleep(20000);
		callyaCustomerInfo_Object.close_KaisPop_WithNoCred();

		wunschtarif_TabObj = new wunschtarif(driver);
		String Success_message = wunschtarif_TabObj.Change_tariff();

		// Assertions.assertEquals("Booking of offer '400 Min/SMS, 400 MB' was
		// successful.", Success_message, 1, true);
		assertEquals(Success_message, prop_file.get_SuccessMsg_wunschtarif("SuccessMSG_wunschtarif_tab"));
	}

	// Nashwa
	@Test(dependsOnMethods = { "navigateToURLandLogin" }) // --> To be checked
	public void assert_wunschtarif_activeButton_Disabled() throws InterruptedException {

		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.enterMSISDN(MSISDNinMocks);
		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);
		Thread.sleep(20000);
		callyaCustomerInfo_Object.close_KaisPop_WithNoCred();

		wunschtarif_TabObj = new wunschtarif(driver);
		By active_button_disabled = wunschtarif_TabObj.Check_Active_button();
		Assertions.assertElementExists(driver, active_button_disabled, true);

	}

	// Nashwa
	@Test(dependsOnMethods = { "navigateToURLandLogin" }) // to be checked
	public void assert_Buchungsübersicht_tab() throws InterruptedException {

		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver);
		Thread.sleep(20000);
		callyaCustomerInfo_Object.close_KaisPop_WithNoCred();

		buchungsubersicht_TabObj = new buchungsubersicht(driver);
		String[] dropDownList_values = buchungsubersicht_TabObj.Check_Buchungsubersicht_tab();

		Assertions.assertEquals(prop_file.get_Buchungsübersicht_tab_dropdown_fields("Buchungsübersicht_tab_Dropdownfields_names"), dropDownList_values, 1, true);
		
		Assertions.assertEquals("Buchungen", dropDownList_values[0], 1, true);
		Assertions.assertEquals("Flex Einstellungen", dropDownList_values[1], 1, true);
		/*Assertions.assertEquals("Stornierungen", dropDownList_values[2], 1, true);
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
		Assertions.assertEquals("Manuelle Auswahl", dropDownList_values[29], 1, true); */
	}

	// Yousra 9-6-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_MSISDNInfo_Fields_Displayed() {

		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page
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
	public void assert_MSISDNInfo_Tabs_Displayed() {

		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page baseLayer =
		String[] arr = callyaCustomerInfo_Object.check_MSISDNInfo_tabs();

		Assertions.assertEquals("Special Instructions", arr[0], 1, true);
		Assertions.assertEquals("KIAS", arr[1], 1, true);
		Assertions.assertEquals("MSISDN erneut laden", arr[2], 1, true);
		Assertions.assertEquals("MSISDN", arr[3], 1, true);
	}

	// Yousra 27-6-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_BalanceTransfers_Feilds() throws InterruptedException {

		checkMSISDN_Object = new Check_MSISDN(driver);
		checkMSISDN_Object.enterMSISDN("491620491952");

		callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new instance of the page
		Thread.sleep(7000);

		callyaCustomerInfo_Object.close_KaisPop_WithNoCred();
		Thread.sleep(8000);

		balance_Transfers_TabObj = new balance_Transfers(driver);
		String[] arr = balance_Transfers_TabObj.assert_BalanceTransfers_Fields();

		Assertions.assertEquals("Sperrsoc gesetzt", arr[0], 1, true);
		Assertions.assertEquals("Nutzungsvoraussetzungen nicht erfüllt", arr[1], 1, true);
		Assertions.assertEquals("maximaler Transferbetrag erreicht ( Geber)", arr[2], 1, true);
		Assertions.assertEquals("maximale tägliche Transferanzahl erreicht", arr[3], 1, true);
		Assertions.assertEquals("maximale monatliche Transferanzahl erreicht", arr[4], 1, true);
		Assertions.assertEquals("keine Aufladung erfolgt", arr[5], 1, true);

		Assertions.assertEquals("Sperrsoc gesetzt", arr[6], 1, true);
		Assertions.assertEquals("Nutzungsvoraussetzungen nicht erfüllt", arr[7], 1, true);
		Assertions.assertEquals("maximaler Transferbetrag erreicht ( Empfänger )", arr[8], 1, true);
		Assertions.assertEquals("maximale tägliche Transferanzahl erreicht", arr[9], 1, true);
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
		callyaCustomerInfo_Object.close_KaisPop_WithNoCred();
		Thread.sleep(8000);

		kundenFlags_MarkerSocs_TabObj = new kundenFlags_MarkerSocs(driver);

		String[] arr = kundenFlags_MarkerSocs_TabObj.assert_KunderFlags_Fields();

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
		callyaCustomerInfo_Object.close_KaisPop_WithNoCred();

		Thread.sleep(8000);

		nachrichten_TabObj = new nachrichten(driver);
		String[] arr1 = nachrichten_TabObj.assert_NachrichtenTab_dropdownFields();

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

}

/*
 * 
 *
 * // Yousra 13-6-2019
 * 
 * @Test(dependsOnMethods = { "navigateToURLandLogin" }) public void
 * assert_neuSuche_KaisPopup() throws InterruptedException {
 * 
 * checkMSISDN_Object = new Check_MSISDN(driver);
 * checkMSISDN_Object.enterMSISDN("491620491952");
 * 
 * callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new
 * instance of the page
 * 
 * buchungsubersicht_TabObj = new buchungsubersicht(driver);
 * buchungsubersicht_TabObj.check_EntryTypeMemo();
 * 
 * callyaCustomerInfo_Object.click_NeueSuche_link();
 * 
 * checkMSISDN_Object.enterMSISDN("491620491952");
 * callyaCustomerInfo_Object.verfiy_KaisPopup_notDisplayed();
 * 
 * }
 * 
 * 
 * @Test(dependsOnMethods = { "navigateToURLandLogin" }) public void
 * assert_EntrtMemoField() throws InterruptedException {
 * 
 * checkMSISDN_Object = new Check_MSISDN(driver);
 * baseLayer.navigateToCheckMsisdnURL();
 * checkMSISDN_Object.enterMSISDN("491620491954");
 * 
 * callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new
 * instance of the page
 * 
 * By entryfield_memo = callyaCustomerInfo_Object.check_EntryTypeMemo();
 * Assertions.assertElementExists(driver, entryfield_memo, true); }
 * 
 * // Yousra 13-6-2019
 * 
 * @Test(dependsOnMethods = { "navigateToURLandLogin" }) public void
 * assert_EntrtMemoField_notExits() throws InterruptedException {
 * 
 * checkMSISDN_Object = new Check_MSISDN(driver);
 * baseLayer.navigateToCheckMsisdnURL();
 * checkMSISDN_Object.enterMSISDN("491620491954");
 * 
 * callyaCustomerInfo_Object = new MSISDN_InfoPage(driver); // initialize a new
 * instance of the page
 * 
 * By entryfield_memo =
 * callyaCustomerInfo_Object.check_EntryTypeMemo_notexists();
 * 
 * Assertions.assertElementExists(driver, entryfield_memo, false); }
 * 
 * 
 * 
 * 
 * 
 * }
 */