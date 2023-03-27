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


public class Inventory_Transfer_Request {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Transfer_Request");

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
			Inventory_Transfer_Requests_Pageopen(driver);
			Inventory_TransferRequest_Refresh(driver);
			Inventory_TransferRequest_addCancel(driver);
			Inventory_TransferRequest_add(driver);
			Inventory_Centralwarehouse_Pageopen(driver);
			Inventory_Centralwarehouse_openTransfer_Centrallevel(driver);
			Inventory_Centralwarehouse_Backbutton(driver);
			Inventory_Centralwarehouse_Click_Transferbutton_Errormessage(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=44)
		public void Inventory_Transfer_Requests_Pageopen(WebDriver driver) throws Exception
		{
	
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"transfer");
			
			Thread.sleep(3000);
			//Check Inventory Items page opened or not
			if(driver.findElement(By.xpath("//div[@id='navigation']/ul[1]/li/a")).getText().equalsIgnoreCase("Transfer Requests"))
			{
				test.log(LogStatus.PASS, " Transfer Requests page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, " Transfer Requests page loaded Failed");
			}
			
			Thread.sleep(3000);
			
		}
	
		@Test(enabled=false,priority=45)
		public void Inventory_TransferRequest_Refresh(WebDriver driver) throws InterruptedException
		{
			Thread.sleep(2000);
			//Click refresh the button for Transfer request
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/a/i")).click();
		
		}
	
		@Test(enabled=false,priority=47)
		public void Inventory_TransferRequest_addCancel(WebDriver driver) throws Exception
		{
			
		Thread.sleep(3000);
		//Click Transfer Request button
		driver.findElement(By.cssSelector("a.btn.btn-small.btn-inverse.btn--icon.ng-binding")).click();
		//Check New Transfer Request page opened or not
		if(driver.findElement(By.xpath("//span[.='New Transfer Request']")).getText().equalsIgnoreCase("New Transfer Request"))
		{
			test.log(LogStatus.PASS, "New Transfer Request page Successfully loaded ");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Transfer Request page Successfully loaded is failed");
		}
		Thread.sleep(2000);
		//Click Destination field to select store
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[1]/div/div/div/a")).click();
		
		Thread.sleep(1000);
		//Click Destination field to select store
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[1]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("Inventory_Menuconfic_From_CentralWareHouse"));
		
		Thread.sleep(1000);
		//Click Destination field to select store
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
	
		Thread.sleep(2000);
		//Click new Inventory item button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr/td[1]/div/a/i")).click();
		
		Thread.sleep(2000);
		//Select new Inventory item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[1]/div/a")).click();
		
		
		Thread.sleep(1000);
		//select Inventory item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[1]/div/div/div/input")).sendKeys(Utility.getProperty("Transfer_Inventory_item"));
		Thread.sleep(1000);
	
		//select Inventory item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//Clear the quantity value
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[4]/input")).clear();
		//Enter the quantity value
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[4]/input")).sendKeys("1");
	
	
						Thread.sleep(1000);
						//Click remove inventory item button
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[6]/a/i")).click();
						
						Thread.sleep(3000);
						//Click cancel the inventory item
						driver.findElement(By.cssSelector("a.btn.btn-small.btn-danger.ng-binding")).click();
						
						Thread.sleep(3000);
						
		}	
		
		@Test(enabled=false,priority=48)
		public void Inventory_TransferRequest_add(WebDriver driver) throws Exception
		{
						
		Thread.sleep(3000);
		//Click Transfer Request button
		driver.findElement(By.cssSelector("a.btn.btn-small.btn-inverse.btn--icon.ng-binding")).click();
		//Check New Transfer Request page opened or not
				if(driver.findElement(By.xpath("//span[.='New Transfer Request']")).getText().equalsIgnoreCase("New Transfer Request"))
				{
					test.log(LogStatus.PASS, "New Transfer Request page Successfully loaded ");
				}
				else
				{
					test.log(LogStatus.FAIL, "New Transfer Request page Successfully loaded is failed");
				}
				Thread.sleep(2000);
				//Click Destination field to select store
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[1]/div/div/div/a")).click();
				
				Thread.sleep(1000);
				//Click Destination field to select store
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[1]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("Inventory_Menuconfic_From_CentralWareHouse"));
				
				Thread.sleep(1000);
				//Click Destination field to select store
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
				
			
				Thread.sleep(2000);
				//Click new Inventory item button
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr/td[1]/div/a/i")).click();
				
				Thread.sleep(2000);
				//Select new Inventory item
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[1]/div/a")).click();
				
				
				Thread.sleep(1000);
				//select Inventory item
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[1]/div/div/div/input")).sendKeys(Utility.getProperty("Transfer_Inventory_item"));
				Thread.sleep(1000);
			
				//select Inventory item
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[1]/div/div/div/input")).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				
				//Clear the quantity value
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[4]/input")).clear();
				//Enter the quantity value
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[4]/input")).sendKeys("1");
	
	
				Thread.sleep(2000);
				//Click new Inventory item button
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr/td[1]/div/a/i")).click();
				
				Thread.sleep(2000);
				//Select new Inventory item
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[2]/td[1]/div/a")).click();
				
				
				Thread.sleep(1000);
				//select Inventory item		
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[2]/td[1]/div/div/div/input")).sendKeys(Utility.getProperty("Transfer_Inventory_item2"));
				Thread.sleep(1000);
			
				//select Inventory item
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[2]/td[1]/div/div/div/input")).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				
				//Clear the quantity value
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[2]/td[4]/input")).clear();
				//Enter the quantity value
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[2]/td[4]/input")).sendKeys("1");
	
			
			//Click Request button for inventory item
			driver.findElement(By.cssSelector("button.btn.btn-small.btn-success.ng-binding")).click();
			Thread.sleep(3000);	
			//Check Central Warehouse page opened or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Transfer request created successfully!."))
			{
				test.log(LogStatus.PASS, "Transfer request created successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Transfer request created is Failed");
			}
			try		
			{			
			if(driver.findElement(By.xpath("//td[.='No Records Found']")).isDisplayed())			
			{				
			test.log(LogStatus.FAIL, "There is no record available for Transfer Requests(store level inventory)");			
			}		
			}		
			catch (Exception e)		
			{			
			test.log(LogStatus.PASS, "Record available for Transfer Requests(store level inventory)");					
			 
			List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr/td[1]"));
				for(int i = 2; i <= rows.size(); i++)
				{
				 //Get the Transfer request
				test.log(LogStatus.PASS,"Source of the  "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[1]")).getText()+"  "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText()+" to "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText()+" on " +driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[4]")).getText());
				}
			}
			}
		
		@Test(enabled=false,priority=49)
		public void Inventory_Centralwarehouse_Pageopen(WebDriver driver) throws Exception
		{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[4]/a/span[1]"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			Thread.sleep(3000);
			//Click the Inventory option
			driver.findElement(By.xpath("//span[.='Inventory']")).click();
			//Click the My Stores option
			driver.findElement(By.xpath("//span[.='My Stores']")).click();
			Thread.sleep(3000);
			
		}
		
		@Test(enabled=false,priority=50)
		public void Inventory_Centralwarehouse_openTransfer_Centrallevel(WebDriver driver) throws Exception
		{
			Thread.sleep(1000);
	        //Click the Central Inventory Reports Option		
			driver.findElement(By.xpath("//span[.='Central Inventory']")).click();
			
			Thread.sleep(2000);
			//Click the Central WareHouse option
			driver.findElement(By.xpath("//span[.='Central WareHouse']")).click();
			
				Thread.sleep(5000);
				if(driver.findElement(By.xpath("//div[.='"+Utility.getProperty("Inventory_Menuconfic_From_CentralWareHouse")+"']")).isDisplayed())
				{
					driver.findElement(By.xpath("//div[.='"+Utility.getProperty("Inventory_Menuconfic_From_CentralWareHouse")+"']")).click();				
					
					JavascriptExecutor je = (JavascriptExecutor) driver;
					//Identify the WebElement which will appear after scrolling down
					WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[2]/ul/li[12]/a/span"));
					//Scroll the page till the Reason option present
					je.executeScript("arguments[0].scrollIntoView(true);",element); 
					//Wait for 30 seconds
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					//Click the  Transfer Option		
					driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[2]/ul/li[12]/a/span")).click();
				}
		}
				
		@Test(enabled=false,priority=51)
		public void Inventory_Centralwarehouse_Backbutton(WebDriver driver) throws Exception		
		{	
				Thread.sleep(2000);
				//Click Accept button
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr/td[5]/span/a[2]/i")).click();//NEED TO CHECK
				//Clear the search field
			//	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div/label/input")).clear();
				//Enter the search field
			//	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div/label/input")).sendKeys(Utility.getProperty("Inventory_Menuconfic_From_CentralWareHouse"));
				
				Thread.sleep(2000);
				//Clear the Transfer Count
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[6]/input")).clear();
				Thread.sleep(2000);

				//Enter the Transfer Count
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[6]/input")).sendKeys("3");
				Thread.sleep(2000);

				//Click back button after entering the values
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/button")).click();
				
				}
				
		@Test(enabled=false,priority=52)
		public void Inventory_Centralwarehouse_Click_Transferbutton_Errormessage(WebDriver driver) throws Exception		
		{	
				{
				Thread.sleep(2000);
				//Click Accept button
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr/td[5]/span/a[2]/i")).click();//NEED TO CHECK
				//Clear the search field
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div/label/input")).clear();
				//Enter the search field
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div/label/input")).sendKeys(Utility.getProperty("Inventory_Menuconfic_From_CentralWareHouse"));
				
				Thread.sleep(2000);
				//Clear the Transfer Count
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[6]/input")).clear();
				Thread.sleep(2000);

				//Enter the Transfer Count
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[6]/input")).sendKeys("1");
				Thread.sleep(2000);
				//Click Transfer Items button
				driver.findElement(By.cssSelector("button.btn.btn-small.btn-success.btn-border-green.ng-binding")).click();
				
				//Check Central Warehouse page opened or not
				if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("item(s) are not transferred"))
				{
					test.log(LogStatus.PASS, "Transfer request accepted successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Transfer request accepted is Failed");
				}
				}
				}

}
