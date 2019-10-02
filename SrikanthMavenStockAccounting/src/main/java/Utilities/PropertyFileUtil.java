package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil 
{
	public static String getValueForKey(String key) throws Throwable
	{
		Properties configproperties=new Properties();
		 FileInputStream fis= new FileInputStream("C:\\Users\\srikanth.ch\\workspace\\SrikanthMavenStockAccounting\\PropertiesFile\\Environment.properties");
		 configproperties.load(fis);
		return configproperties.getProperty(key);
	}

	
	
}
