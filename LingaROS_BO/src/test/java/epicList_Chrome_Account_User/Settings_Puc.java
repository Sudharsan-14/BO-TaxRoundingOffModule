package epicList_Chrome_Account_User;
	
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	
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

	public class Settings_Puc {
	
			public WebDriver driver;
			
			ExtentReports rep = ExtentManager.getInstance();
			ExtentTest test = rep.startTest("Settings_PUC_settings");
			
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
					PLU_Settings_Method_open(driver);
					PLU_Settings_Method_edit(driver);
					Thread.sleep(1500);
				}

				@Test(priority=28,enabled=false)
				public void PLU_Settings_Method_open(WebDriver driver) throws Exception {
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					Thread.sleep(5000);
					//Enter the URL
					driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"pucCabinet");
					
				Thread.sleep(3000);
					
					WebElement html = driver.findElement(By.tagName(excel.getData(5, 174, 5)));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				
					Thread.sleep(4000);
					try
					{
					//Check weather the PUC Settings page is loaded or not
					if(driver.findElement(By.xpath(excel.getData(5, 543, 1))).getText().equalsIgnoreCase("PUC Settings"))
					{
						test.log(LogStatus.PASS, "PUC Settings page loaded successfully");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
					else
					{
						test.log(LogStatus.FAIL, "PUC Settings page loaded fail");
					
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
			public void	PLU_Settings_Method_edit(WebDriver driver) throws Exception 
			  {
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				//To get Enable or disable PUC

				Thread.sleep(2000);
				if(driver.findElement(By.xpath(excel.getData(5, 544, 1))).isSelected())
				{
					//disable PUC

					Thread.sleep(2000);
					driver.findElement(By.xpath(excel.getData(5, 544, 1))).click();
					//update Button
					driver.findElement(By.xpath(excel.getData(5, 549, 1))).click();
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
				//	Thread.sleep(1000);
					WebDriverWait wait=new WebDriverWait(driver,30);
					WebElement ele=driver.findElement(By.xpath(excel.getData(5, 550, 1)));
					//popup after updating
					if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("PUC Cabinet Settings Updated Successfully"))
					{
						test.log(LogStatus.PASS, "PUC Cabinat Settings Updated Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "PUC Cabinat Settings Updated failed");
					}
					
				}
				else {
					Thread.sleep(2000);
					//enable the puc
					driver.findElement(By.xpath(excel.getData(5, 544, 1))).click();
					// Clear the existing valve in API KEY
					driver.findElement(By.xpath(excel.getData(5, 545, 1))).clear();
					// Enter valve into API KEY
					driver.findElement(By.xpath(excel.getData(5, 545, 1))).sendKeys("123");
				/*	//Clear the existing Password
					driver.findElement(By.xpath(excel.getData(5, 546, 1))).clear();
					//Enter valve into Password 
					driver.findElement(By.xpath(excel.getData(5, 546, 1))).sendKeys("Chand@125");*/
					//Clear the existing Cabinate Id
					driver.findElement(By.xpath("//input[@ng-model='store.pucSettings.CabinateID']")).clear();
					//Enter valve into Cabinate Id
					driver.findElement(By.xpath("//input[@ng-model='store.pucSettings.CabinateID']")).sendKeys("1254");
					//Clear the existing Cabinate Alias
					driver.findElement(By.xpath("//input[@ng-model='store.pucSettings.cabinetAlias']")).clear();
					//Enter valve into Cabinate alias
					driver.findElement(By.xpath("//input[@ng-model='store.pucSettings.cabinetAlias']")).sendKeys("1289");
					//Update Button
					driver.findElement(By.xpath(excel.getData(5, 549, 1))).click();
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
					Thread.sleep(1000);
					////popup after updation
					if(driver.findElement(By.xpath(excel.getData(5, 550, 1))).getText().equalsIgnoreCase("PUC Cabinet Settings Updated Successfully"))
					{
						test.log(LogStatus.PASS, "PUC Cabinat Settings Updated Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "PUC Cabinat Settings Updated failed");
					}
					
					
				}
				
				Thread.sleep(5000);
			}
			}

