package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Central_Inventory_Add_Edit_Central_WareHouse {
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Central_Inventory_Add_Edit_Central_WareHouse");

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
			Central_WareHouse_openpage(driver);
			Central_WareHouse_Creation(driver);
			Central_WareHouse_Edit(driver);
			Central_WareHouse_Dashboard(driver); 
			Central_warehouse_Reports(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=2)
	public void Central_WareHouse_openpage(WebDriver driver) throws Exception
	{
		
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
/*		Thread.sleep(15000);
		//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();
 
		Thread.sleep(3000);
        //Click the Central Inventory Reports Option		
		driver.findElement(By.xpath("//span[.='Central Inventory']")).click();
		
		Thread.sleep(2000);
		//Click the Central WareHouse option
		driver.findElement(By.xpath("//span[.='Central WareHouse']")).click();*/
		//Enter the URL for Central Warehouse
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"centralStores/centralWarehouse");
		
		Thread.sleep(5000);
		try
		{
		//Check Central Warehouse page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1398, 1))).getText().equalsIgnoreCase("Central Warehouse"))
		{
			test.log(LogStatus.PASS, "Central Warehouse page loaded Successfully");
			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Central Warehouse page loaded Failed");
			
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
	
	@Test(enabled=false,priority=3)
	public void Central_WareHouse_Creation(WebDriver driver) throws Exception
	{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
		Thread.sleep(2000);
		//Click the Central Warehouse creation button 
		driver.findElement(By.cssSelector(excel.getData(3, 1399, 4))).click();
			
		Thread.sleep(2000);
		//Check New Central WareHouse page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1785, 1))).getText().equalsIgnoreCase("Central Ware House"))
		{
			test.log(LogStatus.PASS, "New Central WareHouse page loaded Successfully");
		}
		else 
		{
			test.log(LogStatus.FAIL, "New Central WareHouse page loaded Failed");
		}	
		Thread.sleep(2000);
		 //Click the New Central WareHouse name
		driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
		Thread.sleep(2000);
		//Enter the New Central WareHouse name  
		driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("New_Central_WareHouse"));	  
		
		Thread.sleep(2000);
		//Click the description
		driver.findElement(By.name(excel.getData(3, 1174, 3))).clear(); 
		Thread.sleep(2000);
		//Enter the description 
		driver.findElement(By.name(excel.getData(3, 1174, 3))).sendKeys("Central place");	  
			
		Thread.sleep(2000);
		//Click the phoneNumber 
		driver.findElement(By.name(excel.getData(3, 286, 3))).clear();
		Thread.sleep(2000);
		//Enter the phoneNumber	    
		driver.findElement(By.name(excel.getData(3, 286, 3))).sendKeys(Utility.getProperty("Central_phoneNumber"));	  
		
			 
		Thread.sleep(2000);
		//Click the emailId 
		driver.findElement(By.name(excel.getData(3, 287, 3))).clear();
		Thread.sleep(2000);
		//Enter the emailId
		driver.findElement(By.name(excel.getData(3, 287, 3))).sendKeys(Utility.getProperty("Central_emailId"));	   
		
		Thread.sleep(4000);
		//Click the addressLine1 
		driver.findElement(By.name(excel.getData(3, 288, 3))).clear();
		Thread.sleep(2000);
		//Enter the addressLine1
		driver.findElement(By.name(excel.getData(3, 288, 3))).sendKeys(Utility.getProperty("Settings_Store_Information_Address1"));	  
		
		Thread.sleep(4000);
		//Enable existing store option
		driver.findElement(By.xpath(excel.getData(3, 1410, 1))).click();
     
		Thread.sleep(2000);
		//Click the Copy data from Option
		driver.findElement(By.xpath(excel.getData(3, 1411, 1))).click();
		//Enter the Copy data from
		//driver.findElement(By.xpath(excel.getData(3, 1412, 1))).sendKeys("LINGABO");
		//Click the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1412, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Time Zone Option
		driver.findElement(By.xpath(excel.getData(3, 1413, 1))).click();
		//Enter the required Time Zone
		driver.findElement(By.xpath(excel.getData(3, 1414, 1))).sendKeys(Utility.getProperty("Settings_Store_Information_TimeZone"));
		//Click the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1414, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Clear the address2 field
		driver.findElement(By.name(excel.getData(3, 289, 3))).clear();
		//Enter the address2
		driver.findElement(By.name(excel.getData(3, 289, 3))).sendKeys(Utility.getProperty("Settings_Store_Information_Address2"));
		
		Thread.sleep(1000);
		//Clear the City field
		driver.findElement(By.name(excel.getData(3, 290, 3))).clear();
		//Enter the City
		driver.findElement(By.name(excel.getData(3, 290, 3))).sendKeys(Utility.getProperty("Settings_Store_Information_City"));
		
		Thread.sleep(1000);
		//Clear the State field
		driver.findElement(By.name(excel.getData(3, 291, 3))).clear();
		//Enter the State
		driver.findElement(By.name(excel.getData(3, 291, 3))).sendKeys(Utility.getProperty("Settings_Store_Information_State"));
		
		Thread.sleep(1000);
		//Clear the Zip Code Field
		driver.findElement(By.name(excel.getData(3, 292, 3))).clear();
		//Enter the Zipcode
		driver.findElement(By.name(excel.getData(3, 292, 3))).sendKeys(Utility.getProperty("Settings_Store_Information_Zipcode"));
		
		
		Thread.sleep(3000);
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(3, 1401, 1))).click();  
		
		WebElement editCK=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,60);  
		    
		//Check New Central WareHouse page opened or not																			
		if(wait.until(ExpectedConditions.visibilityOf(editCK)).getText().equalsIgnoreCase("Central Warehouse Saved successfully"))
		{
			test.log(LogStatus.PASS, " Central Warehouse Saved successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, " Central Warehouse Saved is Failed");
		}
		wb.close();
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=4)
	public void Central_WareHouse_Edit(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(4000);
		//Click refresh button
		driver.findElement(By.xpath(excel.getData(3, 1786, 1))).click();

		Thread.sleep(4000);
		//Click the Central Warehouse creation button 
		List<WebElement> max = driver.findElements(By.xpath(excel.getData(3, 1402, 1)));
		max.size();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@class='list-inline-centralEntity']/li["+max.size()+"]/div[2]/div[1]")).click();
		

		Thread.sleep(4000);
		//Check New Central WareHouse page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1785, 1))).getText().equalsIgnoreCase("Central Ware House"))
		{
			test.log(LogStatus.PASS, "Edit Central WareHouse page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Edit Central WareHouse page loaded Failed");
		}	
		Thread.sleep(4000);
		 //Click the New Central WareHouse name
		driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
		Thread.sleep(2000);
		//Enter the New Central WareHouse name  
		driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("New_Central_WareHouse")+1);	  
		
		Thread.sleep(4000);
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(3, 1401, 1))).click();
		
		WebElement editCK=driver.findElement(By.xpath(excel.getData(3, 576, 1)));
		WebDriverWait wait=new WebDriverWait(driver,60);  
		  
		//Check Edit Central WareHouse page opened or not		
		if(wait.until(ExpectedConditions.visibilityOf(editCK)).getText().equalsIgnoreCase("Central Warehouse updated successfully"))
		{
			test.log(LogStatus.PASS, "Edited Central Warehouse Saved successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Edited Central Warehouse Saved Failed");
		}
		wb.close();
		Thread.sleep(3000);  
	}	
	
	@Test(enabled=false,priority=5)
	public void Central_WareHouse_Dashboard(WebDriver driver) throws Exception
	{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(4000);
		//Click refresh button
		driver.findElement(By.xpath(excel.getData(3, 1405, 1))).click();
		Thread.sleep(4000);
		//Click the store 
		driver.findElement(By.xpath(excel.getData(3, 1406, 1))).click();
		
		{
			Thread.sleep(4000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(3, 1155, 1))).click();
			Thread.sleep(4000);
			//Check Inventory Home page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1156, 1))).getText().equalsIgnoreCase("Live Updates"))
			{
				test.log(LogStatus.PASS, "Central Inventory Home page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Central Inventory Home page refreshed Failed");
			}	
		}
		 wb.close();
	}
		
	@Test(enabled=false,priority=6)
	public void Central_warehouse_Reports(WebDriver driver) throws Exception
	{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
		Thread.sleep(4000);
			//Check the Total Value On Hand value
			if(driver.findElement(By.xpath(excel.getData(3, 1782, 1))).getText().equalsIgnoreCase("Total Value On Hand"))
			{
				test.log(LogStatus.PASS, "Total Value On Hand report is available");
				
				test.log(LogStatus.INFO, "Total Value On Hand amount is : "+driver.findElement(By.xpath(excel.getData(3, 1783, 1))).getText());
				
			}
		
			else
			{
				test.log(LogStatus.FAIL, "Total Value On Hand report is not available");
			}
			
			String lsvalue = driver.findElement(By.xpath(excel.getData(3, 1784, 1))).getText();
			double lowStackValue = Double.parseDouble(lsvalue);
			if(lowStackValue == 0)
			{
				test.log(LogStatus.PASS, "Low Stocks report is not available");
			}
			else
			{
				test.log(LogStatus.PASS, "Low Stocks report is available");
				
				test.log(LogStatus.INFO, "Total Low Stocks value is : "+driver.findElement(By.xpath(excel.getData(3, 1784, 1))).getText());

				//Click the Low Stack
				driver.findElement(By.xpath(excel.getData(3, 1337, 1))).click();
			
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1656, 1)));
				
				for(int i = 1; i <= rows.size(); i++)
				{
					
					test.log(LogStatus.INFO, driver.findElement(By.xpath("//div[@class='panel-collapse in collapse']/div/div[2]/div[1]/div/table/tbody/tr["+i+"]/td[1]")).getText()+" available quantity is "+driver.findElement(By.xpath("//div[@class='panel-collapse in collapse']/div/div[2]/div[1]/div/table/tbody/tr["+i+"]/td[3]")).getText()+" but the minimum quantity is "+driver.findElement(By.xpath("//div[@class='panel-collapse in collapse']/div/div[2]/div[1]/div/table/tbody/tr["+i+"]/td[2]")).getText());
 				
				}
			}  wb.close();
			
			Thread.sleep(3000);	
		//Click the Central WareHouse
		//driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[2]/a/span[1]")).click();
		Thread.sleep(3000);		
		
		//Click the Central WareHouse
		//driver.findElement(By.xpath("//span[contains(.,'Central Kitchen')]")).click();

		Thread.sleep(4000);		
		}

}
