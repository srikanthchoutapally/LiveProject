package CommonLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.PropertyFileUtil;


public class FunctionLibrary 
{
	static WebDriver driver;
	//startBrowser
	public static WebDriver startBrowser(WebDriver driver) throws Throwable
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "path");
			driver= new FirefoxDriver();
		}else 
			if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Chrome")){
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\srikanth.ch\\Downloads\\chromedriver.exe");
				driver= new ChromeDriver();
			}else
				if(PropertyFileUtil.getValueForKey("Broser").equalsIgnoreCase("ie"))
				{
					System.setProperty("webdriver.ie.driver", "path");
					driver= new InternetExplorerDriver();
				}
		return driver;
		
	}
	//OpenApplication
	public static void openAppliction(WebDriver driver) throws Throwable
	{
		driver.get(PropertyFileUtil.getValueForKey("URL"));
		driver.manage().window().maximize();
	}
	//typeAction
	public static void typeAction(WebDriver driver,String locatorType, String locatorValue, String data)
	{
		if(locatorType.equalsIgnoreCase("id")){
			driver.findElement(By.id(locatorValue)).clear();
			driver.findElement(By.id(locatorValue)).sendKeys(data);
		}else
			if(locatorType.equalsIgnoreCase("name")){
				driver.findElement(By.name(locatorValue)).clear();
				driver.findElement(By.name(locatorValue)).sendKeys(data);
			}else
				if(locatorType.equalsIgnoreCase("xpath")){
					driver.findElement(By.xpath(locatorValue)).clear();
					driver.findElement(By.xpath(locatorValue)).sendKeys(data);	
				}
	}
	//clickAction
	public static void clickAction(WebDriver driver,String locatorType,String locatorValue)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).click();
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorValue)).click();
			}else
				if(locatorType.contentEquals("xpath"))
				{
					driver.findElement(By.xpath(locatorValue)).click();
				}
		
	}
	//closeBrowser
	public static void closeBrowser(WebDriver driver){
		driver.close();	
	}
	//waitForElement
	public static void waitForElement(WebDriver driver,String locatorType, String locatorValue, String waitTime)
	{
		WebDriverWait myWait =new WebDriverWait(driver, Integer.parseInt(waitTime));
		if(locatorType.equalsIgnoreCase("id"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		}else
			if(locatorType.equalsIgnoreCase("name")
					){
				myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));					
				}
	}
	public static  void pageDown(WebDriver driver)
	{
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
	
	}
	
	//Capture Supplier Number
	public static void captureData(WebDriver driver,String locatorType,String locatorValue) throws Throwable
	{
		
		Thread.sleep(2000);
		String data="";
		if (locatorType.equalsIgnoreCase("id"))
		{
		data=driver.findElement(By.id(locatorValue)).getAttribute("value");
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				data=driver.findElement(By.name(locatorValue)).getAttribute("value");
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					data=driver.findElement(By.xpath(locatorValue)).getAttribute("value");	
				}
		
		//String data= driver.findElement(By.id("x_Supplier_Number")).getAttribute("value");
		System.out.println("Captured data is :"+data);
		FileWriter fw=new FileWriter("C:\\Users\\srikanth.ch\\workspace\\StockAccounting\\CaptureData\\data.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(data);
		bw.close();
		
	}
	
//SupplierCreation Table Validation

	public static void tableValidation(WebDriver driver,String column) throws Throwable
	{
	//Reading the Supplier Number  from  text file 
	
		FileReader fr=new FileReader("C:\\Users\\srikanth.ch\\workspace\\StockAccounting\\CaptureData\\data.txt");
		BufferedReader br=new BufferedReader(fr);
		String exp_data=br.readLine();
	//Converting String value into integer
		int columNum=Integer.parseInt(column);
	
		if (driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.Panel"))).isDisplayed()) 
		{
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.Panel"))).click();
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.Box"))).click();
		driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Box"))).clear();
		driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Box"))).sendKeys(exp_data);
		driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Button"))).click();
		
		}else
		{
		driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Box"))).clear();
		driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Box"))).sendKeys(exp_data);
		driver.findElement(By.id(PropertyFileUtil.getValueForKey("Search.Button"))).click();
		}
	//identifying Suppliers details WebTable
		WebElement webTable=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable")));
		//row count
		List<WebElement> rows=webTable.findElements(By.tagName("tr"));
		for (int i =1; i <=rows.size(); i++) 
		{
			//System.out.println("hai validation");
			String act_data=driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/form/div//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+columNum+"]/div/span")).getText();	
			System.out.println(act_data);
			//validation
			Assert.assertEquals(exp_data, act_data);
			break;
		}

	}
	
	
	
	
}
