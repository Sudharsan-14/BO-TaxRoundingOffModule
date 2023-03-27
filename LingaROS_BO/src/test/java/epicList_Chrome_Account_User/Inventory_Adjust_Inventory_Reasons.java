package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Inventory_Adjust_Inventory_Reasons 
{
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Adjust_Inventory_Reasons");

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
			// Call the chrome driver by using local path
			System.setProperty("webdriver.chrome.driver", Utility.getProperty("Chrome_Driver_Path"));
			// Open the Chrome window
			driver = new ChromeDriver();
			// Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Maximize the Chrome window
			driver.manage().window().maximize();
			Thread.sleep(1000);
			// Launch the URL
			driver.get(Utility.getProperty("appURL"));
			if (Utility.getProperty("Product").equalsIgnoreCase("UPOS")) {
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.UPOS_login(driver, test);
			} else {
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.Linga_login(driver, test);
			}
		}

		@Test(priority = 500)
		public void logout() throws Exception {
			Browser_Account_Level_User a = new Browser_Account_Level_User();
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
			Adjust_Inventory_Reasons_openpage(driver);
			Adjust_Inventory_Reasons_refresh(driver);
			Adjust_Inventory_Reasons_add_PAge(driver);
			Adjust_Inventory_Reasons_edit(driver);
			Adjust_Inventory_Reasons_delete(driver);
			Adjust_Inventory_Reasons_closeButton(driver);
			Adjust_Inventory_Reasons_verifyActive_InActiveButton(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=23)
		public void Adjust_Inventory_Reasons_openpage (WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);

			
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"adjustInventoryItemReasons");
					
			try
			{
			//Check In Adjust Inventory Reasons page opened or not
			if(driver.findElement(By.xpath(excel.getData(2, 196, 1))).getText().equalsIgnoreCase("Adjust Inventory Reasons"))
			{
				test.log(LogStatus.PASS, "Adjust Inventory Reasons page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Adjust Inventory Reasons page loaded Failed");
			
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
			Thread.sleep(5000);
			
		}
		
		@Test(enabled=false,priority=24)
		public void Adjust_Inventory_Reasons_refresh(WebDriver driver) throws Exception 
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(2, 197, 1))).click();
			Thread.sleep(3000);
			//Check In Adjust Inventory Reasons page opened or not
			if(driver.findElement(By.xpath(excel.getData(2, 198, 1))).getText().equalsIgnoreCase("Reasons"))
			{
				test.log(LogStatus.PASS, "Adjust Inventory Reasons page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Adjust Inventory Reasons page refreshed Failed");
			}
			wb.close();
			Thread.sleep(3000);
	
		}
	
		@Test(enabled=false,priority=26)
		public void Adjust_Inventory_Reasons_add_PAge(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);
			//Click on the Add  Adjust Inventory Reasons  option
			driver.findElement(By.xpath(excel.getData(2, 199, 1))).click();
			
			Thread.sleep(3000);
			//Clear the name field
			driver.findElement(By.name(excel.getData(2, 200, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(2, 200, 3))).sendKeys(Utility.getProperty("New_InventoryReason_name_ForModifier"));
							
			Thread.sleep(2000);
			//Select the Increase button
			Select Type1 = new Select(driver.findElement(By.xpath(excel.getData(2, 202, 1))));
			Type1.selectByVisibleText("Increase");
			
			Thread.sleep(1000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(2, 203, 1))).click();
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			//Thread.sleep(2000);
			WebDriverWait wait=new WebDriverWait(driver,150);
			//Check whether the new In House Units saved or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Reason saved successfully!."))
			{
				test.log(LogStatus.PASS, "Reason saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Reason saved Failed");
			}
			wb.close();
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=27)
		public void Adjust_Inventory_Reasons_edit(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(2, 206, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(2, 206, 1))).sendKeys(Utility.getProperty("New_InventoryReason_name_ForModifier"));
			Thread.sleep(5000);
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(2, 109, 4))).click();
			
			Thread.sleep(5000);
			//Clear the name field
			driver.findElement(By.name(excel.getData(2, 200, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(2, 200, 3))).sendKeys(Utility.getProperty("New_InventoryReason_name_ForModifier")+"1");
			Thread.sleep(3000);
			
			Thread.sleep(2000);
			//Select the Increase button
			Select Type1 = new Select(driver.findElement(By.xpath(excel.getData(2, 202, 1))));
			Type1.selectByVisibleText("Decrease");
			
			
			//Click the update
			driver.findElement(By.xpath(excel.getData(2, 203, 1))).click();
			Thread.sleep(2000);
	//close button --> //a[@class='btn btn-small btn-danger ng-binding']
			//Check whether the In House Units updated successfully or not
			if(driver.findElement(By.xpath(excel.getData(2, 205, 1))).getText().equalsIgnoreCase("Reason updated successfully."))
			{
				test.log(LogStatus.PASS, "Reason updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Reason updated Failed");
			}
			wb.close();
			Thread.sleep(5000);
	
		}
		
		@Test(enabled=false,priority=28)
		public void Adjust_Inventory_Reasons_delete(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(2, 206, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(2, 206, 1))).sendKeys(Utility.getProperty("New_InventoryReason_name_ForModifier")+"1");
			
			Thread.sleep(4000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).click();
			
			Thread.sleep(1000);
			//Click the Yes button in the popup
			driver.findElement(By.linkText(excel.getData(2, 441, 5))).click();
			Thread.sleep(3000);
			
			//Check the In House Units deleted or not
			if(driver.findElement(By.xpath(excel.getData(2, 205, 1))).getText().equalsIgnoreCase("Reason InActivated Successfully"))
			{
				test.log(LogStatus.PASS, "Reason InActivated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Reason InActivated Failed");
			}
	
			Thread.sleep(5000);
		
	
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(2, 210, 1))).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector(excel.getData(2, 118, 4))).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			driver.findElement(By.linkText(excel.getData(2, 441, 5))).click();
			Thread.sleep(3000);
			
			//Check the In House Units activated or not
			if(driver.findElement(By.xpath(excel.getData(2, 205, 1))).getText().equalsIgnoreCase("Reason Activated Successfully"))
			{
				test.log(LogStatus.PASS, "Reason Activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Reason Activated Failed");
			}
			wb.close();
			Thread.sleep(5000);
		}
	
		@Test(enabled=false,priority=29)
		public void Adjust_Inventory_Reasons_closeButton(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);
			//Click on the Add  Adjust Inventory Reasons  option
			driver.findElement(By.xpath(excel.getData(2, 199, 1))).click();
										
			Thread.sleep(3000);
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(2, 440, 1))).getText().equalsIgnoreCase("New Reason"))
			{
				test.log(LogStatus.PASS, "New Reason loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Reason form loaded Failed");
			}
	
			Thread.sleep(3000);
			//Clear the name field
			driver.findElement(By.name(excel.getData(2, 200, 3))).clear(); //--//input[@ng-model='reason.name']
			//Enter the Name
			driver.findElement(By.name(excel.getData(2, 200, 3))).sendKeys(Utility.getProperty("New_InventoryReason_name_ForModifier"));
			
			Thread.sleep(3000);
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(2, 201, 1))).click();
			
			
			Thread.sleep(5000);
			//Check whether the new New Reason canceled or not
				if(driver.findElement(By.xpath(excel.getData(2, 199, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "New Reason Canceled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Reason Canceled Failed");
			}
				wb.close();
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=30)
		public void Adjust_Inventory_Reasons_verifyActive_InActiveButton(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(2, 206, 1))).clear();
			
			Thread.sleep(5000);
			//Click Active or In Active Button
			driver.findElement(By.xpath(excel.getData(2, 210, 1))).click();
	
			Thread.sleep(5000);
			//Check the Inactive page is loaded or not
			if(driver.findElement(By.cssSelector(excel.getData(2, 118, 4))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Deleted categories are here, we are in InActive Page");
				Thread.sleep(10000);
				//Click Active or In Active Button
				driver.findElement(By.xpath(excel.getData(2, 210, 1))).click();
	
			}
			else if(driver.findElement(By.cssSelector(excel.getData(2, 429, 4))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Deleted In House Units are not here, we are in Active Page");
			}
			wb.close();
			Thread.sleep(5000);
		}

}
