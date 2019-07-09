package SP_CYCC;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shaft.element.ElementActions;
import com.shaft.validation.Assertions;
import com.shaft.validation.Verifications;

public class MSISDN_InfoPage {

	// Variables
	WebDriver driver;

	// WebElements
	By kais_popup = By.xpath("//div[@id = 'myModal']//div[@class = 'modal-content']");
	By txt_kaisId = By.id("user");
	By txt_KaisPwd = By.id("pwd");
	By button_Stornieren = By.xpath("//button[contains(.,'Stornieren')]");
	By button_Enigeben = By.id("closeKias");
	By tab_Buchungsübersicht = By.xpath("//li[@heading= 'BuchungsÃ¼bersicht']");
	By entryfield_memo = By.xpath("(//div[@ng-if=\"event.entryType == 'Memo'\"])[1] ");

	By ButtonCloseKias = By.xpath("//div[@id = 'myModal']//div[@class = 'modal-content']//button[@class='close']");
	By neue_Suche_link = By.xpath("//a[@href = 'callyaCustomerInfo.do']");
	By MSISDNerneutLaden_link = By.xpath("//button[@ng-click='reloadPage()']");
	By sessionTimer = By.id("session_counter");
	By MSISDN_link = By.xpath("//button[@ng-click='genesysReload()']");
	// yousra 6-9-2019
	By list_fields_msisdn = By
			.xpath("//div[@id = 'personalInfoDiv']//div[@class = 'result']//span[not(contains (@class, 'ng-hide'))]");
	By list_tabs_msisdn = By.xpath("//table[@class= 'pull-right']//u");

	By tab_BalanceTransfers = By.xpath("//li[@heading = 'Balance Transfers']");
	By list_info_Donor = By.xpath("(//div[@class= 'check-inputs'])[1]//label");
	By list_info_Recipient = By.xpath("(//div[@class= 'check-inputs'])[2]//label");
	By table_Request_fields = By.xpath("//div[@class= 'table-div']//th");

	By tab_KundenFlags = By.xpath("//li[@heading = 'KundenFlags/MarkerSocs']");
	By list_fields_kundenFlags = By.xpath("//div[@id= 'flagSocs']//span");
	
	By tab_Nachrichten = By.xpath("//li[@heading = 'Nachrichten']");
	
	By dropdown_msgDays = By.id("msg-days");
	By list_msgDays = By.xpath("//select[@id= 'msg-days']/option");
	
	By dropdown_msgBody = By.id("msg-body");
	By list_msgBody = By.xpath("//select[@id= 'msg-body']/option");
	
	By Wunschtarif_tab= By.xpath("(//div[@class='ng-isolate-scope']//li)[2]");
	
	
	By min_sms_value= By.xpath("(//*[@name='radioUnit'])[2]");
	By Daten_value = By.xpath("(//*[@name='radioData'])[2]");
	By active_button=By.id("updateDIYBtn");
	By Ja_Button= By.xpath("//button[@ng-click='ok()']");
	By success_Message=By.xpath("//div[@class='alert success']//p");
	By active_button_disabled = By.xpath("(//a[@ng-disabled='activateButtonDisabled'])[1]");
	
	
	By Buchungsübersicht_tab= By.xpath("(//div[@class='ng-isolate-scope']//li)[7]");
	By Mit_Vermerk_CheckBox= By.xpath("//label[text()='Mit Vermerk']");
	By bookingTypeFilter_firstDropDown = By.id("bookingTypeFilter");
	By  channelFilter_secondDropDown= By.id("channelFilter");
	By TimeFrameFilter_ThirdDropDown = By.id("TimeFrameFilter");
//First DropDownList 
	By BookingType=By.xpath("//label[@class='checkbox']");


//Second DropDownList
	By ChannelFilter=By.xpath("//*[@aria-labelledby='channelDropdownMenu']//label[@class='radio']");

	
// Third DropDownList
	By DateInterval_values=By.xpath("//*[@class='dropdown-menu dateInterval-menu']//label[@class='radio']");
	
	

	// Constructor
	public MSISDN_InfoPage(WebDriver driver) {
		this.driver = driver;
	}

	// Functions
	public void verfiy_KaisPopup() {
		ElementActions.waitForElementToBePresent(driver, kais_popup, 3, true);
		Verifications.verifyElementExists(driver, kais_popup, true);
		Assertions.assertElementExists(driver, kais_popup, true);
	}

	public void closeKiasPopup() {

		ElementActions.waitForElementToBePresent(driver, ButtonCloseKias, 10, true); // 27-6-2019
		ElementActions.keyPress(driver, this.ButtonCloseKias, "Enter");

	}

	public By checkMsisdn(String MSISDN) {
		By MSISDNValue = By
				.xpath("//div[@class ='white-content']//div[@class = 'result']//li[text()='" + MSISDN + "']");

		return MSISDNValue;
		
	}

	// by yousra
//	public void navigateToURL() {
	//	BrowserActions.navigateToURL(driver,
	//			"https://ecycc8.stweb.elabs.svcs.hpe.com/cycc/serviceportal/getCustomerType.do?MSISDN=491620491952&clearsession=true");
//	}

	public By verify_NeueSuche_link() {
		return neue_Suche_link;
		

	}

	public void click_NeueSuche_link() {
		ElementActions.click(driver, neue_Suche_link);
	}

	// no need for this class 
	//public void assert_CheckMSISDN_opened() {
	//	Assertions.assertBrowserAttribute(driver, "currenturl", "cycc/serviceportal/callyaCustomerInfo.do", 3, true);
	//}

	public By assert_SessionCounterTime() {
		
		return sessionTimer;
	}

	public By verify_GensisMSISDN() {
		By Gensis_MSISDN = By.xpath("//li[text()='491620491952']");
        
		return Gensis_MSISDN;
		
	}

	public void click_MSISDNerneutLaden_link() {
		ElementActions.click(driver, MSISDNerneutLaden_link);
	}

	public void click_MSISDN_link() {
		ElementActions.click(driver, MSISDN_link);
	}

	// yousra 6-9-2019
	public String[] check_MSISDNInfo_Fields() {
		java.util.List<WebElement> fields = driver.findElements(list_fields_msisdn);
		String[] arr= new String[12];
		arr[0] = fields.get(0).getText();
		arr[1] = fields.get(1).getText();
		arr[2] = fields.get(2).getText();
		arr[3] = fields.get(3).getText();
		arr[4] = fields.get(4).getText();
		arr[5] = fields.get(5).getText();
		arr[6] = fields.get(6).getText();
		arr[7] = fields.get(7).getText();
		arr[8] = fields.get(8).getText();
		arr[9] = fields.get(9).getText();
		arr[10] = fields.get(10).getText();
		arr[11]= fields.get(11).getText();

		

		return arr;
	}

	// yousra 6-9-2019
	public String[] check_MSISDNInfo_tabs() {
		java.util.List<WebElement> tabs = driver.findElements(list_tabs_msisdn);
		
		String[] arr= new String[5];
		arr[0] = tabs.get(0).getText();
		arr[1] = tabs.get(1).getText();
		arr[2]= tabs.get(2).getText();
		arr[3]= tabs.get(3).getText();

		
		return arr;

	}

	// yousra 13-6-2019
	public void verfiy_KaisPopup_notDisplayed() throws InterruptedException {
		ElementActions.waitForElementToBePresent(driver, neue_Suche_link, 10, true);
		Thread.sleep(4000);
		// Verifications.verifyElementExists(driver, kais_popup, false);
		Assertions.assertElementExists(driver, kais_popup, false); // mesh sh3'la

	}

	// yousra 13-6-2019
	public By check_EntryTypeMemo() throws InterruptedException {
		ElementActions.waitForElementToBePresent(driver, kais_popup, 10, true);
		Thread.sleep(1000);
		ElementActions.type(driver, txt_kaisId, "admin");
		ElementActions.type(driver, txt_KaisPwd, "12345");
		ElementActions.click(driver, button_Enigeben);

		Thread.sleep(4000);

		ElementActions.click(driver, tab_Buchungsübersicht);

		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");


		jse.executeScript("window.scrollBy(0,-250)", "");
		
		return entryfield_memo;
	}

	// yousra 13-06-2019
	public By check_EntryTypeMemo_notexists() throws InterruptedException {
		ElementActions.waitForElementToBePresent(driver, kais_popup, 10, true);
		Thread.sleep(1000);

		ElementActions.click(driver, button_Stornieren);

		Thread.sleep(5000);

		ElementActions.click(driver, tab_Buchungsübersicht);

		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	
       return entryfield_memo;
	}

	public String[] assert_BalanceTransfers_Fields() {
		boolean isClickable;

		do {
			isClickable = ElementActions.isElementClickable(driver, tab_BalanceTransfers);

		} while (isClickable == false);

		ElementActions.click(driver, tab_BalanceTransfers);
        String[] arr= new String[20];
		java.util.List<WebElement> info_displayed = driver.findElements(list_info_Donor);
		arr[0] = info_displayed.get(0).getText().trim();
		arr[1] = info_displayed.get(1).getText().trim();
		arr[2] = info_displayed.get(2).getText().trim();
		arr[3] = info_displayed.get(3).getText().trim();
		arr[4] = info_displayed.get(4).getText().trim();
		arr[5] = info_displayed.get(5).getText().trim();

		

		java.util.List<WebElement> info_displayed_Recipient = driver.findElements(list_info_Recipient);
		arr[6]= info_displayed_Recipient.get(0).getText().trim();
		arr[7] = info_displayed_Recipient.get(1).getText().trim();
		arr[8] = info_displayed_Recipient.get(2).getText().trim();
		arr[9] = info_displayed_Recipient.get(3).getText().trim();
		arr[10] = info_displayed_Recipient.get(4).getText().trim();



		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		java.util.List<WebElement> table_fields = driver.findElements(table_Request_fields);
		arr[11] = table_fields.get(0).getText().trim();
		arr[12]  = table_fields.get(1).getText().trim();
		arr[13]  = table_fields.get(2).getText().trim();
		arr[14]  = table_fields.get(3).getText().trim();
		arr[15] = table_fields.get(4).getText().trim();
		arr[16]  = table_fields.get(5).getText().trim();
		arr[17]  = table_fields.get(6).getText().trim();
		arr[18]  = table_fields.get(7).getText().trim();
		arr[19]  = table_fields.get(8).getText().trim();
		arr[20]  = table_fields.get(9).getText().trim();

	
		return arr;

	}

	public String[] assert_KunderFlags_Fields() {
		boolean isClickable;

		do {
			isClickable = ElementActions.isElementClickable(driver, tab_KundenFlags);

		} while (isClickable == false);

		ElementActions.click(driver, tab_KundenFlags);
        String[] arr=new String[7];
		java.util.List<WebElement> fields_displayed = driver.findElements(list_fields_kundenFlags);
		arr[0] = fields_displayed.get(0).getText().trim();
		arr[1] = fields_displayed.get(1).getText().trim();
		arr[2] = fields_displayed.get(2).getText().trim();
		arr[3] = fields_displayed.get(3).getText().trim();
		arr[4] = fields_displayed.get(4).getText().trim();
		arr[5] = fields_displayed.get(5).getText().trim();
		arr[6] = fields_displayed.get(6).getText().trim();

	
		return arr;

	}
	
	
	// Yousra 1-7-2019
	public String[] assert_NachrichtenTab() {
		boolean isClickable;
        String[] arr1 = new String[10];
		do {
			isClickable = ElementActions.isElementClickable(driver, tab_Nachrichten);

		} while (isClickable == false);

		ElementActions.click(driver, tab_Nachrichten);

		java.util.List<WebElement> optionsMsgDays = driver.findElements(list_msgDays);
		 arr1[0] = optionsMsgDays.get(1).getText().trim();
		 arr1[1] = optionsMsgDays.get(2).getText().trim();
		 arr1[2] = optionsMsgDays.get(3).getText().trim();
		 arr1[3] = optionsMsgDays.get(4).getText().trim();
		 arr1[4] = optionsMsgDays.get(5).getText().trim();
		

		
		
		
		java.util.List<WebElement> optionsMsgBody = driver.findElements(list_msgBody);
		 arr1[5] = optionsMsgBody.get(1).getText().trim();
		 arr1[6] = optionsMsgBody.get(2).getText().trim();
		 arr1[7] = optionsMsgBody.get(3).getText().trim();
		 arr1[8] = optionsMsgBody.get(4).getText().trim();
		 arr1[9] = optionsMsgBody.get(5).getText().trim();
		

		
		return arr1;

	}
	
	
	//Nashwa 26-6
	public String Change_tariff () {
		ElementActions.click(driver, Wunschtarif_tab);
		ElementActions.click(driver, min_sms_value);
		ElementActions.click(driver, Daten_value);
		ElementActions.click(driver, active_button);
		ElementActions.click(driver, Ja_Button);
		WebElement Success_message = driver.findElement(success_Message);
		String Success_Message = Success_message.getText();
		
		return Success_Message;
	} 
	
	//Nashwa 02-07
	public  By Check_Active_button () {
		ElementActions.click(driver, Wunschtarif_tab);
		
		
		return active_button_disabled;
	} 
	
	
	public String[]  Check_Buchungsübersicht_tab()
	{
		ElementActions.click(driver, Buchungsübersicht_tab);
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("window.scrollBy(0,700)");
		ElementActions.click(driver, bookingTypeFilter_firstDropDown);
		
		String [] newArr= new String[30];
		// booking type filter drop down list
		java.util.List <WebElement> bookingTypeFilter = driver.findElements(BookingType);
		 newArr[0]=bookingTypeFilter.get(0).getText();
		 newArr[1]=bookingTypeFilter.get(1).getText();
		 newArr[2]=bookingTypeFilter.get(2).getText();
		 newArr[3]=bookingTypeFilter.get(3).getText();
		 newArr[4]=bookingTypeFilter.get(4).getText();
		 
		 // channel filter drop down list
		 ElementActions.click(driver, channelFilter_secondDropDown);
		 java.util.List <WebElement> channelFilter = driver.findElements(ChannelFilter);
		 newArr[5]=channelFilter.get(0).getText();
		 newArr[6]=channelFilter.get(1).getText();
		 newArr[7]=channelFilter.get(2).getText();
		 newArr[8]=channelFilter.get(3).getText();
		 newArr[9]=channelFilter.get(4).getText();
		 newArr[10]=channelFilter.get(5).getText();
		 newArr[11]=channelFilter.get(6).getText();
		 newArr[12]=channelFilter.get(7).getText();
		 newArr[13]=channelFilter.get(8).getText();
		 newArr[14]=channelFilter.get(9).getText();
		 newArr[15]=channelFilter.get(10).getText();
		 newArr[16]=channelFilter.get(11).getText();
		 newArr[17]=channelFilter.get(12).getText();
		 newArr[18]=channelFilter.get(13).getText();
		 newArr[19]=channelFilter.get(14).getText();
		 newArr[20]=channelFilter.get(15).getText();
		 
		 //
		 ElementActions.click(driver, TimeFrameFilter_ThirdDropDown);
		 java.util.List <WebElement> DateInterval = driver.findElements(DateInterval_values);
		 newArr[21]=DateInterval.get(0).getText();
		 newArr[22]=DateInterval.get(1).getText();
		 newArr[23]=DateInterval.get(2).getText();
		 newArr[24]=DateInterval.get(3).getText();
		 newArr[25]=DateInterval.get(4).getText();
		 newArr[26]=DateInterval.get(5).getText();
		 newArr[27]=DateInterval.get(6).getText();
		 newArr[28]=DateInterval.get(7).getText();
		 newArr[29]=DateInterval.get(8).getText();
		return newArr ;
	}
}
