package base.layer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.shaft.browser.BrowserActions;
import com.shaft.browser.BrowserFactory;

public class TestBase {

	// Variables
	public WebDriver driver;
	public static Properties properties;
	private final static String cycc_propertyFilePath = "src/test/resources/Properties/CYCC_PropFile";

	static {

		try {
			properties = load_propertiesFile_CYCC();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Properties load_propertiesFile_CYCC() throws IOException {
		
		Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(cycc_propertyFilePath);
        try {
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            try {
                properties.load(reader);
                return properties;
            } finally {
                reader.close();
            }
        } finally {
           inputStream.close();
        }
	}	

	// by yousra
	public void OpenBrowser_navigateToURL() {

		
		driver = BrowserFactory.getBrowser();
		BrowserActions.navigateToURL(driver,
				"https://esportal8.stweb.elabs.svcs.hpe.com/jsp/serviceportal/login/Login.do");
		
		try {
			properties = load_propertiesFile_CYCC();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void navigateToCheckMsisdnURL() {
		BrowserActions.navigateToURL(driver,
				"https://ecycc8.stweb.elabs.svcs.hpe.com/cycc/serviceportal/callyaCustomerInfo.do?role=1&sp=CALLYACUSTOMERINFO&login=admin&ID=059D104B49057192053292202CDF434E");
	}

}
