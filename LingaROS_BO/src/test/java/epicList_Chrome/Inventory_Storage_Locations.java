package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
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

public class Inventory_Storage_Locations {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Storage_Locations");

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
		//	SendMail.snedMailWithAttachment();
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
		a.Logout(driver, test);}
	
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
			Inventory_Storage_Locations_openpage(driver);
			Inventory_Storage_Locations_refresh(driver);
			Inventory_Storage_Locations_add_Invetory_Storage(driver); 
			Inventory_Storage_Locations_edit_Storage_Locations(driver);
			Inventory_Storage_Locations_delete_Invetory_Storage(driver);
			Inventory_Storage_Locations_closeButton(driver);
			Inventory_Storage_Locations_verifyActive_InActiveButton(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=12)
		public void Inventory_Storage_Locations_openpage(WebDriver driver) throws Exception
		{

            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"masters/storageLocation");

			Thread.sleep(4000);
			try
			{
			//Check Storage Locations page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1180, 1))).getText().equalsIgnoreCase("Storage Location"))
			{
				test.log(LogStatus.PASS, "Storage Locations page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Storage Locations page loaded Failed");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			}
			catch(Exception w)
			{
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.FAIL,test.addScreenCapture(s));
			}
			Thread.sleep(4000);wb.close();
			
		}
		
		@Test(enabled=false,priority=14)
		public void Inventory_Storage_Locations_refresh(WebDriver driver) throws Exception
		{
	             File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
			     FileInputStream fis = new FileInputStream(src);
				
				 XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				 XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(4000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(3, 1181, 1))).click();
			Thread.sleep(4000);
			//Check Storage Locations page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1182, 1))).getText().equalsIgnoreCase("Storage Location"))
			{
				test.log(LogStatus.PASS, "Storage Locations page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Storage Locations page refreshed Failed");
			}
			Thread.sleep(4000);wb.close();
	

		}

		@Test(enabled=false,priority=15)
		public void Inventory_Storage_Locations_add_Invetory_Storage(WebDriver driver) throws Exception
		{
			    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
			    FileInputStream fis = new FileInputStream(src);
				
			    XSSFWorkbook wb = new XSSFWorkbook(fis);
				
			    XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  //Scroll the web page
		    for(int i=1; i <= 20; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		    	Thread.sleep(1000);
		    } 
		    
			Thread.sleep(4000);
			//Click on the Add Storage Location option
			driver.findElement(By.id("storageLocation")).click();
			Thread.sleep(3000);
			
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1183, 1))).getText().equalsIgnoreCase("New Storage Location"))
			{
				test.log(LogStatus.PASS, "New Storage Location form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Storage Location form loaded Failed");
			}

			Thread.sleep(3000);
			
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Inventory_Storage_Locations"));
			Thread.sleep(2000);
			
			//Clear the description field
			driver.findElement(By.name(excel.getData(3, 1174, 3))).clear();
			//Enter the description
			driver.findElement(By.name(excel.getData(3, 1174, 3))).sendKeys("Inventory Storage Location");
			Thread.sleep(2000);
				
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			Thread.sleep(4000);
			//Check whether the new storage location saved or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("StorageLocation updated successfully!."))
			{
				test.log(LogStatus.PASS, "New Storage Location Saved Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Storage Location Save Failed");
			}
 
			Thread.sleep(4000);wb.close();
		}
		
		@Test(enabled=false,priority=17)
		public void Inventory_Storage_Locations_edit_Storage_Locations(WebDriver driver) throws Exception
		{
			
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
		    FileInputStream fis = new FileInputStream(src);
			
		    XSSFWorkbook wb = new XSSFWorkbook(fis);
			
		    XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(4000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			Thread.sleep(3000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Inventory_Storage_Locations"));
			Thread.sleep(4000);
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
			
			Thread.sleep(4000);
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Inventory_Storage_Locations")+"1");
			Thread.sleep(4000);
			//Click the update
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			Thread.sleep(4000);
			//Check whether the storage locations updated successfully or not
			if(driver.findElement(By.xpath(excel.getData(3, 1184, 1))).getText().equalsIgnoreCase("StorageLocation updated successfully!."))
			{
				test.log(LogStatus.PASS, "New Storage Location updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Storage Location updated Failed");
			}

			Thread.sleep(4000);wb.close();

		}
		
		@Test(enabled=false,priority=18)
		public void Inventory_Storage_Locations_delete_Invetory_Storage(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
		    FileInputStream fis = new FileInputStream(src);
			
		    XSSFWorkbook wb = new XSSFWorkbook(fis);
			
		    XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Inventory_Storage_Locations")+"1");
			Thread.sleep(5000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).click();
			Thread.sleep(3000);
			//Click the Yes button in the popup
			driver.findElement(By.xpath(excel.getData(3, 545, 1))).click();
			
			WebElement up=driver.findElement(By.xpath(excel.getData(3, 39,1)));
			WebDriverWait wait=new WebDriverWait(driver,50);
			//Check whether the Inventory Storage Location Deleted successfully or not
			if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Storage Location InActivated successfully!."))
			{
				test.log(LogStatus.PASS, "New Storage Location deleted Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Storage Location deleted Failed");
			}

			Thread.sleep(4000);
		 

			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(3, 1185, 1))).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector(excel.getData(3, 209, 4))).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			driver.findElement(By.linkText("Yes")).click();
			Thread.sleep(3000);
			
			//Check the Storage Location activated or not
			if(driver.findElement(By.xpath(excel.getData(3, 1184, 1))).getText().equalsIgnoreCase("Storage Location Activated successfully"))
			{
				test.log(LogStatus.PASS, "Storage Location is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Storage Location is activated Failed");
			}
			
			Thread.sleep(4000);wb.close();
		}

		@Test(enabled=false,priority=19)
		public void Inventory_Storage_Locations_closeButton(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
		    FileInputStream fis = new FileInputStream(src);
			
		    XSSFWorkbook wb = new XSSFWorkbook(fis);
			
		    XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(4000);
			//Click on the Add storage locations option
			driver.findElement(By.id("storageLocation")).click();
			Thread.sleep(3000);
			
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1183, 1))).getText().equalsIgnoreCase("New Storage Location"))
			{
				test.log(LogStatus.PASS, "New Storage Location form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Storage Location form loaded Failed");
			}

			Thread.sleep(3000);
			
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Inventory_Storage_Locations"));
			Thread.sleep(2000);
				
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(3, 45, 1))).click();
			
			Thread.sleep(3000);
			//Check whether the new Storage Location canceled or not
			if(driver.findElement(By.id("storageLocation")).isDisplayed())
			{
				test.log(LogStatus.PASS, "New Storage Location Canceled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Storage Location Canceled Failed");
			}

			Thread.sleep(4000);wb.close();
		}
				
		@Test(enabled=false,priority=20)
		public void Inventory_Storage_Locations_verifyActive_InActiveButton(WebDriver driver) throws Exception
		{
			    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
			    FileInputStream fis = new FileInputStream(src);
				
			    XSSFWorkbook wb = new XSSFWorkbook(fis);
				
			    XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			
			Thread.sleep(4000);
			//Click Active or In Active Button
			driver.findElement(By.xpath(excel.getData(3, 1185, 1))).click();

			Thread.sleep(4000);
			//Check the Inactive page is loaded or not
			if(driver.findElement(By.cssSelector(excel.getData(3, 209, 4))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Deleted storage locations are here, we are in InActive Page");
				Thread.sleep(10000);
				//Click Active or In Active Button
				driver.findElement(By.xpath(excel.getData(3, 1185, 1))).click();

			}
			else if(driver.findElement(By.cssSelector(excel.getData(3, 244, 4))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Deleted storage locations are not here, we are in Active Page");
			}
			Thread.sleep(4000);
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);//watchTutorial(driver);
			Thread.sleep(5000);wb.close();}
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
}
