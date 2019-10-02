package SP_CYCC;
//16-9
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.layer.TestBase;
import serviceportal.cycc.pages.CheckMSISDN;
import serviceportal.cycc.pages.Login;
import serviceportal.cycc.pages.MSISDNInfo;
import serviceportal.cycc.pages.Nutzungsubersicht;

public class Nutzungsubersicht_Test extends TestBase {

	//TestData
	String MSISDNinMocks = "491620491952";
	String MSISDNnormal = "491620491954";// Bishoy 14-07-2019
	
	
	//Page Object
	Login loginObj;
	CheckMSISDN checkmsisdnObj;
	MSISDNInfo callyaCustomerInfoObj;
	Nutzungsubersicht nutzungsubersichtTabObj;
	
	@BeforeMethod
	public void beforeMethod() {

		OpenBrowser_navigateToURL();

		loginObj = new Login(driver); // initialize a new instance of the page
		loginObj.enterLoginInfo("admin", "admin"); // Enter username & Password then click OK
		loginObj.click_CallYaCustomerInfo();
		// Nashwa 29-5-2019
		loginObj.focus_On_CheckMsisdnPage(); // focus on Check Msisdn Tab
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

}
