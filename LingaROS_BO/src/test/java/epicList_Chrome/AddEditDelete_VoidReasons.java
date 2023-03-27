package epicList_Chrome;

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

public class AddEditDelete_VoidReasons {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Void Reasons");

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
			Browser a = new Browser();
			a.Logout(driver, test);
			
		}
		
		{
		
			try {
				if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
				{
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));	
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath(excel.getData(5, 41, 1)));
				//Scroll the page till the Reason option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
				//Wait for 30 seconds
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				// Click on Logout button
				driver.findElement(By.xpath(excel.getData(5, 41, 1))).click();
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
	ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	JavascriptExecutor je = (JavascriptExecutor) driver;
				
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath(excel.getData(5, 41, 1)));
				//Scroll the page till the Reason option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
				//Wait for 30 seconds
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				// Click on Logout button
				driver.findElement(By.xpath(excel.getData(5, 41, 1))).click();
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
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			Reasons_Void_Reasons_method_openVoidReasons(driver);
			Reasons_Void_Reasons_method_refreshVoidReasons(driver);
			Reasons_Void_Reasons_method_addVoidReasons(driver);
			Reasons_Void_Reasons_method_editVoidReasons(driver);
			Reasons_Void_Reasons_method_deleteVoidReasons(driver);
			Reasons_Void_Reasons_method_closeButton(driver);
			Reasons_Void_Reasons_method_verifyActive_InActiveButton(driver);
			Thread.sleep(1500);
		}
	
	@Test(enabled=false,priority=101)
	public void Reasons_Void_Reasons_method_openVoidReasons(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		/*//Click the Products/Items option
		driver.findElement(By.xpath("//span[.='Products/Items']")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Reasons']"));
		//Scroll the page till the Reason option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Reasons Option		
		driver.findElement(By.xpath("//span[.='Reasons']")).click();*/
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"reasons");
		Thread.sleep(5000);
/*		//Check Tax exempt page opened or not
		if(driver.findElement(By.xpath("//a[.='Tax Exempt Reasons']")).getText().equalsIgnoreCase("Tax Exempt Reasons"))
		{
			test.log(LogStatus.PASS, "Tax Exempt Reasons page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tax Exempt Reasons page loaded Failed");
		}
		
		Thread.sleep(5000);*/
		
		//Click the Void Reasons Tap
		driver.findElement(By.xpath(excel.getData(5, 21, 1))).click();
		Thread.sleep(3000);
		try
		{
		//Check Void Reasons page opened or not
		if(driver.findElement(By.xpath(excel.getData(5, 45, 1))).getText().equalsIgnoreCase("Void Reasons"))
		{
			test.log(LogStatus.PASS, "Void Reasons page loaded Successfully");
			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Void Reasons page loaded Failed");
			
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

	}
	
	@Test(enabled=false,priority=102)
	public void Reasons_Void_Reasons_method_refreshVoidReasons(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Click the refresh button
		driver.findElement(By.xpath(excel.getData(5, 22, 1))).click();
		Thread.sleep(5000);
		//Check Void Reasons page opened or not
		if(driver.findElement(By.xpath(excel.getData(5, 45, 1))).getText().equalsIgnoreCase("Void Reasons"))
		{
			test.log(LogStatus.PASS, "Void Reasons page refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Void Reasons page refreshed Failed");
		}
		Thread.sleep(5000);

	}
	
	@Test(enabled=false,priority=104)
	public void Reasons_Void_Reasons_method_addVoidReasons(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		for (int i = 0; i < 10; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		Thread.sleep(3000);
		try{
			if(driver.findElement(By.cssSelector(excel.getData(5, 48, 4))).isDisplayed())
			{
				//Click the Active/In Active Button
				driver.findElement(By.tagName(excel.getData(5, 34, 5))).click();
				Thread.sleep(3000);		
			}
		}
		catch(Exception e){}
		Thread.sleep(5000);
		//Click on the Add Void Reasons option
		driver.findElement(By.xpath(excel.getData(5, 23, 1))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 46, 1))).getText().equalsIgnoreCase("New Void Reason"))
		{
			test.log(LogStatus.PASS, "New Void Reason form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Void Reason form loaded Failed");
		}

		Thread.sleep(3000);
		
		//Clear the Reason field
		driver.findElement(By.xpath(excel.getData(5, 24, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(5, 24, 1))).sendKeys(Utility.getProperty("VoidReason"));
		Thread.sleep(2000);
			
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(5, 25, 1))).click();
		Thread.sleep(3500);
		//Check whether the new discount saved or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Saved successfully"))
		{
			test.log(LogStatus.PASS, "New Void Reason Saved Sucessfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Void Reason Save Failed");
		}

		Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=105)
	public void Reasons_Void_Reasons_method_editVoidReasons(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("VoidReason")+"1");
		Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
		Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(8000);
		//Click the Edit icon
		driver.findElement(By.xpath(excel.getData(5, 27, 1))).click();
		
		Thread.sleep(3000);
		//Clear the Reasons field
		driver.findElement(By.xpath(excel.getData(5, 28, 1))).clear();
		//Enter the Reasons
		driver.findElement(By.xpath(excel.getData(5, 28, 1))).sendKeys(Utility.getProperty("VoidReason")+"1");


		//Click the update
		driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
		
		WebElement up=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,50);
		//Check weather the new Void Reason is updated or not
		if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New Void Reason Updated Sucessfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Void Reason Updated failed");
		}

		Thread.sleep(5000);

	}
	
	@Test(enabled=false,priority=106)
	public void Reasons_Void_Reasons_method_deleteVoidReasons(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("VoidReason")+"1");
		Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
		Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(8000);
		//Click the Delete button
		driver.findElement(By.xpath(excel.getData(5, 31, 1))).click();
		//Click the Yes button in the popup
		Thread.sleep(1500);
		driver.findElement(By.xpath(excel.getData(5, 32, 1))).click();
		Thread.sleep(3500);
		//Check the menu item deleted or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Inactivated successfully"))
		{
			test.log(LogStatus.PASS, "Void Reason is deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Void Reason is deleted Failed");
		}

		Thread.sleep(7000);
		//Click the Active Button
		driver.findElement(By.tagName(excel.getData(5, 34, 5))).click();
		
		Thread.sleep(3000);
		//Click the success button
		driver.findElement(By.cssSelector(excel.getData(5, 48, 4))).click();
		
		
		//Click the Yes button in the popup
		driver.findElement(By.xpath(excel.getData(5, 37, 1))).click();
		Thread.sleep(4000);
		
		//Check the menu item activated or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Activated successfully"))
		{
			test.log(LogStatus.PASS, "Void Reason is activated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Void Reason is activated Failed");
		}
		
		Thread.sleep(5000);
	}

	@Test(enabled=false,priority=107)
	public void Reasons_Void_Reasons_method_closeButton(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Click the Active Button
		driver.findElement(By.tagName(excel.getData(5, 34, 5))).click();
		
		Thread.sleep(5000);
		//Click on the Add Tax Exempt Reasons option
		driver.findElement(By.id(excel.getData(5, 24, 2))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 46, 1))).getText().equalsIgnoreCase("New Void Reason"))
		{
			test.log(LogStatus.PASS, "New Void Reason form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Void Reason form loaded Failed");
		}

		Thread.sleep(3000);
		
		//Clear the Reason field
		driver.findElement(By.name(excel.getData(5, 24, 3))).clear();
		//Enter the Name
		driver.findElement(By.name(excel.getData(5, 24, 3))).sendKeys(Utility.getProperty("VoidReason"));
		Thread.sleep(2000); 
			
		//Click the Cancel button
		driver.findElement(By.xpath(excel.getData(5, 30, 1))).click();
		Thread.sleep(3000);
		//Check whether the new TaxExemptReason canceled or not
		if(driver.findElement(By.id(excel.getData(5, 3, 2))).isDisplayed())
		{
			test.log(LogStatus.PASS, "New Void Reason Canceled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Void Reason Canceled Failed");
		}

		Thread.sleep(5000);
	}
		
	@Test(enabled=false,priority=108)
	public void Reasons_Void_Reasons_method_verifyActive_InActiveButton(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
		
		Thread.sleep(5000);
		//Click Active or In Active Button
		driver.findElement(By.tagName(excel.getData(5, 34, 5))).click();

		Thread.sleep(3500);
		//Check the New Display Group form is loaded or not
		if(driver.findElement(By.cssSelector(excel.getData(5, 48, 4))).isDisplayed())
		{
			
			test.log(LogStatus.PASS, "Deleted groups are here, we are in InActive Page");
			Thread.sleep(10000);
			//Click Active or In Active Button
			driver.findElement(By.tagName(excel.getData(5, 34, 5))).click();

		}
		else if(driver.findElement(By.cssSelector(excel.getData(5, 50, 4))).isDisplayed())
		{
			test.log(LogStatus.FAIL, "Deleted groups are not here, we are in Active Page");
		}
		Thread.sleep(5000);
	}

}
