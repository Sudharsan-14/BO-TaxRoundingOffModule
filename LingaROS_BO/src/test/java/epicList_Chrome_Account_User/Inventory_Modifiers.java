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

import newReadExcelFile_New.ExcelDataConfig;

public class Inventory_Modifiers {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Modifiers");

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
			Inventory_Modifier_HomePage(driver);
			Inventory_Modifier(driver); 
			Inventory_Modifier_Item_Update(driver);
			Inventory_Modifier_new_item_modifiers_Update(driver);
			Inventory_Modifier_SubRecipe_Update(driver);
			Inventory_Modifier__Add_Invetory_Sub_Recipe(driver);
			Inventory_Modifier__Add_Invetory_Menu_Item(driver);
			Inventory_Modifier__Add_Not_an_InventoryItem(driver);
			Thread.sleep(1500);
		}
		
		@Test(enabled=false,priority=2)
		public void Inventory_Modifier_HomePage(WebDriver driver) throws Exception
		{
			
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(5000);
				//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"inventory_modifiers");

			Thread.sleep(5000);
			try
			{
			//Check Inventory Items page opened or not
			if(driver.findElement(By.xpath(excel.getData(2, 125, 1))).getText().equalsIgnoreCase("Modifiers"))
			{
				test.log(LogStatus.PASS, "Inventory Modifiers page loaded Successfully");
			
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Inventory Modifiers page loaded Failed");
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
			Thread.sleep(5000);
			wb.close();
		}
		
		@Test(enabled=false,priority=4)
		public void Inventory_Modifier(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(5000);

			Thread.sleep(3000);
		//Clear the Search 
		driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
		Thread.sleep(3000);
		//Enter the modifier
		driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Utility.getProperty("Serach_Inventory_modifiers"));
		Thread.sleep(3000);
		//Enter the modifier
	 	driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Keys.ENTER);
		
	 	Thread.sleep(3000);
	 	//Click the modifiers
	 	driver.findElement(By.xpath(excel.getData(2, 484, 1))).click();
		
	 	Thread.sleep(10000);
	 	//Check whether link modifiers page opened or not
		if(driver.findElement(By.xpath(excel.getData(2, 127, 1))).getText().equalsIgnoreCase("Link Modifier"))
		{
			test.log(LogStatus.PASS, "Link Modifier page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Link Modifier page loaded Failed");
		}
		wb.close();	
		Thread.sleep(3000);	
		}
		
		@Test(enabled=false,priority=5)
		public void Inventory_Modifier_Item_Update(WebDriver driver) throws Exception
		{

			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(5000);

			//Click the Item Type
			WebElement ItemType=driver.findElement(By.xpath(excel.getData(2, 128, 1)));
			Select ity=new Select(ItemType);
			ity.selectByVisibleText("Item");
			Thread.sleep(3000);
		
			Thread.sleep(3000);
			 //Click Category for Link Modifer
			 driver.findElement(By.xpath(excel.getData(2, 130, 1))).click();
			 //Click the Input field
			 driver.findElement(By.xpath(excel.getData(2, 131, 1))).sendKeys("FOOD");
			 //Enter Category for Link Modifer
			 driver.findElement(By.xpath(excel.getData(2, 131, 1))).sendKeys(Keys.ENTER);
		
				Thread.sleep(10000);
				 //Select Item
				 driver.findElement(By.xpath(excel.getData(3, 1730, 1))).click();
				 Thread.sleep(1000);
				 //Click the Input field
				 driver.findElement(By.xpath(excel.getData(3, 1731, 1))).sendKeys(Keys.ARROW_DOWN);
				 //Enter Category for Link Modifer
				 driver.findElement(By.xpath(excel.getData(3, 1731, 1))).sendKeys(Keys.ENTER);
				 	
			 
		/*	 Thread.sleep(3000);
			//Enter Category for Link Modifer
			 //driver.findElement(By.xpath(excel.getData(2, 131, 1))).sendKeys(Keys.ENTER);
			
			 //Click Category for Link Modifer
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[3]/div[1]/div/a")).click();
			 Thread.sleep(3000);
			 
			 //Enter Category for Link Modifer
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[3]/div[1]/div/div/div/input")).click();
		
			 Thread.sleep(3000);	 
			 //Enter Category for Link Modifer
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[3]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		*/
			 Thread.sleep(5000);
			 //Click update button						
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				wb.close();
				Thread.sleep(2000);
		
		
		}
		
		@Test(enabled=false,priority=6)
		public void Inventory_Modifier_new_item_modifiers_Update(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(5000);

			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(3000);
			//Clear the Search 
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
			Thread.sleep(3000);
			//Enter the modifier
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Utility.getProperty("Serach_Inventory_modifiers"));
			Thread.sleep(3000);
			//Enter the modifier
		 	driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Keys.ENTER);
			
		 	Thread.sleep(5000);
		 	//Click the modifiers
		 	driver.findElement(By.xpath(excel.getData(2, 484, 1))).click();
			
		 	Thread.sleep(3000);
		 	//Check whether link modifiers page opened or not
			if(driver.findElement(By.xpath(excel.getData(2, 127, 1))).getText().equalsIgnoreCase("Link Modifier"))
			{
				test.log(LogStatus.PASS, "Link Modifier page loaded Successfully for modifier level item");
			}
			else
			{
				test.log(LogStatus.FAIL, "Link Modifier page loaded Failed for modifier level item");
			}
						
			
			Thread.sleep(3000);
			//Click the Item Type
			driver.findElement(By.xpath(excel.getData(2, 128, 1))).click();
		/*	Thread.sleep(3000);
			//Select the Item Type
			 Select temp = new Select(driver.findElement(By.xpath(excel.getData(2, 129, 1))));
			 temp.selectByVisibleText("Item");  */
			 Thread.sleep(3000);
			driver.findElement(By.xpath(excel.getData(2, 129, 1))).click();
			 Thread.sleep(3000);
			 //Click Category for Link Modifer
			 driver.findElement(By.xpath(excel.getData(2, 130, 1))).click();
			 Thread.sleep(3000);
			 
			 //Enter Category for Link Modifer
			 driver.findElement(By.xpath(excel.getData(2, 131, 1))).sendKeys("FOOD");
		
			 Thread.sleep(3000);
			//Enter Category for Link Modifer
			 driver.findElement(By.xpath(excel.getData(2, 131, 1))).sendKeys(Keys.ENTER);
		
			 Thread.sleep(3000);
			 
			 
			//Click new item creation button
			 driver.findElement(By.xpath(excel.getData(2, 136, 1))).click();
			 
				
					//Check whether the new form loaded or not
					if(driver.findElement(By.xpath(excel.getData(2, 137, 1))).getText().equalsIgnoreCase("New Inventory Item"))
					{
						test.log(LogStatus.PASS, "New Inventory Items form loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "New Inventory Items form loaded Failed");
					}
	
					Thread.sleep(3000);
					
					//Clear the name field
					driver.findElement(By.name(excel.getData(2, 51, 3))).clear();
					//Enter the Name
					driver.findElement(By.name(excel.getData(2, 51, 3))).sendKeys(Utility.getProperty("Inventory_Items_Name_Modifiers"));
					Thread.sleep(2000);
					
					
			/*		//Click the Add Category button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[1]/div/div[1]/div[2]/div[2]/a/i")).click();
					Thread.sleep(1000);
					
					//Check Whether the new Category form loaded
					if(driver.findElement(By.xpath("//span[.='New Category']")).getText().equalsIgnoreCase("New Category"))
					{
						test.log(LogStatus.PASS, "New Category form loaded successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "New Category form loaded fail");
					}
					Thread.sleep(2000);
					
					//Clear the name field
					driver.findElement(By.name(excel.getData(2, 100, 1))).clear();
					//Enter the required name
					driver.findElement(By.name(excel.getData(2, 100, 1))).sendKeys(Utility.getProperty("Inventory_Category_Name_ForModifiers"));
					
					//Clear the description box
					driver.findElement(By.name(excel.getData(2, 101, 1))).clear();
					//Enter the Description
					driver.findElement(By.name(excel.getData(2, 101, 1))).sendKeys("Desc of Inventory Category");
					
					Thread.sleep(2000);
					//Click the Save button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[3]/div/div/button")).click();
				*/	
					Thread.sleep(2000);
					//Click the Category field
					driver.findElement(By.xpath(excel.getData(2, 140, 1))).click();
					Thread.sleep(2000);
					//Enter the Category location
					driver.findElement(By.xpath(excel.getData(2, 152, 1))).click();
					Thread.sleep(2000);
					//Press the Enter button
					driver.findElement(By.xpath(excel.getData(2, 152, 1))).sendKeys(Keys.ENTER);
					
					Thread.sleep(3000);
					//Click the Add primary storage button
					driver.findElement(By.xpath(excel.getData(2, 146, 1))).click();
					Thread.sleep(2000);
					
					/*//Click the primary storage field
					driver.findElement(By.xpath(excel.getData(2, 143, 1))).click();
					//Enter the storage location
					driver.findElement(By.xpath(excel.getData(2, 485, 1))).sendKeys("Freezer");
					//Press the Enter button
					driver.findElement(By.xpath(excel.getData(2, 485, 1))).sendKeys(Keys.ENTER);
					
					Thread.sleep(3000);
					 //Click the new storage 
				     driver.findElement(By.xpath("//i[@class='fa fa-plus-circle']")).click();
					*/
				     //Check whether the new primary storage location form loaded or not
					if(driver.findElement(By.xpath(excel.getData(2, 99, 1))).getText().equalsIgnoreCase("New Storage"))
					{
						test.log(LogStatus.PASS, "Primary Storage form loaded successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Primary Storage form loaded fail");
					}
					Thread.sleep(2000); 
					
					//Clear the name field
					driver.findElement(By.name(excel.getData(2, 100, 3))).clear();
					//Enter the required name
					driver.findElement(By.name(excel.getData(2, 100, 3))).sendKeys(Utility.getProperty("Inventory_Primary_Storage_Name_ForModifiers"));
					
					//Clear the description field
					driver.findElement(By.name(excel.getData(2, 101, 3))).clear();
					//Enter the required information
					driver.findElement(By.name(excel.getData(2, 101, 3))).sendKeys("Desc of Inventory Primary Storage");
					
					Thread.sleep(2000);
					//Click the Save button
					driver.findElement(By.xpath(excel.getData(2, 151, 1))).click();
							
					Thread.sleep(2000);
					//Click the Inventory Unit Option
					driver.findElement(By.xpath(excel.getData(2, 142, 1))).click();
					Thread.sleep(2000);
					//Enter the required Inventory unit
					driver.findElement(By.xpath(excel.getData(2, 87, 1))).sendKeys("Liter");
					Thread.sleep(2000);
					//Press the Enter button
					driver.findElement(By.xpath(excel.getData(2, 87, 1))).sendKeys(Keys.ENTER);
					
					Thread.sleep(2000);
					//Clear the Par level option
					driver.findElement(By.name(excel.getData(2, 88, 3))).clear();
					
					Thread.sleep(2000);
					//Enter the required amount of Par level
					driver.findElement(By.name(excel.getData(2, 88, 3))).sendKeys("1");
					
					Thread.sleep(2000);
					//Click the secondary storage field
					driver.findElement(By.xpath(excel.getData(2, 103, 1))).click();
					Thread.sleep(2000);
					//Enter the storage location
					driver.findElement(By.xpath(excel.getData(2, 104, 1))).sendKeys("Freezer");
					
					Thread.sleep(2000);
					//Press the Enter button
					driver.findElement(By.xpath(excel.getData(2, 104, 1))).sendKeys(Keys.ENTER);
					
					Thread.sleep(2000);
					//Click the Vendor unit
					driver.findElement(By.xpath(excel.getData(2, 486, 1))).click();
					//Enter the required vendor
					driver.findElement(By.xpath(excel.getData(2, 487, 1))).sendKeys("Sysco Food");
					//Press the Enter button
					driver.findElement(By.xpath(excel.getData(2, 487, 1))).sendKeys(Keys.ENTER);
					
					
					Thread.sleep(2000);//Clear the Bar code option
					driver.findElement(By.name(excel.getData(2, 156, 3))).clear();
					//Enter the required Bar Code
					driver.findElement(By.name(excel.getData(2, 156, 3))).sendKeys(Utility.getProperty("Inventory_Sku_OR_Bar_Code_ForModifiers"));
					
					//Clear the Brand Name
					driver.findElement(By.name(excel.getData(2, 157, 3))).clear();
					//Enter the required Brand Name
					driver.findElement(By.name(excel.getData(2, 157, 3))).sendKeys("Brand");
					
		try {			Thread.sleep(2000);
					//Click the purchase unit option
					driver.findElement(By.xpath(excel.getData(2, 158, 1))).click();
					Thread.sleep(2000);
					//Enter the required option
					driver.findElement(By.xpath(excel.getData(2, 159, 1))).click();
					//Press the Enter button
					driver.findElement(By.xpath(excel.getData(2, 159, 1))).sendKeys(Keys.ENTER);} catch(Exception g) {}
					
					Thread.sleep(2000);
					//Clear the unit multiplier option
					driver.findElement(By.name(excel.getData(2, 160, 3))).clear();
					//Enter the required unit multipliuer
					driver.findElement(By.name(excel.getData(2, 160, 3))).sendKeys("1");
					
					Thread.sleep(2000);
					//Clear the Quantity option
					driver.findElement(By.name(excel.getData(2, 85, 3))).clear();
					//Enter the required Quantity
					driver.findElement(By.name(excel.getData(2, 85, 3))).sendKeys("1");
					
					
					Thread.sleep(2000);
					//Clear the price option
					driver.findElement(By.name(excel.getData(2, 161, 3))).clear();
					//Enter the required price
					driver.findElement(By.name(excel.getData(2, 161, 3))).sendKeys("115");
					
					Thread.sleep(2000);
					//Clear the yield option
					driver.findElement(By.name(excel.getData(2, 89, 3))).clear();
					//Enter the required yield
					driver.findElement(By.name(excel.getData(2, 89, 3))).sendKeys("11");
					
					Thread.sleep(2000);
					//Clear the pricePerUnit option
					driver.findElement(By.name(excel.getData(2, 162, 3))).clear();
					//Enter the required pricePerUnit
					driver.findElement(By.name(excel.getData(2, 162, 3))).sendKeys("75");
					
					Thread.sleep(2000);
					Thread.sleep(2000);
			/*		//Click the Add new vendor option
					driver.findElement(By.xpath(excel.getData(2, 163, 1))).click();
					
					//Click the Vendor unit
					driver.findElement(By.xpath(excel.getData(2, 488, 1))).click();
					//Enter the required vendor  
					driver.findElement(By.xpath(excel.getData(2, 489, 1))).sendKeys("Krishna butcher shop");
					//Press the Enter button
					driver.findElement(By.xpath(excel.getData(2, 489, 1))).sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					Thread.sleep(2000);
					//Clear the Bar code option
					driver.findElement(By.name(excel.getData(2, 156, 3))).clear();
					//Enter the required Bar Code
					driver.findElement(By.name(excel.getData(2, 156, 3))).sendKeys(Utility.getProperty("Inventory_Sku_OR_Bar_Code")+"NEW");
					Thread.sleep(2000);
					Thread.sleep(2000);
					//Clear the Brand Name
					driver.findElement(By.name(excel.getData(2, 157, 3))).clear();
					//Enter the required Brand Name
					driver.findElement(By.name(excel.getData(2, 157, 3))).sendKeys("Brand");
					
					Thread.sleep(2000);
					//Click the purchase unit option
					driver.findElement(By.xpath(excel.getData(2, 490, 1))).click();
					
					Thread.sleep(2000);//Enter the required option
					driver.findElement(By.xpath(excel.getData(2, 491, 1))).sendKeys("liter");
					Thread.sleep(2000);//Press the Enter button
					driver.findElement(By.xpath(excel.getData(2, 491, 1))).sendKeys(Keys.ENTER);
					
					Thread.sleep(2000);
					//Clear the unit multiplier option
					driver.findElement(By.xpath(excel.getData(2, 492, 1))).clear();
					Thread.sleep(2000);//Enter the required unit multiplier
					driver.findElement(By.xpath(excel.getData(2, 492, 1))).sendKeys("1000");
					
					Thread.sleep(2000);
					//Clear the Quantity option
					driver.findElement(By.xpath(excel.getData(2, 493, 1))).clear();
					//Enter the required Quantity
					driver.findElement(By.xpath(excel.getData(2, 493, 1))).sendKeys("1");
					
					Thread.sleep(2000);
					//Clear the price option
					driver.findElement(By.xpath(excel.getData(2, 494, 1))).clear();
					//Enter the required price
					driver.findElement(By.xpath(excel.getData(2, 494, 1))).sendKeys("115");
					
					Thread.sleep(2000);
					//Clear the yield option
					driver.findElement(By.name(excel.getData(2, 89, 3))).clear();
					//Enter the required yield
					driver.findElement(By.name(excel.getData(2, 89, 3))).sendKeys("10");
					
					Thread.sleep(3000);
					driver.findElement(By.name(excel.getData(2, 162, 3))).click();
					//Clear the pricePerUnit option
					driver.findElement(By.name(excel.getData(2, 162, 3))).clear();
					//Enter the required pricePerUnit
					driver.findElement(By.name(excel.getData(2, 162, 3))).sendKeys("75");
					
					Thread.sleep(2000);
					//Click the New Vendor Option
					driver.findElement(By.xpath(excel.getData(2, 495, 1))).click();
					
					Thread.sleep(2000);
					//Click the Close Button
					driver.findElement(By.xpath(excel.getData(2, 495, 1))).click();
												 
					//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/ul/li[3]/a/uib-tab-heading/a/i
					
					//Click the Yes button in the popup
					driver.findElement(By.linkText(excel.getData(2, 441, 5))).click();
					Thread.sleep(3000);
				*/	
					//Clear the Equal field
					driver.findElement(By.xpath(excel.getData(2, 165, 1))).clear();
					//Enter the required number
					driver.findElement(By.xpath(excel.getData(2, 165, 1))).sendKeys("1");
					
					Thread.sleep(2000);
					//Click the Select an Option
					driver.findElement(By.xpath(excel.getData(2, 496, 1))).click();
					//Enter the required unit
					driver.findElement(By.xpath(excel.getData(2, 497, 1))).sendKeys("Liter");
					//Press the Enter button
					driver.findElement(By.xpath(excel.getData(2, 497, 1))).sendKeys(Keys.ENTER);
	
					//click price option
					driver.findElement(By.xpath(excel.getData(2, 166, 1))).click();
					//Clear the Price Option
					driver.findElement(By.xpath(excel.getData(2, 166, 1))).clear();
					//Enter the required price per unit
					driver.findElement(By.xpath(excel.getData(2, 166, 1))).sendKeys("123");
	
			/*		Thread.sleep(3000);
					//Click the Add Recipe Unit Option
					driver.findElement(By.xpath(excel.getData(2, 167, 1))).click();
					
					Thread.sleep(1000);
					//Clear the Equal field
					driver.findElement(By.xpath(excel.getData(2, 165, 1))).clear();
					//Enter the required number
					driver.findElement(By.xpath(excel.getData(2, 165, 1))).sendKeys("1000");
					
					Thread.sleep(2000);
					//Click the Select an Option
					driver.findElement(By.xpath(excel.getData(2, 498, 1))).click();
					//Enter the required unit
					driver.findElement(By.xpath(excel.getData(2, 499, 1))).sendKeys("Liter");
					//Press the Enter button
					driver.findElement(By.xpath(excel.getData(2, 499, 1))).sendKeys(Keys.ENTER);
	
					//click price option
					driver.findElement(By.xpath(excel.getData(2, 500, 1))).click();
					//Clear the Price Option
					driver.findElement(By.xpath(excel.getData(2, 500, 1))).clear();
					//Enter the required price per unit
					driver.findElement(By.xpath(excel.getData(2, 500, 1))).sendKeys("123");
	*/
					Thread.sleep(2000);
					//Click the Add Recipe Unit Option
					driver.findElement(By.xpath(excel.getData(2, 167, 1))).click();
	
					Thread.sleep(2000);
					//Click the Close Button
					driver.findElement(By.xpath(excel.getData(3, 1729, 1))).click();
					
					Thread.sleep(2000);
					//Click the Yes button in the popup
					driver.findElement(By.linkText(excel.getData(2, 441, 5))).click();
					
					//Click the Save button
					driver.findElement(By.xpath(excel.getData(2, 107, 1))).click();
					Thread.sleep(2000);
			try
			{ 
				WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
				WebDriverWait wait=new WebDriverWait(driver,60);
				//	Thread.sleep(4000);
					//Check whether the new Inventory Items saved or not
					if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Inventory item saved successfully!."))
					{
						test.log(LogStatus.PASS, "New Inventory Items Saved successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "New Inventory Items Save Failed");
					}
			}
			catch(Exception e) {}
			
				
					Thread.sleep(8000);
		 //Click update button
		driver.findElement(By.xpath(excel.getData(3, 2242, 1))).click();
		wb.close();
		Thread.sleep(10000);
		
	}
		
		@Test(enabled=false,priority=7)
		public void Inventory_Modifier_SubRecipe_Update(WebDriver driver) throws Exception
		{

			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(5000);

			//Clear the Search 
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
			Thread.sleep(3000);
			//Enter the modifier
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Utility.getProperty("Serach_Inventory_modifiers"));
			Thread.sleep(3000);
			//Enter the modifier
		 	driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Keys.ENTER);
			
		 	Thread.sleep(3000);
		 	//Click the modifiers
		 	driver.findElement(By.xpath(excel.getData(2, 484, 1))).click();
			
		 	Thread.sleep(3000);
		 	//Check whether link modifiers page opened or not
			if(driver.findElement(By.xpath(excel.getData(2, 127, 1))).getText().equalsIgnoreCase("Link Modifier"))
			{
				test.log(LogStatus.PASS, "Link Modifier page loaded Successfully for sub recipe");
			}
			else
			{
				test.log(LogStatus.FAIL, "Link Modifier page loaded Failed for sub recipe");
			}
			
			Thread.sleep(3000);
			//Click the Item Type
		//	driver.findElement(By.xpath(excel.getData(2, 128, 1))).click();
		
			Thread.sleep(3000);
			//Select the Item Type
			 Select temp = new Select(driver.findElement(By.xpath(excel.getData(2, 128, 1))));
			 temp.selectByVisibleText("Sub Recipe");  
			
			
			Thread.sleep(6000);
			//Select modifier sub recipe option
		//	driver.findElement(By.xpath(excel.getData(2, 502, 1))).click();
			Thread.sleep(3000);
			
			 //Click item field 
			 driver.findElement(By.xpath(excel.getData(2, 130, 1))).click();
			 Thread.sleep(2000);
			 //Select sub recipe
			// driver.findElement(By.xpath(excel.getData(2, 131, 1))).sendKeys("Aruku");
			 driver.findElement(By.xpath(excel.getData(2, 131, 1))).click();

			 Thread.sleep(2000);
			//Enter the selected sub recipe
			 driver.findElement(By.xpath(excel.getData(2, 131, 1))).sendKeys(Keys.ENTER);
			 
			 Thread.sleep(6000);
			 //Click update button
			driver.findElement(By.xpath(excel.getData(3, 2242, 1))).click();
				wb.close();
		
		}
		
		@Test(enabled=false,priority=8)	
		public void Inventory_Modifier__Add_Invetory_Sub_Recipe(WebDriver driver) throws Exception	
			{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(5000);

				//Clear the Search 
				driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
				Thread.sleep(3000);
				//Enter the modifier
				driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Utility.getProperty("Serach_Inventory_modifiers"));
				Thread.sleep(3000);
				//Enter the modifier
			 	driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Keys.ENTER);
				
			 	Thread.sleep(3000);
			 	//Click the modifiers
			 	driver.findElement(By.xpath(excel.getData(2, 484, 1))).click();
				
			 	Thread.sleep(3000);
			 	//Check whether link modifiers page opened or not
				if(driver.findElement(By.xpath(excel.getData(2, 127, 1))).getText().equalsIgnoreCase("Link Modifier"))
				{
					test.log(LogStatus.PASS, "Link Modifier page loaded Successfully for sub recipe");
				}
				else
				{
					test.log(LogStatus.FAIL, "Link Modifier page loaded Failed for sub recipe");
				}
				
				
				Thread.sleep(3000);
				//Click the Item Type
			//	driver.findElement(By.xpath(excel.getData(2, 128, 1))).click();
			
				Thread.sleep(3000);
				//Select the Item Type
				 Select temp = new Select(driver.findElement(By.xpath(excel.getData(2, 128, 1))));
				 temp.selectByVisibleText("Sub Recipe");  
				
				Thread.sleep(3000);
				//Select modifier subreceipe option
			//	driver.findElement(By.xpath(excel.getData(2, 502, 1))).click();
				 
				
				Thread.sleep(3000);
				 //Click new sub recipe creation button
				 driver.findElement(By.xpath(excel.getData(2, 503, 1))).click();
				 
				 Thread.sleep(3000);				
				 //Check whether the new form loaded or not		
				 if(driver.findElement(By.xpath("//span[.='New SubRecipe']")).getText().equalsIgnoreCase("New SubRecipe"))	
				 {		
					 test.log(LogStatus.PASS, "New SubRecipe form loaded Successfully");	
				 }		
				 else		
				 {			
					 test.log(LogStatus.FAIL, "New SubRecipe form loaded Failed");		
				 }		
				 
				 Thread.sleep(3000);				 
				 //Clear the name field		
				 driver.findElement(By.name(excel.getData(2, 51, 3))).clear();		
				 //Enter the Name		
				 driver.findElement(By.name(excel.getData(2, 51, 3))).sendKeys(Utility.getProperty("Inventory_SubRecipe_Name_Modi"));		
				 Thread.sleep(2000);			
				 //Enable the Calculate COGS On Cost Price button		
				 driver.findElement(By.name(excel.getData(2, 52, 3))).click();	
				 

					Thread.sleep(3000);
					
						if(driver.findElement(By.xpath("//table[@class='table table-bordered']")).isDisplayed())
						{
							List<WebElement> list=driver.findElements(By.xpath("//table[@class='table table-bordered']/tbody/tr"));
						
							int rowSize=list.size();
							
							for(int i=1;i<rowSize;i++)
							{
								Thread.sleep(1000);
								//Close the Items
								driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[9]/a")).click();
							
								Thread.sleep(1000);
								//Click Yes buton
								driver.findElement(By.linkText("Yes")).click();
							
							}
						
						}
						else
						{
							
						}

				 Thread.sleep(2000);		
			/*	 //Add Inventory Item		
				 driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();				
				 Thread.sleep(1000);		
				 //Click the Category Option		
				 driver.findElement(By.xpath(excel.getData(2, 54, 1))).click();		
				 //Enter the required input		
				 driver.findElement(By.xpath(excel.getData(2, 55, 1))).sendKeys("FOOD");		
				 //EPress the Enter button	
				 Thread.sleep(1000);
				 driver.findElement(By.xpath(excel.getData(2, 55, 1))).sendKeys(Keys.ENTER);	
				 	
				 Thread.sleep(3000);		
				 	//Click the Item		
				 	driver.findElement(By.xpath(excel.getData(2, 56, 1))).click();		
				//Enter the Required Item		
				driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys("Sugar");		
				//Press the Enter button		
				Thread.sleep(2000);
				driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys(Keys.ENTER);			
				
				Thread.sleep(1000);		
				//Click the Recipe Unit		
				driver.findElement(By.xpath(excel.getData(2, 58, 1))).click();		
				//Enter the Required Recipe Unit		
				driver.findElement(By.xpath(excel.getData(2, 59, 1))).sendKeys("Kilo gram");	
				//Press the Enter button		
				driver.findElement(By.xpath(excel.getData(2, 59, 1))).sendKeys(Keys.ENTER);				
				
				Thread.sleep(1000);		
				//Clear the Quantity		
				driver.findElement(By.xpath(excel.getData(2, 60, 1))).clear();
				//Enter the required quantity	
				driver.findElement(By.xpath(excel.getData(2, 60, 1))).sendKeys("3");				
				Thread.sleep(2000);
				*/
				 
				//Add Sub Recipe	
				driver.findElement(By.xpath(excel.getData(2, 62, 1))).click();	
				Thread.sleep(1000);	
				//Click the Item	
				driver.findElement(By.xpath(excel.getData(2, 56, 1))).click();
				//Enter the Required Item	
			//	driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys("Onion Fry");	
				driver.findElement(By.xpath(excel.getData(2, 57, 1))).click();

				//Press the Enter button		
				driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys(Keys.ENTER);	
				Thread.sleep(1000);	
				
				//Click the Recipe Unit	
				driver.findElement(By.xpath(excel.getData(2, 58, 1))).click();	
				//Enter the Required Recipe Unit	
		//		driver.findElement(By.xpath(excel.getData(2, 59, 1))).sendKeys("Kilo gram");
				driver.findElement(By.xpath(excel.getData(2, 59, 1))).click();	

				//Press the Enter button	
				driver.findElement(By.xpath(excel.getData(2, 59, 1))).sendKeys(Keys.ENTER);	
				
				Thread.sleep(1000);	
				//Clear the Quantity		
				driver.findElement(By.xpath(excel.getData(2, 60, 1))).clear();		
				//Enter the required quantity	
				driver.findElement(By.xpath(excel.getData(2, 60, 1))).sendKeys("3");	
				Thread.sleep(2000);	
				
				//Add Manual Entry	
				driver.findElement(By.xpath(excel.getData(2, 68, 1))).click();	
				Thread.sleep(1000);	
				//clear the Required Item	
				driver.findElement(By.xpath(excel.getData(2, 425, 1))).clear();		
				//Enter the required Item		
				driver.findElement(By.xpath(excel.getData(2, 425, 1))).sendKeys("Test");	
				Thread.sleep(1000);	
				
				//clear the Price per Unit Option		
				driver.findElement(By.xpath(excel.getData(2, 426, 1))).clear();	
				//Enter the Price per Unit
				driver.findElement(By.xpath(excel.getData(2, 426, 1))).sendKeys("500");	
				Thread.sleep(1000);		
				
				//Clear the Quantity	
				driver.findElement(By.xpath(excel.getData(2, 67, 1))).clear();	
				//Enter the required quantity	
				driver.findElement(By.xpath(excel.getData(2, 67, 1))).sendKeys("2");	
				Thread.sleep(2000);	
				
				//Add Inventory Item		
				driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();	
				Thread.sleep(1500);	
				//Click the Close button for Inventory Item	
				driver.findElement(By.xpath(excel.getData(2, 415, 1))).click();	
				Thread.sleep(1000);	
				//Click the Yes Button	
				driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();	
				Thread.sleep(3000);					
				//Add Sub Recipe
				driver.findElement(By.xpath(excel.getData(2, 62, 1))).click();		
				Thread.sleep(1500);	
				//Click the Close button for Sub Recipe	
				driver.findElement(By.xpath(excel.getData(2, 415, 1))).click();	
				Thread.sleep(1000);		
				//Click the Yes Button		
				driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();	
				Thread.sleep(1500);				
				
				//Add Manual Entry	
				//Thread.sleep(2000);	
				//driver.findElement(By.xpath("//input[@class='form-control ng-pristine ng-valid ng-touched']")).sendKeys("2");
				
					Thread.sleep(2000);		
					//Clear the notes field		
					driver.findElement(By.name(excel.getData(2, 84, 3))).clear();	
					//Enter the notes	
					driver.findElement(By.name(excel.getData(2, 84, 3))).sendKeys("Inventory Sub Recipe Notes");	
					Thread.sleep(2000);					
					//Clear the Quantity Field	
					driver.findElement(By.name(excel.getData(2, 85, 3))).clear();	
					//Enter the Quantity		
					driver.findElement(By.name(excel.getData(2, 85, 3))).sendKeys("2");		
					
					Thread.sleep(2000);				
					//Click the Inventory Unit	
					driver.findElement(By.xpath(excel.getData(2, 142, 1))).click();		
					//Enter the Required Inventory Unit		
					driver.findElement(By.xpath(excel.getData(2, 87, 1))).sendKeys("Liter");	
					//Press the Enter button		
 					driver.findElement(By.xpath(excel.getData(2, 87, 1))).sendKeys(Keys.ENTER);	
 					
					Thread.sleep(2000);		
					//Clear the Par Level Option		
					driver.findElement(By.id(excel.getData(2, 88, 2))).clear();		
					//Enter the Par Level		
					driver.findElement(By.id(excel.getData(2, 88, 2))).sendKeys("1");	
					
					Thread.sleep(2000);		
					//Clear the Yield field		 
					driver.findElement(By.name(excel.getData(2, 89, 3))).clear();		
					//Enter the Required Yield		
					driver.findElement(By.name(excel.getData(2, 89, 3))).sendKeys("20");		
					
					Thread.sleep(2000);
					//Price per unit
					driver.findElement(By.name(excel.getData(2, 162, 3))).clear();		
					//Enter the Price per Unit
					driver.findElement(By.name(excel.getData(2, 162, 3))).sendKeys("20");	
					
					//Click Add Primary Storage Location		
					driver.findElement(By.xpath(excel.getData(2, 91, 1))).click();			
					Thread.sleep(1500);	
					//Check whether the New Storage form loaded or not		
					if(driver.findElement(By.xpath(excel.getData(2, 99, 1))).getText().equalsIgnoreCase("New Storage"))	
					{			
						test.log(LogStatus.PASS, "New Storage form Loaded Successfully");	
						}		
					else	
						{			test.log(LogStatus.FAIL, "New Storage form Loaded Failed");		
						}		
					
					Thread.sleep(2000);			
						
						
						//Clear the name field		
						driver.findElement(By.id(excel.getData(2, 100, 2))).clear();		
						//Enter the name		
						driver.findElement(By.id(excel.getData(2, 100, 2))).sendKeys(Utility.getProperty("Inventory_SubRecipe_Primary_Storage_Name_ForModifier")+"a");				
						Thread.sleep(1000);		
						//Clear the Description		
						driver.findElement(By.id(excel.getData(2, 101, 2))).clear();		
						//Enter the Description		
						driver.findElement(By.id(excel.getData(2, 101, 2))).sendKeys("Desc of Primary Storage");	
						Thread.sleep(1000);
						//Click the Save button		
						driver.findElement(By.xpath(excel.getData(2, 151, 1))).click();		
					
						try
						{
						Thread.sleep(3500);			
						//Click the Secondary Storage Location		
						driver.findElement(By.xpath(excel.getData(2, 103, 1))).click();		
						Thread.sleep(500);
						//Enter the Secondary Storage Location		
						driver.findElement(By.xpath(excel.getData(2, 104, 1))).sendKeys("Freezer");
						//Press the Enter button		
						driver.findElement(By.xpath(excel.getData(2, 104, 1))).sendKeys(Keys.ENTER);		
						}
						catch(Exception e) {}
						Thread.sleep(2000);		
						//Clear the Input field		
						driver.findElement(By.xpath(excel.getData(2, 504, 1))).clear();		
						//Enter the required Input	
					 	driver.findElement(By.xpath(excel.getData(2, 504, 1))).sendKeys("2");
					 	
					
						Thread.sleep(1000);		
						//Click the Required option				
						driver.findElement(By.xpath(excel.getData(2, 105, 1))).click();		
						Thread.sleep(1000);		
						//Enter the Required Option		
						driver.findElement(By.xpath(excel.getData(2, 106, 1))).sendKeys("Liter");	
						//Press the Enter button	
						driver.findElement(By.xpath(excel.getData(2, 106, 1))).sendKeys(Keys.ENTER);			
						driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		
						
						Thread.sleep(5000);		
						//Click the Save button		
						driver.findElement(By.xpath(excel.getData(2, 107, 1))).click();		
						
					Thread.sleep(10000); 
					//Click update button
					driver.findElement(By.xpath(excel.getData(3, 2242, 1))).click();
				
				 //	Thread.sleep(4000);
					WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
					WebDriverWait wait=new WebDriverWait(driver,90);
					
				  	//Check whether the new Inventory Items saved or not
					if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Modifier updated successfully!."))
					{
						test.log(LogStatus.PASS, "Modifier updated successfully for sub recipe");
					}
					else
					{
						test.log(LogStatus.FAIL, "Modifier updated Failed for sub recipe");
					}
					wb.close();
				Thread.sleep(3000); 
				}
			
		@Test(enabled=false,priority=9)	
		public void Inventory_Modifier__Add_Invetory_Menu_Item(WebDriver driver) throws Exception	
		
			{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(5000);

			//Clear the Search 
				driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
				Thread.sleep(3000);
				//Enter the modifier
				driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Utility.getProperty("Serach_Inventory_modifiers"));
				Thread.sleep(3000);
				//Enter the modifier
			 	driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Keys.ENTER);
				
			 	Thread.sleep(3000);
			 	//Click the modifiers
			 	driver.findElement(By.xpath(excel.getData(2, 484, 1))).click();
				
			 	Thread.sleep(3000);
			 	//Check whether link modifiers page opened or not
				if(driver.findElement(By.xpath(excel.getData(2, 127, 1))).getText().equalsIgnoreCase("Link Modifier"))
				{
					test.log(LogStatus.PASS, "Link Modifier page loaded Successfully for Menu Item");
				}
				else
				{
					test.log(LogStatus.FAIL, "Link Modifier page loaded Failed for Menu Item");
				}
				
				
				Thread.sleep(3000);
				//Click the Item Type
				driver.findElement(By.xpath(excel.getData(2, 128, 1))).click();
			
			/*	Thread.sleep(3000);
				//Select the Item Type
				 Select temp = new Select(driver.findElement(By.xpath(excel.getData(2, 129, 1))));
				 temp.selectByVisibleText("Item");  
				*/
				Thread.sleep(3000);
				//Select modifier level Menu item option
				driver.findElement(By.xpath(excel.getData(2, 507, 1))).click();
				 
		/*		Thread.sleep(3000);
				 //Click new item creation button
				 driver.findElement(By.xpath(excel.getData(2, 130, 1))).click();
				//Select new item creation button
				 driver.findElement(By.xpath(excel.getData(2, 131, 1))).sendKeys("CHEE LATTU");
				//Enter new item creation button
				 driver.findElement(By.xpath(excel.getData(2, 131, 1))).sendKeys(Keys.ENTER);
			*/
				//Select Item in menu item
				driver.findElement(By.xpath(excel.getData(2, 130, 1))).click();
				//Select Item
				driver.findElement(By.xpath(excel.getData(2, 131, 1))).sendKeys("Burger");
				//Enter the item
				driver.findElement(By.xpath(excel.getData(2, 131, 1))).sendKeys(Keys.ENTER);
				 Thread.sleep(2000);
				 
				   //Click update button
					driver.findElement(By.xpath(excel.getData(3, 2242, 1))).click();
					
			
				//	Thread.sleep(5000);
					WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
					WebDriverWait wait=new WebDriverWait(driver,90);
					
				  	//Check whether the new Inventory Items saved or not
					if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Modifier updated successfully!."))
					{
						test.log(LogStatus.PASS, "Modifier updated successfully for Menu item");
					}
					else
					{
						test.log(LogStatus.FAIL, "Modifier updated Failed for Menu item");
					}
					wb.close();
				Thread.sleep(3000);
				}	
				 	
		@Test(enabled=false,priority=10)	
		public void Inventory_Modifier__Add_Not_an_InventoryItem(WebDriver driver) throws Exception	
		
			{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(5000);

			//Clear the Search 
				driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
				Thread.sleep(3000);
				//Enter the modifier
				driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Utility.getProperty("Serach_Inventory_modifiers"));
				Thread.sleep(3000);
				//Enter the modifier
			 	driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Keys.ENTER);
				
			 	Thread.sleep(3000);
			 	//Click the modifiers
			 	driver.findElement(By.xpath(excel.getData(2, 484, 1))).click();
				
			 	Thread.sleep(3000);
			 	//Check whether link modifiers page opened or not
				if(driver.findElement(By.xpath(excel.getData(2, 127, 1))).getText().equalsIgnoreCase("Link Modifier"))
				{
					test.log(LogStatus.PASS, "Link Modifier page loaded Successfully for Menu Item");
				}
				else
				{
					test.log(LogStatus.FAIL, "Link Modifier page loaded Failed for Menu Item");
				}
				
				
				Thread.sleep(3000);
				//Click the Item Type
				driver.findElement(By.xpath(excel.getData(2, 128, 1))).click();
			
			/*	Thread.sleep(3000);
				//Select the Item Type
				 Select temp = new Select(driver.findElement(By.xpath(excel.getData(2, 129, 1))));
				 temp.selectByVisibleText("Item");  
				*/
				Thread.sleep(3000);
				//Select modifier level Menu item option
				driver.findElement(By.xpath(excel.getData(2, 508, 1))).click();
				 
				Thread.sleep(2000);
				//Click update button
					driver.findElement(By.xpath(excel.getData(3, 2242, 1))).click();
					
			
					//Thread.sleep(5000);
					WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
					WebDriverWait wait=new WebDriverWait(driver,90);
					
				  	//Check whether the new Inventory Items saved or not
					if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Modifier updated successfully!."))
					{
						test.log(LogStatus.PASS, "Modifier updated successfully for Menu item");
					}
					else
					{
						test.log(LogStatus.FAIL, "Modifier updated Failed for Menu item");
					}
				Thread.sleep(3000);
				wb.close();
				if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);//watchTutorial(driver);
				Thread.sleep(5000);
				}
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
