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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Verify_Enterprise_Weekly_Summary_Report {



	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Enterprise_Weekly_Report");

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
				Browser a = new Browser();
				a.UPOS_login(driver, test);
			} else {
				Browser a = new Browser();
				a.Linga_login(driver, test);
			}
		}

		@Test(priority = 500)
		public void logout() throws Exception {
			Browser a = new Browser();
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
			Weekly_Summary_Enterprise_Report_Method_For_openpage(driver);
			Weekly_Summary_Enterprise_Report_Method_For_This_Week(driver);
			Weekly_Summary_Enterprise_Report_Method_For_Last_Week(driver);
			Weekly_Summary_Enterprise_Report_Method_For_Last7days(driver);
			Thread.sleep(1500);
			
		}
	
		@Test(enabled=false,priority=19)
		public void Weekly_Summary_Enterprise_Report_Method_For_openpage(WebDriver driver) throws Exception
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
			driver.findElement(By.xpath("//span[.='Sale']")).click();
			
			Thread.sleep(2000);
			// Create instance of Java script executor
	    	JavascriptExecutor je1 = (JavascriptExecutor) driver;
	    	//Identify the WebElement which will appear after scrolling down
			WebElement element1 = driver.findElement(By.xpath("//span[.='Weekly Summary']"));
			//Scroll the page till the Weekly Summary option present
			je1.executeScript("arguments[0].scrollIntoView(true);",element1); 	*/
			Thread.sleep(2000);
			//Enter the URl
			driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/weeklySaleSummary");
	
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/div/scrollable-tabset/div/button[2]")).click();
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/div/scrollable-tabset/div/button[2]")).click();
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/div/scrollable-tabset/div/button[2]")).click();
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/div/scrollable-tabset/div/button[2]")).click();
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/div/scrollable-tabset/div/button[2]")).click();
			
			
			Thread.sleep(5000);
			try
			{
			//Check Weekly summary Report page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 731, 1))).getText().equalsIgnoreCase("Weekly Summary"))
			{
				test.log(LogStatus.PASS, "Weekly Summary Report page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Weekly Summary Report page loaded Failed");
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
							
		
				
		@Test(enabled=false,priority=20)
		public void Weekly_Summary_Enterprise_Report_Method_For_This_Week(WebDriver driver) throws Exception
		{ 
			
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			List<WebElement> close = driver.findElements(By.xpath(excel.getData(3, 742, 2)));
			close.size();	
			for(int i = 1; i <= close.size(); i++)
			{
				//Click the Close button for all stores
				driver.findElement(By.xpath(excel.getData(3, 743, 2))).click();
			}
			
			Thread.sleep(5000);
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 2159, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 2160, 1))).sendKeys(Utility.getReportProperty("Store"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2160, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(3000);
			//Click the Time Period option
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			Thread.sleep(1000);
			//Enter the required Time Period
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("This week");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(4000);
			//Click the Run Button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			Thread.sleep(5000);

			//List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1518, 1)));

			//Check weather the Table Type report available or not for This Week
			if(driver.findElement(By.xpath(excel.getData(3, 1517, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Table format reprot is available for This Week");
							
				
				List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr/td[1]"));
				for(int i = 2; i <= rows.size(); i++)
				{
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("Gross Sales"))
					{
						
						List<WebElement> Column = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td"));
						Column.size();
						
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportProperty("This_Weekly_Gross_Sale").replace("[,\\₹]", "");
			        	
			        	//Convert the String value of the This_Weekly_Gross_Sale element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        	
			        	//Get the Total value of Check Count
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 751, 1))).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replaceAll("[,\\₹]", "");
			        	
						Thread.sleep(2000);
						//Check the Gross Sale
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column.size()+"]")).getText().equals(Utility.getReportProperty("This_Weekly_Gross_Sale")))
						{
							test.log(LogStatus.PASS, "Actual and Expected Gross Sale is same");
								        	
				        	//Convert the String value of the Gross Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Gross Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Gross Sale Value is : "+ actual);
	
						}
						
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	System.out.println("The Actual Gross Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Gross Sale value is : "+actualText);
						}
						
						else
						{
							test.log(LogStatus.FAIL, "Actual and Expected Gross Sale is different");
				        	
				        	//Convert the String value of the Gross Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Gross Sale different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Gross Sale different is : "+different);	
	
						}
						
					}
				}
				
				for(int i = 2; i <= rows.size(); i++)
				{
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("Total Net Sales"))
					{
						
						List<WebElement> Column1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td"));
						
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportProperty("This_Weekly_Net_Sale").replace("[,\\₹]", "");
			        	
			        	//Convert the String value of the This_Weekly_Net_Sale element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Get the Total value of Check Count
			        	String actualText3 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column1.size()+"]")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText2 = actualText3.replaceAll("[,\\₹]", "");
							
						Thread.sleep(2000);
						//Check the Net Sale
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column1.size()+"]")).getText().equals(Utility.getReportProperty("This_Weekly_Net_Sale")))
						{
							test.log(LogStatus.PASS, "Actual and Expected Net Sale is same");
								        	
				        	//Convert the String value of the Net Sale Total element into float value
				        	float actual = Float.parseFloat(actualText2);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Net Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Net Sale Value is : "+ actual);
	
						}
						
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	System.out.println("The Actual Net Sale value is : "+actualText2);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText2);
						}
						
						else
						{
							test.log(LogStatus.FAIL, "Actual and Expected Net Sale is different");
				        	
				        	//Convert the String value of the Gross Sale Total element into float value
				        	float actual = Float.parseFloat(actualText2);
				        			        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Net Sale different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Net Sale different is : "+different);	
	
						}
						
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);


	
						}
	
				
				}
			}
			
			else
			{
				test.log(LogStatus.FAIL, "Table format report is not available for This Week");wb.close();
		
		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));

		

			}
		
		wb.close();
		}
		@Test(enabled=false,priority=21)
		public void Weekly_Summary_Enterprise_Report_Method_For_Last_Week(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			List<WebElement> close = driver.findElements(By.xpath(excel.getData(3, 742, 2)));
			close.size();	
			for(int i = 1; i <= close.size(); i++)
			{
				//Click the Close button for all stores
				driver.findElement(By.xpath(excel.getData(3, 743, 2))).click();
			}
			Thread.sleep(3000);
			//Click the Store option
			driver.findElement(By.xpath(excel.getData(3, 2159, 1))).click();
			//Enter the required Time Period
			driver.findElement(By.xpath(excel.getData(3, 2160, 1))).sendKeys(Utility.getReportProperty("Store"));
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2160, 1))).sendKeys(Keys.ENTER);
	
			Thread.sleep(3000);
			//Click the Time Period option
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required Time Period
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Last week");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Run Button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			Thread.sleep(5000);
			//Check weather the Table Type report available or not for Last Week
			if(driver.findElement(By.xpath(excel.getData(3, 1517, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Table format reprot is available for Last Week");
							
				List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr/td[1]"));
				for(int i = 2; i <= rows.size(); i++)
				{
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("Gross Sales"))
					{
						
						List<WebElement> Column = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td"));
						Column.size();
						
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportProperty("This_Weekly_Gross_Sale").replace("[,\\₹]", "");
			        	
			        	//Convert the String value of the This_Weekly_Gross_Sale element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        	
			        	//Get the Total value of Check Count
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 751, 1))).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replaceAll("[,\\₹]", "");
			        	
						Thread.sleep(2000);
						//Check the Gross Sale
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column.size()+"]")).getText().equals(Utility.getReportProperty("This_Weekly_Gross_Sale")))
						{
							test.log(LogStatus.PASS, "Actual and Expected Gross Sale is same");
								        	
				        	//Convert the String value of the Gross Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Gross Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Gross Sale Value is : "+ actual);
	
						}
						
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	System.out.println("The Actual Gross Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Gross Sale value is : "+actualText);
						}
						
						else
						{
							test.log(LogStatus.FAIL, "Actual and Expected Gross Sale is different");
				        	
				        	//Convert the String value of the Gross Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Gross Sale different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Gross Sale different is : "+different);	
	
						}
						
					}
				}
				
				for(int i = 2; i <= rows.size(); i++)
				{
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("Total Net Sales"))
					{
						
						List<WebElement> Column1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td"));
						
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportProperty("This_Weekly_Net_Sale").replace("[,\\₹]", "");
			        	
			        	//Convert the String value of the This_Weekly_Net_Sale element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Get the Total value of Check Count
			        	String actualText3 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column1.size()+"]")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText2 = actualText3.replaceAll("[,\\₹]", "");
							
						Thread.sleep(2000);
						//Check the Net Sale
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column1.size()+"]")).getText().equals(Utility.getReportProperty("This_Weekly_Net_Sale")))
						{
							test.log(LogStatus.PASS, "Actual and Expected Net Sale is same");
								        	
				        	//Convert the String value of the Net Sale Total element into float value
				        	float actual = Float.parseFloat(actualText2);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Net Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Net Sale Value is : "+ actual);
	
						}
						
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	System.out.println("The Actual Net Sale value is : "+actualText2);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText2);
						}
						
						else
						{
							test.log(LogStatus.FAIL, "Actual and Expected Net Sale is different");
				        	
				        	//Convert the String value of the Gross Sale Total element into float value
				        	float actual = Float.parseFloat(actualText2);
				        			        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Net Sale different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Net Sale different is : "+different);	
	
						}
	
						}
	
			    	Thread.sleep(3000);
			    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			    	
			    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

					Thread.sleep(3000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);


				}
			}
			else
			{
				test.log(LogStatus.FAIL, "Table format report is not available for Last Week");wb.close();
			
			
		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			
			
			}wb.close();
		}
		
		@Test(enabled=false,priority=22)
		public void Weekly_Summary_Enterprise_Report_Method_For_Last7days(WebDriver driver) throws Exception
		{   
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			List<WebElement> close = driver.findElements(By.xpath(excel.getData(3, 742, 2)));
			close.size();	
			for(int i = 1; i <= close.size(); i++)
			{
				//Click the Close button for all stores
				driver.findElement(By.xpath(excel.getData(3, 743, 2))).click();
			}
			Thread.sleep(3000);
			//Click the Store option
			driver.findElement(By.xpath(excel.getData(3, 2159, 1))).click();
			//Enter the required Time Period
			driver.findElement(By.xpath(excel.getData(3, 2160, 1))).sendKeys(Utility.getReportProperty("Store"));
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2160, 1))).sendKeys(Keys.ENTER);
	
			Thread.sleep(3000);
			//Click the Time Period option
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required Time Period
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Last 7 days");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2500);
			//Click the Run Button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			
			Thread.sleep(5000);
			//Check weather the Table Type report available or not for Last 7 days
			if(driver.findElement(By.xpath(excel.getData(3, 1517, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Table format reprot is available for Last 7 Days");
							
				List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr/td[1]"));
				for(int i = 2; i <= rows.size(); i++)
				{
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("Gross Sales"))
					{
						
						List<WebElement> Column = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td"));
						Column.size();
						
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportProperty("This_Weekly_Gross_Sale").replace("[,\\₹]", "");
			        	
			        	//Convert the String value of the This_Weekly_Gross_Sale element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        	
			        	//Get the Total value of Check Count
			        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 751, 1))).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replaceAll("[,\\₹]", "");
			        	
						Thread.sleep(2000);
						//Check the Gross Sale
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column.size()+"]")).getText().equals(Utility.getReportProperty("This_Weekly_Gross_Sale")))
						{
							test.log(LogStatus.PASS, "Actual and Expected Gross Sale is same");
								        	
				        	//Convert the String value of the Gross Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Gross Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Gross Sale Value is : "+ actual);
	
						}
						
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	System.out.println("The Actual Gross Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Gross Sale value is : "+actualText);
						}
						
						else
						{
							test.log(LogStatus.FAIL, "Actual and Expected Gross Sale is different");
				        	
				        	//Convert the String value of the Gross Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Gross Sale different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Gross Sale different is : "+different);	
	
						}
						
					}
				}
				
				for(int i = 2; i <= rows.size(); i++)
				{
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td[1]")).getText().equalsIgnoreCase("Total Net Sales"))
					{
						
						List<WebElement> Column1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td"));
						
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportProperty("This_Weekly_Net_Sale").replace("[,\\₹]", "");
			        	
			        	//Convert the String value of the This_Weekly_Net_Sale element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Get the Total value of Check Count
			        	String actualText3 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column1.size()+"]")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText2 = actualText3.replaceAll("[,\\₹]", "");
							
						Thread.sleep(2000);
						//Check the Net Sale
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+i+"]/td["+Column1.size()+"]")).getText().equals(Utility.getReportProperty("This_Weekly_Net_Sale")))
						{
							test.log(LogStatus.PASS, "Actual and Expected Net Sale is same");
								        	
				        	//Convert the String value of the Net Sale Total element into float value
				        	float actual = Float.parseFloat(actualText2);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Net Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Net Sale Value is : "+ actual);
	
						}
						
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	System.out.println("The Actual Net Sale value is : "+actualText2);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText2);
						}
						
						else
						{
							test.log(LogStatus.FAIL, "Actual and Expected Net Sale is different");
				        	
				        	//Convert the String value of the Gross Sale Total element into float value
				        	float actual = Float.parseFloat(actualText2);
				        			        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Net Sale different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Net Sale different is : "+different);	
	
						}
	
						}
			    	Thread.sleep(3000);
			    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			    	
			    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

					Thread.sleep(3000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);


				
				}
			}
			
			else
			{
				test.log(LogStatus.FAIL, "Table format report is not available for Last 7 Days");
			
			
		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			
			}wb.close();
		}

}
