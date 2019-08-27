//date 31.07.2019

//added 
package SP_CYCC;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shaft.io.ExcelFileManager;
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
import serviceportal.cycc.pages.nachrichten;
import serviceportal.cycc.pages.Nutzungsubersicht;
import serviceportal.cycc.pages.Optionen;
import serviceportal.cycc.pages.PersonlicheDaten;
import serviceportal.cycc.pages.Tarifwechsel;
import serviceportal.cycc.pages.Wunschtarif;
import serviceportal.cycc.pages.Zahlungsübersicht;

public class MSISDN_Info_Test extends TestBase {

	ExcelFileManager testDataReader;

	String MSISDNinMocks = "491620491952";
	String MSISDNwithout49 = "1620491952";
	String MSISDNwithSpace = "  491620491952  ";
	String MSISDNnormal = "491620491954";// Bishoy 14-07-2019

	// Bishoy
	String ZahlungsubersichtTab_ExpectedText = "Zahlungsübersicht";// Test ID 25560 - Bishoy 14-07-2019
	String Kundenkennwort_ExpectedText = "Kundenkennwort:";// Test ID 25560 - Bishoy 14-07-2019
	String FirstRadioButton_ExpectedText = "Example Message Text";// Test ID 25562 - Bishoy 14-07-2019
	String SecondRadioButton_ExpectedText = "Example Message with multiple channels";// Test ID 25562 - Bishoy
																						// 14-07-2019

	TestBase baseLayer;
	Login loginObj;
	NachrichtSenden nachrichtSendenTabObj;// Bishoy 14-07-2019
	PersonlicheDaten personlicheDatenTabObj;// Bishoy 21-07-2019
	CheckMSISDN checkmsisdnObj;
	MSISDNInfo callyaCustomerInfoObj;
	Wunschtarif wunschtarifTabObj;
	Buchungsubersicht buchungsubersichtTabObj;
	BalanceTransfers balanceTransfersTabObj;
	KundenFlagsMarkerSocs kundenFlagsMarkerSocsTabObj;
	nachrichten nachrichtenTabObj;
	Nutzungsubersicht nutzungsubersichtTabObj;
	// Esraa
	Tarifwechsel tarifwechselTabObj;
	CallnowCode callnowCodeTabObj;
	Optionen optionenTabObj;
	Zahlungsübersicht zahlungsübersichtTabObj;
	
	By SpecialInstruction = By.xpath("(//tr[@class='pull-right']/td)[3]/button");

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
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		By sessionTimer = callyaCustomerInfoObj.assert_SessionCounterTime();
		Assertions.assertElementAttribute(driver, sessionTimer, "text", properties.getProperty("sessionTimer"), 1,
				true);

	}

	// Yousra
	@Test
	public void assert_NeueSuche_link_navigation() {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		boolean displayed = callyaCustomerInfoObj.verify_NeueSuche_link();

		boolean expected = true;
		assertEquals(displayed, expected);

		callyaCustomerInfoObj.click_NeueSuche_link();
		Assertions.assertBrowserAttribute(driver, "currenturl", properties.getProperty("neueSuche_URL"), 3, true);
	}

	// Nashwa 30-5-2019
	@Test
	public void assert_KiasPopup_displayed() throws InterruptedException {
		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		callyaCustomerInfoObj.assert_KaisPopup_displayed();

	}

	// Nashwa 30-5-2019
	@Test(dependsOnMethods = { "assert_KiasPopup_displayed" })
	public void assert_MSISDNnumber_Displayed() throws InterruptedException {

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		callyaCustomerInfoObj.assert_MsisdnNumber_Displayed(MSISDNinMocks);
	}

	// Nashwa 30-5-2019
	@Test
	public void assert_MSISDN_without49() throws InterruptedException {

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		checkmsisdnObj = new CheckMSISDN(driver);

		checkmsisdnObj.enterMSISDN(MSISDNwithout49);

		Thread.sleep(20000);

		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		callyaCustomerInfoObj.assert_MsisdnNumber_Displayed(MSISDNinMocks);

	}

	// Nashwa 3-6-2019
	@Test
	public void assert_MSISDN_Truncation() throws InterruptedException {

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		checkmsisdnObj = new CheckMSISDN(driver);

		checkmsisdnObj.enterMSISDN(MSISDNwithSpace);

		Thread.sleep(20000);

		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		callyaCustomerInfoObj.assert_MsisdnNumber_Displayed(MSISDNinMocks);
	}

	// Nashwa 3-6-2019 //25569
	@Test
	public void assert_GensisMSISDN_link() {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

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

	// Yousra 31-7.2019 //25567
	@Test
	public void assert_MSISDNAbrufen_button_HintText() {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.click_MSISDNAbrufen();

		String actual_hintText = checkmsisdnObj.get_hintText_MSISDN_Abrufen();
		Assertions.assertEquals(properties.getProperty("hintTest_MSISDN_Abrufen"), actual_hintText, 1, true);
	}

	// Nashwa
	@Test
	public void assert_wunschtarif_ChangeTariff_checksuccesMessage() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		Thread.sleep(20000);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();

		wunschtarifTabObj = new Wunschtarif(driver);
		String Success_message = wunschtarifTabObj.Change_tariff();

		// Assertions.assertEquals("Booking of offer '400 Min/SMS, 400 MB' was
		// successful.", Success_message, 1, true);
		assertEquals(Success_message, properties.getProperty("SuccessMSG_wunschtarif_tab"));
	}

	// Nashwa
	@Test
	public void assert_wunschtarif_activeButton_Disabled() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);
		callyaCustomerInfoObj = new MSISDNInfo(driver);
		Thread.sleep(20000);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();

		wunschtarifTabObj = new Wunschtarif(driver);
		By active_button_disabled = wunschtarifTabObj.Check_Active_button();
		Assertions.assertElementExists(driver, active_button_disabled, true);

	}

	// Yousra 9-6-2019
	@Test
	public void assert_MSISDNInfo_Tabs_Displayed() {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page baseLayer =
		String[] arr_actual = callyaCustomerInfoObj.check_MSISDNInfo_tabs();

		String msisdn_info_Tabs = properties.getProperty("MSISDNInfo_Tabs");

		String[] msisdn_TabsArr = new String[4];
		msisdn_TabsArr = msisdn_info_Tabs.split(",");

		for (int i = 0; i < msisdn_TabsArr.length; i++) {

			Assertions.assertEquals(msisdn_TabsArr[i], arr_actual[i], 1, true);
		}
	}

	// Yousra 01-07-2019
	@Test
	public void assert_Nachrichten_dropdownfields() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN("491620491952");

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();

		Thread.sleep(8000);

		nachrichtenTabObj = new nachrichten(driver);
		String[] arr_actual = nachrichtenTabObj.assert_NachrichtenTab_dropdownFields();

		String Nachrichten_dropdownfields = properties.getProperty("NachrichtenTab_dropdownFields");

		String[] Nachrichten_dropdownfieldsArr = new String[10];
		Nachrichten_dropdownfieldsArr = Nachrichten_dropdownfields.split(",");

		for (int i = 0; i < Nachrichten_dropdownfieldsArr.length; i++) {

			Assertions.assertEquals(Nachrichten_dropdownfieldsArr[i], arr_actual[i], 1, true);
		}

	}

	// Nashwa
	@Test
	public void assert_Buchungsubersicht_tab() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		Thread.sleep(20000);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();

		buchungsubersichtTabObj = new Buchungsubersicht(driver);
		String[] dropDownList_values = buchungsubersichtTabObj.Check_Buchungsubersicht_tab();

		String dropdownfields = properties.getProperty("Buchungsubersicht_tab_Dropdownfields_names");
		System.out.print("fields = " + dropdownfields);

		String[] dropdownFields_value = new String[30];
		dropdownFields_value = dropdownfields.split(",");

		for (int i = 0; i < dropDownList_values.length; i++) {
			System.out.print("i = " + i);
			Assertions.assertEquals(dropdownFields_value[i], dropDownList_values[i], 1, true);
		}
	}

	
	// Yousra 9-6-2019
	@Test
	public void assert_MSISDNInfo_Fields_Displayed() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

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

	// Yousra 27-6-2019
	@Test
	public void assert_BalanceTransfers_Fields() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		Thread.sleep(7000);

		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		Thread.sleep(8000);

		balanceTransfersTabObj = new BalanceTransfers(driver);
		String[] arr_actual = balanceTransfersTabObj.assert_BalanceTransfers_Fields();

		String BalanceTransfer_Donor_checkbox_values = properties.getProperty("BalanceTransfer_Donor_checkbox_values");
		String BalanceTransfer_Recipient_checkbox_values = properties
				.getProperty("BalanceTransfer_Recipient_checkbox_values");
		String BalanceTransfer_table_fields = properties.getProperty("BalanceTransfer_table_fields");

		String[] Donor_checkbox_values_Arr = new String[6];
		Donor_checkbox_values_Arr = BalanceTransfer_Donor_checkbox_values.split(",");
		for (int i = 0; i < Donor_checkbox_values_Arr.length; i++) {

			Assertions.assertEquals(Donor_checkbox_values_Arr[i], arr_actual[i], 1, true);
		}

		int j = 6;
		String[] Recipient_checkbox_values_Arr = new String[5];
		Recipient_checkbox_values_Arr = BalanceTransfer_Recipient_checkbox_values.split(",");
		for (int i = 0; i < Recipient_checkbox_values_Arr.length; i++) {

			Assertions.assertEquals(Recipient_checkbox_values_Arr[i], arr_actual[j], 1, true);
			j = j + 1;
		}

		String[] BalanceTransfer_table_Arr = new String[5];
		BalanceTransfer_table_Arr = BalanceTransfer_table_fields.split(",");
		for (int i = 0; i < BalanceTransfer_table_Arr.length; i++) {

			Assertions.assertEquals(BalanceTransfer_table_Arr[i], arr_actual[j], 1, true);
			j = j + 1;
		}

	}

	// Hadeer 18-7-2019
	@Test
	public void assert_UsageHistory_DefaultTab() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver); // initialize a new instance of the page
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		Thread.sleep(8000);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();

		nutzungsubersichtTabObj = new Nutzungsubersicht(driver); // initialize a new instance of the page
		nutzungsubersichtTabObj.Assert_Usage_history();
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

	// Test ID 25560 - Bishoy 14-07-2019
	@Test
	public void assert_PersonlicheDaten_Zahlungsubersicht_Tabs_Displayed() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNnormal);

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();

		Thread.sleep(30000);

		Assertions.assertEquals(ZahlungsubersichtTab_ExpectedText,
				callyaCustomerInfoObj.getZahlungsubersichtTabText(), 1, true);

		callyaCustomerInfoObj.clickPersonlicheDatenTab();
		Thread.sleep(30000);

		personlicheDatenTabObj = new PersonlicheDaten(driver);
		Assertions.assertEquals(Kundenkennwort_ExpectedText, personlicheDatenTabObj.getKundenkennwortText(), 1, true);
		Assertions.assertEquals(Kundenkennwort_ExpectedText, personlicheDatenTabObj.getLabelText(6), 1, true);
	}

	// Test ID 25562 - Bishoy 14-07-2019
	@Test
	public void assert_NachrichtSenden_ConfiguredMessage_Displayed() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNnormal);

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		Thread.sleep(10000);
		callyaCustomerInfoObj.clickNachrichtSendenTab();

		nachrichtSendenTabObj = new NachrichtSenden(driver);
		Thread.sleep(10000);
		Assertions.assertEquals(FirstRadioButton_ExpectedText, nachrichtSendenTabObj.getFirstRadioButtonText(), 3,
				true);
		Assertions.assertEquals(SecondRadioButton_ExpectedText, nachrichtSendenTabObj.getSecondRadioButtonText(), 3,
				true);
	}

	// Test ID 25565 - Bishoy 14-07-2019
	@Test
	public void assert_NachrichtSenden_checkboxAppPush_Selectable() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNnormal);

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		Thread.sleep(10000);
		callyaCustomerInfoObj.clickNachrichtSendenTab();
		Thread.sleep(30000);

		nachrichtSendenTabObj = new NachrichtSenden(driver);
		nachrichtSendenTabObj.selectAppPushChannel();
		nachrichtSendenTabObj.clickSendenButton();

		// pending the message that will be checked
	}

	// Test ID 25566 - Bishoy 14-07-2019
	@Test
	public void assert_NachrichtSenden_checkboxSMS_Selectable() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		Thread.sleep(10000);
		callyaCustomerInfoObj.clickNachrichtSendenTab();

		Thread.sleep(30000);
		nachrichtSendenTabObj = new NachrichtSenden(driver);
		nachrichtSendenTabObj.selectSMSchannel();
		nachrichtSendenTabObj.clickSendenButton();

		// pending the message that will be checked
	}

	// By Esraa //25574
	@Test
	public void AssertButtonNotClickable() throws Exception {
		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();

		Thread.sleep(8000);

		boolean DisableExists = callyaCustomerInfoObj.AssertButtonIsNotClickable(SpecialInstruction);
		Assert.assertTrue(DisableExists);

	}

	// by Esraa //25557
	@Test
	public void AssertAllTabsAreAuthoirzedforRead() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

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
			boolean Ispersonliche_Daten_displayed = personlicheDatenTabObj
					.personliche_Daten_AssertElementISDisplayed();
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
					.kundenFlags_MarkerSocs_AssertElementISDisplayed();
			Assert.assertTrue(Is_kundenFlags_MarkerSocsElementdisplayed);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Nashwa 3-6-2019
	@Test
	public void assert_MsisdnErnuetLaden_link() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		callyaCustomerInfoObj.click_MSISDNerneutLaden_link();
		callyaCustomerInfoObj.assert_SessionCounterTime();

	}

	// Hadeer 06-8-2019
	@Test
	public void assert_WeekView_PUA() throws InterruptedException {
		checkmsisdnObj = new CheckMSISDN(driver); // initialize a new instance of the page
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		Thread.sleep(8000);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();

		nutzungsubersichtTabObj = new Nutzungsubersicht(driver); // initialize a new instance of the page
		nutzungsubersichtTabObj.Assert_Weekview_PUA();

	}

	// Hadeer 06-8-2019
	@Test
	public void assert_WeekView_MMO() throws InterruptedException {
		checkmsisdnObj = new CheckMSISDN(driver); // initialize a new instance of the page
		checkmsisdnObj.enterMSISDN(MSISDNnormal);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		Thread.sleep(8000);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();

		nutzungsubersichtTabObj = new Nutzungsubersicht(driver); // initialize a new instance of the page
		nutzungsubersichtTabObj.Assert_Weekview_MMO();

	}

	/// ------------------- to be checked ---------------------------//

	// Yousra 27-6-2019 // to be updated as per new req.
	@Test
	public void assert_KunderFlags_Feilds() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		Thread.sleep(7000);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		Thread.sleep(8000);

		kundenFlagsMarkerSocsTabObj = new KundenFlagsMarkerSocs(driver);
		String[] arr = kundenFlagsMarkerSocsTabObj.assert_KunderFlags_Fields();

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

	// Yousra 13-6-2019

	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_neuSuche_KaisPopup() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN("491620491952");

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page

		buchungsubersichtTabObj = new Buchungsubersicht(driver);
		By entryfield_memo = buchungsubersichtTabObj.check_EntryTypeMemo();
		Assertions.assertElementExists(driver, entryfield_memo, true);

		callyaCustomerInfoObj.click_NeueSuche_link();

		checkmsisdnObj.enterMSISDN("491620491952");
		callyaCustomerInfoObj.verfiy_KaisPopup_notDisplayed();

	}

	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_EntryMemoField() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		baseLayer.navigateToCheckMsisdnURL();
		checkmsisdnObj.enterMSISDN("491620491954");

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page

		buchungsubersichtTabObj = new Buchungsubersicht(driver);
		By entryfield_memo = buchungsubersichtTabObj.check_EntryTypeMemo();

		Assertions.assertElementExists(driver, entryfield_memo, true);
	}

	// Yousra 13-6-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_EntrtMemoField_notExists() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		baseLayer.navigateToCheckMsisdnURL();
		checkmsisdnObj.enterMSISDN("491620491954");

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page

		buchungsubersichtTabObj = new Buchungsubersicht(driver);
		By entryfield_memo = buchungsubersichtTabObj.check_EntryTypeMemo_notexists();

		Assertions.assertElementExists(driver, entryfield_memo, false);
	}

}
