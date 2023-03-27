package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
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

public class Inventory_Purchase_Template {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Purchase_Template");

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
			Inventory_Purchase_Purchase_Template_openPAGE(driver);
			Inventory_Purchase_Purchase_Template_add_new(driver);
			Inventory_Purchase_Purchase_Template_edit(driver);
			Inventory_Purchase_Purchase_Template_verify_Purchase_Order_Button(driver);
			Inventory_Purchase_Purchase_Template_delete(driver);
			Inventory_Purchase_Purchase_Template_verifyActive_InActiveButton_Sub_Recipe(driver);
			Thread.sleep(1500);
		}
			
			@Test(enabled=false,priority=2)
			public void Inventory_Purchase_Purchase_Template_openPAGE(WebDriver driver) throws Exception
			{
				
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(15000);
/*				//Click the Inventory option
				driver.findElement(By.xpath("//span[.='Inventory']")).click();
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[contains(.,'Purchases')]"));
				//Scroll the page till the Inventory Menu Items option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Purchases Option		
				driver.findElement(By.xpath("//span[contains(.,'Purchases')]")).click();
				Thread.sleep(5000);
				
		        //Click the Purchases Order Option		
				driver.findElement(By.xpath("//span[contains(.,'Purchase Templates')]")).click();
*/				
				//Get the URl
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"purchaseTemplates");

				Thread.sleep(5000);
				try
				{
				//Check Storage Locations page opened or not
				if(driver.findElement(By.xpath("//a[.='Purchase Templates']")).getText().equalsIgnoreCase("Purchase templates"))
				{
					test.log(LogStatus.PASS, "Purchase order template page Successfully loaded");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order template page loaded Failed");
				
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
			
			@Test(enabled=false,priority=4)
			public void Inventory_Purchase_Purchase_Template_add_new(WebDriver driver) throws Exception
			{
				    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				Thread.sleep(5000);
				//Click the Purchase Order Template
				driver.findElement(By.id("purchaseTemplates")).click();
				
				Thread.sleep(4000);
				//Check whether the new form is loaded or not
				if(driver.findElement(By.xpath("//span[contains(.,'New Purchase Template')]")).isDisplayed())
				{
					test.log(LogStatus.PASS, "New Purchase Order Template form loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "New Purchase Order Template form loaded Fail");
				}
				Thread.sleep(2000);
				
				//Clear the name field
				driver.findElement(By.id(excel.getData(3, 254, 3))).clear();
				//Enter the Required Name
				driver.findElement(By.id(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Inventory_Purchase_Order_Template_Name"));
				
				Thread.sleep(15000);
				//Click the Vendor Name
				driver.findElement(By.xpath(excel.getData(3, 1250, 1))).click();
				//Enter the Required Vendor
				driver.findElement(By.xpath(excel.getData(3, 1251, 1))).sendKeys("Sysco Food");
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(3, 1251, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Add Inventory Item
				driver.findElement(By.xpath(excel.getData(3, 1252, 1))).click();
				
				//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(15000);
				//Click the Inventory Item Option
				driver.findElement(By.xpath(excel.getData(3, 1253, 1))).click();
				//Enter the Required Inventory Option
				driver.findElement(By.xpath(excel.getData(3, 1254, 1))).click();
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(3, 1254, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(1000);
				//Clear the Quantity
				driver.findElement(By.xpath(excel.getData(3, 1255, 1))).clear();
				//Enter the Required Quantity
				driver.findElement(By.xpath(excel.getData(3, 1255, 1))).sendKeys("2");
				
				Thread.sleep(5000);
				//Add Inventory Item
				driver.findElement(By.xpath(excel.getData(3, 1252, 1))).click();
				
				//Click the Close button
				driver.findElement(By.xpath(excel.getData(3, 1256, 1))).click();
				
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(2000);
				//Click the Place Order Option
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				Thread.sleep(2500);
				//Check whether the order is placed or not
				if(driver.findElement(By.xpath(excel.getData(3, 1184, 1))).getText().equalsIgnoreCase("Purchase template Saved Successfully!."))
				{
					test.log(LogStatus.PASS, "Purchase order template saved Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order template saved Failed");
				}
				Thread.sleep(5000);wb.close();
		
			}
			
			@Test(enabled=false,priority=5)
			public void Inventory_Purchase_Purchase_Template_edit(WebDriver driver) throws Exception
			{
				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Clear the Search Field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				Thread.sleep(1000);
				//Enter the required Keyword
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Inventory_Purchase_Order_Template_Name"));
				Thread.sleep(1000);
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);
				
				
				Thread.sleep(3000); 
				//Click the Edit button
				driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
				
				
				Thread.sleep(6000);
				//Clear the Quantity
				driver.findElement(By.xpath(excel.getData(3, 1255, 1))).clear();
				//Enter the Required Quantity
				driver.findElement(By.xpath(excel.getData(3, 1255, 1))).sendKeys("3");
		
				
				Thread.sleep(2000);
				//Add Inventory Item
			//	driver.findElement(By.xpath(excel.getData(3, 1252, 1))).click();
				
				Thread.sleep(7500);
				//Click the Inventory Item Option
				driver.findElement(By.xpath(excel.getData(3, 1253, 1))).click();
				//Enter the Required Inventory Option
				driver.findElement(By.xpath(excel.getData(3, 1254, 1))).sendKeys(Keys.ARROW_DOWN);
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(3, 1254, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(1000);
				//Clear the Quantity
				driver.findElement(By.xpath(excel.getData(3, 1255, 1))).clear();
				//Enter the Required Quantity
				driver.findElement(By.xpath(excel.getData(3, 1255, 1))).sendKeys("2");
				
				Thread.sleep(3000);
				//Click the Update button
				driver.findElement(By.xpath(excel.getData(3, 1475, 1))).click();
				
				Thread.sleep(2500);
				//Check whether the order is placed or not
				if(driver.findElement(By.xpath(excel.getData(3, 1184, 1))).getText().equalsIgnoreCase("Purchase template updated successfully!."))
				{
					test.log(LogStatus.PASS, "Purchase order template updated Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order template updated Failed");
				}
				Thread.sleep(5000);wb.close();
				
			}
			
			@Test(enabled=false,priority=7)
			public void Inventory_Purchase_Purchase_Template_verify_Purchase_Order_Button(WebDriver driver) throws Exception
			{
                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
			/*	//Clear the Search Field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				//Enter the required Keyword
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Inventory_Purchase_Order_Template_Name"));
				Thread.sleep(1000);
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);
				
				Thread.sleep(5000);
				//Click the purchase order button
				driver.findElement(By.xpath(excel.getData(3, 1257, 1))).click();
				
				Thread.sleep(8000);
				//Clear the Expecting Date
				driver.findElement(By.name(excel.getData(3, 1258, 3))).clear();
				//Enter the Required Date
				driver.findElement(By.name(excel.getData(3, 1258, 3))).sendKeys("21-Dec-2020");
				
				try
				{
				Thread.sleep(5000);
				//Select the required placed order via option
				WebElement drop=driver.findElement(By.xpath("//select[@name='placedVia']"));
				Select placeOrederVia = new Select(drop);
				placeOrederVia.selectByVisibleText("Email");
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
				driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
				driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
				Thread.sleep(5000);
				//Click the Place Order Option
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				Thread.sleep(2500);
				//Check whether the order is placed or not
				if(driver.findElement(By.xpath(excel.getData(3, 1184, 1))).getText().equalsIgnoreCase("Purchase order saved successfully!."))
				{
					test.log(LogStatus.PASS, "Purchase order saved Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order saved Failed");
				}
				Thread.sleep(5000);*/
				wb.close();
		        //Click the Purchases Order Template Option		
				//driver.findElement(By.xpath(excel.getData(3, 1159, 1))).click();wb.close();
			}
			
			@Test(enabled=false,priority=6) 
			public void Inventory_Purchase_Purchase_Template_delete(WebDriver driver) throws Exception
			{
				 
                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Clear the Search Field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				//Enter the required Keyword
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Inventory_Purchase_Order_Template_Name"));
				Thread.sleep(1000);
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);
				
				Thread.sleep(4000);
				//Click the delete purchase order button
				driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).click();
				
				Thread.sleep(2000);
				//Click the Yes button
				driver.findElement(By.xpath(excel.getData(3, 545, 1))).click();
				
				Thread.sleep(2500);
				//Check whether the order is placed or not
				if(driver.findElement(By.xpath(excel.getData(3, 1184, 1))).getText().equalsIgnoreCase("purchase template InActivated successfully"))
				{
					test.log(LogStatus.PASS, "Purchase order deleted Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order deleted Failed");
				}
		
				Thread.sleep(5000);
				//Click the Active button
				driver.findElement(By.xpath(excel.getData(3, 144, 1))).click();
				
				
				//Clear the Search Field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				//Enter the required Keyword
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Inventory_Purchase_Order_Template_Name"));
				Thread.sleep(1000);
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);
				
				
				Thread.sleep(4000);
				//Click the active symbol
				driver.findElement(By.xpath(excel.getData(3, 1262, 1))).click();
				
				Thread.sleep(2000);
				//Click the Yes button
				driver.findElement(By.xpath(excel.getData(3, 545, 1))).click();
				
				Thread.sleep(3000);
				//Check whether the order is placed or not
				if(driver.findElement(By.xpath(excel.getData(3, 1184, 1))).getText().equalsIgnoreCase("purchase template Activated successfully"))
				{
					test.log(LogStatus.PASS, "Purchase order activated Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order activated Failed");
				}
				Thread.sleep(5000);wb.close();
			}
			
			@Test(enabled=false,priority=8)
			public void Inventory_Purchase_Purchase_Template_verifyActive_InActiveButton_Sub_Recipe(WebDriver driver) throws Exception
			{
                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(8000);
				//Clear the search field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				
				Thread.sleep(5000);
				//Click Active or In Active Button
				driver.findElement(By.xpath(excel.getData(3, 144, 1))).click();
		
				Thread.sleep(5000);
				//Check the Inactive page is loaded or not
				if(driver.findElement(By.xpath(excel.getData(3, 1262, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "We are in InActive Page");
					Thread.sleep(10000);
					//Click Active or In Active Button
					driver.findElement(By.xpath(excel.getData(3, 144, 1))).click();
		
				}
				else
				{
					test.log(LogStatus.FAIL, "We are in Active Page but User Click the Inactive Page");
				}
				Thread.sleep(5000);wb.close();
			}

}
