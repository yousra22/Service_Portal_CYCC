package SP_CYCC;
//16-9
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shaft.validation.Assertions;

import base.layer.TestBase;
import serviceportal.cycc.pages.CheckMSISDN;
import serviceportal.cycc.pages.Login;
import serviceportal.cycc.pages.MSISDNInfo;
import serviceportal.cycc.pages.PersonlicheDaten;

public class PersonlicheDaten_Test extends TestBase {

	// TestData
	String MSISDNinMocks = "491620491952";
	String MSISDNnormal = "491620491954";// Bishoy 14-07-2019

	// Bishoy
	String Kundenkennwort_ExpectedText = "Kundenkennwort:";// Test ID 25560 - Bishoy 14-07-2019
	String ZahlungsubersichtTab_ExpectedText = "Zahlungsübersicht";// Test ID 25560 - Bishoy 14-07-2019

	// Page Object
	Login loginObj;
	CheckMSISDN checkmsisdnObj;
	MSISDNInfo callyaCustomerInfoObj;
	PersonlicheDaten personlicheDatenTabObj;// Bishoy 21-07-2019

	@BeforeMethod
	public void beforeMethod() {

		OpenBrowser_navigateToURL();

		loginObj = new Login(driver); // initialize a new instance of the page
		loginObj.enterLoginInfo("admin", "admin"); // Enter username & Password then click OK
		loginObj.click_CallYaCustomerInfo();
		// Nashwa 29-5-2019
		loginObj.focus_On_CheckMsisdnPage(); // focus on Check Msisdn Tab
	}

	// Test ID 25560 - Bishoy 14-07-2019
	@Test
	public void assert_PersonlicheDaten_Zahlungsubersicht_Tabs_Displayed() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNnormal);

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();

		Thread.sleep(30000);

		Assertions.assertEquals(ZahlungsubersichtTab_ExpectedText, callyaCustomerInfoObj.getZahlungsubersichtTabText(),
				1, true);

		callyaCustomerInfoObj.clickPersonlicheDatenTab();
		Thread.sleep(30000);

		personlicheDatenTabObj = new PersonlicheDaten(driver);
		Assertions.assertEquals(Kundenkennwort_ExpectedText, personlicheDatenTabObj.getKundenkennwortText(), 1, true);
		Assertions.assertEquals(Kundenkennwort_ExpectedText, personlicheDatenTabObj.getLabelText(6), 1, true);
	}




}
