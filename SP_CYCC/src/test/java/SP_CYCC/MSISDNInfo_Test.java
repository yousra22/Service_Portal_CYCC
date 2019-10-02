package SP_CYCC;

//16-9
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shaft.validation.Assertions;

import base.layer.TestBase;
import serviceportal.cycc.pages.BalanceTransfers;
import serviceportal.cycc.pages.Buchungsubersicht;
import serviceportal.cycc.pages.CallnowCode;
import serviceportal.cycc.pages.CheckMSISDN;
import serviceportal.cycc.pages.KundenFlagsMarkerSocs;
import serviceportal.cycc.pages.Login;
import serviceportal.cycc.pages.MSISDNInfo;
import serviceportal.cycc.pages.NachrichtSenden;
import serviceportal.cycc.pages.Nutzungsubersicht;
import serviceportal.cycc.pages.Optionen;
import serviceportal.cycc.pages.PersonlicheDaten;
import serviceportal.cycc.pages.Tarifwechsel;
import serviceportal.cycc.pages.Wunschtarif;
import serviceportal.cycc.pages.Zahlungsübersicht;
import serviceportal.cycc.pages.nachrichten;

public class MSISDNInfo_Test extends TestBase {

	// TestData
	String MSISDN_PUA = "491620491952";
	String MSISDNwithout49 = "1620491952";
	String MSISDNwithSpace = "  491620491952  ";
	String MSISDN_MMO = "491620491954";// Bishoy 14-07-2019
	String MSISDNcancelled = "491620491946";

	// Page Objects :
	Login loginObj;
	CheckMSISDN checkmsisdnObj;
	MSISDNInfo callyaCustomerInfoObj;
	NachrichtSenden nachrichtSendenTabObj;
	PersonlicheDaten personlicheDatenTabObj;
	Wunschtarif wunschtarifTabObj;
	Buchungsubersicht buchungsubersichtTabObj;
	BalanceTransfers balanceTransfersTabObj;
	KundenFlagsMarkerSocs kundenFlagsMarkerSocsTabObj;
	nachrichten nachrichtenTabObj;
	Nutzungsubersicht nutzungsubersichtTabObj;
	Tarifwechsel tarifwechselTabObj;
	CallnowCode callnowCodeTabObj;
	Optionen optionenTabObj;
	Zahlungsübersicht zahlungsübersichtTabObj;

	// Locator - to be removed after refactor
	By SpecialInstruction = By.xpath("(//tr[@class='pull-right']/td)[3]/button");

	// prop
	String SpecialInstructions_Color = "rgba(255, 0, 0, 1)";// red // Test ID 25573 - Bishoy 08-08-2019
	String SpecialInstructionsPopup_Text = "Special Instruction Text";// Test ID 25573 - Bishoy 08-08-2019
	String SpecialInstructionsPopup_TextBkgrndColor = "rgba(255, 255, 255, 1)";// white // Test ID 25573 - Bishoy
																				// 08-08-2019
	String SpecialInstructionsPopup_TextColor = "rgba(85, 85, 85, 1)";// black // Test ID 25573 - Bishoy 08-08-2019
	String SpecialInstructionsPopup_TextFontWeight = "700";// bold // Test ID 25573 - Bishoy 08-08-2019

	// Bishoy 18-09-2019 - Test ID 25572 (for cancelled customer)
	String CancelledCustomer_SIMnumber = "SIM number:04508429434047";
	String CancelledCustomer_PUK = "PUK:20307631";
	String CancelledCustomer_SIMtype = "SIM type:42";

	@BeforeMethod
	public void beforeMethod() {

		OpenBrowser_navigateToURL();

		loginObj = new Login(driver); // initialize a new instance of the page
		loginObj.enterLoginInfo("admin", "admin"); // Enter username & Password then click OK
		loginObj.click_CallYaCustomerInfo();
		// Nashwa 29-5-2019
		loginObj.focus_On_CheckMsisdnPage(); // focus on Check Msisdn Tab
	}

	// Yousra
	@Test
	public void assert_SessionTimer_dispalyed() throws Exception {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDN_PUA);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		By sessionTimer = callyaCustomerInfoObj.assert_SessionCounterTime();
		Assertions.assertElementAttribute(driver, sessionTimer, "text", properties.getProperty("sessionTimer"), 1,
				true);

	}

	// Yousra
	@Test
	public void assert_NeueSuche_link_navigation() {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDN_PUA);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		boolean displayed = callyaCustomerInfoObj.verify_NeueSuche_link();

		boolean expected = true;
		assertEquals(displayed, expected);

		callyaCustomerInfoObj.click_NeueSuche_link();
		Assertions.assertBrowserAttribute(driver, "currenturl", properties.getProperty("neueSuche_URL"), 3, true);
	}

	// Yousra 9-6-2019
	@Test
	public void assert_MSISDNInfo_Tabs_Displayed() {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDN_PUA);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page baseLayer =
		String[] arr_actual = callyaCustomerInfoObj.check_MSISDNInfo_tabs();

		String msisdn_info_Tabs = properties.getProperty("MSISDNInfo_Tabs");

		String[] msisdn_TabsArr = new String[4];
		msisdn_TabsArr = msisdn_info_Tabs.split(",");

		for (int i = 0; i < msisdn_TabsArr.length; i++) {

			Assertions.assertEquals(msisdn_TabsArr[i], arr_actual[i], 1, true);
		}
	}

	// Yousra 9-6-2019
	@Test
	public void assert_MSISDNInfo_Fields_Displayed() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDN_PUA);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		Thread.sleep(8000);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		String[] arr_actual = callyaCustomerInfoObj.check_MSISDNInfo_Fields();

		String msisdn_infoFields = properties.getProperty("MSISDNInfo_fields");

		String[] msisdn_fieldsArr = new String[15];
		msisdn_fieldsArr = msisdn_infoFields.split(",");

		for (int i = 0; i < msisdn_fieldsArr.length; i++) {

			Assertions.assertEquals(msisdn_fieldsArr[i], arr_actual[i], 1, true);
		}

	}

	// Nashwa 30-5-2019
	@Test
	public void assert_KiasPopup_displayed() throws InterruptedException {
		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDN_PUA);

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		callyaCustomerInfoObj.assert_KaisPopup_displayed();

	}

	// Hadeer 18-7-2019
	@Test
	public void assert_Prepaid_ident_Info_empty() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver); // initialize a new instance of the page
		checkmsisdnObj.enterMSISDN("491620491946");

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		Thread.sleep(8000);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		callyaCustomerInfoObj.check_Prepaid_ident_Info_field();
	}

	// Nashwa 30-5-2019
	@Test(dependsOnMethods = { "assert_KiasPopup_displayed" })
	public void assert_MSISDNnumber_Displayed() throws InterruptedException {

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		callyaCustomerInfoObj.assert_MsisdnNumber_Displayed(MSISDN_PUA);
	}

	// Nashwa 30-5-2019
	@Test
	public void assert_MSISDN_without49() throws InterruptedException {

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		checkmsisdnObj = new CheckMSISDN(driver);

		checkmsisdnObj.enterMSISDN(MSISDNwithout49);

		Thread.sleep(20000);

		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		callyaCustomerInfoObj.assert_MsisdnNumber_Displayed(MSISDN_PUA);

	}

	// Nashwa 3-6-2019
	@Test
	public void assert_MSISDN_Truncation() throws InterruptedException {

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		checkmsisdnObj = new CheckMSISDN(driver);

		checkmsisdnObj.enterMSISDN(MSISDNwithSpace);

		Thread.sleep(20000);

		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		callyaCustomerInfoObj.assert_MsisdnNumber_Displayed(MSISDN_PUA);
	}

	// Nashwa 3-6-2019 //25569
	@Test
	public void assert_GensisMSISDN_link() {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDN_PUA);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page

		callyaCustomerInfoObj.click_MSISDN_link();
		callyaCustomerInfoObj.assert_GensisMSISDN_displayed();
	}

	// Yousra 31-7.2019 //25568
	@Test
	public void assert_MSISDN_link() {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.click_MSISDNAbrufen();

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page

		boolean displayed = callyaCustomerInfoObj.check_msisdn_link_Displayed();
		Assertions.assertEquals(true, displayed, 1, true);

		callyaCustomerInfoObj.click_MSISDN_link();
		callyaCustomerInfoObj.assert_GensisMSISDN_displayed();

		callyaCustomerInfoObj.click_MSISDNerneutLaden_link();
		callyaCustomerInfoObj.assert_GensisMSISDN_displayed();
	}

	// By Esraa //25574
	@Test
	public void assert_SpecialInstruction_ButtonNotClickable() throws Exception {
		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDN_PUA);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();

		Thread.sleep(1000);

		boolean DisableExists = callyaCustomerInfoObj.AssertButtonIsNotClickable();
		Assert.assertTrue(DisableExists);

	}

	// Nashwa 3-6-2019
	@Test
	public void assert_MsisdnErnuetLaden_link() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDN_PUA);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		callyaCustomerInfoObj.click_MSISDNerneutLaden_link();
		callyaCustomerInfoObj.assert_SessionCounterTime();

	}

	// by Esraa //25557
	@Test
	public void AssertAllTabsAreAuthoirzedforRead() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDN_PUA);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();

		Thread.sleep(8000);

		personlicheDatenTabObj = new PersonlicheDaten(driver);
		wunschtarifTabObj = new Wunschtarif(driver);
		tarifwechselTabObj = new Tarifwechsel(driver);
		callnowCodeTabObj = new CallnowCode(driver);
		nutzungsubersichtTabObj = new Nutzungsubersicht(driver);
		optionenTabObj = new Optionen(driver);
		buchungsubersichtTabObj = new Buchungsubersicht(driver);
		zahlungsübersichtTabObj = new Zahlungsübersicht(driver);
		nachrichtSendenTabObj = new NachrichtSenden(driver);
		nachrichtenTabObj = new nachrichten(driver);
		balanceTransfersTabObj = new BalanceTransfers(driver);
		kundenFlagsMarkerSocsTabObj = new KundenFlagsMarkerSocs(driver);

		try {
			boolean Ispersonliche_Daten_displayed = personlicheDatenTabObj.personliche_Daten_AssertElementISDisplayed();
			Assert.assertTrue(Ispersonliche_Daten_displayed);

			boolean Is_wunschtarifElementdisplayed = wunschtarifTabObj.wunschtarif_AssertElementISDisplayed();
			Assert.assertTrue(Is_wunschtarifElementdisplayed);

			boolean Is_TarifwechselElementdisplayed = tarifwechselTabObj.Tarifwechsel_AssertElementISDisplayed();
			Assert.assertTrue(Is_TarifwechselElementdisplayed);

			boolean Is_CallNowElementdisplayed = callnowCodeTabObj.CallNowCode_AssertElementISDisplayed();
			Assert.assertTrue(Is_CallNowElementdisplayed);

			boolean Is_nutzungsubersichtElementdisplayed = nutzungsubersichtTabObj
					.nutzungsubersicht_AssertElementISDisplayed();
			Assert.assertTrue(Is_nutzungsubersichtElementdisplayed);

			boolean Is_OptionenElementdisplayed = optionenTabObj.Optionen_AssertElementISDisplayed();
			Assert.assertTrue(Is_OptionenElementdisplayed);

			boolean Is_buchungsubersichtElementdisplayed = buchungsubersichtTabObj
					.buchungsubersicht_AssertElementISDisplayed();
			Assert.assertTrue(Is_buchungsubersichtElementdisplayed);

			boolean Is_ZahlungsübersichtElementdisplayed = zahlungsübersichtTabObj
					.Zahlungsübersicht_AssertElementISDisplayed();
			Assert.assertTrue(Is_ZahlungsübersichtElementdisplayed);

			boolean Is_nachricht_sendenElementdisplayed = nachrichtSendenTabObj
					.nachricht_senden_AssertElementISDisplayed();
			Assert.assertTrue(Is_nachricht_sendenElementdisplayed);

			boolean Is_nachrichtenElementdisplayed = nachrichtenTabObj.nachrichten_AssertElementISDisplayed();
			Assert.assertTrue(Is_nachrichtenElementdisplayed);

			boolean Is_balance_TransfersElementdisplayed = balanceTransfersTabObj
					.balance_Transfers_AssertElementISDisplayed();
			Assert.assertTrue(Is_balance_TransfersElementdisplayed);

			boolean Is_kundenFlags_MarkerSocsElementdisplayed = kundenFlagsMarkerSocsTabObj
					.assertStatementErsteullengIsDisplayed();
			Assert.assertTrue(Is_kundenFlags_MarkerSocsElementdisplayed);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Test ID 25573 - Bishoy 08-08-2019
	@Test
	public void assert_SpecialInstructions_Popup_Displayed() throws InterruptedException {
		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDN_MMO);
		callyaCustomerInfoObj = new MSISDNInfo(driver);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		Thread.sleep(15000);

		Assertions.assertEquals(SpecialInstructions_Color, callyaCustomerInfoObj.getSpecialInstructionsColor(), 1,
				true);
		callyaCustomerInfoObj.clickSpecialInstructionsButton();
		// specialInstructionsPopup_Object = new specialInstructionsPopup(driver);
		callyaCustomerInfoObj.checkPopupExists();
		Assertions.assertEquals(SpecialInstructionsPopup_Text, callyaCustomerInfoObj.getText(), 1, true);
		Assertions.assertEquals(SpecialInstructionsPopup_TextBkgrndColor,
				callyaCustomerInfoObj.getTextBackgroundColor(), 1, true);
		Assertions.assertEquals(SpecialInstructionsPopup_TextColor, callyaCustomerInfoObj.getTextColor(), 1, true);
		Assertions.assertEquals(SpecialInstructionsPopup_TextFontWeight, callyaCustomerInfoObj.getTextFontWeight(), 1,
				true);
	}

	// Test ID 25572 - Bishoy 18-09-2019
	@Test
	public void assert_CancelledCustomer_SIMinfo_Displayed() throws InterruptedException {
		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNcancelled);
		callyaCustomerInfoObj = new MSISDNInfo(driver);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		Thread.sleep(1000);
		Assertions.assertEquals(CancelledCustomer_SIMnumber, callyaCustomerInfoObj.getCancelledCustomer_SIMnumber(), 1,
				true);
		Assertions.assertEquals(CancelledCustomer_PUK, callyaCustomerInfoObj.getCancelledCustomer_PUK(), 1, true);
		Assertions.assertEquals(CancelledCustomer_SIMtype, callyaCustomerInfoObj.getCancelledCustomer_SIMtype(), 1,
				true);
		
		
	}

	// esraa 14-9-2019 //25570
	@Test
	public void AssertStatusforValenciaCustomerIsCancelled() throws Exception {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNcancelled);

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		Thread.sleep(9000);
		callyaCustomerInfoObj.AssertStatusForValenciaCustomerIsCancelled();

	}

	// hadeer 10 -9 -2019 TC 25579
	@Test
	public void assert_kias_errormsg() throws InterruptedException {
		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDN_PUA);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		Thread.sleep(8000);
		callyaCustomerInfoObj.enter_wrongKaisCredintails3times("test", "test");

		// ading Esraa code TC 25580
		buchungsubersichtTabObj = new Buchungsubersicht(driver);
		Thread.sleep(9000);
		boolean entryfield_memo = buchungsubersichtTabObj.check_EntryTypeMemo();
		Assert.assertEquals(entryfield_memo, true);

	}

	// Yousra 22-9-2019
	@Test
	public void assert_KunderFlags_Fields_PUA() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDN_PUA);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		Thread.sleep(7000);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		Thread.sleep(8000);

		kundenFlagsMarkerSocsTabObj = new KundenFlagsMarkerSocs(driver);

		boolean[] arr_actual = kundenFlagsMarkerSocsTabObj.assert_KunderFlags_Fields_PUA();
		for (int i = 0; i < arr_actual.length; i++) {

			Assertions.assertEquals(true, arr_actual[i], 1, true);
		}

	}

	// Yousra 27-6-2019
	@Test
	public void assert_KunderFlags_Fields_MMO() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDN_MMO);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		Thread.sleep(7000);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		Thread.sleep(8000);

		kundenFlagsMarkerSocsTabObj = new KundenFlagsMarkerSocs(driver);

		boolean[] arr_actual = kundenFlagsMarkerSocsTabObj.assert_KunderFlags_Fields_MMO();
		for (int i = 0; i < arr_actual.length; i++) {

			Assertions.assertEquals(true, arr_actual[i], 1, true);
		}

	}
	
	//Yousra 23-9-2019
	@Test
	public void assertAllSocsAreDisplayed() throws Exception
	{
		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDN_PUA);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		Thread.sleep(7000);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		Thread.sleep(8000);

		kundenFlagsMarkerSocsTabObj = new KundenFlagsMarkerSocs(driver);
		
		boolean isPresent = kundenFlagsMarkerSocsTabObj.assertListOfSocsAreDisplayed();

		Assertions.assertEquals(true, isPresent, 1, true);
	}
}
