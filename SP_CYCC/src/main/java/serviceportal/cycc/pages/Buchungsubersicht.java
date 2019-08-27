package serviceportal.cycc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shaft.element.ElementActions;

public class Buchungsubersicht {

	// Variables
	WebDriver driver;

	// locators
	By buchungsübersichtTab = By.xpath("//li[@heading= 'Buchungsübersicht']");
	By entryFieldMemo = By.xpath("(//div[@ng-if=\"event.entryType == 'Memo'\"])[1] ");

	By bookingTypeFilterFirstDropDown = By.id("bookingTypeFilter");
	By channelFilterSecondDropDown = By.id("channelFilter");
	By timeFrameFilterThirdDropDown = By.id("TimeFrameFilter");

	// First DropDownList
	By bookingType = By.xpath("//label[@class='checkbox']");
	// Second DropDownList
	By channelFilter = By.xpath("//*[@aria-labelledby='channelDropdownMenu']//label[@class='radio']");
	// Third DropDownList
	By dateIntervalValues = By.xpath("//*[@class='dropdown-menu dateInterval-menu']//label[@class='radio']");

	// Constructor
	public Buchungsubersicht(WebDriver driver) {
		this.driver = driver;
	}

	// Functions

	// yousra 13-06-2019
	public By check_EntryTypeMemo() throws InterruptedException {

		ElementActions.click(driver, buchungsübersichtTab);

		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		jse.executeScript("window.scrollBy(0,-250)", "");

		return entryFieldMemo;
	}

	// yousra 13-06-2019
	public By check_EntryTypeMemo_notexists() throws InterruptedException {

		ElementActions.click(driver, buchungsübersichtTab);

		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		return entryFieldMemo;
	}

	// Need to be re-named ---> Nashwa
	public String[] Check_Buchungsubersicht_tab() {
		ElementActions.click(driver, buchungsübersichtTab);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");

		ElementActions.waitForElementToBePresent(driver, bookingTypeFilterFirstDropDown, 4, true);
		ElementActions.click(driver, bookingTypeFilterFirstDropDown);

		String[] newArr = new String[30];
		// booking type filter drop down list
		java.util.List<WebElement> bookingTypeFilter = driver.findElements(bookingType);
		newArr[0] = bookingTypeFilter.get(0).getText();
		newArr[1] = bookingTypeFilter.get(1).getText();
		newArr[2] = bookingTypeFilter.get(2).getText();
		newArr[3] = bookingTypeFilter.get(3).getText();
		newArr[4] = bookingTypeFilter.get(4).getText();

		// channel filter drop down list
		ElementActions.click(driver, channelFilterSecondDropDown);
		java.util.List<WebElement> channelFilterWebElement = driver.findElements(channelFilter);
		newArr[5] = channelFilterWebElement.get(0).getText();
		newArr[6] = channelFilterWebElement.get(1).getText();
		newArr[7] = channelFilterWebElement.get(2).getText();
		newArr[8] = channelFilterWebElement.get(3).getText();
		newArr[9] = channelFilterWebElement.get(4).getText();
		newArr[10] = channelFilterWebElement.get(5).getText();
		newArr[11] = channelFilterWebElement.get(6).getText();
		newArr[12] = channelFilterWebElement.get(7).getText();
		newArr[13] = channelFilterWebElement.get(8).getText();
		newArr[14] = channelFilterWebElement.get(9).getText();
		newArr[15] = channelFilterWebElement.get(10).getText();
		newArr[16] = channelFilterWebElement.get(11).getText();
		newArr[17] = channelFilterWebElement.get(12).getText();
		newArr[18] = channelFilterWebElement.get(13).getText();
		newArr[19] = channelFilterWebElement.get(14).getText();
		newArr[20] = channelFilterWebElement.get(15).getText();

		//
		ElementActions.click(driver, timeFrameFilterThirdDropDown);
		java.util.List<WebElement> DateInterval = driver.findElements(dateIntervalValues);
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

	// esraa
	public boolean buchungsubersicht_AssertElementISDisplayed() throws Exception {

		By elementoftab7 = By.xpath("//div[@id='memo-heading']/h4");
		Thread.sleep(9000);
		driver.findElement(buchungsübersichtTab).click();
		Thread.sleep(8000);
		boolean Ispresent = driver.findElement(elementoftab7).isDisplayed();

		return Ispresent;

	}
}
