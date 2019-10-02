package 
serviceportal.cycc.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shaft.element.ElementActions;

//16-9
public class KundenFlagsMarkerSocs {

	// variables
	WebDriver driver;

	// locators
	By kundenFlagsTab = By.xpath("//li[@heading = 'KundenFlags/MarkerSocs']");
	By kundenFlagsFieldsList_values_li = By.xpath("//div[@id= 'flagSocs']//li");
	By kundenFlagsFieldsList_values_Span = By.xpath("//div[@id= 'flagSocs']//span");	
	By txtStatementErstellung= By.xpath("(//div[@id='flagSocs']//span)[1]");

	// constructor
	public KundenFlagsMarkerSocs(WebDriver driver) {
		this.driver = driver;
	}

	// functions

	// Yousra
	public boolean[] assert_KunderFlags_Fields_PUA() {

		boolean isClickable;

		do {
			isClickable = ElementActions.isElementClickable(driver, kundenFlagsTab);

		} while (isClickable == false);

		ElementActions.click(driver, kundenFlagsTab);

		ElementActions.waitForElementToBePresent(driver, txtStatementErstellung, 2, true);

		Map<String, String> Expected_values = new HashMap<String, String>();

		Expected_values.put("Statement Erstellung", ""); // 25593
		Expected_values.put("Telefonbucheintrag", "Nein"); // 25593
		Expected_values.put("DSL", " Ja"); // 25593
		Expected_values.put("Renunciation", ""); // 25593
		Expected_values.put("Prepaid Replenish", ""); // 25593
		Expected_values.put("Advertisement Indicator", ""); // 25593

		java.util.List<WebElement> fields_displayed_values_li = driver.findElements(kundenFlagsFieldsList_values_li);

		Map<String, String> actual_values = new HashMap<String, String>();
		for (int i = 0; i < 6; i++) {

			if (fields_displayed_values_li.get(i).getText().split(":", -1)[1] != "") {

				actual_values.put(fields_displayed_values_li.get(i).getText().split(":", -1)[0],
						fields_displayed_values_li.get(i).getText().split(":", -1)[1]);
			} else
				actual_values.put(fields_displayed_values_li.get(i).getText().split(":", -1)[0], "");

		}

		boolean[] actual_res = new boolean[6];
		int i = 0;

		Iterator<Map.Entry<String, String>> iterator_ex = Expected_values.entrySet().iterator();
		Iterator<Map.Entry<String, String>> iterator_act = actual_values.entrySet().iterator();
		Entry<String, String> entry_ex = iterator_ex.next();
		Entry<String, String> entry_act = iterator_act.next();

		boolean loop = true;

		while (loop == true) {

			if (entry_ex.getKey().contentEquals(entry_act.getKey())) {
				actual_res[i] = entry_ex.getValue().equals(entry_act.getValue());
				i++;

				if (iterator_ex.hasNext() == true) {
					entry_ex = iterator_ex.next();
					entry_act = iterator_act.next();
				} else {
					loop = false;
				}
			} else {
				entry_act = iterator_act.next();
				if (iterator_act.hasNext() != true) {
					iterator_act = actual_values.entrySet().iterator();
				}
			}
		}

		return actual_res;

	}

	public boolean[] assert_KunderFlags_Fields_MMO() {

		boolean isClickable;

		do {
			isClickable = ElementActions.isElementClickable(driver, kundenFlagsTab);

		} while (isClickable == false);

		ElementActions.click(driver, kundenFlagsTab);

		ElementActions.waitForElementToBePresent(driver, txtStatementErstellung, 2, true);

		Map<String, String> Expected_values = new HashMap<String, String>();

		Expected_values.put("Statement Erstellung", ""); // 25593
		Expected_values.put("Telefonbucheintrag", "Ja"); // 25593
		Expected_values.put("DSL", " Ja"); // 25593
		Expected_values.put("Renunciation", "Nein"); // 25593
		Expected_values.put("Prepaid Replenish", "Ja"); // 25593
		Expected_values.put("Advertisement Indicator", ""); // 25593

		java.util.List<WebElement> fields_displayed_values_li = driver.findElements(kundenFlagsFieldsList_values_li);

		Map<String, String> actual_values = new HashMap<String, String>();
		for (int i = 0; i < 6; i++) {

			if (fields_displayed_values_li.get(i).getText().split(":", -1)[1] != "") {

				actual_values.put(fields_displayed_values_li.get(i).getText().split(":", -1)[0],
						fields_displayed_values_li.get(i).getText().split(":", -1)[1]);
			} else
				actual_values.put(fields_displayed_values_li.get(i).getText().split(":", -1)[0], "");

		}

		boolean[] actual_res = new boolean[6];
		int i = 0;

		Iterator<Map.Entry<String, String>> iterator_ex = Expected_values.entrySet().iterator();
		Iterator<Map.Entry<String, String>> iterator_act = actual_values.entrySet().iterator();
		Entry<String, String> entry_ex = iterator_ex.next();
		Entry<String, String> entry_act = iterator_act.next();

		boolean loop = true;

		while (loop == true) {

			if (entry_ex.getKey().contentEquals(entry_act.getKey())) {
				actual_res[i] = entry_ex.getValue().equals(entry_act.getValue());
				i++;

				if (iterator_ex.hasNext() == true) {
					entry_ex = iterator_ex.next();
					entry_act = iterator_act.next();
				} else {
					loop = false;
				}
			} else {
				entry_act = iterator_act.next();
				if (iterator_act.hasNext() != true) {
					iterator_act = actual_values.entrySet().iterator();
				}
			}

		}

		return actual_res;

	}

	// esraa
	public boolean assertStatementErsteullengIsDisplayed() throws Exception {			
		driver.findElement(kundenFlagsTab).click();
		Thread.sleep(8000);
		boolean Ispresent = driver.findElement(txtStatementErstellung).isDisplayed();

		return Ispresent;

	}
	
	
	// Yousra
	public boolean assertListOfSocsAreDisplayed() throws Exception {	
		
		boolean isClickable;

		do {
			isClickable = ElementActions.isElementClickable(driver, kundenFlagsTab);

		} while (isClickable == false);

		ElementActions.click(driver, kundenFlagsTab);

		ElementActions.waitForElementToBePresent(driver, txtStatementErstellung, 2, true);
		
		
		java.util.List<WebElement> allFieldsDisplayed = driver.findElements(kundenFlagsFieldsList_values_li);
		boolean Ispresent = false;
		
		for (int i=0 ;  i<allFieldsDisplayed.size() ; i++)
		{
			String actualTxt = allFieldsDisplayed.get(i).getText();
			if(actualTxt.contentEquals("List of SOCs (FeatureSOC, FeatureSOCDescription) - (AgreementSOC, AgreementSOCDescription)"))
			{
				Ispresent = true;
				break;
			}
		}
		

		return Ispresent;

	}
}
