package epicList_Chrome_Xpath;

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


public class AddEditDelete_ProductItems_NEW {
	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete_ProductItems");

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
			Product_And_Item_method_openProductsItems(driver);
			Product_And_Item_method_refreshMenuItemPage(driver);
			Product_And_Item_method_pagination(driver);
			Product_And_Item_method_addMenuItem(driver);
			Product_And_Item_method_editMenuItem(driver);
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
		Thread.sleep(5000);
		//Check Menu items page opened or not
		if(driver.findElement(By.xpath("//a[.='Menu Items']")).getText().equalsIgnoreCase("Menu items"))
		{
			test.log(LogStatus.PASS, "Menu Items page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Menu Items page loaded Failed");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=3)
	public void Product_And_Item_method_refreshMenuItemPage(WebDriver driver) throws InterruptedException
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(10000);
		//Click the refresh button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/a[1]/i")).click();
		Thread.sleep(5000);
		//Check Menu Items page opened or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[1]/div/h3")).getText().equalsIgnoreCase("Menu Items"))
		{
			test.log(LogStatus.PASS, "Menu Items page refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Menu Items page refreshed Failed");
		}
	}
	
	@Test(enabled=false,priority=3)
	public void Product_And_Item_method_pagination(WebDriver driver) throws InterruptedException
	{
						
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		Thread.sleep(1000);
		
		//Click the Pagination option as 10
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/div/div/div/ul[1]/li[2]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Product and Item(Menu Item)");
		//Create the web element for menu item Table
		List<WebElement> results = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table"));
		for (WebElement result : results){						
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Product and Item(Menu Item)");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 15
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/div/div/div/ul[1]/li[3]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Product and Item(Menu Item)");
		//Create the web element for menu item Table
		List<WebElement> results1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table"));
		for (WebElement result : results1){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Product and Item(Menu Item)");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 20
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/div/div/div/ul[1]/li[4]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for Product and Item(Menu Item)");
		//Create the web element for menu item Table
		List<WebElement> results2 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table"));
		for (WebElement result : results2){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Product and Item(Menu Item)");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 5
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/div/div/div/ul[1]/li[1]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Product and Item(Menu Item)");
		//Create the web element for menu item Table
		List<WebElement> results3 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table"));
		for (WebElement result : results3){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Product and Item(Menu Item)");
		}
		
		 Thread.sleep(3000);
		  
	}
	
	@Test(enabled=false,priority=4)
	public void Product_And_Item_method_addMenuItem(WebDriver driver) throws Exception
	{
		for (int i = 0; i < 10; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		Thread.sleep(3000);
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id("menuItem")).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//span[.='NEW MENU ITEM']")).getText().equalsIgnoreCase("NEW MENU ITEM"))
		{
			test.log(LogStatus.PASS, "New Menu Items form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Items form loaded Failed");
		}
				
		
		Thread.sleep(3000);
		//Clear the Name field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name"));
		Thread.sleep(2500);
		//Clear the Secondary Name field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[3]/div/input")).clear();
		//Enter the Secondary Name
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[3]/div/input")).sendKeys(Utility.getProperty("ProductsItems_SecName"));
		Thread.sleep(1000);
		
		//Clear the Description field
		driver.findElement(By.name("description")).clear();
		//Enter the required description
		driver.findElement(By.name("description")).sendKeys("TESTING DESCRIPTION");
		
/*		//Clear the Chit Name field
		driver.findElement(By.name("chitName")).clear();
		//Enter the required Chit Name
		driver.findElement(By.name("chitName")).sendKeys("TEST CHIT NAME");*/
		
		//Clear the Kitchen Print Name field
		driver.findElement(By.name("chitName")).clear();
		//Enter the required Kitchen Print Name
		driver.findElement(By.name("chitName")).sendKeys("Kitchen Print");
		
		//Clear the PLU CODE
		driver.findElement(By.name("pluCode")).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name("pluCode")).sendKeys(Utility.getProperty("ProductsItems_PLU_Code"));
	
		//Scroll the page
		JavascriptExecutor jse1 = (JavascriptExecutor)driver;
		jse1.executeScript("window.scrollBy(0,250)", "");
		
		//Create the Object
		Select level = new Select(driver.findElement(By.name("level")));
		level.selectByVisibleText("Category");
		
		//Scroll the page
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
	
		//Click the Category Field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[8]/div[1]/div/a")).click();
		//Enter the required category
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[8]/div[1]/div/div/div/input")).click();
		//Press the Enter key
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[8]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);	
		
		Thread.sleep(3000);
		//Enable the Cut and Modify option
		driver.findElement(By.xpath("//span[contains(.,'conversational')]/../../../../div/div/label/span[contains(.,'Cut And Modify')]")).click();Thread.sleep(5000);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);Thread.sleep(2500);
		//Enable the Cut and Modify option
		driver.findElement(By.xpath("//span[contains(.,'Price Level Settings')]")).click();
		Thread.sleep(2500);
		//Enable the Is concventional option
		driver.findElement(By.xpath("//span[contains(.,'conversational')]")).click();Thread.sleep(5000);

		//Clear the Number of Slices option
		driver.findElement(By.xpath("//label[.='Number of slices']/../div/input")).clear();
		//Enter the required input
		driver.findElement(By.xpath("//label[.='Number of slices']/../div/input")).sendKeys("3");
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		Thread.sleep(2500);
		//Select the required measure type
		Select measureType = new Select(driver.findElement(By.name("measureType")));
		measureType.selectByVisibleText("Serving Size");
	
		Thread.sleep(2500);
		//Click the add Serving Size Level
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[2]/td/a")).click();
		      
		Thread.sleep(1000);
		//Click the select an Option field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[2]/td[1]/ng-form/div/div/a")).click();
		//Enter the required Serving Size Level
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[2]/td[1]/ng-form/div/div/div/div/input")).click();
		//Press the Enter Key
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[2]/td[1]/ng-form/div/div/div/div/input")).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Clear the Serial number field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[2]/td[2]/ng-form/div/input[2]")).clear();
		//Enter the required serial number
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[2]/td[2]/ng-form/div/input[2]")).sendKeys("1");
		
		Thread.sleep(1000);
		//Clear the price field
		driver.findElement(By.xpath("//label[.='Prices']/../div/div/table/tbody/tr[2]/td[3]/table/tbody/tr/td[2]/ng-form/div/input")).clear();
		//Enter the required price
		driver.findElement(By.xpath("//label[.='Prices']/../div/div/table/tbody/tr[2]/td[3]/table/tbody/tr/td[2]/ng-form/div/input")).sendKeys("111");
		
		Thread.sleep(1000);
		//Click the add Serving Size Level
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[3]/td/a")).click();
		//Click the select an Option field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[3]/td[1]/ng-form/div/div/a")).click();
		//Enter the required Serving Size Level
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[3]/td[1]/ng-form/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter Key
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[3]/td[1]/ng-form/div/div/div/div/input")).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Clear the Serial number field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[3]/td[2]/ng-form/div/input[2]")).clear();
		//Enter the required serial number
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[3]/td[2]/ng-form/div/input[2]")).sendKeys("3");
		
		Thread.sleep(1000);
		//Clear the price field
		driver.findElement(By.xpath("//label[.='Prices']/../div/div/table/tbody/tr[3]/td[3]/table/tbody/tr/td[2]/ng-form/div/input")).clear();
		//Enter the required price
		driver.findElement(By.xpath("//label[.='Prices']/../div/div/table/tbody/tr[3]/td[3]/table/tbody/tr/td[2]/ng-form/div/input")).sendKeys("113");
		
		Thread.sleep(1000);
		//Click the add Serving Size Level
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[4]/td/a")).click();
		//Click the select an Option field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[4]/td[1]/ng-form/div/div/a")).click();
		//Enter the required Serving Size Level
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[4]/td[1]/ng-form/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(1000);
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[4]/td[1]/ng-form/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[4]/td[1]/ng-form/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter Key
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[4]/td[1]/ng-form/div/div/div/div/input")).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Clear the Serial number field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[4]/td[2]/ng-form/div/input[2]")).clear();
		//Enter the required serial number
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[4]/td[2]/ng-form/div/input[2]")).sendKeys("4");
		
		Thread.sleep(1000);
		//Clear the price field
		driver.findElement(By.xpath("//label[.='Prices']/../div/div/table/tbody/tr[4]/td[3]/table/tbody/tr/td[2]/ng-form/div/input")).clear();
		//Enter the required price
		driver.findElement(By.xpath("//label[.='Prices']/../div/div/table/tbody/tr[4]/td[3]/table/tbody/tr/td[2]/ng-form/div/input")).sendKeys("114");
		
		Thread.sleep(1000);
		//Click the add Serving Size Level
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[5]/td/a")).click();
		//Click the select an Option field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[5]/td[1]/ng-form/div/div/a")).click();
		//Enter the required Serving Size Level
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[5]/td[1]/ng-form/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[5]/td[1]/ng-form/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[5]/td[1]/ng-form/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter Key
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[5]/td[1]/ng-form/div/div/div/div/input")).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Clear the Serial number field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[5]/td[2]/ng-form/div/input[2]")).clear();
		//Enter the required serial number
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[15]/div/div/table/tbody/tr[5]/td[2]/ng-form/div/input[2]")).sendKeys("2");
		
		Thread.sleep(1000);
		//Clear the price field
		driver.findElement(By.xpath("//label[.='Prices']/../div/div/table/tbody/tr[5]/td[3]/table/tbody/tr/td[2]/ng-form/div/input")).clear();
		//Enter the required price
		driver.findElement(By.xpath("//label[.='Prices']/../div/div/table/tbody/tr[5]/td[3]/table/tbody/tr/td[2]/ng-form/div/input")).sendKeys("112");
		
		Thread.sleep(3000);
		for(int i = 1; i <= 4; i++){driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);}
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);Thread.sleep(3000);
		//Click the close button of first option
		driver.findElement(By.xpath("//label[.='Prices']/../div/div/table/tbody/tr[1]/td[3]/table/tbody/tr/th[4]/a/i")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Enable or disable the Kitchen printer
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[1]/div[2]/label/span")).click();
		//Enable or disable the Label printer
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[1]/div[3]/label/span")).click();
		//Enable or disable the Restrict printer
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[1]/div[4]/label/span")).click();
		
		Thread.sleep(1000);
		//Click the Tax option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[2]/div[1]/div/ul")).click();
		//Enter the required tax
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[2]/div[1]/div/ul/li/input")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[2]/div[1]/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Kitchen Printer option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[3]/div[1]/div/ul")).click();
		//Click the input field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[3]/div[1]/div/ul/li/input")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[3]/div[1]/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Click the Label Printer option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[4]/div[1]/div/ul")).click();
		//Click the input field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[4]/div[1]/div/ul/li/input")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[4]/div[1]/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Click the Restrict Service Types option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[5]/div/div/ul")).click();
		//Enter the required service type
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[5]/div/div/ul/li/input")).sendKeys("BarTab");
		//Press Enter button
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[5]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Enable or disable the Do not show Button on POS field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[6]/div/input")).click();
		
		Thread.sleep(1000);
		//Enable or disable the Hide MenuItem in Zenpepper on POS field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[7]/div/input")).click();

		Thread.sleep(1000);
		//Enable or disable the EBT MenuItem field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[8]/div/input")).click();

		Thread.sleep(1000);
		//Click the required display button
		driver.findElement(By.xpath("//div[@class='email-template-color color-swatch ng-scope'][2]")).click();
		
		Thread.sleep(5000);
		//Choose the image from the disk
		driver.findElement(By.xpath("//input[@id='menuImage']")).sendKeys(Utility.getProperty("DisplayGroupImage1"));
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(3000);
		//Click the Applicable Time Period option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/a")).click();
		//Enter the required Time Period
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys("Always");
		//Press the Enter button
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(15000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		Thread.sleep(2500);
		//Click the Next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
			
		Thread.sleep(10000);
		//Click the plus or add symbol
		driver.findElement(By.cssSelector("i.fa.fa-plus-circle")).click();
				
		Thread.sleep(2500);
		//Click the modifier group field
		driver.findElement(By.xpath("//select[@name='modGroup']/../div/a")).click();
		//Enter the required modifier group
		driver.findElement(By.xpath("//select[@name='modGroup']/../div/div/div/input")).click();
		//Press the Enter key
		driver.findElement(By.xpath("//select[@name='modGroup']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Click the modifier option
		driver.findElement(By.xpath("//select[@name='includedModifier']/../div/a")).click();
		//Enter the required modifier
		driver.findElement(By.xpath("//select[@name='includedModifier']/../div/div/div/input")).click();
		//Press the Enter key
		driver.findElement(By.xpath("//select[@name='includedModifier']/../div/div/div/input")).sendKeys(Keys.ENTER);	
		
		Thread.sleep(2500);
		//Click the Alternate Modifier group
		driver.findElement(By.xpath("//span[contains(.,'Alternate modifier group')]")).click();
		
		Thread.sleep(1000);
		//Select the alternate modifier group option
		driver.findElement(By.xpath("//select[@name='alternateGroup']/../div/a")).click();
		//Enter the required modifier
		driver.findElement(By.xpath("//select[@name='alternateGroup']/../div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//select[@name='alternateGroup']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Enable or Disable the Override prefix up-charges
		driver.findElement(By.xpath("//span[contains(.,'Override prefix up-charges')]")).click();
		
		Thread.sleep(1000);
		//Clear the price of prefix amount
		driver.findElement(By.xpath("//th[.='Prefix']/../../tr/td[2]/div/input")).clear();
		//Give the required amount
		driver.findElement(By.xpath("//th[.='Prefix']/../../tr/td[2]/div/input")).sendKeys("120");
		
		Thread.sleep(5000);
		//Click the plus or add symbol
		driver.findElement(By.cssSelector("i.fa.fa-plus-circle")).click();

		//Click the close button of Additional modifier group
		driver.findElement(By.xpath("//p[.='Select included modifiers.']/../../div[2]/div/div/a/i")).click();
		
		Thread.sleep(1000);
		//Click the Yes button in the popup
		Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();

		Thread.sleep(5000);
		//Click the plus or add symbol
		driver.findElement(By.cssSelector("i.fa.fa-plus-circle")).click();
		
		for(int i = 1; i <= 6; i++)
		{
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		Thread.sleep(1000);
		//Click the another one modifier group
		driver.findElement(By.xpath("//select[@name='modGroup']/../../../../../../div[2]/div/div[2]/div/div[1]/div/a")).click();
		//Enter the required modifier group name
		driver.findElement(By.xpath("//select[@name='modGroup']/../../../../../../div[2]/div/div[2]/div/div[1]/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//select[@name='modGroup']/../../../../../../div[2]/div/div[2]/div/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the modifier option
		driver.findElement(By.xpath("//select[@name='modGroup']/../../../../../../div[2]/div/div[2]/div/div[2]/div/a")).click();
		//Enter the required modifier name
		driver.findElement(By.xpath("//select[@name='modGroup']/../../../../../../div[2]/div/div[2]/div/div[2]/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//select[@name='modGroup']/../../../../../../div[2]/div/div[2]/div/div[2]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(2500);
		//Press the Previous button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-left")).click();
		
		Thread.sleep(3000);
		//Click the Next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		Thread.sleep(3000);
		//Click the Next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		//Click the add Optional Modifier option
		driver.findElement(By.xpath("//a[contains(.,'Optional Modifier')]/i")).click();
		
		Thread.sleep(2500);
		//Click the modifier group option
		driver.findElement(By.xpath("//select[@name='modGroup']/../div/a")).click();
		//Enter the required modifier group option
		driver.findElement(By.xpath("//select[@name='modGroup']/../div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//select[@name='modGroup']/../div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//select[@name='modGroup']/../div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//select[@name='modGroup']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Check weather the Override price check box is enabled or not
		if(driver.findElement(By.xpath("//label[.='Override Price']/../div/input")).isEnabled())
		{
		//Click the Override price option
		driver.findElement(By.xpath("//label[.='Override Price']/../div/input")).click();
		}

		//Clear the Display Order option
		driver.findElement(By.name("ordinal")).clear();
		//Enter the required number option
		driver.findElement(By.name("ordinal")).sendKeys("3");
		
/*		//Clear the Price option
		driver.findElement(By.xpath("//td/div/input")).clear();
		//Enter the required price
		driver.findElement(By.xpath("//td/div/input")).sendKeys("250");*/
		
		//Click the Set Tiered Price Check box
		driver.findElement(By.xpath("//label[contains(.,'Set Tiered')]/../div/input")).click();
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		Thread.sleep(5000);
		//Click the Set Tiered option
		driver.findElement(By.xpath("//a[contains(.,'Set Tiered')]/i")).click();Thread.sleep(2500);
		
		for(int i = 1; i <= 5; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		Thread.sleep(5000);
		//Click the Serving Size Level Option
		driver.findElement(By.xpath("//label[.='Serving Level']/../div/div/a")).click();
		//Enter the required serving size level
		driver.findElement(By.xpath("//label[.='Serving Level']/../div/div/div/div/input")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//label[.='Serving Level']/../div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Add Tiered price option
		driver.findElement(By.xpath("//a[contains(.,'Add tiered price')]")).click();
		
		//Clear the Quantity option
		driver.findElement(By.xpath("//ng-form[@name='tieredQtyForm']/div/input")).clear();
		//Give the required quantity
		driver.findElement(By.xpath("//ng-form[@name='tieredQtyForm']/div/input")).sendKeys("1");
		
		Thread.sleep(2500);
		//Select the required option to set the price
		Select setPrice = new Select(driver.findElement(By.xpath("//select[@name='setPriceFor']")));
		setPrice.selectByVisibleText("Each");
		
		//Clear the price option
		driver.findElement(By.xpath("//ng-form/div/input[@name='price']")).clear();
		//Enter the required price
		driver.findElement(By.xpath("//ng-form/div/input[@name='price']")).sendKeys("350");
		
		Thread.sleep(1000);
		//Click the Add Tiered price option
		driver.findElement(By.xpath("//a[contains(.,'Add tiered price')]")).click();
		
		//Clear the Quantity option
		driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]/ng-form/div/input")).clear();
		//Give the required quantity
		driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]/ng-form/div/input")).sendKeys("2");
		
		Thread.sleep(2500);
		//Select the required option to set the price
		Select setPrice1 = new Select(driver.findElement(By.xpath("//table/tbody/tr[2]/td[3]/ng-form/div/select")));
		setPrice1.selectByVisibleText("All");
		
		//Clear the price option
		driver.findElement(By.xpath("//table/tbody/tr[2]/td[4]/ng-form/div/input")).clear();
		//Enter the required price
		driver.findElement(By.xpath("//table/tbody/tr[2]/td[4]/ng-form/div/input")).sendKeys("250");
		
		Thread.sleep(1000);
		//Click the Add Tiered price option
		driver.findElement(By.xpath("//a[contains(.,'Add tiered price')]")).click();

		Thread.sleep(1000);
		//Click the delete option of last added one
		driver.findElement(By.xpath("//table/tbody/tr[3]/td[5]/a/i")).click();
		
		//Click the add Set Tiered Price option
		driver.findElement(By.xpath("//a[contains(.,'Set Tiered')]/i")).click();
		
		Thread.sleep(1000);
		//Click the delete Tiered Price option
		driver.findElement(By.xpath("//span[contains(.,'Add Tiered')]/../a/i")).click();
		
		//Click the add Set Tiered Price option
		driver.findElement(By.xpath("//a[contains(.,'Set Tiered')]/i")).click();

		Thread.sleep(2500);
		//Click the Select Serving Size Level Option
		driver.findElement(By.xpath("//span[contains(.,'Add Tiered')]/../../../../../div/div[3]/div/div/div/div/div/a")).click();
		//Enter the required serving size level
		driver.findElement(By.xpath("//span[contains(.,'Add Tiered')]/../../../../../div/div[3]/div/div/div/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//span[contains(.,'Add Tiered')]/../../../../../div/div[3]/div/div/div/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//span[contains(.,'Add Tiered')]/../../../../../div/div[3]/div/div/div/div/div/div/div/input")).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Click the Add tiered price option
		driver.findElement(By.xpath("//div[@class]/div[3]/div[2]/div/div/table/tbody/tr/td/a[contains(.,'Add tiered price')]")).click();
		
		//Clear the quantity field
		driver.findElement(By.xpath("//div[@class]/div[3]/div[2]/div/div/table/tbody/tr[1]/td[2]/ng-form/div/input")).clear();
		//Enter the quantity
		driver.findElement(By.xpath("//div[@class]/div[3]/div[2]/div/div/table/tbody/tr[1]/td[2]/ng-form/div/input")).sendKeys("4");
		
		Thread.sleep(2500);
		//Select the required option to set the price
		Select setPrice2 = new Select(driver.findElement(By.xpath("//div[@class]/div[3]/div[2]/div/div/table/tbody/tr[1]/td[3]/ng-form/div/select")));
		setPrice2.selectByVisibleText("Each");
		
		//Clear the price field
		driver.findElement(By.xpath("//div[@class]/div[3]/div[2]/div/div/table/tbody/tr[1]/td[4]/ng-form/div/input")).clear();
		//Enter the price
		driver.findElement(By.xpath("//div[@class]/div[3]/div[2]/div/div/table/tbody/tr[1]/td[4]/ng-form/div/input")).sendKeys("212");
		Thread.sleep(1000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		Thread.sleep(3000);
/*		//Click the Optional modifier option
		driver.findElement(By.xpath("//a[contains(.,'Optional Modifier')]/i")).click();
		
		//Click the Modifier Group Option
		driver.findElement(By.xpath("//ng-form[@name='menuOptionalModifiers']/div[2]/div/div[3]/div/div[1]/div/div/div/a")).click();
		//Enter the required modifier group
		driver.findElement(By.xpath("//ng-form[@name='menuOptionalModifiers']/div[2]/div/div[3]/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//ng-form[@name='menuOptionalModifiers']/div[2]/div/div[3]/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//ng-form[@name='menuOptionalModifiers']/div[2]/div/div[3]/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//ng-form[@name='menuOptionalModifiers']/div[2]/div/div[3]/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//ng-form[@name='menuOptionalModifiers']/div[2]/div/div[3]/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Check weather the Override price check box is enabled or not
		if(driver.findElement(By.xpath("//ng-form[@name='menuOptionalModifiers']/div[2]/div/div[3]/div/div[2]/div[1]/div/input")).isEnabled())
		{
			//Click the Override Price
			driver.findElement(By.xpath("//ng-form[@name='menuOptionalModifiers']/div[2]/div/div[3]/div/div[2]/div[1]/div/input")).click();
		}
		
		//Clear the Display order option
		driver.findElement(By.xpath("//ng-form[@name='menuOptionalModifiers']/div[2]/div/div[3]/div/div[2]/div[2]/div/input")).clear();
		//Enter the required number of order
		driver.findElement(By.xpath("//ng-form[@name='menuOptionalModifiers']/div[2]/div/div[3]/div/div[2]/div[2]/div/input")).sendKeys("3");
			
		//Check weather the override price is enabled or not for first option
		if(driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div/div[3]/div[2]/table/tbody/tr[2]/td[2]/input")).isEnabled())
		{
			//Click the override price
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div/div[3]/div[2]/table/tbody/tr[2]/td[2]/input")).click();
			
			Thread.sleep(3000);
			
			//Clear the price for first option
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div/div[3]/div[2]/table/tbody/tr[2]/td[3]/div[1]/input")).clear();
			//Enter the required price
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div/div[3]/div[2]/table/tbody/tr[2]/td[3]/div[1]/input")).sendKeys("111");
			
			//Clear the price for first option
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div/div[3]/div[2]/table/tbody/tr[2]/td[4]/div[1]/input")).clear();
			//Enter the required price
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div/div[3]/div[2]/table/tbody/tr[2]/td[4]/div[1]/input")).sendKeys("111");

			//Clear the price for first option
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div/div[3]/div[2]/table/tbody/tr[2]/td[5]/div[1]/input")).clear();
			//Enter the required price
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div/div[3]/div[2]/table/tbody/tr[2]/td[5]/div[1]/input")).sendKeys("111");

			//Clear the price for first option
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div/div[3]/div[2]/table/tbody/tr[2]/td[6]/div[1]/input")).clear();
			//Enter the required price
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div/div[3]/div[2]/table/tbody/tr[2]/td[6]/div[1]/input")).sendKeys("111");

			
		}*/
		
		Thread.sleep(2500);
		//Press the Previous button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-left")).click();
		
		Thread.sleep(3000);
		//Click the Next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		Thread.sleep(3000);
		//Click the Next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		//Click the Add Mandatory Modifier option
		driver.findElement(By.xpath("//a[contains(.,'Mandatory')]/i")).click();
		
		//Click the Modifier Group option
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div/div[1]/div[1]/div/div/a")).click();
		//Enter the required modifier
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter key
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);	
		
		//Check weather the Override price is enabled or not
		if(driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[1]/div[1]/div[2]/div/input")).isEnabled())
		{
			Thread.sleep(1000);
			//Click the Override price option
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[1]/div[1]/div[2]/div/input")).click();
			
			Thread.sleep(2500);
			//Clear the Price Option
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/div[1]/input")).clear();
			//Enter the price
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/div[1]/input")).sendKeys("150");
			
			//Click the Set Tiered price Check box
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
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr/td/a")).click();
			
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
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[4]/ng-form/div/input")).sendKeys("215");
			
			Thread.sleep(1000);
			//Click the Add tiered price option
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[2]/td/a")).click();
			
			//Clear the Quantity field
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[2]/td[2]/ng-form/div/input")).clear();
			//Enter the Quantity field
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[2]/td[2]/ng-form/div/input")).sendKeys("3");
			
			Thread.sleep(1000);
			//Select the required Price option
			Select setPrice4 = new Select(driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[2]/td[3]/ng-form/div/select")));
			setPrice4.selectByVisibleText("All");
			
			//Clear the price field
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[2]/td[4]/ng-form/div/input")).clear();
			//Enter the required price
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[2]/td[4]/ng-form/div/input")).sendKeys("115");
			
			//Click the Add Tiered Price
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[3]/td/a")).click();
			
			Thread.sleep(3000);
			//Click the close or delete button of last added tiered price option
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[3]/td/a")).click();
			
			//Add the another one Set Tiered Price Option
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/ul/li[2]/a/uib-tab-heading/a")).click();
			
			Thread.sleep(1000);
			//Click the Select Serving Size Level Option
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[3]/div[1]/div[1]/div/div[1]/div/a")).click();
			//Enter the required Serving Size Level
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[3]/div[1]/div[1]/div/div[1]/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[3]/div[1]/div[1]/div/div[1]/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter button
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[3]/div[1]/div[1]/div/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Add tiered price option
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[3]/div[2]/div/div/table/tbody/tr/td/a")).click();
			
			//Clear the Quantity field
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[3]/div[2]/div/div/table/tbody/tr[1]/td[2]/ng-form/div/input")).clear();
			//Enter the required quantity
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[3]/div[2]/div/div/table/tbody/tr[1]/td[2]/ng-form/div/input")).sendKeys("3");
			
			Select setPrice5 = new Select(driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[3]/div[2]/div/div/table/tbody/tr[1]/td[3]/ng-form/div/select")));
			setPrice5.selectByVisibleText("Each");
			
			//Clear the Price field
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[3]/div[2]/div/div/table/tbody/tr[1]/td[4]/ng-form/div/input")).clear();
			//Enter the Price
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/div/div[3]/div[2]/div/div/table/tbody/tr[1]/td[4]/ng-form/div/input")).sendKeys("145");
			
			Thread.sleep(3000);
			//Click the Add Set tiered Price option
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/ul/li[3]/a/uib-tab-heading/a")).click();
			
			Thread.sleep(3000);
			//Delete the last added Set tiered price option
			driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/div/div[2]/div[4]/div/ul/li[3]/a/uib-tab-heading/a")).click();
			
			Thread.sleep(3000);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(2500);

		}
		
		//Clear the minimum Quantity field
		driver.findElement(By.name("minQuantity")).clear();
		//Enter the minimum Quantity
		driver.findElement(By.name("minQuantity")).sendKeys("5");
		
		//Clear the maximum Quantity field
		driver.findElement(By.name("maxQuantity")).clear();
		//Enter the maximum Quantity
		driver.findElement(By.name("maxQuantity")).sendKeys("6");
		
		//Clear the Display order field
		driver.findElement(By.name("ordinal")).clear();
		//Enter the Display order
		driver.findElement(By.name("ordinal")).sendKeys("2");
		
		for(int i = 1;i <= 3;i++){driver.findElement(By.tagName("html")).sendKeys(Keys.END);}
		Thread.sleep(3500);
		//Click the Set Tiered price Check box
		driver.findElement(By.xpath("//label[contains(.,'Set Tiered Price')]/../div/input")).click();
		
		//Click the Add Set Tiered price
		driver.findElement(By.xpath("//a[contains(.,'Set Tiered Price')]/../a/i")).click();
		
		Thread.sleep(1000);
		//Click the Serving Size Level field
		driver.findElement(By.xpath("//ul/../div/div[2]/div/div/div/div/div/a/span[.='Select serving Level']")).click();
		//Enter the required SErving Size Level
		driver.findElement(By.xpath("//ul/../div/div[2]/div/div/div/div/div/a/span[.='Select serving Level']/../../div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//ul/../div/div[2]/div/div/div/div/div/a/span[.='Select serving Level']/../../div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Add tiered price option
		driver.findElement(By.xpath("//a[contains(.,'Add tiered price')]")).click();
		
		//Clear the Quantity field
		driver.findElement(By.xpath("//a[contains(.,'Add tiered price')]/../../../tr[1]/td[2]/ng-form/div/input")).clear();
		//Enter the Quantity field
		driver.findElement(By.xpath("//a[contains(.,'Add tiered price')]/../../../tr[1]/td[2]/ng-form/div/input")).sendKeys("2");
		
		Thread.sleep(1000);
		//Select the required Price option
		Select setPrice3 = new Select(driver.findElement(By.xpath("//a[contains(.,'Add tiered price')]/../../../tr[1]/td[3]/ng-form/div/select")));
		setPrice3.selectByVisibleText("Each");
		
		//Clear the price field
		driver.findElement(By.xpath("//a[contains(.,'Add tiered price')]/../../../tr[1]/td[4]/ng-form/div/input")).clear();
		//Enter the required price
		driver.findElement(By.xpath("//a[contains(.,'Add tiered price')]/../../../tr[1]/td[4]/ng-form/div/input")).sendKeys("215");
		
		
		//Click the Add Tiered Price
		driver.findElement(By.xpath("//a[contains(.,'Add tiered price')]")).click();
		
		Thread.sleep(3000);
		//Click the close or delete button of last added tiered price option
		driver.findElement(By.xpath("//a[contains(.,'Add tiered price')]/../../../tr[2]/td/a")).click();
		
		
		Thread.sleep(3000);
		//Click the Add Set tiered Price option
		driver.findElement(By.xpath("//a[contains(.,'Set Tiered Price')]/../a/i")).click();
		
		Thread.sleep(3000);
		//Delete the last added Set tiered price option
		driver.findElement(By.xpath("//span[contains(.,'Add Tiered')]/../a/i")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(2500);


		//Click the add mandatory modifier field
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/ul/li[2]/a/uib-tab-heading/a")).click();
		
		Thread.sleep(2500);
		//Delete the last added mandatory modifier field
		driver.findElement(By.xpath("//ng-form[@name='menuMandatoryModifiers']/div[2]/ul/li[2]/a/uib-tab-heading/a")).click();

		Thread.sleep(2500);
		//Press the Previous button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-left")).click();
		
		Thread.sleep(3000);
		//Click the Next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4501);
		String s1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText();System.out.println(s1);
		//Check whether the new form loaded or not
		if(s1.equalsIgnoreCase("Menu item saved successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is saved Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is saved Failed");
		}
		Thread.sleep(3000);
		
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("ProductsItems_Name"));
		driver.findElement(By.xpath("//label[contains(.,'Search')]/../a/span")).click();
		
		String menuItemName = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[2]")).getText();
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
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=5)
	public void Product_And_Item_method_editMenuItem(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("ProductsItems_Name"));
		driver.findElement(By.xpath("//label[contains(.,'Search')]/../a/span")).click();
		
		//Click the Edit icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
	
		Thread.sleep(3000);
		//Clear the Name field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).clear();
		//Enter the Name			 
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"1");
				
		Thread.sleep(2500);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(3000);
		
		//Clear the PLU CODE
		driver.findElement(By.name("pluCode")).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name("pluCode")).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"1");
		
		//Click the Category Option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div/div[1]/div/a")).click();
		//Enter the required Category
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div/div[1]/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Enable or Disable the Cut and Modify option
		driver.findElement(By.xpath("//label/span[contains(.,'Cut And Modify')]")).click();
		
		Select measureType =  new Select(driver.findElement(By.name("measureType")));
		measureType.selectByVisibleText("Menu Item");
		
		Thread.sleep(2500);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		//Click the Serving size Level on the price field
		driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/div/a")).click();
		//Enter the required serving size level
		driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Clear the Price option
		driver.findElement(By.name("price")).clear();
		//Enter the required price
		driver.findElement(By.name("price")).sendKeys("250");
		
		Thread.sleep(3000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Enable or disable the Kitchen Printer
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[1]/div[2]/label/span")).click();
		Thread.sleep(1000);
		//Enable or disable the Label Printer
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[1]/div[3]/label/span")).click();
		Thread.sleep(1000);
		//Enable or disable the Restrict printer
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[1]/div[4]/label/span")).click();
		

		Thread.sleep(2500);
		//Check whether the EBT menu item is enabled or not
		if(driver.findElement(By.xpath("//ng-form/div[2]/div[7]/div/input")).isSelected())
		{
			test.log(LogStatus.PASS, "EBT menu item is in enabled status");
		}

		else
		{
			test.log(LogStatus.FAIL, "EBT menu item is in disabled status");
		}
		
		Thread.sleep(3000);driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		//Click the Select image from gallery option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[9]/div/div/div[2]/div[2]/a[1]/i")).click();
		
		Thread.sleep(15000);
		//Click the required image from the Gallery
		driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/form/div[2]/div/div/div[2]/div[1]/a/img")).click();
		Thread.sleep(3000);
		
		Thread.sleep(2500);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys("Days of Week");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Days of Week Option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[11]/div/div/ul")).click();
		//Enter the required DAY for Searching
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[11]/div/div/ul/li/input")).sendKeys("WEDNESDAY");
		//Press the Enter key after enter the keyword
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[11]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Enable or disable the Restriction Time Period option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[12]/div/label/span")).click();
		Thread.sleep(2500);
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[13]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[13]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[13]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}

		
		//Click the update
		driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
		Thread.sleep(3000);
		
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Menu item updated successfully"))
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
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("ProductsItems_Name")+"1");
		driver.findElement(By.xpath("//label[contains(.,'Search')]/../a/span")).click();
		
		String menuItemName = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[2]")).getText();
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
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("ProductsItems_Name")+"1");
		driver.findElement(By.xpath("//label[contains(.,'Search')]/../a/span")).click();
		
		//Click the Delete button
		driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
		//Click the Yes button in the popup
		Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
		Thread.sleep(3000);
		//Check the menu item deleted or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Menu item inactivated successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is deleted Failed");
		}
		Thread.sleep(3000);
		
		//Click the Active Button
		driver.findElement(By.xpath("//input[@ng-model='activeStatus']")).click();
		Thread.sleep(6000);
		
		//Click the success button
		driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
		Thread.sleep(1000);
		
		//Click the Yes button in the popup
		Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
		Thread.sleep(3000);
		
		//Check the menu item activated or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Menu item activated successfully"))
		{
			test.log(LogStatus.PASS, "Menu Item is activated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Menu Item is activated Failed");
		}
		
		Thread.sleep(5000);
		//Click the Active Button
		driver.findElement(By.xpath("//input[@ng-model='activeStatus']")).click();
		Thread.sleep(3000);
	}
		
	@Test(enabled=false,priority=7)
	public void Product_And_Item_method_addMenuItemByCopyMenuItem(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(8000);
		//Click on the Add Menu Item option
		driver.findElement(By.id("menuItem")).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//span[.='NEW MENU ITEM']")).getText().equalsIgnoreCase("NEW MENU ITEM"))
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
		driver.findElement(By.xpath("//label[contains(.,'Menu Items')]/../div/a")).click();Thread.sleep(3000);
		//Enter the REquired menu Item
		driver.findElement(By.xpath("//label[contains(.,'Menu Items')]/../div/div/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"1");
		//Press the Enter Key
		driver.findElement(By.xpath("//label[contains(.,'Menu Items')]/../div/div/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath("//a[contains(.,'Copy')]")).click();
		
		Thread.sleep(2500);
		//Click the Clear Option  
		driver.findElement(By.xpath("//a[contains(.,'Clear')]")).click();
		
		//Click the Menu Item field
		driver.findElement(By.xpath("//label[contains(.,'Menu Items')]/../div/a")).click();
		//Enter the REquired menu Item
		driver.findElement(By.xpath("//label[contains(.,'Menu Items')]/../div/div/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"1");
		//Press the Enter Key
		driver.findElement(By.xpath("//label[contains(.,'Menu Items')]/../div/div/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath("//a[contains(.,'Copy')]")).click();
	
		//Clear the Name field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"10");
		
		Thread.sleep(2500);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		
		Thread.sleep(3000);
		//Clear the PLU CODE
		driver.findElement(By.name("pluCode")).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name("pluCode")).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"2");
		
		Thread.sleep(3000);
		//Select the required Level Option
		Select level = new Select(driver.findElement(By.name("level")));
		level.selectByVisibleText("Sub Category");
		
		Thread.sleep(2500);
		//Click the Category Field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[8]/div/div/a")).click();
		//Enter the required category
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[8]/div[1]/div/div/div/input")).click();
		//Press the Enter key
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[8]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);	
		Thread.sleep(2500);
		
		//Click the add sub category button
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[9]/div[2]/a/i")).click();
		
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
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/form/div[1]/div/input")).sendKeys(Utility.getProperty("ProductsItems_Add_Sub_Category_Name"));

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
		driver.findElement(By.xpath("//a[@class='btn btn-success btn-small ng-binding']")).click();
		
		Thread.sleep(8000);
		Select measureType= new Select(driver.findElement(By.name("measureType")));
		measureType.selectByVisibleText("Open Item");
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Thread.sleep(3000);
		//Click the Serving size Level on the price field
		driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/div/a")).click();
		//Enter the required serving size level
		driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/div/div/div/input")).sendKeys(Keys.ENTER);		Thread.sleep(3000);
		
		//Clear the price field
		driver.findElement(By.xpath("//input[@name='price']")).clear();
		//Enter the required price
		driver.findElement(By.xpath("//input[@name='price']")).sendKeys("120");
		
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys("Days of Month");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Required Date form the Calendar
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[11]/div/table/tbody/tr[2]/td[7]/button")).click();
		Thread.sleep(2500);
		
		//Enable or Disable the Restriction Months option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[12]/div/label/span")).click();
		
		Thread.sleep(2500);
		//Click the months field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[13]/div/div/ul")).click();
		//Enter the Required month
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[13]/div/div/ul/li/input")).sendKeys("MAY");
		//Press the Enter Key
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[13]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		//Enable or Disable the Restriction Time option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[14]/div/label/span")).click();
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[15]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[15]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[15]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		Thread.sleep(2500);
		
		//Click the next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		Thread.sleep(3000);

		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4501);
		String s1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText();System.out.println(s1);
		//Check whether the new form loaded or not
		if(s1.equalsIgnoreCase("Menu item saved successfully"))
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
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(8000);
		//Click on the Add Menu Item option
		driver.findElement(By.id("menuItem")).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//span[.='NEW MENU ITEM']")).getText().equalsIgnoreCase("NEW MENU ITEM"))
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
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/a")).click();Thread.sleep(2500);
		//Enter the REquired menu Item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys("COKE");
		//Press the Enter Key
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/span/div/a")).click();
			
		//Clear the Name field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"10");
				
		//Click the Cancel button
		driver.findElement(By.xpath("//a[.='Cancel']")).click();
		Thread.sleep(3000);
		
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//a[.='Menu Items']")).getText().equalsIgnoreCase("Menu items"))
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
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id("menuItem")).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//span[.='NEW MENU ITEM']")).getText().equalsIgnoreCase("NEW MENU ITEM"))
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
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/a")).click();Thread.sleep(1000);
		//Enter the REquired menu Item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys("COKE");
		//Press the Enter Key
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/span/div/a")).click();
			
		//Clear the Name field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"10");
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		//Click the Cancel button
		driver.findElement(By.xpath("//a[.='Cancel']")).click();
		Thread.sleep(3000);
		
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//a[.='Menu Items']")).getText().equalsIgnoreCase("Menu items"))
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
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id("menuItem")).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//span[.='NEW MENU ITEM']")).getText().equalsIgnoreCase("NEW MENU ITEM"))
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
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/a")).click();Thread.sleep(2500);
		//Enter the REquired menu Item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys("COKE");
		//Press the Enter Key
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/span/div/a")).click();
			
		//Clear the Name field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"10");
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		//Click the Cancel button
		driver.findElement(By.xpath("//a[.='Cancel']")).click();
		Thread.sleep(3000);
		
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//a[.='Menu Items']")).getText().equalsIgnoreCase("Menu items"))
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
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id("menuItem")).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//span[.='NEW MENU ITEM']")).getText().equalsIgnoreCase("NEW MENU ITEM"))
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
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/a")).click();Thread.sleep(2500);
		//Enter the REquired menu Item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys("COKE");
		//Press the Enter Key
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/span/div/a")).click();
			
		//Clear the Name field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"10");
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		//Click the Cancel button
		driver.findElement(By.xpath("//a[.='Cancel']")).click();
		Thread.sleep(3000);
		
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//a[.='Menu Items']")).getText().equalsIgnoreCase("Menu items"))
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
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id("menuItem")).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//span[.='NEW MENU ITEM']")).getText().equalsIgnoreCase("NEW MENU ITEM"))
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
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/a")).click();Thread.sleep(2500);
		//Enter the REquired menu Item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"10");
		//Press the Enter Key
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/span/div/a")).click();
			
		//Clear the Name field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"11");
		
		Thread.sleep(3000);
		//Clear the PLU CODE
		driver.findElement(By.name("pluCode")).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name("pluCode")).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"3");
		
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys("Date Range");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Clear the From date field
		driver.findElement(By.name("fromDate")).clear();
		//Enter the required from Date
		driver.findElement(By.name("fromDate")).sendKeys("25-May-2030");
		//Clear the To Date field
		driver.findElement(By.name("endDate")).clear();
		//Enter the required To Date
		driver.findElement(By.name("endDate")).sendKeys("28-May-2030");

		//Enable or Disable the Restriction Days Option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[13]/div/label/span")).click();
		
		Thread.sleep(2500);
		//Click the Days of a Week option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[14]/div/div/ul")).click();
		//Enter the Required Date
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[14]/div/div/ul/li/input")).sendKeys("FRIDAY");
		//Press the Enter Key
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[14]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		//Enable or Disable the Restriction Time option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[15]/div/label/span")).click();
		for(int i = 1; i <= 6; i++){driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);}
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[16]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[16]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[16]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		
		//Click the Cancel button
		driver.findElement(By.xpath("//button[@type='submit']")).click();driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4501);
		String s1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText();System.out.println(s1);
		//Check whether the new form loaded or not
		if(s1.equalsIgnoreCase("Menu item saved successfully"))
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
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id("menuItem")).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//span[.='NEW MENU ITEM']")).getText().equalsIgnoreCase("NEW MENU ITEM"))
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
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/a")).click();Thread.sleep(2500);
		//Enter the REquired menu Item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"11");
		//Press the Enter Key
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/span/div/a")).click();
			
		//Clear the Name field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"12");
				
		Thread.sleep(3000);
		//Clear the PLU CODE
		driver.findElement(By.name("pluCode")).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name("pluCode")).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"4");
		
		Thread.sleep(3000);
		//Select the required measure type
		Select measureType = new Select(driver.findElement(By.name("measureType")));
		measureType.selectByVisibleText("SCALE");
		
		Thread.sleep(5000);
		//Click the taregroup option
		driver.findElement(By.xpath("//select[@name='tareGroup']/../div/a")).click();
		//Enter the required Taregroup
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
		}
		
		Thread.sleep(3000);
		//Click the Serving size Level on the price field
		driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/div/a")).click();
		//Enter the required serving size level
		driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/div/div/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Clear the price option
		driver.findElement(By.name("price")).clear();
		//Enter the required price
		driver.findElement(By.name("price")).sendKeys("155");
		
		Thread.sleep(3000);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys("Specific date");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Clear the Date field
		driver.findElement(By.name("date")).clear();
		//Enter the required date
		driver.findElement(By.name("date")).sendKeys("15-May-2030");
		
		//Enable or Disable the Restriction Time option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[12]/div/label/span")).click();
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[13]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[13]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[13]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		
		//Click the save and continue button
		driver.findElement(By.xpath("//span[.='Save And Continue']")).click();driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4501);
		String s1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText();System.out.println(s1);
		//Check whether the new form loaded or not
		if(s1.equalsIgnoreCase("Menu item saved successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is saved Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is saved Failed");
		}
		Thread.sleep(10000);
		
		//Click the Menu Item field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/a")).click();
		//Enter the REquired menu Item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"12");
		//Press the Enter Key
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/span/div/a")).click();
			
		//Clear the Name field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"13");
		
		
		Thread.sleep(2500);
		//Clear the PLU CODE
		driver.findElement(By.name("pluCode")).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name("pluCode")).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"5");
		
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys("Start date time & end date time");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[10]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Clear the From date field
		driver.findElement(By.name("fromDate")).clear();
		//Enter the required from Date
		driver.findElement(By.name("fromDate")).sendKeys("25-May-2019");
		//Clear the To Date field
		driver.findElement(By.name("endDate")).clear();
		//Enter the required To Date
		driver.findElement(By.name("endDate")).sendKeys("28-May-2019");
		
		//Enable or Disable the Restriction Days Option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[13]/div/label/span")).click();
		
		Thread.sleep(2500);
		//Click the Days of a Week option
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[14]/div/div/ul")).click();
		//Enter the Required Date
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[14]/div/div/ul/li/input")).sendKeys("FRIDAY");
		//Press the Enter Key
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[14]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[15]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[15]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2500);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[2]/div[15]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}

		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		Thread.sleep(8000);
		//Click the Save And Continue button
		driver.findElement(By.xpath("//span[.='Save And Continue']")).click();driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4501);
		String s2 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText();System.out.println(s2);
		//Check whether the new form loaded or not
		if(s2.equalsIgnoreCase("Menu item saved successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is saved Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is saved Failed");
		}
		Thread.sleep(10000);
		
		//Click the Menu Item field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/a")).click();
		//Enter the REquired menu Item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"13");
		//Press the Enter Key
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/span/div/a")).click();
			
		//Clear the Name field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"14");
				
		Thread.sleep(3000);
		//Clear the PLU CODE
		driver.findElement(By.name("pluCode")).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name("pluCode")).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"6");
		
		Thread.sleep(5000);
		//Click the next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		Thread.sleep(5000);
		//Click the next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		Thread.sleep(8000);
		//Click the Save And Continue button
		driver.findElement(By.xpath("//span[.='Save And Continue']")).click();driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4501);
		String s3 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText();System.out.println(s3);
		//Check whether the new form loaded or not
		if(s3.equalsIgnoreCase("Menu item saved successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is saved Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is saved Failed");
		}
		Thread.sleep(10000);
		
		//Click the Menu Item field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/a")).click();
		//Enter the REquired menu Item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"14");
		//Press the Enter Key
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/span/div/a")).click();
			
		//Clear the Name field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"15");
			
		Thread.sleep(3000);
		//Clear the PLU CODE
		driver.findElement(By.name("pluCode")).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name("pluCode")).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"7");
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		Thread.sleep(8000);
		//Click the Save And Continue button
		driver.findElement(By.xpath("//span[.='Save And Continue']")).click();driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4501);
		String s4 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText();System.out.println(s4);
		//Check whether the new form loaded or not
		if(s4.equalsIgnoreCase("Menu item saved successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is saved Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is saved Failed");
		}
		Thread.sleep(10000);

		//Click the Menu Item field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/a")).click();
		//Enter the REquired menu Item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"15");
		//Press the Enter Key
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(2500);
		
		//Press the Copy button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[1]/span/div/a")).click();
			
		//Clear the Name field
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//ng-form[@name='menuBasicInfo']/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("ProductsItems_Name")+"16");
				
		Thread.sleep(2500);
		//Clear the PLU CODE
		driver.findElement(By.name("pluCode")).clear();
		//Enter the required PLU CODE
		driver.findElement(By.name("pluCode")).sendKeys(Utility.getProperty("ProductsItems_PLU_Code")+"8");
		
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
				
		Thread.sleep(3000);
		//Click the next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		Thread.sleep(8000);
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(4501);
		String s5 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText();System.out.println(s5);
		//Check whether the new form loaded or not
		if(s5.equalsIgnoreCase("Menu item saved successfully"))
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