package epicList_Chrome_Account_User;

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
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;
 
public class Inventory_Reports_Matrix_Report 
{
		public WebDriver driver;
		
		ExtentReports rep = ExtentManager.getInstance();
		ExtentTest test = rep.startTest("Inventory_Reports_Matrix Report");

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
				Inventory_Reports_Matrix_Openpage(driver);
				Inventory_Reports_Verify_Matrix_Report(driver);
				Inventory_Reports_Verify_Matrix_Report_for_Category(driver);
				Inventory_Reports_Verify_Matrix_Report_for_Sub_Category(driver);
				Thread.sleep(1500);
			}
			@Test(priority=11,enabled=false)
			public void Inventory_Reports_Matrix_Openpage(WebDriver driver) throws Exception
			{
				
				  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				  
					Thread.sleep(5000);

					//Get the URl
					driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"matrixReport");
					Thread.sleep(15000);
					try
					{
					//driver.findElement(By.xpath("//span[.=' Purchased Items ']")).click();
					//Check Inventory Items page opened or not
					if(driver.findElement(By.xpath(excel.getData(3, 2277, 1))).getText().equalsIgnoreCase("Matrix Report"))
					{
						test.log(LogStatus.PASS, "Matrix Reports page loaded Successfully");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
					else
					{
						test.log(LogStatus.FAIL, "Matrix Reports page loaded Failed");

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
					Thread.sleep(5000);

			}

			@Test(priority=12,enabled=false)
			public void Inventory_Reports_Verify_Matrix_Report(WebDriver driver) throws Exception
			{
				  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				  
					Thread.sleep(15000);

					test.log(LogStatus.INFO, "Verify Matrix Report for All Starts");
					
					//Select the Level
					Select Level=new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
					Level.selectByVisibleText("All");
					
					Thread.sleep(1000);
					//Select the Time Period
					Select TimePeriod=new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
					TimePeriod.selectByVisibleText("Date Range");

					Thread.sleep(1000);
					//Claer the from date
					driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
					//Enter the from date
					driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
					
					Thread.sleep(1000);
					//Clear the To date
					driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
					//Enter the To date
					driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
					
					Thread.sleep(3000);
					//click the Get Matrix Report button
					driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
					
					Thread.sleep(10000);
					
					try
					{
						if(driver.findElement(By.xpath(excel.getData(3, 2275, 1))).getText().equalsIgnoreCase("No Records Found"))
						{
							test.log(LogStatus.FAIL, "There is no Matrix Report Available for this date");
					
							String scnshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
							String s="data:image/png;base64,"+scnshot;
							test.log(LogStatus.FAIL, test.addScreenCapture(s));
						}
					}
					catch(Exception e)
					{
						test.log(LogStatus.PASS, "Matrix Report Available for this date ");
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.PASS,test.addScreenCapture(s));
						
						List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
				
						int rowSize=rows.size();
						
					/*	for(int i=1;i<=rowSize;i++)
						{
							
							//Get the Matrix
							test.log(LogStatus.INFO, "Menu Item Name is : "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[1]")).getText()+ "   Date : "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[2]")).getText()+ "  Quantity & Price :"+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]")).getText()+ "  Status :"+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[3]")).getText());
							System.out.println("Menu Item Name is : "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[1]")).getText()+ "  Date : "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[2]")).getText()+ "  Quantity & Price :"+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]")).getText()+ "  Status :"+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[3]")).getText());
						
						}*/
					
					}
					test.log(LogStatus.INFO, "Verify Matrix Report for All Ends");

			}
			
			@Test(priority=13,enabled=false)
			public void Inventory_Reports_Verify_Matrix_Report_for_Category(WebDriver driver) throws Exception
			{
				  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				  
					Thread.sleep(15000);

					test.log(LogStatus.INFO, "Verify Matrix Report for Category Starts");

					
					//Select the Level
					Select Level=new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
					Level.selectByVisibleText("Category");
					
					Thread.sleep(1000);
					//Select the Time Period
					Select TimePeriod=new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
					TimePeriod.selectByVisibleText("Date Range");

					Thread.sleep(1000);
					//Claer the from date
					driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
					//Enter the from date
					driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
					
					Thread.sleep(1000);
					//Clear the To date
					driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
					//Enter the To date
					driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
					
					Thread.sleep(3000);
					//click the Get Matrix Report button
					driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
					
					Thread.sleep(10000);
					
					try
					{
						if(driver.findElement(By.xpath(excel.getData(3, 2275, 1))).getText().equalsIgnoreCase("No Records Found"))
						{
							test.log(LogStatus.FAIL, "There is no Matrix Report Available for this date");
					
							String scnshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
							String s="data:image/png;base64,"+scnshot;
							test.log(LogStatus.FAIL, test.addScreenCapture(s));
						}
					}
					catch(Exception e)
					{
						test.log(LogStatus.PASS, "Matrix Report Available for this date ");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.PASS,test.addScreenCapture(s));
						
						List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
				
						int rowSize=rows.size();
						
					/*	for(int i=1;i<=rowSize;i++)
						{
							
							//Get the Matrix
							test.log(LogStatus.INFO, "Menu Item Name is : "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[1]")).getText()+ "   Date : "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[2]")).getText()+ "  Quantity & Price :"+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]")).getText()+ "  Status :"+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[3]")).getText());
							System.out.println("Menu Item Name is : "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[1]")).getText()+ "  Date : "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[2]")).getText()+ "  Quantity & Price :"+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]")).getText()+ "  Status :"+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[3]")).getText());
						
						}*/
					
					}
					test.log(LogStatus.INFO, "Verify Matrix Report for Category Ends");

			}
			
			@Test(priority=14,enabled=false)
			public void Inventory_Reports_Verify_Matrix_Report_for_Sub_Category(WebDriver driver) throws Exception
			{
				  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				  
					Thread.sleep(15000);

					test.log(LogStatus.INFO, "Verify Matrix Report for Sub Category Starts");

					
					//Select the Level
					Select Level=new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
					Level.selectByVisibleText("Sub Category");
					
					Thread.sleep(1000);
					//Select the Time Period
					Select TimePeriod=new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
					TimePeriod.selectByVisibleText("Date Range");

					Thread.sleep(1000);
					//Claer the from date
					driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
					//Enter the from date
					driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
					
					Thread.sleep(1000);
					//Clear the To date
					driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
					//Enter the To date
					driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
					
					Thread.sleep(3000);
					//click the Get Matrix Report button
					driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
					
					Thread.sleep(10000);
					
					try
					{
						if(driver.findElement(By.xpath(excel.getData(3, 2275, 1))).getText().equalsIgnoreCase("No Records Found"))
						{
							test.log(LogStatus.FAIL, "There is no Matrix Report Available for this date");
					
							String scnshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
							String s="data:image/png;base64,"+scnshot;
							test.log(LogStatus.FAIL, test.addScreenCapture(s));
						}
					}
					catch(Exception e)
					{
						test.log(LogStatus.PASS, "Matrix Report Available for this date ");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.PASS,test.addScreenCapture(s));
						
						List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
				
						int rowSize=rows.size();
						
					/*	for(int i=1;i<=rowSize;i++)
						{
							
							//Get the Matrix
							test.log(LogStatus.INFO, "Menu Item Name is : "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[1]")).getText()+ "   Date : "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[2]")).getText()+ "  Quantity & Price :"+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]")).getText()+ "  Status :"+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[3]")).getText());
							System.out.println("Menu Item Name is : "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[1]")).getText()+ "  Date : "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[2]")).getText()+ "  Quantity & Price :"+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]")).getText()+ "  Status :"+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[3]")).getText());
						
						}*/
					
					}
					
					test.log(LogStatus.INFO, "Verify Matrix Report for Sub Category Ends");

			}
}