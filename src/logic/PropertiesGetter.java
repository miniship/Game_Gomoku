package logic;

import java.io.IOException;
import java.util.Properties;

public class PropertiesGetter {

	public String getProperty(String key) {
		Properties properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream(
					"gomoku.properties"));
			return properties.getProperty(key);
		} catch (IOException e) {
			System.out.println("Error: cannot load properties file");
			return null;
		}
	}
}
