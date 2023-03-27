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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome_Account_User.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Verify_Enterprise_Sale_Summary_Report {
	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Enterprise_Sale_Summary_Report");

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
		//	SendMail.snedMailWithAttachment();
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
			Sale_Summary_Enterprise_Report_Method_For_openpage(driver);
			Sale_Summary_Enterprise_Report_Method_For_Stores(driver);
			Sale_Summary_Enterprise_Report_Method_For_Group(driver);
			Sale_Summary_Enterprise_Report_Method_For_City(driver);
			Sale_Summary_Enterprise_Report_Method_For_State(driver);
			Sale_Summary_Enterprise_Report_Method_For_ZipCode(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=23)
		public void Sale_Summary_Enterprise_Report_Method_For_openpage(WebDriver driver) throws Exception
		{

            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		/*	//Click the My Stores option
			driver.findElement(By.xpath("//span[.='My Stores']")).click();

			Thread.sleep(3000);
	        //Click the EnterPrise Reports Option		
			driver.findElement(By.xpath("//span[.='EnterPrise Reports']")).click();
			
			
			Thread.sleep(2000);
			//Click the Sale option
			driver.findElement(By.xpath("//span[.='Sale']")).click();	*/
			
			Thread.sleep(2000);
			//Enter the URl
			driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/saleSummary");
			Thread.sleep(15000);
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			
			Thread.sleep(3000);
			try
			{
			//Check Sale_Summary Report page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 768, 1))).getText().equalsIgnoreCase("Sale Summary"))
			{
				test.log(LogStatus.PASS, "Sale_Summary Report page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Sale_Summary Report page loaded Failed");
			
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
		
		@Test(enabled=false,priority=24)
		public void Sale_Summary_Enterprise_Report_Method_For_Stores(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(3000);
			System.out.println("********************* View The Store Report *************************");
			test.log(LogStatus.INFO, "********************* View The Store Report *************************");

			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Group");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
	
			Thread.sleep(1500);
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Stores");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);

			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			//Remove the store name
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[1]/div[2]/div/ul/li[1]/a")).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("Store"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Select Employee Options
			driver.findElement(By.xpath(excel.getData(3, 2234, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2235, 1))).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2235, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(3000);
			//Click the Service Type
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul")).click();
			//Enter the required Service type
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li/input")).sendKeys(Utility.getReportPropertyUser("Service_Type"));
			//Press the Enter button
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
			
			try
			{
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Sale Summary Report available for Specific Date");
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 769, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr"));
				        
				        int rowSize=rows.size();
				        System.out.println("Row size : "+rowSize);
		                 //Print number of Rows
				        //System.out.println("Number of Rows are : "+rows);
		        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Net_Sale element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Inclusive_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Inclusive_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  

			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("CC_Service_Charge").replace(",", "");
			        	
			        	//Convert the String value of the CC_Service_Charge element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect5 = Float.parseFloat(expected5);  
			        	
			        	//Replace all commo's with empty space
			        	String expected6 = Utility.getReportPropertyUser("Gross_Receipt").replace(",", "");
			        	
			        	//Convert the String value of the Gross_Receipt element into float value
			        	float expect6 = Float.parseFloat(expected6); 
				       
			        	WebElement netSalf=driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rowSize+"]/td[6]/span"));

			        	
			        	JavascriptExecutor js=(JavascriptExecutor)driver;
			        	js.executeScript("argument[0].scrollIntoView(true);", netSalf);
			        	String netSal=driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rowSize+"]/td[6]/span")).getText();
			        	System.out.println("Taken Net Sale    "+netSal);
				        //Check weather the Net Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Net Sale Amount");
				        	
				        	//Get the Total value of Net Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Net Sale Amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Net Sale Amount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Net Sale Amount Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();

				        	System.out.println("The Actual Net Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Net Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the net sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Net Sale Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Net Sale Amount Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Tax reports are same for Quantity");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total Tax
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Tax");
				        	
				        	//Get the Total Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
				        //Check weather the Inclusive Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Inclusive_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Inclusive Tax");
				        	
				        	//Get the Total value of Inclusive Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	

				        	//Convert the String value of the Inclusive Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Inclusive Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Inclusive Tax Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();

				        	System.out.println("The Actual Inclusive Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Inclusive Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Inclusive Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
				        //Check weather the CC Service Charge Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText().equals(Utility.getReportPropertyUser("CC_Service_Charge")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Discount");
				        	
				        	//Get the Total value of CC Service Charge
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the CC Service Charge Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual CC Service Charge Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual CC Service Charge Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of CC Service Charge
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();

				        	System.out.println("The Actual CC Service Charge value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual CC Service Charge value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for CC Service Charge");
				        	
				        	//Get the Total value of CC Service Charge
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the CC Service Charge Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("CC Service Charge Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "CC Service Charge Value different is : "+different);	
				        }
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Discount");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }

				      //Check weather the Gross Receipt Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText().equals(Utility.getReportPropertyUser("Gross_Receipt")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Gross Receipt");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Gross Receipt Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Gross Receipt Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Gross Receipt
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText();

				        	System.out.println("The Actual Gross Receipt value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Gross Receipt value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Gross");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Gross Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect6;
				        	
				        	//Print the different value
				        	System.out.println("Gross Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Gross Value different is : "+different);	
				        }

				        
				       /* //Get the Gross Receipt value
				        String a = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText().replaceAll("[a-zA-Z $ â‚¹ , :]", "");
				        
				        //Convert the String value to float value
				        float aq = Float.parseFloat(a);
				        
				        aq = aq - expect6;
				        //System.out.println(aq);
				        
				        String s = String.valueOf(aq);
				        
				        //Check weather the Gross Receipt Report is correct or not
				        if(s.equals(Utility.getReportPropertyUser("Gross_Receipt").replaceAll("[a-zA-Z $ â‚¹ , :]", "")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Gross Receipt");
				        	
				        	//Get the Total value of Gross Receipt
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Gross Receipt Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Gross Receipt Value is : "+actual);
				        	
				          	test.log(LogStatus.PASS, "The Actual Gross Receipt Value is : "+ actual);
				        }
				        
						else if(expect6 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage Of Sale
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();

				        	System.out.println("The Actual Gross Receipt value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Gross Receipt value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Gross Receipt");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage Of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - aq;
				        	
				        	//Print the different value
				        	System.out.println("Gross Receipt Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Gross Receipt Value different is : "+different);	
				        }*/
				        
				   //Get the Detail for Each and Every Check
				        for(int i = 1; i < 2; i++)
				        {
				        	Thread.sleep(3000);
				        	//Click the required check
				        	driver.findElement(By.xpath(excel.getData(3, 777, 1))).click();
				        	
				        	
				        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				        	Thread.sleep(3000);
				        	//Clear the mail field
				        	driver.findElement(By.xpath(excel.getData(3, 778, 1))).clear();
				        	//Enter the required mail
				        	driver.findElement(By.xpath(excel.getData(3, 778, 1))).sendKeys("sappanimuthub@lingapos.com");

				        	
				        	Thread.sleep(3000);
				        	System.out.println("The Check ("+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText()+") have the following details :");
				        	
				        	test.log(LogStatus.INFO, "The Check ("+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText()+") have the following details :");
				       
				        	System.out.println("Check Number is : "+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Check Number is : "+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText());
				        	
				        	System.out.println("Date is : "+driver.findElement(By.xpath(excel.getData(3, 780, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Date is : "+driver.findElement(By.xpath(excel.getData(3, 780, 1))).getText());

				        	System.out.println("Table is : "+driver.findElement(By.xpath(excel.getData(3, 781, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Table is : "+driver.findElement(By.xpath(excel.getData(3, 781, 1))).getText());

				        	System.out.println("Seat Number is : "+driver.findElement(By.xpath(excel.getData(3, 782, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Seat Number is : "+driver.findElement(By.xpath(excel.getData(3, 782, 1))).getText());

				        	System.out.println("Server Name is : "+driver.findElement(By.xpath(excel.getData(3, 783, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Server Name is : "+driver.findElement(By.xpath(excel.getData(3, 783, 1))).getText());

				        	System.out.println("Gratuity is : "+driver.findElement(By.xpath(excel.getData(3, 784, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Gratuity is : "+driver.findElement(By.xpath(excel.getData(3, 784, 1))).getText());

				        	System.out.println("Discount is : "+driver.findElement(By.xpath(excel.getData(3, 785, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Discount is : "+driver.findElement(By.xpath(excel.getData(3, 785, 1))).getText());

				        	System.out.println("CC Service Charge is : "+driver.findElement(By.xpath(excel.getData(3, 786, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "CC Service Charge is : "+driver.findElement(By.xpath(excel.getData(3, 786, 1))).getText());

				        	System.out.println("Total is : "+driver.findElement(By.xpath(excel.getData(3, 787, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Total is : "+driver.findElement(By.xpath(excel.getData(3, 787, 1))).getText());

				        	System.out.println("Total Tip is : "+driver.findElement(By.xpath(excel.getData(3, 788, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Total Tip is : "+driver.findElement(By.xpath(excel.getData(3, 788, 1))).getText());


				        	Thread.sleep(2000);
					    	//Check Whether the Send Receipt is enabled or not
					    	if(driver.findElement(By.xpath(excel.getData(3, 789, 1))).isEnabled())
					    	{
					    	Thread.sleep(3000);
					    	//Click the Send Receipt
					    	driver.findElement(By.xpath(excel.getData(3, 789, 1))).click();
					    	
							    	Thread.sleep(5000);
							    	//Check whether the receipt sent successfully or not
							    	if(driver.findElement(By.cssSelector(excel.getData(3, 239, 4))).getText().equalsIgnoreCase("Email receipt sent successfully"))
							    	{
							    		test.log(LogStatus.PASS, "Email Receipt sent successfully for store");
							    	
							    	
							    	
							    	}
							    	
							    	else
							    	{
							    		test.log(LogStatus.FAIL, "Email Receipt sent fail for store");
							    	}
					    	}
					    	
					    	//Get the row size of tax table
					    	List<WebElement> taxRow = driver.findElements(By.xpath(excel.getData(3, 790, 1)));
					    	
					    		Thread.sleep(3000);
					    		System.out.println("Total Tax amount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[3]/div/div[1]/table/tbody/tr["+taxRow.size()+"]/td[2]")).getText());
					    		
					    		test.log(LogStatus.PASS, "Total Tax amount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[3]/div/div[1]/table/tbody/tr["+taxRow.size()+"]/td[2]")).getText());
					    	
					    	//Check whether the Payment summary is available or not
					    	if(driver.findElement(By.xpath(excel.getData(3, 792, 1))) != null)
					    	{
					    		test.log(LogStatus.PASS, "Payment Summary report is available");
					    	}
					    	
					    	else
					    	{
					    		test.log(LogStatus.FAIL, "Payment Summary report is not available");
					    	}
					    	
					    	//Get the row size of order summary table
					    	List<WebElement> orderSummaryRow = driver.findElements(By.xpath(excel.getData(3, 793, 1)));
					    	
					    		Thread.sleep(3000);
					    		System.out.println("Order Summary subtotal is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[2]/div/div[2]/table/tbody/tr["+orderSummaryRow.size()+"]/td[2]")).getText());
					    		
					    		test.log(LogStatus.PASS, "Order Summary subtotal is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[2]/div/div[2]/table/tbody/tr["+orderSummaryRow.size()+"]/td[2]")).getText());
				
					    		Thread.sleep(3000);
					    		
					       	
					    		String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
								String s="data:image/png;base64,"+scnShot;
								test.log(LogStatus.INFO,test.addScreenCapture(s));

							

					    		
					    		//Click the Back button
					    		driver.findElement(By.xpath(excel.getData(3, 795, 1))).click();
					    		
					    		Thread.sleep(2000);
					    	
				        }

					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date");
					
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					

					
					}
					
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Sale_Summary Sale Report is not available for Specific Date");

				
		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			Thread.sleep(3000);
			System.out.println("********************* End of Store Report *************************");
			test.log(LogStatus.INFO, "********************* End of Store Report *************************");wb.close();

			
		}
		
		@Test(enabled=false,priority=25)
		public void Sale_Summary_Enterprise_Report_Method_For_Group(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(3000);
			System.out.println("********************* View The Group Report *************************");
			test.log(LogStatus.INFO, "********************* View The Group Report *************************");
			
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Group");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("Group"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Select Employee Options
			driver.findElement(By.xpath(excel.getData(3, 2234, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2235, 1))).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2235, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(3000);
			//Click the Service Type
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul")).click();
			//Remove old the Service Type
		    //driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li[1]/a")).click();
			//Enter the required Service type
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li/input")).sendKeys(Utility.getReportPropertyUser("Service_Type"));
			//Press the Enter button
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(3000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
			
			try
			{
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Sale Summary Report available for Specific Date");
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 769, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Net_Sale element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Inclusive_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Inclusive_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  

			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("CC_Service_Charge").replace(",", "");
			        	
			        	//Convert the String value of the CC_Service_Charge element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect5 = Float.parseFloat(expected5);  
			        	
			        	//Replace all commo's with empty space
			        	String expected6 = Utility.getReportPropertyUser("Gross_Receipt").replace(",", "");
			        	
			        	//Convert the String value of the Gross_Receipt element into float value
			        	float expect6 = Float.parseFloat(expected6); 
				        
				        //Check weather the Net Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Net Sale Amount");
				        	
				        	//Get the Total value of Net Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Net Sale Amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Net Sale Amount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Net Sale Amount Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();

				        	System.out.println("The Actual Net Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Net Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the net sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Net Sale Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Net Sale Amount Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Tax reports are same for Quantity");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Tax");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
				        //Check weather the Inclusive Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Inclusive_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Inclusive Tax");
				        	
				        	//Get the Total value of Inclusive Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	

				        	//Convert the String value of the Inclusive Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Inclusive Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Inclusive Tax Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();

				        	System.out.println("The Actual Inclusive Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Inclusive Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Inclusive Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
				        //Check weather the CC Service Charge Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText().equals(Utility.getReportPropertyUser("CC_Service_Charge")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Discount");
				        	
				        	//Get the Total value of CC Service Charge
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the CC Service Charge Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual CC Service Charge Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual CC Service Charge Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of CC Service Charge
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();

				        	System.out.println("The Actual CC Service Charge value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual CC Service Charge value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for CC Service Charge");
				        	
				        	//Get the Total value of CC Service Charge
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the CC Service Charge Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("CC Service Charge Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "CC Service Charge Value different is : "+different);	
				        }
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Discount");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
					      //Check weather the Gross Receipt Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText().equals(Utility.getReportPropertyUser("Gross_Receipt")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Gross Receipt");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Gross Receipt Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Gross Receipt Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Gross Receipt
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText();

				        	System.out.println("The Actual Gross Receipt value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Gross Receipt value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Discount");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Gross Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect6;
				        	
				        	//Print the different value
				        	System.out.println("Gross Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Gross Value different is : "+different);	
				        }
				        
						   //Get the Detail for Each and Every Check
				        for(int i = 1; i < 2; i++)
				        {
				        	Thread.sleep(3000);
				        	//Click the required check
				        	driver.findElement(By.xpath(excel.getData(3, 777, 1))).click();
				        	
				        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				        	Thread.sleep(3000);
				        	//Clear the mail field
				        	driver.findElement(By.xpath(excel.getData(3, 778, 1))).clear();
				        	//Enter the required mail
				        	driver.findElement(By.xpath(excel.getData(3, 778, 1))).sendKeys("sappanimuthub@lingapos.com");

				        	Thread.sleep(3000);
				        	System.out.println("The Check ("+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText()+") have the following details :");
				        	
				        	test.log(LogStatus.INFO, "The Check ("+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText()+") have the following details :");
				       
				        	System.out.println("Check Number is : "+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Check Number is : "+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText());
				        	
				        	System.out.println("Date is : "+driver.findElement(By.xpath(excel.getData(3, 780, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Date is : "+driver.findElement(By.xpath(excel.getData(3, 780, 1))).getText());

				        	System.out.println("Table is : "+driver.findElement(By.xpath(excel.getData(3, 781, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Table is : "+driver.findElement(By.xpath(excel.getData(3, 781, 1))).getText());

				        	System.out.println("Seat Number is : "+driver.findElement(By.xpath(excel.getData(3, 782, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Seat Number is : "+driver.findElement(By.xpath(excel.getData(3, 782, 1))).getText());

				        	System.out.println("Server Name is : "+driver.findElement(By.xpath(excel.getData(3, 783, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Server Name is : "+driver.findElement(By.xpath(excel.getData(3, 783, 1))).getText());

				        	System.out.println("Gratuity is : "+driver.findElement(By.xpath(excel.getData(3, 784, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Gratuity is : "+driver.findElement(By.xpath(excel.getData(3, 784, 1))).getText());

				        	System.out.println("Discount is : "+driver.findElement(By.xpath(excel.getData(3, 785, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Discount is : "+driver.findElement(By.xpath(excel.getData(3, 785, 1))).getText());

				        System.out.println("CC Service Charge is : "+driver.findElement(By.xpath(excel.getData(3, 786, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "CC Service Charge is : "+driver.findElement(By.xpath(excel.getData(3, 786, 1))).getText());

				        	System.out.println("Total is : "+driver.findElement(By.xpath(excel.getData(3, 787, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Total is : "+driver.findElement(By.xpath(excel.getData(3, 787, 1))).getText());

				        	System.out.println("Total Tip is : "+driver.findElement(By.xpath(excel.getData(3, 788, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Total Tip is : "+driver.findElement(By.xpath(excel.getData(3, 788, 1))).getText());

				        	Thread.sleep(3000);
					    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					    	
					    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
							String s="data:image/png;base64,"+scnShot;
							test.log(LogStatus.INFO,test.addScreenCapture(s));

							Thread.sleep(3000);
							driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);


				        	
				        	Thread.sleep(2000);
					    	//Check Whether the Send Receipt is enabled or not
					    	if(driver.findElement(By.xpath(excel.getData(3, 789, 1))).isEnabled())
					    	{
					    	Thread.sleep(3000);
					    	//Click the Send Receipt
					    	driver.findElement(By.xpath(excel.getData(3, 789, 1))).click();
					    	
							    	Thread.sleep(5000);
							    	//Check whether the receipt sent successfully or not
							    	if(driver.findElement(By.cssSelector(excel.getData(3, 239, 4))).getText().equalsIgnoreCase("Email receipt sent successfully"))
							    	{
							    		test.log(LogStatus.PASS, "Email Receipt sent successfully");
							    	}
							    	
							    	else
							    	{
							    		test.log(LogStatus.FAIL, "Email Receipt sent fail");
							    	}
					    	}
					    	
					    	//Get the row size of tax table
					    	List<WebElement> taxRow = driver.findElements(By.xpath(excel.getData(3, 790, 1)));
					    	
					    		Thread.sleep(3000);
					    		System.out.println("Total Tax amount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[3]/div/div[1]/table/tbody/tr["+taxRow.size()+"]/td[2]")).getText());
					    		
					    		test.log(LogStatus.PASS, "Total Tax amount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[3]/div/div[1]/table/tbody/tr["+taxRow.size()+"]/td[2]")).getText());
					    	
					    	//Check whether the Payment summary is available or not
					    	if(driver.findElement(By.xpath(excel.getData(3, 792, 1))) != null)
					    	{
					    		test.log(LogStatus.PASS, "Payment Summary report is available");
					    	}
					    	
					    	else
					    	{
					    		test.log(LogStatus.FAIL, "Payment Summary report is not available");
					    	}
					    	
					    	//Get the row size of order summary table
					    	List<WebElement> orderSummaryRow = driver.findElements(By.xpath(excel.getData(3, 793, 1)));
					    	
					    		Thread.sleep(3000);
					    		System.out.println("Order Summary subtotal is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[2]/div/div[2]/table/tbody/tr["+orderSummaryRow.size()+"]/td[2]")).getText());
					    		
					    		test.log(LogStatus.PASS, "Order Summary subtotal is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[2]/div/div[2]/table/tbody/tr["+orderSummaryRow.size()+"]/td[2]")).getText());
				
					    		Thread.sleep(3000);
					    		
					    		//Click the Back button
					    		driver.findElement(By.xpath(excel.getData(3, 795, 1))).click();
					    		
					    		Thread.sleep(2000);
					    	
				        }
					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date for Group");
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));


					}
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Sale_Summary Sale Report is not available for Specific Date for Group");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(3000);
			System.out.println("********************* End of Group Report *************************");
			test.log(LogStatus.INFO, "********************* End of Group Report *************************");wb.close();
		}
		
		@Test(enabled=false,priority=26)
		public void Sale_Summary_Enterprise_Report_Method_For_City(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			System.out.println("********************* View The City Report *************************");
			test.log(LogStatus.INFO, "********************* View The City Report *************************");

			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("City");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("City"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Select Employee Options
			driver.findElement(By.xpath(excel.getData(3, 2234, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2235, 1))).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2235, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(3000);
			//Click the Service Type
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul")).click();
			//Remove old the Service Type
		    //driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li[1]/a")).click();
			//Enter the required Service type
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li/input")).sendKeys(Utility.getReportPropertyUser("Service_Type"));
			//Press the Enter button
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(3000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
			
			try
			{
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Sale Summary Report available for Specific Date");
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 769, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Net_Sale element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Inclusive_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Inclusive_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  

			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("CC_Service_Charge").replace(",", "");
			        	
			        	//Convert the String value of the CC_Service_Charge element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect5 = Float.parseFloat(expected5);  
			        	
			        	//Replace all commo's with empty space
			        	String expected6 = Utility.getReportPropertyUser("Gross_Receipt").replace(",", "");
			        	
			        	//Convert the String value of the Gross_Receipt element into float value
			        	float expect6 = Float.parseFloat(expected6); 
				        
				        //Check weather the Net Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Net Sale Amount");
				        	
				        	//Get the Total value of Net Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Net Sale Amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Net Sale Amount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Net Sale Amount Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();

				        	System.out.println("The Actual Net Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Net Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the net sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Net Sale Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Net Sale Amount Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Tax reports are same for Quantity");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Tax");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
				        //Check weather the Inclusive Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Inclusive_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Inclusive Tax");
				        	
				        	//Get the Total value of Inclusive Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	

				        	//Convert the String value of the Inclusive Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Inclusive Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Inclusive Tax Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();

				        	System.out.println("The Actual Inclusive Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Inclusive Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Inclusive Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
				        //Check weather the CC Service Charge Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText().equals(Utility.getReportPropertyUser("CC_Service_Charge")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Discount");
				        	
				        	//Get the Total value of CC Service Charge
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the CC Service Charge Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual CC Service Charge Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual CC Service Charge Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of CC Service Charge
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();

				        	System.out.println("The Actual CC Service Charge value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual CC Service Charge value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for CC Service Charge");
				        	
				        	//Get the Total value of CC Service Charge
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the CC Service Charge Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("CC Service Charge Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "CC Service Charge Value different is : "+different);	
				        }
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Discount");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
				        
					      //Check weather the Gross Receipt Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText().equals(Utility.getReportPropertyUser("Gross_Receipt")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Gross Receipt");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Gross Receipt Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Gross Receipt Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Gross Receipt
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText();

				        	System.out.println("The Actual Gross Receipt value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Gross Receipt value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Discount");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Gross Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect6;
				        	
				        	//Print the different value
				        	System.out.println("Gross Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Gross Value different is : "+different);	
				        }
				        
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);


						   //Get the Detail for Each and Every Check
				        for(int i = 1; i < 2; i++)
				        {
				        	Thread.sleep(3000);
				        	//Click the required check
				        	driver.findElement(By.xpath(excel.getData(3, 777, 1))).click();
				        	
				        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				        	Thread.sleep(3000);
				        	//Clear the mail field
				        	driver.findElement(By.xpath(excel.getData(3, 778, 1))).clear();
				        	//Enter the required mail
				        	driver.findElement(By.xpath(excel.getData(3, 778, 1))).sendKeys("sappanimuthub@lingapos.com");

				        	Thread.sleep(3000);
				        	System.out.println("The Check ("+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText()+") have the following details :");
				        	
				        	test.log(LogStatus.INFO, "The Check ("+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText()+") have the following details :");
				       
				        	System.out.println("Check Number is : "+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Check Number is : "+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText());
				        	
				        	System.out.println("Date is : "+driver.findElement(By.xpath(excel.getData(3, 780, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Date is : "+driver.findElement(By.xpath(excel.getData(3, 780, 1))).getText());

				        	System.out.println("Table is : "+driver.findElement(By.xpath(excel.getData(3, 781, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Table is : "+driver.findElement(By.xpath(excel.getData(3, 781, 1))).getText());

				        	System.out.println("Seat Number is : "+driver.findElement(By.xpath(excel.getData(3, 782, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Seat Number is : "+driver.findElement(By.xpath(excel.getData(3, 782, 1))).getText());

				        	System.out.println("Server Name is : "+driver.findElement(By.xpath(excel.getData(3, 783, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Server Name is : "+driver.findElement(By.xpath(excel.getData(3, 783, 1))).getText());

				        	System.out.println("Gratuity is : "+driver.findElement(By.xpath(excel.getData(3, 784, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Gratuity is : "+driver.findElement(By.xpath(excel.getData(3, 784, 1))).getText());

				        	System.out.println("Discount is : "+driver.findElement(By.xpath(excel.getData(3, 785, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Discount is : "+driver.findElement(By.xpath(excel.getData(3, 785, 1))).getText());

				        	System.out.println("CC Service Charge is : "+driver.findElement(By.xpath(excel.getData(3, 786, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "CC Service Charge is : "+driver.findElement(By.xpath(excel.getData(3, 786, 1))).getText());

				        	System.out.println("Total is : "+driver.findElement(By.xpath(excel.getData(3, 787, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Total is : "+driver.findElement(By.xpath(excel.getData(3, 787, 1))).getText());

				        	System.out.println("Total Tip is : "+driver.findElement(By.xpath(excel.getData(3, 788, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Total Tip is : "+driver.findElement(By.xpath(excel.getData(3, 788, 1))).getText());


				        	Thread.sleep(2000);
					    	//Check Whether the Send Receipt is enabled or not
					    	if(driver.findElement(By.xpath(excel.getData(3, 789, 1))).isEnabled())
					    	{
					    	Thread.sleep(3000);
					    	//Click the Send Receipt
					    	driver.findElement(By.xpath(excel.getData(3, 789, 1))).click();
					    	
							    	Thread.sleep(5000);
							    	//Check whether the receipt sent successfully or not
							    	if(driver.findElement(By.cssSelector(excel.getData(3, 239, 4))).getText().equalsIgnoreCase("Email receipt sent successfully"))
							    	{
							    		test.log(LogStatus.PASS, "Email Receipt sent successfully");
							    
								    	
								    	Utility us1=new Utility();
										us1.capture(driver);
										
										test.log(LogStatus.INFO, test.addScreenCapture(us1.capture(driver)));


							    	}
							    	
							    	else
							    	{
							    		test.log(LogStatus.FAIL, "Email Receipt sent fail");
							    	}
					    	}
					    	
					    	//Get the row size of tax table
					    	List<WebElement> taxRow = driver.findElements(By.xpath(excel.getData(3, 790, 1)));
					    	
					    		Thread.sleep(3000);
					    		System.out.println("Total Tax amount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[3]/div/div[1]/table/tbody/tr["+taxRow.size()+"]/td[2]")).getText());
					    		
					    		test.log(LogStatus.PASS, "Total Tax amount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[3]/div/div[1]/table/tbody/tr["+taxRow.size()+"]/td[2]")).getText());
					    	
					    	//Check whether the Payment summary is available or not
					    	if(driver.findElement(By.xpath(excel.getData(3, 792, 1))) != null)
					    	{
					    		test.log(LogStatus.PASS, "Payment Summary report is available");
					    	}
					    	
					    	else
					    	{
					    		test.log(LogStatus.FAIL, "Payment Summary report is not available");
					    	}
					    	
					    	//Get the row size of order summary table
					    	List<WebElement> orderSummaryRow = driver.findElements(By.xpath(excel.getData(3, 793, 1)));
					    	
					    		Thread.sleep(3000);
					    		System.out.println("Order Summary subtotal is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[2]/div/div[2]/table/tbody/tr["+orderSummaryRow.size()+"]/td[2]")).getText());
					    		
					    		test.log(LogStatus.PASS, "Order Summary subtotal is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[2]/div/div[2]/table/tbody/tr["+orderSummaryRow.size()+"]/td[2]")).getText());
				
					    		Thread.sleep(3000);
					    		
					    		//Click the Back button
					    		driver.findElement(By.xpath(excel.getData(3, 795, 1))).click();
					    		
					    		Thread.sleep(2000);
					    	
				        }
					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date for City");
					
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));


					
					}
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Sale_Summary Sale Report is not available for Specific Date for City");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(3000);
			System.out.println("********************* End of City Report *************************");
			test.log(LogStatus.INFO, "********************* End of City Report *************************");wb.close();
		}
			
		@Test(enabled=false,priority=27)
		public void Sale_Summary_Enterprise_Report_Method_For_State(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(3000);
			System.out.println("********************* View The State Report *************************");
			test.log(LogStatus.INFO, "********************* View The State Report *************************");

			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("State");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("State"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Select Employee Options
			driver.findElement(By.xpath(excel.getData(3, 2234, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2235, 1))).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2235, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(3000);
			//Click the Service Type
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul")).click();
			//Remove old the Service Type
		    //driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li[1]/a")).click();
			//Enter the required Service type
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li/input")).sendKeys(Utility.getReportPropertyUser("Service_Type"));
			//Press the Enter button
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
			
			try
			{
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Sale Summary Report available for Specific Date");
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 769, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Net_Sale element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Inclusive_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Inclusive_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  

			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("CC_Service_Charge").replace(",", "");
			        	
			        	//Convert the String value of the CC_Service_Charge element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect5 = Float.parseFloat(expected5);  
			        	
			        	//Replace all commo's with empty space
			        	String expected6 = Utility.getReportPropertyUser("Gross_Receipt").replace(",", "");
			        	
			        	//Convert the String value of the Gross_Receipt element into float value
			        	float expect6 = Float.parseFloat(expected6); 
				        
				        //Check weather the Net Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Net Sale Amount");
				        	
				        	//Get the Total value of Net Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Net Sale Amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Net Sale Amount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Net Sale Amount Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();

				        	System.out.println("The Actual Net Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Net Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the net sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Net Sale Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Net Sale Amount Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Tax reports are same for Quantity");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Tax");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
				        //Check weather the Inclusive Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Inclusive_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Inclusive Tax");
				        	
				        	//Get the Total value of Inclusive Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	

				        	//Convert the String value of the Inclusive Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Inclusive Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Inclusive Tax Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();

				        	System.out.println("The Actual Inclusive Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Inclusive Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Inclusive Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
				        //Check weather the CC Service Charge Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText().equals(Utility.getReportPropertyUser("CC_Service_Charge")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Discount");
				        	
				        	//Get the Total value of CC Service Charge
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the CC Service Charge Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual CC Service Charge Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual CC Service Charge Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of CC Service Charge
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();

				        	System.out.println("The Actual CC Service Charge value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual CC Service Charge value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for CC Service Charge");
				        	
				        	//Get the Total value of CC Service Charge
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the CC Service Charge Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("CC Service Charge Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "CC Service Charge Value different is : "+different);	
				        }
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Discount");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
				        
					      //Check weather the Gross Receipt Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText().equals(Utility.getReportPropertyUser("Gross_Receipt")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Gross Receipt");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Gross Receipt Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Gross Receipt Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Gross Receipt
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText();

				        	System.out.println("The Actual Gross Receipt value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Gross Receipt value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Discount");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Gross Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect6;
				        	
				        	//Print the different value
				        	System.out.println("Gross Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Gross Value different is : "+different);	
				        }
				        
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);


				        
				        
				        
						   //Get the Detail for Each and Every Check
				        for(int i = 1; i < 2; i++)
				        {
				        	Thread.sleep(3000);
				        	//Click the required check
				        	driver.findElement(By.xpath(excel.getData(3, 777, 1))).click();
				        	
				        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				        	Thread.sleep(3000);
				        	//Clear the mail field
				        	driver.findElement(By.xpath(excel.getData(3, 778, 1))).clear();
				        	//Enter the required mail
				        	driver.findElement(By.xpath(excel.getData(3, 778, 1))).sendKeys("sappanimuthub@lingapos.com");
				        	
				        	Thread.sleep(3000);
				        	System.out.println("The Check ("+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText()+") have the following details :");
				        	
				        	test.log(LogStatus.INFO, "The Check ("+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText()+") have the following details :");
				       
				        	System.out.println("Check Number is : "+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Check Number is : "+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText());
				        	
				        	System.out.println("Date is : "+driver.findElement(By.xpath(excel.getData(3, 780, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Date is : "+driver.findElement(By.xpath(excel.getData(3, 780, 1))).getText());

				        	System.out.println("Table is : "+driver.findElement(By.xpath(excel.getData(3, 781, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Table is : "+driver.findElement(By.xpath(excel.getData(3, 781, 1))).getText());

				        	System.out.println("Seat Number is : "+driver.findElement(By.xpath(excel.getData(3, 782, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Seat Number is : "+driver.findElement(By.xpath(excel.getData(3, 782, 1))).getText());

				        	System.out.println("Server Name is : "+driver.findElement(By.xpath(excel.getData(3, 783, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Server Name is : "+driver.findElement(By.xpath(excel.getData(3, 783, 1))).getText());

				        	System.out.println("Gratuity is : "+driver.findElement(By.xpath(excel.getData(3, 784, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Gratuity is : "+driver.findElement(By.xpath(excel.getData(3, 784, 1))).getText());

				        	System.out.println("Discount is : "+driver.findElement(By.xpath(excel.getData(3, 785, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Discount is : "+driver.findElement(By.xpath(excel.getData(3, 785, 1))).getText());

				        	System.out.println("CC Service Charge is : "+driver.findElement(By.xpath(excel.getData(3, 786, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "CC Service Charge is : "+driver.findElement(By.xpath(excel.getData(3, 786, 1))).getText());

				        	System.out.println("Total is : "+driver.findElement(By.xpath(excel.getData(3, 787, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Total is : "+driver.findElement(By.xpath(excel.getData(3, 787, 1))).getText());

				        	System.out.println("Total Tip is : "+driver.findElement(By.xpath(excel.getData(3, 788, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Total Tip is : "+driver.findElement(By.xpath(excel.getData(3, 788, 1))).getText());


				        	Thread.sleep(2000);
					    	//Check Whether the Send Receipt is enabled or not
					    	if(driver.findElement(By.xpath(excel.getData(3, 789, 1))).isEnabled())
					    	{
					    	Thread.sleep(3000);
					    	//Click the Send Receipt
					    	driver.findElement(By.xpath(excel.getData(3, 789, 1))).click();
					    	
							    	Thread.sleep(5000);
							    	//Check whether the receipt sent successfully or not
							    	if(driver.findElement(By.cssSelector(excel.getData(3, 239, 4))).getText().equalsIgnoreCase("Email receipt sent successfully"))
							    	{
							    		test.log(LogStatus.PASS, "Email Receipt sent successfully");
							    	}
							    	
							    	else
							    	{
							    		test.log(LogStatus.FAIL, "Email Receipt sent fail");
							    	}
					    	}
					    	
					    	//Get the row size of tax table
					    	List<WebElement> taxRow = driver.findElements(By.xpath(excel.getData(3, 790, 1)));
					    	
					    		Thread.sleep(3000);
					    		System.out.println("Total Tax amount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[3]/div/div[1]/table/tbody/tr["+taxRow.size()+"]/td[2]")).getText());
					    		
					    		test.log(LogStatus.PASS, "Total Tax amount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[3]/div/div[1]/table/tbody/tr["+taxRow.size()+"]/td[2]")).getText());
					    	
					    	//Check whether the Payment summary is available or not
					    	if(driver.findElement(By.xpath(excel.getData(3, 792, 1))) != null)
					    	{
					    		test.log(LogStatus.PASS, "Payment Summary report is available");
					    	}
					    	
					    	else
					    	{
					    		test.log(LogStatus.FAIL, "Payment Summary report is not available");
					    	}
					    	
					    	//Get the row size of order summary table
					    	List<WebElement> orderSummaryRow = driver.findElements(By.xpath(excel.getData(3, 793, 1)));
					    	
					    		Thread.sleep(3000);
					    		System.out.println("Order Summary subtotal is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[2]/div/div[2]/table/tbody/tr["+orderSummaryRow.size()+"]/td[2]")).getText());
					    		
					    		test.log(LogStatus.PASS, "Order Summary subtotal is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[2]/div/div[2]/table/tbody/tr["+orderSummaryRow.size()+"]/td[2]")).getText());
				
					    		Thread.sleep(3000);
					    		
					        	
						    	Utility us1=new Utility();
								us1.capture(driver);
								
								test.log(LogStatus.INFO, test.addScreenCapture(us1.capture(driver)));


					    		
					    		//Click the Back button
					    		driver.findElement(By.xpath(excel.getData(3, 795, 1))).click();
					    		
					    		Thread.sleep(2000);
					    	
				        }
					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date for City");
					
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));


					
					}
				}
			}

			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Sale_Summary Sale Report is not available for Specific Date for State");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(3000);
			System.out.println("********************* End of State Report *************************");
			test.log(LogStatus.INFO, "********************* End of State Report *************************");wb.close();
		}

		@Test(enabled=false,priority=28)
		public void Sale_Summary_Enterprise_Report_Method_For_ZipCode(WebDriver driver) throws Exception
		{  
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));


			Thread.sleep(3000);
			System.out.println("********************* View The Zip Code Report *************************");
			test.log(LogStatus.INFO, "********************* View The Zip Code Report *************************");
			
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Zip Code");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			Thread.sleep(500);
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("Zipcode"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Select Employee Options
			driver.findElement(By.xpath(excel.getData(3, 2234, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2235, 1))).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2235, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(3000);
			//Click the Service Type
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul")).click();
			
			//Remove old the Service Type
		    //driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li[1]/a")).click();
			
			//Enter the required Service type  
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li/input")).sendKeys(Utility.getReportPropertyUser("Service_Type"));
			//Press the Enter button
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(3000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
			
			
			try
			{
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Sale Summary Report available for Specific Date");
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 769, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Net_Sale element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Inclusive_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Inclusive_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  

			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("CC_Service_Charge").replace(",", "");
			        	
			        	//Convert the String value of the CC_Service_Charge element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect5 = Float.parseFloat(expected5);  
			        	
			        	//Replace all commo's with empty space
			        	String expected6 = Utility.getReportPropertyUser("Gross_Receipt").replace(",", "");
			        	
			        	//Convert the String value of the Gross_Receipt element into float value
			        	float expect6 = Float.parseFloat(expected6); 
				        
				        //Check weather the Net Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Net Sale Amount");
				        	
				        	//Get the Total value of Net Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Net Sale Amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Net Sale Amount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Net Sale Amount Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();

				        	System.out.println("The Actual Net Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Net Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the net sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Net Sale Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Net Sale Amount Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Tax reports are same for Quantity");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Tax");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
				        //Check weather the Inclusive Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Inclusive_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Inclusive Tax");
				        	
				        	//Get the Total value of Inclusive Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	

				        	//Convert the String value of the Inclusive Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Inclusive Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Inclusive Tax Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();

				        	System.out.println("The Actual Inclusive Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Inclusive Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Inclusive Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
				        //Check weather the CC Service Charge Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText().equals(Utility.getReportPropertyUser("CC_Service_Charge")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Discount");
				        	
				        	//Get the Total value of CC Service Charge
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the CC Service Charge Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual CC Service Charge Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual CC Service Charge Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of CC Service Charge
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();

				        	System.out.println("The Actual CC Service Charge value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual CC Service Charge value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for CC Service Charge");
				        	
				        	//Get the Total value of CC Service Charge
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the CC Service Charge Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("CC Service Charge Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "CC Service Charge Value different is : "+different);	
				        }
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Discount");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[10]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }

					      //Check weather the Gross Receipt Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText().equals(Utility.getReportPropertyUser("Gross_Receipt")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Gross Receipt");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Gross Receipt Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Gross Receipt Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Gross Receipt
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText();

				        	System.out.println("The Actual Gross Receipt value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Gross Receipt value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Discount");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Gross Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect6;
				        	
				        	//Print the different value
				        	System.out.println("Gross Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Gross Value different is : "+different);	
				        }

				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);


				     /* //Check weather the Gross Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[11]/span")).getText().equals(Utility.getReportPropertyUser("Gross_Receipt")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Gross Report");
				        	
				        	//Get the Total value of Gross Receipt
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Gross Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Gross Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Gross Value is : "+ actual);
				        }

				        
				      //Get the Gross Receipt value
				        String a = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr[22]/td[9]/span")).getText().replaceAll("[a-zA-Z $ â‚¹ , :]", "");
				        
				        //Convert the String value to float value
				        float aq = Float.parseFloat(a);
				        
				        aq = aq - expect6;
				        //System.out.println(aq);
				        
				        String s = String.valueOf(aq);
				        
				        //Check weather the Gross Receipt Report is correct or not
				        if(s.equals(Utility.getReportPropertyUser("Gross_Receipt").replaceAll("[a-zA-Z $ â‚¹ , :]", "")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Gross Receipt");
				        	
				        	//Get the Total value of Gross Receipt
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr[22]/td[9]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Gross Receipt Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Gross Receipt Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Gross Receipt Value is : "+ actual);
				        }
				        
						else if(expect6 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage Of Sale
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr[22]/td[9]/span")).getText();

				        	System.out.println("The Actual Gross Receipt value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Gross Receipt value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Gross Receipt");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[9]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage Of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - aq;
				        	
				        	//Print the different value
				        	System.out.println("Gross Receipt Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Gross Receipt Value different is : "+different);	
				        }*/
				        
						   //Get the Detail for Each and Every Check
				        for(int i = 1; i < 2; i++)
				        {
				        	
				        	
				        	Thread.sleep(3000);
				        	//Click the required check
				        	driver.findElement(By.xpath(excel.getData(3, 777, 1))).click();
				        	
				        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				        	Thread.sleep(3000);
				        	//Clear the mail field
				        	driver.findElement(By.xpath(excel.getData(3, 778, 1))).clear();
				        	//Enter the required mail
				        	driver.findElement(By.xpath(excel.getData(3, 778, 1))).sendKeys("sappanimuthub@lingapos.com");
				        	
				        	Thread.sleep(3000);
				        	System.out.println("The Check ("+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText()+") have the following details :");
				        	
				        	test.log(LogStatus.INFO, "The Check ("+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText()+") have the following details :");
				       
				        	System.out.println("Check Number is : "+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Check Number is : "+driver.findElement(By.xpath(excel.getData(3, 779, 1))).getText());
				        	
				        	System.out.println("Date is : "+driver.findElement(By.xpath(excel.getData(3, 780, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Date is : "+driver.findElement(By.xpath(excel.getData(3, 780, 1))).getText());

				        	System.out.println("Table is : "+driver.findElement(By.xpath(excel.getData(3, 781, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Table is : "+driver.findElement(By.xpath(excel.getData(3, 781, 1))).getText());

				        	System.out.println("Seat Number is : "+driver.findElement(By.xpath(excel.getData(3, 782, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Seat Number is : "+driver.findElement(By.xpath(excel.getData(3, 782, 1))).getText());

				        	System.out.println("Server Name is : "+driver.findElement(By.xpath(excel.getData(3, 783, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Server Name is : "+driver.findElement(By.xpath(excel.getData(3, 783, 1))).getText());

				        	System.out.println("Gratuity is : "+driver.findElement(By.xpath(excel.getData(3, 784, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Gratuity is : "+driver.findElement(By.xpath(excel.getData(3, 784, 1))).getText());

				        	System.out.println("Discount is : "+driver.findElement(By.xpath(excel.getData(3, 785, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Discount is : "+driver.findElement(By.xpath(excel.getData(3, 785, 1))).getText());

				        	System.out.println("CC Service Charge is : "+driver.findElement(By.xpath(excel.getData(3, 786, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "CC Service Charge is : "+driver.findElement(By.xpath(excel.getData(3, 786, 1))).getText());

				        	System.out.println("Total is : "+driver.findElement(By.xpath(excel.getData(3, 787, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Total is : "+driver.findElement(By.xpath(excel.getData(3, 787, 1))).getText());

				        	System.out.println("Total Tip is : "+driver.findElement(By.xpath(excel.getData(3, 788, 1))).getText());
				        	
				        	test.log(LogStatus.INFO, "Total Tip is : "+driver.findElement(By.xpath(excel.getData(3, 788, 1))).getText());


				        	Thread.sleep(5000);
					    	//Check Whether the Send Receipt is enabled or not
					    	if(driver.findElement(By.xpath(excel.getData(3, 789, 1))).isEnabled())
					    	{
					    	Thread.sleep(5000);
					    	//Click the Send Receipt
					    	driver.findElement(By.xpath(excel.getData(3, 789, 1))).click();
					    	
							    	Thread.sleep(5000);
							    	//Check whether the receipt sent successfully or not
							    	if(driver.findElement(By.cssSelector(excel.getData(3, 239, 4))).getText().equalsIgnoreCase("Email receipt sent successfully"))
							    	{
							    		test.log(LogStatus.PASS, "Email Receipt sent successfully");
							    	}
							    	
							    	else
							    	{
							    		test.log(LogStatus.FAIL, "Email Receipt sent fail");
							    	}
					    	}
					    	
					    	//Get the row size of tax table
					    	List<WebElement> taxRow = driver.findElements(By.xpath(excel.getData(3, 790, 1)));
					    	
					    		Thread.sleep(3000);
					    		System.out.println("Total Tax amount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[3]/div/div[1]/table/tbody/tr["+taxRow.size()+"]/td[2]")).getText());
					    		
					    		test.log(LogStatus.PASS, "Total Tax amount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[3]/div/div[1]/table/tbody/tr["+taxRow.size()+"]/td[2]")).getText());
					    	
					    	//Check whether the Payment summary is available or not
					    	if(driver.findElement(By.xpath(excel.getData(3, 792, 1))) != null)
					    	{
					    		test.log(LogStatus.PASS, "Payment Summary report is available");
					    	}
					    	
					    	else
					    	{
					    		test.log(LogStatus.FAIL, "Payment Summary report is not available");
					    	}
					    	
					    	//Get the row size of order summary table
					    	List<WebElement> orderSummaryRow = driver.findElements(By.xpath(excel.getData(3, 793, 1)));
					    	
					    		Thread.sleep(3000);
					    		System.out.println("Order Summary subtotal is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[2]/div/div[2]/table/tbody/tr["+orderSummaryRow.size()+"]/td[2]")).getText());
					    		
					    		test.log(LogStatus.PASS, "Order Summary subtotal is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[2]/div/div[2]/table/tbody/tr["+orderSummaryRow.size()+"]/td[2]")).getText());
				
					    		Thread.sleep(3000);
					        	Thread.sleep(3000);
						    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
						    	
						    	Utility us1=new Utility();
								us1.capture(driver);
								
								test.log(LogStatus.INFO, test.addScreenCapture(us1.capture(driver)));

								Thread.sleep(3000);
								driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);


					    		
					    		
					    		//Click the Back button
					    		driver.findElement(By.xpath(excel.getData(3, 795, 1))).click();
					    		
					    		Thread.sleep(2000);
					    	
				        }
					}
					else
					{
						Thread.sleep(3000);
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date for Zip Code");
					
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));


					
					}
				}
			}
			
			catch(Exception e)
			{
				Thread.sleep(3000);
				test.log(LogStatus.FAIL, "Sale_Summary Sale Report is not available for Specific Date for Zip Code");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(3000);
			System.out.println("********************* End of Zip Code Report *************************");
			test.log(LogStatus.INFO, "********************* End of Zip Code Report *************************");wb.close();

		}
}
