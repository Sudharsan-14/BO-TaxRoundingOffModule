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


public class Inventory_Reports_Purchased_Item {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Reports_Purchased_Item");

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
			Inventory_Reports_Purchased_Items_openpage(driver);
			Inventory_Reports_Purchased_Items_verify_Purchased_Items_Report_For_All(driver);
			Inventory_Reports_Purchased_Items_verify_Purchased_Items_Report_For_Inventory_Item(driver);
			Inventory_Reports_Purchased_Items_verify_Purchased_Items_Report_For_Sub_Recipe(driver);
			Thread.sleep(1500);
		}
	
	@Test(enabled=false,priority=33)
	public void Inventory_Reports_Purchased_Items_openpage(WebDriver driver) throws Exception
	{
			/*//Click the Inventory option
			driver.findElement(By.xpath("//span[.='Inventory']")).click();
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='Reports ']"));
			//Scroll the page till the Inventory Items option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
	        //Click the Inventory Reports Option		
			driver.findElement(By.xpath("//span[.='Reports ']")).click();
			Thread.sleep(5000);
			*/
			
			Thread.sleep(5000);
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"purchaseItems");
			Thread.sleep(2000);
			//driver.findElement(By.xpath("//span[.=' Purchased Items ']")).click();
			//Check Inventory Items page opened or not
			if(driver.findElement(By.xpath("//a[.='Purchased Items']")).getText().equalsIgnoreCase("Purchased Items"))
			{
				test.log(LogStatus.PASS, "Inventory Reports page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inventory Reports page loaded Failed");
			}
			
			Thread.sleep(5000);
			
		}
		
	@Test(enabled=false,priority=34)
	public void Inventory_Reports_Purchased_Items_verify_Purchased_Items_Report_For_All(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Select the particular Type
		Select types = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/select")));
		types.selectByVisibleText("All");
		
		//Select the required Time period option
		Select timePeriod = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/select")));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(2000);
		//Clear the From date check box
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/input[1]")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Date_Range_From"));
		
		Thread.sleep(1000);
		//Clear the To date check box
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/input[2]")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Date_Range_To"));

		Thread.sleep(2000);
		//Click the Search
		driver.findElement(By.xpath("//button[.='Search']")).click();
		
		try {
			if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr/td")).getText().equalsIgnoreCase("No purchase record for selected item or time period"))
			{
				List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr"));
				rows.size();
						
				Thread.sleep(5000);
				for(int i = 1; i <= rows.size(); i++)
				{
			    	//Replace all commo's with empty space
			    	String expected = Utility.getReportProperty("Purchesed_Item_Price"+i).replace(",", "");
			    	
			    	//Convert the String value of the purchasedItemCounts element into int value
			    	float expects = Float.parseFloat(expected);
			    	
			    	String actual_Price1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr["+i+"]/td[5]")).getText();
			    	
			    	String actual_Price = actual_Price1.replaceAll("[a-zA-Z $ ₹ ,]", "");
			    	
			    	float price = Float.parseFloat(actual_Price);
			    	
			    	float unknownValue = Float.parseFloat(actual_Price);
					if(expects == price)
			    	{
			    		test.log(LogStatus.PASS, "Purchased Item("+i+") Report and Received Log Items are Same");
			    		
			    		test.log(LogStatus.INFO, "Purchased Item("+i+") aoumnt is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr["+i+"]/td[5]")).getText());
			    	}
			    	else if(expects == unknownValue)
			    	{
						test.log(LogStatus.PASS, "Here we don't have the exact expected value");
						
			        	//Get the Total value of Check Count
			        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr["+i+"]/td[5]")).getText();

			        	System.out.println("The Actual Purchased Item("+i+") Amount value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Purchased Item("+i+") Amount value is : "+actualText);
			    	}
			    	else
			    	{
			    		test.log(LogStatus.FAIL, "Purchased Item("+i+") Report and Received Log Items are different");
			    		
			    		float diff = price - expects;
			    		
			    		test.log(LogStatus.FAIL, "Different is : "+diff);
			    	}
				}
			}
		} catch (Exception e) {
		
			test.log(LogStatus.FAIL, "There is no Report available for Purchased Items");
		}
		
		

	}
	
	@Test(enabled=false,priority=35)
	public void Inventory_Reports_Purchased_Items_verify_Purchased_Items_Report_For_Inventory_Item(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Select the particular Type
		Select types = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/select")));
		types.selectByVisibleText("Inventory Item");
		
		//Click the Category filed
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/a")).click();
		//Enter the required Category
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Inventory Item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/div/a")).click();
		//Enter the required Inventory Item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/div/div/div/input")).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Select the required Time period option
		Select timePeriod = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/select")));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(2000);
		//Clear the From date check box
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[5]/div/div/input[1]")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[5]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Date_Range_From"));
		
		Thread.sleep(1000);
		//Clear the To date check box
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[5]/div/div/input[2]")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[5]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Date_Range_To"));

		Thread.sleep(2000);
		//Click the Search
		driver.findElement(By.xpath("//button[.='Search']")).click();
		
		Thread.sleep(3000);
		
		try {
			if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr/td")).getText().equalsIgnoreCase("No purchase record for selected item or time period"))
			{
				List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr"));
				rows.size();
						
				Thread.sleep(5000);
				for(int i = 1; i <= rows.size(); i++)
				{
					System.out.println("Purchased Item("+i+") aoumnt is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr["+i+"]/td[5]")).getText());
					
					test.log(LogStatus.INFO, "Purchased Item("+i+") aoumnt is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr["+i+"]/td[5]")).getText());
				}
			}
	
		}catch(Exception e)
		{
			test.log(LogStatus.FAIL, "There is no Report available for Purchased Items(Inventory Item)");
		}
	}
	
	@Test(enabled=false,priority=36)
	public void Inventory_Reports_Purchased_Items_verify_Purchased_Items_Report_For_Sub_Recipe(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Select the particular Type
		Select types = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/select")));
		types.selectByVisibleText("Sub Recipe");
		
		//Click the Sub Recipe filed
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/a")).click();
		//Enter the required Category
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		
		//Select the required Time period option
		Select timePeriod = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/select")));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(2000);
		//Clear the From date check box
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/input[1]")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Date_Range_From"));
		
		Thread.sleep(1000);
		//Clear the To date check box
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/input[2]")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Date_Range_To"));

		Thread.sleep(2000);
		//Click the Search
		driver.findElement(By.xpath("//button[.='Search']")).click();
		
		Thread.sleep(3000);
		
		try {
			if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr/td")).getText().equalsIgnoreCase("No purchase record for selected item or time period"))
			{
				List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr"));
				rows.size();
						
				Thread.sleep(5000);
				for(int i = 1; i <= rows.size(); i++)
				{
					System.out.println("Purchased Item("+i+") aoumnt is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr["+i+"]/td[5]")).getText());
					
					test.log(LogStatus.INFO, "Purchased Item("+i+") aoumnt is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr["+i+"]/td[5]")).getText());
				}
			}
	
		}catch(Exception e)
		{
			test.log(LogStatus.FAIL, "There is no Report available for Purchased Items(Sub Recipe)");
		}
	}

}
