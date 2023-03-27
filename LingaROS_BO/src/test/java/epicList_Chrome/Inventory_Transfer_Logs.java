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
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Inventory_Transfer_Logs {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Transfer_Logs");

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
			Inventory_Transfer_Logs_Pageopen(driver);
			Inventory_Transfer_Logs_ALL(driver);
			Inventory_Transfer_Logs_ALL_Transferred(driver);
			Inventory_Transfer_Logs_InventoryItem(driver);
			Inventory_Transfer_Logs_InventoryItem_Transferred(driver);
			Inventory_Transfer_Logs_SubRecipe(driver);
			Inventory_Transfer_Logs_SubRecipe_Transferred(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=53)
		public void Inventory_Transfer_Logs_Pageopen(WebDriver driver) throws Exception
		{
		
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis); 
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
		 
					//Get the URl
					driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"transfer/transferLogs");
					Thread.sleep(5000);
					
					try
					{
					//Check Inventory Items page opened or not
					if(driver.findElement(By.xpath(excel.getData(3, 1680, 1))).getText().equalsIgnoreCase("Transfer Logs"))
					{
						test.log(LogStatus.PASS, " Transfer Logs report loaded Successfully");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
					else
					{
						test.log(LogStatus.FAIL, " Transfer Logs report loaded Failed");
					
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
					Thread.sleep(1000);
				}
				
		@Test(enabled=false,priority=54) 
		public void Inventory_Transfer_Logs_ALL(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Select type as ALL 
				Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1681, 1))));
				ALL.selectByVisibleText("All"); 
			 	
				Thread.sleep(3000);
				//Select type as Time period
				Select Timeperiod= new Select(driver.findElement(By.xpath(excel.getData(3, 1682, 1))));
				Timeperiod.selectByVisibleText("Date Range"); 
			 	
				//Select the From Date Range
				driver.findElement(By.xpath(excel.getData(3, 1683, 1))).clear();
				//Enter the required From Date Range
				driver.findElement(By.xpath(excel.getData(3, 1683, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
						
				//Select the TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1684, 1))).clear();
				//Enter the  required TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1684, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				//Select type as Time period
				Select TransferStatus = new Select(driver.findElement(By.xpath(excel.getData(3, 1685, 1))));
				TransferStatus.selectByVisibleText("Received"); 
				Thread.sleep(1000);
				//Click the Search button
				driver.findElement(By.cssSelector(excel.getData(3, 1701, 4))).click();
				
				Thread.sleep(2000);
				//Check weather data's available or not
				if(driver.findElement(By.xpath(excel.getData(3, 1686, 1))).getText().equalsIgnoreCase("No transfer record for selected item or time period"))
				{
					test.log(LogStatus.PASS, "No transfer record for selected item or time period for received status for recieved (ALL)");
				}
				else if(driver.findElement(By.xpath(excel.getData(3, 1687, 1))).getText() != null && driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr[2]/td[7]")).getText() != null)
				{
					test.log(LogStatus.PASS, "Transaction Report(By Received ) available for Specific Date status Recieved(ALL)");
					
					//Get the number of rows
					 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1688, 1)));
					for(int i = 1; i <= rows.size(); i++)
					{
						//Get the Consumption Log Quantity value
						test.log(LogStatus.PASS,"In Transfer Logs source of the " +driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[1]")).getText() +" store "+ driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText()+ " item is "+" "+  driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[6]")).getText()+ " Quantity");
					}		
					}		
				Thread.sleep(3000);
				wb.close();
				}
				
		@Test(enabled=false,priority=55) 
		public void Inventory_Transfer_Logs_ALL_Transferred(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
					//Select type as Time period
					Select TransferStatus = new Select(driver.findElement(By.xpath(excel.getData(3, 1685, 1))));
					TransferStatus.selectByVisibleText("Transferred"); 
					
					Thread.sleep(2000);
					//Click the search button
					driver.findElement(By.cssSelector(excel.getData(3, 1701, 4))).click();
					
					//Check weather data's available or not
					if(driver.findElement(By.xpath(excel.getData(3, 1686, 1))).getText().equalsIgnoreCase("No transfer record for selected item or time period"))
					{
						test.log(LogStatus.PASS, "No transfer record for selected item or time period for Transdfered status (ALL)");
					}
					else if(driver.findElement(By.xpath(excel.getData(3, 1687, 1))).getText() != null && driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr[2]/td[7]")).getText() != null)
					{
						test.log(LogStatus.PASS, "Transaction Report available for Specific Date Transferred(ALL)");
						
						//Get the number of rows
						 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1688, 1)));
						for(int i = 1; i <= rows.size(); i++)
						{
							//Get the Consumption Log Quantity value
							test.log(LogStatus.PASS,"In Transfer Logs source of the " +driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[1]")).getText() +" store "+ driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText()+ " item is "+" "+  driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[6]")).getText()+ " Quantity");
						}	
					}
					wb.close();
				}
				
		@Test(enabled=false,priority=56) 
		public void Inventory_Transfer_Logs_InventoryItem(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Select type as ALL 
				Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1681, 1))));
				ALL.selectByVisibleText("Inventory Item"); 
			 	
				//Click the Search 
				driver.findElement(By.xpath(excel.getData(3, 1690, 1))).click();
				Thread.sleep(3000);
				//Enter the Adjust Inventory item
				driver.findElement(By.xpath(excel.getData(3, 1691, 1))).sendKeys(Keys.ARROW_DOWN);
				Thread.sleep(3000);
				//Enter the Adjust Inventory item
				driver.findElement(By.xpath(excel.getData(3, 1691, 1))).sendKeys(Keys.ENTER);
				
				//Click the Search 
				driver.findElement(By.xpath(excel.getData(3, 1696, 1))).click();
				Thread.sleep(3000);
				//Enter the Adjust Inventory item
				driver.findElement(By.xpath(excel.getData(3, 1697, 1))).sendKeys(Keys.ARROW_DOWN);
				Thread.sleep(3000);
				//Enter the Adjust Inventory item
				driver.findElement(By.xpath(excel.getData(3, 1697, 1))).sendKeys(Keys.ENTER);	 	
				 	
				Thread.sleep(3000);
				//Select type as Time period
				Select Timeperiod= new Select(driver.findElement(By.xpath(excel.getData(3, 1698, 1))));
				Timeperiod.selectByVisibleText("Date Range"); 
			 
				//Select the From Date Range
				driver.findElement(By.xpath(excel.getData(3, 1699, 1))).clear();
				//Enter the required From Date Range
				driver.findElement(By.xpath(excel.getData(3, 1699, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
						
				//Select the TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1700, 1))).clear();
				//Enter the  required TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1700, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				//Select type as Time period
				Select TransferStatus = new Select(driver.findElement(By.xpath(excel.getData(3, 1695, 1))));
				TransferStatus.selectByVisibleText("Received"); 
					
				Thread.sleep(1000);
				//Click the Search button
				driver.findElement(By.cssSelector(excel.getData(3, 1701, 4))).click();
				
				//Check weather data's available or not
					if(driver.findElement(By.xpath(excel.getData(3, 1686, 1))).getText().equalsIgnoreCase("No transfer record for selected item or time period"))
					{
						test.log(LogStatus.PASS, "No transfer record for selected item or time period for received status InventoryItem_Recieved");
					}
					else if(driver.findElement(By.xpath(excel.getData(3, 1687, 1))).getText() != null && driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr[2]/td[7]")).getText() != null)
					{
						test.log(LogStatus.PASS, "Transaction Report(By Received ) available for Specific Date");
						
						//Get the number of rows
						 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1688, 1)));
						for(int i = 1; i <= rows.size(); i++)
						{
							//Get the Consumption Log Quantity value
							test.log(LogStatus.PASS,"In Transfer Logs source of the " +driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[1]")).getText() +" store "+ driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText()+ " item is "+" "+  driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[6]")).getText()+ " Quantity");
						}		
				
					}wb.close();
					}
				
		@Test(enabled=false,priority=57) 
		public void Inventory_Transfer_Logs_InventoryItem_Transferred(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
		//Select type as Time period
					Select TransferStatus = new Select(driver.findElement(By.xpath(excel.getData(3, 1695, 1))));
					TransferStatus.selectByVisibleText("Transferred"); 
						
					Thread.sleep(1000);
					//Click the Search button
					driver.findElement(By.cssSelector(excel.getData(3, 1701, 4))).click();
					
					//Check weather data's available or not
							if(driver.findElement(By.xpath(excel.getData(3, 1686, 1))).getText().equalsIgnoreCase("No transfer record for selected item or time period"))
							{
								test.log(LogStatus.PASS, "No transfer record for selected item or time period for received status InventoryItem_Transfered");
							}
							else if(driver.findElement(By.xpath(excel.getData(3, 1687, 1))).getText() != null && driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr[2]/td[7]")).getText() != null)
							{
								test.log(LogStatus.PASS, "Transaction Report(By Transfered status) available for Specific Date");
								
								//Get the number of rows
								 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1688, 1)));
								for(int i = 1; i <= rows.size(); i++)
								{
									//Get the Consumption Log Quantity value
									test.log(LogStatus.PASS,"In Transfer Logs source of the " +driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[1]")).getText() +" store "+ driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText()+ " item is "+" "+  driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[6]")).getText()+ " Quantity");
								}	
							} wb.close();
					}	
				
		@Test(enabled=false,priority=58) 
		public void Inventory_Transfer_Logs_SubRecipe(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Select type as ALL 
				Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1681, 1))));
				ALL.selectByVisibleText("Sub Recipe"); 
			 	
				//Click the Sub recipe
				driver.findElement(By.xpath(excel.getData(3, 1690, 1))).click();
				Thread.sleep(3000);
				//Enter the Adjust Inventory item
				driver.findElement(By.xpath(excel.getData(3, 1691, 1))).sendKeys("ALL");
				Thread.sleep(3000);
				//Enter the Adjust Inventory item
				driver.findElement(By.xpath(excel.getData(3, 1691, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(3000);
				//Select type as Time period
				Select Timeperiod= new Select(driver.findElement(By.xpath(excel.getData(3, 1692, 1))));
				Timeperiod.selectByVisibleText("Date Range"); 
			 
				//Select the From Date Range
				driver.findElement(By.xpath(excel.getData(3, 1693, 1))).clear();
				//Enter the required From Date Range
				driver.findElement(By.xpath(excel.getData(3, 1693, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
						
				//Select the TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1694, 1))).clear();
				//Enter the  required TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1694, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				//Select type as Time period
				Select TransferStatus = new Select(driver.findElement(By.xpath(excel.getData(3, 1689, 1))));
				TransferStatus.selectByVisibleText("Received"); 

				//Check weather data's available or not
					if(driver.findElement(By.xpath(excel.getData(3, 1686, 1))).getText().equalsIgnoreCase("No transfer record for selected item or time period"))
					{
						test.log(LogStatus.PASS, "No transfer record for selected item or time period for received status SubRecipe_Recieved");
					}
					else if(driver.findElement(By.xpath(excel.getData(3, 1687, 1))).getText() != null && driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr[2]/td[7]")).getText() != null)
					{
						test.log(LogStatus.PASS, "Transaction Report(By Received ) available for Specific Date");
						
						//Get the number of rows
						 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1688, 1)));
						for(int i = 1; i <= rows.size(); i++)
						{
							//Get the Consumption Log Quantity value
							test.log(LogStatus.PASS,"In Transfer Logs source of the " +driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[1]")).getText() +" store "+ driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText()+ " item is "+" "+  driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[6]")).getText()+ " Quantity");
						}	}		
					wb.close();
					}
				
		@Test(enabled=false,priority=59) 
		public void Inventory_Transfer_Logs_SubRecipe_Transferred(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Select type as Time period
					Select TransferStatus = new Select(driver.findElement(By.xpath(excel.getData(3, 1689, 1))));
					TransferStatus.selectByVisibleText("Transferred"); 
					
					//Click the Search button
							driver.findElement(By.cssSelector(excel.getData(3, 1701, 4))).click();
							
							//Check weather data's available or not
									if(driver.findElement(By.xpath(excel.getData(3, 1686, 1))).getText().equalsIgnoreCase("No transfer record for selected item or time period"))
									{
										test.log(LogStatus.PASS, "No transfer record for selected item or time period for received status SubRecipe_Transferred");
									}
									else if(driver.findElement(By.xpath(excel.getData(3, 1687, 1))).getText() != null && driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr[2]/td[7]")).getText() != null)
									{
										test.log(LogStatus.PASS, "Transaction Report(By Transfered status) available for Specific Date");
										
										//Get the number of rows
										 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1688, 1)));
										for(int i = 1; i <= rows.size(); i++)
										{
											//Get the Consumption Log Quantity value
											test.log(LogStatus.PASS,"In Transfer Logs source of the " +driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[1]")).getText() +" store "+ driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText()+ " item is "+" "+  driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[6]")).getText()+ " Quantity");
										}
									}
									wb.close();
			}

}
