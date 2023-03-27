package epicList_Chrome_Account_User;

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


public class Settings_OT_Setings {


	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_OT_Setings");
	
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
			SendMail.snedMailWithAttachment();    			
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
			OT_Settings_Method_openpage(driver);
			OT_Settings_Method_Add_Daily(driver);
			OT_Settings_Method_Add_Weekly(driver);
			OT_Settings_Method_close_Button(driver);
			Thread.sleep(1500);
		}

	@Test(priority=3, enabled=false)
	public void watchTutorial(WebDriver driver) throws Exception
	{driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(2000);
		//Click the Watch Tutorial Option
		driver.findElement(By.xpath("//span[.='Watch Tutorial']")).click();
		WebElement iframe = driver.findElement(By.xpath("//div[@class='modal-content']/span/iframe"));
		driver.switchTo().frame(iframe);
		Thread.sleep(3500);
		try
		{
			if(driver.findElement(By.xpath("//button/div[.='Watch later']")).isDisplayed()&&driver.findElement(By.xpath("//button/div[.='Share']")).isDisplayed())
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
		driver.findElement(By.xpath("//span[.='x' and @title='close']")).click();
	}

	@Test(enabled=false,priority=25)
	public void OT_Settings_Method_openpage(WebDriver driver) throws Exception
		{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
			
			Thread.sleep(5000);
			//Enter the URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"newPayrollSettings");
			

			Thread.sleep(5000);
			try
			{
			//Check weather the OT Settings page is loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 2008, 1))).getText().equalsIgnoreCase("Payroll Settings"))
			{
				test.log(LogStatus.PASS, "OT Settings page loaded successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "OT Settings page loaded fail");
			
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

			Thread.sleep(5000);wb.close();

		}	
		
	@Test(enabled=false,priority=26)
	public void OT_Settings_Method_Add_Daily(WebDriver driver) throws Exception
		{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(3, 2009, 1))).click();
			
			Thread.sleep(3000);
			//Payroll Settings Update starts
			test.log(LogStatus.INFO, "Start-Payroll Settings Update for Daily");
			
			Thread.sleep(2000);
			//Select the Minimum Payroll Age
			driver.findElement(By.xpath(excel.getData(3, 2022, 1))).clear();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 2022, 1))).sendKeys("18");
			
			
			Thread.sleep(2000);
			//Select the Minimum Payroll age Hours
			driver.findElement(By.xpath(excel.getData(3, 2023, 1))).clear();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 2023, 1))).sendKeys("24");
			
			
			Thread.sleep(2000);
			//Select the Start Day of Week
			driver.findElement(By.xpath(excel.getData(3, 2024, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 2025, 1))).sendKeys("Monday");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2025, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(2000);
			//Select the Default Payroll Process
			driver.findElement(By.xpath(excel.getData(3, 2026, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 2027, 1))).sendKeys("Daily");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2027, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Update Payroll Settings button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		//	Thread.sleep(4000);
			
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver, 60);
			
			//Check weather the new OT form saved or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Payroll Settings Updated Successfully"))
			{
				test.log(LogStatus.PASS, "Payroll Settings (Daily) Updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Payroll Settings (Daily) Updated fail");
			}
			Thread.sleep(4000);
			
			Thread.sleep(3000);
			//Payroll Settings Update starts
			test.log(LogStatus.INFO, "End-Payroll Settings Update for Daily");
			
			Thread.sleep(5000);
			//Click the Add OT settings button
			driver.findElement(By.xpath(excel.getData(3, 2010, 1))).click();
			Thread.sleep(2000);
			
			//Check weather the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 2011, 1))).getText().equalsIgnoreCase("New OT Settings"))
			{
				test.log(LogStatus.PASS, "New OT Settings form or page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New OT Settings form or page loaded fail");
			}
			
			Thread.sleep(2000);
			//Select the OT
			driver.findElement(By.xpath(excel.getData(3, 2012, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 2013, 1))).sendKeys("Daily");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2013, 1))).sendKeys(Keys.ENTER);
			
			
			//Clear the OverTime1 hours field
			driver.findElement(By.xpath(excel.getData(3, 2014, 1))).clear();
			//Enter the OverTime1 hours
			driver.findElement(By.xpath(excel.getData(3, 2014, 1))).sendKeys("1");
			
			Thread.sleep(2000);
			//Clear the OverTime1 percentage field
			driver.findElement(By.xpath(excel.getData(3, 2015, 1))).clear();
			//Enter the OverTime1 percentage
			driver.findElement(By.xpath(excel.getData(3, 2015, 1))).sendKeys("101");
			
			//Clear the OverTime2 hours field
			driver.findElement(By.xpath(excel.getData(3, 2016, 1))).clear();
			//Enter the OverTime2 hours
			driver.findElement(By.xpath(excel.getData(3, 2016, 1))).sendKeys("2");
			
			Thread.sleep(2000);
			//Clear the OverTime2 percentage field
			driver.findElement(By.xpath(excel.getData(3, 2017, 1))).clear();
			//Enter the OverTime2 percentage
			driver.findElement(By.xpath(excel.getData(3, 2017, 1))).sendKeys("102");
			
			//Clear the OverTime3 hours field
			driver.findElement(By.xpath(excel.getData(3, 2018, 1))).clear();
			//Enter the OverTime3 hours
			driver.findElement(By.xpath(excel.getData(3, 2018, 1))).sendKeys("3");
			
			Thread.sleep(2000);
			//Clear the OverTime3 percentage field
			driver.findElement(By.xpath(excel.getData(3, 2019, 1))).clear();
			//Enter the OverTime3 percentage
			driver.findElement(By.xpath(excel.getData(3, 2019, 1))).sendKeys("103");
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(2000);
			//Clear the effectiveDate field
			driver.findElement(By.xpath(excel.getData(3, 2020, 1))).clear();
			//Enter the effectiveDate
			driver.findElement(By.xpath(excel.getData(3, 2020, 1))).sendKeys(Utility.getProperty("OT_Settings_Date"));
				
			Thread.sleep(1000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		//	Thread.sleep(4000);
			
			WebElement ele1=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait1=new WebDriverWait(driver, 60);
			
			//Check weather the new OT form saved or not
			if(wait1.until(ExpectedConditions.visibilityOf(ele1)).getText().equalsIgnoreCase("OT Saved Successfully"))
			{
				test.log(LogStatus.PASS, "New OT Settings (Daily) Saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New OT Settings (Daily) Saved fail");
			}
			
			Thread.sleep(8000);
			//get the number of rows
			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 2021, 1)));
			rows.size();
			
			for(int i = rows.size() ; i == rows.size(); i-- )
			{
				//Print the date
				System.out.println("The Effective Date is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[1]")).getText());
				test.log(LogStatus.INFO, "The Effective Date is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[1]")).getText());
				
				//Print the Type
				System.out.println("The OT Type is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[2]")).getText());
				test.log(LogStatus.INFO, "The OT Type is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[2]")).getText());
				
				//Print the Hour
				System.out.println("OT Hour is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[3]")).getText());
				test.log(LogStatus.INFO, "OT Hour is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[3]")).getText());
				
				//Print the Percentage
				System.out.println("The Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[4]")).getText());
				test.log(LogStatus.INFO, "The Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[4]")).getText());
			
				wb.close();
			}
		}

	

	@Test(enabled=false,priority=27)
	public void OT_Settings_Method_Add_Weekly(WebDriver driver) throws Exception
		{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
			Thread.sleep(5000);
		
			Thread.sleep(3000);
			//Payroll Settings Update starts
			test.log(LogStatus.INFO, "Start-Payroll Settings Update for Weekly");
			
			Thread.sleep(2000);
			//Select the Minimum Payroll Age
			driver.findElement(By.xpath(excel.getData(3, 2022, 1))).clear();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 2022, 1))).sendKeys("20");
			
			
			Thread.sleep(2000);
			//Select the Minimum Payroll age Hours
			driver.findElement(By.xpath(excel.getData(3, 2023, 1))).clear();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 2023, 1))).sendKeys("2");
			
			
			Thread.sleep(2000);
			//Select the Start Day of Week
			driver.findElement(By.xpath(excel.getData(3, 2024, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 2025, 1))).sendKeys("Monday");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2025, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(2000);
			//Select the Default Payroll Process
			driver.findElement(By.xpath(excel.getData(3, 2026, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 2027, 1))).sendKeys("Weekly");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2027, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Update Payroll Settings button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			//Thread.sleep(4000);
			
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver, 60);
			
			//Check weather the new OT form saved or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Payroll Settings Updated Successfully"))
			{
				test.log(LogStatus.PASS, "Payroll Settings to Weekly Updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Payroll Settings to Weekly Updated fail");
			}
			Thread.sleep(4000);
			
			Thread.sleep(3000);
			//Payroll Settings Update starts
			test.log(LogStatus.INFO, "End-Payroll Settings Update for Weekly");
			
			Thread.sleep(5000);
			//Click the Add OT settings button
			driver.findElement(By.xpath(excel.getData(3, 2010, 1))).click();
			Thread.sleep(2000);
			
			//Check weather the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 2011, 1))).getText().equalsIgnoreCase("New OT Settings"))
			{
				test.log(LogStatus.PASS, "New OT Settings form or page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New OT Settings form or page loaded fail");
			}
			
			Thread.sleep(2000);
			//Select the OT
			driver.findElement(By.xpath(excel.getData(3, 2012, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 2013, 1))).sendKeys("Weekly");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2013, 1))).sendKeys(Keys.ENTER);
			
			
			//Clear the OverTime hours field
			driver.findElement(By.xpath(excel.getData(3, 2028, 1))).clear();
			//Enter the OverTime1 hours
			driver.findElement(By.xpath(excel.getData(3, 2028, 1))).sendKeys("14");
			
			Thread.sleep(2000);
			//Clear the OverTime percentage field
			driver.findElement(By.xpath(excel.getData(3, 2029, 1))).clear();
			//Enter the OverTime1 percentage
			driver.findElement(By.xpath(excel.getData(3, 2029, 1))).sendKeys("200");
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(2000);
			//Clear the effectiveDate field
			driver.findElement(By.xpath(excel.getData(3, 2020, 1))).clear();
			//Enter the effectiveDate
			driver.findElement(By.xpath(excel.getData(3, 2020, 1))).sendKeys(Utility.getProperty("OT_Settings_Date_Weekly"));
				
			Thread.sleep(1000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			//Thread.sleep(4000); 
			
			WebElement ele1=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait1=new WebDriverWait(driver, 60);
			
			//Check weather the new OT form saved or not
			if(wait1.until(ExpectedConditions.visibilityOf(ele1)).getText().equalsIgnoreCase("OT Saved Successfully"))
			{
				test.log(LogStatus.PASS, "New OT Settings (Weekly) Saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New OT Settings (Weekly) Saved fail");
			}
			Thread.sleep(4000);
			
			//get the number of rows
			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 2021, 1)));
			rows.size();
			
			for(int i = rows.size() ; i == rows.size(); i-- )
			{
				//Print the date
				System.out.println("The Effective Date is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[1]")).getText());
				test.log(LogStatus.INFO, "The Effective Date is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[1]")).getText());
				
				//Print the Type
				System.out.println("The OT Type is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[2]")).getText());
				test.log(LogStatus.INFO, "The OT Type is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[2]")).getText());
				
				//Print the Hour
				System.out.println("OT Hour is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[3]")).getText());
				test.log(LogStatus.INFO, "OT Hour is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[3]")).getText());
				
				//Print the Percentage
				System.out.println("The Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[4]")).getText());
				test.log(LogStatus.INFO, "The Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+i+"]/td[4]")).getText());
			
				wb.close();
			}
		}

	
	
	@Test(enabled=false,priority=28)
	public void OT_Settings_Method_close_Button(WebDriver driver) throws Exception
		{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		
		Thread.sleep(3000);
			//Click the Add OT settings button
			driver.findElement(By.xpath(excel.getData(3, 2010, 1))).click();
			Thread.sleep(2000);
			
			//Check weather the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 2011, 1))).getText().equalsIgnoreCase("New OT Settings"))
			{
				test.log(LogStatus.PASS, "New OT Settings form or page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New OT Settings form or page loaded fail");
			}
			
			Thread.sleep(2000);
			//Select the OT
			driver.findElement(By.xpath(excel.getData(3, 2012, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 2013, 1))).sendKeys("Daily");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2013, 1))).sendKeys(Keys.ENTER);
			
			
			//Clear the OverTime1 hours field
			driver.findElement(By.xpath(excel.getData(3, 2014, 1))).clear();
			//Enter the OverTime1 hours
			driver.findElement(By.xpath(excel.getData(3, 2014, 1))).sendKeys("1");
			
			Thread.sleep(2000);
			//Clear the OverTime1 percentage field
			driver.findElement(By.xpath(excel.getData(3, 2015, 1))).clear();
			//Enter the OverTime1 percentage
			driver.findElement(By.xpath(excel.getData(3, 2015, 1))).sendKeys("101");
			
			//Clear the OverTime2 hours field
			driver.findElement(By.xpath(excel.getData(3, 2016, 1))).clear();
			//Enter the OverTime2 hours
			driver.findElement(By.xpath(excel.getData(3, 2016, 1))).sendKeys("2");
			
			Thread.sleep(2000);
			//Clear the OverTime2 percentage field
			driver.findElement(By.xpath(excel.getData(3, 2017, 1))).clear();
			//Enter the OverTime2 percentage
			driver.findElement(By.xpath(excel.getData(3, 2017, 1))).sendKeys("102");
			
			//Clear the OverTime3 hours field
			driver.findElement(By.xpath(excel.getData(3, 2018, 1))).clear();
			//Enter the OverTime3 hours
			driver.findElement(By.xpath(excel.getData(3, 2018, 1))).sendKeys("3");
			
			Thread.sleep(2000);
			//Clear the OverTime3 percentage field
			driver.findElement(By.xpath(excel.getData(3, 2019, 1))).clear();
			//Enter the OverTime3 percentage
			driver.findElement(By.xpath(excel.getData(3, 2019, 1))).sendKeys("103");
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(2000);
			//Clear the effectiveDate field
			driver.findElement(By.xpath(excel.getData(3, 2020, 1))).clear();
			//Enter the effectiveDate
			driver.findElement(By.xpath(excel.getData(3, 2020, 1))).sendKeys(Utility.getProperty("OT_Settings_Date"));

			Thread.sleep(1000);
			//Click the Close button
			driver.findElement(By.xpath(excel.getData(3, 145, 1))).click();
			Thread.sleep(2000);
			
			//Check weather the new emv settings form closed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2010, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "OT Settings form Closed successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "OT Settings form Closed fail");
			}
			wb.close();
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);
			//watchTutorial(driver);
			Thread.sleep(5000);
			}
		}

}
