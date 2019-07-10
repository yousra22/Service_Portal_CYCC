package SP_CYCC;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shaft.element.ElementActions;

public class buchungsubersicht {

	// Variables
	WebDriver driver;

	// locators
	By tab_Buchungsübersicht = By.xpath("//li[@heading= 'Buchungsübersicht']");
	By entryfield_memo = By.xpath("(//div[@ng-if=\"event.entryType == 'Memo'\"])[1] ");

	By bookingTypeFilter_firstDropDown = By.id("bookingTypeFilter");
	By channelFilter_secondDropDown = By.id("channelFilter");
	By TimeFrameFilter_ThirdDropDown = By.id("TimeFrameFilter");

	// First DropDownList
	By BookingType = By.xpath("//label[@class='checkbox']");
	// Second DropDownList
	By ChannelFilter = By.xpath("//*[@aria-labelledby='channelDropdownMenu']//label[@class='radio']");
	// Third DropDownList
	By DateInterval_values = By.xpath("//*[@class='dropdown-menu dateInterval-menu']//label[@class='radio']");

	
	// Constructor
	public buchungsubersicht(WebDriver driver) {
		this.driver = driver;
	}

	// Functions

	// yousra 13-06-2019
	public By check_EntryTypeMemo() throws InterruptedException {

		ElementActions.click(driver, tab_Buchungsübersicht);

		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		jse.executeScript("window.scrollBy(0,-250)", "");

		return entryfield_memo;
	}

	// yousra 13-06-2019
	public By check_EntryTypeMemo_notexists() throws InterruptedException {

		ElementActions.click(driver, tab_Buchungsübersicht);

		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		return entryfield_memo;
	}

	
	//Need to be re-named ---> Nashwa
	public String[] Check_Buchungsubersicht_tab() {
		ElementActions.click(driver, tab_Buchungsübersicht);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");
		ElementActions.click(driver, bookingTypeFilter_firstDropDown);

		String[] newArr = new String[30];
		// booking type filter drop down list
		java.util.List<WebElement> bookingTypeFilter = driver.findElements(BookingType);
		newArr[0] = bookingTypeFilter.get(0).getText();
		newArr[1] = bookingTypeFilter.get(1).getText();
		newArr[2] = bookingTypeFilter.get(2).getText();
		newArr[3] = bookingTypeFilter.get(3).getText();
		newArr[4] = bookingTypeFilter.get(4).getText();

		// channel filter drop down list
		ElementActions.click(driver, channelFilter_secondDropDown);
		java.util.List<WebElement> channelFilter = driver.findElements(ChannelFilter);
		newArr[5] = channelFilter.get(0).getText();
		newArr[6] = channelFilter.get(1).getText();
		newArr[7] = channelFilter.get(2).getText();
		newArr[8] = channelFilter.get(3).getText();
		newArr[9] = channelFilter.get(4).getText();
		newArr[10] = channelFilter.get(5).getText();
		newArr[11] = channelFilter.get(6).getText();
		newArr[12] = channelFilter.get(7).getText();
		newArr[13] = channelFilter.get(8).getText();
		newArr[14] = channelFilter.get(9).getText();
		newArr[15] = channelFilter.get(10).getText();
		newArr[16] = channelFilter.get(11).getText();
		newArr[17] = channelFilter.get(12).getText();
		newArr[18] = channelFilter.get(13).getText();
		newArr[19] = channelFilter.get(14).getText();
		newArr[20] = channelFilter.get(15).getText();

		//
		ElementActions.click(driver, TimeFrameFilter_ThirdDropDown);
		java.util.List<WebElement> DateInterval = driver.findElements(DateInterval_values);
		newArr[21] = DateInterval.get(0).getText();
		newArr[22] = DateInterval.get(1).getText();
		newArr[23] = DateInterval.get(2).getText();
		newArr[24] = DateInterval.get(3).getText();
		newArr[25] = DateInterval.get(4).getText();
		newArr[26] = DateInterval.get(5).getText();
		newArr[27] = DateInterval.get(6).getText();
		newArr[28] = DateInterval.get(7).getText();
		newArr[29] = DateInterval.get(8).getText();
		return newArr;
	}
}
