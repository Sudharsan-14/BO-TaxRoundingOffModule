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
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;


public class Inventory_Reports_COGS_Report {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_COGS_Report");

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
			Inventory_Reports_COGS_Report_openpage(driver);
			Inventory_Reports_COGS_verify_All_report(driver);
			Inventory_Reports_COGS_Report_verify_MenuItem(driver);
			Inventory_Reports_COGS_Report_verify_Modifier(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=53)
	public void Inventory_Reports_COGS_Report_openpage(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				/*//Click the Inventory option
				driver.findElement(By.xpath("//span[.='Inventory']")).click();
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[contains(.,'Reports')]"));
				//Scroll the page till the Inventory Reports option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Inventory Reports Option		
				driver.findElement(By.xpath("//span[contains(.,'Reports')]")).click();
				 //driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[4]/ul/li[11]/a/i")).click();
				*/
				Thread.sleep(5000);
				//Get the URl
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"cogs");
				
				Thread.sleep(5000);
				try
				{
				//Check COGS page opened or not
				if(driver.findElement(By.xpath(excel.getData(3, 1326, 1))).getText().equalsIgnoreCase("COGS"))
				{
					test.log(LogStatus.PASS, "COGS page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "COGS page loaded Failed");
				
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
			
	@Test(enabled=false,priority=54)
	public void Inventory_Reports_COGS_verify_All_report(WebDriver driver) throws Exception
	{
		    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Select the Required Type
				//Select the Required Type
				driver.findElement(By.xpath(excel.getData(3, 1669, 1))).click();
				//Enter Type
				driver.findElement(By.xpath(excel.getData(3, 1670, 1))).sendKeys("All");
				//Click 
				driver.findElement(By.xpath(excel.getData(3, 1670, 1))).sendKeys(Keys.ENTER);
						
				Thread.sleep(2000);
				//Select the Time Period Option
				Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1671, 1))));
				timePeriod.selectByVisibleText("Date Range");
				
				Thread.sleep(1500);
				//Clear the Date Range from Option
				driver.findElement(By.xpath(excel.getData(3, 1672, 1))).clear();
				//Enter the required
				driver.findElement(By.xpath(excel.getData(3, 1672, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

				Thread.sleep(1500);
				//Clear the Date Range To Option
				driver.findElement(By.xpath(excel.getData(3, 1673, 1))).clear();
				//Enter the required
				driver.findElement(By.xpath(excel.getData(3, 1673, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
				
				Thread.sleep(1000);
				//Click the Search button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				Thread.sleep(10000);
				try
				{
					//Check whether the Received Items are Loaded Or not
					if(driver.findElement(By.xpath(excel.getData(3, 1331, 1))).isDisplayed())
					{
						test.log(LogStatus.INFO, "There is no record available for the Selected Time Period for All");
					}
				}
				catch(Exception e)		
				{
					test.log(LogStatus.PASS, "COGS Report is available for the Selected time period for All");
					test.log(LogStatus.INFO, "**********   The below is for All filter data   **********");
					
					List<WebElement> data = driver.findElements(By.xpath(excel.getData(3, 1674, 1)));
					data.size();
					
					for(int i=1;i<=data.size();i++)
					{
						test.log(LogStatus.INFO, "Sold the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[2]/div/div/div/div[2]/div[3]/table/tbody/tr["+i+"]/td[1]")).getText()+" for "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[2]/div/div/div/div[2]/div[3]/table/tbody/tr["+i+"]/td[3]")).getText()+" with "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[2]/div/div/div/div[2]/div[3]/table/tbody/tr["+i+"]/td[2]")).getText()+" quantity");
						wb.close();
					}		

				}
						
			}
			
	@Test(enabled=false,priority=55)
	public void Inventory_Reports_COGS_Report_verify_MenuItem(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Select the Required Type
				//Select the Required Type
				driver.findElement(By.xpath(excel.getData(3, 1669, 1))).click();
				//Enter Type
				driver.findElement(By.xpath(excel.getData(3, 1670, 1))).sendKeys("Menu Item");
				//Click 
				driver.findElement(By.xpath(excel.getData(3, 1670, 1))).sendKeys(Keys.ENTER);
						
				Thread.sleep(2000);
				//Select the Time Period Option
				Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1671, 1))));
				timePeriod.selectByVisibleText("Date Range");
				
				Thread.sleep(1500);
				//Clear the Date Range from Option
				driver.findElement(By.xpath(excel.getData(3, 1672, 1))).clear();
				//Enter the required
				driver.findElement(By.xpath(excel.getData(3, 1672, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

				Thread.sleep(1500);
				//Clear the Date Range To Option
				driver.findElement(By.xpath(excel.getData(3, 1673, 1))).clear();
				//Enter the required
				driver.findElement(By.xpath(excel.getData(3, 1673, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
				
				Thread.sleep(1000);
				//Click the Search button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				Thread.sleep(10000);
				try
				{
					//Check whether the Received Items are Loaded Or not
					if(driver.findElement(By.xpath(excel.getData(3, 1331, 1))).isDisplayed())
					{
						test.log(LogStatus.INFO, "There is no record available for the Selected Time Period for Menu Item");
					}
				}
				catch(Exception e)		
				{
					test.log(LogStatus.PASS, "COGS Report is available for the Selected time period for Menu Item");
								
					List<WebElement> data = driver.findElements(By.xpath(excel.getData(3, 1674, 1)));
					data.size();
					
					//Get the Value of total value of Sold
					String actual = driver.findElement(By.xpath(excel.getData(3, 1675, 1))).getText();
					
					//Replace the alphabets
					String actual1 = actual.replaceAll("[a-zA-Z $ ₹ , :]", "");
					
					float actualF = Float.parseFloat(actual1);
								

			/*			String temp = driver.findElement(By.xpath(excel.getData(3, 1334, 1))).getText();
						
						// Create instance of Java script executor
						JavascriptExecutor je = (JavascriptExecutor) driver;
						//Identify the WebElement which will appear after scrolling down
						WebElement element = driver.findElement(By.xpath(excel.getData(3, 1335, 1)));
						//Scroll the page till the Reports option present
						je.executeScript("arguments[0].scrollIntoView(true);",element); 
				        //Click the Reports Option		
						driver.findElement(By.xpath(excel.getData(3, 1335, 1))).click();
						Thread.sleep(10000);
					
						//Clear the search field
						driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
						//Enter the search field
						driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(temp);
						
						Thread.sleep(2000);
						//Click the sync icon
						driver.findElement(By.xpath(excel.getData(3, 1336, 1))).click();
						
						Thread.sleep(8000);
						//Click the right arrow
						driver.findElement(By.xpath(excel.getData(3, 1337, 1))).click();
						
					String expect = driver.findElement(By.xpath("//div[@class='panel-collapse in collapse']/div/div[3]/div/table/tbody/tr[2]/td[2]")).getText();
						
					//Replace the alphabets
					String expect1 = expect.replaceAll("[a-zA-Z $ ₹ ,]", "");
					
					float expectF = Float.parseFloat(expect1);
					
					if(expectF == actualF)
					{
						test.log(LogStatus.PASS, "Total value and Menu Item price are equal, the value is :"+expectF);
					}
					else
					{
						test.log(LogStatus.PASS, "Total value and Menu Item price are not equal");
						
						float diff =  expectF-actualF;
						
						test.log(LogStatus.FAIL, "Total difference is : "+diff);
					}
					
					// Create instance of Java script executor
					JavascriptExecutor je1 = (JavascriptExecutor) driver;
					//Identify the WebElement which will appear after scrolling down
					WebElement element1 = driver.findElement(By.xpath(excel.getData(3, 1338, 1)));
					//Scroll the page till the Reports option present
					je1.executeScript("arguments[0].scrollIntoView(true);",element1); 
			        //Click the Reports Option		
					driver.findElement(By.xpath(excel.getData(3, 1338, 1))).click();
					Thread.sleep(5000);
					
			        //Click the COGS Option		
					driver.findElement(By.xpath(excel.getData(3, 1339, 1))).click();*/
					
					Thread.sleep(5000);
					wb.close();
				}

				
							
			}
			
	@Test(enabled=false,priority=56)
	public void Inventory_Reports_COGS_Report_verify_Modifier(WebDriver driver) throws Exception
	{
		
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Select the Required Type
				driver.findElement(By.xpath(excel.getData(3, 1669, 1))).click();
				//Enter Type
				driver.findElement(By.xpath(excel.getData(3, 1670, 1))).sendKeys("Modifier");
				//Click 
				driver.findElement(By.xpath(excel.getData(3, 1670, 1))).sendKeys(Keys.ENTER);
						
				Thread.sleep(2000);
				//Select the Time Period Option
				Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1671, 1))));
				timePeriod.selectByVisibleText("Date Range");
				
				Thread.sleep(1500);
				//Clear the Date Range from Option
				driver.findElement(By.xpath(excel.getData(3, 1672, 1))).clear();
				//Enter the required
				driver.findElement(By.xpath(excel.getData(3, 1672, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

				Thread.sleep(1500);
				//Clear the Date Range To Option
				driver.findElement(By.xpath(excel.getData(3, 1673, 1))).clear();
				//Enter the required
				driver.findElement(By.xpath(excel.getData(3, 1673, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
				
				Thread.sleep(1000);
				//Click the Search button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				Thread.sleep(10000);
				try
				{
					//Check whether the Received Items are Loaded Or not
					if(driver.findElement(By.xpath(excel.getData(3, 1331, 1))).isDisplayed())
					{
						test.log(LogStatus.INFO, "There is no record available for the Selected Time Period for Modifier");
					}
				}
				catch(Exception e)		
				{
					test.log(LogStatus.PASS, "COGS Report is available for the Selected time period for Modifier");
					test.log(LogStatus.INFO, "**********   The below is for Modifier filter data   **********");
					
					List<WebElement> data = driver.findElements(By.xpath(excel.getData(3, 1674, 1)));
					data.size();
					
					Thread.sleep(1000);
					for(int i=1;i<=data.size();i++)
					{
						test.log(LogStatus.INFO, "Sold the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[2]/div/div/div/div[2]/div[3]/table/tbody/tr["+i+"]/td[1]")).getText()+" for "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[2]/div/div/div/div[2]/div[3]/table/tbody/tr["+i+"]/td[3]")).getText()+" with "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[2]/div/div/div/div[2]/div[3]/table/tbody/tr["+i+"]/td[2]")).getText()+" quantity");
						wb.close();
					}		

				}
						 
			}

}
