package epicList_Chrome_Account_User;

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


public class AddEditDelete_OverShortage_Reason {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete_OverShortage");

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
		/*{
			try {
				if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
				{
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath(excel.getData(5, 54, 1)));
				//Scroll the page till the Reason option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
				//Wait for 30 seconds
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				// Click on  button
				driver.findElement(By.xpath(excel.getData(5, 54, 1))).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(3000);
				
				//Check whether user get logged out or not
				if(driver.findElement(By.xpath(excel.getData(5, 55, 1))).getText().equalsIgnoreCase("User Login"))
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
				// Click on  button
				driver.findElement(By.xpath(excel.getData(5, 54, 1))).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(3000);
				
				//Check whether user get logged out or not
				if(driver.findElement(By.xpath(excel.getData(5, 56, 1))).getText().equalsIgnoreCase("Account Login"))
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
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	*/
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
			Reasons_Overshortage_method_openOverShortageReasons(driver);
			Reasons_Overshortage_method_refreshOverShortageReasons(driver);
			Reasons_Overshortage_method_addOverShortageReasons(driver);
			Reasons_Overshortage_method_editOverShortageReasons(driver);
			Reasons_Overshortage_method_deleteOverShortageReasons(driver);
			Reasons_Overshortage_method_closeButton(driver);
			Reasons_Overshortage_method_verifyActive_InActiveButton(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=161)
		public void Reasons_Overshortage_method_openOverShortageReasons(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	/*		//Click the Products/Items option
			driver.findElement(By.xpath("//span[.='Products/Items']")).click();
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='Reasons']"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
	        //Click the Reasons Option		
			driver.findElement(By.xpath("//span[.='Reasons']")).click();*/
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+("reasons"));
			Thread.sleep(8000);
			
			
			
/*			//Check Tax exempt page opened or not
			if(driver.findElement(By.xpath("//a[.='Tax Exempt Reasons']")).getText().equalsIgnoreCase("Tax Exempt Reasons"))
			{
				test.log(LogStatus.PASS, "Tax Exempt Reasons page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Tax Exempt Reasons page loaded Failed");
			}
			
			Thread.sleep(5000);*/
			
			//Click the OverShortage Reasons Tap
			driver.findElement(By.xpath(excel.getData(5, 57, 1))).click();
			Thread.sleep(3000);
			try
			{
			//Check OverShortage Reasons page opened or not
			if(driver.findElement(By.xpath(excel.getData(5, 59, 1))).getText().equalsIgnoreCase("Over Shortages"))
			{
				test.log(LogStatus.PASS, "Over Shortage Reasons page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Over Shortage Reasons page loaded Failed");
				
				String scnShot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s1="data:image/png;base64,"+scnShot1;
				test.log(LogStatus.INFO,test.addScreenCapture(s1));
			}
			}
			catch(Exception e)
			{
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.FAIL,test.addScreenCapture(s));
			}
			
	
		}
		
		@Test(enabled=false,priority=162)
		public void Reasons_Overshortage_method_refreshOverShortageReasons(WebDriver driver) throws InterruptedException
		{
			
				ExcelDataConfig excel;
				try {
					excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			Thread.sleep(5000);
			
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(5, 58, 1))).click();
			Thread.sleep(5000);
			//Check OverShortage Reasons page opened or not
			if(driver.findElement(By.xpath(excel.getData(5, 59, 1))).getText().equalsIgnoreCase("Over Shortages"))
			{
				test.log(LogStatus.PASS, "Over Shortage Reasons page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Over Shortage Reasons page refreshed Failed");
			}
			Thread.sleep(5000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		@Test(enabled=false,priority=164)
		public void Reasons_Overshortage_method_addOverShortageReasons(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			for (int i = 0; i < 10; i++) {
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
			Thread.sleep(3000);
			try{
				if(driver.findElement(By.cssSelector(excel.getData(5, 69, 4))).isDisplayed())
				{
					//Click the Active/In Active Button
					driver.findElement(By.xpath(excel.getData(5, 72, 1))).click();
					Thread.sleep(3000);		
				}
			}
			catch(Exception e){}
			Thread.sleep(5000);
			//Click on the Add OverShortage Reasons option
			driver.findElement(By.cssSelector(excel.getData(5, 60, 4))).click();
			Thread.sleep(3000);
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(5, 61, 1))).getText().equalsIgnoreCase("New Over Shortage"))
			{
				test.log(LogStatus.PASS, "New Over Shortage Reason form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Over Shortage Reason form loaded Failed");
			}
	
			Thread.sleep(3000);
			
			//Clear the Reason field
			driver.findElement(By.name(excel.getData(5, 62, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(5, 62, 3))).sendKeys(Utility.getProperty("OverShortageReason"));
			Thread.sleep(2000);
				
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(5, 63, 1))).click();
			Thread.sleep(2000);
			
			//Check whether the new discount saved or not
			if(driver.findElement(By.cssSelector(excel.getData(5, 64, 4))).getText().equalsIgnoreCase("Saved successfully"))
			{
				test.log(LogStatus.PASS, "New OverShortage Reason Saved Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New OverShortage Reason Save Failed");
			}
	
			Thread.sleep(5000);
		}
			
		@Test(enabled=false,priority=165)
		public void Reasons_Overshortage_method_editOverShortageReasons(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(5, 71, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys(Utility.getProperty("OverShortageReason")+"1");
			Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys(Keys.BACK_SPACE);
			Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(8000);
			//Click the Edit icon
		//	driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			driver.findElement(By.xpath(excel.getData(5, 66, 1))).click();
			Thread.sleep(3000);
			//Clear the Reasons field
			driver.findElement(By.name(excel.getData(5, 67, 3))).clear();
			//Enter the Reasons
			driver.findElement(By.name(excel.getData(5, 67, 3))).sendKeys(Utility.getProperty("OverShortageReason")+"1");
	
	
			//Click the update
			//driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
			driver.findElement(By.xpath(excel.getData(5, 63, 1))).click();
			
			WebElement up=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
			//Check weather the new Over shortage is updated or not
			if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Updated successfully"))
			{
				test.log(LogStatus.PASS, "New Over Shortage Reason Updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Over Shortage Reason Updated failed");
			}
	
			Thread.sleep(5000);
	
		}
		
		@Test(enabled=false,priority=166)
		public void Reasons_Overshortage_method_deleteOverShortageReasons(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(5, 71, 1))).clear();
			Thread.sleep(5000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys(Utility.getProperty("OverShortageReason")+"11");
			Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys(Keys.BACK_SPACE);
			Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(8000);
			//Click the Delete button
			driver.findElement(By.xpath(excel.getData(5, 68, 1))).click();
			//Click the Yes button in the popup
			Thread.sleep(1500);
			driver.findElement(By.cssSelector(excel.getData(5, 69, 4))).click();
			Thread.sleep(3000);
			//Check the menu item deleted or not
			if(driver.findElement(By.cssSelector(excel.getData(5, 64, 4))).getText().equalsIgnoreCase("Inactivated successfully"))
			{
				test.log(LogStatus.PASS, "Over Shortage Reason is deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Over Shortage Reason is deleted Failed");
			}
			Thread.sleep(7000);
			
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(5, 72, 1))).click();
			Thread.sleep(3000);
			//Click the success button
			driver.findElement(By.cssSelector(excel.getData(5, 69, 4))).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			Thread.sleep(1500);
			driver.findElement(By.cssSelector(excel.getData(5, 69, 4))).click();
			Thread.sleep(3000);
			
			//Check the menu item activated or not
			if(driver.findElement(By.cssSelector(excel.getData(5, 64, 4))).getText().equalsIgnoreCase("Activated successfully"))
			{
				test.log(LogStatus.PASS, "Over Shortage is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Over Shortage is activated Failed");
			}
			
			Thread.sleep(5000);
		}
	
		@Test(enabled=false,priority=167)
		public void Reasons_Overshortage_method_closeButton(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(5, 72, 1))).click();
			Thread.sleep(3000);
			
			
			//Click on the Add Tax Exempt Reasons option
			driver.findElement(By.id(excel.getData(5, 60, 2))).click();
			Thread.sleep(3000);
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(5, 61, 1))).getText().equalsIgnoreCase("New Over Shortage"))
			{
				test.log(LogStatus.PASS, "New Over Shortage Reason form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Over Shortage Reason form loaded Failed");
			}
	
			Thread.sleep(3000);
			
			//Clear the Reason field
			driver.findElement(By.name(excel.getData(5, 62, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(5, 62, 3))).sendKeys(Utility.getProperty("OverShortageReason"));
			Thread.sleep(2000); 
				
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(5, 65, 1))).click();
			Thread.sleep(3000);
			//Check whether the new TaxExemptReason canceled or not
			if(driver.findElement(By.id(excel.getData(5, 60, 2))).isDisplayed())
			{
				test.log(LogStatus.PASS, "New Over Shortage Reason Canceled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Over Shortage Reason Canceled Failed");
			}
	
			Thread.sleep(5000);
		}
			
		@Test(enabled=false,priority=168)
		public void Reasons_Overshortage_method_verifyActive_InActiveButton(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(5, 71, 1))).clear();
	
			Thread.sleep(5000);
			//Click Active or In Active Button
			driver.findElement(By.xpath(excel.getData(5, 72, 1))).click();
	
//			Thread.sleep(10000);
//			//Check the New Display Group form is loaded or not
//			if(driver.findElement(By.cssSelector(excel.getData(5, 69, 4))).isDisplayed())
//			{
//				test.log(LogStatus.PASS, "Deleted groups are here, we are in InActive Page");
//				Thread.sleep(10000);
//				//Click Active or In Active Button
//				driver.findElement(By.xpath(excel.getData(5, 71, 1))).click();
//	
//			}
//			else if(driver.findElement(By.cssSelector(excel.getData(5, 74, 4))).isDisplayed())
//			{
//				test.log(LogStatus.FAIL, "Deleted groups are not here, we are in Active Page");
//			}
			Thread.sleep(5000);
		}

}
