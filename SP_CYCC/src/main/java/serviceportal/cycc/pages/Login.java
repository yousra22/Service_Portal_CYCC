package serviceportal.cycc.pages;

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
	By usernameTxt = By.name("username");
	By passwordTxt = By.name("password");
	By okButton = By.name("OK");
	By callYaCustomerInfoLink = By.xpath("//div/*[text()='CallYa Customer Info (beta)']");
	By callYaInnerLink = By.xpath("//div/*[@title='CallYa Customer Info (beta)']");
	By webteamLink = By.xpath("//div/*[text()='Webteam']");
	 
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
		ElementActions.waitForElementToBePresent(driver, usernameTxt, 4, true);
		ElementActions.type(driver, usernameTxt, username);
		ElementActions.type(driver, passwordTxt, password);

		ElementActions.keyPress(driver, okButton, "Enter");
	}

	public void click_CallYaCustomerInfo() {
		WebElement CallyaLink = driver.findElement(callYaCustomerInfoLink);
		if (CallyaLink != null) {
			ElementActions.click(driver, callYaCustomerInfoLink);
			ElementActions.click(driver, callYaInnerLink);

		} else {

			ElementActions.click(driver, webteamLink);
			ElementActions.click(driver, callYaInnerLink);
		}

	}

	// Nashwa 29-5-2019
	public void focus_On_CheckMsisdnPage() {
		// focus on the Check MSISDN page to Validate
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));

	}

}
