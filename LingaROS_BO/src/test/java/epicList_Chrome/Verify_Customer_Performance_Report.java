package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Verify_Customer_Performance_Report {

	public WebDriver driver;

		ExtentReports rep = ExtentManager.getInstance();
		ExtentTest test = rep.startTest("Verify_Customer_Performance_Report");
		
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
			CustomerPerformance_Report_Method_viewpage(driver);
			CustomerPerformance_Report_Method_verify(driver);
		//	CustomerPerformance_Report_Method_verify(driver);
			CustomerPerformance_Report_Method_WithoutCategorySubcategoryAdnServingSize(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=17)
		public void CustomerPerformance_Report_Method_viewpage(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			/*//Click the Reports option
			driver.findElement(By.xpath("//span[.='Reports']")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='Customer Preference']"));
			//Scroll the page till the Customer Preference Report option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
		    //Click the Customer Preference Report Option		
			driver.findElement(By.xpath("//span[.='Customer Preference']")).click();   */
			
			Thread.sleep(3000);
			//Enter the Users URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"customerPreferenceReport");
		
			Thread.sleep(5000);
			try
			{
			//Check Customer Preference Report page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1128, 1))).getText().equalsIgnoreCase("Customer Preference Report"))
			{
				test.log(LogStatus.PASS,"Customer Preference Report page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL,"Customer Preference Report page loaded Failed");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			}
			catch(Exception e)
			{
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			Thread.sleep(3000);wb.close();
			
		}
		
		@Test(enabled=false,priority=18)
		public void CustomerPerformance_Report_Method_verify(WebDriver driver) throws Exception
		{
			

			    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		    Thread.sleep(5000);
			//Select the Employee option
		    driver.findElement(By.xpath(excel.getData(3, 1093, 1))).click();
		    //Enter the required Employee
		    driver.findElement(By.xpath(excel.getData(3, 1094, 1))).sendKeys("All");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 1094, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		    Thread.sleep(5000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 1129, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 1130, 1))).sendKeys("Date Range");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 1130, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		    Thread.sleep(2000);
		    //Clear the Date field
		    driver.findElement(By.xpath(excel.getData(3, 1131, 1))).clear();
		    //Enter the required date
		    driver.findElement(By.xpath(excel.getData(3, 1131, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
		
		    Thread.sleep(2000);
		    //Clear the Date field
		    driver.findElement(By.xpath(excel.getData(3, 1132, 1))).clear();
		    //Enter the required date
		    driver.findElement(By.xpath(excel.getData(3, 1132, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
		    
		    //Click the Run
		    driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		    Thread.sleep(8000);
		    
		    driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		//    test.log(LogStatus.PASS,"Run button clicked successfully" );    
			try
			{
				//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(3,704, 1))).isDisplayed())
				{
					test.log(LogStatus.FAIL, "Customer Performance Report(With Category, Sub Category and Serving Size) is not available for Specific Date");
			
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				
				}
				else
				{
					test.log(LogStatus.PASS, "Customer Performance Report(With Category, Sub Category and Serving Size) is available for Specific Date");
					
					System.out.println("Customer Performance Report(With Category, Sub Category and Serving Size) is available for Specific Date");
					
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1133, 1)));
					
					
					System.out.println("Quantity Sold is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[2]/span/span/b")).getText());
					
					test.log(LogStatus.INFO, "Quantity Sold is : "+ driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[2]/span/span/b")).getText());
					
					System.out.println("Quantity Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[3]/span/span/b")).getText());			
					
					test.log(LogStatus.INFO, "Quantity Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[3]/span/span/b")).getText());
					
					System.out.println("Quantity Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[4]/span/span/b")).getText());
					
					test.log(LogStatus.INFO, "Quantity Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[4]/span/span/b")).getText());
					
					System.out.println("Quantity Group Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[5]/span/span/b")).getText());
					
					test.log(LogStatus.INFO, "Quantity Group Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[5]/span/span/b")).getText());
					
					System.out.println("Quantity Total Percntage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[6]/span/span/b")).getText());
					
					test.log(LogStatus.INFO, "Quantity Total Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[6]/span/span/b")).getText());
					
					System.out.println("Amount Sold is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[7]/span/span/b")).getText());			
					
					test.log(LogStatus.INFO, "Amount Sold is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[7]/span/span/b")).getText());
					
					System.out.println("Amount Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[8]/span/span/b")).getText());
					
					test.log(LogStatus.INFO, "Amount Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[8]/span/span/b")).getText());
					
					System.out.println("Amount Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[9]/span/span/b")).getText());
					
					test.log(LogStatus.INFO, "Amount Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[9]/span/span/b")).getText());
					
					System.out.println("Amount Group Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[10]/span/span/b")).getText());
					
					test.log(LogStatus.INFO, "Amount Group Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[10]/span/span/b")).getText());
					
					System.out.println("Amount Total Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[11]/span/span/b")).getText());
					
					test.log(LogStatus.INFO, "Amount Total Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[11]/span/span/b")).getText());
			
					Thread.sleep(3000);

					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

					Thread.sleep(3000);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				
				}
			}
			catch(Exception fd)
			{
				System.out.println(fd.getMessage());
	/*			test.log(LogStatus.PASS, "Customer Performance Report(With Category, Sub Category and Serving Size) is available for Specific Date");
				
				System.out.println("Customer Performance Report(With Category, Sub Category and Serving Size) is available for Specific Date");
				
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1133, 1)));
				
				
				System.out.println("Quantity Sold is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[2]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Quantity Sold is : "+ driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[2]/span/span/b")).getText());
				
				System.out.println("Quantity Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[3]/span/span/b")).getText());			
				
				test.log(LogStatus.INFO, "Quantity Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[3]/span/span/b")).getText());
				
				System.out.println("Quantity Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[4]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Quantity Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[4]/span/span/b")).getText());
				
				System.out.println("Quantity Group Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[5]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Quantity Group Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[5]/span/span/b")).getText());
				
				System.out.println("Quantity Total Percntage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[6]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Quantity Total Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[6]/span/span/b")).getText());
				
				System.out.println("Amount Sold is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[7]/span/span/b")).getText());			
				
				test.log(LogStatus.INFO, "Amount Sold is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[7]/span/span/b")).getText());
				
				System.out.println("Amount Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[8]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Amount Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[8]/span/span/b")).getText());
				
				System.out.println("Amount Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[9]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Amount Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[9]/span/span/b")).getText());
				
				System.out.println("Amount Group Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[10]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Amount Group Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[10]/span/span/b")).getText());
				
				System.out.println("Amount Total Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[11]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Amount Total Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[11]/span/span/b")).getText());
		
*/				
			}
		    Thread.sleep(2000);wb.close();
		    
		}
		
		@Test(enabled=false,priority=19)
		public void CustomerPerformance_Report_Method_WithoutCategorySubcategoryAdnServingSize(WebDriver driver) throws Exception
		{   
			    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			
		
			
		    Thread.sleep(5000);
			//Select the Employee option
		    driver.findElement(By.xpath(excel.getData(3, 1093, 1))).click();
		    //Enter the required Employee
		    driver.findElement(By.xpath(excel.getData(3, 1094, 1))).sendKeys("All");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 1094, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		    Thread.sleep(5000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 1129, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 1130, 1))).sendKeys("Date Range");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 1130, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		    Thread.sleep(2000);
		    //Clear the Date field
		    driver.findElement(By.xpath(excel.getData(3, 1131, 1))).clear();
		    //Enter the required date
		    driver.findElement(By.xpath(excel.getData(3, 1131, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
		
		    Thread.sleep(2000);
		    //Clear the Date field
		    driver.findElement(By.xpath(excel.getData(3, 1132, 1))).clear();
		    //Enter the required date
		    driver.findElement(By.xpath(excel.getData(3, 1132, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
		    
			if(driver.findElement(By.xpath(excel.getData(3, 1134, 1))).isEnabled())
			{
				//Disable the Category option
				driver.findElement(By.xpath(excel.getData(3, 1134, 1))).click();
			}
			
			Thread.sleep(3000);
		
			if(driver.findElement(By.xpath(excel.getData(3, 1135, 1))).isEnabled())
			{
				//Disable the Sub Category option
				driver.findElement(By.xpath(excel.getData(3, 1135, 1))).click();
			}
			
			Thread.sleep(3000);
			
			if(driver.findElement(By.xpath(excel.getData(3, 1136, 1))).isEnabled())
			{
				//Disable the Serving Size option
				driver.findElement(By.xpath(excel.getData(3, 1136, 1))).click();
			}
		    
		    //Click the Run
		    driver.findElement(By.cssSelector(excel.getData(3, 974, 4))).click();
		    Thread.sleep(8000);
		
		    driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		    	    
			try
			{
				//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
				{
					test.log(LogStatus.FAIL, "Customer Performance Report(Without Category, Sub Category and Serving Size) is not available for Specific Date");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				
				}
			}
			catch(Exception fd)
			{
				
				test.log(LogStatus.PASS, "Customer Performance Report(Without Category, Sub Category and Serving Size) is available for Specific Date");
				
				List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr"));
				
				System.out.println("Customer Performance Report(Without Category, Sub Category and Serving Size) is available for Specific Date");
				
				System.out.println("Quantity Sold is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[2]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Quantity Sold is : "+ driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[2]/span/span/b")).getText());
				
				System.out.println("Quantity Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[3]/span/span/b")).getText());			
				
				test.log(LogStatus.INFO, "Quantity Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[3]/span/span/b")).getText());
				
				System.out.println("Quantity Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[4]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Quantity Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[4]/span/span/b")).getText());
				
				System.out.println("Quantity Group Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[5]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Quantity Group Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[5]/span/span/b")).getText());
				
				System.out.println("Quantity Total Percntage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[6]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Quantity Total Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[6]/span/span/b")).getText());
				
				System.out.println("Amount Sold is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[7]/span/span/b")).getText());			
				
				test.log(LogStatus.INFO, "Amount Sold is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[7]/span/span/b")).getText());
				
				System.out.println("Amount Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[8]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Amount Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[8]/span/span/b")).getText());
				
				System.out.println("Amount Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[9]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Amount Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[9]/span/span/b")).getText());
				
				System.out.println("Amount Group Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[10]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Amount Group Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[10]/span/span/b")).getText());
				
				System.out.println("Amount Total Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[11]/span/span/b")).getText());
				
				test.log(LogStatus.INFO, "Amount Total Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[3]/table/tbody/tr["+rows.size()+"]/td[11]/span/span/b")).getText());
		
				Thread.sleep(3000);
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));

				Thread.sleep(3000);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				
			} 
		    Thread.sleep(2000);wb.close();
		    
		}
	
}
