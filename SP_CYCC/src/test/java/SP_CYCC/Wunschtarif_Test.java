package SP_CYCC;
//16-9
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shaft.validation.Assertions;

import base.layer.TestBase;
import serviceportal.cycc.pages.CheckMSISDN;
import serviceportal.cycc.pages.Login;
import serviceportal.cycc.pages.MSISDNInfo;
import serviceportal.cycc.pages.Wunschtarif;

public class Wunschtarif_Test extends TestBase{
	
	//TestData
	String MSISDNinMocks = "491620491952";
	
	//Page Objects
	Login loginObj;	
	CheckMSISDN checkmsisdnObj;
	MSISDNInfo callyaCustomerInfoObj;
	Wunschtarif wunschtarifTabObj;
	
	@BeforeMethod
	public void beforeMethod() {

		OpenBrowser_navigateToURL();

		loginObj = new Login(driver); // initialize a new instance of the page
		loginObj.enterLoginInfo("admin", "admin"); // Enter username & Password then click OK
		loginObj.click_CallYaCustomerInfo();
		// Nashwa 29-5-2019
		loginObj.focus_On_CheckMsisdnPage(); // focus on Check Msisdn Tab
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
}
