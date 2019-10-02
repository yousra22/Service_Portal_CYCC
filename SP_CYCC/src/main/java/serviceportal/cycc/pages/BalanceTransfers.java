package serviceportal.cycc.pages;
//16-9
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shaft.element.ElementActions;

public class BalanceTransfers {

	
	// Changes 12-9
	
	// Variables
	WebDriver driver;

	// locators
	By balanceTransfersTab = By.xpath("//li[@heading = 'Balance Transfers']");
	By infoDonorList = By.xpath("(//div[@class= 'check-inputs'])[1]//label");
	By infoRecipientList = By.xpath("(//div[@class= 'check-inputs'])[2]//label");
	By requestFieldsTable = By.xpath("//div[@class= 'table-div']//th");

	// Constructor
	public BalanceTransfers(WebDriver driver) {
		this.driver = driver;
	}

	// Functions

	public String[] assert_BalanceTransfers_Fields() {
		boolean isClickable;

		do {
			isClickable = ElementActions.isElementClickable(driver, balanceTransfersTab);

		} while (isClickable == false);

		ElementActions.click(driver, balanceTransfersTab);

		String[] arr = new String[21];
		java.util.List<WebElement> info_displayed = driver.findElements(infoDonorList);
		arr[0] = info_displayed.get(0).getText().trim();
		arr[1] = info_displayed.get(1).getText().trim();
		arr[2] = info_displayed.get(2).getText().trim();
		arr[3] = info_displayed.get(3).getText().trim();
		arr[4] = info_displayed.get(4).getText().trim();
		arr[5] = info_displayed.get(5).getText().trim();

		java.util.List<WebElement> info_displayed_Recipient = driver.findElements(infoRecipientList);
		arr[6] = info_displayed_Recipient.get(0).getText().trim();
		arr[7] = info_displayed_Recipient.get(1).getText().trim();
		arr[8] = info_displayed_Recipient.get(2).getText().trim();
		arr[9] = info_displayed_Recipient.get(3).getText().trim();
		arr[10] = info_displayed_Recipient.get(4).getText().trim();

		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		java.util.List<WebElement> table_fields = driver.findElements(requestFieldsTable);
		arr[11] = table_fields.get(0).getText().trim();
		arr[12] = table_fields.get(1).getText().trim();
		arr[13] = table_fields.get(2).getText().trim();
		arr[14] = table_fields.get(3).getText().trim();
		arr[15] = table_fields.get(4).getText().trim();
		arr[16] = table_fields.get(5).getText().trim();
		arr[17] = table_fields.get(6).getText().trim();
		arr[18] = table_fields.get(7).getText().trim();
		arr[19] = table_fields.get(8).getText().trim();
		arr[20] = table_fields.get(9).getText().trim();

		return arr;

	}

	// esraa
	public boolean balance_Transfers_AssertElementISDisplayed() throws Exception {

		By elementoftab11 = By.xpath("//div[@id='balanceTransfer']//h3");
		Thread.sleep(9000);
		Thread.sleep(5000);
		driver.findElement(balanceTransfersTab).click();
		Thread.sleep(8000);
		boolean Ispresent = driver.findElement(elementoftab11).isDisplayed();

		return Ispresent;

	}

}
