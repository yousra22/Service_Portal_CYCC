package SP_CYCC;
//16-9
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shaft.validation.Assertions;

import base.layer.TestBase;
import serviceportal.cycc.pages.BalanceTransfers;
import serviceportal.cycc.pages.CheckMSISDN;
import serviceportal.cycc.pages.Login;
import serviceportal.cycc.pages.MSISDNInfo;

public class BalanceTransfers_Test extends TestBase{
		
	//TestData
	String MSISDNinMocks = "491620491952";
		
	//Page objects
	Login loginObj;
	BalanceTransfers balanceTransfersTabObj;
	CheckMSISDN checkmsisdnObj;
	MSISDNInfo callyaCustomerInfoObj;
	
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
	// Yousra 27-6-2019
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
}
