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
import org.openqa.selenium.support.ui.ExpectedCondition;
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

public class Verify_Transaction_Report {

	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Transaction_Report");

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
			Transaction_Report_Method_openpage(driver);
			Transaction_Report_Method_For_Tender_Type(driver);
			Transaction_Report_Method_For_Tender_Name(driver);
			Thread.sleep(1500);
		}
	
			@Test(priority=2,enabled = false)
			public void Transaction_Report_Method_openpage(WebDriver driver) throws Exception
			{
                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(12000);
/*				WebElement html = driver.findElement(By.tagName("html"));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				
				//Click the Reports option
				driver.findElement(By.xpath("//span[.='Reports']")).click();Thread.sleep(2000);
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[.='Transactions']"));Thread.sleep(2000);
				//Scroll the page till the Transactions option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Transactions Option		
				driver.findElement(By.xpath("//span[.='Transactions']")).click();*/
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"report/transactionSale");
				Thread.sleep(5000);
				try
				{
				//Check Transactions Report page opened or not
				if(driver.findElement(By.xpath(excel.getData(1, 820, 1))).getText().equalsIgnoreCase("Transaction Sale"))
				{
					test.log(LogStatus.PASS, "Transactions report page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Transactions report page loaded Failed");
				
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
				
				Thread.sleep(2000);wb.close();
	
			}
			
			@Test(priority=3,enabled = false)
			public void Transaction_Report_Method_For_Tender_Type(WebDriver driver) throws Exception
			{
				    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					Thread.sleep(12000);
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
				catch(Exception fg)
				{
					test.log(LogStatus.PASS, "Transaction Report(By Tender Type) available for Specific Date");
					
					//Get the number of rows
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 825, 1)));
					
					//Get the Total amount value
					String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[10]")).getText();
					
					//Print the Total_Amount value
					System.out.println("Transaction report(By Tender Type) Total Amount is : "+Total_Amount);
					
					test.log(LogStatus.INFO, "Transaction report(By Tender Type) Total Amount is : "+Total_Amount);wb.close();
				
					driver.findElement(By.tagName("html")).sendKeys(Keys.END);
					
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
			}
			
			@Test(priority=4,enabled = false)
			public void Transaction_Report_Method_For_Tender_Name(WebDriver driver) throws Exception
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
						test.log(LogStatus.FAIL, "Transaction Report(By Tender Name) is not available for Specific Date");
					
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				}
				catch(Exception ds)
				{
					test.log(LogStatus.PASS, "Transaction Report(By Tender Name) available for Specific Date");
					
					//Get the number of rows
					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 825, 1)));
					
					//Get the Total amount value
					String Total_Amount = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[10]")).getText();
								
					//Print the Total_Amount value
					System.out.println("Transaction report(By Tender Name) Total Amount is : "+Total_Amount);
					
					test.log(LogStatus.INFO, "Transaction report(By Tender Name) Total Amount is : "+Total_Amount);
					
						
			/*					String netSales = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[1]/td[3]/span")).getText();
						double netSalesAmt = Double.parseDouble(netSales);*/
						
						//driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[1]/td[1]/a")).click();
	
						int rowco = rows.size() - 1;
						//Get Amount Value
						String totAmt = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[11]")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
						double totalAmt = Double.parseDouble(totAmt);
							
						//Get CC SErvice Charge
						String ccServiceCharge = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[10]")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
						double ccServiceChargeAmt = Double.parseDouble(ccServiceCharge);
										
						//Get the Tip Amount
						String tip = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[9]")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
						double tipAmt = Double.parseDouble(tip);
						
						Thread.sleep(3000);
						//Click the Required Check
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rowco+"]/td[1]/a")).click();
						
						WebElement ele=driver.findElement(By.xpath(excel.getData(1, 830, 1)));
						
						WebDriverWait wait=new WebDriverWait(driver,150);
						wait.until(ExpectedConditions.visibilityOf(ele));
						
						
						Thread.sleep(3000);
						//Get the Total Value
						String tot = driver.findElement(By.xpath(excel.getData(1, 830, 1))).getText().replaceAll("[a-zA-Z $ ₹ , :]", "").toString().substring(1);
						double totaAmt = Double.parseDouble(tot);
							
						//Get the CC Service Charge Value
						String ccs =  driver.findElement(By.xpath(excel.getData(1, 831, 1))).getText().replaceAll("[a-zA-Z $ ₹ , :]", "").toString().substring(1);
						double ccsAmt = Double.parseDouble(ccs);
						
						//Get the Tip Value
						String tips =  driver.findElement(By.xpath(excel.getData(1, 832, 1))).getText().replaceAll("[a-zA-Z $ ₹ , :]", "").toString().substring(1);
						double tipsAmt = Double.parseDouble(tips);
	
						//Check whether the Total Amount value is correct or not 
						if(totalAmt == totaAmt)
						{
							test.log(LogStatus.PASS, "Total Amount value is correct");
						}
						else
						{
							double diff = totalAmt - totaAmt;
							test.log(LogStatus.INFO, "Total Amount value is different : "+diff);
						}
						
						//Check whether the Total Tax is correct or not
						if(tipAmt == tipsAmt)
						{
							test.log(LogStatus.PASS, "Tip value is correct");
						}
						else
						{
							double diff = tipAmt - tipsAmt;
							test.log(LogStatus.INFO, "Tip value is different : "+diff);
						}
						
						//Check whether the CC Service Charge is correct or not
						if(ccServiceChargeAmt == ccsAmt)
						{
							test.log(LogStatus.PASS, "CC Service Charge value is correct");
						}
						else
						{
							double diff = ccServiceChargeAmt - ccsAmt;
							test.log(LogStatus.INFO, "CC Service Charge value is different : "+diff);
						}
								
						//Clear the email field
						driver.findElement(By.xpath(excel.getData(1, 778, 1))).clear();
						//Enter the Required mail id
						driver.findElement(By.xpath(excel.getData(1, 778, 1))).sendKeys("sappanimuthub@benseron.com");
						
						Thread.sleep(2000);
						//Click the Send Receipt button
						driver.findElement(By.xpath(excel.getData(1, 833, 1))).click();
						
						Thread.sleep(1500);
						if(driver.findElement(By.xpath(excel.getData(1, 835, 1))).getText().equalsIgnoreCase("Email receipt sent successfully"))
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
						
/*						Thread.sleep(2000);
						//Click the Send Receipt button
						driver.findElement(By.xpath("//i[@class='fa fa-file-pdf-o']/../../button/i")).click();
						
						Thread.sleep(2000);
						if(driver.findElement(By.xpath("//span[contains(.,'Payment Receipt sent successfully')]")).getText().equalsIgnoreCase("Payment Receipt sent successfully"))
						{
							test.log(LogStatus.PASS, "Payment receipt sent successfully");
						}
						else
						{
							test.log(LogStatus.FAIL, "Payment receipt sent fail");
						}*/
						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
						Thread.sleep(5000);
						//Click the Back button
						driver.findElement(By.xpath(excel.getData(3, 2144, 1))).click();
						Thread.sleep(5000);wb.close();
					
				}
			}
}
