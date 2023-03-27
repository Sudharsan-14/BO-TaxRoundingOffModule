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

	public class Setting_Application_settings {


			public WebDriver driver;
			
			ExtentReports rep = ExtentManager.getInstance();
			ExtentTest test = rep.startTest("Settings_Application_settings");
			
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
					Application_Settings_Method_open(driver);
					Application_Settings_Method_edit(driver);
					Thread.sleep(1500);
				}

				@Test(priority=28,enabled=false)
				public void Application_Settings_Method_open(WebDriver driver) throws Exception 
				{
					Thread.sleep(5000);
					// TODO Auto-generated method stub
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					//Enter the URL
					driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"newStoreSettings");
					
					WebElement html = driver.findElement(By.tagName(excel.getData(5, 174, 5)));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				
					try
					{
					Thread.sleep(5000);
					//Check weather the Application Settings page is loaded or not
					if(driver.findElement(By.xpath(excel.getData(5, 401, 1))).getText().equalsIgnoreCase("Application Settings"))
					{
						test.log(LogStatus.PASS, "Application Settings page loaded successfully");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
					else
					{
						test.log(LogStatus.FAIL, "Application Settings page loaded fail");
					
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

				
			@Test(priority=29,enabled=false)
			public void Application_Settings_Method_edit(WebDriver driver) throws Exception
				{
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
					Thread.sleep(5000);
					//Prompt Tip Screen before Credit Card Screen for enable or disable 
					driver.findElement(By.name(excel.getData(5, 402, 3))).click();
					Thread.sleep(2000);
					//Enable or Disable the	Enable Intranet sync
					if(driver.findElement(By.xpath(excel.getData(5, 403, 1))).isSelected())
					{
						driver.findElement(By.xpath(excel.getData(5, 403, 1))).click();
						//driver.findElement(By.xpath(excel.getData(5, 19, 1))).click();
					}
					else {
						driver.findElement(By.xpath(excel.getData(5, 403, 1))).click();
						driver.findElement(By.xpath(excel.getData(5, 19, 1))).click();
					}
						
					//Enable or Disable the	Enable Pre Auth
				    driver.findElement(By.xpath(excel.getData(5, 404, 1))).click();
					//Enable Extended Pre Auth
					if(driver.findElement(By.xpath(excel.getData(5, 404, 1))).isSelected())
					{
					driver.findElement(By.xpath(excel.getData(5, 405, 1))).click();
					Thread.sleep(2000);
					// enter the default PreAuth Amount
					driver.findElement(By.name(excel.getData(5, 406, 3))).clear();
					driver.findElement(By.name(excel.getData(5, 406, 3))).sendKeys("510");
									}
					else {
						
						driver.findElement(By.xpath(excel.getData(5, 404, 1))).click();
						// enter the default PreAuth Amount
						driver.findElement(By.xpath(excel.getData(5, 405, 1))).click();
						driver.findElement(By.name(excel.getData(5, 406, 3))).clear();
						driver.findElement(By.name(excel.getData(5, 406, 3))).sendKeys("120");
		
					}	
					Thread.sleep(1000);
				
					
				//Employee Tip Out Deducted from Cash Expected on Reports
					driver.findElement(By.xpath(excel.getData(5, 407, 1))).click();
				//Enable Gratuity Line in Credit Card Screen
					driver.findElement(By.name(excel.getData(5, 408, 3))).click();
					// Enable or Disable Customer Nationality 
					driver.findElement(By.xpath(excel.getData(5, 409, 1))).click();
				//Show Popup for Tax Exempt Reference Id
					driver.findElement(By.xpath(excel.getData(5, 410, 1))).click();
				//Check Number will Not Reset Daily
					driver.findElement(By.xpath(excel.getData(5, 411, 1))).click();
					
				//Auto log off
					//driver.findElement(By.xpath(excel.getData(5, 412, 1))).click();
					//Auto log off time
					driver.findElement(By.xpath(excel.getData(5, 412, 1))).sendKeys("0");

							
					try
					{
						//Taking no of close button in split custom field
						List<WebElement> s = driver.findElements(By.xpath(excel.getData(5, 413, 1)));
						for(int i=1; i<=s.size(); i++)
						{
							//close all the given data
							driver.findElement(By.xpath("//p[contains(.,'Split Custom Button Action')]/../../div[2]/div/ul/li["+i+"]/a")).click();
						}
						
						//Input field for split custom button
						
						driver.findElement(By.xpath(excel.getData(5, 414, 1))).sendKeys("Split by Check");
						driver.findElement(By.xpath(excel.getData(5, 414, 1))).sendKeys(Keys.ENTER);
						
						driver.findElement(By.xpath(excel.getData(5, 414, 1))).sendKeys("Split by Seat");
						driver.findElement(By.xpath(excel.getData(5, 414, 1))).sendKeys(Keys.ENTER);
					}
					catch(Exception f)
					{
						driver.findElement(By.xpath(excel.getData(5, 414, 1))).sendKeys("Split by Check");
						driver.findElement(By.xpath(excel.getData(5, 414, 1))).sendKeys(Keys.ENTER);
						
						driver.findElement(By.xpath(excel.getData(5, 414, 1))).sendKeys("Split by Seat");
						driver.findElement(By.xpath(excel.getData(5, 414, 1))).sendKeys(Keys.ENTER);
					}
					
					//Auto generate house account number
					driver.findElement(By.xpath(excel.getData(5, 415, 1))).click();
					//Auto print Close day report
					driver.findElement(By.xpath(excel.getData(5, 416, 1))).click();
				
	                //Batch submit device
					//driver.findElement(By.xpath(excel.getData(5, 417, 1))).click();
					driver.findElement(By.xpath(excel.getData(5, 417, 1))).sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(2000);
					// Preferred network
					driver.findElement(By.name(excel.getData(5, 418, 3))).clear();
					driver.findElement(By.name(excel.getData(5, 418, 3))).sendKeys("Linga Network");
					Thread.sleep(3000);
					//default Batch Payment
					driver.findElement(By.xpath(excel.getData(5, 419, 1))).click();
					driver.findElement(By.xpath(excel.getData(5, 420, 1))).sendKeys(Keys.ARROW_DOWN);
					driver.findElement(By.xpath(excel.getData(5, 420, 1))).sendKeys(Keys.ARROW_DOWN);
					driver.findElement(By.xpath(excel.getData(5, 420, 1))).sendKeys(Keys.ENTER);
					Thread.sleep(3000);
					//Owner Of The Check
					driver.findElement(By.xpath(excel.getData(5, 421, 1))).click();
					driver.findElement(By.xpath(excel.getData(5, 422, 1))).sendKeys(Keys.ARROW_DOWN);
					driver.findElement(By.xpath(excel.getData(5, 422, 1))).sendKeys(Keys.ARROW_DOWN);
					driver.findElement(By.xpath(excel.getData(5, 422, 1))).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
	                //Time Expires field
			//		driver.findElement(By.name(excel.getData(5, 423, 3))).clear();
			//		driver.findElement(By.name(excel.getData(5, 423, 3))).sendKeys("69");
					
					//	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		
					//Tip setting for percentage
				

					if(driver.findElement(By.xpath(excel.getData(5, 424, 1))).isSelected())

							{
	driver.findElement(By.xpath(excel.getData(5, 425, 1))).click();					
	}
					else {
						
						driver.findElement(By.xpath(excel.getData(5, 424, 1))).click();

						
					}
				//	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);

					driver.findElement(By.xpath(excel.getData(5, 426, 1))).clear();
					driver.findElement(By.xpath(excel.getData(5, 426, 1))).sendKeys("1");
					driver.findElement(By.xpath(excel.getData(5, 427, 1))).clear();
					driver.findElement(By.xpath(excel.getData(5, 427, 1))).sendKeys("10");
					driver.findElement(By.xpath(excel.getData(5, 428, 1))).clear();
					driver.findElement(By.xpath(excel.getData(5, 428, 1))).sendKeys("20");
					driver.findElement(By.xpath(excel.getData(5, 429, 1))).clear();
					driver.findElement(By.xpath(excel.getData(5, 429, 1))).sendKeys("30");
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.END);
					Thread.sleep(3000);
					
					try
					{
					WebDriverWait wait=new WebDriverWait(driver,60);
					
					wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(excel.getData(5, 430, 1))))).click();
					}
					catch(Exception p) {}
		 			//Floating Tab
					driver.findElement(By.name(excel.getData(5, 431, 3))).clear();
					driver.findElement(By.name(excel.getData(5, 431, 3))).sendKeys("BarNew");
					Thread.sleep(1000);
					//Serving Size in Front of Menu in KOT
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.END);
				//	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(2000);
					//driver.findElement(By.xpath(excel.getData(5, 432, 1))).click();
					//Pos Custom Field 1
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[1]/div[2]/div/a")).click();
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[1]/div[2]/div/div/div/input")).sendKeys("Void");
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[1]/div[2]/div/div/div/input")).sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					//pos Custom field 2
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[2]/div[2]/div/a")).click();
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[2]/div[2]/div/div/div/input")).sendKeys("Search");
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[2]/div[2]/div/div/div/input")).sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					//pos Custom field 3
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[3]/div[2]/div/a")).click();
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[3]/div[2]/div/div/div/input")).sendKeys("Order");
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[3]/div[2]/div/div/div/input")).sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					//pos Custom field 4
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[4]/div[2]/div/a")).click();
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[4]/div[2]/div/div/div/input")).sendKeys("Cash");
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[4]/div[2]/div/div/div/input")).sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					//pos Custom field 5
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[5]/div[2]/div/a")).click();
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[5]/div[2]/div/div/div/input")).sendKeys("Modify");
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[5]/div[2]/div/div/div/input")).sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					//pos Custom field 6
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[6]/div[2]/div/a")).click();
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[6]/div[2]/div/div/div/input")).sendKeys("Gift Card");
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div[11]/div/div[2]/div/div[6]/div[2]/div/div/div/input")).sendKeys(Keys.ENTER);
						
					Thread.sleep(2000);
					//Check whether the Memberships Auto Renewal Enables or not
					if(driver.findElement(By.xpath("//input[@ng-model='storeSettings.membershipAutoRenewal']")).isSelected())
					{
						
					}
					else
					{
						driver.findElement(By.xpath("//input[@ng-model='storeSettings.membershipAutoRenewal']")).click();
					}
					
					//Click the update button
					driver.findElement(By.xpath(excel.getData(5, 445, 1))).click();
					Thread.sleep(3000);
					
					//Check weather the notification setting is updated or not  Notification Settings updated successfully
					if(driver.findElement(By.cssSelector(excel.getData(5, 6, 4))).getText().equalsIgnoreCase("Application Settings updated successfully"))
					{
						test.log(LogStatus.PASS, "Application Settings updated successfully");
					}
					
					else{
						test.log(LogStatus.FAIL, "Application Settings updated fail");
					}
				Thread.sleep(5000);	
				}

	}




