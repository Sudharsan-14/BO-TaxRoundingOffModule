package epicList_Chrome;

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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Settings_Service_Charge {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_Service_Charge");
	
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
			Service_Charge_Open_Service_Charge_Page(driver);
			Service_Charge_Refresh(driver);
			//Service_Charge_Pagination(driver);
			Service_Charge_Add_Service_Charge(driver);
			Service_Charge_Edit_Service_Charge(driver);
			Service_Charge_Delete_Service_Charge(driver);
			Service_Charge_Close_Button(driver);
			Thread.sleep(1500);
		}

	@Test(priority=2,enabled=false)
	public void Service_Charge_Open_Service_Charge_Page(WebDriver driver) throws Exception
	{
		/*//Click the Settings option
		driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[5]/a/span[1]")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Store']"));
		//Scroll the page till the Store option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Store Option		
		driver.findElement(By.xpath("//span[.='Store']")).click();
		Thread.sleep(5000);
		//Check Store Information page opened or not
		if(driver.findElement(By.xpath("//a[.='Store Information']")).getText().equalsIgnoreCase("Store Information"))
		{
			test.log(LogStatus.PASS, "Store Information page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Store Information page loaded Failed");
		}
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		Thread.sleep(4000);
		// Create instance of Java script executor
		JavascriptExecutor je1 = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element1 = driver.findElement(By.xpath("//span[.=' Service Charge ']"));
		//Scroll the page till the Store option present
		je1.executeScript("arguments[0].scrollIntoView(true);",element1); 
		Thread.sleep(3000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(2000);
		//Click the Service Charge Option
		driver.findElement(By.xpath("//span[.=' Service Charge ']")).click();
		Thread.sleep(3000);*/

		Thread.sleep(3000);
		//Enter the Users URL
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"newServiceCharge");
		Thread.sleep(5000);
		try
		{
		//Check weather the Service Charge page is loaded or not
		if(driver.findElement(By.xpath("//a[.='Service Charge']")).getText().equalsIgnoreCase("Service Charge"))
		{
			test.log(LogStatus.PASS, "Service Charge page loaded successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Service Charge page loaded fail");
		
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

	}	
	
	@Test(priority=3,enabled=false)
	public void Service_Charge_Refresh(WebDriver driver) throws Exception
	{
		//Click the refresh button
		driver.findElement(By.xpath("//a[@ng-click='serviceChargeList()']")).click();
		Thread.sleep(3000);
		
		//Check weather the page is refreshed or not
		if(driver.findElement(By.xpath("//h4[normalize-space()='Service Charge']")).getText().equalsIgnoreCase("Service Charge"))
		{
			test.log(LogStatus.PASS, "Service Charge Page refreshed successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Service Charge Page refreshed successfully");
		}
		
	}
	
	@Test(priority=3,enabled=false)
	public void Service_Charge_Pagination(WebDriver driver) throws InterruptedException
	{			
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		Thread.sleep(1000);
		
		//Click the Pagination option as 10
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[3]/div/div/div/ul/li[2]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 10");
		//Create the web element for Service Charge Table
		List<WebElement> results = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[3]/table"));
		for (WebElement result : results){						
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 15
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[3]/div/div/div/ul/li[3]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 15");
		//Create the web element for Service Charge Table
		List<WebElement> results1 = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[3]/table"));
		for (WebElement result : results1){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 20
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[3]/div/div/div/ul/li[4]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 20");
		//Create the web element for Service Charge Table
		List<WebElement> results2 = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[3]/table"));
		for (WebElement result : results2){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 5
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[3]/div/div/div/ul/li[1]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 5");
		//Create the web element for Service Charge Table
		List<WebElement> results3 = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[3]/table"));
		for (WebElement result : results3){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available");
		}
		Thread.sleep(8000);
	}
	
	@Test(priority=4,enabled=false)
	public void Service_Charge_Add_Service_Charge(WebDriver driver) throws Exception
	{
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//enter the keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("Master");
		Thread.sleep(2000);
		//List<WebElement> cards = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[3]/table/tbody"));
		WebElement ele = driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[3]/table/tbody/tr/td"));
			Thread.sleep(2000);
			if(ele.getText().equalsIgnoreCase("Master")) {
	    		//Click the Delete button
	    		Thread.sleep(2000);
	    		driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
	    		driver.findElement(By.linkText("Yes")).click();
	    		Thread.sleep(2000);
	    		//Clear the search field
	    		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
	    		//Click the Add Service Charge button
	    		driver.findElement(By.xpath("//button[normalize-space()='Service Charge']")).click();
	    		Thread.sleep(2000);
			}
			else {
		Thread.sleep(2000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		
		Thread.sleep(2000);
		//Click the Add Service Charge button
		driver.findElement(By.xpath("//button[normalize-space()='Service Charge']")).click();
		Thread.sleep(2000);
		
		//Check weather the new form loaded or not
		if(driver.findElement(By.xpath("//span[.='New Service Charge']")).getText().equalsIgnoreCase("New Service Charge"))
		{
			test.log(LogStatus.PASS, "New Service Charge form or page loaded successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Service Charge form or page loaded fail");
		}
			}
		
		Thread.sleep(2000);
		//Select the CArd Type 
		driver.findElement(By.xpath("//span[.='Select Card Type']")).click();
		driver.findElement(By.xpath("//div[@class='chosen-search']/input")).sendKeys("Master");
		driver.findElement(By.xpath("//div[@class='chosen-search']/input")).sendKeys(Keys.ENTER);
		//Clear the service charge field
		driver.findElement(By.name("serviceCharge")).clear();
		//Enter the service charge
		driver.findElement(By.name("serviceCharge")).sendKeys("123");
		
		//Clear the tip charge field
		driver.findElement(By.name("tipCharge")).clear();
		//Enter the tip charge
		driver.findElement(By.name("tipCharge")).sendKeys("125");
		
		Thread.sleep(1000);
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		
		//Check weather the new denomination form saved or not
		if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Service Charge Added Successfully"))
		{
			test.log(LogStatus.PASS, "New Service Charge saved successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Service Charge saved fail");
		}
			
		Thread.sleep(2000);
		//Click the Add Service Charge button
		driver.findElement(By.xpath("//button[normalize-space()='Service Charge']")).click();
		Thread.sleep(2000);

	//Select the CArd Type 
	driver.findElement(By.xpath("//span[.='Select Card Type']")).click();
	driver.findElement(By.xpath("//div[@class='chosen-search']/input")).sendKeys("Master");
	driver.findElement(By.xpath("//div[@class='chosen-search']/input")).sendKeys(Keys.ENTER);
	//Clear the service charge field
	driver.findElement(By.name("serviceCharge")).clear();
	//Enter the service charge
	driver.findElement(By.name("serviceCharge")).sendKeys("123");
	
	//Clear the tip charge field
	driver.findElement(By.name("tipCharge")).clear();
	//Enter the tip charge
	driver.findElement(By.name("tipCharge")).sendKeys("125");
	
	Thread.sleep(1000);
	//Click the Save button
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	Thread.sleep(2000);
	
	//Check weather the duplicate card form saved or not
	if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Card Master is already Used."))
	{
		test.log(LogStatus.PASS, "Duplicate card is not allowed successfully");
	}
	else
	{
		test.log(LogStatus.FAIL, "duplicate card is allowed");
	}  
	Thread.sleep(1000);
	//Click the Close button
	driver.findElement(By.xpath("//a[.='Close']")).click();
}
	@Test(priority=5,enabled=false)
	public void Service_Charge_Edit_Service_Charge(WebDriver driver) throws Exception
	{
		Thread.sleep(10000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//enter the keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("Master");
		
		//Click the Delete button
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		Thread.sleep(5000);
		
		//Clear the service charge field
		driver.findElement(By.name("serviceCharge")).clear();
		//Enter the service charge
		driver.findElement(By.name("serviceCharge")).sendKeys("125");
		
		//Clear the tip charge field
		driver.findElement(By.name("tipCharge")).clear();
		//Enter the tip charge
		driver.findElement(By.name("tipCharge")).sendKeys("126");
		
		Thread.sleep(1000);
		//Click the update button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		
		//Check weather the new denomination form saved or not
		if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Service Charges Updated Successfully"))
		{
			test.log(LogStatus.PASS, "New Service Charges Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Service Charges Updated fail");
		}
		Thread.sleep(3000);
		
		
	}
		
	@Test(priority=6,enabled=false)
	public void Service_Charge_Delete_Service_Charge(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//enter the keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("Master");
		
		//Click the Delete button
		driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
		
		//Click the Yes button in the popup
		driver.findElement(By.linkText("Yes")).click();
		
		Thread.sleep(4000);
		//Check the denomination deleted or not
		if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Service Charge Removed Successfully"))
		{
			test.log(LogStatus.PASS, "Service Charge settings deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Service Charge Settings deleted Failed");
		}
		Thread.sleep(2000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		
	}
	
	@Test(priority=7,enabled=false)
	public void Service_Charge_Close_Button(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		//Click the Add Service Charge settings button
		driver.findElement(By.xpath("//button[normalize-space()='Service Charge']")).click();
		Thread.sleep(2000);
		
		//Check weather the new form loaded or not
		if(driver.findElement(By.xpath("//span[.='New Service Charge']")).getText().equalsIgnoreCase("New Service Charge"))
		{
			test.log(LogStatus.PASS, "New Service Charge form or page loaded successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Service Charge form or page loaded fail");
		}
		
		Thread.sleep(2000);
		//Select the CArd Type
		driver.findElement(By.xpath("//span[.='Select Card Type']")).click();
		driver.findElement(By.xpath("//div[@class='chosen-search']/input")).sendKeys("visa");
		driver.findElement(By.xpath("//div[@class='chosen-search']/input")).sendKeys(Keys.ENTER);
		
		//Clear the service charge field
		driver.findElement(By.name("serviceCharge")).clear();
		//Enter the service charge
		driver.findElement(By.name("serviceCharge")).sendKeys("123");
		
		//Clear the tip charge field
		driver.findElement(By.name("tipCharge")).clear();
		//Enter the tip charge
		driver.findElement(By.name("tipCharge")).sendKeys("125");
		
		Thread.sleep(1000);
		//Click the Close button
		driver.findElement(By.xpath("//a[.='Close']")).click();
		Thread.sleep(2000);
		
		//Check weather the new service charge form closed or not
		if(driver.findElement(By.xpath("//button[normalize-space()='Service Charge']")).isDisplayed())
		{
			test.log(LogStatus.PASS, "Service Charge form Closed successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Service Charge form Closed fail");
		}
	}
}
