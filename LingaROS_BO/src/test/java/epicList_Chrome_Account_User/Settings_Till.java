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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Settings_Till {

		public WebDriver driver;
		
		ExtentReports rep = ExtentManager.getInstance();
		ExtentTest test = rep.startTest("Settings_Till_settings");
		
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
				SendMail.snedMailWithAttachment();    

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
				Thread.sleep(3000);*/
				//Close the Browser_Account_Level_User
				
	
		
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
				Till_Settings_Method_open(driver);
				Till_Settings_Method_edit(driver);
				Till_Settings_Method_Denomination(driver);
				Till_Settings_Pagination(driver);
				Till_Settings_Select_Search(driver);
				Till_Settings_BankDeposit_Add(driver);
				Thread.sleep(1500);
			}

			@Test(priority=28,enabled=false)
			public void Till_Settings_Method_open(WebDriver driver) throws Exception 
			{
				// TODO Auto-generated method stub
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//	Thread.sleep(10000);
				//Enter the URL
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"newTillSettings");
				
				
				WebElement html = driver.findElement(By.tagName(excel.getData(5, 174, 5)));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			
				Thread.sleep(5000);
				try
				{
				//Check weather the Application Settings page is loaded or not
				if(driver.findElement(By.xpath(excel.getData(5, 492, 1))).getText().equalsIgnoreCase("Till Settings"))
				{
					test.log(LogStatus.PASS, "Till Settings page loaded successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Till Settings page loaded fail");
				
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
			
		@Test(priority=29,enabled=false)
		public void Till_Settings_Method_edit(WebDriver driver) throws Exception
			{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
				driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
				Thread.sleep(5000);
				//Automatic Global Till
				if(driver.findElement(By.xpath(excel.getData(5, 493, 1))).isSelected())
				{
				driver.findElement(By.xpath(excel.getData(5, 493, 1))).click();
				}
				else {
					
					driver.findElement(By.xpath(excel.getData(5, 493, 1))).click();
					driver.findElement(By.xpath(excel.getData(5, 494, 1))).clear();
					driver.findElement(By.xpath(excel.getData(5, 494, 1))).sendKeys("15");
				}
				Thread.sleep(2000);
			
				driver.findElement(By.xpath(excel.getData(5, 495, 1))).click();
				driver.findElement(By.xpath(excel.getData(5, 496, 1))).click();
				
			}
		public void Till_Settings_Method_Denomination(WebDriver driver) throws Exception
		{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		Thread.sleep(2000);
		//Click on add button for denomination
		driver.findElement(By.xpath(excel.getData(5, 497, 1))).click();
		// Enter Display Name
		driver.findElement(By.xpath(excel.getData(5, 498, 1))).sendKeys(Utility.getProperty("Till_Denomination_Display_Name"));
		Thread.sleep(1000);
		driver.findElement(By.xpath(excel.getData(5, 499, 1))).sendKeys("19");
		//Click Add after entering the data
		driver.findElement(By.xpath(excel.getData(5, 500, 1))).click();
		
		//Thread.sleep(1000);
		//driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,50);
		
		if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Denomination Saved Successfully"))
		{
			test.log(LogStatus.PASS, "Denomination Added Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Denomination Failed");
		}
		Thread.sleep(1000);
		
		}
		
		public void Till_Settings_Pagination(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));				
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(5, 501, 1))).clear();
			Thread.sleep(1000);
			
			//Click the Pagination option as 10
			driver.findElement(By.xpath(excel.getData(5, 502, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Product and Item(Menu Item)");
			//Create the web element for menu item Table
			List<WebElement> results = driver.findElements(By.xpath(excel.getData(5, 503, 1)));
			for (WebElement result : results){						
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(5, 51, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for Product and Item(Menu Item)");
			}
			Thread.sleep(8000);
			
			//Click the Pagination option as 15
			driver.findElement(By.xpath(excel.getData(5, 504, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Product and Item(Menu Item)");
			//Create the web element for menu item Table
			List<WebElement> results1 = driver.findElements(By.xpath(excel.getData(5, 503, 1)));
			for (WebElement result : results1){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(5, 51, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for Product and Item(Menu Item)");
			}
			Thread.sleep(8000);
			
			//Click the Pagination option as 20
			driver.findElement(By.xpath(excel.getData(5, 505, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for Product and Item(Menu Item)");
			//Create the web element for menu item Table
			List<WebElement> results2 = driver.findElements(By.xpath(excel.getData(5, 503, 1)));
			for (WebElement result : results2){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(5, 51, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for Product and Item(Menu Item)");
			}
			Thread.sleep(8000);
			
			//Click the Pagination option as 5
			driver.findElement(By.xpath(excel.getData(5, 506, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Product and Item(Menu Item)");
			//Create the web element for menu item Table
			List<WebElement> results3 = driver.findElements(By.xpath(excel.getData(5, 503, 1)));
			for (WebElement result : results3){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(5, 51, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for Product and Item(Menu Item)");
			}
			 Thread.sleep(3000);
		}
		public void	Till_Settings_Select_Search(WebDriver driver) throws Exception
	{
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));	
	/*		Select s = new Select(driver.findElement(By.xpath(excel.getData(5, 507, 1))));
			s.selectByVisibleText("Cashnew");

			
			Select s2 = new Select(driver.findElement(By.xpath(excel.getData(5, 507, 1))));
			s2.selectByVisibleText("Cash5");
			
			Select s3 = new Select(driver.findElement(By.xpath(excel.getData(5, 507, 1))));
			s3.selectByVisibleText("Cash2");
		*/	
			
			Thread.sleep(5000);
			
			Select s1 = new Select(driver.findElement(By.xpath(excel.getData(5, 507, 1))));
			s1.selectByVisibleText("Default");
			//Search for newly added denomination
			driver.findElement(By.xpath(excel.getData(5, 7, 1))).sendKeys(Utility.getProperty("Till_Denomination_Display_Name"));
			//driver.findElement(By.xpath(excel.getData(5, 508, 1))).
			
			//delete the existing one
			driver.findElement(By.xpath(excel.getData(5, 149, 1))).click();
			Thread.sleep(1000);
			//confirmation popup
			driver.findElement(By.xpath(excel.getData(5, 474, 1))).click();
			Thread.sleep(1000);
			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
			
			WebElement el=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,30);
			
			if(wait.until(ExpectedConditions.visibilityOf(el)).getText().equalsIgnoreCase("Denomination Deleted Successfully"))
			{
				test.log(LogStatus.PASS, "Denomination Deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Denomination Deletion Failed");
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath(excel.getData(5, 510, 1))).click();
			Thread.sleep(1000);
			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
			Thread.sleep(2000);
			if(driver.findElement(By.cssSelector(excel.getData(5, 49, 4))).getText().equalsIgnoreCase("Till Settings Updated Successfully"))
			{
				test.log(LogStatus.PASS, "Till Settings Updated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Till Settings Updation Failed");
			}
			
			driver.findElement(By.xpath(excel.getData(5, 509, 1))).click();
			Thread.sleep(1000);
			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
			if(driver.findElement(By.xpath(excel.getData(5, 492, 1))).getText().equalsIgnoreCase("Till Settings"))
			{
				test.log(LogStatus.PASS, "Denomination list Refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Denomination list Refreshed UnSuccessful");
			}
			Thread.sleep(5000);
			
		}
		public void Till_Settings_BankDeposit_Add(WebDriver driver2) throws Exception {
			
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));	
		Thread.sleep(10000);
		driver.findElement(By.xpath(excel.getData(5, 511, 1))).click();
			
			Thread.sleep(1000);
			
			driver.findElement(By.xpath(excel.getData(5, 512, 1))).sendKeys("120");
			Thread.sleep(1000);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.xpath(excel.getData(5, 513, 1))).click();
			driver.findElement(By.xpath(excel.getData(5, 514, 1))).click();
			driver.findElement(By.xpath(excel.getData(5, 515, 1))).click();
			driver.findElement(By.xpath(excel.getData(5, 516, 1))).click();
			Thread.sleep(1000);
			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
			Thread.sleep(3000);
			if(driver.findElement(By.cssSelector(excel.getData(5, 49, 4))).getText().equalsIgnoreCase("Cash flow updated successfully"))
			{
				test.log(LogStatus.PASS, "Cash Flow updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Cash Flow updation failed");
			}
		}
	}
		
			