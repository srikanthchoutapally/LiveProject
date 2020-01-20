package DriverFactory;

import org.testng.annotations.Test;

public class AppTest 
{
	//Test Run 
@Test
public void kickStart() throws Throwable 
{
	//this is for method comment
	DriverScript ds=new DriverScript();
	try
	{
		ds.startTest();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
}
}
