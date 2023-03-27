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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

//import newReadExcelFile_New.ExcelDataConfig;



public class AddEditDelete_HouseAccounts {

	public WebDriver driver;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete_HouseAccounts");
		
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
			HouseAccount_Method_OpenPage(driver);
			HouseAccount_Method_refresh(driver);
			HouseAccount_Method_CreateNewHouseAccounts(driver);
			HouseAccount_Method_addHouseAccountsfor_Daily(driver);
			HouseAccount_Method_EditHouseAccountsfor_Weekly(driver);
			HouseAccount_Method_EditHouseAccountsfor_Monthly(driver);
			HouseAccount_Method_deleteHouseAccounts(driver);
			HouseAccount_Method_DeActivestatus(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=3)
		public void HouseAccount_Method_OpenPage(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		   
			/*//Click the Customers option
			driver.findElement(By.xpath("//span[.='Customers']")).click();
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='House Account']"));
			//Scroll the page till the House Account option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
		    //Click the House Account Option		
			driver.findElement(By.xpath("//span[.='House Account']")).click();  */
			
			//Enter the URL 
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"houseAccount");
			Thread.sleep(8000);
			
			try
			{
			//Check House Account page opened or not
			if(driver.findElement(By.xpath(excel.getData(5, 129, 1))).getText().equalsIgnoreCase("House Account"))
			{
				test.log(LogStatus.PASS,"House Account page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL,"House Account page loaded Failed");wb.close();
				
				String scnShot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s1="data:image/png;base64,"+scnShot1;
				test.log(LogStatus.INFO,test.addScreenCapture(s1));
			}
			}
			catch(Exception e)
			{
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.FAIL,test.addScreenCapture(s));
			}
		}

		@Test(enabled=false,priority=4)
		public void HouseAccount_Method_refresh(WebDriver driver) throws Exception
		{
           File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		   
			Thread.sleep(2000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(5, 148, 1))).click();
			Thread.sleep(2000);
			//Check House Account page opened or not
			if(driver.findElement(By.xpath(excel.getData(5, 129, 1))).getText().equalsIgnoreCase("House Account"))
			{
				test.log(LogStatus.PASS,"House Account page refresh  Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL,"House Account page refresh  Successfully is Failed");wb.close();
			}
			
		}
		
		@Test(enabled=false,priority=5)
		public void HouseAccount_Method_CreateNewHouseAccounts(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		   
			Thread.sleep(2000);
			//Click the Add House Account or Create new House Account button
			driver.findElement(By.xpath(excel.getData(5, 130, 1))).click();
			Thread.sleep(2000);
			//Check  New House Account form page loaded or not
			if(driver.findElement(By.xpath(excel.getData(5, 151, 1))).getText().equalsIgnoreCase("New House Account"))
			{
				test.log(LogStatus.PASS,"New House Account form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL,"New House Account form loaded Failed");wb.close();
			}
		}
				
		@Test(enabled=false,priority=6)
		public void HouseAccount_Method_addHouseAccountsfor_Daily(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			   
				Thread.sleep(5000);
			//Click the Customer Field
			driver.findElement(By.xpath(excel.getData(5, 132, 1))).click();
			//Enter the required Customer name in the Corresponding field
			driver.findElement(By.xpath(excel.getData(5, 133, 1))).sendKeys(Utility.getProperty("HA_Customer_Name"));
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(5, 133, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			//Clear the Account number field
			driver.findElement(By.name(excel.getData(5, 134, 3))).clear();
			//Enter the required name
			driver.findElement(By.name(excel.getData(5, 134, 3))).sendKeys(Utility.getProperty("HA_Account_Number"));
			//Click the Allow Credits Check box
			driver.findElement(By.xpath(excel.getData(5, 135, 1))).click();
			//Click the Enable Limit Check box
			driver.findElement(By.xpath(excel.getData(5, 136, 1))).click();
			//Click the Enable Max Limit button
			driver.findElement(By.xpath(excel.getData(5, 137, 1))).click();
			//Select the Limit Period as Daily
			Select limitPeriod = new Select(driver.findElement(By.xpath(excel.getData(5, 138, 1))));
			limitPeriod.selectByVisibleText("Daily");
			//Enter the minimum Limit
			driver.findElement(By.name(excel.getData(5, 139, 3))).clear();
			//Enter the minimum Limit
			driver.findElement(By.name(excel.getData(5, 139, 3))).sendKeys("1500");
			//Enter the Maximum Limit
			driver.findElement(By.name(excel.getData(5, 140, 3))).clear();
			//Enter the Maximum Limit
			driver.findElement(By.name(excel.getData(5, 140, 3))).sendKeys("2000");
			//Enter the Balance amount
			driver.findElement(By.name(excel.getData(5, 141, 3))).clear();
			//Enter the Balance amount
			driver.findElement(By.name(excel.getData(5, 141, 3))).sendKeys("2500");
			Thread.sleep(2000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(5, 142, 1))).click();
			
			Thread.sleep(5000);
			//Check weather the new House Account saved successfully or not
			if(driver.findElement(By.cssSelector(excel.getData(5, 49, 4))).getText().equalsIgnoreCase("HouseAccount saved successfully"))
			{										
				test.log(LogStatus.PASS,"House Account Daily Saved  Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL,"House Account Daily Save Failed");
				wb.close();
			}
		
		}
			
		@Test(enabled=false,priority=7)
		public void HouseAccount_Method_EditHouseAccountsfor_Weekly(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			   
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("HA_Customer_Name"));
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(5, 86, 4))).click();
			Thread.sleep(1000);
				//Click the Allow Credits Check box
				driver.findElement(By.xpath(excel.getData(5, 135, 1))).click();
				//Click the Enable Limit Check box
				driver.findElement(By.xpath(excel.getData(5, 136, 1))).click();
				//Click the Enable Max Limit button
				driver.findElement(By.xpath(excel.getData(5, 137, 1))).click();
				//Select the Limit Period as Weekly
				Select limitPeriod = new Select(driver.findElement(By.xpath(excel.getData(5, 143, 1))));
				limitPeriod.selectByVisibleText("Weekly");
				Thread.sleep(2000);
				//Select the Start Day option
				Select startDay = new Select(driver.findElement(By.xpath(excel.getData(5, 144, 1))));
				startDay.selectByVisibleText("WEDNESDAY");
				Thread.sleep(2000);
				//Clear the limit field
				driver.findElement(By.name(excel.getData(5, 139, 3))).clear();
				//Enter the minimum Limit
				driver.findElement(By.name(excel.getData(5, 139, 3))).sendKeys("1600");
				//Clear the Maximum Limit
				driver.findElement(By.name(excel.getData(5, 140, 3))).clear();
				//Enter the Maximum Limit
				driver.findElement(By.name(excel.getData(5, 140, 3))).sendKeys("2100");
				//Click the Save button
				driver.findElement(By.xpath(excel.getData(5, 142, 1))).click();
				Thread.sleep(2000);
				
				//Check weather the new House Account saved successfully or not
				//if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("House account updated successfully"))
				if(driver.findElement(By.cssSelector(excel.getData(5, 49, 4))).getText().equalsIgnoreCase("House account updated successfully"))
				{
					test.log(LogStatus.PASS,"House account Weekly updated successfully");
				}
				else
				{
					test.log(LogStatus.FAIL,"House account Weekly updated successfully is Failed");wb.close();
				}
			
		}
				
		@Test(enabled=false,priority=8)
		public void HouseAccount_Method_EditHouseAccountsfor_Monthly(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			   
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(5, 71, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys(Utility.getProperty("HA_Customer_Name"));
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(5, 86, 4))).click();
			Thread.sleep(5000);
			//Select the Limit Period as Monthly
			Select limitPeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 652, 1))));
			limitPeriod.selectByVisibleText("Monthly");
			//Clear the limit field
			driver.findElement(By.name(excel.getData(5, 139, 3))).clear();
			//Enter the minimum Limit
			driver.findElement(By.name(excel.getData(5, 139, 3))).sendKeys("1700");
			//Clear the Maximum Limit
			driver.findElement(By.name(excel.getData(5, 140, 3))).clear();
			//Enter the Maximum Limit
			driver.findElement(By.name(excel.getData(5, 140, 3))).sendKeys("2200");
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(5, 142, 1))).click();
			Thread.sleep(5000);
		
			//Check weather the new House Account saved successfully or not
			//if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("House account updated successfully"))
			if(driver.findElement(By.cssSelector(excel.getData(5, 49, 4))).getText().equalsIgnoreCase("House account updated successfully"))
			{
				test.log(LogStatus.PASS,"House account Monthly updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL,"House account Monthly updated successfully is Failed");
			}
			Thread.sleep(5000);wb.close();
		}
		
		@Test(enabled=false,priority=9)
		public void HouseAccount_Method_deleteHouseAccounts(WebDriver driver) throws Exception
		{	
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			   
			Thread.sleep(5000);
		     //Clear the search field       
			driver.findElement(By.xpath(excel.getData(5, 71, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys(Utility.getProperty("HA_Customer_Name"));
			Thread.sleep(5000);		
			//Click the delete button
			driver.findElement(By.xpath(excel.getData(5, 149, 1))).click();
			
			Thread.sleep(5000);
			//Click the yes button on popup
			driver.findElement(By.cssSelector(excel.getData(5, 69, 4))).click();
			
			Thread.sleep(5000);
			//Check weather the House Account deleted or not
			if(driver.findElement(By.xpath(excel.getData(5, 145, 1))).getText().equalsIgnoreCase("House account inactivated successfully"))
			{									
				test.log(LogStatus.PASS,"House account  inactivated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL,"House Account inactivated Failed");
			}
			Thread.sleep(5000);wb.close();
		}
			
		@Test(enabled=false,priority=10)
		public void HouseAccount_Method_DeActivestatus(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			   
			Thread.sleep(4000);
			//Check Deactivate course
		    driver.findElement(By.xpath(excel.getData(5, 146, 1))).click();
			//Clear the search field
		    Thread.sleep(5000);
		    driver.findElement(By.xpath(excel.getData(5, 71, 1))).clear();
				//Enter the required keyword
				driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys(Utility.getProperty("HA_Customer_Name"));
				Thread.sleep(5000);
				//Click the activate button
			driver.findElement(By.xpath(excel.getData(5, 150, 1))).click();
			Thread.sleep(5000);
			//Click the Yes button in the popup
			driver.findElement(By.cssSelector(excel.getData(5, 69, 4))).click();  
		    
			Thread.sleep(4000);
			//Check if we Deactivate or not///House account  activated successfully
			if(driver.findElement(By.xpath(excel.getData(5, 145, 1))).getText().equalsIgnoreCase("House account activated successfully"))
			{
				test.log(LogStatus.PASS,"House account  activated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL,"House account  activated successfully is Failed");
			}
			Thread.sleep(4000);
			//Check Deactivate course
		    driver.findElement(By.xpath(excel.getData(5, 146, 1))).click();
			//Clear the search field
		    Thread.sleep(5000);
		    driver.findElement(By.xpath(excel.getData(5, 71, 1))).clear();wb.close();
			}
	
}



