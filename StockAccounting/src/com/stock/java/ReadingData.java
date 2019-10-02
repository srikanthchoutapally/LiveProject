package com.stock.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadingData {

	public static void main(String[] args) throws Throwable  {
	 Properties configproperties=new Properties();
	 FileInputStream files= new FileInputStream("C:\\Users\\srikanth.ch\\workspace\\StockAccounting\\PropertiesFile\\Environment.properties");
	 configproperties.load(files);
	 System.out.println(configproperties.getProperty("Browser"));
	 System.out.println(configproperties.getProperty("URL"));
	 System.out.println(configproperties.getProperty("Username"));
	 System.out.println(configproperties.getProperty("Password"));
	}

}
