package epicList_Chrome_Account_User;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Add_Product_Item_Price_Level {
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Add_Product_Item_Price_Level");

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
			Browser_Account_Level_User a = new Browser_Account_Level_User();
			a.UPOS_login(driver, test);
		}
		else 			
		{
			Browser_Account_Level_User a = new Browser_Account_Level_User();
			a.Linga_login(driver, test);
		}
	}
		
	@Test(priority=100)
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
		if(driver.findElement(By.xpath("//div[@id='x-content-band-1']/div/div[2]/h2")).getText().equalsIgnoreCase("Account Login"))
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
		openProductsItems(driver);
		refreshMenuItemPage(driver);
		pagination(driver);
		addMenuItem(driver);
		editMenuItem(driver);
		editExistingMenuItem_Single_And_Multi_Serving_Size(driver);
		Thread.sleep(1500);
	}

	@Test(priority=2,enabled = false)
	public void openProductsItems(WebDriver driver) throws Exception
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
*/		Thread.sleep(3000);
		//Enter the Users URL
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"menus");
		Thread.sleep(3000);
		
		Thread.sleep(5000);
		try
		{
		
		//Check Menu items page opened or not
		if(driver.findElement(By.xpath("//a[.='Menu items']")).getText().equalsIgnoreCase("Menu items"))
		{
			test.log(LogStatus.PASS, "Menu Items page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Menu Items page loaded Failed");
			
			
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
	}
	
	@Test(priority=3, enabled=false)
	public void refreshMenuItemPage(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(5000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);
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
	
	@Test(priority=3, enabled=false)
	public void pagination(WebDriver driver) throws InterruptedException
	{
				
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		Thread.sleep(1000);
		
		//Click the Pagination option as 10
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/div/div/div/ul[1]/li[2]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 10 in Menu Item");
		//Create the web element for menu item Table
		List<WebElement> results = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table"));
		for (WebElement result : results){						
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available in Menu Item");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 15
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/div/div/div/ul[1]/li[3]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 15 in Menu Item");
		//Create the web element for menu item Table
		List<WebElement> results1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table"));
		for (WebElement result : results1){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available in Menu Item");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 20
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/div/div/div/ul[1]/li[4]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 20 in Menu Item");
		//Create the web element for menu item Table
		List<WebElement> results2 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table"));
		for (WebElement result : results2){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available in Menu Item");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 5
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/div/div/div/ul[1]/li[1]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 5 in Menu Item");
		//Create the web element for menu item Table
		List<WebElement> results3 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table"));
		for (WebElement result : results3){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available in Menu Item");
		}
		
		 Thread.sleep(3000);
		  
	}
	
	@Test(priority=4, enabled=false)
	public void addMenuItem(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		
		//Click the new Menu Item button
		driver.findElement(By.id("menuItem")).click();
		
		Thread.sleep(3000);
		//Clear the Name field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[2]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[2]/div/input")).sendKeys("s"+Utility.getProperty("ProductsItems_Name"));
		Thread.sleep(2000);
		//Clear the Secondary Name field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[3]/div/input")).clear();
		//Enter the Secondary Name
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[3]/div/input")).sendKeys("s"+Utility.getProperty("ProductsItems_SecName"));
		Thread.sleep(1000);
		
		//Clear the Description field
		driver.findElement(By.name("description")).clear();
		//Enter the required description
		driver.findElement(By.name("description")).sendKeys("TESTING DESCRIPTION");
		
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
		
		//Click the Category Option
		driver.findElement(By.xpath("//form[@name='menuItemCreation']/div/ng-form/div[1]/div[8]/div[1]/div/a")).click();
		//Click the input text
		driver.findElement(By.xpath("//form[@name='menuItemCreation']/div/ng-form/div[1]/div[8]/div[1]/div/div/div/input")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//form[@name='menuItemCreation']/div/ng-form/div[1]/div[8]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Check whether the Price Level Settings is Updated Or Not
		if(driver.findElement(By.xpath("//input[@ng-model='menu.priceLevelSettings']")).isEnabled())
		{
			test.log(LogStatus.PASS, "Price Level Settings option is initially disabled");
			
			//Enable the Price Level Settings
			driver.findElement(By.xpath("//span[contains(.,'Price Level Settings')]")).click();
		}
		else
		{
			test.log(LogStatus.FAIL, "Price Level Settings option is initially enabled");
		}
		
		//Create the Object
		Select measureType = new Select(driver.findElement(By.name("measureType")));
		measureType.selectByValue("Serving Size");
		Thread.sleep(3000);
		
		for(int i = 1; i <= 10; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		//Click the Serving Size Level Box
		driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/div/a")).click();
		//Click the input text box
		driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/div/div/div/input")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the Price value
		driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/input[@name='price']")).clear();
		//Enter the amount
		driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/input[@name='price']")).sendKeys("100");
		
		for(int i = 3; i <= 21; i++)
		{
			//Click the add button of prizing level
			driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[1]/th[3]/a")).click();
			
		}
		
		for(int i = 3; i <= 21; i++)
		{	
			Thread.sleep(1000);
			//Clear the Price value
			driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr["+i+"]/td[2]/ng-form/div/input[@name='price']")).clear();
			//Enter the amount
			driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr["+i+"]/td[2]/ng-form/div/input[@name='price']")).sendKeys(i+"00");
		}
		
		//Click the Serving Size Level Option
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr/td/a")).click();
		
		//Click the Serving Size Level Box
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td/ng-form/div/div/a")).click();
		//Click the input text box
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td/ng-form/div/div/div/div/input")).click();
		//Click the input text box
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td/ng-form/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td/ng-form/div/div/div/div/input")).sendKeys(Keys.ENTER);

		//Clear the Price Level
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[3]/table/tbody/tr/td[2]/ng-form/div/input[@name='price']")).clear();
		//Enter the Required Amount
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[3]/table/tbody/tr/td[2]/ng-form/div/input[@name='price']")).sendKeys("500");
		
		Thread.sleep(3000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		//Click the Serving Size Level Option
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr/td/a")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
		//Click the Serving Size Level Box
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[3]/td/ng-form/div/div/a")).click();
		Thread.sleep(2000);
		//Click the input text box
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[3]/td/ng-form/div/div/div/div/input")).click();
		Thread.sleep(3000);
		//Click the input text box
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[3]/td/ng-form/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[3]/td/ng-form/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		//Press the Enter button
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[3]/td/ng-form/div/div/div/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(8000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		Thread.sleep(3000);
		//Clear the Price Level
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[3]/td[3]/table/tbody/tr/td[2]/ng-form/div/input[@name='price']")).clear();
		//Enter the Required Amount
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[3]/td[3]/table/tbody/tr/td[2]/ng-form/div/input[@name='price']")).sendKeys("500");
		Thread.sleep(5000);

		//Click Home button
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(2000);
		//Click the Label Printers Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[4]/div[1]/div/ul")).click();
		//Click the input field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[4]/div[1]/div/ul/li/input")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[4]/div[1]/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		//Click the Restrict Service Types Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[5]/div[1]/div/ul")).click();
		//Click the input field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[5]/div[1]/div/ul/li/input")).sendKeys("DELIVERY");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[5]/div[1]/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		//Press the END button
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		
		//Press the Right Arrow
		driver.findElement(By.xpath("//i[@class='fa fa-arrow-right']")).click();
		
		Thread.sleep(10000);
		//Click the plus or add symbol
		driver.findElement(By.cssSelector("i.fa.fa-plus-circle")).click();
				
		Thread.sleep(2000);
		//Click the modifier group field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[1]/div/div/div/div[2]/div[1]/div[1]/div/a")).click();
		//Enter the required modifier group
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[1]/div/div/div/div[2]/div[1]/div[1]/div/div/div/input")).click();
		//Press the Enter key
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[1]/div/div/div/div[2]/div[1]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Click the modifier option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[1]/div/div/div/div[2]/div[1]/div[2]/div/a")).click();
		//Enter the required modifier
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[1]/div/div/div/div[2]/div[1]/div[2]/div/div/div/input")).click();
		//Press the Enter key
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[1]/div/div/div/div[2]/div[1]/div[2]/div/div/div/input")).sendKeys(Keys.ENTER);	
		
		Thread.sleep(2000);
		//Click the Alternate Modifier group
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[1]/div/div/div/div[2]/div[2]/label/span")).click();
		
		Thread.sleep(1000);
		//Select the alternate modifier group option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[1]/div/div/div/div[2]/div[3]/div/div/a")).click();
		//Enter the required modifier
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[1]/div/div/div/div[2]/div[3]/div/div/div/div/input")).click();
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[1]/div/div/div/div[2]/div[3]/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[1]/div/div/div/div[2]/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Enable or Disable the Override prefix up-charges
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[1]/div/div/div/div[2]/div[4]/label/span")).click();
		
		Thread.sleep(1000);
		//Clear the price of prefix amount
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[1]/div/div/div/div[2]/div[5]/table/tbody/tr[2]/td[2]/div[2]/input")).clear();
		//Give the required amount
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[1]/div/div/div/div[2]/div[5]/table/tbody/tr[2]/td[2]/div[2]/input")).sendKeys("120");

		//Press the Right Arrow
		driver.findElement(By.xpath("//i[@class='fa fa-arrow-right']")).click();
		
		//Click the add Optional Modifier option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/ul/li/a/uib-tab-heading/a")).click();
		
		Thread.sleep(2000);
		//Click the modifier group option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div/div[1]/div/div/div/a")).click();
		//Enter the required modifier group option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div/div[1]/div/div/div/div/div/input")).click();
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Check weather the Override price check box is enabled or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[1]/div[2]/div[1]/div/input")).isEnabled())
		{
		//Click the Override price option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[1]/div[2]/div[1]/div/input")).click();
		}

		//Clear the Display Order option
		driver.findElement(By.name("ordinal")).clear();
		//Enter the required number option
		driver.findElement(By.name("ordinal")).sendKeys("3");
		
		//Clear the Price option
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[2]/table/tbody/tr[2]/td[5]/div[1]/input")).clear();
		//Enter the required price
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[2]/table/tbody/tr[2]/td[5]/div[1]/input")).sendKeys("250");
		
		//Click the Set Tiered Price Check box
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[3]/div/div/div/input")).click();
		
		Thread.sleep(3000);
		//Click the Set Tiered option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/ul/li/a/uib-tab-heading/a")).click();
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		
		Thread.sleep(3000);
		//Click the Serving Size Level Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[1]/div[1]/div/div[1]/div/a")).click();
		//Enter the required serving size level
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[1]/div[1]/div/div[1]/div/div/div/input")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[1]/div[1]/div/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Add Tiered price option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr/td/a")).click();
		
		//Clear the Quantity option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[2]/ng-form/div/input")).clear();
		//Give the required quantity
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[2]/ng-form/div/input")).sendKeys("1");
		
		Thread.sleep(2000);
		//Select the required option to set the price
		Select setPrice = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[3]/ng-form/div/select")));
		setPrice.selectByVisibleText("Each");
		
		//Clear the price option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[4]/ng-form/div/input")).clear();
		//Enter the required price
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[4]/ng-form/div/input")).sendKeys("350");
		
		Thread.sleep(3000);
		//Click the Next button
		driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
		
		//Click the Add Mandatory Modifier option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/ul/li/a/uib-tab-heading/a")).click();
		
		//Click the Modifier Group option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div/div[1]/div[1]/div/div/a")).click();
		//Enter the required modifier
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div/div[1]/div[1]/div/div/div/div/input")).sendKeys("TOPPINGS");
		//Press the Enter key
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);	
		
		//Check weather the Override price is enabled or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[1]/div[1]/div[2]/div/input")).isEnabled())
		{
			Thread.sleep(1000);
			//Click the Override price option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[1]/div[1]/div[2]/div/input")).click();
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
		
		Thread.sleep(2000);
		//Clear the Price Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/div[1]/input")).clear();
		//Enter the price
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[2]/table/tbody/tr[2]/td[2]/div[1]/input")).sendKeys("150");
		
		//Click the Set Tiered price Check box
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[3]/div/div/div/input")).click();
		
		//Click the Add Set Tiered price
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/ul/li/a/uib-tab-heading/a")).click();
		
		Thread.sleep(1000);
		//Click the Serving Size Level field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[1]/div[1]/div/div[1]/div/a")).click();
		//Enter the required SErving Size Level
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[1]/div[1]/div/div[1]/div/div/div/input")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[1]/div[1]/div/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Add tiered price option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr/td/a")).click();
		
		//Clear the Quantity field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[2]/ng-form/div/input")).clear();
		//Enter the Quantity field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[2]/ng-form/div/input")).sendKeys("2");
		
		Thread.sleep(1000);
		//Select the required Price option
		Select setPrice3 = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[3]/ng-form/div/select")));
		setPrice3.selectByVisibleText("Each");
		
		//Clear the price field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[4]/ng-form/div/input")).clear();
		//Enter the required price
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div/div[2]/div[4]/div/div[2]/div[2]/form/div/ng-form/div[2]/div/div[2]/div[4]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[4]/ng-form/div/input")).sendKeys("215");

		//Click the Save and Publish Option
		driver.findElement(By.xpath("//span[contains(.,'Save And Publish')]")).click();
		Thread.sleep(3500);
		//Check whether the new form loaded or not
		Thread.sleep(1500);if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Menu item saved and published successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is saved and published Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is saved and published Failed");
		}
		Thread.sleep(3000);
	}
	
	@Test(priority=5, enabled=false)
	public void editMenuItem(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("s"+Utility.getProperty("ProductsItems_Name"));
		Thread.sleep(1000);
		//Click the Search Icon
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		Thread.sleep(5000);
		//Click the Edit icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		for(int i = 2; i <= 21; i++)
		{	
			Thread.sleep(1000);
			//Clear the Price value
			driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr["+i+"]/td[2]/ng-form/div/input[@name='price']")).clear();
			//Enter the amount
			driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr["+i+"]/td[2]/ng-form/div/input[@name='price']")).sendKeys(i+"50");
		}
	
		WebElement upAmt1 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[2]/td[2]/ng-form/div/input[@name='price']"));
		String amt1 = upAmt1.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal1 = Float.parseFloat(amt1);
		
		WebElement upAmt2 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[3]/td[2]/ng-form/div/input[@name='price']"));
		String amt2 = upAmt2.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal2 = Float.parseFloat(amt2);
		
		WebElement upAmt3 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[4]/td[2]/ng-form/div/input[@name='price']"));
		String amt3 = upAmt3.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal3 = Float.parseFloat(amt3);
		
		WebElement upAmt4 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[5]/td[2]/ng-form/div/input[@name='price']"));
		String amt4 = upAmt4.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal4 = Float.parseFloat(amt4);
		
		WebElement upAmt5 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[6]/td[2]/ng-form/div/input[@name='price']"));
		String amt5 = upAmt5.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal5 = Float.parseFloat(amt5);
		
		WebElement upAmt6 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[7]/td[2]/ng-form/div/input[@name='price']"));
		String amt6 = upAmt6.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal6 = Float.parseFloat(amt6);
		
		WebElement upAmt7 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[8]/td[2]/ng-form/div/input[@name='price']"));
		String amt7 = upAmt7.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal7 = Float.parseFloat(amt7);
		
		WebElement upAmt8 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[9]/td[2]/ng-form/div/input[@name='price']"));
		String amt8 = upAmt8.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal8 = Float.parseFloat(amt8);
		
		WebElement upAmt9 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[10]/td[2]/ng-form/div/input[@name='price']"));
		String amt9 = upAmt9.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal9 = Float.parseFloat(amt9);
		
		WebElement upAmt10 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[11]/td[2]/ng-form/div/input[@name='price']"));
		String amt10 = upAmt10.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal10 = Float.parseFloat(amt10);
		
		WebElement upAmt11 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[12]/td[2]/ng-form/div/input[@name='price']"));
		String amt11 = upAmt11.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal11 = Float.parseFloat(amt11);
		
		WebElement upAmt12 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[13]/td[2]/ng-form/div/input[@name='price']"));
		String amt12 = upAmt12.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal12 = Float.parseFloat(amt12);
		
		WebElement upAmt13 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[14]/td[2]/ng-form/div/input[@name='price']"));
		String amt13 = upAmt13.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal13 = Float.parseFloat(amt13);
		
		WebElement upAmt14 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[15]/td[2]/ng-form/div/input[@name='price']"));
		String amt14 = upAmt14.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal14 = Float.parseFloat(amt14);
		
		WebElement upAmt15 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[16]/td[2]/ng-form/div/input[@name='price']"));
		String amt15 = upAmt15.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal15 = Float.parseFloat(amt15);
		
		WebElement upAmt16 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[17]/td[2]/ng-form/div/input[@name='price']"));
		String amt16 = upAmt16.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal16 = Float.parseFloat(amt16);
		
		WebElement upAmt17 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[18]/td[2]/ng-form/div/input[@name='price']"));
		String amt17 = upAmt17.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal17 = Float.parseFloat(amt17);
		
		WebElement upAmt18 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[19]/td[2]/ng-form/div/input[@name='price']"));
		String amt18 = upAmt18.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal18 = Float.parseFloat(amt18);
		
		WebElement upAmt19 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[20]/td[2]/ng-form/div/input[@name='price']"));
		String amt19 = upAmt19.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal19 = Float.parseFloat(amt19);
		
		WebElement upAmt20 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[21]/td[2]/ng-form/div/input[@name='price']"));
		String amt20 = upAmt20.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float actAmtVal20 = Float.parseFloat(amt20);
		Thread.sleep(3000);
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		Thread.sleep(3000);
		//Click the Update and Publish
		driver.findElement(By.xpath("//span[contains(.,'Update And Publish')]")).click();
		
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		Thread.sleep(1500);if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Menu item updated and published successfully"))
		{
			test.log(LogStatus.PASS, "New Menu Item is Updated and published Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Menu Item is Updated and published Failed");
		}
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("s"+Utility.getProperty("ProductsItems_Name"));
		Thread.sleep(1000);
		//Click the Search Icon
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		Thread.sleep(5000);
		//Click the Edit icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		WebElement expupAmt1 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[2]/td[2]/ng-form/div/input[@name='price']"));
		String expamt1 = expupAmt1.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal1 = Float.parseFloat(expamt1);
		
		WebElement expupAmt2 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[3]/td[2]/ng-form/div/input[@name='price']"));
		String expamt2 = expupAmt2.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal2 = Float.parseFloat(expamt2);
		
		WebElement expupAmt3 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[4]/td[2]/ng-form/div/input[@name='price']"));
		String expamt3 = expupAmt3.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal3 = Float.parseFloat(expamt3);
		
		WebElement expupAmt4 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[5]/td[2]/ng-form/div/input[@name='price']"));
		String expamt4 = expupAmt4.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal4 = Float.parseFloat(expamt4);
		
		WebElement expupAmt5 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[6]/td[2]/ng-form/div/input[@name='price']"));
		String expamt5 = expupAmt5.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal5 = Float.parseFloat(expamt5);
		
		WebElement expupAmt6 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[7]/td[2]/ng-form/div/input[@name='price']"));
		String expamt6 = expupAmt6.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal6 = Float.parseFloat(expamt6);
		
		WebElement expupAmt7 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[8]/td[2]/ng-form/div/input[@name='price']"));
		String expamt7 = expupAmt7.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal7 = Float.parseFloat(expamt7);
		
		WebElement expupAmt8 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[9]/td[2]/ng-form/div/input[@name='price']"));
		String expamt8 = expupAmt8.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal8 = Float.parseFloat(expamt8);
		
		WebElement expupAmt9 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[10]/td[2]/ng-form/div/input[@name='price']"));
		String expamt9 = expupAmt9.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal9 = Float.parseFloat(expamt9);
		
		WebElement expupAmt10 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[11]/td[2]/ng-form/div/input[@name='price']"));
		String expamt10 = expupAmt10.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal10 = Float.parseFloat(expamt10);
		
		WebElement expupAmt11 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[12]/td[2]/ng-form/div/input[@name='price']"));
		String expamt11 = expupAmt11.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal11 = Float.parseFloat(expamt11);
		
		WebElement expupAmt12 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[13]/td[2]/ng-form/div/input[@name='price']"));
		String expamt12 = expupAmt12.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal12 = Float.parseFloat(expamt12);
		
		WebElement expupAmt13 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[14]/td[2]/ng-form/div/input[@name='price']"));
		String expamt13 = expupAmt13.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal13 = Float.parseFloat(expamt13);
		
		WebElement expupAmt14 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[15]/td[2]/ng-form/div/input[@name='price']"));
		String expamt14 = expupAmt14.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal14 = Float.parseFloat(expamt14);
		
		WebElement expupAmt15 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[16]/td[2]/ng-form/div/input[@name='price']"));
		String expamt15 = expupAmt15.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal15 = Float.parseFloat(expamt15);
		
		WebElement expupAmt16 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[17]/td[2]/ng-form/div/input[@name='price']"));
		String expamt16 = expupAmt16.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal16 = Float.parseFloat(expamt16);
		
		WebElement expupAmt17 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[18]/td[2]/ng-form/div/input[@name='price']"));
		String expamt17 = expupAmt17.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal17 = Float.parseFloat(expamt17);
		
		WebElement expupAmt18 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[19]/td[2]/ng-form/div/input[@name='price']"));
		String expamt18 = expupAmt18.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal18 = Float.parseFloat(expamt18);
		
		WebElement expupAmt19 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[20]/td[2]/ng-form/div/input[@name='price']"));
		String expamt19 = expupAmt19.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal19 = Float.parseFloat(expamt19);
		
		WebElement expupAmt20 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[21]/td[2]/ng-form/div/input[@name='price']"));
		String expamt20 = expupAmt20.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
		float expAmtVal20 = Float.parseFloat(expamt20);
		
		if(actAmtVal1 == expAmtVal1)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 1st Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal1 - expAmtVal1;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 1st Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal2 == expAmtVal2)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 2nd Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal2 - expAmtVal2;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 2nd Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal3 == expAmtVal3)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 3rd Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal3 - expAmtVal3;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 3rd Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal4 == expAmtVal4)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 4th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal4 - expAmtVal4;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 4th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal5 == expAmtVal5)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 5th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal5 - expAmtVal5;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 5th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal6 == expAmtVal6)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 6th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal6 - expAmtVal6;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 6th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal7 == expAmtVal7)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 7th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal7 - expAmtVal7;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 7th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal8 == expAmtVal8)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 8th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal8 - expAmtVal8;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 8th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal9 == expAmtVal9)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 9th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal9 - expAmtVal9;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 9th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal10 == expAmtVal10)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 10th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal10 - expAmtVal10;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 10th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal11 == expAmtVal11)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 11th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal11 - expAmtVal11;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 11th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal12 == expAmtVal12)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 12th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal12 - expAmtVal12;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 12th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal13 == expAmtVal13)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 13th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal13 - expAmtVal13;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 13th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal14 == expAmtVal14)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 14th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal14 - expAmtVal14;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 14th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal15 == expAmtVal15)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 15th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal15 - expAmtVal15;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 15th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal16 == expAmtVal16)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 16th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal16 - expAmtVal16;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 16th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal17 == expAmtVal17)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 17th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal17 - expAmtVal17;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 17th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal18 == expAmtVal18)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 18th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal18 - expAmtVal18;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 18th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal19 == expAmtVal19)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 19th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal19 - expAmtVal19;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 19th Price Level) updated in Price Level"+diff);
		}
		if(actAmtVal20 == expAmtVal20)
		{
			test.log(LogStatus.PASS, "Updated values are Correctly(For 20th Price Level) updated in Price Level");
		}
		else
		{
			float diff = actAmtVal20 - expAmtVal20;
			test.log(LogStatus.FAIL, "Updated values are Wrongly(For 20th Price Level) updated in Price Level"+diff);
		}
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[.='Cancel']")).click();
	}
	
	@Test(priority=6, enabled=false)
	public void editExistingMenuItem_Single_And_Multi_Serving_Size(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		Thread.sleep(2000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		Thread.sleep(1000);
		//Click the Search Icon
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		Thread.sleep(5000);

		//Click the Pagination option as 20
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/div/div/div/ul[1]/li[4]/a/span")).click();

		Thread.sleep(8000);		
		for(int i = 1; i <= 20; i++)
		{
			List<WebElement> count = driver.findElements(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[5]/div"));
			if(count.size()==1)
			{
				String sname = driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[1]")).getText();
				System.out.println(sname+"Test 1");
				//Clear the search field
				driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
				//Enter the required keyword
				driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(sname);
				Thread.sleep(1000);
				//Click the Search Icon
				driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
				Thread.sleep(5000);
				//Click the Edit icon
				driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
				Thread.sleep(5000);
				//Check Whether the Pricing Level Option is enable or not
				//Check whether the Price Level Settings is Updated Or Not
				if(driver.findElement(By.xpath("//input[@ng-model='menu.priceLevelSettings']")).isEnabled())
				{
					test.log(LogStatus.PASS, "Price Level Settings option is initially disabled");
					
					//Enable the Price Level Settings
					driver.findElement(By.xpath("//span[contains(.,'Price Level Settings')]")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Price Level Settings option is initially enabled");
				}
				
				Thread.sleep(1000);
				//Clear the Price value
				driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/input[@name='price']")).clear();
				//Enter the amount
				driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/input[@name='price']")).sendKeys("100");
				
				for(int a = 3; a <= 11; a++)
				{
					//Click the add button of prizing level
					driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[1]/th[3]/a")).click();
					
				}
				
				for(int b = 3; b <= 11; b++)
				{
					Thread.sleep(1000);
					//Clear the Price value
					driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr["+b+"]/td[2]/ng-form/div/input[@name='price']")).clear();
					//Enter the amount
					driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr["+b+"]/td[2]/ng-form/div/input[@name='price']")).sendKeys(b+"00");
				}
			
				WebElement upAmt1 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[2]/td[2]/ng-form/div/input[@name='price']"));
				String amt1 = upAmt1.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal1 = Float.parseFloat(amt1);
				
				WebElement upAmt2 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[3]/td[2]/ng-form/div/input[@name='price']"));
				String amt2 = upAmt2.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal2 = Float.parseFloat(amt2);
				
				WebElement upAmt3 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[4]/td[2]/ng-form/div/input[@name='price']"));
				String amt3 = upAmt3.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal3 = Float.parseFloat(amt3);
				
				WebElement upAmt4 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[5]/td[2]/ng-form/div/input[@name='price']"));
				String amt4 = upAmt4.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal4 = Float.parseFloat(amt4);
				
				WebElement upAmt5 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[6]/td[2]/ng-form/div/input[@name='price']"));
				String amt5 = upAmt5.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal5 = Float.parseFloat(amt5);
				
				WebElement upAmt6 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[7]/td[2]/ng-form/div/input[@name='price']"));
				String amt6 = upAmt6.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal6 = Float.parseFloat(amt6);
				
				WebElement upAmt7 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[8]/td[2]/ng-form/div/input[@name='price']"));
				String amt7 = upAmt7.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal7 = Float.parseFloat(amt7);
				
				WebElement upAmt8 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[9]/td[2]/ng-form/div/input[@name='price']"));
				String amt8 = upAmt8.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal8 = Float.parseFloat(amt8);
				
				WebElement upAmt9 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[10]/td[2]/ng-form/div/input[@name='price']"));
				String amt9 = upAmt9.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal9 = Float.parseFloat(amt9);
				
				WebElement upAmt10 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[11]/td[2]/ng-form/div/input[@name='price']"));
				String amt10 = upAmt10.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal10 = Float.parseFloat(amt10);
				
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(3000);
				//Click the Update and Publish
				driver.findElement(By.xpath("//span[contains(.,'Update And Publish')]")).click();
				
				Thread.sleep(3000);
				//Check whether the new form loaded or not
				Thread.sleep(1500);if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Menu item updated and published successfully"))
				{
					test.log(LogStatus.PASS, "New Menu Item is Updated and published Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "New Menu Item is Updated and published Failed");
				}
				Thread.sleep(3000);
				//Clear the search field
				driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
				//Enter the required keyword
				driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(sname);
				Thread.sleep(1000);
				//Click the Search Icon
				driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
				Thread.sleep(5000);
				//Click the Edit icon
				driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
				
				WebElement expupAmt1 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[2]/td[2]/ng-form/div/input[@name='price']"));
				String expamt1 = expupAmt1.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal1 = Float.parseFloat(expamt1);
				
				WebElement expupAmt2 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[3]/td[2]/ng-form/div/input[@name='price']"));
				String expamt2 = expupAmt2.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal2 = Float.parseFloat(expamt2);
				
				WebElement expupAmt3 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[4]/td[2]/ng-form/div/input[@name='price']"));
				String expamt3 = expupAmt3.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal3 = Float.parseFloat(expamt3);
				
				WebElement expupAmt4 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[5]/td[2]/ng-form/div/input[@name='price']"));
				String expamt4 = expupAmt4.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal4 = Float.parseFloat(expamt4);
				
				WebElement expupAmt5 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[6]/td[2]/ng-form/div/input[@name='price']"));
				String expamt5 = expupAmt5.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal5 = Float.parseFloat(expamt5);
				
				WebElement expupAmt6 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[7]/td[2]/ng-form/div/input[@name='price']"));
				String expamt6 = expupAmt6.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal6 = Float.parseFloat(expamt6);
				
				WebElement expupAmt7 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[8]/td[2]/ng-form/div/input[@name='price']"));
				String expamt7 = expupAmt7.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal7 = Float.parseFloat(expamt7);
				
				WebElement expupAmt8 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[9]/td[2]/ng-form/div/input[@name='price']"));
				String expamt8 = expupAmt8.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal8 = Float.parseFloat(expamt8);
				
				WebElement expupAmt9 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[10]/td[2]/ng-form/div/input[@name='price']"));
				String expamt9 = expupAmt9.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal9 = Float.parseFloat(expamt9);
				
				WebElement expupAmt10 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[11]/td[2]/ng-form/div/input[@name='price']"));
				String expamt10 = expupAmt10.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal10 = Float.parseFloat(expamt10);
				
				if(actAmtVal1 == expAmtVal1)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 1st Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal1 - expAmtVal1;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 1st Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal2 == expAmtVal2)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 2nd Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal2 - expAmtVal2;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 2nd Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal3 == expAmtVal3)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 3rd Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal3 - expAmtVal3;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 3rd Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal4 == expAmtVal4)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 4th Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal4 - expAmtVal4;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 4th Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal5 == expAmtVal5)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 5th Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal5 - expAmtVal5;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 5th Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal6 == expAmtVal6)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 6th Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal6 - expAmtVal6;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 6th Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal7 == expAmtVal7)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 7th Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal7 - expAmtVal7;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 7th Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal8 == expAmtVal8)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 8th Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal8 - expAmtVal8;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 8th Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal9 == expAmtVal9)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 9th Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal9 - expAmtVal9;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 9th Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal10 == expAmtVal10)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 10th Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal10 - expAmtVal10;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 10th Price Level) updated in Price Level"+diff);
				}

				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[.='Cancel']")).click();
			}
			//For Multi Serving Size
			else if(2 <= count.size())
			{
				String sname = driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[1]")).getText();
				System.out.println(sname+"Test 2");
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				Thread.sleep(3000);
				//Clear the search field
				driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
				//Enter the required keyword
				driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(sname);
				Thread.sleep(1000);
				//Click the Search Icon
				driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
				Thread.sleep(5000);
				//Click the Edit icon
				driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
				
				Thread.sleep(5000);
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				Thread.sleep(3000);
				//Check Whether the Pricing Level Option is enable or not
				if(driver.findElement(By.xpath("//input[@ng-model='menu.priceLevelSettings']")).isEnabled())
				{
					test.log(LogStatus.PASS, "Price Level Settings option is initially disabled");
					Thread.sleep(3000);
					//Enable the Price Level Settings
					driver.findElement(By.xpath("//span[contains(.,'Price Level Settings')]")).click();
				}
				else
				{
					test.log(LogStatus.FAIL, "Price Level Settings option is initially enabled");
				}
				
				Select measureType = new Select(driver.findElement(By.name("measureType")));
				measureType.selectByValue("Serving Size");
						
				Thread.sleep(1000);
				//Clear the Price value
				driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/input[@name='price']")).clear();
				//Enter the amount
				driver.findElement(By.xpath("//ng-form[@name='menuSizeForm']/div/input[@name='price']")).sendKeys("100");
				
				for(int a = 3; a <= 11; a++)
				{
					//Click the add button of prizing level
					driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[1]/th[3]/a")).click();
				}
			
				for(int b = 3; b <= 11; b++)
				{
					Thread.sleep(1000);
					//Clear the Price value
					driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr["+b+"]/td[2]/ng-form/div/input[@name='price']")).clear();
					//Enter the amount
					driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr["+b+"]/td[2]/ng-form/div/input[@name='price']")).sendKeys(b+"00");
				}
				//Clear the Price Level
				driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[3]/table/tbody/tr[2]/td[2]/ng-form/div/input[@name='price']")).clear();
				//Enter the Required Amount
				driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[3]/table/tbody/tr[2]/td[2]/ng-form/div/input[@name='price']")).sendKeys("500");
				
				//Click the add price level of 2nd serving size level
				driver.findElement(By.xpath("//table/tbody/tr[2]/td[3]/table/tbody/tr[1]/th[3]/a/i")).click();
				
				//Clear the Price Level
				driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[3]/table/tbody/tr[3]/td[2]/ng-form/div/input[@name='price']")).clear();
				//Enter the Required Amount
				driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[3]/table/tbody/tr[3]/td[2]/ng-form/div/input[@name='price']")).sendKeys("600");

				//Click the add price level of 2nd serving size level
				driver.findElement(By.xpath("//table/tbody/tr[2]/td[3]/table/tbody/tr[1]/th[3]/a/i")).click();
				
				//Clear the Price Level
				driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[3]/table/tbody/tr[4]/td[2]/ng-form/div/input[@name='price']")).clear();
				//Enter the Required Amount
				driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[3]/table/tbody/tr[4]/td[2]/ng-form/div/input[@name='price']")).sendKeys("700");
			
				
				WebElement upAmt1 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[2]/td[2]/ng-form/div/input[@name='price']"));
				String amt1 = upAmt1.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal1 = Float.parseFloat(amt1);
				
				WebElement upAmt2 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[3]/td[2]/ng-form/div/input[@name='price']"));
				String amt2 = upAmt2.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal2 = Float.parseFloat(amt2);
				
				WebElement upAmt3 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[4]/td[2]/ng-form/div/input[@name='price']"));
				String amt3 = upAmt3.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal3 = Float.parseFloat(amt3);
				
				WebElement upAmt4 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[5]/td[2]/ng-form/div/input[@name='price']"));
				String amt4 = upAmt4.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal4 = Float.parseFloat(amt4);
				
				WebElement upAmt5 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[6]/td[2]/ng-form/div/input[@name='price']"));
				String amt5 = upAmt5.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal5 = Float.parseFloat(amt5);
				
				WebElement upAmt6 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[7]/td[2]/ng-form/div/input[@name='price']"));
				String amt6 = upAmt6.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal6 = Float.parseFloat(amt6);
				
				WebElement upAmt7 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[8]/td[2]/ng-form/div/input[@name='price']"));
				String amt7 = upAmt7.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal7 = Float.parseFloat(amt7);
				
				WebElement upAmt8 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[9]/td[2]/ng-form/div/input[@name='price']"));
				String amt8 = upAmt8.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal8 = Float.parseFloat(amt8);
				
				WebElement upAmt9 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[10]/td[2]/ng-form/div/input[@name='price']"));
				String amt9 = upAmt9.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal9 = Float.parseFloat(amt9);
				
				WebElement upAmt10 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[11]/td[2]/ng-form/div/input[@name='price']"));
				String amt10 = upAmt10.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal10 = Float.parseFloat(amt10);
				
				WebElement upAmt11 = driver.findElement(By.xpath("//table/tbody/tr[2]/td[3]/table/tbody/tr[2]/td[2]/ng-form/div/input"));
				String amt11 = upAmt11.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal11 = Float.parseFloat(amt11);
				
				WebElement upAmt12 = driver.findElement(By.xpath("//table/tbody/tr[2]/td[3]/table/tbody/tr[3]/td[2]/ng-form/div/input"));
				String amt12 = upAmt12.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal12 = Float.parseFloat(amt12);
				
				WebElement upAmt13 = driver.findElement(By.xpath("//table/tbody/tr[2]/td[3]/table/tbody/tr[4]/td[2]/ng-form/div/input"));
				String amt13 = upAmt13.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float actAmtVal13 = Float.parseFloat(amt13);
				
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(3000);
				//Click the Update and Publish
				driver.findElement(By.xpath("//span[contains(.,'Update And Publish')]")).click();
				
				Thread.sleep(3000);
				//Check whether the new form loaded or not
				Thread.sleep(1500);if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Menu item updated and published successfully"))
				{
					test.log(LogStatus.PASS, "New Menu Item is Updated and published Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "New Menu Item is Updated and published Failed");
				}
				Thread.sleep(3000);
				//Clear the search field
				driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
				//Enter the required keyword
				driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(sname);
				Thread.sleep(1000);
				//Click the Search Icon
				driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
				Thread.sleep(5000);
				//Click the Edit icon
				driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
				
				WebElement expupAmt1 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[2]/td[2]/ng-form/div/input[@name='price']"));
				String expamt1 = expupAmt1.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal1 = Float.parseFloat(expamt1);
				
				WebElement expupAmt2 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[3]/td[2]/ng-form/div/input[@name='price']"));
				String expamt2 = expupAmt2.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal2 = Float.parseFloat(expamt2);
				
				WebElement expupAmt3 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[4]/td[2]/ng-form/div/input[@name='price']"));
				String expamt3 = expupAmt3.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal3 = Float.parseFloat(expamt3);
				
				WebElement expupAmt4 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[5]/td[2]/ng-form/div/input[@name='price']"));
				String expamt4 = expupAmt4.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal4 = Float.parseFloat(expamt4);
				
				WebElement expupAmt5 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[6]/td[2]/ng-form/div/input[@name='price']"));
				String expamt5 = expupAmt5.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal5 = Float.parseFloat(expamt5);
				
				WebElement expupAmt6 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[7]/td[2]/ng-form/div/input[@name='price']"));
				String expamt6 = expupAmt6.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal6 = Float.parseFloat(expamt6);
				
				WebElement expupAmt7 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[8]/td[2]/ng-form/div/input[@name='price']"));
				String expamt7 = expupAmt7.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal7 = Float.parseFloat(expamt7);
				
				WebElement expupAmt8 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[9]/td[2]/ng-form/div/input[@name='price']"));
				String expamt8 = expupAmt8.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal8 = Float.parseFloat(expamt8);
				
				WebElement expupAmt9 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[10]/td[2]/ng-form/div/input[@name='price']"));
				String expamt9 = expupAmt9.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal9 = Float.parseFloat(expamt9);
				
				WebElement expupAmt10 = driver.findElement(By.xpath("//td[@ng-if='menu.priceLevelSettings']/table/tbody/tr[11]/td[2]/ng-form/div/input[@name='price']"));
				String expamt10 = expupAmt10.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal10 = Float.parseFloat(expamt10);
				
				WebElement expupAmt11 = driver.findElement(By.xpath("//table/tbody/tr[2]/td[3]/table/tbody/tr[2]/td[2]/ng-form/div/input"));
				String expamt11 = expupAmt11.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal11 = Float.parseFloat(expamt11);
				
				WebElement expupAmt12 = driver.findElement(By.xpath("//table/tbody/tr[2]/td[3]/table/tbody/tr[3]/td[2]/ng-form/div/input"));
				String expamt12 = expupAmt12.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal12 = Float.parseFloat(expamt12);
				
				WebElement expupAmt13 = driver.findElement(By.xpath("//table/tbody/tr[2]/td[3]/table/tbody/tr[4]/td[2]/ng-form/div/input"));
				String expamt13 = expupAmt13.getAttribute("value").replaceAll("[a-zA-Z $ ₹ , :]", "");
				float expAmtVal13 = Float.parseFloat(expamt13);
				
				if(actAmtVal1 == expAmtVal1)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 1st Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal1 - expAmtVal1;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 1st Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal2 == expAmtVal2)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 2nd Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal2 - expAmtVal2;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 2nd Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal3 == expAmtVal3)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 3rd Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal3 - expAmtVal3;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 3rd Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal4 == expAmtVal4)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 4th Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal4 - expAmtVal4;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 4th Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal5 == expAmtVal5)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 5th Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal5 - expAmtVal5;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 5th Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal6 == expAmtVal6)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 6th Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal6 - expAmtVal6;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 6th Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal7 == expAmtVal7)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 7th Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal7 - expAmtVal7;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 7th Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal8 == expAmtVal8)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 8th Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal8 - expAmtVal8;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 8th Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal9 == expAmtVal9)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 9th Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal9 - expAmtVal9;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 9th Price Level) updated in Price Level"+diff);
				}
				if(actAmtVal10 == expAmtVal10)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 10th Price Level) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal10 - expAmtVal10;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 10th Price Level) updated in Price Level"+diff);
				}

				if(actAmtVal11 == expAmtVal11)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 11th Price Level - 2nd SSL) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal11 - expAmtVal11;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 11th Price Level - 2nd SSL) updated in Price Level"+diff);
				}
				if(actAmtVal12 == expAmtVal12)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 12th Price Level - 2nd SSL) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal12 - expAmtVal12;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 12th Price Level - 2nd SSL) updated in Price Level"+diff);
				}
				if(actAmtVal13 == expAmtVal13)
				{
					test.log(LogStatus.PASS, "Updated values are Correctly(For 13th Price Level - 2nd SSL) updated in Price Level");
				}
				else
				{
					float diff = actAmtVal13 - expAmtVal13;
					test.log(LogStatus.FAIL, "Updated values are Wrongly(For 13th Price Level - 2nd SSL) updated in Price Level"+diff);
				}
				
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[.='Cancel']")).click();
			
			}
		}
	}
}
