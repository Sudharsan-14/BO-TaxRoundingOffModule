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

public class Settings_Delivery {
	
	


			public WebDriver driver;
			
			ExtentReports rep = ExtentManager.getInstance();
			ExtentTest test = rep.startTest("Settings_Delivery_settings");
			
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
					Delivery_Settings_Method_open(driver);
					Delivery_Settings_Method_edit(driver);
					Thread.sleep(1500);
				}

				@Test(enabled=false,priority=28)
				public void Delivery_Settings_Method_open(WebDriver driver) throws Exception {
					// TODO Auto-generated method stub
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					//Enter the URL
					driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"newDeliveryZone");
					
				Thread.sleep(3000);
					
					WebElement html = driver.findElement(By.tagName(excel.getData(5, 174, 5)));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				try
				{
					//Check weather the Delivery Settings page is loaded or not
					if(driver.findElement(By.xpath(excel.getData(5, 519, 1))).getText().equalsIgnoreCase("Delivery Zone"))
					{
						test.log(LogStatus.PASS, "Delivery Zone page loaded successfully");
				
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
					else
					{
						test.log(LogStatus.FAIL, "Delivery Zone page loaded fail");
					
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
				public void Delivery_Settings_Method_edit(WebDriver driver) throws Exception {
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					Thread.sleep(1000);
					//Clear Delivery Charge Cost
					driver.findElement(By.xpath(excel.getData(5, 520, 1))).clear();
					//Enter Delivery Charge Cost
					driver.findElement(By.xpath(excel.getData(5, 520, 1))).sendKeys("120");
					//Clear print Future Order Before
					driver.findElement(By.xpath(excel.getData(5, 521, 1))).clear();
					//Enter print Future Order Before
					driver.findElement(By.xpath(excel.getData(5, 521, 1))).sendKeys("10");
					//Enable or disable the Assign order to departed driver
					driver.findElement(By.xpath(excel.getData(5, 522, 1))).click();
					//Sync Data to  web order
					driver.findElement(By.xpath(excel.getData(5, 523, 1))).click();
					//confirmation to delete
					driver.findElement(By.xpath(excel.getData(5, 474, 1))).click();
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
					Thread.sleep(1000);
					/*if(driver.findElement(By.cssSelector(excel.getData(5, 49, 4))).getText().equalsIgnoreCase("Store successfully synced"))
					{
						test.log(LogStatus.PASS, "Store Successfully Synced");
					}
					else
					{
						test.log(LogStatus.FAIL, "Store Synced unSuccessfull");
					}*/
						driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);

					
					driver.findElement(By.xpath(excel.getData(5, 524, 1))).click();
					
					WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
					WebDriverWait wait=new WebDriverWait(driver,150); 
					
				//	Thread.sleep(1000);
					if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Delivery Zone Settings updated Successfully"))
					{
						test.log(LogStatus.PASS, "Delivery Zone Settings updated Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Delivery Zone Settings update failed");
					}
					Thread.sleep(1000);
					//Click on Add Delivery zone
					driver.findElement(By.xpath(excel.getData(5, 525, 1))).click();
					Thread.sleep(1000);
					//enter Name
					driver.findElement(By.xpath(excel.getData(5, 526, 1))).sendKeys("New12");
					//enter delivery Charge 
					driver.findElement(By.xpath(excel.getData(5, 527, 1))).sendKeys("150");
					//Enter Delivery Time
					driver.findElement(By.xpath(excel.getData(5, 528, 1))).sendKeys("60");
					//Cancel Button
					driver.findElement(By.xpath(excel.getData(5, 529, 1))).click();
					Thread.sleep(1000);
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
					Thread.sleep(1000);
					//Sync to Online Order
					driver.findElement(By.xpath(excel.getData(5, 530, 1))).click();
					Thread.sleep(2000);
					//Confirmation message after updation
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
					
					WebElement ele1=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
					
					WebDriverWait wait1=new WebDriverWait(driver,90)
;
					if(wait1.until(ExpectedConditions.visibilityOf(ele1)).getText().equalsIgnoreCase("Delivery Zones Synced Successfully"))
					{
						test.log(LogStatus.PASS, "Delivery Zones synced Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Delivery Zone synced failed");
					}
				for(int i=0;i<=5;i++)
				{
					driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				}
				Thread.sleep(20000);
					//Edit the existing Delivery zone
					driver.findElement(By.xpath(excel.getData(5, 531, 1))).click();
					Thread.sleep(1000);
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
					Thread.sleep(1000);
					//Clear Existing Name
					driver.findElement(By.xpath(excel.getData(5, 526, 1))).clear();
					///enter Name
					driver.findElement(By.xpath(excel.getData(5, 526, 1))).sendKeys("Locat1");
					//Clear Existing delivery Charge 
					driver.findElement(By.xpath(excel.getData(5, 527, 1))).sendKeys(Keys.BACK_SPACE);
					Thread.sleep(1000);
					driver.findElement(By.xpath(excel.getData(5, 527, 1))).sendKeys(Keys.BACK_SPACE);
					Thread.sleep(1000);
					driver.findElement(By.xpath(excel.getData(5, 527, 1))).sendKeys(Keys.BACK_SPACE);
					Thread.sleep(1000);
					driver.findElement(By.xpath(excel.getData(5, 527, 1))).sendKeys(Keys.BACK_SPACE);
					//enter delivery Charge 
					driver.findElement(By.xpath(excel.getData(5, 527, 1))).sendKeys("1100");
                    //Clear Existing delivery Time
					driver.findElement(By.xpath(excel.getData(5, 528, 1))).clear();
					//Enter Delivery Time
					driver.findElement(By.xpath(excel.getData(5, 528, 1))).sendKeys("25");
					Thread.sleep(2000);
					//Update Button
					driver.findElement(By.xpath(excel.getData(5, 532, 1))).click();
					//Thread.sleep(1000);
					Thread.sleep(2000);
					//confirmation popup
					if(driver.findElement(By.xpath(excel.getData(5, 519, 1))).getText().equalsIgnoreCase("Delivery Zone"))
					{
						test.log(LogStatus.PASS, "Delivery Zone updated Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Delivery Zone updation failed");
					}
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.END);
					Thread.sleep(2000);
					
					//InActive one to Active
					driver.findElement(By.xpath(excel.getData(5, 534, 1))).click();

					
						
					
					//Click on satelite for satelite view
					driver.findElement(By.xpath(excel.getData(5, 535, 1))).click();
					
					//Click on map view

					driver.findElement(By.xpath(excel.getData(5, 536, 1))).click();
					//To maximize the maps page
					driver.findElement(By.xpath(excel.getData(5, 537, 1))).click();
					//To minimize the maps
					driver.findElement(By.xpath(excel.getData(5, 537, 1))).click();
					//To zoom the maps
					driver.findElement(By.xpath(excel.getData(5, 538, 1))).click();
					
				//To zoom out the maps
					driver.findElement(By.xpath(excel.getData(5, 539, 1))).click();
					//To drag the image to location
					driver.findElement(By.xpath(excel.getData(5, 539, 1))).click();

					if(driver.findElement(By.xpath(excel.getData(5, 536, 1))).getText().equalsIgnoreCase("Map"))
					{
						test.log(LogStatus.PASS, "Map checked Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Map checked failed");
					}
				}
				
			
				
				}

