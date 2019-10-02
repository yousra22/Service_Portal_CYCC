package SP_CYCC;
//16-9
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shaft.validation.Assertions;

import base.layer.TestBase;
import serviceportal.cycc.pages.CheckMSISDN;
import serviceportal.cycc.pages.Login;

public class CheckMSISDN_Test extends TestBase {

	//Page Objects
	Login loginObj;
	CheckMSISDN checkmsisdnObj;
	
	
	@BeforeMethod
	public void beforeMethod() {

		OpenBrowser_navigateToURL();

		loginObj = new Login(driver); // initialize a new instance of the page
		loginObj.enterLoginInfo("admin", "admin"); // Enter username & Password then click OK
		loginObj.click_CallYaCustomerInfo();
		// Nashwa 29-5-2019
		loginObj.focus_On_CheckMsisdnPage(); // focus on Check Msisdn Tab
	}

	// Yousra 31-7.2019 //25567
	@Test
	public void assert_MSISDNAbrufen_button_HintText() {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.click_MSISDNAbrufen();

		String actual_hintText = checkmsisdnObj.get_hintText_MSISDN_Abrufen();
		Assertions.assertEquals(properties.getProperty("hintTest_MSISDN_Abrufen"), actual_hintText, 1, true);
	}

}
