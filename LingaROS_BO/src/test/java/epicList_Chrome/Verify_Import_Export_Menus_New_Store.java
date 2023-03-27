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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Verify_Import_Export_Menus_New_Store 
{
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify Import/Export Menus's for New Store");
	
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
		{
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
			{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath("//h2[.='Sign in']")).getText().equalsIgnoreCase("Sign in"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully for Dealer App");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed for Dealer App");
			}
			Thread.sleep(3000);
			//Close the Browser
			driver.close();
		}
		else
		{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath("//h2[.='Sign in']")).getText().equalsIgnoreCase("Sign in"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully LingaPOs");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed LingaPos");
			}
			Thread.sleep(3000);
			//Close the Browser
			driver.close();
		}
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
			//open_Enterprise_Page(driver);
			//create_New_Store(driver);
			//import_Menu_Items(driver);
			verify_ExportedData_Departments(driver);
			//verify_ExportedData_Coursing(driver);
			Thread.sleep(1500);
		}
		
		@Test(priority=4,enabled=false)
		public void open_Enterprise_Page(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//To Get URL
			driver.get((Utility.getProperty("Enterprise_Base_URL")));
			
			Thread.sleep(5000);
			try
			{
			//Check whether the Enterprise page loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 2219, 1))).getText().equalsIgnoreCase("My Stores"))
			{
				test.log(LogStatus.PASS, "Enterprise Page Loaded successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Enterprise page loaded Failed");
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
		}
		
		@Test(priority=5,enabled=false)
		public void create_New_Store(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			Thread.sleep(5000);
			//Click Add Store button
			driver.findElement(By.xpath(excel.getData(3, 2220, 1))).click();
			
			Thread.sleep(3000);
			//Check whether the New Store creation page loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 2221, 1))).getText().equalsIgnoreCase("Store"))
			{
				test.log(LogStatus.PASS, "New Store creation page Loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Creation page Loaded Failed");
			}
		
			//Enter the Store Name
			driver.findElement(By.xpath(excel.getData(3, 2222, 1))).sendKeys(Utility.getProperty("New_Store_Name"));
		
			//Enter the Phone Number
			driver.findElement(By.name(excel.getData(3, 286, 3))).sendKeys("New_Store_Phone");
			
			//Enter the Store Email ID
			driver.findElement(By.name(excel.getData(3, 287, 3))).sendKeys("New_Store_Email");
			
			//Enter the Address Line 1
			driver.findElement(By.name(excel.getData(3, 288, 3))).sendKeys("New_Store_Address_Line1");
			
			//Enter the Address Line 2
			driver.findElement(By.name(excel.getData(3, 289, 3))).sendKeys("New_Store_Address_Line2");

			//Enter the City
			driver.findElement(By.name(excel.getData(3, 290, 3))).sendKeys("New_Store_City");

			//Enter the State
			driver.findElement(By.name(excel.getData(3, 291, 3))).sendKeys("New_Store_State");

			//Enter the Zipcode
			driver.findElement(By.name(excel.getData(3, 292, 3))).sendKeys("New_Store_ZipCode");

			//Click the Country
			driver.findElement(By.xpath(excel.getData(3, 2223, 1))).click();
			//Select the Country
			driver.findElement(By.xpath(excel.getData(3, 2224, 1))).sendKeys("India");
			//enter the Country
			driver.findElement(By.xpath(excel.getData(3, 2224, 1))).sendKeys(Keys.ENTER);
			
			
			//Click the Time Zone
			driver.findElement(By.xpath(excel.getData(3, 2225, 1))).click();
			//Select the Time Zone
			driver.findElement(By.xpath(excel.getData(3, 2226, 1))).sendKeys("(GMT+05:30) Chennai, Kolkata, Mumbai, New Delhi");
			//enter the Time Zone
			driver.findElement(By.xpath(excel.getData(3, 2226, 1))).sendKeys(Keys.ENTER);
		
	/*		//Click the Language
			driver.findElement(By.xpath(excel.getData(3, 2227, 1))).click();
			//Select the Language 
			driver.findElement(By.xpath(excel.getData(3, 2228, 1))).sendKeys("Tamil");
			//enter the Language 
			driver.findElement(By.xpath(excel.getData(3, 2228, 1))).sendKeys(Keys.ENTER);*/
		
			Thread.sleep(4000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			Thread.sleep(4000);
			//Check whether the New Store Created or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Store saved successfully"))
			{
				test.log(LogStatus.PASS, "New Store Created Successfully");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "New Store Created Failed");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			
			
		}

		@Test(priority=6,enabled=false)
		public void import_Menu_Items(WebDriver driver) throws Exception
		{
			
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		/*	try
			{
				if(driver.findElement(By.xpath("//span[.='Do You Want to Take a look.. ']")).isDisplayed())
				{
					driver.findElement(By.xpath("//button[contains(.,'Not now')]")).click();
				}		
			}
			catch(Exception e) {}
			*/
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"importExportMenu");
			
			Thread.sleep(5000);
			//Check whether the Import/Export page Loaded or not
			if(driver.findElement(By.xpath("//a[.='Menu Import/Export']")).getText().equalsIgnoreCase("Menu Import/Export"))
			{
				test.log(LogStatus.PASS, "Import/Export page Loaded Successfully");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Import/Export page Loaded Failed");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			
			Thread.sleep(4000);
			//Click the Browse Button 
			//driver.findElement(By.xpath(excel.getData(3, 2216, 1))).sendKeys(Utility.getProperty("Import_Export_Template_Path"));
			WebElement upl=driver.findElement(By.xpath(excel.getData(3, 2216, 1)));
			upl.sendKeys("C:\\Automation\\Web_POS\\Menu.xlsx");
			
			Thread.sleep(4000);	
			//Click the Upload Button
			driver.findElement(By.xpath(excel.getData(3, 2218, 1))).click();
			
			Thread.sleep(4000);
			//Chevk whether the File was Exported( Uploaded) or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Menu Import is processing now. Please check back in few minutes!."))
			{
				test.log(LogStatus.PASS, "Menu Exported/Uploaded Successfully");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Menu Exported/Uploaded Failed");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
		}

		@Test(priority=7,enabled=false)
		public void verify_ExportedData_Departments(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			
			File src1 = new File(Utility.getProperty("Import_Export_Template_Path"));
			
		//	FileInputStream fis1 = new FileInputStream(src1);
			
			//XSSFWorkbook wb1 = new XSSFWorkbook(fis1);
			
			//XSSFSheet sheet1 = wb1.getSheetAt(0);
						
		//	int rowCount = sheet1.getLastRowNum();

			//int sheetCount=wb1.getNumberOfSheets();
			
			
			ExcelDataConfig menu=new ExcelDataConfig(Utility.getProperty("Import_Export_Template_Path"));
			 
		//	driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"department");
			
			Thread.sleep(5000);
			//Click the Departments on Product/Items Menu's
			driver.findElement(By.xpath("//span[.='Departments']/../../a")).click();
			
			Thread.sleep(5000);
			//Check whether the Department page Loaded or not
			if(driver.findElement(By.xpath("//a[.='Departments']")).getText().equalsIgnoreCase("Departments"))
			{
				test.log(LogStatus.PASS, "Department page Loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Department page Loaded Failed");
			}
			
			
			Thread.sleep(5000);
			//click the Refresh button
			driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-refresh']")).click();
	
			test.log(LogStatus.INFO, "Department Items Import Validation Starts (Newly Created Store");
			
			for(int i=2;i<=3;i++)
			{
			Thread.sleep(4000);
			//Clear the Search box
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//enter the Department to seatch
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(menu.getData(0, i, 0));
			//Enter the Search
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(5000);
			//Check whwther the Department Available or not
		/*	if(driver.findElement(By.xpath("//td[.='No records found']")).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Department Not Available");
			}
			else
			{
				test.log(LogStatus.PASS, "Department Available");
			}*/
			List<WebElement> dept=driver.findElements(By.xpath("//table[@ng-table='tableParams']/tbody/tr"));
		
			
				if(driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr/td[1]")).getText().equals(menu.getData(0, i, 0)))
				{
					test.log(LogStatus.PASS, "  "+ menu.getData(0, i, 0)+" -- "+"  Department Imported Successfully");
			
					Thread.sleep(4000);
					//Edit the Department
					driver.findElement(By.xpath("//td[contains(.,'"+menu.getData(0, i, 0)+"')]/../td[3]/span[1]/a[1]/i")).click();
					
					Thread.sleep(5000);
					//check whether the New Department page Opened or not
					if(driver.findElement(By.xpath("//span[.='Department']")).getText().equalsIgnoreCase("Department"))
					{
						test.log(LogStatus.PASS, "Department Edit Opened Successfully");
						
					}
					else
					{
						test.log(LogStatus.FAIL,"Department Edit Opened Failed");
					}
				
					Thread.sleep(3000);
					//clear the Department Description
					driver.findElement(By.xpath("//textarea[@name='description']")).clear();
					//Enter the Department description
					driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(menu.getData(0, i, 0));
				
					
					//Click the Update button
					driver.findElement(By.xpath("//span[.='Update']")).click();
					
					WebElement update=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
					WebDriverWait wait=new WebDriverWait(driver,50);
					
					//check whether the Department Updated or not
					if(wait.until(ExpectedConditions.visibilityOf(update)).getText().equalsIgnoreCase("Department Updated Successfully"))
					{
						test.log(LogStatus.PASS, " * "+menu.getData(0, i, 0)+" * "+" Department Updated Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Department Updated Failed");
					}
					
				}
				else
				{
					test.log(LogStatus.INFO, "  "+menu.getData(0, i, 0)+"--"+"  Department Updated Failed");
				}
			}
			
			test.log(LogStatus.INFO, "Department Items Import Validation Ends (Newly Created Store");
			Thread.sleep(5000);
			//catch(Exception c) {}
	/*		for(int l=0;l<sheetCount;l++)
			{
			String sheetName=wb.getSheetName(l);
				if(sheetName.equalsIgnoreCase("Department"))
				{
					for(int i=1;i<rowCount;i++)
					{
						
					}
				}
			}
			for(int i=0;i<=rowCount;i++)
			{
				
			}*/
				
		}
		
		@Test(priority=7,enabled=false)
		public void verify_ExportedData_Coursing(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			
			File src = new File(Utility.getProperty("Import_Export_Template_Path"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);
						
			int rowCount = sheet1.getLastRowNum();

			int sheetCount=wb.getNumberOfSheets();
			
			
			ExcelDataConfig menu=new ExcelDataConfig(Utility.getProperty("Import_Export_Template_Path"));
			 
		//	driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"course");
			Thread.sleep(5000);
			//Click the Courisng in Men screen on Product/Items
			driver.findElement(By.xpath("//span[.='Coursing']/../../a")).click();
			
			
			Thread.sleep(5000);
			//Check whether the Coursing page Loaded or not
			if(driver.findElement(By.xpath("//a[.='Coursing']")).getText().equalsIgnoreCase("Coursing"))
			{
				test.log(LogStatus.PASS, "Coursing page Loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Coursing page Loaded Failed");
			}
			
			
			Thread.sleep(5000);
			//click the Refresh button
			driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-refresh']")).click();
	
			test.log(LogStatus.INFO, "Coursing Items Import Validation Starts (Newly Created Store");

			
			for(int i=2;i<=3;i++)
			{
			Thread.sleep(4000);
			//Clear the Search box
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//enter the Department to seatch
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(menu.getData(1, i, 0));
			//Enter the Search
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(5000);
			//Check whwther the Coursing Available or not
			if(driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr/td[1]")).getText().equals(menu.getData(1, i, 0)))
			{
				test.log(LogStatus.PASS, " "+menu.getData(1, i, 0)+" -- "+"  Coursing  Imported Successfully");
				
				Thread.sleep(5000);
				//edit the Couring
				driver.findElement(By.xpath("//td[contains(.,'"+menu.getData(1, i, 0)+"')]/../td[3]/span[1]/a[1]/i")).click();
				
				Thread.sleep(3000);
				//Check whether the Courisng Edited page Opened or not
				if(driver.findElement(By.xpath("//h3[contains(.,'Coursing')]/span")).getText().equalsIgnoreCase("Coursing"))
				{
					test.log(LogStatus.PASS, "Coursing Editing page Opened Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Coursing Editing page Opened Failed");
				}
			
				Thread.sleep(3000);
				//Clear the Priority
				driver.findElement(By.xpath("//input[@name='priority']")).clear();
				//Enter the Priority
				driver.findElement(By.xpath("//input[@name='priority']")).sendKeys("2");
			
				Thread.sleep(3000);
				//Click the Update button
				driver.findElement(By.xpath("//span[.='Update']")).click();
				
				
				WebElement update=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
				WebDriverWait wait=new WebDriverWait(driver,50);
				
				//check whether the Coursing Updated or not
				if(wait.until(ExpectedConditions.visibilityOf(update)).getText().equalsIgnoreCase("Course Updated Successfully"))
				{
					test.log(LogStatus.PASS, " * "+ menu.getData(1, i, 0)+" * "+ "  Coursing Updated Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Coursing Updated Failed");
				}
			
			}
			else
			{
				test.log(LogStatus.INFO, "  "+menu.getData(1, i, 0)+"--"+"  Coursing Imported Failed");
			}
			}
			test.log(LogStatus.INFO, "Coursing Items Import Validation Ends (Newly Created Store");
			Thread.sleep(5000);
		}	

		@Test(priority=8,enabled=false)
		public void verify_ExportedData_Serving_Size_Levels(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			
			File src = new File(Utility.getProperty("Import_Export_Template_Path"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);
						
			int rowCount = sheet1.getLastRowNum();

			int sheetCount=wb.getNumberOfSheets();
			
			
			ExcelDataConfig menu=new ExcelDataConfig(Utility.getProperty("Import_Export_Template_Path"));
			 
		//	driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"course");
			Thread.sleep(5000);
			//Click the Courisng in Men screen on Product/Items
			driver.findElement(By.xpath("//span[.='Serving Size Levels']/../../a")).click();
			
			
			Thread.sleep(5000);
			//Check whether the Serving Size Levels page Loaded or not
			if(driver.findElement(By.xpath("//a[.='Serving Size Levels']")).getText().equalsIgnoreCase("Serving Size Levels"))
			{
				test.log(LogStatus.PASS, "Serving Size Levels page Loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Serving Size Levels page Loaded Failed");
			}
			
			
			Thread.sleep(5000);
			//click the Refresh button
			driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-refresh']")).click();
	
			test.log(LogStatus.INFO, "Serving Size Levels for  Items Import Validation Starts (Newly Created Store");

			
			for(int i=2;i<=3;i++)
			{
			Thread.sleep(4000);
			//Clear the Search box
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//enter the Serving Size Level to seatch
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(menu.getData(3, i, 0));
			//Enter the Search
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(5000);
			//Check whwther the Serving Size Levels Available or not
			if(driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr/td[1]")).getText().equals(menu.getData(3, i, 0)))
			{
				test.log(LogStatus.PASS, " "+ menu.getData(3, i, 0)+" -- "+" Serving Size Level  Imported Successfully");
			
				Thread.sleep(5000);
				//edit the Serving Size Levels
				driver.findElement(By.xpath("//td[contains(.,'"+menu.getData(3, i, 0)+"')]/../td[3]/span[1]/a[1]/i")).click();
				
				Thread.sleep(3000);
				//Check whether the Serving Size Levels Edited page Opened or not
				if(driver.findElement(By.xpath("//span[.='Serving Size Level']")).getText().equalsIgnoreCase("Serving Size Level"))
				{
					test.log(LogStatus.PASS, "Serving Size Level Editing page Opened Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Serving Size Level Editing page Opened Failed");
				}
			
				Thread.sleep(3000);
				//Clear the Description
				driver.findElement(By.xpath("//textarea[@name='description']")).clear();
				//Enter the Priority
				driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(menu.getData(3, i, 0));
			
				Thread.sleep(3000);
				//Click the Update button
				driver.findElement(By.xpath("//span[.='Update']")).click();
				
				
				WebElement update=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
				WebDriverWait wait=new WebDriverWait(driver,50);
				
				//check whether the Serving Size Level Updated or not
				if(wait.until(ExpectedConditions.visibilityOf(update)).getText().equalsIgnoreCase("Serving Size Level Updated Successfully"))
				{
					test.log(LogStatus.PASS, " * "+ menu.getData(3, i, 0)+" * "+ "  Serving Size Level Updated Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Serving Size Level Updated Failed");
				}

			
			}
			else
			{
				test.log(LogStatus.INFO, menu.getData(3, i, 0)+" -- "+"  Serving Size Level Imported Failed");
			}
			}
			test.log(LogStatus.INFO, "Serving Size Levels for Items Import Validation Ends (Newly Created Store");
			Thread.sleep(5000);
		}	

		
		
		@Test(priority=9,enabled=false)
		public void verify_ExportedData_Tax(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			
			File src = new File(Utility.getProperty("Import_Export_Template_Path"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);
						
			int rowCount = sheet1.getLastRowNum();

			int sheetCount=wb.getNumberOfSheets();
			
			
			ExcelDataConfig menu=new ExcelDataConfig(Utility.getProperty("Import_Export_Template_Path"));
			 
		//	driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"course");
			Thread.sleep(5000);
			//Click the Taxes in Men screen on Product/Items
			driver.findElement(By.xpath("//span[.='Taxes']/../../a")).click();
			
			
			Thread.sleep(5000);
			//Check whether the Taxes page Loaded or not
			if(driver.findElement(By.xpath("//a[.='Taxes']")).getText().equalsIgnoreCase("Taxes"))
			{
				test.log(LogStatus.PASS, "Taxes page Loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Taxes page Loaded Failed");
			}
			
			
			Thread.sleep(5000);
			//click the Refresh button
			driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-refresh']")).click();
	
			test.log(LogStatus.INFO, "Taxes Import Validation Starts (Newly Created Store");

			
			for(int i=2;i<=3;i++)
			{
			Thread.sleep(4000);
			//Clear the Search box
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//enter the Taxes to seatch
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(menu.getData(2, i, 0));
			//Enter the Search
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(5000);
			//Check whether the Taxes Available or not
			if(driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr/td[1]")).getText().equals(menu.getData(2, i, 0)))
			{
				test.log(LogStatus.PASS, menu.getData(2, i, 0)+" -- "+" Taxes  Imported Successfully");
			
				Thread.sleep(5000);
				//edit the Tax
				driver.findElement(By.xpath("//td[contains(.,'"+menu.getData(2, i, 0)+"')]/../td[8]/span[1]/a[1]/i")).click();
				
				Thread.sleep(3000);
				//Check whether the Tax Edited page Opened or not
				if(driver.findElement(By.xpath("//span[.='Tax']")).getText().equalsIgnoreCase("Tax"))
				{
					test.log(LogStatus.PASS, "Tax Editing page Opened Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Tax Editing page Opened Failed");
				}
			
				Thread.sleep(3000);
				//Clear the Tax Code
				driver.findElement(By.xpath("//input[@name='taxCode']")).clear();
				//Enter the Tax Code
				driver.findElement(By.xpath("//input[@name='taxCode']")).sendKeys(menu.getData(2, i, 0)+"A01");
			
				Thread.sleep(3000);
				//Click the Update button
				driver.findElement(By.xpath("//span[.='Update']")).click();
				
				
				WebElement update=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
				WebDriverWait wait=new WebDriverWait(driver,50);
				
				//check whether the Tax Updated or not
				if(wait.until(ExpectedConditions.visibilityOf(update)).getText().equalsIgnoreCase("Tax Updated Successfully"))
				{
					test.log(LogStatus.PASS, " * "+ menu.getData(2, i, 0)+" * "+ "  Tax Updated Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Tax Updated Failed");
				}

			
			}
			else
			{
				test.log(LogStatus.INFO, menu.getData(2, i, 0)+" -- "+"  Taxes Imported Failed");
			}
			}
			test.log(LogStatus.INFO, "Taxes Items Import Validation Ends (Newly Created Store");
			Thread.sleep(5000);
		}	
		
		@Test(priority=10,enabled=false)
		public void verify_ExportedData_Categories(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			
			File src = new File(Utility.getProperty("Import_Export_Template_Path"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);
						
			int rowCount = sheet1.getLastRowNum();

			int sheetCount=wb.getNumberOfSheets();
			
			
			ExcelDataConfig menu=new ExcelDataConfig(Utility.getProperty("Import_Export_Template_Path"));
			 
		//	driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"course");
			Thread.sleep(5000);
			//Click the Courisng in Men screen on Product/Items
			driver.findElement(By.xpath("//span[.='Categories']/../../a")).click();
			
			
			Thread.sleep(5000);
			//Check whether the Categories page Loaded or not
			if(driver.findElement(By.xpath("//a[.='Categories']")).getText().equalsIgnoreCase("Categories"))
			{
				test.log(LogStatus.PASS, "Categories page Loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Categories page Loaded Failed");
			}
			
			
			Thread.sleep(5000);
			//click the Refresh button
			driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-refresh']")).click();
	
			test.log(LogStatus.INFO, "Categories Import Validation Starts (Newly Created Store");

			
			for(int i=2;i<=3;i++)
			{
			Thread.sleep(4000);
			//Clear the Search box
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//enter the Categories to seatch
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(menu.getData(6, i, 0));
			//Enter the Search
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(5000);
			//Check whwther the Categories Available or not
			if(driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr/td[2][contains(.,'"+menu.getData(6, i, 0)+"')]")).getText().equals(menu.getData(6, i, 0)))
			{
				test.log(LogStatus.PASS, menu.getData(6, i, 0)+" -- "+"  Categories  Imported Successfully");
			 
				Thread.sleep(5000);
				//Edit the Category
				driver.findElement(By.xpath("//td[contains(.,'"+menu.getData(6, i, 0)+"')]/../td[6]/span[1]/a[1]/i")).click();
			
				//Check whether the Category Edit page opened or not
				if(driver.findElement(By.xpath("//span[.='Category']")).getText().equalsIgnoreCase("Category"))
				{
					test.log(LogStatus.PASS, "Category Editing Opened Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Category Editing page Opened Failed");
				}
				
				Thread.sleep(3000);
				//Clear the Category name
				driver.findElement(By.xpath("//input[@ng-model='category.name']")).clear();
				//Enter the Category Name
				driver.findElement(By.xpath("//input[@ng-model='category.name']")).sendKeys(menu.getData(6, i, 0)+"a1");
				
				//Click the Update button
				driver.findElement(By.xpath("//span[.='Update']")).click();
				
				WebElement upd=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
				WebDriverWait wait=new WebDriverWait(driver,50);
				
				//Check whether the Category Updated or not
				if(wait.until(ExpectedConditions.visibilityOf(upd)).getText().equalsIgnoreCase("Category Updated Successfully"))
				{
					test.log(LogStatus.PASS," * "+menu.getData(6, i, 0)+" * "+ "  Category Updated Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Category Updated Failed");
				}
			
			}
			else
			{
				test.log(LogStatus.INFO, menu.getData(6, i, 0)+" -- "+"  Categories Imported Failed");
			}
			}
			test.log(LogStatus.INFO, "Categories Items Import Validation Ends (Newly Created Store");
			Thread.sleep(5000);
		}	
		
		
				@Test(priority=11,enabled=false)
				public void verify_ExportedData_Sub_Categories(WebDriver driver) throws Exception
				{
					ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					
					File src = new File(Utility.getProperty("Import_Export_Template_Path"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(0);
								
					int rowCount = sheet1.getLastRowNum();

					int sheetCount=wb.getNumberOfSheets();
					
					
					ExcelDataConfig menu=new ExcelDataConfig(Utility.getProperty("Import_Export_Template_Path"));
					 
				//	driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"course");
					Thread.sleep(5000);
					//Click the Courisng in Men screen on Product/Items
					driver.findElement(By.xpath("//span[.='Sub Categories']/../../a")).click();
					
					
					Thread.sleep(5000);
					//Check whether the Sub Categories page Loaded or not
					if(driver.findElement(By.xpath("//a[.='Sub Categories']")).getText().equalsIgnoreCase("Sub Categories"))
					{
						test.log(LogStatus.PASS, "Sub Categories page Loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Sub Categories page Loaded Failed");
					}
					
					
					Thread.sleep(5000);
					//click the Refresh button
					driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-refresh']")).click();
			
					test.log(LogStatus.INFO, "Sub Categories Import Validation Starts (Newly Created Store");

					
					for(int i=2;i<=3;i++)
					{
					Thread.sleep(4000);
					//Clear the Search box
					driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
					//enter the Sub Categories to seatch
					driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(menu.getData(7, i, 0));
					//Enter the Search
					driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.ENTER);
					
					
					Thread.sleep(5000);
					//Check whwther the Sub Categories Available or not
					if(driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr/td[2][contains(.,'"+menu.getData(7, i, 0)+"')]")).getText().equals(menu.getData(7, i, 0)))
					{
						test.log(LogStatus.PASS, menu.getData(7, i, 0)+" -- "+"  Sub Categories  Imported Successfully");
					
					
						Thread.sleep(4000);
						//Edit the Sub Category
						driver.findElement(By.xpath("//td[contains(.,'"+menu.getData(7, i, 0)+"')]/../td[7]/span[1]/a[1]/i")).click();
						
						
						Thread.sleep(4000);
						//Check whether the Sub Category Edit page oprnrd or not
						if(driver.findElement(By.xpath("//span[.='Sub Category']")).getText().equalsIgnoreCase("Sub Category"))
						{
							test.log(LogStatus.PASS, "Sub Category Editing page opened Successfully");
						}
						else
						{
							test.log(LogStatus.FAIL, "Sub Category Edit page opened Failed");
						}
					
						Thread.sleep(3000);
						//Clear the Sub Category name
						driver.findElement(By.xpath("//input[@ng-model='category.name']")).clear();
						//Enter the Sub Category Name
						driver.findElement(By.xpath("//input[@ng-model='category.name']")).sendKeys(menu.getData(7, i, 0)+"a");
						
						Thread.sleep(4000);
						//Click Update button
						driver.findElement(By.xpath("//span[.='Update']")).click();
						
						WebElement update=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
						
						WebDriverWait wait=new WebDriverWait(driver,50);
						
						//Check whether the Sub Category Updated or not
						if(wait.until(ExpectedConditions.visibilityOf(update)).getText().equalsIgnoreCase("Sub Category Updated Successfully"))
						{
							test.log(LogStatus.PASS," * "+menu.getData(7, i, 0)+" * "+ "  Sub Category Updated Successfully");
						}
						else
						{
							test.log(LogStatus.FAIL, "Sub Category Updated Failed");
						}
					}
					else
					{
						test.log(LogStatus.INFO, menu.getData(7, i, 0)+" -- "+"  Sub Categories Imported Failed");
					}
					}
					test.log(LogStatus.INFO, "Sub Categories Items Import Validation Ends (Newly Created Store");
					Thread.sleep(5000);
				}	
				
		
				@Test(priority=12,enabled=false)
				public void verify_ExportedData_Modifiers(WebDriver driver) throws Exception
				{
					ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					
					File src = new File(Utility.getProperty("Import_Export_Template_Path"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(0);
								
					int rowCount = sheet1.getLastRowNum();

					int sheetCount=wb.getNumberOfSheets();
					
					
					ExcelDataConfig menu=new ExcelDataConfig(Utility.getProperty("Import_Export_Template_Path"));
					 
				//	driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"course");
					Thread.sleep(5000);
					//Click the Modifiers in Men screen on Product/Items
					driver.findElement(By.xpath("//span[.='Modifiers']/../../a")).click();
					
					
					Thread.sleep(5000);
					//Check whether the Modifiers page Loaded or not
					if(driver.findElement(By.xpath("//a[.='Modifiers']")).getText().equalsIgnoreCase("Modifiers"))
					{
						test.log(LogStatus.PASS, "Modifiers page Loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Modifiers page Loaded Failed");
					}
					
					
					Thread.sleep(5000);
					//click the Refresh button
					driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-refresh']")).click();
			
					test.log(LogStatus.INFO, "Modifiers for Menu Items Import Validation Starts (Newly Created Store");

					
					for(int i=2;i<=3;i++)
					{
					Thread.sleep(4000);
					//Clear the Search box
					driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
					//enter the Modifiers to seatch
					driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(menu.getData(8, i, 0));
					//Enter the Search
					driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.ENTER);
					
					
					Thread.sleep(5000);
					//Check whwther the Modifiers Available or not
					if(driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr/td[1]")).getText().equals(menu.getData(8, i, 0)))
					{
						test.log(LogStatus.PASS, menu.getData(8, i, 0)+" -- "+"  Modifiers  Imported Successfully");
					
						Thread.sleep(5000);
						//Edit the Modifier
						driver.findElement(By.xpath("//td[contains(.,'"+menu.getData(8, i, 0)+"')]/../td[3]/span[1]/a[1]/i")).click();

						Thread.sleep(4000);
						//Check whether the Modifier Editing page opened or not
						if(driver.findElement(By.xpath("//span[.='MODIFIER']")).getText().equalsIgnoreCase("MODIFIER"))
						{
							test.log(LogStatus.PASS, "Modifier Editing page Opened Successfully");
						}
						else
						{
							test.log(LogStatus.FAIL, "Modifier Editing page opened Failed");
						}
						
						Thread.sleep(3000);
						//Clear Max No of Times in Modifiers
						driver.findElement(By.xpath("//input[@name='maxNoOfTimes']")).clear();
						//Enter the Maximum No of Times 
						driver.findElement(By.xpath("//input[@name='maxNoOfTimes']")).sendKeys("3");
						
						Thread.sleep(4000);
						//click the Update button
						driver.findElement(By.xpath("//span[.='Update']")).click();
						
						WebElement update=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
						
						WebDriverWait wait=new WebDriverWait(driver,50);
						
						//Check whether the Modifiers Updated or not
						if(wait.until(ExpectedConditions.visibilityOf(update)).getText().equalsIgnoreCase("Modifier Updated Successfully"))
						{
							test.log(LogStatus.PASS, " * "+menu.getData(8, i, 0)+" * "+ "Modifier Updated Successfully");
						}
						else
						{
							test.log(LogStatus.FAIL, "Modifier Updated Failed");
						}
						
					}
					else
					{
						test.log(LogStatus.INFO, menu.getData(8, i, 0)+" -- "+"  Modifiers Imported Failed");
					}
					}
					test.log(LogStatus.INFO, "Modifiers for Menu Items Import Validation Ends (Newly Created Store");

					Thread.sleep(5000);
				}	
				
				
				@Test(priority=13,enabled=false)
				public void verify_ExportedData_Modifier_Groups(WebDriver driver) throws Exception
				{
					ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					
					File src = new File(Utility.getProperty("Import_Export_Template_Path"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(0);
								
					int rowCount = sheet1.getLastRowNum();

					int sheetCount=wb.getNumberOfSheets();
					
					
					ExcelDataConfig menu=new ExcelDataConfig(Utility.getProperty("Import_Export_Template_Path"));
					 
				//	driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"course");
					Thread.sleep(5000);
					//Click the Modifier Groups in Men screen on Product/Items
					driver.findElement(By.xpath("//span[.='Modifier Groups']/../../a")).click();
					
					
					Thread.sleep(5000);
					//Check whether the Modifier Groups page Loaded or not
					if(driver.findElement(By.xpath("//a[.='Modifier Groups']")).getText().equalsIgnoreCase("Modifier Groups"))
					{
						test.log(LogStatus.PASS, "Modifier Groups page Loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Modifier Groups page Loaded Failed");
					}
					
					
					Thread.sleep(5000);
					//click the Refresh button
					driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-refresh']")).click();
			
					test.log(LogStatus.INFO, "Modifier Groups for Menu Items Import Validation Starts (Newly Created Store");

					
					for(int i=2;i<=3;i++)
					{
					Thread.sleep(4000);
					//Clear the Search box
					driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
					//enter the Modifier Groups to seatch
					driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(menu.getData(9, i, 0));
					//Enter the Search
					driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.ENTER);
					
					
					Thread.sleep(5000);
					//Check whether the Modifier Groups Available or not
					if(driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr/td[1]")).getText().equals(menu.getData(9, i, 0)))
					{
						test.log(LogStatus.PASS, menu.getData(9, i, 0)+" -- "+"  Modifier Groups  Imported Successfully");
						
						Thread.sleep(5000);
						//Edit the Modifier Group
						driver.findElement(By.xpath("//td[contains(.,'"+menu.getData(9, i, 0)+"')]/../td[5]/span[1]/a[1]/i")).click();
						
						Thread.sleep(4000);
						//Check whether the Modifier Group page opened or not
						if(driver.findElement(By.xpath("//span[.='Modifier Group']")).getText().equalsIgnoreCase("Modifier Group"))
						{
							test.log(LogStatus.PASS, "Modifier Group Editing page Opened Successfully");
						}
						else
						{
							test.log(LogStatus.FAIL, "Modifier Group Editing page Opened Failed");
						}
						
						Thread.sleep(3000);
						//Clear the priority of modifier group
						driver.findElement(By.xpath("//input[@name='priority']")).clear();
						//Enter the priority
						driver.findElement(By.xpath("//input[@name='priority']")).sendKeys("2");
						
						Thread.sleep(4000);
						//Click the Update button
						driver.findElement(By.xpath("//span[.='Update']")).click();
						
						WebElement update=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
						
						WebDriverWait wait=new WebDriverWait(driver,50);
						
						if(wait.until(ExpectedConditions.visibilityOf(update)).getText().equalsIgnoreCase("Modifier Group Updated Successfully"))
						{
							test.log(LogStatus.PASS, " * "+menu.getData(9, i, 0)+" * "+"  Modifier Group Updated Successfully");
						}
						else
						{
							test.log(LogStatus.FAIL, "Modifier Group Updated Successfully");
						}
						
						
					}
					else
					{
						test.log(LogStatus.INFO, menu.getData(9, i, 0)+" -- "+"  Modifier Groups Imported Failed");
					}
					}
					test.log(LogStatus.INFO, "Modifier Groups for Menu Items Import Validation Ends (Newly Created Store");

					Thread.sleep(5000);
				}	
				
}
		
		
		


