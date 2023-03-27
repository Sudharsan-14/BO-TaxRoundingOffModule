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


public class Inventory_Items {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Items");

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
			//SendMail.snedMailWithAttachment();
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
			Inventory_Items_openpage(driver);
			Inventory_Items_refresh(driver);
			Inventory_item_pagination(driver);
			Inventory_Items_add(driver);
			Inventory_Items_edit_Inventory_Items(driver);
			Inventory_Items_delete(driver);
			Inventory_Items_closeButton(driver);
			Inventory_Items_verifyActive_InActiveButton(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=37)
		public void Inventory_Items_openpage(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis); 
		
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"inventoryItems");

			Thread.sleep(4000);
			try
			{
			//Check Inventory Items page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1205, 1))).getText().equalsIgnoreCase("Inventory Item"))
			{
				test.log(LogStatus.PASS, "Inventory Items page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Inventory Items page loaded Failed");
			
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
			
			Thread.sleep(4000);wb.close();
			
		}
		
		@Test(enabled=false,priority=38)
		public void Inventory_Items_refresh(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(3, 1660, 1))).click();
			Thread.sleep(5000);
			//Check Inventory Items page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1205, 1))).getText().equalsIgnoreCase("Inventory Item"))
			{
				test.log(LogStatus.PASS, "Inventory Items page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inventory Items page refreshed Failed");
			}
			Thread.sleep(5000);wb.close();

		}

		@Test(enabled=false,priority=39)
		public void Inventory_item_pagination(WebDriver driver) throws Exception
		{
			   File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
	 				
			Thread.sleep(10000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			Thread.sleep(1000);
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			Thread.sleep(2000);
			//Click the Pagination option as 15
			driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[3]/a")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 15");
			//Create the web element for Inventory Items Table
			List<WebElement> results = driver.findElements(By.xpath(excel.getData(3, 1265, 1)));
			for (WebElement result : results){						
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			} 
			Thread.sleep(8000);
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			Thread.sleep(2000);
			//Click the Pagination option as 20
			driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[4]/a")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 20");
			//Create the web element for Inventory Items Table
			List<WebElement> results1 = driver.findElements(By.xpath(excel.getData(3, 1265, 1)));
			for (WebElement result : results1){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			//Thread.sleep(8000);
			
			//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			//Thread.sleep(8000);
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			Thread.sleep(2000);
			//Click the Pagination option as 10
			driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[2]/a")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 10");
			//Create the web element for Inventory Items Table
			List<WebElement> results3 = driver.findElements(By.xpath(excel.getData(3, 1265, 1)));
			for (WebElement result : results3){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			
			Thread.sleep(2000);
			//Click the Pagination option as 25
			driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[1]/a")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 5");
			//Create the web element for Inventory Items Table
			List<WebElement> results2 = driver.findElements(By.xpath(excel.getData(3, 1265, 1)));
			for (WebElement result : results2){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			
			
			Thread.sleep(8000);wb.close();
		}
		
		@Test(enabled=false,priority=40)
		public void Inventory_Items_add(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Click on the Add Inventory Items option
			driver.findElement(By.id("inventoryItem")).click();
			Thread.sleep(3000);
			
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
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Inventory_Items_Name_Item"));
			Thread.sleep(2000);
			
			
			//Click the Add Category button
		/*	driver.findElement(By.xpath(excel.getData(3, 1270, 1))).click();
			Thread.sleep(1000); 
			
			//Check Whether the new Category form loaded
			if(driver.findElement(By.xpath(excel.getData(3, 1271, 1))).getText().equalsIgnoreCase("New Category"))
			{
				test.log(LogStatus.PASS, "New Category form loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Category form loaded fail");
			}
			Thread.sleep(2000);
			
			//Clear the name field
			driver.findElement(By.name(excel.getData(2, 148, 1))).clear();
			//Enter the required name
			driver.findElement(By.name(excel.getData(2, 148, 1))).sendKeys(Utility.getProperty("Inventory_Category_Name"));
			
			//Clear the description box
			driver.findElement(By.name(excel.getData(3, 1174, 3))).clear();
			//Enter the Description
			driver.findElement(By.name(excel.getData(3, 1174, 3))).sendKeys("Desc of Inventory Category");
			
			Thread.sleep(2000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(2, 102, 1))).click();
			*/
			
			//Click Category
			driver.findElement(By.xpath(excel.getData(2, 140, 1))).click();
			//Enter Category
			driver.findElement(By.xpath(excel.getData(2, 152, 1))).sendKeys(Keys.ARROW_DOWN);
			//Enter Category
			driver.findElement(By.xpath(excel.getData(2, 152, 1))).sendKeys(Keys.ENTER);
			
			 
			Thread.sleep(2000);
			//Click the Add primary storage button
			driver.findElement(By.xpath(excel.getData(2, 146, 1))).click();
			Thread.sleep(2000);
			
			//Check whether the new primary storage location form loaded or not
			if(driver.findElement(By.xpath(excel.getData(2, 99, 1))).getText().equalsIgnoreCase("New Storage"))
			{
				test.log(LogStatus.PASS, "Primary Storage form loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Primary Storage form loaded fail");
			}
			Thread.sleep(5000); 
			
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(2, 148, 1))).clear();
			//Enter the required name
			driver.findElement(By.xpath(excel.getData(2, 148, 1))).sendKeys(Utility.getProperty("Inventory_Primary_Storage_Name")); 
			
			//Clear the description field
			driver.findElement(By.xpath(excel.getData(2, 149, 1))).clear();
			//Enter the required information
			driver.findElement(By.xpath(excel.getData(2, 149, 1))).sendKeys("Desc of Inventory Primary Storage");
			
			Thread.sleep(2000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(2, 102, 1))).click();
			
			Thread.sleep(2000);
			//Click the Inventory Unit Option
			driver.findElement(By.xpath(excel.getData(2, 86, 1))).click();
			//Enter the required Inventory unit
			driver.findElement(By.xpath(excel.getData(2, 87, 1))).sendKeys("Liter");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 87, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Clear the Par level option
			driver.findElement(By.xpath(excel.getData(2, 88, 1))).clear();
			//Enter the required amount of Par level
			driver.findElement(By.xpath(excel.getData(2, 88, 1))).sendKeys("1");
			
			Thread.sleep(2000);
			//Click the secondary storage field
			driver.findElement(By.xpath(excel.getData(2, 103, 1))).click();
			//Enter the storage location
			Thread.sleep(1500);
			//driver.findElement(By.xpath(excel.getData(2, 104, 1))).sendKeys("Freezer");
			driver.findElement(By.xpath(excel.getData(2, 104, 1))).click();

			Thread.sleep(2000);
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 104, 1))).sendKeys(Keys.ENTER);
			
			//Click the Vendor unit
			driver.findElement(By.xpath(excel.getData(2, 486, 1))).click();
			//Enter the required vendor
			driver.findElement(By.xpath(excel.getData(2, 487, 1))).sendKeys("Sysco Food");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 487, 1))).sendKeys(Keys.ENTER);
			
			//Clear the Bar code option
			driver.findElement(By.xpath(excel.getData(2, 156, 1))).clear();
			//Enter the required Bar Code
			driver.findElement(By.xpath(excel.getData(2, 156, 1))).sendKeys(Utility.getProperty("Inventory_Sku_OR_Bar_Code"));
			
			//Clear the Brand Name
			driver.findElement(By.xpath(excel.getData(2, 157, 1))).clear();
			//Enter the required Brand Name
			driver.findElement(By.xpath(excel.getData(2, 157, 1))).sendKeys("Brand"); 
			
			//Click the purchase unit option
			driver.findElement(By.xpath(excel.getData(2, 158, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(2, 159, 1))).sendKeys("Liter");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 159, 1))).sendKeys(Keys.ENTER);
			
			//Clear the unit multiplier option
			driver.findElement(By.name(excel.getData(2, 160, 3))).clear();
			//Enter the required unit multipliuer
			driver.findElement(By.name(excel.getData(2, 160, 3))).sendKeys("1");
			
			//Clear the Quantity option
			driver.findElement(By.name(excel.getData(2, 85, 3))).clear();
			//Enter the required Quantity
			driver.findElement(By.name(excel.getData(2, 85, 3))).sendKeys("1");
			
			//Clear the price option
			driver.findElement(By.name(excel.getData(2, 161, 3))).clear();
			//Enter the required price
			driver.findElement(By.name(excel.getData(2, 161, 3))).sendKeys("115");
			
			//Clear the yield option
			driver.findElement(By.name(excel.getData(2, 89, 3))).clear();
			//Enter the required yield
			driver.findElement(By.name(excel.getData(2, 89, 3))).sendKeys("11");
			
			//Clear the pricePerUnit option 
			driver.findElement(By.name(excel.getData(2, 162, 3))).clear();
			//Enter the required pricePerUnit
			driver.findElement(By.name(excel.getData(2, 162, 3))).sendKeys("75");
			
			//Click the	
	/*		driver.findElement(By.xpath(excel.getData(3, 1301, 1))).click();
			//Enter the required vendor  
			driver.findElement(By.xpath(excel.getData(3, 1302, 1))).click();
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1302, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Bar code option
			driver.findElement(By.xpath(excel.getData(3, 1303, 1))).clear();
			//Enter the required Bar Code
			driver.findElement(By.xpath(excel.getData(3, 1303, 1))).sendKeys(Utility.getProperty("Inventory_Sku_OR_Bar_Code")+"NEW");
			
			Thread.sleep(2000);
			//Clear the Brand Name
			driver.findElement(By.xpath(excel.getData(3, 1304, 1))).clear();
			//Enter the required Brand Name
			driver.findElement(By.xpath(excel.getData(3, 1304, 1))).sendKeys("Brand");
			
			Thread.sleep(2000);
			//Click the purchase unit option
			driver.findElement(By.xpath(excel.getData(3, 1305, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 1306, 1))).sendKeys("liter");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1306, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the unit multiplier option
			driver.findElement(By.xpath(excel.getData(3, 1307, 1))).clear();
			//Enter the required unit multipliuer
			driver.findElement(By.xpath(excel.getData(3, 1307, 1))).sendKeys("1000");
			
			Thread.sleep(2000);
			//Clear the Quantity option
			driver.findElement(By.xpath(excel.getData(3, 1308, 1))).clear();
			//Enter the required Quantity
			driver.findElement(By.xpath(excel.getData(3, 1308, 1))).sendKeys("1");
			
			Thread.sleep(2000);
			//Clear the price option
			driver.findElement(By.xpath(excel.getData(3, 1309, 1))).clear();
			//Enter the required price
			driver.findElement(By.xpath(excel.getData(3, 1309, 1))).sendKeys("115");
			
			Thread.sleep(2000);
			//Clear the yield option
			driver.findElement(By.xpath(excel.getData(3, 1310, 1))).clear();
			//Enter the required yield
			driver.findElement(By.xpath(excel.getData(3, 1310, 1))).sendKeys("10");
			
			Thread.sleep(3000);
			driver.findElement(By.xpath(excel.getData(2, 166, 1))).click();
			//Clear the pricePerUnit option
			driver.findElement(By.xpath(excel.getData(2, 166, 1))).clear();
			//Enter the required pricePerUnit
			driver.findElement(By.xpath(excel.getData(2, 166, 1))).sendKeys("75");
			
			Thread.sleep(2000);
			//Click the New Vendor Option
			driver.findElement(By.xpath(excel.getData(2, 163, 1))).click();
		
			Thread.sleep(2000);
			//Click the Close Button
			driver.findElement(By.xpath(excel.getData(3, 1663, 1))).click();
					//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/ul/li[3]/a/uib-tab-heading/a/i
			Thread.sleep(3000);
			//Click the Yes button in the popup
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click(); */ 
			
			Thread.sleep(4000); 
			//Click the Select an Option
			driver.findElement(By.xpath(excel.getData(2, 496, 1))).click();
			//Enter the required unit
			driver.findElement(By.xpath(excel.getData(2, 497, 1))).sendKeys("Liter");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 497, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Equal field
			driver.findElement(By.xpath(excel.getData(2, 165, 1))).clear();
			//Enter the required number
			driver.findElement(By.xpath(excel.getData(2, 165, 1))).sendKeys("1");
			
						
			/*Thread.sleep(7000);
			driver.findElement(By.name("pricePerUnit")).click();
			//Clear the Price Option
			driver.findElement(By.name("pricePerUnit")).clear();
			//Enter the required price per unit
			driver.findElement(By.name("pricePerUnit")).sendKeys("123");*/
			
			//Thread.sleep(5000);
			//Click the Add Recipe Unit Option
			//driver.findElement(By.xpath(excel.getData(2, 167, 1))).click();
			
			Thread.sleep(2000); 
			//Clear the Equal field
			driver.findElement(By.xpath(excel.getData(2, 165, 1))).clear(); 
			//Enter the required number
			driver.findElement(By.xpath(excel.getData(2, 165, 1))).sendKeys("1000");
			
			Thread.sleep(2000);
			//Click the Select an Option
			driver.findElement(By.xpath(excel.getData(2, 496, 1))).click();
			//Enter the required unit
			driver.findElement(By.xpath(excel.getData(2, 497, 1))).sendKeys("liter");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 497, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			driver.findElement(By.xpath(excel.getData(2, 166, 1))).click();
			//Clear the Price Option
			driver.findElement(By.xpath(excel.getData(2, 166, 1))).clear();
			//Enter the required price per unit
			driver.findElement(By.xpath(excel.getData(2, 166, 1))).sendKeys("123");

			Thread.sleep(2000);
			//Click the Add Recipe Unit Option
			driver.findElement(By.xpath(excel.getData(2, 167, 1))).click();

			Thread.sleep(2000);
			//Click the Close Button
			driver.findElement(By.xpath(excel.getData(3, 1663, 1))).click();
			
			Thread.sleep(1000);
			//Click the Yes button in the popup
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			Thread.sleep(5000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 93, 1))).click();
			
			//Thread.sleep(2000);
			WebElement invItems=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,50);
						
			//Check whether the New Inventory saved or not
			if(wait.until(ExpectedConditions.visibilityOf(invItems)).getText().equalsIgnoreCase("Inventory item saved successfully!."))
			{
				test.log(LogStatus.PASS, "New Inventory Items Saved Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Inventory Items Saved Failed");
			}

			Thread.sleep(5000);wb.close();
			}
		
		@Test(enabled=false,priority=41)
		public void Inventory_Items_edit_Inventory_Items(WebDriver driver) throws Exception
		{
			    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		        FileInputStream fis = new FileInputStream(src);

		        XSSFWorkbook wb = new XSSFWorkbook(fis);

		        XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();

		        ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(10000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			Thread.sleep(4000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Inventory_Items_Name_Item"));
			Thread.sleep(4000);
			driver.findElement(By.cssSelector(excel.getData(3, 1293, 4))).click();

			Thread.sleep(4000);
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
			
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Inventory_Items_Name_Item")+"1");
			Thread.sleep(2000);

			Thread.sleep(6000);
			//Click the Category Option
			driver.findElement(By.xpath(excel.getData(3, 1295, 1))).click();
			Thread.sleep(2000);
			Thread.sleep(2000);
			//Enter the Required Category
			driver.findElement(By.xpath(excel.getData(3, 1296, 1))).sendKeys("FOOD");
			Thread.sleep(2000);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 1296, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(5000);
			//Click the Primary Storage Option
			driver.findElement(By.xpath(excel.getData(3, 1211, 1))).click();
			Thread.sleep(3000);
			//Enter the Required Storage
			driver.findElement(By.xpath(excel.getData(3, 1212, 1))).sendKeys("Freezer");
			Thread.sleep(2000);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 1212, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(5000);
			//Click the secondary storage field
			driver.findElement(By.xpath(excel.getData(3, 1213, 1))).click();
			//Enter the storage location
			driver.findElement(By.xpath(excel.getData(3, 1214, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1214, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000); 
			//Click the first vendor 
		/*	driver.findElement(By.xpath(excel.getData(2, 156, 1))).click();
			//Clear the Barcode option
			driver.findElement(By.name(excel.getData(2, 156, 1))).clear();
			//Enter the new Barcode
			driver.findElement(By.name(excel.getData(2, 156, 1))).sendKeys(Utility.getProperty("Inventory_Sku_OR_Bar_Code")+"1");
			
		Thread.sleep(3000);
			//Click the second vendor
			driver.findElement(By.xpath(excel.getData(3, 1297, 1))).click();
			//Clear the Barcode option
			driver.findElement(By.xpath(excel.getData(3, 1298, 1))).clear();
			//Enter the new Barcode
			driver.findElement(By.xpath(excel.getData(3, 1298, 1))).sendKeys(Utility.getProperty("Inventory_Sku_OR_Bar_Code")+"NEW1");
				
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		*/

			Thread.sleep(2000);
			//Clear the Bar code option
			driver.findElement(By.xpath(excel.getData(2, 156, 1))).clear();
			//Enter the required Bar Code
			driver.findElement(By.xpath(excel.getData(2, 156, 1))).sendKeys(Utility.getProperty("Inventory_Sku_OR_Bar_Code")+"1");

			
			Thread.sleep(2000);
			//Click the Select an Option
			driver.findElement(By.xpath(excel.getData(2, 496, 1))).click(); 
			//Enter the required unit
			driver.findElement(By.xpath(excel.getData(2, 497, 1))).sendKeys("Liter");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 497, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000); 
			//Clear the Equal field
			driver.findElement(By.xpath(excel.getData(2, 165, 1))).clear(); 
			//Enter the required number
			driver.findElement(By.xpath(excel.getData(2, 165, 1))).sendKeys("2000");
			 
			 
			Thread.sleep(2000);
			driver.findElement(By.xpath(excel.getData(2, 166, 1))).click();
			//Clear the Price Option
			driver.findElement(By.xpath(excel.getData(2, 166, 1))).clear();
			//Enter the required price per unit
			driver.findElement(By.xpath(excel.getData(2, 166, 1))).sendKeys("240");


			
			Thread.sleep(4000); 
			//Click the update 
			driver.findElement(By.xpath(excel.getData(3, 405, 1))).click();
			
			WebElement invItems=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,150);
						
			//Check whether the Inventory Item Updated or not
			if(wait.until(ExpectedConditions.visibilityOf(invItems)).getText().equalsIgnoreCase("Inventory item updated."))
			{
				test.log(LogStatus.PASS, "New Inventory Items updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Inventory Items updated Failed");
			}

			Thread.sleep(5000);wb.close();
		

		}
		
		@Test(enabled=false,priority=42)
		public void Inventory_Items_delete(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		        FileInputStream fis = new FileInputStream(src);
 
		        XSSFWorkbook wb = new XSSFWorkbook(fis);

		        XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();

		        ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			Thread.sleep(1000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Inventory_Items_Name_Item")+"1");
			
			Thread.sleep(4000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).click();
		  	
			Thread.sleep(2000);
			//Click the yes button			 
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click(); 
			try
			{
			//Thread.sleep(50000); 
				WebElement el=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
				WebDriverWait wait=new WebDriverWait(driver,60);
			//Check whether the Inventory Deleted or not
			if(wait.until(ExpectedConditions.visibilityOf(el)).getText().equalsIgnoreCase("Inventory item inactivated."))
			{
				test.log(LogStatus.PASS, "New Inventory Items deleted Sucessfully");
			}
			}
			catch(Exception e)
			{
				test.log(LogStatus.INFO, "New Inventory Items deleted Failed");
			}

			Thread.sleep(30000);
			//Click the Active Button 
			driver.findElement(By.xpath(excel.getData(3, 144, 1))).click();
			
			Thread.sleep(10000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			Thread.sleep(1000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Inventory_Items_Name_Item")+"1");
		
			
			Thread.sleep(3000); 
			//Click the success button
			driver.findElement(By.xpath(excel.getData(3, 1716, 1))).click();
			
			Thread.sleep(2000);
			//Click the Yes button in the popup
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			Thread.sleep(3000);
			//Check the Inventory Items activated or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Inventory item activated."))
			{
				test.log(LogStatus.PASS, "Inventory Items is activated Successfully");
			}
			else 
			{
				test.log(LogStatus.FAIL, "Inventory Items is activated Failed");
			}
			
			Thread.sleep(4000);wb.close();
		}

		@Test(enabled=false,priority=43)
		public void Inventory_Items_closeButton(WebDriver driver) throws Exception
		{
			    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		        FileInputStream fis = new FileInputStream(src);

		        XSSFWorkbook wb = new XSSFWorkbook(fis);
 
		        XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();

		        ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(3000);
			//Click on the Add Inventory Items option
			driver.findElement(By.id("inventoryItem")).click();
			Thread.sleep(3000); 
			
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
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Inventory_Items_Name_Item"));
			Thread.sleep(2000);
				 
			//Click the Cancel button 
			driver.findElement(By.xpath(excel.getData(3, 130, 1))).click();
			
			Thread.sleep(3000);
			//Check whether the new Inventory Items canceled or not
			if(driver.findElement(By.id("inventoryItem")).isDisplayed())
			{
				test.log(LogStatus.PASS, "New Inventory Items Canceled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Inventory Items Canceled Failed");
			}

			Thread.sleep(5000);wb.close();
		}
				
		@Test(enabled=false,priority=44)
		public void Inventory_Items_verifyActive_InActiveButton(WebDriver driver) throws Exception
		{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

        FileInputStream fis = new FileInputStream(src);

        XSSFWorkbook wb = new XSSFWorkbook(fis);

        XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();

        ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			
			Thread.sleep(5000);
			//Click Active or In Active Button 
			driver.findElement(By.xpath(excel.getData(3, 144, 1))).click();

			Thread.sleep(5000);
			//Check the Inactive page is loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1716, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Deleted categories are here, we are in InActive Page");
				Thread.sleep(10000);
				//Click Active or In Active Button
				driver.findElement(By.xpath(excel.getData(3, 144, 1))).click();

			}
			else if(driver.findElement(By.cssSelector(excel.getData(3, 244, 4))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Deleted Inventory Items are not here, we are in Active Page");
			}
			Thread.sleep(5000);
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);//watchTutorial(driver);
			Thread.sleep(5000);
			wb.close();
			}
			}
		@Test(priority=3, enabled=false)
		public void watchTutorial(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(2000);
			//Click the Watch Tutorial Option
			driver.findElement(By.xpath(excel.getData(3, 47, 1))).click();
			WebElement iframe = driver.findElement(By.xpath(excel.getData(3, 48, 1)));
			driver.switchTo().frame(iframe);
			Thread.sleep(3500);
			try
			{
				if(driver.findElement(By.xpath(excel.getData(3, 49, 1))).isDisplayed()&&driver.findElement(By.xpath("//button/div[.='Share']")).isDisplayed())
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
			driver.findElement(By.xpath(excel.getData(3, 50, 1))).click();wb.close();
		}
}
