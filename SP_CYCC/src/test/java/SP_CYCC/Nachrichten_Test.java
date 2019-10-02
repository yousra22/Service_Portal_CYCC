package SP_CYCC;
//16-9
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shaft.validation.Assertions;

import base.layer.TestBase;
import serviceportal.cycc.pages.CheckMSISDN;
import serviceportal.cycc.pages.Login;
import serviceportal.cycc.pages.MSISDNInfo;
import serviceportal.cycc.pages.nachrichten;

public class Nachrichten_Test extends TestBase{

	//Page Objects:
	Login loginObj;
	CheckMSISDN checkmsisdnObj;
	MSISDNInfo callyaCustomerInfoObj;
	nachrichten nachrichtenTabObj;
	
	@BeforeMethod
	public void beforeMethod() {

		OpenBrowser_navigateToURL();

		loginObj = new Login(driver); // initialize a new instance of the page
		loginObj.enterLoginInfo("admin", "admin"); // Enter username & Password then click OK
		loginObj.click_CallYaCustomerInfo();
		// Nashwa 29-5-2019
		loginObj.focus_On_CheckMsisdnPage(); // focus on Check Msisdn Tab
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
}
