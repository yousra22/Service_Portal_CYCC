package SP_CYCC;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shaft.browser.BrowserActions;
import com.shaft.element.ElementActions;

public class Login {

	// Variables
	WebDriver driver;

	// WebElements
	By username = By.name("username");
	By password = By.name("password");
	By ok_button = By.name("OK");
	By callYa_CustomerInfo_link = By.xpath("//div/*[text()='CallYa Customer Info (beta)']");
	By callYa_innerLink = By.xpath("//div/*[@title='CallYa Customer Info (beta)']");
	By webteam_link = By.xpath("//div/*[text()='Webteam']");
	 
	// Constructor
	public Login(WebDriver driver) {
		this.driver = driver;
	}

	// Functions
	public void navigateToURL() {
		BrowserActions.navigateToURL(driver,
				"https://esportal8.stweb.elabs.svcs.hpe.com/jsp/serviceportal/login/ServicePortalLogin.jsp");
	}

	public void enterLoginInfo(String username, String password) {
		ElementActions.type(driver, this.username, username);
		ElementActions.type(driver, this.password, password);

		ElementActions.keyPress(driver, ok_button, "Enter");
	}

	public void click_CallYaCustomerInfo() {
		WebElement CallyaLink = driver.findElement(callYa_CustomerInfo_link);
		if (CallyaLink != null) {
			ElementActions.click(driver, callYa_CustomerInfo_link);
			ElementActions.click(driver, callYa_innerLink);

		} else {

			ElementActions.click(driver, webteam_link);
			ElementActions.click(driver, callYa_innerLink);
		}

	}

	// Nashwa 29-5-2019
	public void focus_On_CheckMsisdnPage() {
		// focus on the Check MSISDN page to Validate
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));

	}

}
