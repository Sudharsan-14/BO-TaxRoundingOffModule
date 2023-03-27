package epicList_Chrome_Account_User;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

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
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.UPOS_login(driver, test);
			}
			else 			
			{
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.Linga_login(driver, test);
			}	
		}
			
		@Test(priority=500)
		public void logout() throws Exception
		{
			
		
			Browser_Account_Level_User a = new Browser_Account_Level_User();
			a.Logout(driver, test);
		}
			/*if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
			{
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));	
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath(excel.getData(5, 54, 1)));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath(excel.getData(5, 54, 1))).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath(excel.getData(5, 155, 1))).getText().equalsIgnoreCase("User Login"))
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
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath(excel.getData(5, 54, 1)));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath(excel.getData(5, 54, 1))).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath(excel.getData(5, 156, 1))).getText().equalsIgnoreCase("Account Login"))
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
		}*/
	
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
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
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
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"sort");
		Thread.sleep(5000);
		try
		{
		//Check Up Charges page opened or not
		if(driver.findElement(By.xpath(excel.getData(5, 213, 1))).getText().equalsIgnoreCase("Sort"))
		{
			test.log(LogStatus.PASS, "Sort Menu Config page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Sort Menu Config page loaded Failed");
		
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
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=3)
	public void Sort_Menu_Config_Category_Sorting(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		//Create the webelement for the Category field
		WebElement showCategory = driver.findElement(By.xpath(excel.getData(5, 214, 1)));
		actions.moveToElement(showCategory);				
		actions.click().build().perform();
		Thread.sleep(5000);
		
		//Click the settings icon of Category
		driver.findElement(By.xpath(excel.getData(5, 215, 1))).click();
		Thread.sleep(2000);		   
		//Check the Category sort field page opened or not
		if(driver.findElement(By.id(excel.getData(5, 216, 2))).getText().equalsIgnoreCase("Category Sort"))
		{
			test.log(LogStatus.PASS, "Category Sort page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Category Sort page loaded Failed");
		}
		Thread.sleep(3000);
		//Click the Sort Z to A button
		driver.findElement(By.xpath(excel.getData(5, 217, 1))).click();
		Thread.sleep(3000);
		
		//Click the Save button
		driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
		Thread.sleep(3000);
		
		//Check the required items sorted or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Items sorted successfully"))
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
		driver.findElement(By.xpath(excel.getData(5, 215, 1))).click();
		Thread.sleep(3000);
		//Check the Category sort field page opened or not
		if(driver.findElement(By.id(excel.getData(5, 216, 2))).getText().equalsIgnoreCase("Category Sort"))
		{
			test.log(LogStatus.PASS, "Category Sort page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Category Sort page loaded Failed");
		}
		Thread.sleep(2000);
		//Click the Custom Sort button
		driver.findElement(By.xpath(excel.getData(5, 220, 1))).click();
		Thread.sleep(3000);
		
		// Element which needs to drag.
		WebElement From = driver.findElement(By.xpath(excel.getData(5, 221, 1)));
		// Element on which need to drop.
		WebElement To = driver.findElement(By.xpath(excel.getData(5, 222, 1)));
		// Using Action class for drag and drop.
		Actions act = new Actions(driver);
		// Dragged and dropped.
		act.dragAndDrop(From, To).build().perform();
     
		//Click the Save button
		driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
		
		//Thread.sleep(4000);
		//String sys = driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText();
		//System.out.println(sys);
		WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,30);
		//Check the required items sorted or not
		if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Items sorted successfully"))
		{
			test.log(LogStatus.PASS, "Category sorted by Custom Sort order Successfully");
		}
		else
		{
			test.log(LogStatus.INFO, "Category sorted by Custom Sort order Failed");
		}
		Thread.sleep(10000);
		
		actions.moveToElement(showCategory);				
		actions.click().build().perform();
		Thread.sleep(5000);
		
		//Click the settings icon of Category
		driver.findElement(By.xpath(excel.getData(5, 215, 1))).click();
		Thread.sleep(2000);		   
		//Check the Category sort field page opened or not
		if(driver.findElement(By.id(excel.getData(5, 216, 2))).getText().equalsIgnoreCase("Category Sort"))
		{
			test.log(LogStatus.PASS, "Category Sort page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Category Sort page loaded Failed");
		}
		Thread.sleep(3000);
		//Click the Sort Z to A button
		driver.findElement(By.xpath(excel.getData(5, 223, 1))).click();
		Thread.sleep(3000);
		
		//Click the Save button
		driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
		Thread.sleep(3000);
		
		//Check the required items sorted or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Items sorted successfully"))
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
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		WebElement showCategory = driver.findElement(By.xpath(excel.getData(5, 214, 1)));
		actions.moveToElement(showCategory);				
		actions.click().build().perform();
		Thread.sleep(7000);
			//Click the settings icon of Category
		driver.findElement(By.xpath(excel.getData(5, 215, 1))).click();
		
		Thread.sleep(3000);
		//Get the number of Categories
		List<WebElement> catcount = driver.findElements(By.xpath(excel.getData(5, 224, 1)));
		catcount.size();
		
		//Click Close button
		driver.findElement(By.xpath(excel.getData(5, 227, 1))).click();
		Thread.sleep(1000);
			
		for(int i = 1; i <= catcount.size(); i++)
		{
			Thread.sleep(2000);
			//Click the Arrow mark in the Category
			driver.findElement(By.xpath(excel.getData(5, 225, 1))).click();
			
			//Click the Category
			driver.findElement(By.xpath(excel.getData(5, 226, 1))).click();
			
			Thread.sleep(2000);
			try
			{
				if(driver.findElements(By.xpath(excel.getData(5, 236, 1))).size() > 2)
				{
					//Click the sub category field
					driver.findElement(By.xpath(excel.getData(5, 228, 1))).click();
					Thread.sleep(2000);
					//Click the Settings Icon of Sub Category Result
					driver.findElement(By.xpath(excel.getData(5, 229, 1))).click();
					Thread.sleep(2000);
					
					//Check the Sub Category sort field page opened or not
					if(driver.findElement(By.id(excel.getData(5, 216, 2))).getText().equalsIgnoreCase("Sub Category Sort"))
					{
						test.log(LogStatus.PASS, "Sub Category Sort page loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Sub Category Sort page loaded Failed");
					}
					Thread.sleep(2000);
					//Click the Sort A to Z button
					driver.findElement(By.xpath(excel.getData(5, 217, 1))).click();
										
					Thread.sleep(3000);
					//Click the Save button
					driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
					
					Thread.sleep(3000);
					//Check the required items sorted or not
					if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Items sorted successfully"))
					{
						test.log(LogStatus.PASS, "SubCategory sorted by Decending order Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "SubCategory sorted by Decending order Failed");
					}
					Thread.sleep(10000);
					
					//Click the sub category field
					driver.findElement(By.xpath(excel.getData(5, 228, 1))).click();
					Thread.sleep(5000);
					//Click the Settings Icon of Sub Category Result
					driver.findElement(By.xpath(excel.getData(5, 229, 1))).click();
					
					Thread.sleep(2000);
					//Check the Sub Category sort field page opened or not
					if(driver.findElement(By.id(excel.getData(5, 216, 2))).getText().equalsIgnoreCase("Sub Category Sort"))
					{
						test.log(LogStatus.PASS, "Sub Category Sort page loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Sub Category Sort page loaded Failed");
					}
					Thread.sleep(2000);
					//Click the Custom Sort button
					driver.findElement(By.xpath(excel.getData(5, 220, 1))).click();
					Thread.sleep(3000);
					
					// Element which needs to drag.
					WebElement From = driver.findElement(By.xpath(excel.getData(5, 221, 1)));
					// Element on which need to drop.
					WebElement To = driver.findElement(By.xpath(excel.getData(5, 222, 1)));
					// Using Action class for drag and drop.
					Actions act = new Actions(driver);
					// Dragged and dropped.
					act.dragAndDrop(From, To).build().perform();
					
					//Click the Save button
					driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
					
					Thread.sleep(3000);
					//Check the required items sorted or not
					if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Items sorted successfully"))
					{
						test.log(LogStatus.PASS, "SubCategory sorted by Custom Sort order Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "SubCategory sorted by Custom Sort order Failed");
					}
					Thread.sleep(10000);
					
					//Click the sub category field
					driver.findElement(By.xpath(excel.getData(5, 228, 1))).click();
					Thread.sleep(2000);
					//Click the Settings Icon of Sub Category Result
					driver.findElement(By.xpath(excel.getData(5, 229, 1))).click();
					Thread.sleep(2000);
					
					//Check the Sub Category sort field page opened or not
					if(driver.findElement(By.id(excel.getData(5, 216, 2))).getText().equalsIgnoreCase("Sub Category Sort"))
					{
						test.log(LogStatus.PASS, "Sub Category Sort page loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Sub Category Sort page loaded Failed");
					}
					Thread.sleep(2000);
					//Click the Sort A to Z button
					driver.findElement(By.xpath(excel.getData(5, 223, 1))).click();
										
					Thread.sleep(3000);
					//Click the Save button
					driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
					
					Thread.sleep(3000);
					//Check the required items sorted or not
					if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Items sorted successfully"))
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
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		WebElement showCategory = driver.findElement(By.xpath(excel.getData(5, 214, 1)));
		actions.moveToElement(showCategory);				
		actions.click().build().perform();
		Thread.sleep(7000);
			//Click the settings icon of Category
		driver.findElement(By.xpath(excel.getData(5, 215, 1))).click();
		
		Thread.sleep(3000);
		//Get the number of Categories
		List<WebElement> catcount = driver.findElements(By.xpath(excel.getData(5, 224, 1)));
		catcount.size();
		
		//Click Close button
		driver.findElement(By.xpath(excel.getData(5, 227, 1))).click();
		Thread.sleep(1000);
				
		for(int i = 1; i <= catcount.size(); i++)
		{
			//Click the Arrow mark in the Category
			driver.findElement(By.xpath(excel.getData(5, 225, 1))).click();
			
			//Click the Category
			driver.findElement(By.xpath(excel.getData(5, 226, 1))).click();
			Thread.sleep(5000);
			try
			{
				if(driver.findElement(By.xpath(excel.getData(5, 230, 1))).isDisplayed())
				{
					//Click the menu item field
					driver.findElement(By.xpath(excel.getData(5, 231, 1))).click();
					Thread.sleep(2000);
					//Click the Settings Icon of menu item field
					driver.findElement(By.xpath(excel.getData(5, 232, 1))).click();
					Thread.sleep(2000);        
					
					//Check the Sub Category sort field page opened or not
					if(driver.findElement(By.id(excel.getData(5, 216, 2))).getText().equalsIgnoreCase("Menu Item Sort"))
					{
						test.log(LogStatus.PASS, "Menu Item Sort page loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Menu Item Sort page loaded Failed");
					}
				
					Thread.sleep(2000);
					//Click the Sort Z to A button
					driver.findElement(By.xpath(excel.getData(5, 233, 1))).click();
					
					Thread.sleep(3000);
					//Click the Save button
					driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
					
					Thread.sleep(3000);
					//Check the required items sorted or not
					if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Items sorted successfully"))
					{
						test.log(LogStatus.PASS, "Items sorted by Decending order Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Items sorted by Decending order Failed");
					}
					Thread.sleep(10000);

					//Click the Menu item field
					driver.findElement(By.xpath(excel.getData(5, 231, 1))).click();
					Thread.sleep(2000);
					//Click the Settings Icon of Menu Item Result
					driver.findElement(By.xpath(excel.getData(5, 232, 1))).click();
					Thread.sleep(2000);        
					
					//Check the Menu Item sort field page opened or not
					if(driver.findElement(By.id(excel.getData(5, 216, 2))).getText().equalsIgnoreCase("Menu Item Sort"))
					{
						test.log(LogStatus.PASS, "Menu Item Sort page loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Menu Item Sort page loaded Failed");
					}
					Thread.sleep(2000);
					//Click the Sort Z to A button
					driver.findElement(By.xpath(excel.getData(5, 234, 1))).click();
					Thread.sleep(3000);
					
					// Element which needs to drag.
					WebElement From = driver.findElement(By.xpath(excel.getData(5, 235, 1)));
					// Element on which need to drop.
					WebElement To = driver.findElement(By.xpath(excel.getData(5, 222, 1)));
					// Using Action class for drag and drop.
					Actions act = new Actions(driver);
					// Dragged and dropped.
					act.dragAndDrop(From, To).build().perform();
					
					//Click the Save button
					driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
					
					Thread.sleep(3000);
					//Check the required items sorted or not
					if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Items sorted successfully"))
					{
						test.log(LogStatus.PASS, "Items sorted by Custom Sort order Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Items sorted by Custom Sort order Failed");
					}
					Thread.sleep(3000);
					
					//Click the menu item field
					driver.findElement(By.xpath(excel.getData(5, 231, 1))).click();
					Thread.sleep(2000);
					//Click the Settings Icon of menu item field
					driver.findElement(By.xpath(excel.getData(5, 232, 1))).click();
					Thread.sleep(2000);        
					
					//Check the Sub Category sort field page opened or not
					if(driver.findElement(By.id(excel.getData(5, 216, 2))).getText().equalsIgnoreCase("Menu Item Sort"))
					{
						test.log(LogStatus.PASS, "Menu Item Sort page loaded Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Menu Item Sort page loaded Failed");
					}
				
					Thread.sleep(2000);
					//Click the Sort A to Z button
					driver.findElement(By.xpath(excel.getData(5, 218, 1))).click();
					
					Thread.sleep(3000);
					//Click the Save button
					driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
					
					Thread.sleep(3000);
					//Check the required items sorted or not
					if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Items sorted successfully"))
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
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		//Create the webelement for the Category field
		WebElement showCategory = driver.findElement(By.xpath(excel.getData(5, 214, 1)));
		actions.moveToElement(showCategory);				
		actions.click().build().perform();
		Thread.sleep(2000);
		//Click the settings icon of Category
		driver.findElement(By.xpath(excel.getData(5, 215, 1))).click();
		Thread.sleep(2000);		   
		//Check the Category sort field page opened or not
		if(driver.findElement(By.id(excel.getData(5, 216, 2))).getText().equalsIgnoreCase("Category Sort"))
		{
			test.log(LogStatus.PASS, "Category Sort page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Category Sort page loaded Failed");
		}

		//Click the Save button
		driver.findElement(By.xpath(excel.getData(5, 227, 1))).click();
		Thread.sleep(2000);
		//Check the required items close button working or not
		if(driver.findElement(By.xpath(excel.getData(5, 213, 1))).getText().equalsIgnoreCase("Sort"))
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
