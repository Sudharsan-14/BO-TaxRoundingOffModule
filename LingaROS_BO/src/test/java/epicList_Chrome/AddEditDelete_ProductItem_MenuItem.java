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

public class AddEditDelete_ProductItem_MenuItem {
	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete_Product Menu Items");

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
		//	SendMail.snedMailWithAttachment();    
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
			Browser a = new Browser();
			a.Logout(driver, test);
			}
		/*if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
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
			if(driver.findElement(By.xpath("//div[@id='x-content-band-1']/div/div[2]/h2")).getText().equalsIgnoreCase("Account Login"))
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
			Product_And_Item_method_openProductsItems(driver);
			Product_And_Item_method_refreshMenuItemPage(driver);
			Product_And_Item_method_pagination(driver);
			Product_And_Item_method_addMenuItem(driver);
			//Product_And_Item_method_editMenuItem(driver);
			Product_And_Item_method_deleteMenuItem(driver);
			Product_And_Item_method_addMenuItemByCopyMenuItem(driver);
			Product_And_Item_method_cancelMenuItemByCopyMenuItem_Basic(driver);
			Product_And_Item_method_cancelMenuItemByCopyMenuItem_Included_Modifiers(driver);
			Product_And_Item_method_cancelMenuItemByCopyMenuItem_Optional_Modifier_Groups(driver);
			Product_And_Item_method_cancelMenuItemByCopyMenuItem_Mandatory_Modifier(driver);
			Product_And_Item_method_saveMenuItemByCopyMenuItem(driver);
			Product_And_Item_method_saveAndContinueMenuItemByCopyMenuItem(driver);
			Thread.sleep(1500);
		}
	
	@Test(enabled=false,priority=2)
	public void Product_And_Item_method_openProductsItems(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(15000);
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
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"menus");
		Thread.sleep(8000);
		
		try
		{
		
		//Check Menu items page opened or not
		if(driver.findElement(By.xpath(excel.getData(5, 260, 1))).getText().equalsIgnoreCase("Menu items"))
		{
			test.log(LogStatus.PASS, "Menu Items page loaded Successfully");
			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Menu Items page loaded Failed");
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.FAIL,test.addScreenCapture(s));	
			String scnShot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s1="data:image/png;base64,"+scnShot1;
			test.log(LogStatus.INFO,test.addScreenCapture(s1));
		}
		}
		catch(Exception e)
		{
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.FAIL,test.addScreenCapture(s));
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=3)
	public void Product_And_Item_method_refreshMenuItemPage(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		driver.findElement(By.tagName(excel.getData(5, 	174, 5))).sendKeys(Keys.HOME);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		Thread.sleep(10000);
		//Click the refresh button
		driver.findElement(By.xpath(excel.getData(5, 261, 1))).click();
		Thread.sleep(5000);
		//Check Menu Items page opened or not
		if(driver.findElement(By.xpath(excel.getData(5, 262, 1))).getText().equalsIgnoreCase("Menu Items"))
		{
			test.log(LogStatus.PASS, "Menu Items page refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Menu Items page refreshed Failed");
		}
	}
	
	@Test(enabled=false,priority=3)
	public void Product_And_Item_method_pagination(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));				
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 7, 1))).clear();
		Thread.sleep(1000);
		
		//Click the Pagination option as 10
		driver.findElement(By.xpath(excel.getData(5, 264, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Product and Item(Menu Item)");
		//Create the web element for menu item Table
		List<WebElement> results = driver.findElements(By.xpath(excel.getData(5, 265, 1)));
		for (WebElement result : results){						
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(5, 51, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Product and Item(Menu Item)");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 15
		driver.findElement(By.xpath(excel.getData(5, 266, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Product and Item(Menu Item)");
		//Create the web element for menu item Table
		List<WebElement> results1 = driver.findElements(By.xpath(excel.getData(5, 265, 1)));
		for (WebElement result : results1){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(5, 51, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Product and Item(Menu Item)");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 20
		driver.findElement(By.xpath(excel.getData(5, 267, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for Product and Item(Menu Item)");
		//Create the web element for menu item Table
		List<WebElement> results2 = driver.findElements(By.xpath(excel.getData(5, 265, 1)));
		for (WebElement result : results2){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(5, 51, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Product and Item(Menu Item)");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 5
		driver.findElement(By.xpath(excel.getData(5, 268, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Product and Item(Menu Item)");
		//Create the web element for menu item Table
		List<WebElement> results3 = driver.findElements(By.xpath(excel.getData(5, 265, 1)));
		for (WebElement result : results3){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(5, 51, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Product and Item(Menu Item)");
		}
		
		 Thread.sleep(3000);
		  
	}
	
	@Test(enabled=false,priority=4)
	public void Product_And_Item_method_addMenuItem(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		for (int i = 0; i < 10; i++) {
			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		}
		Thread.sleep(3000);
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id(excel.getData(5, 269, 2))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 270, 1))).getText().equalsIgnoreCase("NEW MENU ITEM"))
		{
			test.log(LogStatus.PASS, "New Menu Items form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Items form loaded Failed");
		}
				
		
		Thread.sleep(3000);
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).sendKeys(Utility.getProperty("ProductsItems_Name"));
		Thread.sleep(2500);
		//Clear the Secondary Name field
		driver.findElement(By.xpath(excel.getData(5, 272, 1))).clear();
		//Enter the Secondary Name
		driver.findElement(By.xpath(excel.getData(5, 272, 1))).sendKeys(Utility.getProperty("ProductsItems_SecName"));
		Thread.sleep(1000);
		
		//Clear the Description field
		driver.findElement(By.name(excel.getData(5, 273, 3))).clear();
		//Enter the required description
		driver.findElement(By.name(excel.getData(5, 273, 3))).sendKeys("TESTING DESCRIPTION");
		
/*		//Clear the Chit Name field
		driver.findElement(By.name(excel.getData(5, 274, 3))).clear();
		//Enter the required Chit Name
		driver.findElement(By.name(excel.getData(5, 274, 3))).sendKeys("TEST CHIT NAME");*/
		
		//Clear the Kitchen Print Name field
		driver.findElement(By.name(excel.getData(5, 274, 3))).clear();
		//Enter the required Kitchen Print Name
		driver.findElement(By.name(excel.getData(5, 274, 3))).sendKeys("Kitchen Print");
		
		//Clear the PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).sendKeys(Utility.getProperty("ProductsItems_PLU_Code"));
	
		//Scroll the page
		JavascriptExecutor jse1 = (JavascriptExecutor)driver;
		jse1.executeScript("window.scrollBy(0,250)", "");
		
		//Create the Object
		Select level = new Select(driver.findElement(By.name(excel.getData(5, 379, 3))));
		level.selectByVisibleText("Category");
		
		//Scroll the page
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
	
		//Click the Category Field
		driver.findElement(By.xpath(excel.getData(5, 276, 1))).click();
		//Enter the required category
		driver.findElement(By.xpath(excel.getData(5, 277, 1))).sendKeys("Beer");
		//Press the Enter key
		driver.findElement(By.xpath(excel.getData(5, 277, 1))).sendKeys(Keys.ENTER);	
		
		Thread.sleep(3000);
		//Enable the Cut and Modify option
		driver.findElement(By.xpath(excel.getData(5, 278, 1))).click();Thread.sleep(5000);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);Thread.sleep(2500);
		//Enable the Cut and Modify option
		driver.findElement(By.xpath(excel.getData(5, 279, 1))).click();
		Thread.sleep(2500);
		//Enable the Is concventional option
		driver.findElement(By.xpath(excel.getData(5, 280,1))).click();Thread.sleep(5000);

		//Clear the Number of Slices option
		driver.findElement(By.name(excel.getData(5, 281, 3))).clear();
		//Enter the required input
		driver.findElement(By.name(excel.getData(5, 281, 3))).sendKeys("3");
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.END);
		Thread.sleep(2500);
		//Select the required measure type
		Select measureType = new Select(driver.findElement(By.name(excel.getData(5, 282, 3))));
		measureType.selectByVisibleText("Serving Size");
	
		Thread.sleep(2500);
		//Click the add Serving Size Level
		driver.findElement(By.xpath(excel.getData(5, 283, 1))).click();
		      
		Thread.sleep(1000);
		//Click the select an Option field
		driver.findElement(By.xpath(excel.getData(5, 284, 1))).click();
		//Enter the required Serving Size Level
		driver.findElement(By.xpath(excel.getData(5, 285, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 285, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Clear the Serial number field
		driver.findElement(By.name(excel.getData(5, 286, 3))).clear();
		//Enter the required serial number
		driver.findElement(By.name(excel.getData(5, 286, 3))).sendKeys("1");
		
		Thread.sleep(1000);
		//Clear the price field
		driver.findElement(By.xpath(excel.getData(5, 287, 1))).clear();
		//Enter the required price
		driver.findElement(By.xpath(excel.getData(5, 287, 1))).sendKeys("111");
		
		Thread.sleep(1000);
		//Click the add Serving Size Level
		/*driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[3]/td/a")).click();
		//Click the select an Option field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[3]/td[1]/ng-form/div/div/a")).click();
		//Enter the required Serving Size Level
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[3]/td[1]/ng-form/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter Key
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[3]/td[1]/ng-form/div/div/div/div/input")).sendKeys(Keys.ENTER);
*/
		Thread.sleep(1000);
		//Clear the Serial number field
		driver.findElement(By.name(excel.getData(5, 286, 3))).clear();
		//Enter the required serial number
		driver.findElement(By.name(excel.getData(5, 286, 3))).sendKeys("3");
		
		Thread.sleep(1000);
		//Clear the price field
		driver.findElement(By.xpath(excel.getData(5, 287, 1))).clear();
		//Enter the required price
		driver.findElement(By.xpath(excel.getData(5, 287, 1))).sendKeys("113");
		
		Thread.sleep(1000);
		//Click the add Serving Size Level
		driver.findElement(By.xpath(excel.getData(5, 283, 1))).click();
		//Click the select an Option field
		driver.findElement(By.xpath(excel.getData(5, 284, 1))).click();
		//Enter the required Serving Size Level
		driver.findElement(By.xpath(excel.getData(5, 285, 1))).sendKeys(Keys.ARROW_DOWN);Thread.sleep(1000);
		driver.findElement(By.xpath(excel.getData(5, 285, 1))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath(excel.getData(5, 285, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 285, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Clear the Serial number field
		driver.findElement(By.name(excel.getData(5, 286, 3))).clear();
		//Enter the required serial number
		driver.findElement(By.name(excel.getData(5, 286, 3))).sendKeys("4");
		
		Thread.sleep(1000);
		//Clear the price field
		driver.findElement(By.xpath(excel.getData(5, 287, 1))).clear();
		//Enter the required price
		driver.findElement(By.xpath(excel.getData(5, 287, 1))).sendKeys("114");
		
		Thread.sleep(1000);
		//Click the add Serving Size Level
		driver.findElement(By.xpath(excel.getData(5, 283, 1))).click();
		//Click the select an Option field
		driver.findElement(By.xpath(excel.getData(5, 284, 1))).click();
		//Enter the required Serving Size Level
		driver.findElement(By.xpath(excel.getData(5, 285, 1))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath(excel.getData(5, 285, 1))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath(excel.getData(5, 285, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 285, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Clear the Serial number field
		driver.findElement(By.name(excel.getData(5, 286, 3))).clear();
		//Enter the required serial number
		driver.findElement(By.name(excel.getData(5, 286, 3))).sendKeys("2");
		
		Thread.sleep(1000);
		//Clear the price field
		driver.findElement(By.xpath(excel.getData(5, 287, 1))).clear();
		//Enter the required price
		driver.findElement(By.xpath(excel.getData(5, 287, 1))).sendKeys("112");
		
		Thread.sleep(3000);
		for(int i = 1; i <= 4; i++){driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.ARROW_UP);}
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.ARROW_UP);Thread.sleep(3000);
		//Click the close button of first option
		driver.findElement(By.xpath(excel.getData(5, 288, 1))).click();
		
		//For adding new serving size level
		driver.findElement(By.xpath(excel.getData(5, 289, 1))).click();
		//Add serving size
		driver.findElement(By.xpath(excel.getData(5, 283, 1))).click();
		//Click the select an Option field
		driver.findElement(By.xpath(excel.getData(5, 284, 1))).click();
		//Enter the required Serving Size Level
		driver.findElement(By.xpath(excel.getData(5, 285, 1))).sendKeys("Each");
		driver.findElement(By.xpath(excel.getData(5, 285, 1))).sendKeys(Keys.ENTER);
		//Clear the Serial number field
				driver.findElement(By.name(excel.getData(5, 286, 3))).clear();
				//Enter the required serial number
				driver.findElement(By.name(excel.getData(5, 286, 3))).sendKeys("2");
				
				Thread.sleep(1000);
				//Clear the price field
				driver.findElement(By.xpath(excel.getData(5, 287, 1))).clear();
				//Enter the required price
				driver.findElement(By.xpath(excel.getData(5, 287, 1))).sendKeys("112");
				
				Thread.sleep(3000);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Enable or disable the Kitchen printer
		driver.findElement(By.xpath(excel.getData(5, 290, 1))).click();
		Thread.sleep(1500);
		//Enable or disable the Label printer
		driver.findElement(By.xpath(excel.getData(5, 291, 1))).click();
		Thread.sleep(1500);
		//Enable or disable the Restrict printer
		driver.findElement(By.xpath(excel.getData(5, 292, 1))).click();
		
		Thread.sleep(1500);
		//Click the Tax option
		driver.findElement(By.xpath(excel.getData(5, 293, 1))).click();
		//Enter the required tax
		driver.findElement(By.xpath(excel.getData(5, 294, 1))).click();
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 294, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Kitchen Printer option
		driver.findElement(By.xpath(excel.getData(5, 295, 1))).click();
		//Click the input field
		driver.findElement(By.xpath(excel.getData(5, 296, 1))).click();
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 296, 1))).sendKeys(Keys.ENTER);
try {
		Thread.sleep(1000);
		//Click the Label Printer option
				driver.findElement(By.xpath(excel.getData(5, 297, 1))).click();
				Thread.sleep(1000);
				//Click the input field
				driver.findElement(By.xpath(excel.getData(5, 298, 1))).click();
				Thread.sleep(1000);
				//Press Enter button
				driver.findElement(By.xpath(excel.getData(5, 298, 1))).sendKeys(Keys.ENTER);
}
catch(Exception d) {}
		Thread.sleep(1000);
		//Click the Restrict Service Types option
		driver.findElement(By.xpath(excel.getData(5, 299, 1))).click();
		//Enter the required service type
		driver.findElement(By.xpath(excel.getData(5, 300, 1))).sendKeys("BarTab");
		//Press Enter button
		driver.findElement(By.xpath(excel.getData(5, 300, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Enable or disable the Do not show Button on POS field
		
		
		
		//Enable or disable the Do not show Button on POS field
		driver.findElement(By.xpath(excel.getData(5, 301, 1))).click();

		Thread.sleep(1000);
		//Enable or disable the hide menu item in online order
		driver.findElement(By.xpath(excel.getData(5, 302, 1))).click();
		Thread.sleep(1000);
		//Enable or disable the EBT MenuItem field
		driver.findElement(By.xpath(excel.getData(5, 303, 1))).click();
		Thread.sleep(1000);
		//Click the required display button
		driver.findElement(By.xpath(excel.getData(5, 304, 1))).click();
		Thread.sleep(5000);
		//Choose the image from the disk
		driver.findElement(By.xpath(excel.getData(5, 305, 1))).sendKeys(Utility.getProperty("DisplayGroupImage1"));
		
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.ARROW_DOWN);Thread.sleep(3000);
		
		//Click the Applicable Time Period option
		driver.findElement(By.xpath(excel.getData(5, 306, 1))).click();
		//Enter the required Time Period
		driver.findElement(By.xpath(excel.getData(5, 307, 1))).sendKeys("Always");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 307, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(15000);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.END);
		Thread.sleep(2500);
		//Click the Next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
			
		Thread.sleep(10000);
		//Click the plus or add symbol
		driver.findElement(By.cssSelector(excel.getData(5, 309, 4))).click();
				
		Thread.sleep(2500);
		//Click the modifier group field
		driver.findElement(By.xpath(excel.getData(5, 310, 1))).click();
		//Enter the required modifier group
		driver.findElement(By.xpath(excel.getData(5, 312, 1))).click();
		//Press the Enter key
		driver.findElement(By.xpath(excel.getData(5, 312, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Click the modifier option
		driver.findElement(By.xpath(excel.getData(5, 315, 1))).click();
		//Enter the required modifier
		driver.findElement(By.xpath(excel.getData(5, 316, 1))).click();
		//Press the Enter key
		driver.findElement(By.xpath(excel.getData(5, 316, 1))).sendKeys(Keys.ENTER);	
		
		
		Thread.sleep(2500);
		//Click the Alternate Modifier group
		driver.findElement(By.xpath(excel.getData(5, 317, 1))).click();
		
		Thread.sleep(1000);
		//Select the alternate modifier group option
		driver.findElement(By.xpath(excel.getData(5, 318, 1))).click();
		//Enter the required modifier
		driver.findElement(By.xpath(excel.getData(5, 319, 1))).click();
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 319, 1))).sendKeys(Keys.ENTER);
		
		//Enable or Disable the Override prefix up-charges
		driver.findElement(By.xpath(excel.getData(5, 320, 1))).click();
		try {
		Thread.sleep(1000);
		//Clear the price of prefix amount
		driver.findElement(By.xpath(excel.getData(5, 321, 1))).clear();
		//Give the required amount
		driver.findElement(By.xpath(excel.getData(5, 321, 1))).sendKeys("120");}catch(Exception l) {}
		
		Thread.sleep(5000);
		//Click the plus or add symbol
		driver.findElement(By.cssSelector(excel.getData(5, 309, 4))).click();

		//Click the close button of Additional modifier group
		driver.findElement(By.xpath(excel.getData(5, 322, 1))).click();
		
		Thread.sleep(1000);
		//Click the Yes button in the popup
		Thread.sleep(1500);driver.findElement(By.xpath(excel.getData(5, 69, 1))).click();

		Thread.sleep(5000);
		//Click the plus or add symbol
		driver.findElement(By.cssSelector(excel.getData(5, 309, 4))).click();
		
		for(int i = 1; i <= 6; i++)
		{
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.ARROW_DOWN);
		}
		
		Thread.sleep(1000);
		//Click the another one modifier group
		driver.findElement(By.xpath(excel.getData(5, 323, 1))).click();
		//Enter the required modifier group name
		driver.findElement(By.xpath(excel.getData(5, 324, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 324, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the modifier option
		driver.findElement(By.xpath(excel.getData(5, 325, 1))).click();
		//Enter the required modifier name
		driver.findElement(By.xpath(excel.getData(5, 326, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 326, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2500);
		//Press the Previous button
		driver.findElement(By.cssSelector(excel.getData(5, 327, 4))).click();
		
		Thread.sleep(3000);
		//Click the Next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		Thread.sleep(3000);
		//Click the Next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		//Click the add Optional Modifier option
		driver.findElement(By.xpath(excel.getData(5, 311, 1))).click();
		
		Thread.sleep(2500);
		//Click the modifier group option
		driver.findElement(By.xpath(excel.getData(5, 310, 1))).click();
		//Enter the required modifier group option
		driver.findElement(By.xpath(excel.getData(5, 312, 1))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath(excel.getData(5, 312, 1))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath(excel.getData(5, 312, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 312, 1))).sendKeys(Keys.ENTER);
		
		//Check weather the Override price check box is enabled or not
		if(driver.findElement(By.xpath(excel.getData(5, 313, 1))).isEnabled())
		{
		//Click the Override price option
		driver.findElement(By.xpath(excel.getData(5, 313, 1))).click();
		}

		//Clear the Display Order option
		driver.findElement(By.name(excel.getData(5, 314, 3))).clear();
		//Enter the required number option
		driver.findElement(By.name(excel.getData(5, 314, 3))).sendKeys("3");
		
/*		//Clear the Price option
		driver.findElement(By.xpath("//td/div/input")).clear();
		//Enter the required price
		driver.findElement(By.xpath("//td/div/input")).sendKeys("250");*/
		
		//Click the Set Tiered Price Check box
		driver.findElement(By.xpath(excel.getData(5, 328, 1))).click();
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.ARROW_DOWN);
		}
		Thread.sleep(5000);
		//Click the Set Tiered option
		driver.findElement(By.xpath(excel.getData(5, 329, 1))).click();Thread.sleep(2500);
		
		for(int i = 1; i <= 5; i++)
		{
			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.ARROW_DOWN);
		}
		Thread.sleep(5000);
		//Click the Serving Size Level Option
		driver.findElement(By.xpath(excel.getData(5, 330, 1))).click();
		//Enter the required serving size level
		driver.findElement(By.xpath(excel.getData(5, 331, 1))).click();
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 331, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the set Tiered price option
		driver.findElement(By.xpath(excel.getData(5, 332, 1))).click();
		//click add Tiered Price
		//driver.findElement(By.xpath(excel.getData(5, 343, 1))).click();
		
		//Clear the Quantity option
		
		driver.findElement(By.xpath(excel.getData(5, 333, 1))).clear();
		//Give the required quantity
		driver.findElement(By.xpath(excel.getData(5, 333, 1))).sendKeys("1");
		
		Thread.sleep(2500);
		//Select the required option to set the price
		Select setPrice = new Select(driver.findElement(By.xpath(excel.getData(5, 334, 1))));
		setPrice.selectByVisibleText("Each");
		
		//Clear the price option
		driver.findElement(By.xpath(excel.getData(5, 335, 1))).clear();
		//Enter the required price
		driver.findElement(By.xpath(excel.getData(5, 335, 1))).sendKeys("350");
		
		Thread.sleep(1000);
		//Click the set Tiered price option
		driver.findElement(By.xpath(excel.getData(5, 332, 1))).click();
		//click add Tiered Price
				//driver.findElement(By.xpath(excel.getData(5, 343, 1))).click();
		//Clear the Quantity option
		driver.findElement(By.xpath(excel.getData(5, 336, 1))).clear();
		//Give the required quantity
		driver.findElement(By.xpath(excel.getData(5, 336, 1))).sendKeys("2");
		
		Thread.sleep(2500);
		//Select the required option to set the price
		Select setPrice1 = new Select(driver.findElement(By.xpath(excel.getData(5, 337, 1))));
		setPrice1.selectByVisibleText("All");
		
		//Clear the price option
		driver.findElement(By.xpath(excel.getData(5, 338, 1))).clear();
		//Enter the required price
		driver.findElement(By.xpath(excel.getData(5, 338, 1))).sendKeys("250");
		
		Thread.sleep(1000);
		//Click the Add Tiered price option
		driver.findElement(By.xpath(excel.getData(5, 332, 1))).click();

		Thread.sleep(1000);
		//Click the delete option of last added one
		driver.findElement(By.xpath(excel.getData(5, 339, 1))).click();
		
		//Click the add Set Tiered Price option
		driver.findElement(By.xpath(excel.getData(5, 329, 1))).click();
		
		Thread.sleep(1000);
		//Click the delete Tiered Price option
		driver.findElement(By.xpath(excel.getData(5, 340, 1))).click();
		
		//Click the add Set Tiered Price option
		driver.findElement(By.xpath(excel.getData(5, 329, 1))).click();

		Thread.sleep(2500);
		//Click the Select Serving Size Level Option
		driver.findElement(By.xpath(excel.getData(5, 341, 1))).click();
		//Enter the required serving size level
		driver.findElement(By.xpath(excel.getData(5, 342, 1))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath(excel.getData(5, 342, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 342, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Click the Add tiered price option
		driver.findElement(By.xpath(excel.getData(5, 343, 1))).click();
		
		//Clear the quantity field
		driver.findElement(By.xpath(excel.getData(5, 344, 1))).clear();
		//Enter the quantity
		driver.findElement(By.xpath(excel.getData(5, 344, 1))).sendKeys("4");
		
		Thread.sleep(2500);
		//Select the required option to set the price
		Select setPrice2 = new Select(driver.findElement(By.xpath(excel.getData(5, 345, 1))));
		setPrice2.selectByVisibleText("Each");
		
		//Clear the price field
		driver.findElement(By.xpath(excel.getData(5, 346, 1))).clear();
		//Enter the price
		driver.findElement(By.xpath(excel.getData(5, 346, 1))).sendKeys("212");
		Thread.sleep(1000);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.END);
		Thread.sleep(3000);
		
		Thread.sleep(2500);
		//Press the Previous button
		driver.findElement(By.cssSelector(excel.getData(5, 327, 4))).click();
		
		Thread.sleep(3000);
		//Click the Next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		Thread.sleep(3000);
		//Click the Next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		
		//////////////////////////////////////////////////////////////////////////////////
		//Click the Add Mandatory Modifier option
		
		
		driver.findElement(By.xpath(excel.getData(5, 347, 1))).click();
		////label[contains(.,'Modifier Group')]/../div/div/a
		//Click the Modifier Group option
		driver.findElement(By.xpath(excel.getData(5, 348, 1))).click();
		//Enter the required modifier
		driver.findElement(By.xpath(excel.getData(5, 349, 1))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath(excel.getData(5, 349, 1))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath(excel.getData(5, 349, 1))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath(excel.getData(5, 349, 1))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath(excel.getData(5, 349, 1))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath(excel.getData(5, 349, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter key

		driver.findElement(By.xpath(excel.getData(5, 349, 1))).sendKeys(Keys.ENTER);	
		
		//Check weather the Override price is enabled or not
		if(driver.findElement(By.xpath(excel.getData(5, 350, 1))).isEnabled())
		{
			Thread.sleep(1000);
			//Click the Override price option
			driver.findElement(By.xpath(excel.getData(5, 350, 1))).click();
			
			Thread.sleep(2500);
			//Clear the Price Option
			driver.findElement(By.xpath(excel.getData(5, 351, 1))).clear();
			//Enter the price
			driver.findElement(By.xpath(excel.getData(5, 351, 1))).sendKeys("150");

		}
		
	//	Clear the minimum Quantity field
		driver.findElement(By.name(excel.getData(5, 352, 3))).clear();
		//Enter the minimum Quantity
		driver.findElement(By.name(excel.getData(5, 352, 3))).sendKeys("5");
		
		//Clear the maximum Quantity field
		driver.findElement(By.name(excel.getData(5, 353, 3))).clear();
		//Enter the maximum Quantity
		driver.findElement(By.name(excel.getData(5, 353, 3))).sendKeys("6");
		
		//Clear the Display order field
		driver.findElement(By.name(excel.getData(5, 314, 3))).clear();
		//Enter the Display order
		driver.findElement(By.name(excel.getData(5, 314, 3))).sendKeys("2");
		
		//Click the add mandatory modifier field
				driver.findElement(By.xpath(excel.getData(5, 354, 1))).click();
				
				Thread.sleep(2500);
				//Delete the last added mandatory modifier field
				driver.findElement(By.xpath(excel.getData(5, 355, 1))).click();

		for(int i = 1;i <= 3;i++){driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.END);}
		Thread.sleep(3500);
		
	/*	//Click the Set Tiered price Check box
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[3]/div/div/div/input")).click();
		
		//Click the Add Set Tiered price
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/ul/li/a")).click();
		
		Thread.sleep(1000);
		//Click the Serving Size Level field
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[1]/div[1]/div/div[1]/div/a")).click();
		//Enter the required SErving Size Level
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[1]/div[1]/div/div[1]/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[1]/div[1]/div/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Add tiered price option
		driver.findElement(By.xpath(excel.getData(5, 289, 1))).click();
		
		//Clear the Quantity field
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[2]/ng-form/div/input")).clear();
		//Enter the Quantity field
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[2]/ng-form/div/input")).sendKeys("2");
		
		Thread.sleep(1000);
		//Select the required Price option
		Select setPrice3 = new Select(driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[3]/ng-form/div/select")));
		setPrice3.selectByVisibleText("Each");
		
		//Clear the price field
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[4]/ng-form/div/input")).clear();
		//Enter the required price
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[4]/ng-form/div/input")).sendKeys("2165");
		
		Thread.sleep(1000);
		//Click the Add tiered price option
		driver.findElement(By.xpath("//tbody/tr[2]/td[1]/a[1]")).click();
		//
		//
		//Clear the Quantity field
		driver.findElement(By.xpath(excel.getData(5, 287, 1))).clear();
		//Enter the Quantity field
		driver.findElement(By.xpath(excel.getData(5, 287, 1))).sendKeys("35");
		
		Thread.sleep(1000);
		//Select the required Price option
		Select setPrice4 = new Select(driver.findElement(By.xpath("//tbody/tr[2]/td[3]/ng-form[1]/div[1]/select[1]")));
		setPrice4.selectByVisibleText("All");
		
		//Clear the price field
		driver.findElement(By.xpath("//tbody/tr[2]/td[4]/ng-form[1]/div[1]/input[1]")).clear();
		//Enter the required price
		driver.findElement(By.xpath("//tbody/tr[2]/td[4]/ng-form[1]/div[1]/input[1]")).sendKeys("1115");
		
		//delete the second Tiered price option
		driver.findElement(By.xpath("//tbody/tr[2]/td[5]/a[1]/i[1]")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		Thread.sleep(2500);

		
		//Click the Set Tiered price Check box
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[3]/div/div/div/input")).click();
		
		//Click the Add Set Tiered price
		driver.findElement(By.xpath("//a[contains(.,'Set Tiered Price')]/../a/i")).click();
		
		Thread.sleep(1000);
		//Click the Serving Size Level field
		driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[2]/div[2]/form[1]/div[1]/ng-form[1]/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]")).click();
		//Enter the required SErving Size Level
		driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[2]/div[2]/form[1]/div[1]/ng-form[1]/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[2]/div[2]/form[1]/div[1]/ng-form[1]/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys(Keys.ENTER);
		
		//Delete the last added Set tiered price option
				driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[2]/div[2]/form[1]/div[1]/ng-form[1]/div[2]/div[1]/div[2]/div[4]/div[1]/ul[1]/li[2]/a[1]/uib-tab-heading[1]/a[1]/i[1]")).click();
				
		Thread.sleep(1000);*/
		// click on the check button of set tiered option
		driver.findElement(By.xpath(excel.getData(5, 356, 1))).click();
		Thread.sleep(2000);
		//Click the Set Tiered option
				driver.findElement(By.xpath(excel.getData(5, 329, 1))).click();Thread.sleep(2500);
				
				for(int i = 1; i <= 5; i++)
				{
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.ARROW_DOWN);
				}
				Thread.sleep(5000);
				//Click the Serving Size Level Option
				driver.findElement(By.xpath(excel.getData(5, 330, 1))).click();
				//Enter the required serving size level
				driver.findElement(By.xpath(excel.getData(5, 331, 1))).click();
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(5, 331, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(1000);
				//Click the set Tiered price option
				driver.findElement(By.xpath(excel.getData(5, 332, 1))).click();
				//click add Tiered Price
				//driver.findElement(By.xpath(excel.getData(5, 343, 1))).click();
				
				//Clear the Quantity option
				driver.findElement(By.xpath(excel.getData(5, 333, 1))).clear();
				//Give the required quantity
				driver.findElement(By.xpath(excel.getData(5, 333, 1))).sendKeys("1");
				
				Thread.sleep(2500);
				//Select the required option to set the price
				Select setPrice3 = new Select(driver.findElement(By.xpath(excel.getData(5, 334, 1))));
				setPrice3.selectByVisibleText("Each");
				
				//Clear the price option
				driver.findElement(By.xpath(excel.getData(5, 335, 1))).clear();
				//Enter the required price
				driver.findElement(By.xpath(excel.getData(5, 335, 1))).sendKeys("350");
				
				Thread.sleep(1000);
				//Click the set Tiered price option
				driver.findElement(By.xpath(excel.getData(5, 332, 1))).click();
				//click add Tiered Price
						//driver.findElement(By.xpath(excel.getData(5, 343, 1))).click();
				//Clear the Quantity option
				driver.findElement(By.xpath(excel.getData(5, 336, 1))).clear();
				//Give the required quantity
				driver.findElement(By.xpath(excel.getData(5, 336, 1))).sendKeys("2");
				
				Thread.sleep(2500);
				//Select the required option to set the price
				Select setPrice4 = new Select(driver.findElement(By.xpath(excel.getData(5, 337, 1))));
				setPrice4.selectByVisibleText("All");
				
				//Clear the price option
				driver.findElement(By.xpath(excel.getData(5, 338, 1))).clear();
				//Enter the required price
				driver.findElement(By.xpath(excel.getData(5, 338, 1))).sendKeys("250");
				
				Thread.sleep(1000);
				//Click the Add Tiered price option
				driver.findElement(By.xpath(excel.getData(5, 332, 1))).click();

				Thread.sleep(1000);
				//Click the delete option of last added one
				driver.findElement(By.xpath(excel.getData(5, 339, 1))).click();
				
				//Click the add Set Tiered Price option
				driver.findElement(By.xpath(excel.getData(5, 329, 1))).click();
				
				Thread.sleep(1000);
				//Click the delete Tiered Price option
				driver.findElement(By.xpath(excel.getData(5, 340, 1))).click();
				
				//Click the add Set Tiered Price option
				driver.findElement(By.xpath(excel.getData(5, 329, 1))).click();

				Thread.sleep(2500);
				//Click the Select Serving Size Level Option
				driver.findElement(By.xpath(excel.getData(5, 341, 1))).click();
				//Enter the required serving size level
				driver.findElement(By.xpath(excel.getData(5, 342, 1))).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.xpath(excel.getData(5, 342, 1))).sendKeys(Keys.ARROW_DOWN);
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(5, 342, 1))).sendKeys(Keys.ENTER);

				Thread.sleep(1000);
				//Click the Add tiered price option
				driver.findElement(By.xpath(excel.getData(5, 343, 1))).click();
				
				//Clear the quantity field
				driver.findElement(By.xpath(excel.getData(5, 344, 1))).clear();
				//Enter the quantity
				driver.findElement(By.xpath(excel.getData(5, 344, 1))).sendKeys("4");
				
				Thread.sleep(2500);
				//Select the required option to set the price
				Select setPrice5 = new Select(driver.findElement(By.xpath(excel.getData(5, 345, 1))));
				setPrice5.selectByVisibleText("Each");
				
				//Clear the price field
				driver.findElement(By.xpath(excel.getData(5, 346, 1))).clear();
				//Enter the price
				driver.findElement(By.xpath(excel.getData(5, 346, 1))).sendKeys("212");
				Thread.sleep(1000);
				driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.END);
				Thread.sleep(3000);
				

		
		Thread.sleep(2500);
		//Press the Previous button
		driver.findElement(By.cssSelector(excel.getData(5, 327, 4))).click();
		
		Thread.sleep(3000);
		//Click the Next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();  
		
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(5, 88, 1))).click();driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4501);
//		String s1 = driver.findElement(By.xpath(excel.getData(5, 219, 1))).getText();System.out.println(s1);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Menu item saved successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is saved Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is saved Failed");
		}
		
		Thread.sleep(8000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 7, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 7, 1))).sendKeys(Utility.getProperty("ProductsItems_Name"));
		driver.findElement(By.xpath(excel.getData(5, 263, 1))).click();
		
		String menuItemName = driver.findElement(By.xpath(excel.getData(5, 357, 1))).getText();
		String tempStr = Utility.getProperty("ProductsItems_Name")+" (EBT)";
		Thread.sleep(3000);
		if(menuItemName.equalsIgnoreCase(tempStr) )
		{
			test.log(LogStatus.PASS, "EBT is added successfully after the required menu item name");
		}
		else
		{
			test.log(LogStatus.FAIL, "EBT is added fail after the required menu item name");
		}
		Thread.sleep(5000);
		}
		
	
	@Test(enabled=false,priority=5)
	public void Product_And_Item_method_editMenuItem(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 7, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 7, 1))).sendKeys(Utility.getProperty("ProductsItems_Name"));
		driver.findElement(By.xpath(excel.getData(5, 263, 1))).click();
		
		//Click the Edit icon
		driver.findElement(By.cssSelector(excel.getData(5, 63, 4))).click();
	
		Thread.sleep(3000);
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).clear();
		//Enter the Name			 
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"1");
				
		Thread.sleep(2500);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(3000);
		
		//Clear the PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"1");
		
		//Click the Category Option
		driver.findElement(By.xpath(excel.getData(5, 358, 1))).click();
		//Enter the required Category
		driver.findElement(By.xpath(excel.getData(5, 359, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 359, 1))).sendKeys(Keys.ENTER);
		
		//Enable or Disable the Cut and Modify option
		driver.findElement(By.xpath(excel.getData(5, 360, 1))).click();
		
		Select measureType =  new Select(driver.findElement(By.name(excel.getData(5, 282, 3))));
		measureType.selectByVisibleText("Menu Item");
		
		Thread.sleep(2500);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		//Click the Serving size Level on the price field
		driver.findElement(By.xpath(excel.getData(5, 361, 1))).click();
		//Enter the required serving size level
		driver.findElement(By.xpath(excel.getData(5, 362, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 362, 1))).sendKeys(Keys.ENTER);
		
		//Clear the Price option
		driver.findElement(By.name(excel.getData(5, 363, 3))).clear();
		//Enter the required price
		driver.findElement(By.name(excel.getData(5, 363, 3))).sendKeys("250");
		
		Thread.sleep(3000);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Enable or disable the Kitchen Printer
		driver.findElement(By.xpath(excel.getData(5, 290, 1))).click();
		Thread.sleep(1000);
		//Enable or disable the Label Printer
		driver.findElement(By.xpath(excel.getData(5, 291, 1))).click();
		Thread.sleep(1000);
		//Enable or disable the Restrict printer
		driver.findElement(By.xpath(excel.getData(5, 292, 1))).click();
		

		Thread.sleep(2500);
		//Check whether the EBT menu item is enabled or not
		if(driver.findElement(By.xpath(excel.getData(5, 364, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "EBT menu item is in enabled status");
		}

		else
		{
			test.log(LogStatus.FAIL, "EBT menu item is in disabled status");
		}
		
		Thread.sleep(3000);driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.END);
		//Click the Select image from gallery option
		driver.findElement(By.xpath(excel.getData(5, 365, 1))).click();
		
		Thread.sleep(15000);
		//Click the required image from the Gallery
		driver.findElement(By.xpath(excel.getData(5, 366, 1))).click();
		Thread.sleep(3000);
		
		Thread.sleep(2500);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath(excel.getData(5, 370, 1))).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath(excel.getData(5, 371, 1))).sendKeys("Days of Week");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath(excel.getData(5, 371, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		//Click the Days of Week Option
		//driver.findElement(By.xpath("//input[@class='default']")).click();
		//Enter the required DAY for Searching
		
		driver.findElement(By.xpath(excel.getData(5, 372, 1))).click();
		driver.findElement(By.xpath(excel.getData(5, 373, 1))).sendKeys("Wednesday");
		driver.findElement(By.xpath(excel.getData(5, 373, 1))).sendKeys(Keys.ENTER);
		//driver.findElement(By.cssSelector("div.ng-scope:nth-child(8) div.ng-scope:nth-child(1) div.ng-scope:nth-child(1) div.mainContainer.ng-scope div.content div.container-fluid div.row div.ng-scope div.row.ng-scope div.col-sm-12 div.row div.col-sm-12 div.col-sm-12.content-block div.row div.col-sm-12 div.box.box-color.box-bordered div.box-content.nopadding form.form-horizontal.form-column.form-bordered.custom-form-border.ng-valid-maxlength.ng-valid-min.ng-valid-pattern.ng-valid-max.ng-dirty.ng-valid-parse.ng-invalid.ng-invalid-required div.ng-scope ng-form.ng-valid-maxlength.ng-valid-min.ng-valid-pattern.ng-valid-max.ng-dirty.ng-valid-parse.ng-invalid.ng-invalid-required div.col-sm-6.nopadding:nth-child(2) div.form-group.required.ng-scope.has-error:nth-child(9) div.col-sm-9 div.chosen-container.chosen-container-multi.chosen-with-drop.chosen-container-active:nth-child(2) > ul.chosen-choices")).sendKeys("Wednesday");
		//Select startDay = new Select(driver.findElement(By.xpath("//label[contains(.,'DAYS OF WEEK')]/../div/select")));
	//	startDay.selectByVisibleText("WEDNESDAY");

		//Press the Enter key after enter the keyword
		//driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/form[1]/div[1]/ng-form[1]/div[2]/div[9]/div[1]/div[1]/ul[1]/li[1]/input[1]")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		//Enable or disable the Restriction Time Period option
		driver.findElement(By.xpath(excel.getData(5, 374, 1))).click();
		Thread.sleep(2500);
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath(excel.getData(5, 375, 1))).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath(excel.getData(5, 377, 1))).click();
		}
		else
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath(excel.getData(5, 375, 1))).click();
		}

		
		//Click the update
		driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
		Thread.sleep(3000);
		
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Menu item updated successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is updated Failed");
		}
		Thread.sleep(3000);

		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 7, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 7, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"1");
		driver.findElement(By.xpath(excel.getData(5, 263, 1))).click();
		
		String menuItemName = driver.findElement(By.xpath(excel.getData(5, 357, 1))).getText();
		String tempSTR = Utility.getProperty("ProductsItems_Name")+"1 (EBT)";
		Thread.sleep(3000);
		if(menuItemName.equalsIgnoreCase(tempSTR))
		{
			test.log(LogStatus.PASS, "EBT is added successfully after the required menu item name");
		}
		else
		{
			test.log(LogStatus.FAIL, "EBT is added fail after the required menu item name");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=6)
	public void Product_And_Item_method_deleteMenuItem(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 7, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 7, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"1");
		//Need to Remove it
		Thread.sleep(1000);
		driver.findElement(By.xpath(excel.getData(5, 7, 1))).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath(excel.getData(5, 263, 1))).click();
		
		//Click the Delete button
		driver.findElement(By.cssSelector(excel.getData(5, 51, 4))).click();
		//Click the Yes button in the popup
		Thread.sleep(1500);driver.findElement(By.linkText("Yes")).click();
		Thread.sleep(3000);
		//Check the menu item deleted or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Menu item inactivated successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is deleted Failed");
		}
		Thread.sleep(3000);
		
		//Click the Active Button
		driver.findElement(By.xpath(excel.getData(5, 16, 1))).click();
		Thread.sleep(6000);
		
		//Click the success button
		driver.findElement(By.cssSelector(excel.getData(5, 39, 4))).click();
		
		Thread.sleep(2000);
		//Click the Yes button in the popup
		Thread.sleep(1500);driver.findElement(By.linkText("Yes")).click();
		Thread.sleep(3000);
		
		//Check the menu item activated or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Menu item activated successfully"))
		{
			test.log(LogStatus.PASS, "Menu Item is activated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Menu Item is activated Failed");
		}
		
		Thread.sleep(5000);
		//Click the Active Button
		driver.findElement(By.xpath(excel.getData(5, 16, 1))).click();
		Thread.sleep(3000);
	}
		
	@Test(enabled=false,priority=7)
	public void Product_And_Item_method_addMenuItemByCopyMenuItem(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		Thread.sleep(8000);
		//Click on the Add Menu Item option
		driver.findElement(By.id(excel.getData(5, 269, 2))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 270, 1))).getText().equalsIgnoreCase("NEW MENU ITEM"))
		{
			test.log(LogStatus.PASS, "New Menu Items form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Items form loaded Failed");
		}
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(5000);
		//Click the Menu Item field
		driver.findElement(By.xpath(excel.getData(5, 367, 1))).click();Thread.sleep(3000);
		//Enter the REquired menu Item
		driver.findElement(By.xpath(excel.getData(5, 368, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"1");
		Thread.sleep(1000);
		//need to Remove it
		driver.findElement(By.xpath(excel.getData(5, 368, 1))).sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 368, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath(excel.getData(5, 369, 1))).click();
		
		Thread.sleep(2500);
		//Click the Clear Option  
		driver.findElement(By.xpath(excel.getData(5, 378, 1))).click();
		
		//Click the Menu Item field
		driver.findElement(By.xpath(excel.getData(5, 367, 1))).click();
		//Enter the REquired menu Item
		driver.findElement(By.xpath(excel.getData(5, 368, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"1");

		//need to Remove it
		driver.findElement(By.xpath(excel.getData(5, 368, 1))).sendKeys(Keys.BACK_SPACE);
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 368, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath(excel.getData(5, 369, 1))).click();
	
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"10");
		
		Thread.sleep(2500);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		
		Thread.sleep(3000);
		//Clear the PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"2");
		
		Thread.sleep(3000);
		//Select the required Level Option
		Select level = new Select(driver.findElement(By.name(excel.getData(5, 379, 3))));
		level.selectByVisibleText("Sub Category");
		
		Thread.sleep(2500);
		
		//Click the Category Field
		driver.findElement(By.xpath(excel.getData(5, 276, 1))).click();
		//Enter the required category
		driver.findElement(By.xpath(excel.getData(5, 277, 1))).click();
		//Press the Enter key
		driver.findElement(By.xpath(excel.getData(5, 277, 1))).sendKeys(Keys.ENTER);	
		Thread.sleep(2500);
		//Click the sub Category Field
				driver.findElement(By.xpath(excel.getData(5, 380, 1))).click();
				//Enter the required sub-category
				driver.findElement(By.xpath(excel.getData(5, 381, 1))).click();
				//Press the Enter key
				driver.findElement(By.xpath(excel.getData(5, 381, 1))).sendKeys(Keys.ENTER);	
				Thread.sleep(2500);
				
		
		/*Click the add sub category button
		//driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[9]/div[2]/a/i")).click();
		
		Thread.sleep(3000);
		//Check weather the new Sub Category form loaded or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[1]/h3")).getText().equalsIgnoreCase("New sub-category"))
		{
			test.log(LogStatus.PASS, "New Sub Category form loaded successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Sub Category form loaded Failed");
		}
		
		Thread.sleep(2500);
		//Clear the name field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[1]/div/input")).clear();
		//Enter the new sub Category name
		/*driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[1]/div/input")).sendKeys(Utility.getProperty("ProductsItems_Add_Sub_Category_Name"));

		Thread.sleep(2500);
		//Click the Serving Size Level Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/ul")).click();
		//Enter the Serving size level
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/ul/li/input")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(2500);
		//Click the Serving Size Level Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/ul")).click();
		//Enter the Serving size level
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(2500);
		//Click the Serving Size Level Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/ul")).click();
		//Enter the Serving size level
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(2500);
		//Click the Serving Size Level Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/ul")).click();
		//Enter the Serving size level
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
				
		Thread.sleep(2500);
		//Click the Taxes option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[3]/div[1]/div[1]/div/ul")).click();
		//Enter the required tax
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[3]/div[1]/div[1]/div/ul/li/input")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[3]/div[1]/div[1]/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		//Click the Taxes option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[3]/div[1]/div[1]/div/ul")).click();
		//Enter the required tax
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[3]/div[1]/div[1]/div/ul/li/input")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[3]/div[1]/div[1]/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(2500);
		//Click the Save button
		driver.findElement(By.xpath("//a[@class='btn btn-success btn-small ng-binding']")).click();*/
		
		Thread.sleep(8000);
		Select measureType= new Select(driver.findElement(By.name(excel.getData(5, 282, 3))));
		measureType.selectByVisibleText("Open Item");
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(3000);
		//Click the Serving size Level on the price field
		driver.findElement(By.xpath(excel.getData(5, 361, 1))).click();
		//Enter the required serving size level
		driver.findElement(By.xpath(excel.getData(5, 362, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 362, 1))).sendKeys(Keys.ENTER);		
		Thread.sleep(3000);
		
		//Clear the price field
		driver.findElement(By.xpath(excel.getData(5, 382, 1))).clear();
		//Enter the required price
		driver.findElement(By.xpath(excel.getData(5, 382, 1))).sendKeys("120");
		
		//Click the Applicable Time Period field
		/*driver.findElement(By.xpath("")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys("Days of Month");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys(Keys.ENTER);*/
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		
		driver.findElement(By.xpath(excel.getData(5, 306, 1))).click();
		//Enter the required Time Period
		driver.findElement(By.xpath(excel.getData(5, 307, 1))).sendKeys("Days of Month");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 307, 1))).sendKeys(Keys.ENTER);
		//Click the Required Date form the Calendar
		driver.findElement(By.xpath(excel.getData(5, 383, 1))).click();
		Thread.sleep(2500);
		
		//Enable or Disable the Restriction Months option
		driver.findElement(By.xpath(excel.getData(5, 384, 1))).click();
		
		Thread.sleep(2500);
		//Click the months field
		driver.findElement(By.xpath(excel.getData(5, 385, 1))).click();
		//Enter the Required month
		driver.findElement(By.xpath(excel.getData(5, 386, 1))).sendKeys("MAY");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 386, 1))).sendKeys(Keys.ENTER);
		
		driver.findElement(By.xpath(excel.getData(5, 374, 1))).click();
		Thread.sleep(2500);
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath(excel.getData(5, 375, 1))).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath(excel.getData(5, 387, 1))).click();
		}
		else
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath(excel.getData(5, 375, 1))).click();
		}

		Thread.sleep(2500);
		
		//Click the next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		Thread.sleep(5000);
		
		//Clear the price of prefix amount
				driver.findElement(By.xpath(excel.getData(5, 321, 1))).clear();
				//Give the required amount
				driver.findElement(By.xpath(excel.getData(5, 321, 1))).sendKeys("120");
				//Give the amount for added valve
				//driver.findElement(By.xpath("//tbody/tr[2]/td[3]/div[2]/input[1]")).sendKeys("150");
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				Thread.sleep(3000);
				//close the additional modifer
				driver.findElement(By.xpath(excel.getData(5, 388, 1))).click();
				Thread.sleep(1000);
				//Click the Yes button in the popup
				Thread.sleep(1500);driver.findElement(By.linkText("Yes")).click();
				
				//Click the Alternate Modifier group
				driver.findElement(By.xpath(excel.getData(5, 317, 1))).click();
				
				Thread.sleep(1000);
				//Select the alternate modifier group option
				driver.findElement(By.xpath(excel.getData(5, 318, 1))).click();
				//Enter the required modifier
				driver.findElement(By.xpath(excel.getData(5, 319, 1))).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.xpath(excel.getData(5, 319, 1))).sendKeys(Keys.ARROW_DOWN);
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(5, 319, 1))).sendKeys(Keys.ENTER);
				
				//Enable or Disable the Override prefix up-charges
				driver.findElement(By.xpath(excel.getData(5, 320, 1))).click();
				
				Thread.sleep(1000);
				//Clear the price of prefix amount
				driver.findElement(By.xpath(excel.getData(5, 321, 1))).clear();
				//Give the required amount
				driver.findElement(By.xpath(excel.getData(5, 321, 1))).sendKeys("120");
				
				//Give the amount for added valve
				//driver.findElement(By.xpath("//tbody/tr[2]/td[3]/div[2]/input[1]")).sendKeys("190");

		//Click the Save button
		driver.findElement(By.xpath(excel.getData(5, 88, 1))).click();driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4501);
		//String s1 = driver.findElement(By.xpath(excel.getData(5, 219, 1))).getText();System.out.println(s1);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Menu item saved successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is saved Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is saved Failed");
		}
		Thread.sleep(3000);
	}
	@Test(enabled=false,priority=8)
	public void Product_And_Item_method_cancelMenuItemByCopyMenuItem_Basic(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		Thread.sleep(8000);
		//Click on the Add Menu Item option
		driver.findElement(By.id(excel.getData(5, 269, 2))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 270, 1))).getText().equalsIgnoreCase("NEW MENU ITEM"))
		{
			test.log(LogStatus.PASS, "New Menu Items form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Items form loaded Failed");
		}
				
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(3000);
		//Click the Menu Item field
		driver.findElement(By.xpath(excel.getData(5, 389, 1))).click();Thread.sleep(2500);
		//Enter the REquired menu Item
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys("COKE");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath(excel.getData(5, 391, 1))).click();
			
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"10");
				
		//Click the Cancel button
		driver.findElement(By.xpath(excel.getData(5, 392, 1))).click();
		Thread.sleep(3000);
		
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 260, 1))).getText().equalsIgnoreCase("Menu items"))
		{
			test.log(LogStatus.PASS, "New Menu Item is cancelled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is cancelled Failed");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=9)
	public void Product_And_Item_method_cancelMenuItemByCopyMenuItem_Included_Modifiers(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id(excel.getData(5, 269, 2))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 270, 1))).getText().equalsIgnoreCase("NEW MENU ITEM"))
		{
			test.log(LogStatus.PASS, "New Menu Items form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Items form loaded Failed");
		}
				
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(3000);
		//Click the Menu Item field
		driver.findElement(By.xpath(excel.getData(5, 389, 1))).click();Thread.sleep(1000);
		//Enter the REquired menu Item
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys("COKE");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath(excel.getData(5, 391, 1))).click();
			
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"10");
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		//Click the Cancel button
		driver.findElement(By.xpath(excel.getData(5, 392, 1))).click();
		Thread.sleep(3000);
		
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 260, 1))).getText().equalsIgnoreCase("Menu items"))
		{
			test.log(LogStatus.PASS, "New Menu Item is cancelled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is cancelled Failed");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=10)
	public void Product_And_Item_method_cancelMenuItemByCopyMenuItem_Optional_Modifier_Groups(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id(excel.getData(5, 269, 2))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 270, 1))).getText().equalsIgnoreCase("NEW MENU ITEM"))
		{
			test.log(LogStatus.PASS, "New Menu Items form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Items form loaded Failed");
		}
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(10000);
		//Click the Menu Item field
		driver.findElement(By.xpath(excel.getData(5, 389, 1))).click();Thread.sleep(2500);
		//Enter the REquired menu Item
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys("COKE");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath(excel.getData(5, 391, 1))).click();
			
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"10");
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		//Click the Cancel button
		driver.findElement(By.xpath(excel.getData(5, 392, 1))).click();
		Thread.sleep(3000);
		
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 260, 1))).getText().equalsIgnoreCase("Menu items"))
		{
			test.log(LogStatus.PASS, "New Menu Item is cancelled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is cancelled Failed");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=11)
	public void Product_And_Item_method_cancelMenuItemByCopyMenuItem_Mandatory_Modifier(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id(excel.getData(5, 269, 2))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 270, 1))).getText().equalsIgnoreCase("NEW MENU ITEM"))
		{
			test.log(LogStatus.PASS, "New Menu Items form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Items form loaded Failed");
		}
				
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(3000);
		//Click the Menu Item field
		driver.findElement(By.xpath(excel.getData(5, 389, 1))).click();Thread.sleep(2500);
		//Enter the REquired menu Item
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys("COKE");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath(excel.getData(5, 391, 1))).click();
			
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"10");
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		//Click the Cancel button
		driver.findElement(By.xpath(excel.getData(5, 392, 1))).click();
		Thread.sleep(3000);
		
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 260, 1))).getText().equalsIgnoreCase("Menu items"))
		{
			test.log(LogStatus.PASS, "New Menu Item is cancelled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is cancelled Failed");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=12)
	public void Product_And_Item_method_saveMenuItemByCopyMenuItem(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id(excel.getData(5, 269, 2))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 270, 1))).getText().equalsIgnoreCase("NEW MENU ITEM"))
		{
			test.log(LogStatus.PASS, "New Menu Items form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Items form loaded Failed");
		}
				
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(3000);
		//Click the Menu Item field
		driver.findElement(By.xpath(excel.getData(5, 389, 1))).click();Thread.sleep(2500);
		//Enter the REquired menu Item
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"10");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath(excel.getData(5, 391, 1))).click();
			
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"11");
		
		Thread.sleep(3000);
		//Clear the PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"3");
		
		driver.findElement(By.xpath(excel.getData(5, 306, 1))).click();
		//Enter the required Time Period
		driver.findElement(By.xpath(excel.getData(5, 307, 1))).sendKeys("Date Range");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 307, 1))).sendKeys(Keys.ENTER);
		/*//Click the Applicable Time Period field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys("Date Range");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		*/
		Thread.sleep(3000);
		//Clear the From date field
		driver.findElement(By.name(excel.getData(5, 393, 3))).clear();
		//Enter the required from Date
		driver.findElement(By.name(excel.getData(5, 393, 3))).sendKeys("25-May-2030");
		//Clear the To Date field
		driver.findElement(By.name(excel.getData(5, 394, 3))).clear();
		//Enter the required To Date
		driver.findElement(By.name(excel.getData(5, 394, 3))).sendKeys("28-May-2030");

		//Enable or Disable the Restriction Days Option
		driver.findElement(By.xpath(excel.getData(5, 396, 1))).click();
		
		Thread.sleep(2500);
		//Click the Days of a Week option
		driver.findElement(By.xpath(excel.getData(5, 372, 1))).click();
		//Enter the Required Date
		driver.findElement(By.xpath(excel.getData(5, 373, 1))).sendKeys("FRIDAY");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 373, 1))).sendKeys(Keys.ENTER);
// enable the time restriction
		driver.findElement(By.xpath(excel.getData(5, 374, 1))).click();
		Thread.sleep(2500);
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath(excel.getData(5, 375, 1))).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath(excel.getData(5, 387, 1)
					+ "")).click();
		}
		else
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath(excel.getData(5, 375, 1))).click();
		}

		Thread.sleep(2500);
		
		
		
		//Click the Cancel button
		driver.findElement(By.xpath(excel.getData(5, 88, 1))).click();driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4500);
	//	String s1 = driver.findElement(By.xpath(excel.getData(5, 219, 1))).getText();System.out.println(s1);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Menu item saved successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is saved Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is saved Failed");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=13)
	public void Product_And_Item_method_saveAndContinueMenuItemByCopyMenuItem(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id(excel.getData(5, 269, 2))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 270, 1))).getText().equalsIgnoreCase("NEW MENU ITEM"))
		{
			test.log(LogStatus.PASS, "New Menu Items form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Items form loaded Failed");
		}
			
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(3000);
		//Click the Menu Item field
		driver.findElement(By.xpath(excel.getData(5, 389, 1))).click();Thread.sleep(2500);
		//Enter the REquired menu Item
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"11");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath(excel.getData(5, 391, 1))).click();
			
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"12");
				
		Thread.sleep(3000);
		//Clear the PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"4");
		
		Thread.sleep(3000);
		//Select the required measure type
		Select measureType = new Select(driver.findElement(By.name(excel.getData(5, 282, 3))));
		measureType.selectByVisibleText("Open Item");
		
		Thread.sleep(5000);
	/*	//Click the taregroup option
		driver.findElement(By.xpath("//select[@name='tareGroup']/../div/a")).click();
		//Enter the required Taregroup
		driver.findElement(By.xpath("//select[@name='tareGroup']/../div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//select[@name='tareGroup']/../div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//select[@name='tareGroup']/../div/div/div/input")).sendKeys(Keys.ARROW_DOWN);

		//Press the Enter button
		driver.findElement(By.xpath("//select[@name='tareGroup']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(2500);
		//Check weather the Default tare is selected or not
		if (driver.findElement(By.xpath("//select[@name='defaultTare']")).isEnabled()) {
			
			Thread.sleep(2500);
			//Click the Default tare option
			driver.findElement(By.xpath("//select[@name='defaultTare']/../div/a")).click();
			//Enter the required tare
			driver.findElement(By.xpath("//select[@name='defaultTare']/../div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter button
			driver.findElement(By.xpath("//select[@name='defaultTare']/../div/div/div/input")).sendKeys(Keys.ENTER);
		}*/
		
	//	Select measureType= new Select(driver.findElement(By.name(excel.getData(5, 282, 3))));
		//measureType.selectByVisibleText("Open Item");
		
		Thread.sleep(3000);
		//Click the Serving size Level on the price field
		driver.findElement(By.xpath(excel.getData(5, 361, 1))).click();
		//Enter the required serving size level
		driver.findElement(By.xpath(excel.getData(5, 362, 1))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath(excel.getData(5, 362, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 362, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Clear the price option
		driver.findElement(By.name(excel.getData(5, 363, 3))).clear();
		//Enter the required price
		driver.findElement(By.name(excel.getData(5, 363, 3))).sendKeys("155");
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath(excel.getData(5, 306, 1))).click();
		//Enter the required Time Period
		driver.findElement(By.xpath(excel.getData(5, 307, 1))).sendKeys("Specific date");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 307, 1))).sendKeys(Keys.ENTER);
		//Click the Required Date form the Calendar
		//driver.findElement(By.xpath(excel.getData(5, 383, 1))).click();
		Thread.sleep(2500);
		
		//Clear the Date field
		driver.findElement(By.name(excel.getData(5, 203, 3))).clear();
		//Enter the required date
		driver.findElement(By.name(excel.getData(5, 203, 3))).sendKeys("15-May-2030");
		
		//Enable or disable the Restriction Time Period option
				driver.findElement(By.xpath(excel.getData(5, 374, 1))).click();
				Thread.sleep(2500);
				
				//Check it is AM or PM in the Start Time Option
				if(driver.findElement(By.xpath(excel.getData(5, 375, 1))).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2500);
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath(excel.getData(5, 387, 1))).click();
				}
				
				else
				{
					Thread.sleep(2500);
					//Click the AM or PM option in the Start Time
					driver.findElement(By.xpath(excel.getData(5, 375, 1))).click();
				}

		
		//Click the save and continue button
		driver.findElement(By.xpath(excel.getData(5, 395, 1))).click();driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4501);
	//	String s1 = driver.findElement(By.xpath(excel.getData(5, 219, 1))).getText();System.out.println(s1);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Menu item saved successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is saved Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is saved Failed");
		}
		Thread.sleep(10000);
		
		//Click the Menu Item field
		driver.findElement(By.xpath(excel.getData(5, 389, 1))).click();
		//Enter the REquired menu Item
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"12");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath(excel.getData(5, 391, 1))).click();
			
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"13");
		
		
		Thread.sleep(2500);
		//Clear the PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"5");
	
		//Applicable time period
		driver.findElement(By.xpath(excel.getData(5, 306, 1))).click();
		//Enter the required Time Period
		driver.findElement(By.xpath(excel.getData(5, 307, 1))).sendKeys("Start date time & end date time");
				
		//Press the Enter Key in the Applicable time period option field
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(5, 307, 1))).sendKeys(Keys.ENTER);
		
	//Press the Enter Key in the Applicable time period option field
		
		Thread.sleep(3000);
		//Clear the From date field
		driver.findElement(By.name(excel.getData(5, 393, 3))).clear();
		//Enter the required from Date
		driver.findElement(By.name(excel.getData(5, 393, 3))).sendKeys("25-May-2019");
		//Clear the To Date field
		driver.findElement(By.name(excel.getData(5, 394, 3))).clear();
		//Enter the required To Date
		driver.findElement(By.name(excel.getData(5, 394, 3))).sendKeys("28-May-2019");
		
		//Enable or Disable the Restriction Days Option
		driver.findElement(By.xpath(excel.getData(5, 396, 1))).click();
		
		Thread.sleep(2500);
		//Click the Days of a Week option
		driver.findElement(By.xpath(excel.getData(5, 397, 1))).click();
		//Enter the Required Date
		driver.findElement(By.xpath(excel.getData(5, 398, 1))).sendKeys("FRIDAY");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 398, 1))).sendKeys(Keys.ENTER);

		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath(excel.getData(5, 375, 1))).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath(excel.getData(5, 387, 1))).click();
		}
		else
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath(excel.getData(5, 375, 1))).click();
		}

		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		Thread.sleep(6000);
		//Click the Save And Continue button
		driver.findElement(By.xpath(excel.getData(5, 395, 1))).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4501);
//		String s2 = driver.findElement(By.xpath(excel.getData(5, 219, 1))).getText();System.out.println(s2);*/
	//	Thread.sleep(4000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Menu item saved successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is saved Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is saved Failed");
		}
		Thread.sleep(10000);
		
		//Click the Menu Item field
		driver.findElement(By.xpath(excel.getData(5, 389, 1))).click();
		//Enter the REquired menu Item
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"13");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath(excel.getData(5, 391, 1))).click();
			
		//Clear the Name field
		
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"14");
				
		Thread.sleep(3000);
		//Clear the PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"6");
		
		Thread.sleep(5000);
		//Click the next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		Thread.sleep(5000);
		//Click the next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		Thread.sleep(8000);
		//Click the Save And Continue button
		driver.findElement(By.xpath(excel.getData(5, 395, 1))).click();driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4501);
	//	String s3 = driver.findElement(By.xpath(excel.getData(5, 219, 1))).getText();System.out.println(s3);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Menu item saved successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is saved Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is saved Failed");
		}
		Thread.sleep(10000);
		
		//Click the Menu Item field
		driver.findElement(By.xpath(excel.getData(5, 389, 1))).click();
		//Enter the REquired menu Item
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"14");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath(excel.getData(5, 391, 1))).click();
			
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"15");
			
		Thread.sleep(3000);
		//Clear the PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"7");
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		Thread.sleep(8000);
		//Click the Save And Continue button
		driver.findElement(By.xpath(excel.getData(5, 395, 1))).click();driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4501);
//		String s4 = driver.findElement(By.xpath(excel.getData(5, 219, 1))).getText();System.out.println(s4);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Menu item saved successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is saved Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is saved Failed");
		}
		Thread.sleep(10000);

		//Click the Menu Item field
		driver.findElement(By.xpath(excel.getData(5, 389, 1))).click();
		//Enter the REquired menu Item
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"15");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(5, 390, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath(excel.getData(5, 391, 1))).click();
			
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(5, 271, 1))).sendKeys(Utility.getProperty("ProductsItems_Name")+"16");
				
		Thread.sleep(2500);
		//Clear the PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name(excel.getData(5, 275, 3))).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"8");
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
				
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector(excel.getData(5, 308, 4))).click();
		
		Thread.sleep(8000);
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(5, 88, 1))).click();driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4501);
		//String s5 = driver.findElement(By.xpath(excel.getData(5, 219, 1))).getText();System.out.println(s5);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Menu item saved successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is saved Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is saved Failed");
		}
		Thread.sleep(10000);
		
	}
}
