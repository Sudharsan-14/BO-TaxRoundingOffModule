package epicList_Chrome_Xpath;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Setting_EMV_Setting {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Setting_EMV_Setting");
	
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
			EMV_Settings_Method_openpage(driver);
			EMV_Settings_Method_refresh(driver);
			EMV_Settings_Method_pagination(driver);
			EMV_Settings_Method_add(driver);
			EMV_Settings_Method_edit(driver);
			EMV_Settings_Method_delete(driver);
			EMV_Settings_Method_close_Button(driver);
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

	@Test(enabled=false,priority=16)
	public void EMV_Settings_Method_openpage(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Enter the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"emvSettings");
			
			Thread.sleep(3000);
			
			//Check weather the Emv Settings page is loaded or not
			if(driver.findElement(By.xpath("//a[.='Emv Settings']")).getText().equalsIgnoreCase("Emv Settings"))
			{
				test.log(LogStatus.PASS, "Emv Settings page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Emv Settings page loaded fail");
			}

			Thread.sleep(3000);

		}	
		
	@Test(enabled=false,priority=17)
	public void EMV_Settings_Method_refresh(WebDriver driver) throws Exception
		{
			//Click the refresh button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/a[1]/i")).click();
			Thread.sleep(3000);
			
			//Check weather the page is refreshed or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[1]/div/h3")).getText().equalsIgnoreCase("Emv Settings"))
			{
				test.log(LogStatus.PASS, "Emv Settings Page refreshed successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Emv Settings Page refreshed successfully");
			}
			
		}
		
	@Test(enabled=false,priority=18)
	public void EMV_Settings_Method_pagination(WebDriver driver) throws InterruptedException
		{
			
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			Thread.sleep(1000);
			
			//Click the Pagination option as 10
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/div/div/div/ul/li[2]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for EMV Settings");
			//Create the web element for Emv Settings Table
			List<WebElement> results = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/table"));
			for (WebElement result : results){						
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for EMV Settings");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 15
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/div/div/div/ul/li[3]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for EMV Settings");
			//Create the web element for Emv Settings Table
			List<WebElement> results1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/table"));
			for (WebElement result : results1){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for EMV Settings");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 20
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/div/div/div/ul/li[4]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for EMV Settings");
			//Create the web element for Emv Settings Table
			List<WebElement> results2 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/table"));
			for (WebElement result : results2){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for EMV Settings");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 5
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/div/div/div/ul/li[1]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for EMV Settings");
			//Create the web element for Emv Settings Table
			List<WebElement> results3 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/table"));
			for (WebElement result : results3){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for EMV Settings");
			}
			Thread.sleep(3000);
		}
		
	@Test(enabled=false,priority=19)
	public void EMV_Settings_Method_add(WebDriver driver) throws Exception
		{
			Thread.sleep(2000);
			//Click the Add EMV settings button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/button")).click();
			Thread.sleep(2000);
			
			//Check weather the new form loaded or not
			if(driver.findElement(By.xpath("//span[.='New EMV Settings']")).getText().equalsIgnoreCase("New EMV Settings"))
			{
				test.log(LogStatus.PASS, "New EMV Settings form or page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New EMV Settings form or page loaded fail");
			}
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.name("name")).clear();
			//Enter the name
			driver.findElement(By.name("name")).sendKeys("New_EMV");
			
			Thread.sleep(2000);
			//Clear the IP Address field
			driver.findElement(By.name("ipAddress")).clear();
			//Enter the IP Address
			driver.findElement(By.name("ipAddress")).sendKeys("192.100.100.145");
			
			//Select the required type
			Select type = new Select(driver.findElement(By.name("type")));
			type.selectByVisibleText("OPTOMANY");
			
			//Clear the port field
			driver.findElement(By.name("optomanyPort")).clear();
			//Enter the PORT number
			driver.findElement(By.name("optomanyPort")).sendKeys("3");
			
			Thread.sleep(5000);
			//Click the Save button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(2500);
			
			//Check weather the new denomination form saved or not
			if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("EMV Saved Successfully"))
			{
				test.log(LogStatus.PASS, "New EMV saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New EMV saved fail");
			}
		}
				
	@Test(enabled=false,priority=20)
	public void EMV_Settings_Method_edit(WebDriver driver) throws Exception
		{
			Thread.sleep(2000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//enter the keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("New_EMV");
			
			//Click the Delete button
			driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.name("name")).clear();
			//Enter the name
			driver.findElement(By.name("name")).sendKeys("New_EMV1");
			
			Thread.sleep(2000);
			//Clear the IP Address field
			driver.findElement(By.name("ipAddress")).clear();
			//Enter the IP Address
			driver.findElement(By.name("ipAddress")).sendKeys("192.100.100.121");
			
			Thread.sleep(4000);
			//Click the update button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			Thread.sleep(4000);
			//Check weather the new denomination form saved or not
			if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("EMV Updated Successfully"))
			{
				test.log(LogStatus.PASS, "New EMV Updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New EMV Updated fail");
			}
			Thread.sleep(3000);
			
			
		}
				
	@Test(enabled=false,priority=21)
	public void EMV_Settings_Method_delete(WebDriver driver) throws Exception
		{
			Thread.sleep(2000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//enter the keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("New_EMV1");
			
			//Click the Delete button
			driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
			
			//Click the Yes button in the popup
			Thread.sleep(5000);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			
			Thread.sleep(3000);
			//Check the denomination deleted or not
			if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("EMV Settings removed successfuly"))
			{
				test.log(LogStatus.PASS, "EMV settings deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "EMV Settings deleted Failed");
			}
			Thread.sleep(3000);
			
			
		}
		
	@Test(enabled=false,priority=22)
	public void EMV_Settings_Method_close_Button(WebDriver driver) throws Exception
		{
			Thread.sleep(2000);
			//Click the Add EMV settings button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/button")).click();
			Thread.sleep(2000);
			
			//Check weather the new form loaded or not
			if(driver.findElement(By.xpath("//span[.='New EMV Settings']")).getText().equalsIgnoreCase("New EMV Settings"))
			{
				test.log(LogStatus.PASS, "New EMV Settings form or page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New EMV Settings form or page loaded fail");
			}
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.name("name")).clear();
			//Enter the name
			driver.findElement(By.name("name")).sendKeys("New_EMV");
			
			Thread.sleep(2000);
			//Clear the IP Address field
			driver.findElement(By.name("ipAddress")).clear();
			//Enter the IP Address
			driver.findElement(By.name("ipAddress")).sendKeys("192.100.100.111");
			
			Thread.sleep(1000);
			//Click the Close button
			driver.findElement(By.xpath("//a[.='Close']")).click();
			Thread.sleep(2000);
			
			//Check weather the new emv settings form closed or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/button")).isDisplayed())
			{
				test.log(LogStatus.PASS, "EMV Settings form Closed successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "EMV Settings form Closed fail");
			}
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);}
	}
	
}
