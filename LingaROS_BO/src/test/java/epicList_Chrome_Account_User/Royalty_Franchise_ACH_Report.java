package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Royalty_Franchise_ACH_Report {
	public WebDriver driver;
		
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Royalty_Franchise_ACH_Report");

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
			Enterprice_Royalty_Franchise_Settings_method_open_Enterprice_Royalty_Franchise_ACH_Report(driver);
			Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Store(driver);
			Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Group(driver);
			Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_City(driver);
			Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_State(driver);
			Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Zipcode(driver);
			Enterprice_Royalty_Franchise_Settings_method_Royalty_Report_ACH_Zipcode_CCD_Debit(driver);
			Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Store_With_ExcludeLoyaltyRedemtion(driver);
			Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Group_With_ExcludeLoyaltyRedemtion(driver);
			Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_City_With_ExcludeLoyaltyRedemtion(driver);
			Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_State_With_ExcludeLoyaltyRedemtion(driver);
			Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Zipcode_With_ExcludeLoyaltyRedemtion(driver);
			Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Zipcode_With_ExcludeLoyaltyRedemtion_PPD_Both(driver);
			Thread.sleep(1500);
		}
	
	@Test(enabled=false,priority=27)
	public void Enterprice_Royalty_Franchise_Settings_method_open_Enterprice_Royalty_Franchise_ACH_Report(WebDriver driver) throws Exception
	{
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);

		/*driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();

		Thread.sleep(3000);
        //Click the Royalty/Franchise Option		
		driver.findElement(By.xpath(excel.getData(2, 357, 1))).click();
		
		Thread.sleep(5000);
		//Check Royalty/Franchise page opened or not
		if(driver.findElement(By.xpath("//a[.='Royalty/Franchise']")).getText().equalsIgnoreCase("Royalty/Franchise"))
		{
			test.log(LogStatus.PASS, "Royalty/Franchise Settings page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Royalty/Franchise Settings page loaded Failed");
		}
		*/
		Thread.sleep(3000);
		//Enter the Coursing URL
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"royalty");

		Thread.sleep(3000);
		//Click the Royalty/Franchise Option	
		driver.findElement(By.xpath(excel.getData(2, 357, 1))).click();
		
		//Click the Royalty Report
		driver.findElement(By.xpath(excel.getData(2, 358, 1))).click();
		Thread.sleep(2500);
		try
		{
		//Check Royalty Report page opened or not
		if(driver.findElement(By.xpath(excel.getData(2, 359, 1))).getText().equalsIgnoreCase("Royalty Report"))
		{
			test.log(LogStatus.PASS, "ACH Report page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "ACH Report page loaded Failed");
		
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

	@Test(enabled=false,priority=28)
	public void Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Store(WebDriver driver) throws Exception
	{
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);

		Thread.sleep(5000);
		//Click the First Option
		driver.findElement(By.xpath(excel.getData(2, 343, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys("Group");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the First Option
		driver.findElement(By.xpath(excel.getData(2, 343, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys("Stores");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Second Option
		driver.findElement(By.xpath(excel.getData(2, 345, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Utility.getReportPropertyUser("Store"));
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Time Period Option
		driver.findElement(By.xpath(excel.getData(2, 347, 1))).click();
		//Enter the Time option
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys("Date Range");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the from date option
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).clear();
		//Enter the from Date
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		Thread.sleep(2000);
		//Clear the To date option
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).clear();
		//Enter the To Date
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Standard Entry Class Code Option
		driver.findElement(By.xpath(excel.getData(2, 360, 1))).click();
		//Enter the Required option of Standard Entry Class Code 
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys("PPD");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the	Credit/Debit option
		driver.findElement(By.xpath(excel.getData(2, 362, 1))).click();
		//Enter the Required option
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys("Both");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(2, 351, 1))).click();
		Thread.sleep(1500);
		
		Thread.sleep(3000);
		//Check weather the report is available for the selected time period
		if(driver.findElement(By.xpath(excel.getData(2, 352, 1))).isDisplayed())
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for Store");
		}
											 
		else if(driver.findElement(By.xpath(excel.getData(2, 364, 1))).getText() != null)
		{
			test.log(LogStatus.PASS, "Royalty Report is available for Store");
			
			Thread.sleep(3000);
			List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 1732, 1)));
			Thread.sleep(2000);
			//Get the Total amount value
			String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
			
			float total_AMT = Float.parseFloat(Total_Amount);
			
			String Net_Sale = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr[2]/td[3]")).getText().replaceAll("[a-zA-Z $ ₹ ,]", "");
			
			float netsale_AMT = Float.parseFloat(Net_Sale);
			
			//Print the Net sale value 
			System.out.println("Netsale Amount(Store) is : "+netsale_AMT);
			
			test.log(LogStatus.INFO, "Netsale Amount(Store) is : "+netsale_AMT);	
			
			//Print the Total_Amount value
			System.out.println("Royalty Fee(Store) is : "+total_AMT);
			
			test.log(LogStatus.INFO, "Royalty Fee(Store) is : "+total_AMT);		
			
			float percen = (float)((netsale_AMT*5.0)/100.0);
			 
		     DecimalFormat f = new DecimalFormat("##.00");
		     	    
		     String a = f.format(percen);
		     
		     float percentage = Float.parseFloat(a);
		     
			if(percentage == total_AMT)
			{
				test.log(LogStatus.PASS, "Royalty Fee(Store) is Correct");
			}
			else
			{
				test.log(LogStatus.FAIL, "Royalty Fee(Store) is Incorrect");
			}
		}
		
		else
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for Store");
		}
		wb.close();
	}
	
	@Test(enabled=false,priority=29)
	public void Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Group(WebDriver driver) throws Exception
	{
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);

		//Click the First Option
		driver.findElement(By.xpath(excel.getData(2, 343, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys("Group");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys(Keys.ENTER);
			
		Thread.sleep(1000);
		//Click the Second Option
		driver.findElement(By.xpath(excel.getData(2, 345, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Utility.getReportPropertyUser("Group"));
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Time Period Option
		driver.findElement(By.xpath(excel.getData(2, 347, 1))).click();
		//Enter the Time option
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys("Date Range");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the from date option
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).clear();
		//Enter the from Date
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		Thread.sleep(1000);
		//Clear the To date option
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).clear();
		//Enter the To Date
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Standard Entry Class Code Option
		driver.findElement(By.xpath(excel.getData(2, 360, 1))).click();
		//Enter the Required option of Standard Entry Class Code
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys("PPD");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the	Credit/Debit option
		driver.findElement(By.xpath(excel.getData(2, 362, 1))).click();
		//Enter the Required option
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys("Credit");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys(Keys.ENTER);

		
		Thread.sleep(3000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(2, 351, 1))).click();
		Thread.sleep(1500);
		
		Thread.sleep(3000);
		//Check weather the report is available for the selected time period
		if(driver.findElement(By.xpath(excel.getData(2, 352, 1))).isDisplayed())
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for Store");
		}
		
		else if(driver.findElement(By.xpath(excel.getData(2, 364, 1))).getText() != null)
		{
			test.log(LogStatus.PASS, "Royalty Report is available for Store");
			
			List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 1732, 1)));
			Thread.sleep(3000);//Get the Total amount value
			String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
			
			float total_AMT = Float.parseFloat(Total_Amount);
			
			String Net_Sale = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr[2]/td[3]")).getText().replaceAll("[a-zA-Z $ ₹ ,]", "");
			
			float netsale_AMT = Float.parseFloat(Net_Sale);
			 
			//Print the Net sale value
			System.out.println("Netsale Amount(Group) is : "+netsale_AMT);
			
			test.log(LogStatus.INFO, "Netsale Amount(Group) is : "+netsale_AMT);	
			
			//Print the Total_Amount value
			System.out.println("Royalty Fee(Group) is : "+total_AMT);
			
			test.log(LogStatus.INFO, "Royalty Fee(Group) is : "+total_AMT);		
			
			float percen = (float)((netsale_AMT*5.0)/100.0);
			
		     DecimalFormat f = new DecimalFormat("##.00");
		     	    
		     String a = f.format(percen);
		     
		     float percentage = Float.parseFloat(a);
		     
			if(percentage == total_AMT)
			{
				test.log(LogStatus.PASS, "Royalty Fee(Group) is Correct");
			}
			else
			{
				test.log(LogStatus.FAIL, "Royalty Fee(Group) is Incorrect");
			}
		}
		
		else
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for Group");
		}
		Thread.sleep(3000);
	wb.close();
	}

	@Test(enabled=false,priority=30)
	public void Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_City(WebDriver driver) throws Exception
	{
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);

		//Click the First Option
		driver.findElement(By.xpath(excel.getData(2, 343, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys("City");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys(Keys.ENTER);
			
		Thread.sleep(1000);
		//Click the Second Option
		driver.findElement(By.xpath(excel.getData(2, 345, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Utility.getReportPropertyUser("City"));
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Time Period Option
		driver.findElement(By.xpath(excel.getData(2, 347, 1))).click();
		//Enter the Time option
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys("Date Range");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the from date option
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).clear();
		//Enter the from Date
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		Thread.sleep(1000);
		//Clear the To date option
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).clear();
		//Enter the To Date
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Standard Entry Class Code Option
		driver.findElement(By.xpath(excel.getData(2, 360, 1))).click();
		//Enter the Required option of Standard Entry Class Code
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys("PPD");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the	Credit/Debit option
		driver.findElement(By.xpath(excel.getData(2, 362, 1))).click();
		//Enter the Required option
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys("Debit");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(2, 351, 1))).click();
		Thread.sleep(1500);
		
		Thread.sleep(3000);
		//Check weather the report is available for the selected time period
		if(driver.findElement(By.xpath(excel.getData(2, 352, 1))).isDisplayed())
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for City");
		}
		
		else if(driver.findElement(By.xpath(excel.getData(2, 364, 1))).getText() != null)
		{
			test.log(LogStatus.PASS, "Royalty Report is available for City");
			
			Thread.sleep(3000);
			List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 1732, 1)));
			Thread.sleep(3000);//Get the Total amount value
			String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
			
			float total_AMT = Float.parseFloat(Total_Amount);
			
			String Net_Sale = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr[2]/td[3]")).getText().replaceAll("[a-zA-Z $ ₹ ,]", "");
			
			float netsale_AMT = Float.parseFloat(Net_Sale);
			
			//Print the Net sale value
			System.out.println("Netsale Amount(City) is : "+netsale_AMT);
			
			test.log(LogStatus.INFO, "Netsale Amount(City) is : "+netsale_AMT);	
			
			//Print the Total_Amount value
			System.out.println("Royalty Fee(City) is : "+total_AMT);
			
			test.log(LogStatus.INFO, "Royalty Fee(City) is : "+total_AMT);		
			
			float percen = (float)((netsale_AMT*5.0)/100.0);
			
		     DecimalFormat f = new DecimalFormat("##.00");
		     	    
		     String a = f.format(percen);
		     
		     float percentage = Float.parseFloat(a);
		     
			if(percentage == total_AMT)
			{
				test.log(LogStatus.PASS, "Royalty Fee(City) is Correct");
			}
			else
			{
				test.log(LogStatus.FAIL, "Royalty Fee(City) is Incorrect");
			}
		}
		
		else
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for City");
		}
		Thread.sleep(3000);
		wb.close();
	}
	
	@Test(enabled=false,priority=31)
	public void Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_State(WebDriver driver) throws Exception
	{
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);
		//Click the First Option
		driver.findElement(By.xpath(excel.getData(2, 343, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys("State");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys(Keys.ENTER);
			
		Thread.sleep(1000);
		//Click the Second Option
		driver.findElement(By.xpath(excel.getData(2, 345, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Utility.getReportPropertyUser("State"));
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Time Period Option
		driver.findElement(By.xpath(excel.getData(2, 347, 1))).click();
		//Enter the Time option
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys("Date Range");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the from date option
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).clear();
		//Enter the from Date
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		Thread.sleep(1000);
		//Clear the To date option
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).clear();
		//Enter the To Date
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Standard Entry Class Code Option
		driver.findElement(By.xpath(excel.getData(2, 360, 1))).click();
		//Enter the Required option of Standard Entry Class Code
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys("CCD");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the	Credit/Debit option
		driver.findElement(By.xpath(excel.getData(2, 362, 1))).click();
		//Enter the Required option
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys("Both");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(2, 351, 1))).click();
		Thread.sleep(1500);
		
		Thread.sleep(3000);
		//Check weather the report is available for the selected time period
		if(driver.findElement(By.xpath(excel.getData(2, 352, 1))).isDisplayed())
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for State");
		}
		
		else if(driver.findElement(By.xpath(excel.getData(2, 364, 1))).getText() != null)
		{
			test.log(LogStatus.PASS, "Royalty Report is available for State");
			Thread.sleep(3000);
			List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 1732, 1)));

			
			Thread.sleep(3000);//Get the Total amount value
			String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
			
			float total_AMT = Float.parseFloat(Total_Amount);
			
			String Net_Sale = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr[2]/td[3]")).getText().replaceAll("[a-zA-Z $ ₹ ,]", "");
			
			float netsale_AMT = Float.parseFloat(Net_Sale);
			
			//Print the Net sale value
			System.out.println("Netsale Amount(State) is : "+netsale_AMT);
			
			test.log(LogStatus.INFO, "Netsale Amount(State) is : "+netsale_AMT);	
			
			//Print the Total_Amount value
			System.out.println("Royalty Fee(State) is : "+total_AMT);
			
			test.log(LogStatus.INFO, "Royalty Fee(State) is : "+total_AMT);		
			
			float percen = (float)((netsale_AMT*5.0)/100.0);
			
		     DecimalFormat f = new DecimalFormat("##.00");
		     	    
		     String a = f.format(percen);
		     
		     float percentage = Float.parseFloat(a);
		     
			if(percentage == total_AMT)
			{
				test.log(LogStatus.PASS, "Royalty Fee(State) is Correct");
			}
			else
			{
				test.log(LogStatus.FAIL, "Royalty Fee(State) is Incorrect");
			}
		}
		
		else
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for State");
		}
		Thread.sleep(3000);
		wb.close();
	}
		
	@Test(enabled=false,priority=32)
	public void Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Zipcode(WebDriver driver) throws Exception
	{
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);

		//Click the First Option
		driver.findElement(By.xpath(excel.getData(2, 343, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys("Zip Code");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys(Keys.ENTER);
			
		Thread.sleep(1000);
		//Click the Second Option
		driver.findElement(By.xpath(excel.getData(2, 345, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Utility.getReportPropertyUser("Zipcode"));
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Time Period Option
		driver.findElement(By.xpath(excel.getData(2, 347, 1))).click();
		//Enter the Time option
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys("Date Range");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the from date option
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).clear();
		//Enter the from Date
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		Thread.sleep(1000);
		//Clear the To date option
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).clear();
		//Enter the To Date
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Standard Entry Class Code Option
		driver.findElement(By.xpath(excel.getData(2, 360, 1))).click();
		//Enter the Required option of Standard Entry Class Code
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys("CCD");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the	Credit/Debit option
		driver.findElement(By.xpath(excel.getData(2, 362, 1))).click();
		//Enter the Required option
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys("Credit");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(2, 351, 1))).click();
		Thread.sleep(1500);
		
		Thread.sleep(3000);
		//Check weather the report is available for the selected time period
		if(driver.findElement(By.xpath(excel.getData(2, 352, 1))).isDisplayed())
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for Zipcode");
		}
		
		else if(driver.findElement(By.xpath(excel.getData(2, 364, 1))).getText() != null)
		{
			test.log(LogStatus.PASS, "Royalty Report is available for Zipcode");
			
			Thread.sleep(3000);
			List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 1732, 1)));

			Thread.sleep(3000);//Get the Total amount value
			String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
			
			float total_AMT = Float.parseFloat(Total_Amount);
			
			String Net_Sale = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr[2]/td[3]")).getText().replaceAll("[a-zA-Z $ ₹ ,]", "");
			
			float netsale_AMT = Float.parseFloat(Net_Sale);
			
			//Print the Net sale value
			System.out.println("Netsale Amount(Zipcode) is : "+netsale_AMT);
			
			test.log(LogStatus.INFO, "Netsale Amount(Zipcode) is : "+netsale_AMT);	
			
			//Print the Total_Amount value
			System.out.println("Royalty Fee(Zipcode) is : "+total_AMT);
			
			test.log(LogStatus.INFO, "Royalty Fee(Zipcode) is : "+total_AMT);		
			
			float percen = (float)((netsale_AMT*5.0)/100.0);
			
		     DecimalFormat f = new DecimalFormat("##.00");
		     	    
		     String a = f.format(percen);
		     
		     float percentage = Float.parseFloat(a);
		     
			if(percentage == total_AMT)
			{
				test.log(LogStatus.PASS, "Royalty Fee(Zipcode) is Correct");
			}
			else
			{
				test.log(LogStatus.FAIL, "Royalty Fee(Zipcode) is Incorrect");
			}
		}
		
		else
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for Zipcode");
		}
		Thread.sleep(3000);
		wb.close();
	}
	
	@Test(enabled=false,priority=33)
	public void Enterprice_Royalty_Franchise_Settings_method_Royalty_Report_ACH_Zipcode_CCD_Debit(WebDriver driver) throws Exception
	{
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);
		//Click the First Option
		driver.findElement(By.xpath(excel.getData(2, 343, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys("Zip Code");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys(Keys.ENTER);
			
		Thread.sleep(1000);
		//Click the Second Option
		driver.findElement(By.xpath(excel.getData(2, 345, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Utility.getReportPropertyUser("Zipcode"));
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Time Period Option
		driver.findElement(By.xpath(excel.getData(2, 347, 1))).click();
		//Enter the Time option
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys("Date Range");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the from date option
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).clear();
		//Enter the from Date
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		Thread.sleep(1000);
		//Clear the To date option
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).clear();
		//Enter the To Date
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Standard Entry Class Code Option
		driver.findElement(By.xpath(excel.getData(2, 360, 1))).click();
		//Enter the Required option of Standard Entry Class Code
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys("CCD");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the	Credit/Debit option
		driver.findElement(By.xpath(excel.getData(2, 362, 1))).click();
		//Enter the Required option
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys("Debit");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(2, 351, 1))).click();
		Thread.sleep(1500);
		
		Thread.sleep(3000);
		//Check weather the report is available for the selected time period
		if(driver.findElement(By.xpath(excel.getData(2, 352, 1))).isDisplayed())
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for Zipcode");
		}
		
		else if(driver.findElement(By.xpath(excel.getData(2, 364, 1))).getText() != null)
		{
			test.log(LogStatus.PASS, "Royalty Report is available for Zipcode");
			
			Thread.sleep(3000);
			List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 1732, 1)));

			Thread.sleep(2000);//Get the Total amount value
			String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
			
			float total_AMT = Float.parseFloat(Total_Amount);
			
			String Net_Sale = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr[2]/td[3]")).getText().replaceAll("[a-zA-Z $ ₹ ,]", "");
			
			float netsale_AMT = Float.parseFloat(Net_Sale);
			
			//Print the Net sale value
			System.out.println("Netsale Amount(Zipcode) is : "+netsale_AMT);
			
			test.log(LogStatus.INFO, "Netsale Amount(Zipcode) is : "+netsale_AMT);	
			
			//Print the Total_Amount value
			System.out.println("Royalty Fee(Zipcode) is : "+total_AMT);
			
			test.log(LogStatus.INFO, "Royalty Fee(Zipcode) is : "+total_AMT);		
			
			float percen = (float)((netsale_AMT*5.0)/100.0);
			
		     DecimalFormat f = new DecimalFormat("##.00");
		     	    
		     String a = f.format(percen);
		     
		     float percentage = Float.parseFloat(a);
		     
			if(percentage == total_AMT)
			{
				test.log(LogStatus.PASS, "Royalty Fee(Zipcode) is Correct");
			}
			else
			{
				test.log(LogStatus.FAIL, "Royalty Fee(Zipcode) is Incorrect");
			}
		}
		
		else
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for Zipcode");
		}
		Thread.sleep(3000);
		wb.close();
	}
		
	@Test(enabled=false,priority=34)
	public void Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Store_With_ExcludeLoyaltyRedemtion(WebDriver driver) throws Exception
	{
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);
		//Click the First Option
		driver.findElement(By.xpath(excel.getData(2, 343, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys("Group");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the First Option
		driver.findElement(By.xpath(excel.getData(2, 343, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys("Stores");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Second Option
		driver.findElement(By.xpath(excel.getData(2, 345, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Utility.getReportPropertyUser("Store"));
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Time Period Option
		driver.findElement(By.xpath(excel.getData(2, 347, 1))).click();
		//Enter the Time option
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys("Date Range");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the from date option
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).clear();
		//Enter the from Date
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		Thread.sleep(1000);
		//Clear the To date option
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).clear();
		//Enter the To Date
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1500);
		if(driver.findElement(By.xpath(excel.getData(2, 356, 1))).isSelected()){}
		else
		{
		//Enable the ExcludeLoyaltyRedemtion button
		driver.findElement(By.xpath(excel.getData(2, 356, 1))).click();
		}
		
		Thread.sleep(1000);
		//Click the Standard Entry Class Code Option
		driver.findElement(By.xpath(excel.getData(2, 360, 1))).click();
		//Enter the Required option of Standard Entry Class Code
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys("CCD");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the	Credit/Debit option
		driver.findElement(By.xpath(excel.getData(2, 362, 1))).click();
		//Enter the Required option
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys("Debit");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(2, 351, 1))).click();
		Thread.sleep(1500);
		
		Thread.sleep(3000);
		//Check weather the report is available for the selected time period
		if(driver.findElement(By.xpath(excel.getData(2, 352, 1))).isDisplayed())
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for Store");
		}
		
		else if(driver.findElement(By.xpath(excel.getData(2, 364, 1))).getText() != null)
		{
			test.log(LogStatus.PASS, "Royalty Report is available for Store(With Exclude Loyalty Redemtion)");
			
			Thread.sleep(3000);
			List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 1732, 1)));

			Thread.sleep(2000);//Get the Total amount value
			String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
			
			float total_AMT = Float.parseFloat(Total_Amount);
			
			String Net_Sale = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr[2]/td[3]")).getText().replaceAll("[a-zA-Z $ ₹ ,]", "");
			
			float netsale_AMT = Float.parseFloat(Net_Sale);
			
			//Print the Net sale value
			System.out.println("Netsale Amount for Store(With Exclude Loyalty Redemtion) is : "+netsale_AMT);
			
			test.log(LogStatus.INFO, "Netsale Amount for Store(With Exclude Loyalty Redemtion) is : "+netsale_AMT);	
			
			//Print the Total_Amount value
			System.out.println("Royalty Fee for Store(With Exclude Loyalty Redemtion) is : "+total_AMT);
			
			test.log(LogStatus.INFO, "Royalty Fee for Store(With Exclude Loyalty Redemtion) is : "+total_AMT);		
			
			float percen = (float)((netsale_AMT*5.0)/100.0);
			
		     DecimalFormat f = new DecimalFormat("##.00");
		     	    
		     String a = f.format(percen);
		     
		     float percentage = Float.parseFloat(a);
		     
			if(percentage == total_AMT)
			{
				test.log(LogStatus.PASS, "Royalty Fee for Store(With Exclude Loyalty Redemtion) is Correct");
			}
			else
			{
				test.log(LogStatus.FAIL, "Royalty Fee for Store(With Exclude Loyalty Redemtion) is Incorrect");
			}
				
		}
		
		else
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for Store");
		}
		Thread.sleep(3000);
		wb.close();
	}
	
	@Test(enabled=false,priority=35)
	public void Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Group_With_ExcludeLoyaltyRedemtion(WebDriver driver) throws Exception
	{
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);
		//Click the First Option
		driver.findElement(By.xpath(excel.getData(2, 343, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys("Group");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys(Keys.ENTER);
			
		Thread.sleep(1000);
		//Click the Second Option
		driver.findElement(By.xpath(excel.getData(2, 345, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Utility.getReportPropertyUser("Group"));
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Time Period Option
		driver.findElement(By.xpath(excel.getData(2, 347, 1))).click();
		//Enter the Time option
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys("Date Range");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the from date option
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).clear();
		//Enter the from Date
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		Thread.sleep(1000);
		//Clear the To date option
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).clear();
		//Enter the To Date
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1500);
		if(driver.findElement(By.xpath(excel.getData(2, 356, 1))).isSelected()){}
		else
		{
		//Enable the ExcludeLoyaltyRedemtion button
		driver.findElement(By.xpath(excel.getData(2, 356, 1))).click();
		}
		
		Thread.sleep(1000);
		//Click the Standard Entry Class Code Option
		driver.findElement(By.xpath(excel.getData(2, 360, 1))).click();
		//Enter the Required option of Standard Entry Class Code
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys("CCD");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the	Credit/Debit option
		driver.findElement(By.xpath(excel.getData(2, 362, 1))).click();
		//Enter the Required option
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys("Credit");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys(Keys.ENTER);
				
		Thread.sleep(3000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(2, 351, 1))).click();
		Thread.sleep(1500);
		
		Thread.sleep(3000);
		//Check weather the report is available for the selected time period
		if(driver.findElement(By.xpath(excel.getData(2, 352, 1))).isDisplayed())
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for Group");
		}
		
		else if(driver.findElement(By.xpath(excel.getData(2, 364, 1))).getText() != null)
		{
			test.log(LogStatus.PASS, "Royalty Report is available for Group(With Exclude Loyalty Redemtion)");
			
			Thread.sleep(3000);
			List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 1732, 1)));
			
			Thread.sleep(2000);
			//Get the Total amount value
			String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
			
			float total_AMT = Float.parseFloat(Total_Amount);
			
			String Net_Sale = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr[2]/td[3]")).getText().replaceAll("[a-zA-Z $ ₹ ,]", "");
			
			float netsale_AMT = Float.parseFloat(Net_Sale);
			
			//Print the Net sale value
			System.out.println("Netsale Amount for Group(With Exclude Loyalty Redemtion) is : "+netsale_AMT);
			
			test.log(LogStatus.INFO, "Netsale Amount for Group(With Exclude Loyalty Redemtion) is : "+netsale_AMT);	
			
			//Print the Total_Amount value
			System.out.println("Royalty Fee for Group(With Exclude Loyalty Redemtion) is : "+total_AMT);
			
			test.log(LogStatus.INFO, "Royalty Fee for Group(With Exclude Loyalty Redemtion) is : "+total_AMT);		
			
			float percen = (float)((netsale_AMT*5.0)/100.0);
			
		     DecimalFormat f = new DecimalFormat("##.00");
		     	    
		     String a = f.format(percen);
		     
		     float percentage = Float.parseFloat(a);
		     
			if(percentage == total_AMT)
			{
				test.log(LogStatus.PASS, "Royalty Fee for Group(With Exclude Loyalty Redemtion) is Correct");
			}
			else
			{
				test.log(LogStatus.FAIL, "Royalty Fee for Group(With Exclude Loyalty Redemtion) is Incorrect");
			}
				
		}
		
		else
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for Group");
		}
		Thread.sleep(3000);
		wb.close();
	}

	@Test(enabled=false,priority=36)
	public void Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_City_With_ExcludeLoyaltyRedemtion(WebDriver driver) throws Exception
	{
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);
		//Click the First Option
		driver.findElement(By.xpath(excel.getData(2, 343, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys("City");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys(Keys.ENTER);
			
		Thread.sleep(1000);
		//Click the Second Option
		driver.findElement(By.xpath(excel.getData(2, 345, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Utility.getReportPropertyUser("City"));
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Time Period Option
		driver.findElement(By.xpath(excel.getData(2, 347, 1))).click();
		//Enter the Time option
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys("Date Range");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the from date option
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).clear();
		//Enter the from Date
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		Thread.sleep(1000);
		//Clear the To date option
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).clear();
		//Enter the To Date
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1500);
		if(driver.findElement(By.xpath(excel.getData(2, 356, 1))).isSelected()){}
		else
		{
		//Enable the ExcludeLoyaltyRedemtion button
		driver.findElement(By.xpath(excel.getData(2, 356, 1))).click();
		}
		
		Thread.sleep(1000);
		//Click the Standard Entry Class Code Option
		driver.findElement(By.xpath(excel.getData(2, 360, 1))).click();
		//Enter the Required option of Standard Entry Class Code
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys("CCD");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the	Credit/Debit option
		driver.findElement(By.xpath(excel.getData(2, 362, 1))).click();
		//Enter the Required option
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys("Both");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(2, 351, 1))).click();
		Thread.sleep(1500);
		
		Thread.sleep(3000);
		//Check weather the report is available for the selected time period
		if(driver.findElement(By.xpath(excel.getData(2, 352, 1))).isDisplayed())
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for City");
		}
		
		else if(driver.findElement(By.xpath(excel.getData(2, 364, 1))).getText() != null)
		{
			test.log(LogStatus.PASS, "Royalty Report is available for City(With Exclude Loyalty Redemtion)");
			
			Thread.sleep(3000);
			List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 1732, 1)));
			Thread.sleep(2000);
			//Get the Total amount value
			String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
			
			float total_AMT = Float.parseFloat(Total_Amount);
			
			String Net_Sale = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr[2]/td[3]")).getText().replaceAll("[a-zA-Z $ ₹ ,]", "");
			
			float netsale_AMT = Float.parseFloat(Net_Sale);
			
			//Print the Net sale value
			System.out.println("Netsale Amount for City(With Exclude Loyalty Redemtion) is : "+netsale_AMT);
			
			test.log(LogStatus.INFO, "Netsale Amount for City(With Exclude Loyalty Redemtion) is : "+netsale_AMT);	
			
			//Print the Total_Amount value
			System.out.println("Royalty Fee for City(With Exclude Loyalty Redemtion) is : "+total_AMT);
			
			test.log(LogStatus.INFO, "Royalty Fee for City(With Exclude Loyalty Redemtion) is : "+total_AMT);		
			
			float percen = (float)((netsale_AMT*5.0)/100.0);
			
		     DecimalFormat f = new DecimalFormat("##.00");
		     	    
		     String a = f.format(percen);
		     
		     float percentage = Float.parseFloat(a);
		     
			if(percentage == total_AMT)
			{
				test.log(LogStatus.PASS, "Royalty Fee for City(With Exclude Loyalty Redemtion) is Correct");
			}
			else
			{
				test.log(LogStatus.FAIL, "Royalty Fee for City(With Exclude Loyalty Redemtion) is Incorrect");
			}
				
		}
		
		else
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for City");
		}
		Thread.sleep(3000);
		wb.close();
	}
	
	@Test(enabled=false,priority=37)
	public void Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_State_With_ExcludeLoyaltyRedemtion(WebDriver driver) throws Exception
	{
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);
		//Click the First Option
		driver.findElement(By.xpath(excel.getData(2, 343, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys("State");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys(Keys.ENTER);
			
		Thread.sleep(1000);
		//Click the Second Option
		driver.findElement(By.xpath(excel.getData(2, 345, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Utility.getReportPropertyUser("State"));
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Time Period Option
		driver.findElement(By.xpath(excel.getData(2, 347, 1))).click();
		//Enter the Time option
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys("Date Range");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the from date option
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).clear();
		//Enter the from Date
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		Thread.sleep(1000);
		//Clear the To date option
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).clear();
		//Enter the To Date
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1500);
		if(driver.findElement(By.xpath(excel.getData(2, 356, 1))).isSelected()){}
		else
		{
		//Enable the ExcludeLoyaltyRedemtion button
		driver.findElement(By.xpath(excel.getData(2, 356, 1))).click();
		}
		
		Thread.sleep(1000);
		//Click the Standard Entry Class Code Option
		driver.findElement(By.xpath(excel.getData(2, 360, 1))).click();
		//Enter the Required option of Standard Entry Class Code
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys("PPD");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the	Credit/Debit option
		driver.findElement(By.xpath(excel.getData(2, 362, 1))).click();
		//Enter the Required option
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys("Debit");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(2, 351, 1))).click();
		Thread.sleep(1500);
		
		Thread.sleep(3000);
		//Check weather the report is available for the selected time period
		if(driver.findElement(By.xpath(excel.getData(2, 352, 1))).isDisplayed())
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for State");
		}
		
		else if(driver.findElement(By.xpath(excel.getData(2, 364, 1))).getText() != null)
		{
			test.log(LogStatus.PASS, "Royalty Report is available for State(With Exclude Loyalty Redemtion)");
			
			Thread.sleep(3000);
			List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 1732, 1)));

			Thread.sleep(2000);//Get the Total amount value
			String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
			
			float total_AMT = Float.parseFloat(Total_Amount);
			
			String Net_Sale = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr[2]/td[3]")).getText().replaceAll("[a-zA-Z $ ₹ ,]", "");
			
			float netsale_AMT = Float.parseFloat(Net_Sale);
			
			//Print the Net sale value
			System.out.println("Netsale Amount for State(With Exclude Loyalty Redemtion) is : "+netsale_AMT);
			
			test.log(LogStatus.INFO, "Netsale Amount for State(With Exclude Loyalty Redemtion) is : "+netsale_AMT);	
			
			//Print the Total_Amount value
			System.out.println("Royalty Fee for State(With Exclude Loyalty Redemtion) is : "+total_AMT);
			
			test.log(LogStatus.INFO, "Royalty Fee for State(With Exclude Loyalty Redemtion) is : "+total_AMT);		
			
			float percen = (float)((netsale_AMT*5.0)/100.0);
			
		     DecimalFormat f = new DecimalFormat("##.00");
		     	    
		     String a = f.format(percen);
		     
		     float percentage = Float.parseFloat(a);
		     
			if(percentage == total_AMT)
			{
				test.log(LogStatus.PASS, "Royalty Fee for State(With Exclude Loyalty Redemtion) is Correct");
			}
			else
			{
				test.log(LogStatus.FAIL, "Royalty Fee for State(With Exclude Loyalty Redemtion) is Incorrect");
			}
				
		}
		
		else
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for state");
		}
		Thread.sleep(3000);
		wb.close();
	}
	
	@Test(enabled=false,priority=38)
	public void Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Zipcode_With_ExcludeLoyaltyRedemtion(WebDriver driver) throws Exception
	{
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);
		//Click the First Option
		driver.findElement(By.xpath(excel.getData(2, 343, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys("Zip code");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys(Keys.ENTER);
			
		Thread.sleep(1000);
		//Click the Second Option
		driver.findElement(By.xpath(excel.getData(2, 345, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Utility.getReportPropertyUser("Zipcode"));
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Time Period Option
		driver.findElement(By.xpath(excel.getData(2, 347, 1))).click();
		//Enter the Time option
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys("Date Range");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the from date option
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).clear();
		//Enter the from Date
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		Thread.sleep(1000);
		//Clear the To date option
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).clear();
		//Enter the To Date
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1500);
		if(driver.findElement(By.xpath(excel.getData(2, 356, 1))).isSelected()){}
		else
		{
		//Enable the ExcludeLoyaltyRedemtion button
		driver.findElement(By.xpath(excel.getData(2, 356, 1))).click();
		}
		
		Thread.sleep(1000);
		//Click the Standard Entry Class Code Option
		driver.findElement(By.xpath(excel.getData(2, 360, 1))).click();
		//Enter the Required option of Standard Entry Class Code
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys("PPD");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the	Credit/Debit option
		driver.findElement(By.xpath(excel.getData(2, 362, 1))).click();
		//Enter the Required option
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys("Credit");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(2, 351, 1))).click();
		Thread.sleep(1500);
		
		Thread.sleep(3000);
		//Check weather the report is available for the selected time period
		if(driver.findElement(By.xpath(excel.getData(2, 352, 1))).isDisplayed())
		{
			test.log(LogStatus.INFO, "Royalty Report is not available  for Zipcode");
		}
		
		else if(driver.findElement(By.xpath(excel.getData(2, 364, 1))).getText() != null)
		{
			test.log(LogStatus.PASS, "Royalty Report is available for Zipcode(With Exclude Loyalty Redemtion)");
			
			Thread.sleep(3000);
			List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 1732, 1)));
			Thread.sleep(2000);

			//Get the Total amount value
			String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
			//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr[3]/td[7]
			float total_AMT = Float.parseFloat(Total_Amount);
			
			String Net_Sale = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr[2]/td[3]")).getText().replaceAll("[a-zA-Z $ ₹ ,]", "");
			
			float netsale_AMT = Float.parseFloat(Net_Sale);
			
			//Print the Net sale value
			System.out.println("Netsale Amount for Zipcode(With Exclude Loyalty Redemtion) is : "+netsale_AMT);
			
			test.log(LogStatus.INFO, "Netsale Amount for Zipcode(With Exclude Loyalty Redemtion) is : "+netsale_AMT);	
			
			//Print the Total_Amount value
			System.out.println("Royalty Fee for Zipcode(With Exclude Loyalty Redemtion) is : "+total_AMT);
			
			test.log(LogStatus.INFO, "Royalty Fee for Zipcode(With Exclude Loyalty Redemtion) is : "+total_AMT);		
			
			float percen = (float)((netsale_AMT*5.0)/100.0);
			
		     DecimalFormat f = new DecimalFormat("##.00");
		     	    
		     String a = f.format(percen);
		     
		     float percentage = Float.parseFloat(a);
		     
			if(percentage == total_AMT)
			{
				test.log(LogStatus.PASS, "Royalty Fee for Zipcode(With Exclude Loyalty Redemtion) is Correct");
			}
			else
			{
				test.log(LogStatus.FAIL, "Royalty Fee for Zipcode(With Exclude Loyalty Redemtion) is Incorrect");
			}
				
		}
		
		else
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for Zipcode");
		}
		Thread.sleep(3000);
		wb.close();
	}

	@Test(enabled=false,priority=39)
	public void Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Zipcode_With_ExcludeLoyaltyRedemtion_PPD_Both(WebDriver driver) throws Exception
	{
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);
		//Click the First Option
		driver.findElement(By.xpath(excel.getData(2, 343, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys("Zip code");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 344, 1))).sendKeys(Keys.ENTER);
			
		Thread.sleep(1000);
		//Click the Second Option
		driver.findElement(By.xpath(excel.getData(2, 345, 1))).click();
		//Enter the Group option
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Utility.getReportPropertyUser("Zipcode"));
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 346, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Time Period Option
		driver.findElement(By.xpath(excel.getData(2, 347, 1))).click();
		//Enter the Time option
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys("Date Range");
		//Click Enter button
		driver.findElement(By.xpath(excel.getData(2, 348, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the from date option
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).clear();
		//Enter the from Date
		driver.findElement(By.xpath(excel.getData(2, 349, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		Thread.sleep(1000);
		//Clear the To date option
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).clear();
		//Enter the To Date
		driver.findElement(By.xpath(excel.getData(2, 350, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1500);
		if(driver.findElement(By.xpath(excel.getData(2, 356, 1))).isSelected()){}
		else
		{
		//Enable the ExcludeLoyaltyRedemtion button
		driver.findElement(By.xpath(excel.getData(2, 356, 1))).click();
		}
		
		Thread.sleep(1000);
		//Click the Standard Entry Class Code Option
		driver.findElement(By.xpath(excel.getData(2, 360, 1))).click();
		//Enter the Required option of Standard Entry Class Code
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys("PPD");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 361, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the	Credit/Debit option
		driver.findElement(By.xpath(excel.getData(2, 362, 1))).click();
		//Enter the Required option
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys("Both");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(2, 363, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(2, 351, 1))).click();
		Thread.sleep(1500);
		
		Thread.sleep(3000);
		//Check weather the report is available for the selected time period
		if(driver.findElement(By.xpath(excel.getData(2, 352, 1))).isDisplayed())
		{
			test.log(LogStatus.INFO, "Royalty Report is not available  for Zipcode");
		}
		
		else if(driver.findElement(By.xpath(excel.getData(2, 364, 1))).getText() != null)
		{
			test.log(LogStatus.PASS, "Royalty Report is available for Zipcode(With Exclude Loyalty Redemtion)");
			
			Thread.sleep(3000);
			List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 1732, 1)));
			Thread.sleep(2000);

			//Get the Total amount value
			String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[7]")).getText();
			
			float total_AMT = Float.parseFloat(Total_Amount);
			
			String Net_Sale = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[5]/table/tbody/tr[2]/td[3]")).getText().replaceAll("[a-zA-Z $ ₹ ,]", "");
			 
			float netsale_AMT = Float.parseFloat(Net_Sale);
			
			//Print the Net sale value
			System.out.println("Netsale Amount for Zipcode(With Exclude Loyalty Redemtion) is : "+netsale_AMT);
			
			test.log(LogStatus.INFO, "Netsale Amount for Zipcode(With Exclude Loyalty Redemtion) is : "+netsale_AMT);	
			
			//Print the Total_Amount value
			System.out.println("Royalty Fee for Zipcode(With Exclude Loyalty Redemtion) is : "+total_AMT);
			
			test.log(LogStatus.INFO, "Royalty Fee for Zipcode(With Exclude Loyalty Redemtion) is : "+total_AMT);		
			
			float percen = (float)((netsale_AMT*5.0)/100.0);
			
		     DecimalFormat f = new DecimalFormat("##.00");
		     	    
		     String a = f.format(percen);
		     
		     float percentage = Float.parseFloat(a);
		     
			if(percentage == total_AMT)
			{
				test.log(LogStatus.PASS, "Royalty Fee for Zipcode(With Exclude Loyalty Redemtion) is Correct");
			}
			else
			{
				test.log(LogStatus.FAIL, "Royalty Fee for Zipcode(With Exclude Loyalty Redemtion) is Incorrect");
			}
				
		}
		
		else
		{
			test.log(LogStatus.INFO, "Royalty Report is not available for Zipcode");
		}
		wb.close();
		Thread.sleep(3000);
	}

}
