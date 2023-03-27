package epicList_Chrome_Xpath;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Sort_Menu_Config {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Sort_Menu_Configuration");
	
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
			Sort_Menu_Config_openSortMenuConfig(driver);
			Sort_Menu_Config_Category_Sorting(driver);
			Sort_Menu_Config_Subctegory_Sorting(driver);
			Sort_Menu_Config_MenuItem_Sorting(driver);
			Sort_Menu_Config_closeButton(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=2)
	public void Sort_Menu_Config_openSortMenuConfig(WebDriver driver) throws Exception
	{
		Thread.sleep(15000);
/*		//Click the Products/Items option
		driver.findElement(By.xpath("//span[.='Products/Items']")).click();Thread.sleep(2000);
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Sort Menu Config']"));Thread.sleep(2000);
		//Scroll the page till the Reason option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Discounts Option		
		driver.findElement(By.xpath("//span[.='Sort Menu Config']")).click();*/
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"sort");
		Thread.sleep(5000);
		//Check Up Charges page opened or not
		if(driver.findElement(By.xpath("//a[.='Sort']")).getText().equalsIgnoreCase("Sort"))
		{
			test.log(LogStatus.PASS, "Sort Menu Config page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Sort Menu Config page loaded Failed");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=3)
	public void Sort_Menu_Config_Category_Sorting(WebDriver driver) throws Exception
	{
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		//Create the webelement for the Category field
		WebElement showCategory = driver.findElement(By.xpath("//div[@class='box_go']/div[1]"));
		actions.moveToElement(showCategory);				
		actions.click().build().perform();
		Thread.sleep(5000);
		
		//Click the settings icon of Category
		driver.findElement(By.xpath("//div[@class='pull-left']/div/img")).click();
		Thread.sleep(2000);		   
		//Check the Category sort field page opened or not
		if(driver.findElement(By.id("myModalLabel")).getText().equalsIgnoreCase("Category Sort"))
		{
			test.log(LogStatus.PASS, "Category Sort page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Category Sort page loaded Failed");
		}
		Thread.sleep(3000);
		//Click the Sort Z to A button
		driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/div/div[2]/a[3]")).click();
		Thread.sleep(3000);
		
		//Click the Save button
		driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
		Thread.sleep(3000);
		
		//Check the required items sorted or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Items sorted successfully"))
		{
			test.log(LogStatus.PASS, "Category sorted by Decending order Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Category sorted by Decending order Failed");
		}
		Thread.sleep(10000);
		
		actions.moveToElement(showCategory);
		actions.click().build().perform();
		Thread.sleep(5000);
		
		//Click the settings icon
		driver.findElement(By.xpath("//div[@class='pull-left']/div/img")).click();
		Thread.sleep(2000);
		//Check the Category sort field page opened or not
		if(driver.findElement(By.id("myModalLabel")).getText().equalsIgnoreCase("Category Sort"))
		{
			test.log(LogStatus.PASS, "Category Sort page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Category Sort page loaded Failed");
		}
		Thread.sleep(2000);
		//Click the Custom Sort button
		driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/div/div[2]/a[1]")).click();
		Thread.sleep(3000);
		
		// Element which needs to drag.
		WebElement From = driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/form/div/div/div[1]/div/div[3]/div/ol/li[3]/div"));
		// Element on which need to drop.
		WebElement To = driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/form/div/div/div[1]/div/div[3]/div/ol/li[1]/div"));
		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Dragged and dropped.
		act.dragAndDrop(From, To).build().perform();
     
		//Click the Save button
		driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
		
		Thread.sleep(4000);
		String sys = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText();
		System.out.println(sys);
		//Check the required items sorted or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Items sorted successfully"))
		{
			test.log(LogStatus.PASS, "Category sorted by Custom Sort order Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Category sorted by Custom Sort order Failed");
		}
		Thread.sleep(10000);
		
		actions.moveToElement(showCategory);				
		actions.click().build().perform();
		Thread.sleep(5000);
		
		//Click the settings icon of Category
		driver.findElement(By.xpath("//div[@class='pull-left']/div/img")).click();
		Thread.sleep(2000);		   
		//Check the Category sort field page opened or not
		if(driver.findElement(By.id("myModalLabel")).getText().equalsIgnoreCase("Category Sort"))
		{
			test.log(LogStatus.PASS, "Category Sort page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Category Sort page loaded Failed");
		}
		Thread.sleep(3000);
		//Click the Sort Z to A button
		driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/div/div[2]/a[2]")).click();
		Thread.sleep(3000);
		
		//Click the Save button
		driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
		Thread.sleep(3000);
		
		//Check the required items sorted or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Items sorted successfully"))
		{
			test.log(LogStatus.PASS, "Category sorted by Ascending order Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Category sorted by Ascending order Failed");
		}
		Thread.sleep(10000);
		
	}
	
	@Test(enabled=false,priority=5)
	public void Sort_Menu_Config_Subctegory_Sorting(WebDriver driver) throws Exception
	{
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		WebElement showCategory = driver.findElement(By.xpath("//div[@class='box_go']/div[1]"));
		actions.moveToElement(showCategory);				
		actions.click().build().perform();
		Thread.sleep(7000);
			//Click the settings icon of Category
		driver.findElement(By.xpath("//div[@class='pull-left']/div/img")).click();
		
		Thread.sleep(3000);
		//Get the number of Categories
		List<WebElement> catcount = driver.findElements(By.xpath("//form[@name='categorySort']/div/div/div[1]/div/div[1]/div/div[1]"));
		catcount.size();
		
		//Click Close button
		driver.findElement(By.xpath("//a[.='Close']")).click();
		Thread.sleep(1000);
			
		for(int i = 1; i <= catcount.size(); i++)
		{
			//Click the Arrow mark in the Category
			driver.findElement(By.xpath("//div[@id='navigation']/span/img")).click();
			
			//Click the Category
			driver.findElement(By.xpath("//div[@id='navigation']/ul/li["+i+"]/div")).click();
			
			Thread.sleep(2000);
			try
			{
				if(driver.findElements(By.xpath("//ul[@ng-model='listSubCat']/li")).size() > 2)
				{
					//Click the sub category field
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[5]/div/div[1]/div/div[1]/div/ul")).click();
					Thread.sleep(2000);
					//Click the Settings Icon of Sub Category Result
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[5]/div/div[1]/div/div[2]/div/img")).click();
					Thread.sleep(2000);
					
					//Check the Sub Category sort field page opened or not
					if(driver.findElement(By.id("myModalLabel")).getText().equalsIgnoreCase("Sub Category Sort"))
					{
						test.log(LogStatus.PASS, "Sub Category Sort page loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Sub Category Sort page loaded Failed");
					}
					Thread.sleep(2000);
					//Click the Sort A to Z button
					driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/div/div[2]/a[3]")).click();
										
					Thread.sleep(3000);
					//Click the Save button
					driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
					
					Thread.sleep(3000);
					//Check the required items sorted or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Items sorted successfully"))
					{
						test.log(LogStatus.PASS, "SubCategory sorted by Decending order Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "SubCategory sorted by Decending order Failed");
					}
					Thread.sleep(10000);
					
					//Click the sub category field
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[5]/div/div[1]/div/div[1]/div/ul")).click();
					Thread.sleep(5000);
					//Click the Settings Icon of Sub Category Result
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[5]/div/div[1]/div/div[2]/div/img")).click();
					
					Thread.sleep(2000);
					//Check the Sub Category sort field page opened or not
					if(driver.findElement(By.id("myModalLabel")).getText().equalsIgnoreCase("Sub Category Sort"))
					{
						test.log(LogStatus.PASS, "Sub Category Sort page loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Sub Category Sort page loaded Failed");
					}
					Thread.sleep(2000);
					//Click the Custom Sort button
					driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/div/div[2]/a[1]")).click();
					Thread.sleep(3000);
					
					// Element which needs to drag.
					WebElement From = driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/form/div/div/div[1]/div/div[3]/div/ol/li[3]/div"));
					// Element on which need to drop.
					WebElement To = driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/form/div/div/div[1]/div/div[3]/div/ol/li[1]/div"));
					// Using Action class for drag and drop.
					Actions act = new Actions(driver);
					// Dragged and dropped.
					act.dragAndDrop(From, To).build().perform();
					
					//Click the Save button
					driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
					
					Thread.sleep(3000);
					//Check the required items sorted or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Items sorted successfully"))
					{
						test.log(LogStatus.PASS, "SubCategory sorted by Custom Sort order Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "SubCategory sorted by Custom Sort order Failed");
					}
					Thread.sleep(10000);
					
					//Click the sub category field
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[5]/div/div[1]/div/div[1]/div/ul")).click();
					Thread.sleep(2000);
					//Click the Settings Icon of Sub Category Result
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[5]/div/div[1]/div/div[2]/div/img")).click();
					Thread.sleep(2000);
					
					//Check the Sub Category sort field page opened or not
					if(driver.findElement(By.id("myModalLabel")).getText().equalsIgnoreCase("Sub Category Sort"))
					{
						test.log(LogStatus.PASS, "Sub Category Sort page loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Sub Category Sort page loaded Failed");
					}
					Thread.sleep(2000);
					//Click the Sort A to Z button
					driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/div/div[2]/a[2]")).click();
										
					Thread.sleep(3000);
					//Click the Save button
					driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
					
					Thread.sleep(3000);
					//Check the required items sorted or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Items sorted successfully"))
					{
						test.log(LogStatus.PASS, "SubCategory sorted by Ascending order Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "SubCategory sorted by Ascending order Failed");
					}
					Thread.sleep(10000);
				}
				break;
			}
			catch(Exception e)
			{
				
			}
		}	
	}
	
	@Test(enabled=false,priority=4)
	public void Sort_Menu_Config_MenuItem_Sorting(WebDriver driver) throws Exception
	{
	
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		WebElement showCategory = driver.findElement(By.xpath("//div[@class='box_go']/div[1]"));
		actions.moveToElement(showCategory);				
		actions.click().build().perform();
		Thread.sleep(7000);
			//Click the settings icon of Category
		driver.findElement(By.xpath("//div[@class='pull-left']/div/img")).click();
		
		Thread.sleep(3000);
		//Get the number of Categories
		List<WebElement> catcount = driver.findElements(By.xpath("//form[@name='categorySort']/div/div/div[1]/div/div[1]/div/div[1]"));
		catcount.size();
		
		//Click Close button
		driver.findElement(By.xpath("//a[.='Close']")).click();
		Thread.sleep(1000);
				
		for(int i = 1; i <= catcount.size(); i++)
		{
			//Click the Arrow mark in the Category
			driver.findElement(By.xpath("//div[@id='navigation']/span/img")).click();
			
			//Click the Category
			driver.findElement(By.xpath("//div[@id='navigation']/ul/li["+i+"]/div")).click();
			Thread.sleep(5000);
			try
			{
				if(driver.findElement(By.xpath("//div[@class='text-center ng-binding' and contains(.,'Menu Items of')]")).isDisplayed())
				{
					//Click the menu item field
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[5]/div/div[2]/div[3]/div/div/div[1]/ul")).click();
					Thread.sleep(2000);
					//Click the Settings Icon of menu item field
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[5]/div/div[2]/div[3]/div/div/div[2]/div/img")).click();
					Thread.sleep(2000);        
					
					//Check the Sub Category sort field page opened or not
					if(driver.findElement(By.id("myModalLabel")).getText().equalsIgnoreCase("Menu Item Sort"))
					{
						test.log(LogStatus.PASS, "Menu Item Sort page loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Menu Item Sort page loaded Failed");
					}
				
					Thread.sleep(2000);
					//Click the Sort Z to A button
					driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/div/div[2]/div/a[3]")).click();
					
					Thread.sleep(3000);
					//Click the Save button
					driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
					
					Thread.sleep(3000);
					//Check the required items sorted or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Items sorted successfully"))
					{
						test.log(LogStatus.PASS, "Items sorted by Decending order Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Items sorted by Decending order Failed");
					}
					Thread.sleep(10000);

					//Click the Menu item field
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[5]/div/div[2]/div[3]/div/div/div[1]/ul")).click();
					Thread.sleep(2000);
					//Click the Settings Icon of Menu Item Result
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[5]/div/div[2]/div[3]/div/div/div[2]/div/img")).click();
					Thread.sleep(2000);        
					
					//Check the Menu Item sort field page opened or not
					if(driver.findElement(By.id("myModalLabel")).getText().equalsIgnoreCase("Menu Item Sort"))
					{
						test.log(LogStatus.PASS, "Menu Item Sort page loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Menu Item Sort page loaded Failed");
					}
					Thread.sleep(2000);
					//Click the Sort Z to A button
					driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/div/div[2]/div/a[1]")).click();
					Thread.sleep(3000);
					
					// Element which needs to drag.
					WebElement From = driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/form/div/div/div[1]/div/div[3]/div/ol/li[2]/div"));
					// Element on which need to drop.
					WebElement To = driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/form/div/div/div[1]/div/div[3]/div/ol/li[1]/div"));
					// Using Action class for drag and drop.
					Actions act = new Actions(driver);
					// Dragged and dropped.
					act.dragAndDrop(From, To).build().perform();
					
					//Click the Save button
					driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
					
					Thread.sleep(3000);
					//Check the required items sorted or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Items sorted successfully"))
					{
						test.log(LogStatus.PASS, "Items sorted by Custom Sort order Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Items sorted by Custom Sort order Failed");
					}
					Thread.sleep(3000);
					
					//Click the menu item field
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[5]/div/div[2]/div[3]/div/div/div[1]/ul")).click();
					Thread.sleep(2000);
					//Click the Settings Icon of menu item field
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[5]/div/div[2]/div[3]/div/div/div[2]/div/img")).click();
					Thread.sleep(2000);        
					
					//Check the Sub Category sort field page opened or not
					if(driver.findElement(By.id("myModalLabel")).getText().equalsIgnoreCase("Menu Item Sort"))
					{
						test.log(LogStatus.PASS, "Menu Item Sort page loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Menu Item Sort page loaded Failed");
					}
				
					Thread.sleep(2000);
					//Click the Sort A to Z button
					driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/div/div[2]/div/a[2]")).click();
					
					Thread.sleep(3000);
					//Click the Save button
					driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
					
					Thread.sleep(3000);
					//Check the required items sorted or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Items sorted successfully"))
					{
						test.log(LogStatus.PASS, "Items sorted by Ascending order Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Items sorted by Ascending order Failed");
					}
					Thread.sleep(10000);
				}
				break;
			}
			catch(Exception e)
			{
				
			}
		}

		Thread.sleep(10000);

	}
	
	@Test(enabled=false,priority=6)
	public void Sort_Menu_Config_closeButton(WebDriver driver) throws Exception
	{
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		//Create the webelement for the Category field
		WebElement showCategory = driver.findElement(By.xpath("//div[@class='box_go']/div[1]"));
		actions.moveToElement(showCategory);				
		actions.click().build().perform();
		Thread.sleep(2000);
		//Click the settings icon of Category
		driver.findElement(By.xpath("//div[@class='pull-left']/div/img")).click();
		Thread.sleep(2000);		   
		//Check the Category sort field page opened or not
		if(driver.findElement(By.id("myModalLabel")).getText().equalsIgnoreCase("Category Sort"))
		{
			test.log(LogStatus.PASS, "Category Sort page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Category Sort page loaded Failed");
		}

		//Click the Save button
		driver.findElement(By.xpath("//a[.='Close']")).click();
		Thread.sleep(2000);
		//Check the required items close button working or not
		if(driver.findElement(By.xpath("//a[.='Sort']")).getText().equalsIgnoreCase("Sort"))
		{
			test.log(LogStatus.PASS, "Items sorting canceled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Items sorting canceled Failed");
		}
		Thread.sleep(3000);
		
		
	}

}
