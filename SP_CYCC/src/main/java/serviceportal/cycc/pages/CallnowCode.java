package serviceportal.cycc.pages;
//16-9
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CallnowCode {

	// Variables
	WebDriver driver;

	// Element Locators

	// Esraa
	By callnowCodeTab = By.xpath("(//div[@class='ng-isolate-scope']//li)[4]");
	By elementOfTab4 = By.xpath("//div[@id='topup']/h3");

	// Constructor
	public CallnowCode(WebDriver driver) {
		this.driver = driver;
	}

	// Esraa 17/7
	public boolean CallNowCode_AssertElementISDisplayed() throws Exception {

		Thread.sleep(9000);
		driver.findElement(callnowCodeTab).click();
		Thread.sleep(8000);
		boolean Ispresent = driver.findElement(elementOfTab4).isDisplayed();

		return Ispresent;

	}
}
