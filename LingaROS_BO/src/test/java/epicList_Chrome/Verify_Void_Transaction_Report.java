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

import epicList_Chrome.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Verify_Void_Transaction_Report {

	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Void_Transaction_Report");

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
			Void_Transaction_Report_Method_openpage(driver);
			Void_Transaction_Report_Method__For_Tender_Type(driver);
			Void_Transaction_Report_Method__For_Tender_Name(driver);
			Thread.sleep(1500);
		}
	
			@Test(priority=5,enabled = false)
			public void Void_Transaction_Report_Method_openpage(WebDriver driver) throws Exception
			{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				/*
				Thread.sleep(2000);
				WebElement html = driver.findElement(By.tagName("html"));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				
				//Click the Reports option
				driver.findElement(By.xpath("//span[.='Reports']")).click();
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[.='Transactions']"));
				//Scroll the page till the Transactions option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Transactions Option		
				driver.findElement(By.xpath("//span[.='Transactions']")).click(); */
				
				Thread.sleep(3000);
				//Enter the Users URL
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"report/voidTransactionSale");
				
				Thread.sleep(5000);
				try
				{
				//Check weather the Void Transaction page is loaded or not
				if(driver.findElement(By.xpath(excel.getData(1, 836, 1))).getText().equalsIgnoreCase("Void Transaction"))
				{
					test.log(LogStatus.PASS, "Void Transaction Report page loaded successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Void Transaction Report page loaded fail");
				
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
			
			@Test(priority=6,enabled = false)
			public void Void_Transaction_Report_Method__For_Tender_Type(WebDriver driver) throws Exception
			{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(2000);
				//Select the Tender option
				Select tenderOption = new Select(driver.findElement(By.xpath(excel.getData(1, 817, 1))));
				tenderOption.selectByVisibleText("By Tender Type");
				
				Thread.sleep(5000);
				//Click the Tender Type option
				driver.findElement(By.xpath(excel.getData(1, 818, 1))).click();
				//Enter the required Tender Type
				driver.findElement(By.xpath(excel.getData(1, 819, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 819, 1))).sendKeys(Keys.ENTER);
			
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(3, 722, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(3, 723, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 723, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 724, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 724, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
	
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 725, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 725, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
				
				Thread.sleep(3000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(1, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Transaction Report(By Tender Type) is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				}
				catch(Exception fd)
				{
					test.log(LogStatus.PASS, "Void Transaction Report(By Tender Type) available for Specific Date");
					
					//Get the number of rows
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 825, 1)));
					
					//Get the Total amount value
					String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[11]")).getText();
					
					//Print the Total_Amount value
					System.out.println("Void Transaction report(By Tender Type) Total Amount is : "+Total_Amount);
					
					test.log(LogStatus.INFO, "Void Transaction report(By Tender Type) Total Amount is : "+Total_Amount);wb.close();
				}
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			
			@Test(priority=7,enabled = false)
			public void Void_Transaction_Report_Method__For_Tender_Name(WebDriver driver) throws Exception
			{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(2000);
				//Select the Tender option
				Select tenderOption = new Select(driver.findElement(By.xpath(excel.getData(1, 817, 1))));
				tenderOption.selectByVisibleText("By Tender Name");
				
				Thread.sleep(5000); 
				//Click the Tender Name option
				driver.findElement(By.xpath(excel.getData(1, 818, 1))).click();
				//Enter the required Tender Name
				driver.findElement(By.xpath(excel.getData(1, 819, 1))).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(1, 819, 1))).sendKeys(Keys.ENTER);
			
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath(excel.getData(3, 722, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(3, 723, 1))).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 723, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 724, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 724, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
	
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath(excel.getData(3, 725, 1))).clear();
				//Enter the date
				driver.findElement(By.xpath(excel.getData(3, 725, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
				
				Thread.sleep(3000);
				try
				{
					//Check weather the report is available for the selected time period
					if(driver.findElement(By.xpath(excel.getData(1, 704, 1))).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Transaction Report(By Tender name) is not available for Specific Date");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				}
				catch(Exception ds)
				{
					test.log(LogStatus.PASS, "Void Transaction Report(By Tender Name) available for Specific Date");
					
					//Get the number of rows
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 825, 1)));
					
					//Get the Total amount value
					String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[11]")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
					double totValue = Double.parseDouble(Total_Amount);
					
					//Print the Total_Amount value
					System.out.println("Void Transaction report(By Tender Name) Total Amount is : "+Total_Amount);
					
					test.log(LogStatus.INFO, "Void Transaction report(By Tender Name) Total Amount is : "+Total_Amount);
							
					int numChecks = rows.size() - 1;
					
					double subTot = 0;
					
					for(int a = 2; a <= numChecks; a++)
					{
						Thread.sleep(2000);
						//Click the Required Check
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+a+"]/td[1]/a")).click();
						
	WebElement ele=driver.findElement(By.xpath(excel.getData(3, 1601, 1)));
						
						WebDriverWait wait=new WebDriverWait(driver,150);
						wait.until(ExpectedConditions.visibilityOf(ele));
						
						Thread.sleep(3000);
						String st = driver.findElement(By.xpath(excel.getData(3, 1601, 1))).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
						double subTotal = Double.parseDouble(st);
						
						//Clear the email field
						driver.findElement(By.xpath(excel.getData(1, 778, 1))).clear();
						//Enter the Required mail id
						driver.findElement(By.xpath(excel.getData(1, 778, 1))).sendKeys("sappanimuthub@benseron.com");
						
						Thread.sleep(2000);
						//Click the Send Receipt button
						driver.findElement(By.xpath(excel.getData(1, 833, 1))).click();
						
						Thread.sleep(2000);
						if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Email receipt sent successfully"))
						{
							test.log(LogStatus.PASS, "Email receipt sent successfully");
						}
						else
						{
							test.log(LogStatus.FAIL, "Email receipt sent fail");
						}
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
						
						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
						Thread.sleep(5000);
						//Click the Back button
						driver.findElement(By.xpath(excel.getData(3, 2144, 1))).click();
						Thread.sleep(3000);
						
						subTot = subTot + subTotal;
						if(a == numChecks)
						{
							Thread.sleep(3000);
							//Enter the SaleRecap Url
							driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"report/saleRecap");
							
							Thread.sleep(3000);
							
							Thread.sleep(3000);
							//Click the Select Recap Type option
							driver.findElement(By.xpath(excel.getData(1, 839, 1))).click();
							//Enter the required department
							driver.findElement(By.xpath(excel.getData(1, 840, 1))).sendKeys("Time period");
							//Press the Enter Key
							driver.findElement(By.xpath(excel.getData(1, 840, 1))).sendKeys(Keys.ENTER);
						
							Thread.sleep(3000);
							//Click the Employee option
							driver.findElement(By.xpath(excel.getData(1, 841, 1))).click();
							//Enter the required employee
							driver.findElement(By.xpath(excel.getData(1, 842, 1))).sendKeys("All");
							//Press the Enter Key
							driver.findElement(By.xpath(excel.getData(1, 842, 1))).sendKeys(Keys.ENTER);
							
							Thread.sleep(3000);
							//Click the Time Period option
							driver.findElement(By.xpath(excel.getData(1, 843, 1))).click();
							//Enter the required Time Period
							driver.findElement(By.xpath(excel.getData(1, 844, 1))).sendKeys("Date Range");
							//Press the Enter Key
							driver.findElement(By.xpath(excel.getData(1, 844, 1))).sendKeys(Keys.ENTER);
							
							Thread.sleep(2000);
							//Clear the date field
							driver.findElement(By.xpath(excel.getData(1, 845, 1))).clear();
							//Enter the date
							driver.findElement(By.xpath(excel.getData(1, 845, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));

							Thread.sleep(2000);
							//Clear the date field
							driver.findElement(By.xpath(excel.getData(1, 846, 1))).clear();
							//Enter the date
							driver.findElement(By.xpath(excel.getData(1, 846, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
							
							Thread.sleep(2000);
							//Click the Run Button
							driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
						
							Thread.sleep(5000);
							
							//Get the Gross Void value
							String gV = driver.findElement(By.xpath(excel.getData(1, 847, 1))).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
							double GrossVoid = Double.parseDouble(gV);
							
							//Get the Net Void value
							String nv = driver.findElement(By.xpath(excel.getData(1, 848, 1))).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
							double netVoid = Double.parseDouble(nv);
							
							
							if(subTot == netVoid)
							{
								test.log(LogStatus.PASS, "Net Void and Subtotal for all Checks are same and Value is : "+subTot);
							}
							else
							{
								double diff = subTot - netVoid;
								test.log(LogStatus.INFO, "Net Void and Subtotal for all Checks are different and Value is : "+diff);
							}
							
							if(totValue == GrossVoid)
							{
								test.log(LogStatus.PASS, "Gross Void and Total Amount are same and Value is : "+totValue);
							}
							else
							{
								double diff = totValue - GrossVoid;
								test.log(LogStatus.INFO, "Gross Void and Total Amount are different and Value is : "+diff);wb.close();
							}
							driver.findElement(By.tagName("html")).sendKeys(Keys.END);
							
							String scnShot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
							String s1="data:image/png;base64,"+scnShot1;
							test.log(LogStatus.INFO,test.addScreenCapture(s1));
						}
					} wb.close();
			Thread.sleep(5000);
				}
			}
}
