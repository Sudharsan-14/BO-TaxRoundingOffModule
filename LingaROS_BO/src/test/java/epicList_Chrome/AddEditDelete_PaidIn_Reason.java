package epicList_Chrome;

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


public class AddEditDelete_PaidIn_Reason {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete_PaidIn");


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
			Browser b = new Browser();
			b.Logout(driver, test);
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
			Reasons_PaidIn_method_openPaidInReasons(driver);
			Reasons_PaidIn_method_refreshPaidInReasons(driver);
			Reasons_PaidIn_method_addPaidInReasons(driver);
			Reasons_PaidIn_method_editPaidInReasons(driver);
			Reasons_PaidIn_method_deletePaidInReasons(driver);
			Reasons_PaidIn_method_closeButton(driver);
			Reasons_PaidIn_method_verifyActive_InActiveButton(driver);
			Thread.sleep(1500);
		}

	
	@Test(enabled=false,priority=121)
	public void Reasons_PaidIn_method_openPaidInReasons(WebDriver driver) throws Exception
	{
		//Click the Products/Items option
		/*driver.findElement(By.xpath("//span[.='Products/Items']")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Reasons']"));
		//Scroll the page till the Reason option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Reasons Option		
		driver.findElement(By.xpath("//span[.='Reasons']")).click();*/
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"reasons");
		Thread.sleep(8000);
		
		
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
		ExcelDataConfig ex = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		//Click the PaidIn Reasons Tap
		driver.findElement(By.xpath(ex.getData(4, 1, 1))).click();
		Thread.sleep(5000);
		
		try
		{
		//Check PaidIn Reasons page opened or not
		if(driver.findElement(By.xpath(ex.getData(4, 2, 1))).getText().equalsIgnoreCase("Paid Ins"))
		{
			test.log(LogStatus.PASS, "Paid In Reasons page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Paid In Reasons page loaded Failed");
			
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
	
	@Test(enabled=false,priority=122)
	public void Reasons_PaidIn_method_refreshPaidInReasons(WebDriver driver) throws Exception
	{
		
		Thread.sleep(5000);
		ExcelDataConfig ex = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		//Click the refresh button
		driver.findElement(By.xpath(ex.getData(4, 3, 1))).click();
		Thread.sleep(10000);
		//Check PaidIn Reasons page opened or not
		if(driver.findElement(By.xpath(ex.getData(4, 1, 1))).getText().equalsIgnoreCase("Paid Ins"))
		{
			test.log(LogStatus.PASS, "Paid In Reasons page refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.INFO, "Paid In Reasons page refreshed Failed");
		}
		Thread.sleep(5000);

	}
	
	@Test(enabled=false,priority=124)
	public void Reasons_PaidIn_method_addPaidInReasons(WebDriver driver) throws Exception
	{
		for (int i = 0; i < 10; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		
		Thread.sleep(5000);
		ExcelDataConfig ex = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		/*try{
			if(driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).isDisplayed())
			{
				//Click the Active/In Active Button
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[1]/div[1]/input")).click();
				Thread.sleep(3000);		
			}
		}
		catch(Exception e){}*/
		//Click on the Add PaidIn Reasons option
		driver.findElement(By.id(ex.getData(4, 4, 2))).click();
		Thread.sleep(5000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(ex.getData(4, 5, 1))).getText().equalsIgnoreCase("New Paid In"))
		{
			test.log(LogStatus.PASS, "New Paid In Reason form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Paid In Reason form loaded Failed");
		}

		Thread.sleep(3000);
		
		//Clear the Reason field
		driver.findElement(By.name(ex.getData(4, 6, 3))).clear();
		//Enter the Name
		driver.findElement(By.name(ex.getData(4, 6, 3))).sendKeys(Utility.getProperty("PaidInReason"));
		Thread.sleep(2000);
			
		//Click the Save button
		driver.findElement(By.xpath(ex.getData(3, 572,1))).click();
		
		WebElement up=driver.findElement(By.xpath(ex.getData(3, 39,1)));
		WebDriverWait wait=new WebDriverWait(driver,40);
		//Check whether the Paid In Saved successfully or not
		if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Saved successfully"))
		{
			test.log(LogStatus.PASS, "New PaidIn Reason Saved Sucessfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New PaidIn Reason Save Failed");
		}

		Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=125)
	public void Reasons_PaidIn_method_editPaidInReasons(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		ExcelDataConfig ex = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Clear the search field
		driver.findElement(By.xpath(ex.getData(3, 40, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(ex.getData(3, 40, 1))).sendKeys(Utility.getProperty("PaidInReason")+"1");
		Thread.sleep(4000);driver.findElement(By.xpath(ex.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);
		Thread.sleep(4000);driver.findElement(By.xpath(ex.getData(3, 40, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(8000);
		//Click the Edit icon
		driver.findElement(By.cssSelector(ex.getData(4, 11, 4))).click();
		
		Thread.sleep(3000);
		//Clear the Reasons field
		driver.findElement(By.name(ex.getData(4, 6, 3))).clear();
		//Enter the Reasons
		driver.findElement(By.name(ex.getData(4, 6, 3))).sendKeys(Utility.getProperty("PaidInReason")+"1a");


		//Click the update
		driver.findElement(By.cssSelector(ex.getData(4, 12, 4))).click();
	try
	{
		WebElement up=driver.findElement(By.xpath(ex.getData(3, 39,1)));
		WebDriverWait wait=new WebDriverWait(driver,60);
		//Check whether the Product Item updated successfully or not
		if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New Paid In Reason Updated Sucessfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Paid In Reason Updated failed");
		}
	}
	catch(Exception e) {}
		Thread.sleep(5000);

	}
	
	@Test(enabled=false,priority=126)
	public void Reasons_PaidIn_method_deletePaidInReasons(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		ExcelDataConfig ex = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		//Clear the search field
		driver.findElement(By.xpath(ex.getData(3, 40, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(ex.getData(3, 40, 1))).sendKeys(Utility.getProperty("PaidInReason")+"11");
		Thread.sleep(4000);driver.findElement(By.xpath(ex.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);
		Thread.sleep(4000);driver.findElement(By.xpath(ex.getData(3, 40, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(8000);
		//Click the Delete button
		driver.findElement(By.cssSelector(ex.getData(4, 13, 4))).click();
		//Click the Yes button in the popup
		Thread.sleep(1500);driver.findElement(By.cssSelector(ex.getData(4, 14, 4))).click();
		Thread.sleep(3000);
		//Check the menu item deleted or not
		if(driver.findElement(By.xpath(ex.getData(3, 39,1))).getText().equalsIgnoreCase("Inactivated successfully"))
		{
			test.log(LogStatus.PASS, "Paid In Reason is deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Paid In Reason is deleted Failed");
		}

		Thread.sleep(8000);
		//Click the Active Button
		driver.findElement(By.xpath(ex.getData(3, 44,1))).click();
		Thread.sleep(3000);
		
		//Click the success button
		driver.findElement(By.cssSelector(ex.getData(4, 14, 4))).click();
		Thread.sleep(1000);
		
		//Click the Yes button in the popup
		Thread.sleep(1500);driver.findElement(By.cssSelector(ex.getData(4, 14, 4))).click();
		Thread.sleep(3000);
		
		//Check the menu item activated or not
		if(driver.findElement(By.xpath(ex.getData(3, 39,1))).getText().equalsIgnoreCase("Activated successfully"))
		{
			test.log(LogStatus.PASS, "Paid In is activated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Paid In is activated Failed");
		}
		
		Thread.sleep(5000);
	}

	@Test(enabled=false,priority=127)
	public void Reasons_PaidIn_method_closeButton(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		ExcelDataConfig ex = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(3000);
		//Click the Active Button
		driver.findElement(By.xpath(ex.getData(3, 44,1))).click();
		
		Thread.sleep(5000);
		//Click on the Add Tax Exempt Reasons option
		driver.findElement(By.id(ex.getData(4, 4, 2))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(ex.getData(4, 5, 1))).getText().equalsIgnoreCase("New Paid In"))
		{
			test.log(LogStatus.PASS, "New Paid In Reason form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Paid In Reason form loaded Failed");
		}

		Thread.sleep(3000);
		
		//Clear the Reason field
		driver.findElement(By.name(ex.getData(4, 6, 3))).clear();
		//Enter the Name
		driver.findElement(By.name(ex.getData(4, 6, 3))).sendKeys(Utility.getProperty("PaidInReason"));
		Thread.sleep(2000); 
			
		//Click the Cancel button
		driver.findElement(By.xpath(ex.getData(3, 45,1))).click();
		Thread.sleep(3000);
		//Check whether the new TaxExemptReason canceled or not
		if(driver.findElement(By.id(ex.getData(4, 4, 2))).isDisplayed())
		{
			test.log(LogStatus.PASS, "New Paid In Reason Canceled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Paid In Reason Canceled Failed");
		}

		Thread.sleep(5000);
	}
		
	@Test(enabled=false,priority=128)
	public void Reasons_PaidIn_method_verifyActive_InActiveButton(WebDriver driver) throws Exception
	{
		Thread.sleep(3000);
		ExcelDataConfig ex = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		//Clear the search field
		driver.findElement(By.xpath(ex.getData(3, 40, 1))).clear();
		
		Thread.sleep(5000);
		//Click Active or In Active Button
		driver.findElement(By.xpath(ex.getData(3, 44,1))).click();

		Thread.sleep(5000);
		//Check the New Display Group form is loaded or not
		if(driver.findElement(By.cssSelector(ex.getData(4, 14, 4))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Deleted groups are here, we are in InActive Page");
			Thread.sleep(10000);
			//Click Active or In Active Button
			driver.findElement(By.xpath(ex.getData(3, 44,1))).click();

		}
		else if(driver.findElement(By.cssSelector(ex.getData(4, 17, 4))).isDisplayed())
		{
			test.log(LogStatus.FAIL, "Deleted groups are not here, we are in Active Page");
		}
		Thread.sleep(5000);
	}

}
