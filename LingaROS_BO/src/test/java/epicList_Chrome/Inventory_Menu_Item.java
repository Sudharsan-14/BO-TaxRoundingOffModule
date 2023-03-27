package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import newReadExcelFile_New.ExcelDataConfig;

public class Inventory_Menu_Item {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Menu_Item");

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
			Inventory_Menu_Items_OpenPage(driver);
			Inventory_Menu_Items_refresh_page(driver);
			Inventory_Menu_Items_verify_UnLinked_TO_Linked(driver);
			Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Inv(driver);
			Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Inv_Sub(driver);
			Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Inv_Manual(driver);
			Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Sub_Manual(driver);
			Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Sub_Sub(driver);
			Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Manual_Manual(driver);
			Inventory_Menu_Items_verify_Linked_Type_of_Menu_Items_TO_UnLinked_Type_of_Menu_Items(driver);
			Inventory_Menu_Items_verifyActive_InActiveButton_Sub_Recipe(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=11)
		public void Inventory_Menu_Items_OpenPage(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(5000);

		//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"inventory_menuItems");
	
			Thread.sleep(5000);
			try
			{
			//Check Storage Locations page opened or not
			if(driver.findElement(By.xpath(excel.getData(2, 172, 1))).getText().equalsIgnoreCase("Inventory Menu Items"))
			{
				test.log(LogStatus.PASS, "Inventory Menu Items page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Inventory Menu Items page loaded Failed");
			
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
		
		@Test(enabled=false,priority=12)
		public void Inventory_Menu_Items_refresh_page(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(2, 173, 1))).click();
			Thread.sleep(5000);
			
			//Check Inventory Menu Items page opened or not
			if(driver.findElement(By.xpath(excel.getData(2, 174, 1))).getText().equalsIgnoreCase("Inventory Menu Items"))
			{
				test.log(LogStatus.PASS, "Inventory Menu Items page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inventory Menu Items page refreshed Failed");
			}
			Thread.sleep(5000);
			wb.close();
		}
	
		@Test(enabled=false,priority=14)
		public void Inventory_Menu_Items_verify_UnLinked_TO_Linked(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(5000);

			//Scroll the web page
		    for(int i=1; i <= 20; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		    } 
		    
			Thread.sleep(3000);
			//Click the filter button
			driver.findElement(By.xpath(excel.getData(2, 175, 1))).click();
			
			Thread.sleep(1500);
			//Choose the Option as linked Items
			driver.findElement(By.xpath(excel.getData(2, 176, 1))).click();
			
			
			Thread.sleep(2000);
			//Click the Link Option
			driver.findElement(By.xpath(excel.getData(2, 177, 1))).click();
			
			Thread.sleep(4500);
			//Click the Right arrow
			driver.findElement(By.xpath(excel.getData(2, 178, 1))).click();
			
	/*		Thread.sleep(2000);
			//Add Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();
			
			Thread.sleep(5000);
			//Click the Category Option
			driver.findElement(By.xpath(excel.getData(2, 482, 1))).click();
			//Press the Enter button
			Thread.sleep(1000);driver.findElement(By.xpath(excel.getData(2, 483, 1))).click();
			Thread.sleep(1000);driver.findElement(By.xpath(excel.getData(2, 483, 1))).sendKeys(Keys.ENTER);
	
			Thread.sleep(5000);
			//Click the Item
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[3]/td[4]/div/a")).click();
			//Press the Enter button
			Thread.sleep(5000);
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[3]/td[4]/div/div/div/input")).click();
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[3]/td[4]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Recipe Unit
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[3]/td[5]/div/a")).click();
			//Press the Enter button
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[3]/td[5]/div/div/div/input")).sendKeys(Keys.ENTER);
	
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 453, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 453, 1))).sendKeys("1");
			*/
			Thread.sleep(5000);
			//Add Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 62, 1))).click();
	
			Thread.sleep(3000);
			//Click the Item
			driver.findElement(By.xpath(excel.getData(2, 449, 1))).click();
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 450, 1))).sendKeys(Keys.ARROW_DOWN);
			//Enter item
			driver.findElement(By.xpath(excel.getData(2, 450, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 451, 1))).click();
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 452, 1))).sendKeys(Keys.ARROW_DOWN);
			//Enter Recipe 
			driver.findElement(By.xpath(excel.getData(2, 452, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 453, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 453, 1))).sendKeys("3");
	
			Thread.sleep(2000);
			//Add Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 68, 1))).click();
	
			Thread.sleep(1000);
			//clear the Required Item
			driver.findElement(By.xpath(excel.getData(2, 454, 1))).clear();
			//Enter the required Item
			driver.findElement(By.xpath(excel.getData(2, 454, 1))).sendKeys("Test");
			
			Thread.sleep(1000);  
			//clear the Price per Unit Option
			driver.findElement(By.xpath(excel.getData(2, 455, 1))).clear();
			//Enter the Price per Unit
			driver.findElement(By.xpath(excel.getData(2, 455, 1))).sendKeys("500");
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 456, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 456, 1))).sendKeys("2");
	
	/*		Thread.sleep(2000);
			//Add Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();
			Thread.sleep(1500);
			//Click the Close button for Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 457, 1))).click();
			Thread.sleep(1500);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
			
			Thread.sleep(2000);
			//Add Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 62, 1))).click();
			Thread.sleep(1500);
			//Click the Close button for Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 457, 1))).click();
			Thread.sleep(1500);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
			
			Thread.sleep(2000);
			//Add Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 68, 1))).click();
			Thread.sleep(1500);
			//Click the Close button for Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 457, 1))).click();
			Thread.sleep(1500);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
*/
			
			Thread.sleep(4000);
			//Click the Update and Go Button
			driver.findElement(By.xpath(excel.getData(2, 185, 1))).click();
			
			Thread.sleep(2500);
			
			//Check whether the inventory item updated or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Menu item Updated"))
			{
				test.log(LogStatus.PASS, "Menu item Updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Menu item Updated Failed");
			}
	
			Thread.sleep(10000);
			
			//Click the filter button
			driver.findElement(By.xpath(excel.getData(2, 175, 1))).click();
			
			Thread.sleep(1500);
			//Choose the Option as linked Items
			driver.findElement(By.xpath(excel.getData(2, 458, 1))).click();
			try
			{
			Thread.sleep(1000);
			//Check whether the Linked Items are displayed or not
			if(driver.findElement(By.xpath(excel.getData(2, 459, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Unlinked Inventory Menu Item is Changed to Linked Menu Item was Successfully");
			}
			}
			catch(Exception e)
			{
				test.log(LogStatus.FAIL, "Unlinked Inventory Menu Item is Changed to Linked Menu Item was Fail");
			}
			wb.close();
			Thread.sleep(5000);
			
		}
		
		@Test(enabled=false,priority=15)
		public void Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Inv(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(5000);

			Thread.sleep(8000);
			//Click the filter button
			driver.findElement(By.xpath(excel.getData(2, 175, 1))).click();
			
			Thread.sleep(1500);
			//Choose the Option as linked Items
			driver.findElement(By.xpath(excel.getData(2, 458, 1))).click();
			
			//Click the Link Option
			driver.findElement(By.xpath(excel.getData(2, 177, 1))).click();
			
			Thread.sleep(4500);
			//Click the Right arrow
			driver.findElement(By.xpath(excel.getData(2, 178, 1))).click();
			
			Thread.sleep(1500);
			//Click the Close button for first
			driver.findElement(By.xpath(excel.getData(2, 460, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
			
			//Click the Close button for first
			driver.findElement(By.xpath(excel.getData(2, 460, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
			
	/*		//Click the Close button for first
			driver.findElement(By.xpath(excel.getData(2, 460, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
			
			Thread.sleep(2000);
			//Add Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();
			
			Thread.sleep(1000);
			//Click the Category Option
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[2]/div/a")).click();
			//Press the Enter button
			Thread.sleep(1000);driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[2]/div/div/div/input")).sendKeys(Keys.ENTER);
	
			Thread.sleep(1000);
			//Click the Item
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[4]/div/a")).click();
			//Press the Enter button
			Thread.sleep(2000);driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[4]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Recipe Unit
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[5]/div/a")).click();
			//Press the Enter button
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[5]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[7]/input")).clear();
			//Enter the required quantity
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[7]/input")).sendKeys("1");
	
			Thread.sleep(2000);
			//Add Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();
			
			Thread.sleep(1000);
			//Click the Category Option
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[2]/div/a")).click();
			//Press the Enter button
			Thread.sleep(1000);driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[2]/div/div/div/input")).sendKeys(Keys.ENTER);
	
			Thread.sleep(1000);
			//Click the Item
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[4]/div/a")).click();
			//Press the Enter button
			Thread.sleep(2000);driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[4]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Recipe Unit
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[5]/div/a")).click();
			//Press the Enter button
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[5]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[7]/input")).clear();
			//Enter the required quantity
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[7]/input")).sendKeys("1");
	*/
			Thread.sleep(3000);
			//Click the Update and Go Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			
			Thread.sleep(2500);
			
			//Check whether the inventory item updated or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Menu item Updated"))
			{
				test.log(LogStatus.PASS, "Menu item Updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Menu item Updated Failed");
			}
	
			Thread.sleep(5000);
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(2, 120, 1))).click();
			wb.close();
			Thread.sleep(2000);
		}
		
		@Test(enabled=false,priority=16)
		public void Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Inv_Sub(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(5000);

			Thread.sleep(8000);
			//Click the filter button
			driver.findElement(By.xpath(excel.getData(2, 175, 1))).click();
			
			Thread.sleep(1500);
			//Choose the Option as linked Items
			driver.findElement(By.xpath(excel.getData(2, 458, 1))).click();
			
			//Click the Link Option
			driver.findElement(By.xpath(excel.getData(2, 177, 1))).click();
			
			Thread.sleep(4500);
			//Click the Right arrow
			driver.findElement(By.xpath(excel.getData(2, 178, 1))).click();
			
			Thread.sleep(1500);
			//Click the Close button for first
			driver.findElement(By.xpath(excel.getData(2, 460, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
			
	/*		//Click the Close button for first
			driver.findElement(By.xpath(excel.getData(2, 460, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
				
			Thread.sleep(2000);
			//Add Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();
			
			Thread.sleep(1000);
			//Click the Category Option
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[2]/div/a")).click();
			//Press the Enter button
			Thread.sleep(1000);driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[2]/div/div/div/input")).sendKeys(Keys.ENTER);
	
			Thread.sleep(1000);
			//Click the Item
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[4]/div/a")).click();
			//Press the Enter button
			Thread.sleep(2000);driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[4]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Recipe Unit
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[5]/div/a")).click();
			//Press the Enter button
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[5]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[7]/input")).clear();
			//Enter the required quantity
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[7]/input")).sendKeys("1");
	
			Thread.sleep(2000);
			//Add Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 62, 1))).click();
	
			Thread.sleep(1000);
			//Click the Item
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[4]/div/a")).click();
			//Press the Enter button
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[4]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Recipe Unit
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[5]/div/a")).click();
			//Press the Enter button
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[5]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[7]/input")).clear();
			//Enter the required quantity
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[7]/input")).sendKeys("3");
	*/
			Thread.sleep(3000);
			//Click the Update and Go Button
			driver.findElement(By.xpath(excel.getData(2, 185, 1))).click();
			
			Thread.sleep(2500);
			
			//Check whether the inventory item updated or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Menu item Updated"))
			{
				test.log(LogStatus.PASS, "Menu item Updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Menu item Updated Failed");
			}
			wb.close();
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=17)
		public void Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Inv_Manual(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  

			Thread.sleep(8000);
			//Click the filter button
			driver.findElement(By.xpath(excel.getData(2, 175, 1))).click();
			
			Thread.sleep(1500);
			//Choose the Option as linked Items
			driver.findElement(By.xpath(excel.getData(2, 458, 1))).click();
			
			//Click the Link Option
			driver.findElement(By.xpath(excel.getData(2, 177, 1))).click();
			
			Thread.sleep(4500);
			//Click the Right arrow
			driver.findElement(By.xpath(excel.getData(2, 178, 1))).click();
			
			Thread.sleep(1500);
			//Click the Close button for first
			driver.findElement(By.xpath(excel.getData(2, 460, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
			
		/*	//Click the Close button for first
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[9]/a/i")).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
				
			Thread.sleep(2000);
			//Add Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();
			
			Thread.sleep(1000);
			//Click the Category Option
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[2]/div/a")).click();
			//Press the Enter button
			Thread.sleep(1000);driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[2]/div/div/div/input")).sendKeys(Keys.ENTER);
	
			Thread.sleep(1000);
			//Click the Item
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[4]/div/a")).click();
			//Press the Enter button
			Thread.sleep(2000);driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[4]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 474, 1))).click();
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 475, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 464, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[7]/input")).sendKeys("1");
	*/
			Thread.sleep(2000);
			//Add Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 68, 1))).click();
	
			Thread.sleep(1000);
			//clear the Required Item
			driver.findElement(By.xpath(excel.getData(2, 462, 1))).clear();
			//Enter the required Item
			driver.findElement(By.xpath(excel.getData(2, 462, 1))).sendKeys("Test");
			
			Thread.sleep(1000);
			//clear the Price per Unit Option
			driver.findElement(By.xpath(excel.getData(2, 463, 1))).clear();
			//Enter the Price per Unit
			driver.findElement(By.xpath(excel.getData(2, 463, 1))).sendKeys("500");
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 464, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 464, 1))).sendKeys("2");
		
			Thread.sleep(3000);
			//Click the Update and Go Button
			driver.findElement(By.xpath(excel.getData(2, 185, 1))).click();
			
			Thread.sleep(2500);
			
			//Check whether the inventory item updated or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Menu item Updated"))
			{
				test.log(LogStatus.PASS, "Menu item Updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Menu item Updated Failed");
			}
			wb.close();
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=18)
		public void Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Sub_Manual(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  

			Thread.sleep(8000);
			//Click the filter button
			driver.findElement(By.xpath(excel.getData(2, 175, 1))).click();
			
			Thread.sleep(1500);
			//Choose the Option as linked Items
			driver.findElement(By.xpath(excel.getData(2, 458, 1))).click();
			
			//Click the Link Option
			driver.findElement(By.xpath(excel.getData(2, 177, 1))).click();
			
			Thread.sleep(4500);
			//Click the Right arrow
			driver.findElement(By.xpath(excel.getData(2, 178, 1))).click();
			
			Thread.sleep(1500);
			//Click the Close button for first
			driver.findElement(By.xpath(excel.getData(2, 460, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
/*			
			//Click the Close button for first
			driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[7]/a/i")).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
				*/
			Thread.sleep(2000);
			//Add Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 62, 1))).click();
	
			Thread.sleep(1000);
			//Click the Item
			driver.findElement(By.xpath(excel.getData(2, 474, 1))).click();
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 475, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 476, 1))).click();
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 477, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 464, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 464, 1))).sendKeys("3");
	
			Thread.sleep(2000);
			//Add Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 68, 1))).click();
	
			Thread.sleep(1000);
			//clear the Required Item
			driver.findElement(By.xpath(excel.getData(2, 465, 1))).clear();
			//Enter the required Item
			driver.findElement(By.xpath(excel.getData(2, 465, 1))).sendKeys("Test");
			
			Thread.sleep(1000);
			//clear the Price per Unit Option
			driver.findElement(By.xpath(excel.getData(2, 466, 1))).clear();
			//Enter the Price per Unit
			driver.findElement(By.xpath(excel.getData(2, 466, 1))).sendKeys("500");
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 467, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 467, 1))).sendKeys("2");
	
			Thread.sleep(3000);
			//Click the Update and Go Button
			driver.findElement(By.xpath(excel.getData(2, 185, 1))).click();
			
			Thread.sleep(2500);
			
			//Check whether the inventory item updated or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Menu item Updated"))
			{
				test.log(LogStatus.PASS, "Menu item Updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Menu item Updated Failed");
			}
			wb.close();
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=19)
		public void Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Sub_Sub(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  

			Thread.sleep(8000);
			//Click the filter button
			driver.findElement(By.xpath(excel.getData(2, 175, 1))).click();
			
			Thread.sleep(1500);
			//Choose the Option as linked Items
			driver.findElement(By.xpath(excel.getData(2, 458, 1))).click();
			
			//Click the Link Option
			driver.findElement(By.xpath(excel.getData(2, 177, 1))).click();
			
			Thread.sleep(4500);
			//Click the Right arrow
			driver.findElement(By.xpath(excel.getData(2, 178, 1))).click();
			
			Thread.sleep(1500);
			//Click the Close button for first
			driver.findElement(By.xpath(excel.getData(2, 460, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
			
			//Click the Close button for first
			driver.findElement(By.xpath(excel.getData(2, 460, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
				
			Thread.sleep(2000);
			//Add Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 62, 1))).click();
	
			Thread.sleep(1000);
			//Click the Item
			driver.findElement(By.xpath(excel.getData(2, 474, 1))).click();
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 475, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 476, 1))).click();
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 477, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 464, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 464, 1))).sendKeys("3");
	
			Thread.sleep(2000);
			//Add Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 62, 1))).click();
	
			Thread.sleep(1000);
			//Click the Item
			driver.findElement(By.xpath(excel.getData(2, 470, 1))).click();
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 471, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 472, 1))).click();
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 473, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 467, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 467, 1))).sendKeys("3");
	
			Thread.sleep(3000);
			//Click the Update and Go Button
			driver.findElement(By.xpath(excel.getData(2, 185, 1))).click();
			
			Thread.sleep(2500);
			
			//Check whether the inventory item updated or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Menu item Updated"))
			{
				test.log(LogStatus.PASS, "Menu item Updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Menu item Updated Failed");
			}
			wb.close();
	
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=20)
		public void Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Manual_Manual(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  

			Thread.sleep(8000);
			//Click the filter button
			driver.findElement(By.xpath(excel.getData(2, 175, 1))).click();
			
			Thread.sleep(1500);
			//Choose the Option as linked Items
			driver.findElement(By.xpath(excel.getData(2, 458, 1))).click();
			
			//Click the Link Option
			driver.findElement(By.xpath(excel.getData(2, 177, 1))).click();
			
			Thread.sleep(4500);
			//Click the Right arrow
			driver.findElement(By.xpath(excel.getData(2, 178, 1))).click();
			
			Thread.sleep(1500);
			//Click the Close button for first
			driver.findElement(By.xpath(excel.getData(2, 460, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
			
			//Click the Close button for first
			driver.findElement(By.xpath(excel.getData(2, 460, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
				
			Thread.sleep(2000);
			//Add Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 68, 1))).click();
	
			Thread.sleep(1000);
			//clear the Required Item
			driver.findElement(By.xpath(excel.getData(2, 462, 1))).clear();
			//Enter the required Item
			driver.findElement(By.xpath(excel.getData(2, 462, 1))).sendKeys("Test");
			
			Thread.sleep(1000);
			//clear the Price per Unit Option
			driver.findElement(By.xpath(excel.getData(2, 463, 1))).clear();
			//Enter the Price per Unit
			driver.findElement(By.xpath(excel.getData(2, 463, 1))).sendKeys("500");
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 464, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 464, 1))).sendKeys("2");
	
			Thread.sleep(2000);
			//Add Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 68, 1))).click();
	
			Thread.sleep(1000);
			//clear the Required Item
			driver.findElement(By.xpath(excel.getData(2, 465, 1))).clear();
			//Enter the required Item
			driver.findElement(By.xpath(excel.getData(2, 465, 1))).sendKeys("Test");
			
			Thread.sleep(1000);
			//clear the Price per Unit Option
			driver.findElement(By.xpath(excel.getData(2, 466, 1))).clear();
			//Enter the Price per Unit
			driver.findElement(By.xpath(excel.getData(2, 466, 1))).sendKeys("500");
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 467, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 467, 1))).sendKeys("2");
	
			Thread.sleep(3000);
			//Click the Update and Go Button
			driver.findElement(By.xpath(excel.getData(2, 185, 1))).click();
			
			Thread.sleep(2500);
			
			//Check whether the inventory item updated or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Menu item Updated"))
			{
				test.log(LogStatus.PASS, "Menu item Updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Menu item Updated Failed");
			}
			wb.close();
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=21)
		public void Inventory_Menu_Items_verify_Linked_Type_of_Menu_Items_TO_UnLinked_Type_of_Menu_Items(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  

			Thread.sleep(8000);
			//Click the filter button
			driver.findElement(By.xpath(excel.getData(2, 175, 1))).click();
			
			Thread.sleep(1500);
			//Choose the Option as linked Items
			driver.findElement(By.xpath(excel.getData(2, 458, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the Linked Items are displayed or not
			if(driver.findElement(By.xpath(excel.getData(2, 459, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Linked Inventory Menu Items are displayed");
			}
			else
			{
				test.log(LogStatus.FAIL, "Linked Inventory Menu Items are not displayed");
			}
			Thread.sleep(2000);	
			
			//Click the Link Option
			driver.findElement(By.xpath(excel.getData(2, 177, 1))).click();
			
			Thread.sleep(4500);
			//Click the Right arrow
			driver.findElement(By.xpath(excel.getData(2, 178, 1))).click();
			
	
			Thread.sleep(3500);
			//Click the Close button for Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 460, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			Thread.sleep(1500);
			
			//Click the Close button for Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 460, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 209, 1))).click();
			
			Thread.sleep(3000);
			//Click the Update and Go Button
			driver.findElement(By.xpath(excel.getData(2, 185, 1))).click();
			
			Thread.sleep(2500);
			
			//Check whether the inventory item updated or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Menu item Updated"))
			{
				test.log(LogStatus.PASS, "Menu item Updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Menu item Updated Failed");
			}
			wb.close();
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=22)
		public void Inventory_Menu_Items_verifyActive_InActiveButton_Sub_Recipe(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  

			Thread.sleep(8000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
			
			Thread.sleep(5000);
			//Click Active or In Active Button
			driver.findElement(By.xpath(excel.getData(2, 468, 1))).click();
	
			Thread.sleep(5000);
			//Check the Inactive page is loaded or not
			if(!driver.findElement(By.cssSelector(excel.getData(2, 469, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "We are in InActive Page");
				Thread.sleep(10000);
				//Click Active or In Active Button
				driver.findElement(By.xpath(excel.getData(2, 468, 1))).click();
	
			}
			else
			{
				test.log(LogStatus.FAIL, "We are in Active Page but User Click the Inactive Page");
			}
			wb.close();
			Thread.sleep(5000);
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);}
		}
		@Test(priority=3, enabled=false)
		public void watchTutorial(WebDriver driver) throws Exception
		{
			Thread.sleep(2000);
			//Click the Watch Tutorial Option
			driver.findElement(By.xpath("//span[.='Watch Tutorial']")).click();
			WebElement iframe = driver.findElement(By.xpath("//div[@class='modal-content']/span/iframe"));
			driver.switchTo().frame(iframe);
			Thread.sleep(3500);
			try
			{
				if(driver.findElement(By.xpath("//button/div[.='Watch later']")).isDisplayed()&&driver.findElement(By.xpath("//button/div[.='Share']")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Watch Tutorial Video Played Well");
					Thread.sleep(2500);
				}
			}
			catch(Exception e)
			{
				test.log(LogStatus.FAIL, "Watch Tutorial Video not Played");
			}
			driver.switchTo().defaultContent();
			
			Thread.sleep(2000);
			//Click the Close button
			driver.findElement(By.xpath("//span[.='x' and @title='close']")).click();
		}
}

