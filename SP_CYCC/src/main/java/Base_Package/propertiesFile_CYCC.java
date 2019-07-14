package Base_Package;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class propertiesFile_CYCC {

	private Properties properties;
	private final String cycc_propertyFilePath = "src/test/resources/Properties/CYCC_PropFile";

	public propertiesFile_CYCC() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(cycc_propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + cycc_propertyFilePath);
		}
	}

	public String get_SessionTimer(String ElementName) throws Exception {
		// Read value using the logical name as Key
		String data = properties.getProperty(ElementName);
		return data;
	}

	public String get_neuSuche_URL(String ElementName) {
		// Read value using the logical name as Key
		String data = properties.getProperty(ElementName);
		return data;
	}
	
	public String get_SuccessMsg_wunschtarif(String ElementName) {
		// Read value using the logical name as Key
		String data = properties.getProperty(ElementName);
		return data;
	}
	
	public String[] get_Buchungsübersicht_tab_dropdown_fields(String ElementName) {
		// Read value using the logical name as Key
		String [] data = properties.getProperty(ElementName).toString().split(",");
		return data;
	}
}
