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

public class Verify_Comparision_Report {

	public WebDriver driver;

		ExtentReports rep = ExtentManager.getInstance();
		ExtentTest test = rep.startTest("Verify_Comparision_Report");
		
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
			Comparision_Report_Method_viewOpage(driver);
			Comparision_Report_Method_verify_By_Payment_Type(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=15)
		public void Comparision_Report_Method_viewOpage(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		/*	//Click the Reports option
			driver.findElement(By.xpath("//span[.='Reports']")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='Comparision Report']"));
			//Scroll the page till the Comparision Report option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
		    //Click the Comparision Report Option		
			driver.findElement(By.xpath("//span[.='Comparision Report']")).click(); */
			
			Thread.sleep(3000);
			//Enter the Users URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"comparisionReport");
			
			Thread.sleep(5000);
			//Check Comparision Report page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1359, 1))).getText().equalsIgnoreCase("Comparison Report"))
			{
				test.log(LogStatus.PASS,"Comparison Report page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL,"Comparison Report page loaded Failed");
			}
			
			Thread.sleep(3000);wb.close();
			
		}
		
		@Test(enabled=false,priority=16)
		public void Comparision_Report_Method_verify_By_Payment_Type(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		    Thread.sleep(5000);
			//Select the Time Period option   
		    driver.findElement(By.xpath(excel.getData(3, 1360, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 1361, 1))).sendKeys("Today");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 1361, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		    Thread.sleep(5000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 1362, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 1363, 1))).sendKeys("Yesterday");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 1363, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		    Thread.sleep(5000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 1364, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 1365, 1))).sendKeys("Last");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 1365, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
		    
		    //Clear the number of day field
		    driver.findElement(By.xpath(excel.getData(3, 1366, 1))).clear();
		    //Enter the required days
		    driver.findElement(By.xpath(excel.getData(3, 1366, 1))).sendKeys("100");
		    //Press the Enter Key
		    driver.findElement(By.xpath(excel.getData(3, 1366, 1))).sendKeys(Keys.ENTER);
		    
		    Thread.sleep(5000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 1367, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 1368, 1))).sendKeys("This week");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 1368, 1))).sendKeys(Keys.ENTER);
		    
		    
		    Thread.sleep(3000);
		    //Click the Run
		    driver.findElement(By.cssSelector(excel.getData(3, 974 ,4))).click();
		    
		    Thread.sleep(2000);
		    driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		    
		    String no_sale_amount = "₹0.00";
		    
		    Thread.sleep(3000);
		    
				//Check weather the report is available for the selected time period
				if(driver.findElement(By.xpath(excel.getData(3, 1543, 1))).getText() == no_sale_amount && driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[1]")).getText() == no_sale_amount && driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[1]")).getText() == no_sale_amount && driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[1]")).getText() == no_sale_amount)
				{
					test.log(LogStatus.FAIL, "Comparision Report is not available for Specific Date");
				}
				else if(driver.findElement(By.xpath(excel.getData(3, 1544, 1))).getText() != no_sale_amount && driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/form/div[7]/div/table/tbody/tr[2]/td[3]/div[1]")).getText() != no_sale_amount && driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/form/div[7]/div/table/tbody/tr[2]/td[4]/div[1]")).getText() != no_sale_amount && driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/form/div[7]/div/table/tbody/tr[2]/td[5]/div[1]")).getText() != no_sale_amount)
				{
					
					test.log(LogStatus.PASS, "Comparision Report is available for Specific Date");
					
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1369, 1)));
					rows.size();
			
					System.out.println("Date or Date Range is : "+driver.findElement(By.xpath(excel.getData(3, 1545, 1))).getText());
					
					test.log(LogStatus.INFO, "Date or Date Range is : "+ driver.findElement(By.xpath(excel.getData(3, 1545, 1))).getText());
			
			
					for(int i = 1; i <= rows.size(); i++)
					{
						
						System.out.println(driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[1]/div["+i+"]")).getText()+" is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div["+i+"]")).getText());
						
						test.log(LogStatus.INFO, driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[1]/div["+i+"]")).getText()+" is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div["+i+"]")).getText());
			
					}
					
			
					System.out.println("Date or Date Range is : "+driver.findElement(By.xpath(excel.getData(3, 1546, 1))).getText());
					
					test.log(LogStatus.INFO, "Date or Date Range is : "+ driver.findElement(By.xpath(excel.getData(3, 1546, 1))).getText());
			
					for(int i = 1; i <= rows.size(); i++)
					{
						
			
				  		System.out.println(driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[1]/div["+i+"]")).getText()+" is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div["+i+"]")).getText());
						
						test.log(LogStatus.INFO, driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[1]/div["+i+"]")).getText()+" is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div["+i+"]")).getText());
			
					}
			
					System.out.println("Date or Date Range is : "+driver.findElement(By.xpath(excel.getData(3, 1547, 1))).getText());
					
					test.log(LogStatus.INFO, "Date or Date Range is : "+ driver.findElement(By.xpath(excel.getData(3, 1547, 1))).getText());
			
			
					for(int i = 1; i <= rows.size(); i++)
					{
						
						System.out.println(driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[1]/div["+i+"]")).getText()+" is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div["+i+"]")).getText());
						
						test.log(LogStatus.INFO, driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[1]/div["+i+"]")).getText()+" is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div["+i+"]")).getText());
			
					}
					
					
					System.out.println("Date or Date Range is : "+driver.findElement(By.xpath(excel.getData(3, 1548, 1))).getText());
					
					test.log(LogStatus.INFO, "Date or Date Range is : "+ driver.findElement(By.xpath(excel.getData(3, 1548, 1))).getText());
			
			
					for(int i = 1; i <= rows.size(); i++)
					{
						
						System.out.println(driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[1]/div["+i+"]")).getText()+" is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div["+i+"]")).getText());
						
						test.log(LogStatus.INFO, driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[1]/div["+i+"]")).getText()+" is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div["+i+"]")).getText());wb.close();
			
					}
							
	/*				
				
				
				
				
				System.out.println("Date or Date Range is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[1]/th[2]")).getText());
				
				test.log(LogStatus.INFO, "Date or Date Range is : "+ driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[1]/th[2]")).getText());
				
				System.out.println("Net Sales is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[1]")).getText());			
				
				test.log(LogStatus.INFO, "Net Sales is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[1]")).getText());
				
				System.out.println("Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[2]")).getText());
				
				test.log(LogStatus.INFO, "Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[2]")).getText());
				
				System.out.println("Actual Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[3]")).getText());
				
				test.log(LogStatus.INFO, "Actual Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[3]")).getText());
				
				System.out.println("Net Void is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[4]")).getText());
				
				test.log(LogStatus.INFO, "Net Void is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[4]")).getText());
				
				System.out.println("Discounts is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[5]")).getText());			
				
				test.log(LogStatus.INFO, "Discounts is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[5]")).getText());
				
				System.out.println("Refunds is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[6]")).getText());
				
				test.log(LogStatus.INFO, "Refunds is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[6]")).getText());
				
				System.out.println("Gratuity is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[7]")).getText());
				
				test.log(LogStatus.INFO, "Gratuity is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[7]")).getText());
				
				System.out.println("Cash is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[8]")).getText());
				
				test.log(LogStatus.INFO, "Cash is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[8]")).getText());
				
				System.out.println("Payments is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[9]")).getText());
				
				test.log(LogStatus.INFO, "Payments is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[2]/div[9]")).getText());
		
				System.out.println("Date or Date Range is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[1]/th[3]")).getText());
				
				test.log(LogStatus.INFO, "Date or Date Range is : "+ driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[1]/th[3]")).getText());
				
				System.out.println("Net Sales is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[1]")).getText());			
				
				test.log(LogStatus.INFO, "Net Sales is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[1]")).getText());
				
				System.out.println("Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[2]")).getText());
				
				test.log(LogStatus.INFO, "Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[2]")).getText());
				
				System.out.println("Actual Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[3]")).getText());
				
				test.log(LogStatus.INFO, "Actual Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[3]")).getText());
				
				System.out.println("Net Void is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[4]")).getText());
				
				test.log(LogStatus.INFO, "Net Void is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[4]")).getText());
				
				System.out.println("Discounts is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[5]")).getText());			
				
				test.log(LogStatus.INFO, "Discounts is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[5]")).getText());
				
				System.out.println("Refunds is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[6]")).getText());
				
				test.log(LogStatus.INFO, "Refunds is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[6]")).getText());
				
				System.out.println("Gratuity is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[7]")).getText());
				
				test.log(LogStatus.INFO, "Gratuity is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[7]")).getText());
				
				System.out.println("Cash is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[8]")).getText());
				
				test.log(LogStatus.INFO, "Cash is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[8]")).getText());
				
				System.out.println("Payments is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[9]")).getText());
				
				test.log(LogStatus.INFO, "Payments is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[3]/div[9]")).getText());
				
				System.out.println("Date or Date Range is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[1]/th[4]")).getText());
				
				test.log(LogStatus.INFO, "Date or Date Range is : "+ driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[1]/th[4]")).getText());
				
				System.out.println("Net Sales is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[1]")).getText());			
				
				test.log(LogStatus.INFO, "Net Sales is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[1]")).getText());
				
				System.out.println("Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[2]")).getText());
				
				test.log(LogStatus.INFO, "Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[2]")).getText());
				
				System.out.println("Actual Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[3]")).getText());
				
				test.log(LogStatus.INFO, "Actual Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[3]")).getText());
				
				System.out.println("Net Void is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[4]")).getText());
				
				test.log(LogStatus.INFO, "Net Void is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[4]")).getText());
				
				System.out.println("Discounts is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[5]")).getText());			
				
				test.log(LogStatus.INFO, "Discounts is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[5]")).getText());
				
				System.out.println("Refunds is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[6]")).getText());
				
				test.log(LogStatus.INFO, "Refunds is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[6]")).getText());
				
				System.out.println("Gratuity is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[7]")).getText());
				
				test.log(LogStatus.INFO, "Gratuity is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[7]")).getText());
				
				System.out.println("Cash is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[8]")).getText());
				
				test.log(LogStatus.INFO, "Cash is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[8]")).getText());
				
				System.out.println("Payments is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[9]")).getText());
				
				test.log(LogStatus.INFO, "Payments is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[4]/div[9]")).getText());
				
				System.out.println("Date or Date Range is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[1]/th[5]")).getText());
				
				test.log(LogStatus.INFO, "Date or Date Range is : "+ driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[1]/th[5]")).getText());
				
				System.out.println("Net Sales is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[1]")).getText());			
				
				test.log(LogStatus.INFO, "Net Sales is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[1]")).getText());
				
				System.out.println("Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[2]")).getText());
				
				test.log(LogStatus.INFO, "Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[2]")).getText());
				
				System.out.println("Actual Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[3]")).getText());
				
				test.log(LogStatus.INFO, "Actual Tax is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[3]")).getText());
				
				System.out.println("Net Void is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[4]")).getText());
				
				test.log(LogStatus.INFO, "Net Void is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[4]")).getText());
				
				System.out.println("Discounts is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[5]")).getText());			
				
				test.log(LogStatus.INFO, "Discounts is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[5]")).getText());
				
				System.out.println("Refunds is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[6]")).getText());
				
				test.log(LogStatus.INFO, "Refunds is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[6]")).getText());
				
				System.out.println("Gratuity is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[7]")).getText());
				
				test.log(LogStatus.INFO, "Gratuity is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[7]")).getText());
				
				System.out.println("Cash is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[8]")).getText());
				
				test.log(LogStatus.INFO, "Cash is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[8]")).getText());
				
				System.out.println("Payments is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[9]")).getText());
				
				test.log(LogStatus.INFO, "Payments is : "+driver.findElement(By.xpath("//form[@name='comparisionReport']/div[7]/div/table/tbody/tr[2]/td[5]/div[9]")).getText());
				}*/
				}

			else
			{
				test.log(LogStatus.FAIL, "Comparision Report is not available for Specific Date");
			}  
		    Thread.sleep(2000);
		
			wb.close();
			}

}
