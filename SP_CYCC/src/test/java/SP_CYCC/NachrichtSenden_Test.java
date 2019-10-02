package SP_CYCC;
//16-9
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shaft.validation.Assertions;

import base.layer.TestBase;
import serviceportal.cycc.pages.CheckMSISDN;
import serviceportal.cycc.pages.Login;
import serviceportal.cycc.pages.MSISDNInfo;
import serviceportal.cycc.pages.NachrichtSenden;

public class NachrichtSenden_Test extends TestBase {

	// TestData
	String MSISDNnormal = "491620491954";// Bishoy 14-07-2019
	String MSISDNinMocks = "491620491952";

	String FirstRadioButton_ExpectedText = "Example Message Text";// Test ID 25562 - Bishoy 14-07-2019
	String SecondRadioButton_ExpectedText = "Example Message with multiple channels";// Test ID 25562 - Bishoy  // 14-07-2019
	String NachrichtSendenTabMessageText = "Send Message Result: OK";// Test ID 25573 - Bishoy 08-08-2019
	
	// Page Object:
	Login loginObj;
	CheckMSISDN checkmsisdnObj;
	MSISDNInfo callyaCustomerInfoObj;
	NachrichtSenden nachrichtSendenTabObj;// Bishoy 14-07-2019

	@BeforeMethod
	public void beforeMethod() {

		OpenBrowser_navigateToURL();

		loginObj = new Login(driver); // initialize a new instance of the page
		loginObj.enterLoginInfo("admin", "admin"); // Enter username & Password then click OK
		loginObj.click_CallYaCustomerInfo();
		// Nashwa 29-5-2019
		loginObj.focus_On_CheckMsisdnPage(); // focus on Check Msisdn Tab
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
	// Updated by Bishoy on 08-08-2019: adding selectFirstRadioButton and the
	// NachrichtSendenTabMessageText assertion
	@Test
	public void assert_NachrichtSenden_checkboxAppPush_Selectable() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNnormal);

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		Thread.sleep(3000);
		callyaCustomerInfoObj.clickNachrichtSendenTab();
		Thread.sleep(3000);
		nachrichtSendenTabObj = new NachrichtSenden(driver);
		nachrichtSendenTabObj.selectFirstRadioButton();
		nachrichtSendenTabObj.selectAppPushChannel();
		nachrichtSendenTabObj.clickSendenButton();		
		Assertions.assertEquals(NachrichtSendenTabMessageText, nachrichtSendenTabObj.getMessageText(), 1, true);

	}

	// Test ID 25566 - Bishoy 14-07-2019
	// Updated by Bishoy on 08-08-2019: adding selectFirstRadioButton and the
	// NachrichtSendenTabMessageText assertion
	@Test
	public void assert_NachrichtSenden_checkboxSMS_Selectable() throws InterruptedException {

		checkmsisdnObj = new CheckMSISDN(driver);
		checkmsisdnObj.enterMSISDN(MSISDNinMocks);

		callyaCustomerInfoObj = new MSISDNInfo(driver);
		callyaCustomerInfoObj.close_KaisPop_WithNoCred();
		Thread.sleep(3000);
		callyaCustomerInfoObj.clickNachrichtSendenTab();
		Thread.sleep(3000);
		nachrichtSendenTabObj = new NachrichtSenden(driver);
		nachrichtSendenTabObj.selectFirstRadioButton();
		nachrichtSendenTabObj.selectSMSchannel();
		nachrichtSendenTabObj.clickSendenButton();		
		Assertions.assertEquals(NachrichtSendenTabMessageText, nachrichtSendenTabObj.getMessageText(), 1, true);
	}
}
