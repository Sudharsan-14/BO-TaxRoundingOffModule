package epicList_Chrome_Xpath;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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


public class Settings_Add_Edit_Delete_Receipt_Printer {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_Add_Edit_Delete_Receipt_Printer");
	
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
			Receipt_Printer_method_open_Receipt_Printer(driver);
			Receipt_Printer_method_refreshReceipt_Printer(driver);
			Receipt_Printer_method_pagination(driver);
			Receipt_Printer_method_add_Receipt_Printer(driver);
			Receipt_Printer_method_edit_Receipt_Printer(driver);
			Receipt_Printer_method_delete_Receipt_Printer(driver);
			Receipt_Printer_method_close_Button(driver);
			Thread.sleep(1500);
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
	
	@Test(priority=18,enabled = false)
	public void Receipt_Printer_method_open_Receipt_Printer(WebDriver driver) throws Exception
	{
/*		//Click the Settings option
		driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[5]/a/span[1]")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Printer Configuration']"));
		//Scroll the page till the Printer Configuration option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Store Option		
		driver.findElement(By.xpath("//span[.='Printer Configuration']")).click();*/
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"receiptPrinters");
		Thread.sleep(5000);
		//Check receipt Printer page opened or not
		if(driver.findElement(By.xpath("//a[.='Receipt printers']")).getText().equalsIgnoreCase("Receipt printers"))
		{
			test.log(LogStatus.PASS, "Receipt printers page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt printers page loaded Failed");
		}
		Thread.sleep(3000);
	}	
			
	@Test(priority=19,enabled = false)
	public void Receipt_Printer_method_refreshReceipt_Printer(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(5000);
		//Click the refresh button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/a/i")).click();
		
		Thread.sleep(5000);
		//Check Kitchen Printer page opened or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[1]/div/h3")).getText().equalsIgnoreCase("Receipt Printers"))
		{
			test.log(LogStatus.PASS, "Receipt Printer page refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt Printer page refreshed Failed");
		}
	}
	
	@Test(priority=20,enabled = false)
	public void Receipt_Printer_method_pagination(WebDriver driver) throws InterruptedException
	{
						
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		Thread.sleep(1000);
		
		//Click the Pagination option as 10
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/div/div/div/ul/li[2]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Receipt Printer");
		//Create the web element for Receipt Printer Table
		List<WebElement> results = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/table"));
		for (WebElement result : results){						
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Receipt Printer");
		}
		Thread.sleep(5000);
		
		//Click the Pagination option as 15
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/div/div/div/ul/li[3]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Receipt Printer");
		//Create the web element for Receipt Printer Table
		List<WebElement> results1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/table"));
		for (WebElement result : results1){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Receipt Printer");
		}
		Thread.sleep(5000);
		
		//Click the Pagination option as 20
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/div/div/div/ul/li[4]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for Receipt Printer");
		//Create the web element for Receipt Printer Table
		List<WebElement> results2 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/table"));
		for (WebElement result : results2){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Receipt Printer");
		}
		Thread.sleep(5000);
		
		//Click the Pagination option as 5
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/div/div/div/ul/li[1]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Receipt Printer");
		//Create the web element for Receipt Printer Table
		List<WebElement> results3 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/table"));
		for (WebElement result : results3){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Receipt Printer");
		}
		Thread.sleep(5000);
	}
		
	@Test(priority=21,enabled = false)
	public void Receipt_Printer_method_add_Receipt_Printer(WebDriver driver) throws Exception
	{
		for(int i = 0; i <= 10; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		Thread.sleep(5000);
		//Click Add Receipt Printer Button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/button")).click();
		
		Thread.sleep(3000);
		//Check New Kitchen Printer form opened or not
		if(driver.findElement(By.xpath("//span[.='New Receipt Printer']")).getText().equalsIgnoreCase("New Receipt Printer"))
		{
			test.log(LogStatus.PASS, "Receipt printer form opened Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt printer form opened Failed");
		}
		Thread.sleep(5000);
		//Clear the name field
		driver.findElement(By.name("name")).clear();
		//Enter the name
		driver.findElement(By.name("name")).sendKeys("New_Receipt_Print");
		
		Thread.sleep(1000);
		//Click Printer Model
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[2]/div/a")).click();
		//Select the Printer
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[2]/div/div/div/input")).sendKeys("Epson - TM-T88IV");
		//Enter the Printer 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[2]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(1000);
		//Clear the IP Address field
		driver.findElement(By.name("IPAddress")).clear();
		//Enter the IP Address
		driver.findElement(By.name("IPAddress")).sendKeys("192.168.11.11");
		
		
		Thread.sleep(5000);
		//Check whether the default option is enable or not
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[4]/label/span")).click();
		
	
		Thread.sleep(2000);
		//Click the Save button
		driver.findElement(By.xpath("//button[@id='saveReceipt']")).click();
		Thread.sleep(2000);
		
		//Check whether the Receipt printer was saved or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Printer saved successfully"))
		{
			test.log(LogStatus.PASS, "Receipt Printer Saved successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt Printer Saved fail");
		}
		Thread.sleep(3000);

	}
	
	@Test(priority=22,enabled = false)
	public void Receipt_Printer_method_edit_Receipt_Printer(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		//Clear the Search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required key word
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("New_Receipt_Print");
		
		Thread.sleep(1000);
		//Click the Edit button
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(5000);
		//Clear the name field
		driver.findElement(By.name("name")).clear();
		//Enter the name
		driver.findElement(By.name("name")).sendKeys("New_Receipt_Print1");
		
		Thread.sleep(1000);
		//Click Printer Model
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[2]/div/a")).click();
		//Select the Printer
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[2]/div/div/div/input")).sendKeys("Epson - TM-T88IV");
		//Enter the Printer 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[2]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(5000);
		//Clear the IP Address field
		driver.findElement(By.name("IPAddress")).clear();
		//Enter the IP Address
		driver.findElement(By.name("IPAddress")).sendKeys("192.168.11.12");
		
		
		Thread.sleep(5000);
		//Check whether the default option is enable or not
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[4]/label/span")).click();
		
		Thread.sleep(2000);
		//Click the Update button
		driver.findElement(By.xpath("//button[@id='saveReceipt']")).click();
		Thread.sleep(2000);
		
		//Check whether the Receipt printer was updated or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Printer updated successfully"))
		{
			test.log(LogStatus.PASS, "Receipt Printer Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt Printer Updated fail");
		}
		Thread.sleep(3000);
	}
	
	@Test(priority=23,enabled = false)
	public void Receipt_Printer_method_delete_Receipt_Printer(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		//Clear the Search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required key word
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("New_Receipt_Print1");
		
		Thread.sleep(1000);
		//Click the Delete button
		driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
		
		//Click the Yes button in the popup
		Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
		
		Thread.sleep(3000);
		//Check the Kitchen Printer deleted or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Printer deleted successfully"))
		{
			test.log(LogStatus.PASS, "New Receipt Printer is deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Receipt Printer is deleted Failed");
		}
		Thread.sleep(3000);
		//Clear the Search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
	}
	
	@Test(priority=24,enabled = false)
	public void Receipt_Printer_method_close_Button(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Click Add Receipt Printer Button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/button")).click();
		
		Thread.sleep(3000);
		//Check New Kitchen Printer form opened or not
		if(driver.findElement(By.xpath("//span[.='New Receipt Printer']")).getText().equalsIgnoreCase("New Receipt Printer"))
		{
			test.log(LogStatus.PASS, "Receipt printer form opened Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt printer form opened Failed");
		}
		Thread.sleep(3000);
		

		//Clear the name field
		driver.findElement(By.name("name")).clear();
		//Enter the name
		driver.findElement(By.name("name")).sendKeys("New_Receipt_Print");
		
	
		Thread.sleep(2000);
		//Click the Close button
		driver.findElement(By.xpath("//a[.='Close']")).click();
		Thread.sleep(2000);
		
		//Check whether the Kitchen printer form was closed or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/button")).isDisplayed())
		{
			test.log(LogStatus.PASS, "Receipt Printer form Closed successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt Printer form Closed fail");
		}
		Thread.sleep(3000);

	}

}
