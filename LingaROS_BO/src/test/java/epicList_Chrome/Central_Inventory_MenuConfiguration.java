package epicList_Chrome;

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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Central_Inventory_MenuConfiguration {
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Central_Inventory_MenuConfiguration");

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
				Browser a = new Browser();
				a.UPOS_login(driver, test);
			}
			else 			
			{
				Browser a = new Browser();
				a.Linga_login(driver, test);
			}	
		}
			
		@Test(priority=500)
		public void logout() throws Exception
		{		Browser a = new Browser();
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
			Inventory_MenuConfiguration_openpage(driver);
			Inventory_MenuConfiguration_Store_option(driver);
			Inventory_MenuConfiguration_Centralwarehouse_option(driver);
			Inventory_MenuConfiguration_CentralKitchen_option(driver);
			Inventory_MenuConfig_Store_To_Centralware(driver);
			Inventory_MenuConfig_Centralware_To_Centralkitchen(driver);
			Inventory_MenuConfig_CentralKitchen_To_Store(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=12)
	public void Inventory_MenuConfiguration_openpage(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		/*//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();
 
		Thread.sleep(3000);
        //Click the Central Inventory Reports Option		
		driver.findElement(By.xpath("//span[.='Central Inventory']")).click();
		
		Thread.sleep(2000);
		//Click the Central WareHouse option
		driver.findElement(By.xpath("//span[contains(.,'Menu Configuration ')]")).click();
*/ 
		Thread.sleep(3000);	
		//Enter the Coursing URL
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"centralStores/menuConfig");
		
		Thread.sleep(5000);
		try
		{
		//Check Central Warehouse page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1372, 1))).getText().equalsIgnoreCase("Menu Configuration"))
		{
			test.log(LogStatus.PASS, "Central Menu Configuration page loaded Successfully");
			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Central Menu Configuration page loaded Failed");
			
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
		wb.close();
		Thread.sleep(3000);
		
	}
	
	@Test(enabled=false,priority=13)
	public void Inventory_MenuConfiguration_Store_option(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
		Thread.sleep(2000);
		//Click the From Store
		driver.findElement(By.xpath(excel.getData(3, 1373, 1))).click();
		//Enter the From Store
		driver.findElement(By.xpath(excel.getData(3, 1374, 1))).sendKeys("Stores");
		//Send the From Store
		driver.findElement(By.xpath(excel.getData(3, 1374, 1))).sendKeys(Keys.ENTER);
							
		Thread.sleep(2000);
		//Click the Select Store
		driver.findElement(By.xpath(excel.getData(3, 1375, 1))).click();
		//Enter the Required Store
		//driver.findElement(By.xpath(excel.getData(3, 1376, 1))).sendKeys(Utility.getProperty("Inventory_Menuconfic_From_Store"));
		//Send the selected Store
		driver.findElement(By.xpath(excel.getData(3, 1376, 1))).sendKeys(Keys.ENTER);
				
		Thread.sleep(2000);
		//Click the To Store
		driver.findElement(By.xpath(excel.getData(3, 1377, 1))).click();
		//Enter the To Store
		driver.findElement(By.xpath(excel.getData(3, 1378, 1))).sendKeys("Stores");
		//Send the To Store
		driver.findElement(By.xpath(excel.getData(3, 1378, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Select Stores for To field
		driver.findElement(By.xpath(excel.getData(3, 1379, 1))).click();
		//Enter the Select Stores for To field
		driver.findElement(By.xpath(excel.getData(3, 1380, 1))).sendKeys(Keys.ARROW_DOWN);
		//Send the Select Stores for To field
		driver.findElement(By.xpath(excel.getData(3, 1380, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);
		//Click the Pull Menu Configuration button
		//driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
	    driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
	  
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		//Check Central Warehouse page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Data pulled successfully"))
		{
			test.log(LogStatus.PASS, "Data pulled successfully for store");
		}
		else
		{
			test.log(LogStatus.FAIL, "Data pulled Failed for store");
		}
		wb.close();
		Thread.sleep(5000);	
	}
	
	@Test(enabled=false,priority=14)
	public void Inventory_MenuConfiguration_Centralwarehouse_option(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
		Thread.sleep(2000);
		//Click the From Central warehouse
		driver.findElement(By.xpath(excel.getData(3, 1373, 1))).click();
		//Enter the From Store
		driver.findElement(By.xpath(excel.getData(3, 1374, 1))).sendKeys("Central WareHouse");
		//Send the From Store
		driver.findElement(By.xpath(excel.getData(3, 1374, 1))).sendKeys(Keys.ENTER);
							
		Thread.sleep(2000);
		//Click the Select Central warehouse
		driver.findElement(By.xpath(excel.getData(3, 1375, 1))).click();
		//Enter the Required Store
		//driver.findElement(By.xpath(excel.getData(3, 1376, 1))).sendKeys(Utility.getProperty("Inventory_Menuconfic_From_CentralWareHouse"));
		//Send the selected Store
		driver.findElement(By.xpath(excel.getData(3, 1376, 1))).sendKeys(Keys.ENTER);
				
		Thread.sleep(2000);
		//Click the To Central warehouse
		driver.findElement(By.xpath(excel.getData(3, 1377, 1))).click();
		//Enter the To Store
		driver.findElement(By.xpath(excel.getData(3, 1378, 1))).sendKeys("Central WareHouse");
		//Send the To Store
		driver.findElement(By.xpath(excel.getData(3, 1378, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Select Stores for To field
		driver.findElement(By.xpath(excel.getData(3, 1379, 1))).click();
		//Enter the Select Stores for To field
		driver.findElement(By.xpath(excel.getData(3, 1380, 1))).sendKeys(Keys.ARROW_DOWN);
		//Send the Select Stores for To field
		driver.findElement(By.xpath(excel.getData(3, 1380, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Pull Menu Configuration button
		driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		
		//Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);Thread.sleep(2500);
		//Check Central Warehouse page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Data pulled successfully"))
		{
			test.log(LogStatus.PASS, "Data pulled successfully for Central warehouse");
		}
		else
		{
			test.log(LogStatus.FAIL, "Data pulled Failed for Central warehouse");
		}
		wb.close();
		Thread.sleep(5000);	
	}
				
	@Test(enabled=false,priority=15)
	public void Inventory_MenuConfiguration_CentralKitchen_option(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
		Thread.sleep(2000);
		//Click the From Central Kitchen
		driver.findElement(By.xpath(excel.getData(3, 1373, 1))).click();
		//Enter the From Store
		driver.findElement(By.xpath(excel.getData(3, 1374, 1))).sendKeys("Central Kitchen");
		//Send the From Store
		driver.findElement(By.xpath(excel.getData(3, 1374, 1))).sendKeys(Keys.ENTER);
							
		Thread.sleep(2000);
		//Click the Select Central Kitchen
		driver.findElement(By.xpath(excel.getData(3, 1375, 1))).click();
		//Enter the Required Store
		//driver.findElement(By.xpath(excel.getData(3, 1376, 1))).sendKeys(Utility.getProperty("Inventory_Menuconfic_From_CentralKitchen"));
		//Send the selected Store
		driver.findElement(By.xpath(excel.getData(3, 1376, 1))).sendKeys(Keys.ENTER);
				
		Thread.sleep(2000);
		//Click the To Central Kitchen
		driver.findElement(By.xpath(excel.getData(3, 1377, 1))).click();
		//Enter the To Store
		driver.findElement(By.xpath(excel.getData(3, 1378, 1))).sendKeys("Central Kitchen");
		//Send the To Store
		driver.findElement(By.xpath(excel.getData(3, 1378, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Select Stores for To field
		driver.findElement(By.xpath(excel.getData(3, 1379, 1))).click();
		//Enter the Select Stores for To field
		driver.findElement(By.xpath(excel.getData(3, 1380, 1))).sendKeys(Keys.ARROW_DOWN);
		//Send the Select Stores for To field
		driver.findElement(By.xpath(excel.getData(3, 1380, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Pull Menu Configuration button
		driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		
		//Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);Thread.sleep(2500);
		//Check Central Warehouse page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Data pulled successfully"))
		{
			test.log(LogStatus.PASS, "Data pulled successfully for Central Kitchen");
		}
		else
		{
			test.log(LogStatus.FAIL, "Data pulled Failed for Central Kitchen");
		}
		wb.close();
		Thread.sleep(5000);	
	}	
	
	@Test(enabled=false,priority=16)
	public void Inventory_MenuConfig_Store_To_Centralware(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
		Thread.sleep(2000);
		//Click the From Store
		driver.findElement(By.xpath(excel.getData(3, 1373, 1))).click();
		//Enter the From Store
		driver.findElement(By.xpath(excel.getData(3, 1374, 1))).sendKeys("Stores");
		//Send the From Store
		driver.findElement(By.xpath(excel.getData(3, 1374, 1))).sendKeys(Keys.ENTER);
							
		Thread.sleep(2000);
		//Click the Select Store
		driver.findElement(By.xpath(excel.getData(3, 1375, 1))).click();
		//Enter the Required Store
		//driver.findElement(By.xpath(excel.getData(3, 1376, 1))).sendKeys(Utility.getProperty("Inventory_Menuconfic_From_Store"));
		//Send the selected Store
		driver.findElement(By.xpath(excel.getData(3, 1376, 1))).sendKeys(Keys.ENTER);
				
		Thread.sleep(2000);
		//Click the To Store
		driver.findElement(By.xpath(excel.getData(3, 1377, 1))).click();
		//Enter the To Store
		driver.findElement(By.xpath(excel.getData(3, 1378, 1))).sendKeys("Central WareHouse");
		//Send the To Store
		driver.findElement(By.xpath(excel.getData(3, 1378, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Select Stores for To field
		driver.findElement(By.xpath(excel.getData(3, 1379, 1))).click();
		//Enter the Select Stores for To field
		//driver.findElement(By.xpath(excel.getData(3, 1380, 1))).sendKeys(Utility.getProperty("Inventory_Menuconfic_To_CentralWareHouse"));
		//Send the Select Stores for To field
		driver.findElement(By.xpath(excel.getData(3, 1380, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Pull Menu Configuration button
		driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		//Check Central Warehouse page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Data pulled successfully"))
		{
			test.log(LogStatus.PASS, "Data pulled successfully for Store To Centralware");
		}
		else
		{
			test.log(LogStatus.FAIL, "Data pulled Failed for Store To Centralware");
		}
		wb.close();
		Thread.sleep(5000);	
	}
	
	@Test(enabled=false,priority=17)
	public void Inventory_MenuConfig_Centralware_To_Centralkitchen(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
		Thread.sleep(2000);
		//Click the From Central warehouse
		driver.findElement(By.xpath(excel.getData(3, 1373, 1))).click();
		//Enter the From Store
		driver.findElement(By.xpath(excel.getData(3, 1374, 1))).sendKeys("Central WareHouse");
		//Send the From Store
		driver.findElement(By.xpath(excel.getData(3, 1374, 1))).sendKeys(Keys.ENTER);
							
		Thread.sleep(2000);
		//Click the Select Central warehouse
		driver.findElement(By.xpath(excel.getData(3, 1375, 1))).click();
		//Enter the Required Store
		//driver.findElement(By.xpath(excel.getData(3, 1376, 1))).sendKeys(Utility.getProperty("Inventory_Menuconfic_From_CentralWareHouse"));
		//Send the selected Store
		driver.findElement(By.xpath(excel.getData(3, 1376, 1))).sendKeys(Keys.ENTER);
				
		Thread.sleep(2000);
		//Click the To Central warehouse
		driver.findElement(By.xpath(excel.getData(3, 1377, 1))).click();
		//Enter the To Store
		driver.findElement(By.xpath(excel.getData(3, 1378, 1))).sendKeys("Central Kitchen");
		//Send the To Store
		driver.findElement(By.xpath(excel.getData(3, 1378, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Select Stores for To field
		driver.findElement(By.xpath(excel.getData(3, 1379, 1))).click();
		//Enter the Select Stores for To field
		//driver.findElement(By.xpath(excel.getData(3, 1380, 1))).sendKeys(Utility.getProperty("Inventory_Menuconfic_To_CentralKitchen"));
		//Send the Select Stores for To field
		driver.findElement(By.xpath(excel.getData(3, 1380, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Pull Menu Configuration button
		driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		
		//Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);Thread.sleep(2500);
		//Check Central Warehouse page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Data pulled successfully"))
		{
			test.log(LogStatus.PASS, "Data pulled successfully for Centralware To Centralkitchen");
		}
		else
		{
			test.log(LogStatus.FAIL, "Data pulled Failed for Centralware To Centralkitchen");
		}
		wb.close();
		Thread.sleep(5000);	
	}
	
	@Test(enabled=false,priority=18)
	public void Inventory_MenuConfig_CentralKitchen_To_Store(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
		Thread.sleep(2000);
		//Click the From Central Kitchen
		driver.findElement(By.xpath(excel.getData(3, 1373, 1))).click();
		//Enter the From Store
		driver.findElement(By.xpath(excel.getData(3, 1374, 1))).sendKeys("Central Kitchen");
		//Send the From Store
		driver.findElement(By.xpath(excel.getData(3, 1374, 1))).sendKeys(Keys.ENTER);
							
		Thread.sleep(2000);
		//Click the Select Central Kitchen
		driver.findElement(By.xpath(excel.getData(3, 1375, 1))).click();
		//Enter the Required Store
		//driver.findElement(By.xpath(excel.getData(3, 1376, 1))).sendKeys(Utility.getProperty("Inventory_Menuconfic_From_CentralKitchen"));
		//Send the selected Store
		driver.findElement(By.xpath(excel.getData(3, 1376, 1))).sendKeys(Keys.ENTER);
				
		Thread.sleep(2000);
		//Click the To Central Kitchen
		driver.findElement(By.xpath(excel.getData(3, 1377, 1))).click();
		//Enter the To Store
		driver.findElement(By.xpath(excel.getData(3, 1378, 1))).sendKeys("Stores");
		//Send the To Store
		driver.findElement(By.xpath(excel.getData(3, 1378, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Select Stores for To field
		driver.findElement(By.xpath(excel.getData(3, 1379, 1))).click();
		//Enter the Select Stores for To field
		//driver.findElement(By.xpath(excel.getData(3, 1380, 1))).sendKeys(Utility.getProperty("Inventory_Menuconfic_To_Store"));
		//Send the Select Stores for To field
		driver.findElement(By.xpath(excel.getData(3, 1380, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Pull Menu Configuration button
		driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		//Check Central Warehouse page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Data pulled successfully"))
		{
			test.log(LogStatus.PASS, "Data pulled successfully for CentralKitchen To Store");
		}
		else
		{
			test.log(LogStatus.FAIL, "Data pulled Failed for CentralKitchen To Store");
		}
		wb.close();
		Thread.sleep(5000);	
	} 

}
