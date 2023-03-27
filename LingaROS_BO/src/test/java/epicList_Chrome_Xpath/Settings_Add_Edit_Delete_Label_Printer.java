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


public class Settings_Add_Edit_Delete_Label_Printer {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_Add_Edit_Delete_Label_Printer");
	
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
			Label_Printer_method_open_Label_Printer(driver);
			Label_Printer_method_refreshLabel_Printer(driver);
			Label_Printer_method_pagination(driver);
			Label_Printer_method_add_Label_Printer(driver);
			Label_Printer_method_edit_Label_Printer(driver);
			Label_Printer_method_delete_Label_Printer(driver);
			Label_Printer_method_close_Button(driver);
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

	@Test(priority=25,enabled = false)
	public void Label_Printer_method_open_Label_Printer(WebDriver driver) throws Exception
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
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"labelPrinters");
		Thread.sleep(5000);

		//Check Label Printer page opened or not
		if(driver.findElement(By.xpath("//a[.='Label printers']")).getText().equalsIgnoreCase("Label printers"))
		{
			test.log(LogStatus.PASS, "Label printers page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Label printers page loaded Failed");
		}
		Thread.sleep(3000);
	}	
			
	@Test(priority=26,enabled = false)
	public void Label_Printer_method_refreshLabel_Printer(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(5000);
		//Click the refresh button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/a/i")).click();
		
		Thread.sleep(5000);
		//Check Label Printer page opened or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[1]/div/h3")).getText().equalsIgnoreCase("Label Printers"))
		{
			test.log(LogStatus.PASS, "Label Printer page refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Label Printer page refreshed Failed");
		}
	}
	
	@Test(priority=27,enabled = false)
	public void Label_Printer_method_pagination(WebDriver driver) throws InterruptedException
	{
						
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		Thread.sleep(1000);
		
		//Click the Pagination option as 10
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/div/div/div/ul[1]/li[2]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Lable Printer");
		//Create the web element for Label Printer Table
		List<WebElement> results = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/table"));
		for (WebElement result : results){						
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Lable Printer");
		}
		Thread.sleep(5000);
		
		//Click the Pagination option as 15
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/div/div/div/ul[1]/li[3]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Lable Printer");
		//Create the web element for Label Printer Table
		List<WebElement> results1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/table"));
		for (WebElement result : results1){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Lable Printer");
		}
		Thread.sleep(5000);
		
		//Click the Pagination option as 20
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/div/div/div/ul[1]/li[4]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for Lable Printer");
		//Create the web element for Label Printer Table
		List<WebElement> results2 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/table"));
		for (WebElement result : results2){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Lable Printer");
		}
		Thread.sleep(5000);
		
		//Click the Pagination option as 5
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/div/div/div/ul[1]/li[1]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Lable Printer");
		//Create the web element for Label Printer Table
		List<WebElement> results3 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[1]/div/div/div[2]/div[2]/table"));
		for (WebElement result : results3){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Lable Printer");
		}
		Thread.sleep(5000);
	}
		
	@Test(priority=28,enabled = false)
	public void Label_Printer_method_add_Label_Printer(WebDriver driver) throws Exception
	{
		for(int i = 0; i <= 10; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}

		Thread.sleep(5000);
		//Click Add Kitchen Printer Button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/button")).click();
		
		Thread.sleep(3000);
		//Check New Label Printer form opened or not
		if(driver.findElement(By.xpath("//span[.='New Label Printer']")).getText().equalsIgnoreCase("New Label Printer"))
		{
			test.log(LogStatus.PASS, "Label printer form opened Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Label printer form opened Failed");
		}
		Thread.sleep(3000);
		
		//Click the Types Option
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[1]/div/a")).click();
		//Enter the required type
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[1]/div/div/div/input")).sendKeys("Menu");
		//Press Enter button
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the name field
		driver.findElement(By.name("name")).clear();
		//Enter the name
		driver.findElement(By.name("name")).sendKeys("New_Label_Print");
		
		Thread.sleep(2000);
		//Click the Printer model
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[3]/div/a")).click();
		//Click
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[3]/div/div/div/input")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[3]/div/div/div/input")).sendKeys(Keys.ENTER);
				
		Thread.sleep(1000);
		//Clear the IP Address field
		driver.findElement(By.name("IPAddress")).clear();
		//Enter the IP Address
		driver.findElement(By.name("IPAddress")).sendKeys("192.168.17.11");
		
		Thread.sleep(2000);
		//Click the Alternate Printer model
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[5]/div/a")).click();
		//Click
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[5]/div/div/div/input")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[5]/div/div/div/input")).sendKeys(Keys.ENTER);

		
		Thread.sleep(1000);
		//Check whether the Enable Service Type Restriction option is enable or not
		if(driver.findElement(By.name("enableServiceTypeRestriction")).isSelected())
		{
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul")).click();
			//Enter the required Option
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys("QSR");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul")).click();
			//Enter the required Option
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys("ToGo");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul")).click();
			//Enter the required Option
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys("WEBORDER");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys(Keys.ENTER);
		}
		
		else
		{
			Thread.sleep(1000);
			//Enable or disable service type
			driver.findElement(By.name("enableServiceTypeRestriction")).click();
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul")).click();
			//Enter the required Option
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys("QSR");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul")).click();
			//Enter the required Option
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys("ToGo");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul")).click();
			//Enter the required Option
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys("WEBORDER");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys(Keys.ENTER);
			
		}
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		Thread.sleep(1000);
		//Check whether the Apply to all Categories is enabled or not
		if(driver.findElement(By.name("default")).isSelected())
		{
			Thread.sleep(1000);
			//Check whether the Apply to all menu items is enabled or not
			if(driver.findElement(By.name("forAllMenuItems")).isSelected())
			{
				
			}
			
			else
			{
				
				Thread.sleep(1000);
				//Enable the Apply to all menu items
				driver.findElement(By.name("forAllMenuItems")).click();
				
				//Click the Yes button
				//driver.findElement(By.partialLinkText("Yes")).click();
			}
		}
		
		else
		{
			Thread.sleep(1000);
			//Enable the Apply to all Categories
			driver.findElement(By.name("default")).click();
			Thread.sleep(1000);
			//Click the Yes button
			//driver.findElement(By.partialLinkText("Yes")).click();
			
			Thread.sleep(1000);
			//Check whether the Apply to all menu items is enabled or not
			if(driver.findElement(By.name("forAllMenuItems")).isSelected())
			{
				
			}
			
			else
			{
				Thread.sleep(1000);
				//Enable the Apply to all menu items
				driver.findElement(By.name("forAllMenuItems")).click();
				
				Thread.sleep(1000);
				//Click the Yes button
				//driver.findElement(By.partialLinkText("Yes")).click();
			}
		}
		
		Thread.sleep(2000);
		//Click the Save button
		driver.findElement(By.xpath("//button[@id='saveLabel']")).click();
		Thread.sleep(4000);driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Check whether the Kitchen printer was saved or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Printer saved successfully"))
		{
			test.log(LogStatus.PASS, "Label Printer Saved successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Label Printer Saved fail");
		}
		Thread.sleep(3000);

	}
	
	@Test(priority=29,enabled = false)
	public void Label_Printer_method_edit_Label_Printer(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(2000);
		//Clear the Search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required key word
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("New_Label_Print");
		
		Thread.sleep(1000);
		//Click the Edit button
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(5000);
		//Click the Types Option
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[1]/div/a")).click();
		//Enter the required type
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[1]/div/div/div/input")).sendKeys("Address");
		//Press Enter button
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the name field
		driver.findElement(By.name("name")).clear();
		//Enter the name
		driver.findElement(By.name("name")).sendKeys("New_Label_Print1");
		
		Thread.sleep(1000);
		//Clear the IP Address field
		driver.findElement(By.name("IPAddress")).clear();
		//Enter the IP Address
		driver.findElement(By.name("IPAddress")).sendKeys("192.168.17.12");
		Thread.sleep(4000);
/*		//Click the Types Option
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[5]/div/a")).click();
		//Enter the required type
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[5]/div/div/div/input")).click();
		//Press Enter button
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[5]/div/div/div/input")).sendKeys(Keys.ENTER);
*/
		
		Thread.sleep(1000);
		//Check whether the Enable Service Type Restriction option is enable or not
		if(driver.findElement(By.name("enableServiceTypeRestriction")).isSelected())
		{
			for(int i = 1; i <= 3; i++)
			{
				driver.findElement(By.xpath("//a[@class='search-choice-close']")).click();
			}
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul")).click();
			//Enter the required Option
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys("DELIVERY");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul")).click();
			//Enter the required Option
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys("FORHERE");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul")).click();
			//Enter the required Option
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys("PHONEORDER");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys(Keys.ENTER);
		}
		
		else
		{
			Thread.sleep(1000);
			//Enable or disable service type
			driver.findElement(By.name("enableServiceTypeRestriction")).click();
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul")).click();
			//Enter the required Option
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys("DELIVERY");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul")).click();
			//Enter the required Option
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys("FORHERE");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul")).click();
			//Enter the required Option
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys("PHONEORDER");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@ng-if='labelPrinter.enableServiceTypeRestriction']/div/ul/li/input")).sendKeys(Keys.ENTER);
			
		}
		
		Thread.sleep(2000);
		//Click the Update button
		driver.findElement(By.xpath("//button[@id='saveLabel']")).click();
		Thread.sleep(4000);driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Check whether the Kitchen printer was updated or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Printer updated successfully"))
		{
			test.log(LogStatus.PASS, "Label Printer Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Label Printer Updated fail");
		}
		Thread.sleep(3000);
		//Clear the Search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		Thread.sleep(2000);
	}
	
	@Test(priority=30,enabled = false)
	public void Label_Printer_method_delete_Label_Printer(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(2000);
		//Clear the Search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required key word
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("New_Label_Print1");
		
		Thread.sleep(1000);
		//Click the Delete button
		driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
		
		//Click the Yes button in the popup
		Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
		
		Thread.sleep(4000);driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Check the Kitchen Printer deleted or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Printer deleted successfully"))
		{
			test.log(LogStatus.PASS, "New Label Printer is deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Label Printer is deleted Failed");
		}
		Thread.sleep(3000);
		
		//Clear the Search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required key word
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("New_Label_Print1");
		
		Thread.sleep(2000);
		try{
			if(driver.findElement(By.xpath("//td[contains(.,'New_Label_Print1')]")).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Deleted Printer available here");
			}
		}

		catch(Exception e)
		{
			test.log(LogStatus.PASS, "Deleted Printer not available here");
		}
		//Clear the Search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		Thread.sleep(2000);
	}
	
	@Test(priority=31,enabled = false)
	public void Label_Printer_method_close_Button(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Click Add Label Printer Button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/button")).click();
		
		Thread.sleep(3000);
		//Check New Label Printer form opened or not
		if(driver.findElement(By.xpath("//span[.='New Label Printer']")).getText().equalsIgnoreCase("New Label Printer"))
		{
			test.log(LogStatus.PASS, "Label printer form opened Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Label printer form opened Failed");
		}
		Thread.sleep(3000);
		
		//Click the Types Option
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[1]/div/a")).click();
		//Enter the required type
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[1]/div/div/div/input")).sendKeys("Address");
		//Press Enter button
		driver.findElement(By.xpath("//form[@name='labelPrinterCreation']/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the name field
		driver.findElement(By.name("name")).clear();
		//Enter the name
		driver.findElement(By.name("name")).sendKeys("New_Label_Print");
		
	
		Thread.sleep(2000);
		//Click the Close button
		driver.findElement(By.xpath("//a[.='Close']")).click();
		Thread.sleep(2000);
		
		//Check whether the Label printer form was closed or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/button")).isDisplayed())
		{
			test.log(LogStatus.PASS, "Label Printer form Closed successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Label Printer form Closed fail");
		}
		Thread.sleep(3000);

	}


}
