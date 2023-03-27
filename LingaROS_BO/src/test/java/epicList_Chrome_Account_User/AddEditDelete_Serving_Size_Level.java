package epicList_Chrome_Account_User;

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

public class AddEditDelete_Serving_Size_Level {
	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete Serving Size Level");

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
			Serving_Size_Level_method_openServingSizeLevel(driver);
			Serving_Size_Level_method_refreshServingSizeLevel_Page(driver);
			Serving_Size_Level_method_add_ServingSizeLevel(driver);
			Serving_Size_Level_method_edit_ServingSizeLevel(driver);
			Serving_Size_Level_method_delete_ServingSizeLevel(driver);
			Serving_Size_Level_method_cancel_ServingSizeLevel(driver);
			Serving_Size_Level_method_inactiveAndActive_Button(driver);
			Thread.sleep(1500);
		}
		
		@Test(enabled=false,priority=41)
		public void Serving_Size_Level_method_openServingSizeLevel(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"servingSizeLevel");
			Thread.sleep(8000);
			
			try
			{
			//Check Serving Size Level page opened or not
			if(driver.findElement(By.xpath(excel.getData(0, 13, 1))).getText().equalsIgnoreCase("Serving size levels"))
			{
				test.log(LogStatus.PASS, "Serving Size Level page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Serving Size Level page loaded Failed");
				
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
			Thread.sleep(3000);wb.close();
		}
		
		@Test(enabled=false,priority=42)
		public void Serving_Size_Level_method_refreshServingSizeLevel_Page(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(10000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(0, 30, 1))).click();
			Thread.sleep(10000);
			
			//Check Coursing page opened or not
			if(driver.findElement(By.xpath(excel.getData(0, 58, 1))).getText().equalsIgnoreCase("Serving Size Levels"))
			{
				test.log(LogStatus.PASS, "Serving Size Level page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Serving Size Level page loaded Failed");
			}
			Thread.sleep(3000);wb.close();
		}
	
		@Test(enabled=false,priority=44)
		public void Serving_Size_Level_method_add_ServingSizeLevel(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			for (int i = 0; i < 10; i++) {
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
			Thread.sleep(3000);
			//Click the Add serving Size Level button
			driver.findElement(By.xpath(excel.getData(0, 59, 1))).click();
			
			Thread.sleep(2000);
			//Check weather the new Serving Size Level form loaded or not
			if(driver.findElement(By.xpath(excel.getData(0, 60, 1))).getText().equalsIgnoreCase("New Serving Size Level"))
			{
				test.log(LogStatus.PASS, "New Serving Size Level form Loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Serving Size Level form Loaded Fail");
			}
			
			Thread.sleep(6000);
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(0, 54, 1))).clear();
			//Enter the required name
			driver.findElement(By.xpath(excel.getData(0, 54, 1))).sendKeys(Utility.getProperty("ServingSizeLevel_Name"));
			
			//Clear the description field
			driver.findElement(By.xpath(excel.getData(0, 35, 1))).clear();
			//Enter the required description field
			driver.findElement(By.xpath(excel.getData(0, 35, 1))).sendKeys("New SSL Description");
			
			Thread.sleep(2000);
			//Click the save button
			driver.findElement(By.xpath(excel.getData(0, 61, 1))).click();
			
		//	Thread.sleep(2000);
			WebElement ele=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			
			//Check weather the new SSL is saved or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Serving size level saved successfully"))
			{
				test.log(LogStatus.PASS, "New Serving Size Level Saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Serving Size Level saved fail");
			}
			Thread.sleep(5000);wb.close();
		}
		
		@Test(enabled=false,priority=45)
		public void Serving_Size_Level_method_edit_ServingSizeLevel(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("ServingSizeLevel_Name")+"1");
			Thread.sleep(8000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(8000);
			//Click the Edit button
			driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();
			
			Thread.sleep(8000);
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(0, 54, 1))).clear();
			//Enter the required name
			driver.findElement(By.xpath(excel.getData(0, 54, 1))).sendKeys(Utility.getProperty("ServingSizeLevel_Name")+"1");
	
			Thread.sleep(1000);
			//Click the Update button
			driver.findElement(By.xpath(excel.getData(0, 61, 1))).click();
			
			//Thread.sleep(4000);
			WebElement up=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
			//Check weather the new Serving Size Level is updated or not
			if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Serving Size Level Updated Successfully"))
			{
				test.log(LogStatus.PASS, "New Serving Size Level is updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Serving Size Level is updated fail");
			}
			Thread.sleep(5000);wb.close();
		}
			
		@Test(enabled=false,priority=46)
		public void Serving_Size_Level_method_delete_ServingSizeLevel(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("ServingSizeLevel_Name")+"11");
			Thread.sleep(8000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(8000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(0, 42, 4))).click();
			
			Thread.sleep(2000);
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(0, 43, 4))).click();
			
			//Thread.sleep(3000);
			
			WebElement ele=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			//Check the Serving Size Level deleted or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Serving Size Level Inactivated Successfully"))
			{
				test.log(LogStatus.PASS, "New Serving Size Level is deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Serving Size Level is deleted Failed");
			}
			Thread.sleep(5000);
			
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(0, 44, 1))).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector(excel.getData(0, 46, 4))).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(0, 43, 4))).click();
			//Thread.sleep(3000);
			
			WebElement ele1=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			
			WebDriverWait wait1=new WebDriverWait(driver,60);
			
			
			//Check the Serving Size Level activated or not
			if(wait1.until(ExpectedConditions.visibilityOf(ele1)).getText().equalsIgnoreCase("Serving Size Level Activated Successfully"))
			{
				test.log(LogStatus.PASS, "Serving Size Level is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Serving Size Level is activated Failed");
			}
			
			Thread.sleep(5000);
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(0, 44, 1))).click();
			Thread.sleep(3000);wb.close();
		}
		
		@Test(enabled=false,priority=47)
		public void Serving_Size_Level_method_cancel_ServingSizeLevel(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("ServingSizeLevel_Name")+"11");
			Thread.sleep(8000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(8000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();
			
			Thread.sleep(5000);
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(0, 45, 1))).click();
			
			Thread.sleep(2000);
			//Check the serving size level form is closed or not
			if(driver.findElement(By.xpath(excel.getData(0, 59, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Serving Size Level page is Closed after click the close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Serving Size Level page is displayed after click the close button");
			}
			
			Thread.sleep(5000);wb.close();
		}
		
		@Test(enabled=false,priority=48)
		public void Serving_Size_Level_method_inactiveAndActive_Button(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
			Thread.sleep(1000);
			
			//Click the Active button
			driver.findElement(By.xpath(excel.getData(0, 44, 1))).click();
			Thread.sleep(1000);
			try
			{
			//Check the page
			if(driver.findElement(By.cssSelector(excel.getData(0, 46, 4))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Inactive page is displayed after click the Active button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inactive page is not displayed after click the Active button");
			}
			}
			catch(Exception d) {}
			Thread.sleep(3000);
			//Click the InActive button
			driver.findElement(By.xpath(excel.getData(0, 44, 1))).click();
			Thread.sleep(1000);
			
			//Check the page
			if(driver.findElement(By.cssSelector(excel.getData(0, 42, 4))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Active page is displayed after click the Inactive button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Active page is not displayed after click the Inactive button");
			}
			Thread.sleep(5000);
			
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);
			//watchTutorial(driver);
			Thread.sleep(5000);}
			wb.close();
		}
		@Test(priority=3, enabled=false)
		public void watchTutorial(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(2000);
			//Click the Watch Tutorial Option
			driver.findElement(By.xpath(excel.getData(0, 47, 1))).click();
			WebElement iframe = driver.findElement(By.xpath(excel.getData(0, 48, 1)));
			driver.switchTo().frame(iframe);
			Thread.sleep(3500);
			try
			{
				if(driver.findElement(By.xpath(excel.getData(0, 49, 1))).isDisplayed()&&driver.findElement(By.xpath("//button/div[.='Share']")).isDisplayed())
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
			driver.findElement(By.xpath(excel.getData(0, 50, 1))).click();
			wb.close();
		}
}
