package SP_CYCC;
//16-9
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shaft.validation.Assertions;

import base.layer.TestBase;
import serviceportal.cycc.pages.Buchungsubersicht;
import serviceportal.cycc.pages.CheckMSISDN;
import serviceportal.cycc.pages.Login;
import serviceportal.cycc.pages.MSISDNInfo;

public class Buchungsubersicht_Test extends TestBase {

	// TestData
	String MSISDNinMocks = "491620491952";

	// Page Objects :
	Login loginObj;
	CheckMSISDN checkmsisdnObj;
	MSISDNInfo callyaCustomerInfoObj;
	Buchungsubersicht buchungsubersichtTabObj;

	@BeforeMethod
	public void beforeMethod() {

		OpenBrowser_navigateToURL();

		loginObj = new Login(driver); // initialize a new instance of the page
		loginObj.enterLoginInfo("admin", "admin"); // Enter username & Password then click OK
		loginObj.click_CallYaCustomerInfo();
		// Nashwa 29-5-2019
		loginObj.focus_On_CheckMsisdnPage(); // focus on Check Msisdn Tab
	}

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

	// Yousra 13-6-2019
	// Esraa 8-9-2019 //25580
	@Test
	public void assert_neuSuche_KaisPopup() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN("491620491952");

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		Thread.sleep(3000);
		callyaCustomerInfoObj.enter_KaisCredintails("admin", "1234");
		Thread.sleep(3000);
		buchungsubersichtTabObj = new Buchungsubersicht(driver);
		Thread.sleep(3000);
		boolean entryfield_memo = buchungsubersichtTabObj.check_EntryTypeMemo();
		Assert.assertEquals(entryfield_memo, true);
	}

	@Test
	// esraa 8/9/2019 //25577
	public void assert_EntryMemoField() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		// baseLayer.navigateToCheckMsisdnURL();
		checkmsisdnObj.enterMSISDN("491620491954");

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page
		Thread.sleep(3000);
		callyaCustomerInfoObj.enter_KaisCredintails("admin", "1234");
		Thread.sleep(3000);
		buchungsubersichtTabObj = new Buchungsubersicht(driver);
		boolean entryfield_memo = buchungsubersichtTabObj.check_EntryTypeMemo();
		Assert.assertEquals(entryfield_memo, false);

	}

	
	// Yousra 13-6-2019
	@Test(dependsOnMethods = { "navigateToURLandLogin" })
	public void assert_EntrtMemoField_notExists() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		navigateToCheckMsisdnURL();
		checkmsisdnObj.enterMSISDN("491620491954");

		callyaCustomerInfoObj = new MSISDNInfo(driver); // initialize a new instance of the page

		buchungsubersichtTabObj = new Buchungsubersicht(driver);
		By entryfield_memo = buchungsubersichtTabObj.check_EntryTypeMemo_notexists();

		Assertions.assertElementExists(driver, entryfield_memo, false);
	}

}
