package serviceportal.cycc.pages;
//16-9
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Optionen {

	// Variables
	WebDriver driver;

	// Element Locators
	By optionenTab = By.xpath("(//div[@class='ng-isolate-scope']//li)[6]");

	By elementOfTab6 = By.xpath("//div[@id='headingOne']/h4");

	// Constructor
	public Optionen(WebDriver driver) {
		this.driver = driver;
	}

	// Esraa 17/7
	public boolean Optionen_AssertElementISDisplayed() throws Exception {

		Thread.sleep(9000);
		driver.findElement(optionenTab).click();
		Thread.sleep(8000);
		boolean Ispresent = driver.findElement(elementOfTab6).isDisplayed();

		return Ispresent;

	}

}
