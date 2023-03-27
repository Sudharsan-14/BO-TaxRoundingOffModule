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


public class Royalty_Franchise_Settings {


	public WebDriver driver;
		
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Royalty_Franchise_Settings");

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
			Enterprice_Royalty_Franchise_Settings_method_open_Enterprice_Royalty_Franchise_Settings(driver);
			Enterprice_Royalty_Franchise_Settings_method_Pagination(driver);
			Enterprice_Royalty_Franchise_Settings_method_Update_Settings(driver);
			Enterprice_Royalty_Franchise_Settings_method_Close_Button(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=8)
	public void Enterprice_Royalty_Franchise_Settings_method_open_Enterprice_Royalty_Franchise_Settings(WebDriver driver) throws Exception
	{
		/*driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();

		
		Thread.sleep(3000);
        //Click the Royalty/Franchise Option		
		driver.findElement(By.xpath("//span[.='Royalty/Franchise']")).click();
		

		*/
		//Enter the Coursing URL
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"royalty");
		Thread.sleep(8000);
		//Check Royalty/Franchise page opened or not
		if(driver.findElement(By.xpath("//a[.='Royalty/Franchise']")).getText().equalsIgnoreCase("Royalty/Franchise"))
		{
			test.log(LogStatus.PASS, "Royalty/Franchise Settings page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Royalty/Franchise Settings page loaded Failed");
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
	
	@Test(enabled=false,priority=9)
	public void Enterprice_Royalty_Franchise_Settings_method_Pagination(WebDriver driver) throws Exception
	{

		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		Thread.sleep(1000);
		
		//Click the Pagination option as 10
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div[2]/div/div/div/ul/li[2]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Royalty/Franchise");
		//Create the web element for Account Users Table
		List<WebElement> results = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[2]/table"));
		for (WebElement result : results){						
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Royalty/Franchise, When user click 10");
		}
		Thread.sleep(4000);
		
		//Click the Pagination option as 15
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div[2]/div/div/div/ul/li[3]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Royalty/Franchise");
		//Create the web element for Account Users Table
		List<WebElement> results1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[2]/table"));
		for (WebElement result : results1){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Royalty/Franchise, When user click 15");
		}
		Thread.sleep(4000);
		
		//Click the Pagination option as 20
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div[2]/div/div/div/ul/li[4]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 20 Fran");
		//Create the web element for Account Users Table
		List<WebElement> results2 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[2]/table"));
		for (WebElement result : results2){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Royalty/Franchise, When user click 20");
		}
		Thread.sleep(4000);
		
		//Click the Pagination option as 5
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div[2]/div/div/div/ul/li[1]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Royalty/Franchise");
		//Create the web element for Account Users Table
		List<WebElement> results3 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[2]/table"));
		for (WebElement result : results3){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Royalty/Franchise, When user click 5");
		}
		Thread.sleep(4000);
	}

	@Test(enabled=false,priority=10)
	public void Enterprice_Royalty_Franchise_Settings_method_Update_Settings(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Search the required store
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Store"));
		
		Thread.sleep(8000);
		//Click Royalty/Franchise button is enabled or disabled
		driver.findElement(By.xpath("//td[2]/div/div/label/span")).click();
		
		Thread.sleep(1500);
		//Check whether the Royalty/Franchise button is enabled or not
		if(driver.findElement(By.xpath("//td[2]/div/div/label/input")).isSelected())
		{}
		else
		{
			Thread.sleep(2500);
			
			//Click Royalty/Franchise button is enabled or disabled
			driver.findElement(By.xpath("//td[2]/div/div/label/span")).click();
		}

		Thread.sleep(1500);
		//Enter the Bank Details
		//Clear the routing field
		driver.findElement(By.xpath("//td[3]/div[1]/input")).clear();
		//Enter the required routing name
		driver.findElement(By.xpath("//td[3]/div[1]/input")).sendKeys("Test");
		
		Thread.sleep(1500);
		//Clear the Bank Name
		driver.findElement(By.xpath("//td[3]/div[2]/input")).clear();
		//Enter the Bank Name
		driver.findElement(By.xpath("//td[3]/div[2]/input")).sendKeys("Standard");
		
		Thread.sleep(1500);
		//Clear the Account Number
		driver.findElement(By.xpath("//td[3]/div[3]/input")).clear();
		//Enter the Required Account Number
		driver.findElement(By.xpath("//td[3]/div[3]/input")).sendKeys("7458962531");
		
		//System.out.println("Per : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div[2]/table/tbody/tr/td[4]/div/div[3]/ng-form/div/div/div[2]/div/input")).getText());
		
		Thread.sleep(2000);
		//Click the Add Percentage button
		driver.findElement(By.xpath("//td[4]/div/div[1]/div/a/i")).click();
		
		//Clear the name of percentage
		driver.findElement(By.xpath("//td[4]/div/div[3]/ng-form/div/div/div[1]/div/input")).clear();
		//Enter the name of Percentage
		driver.findElement(By.xpath("//td[4]/div/div[3]/ng-form/div/div/div[1]/div/input")).sendKeys("Employee");
	
		Thread.sleep(1500);
		//Clear the percentage
		driver.findElement(By.xpath("//td[4]/div/div[3]/ng-form/div/div/div[2]/div/input")).clear();
		//Enter the percentage
		driver.findElement(By.xpath("//td[4]/div/div[3]/ng-form/div/div/div[2]/div/input")).sendKeys("5");
		
		Thread.sleep(1000);
		//Enable or Disable the Set Default in the newly add percentage option
		driver.findElement(By.xpath("//td[4]/div/div[3]/ng-form/div/div/div[3]/label/span")).click();

		Thread.sleep(2000);
		//Click the Add Percentage button
		driver.findElement(By.xpath("//td[4]/div/div[1]/div/a/i")).click();
		
		Thread.sleep(1000);
		//Clear the name of percentage
		driver.findElement(By.xpath("//td[4]/div/div[4]/ng-form/div/div/div[1]/div/input")).clear();
		//Enter the name of Percentage
		driver.findElement(By.xpath("//td[4]/div/div[4]/ng-form/div/div/div[1]/div/input")).sendKeys("Employee");
		
		//Clear the percentage
		driver.findElement(By.xpath("//td[4]/div/div[4]/ng-form/div/div/div[2]/div/input")).clear();
		//Enter the percentage
		driver.findElement(By.xpath("//td[4]/div/div[4]/ng-form/div/div/div[2]/div/input")).sendKeys("50");
		
		Thread.sleep(1000);
		//Enable or Disable the Set Default in the newly add percentage option
		driver.findElement(By.xpath("//td[4]/div/div[4]/ng-form/div/div/div[3]/label/span")).click();
		
		Thread.sleep(3000);
		//Check whether the error or warning message displayed or not
		if(driver.findElement(By.xpath("//p[.='Select only one as default']")).isDisplayed())
		{
			test.log(LogStatus.PASS, "Error message showed successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Error message showed fail");
		}
		Thread.sleep(1000);
		//Enable or Disable the Set Default in the newly add percentage option
		driver.findElement(By.xpath("//td[4]/div/div[4]/ng-form/div/div/div[3]/label/span")).click();

		Thread.sleep(2000);
		//Click the Add Percentage button
		driver.findElement(By.xpath("//td[4]/div/div[1]/div/a/i")).click();

		Thread.sleep(2000);
		//Click the Close button of newly added one
		driver.findElement(By.xpath("//td[4]/div/div[5]/ng-form/div/div/div[3]/a/i")).click();
		
		Thread.sleep(1000);
		//Click the Yes button
		driver.findElement(By.xpath("//a[contains(.,'Yes')]")).click();
		
		Thread.sleep(2000);
		//Click the Save button in Controls field
		driver.findElement(By.xpath("//button[@class='btn btn-small btn-success']")).click();
		
		Thread.sleep(2500);
		//Check whether the account user is saved or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Royalty details updated successfully."))
		{
			test.log(LogStatus.PASS, "Royalty/Franchise updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Royalty/Franchise updated fail");
		}
		
		Thread.sleep(3000);
		
		Thread.sleep(2000);
		//Click the Close button of newly added one
		driver.findElement(By.xpath("//td[4]/div/div[4]/ng-form/div/div/div[3]/a/i")).click();
		
		Thread.sleep(1000);
		//Click the Yes button
		driver.findElement(By.xpath("//a[@class='btn btn-small btn-success']")).click();
		
		Thread.sleep(2000);
		//Click the Save button in Controls field
		driver.findElement(By.xpath("//button[@class='btn btn-small btn-success']")).click();
		Thread.sleep(3000);
		
		
	}
	
	@Test(enabled=false,priority=11)
	public void Enterprice_Royalty_Franchise_Settings_method_Close_Button(WebDriver driver) throws Exception
	{

		Thread.sleep(3500);
		//Enter the Bank Details
		//Clear the routing field
		driver.findElement(By.xpath("//td[3]/div[1]/input")).clear();
		//Enter the required routing name
		driver.findElement(By.xpath("//td[3]/div[1]/input")).sendKeys("Test");
		
		Thread.sleep(1500);
		//Clear the Bank Name
		driver.findElement(By.xpath("//td[3]/div[2]/input")).clear();
		//Enter the Bank Name
		driver.findElement(By.xpath("//td[3]/div[2]/input")).sendKeys("Standard");
		
		Thread.sleep(1500);
		//Clear the Account Number
		driver.findElement(By.xpath("//td[3]/div[3]/input")).clear();
		//Enter the Required Account Number
		driver.findElement(By.xpath("//td[3]/div[3]/input")).sendKeys("7458962531");
		
		Thread.sleep(2000);
		//Click the Add Percentage button
		driver.findElement(By.xpath("//td[4]/div/div[1]/div/a/i")).click();
		
		Thread.sleep(3000);
		//Clear the name of percentage
		driver.findElement(By.xpath("//td[4]/div/div[4]/ng-form/div/div/div[1]/div/input")).clear();
		//Enter the name of Percentage
		driver.findElement(By.xpath("//td[4]/div/div[4]/ng-form/div/div/div[1]/div/input")).sendKeys("Employee");
		
		//Clear the percentage
		driver.findElement(By.xpath("//td[4]/div/div[4]/ng-form/div/div/div[2]/div/input")).clear();
		//Enter the percentage
		driver.findElement(By.xpath("//td[4]/div/div[4]/ng-form/div/div/div[2]/div/input")).sendKeys("50");
		
		//Click the Close button in Controls field
		driver.findElement(By.xpath("//a[@class='btn btn-small btn-danger']")).click();
		Thread.sleep(5000);
		
		int close = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div[2]/table/tbody/tr/td[4]/div/div/ng-form/div/div/div[3]/a")).size();
		//Check whether the Closed or not
		if( close == 1)
		{
			test.log(LogStatus.PASS, "Close button working fine");
		}
		else
		{
			test.log(LogStatus.FAIL, "Close button not working");
		}
		Thread.sleep(2000);
		//Click Royalty/Franchise button is enabled or disabled
		driver.findElement(By.xpath("//td[2]/div/div/label/span")).click();
		
		Thread.sleep(2000);
		//Click the Save button in Controls field
		driver.findElement(By.xpath("//button[@class='btn btn-small btn-success']")).click();
		Thread.sleep(3000);
		
		if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);}
	}

}
