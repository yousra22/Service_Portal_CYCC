package Base_Package;
import org.openqa.selenium.WebDriver;

import com.shaft.browser.BrowserActions;

import SP_CYCC.Login;

public class base_Layer {

	// Variables
		WebDriver driver;
		Login loginObject;

		// Constructor
		public base_Layer(WebDriver driver) {
			this.driver = driver;
		}
		
		// by yousra
			public void navigateToURL() {
				BrowserActions.navigateToURL(driver,
						"https://ecycc8.stweb.elabs.svcs.hpe.com/cycc/serviceportal/getCustomerType.do?MSISDN=491620491952&clearsession=true");
			}

			
			public void navigateToCheckMsisdnURL() {
				BrowserActions.navigateToURL(driver,
						"https://ecycc8.stweb.elabs.svcs.hpe.com/cycc/serviceportal/callyaCustomerInfo.do?role=1&sp=CALLYACUSTOMERINFO&login=admin&ID=059D104B49057192053292202CDF434E");
			}

}
