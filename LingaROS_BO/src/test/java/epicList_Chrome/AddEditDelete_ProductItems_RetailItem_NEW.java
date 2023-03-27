	package epicList_Chrome;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
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

public class AddEditDelete_ProductItems_RetailItem_NEW {
	
	public WebDriver driver;
		
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete_Product_Retail_Items");

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
			Thread.sleep(10000);
			//SendMail_Product_Items.snedMailWithAttachment();
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
			
		/*@Test(priority=500)
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
			if(driver.findElement(By.xpath("//b[.='User Login']")).getText().equalsIgnoreCase("User Login"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully for Dealer App");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed for Dealer App");
			}
			Thread.sleep(3000);
			//Close the Browser_Account_Level_User
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
			if(driver.findElement(By.xpath("//div[@id='content']/div/div/h2")).getText().equalsIgnoreCase("Sign in"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully LingaPOs");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed LingaPos");
			}
			Thread.sleep(3000);
			//Close the Browser_Account_Level_User
			driver.close();
		}
		}*/
	
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
			Retail_Item_Open_Retail_Item_Page(driver);
			Retail_Item_Refresh_Page(driver);
			Retail_Item_Pagination(driver);  
		    Retail_Item_add_Retail_Item(driver);
			Retail_Item_Edit_Retail_Item(driver);
			Retail_Item_Delete_Retail_Item(driver);
		    Retail_Item_Add_RetailItem_By_Copy_RetailItem(driver);
			Retail_Item_cancelRetailItemByCopyRetailItem_Inventory_Details(driver);
			Retail_Item_cancelRetailItemByCopyRetailItem_MenuDetails(driver);
			Retail_Item_cancelRetailItemByCopyRetailItem_Childs(driver);
			Retail_Item_saveAndContinueRetailItemByCopyRetailItem(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=151)
		public void Retail_Item_Open_Retail_Item_Page(WebDriver driver) throws Exception
		{
	/*		//Click the Products/Items option
			driver.findElement(By.xpath("//span[.='Products/Items']")).click();
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[3]/ul/li[10]/a/span"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
	        //Click the Products/Items Option		
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[3]/ul/li[10]/a/span")).click();
	*/		
			
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"menus");
			
			
		try
		{
			
			Thread.sleep(8000);
			//Check Menu items page opened or not	
			if(driver.findElement(By.xpath(excel.getData(3, 2178, 1))).getText().equalsIgnoreCase("Menu Items"))
			{
				test.log(LogStatus.PASS, "Menu Items page loaded Successfully");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.PASS,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Enabled without POS Login - Menu Items page loaded Failed");
				
				String scnShot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s1="data:image/png;base64,"+scnShot1;
				test.log(LogStatus.FAIL,test.addScreenCapture(s1));
			}
			}
			
			catch(Exception e) 
		{
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.FAIL,test.addScreenCapture(s));
		}
		}
		
		@Test(enabled=false,priority=152)
		public void Retail_Item_Refresh_Page(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(4, 3, 1))).click();
			
			Thread.sleep(6000);
			//Check Menu Items page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 2178, 1))).getText().equalsIgnoreCase("Menu Items"))
			//if(driver.findElement(By.xpath("//i[@class='fa fa-spinner fa-spin fa-md']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Menu Items page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Menu Items page refreshed Failed");
				
			//	Utility us=new Utility();
			//	us.capture(driver);
				
			//	test.log(LogStatus.INFO, test.addScreenCapture(us.capture(driver)));
			}
		}
		
		@Test(priority=153,enabled=false)
		public void Retail_Item_Pagination(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			Thread.sleep(1000);
			
			//Click the Pagination option as 10
			driver.findElement(By.xpath(excel.getData(3, 1806, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 10");
			//Create the web element for menu item Table
			List<WebElement> results = driver.findElements(By.xpath(excel.getData(3, 2179, 1)));
			for (WebElement result : results){						
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(4, 13, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(8000);
			
			//Click the Pagination option as 15
			driver.findElement(By.xpath(excel.getData(3, 1808, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 15");
			//Create the web element for menu item Table
			List<WebElement> results1 = driver.findElements(By.xpath(excel.getData(3, 2179, 1)));
			for (WebElement result : results1){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(4, 13, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(8000);
			
			//Click the Pagination option as 20
			driver.findElement(By.xpath(excel.getData(3, 1809, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 20");
			//Create the web element for menu item Table
			List<WebElement> results2 = driver.findElements(By.xpath(excel.getData(3, 2179, 1)));
			for (WebElement result : results2){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(4, 13, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(8000);
			
			//Click the Pagination option as 5
			driver.findElement(By.xpath(excel.getData(3, 1810, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 5");
			//Create the web element for menu item Table
			List<WebElement> results3 = driver.findElements(By.xpath(excel.getData(3, 2179, 1)));
			for (WebElement result : results3){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(4, 13, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(8000);
		}
		
		@Test(priority=154,enabled=false)
		public void Retail_Item_add_Retail_Item(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Click on the Add Retail Item option
			driver.findElement(By.xpath(excel.getData(4, 47, 1))).click();
			Thread.sleep(3000);
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(4, 48, 1))).getText().equalsIgnoreCase("NEW RETAIL ITEM"))
			{
				test.log(LogStatus.PASS, "New Retail Items form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Retail Items form loaded Failed");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
					
			
			Thread.sleep(3000);
			//Clear the Name field		 
			driver.findElement(By.xpath(excel.getData(3, 2180, 1))).clear();
			//Enter the Name
			driver.findElement(By.xpath(excel.getData(3, 2180, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name"));
			Thread.sleep(2000);
			//Clear the Secondary Name field
			driver.findElement(By.xpath(excel.getData(3, 2181, 1))).clear();
			//Enter the Secondary Name
			driver.findElement(By.xpath(excel.getData(3, 2181, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_SecName"));
			Thread.sleep(1000);
			
			//Select the required Level
			Select level = new Select(driver.findElement(By.xpath(excel.getData(4, 51, 1))));
			level.selectByVisibleText("Category");
			
			//Click the Category option
			driver.findElement(By.xpath(excel.getData(3, 2182, 1))).click();
			//Enter the required Category
			driver.findElement(By.xpath(excel.getData(3, 2183, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2183, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Clear the SKU code
			driver.findElement(By.xpath(excel.getData(3, 2184, 1))).clear();
			//Enter the SKU code
			driver.findElement(By.xpath(excel.getData(3, 2184, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_SKUcode"));
			
			//Click the Add SKU button
			driver.findElement(By.xpath(excel.getData(3, 2185, 1))).click();
			
			Thread.sleep(2000);
			//Clear the SKU code of newly added one
			driver.findElement(By.xpath(excel.getData(3, 2186, 1))).clear();
			//Enter the SKU code of newly added one
			driver.findElement(By.xpath(excel.getData(3, 2186, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_SKUcode")+"new");		
			
			Thread.sleep(2000);
			//Click the Add SKU button
			driver.findElement(By.xpath(excel.getData(3, 2185, 1))).click();
		/*	List<WebElement> rows = driver.findElements(By.xpath(excel.getData(4, 56, 1)));
			rows.size();
			int row = rows.size() + 3;*/
			//Click the Close button of newly added button of SKU code
			driver.findElement(By.xpath("//label[contains(.,'Sku Codes')]/../div/div[5]/ng-form/div/div/a/i")).click();
			//Switch the control to the alert pouup
			Alert x = driver.switchTo().alert();
			//Capture the alert message									
			String alertMessage = driver.switchTo().alert().getText();
			//Print the alert message
			System.out.println(alertMessage);
			//Accept the alert
			x.accept();
			
			Thread.sleep(3000);
			
			//Click the Serving Size Level Option
			driver.findElement(By.xpath(excel.getData(3, 2190, 1))).click();
			//Enter the required Serving size Level
			driver.findElement(By.xpath(excel.getData(3, 2191, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2191, 1))).sendKeys(Keys.ENTER);
					
			//Clear the Price option
			driver.findElement(By.xpath(excel.getData(3, 2192, 1))).clear();
			//Enter the required price
			driver.findElement(By.xpath(excel.getData(3, 2192, 1))).sendKeys("125");
			
			Thread.sleep(1000);
			//Clear the margin percentage
			driver.findElement(By.name(excel.getData(4, 61, 3))).clear();
			//Enter the required percentage
			driver.findElement(By.name(excel.getData(4, 61, 3))).sendKeys("3");
			
			Thread.sleep(1000);
			//Clear the Cost Price
			driver.findElement(By.xpath(excel.getData(3, 2193, 1))).clear();
			//Enter the Cost Price
			driver.findElement(By.xpath(excel.getData(3, 2193, 1))).sendKeys("600");
			
			Thread.sleep(2000);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			
			Thread.sleep(4000);
			//Click the Add new Tax Button
			driver.findElement(By.xpath(excel.getData(3, 2187, 1))).click();
			
			Thread.sleep(5000);
			//Check weather new tax form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 2208, 1))).getText().equalsIgnoreCase("New Tax"))
			{
				test.log(LogStatus.PASS, "New Tax Form Loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tax Form Loaded Fail");
				
				
			}
			Thread.sleep(1000);
			
			//Clear the new tax name field
			driver.findElement(By.xpath(excel.getData(3, 2188, 1))).clear();
			//Enter the required new tax Name field
			driver.findElement(By.xpath(excel.getData(3, 2188, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_TaxName"));
					
			//Enable or Disable the Default Tax button
			//driver.findElement(By.xpath("//span[contains(.,'Default Tax')]")).click();
			
			Thread.sleep(2000);
			//Enable or disable the Inclusive Tax button
			//driver.findElement(By.xpath("//span[contains(.,'Inclusive Tax')]")).click();
			
			Thread.sleep(1000);
			//Clear the percentage field
			driver.findElement(By.name(excel.getData(3, 92, 3))).clear();
			//Enter the required percentage
			driver.findElement(By.name(excel.getData(3, 92, 3))).sendKeys("5000");
			
			Thread.sleep(1000);
			//click the Save button
			driver.findElement(By.xpath(excel.getData(3, 2189, 1))).click();		
			Thread.sleep(10000);
	
			//Enable or disable the Do not show Button on POS
			driver.findElement(By.xpath(excel.getData(4, 68, 1))).click();
			
			Thread.sleep(1000);
			//Enable or disable the Hide MenuItem in Online Order
			driver.findElement(By.xpath(excel.getData(4, 69, 1))).click();
	
			Thread.sleep(3000);
			//Enable or Disable the EBT menu item option
			driver.findElement(By.xpath(excel.getData(4, 71, 1))).click();
			
			Thread.sleep(1000);
			//Click the any one display button from the given buttons
			driver.findElement(By.xpath(excel.getData(4, 71, 1))).click();
	
			Thread.sleep(5000);
			//Choose the image from the disk
			driver.findElement(By.xpath(excel.getData(4, 72, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_User_Img"));
			
			Thread.sleep(3000);
			//Click the Applicable Time Period option
			driver.findElement(By.xpath(excel.getData(4, 73, 1))).click();
			//Enter the required Time Period
			driver.findElement(By.xpath(excel.getData(4, 74, 1))).sendKeys("Always");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(4, 74, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(10000);
			//Click the Next button
			driver.findElement(By.cssSelector(excel.getData(4, 74, 4))).click();
			Thread.sleep(10000);
			//select the required level
			Select leve = new Select(driver.findElement(By.xpath(excel.getData(4, 51, 1))));
			leve.selectByVisibleText("Category");
			Thread.sleep(10000);
			//Click the Add new Inventory Category Button 
		    driver.findElement(By.xpath(excel.getData(3, 2194, 1))).click();
			Thread.sleep(5000);
			//Check weather new Inventory Category form loaded or not
			if(driver.findElement(By.xpath(excel.getData(4, 77, 1))).getText().equalsIgnoreCase("New Category"))
			{
				test.log(LogStatus.PASS, "New Inventory Category Form Loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Inventory Category Form Loaded Fail");
			}
			Thread.sleep(1000);
	
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
			//Enter the Category name
			driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Inventory_CategoryName"));
			
			//CLear the description field
			driver.findElement(By.xpath(excel.getData(3, 35, 1))).clear();
			//Enter the required description
			driver.findElement(By.xpath(excel.getData(3, 35, 1))).sendKeys("NewCategory Description");
			
			Thread.sleep(2000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 2189, 1))).click();
			
			Thread.sleep(5000);
			//Click the Add new Primary Storage Button
			driver.findElement(By.xpath(excel.getData(3, 2195, 1))).click();
										 
			Thread.sleep(5000);
			//Check weather new Primary Storage form loaded or not
			if(driver.findElement(By.xpath(excel.getData(4, 81, 1))).getText().equalsIgnoreCase("New Storage Location"))
			{
				test.log(LogStatus.PASS, "New Primary Storage Location Form Loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Primary Storage Location Form Loaded Fail");
			}
			Thread.sleep(1000);
	
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
			//Enter the name
			driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Storage_Name"));
			
			//CLear the description field
			driver.findElement(By.xpath(excel.getData(3, 35, 1))).clear();
			//Enter the required description
			driver.findElement(By.xpath(excel.getData(3, 35, 1))).sendKeys("New Storage Description");
			
			Thread.sleep(2000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 2189, 1))).click();
			
			Thread.sleep(5000);
			//Click the Secondary storage option
			driver.findElement(By.xpath(excel.getData(4, 82, 1))).click();
			Thread.sleep(1000);
			//Enter the required storage location
			driver.findElement(By.xpath(excel.getData(4, 83, 1))).sendKeys("Freezer");
			//Press the Enter key
			driver.findElement(By.xpath(excel.getData(4, 83, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			if(driver.findElement(By.xpath(excel.getData(3, 2209, 1))).isEnabled())
			{
				Thread.sleep(1000);
				//Enable the Calculate COGS on Cost Prise
				driver.findElement(By.xpath(excel.getData(3, 2209, 1))).click();
			}
			Thread.sleep(2000);
			//Click the Add new Vendor Button
			driver.findElement(By.xpath(excel.getData(3, 2196, 1))).click();
			
			Thread.sleep(5000);
			//Check weather new vendor form loaded or not
			if(driver.findElement(By.xpath(excel.getData(4, 87, 1))).getText().equalsIgnoreCase("New Vendor"))
			{
				test.log(LogStatus.PASS, "New Vendor Form Loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Vendor Form Loaded Fail");
			}
			Thread.sleep(1000);
	
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
			//Enter the Category name
			driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Vendor_Name"));
			
			Thread.sleep(2000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 2189, 1))).click();
	
			Thread.sleep(3000);
			//Clear the Brand Name
			driver.findElement(By.name(excel.getData(3, 2197, 3))).clear(); 
			//Enter the Brand Name
			driver.findElement(By.name(excel.getData(3, 2197, 3))).sendKeys("Retail_Brand");
			
			
			//Click the Selling Unit Option
			driver.findElement(By.xpath(excel.getData(4, 89, 1))).click();
			//Enter the required Selling Unit Option
			driver.findElement(By.xpath(excel.getData(4, 90, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(4, 90, 1))).sendKeys(Keys.ENTER);
			
			//Clear the Par Level
			driver.findElement(By.name(excel.getData(4, 90, 3))).clear();
			//Enter the Par Level
			driver.findElement(By.name(excel.getData(4, 90, 3))).sendKeys("4");
			
			//Clear the cost option
			//driver.findElement(By.name("price")).clear();
			//Enter the cost option
			//driver.findElement(By.name("price")).sendKeys("165");
			
			
			Thread.sleep(5000);
			//Press the Previous button
			driver.findElement(By.cssSelector(excel.getData(4, 91, 4))).click();
			
			Thread.sleep(3000);
			//Click the Next button
			driver.findElement(By.cssSelector(excel.getData(4, 74, 4))).click();
				
			Thread.sleep(5000);
			//Click the Save and Next button
			driver.findElement(By.xpath(excel.getData(4, 93, 1))).click();
	
			//Check Childs page is opened or not
			try
			{
				/*		//Click the Childs option
				driver.findElement(By.xpath("//span[.='Childs']")).click();*/
				
				Thread.sleep(20000);
			
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					//Click the Add button
					driver.findElement(By.xpath(excel.getData(3, 2077, 1))).click();
								
					Thread.sleep(8000);
					//Check weather new child form loaded or not
					if(driver.findElement(By.xpath("//h4[.='Add child']")).getText().equalsIgnoreCase("Add child"))
					{
						test.log(LogStatus.PASS, "Add child Form Loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Add child Form Loaded Fail");
					}
					Thread.sleep(1000);
					
					//Clear the name field
					driver.findElement(By.xpath("//input[@ng-model='inventoryItem.name']")).clear();
					//Enter the name field
					driver.findElement(By.xpath("//input[@ng-model='inventoryItem.name']")).sendKeys(Utility.getProperty("Product_Retail_Items_Child_Name"));
	
					//Clear the Secondary name field
					driver.findElement(By.xpath("//input[@ng-model='inventoryItem.localName']")).clear();
					//Enter the Secondary name field
					driver.findElement(By.xpath("//input[@ng-model='inventoryItem.localName']")).sendKeys("Sec_Name");
					
					//Clear the SKU code field
					driver.findElement(By.xpath("//input[@name='skuCode']")).clear();
					//Enter the SKU code field
					driver.findElement(By.xpath("//input[@name='skuCode']")).sendKeys(Utility.getProperty("Product_Retail_Items_Child_SKUCode"));
					
					//Click the new SKU code option
					driver.findElement(By.xpath("//form[@name='childForm']/div[1]/div/div/div[2]/div[1]/div[1]/label/a/i")).click();
					//Clear the SKU code field
					driver.findElement(By.xpath("//form[@name='childForm']/div[1]/div/div/div[2]/div[1]/div[1]/div/div[3]/ng-form/div/div/input")).clear();
					//Enter the SKU code field
					driver.findElement(By.xpath("//form[@name='childForm']/div[1]/div/div/div[2]/div[1]/div[1]/div/div[3]/ng-form/div/div/input")).sendKeys(Utility.getProperty("Product_Retail_Items_Child_SKUCode")+"new");
					
					//Click the Add SKU button
					driver.findElement(By.xpath("//form[@name='childForm']/div[1]/div/div/div[2]/div[1]/div[1]/label/a/i")).click();
					//Click the Close button of newly added button of SKU code
					driver.findElement(By.xpath("//form[@name='childForm']/div[1]/div/div/div[2]/div[1]/div[1]/div/div[4]/ng-form/div/div/a/i")).click();
					
					//Switch the control to the alert pouup
					Alert x1 = driver.switchTo().alert();
					//Capture the alert message									
					String alertMessage1 = driver.switchTo().alert().getText();
					//Print the alert message
					System.out.println(alertMessage1);
					//Accept the alert
					x1.accept();
					
					Thread.sleep(3000);
					
					//CLear the description
					driver.findElement(By.name("description")).clear();
					//Enter the description
					driver.findElement(By.name("description")).sendKeys("Description");
					
					//Clear the Selling ratio
					driver.findElement(By.name("sellingRatio")).clear();
					//Enter the Selling ratio
					driver.findElement(By.name("sellingRatio")).sendKeys("3");
					
					//Clear the Cost field
					//driver.findElement(By.name("cost")).clear();
					//Enter the cost
					//driver.findElement(By.name("cost")).sendKeys("185");
	
					//Enable or Disable the Override price option
					//driver.findElement(By.xpath("//span[contains(.,'Override price')]")).click();
					
					//Enable or disable the Do not show Button on POS
					driver.findElement(By.xpath("//span[contains(.,'Do not show Button on POS')]")).click();
					
					//Click the any one Display button
					driver.findElement(By.xpath("//div[@class='email-template-color color-swatch ng-scope'][4]")).click();
					
					Thread.sleep(5000);
					//Click the Select image from disk option
					driver.findElement(By.xpath("//input[@id='retailItemImage']")).sendKeys(Utility.getProperty("Child_Imagepath"));
					
					Thread.sleep(3000);    
					//Click the Selling Unit Option
					driver.findElement(By.xpath("//select[@name='inventoryUnit']/../div/a")).click();
					//Enter the required Selling Unit
					driver.findElement(By.xpath("//select[@name='inventoryUnit']/../div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
					//Press the Enter Key
					driver.findElement(By.xpath("//select[@name='inventoryUnit']/../div/div/div/input")).sendKeys(Keys.ENTER);
					
					Thread.sleep(3000);
					//Clear the Price Option
					//driver.findElement(By.name("price")).clear();
					//Enter the required price
					//driver.findElement(By.name("price")).sendKeys("225");
					
					Thread.sleep(3000);
					//Click the OK button
					driver.findElement(By.xpath("//form[@name='childForm']/div[2]/button")).click();
					Thread.sleep(3000);
					
					//Check whether the child saved or not
					if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Child item saved successfully"))
					{
						test.log(LogStatus.PASS, "New Child is saved Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "New Child is saved Failed");
					}
					Thread.sleep(3000);
			
			/*	//Click the Add button
				driver.findElement(By.xpath(excel.getData(3, 2077, 1))).click();
				
				Thread.sleep(2000);
				//Check weather new child form loaded or not
				if(driver.findElement(By.xpath("//h4[.='Add child']")).getText().equalsIgnoreCase("Add child"))
				{
					test.log(LogStatus.PASS, "Add child Form Loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Add child Form Loaded Fail");
				}
				Thread.sleep(1000);
				
				//Clear the name field
				driver.findElement(By.xpath("//input[@ng-model='inventoryItem.name']")).clear();
				//Enter the name field
				driver.findElement(By.xpath("//input[@ng-model='inventoryItem.name']")).sendKeys(Utility.getProperty("Product_Retail_Items_Child_Name")+"10");
	
				//Clear the Secondary name field
				driver.findElement(By.xpath("//input[@ng-model='inventoryItem.localName']")).clear();
				//Enter the Secondary name field
				driver.findElement(By.xpath("//input[@ng-model='inventoryItem.localName']")).sendKeys("Sec_Name");
				
				//Clear the SKU code field
				driver.findElement(By.xpath("//input[@name='skuCode']")).clear();
				//Enter the SKU code field
				driver.findElement(By.xpath("//input[@name='skuCode']")).sendKeys(Utility.getProperty("Product_Retail_Items_Child_SKUCode")+"12");
						
				
				Thread.sleep(3000);
				
				//CLear the description
				driver.findElement(By.name("description")).clear();
				//Enter the description
				driver.findElement(By.name("description")).sendKeys("Description");
				
				//Clear the Selling ratio
				driver.findElement(By.name("sellingRatio")).clear();
				//Enter the Selling ratio
				driver.findElement(By.name("sellingRatio")).sendKeys("3");
				
				//Clear the Cost field
				driver.findElement(By.name("cost")).clear();
				//Enter the cost
				driver.findElement(By.name("cost")).sendKeys("185");
	
				//Enable or Disable the Override price option
				//driver.findElement(By.xpath("//span[contains(.,'Override price')]")).click();
				
				//Enable or disable the Do not show Button on POS
				driver.findElement(By.xpath("//span[contains(.,'Do not show Button on POS')]")).click();
				
				//Click the any one Display button
				driver.findElement(By.xpath("//label[contains(.,'Display Button')]/../div/div/div[2]/div/div[8]")).click();
				
				Thread.sleep(5000);
				//Click the Select image from gallery option
				driver.findElement(By.xpath("//input[@id='retailItemImage']")).sendKeys(Utility.getProperty("Product_Retail_Items_User_Img"));
				
				Thread.sleep(15000);
				//Select the required image
			//	driver.findElement(By.xpath("//form[@name='imageform']/div[2]/div/div/div[3]/div[1]/a/img")).click();
				
				Thread.sleep(8000);
				//Click the Selling Unit Option
				driver.findElement(By.xpath("//select[@name='inventoryUnit']/../div/a")).click();
				//Enter the required Selling Unit
				driver.findElement(By.xpath("//select[@name='inventoryUnit']/../div/div/div/input")).sendKeys("Each");
				//Press the Enter Key
				driver.findElement(By.xpath("//select[@name='inventoryUnit']/../div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(3000);
				//Clear the Price Option
				driver.findElement(By.name("price")).clear();
				//Enter the required price
				driver.findElement(By.name("price")).sendKeys("225");
				
				Thread.sleep(3000);
				//Click the OK button
				driver.findElement(By.xpath("//form[@name='childForm']/div[2]/button")).click();
				Thread.sleep(3000);
				
				//Check whether the child saved or not
				if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Child item saved successfully"))
				{
					test.log(LogStatus.PASS, "New Child is saved Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "New Child is saved Failed");
				}*/
				Thread.sleep(3000);
				//Click the Unlink Option
				driver.findElement(By.cssSelector("i.fa.fa-chain-broken")).click();
				Thread.sleep(3000);
				
				//Check whether the new form loaded or not
				if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Menu item un-linked successfully"))
				{
					test.log(LogStatus.PASS, "New Menu Item is un-linked Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "New Menu Item is un-linked Failed");
				}
				Thread.sleep(3000);
				//Click the Link Item Option
				driver.findElement(By.xpath("//a[contains(.,'Link Item')]")).click();
				
				Thread.sleep(3000);
				//Click the menu option
				driver.findElement(By.xpath("//select[@name='menu']/../div/a")).click();
				Thread.sleep(2000);
				//Enter the required input
				driver.findElement(By.xpath("//select[@name='menu']/../div/div/div/input")).click();
				Thread.sleep(2000);
				//Press the Enter Key
				driver.findElement(By.xpath("//select[@name='menu']/../div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Click the Link Option
				driver.findElement(By.xpath("//form[@name='linkForm']/div[2]/button")).click();
				
				
				Thread.sleep(5000);
				//Click the SKU code field
				driver.findElement(By.xpath("//input[@name='skuCode']")).click();
				//Press the Backspace key
				driver.findElement(By.xpath("//input[@name='skuCode']")).sendKeys(Keys.BACK_SPACE);
				//Enter new code
				driver.findElement(By.xpath("//input[@name='skuCode']")).sendKeys("1");
	
				//Click the Selling Unit Option
				driver.findElement(By.xpath("//select[@name='inventoryUnit']/../div/a")).click();
				//Enter the required Selling Unit
				driver.findElement(By.xpath("//select[@name='inventoryUnit']/../div/div/div/input")).sendKeys("Each");
				//Press the Enter Key
				driver.findElement(By.xpath("//select[@name='inventoryUnit']/../div/div/div/input")).sendKeys(Keys.ENTER);
				Thread.sleep(3000);
				//Click the Update button
				driver.findElement(By.xpath("//form[@name='childForm']/div[2]/button")).click();
				
				Thread.sleep(1000);
				//Check whether the child is updated or not
				if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Child item updated successfully"))
				{
					test.log(LogStatus.PASS, "New Child is updated Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "New Child is updated Failed");
				}
				
				Thread.sleep(5000);
				
				//Click the Cancel button
				driver.findElement(By.xpath("//a[.='Cancel']")).click();
				Thread.sleep(8000);
			}
			catch(Exception e)
			{
				test.log(LogStatus.INFO, "Retail Item Create page opened when user click the Save & Next option");
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				Thread.sleep(2000);
				//Click the Cancel button
				driver.findElement(By.xpath("//a[.='Cancel']")).click();
				Thread.sleep(8000);
			}
		
			
		}
		
		@Test(priority=155,enabled=false)
		public void Retail_Item_Edit_Retail_Item(WebDriver driver) throws Exception
		{
			Thread.sleep(10000);
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"1");
			Thread.sleep(3000);driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(5000);
			//Click the Search button
			driver.findElement(By.xpath(excel.getData(3, 2118, 1))).click();
			
			Thread.sleep(5000);
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(4, 11, 4))).click();
		
			Thread.sleep(3000);
			//Clear the Name field		 
			driver.findElement(By.xpath(excel.getData(3, 2180, 1))).clear();
			//Enter the Name			 
			driver.findElement(By.xpath(excel.getData(3, 2180, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"1");
					
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,300)", "");
			Thread.sleep(3000);
			//Select the required level
			Select level = new Select(driver.findElement(By.xpath(excel.getData(4, 51, 1))));
			level.selectByVisibleText("Sub Category");
			
			//Click the Category option
			driver.findElement(By.xpath(excel.getData(3, 2182, 1))).click();
			//Enter the required category
			driver.findElement(By.xpath(excel.getData(3, 2183, 1))).sendKeys("Appetizers");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2183, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(8000);
			//Click the Sub Category option
			driver.findElement(By.xpath(excel.getData(3, 2198, 1))).click();
			//Enter the required Sub category
			driver.findElement(By.xpath(excel.getData(3, 2199, 1))).click();
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2199, 1))).sendKeys(Keys.ENTER);
			//System.out.println("Test 1");
			Thread.sleep(4000);
			//Click the Serving Size option
			driver.findElement(By.xpath(excel.getData(3, 2190, 1))).click();
			//Enter the required serving size level
			driver.findElement(By.xpath(excel.getData(3, 2191, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2191, 1))).sendKeys(Keys.ENTER);
			
			//Clear the price option
			driver.findElement(By.xpath(excel.getData(3, 2192, 1))).clear();
			//Enter the price
			driver.findElement(By.xpath(excel.getData(3, 2192, 1))).sendKeys("255");
			//System.out.println("Test 2");
		
			Thread.sleep(1000);
			//Clear the margin percentage
			driver.findElement(By.name(excel.getData(4, 61, 3))).clear();
			//Enter the required percentage
			driver.findElement(By.name(excel.getData(4, 61, 3))).sendKeys("500");
			
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Click the Include sub-category taxes
			driver.findElement(By.xpath(excel.getData(3, 2200, 1))).click();
			
			Thread.sleep(3000);
			//Click the taxes option
			driver.findElement(By.xpath(excel.getData(3, 2201, 1))).click();
			//Enter the required tax
			driver.findElement(By.xpath(excel.getData(3, 2202, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2202, 1))).sendKeys(Keys.ENTER);
			
			if(driver.findElement(By.xpath(excel.getData(4, 70, 1))).isEnabled())
			{
			Thread.sleep(3000);
			//Enable or disable the EBT menu item
			driver.findElement(By.xpath(excel.getData(4, 70, 1))).click();
			}
			
			//Click the Select the image from gallery option
	//	driver.findElement(By.xpath(excel.getData(4, 102, 1))).click();
			
			
			Thread.sleep(15000);
			//Click the required image
		//	driver.findElement(By.xpath(excel.getData(4, 103, 1))).click();
		
			driver.findElement(By.xpath("//input[@id='retailItemImage']")).sendKeys(Utility.getProperty("Product_Retail_Items_User_Img"));

			Thread.sleep(5000);
				
			//Click the Applicable Time Period field
			driver.findElement(By.xpath(excel.getData(4, 105, 1))).click();
			//Enter the required time option from the given item list
			driver.findElement(By.xpath(excel.getData(4, 106, 1))).sendKeys("Days of Week");
			//Press the Enter Key in the Applicable time period option field
			driver.findElement(By.xpath(excel.getData(4, 106, 1))).sendKeys(Keys.ENTER);
			
			//Click the Days of Week Option
			driver.findElement(By.xpath(excel.getData(4, 107, 1))).click();
			//Enter the required DAY for Searching
			driver.findElement(By.xpath(excel.getData(4, 108, 1))).sendKeys("WEDNESDAY");
			//Press the Enter key after enter the keyword
			driver.findElement(By.xpath(excel.getData(4, 108, 1))).sendKeys(Keys.ENTER);
	
			Thread.sleep(2000);
			//Enable or Disable the Restriction Time option
			driver.findElement(By.xpath(excel.getData(4, 109, 1))).click();
			
			Thread.sleep(2000);
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath(excel.getData(4, 110, 1))).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath(excel.getData(4, 111, 1))).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath(excel.getData(4, 110, 1))).click();
			}
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			Thread.sleep(5000);
	
			Thread.sleep(3000);
			//Click the update and publish button
			driver.findElement(By.xpath(excel.getData(4, 112, 1))).click();
		
			try
			{
				Thread.sleep(3000);
			//Check whether the Retail Item updated successfully or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Retail item updated and published successfully"))
			{
				test.log(LogStatus.PASS, "New Retail item updated and Published successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Retail Item is updated and Published Failed");
			}
			}
			catch(Exception e) {}
			Thread.sleep(8000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name"));
			Thread.sleep(3000);
			
			//Click the Search button
			driver.findElement(By.xpath(excel.getData(3, 2118, 1))).click();
			
			Thread.sleep(3000);
			List<WebElement> ds = driver.findElements(By.xpath(excel.getData(4, 113, 1)));
			try
			{
				if(driver.findElement(By.xpath("//td[contains(.,'"+Utility.getProperty("Product_Retail_Items_Name")+" ')]")).isDisplayed() && ds.size()==2)
				{
					test.log(LogStatus.FAIL, "Old Retail Item is found, that edited retail item is created as new menu item");
				}
			}
			catch(Exception fd)
			{}
		}
			
		@Test(priority=156,enabled=false)
		public void Retail_Item_Delete_Retail_Item(WebDriver driver) throws Exception
		{
			Thread.sleep(13000);
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"11");
			Thread.sleep(3000);driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			//Click the Search button
			driver.findElement(By.xpath(excel.getData(3, 2118, 1))).click();
			Thread.sleep(8000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(4, 13, 4))).click();
			//Click the Yes button in the popup
			driver.findElement(By.linkText("Yes")).click();
			Thread.sleep(3000);
			//Check the menu item deleted or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Menu item inactivated successfully"))
			{
				test.log(LogStatus.PASS, "New Retail Item is deleted successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Retail Item is deleted Failed");
			}
			Thread.sleep(4000);
			
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(3, 44, 1))).click();
			Thread.sleep(5000);
			
			//Click the success button
			driver.findElement(By.cssSelector(excel.getData(4, 33, 4))).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			driver.findElement(By.linkText("Yes")).click();
			Thread.sleep(3000);
			
			//Check the menu item activated or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Menu item activated successfully"))
			{
				test.log(LogStatus.PASS, "Retail Item is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Retail Item is activated Failed");
			}
			
			Thread.sleep(3000);
			
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(3, 44, 1))).click();
			Thread.sleep(3000);
		
		}
			
		@Test(enabled=false,priority=157)
		public void Retail_Item_Add_RetailItem_By_Copy_RetailItem(WebDriver driver) throws Exception
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//Click on the Add Retail Item option
			driver.findElement(By.xpath(excel.getData(4, 47, 1))).click();
			Thread.sleep(3000);
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(4, 48, 1))).getText().equalsIgnoreCase("NEW RETAIL ITEM"))
			{
				test.log(LogStatus.PASS, "New Retail Items form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Retail Items form loaded Failed");
			}
			
			//Click the Retail Item field
			driver.findElement(By.xpath(excel.getData(3, 2203, 1))).click();
			//Enter the REquired retail Item
			driver.findElement(By.xpath(excel.getData(3, 2204, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"1");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2204, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			//Press the Copy button
			driver.findElement(By.xpath(excel.getData(3, 2205, 1))).click();
			
			Thread.sleep(2000);
			//Click the Clear Option  
			driver.findElement(By.xpath(excel.getData(3, 2206, 1))).click();
			
			//Click the Menu Item field
			driver.findElement(By.xpath(excel.getData(3, 2203, 1))).click();
			//Enter the REquired menu Item
			driver.findElement(By.xpath(excel.getData(3, 2204, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"1");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2204, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			//Press the Copy button
			driver.findElement(By.xpath(excel.getData(3, 2205, 1))).click();
		
			//Clear the Name field
			driver.findElement(By.xpath(excel.getData(3, 2180, 1))).clear();
			//Enter the Name
			driver.findElement(By.xpath(excel.getData(3, 2180, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"10");
			
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			
			//Select the required level
			Select level = new Select(driver.findElement(By.xpath(excel.getData(4, 51, 1))));
			level.selectByVisibleText("Category");
			
			//Click the Category option
			driver.findElement(By.xpath(excel.getData(3, 2182, 1))).click();
			//Enter the required Category
			driver.findElement(By.xpath(excel.getData(3, 2183, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the enter button
			driver.findElement(By.xpath(excel.getData(3, 2183, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			//Clear the PLU CODE
			driver.findElement(By.xpath(excel.getData(3, 2184, 1))).clear();
			//Enter the required PLU CODE
			driver.findElement(By.xpath(excel.getData(3, 2184, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_SKUcode")+"2");
				
			//Click the Serving Size level option
			driver.findElement(By.xpath(excel.getData(3, 2190, 1))).click();
			//Enter the required serving size level
			driver.findElement(By.xpath(excel.getData(3, 2191, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2191, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Clear the Price field
			driver.findElement(By.xpath(excel.getData(3, 2192, 1))).clear();
			//Enter the required price
			driver.findElement(By.xpath(excel.getData(3, 2192, 1))).sendKeys("215");
		
			Thread.sleep(1000);
			//Clear the margin percentage
			driver.findElement(By.name(excel.getData(4, 61, 3))).clear();
			//Enter the required percentage
			driver.findElement(By.name(excel.getData(4, 61, 3))).sendKeys("300");
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		
			
			Thread.sleep(2000);
			//Enable or disable the Include Category Taxes option
			driver.findElement(By.xpath(excel.getData(3, 2210, 1))).click();
		
			Thread.sleep(3000);
			//Click the tax option
			driver.findElement(By.xpath(excel.getData(3, 2201, 1))).click();
			//Enter the required tax
			driver.findElement(By.xpath(excel.getData(3, 2202, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2202, 1))).sendKeys(Keys.ENTER);
			
			//Click the Applicable Time Period field
			driver.findElement(By.xpath(excel.getData(4, 105, 1))).click();
			//Enter the required time option from the given item list
			driver.findElement(By.xpath(excel.getData(4, 106, 1))).sendKeys("Days of Month");
			//Press the Enter Key in the Applicable time period option field
			driver.findElement(By.xpath(excel.getData(4, 106, 1))).sendKeys(Keys.ENTER);
			
			//Click the Required Date form the Calendar
			driver.findElement(By.xpath(excel.getData(3, 2207, 1))).click();
			Thread.sleep(2000);
			
			//Enable or Disable the Restriction Months option
			driver.findElement(By.xpath(excel.getData(4, 121, 1))).click();
			
			Thread.sleep(2000);
			//Click the months field
			driver.findElement(By.xpath(excel.getData(4, 122, 1))).click();
			//Enter the Required month
			driver.findElement(By.xpath(excel.getData(4, 123, 1))).sendKeys("MAY");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(4, 123, 1))).sendKeys(Keys.ENTER);
			
			//Enable or Disable the Restriction Time option
			driver.findElement(By.xpath(excel.getData(4, 109, 1))).click();
			
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath(excel.getData(4, 110, 1))).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath(excel.getData(4, 111, 1))).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath(excel.getData(4, 109, 1))).click();
			}
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			Thread.sleep(5000);
	
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(4, 30, 1))).click();
			Thread.sleep(3000);
			
			//Check whether the new Retail saved or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Retail item saved successfully"))
			{
				test.log(LogStatus.PASS, "New Retail Item is saved Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Retail Item is saved Failed");
			}
			Thread.sleep(8000);
		}
	
		@Test(enabled=false,priority=158)
		public void Retail_Item_cancelRetailItemByCopyRetailItem_Inventory_Details(WebDriver driver) throws Exception
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(8000);
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//Click on the Add Retail Item option
			driver.findElement(By.xpath(excel.getData(4, 47, 1))).click();
			Thread.sleep(3000);
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(4, 48, 1))).getText().equalsIgnoreCase("NEW RETAIL ITEM"))
			{
				test.log(LogStatus.PASS, "New Retail Items form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Retail Items form loaded Failed");
			}
				
			Thread.sleep(3000);
			//Click the Retail Item field
			driver.findElement(By.xpath(excel.getData(3, 2203, 1))).click();
			Thread.sleep(1500);
			//Enter the REquired retail Item
			driver.findElement(By.xpath(excel.getData(3, 2204, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"1");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2204, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			//Press the Copy button
			driver.findElement(By.xpath(excel.getData(3, 2205, 1))).click();
				
			//Clear the Name field
			driver.findElement(By.xpath(excel.getData(3, 2180, 1))).clear();
			//Enter the Name
			driver.findElement(By.xpath(excel.getData(3, 2180, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"10");
	
			
			  Thread.sleep(3000); //Clear the sku code
			  driver.findElement(By.xpath(excel.getData(3, 2184, 1))).clear();
			 
			  //Enter the SKU COde 
			  driver.findElement(By.xpath(excel.getData(3, 2184, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_SKUcode")+"31");
			 
			
			Thread.sleep(3000);
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			/*
			 * Thread.sleep(3000); //Click the next button
			 * driver.findElement(By.cssSelector(excel.getData(4, 74, 4))).click();
			 */
			
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(4, 124, 1))).click();
			Thread.sleep(3000);
			
			//Check whether the new retail item cancelled or not
			if(driver.findElement(By.xpath(excel.getData(4, 125, 1))).getText().equalsIgnoreCase("Menu items"))
			{
				test.log(LogStatus.PASS, "New Retail Item is cancelled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Retail Item is cancelled Failed");
			}
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=159)
		public void Retail_Item_cancelRetailItemByCopyRetailItem_MenuDetails(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(8000);
			//Click on the Add Retail Item option
			driver.findElement(By.xpath(excel.getData(4, 47, 1))).click();
			Thread.sleep(3000);
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(4, 48, 1))).getText().equalsIgnoreCase("NEW RETAIL ITEM"))
			{
				test.log(LogStatus.PASS, "New Retail Items form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Retail Items form loaded Failed");
			}
				
			Thread.sleep(3000);
			//Click the Retail Item field
			driver.findElement(By.xpath(excel.getData(3, 2203, 1))).click();
			Thread.sleep(1000);
			//Enter the REquired retail Item
			driver.findElement(By.xpath(excel.getData(3, 2204, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"1");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2204, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			//Press the Copy button
			driver.findElement(By.xpath(excel.getData(3, 2205, 1))).click();
				
			//Clear the Name field
			driver.findElement(By.xpath(excel.getData(3, 2180, 1))).clear();
			//Enter the Name
			driver.findElement(By.xpath(excel.getData(3, 2180, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"10");
			
			//WebElement wait = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(excel.getData(4, 123, 1))));
			Thread.sleep(3000);
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript("window.scrollBy(0,300)");
			 Thread.sleep(3000);
			 //Click the Cancel button
			driver.findElement(By.xpath(excel.getData(4, 124, 1))).click();
			Thread.sleep(3000);
			
			//Check whether the new menu item page loaded or not
			if(driver.findElement(By.xpath(excel.getData(4, 125, 1))).getText().equalsIgnoreCase("Menu items"))
			{
				test.log(LogStatus.PASS, "New Retail Item is cancelled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Retail Item is cancelled Failed");
			}
			Thread.sleep(3000);
		}
			
		@Test(enabled=false,priority=160)
		public void Retail_Item_cancelRetailItemByCopyRetailItem_Childs(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(8000);
			//Click on the Add Retail Item option
			driver.findElement(By.xpath(excel.getData(4, 47, 1))).click();
			Thread.sleep(3000);
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(4, 48, 1))).getText().equalsIgnoreCase("NEW RETAIL ITEM"))
			{
				test.log(LogStatus.PASS, "New Retail Items form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Retail Items form loaded Failed");
			}
			
			Thread.sleep(2000);
			//Click the Retail Item field
			driver.findElement(By.xpath(excel.getData(3, 2203, 1))).click();
			//Enter the REquired retail Item
			driver.findElement(By.xpath(excel.getData(3, 2204, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"1");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2204, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			//Press the Copy button
			driver.findElement(By.xpath(excel.getData(3, 2205, 1))).click();
				
			//Clear the Name field
			driver.findElement(By.xpath(excel.getData(3, 2180, 1))).clear();
			//Enter the Name
			driver.findElement(By.xpath(excel.getData(3, 2180, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"10");
	
			Thread.sleep(2000);
			//Clear the sku code
			driver.findElement(By.xpath(excel.getData(3, 2184, 1))).clear();Thread.sleep(2000);
			Thread.sleep(2000);
			//Enter the SKU COde
			driver.findElement(By.xpath(excel.getData(3, 2184, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_SKUcode")+"3e");
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			
			Thread.sleep(3000);
			//Click the next button
			driver.findElement(By.cssSelector(excel.getData(4, 74, 4))).click();
			
			/*
			 * Thread.sleep(3000); //Click the Childs Circle option
			 * driver.findElement(By.xpath(excel.getData(4, 125, 4))).click();
			 */
			
			Thread.sleep(2000);
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(4, 124, 1))).click();
			Thread.sleep(3000);
			
			//Check whether the new retail item cancelled or not
			if(driver.findElement(By.xpath(excel.getData(4, 125, 1))).getText().equalsIgnoreCase("Menu items"))
			{
				test.log(LogStatus.PASS, "New Retail Item is cancelled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Retail Item is cancelled Failed");
			}
			Thread.sleep(3000);
		}
			
		@Test(enabled=false,priority=161)
		public void Retail_Item_saveAndContinueRetailItemByCopyRetailItem(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Click on the Add Retail Item option
			driver.findElement(By.xpath(excel.getData(4, 47, 1))).click();
			Thread.sleep(3000);
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(4, 48, 1))).getText().equalsIgnoreCase("NEW RETAIL ITEM"))
			{
				test.log(LogStatus.PASS, "New Retail Items form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Retail Items form loaded Failed");
			}
			Thread.sleep(2000);
			//Click the Retail Item field
			driver.findElement(By.xpath(excel.getData(3, 2203, 1))).click();
			//Enter the REquired retail Item
			driver.findElement(By.xpath(excel.getData(3, 2204, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"1");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2204, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			//Press the Copy button
			driver.findElement(By.xpath(excel.getData(3, 2205, 1))).click();
				
			//Clear the Name field
			driver.findElement(By.xpath(excel.getData(3, 2180, 1))).clear();
			//Enter the Name
			driver.findElement(By.xpath(excel.getData(3, 2180, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"11");
	
			Thread.sleep(2000);
			//Clear the sku code
			driver.findElement(By.xpath(excel.getData(3, 2184, 1))).clear();
			Thread.sleep(2000);
			//Enter the SKU COde
			driver.findElement(By.xpath(excel.getData(3, 2184, 1))).sendKeys(Utility.getProperty("Product_Retail_Items_SKUcode")+"3");
			Thread.sleep(2000);
			
			//Click the Applicable Time Period field
			driver.findElement(By.xpath(excel.getData(4, 105, 1))).click();
			//Enter the required time option from the given item list
			driver.findElement(By.xpath(excel.getData(4, 106, 1))).sendKeys("Always");
			//Press the Enter Key in the Applicable time period option field
			driver.findElement(By.xpath(excel.getData(4, 106, 1))).sendKeys(Keys.ENTER);
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			
			Thread.sleep(6000);
			//Click the next button
			driver.findElement(By.cssSelector(excel.getData(4, 74, 4))).click();
			
			Thread.sleep(8000);
			//Click the Inventory Category option
			driver.findElement(By.xpath("//div[@id='inventoryCategory_chosen']/a")).click();
			//Enter the required inventory category
			driver.findElement(By.xpath("//div[@id='inventoryCategory_chosen']/div/div/input")).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='inventoryCategory_chosen']/div/div/input")).sendKeys(Keys.ENTER);
					
			Thread.sleep(3000);
			//Click the Primary Storage option
			driver.findElement(By.xpath("//div[@id='psl_chosen']/a")).click();
			//Enter the required primary storage
			driver.findElement(By.xpath("//div[@id='psl_chosen']/div/div/input")).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath("//div[@id='psl_chosen']/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the Secondary Storage option
			driver.findElement(By.xpath("//div[@id='ssl_chosen']/a")).click();
			//Enter the required Secondary storage
			driver.findElement(By.xpath("//div[@id='ssl_chosen']/div/div/input")).sendKeys("Freezer");
			//Press the Enter Key
			driver.findElement(By.xpath("//div[@id='ssl_chosen']/div/div/input")).sendKeys(Keys.ENTER);
	
			Thread.sleep(3000);
			//Click the Vendor option
			driver.findElement(By.xpath("//select[@name='vendor']/../div/a")).click();
			//Enter the required vendor option
			driver.findElement(By.xpath("//select[@name='vendor']/../div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter button
			driver.findElement(By.xpath("//select[@name='vendor']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
			//Click the Save And Continue button
			driver.findElement(By.xpath("//span[.='Save and Continue']")).click();
			Thread.sleep(3000);
			
			//Check whether the new retail item saved or not
			if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Retail item saved successfully"))
			{
				test.log(LogStatus.PASS, "New Retail Item is saved Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Retail Item is saved Failed");
			}
			Thread.sleep(10000);
			
			
			//Click the Retail Item field
			driver.findElement(By.xpath("//select[@ng-model='retailItem.id']/../div/a")).click();
			//Enter the REquired retail Item
			driver.findElement(By.xpath("//select[@ng-model='retailItem.id']/../div/div/div/input")).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"11");
			//Press the Enter Key
			driver.findElement(By.xpath("//select[@ng-model='retailItem.id']/../div/div/div/input")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			//Press the Copy button
			driver.findElement(By.xpath("//a[contains(.,'Copy')]")).click();
				
			//Clear the Name field
			driver.findElement(By.xpath("//input[@ng-model='menu.name']")).clear();
			//Enter the Name
			driver.findElement(By.xpath("//input[@ng-model='menu.name']")).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"12");
	
			Thread.sleep(3000);
			//Clear the sku code
			driver.findElement(By.name("skuCode")).clear();Thread.sleep(3000);
			//Enter the SKU COde
			driver.findElement(By.name("skuCode")).sendKeys(Utility.getProperty("Product_Retail_Items_SKUcode")+"4");
			
			Thread.sleep(3000);
			//Click the Applicable Time Period field
			driver.findElement(By.xpath("//label[.='Applicable Time Period']/../div/div/a")).click();
			//Enter the required time option from the given item list
			driver.findElement(By.xpath("//label[.='Applicable Time Period']/../div/div/div/div/input")).sendKeys("Specific date");
			//Press the Enter Key in the Applicable time period option field
			driver.findElement(By.xpath("//label[.='Applicable Time Period']/../div/div/div/div/input")).sendKeys(Keys.ENTER);
			
			//Clear the Date field
			driver.findElement(By.name("date")).clear();
			//Enter the required date
			driver.findElement(By.name("date")).sendKeys("15-May-2040");
			
			//Enable or Disable the Restriction Time option
			driver.findElement(By.xpath("//label/span[contains(.,'Restriction Time')]")).click();
			
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath("//label[contains(.,'Start Time')]/../div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//label[contains(.,'End Time')]/../div/table/tbody/tr[2]/td[6]/button")).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath("//label[contains(.,'Start Time')]/../div/table/tbody/tr[2]/td[6]/button")).click();
			}
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			
			
			//Click the save and continue button
			driver.findElement(By.xpath("//a//span[.='Save and Continue']")).click();
			Thread.sleep(3000);
			
			//Check whether the new retail item saved or not
			if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Retail item saved successfully"))
			{
				test.log(LogStatus.PASS, "New Retail Item is saved Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Retail Item is saved Failed");
			}
			Thread.sleep(10000);
			
			//Click the Retail Item field
			driver.findElement(By.xpath("//select[@ng-model='retailItem.id']/../div/a")).click();
			//Enter the REquired retail Item
			driver.findElement(By.xpath("//select[@ng-model='retailItem.id']/../div/div/div/input")).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"12");
			//Press the Enter Key
			driver.findElement(By.xpath("//select[@ng-model='retailItem.id']/../div/div/div/input")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			//Press the Copy button
			driver.findElement(By.xpath("//a[contains(.,'Copy')]")).click();
				
			Thread.sleep(3000);
			//Clear the Name field
			driver.findElement(By.xpath("//input[@ng-model='menu.name']")).clear();
			//Enter the Name
			driver.findElement(By.xpath("//input[@ng-model='menu.name']")).sendKeys(Utility.getProperty("Product_Retail_Items_Name")+"13");
			
			Thread.sleep(3000);
			//Clear the sku code
			driver.findElement(By.name("skuCode")).clear();
			//Enter the SKU COde
			driver.findElement(By.name("skuCode")).sendKeys(Utility.getProperty("Product_Retail_Items_SKUcode")+"5");
			
			Thread.sleep(3000);
			//Click the Applicable Time Period field
			driver.findElement(By.xpath("//label[.='Applicable Time Period']/../div/div/a")).click();
			//Enter the required time option from the given item list
			driver.findElement(By.xpath("//label[.='Applicable Time Period']/../div/div/div/div/input")).sendKeys("Start date time & end date time");
			//Press the Enter Key in the Applicable time period option field
			driver.findElement(By.xpath("//label[.='Applicable Time Period']/../div/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Clear the From date field
			driver.findElement(By.name("fromDate")).clear();
			//Enter the required from Date
			driver.findElement(By.name("fromDate")).sendKeys("25-May-2050");Thread.sleep(3000);
			//Clear the To Date field
			driver.findElement(By.name("endDate")).clear();
			//Enter the required To Date
			driver.findElement(By.name("endDate")).sendKeys("28-May-2050");
			
			//Enable or Disable the Restriction Months option
			driver.findElement(By.xpath("//label/span[contains(.,'Restriction Days')]")).click();
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);		
			
			Thread.sleep(2000);
			//Click the Days of a Week option
			driver.findElement(By.xpath("//label[contains(.,'DAYS OF WEEK')]/../div/div/ul")).click();
			//Enter the Required Date
			driver.findElement(By.xpath("//label[contains(.,'DAYS OF WEEK')]/../div/div/ul/li/input")).sendKeys("FRIDAY");
			Thread.sleep(1000);
			//Press the Enter Key
			driver.findElement(By.xpath("//label[contains(.,'DAYS OF WEEK')]/../div/div/ul/li/input")).sendKeys(Keys.ENTER);
	
			Thread.sleep(2000);
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath(excel.getData(4, 110, 1))).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath(excel.getData(4, 111, 1))).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath(excel.getData(4, 110, 1))).click();
			}
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			Thread.sleep(5000);
			//Click the next button
			driver.findElement(By.cssSelector(excel.getData(4, 74, 4))).click();
	
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(4, 30, 1))).click();
			Thread.sleep(3000);
			
			//Check whether the new Retail saved or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Retail item saved successfully"))
			{
				test.log(LogStatus.PASS, "New Retail Item is saved Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Retail Item is saved Failed");
			}
			Thread.sleep(8000);
						
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);
			//watchTutorial(driver);
			Thread.sleep(5000);}
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