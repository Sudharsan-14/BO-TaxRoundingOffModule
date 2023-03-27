package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;


public class Settings_KDS_Configuration {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("KDS_Configuration");
	
	float unknownValue = 00;
	
		@AfterMethod
		public void tearDown(ITestResult result)
		{
			if(ITestResult.FAILURE == result.getStatus())
			{
				Utility.captureScreenshot(driver, result.getName());
			}
		}
			
		@AfterClass
		public void flushTest() throws Exception
		{
			Thread.sleep(2000);
			rep.endTest(test);
			rep.flush();
		}

		@Test(priority=1)
		public void login() throws Exception
		{
			Thread.sleep(2000);
			//Call the chrome driver
			System.setProperty("webdriver.chrome.driver",Utility.getProperty("Chrome_Driver_Path"));
			//Open the Chrome window
			driver = new ChromeDriver();
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//Maximize the Chrome window
			driver.manage().window().maximize();
			Thread.sleep(1000);
			//Launch the URL
			driver.get(Utility.getProperty("appURL"));
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
			{
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.UPOS_login(driver, test);
			}
			else 			
			{
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.Linga_login(driver, test);
			}	
		}
			
		@Test(priority=500)
		public void logout() throws Exception
		{		Browser_Account_Level_User a = new Browser_Account_Level_User();
		a.Logout(driver, test);
		}
		@Test(priority = 100)
		public void calling() throws Exception
		{
			Thread.sleep(10000);
			try
			{
					Thread.sleep(1000);System.out.println("Minimize Chat Icon");
					driver.findElement(By.id("zsiq_minimize")).click();			
			}
			catch(Exception e)
			{
				System.out.println("Minimize Chat Icon Missing");
			}
			//ViewDashBoardReports a = new ViewDashBoardReports();
			Thread.sleep(1000);
			KDS_Configuration_Open_Page(driver);
			KDS_Configuration_update_KDS_Configuration(driver);
			Verify_KDS_Configuration_method_update_KDS_Configuration(driver);
					Thread.sleep(1500);
		}

	@Test(priority=3, enabled=false)
	public void watchTutorial(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	 
		Thread.sleep(2000);
		//Click the Watch Tutorial Option
		driver.findElement(By.xpath(excel.getData(3, 47, 1))).click();
		WebElement iframe = driver.findElement(By.xpath(excel.getData(3, 48, 1)));
		driver.switchTo().frame(iframe);
		Thread.sleep(3500);
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 49, 1))).isDisplayed()&&driver.findElement(By.xpath("//button/div[.='Share']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Watch Tutorial Video Played Well");
				Thread.sleep(2500);
			}
		}
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Watch Tutorial Video not Played");
		}
		driver.switchTo().defaultContent();
		
		Thread.sleep(2000);
		//Click the Close button
		driver.findElement(By.xpath(excel.getData(3, 50, 1))).click();wb.close();
	}
	
	@Test(priority=5,enabled = false)
	public void KDS_Configuration_Open_Page(WebDriver driver) throws Exception
	{
       
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	 
/*		//Click the Settings option
		driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[5]/a/span[1]")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Printer Configuration']"));
		//Scroll the page till the Printer Configuration option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Store Option		
		driver.findElement(By.xpath("//span[.='Printer Configuration']")).click();*/
		
		for(int i = 0; i <= 10; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"newKitchenDisplaySystem");
		
		Thread.sleep(8000);
		try
		{
		//Check whether the KDS Configuration
		if(driver.findElement(By.xpath(excel.getData(3, 1867, 1))).getText().equalsIgnoreCase("KDS Configuration"))
		{
			test.log(LogStatus.PASS, "KDS Configuration page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "KDS Configuration page loaded Failed");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		}
		catch(Exception e)
		{
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.FAIL,test.addScreenCapture(s));
		}
		Thread.sleep(3000);wb.close();
	}	
		
	@Test(priority=6,enabled = false)
	public void KDS_Configuration_update_KDS_Configuration(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	 
		Thread.sleep(8000);
		//Check whether the Table Name Enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1868, 1))).isSelected())
		{
			
		}
		else
		{
			Thread.sleep(2000);
			//Enable Table Name Check box
			driver.findElement(By.xpath(excel.getData(3, 1868, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Check Number Enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1870, 1))).isSelected())
		{
					
		}
		else
		{
			Thread.sleep(2000);
			//Enable Check Number box
		driver.findElement(By.xpath(excel.getData(3, 1870, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Time Enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1871, 1))).isSelected())
		{
					
		}
		else
		{

			Thread.sleep(2000);
			//Enable Time box
		driver.findElement(By.xpath(excel.getData(3, 1871, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Server Name Enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1872, 1))).isSelected())
		{
					
		}
		else
		{
			
			Thread.sleep(2000);
			//Enable Server Name box
		driver.findElement(By.xpath(excel.getData(3, 1872, 1))).click();
		}
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		Thread.sleep(2000);
		//Check whether the Service Type Enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1873, 1))).isSelected())
		{
					
		}
		else
		{

			Thread.sleep(2000);
			//Enable Service Type box
		driver.findElement(By.xpath(excel.getData(3, 1873, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Seat Number Enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1874, 1))).isSelected())
		{
					
		}
		else
		{
			
			Thread.sleep(2000);
			//Enable Seat Number box
		driver.findElement(By.xpath(excel.getData(3, 1874, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Notes Enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1875, 1))).isSelected())
		{
					
		}
		else
		{
			
			Thread.sleep(2000);
			//Enable Notes box
		driver.findElement(By.xpath(excel.getData(3, 1875, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the KDS Done Button Enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1876, 1))).isSelected())
		{
					
		}
		else
		{
			
			Thread.sleep(2000);
			//Enable KDS Done button box
		driver.findElement(By.xpath(excel.getData(3, 1876, 1))).click();
		}
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		}
		
		
		Thread.sleep(2000);
		//Select Modifier Color
		WebElement  modifColor=driver.findElement(By.xpath(excel.getData(3, 1869, 1)));
		Select mColor=new Select(modifColor);
		mColor.selectByVisibleText("Wisteria");
	
		Thread.sleep(2000);
		//Select Addon Color
		WebElement  addonColor=driver.findElement(By.xpath(excel.getData(3, 1877, 1)));
		Select aColor=new Select(addonColor);
		aColor.selectByVisibleText("Wisteria");
		
		Thread.sleep(2000);
		//Select Check Status Color 1
		WebElement  chstColor=driver.findElement(By.xpath(excel.getData(3, 1878, 1)));
		Select chColor=new Select(chstColor);
		chColor.selectByVisibleText("Wisteria");

		Thread.sleep(2000);
		//Select Mins 1
		driver.findElement(By.xpath(excel.getData(3, 1879, 1))).clear();
		//Enter Mins 1
		driver.findElement(By.xpath(excel.getData(3, 1879, 1))).sendKeys("5");
		
		Thread.sleep(2000);
		//Select check status Color 2
		WebElement  chstColor2=driver.findElement(By.xpath(excel.getData(3, 1880, 1)));
		Select cColor2=new Select(chstColor2);
		cColor2.selectByVisibleText("Turquoise");
	
		Thread.sleep(2000);
		//Select Mins 2
		driver.findElement(By.xpath(excel.getData(3, 1881, 1))).clear();
		//Enter Mins 2
		driver.findElement(By.xpath(excel.getData(3, 1881, 1))).sendKeys("4");		
		
		
		Thread.sleep(2000);
		//Select Check Status Color 3
		WebElement  chstColor3=driver.findElement(By.xpath(excel.getData(3, 1882, 1)));
		Select cColor3=new Select(chstColor3);
		cColor3.selectByVisibleText("Alizarin");
	
		Thread.sleep(2000);
		//Select Mins 3
		driver.findElement(By.xpath(excel.getData(3, 1883, 1))).clear();
		//Enter Mins 3
		//Select Mins 2
		driver.findElement(By.xpath(excel.getData(3, 1883, 1))).sendKeys("10");
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
	
		Thread.sleep(2000);
		//Check whether the To Guest Button Enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1884, 1))).isSelected())
		{
			Thread.sleep(2000);
			//Clear Notification mesaage To Guest
			driver.findElement(By.xpath(excel.getData(3, 1885, 1))).clear();
			//Enter Notification mesaage To Guest
			driver.findElement(By.xpath(excel.getData(3, 1885, 1))).sendKeys("Dear Guest., Your Quick Order is Ready");
		}
		else
		{
			
			Thread.sleep(1000);
			//Enable To Guest button box
		driver.findElement(By.xpath(excel.getData(3, 1884, 1))).click();
		Thread.sleep(2000);
		//Clear Notification mesaage To Guest
		driver.findElement(By.xpath(excel.getData(3, 1885, 1))).clear();
		//Enter Notification mesaage To Guest
		driver.findElement(By.xpath(excel.getData(3, 1885, 1))).sendKeys("Dear Guest., Your Quick Order is Ready");
		}
	
		Thread.sleep(2000);
		//Check whether the To Server Button Enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1888, 1))).isSelected())
		{
			Thread.sleep(2000);
			//Clear Notification mesaage To Server
			driver.findElement(By.xpath(excel.getData(3, 1889, 1))).clear();
			//Enter Notification mesaage To Server
			driver.findElement(By.xpath(excel.getData(3, 1889, 1))).sendKeys("Hi Server., Your Quick Order ready to Serve");
		}
		else
		{
			Thread.sleep(1000);
		//Enable To Server (Quick Order)
		driver.findElement(By.xpath(excel.getData(3, 1888, 1))).click();
		Thread.sleep(2000);
		//Clear Notification mesaage To server
		driver.findElement(By.xpath(excel.getData(3, 1889, 1))).clear();
		//Enter Notification mesaage To server
		driver.findElement(By.xpath(excel.getData(3, 1889, 1))).sendKeys("Hi Server., Your Quick Order ready to Serve");
		}
		Thread.sleep(2000);
		//Check whether the To Guest Button Enabled or not (For Table Service)
		if(driver.findElement(By.xpath(excel.getData(3, 1892, 1))).isSelected())
		{
			Thread.sleep(2000);
			//Clear Notification mesaage To Guest (Table Service)
			driver.findElement(By.xpath(excel.getData(3, 1893, 1))).clear();
			//Enter Notification mesaage To Server
			driver.findElement(By.xpath(excel.getData(3, 1893, 1))).sendKeys("Dear Guest., Your Table Service Order is Ready");
		}
		else
		{
			
			Thread.sleep(1000);
			//Enable To guest (Table Service)
		driver.findElement(By.xpath(excel.getData(3, 1892, 1))).click();
		Thread.sleep(2000);
		//Clear Notification mesaage To Guest
		driver.findElement(By.xpath(excel.getData(3, 1893, 1))).clear();
		//Enter Notification mesaage To Guest
		driver.findElement(By.xpath(excel.getData(3, 1893, 1))).sendKeys("Dear Guest., Your Table service Order is Ready");
		}
		
		Thread.sleep(2000);
		//Check whether the To Server Button Enabled or not (Table service)
		if(driver.findElement(By.xpath(excel.getData(3, 2161, 1))).isSelected())
		{
			//Clear Notification mesaage To Server
			driver.findElement(By.xpath(excel.getData(3, 2162, 1))).clear();
			//Enter Notification mesaage To Server
			driver.findElement(By.xpath(excel.getData(3, 2162, 1))).sendKeys("Hi Server., Your Table Service Order ready to Serve");
		}
		else
		{
		//Enable To Server (Table Service Order)
		driver.findElement(By.xpath(excel.getData(3, 2161, 1))).click();
		Thread.sleep(2000);
		//Clear Notification mesaage To server
		driver.findElement(By.xpath(excel.getData(3, 2162, 1))).clear();
		//Enter Notification mesaage To server
		driver.findElement(By.xpath(excel.getData(3, 2162, 1))).sendKeys("Hi Server., Your Table Service Order ready to Serve");
		}

		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
/*
Thread.sleep(2000);
//Check whether the To Guest Button Enabled or not (For Online Order)
if(driver.findElement(By.xpath(excel.getData(3, 2161, 1))).isSelected())
{
	
	Thread.sleep(2000);
	//Clear Notification mesaage To Guest (Online Order)
	driver.findElement(By.xpath(excel.getData(3, 2162, 1))).clear();
	//Enter Notification mesaage To Server
	driver.findElement(By.xpath(excel.getData(3, 2162, 1))).sendKeys("Dear Guest., Your Online Order Order is Ready");
}
else
{
	
	Thread.sleep(2000);
	//Enable To guest (Online Order)
driver.findElement(By.xpath(excel.getData(3, 2161, 1))).click();
Thread.sleep(2000);
//Clear Notification mesaage To Guest
driver.findElement(By.xpath(excel.getData(3, 2162, 1))).clear();
//Enter Notification mesaage To Guest
driver.findElement(By.xpath(excel.getData(3, 2162, 1))).sendKeys("Dear Guest., Your Online Order is Ready");
}
*/
Thread.sleep(2000);
//Check whether the To ToGo Orders Button Enabled or not (For Online Order)
if(driver.findElement(By.xpath(excel.getData(3, 2163, 1))).isSelected())
{
	//Clear Notification mesaage To Server
	driver.findElement(By.xpath(excel.getData(3, 2164, 1))).clear();
	//Enter Notification mesaage To Server
	driver.findElement(By.xpath(excel.getData(3, 2164, 1))).sendKeys("Hi Server., Your Online Order ready to Serve");
}
else
{
//Enable To ToGo Orders (Online Order)
driver.findElement(By.xpath(excel.getData(3, 2163, 1))).click();
Thread.sleep(2000);
//Clear Notification mesaage To server
driver.findElement(By.xpath(excel.getData(3, 2164, 1))).clear();
//Enter Notification mesaage To server
driver.findElement(By.xpath(excel.getData(3, 2164, 1))).sendKeys("Hi Server., Your Online Order ready to Serve");
}

	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
	Thread.sleep(2000);
	//Check whether the Here Orders Button Enabled or not (For Online Order)
	if(driver.findElement(By.xpath(excel.getData(3, 2165, 1))).isSelected())
	{
		//Clear Notification mesaage To Server
		driver.findElement(By.xpath(excel.getData(3, 2166, 1))).clear();
		//Enter Notification mesaage To Server
		driver.findElement(By.xpath(excel.getData(3, 2166, 1))).sendKeys("Hi Server., Your Online Order ready to Serve");
	}
	else
	{
	//Enable To Here Orders (Online Order)
	driver.findElement(By.xpath(excel.getData(3, 2165, 1))).click();
	Thread.sleep(2000);
	//Clear Notification mesaage To server
	driver.findElement(By.xpath(excel.getData(3, 2166, 1))).clear();
	//Enter Notification mesaage To server
	driver.findElement(By.xpath(excel.getData(3, 2166, 1))).sendKeys("Hi Server., Your Online Order ready to Serve");
	}

	
	
	Thread.sleep(2000);
	//Click the Update Template button
	driver.findElement(By.xpath(excel.getData(3, 1896, 1))).click();
	
	//Check whether the Kitchen printer was saved or not
	WebElement ele1=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
	WebDriverWait wait1=new WebDriverWait(driver,150);
	
	if(wait1.until(ExpectedConditions.visibilityOf(ele1)).getText().equalsIgnoreCase("KDS Configuration saved successfully"))
	{
		test.log(LogStatus.PASS, "KDS Configuration Saved successfully");
	}
	else
	{
		test.log(LogStatus.FAIL, "KDS Configuration Saved fail");
	}
	Thread.sleep(5000);
	wb.close();

	}

	@Test(priority=7,enabled = false)
	public void Verify_KDS_Configuration_method_update_KDS_Configuration(WebDriver driver) throws Exception
	{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	 
		Thread.sleep(5000);		
		//Check whether the Table name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1868, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Table Name field is Enabled in KDS Configuration");
		}
		else
		{
			test.log(LogStatus.FAIL, "Table Name field is disabled in KDS Configuration");
		}
		
		Thread.sleep(1000);
		//Check whether the Check Number field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1870, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Check Number field is Enabled in KDS Configuration");
		}
		else
		{
			test.log(LogStatus.FAIL, "Check Number field is disabled in KDS Configuration");
		}
		
				
		Thread.sleep(1000);
		//Check whether the Time field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1871, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Time field is Enabled in KDS Configuration");
		}
		else
		{
			test.log(LogStatus.FAIL, "Time field is disabled in KDS Configuration");
		}
		
		Thread.sleep(1000);
		//Check whether the Server name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1872, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Server Name field is Enabled in KDS Configuration");
		}
		else
		{
			test.log(LogStatus.FAIL, "Server Name field is disabled in KDS Configuration");
		}
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		Thread.sleep(1000);
		//Check whether the Service Type field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1873, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Service Type field is Enabled in KDS Configuration");
		}
		else
		{
			
			test.log(LogStatus.FAIL, "Service Type field is disabled in KDS Configuration");
		}
		
		
		Thread.sleep(1000);
		//Check whether the Seat Number field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1874, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Seat Number field is Enabled in KDS Configuration");
		}
		else
		{
			test.log(LogStatus.FAIL, "Seat Number field is disabled in KDS Configuration");
		}
		
		
		Thread.sleep(1000);
		//Check whether the Notes field - is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1875, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Notes field is Enabled in KDS Configuration");
		}
		else
		{
			test.log(LogStatus.FAIL, "Notes field is disabled in KDS Configuration");
		}
		
		
		Thread.sleep(1000);
		//Check whether the KDS Done Button field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1876, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "KDS Done Button field is Enabled in KDS Configuration");
		}
		else
		{
			test.log(LogStatus.FAIL, "KDS Done Button field is disabled in KDS Configuration");
		}
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		
		Thread.sleep(1000);
		//Check whether the To Guest Button field is enabled or not (For Quick Order)
		if(driver.findElement(By.xpath(excel.getData(3, 1884, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "To Guest Quick Order Button field is Enabled in KDS Configuration");
		}
		else
		{
			test.log(LogStatus.FAIL, "To Guest Quick Order Button field is disabled in KDS Configuration");
		}
		
		//Check whether the To Server Quick Order Button field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1888, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "To Server Quick Order Button field is Enabled in KDS Configuration");
		}
		else
		{
			test.log(LogStatus.FAIL, "To Server Quick Order Button field is disabled in KDS Configuration");
		}
		Thread.sleep(1000);
		
		
		Thread.sleep(1000);
		//Check whether the To Guest Button field is enabled or not (For Table Service Order)
				if(driver.findElement(By.xpath(excel.getData(3, 1892, 1))).isSelected())
				{
					test.log(LogStatus.PASS, "To Guest Table Service Order Button field is Enabled in KDS Configuration");
				}
				else
				{
					test.log(LogStatus.FAIL, "To Guest Table Service Order Button field is disabled in KDS Configuration");
				}
				
				//Check whether the To Server Table Service Order Button field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 2161, 1))).isSelected())
				{
					test.log(LogStatus.PASS, "To Server Table Service Order Button field is Enabled in KDS Configuration");
				}
				else
				{
					test.log(LogStatus.FAIL, "To Server Table Service Order Button field is disabled in KDS Configuration");
				}
				Thread.sleep(1000);
				
				for(int i = 1; i <= 8; i++)
				{
					driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				}
				
				
				Thread.sleep(1000);
				//Check whether the To Guest Button field is enabled or not (For Online Order)
				if(driver.findElement(By.xpath(excel.getData(3, 2163, 1))).isSelected())
				{
					test.log(LogStatus.PASS, "To Guest Online Order Button field is Enabled in KDS Configuration");
				}
				else
				{
					test.log(LogStatus.FAIL, "To Guest Online Order Button field is disabled in KDS Configuration");
				}
				
				//Check whether the To Server Online Order Button field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 2165, 1))).isSelected())
				{
					test.log(LogStatus.PASS, "To Server Online Order Button field is Enabled in KDS Configuration");
				}
				else
				{
					test.log(LogStatus.FAIL, "To Server Online Order Button field is disabled in KDS Configuration");
				}
				Thread.sleep(3000);
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				
				Thread.sleep(5000);
				wb.close();
			}
}
