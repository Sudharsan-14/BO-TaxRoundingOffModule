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


public class AddEditDelete_AttachRequest_Reason {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Attach Request");

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
		
		//SendMail.snedMailWithAttachment();
		//SendMail.sendMailWithAttachment_Passed_And_Failed_Test_Methods();
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
			if(driver.findElement(By.xpath(excel.getData(5, 55, 1))).getText().equalsIgnoreCase("User Login"))
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
			if(driver.findElement(By.xpath(excel.getData(5, 56, 1))).getText().equalsIgnoreCase("Account Login"))
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
		Reason_Attach_Request_openAttachRequestReasons(driver);
		Reason_Attach_Request_refreshAttachRequestReasons(driver);
		Reason_Attach_Request_addAttachRequestReasons(driver);
		Reason_Attach_Request_editAttachRequestReasons(driver);
		Reason_Attach_Request_deleteAttachRequestReasons(driver);
		Reason_Attach_Request_closeButton(driver);
		Reason_Attach_Request_verifyActive_InActiveButton(driver);
		Thread.sleep(1500);
	}

	@Test(enabled = false,priority=181)
	public void Reason_Attach_Request_openAttachRequestReasons(WebDriver driver) throws Exception
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
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+("reasons"));
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
		
		
		
		//Click the AttachRequest Reasons Tap
		driver.findElement(By.xpath(excel.getData(5, 76, 1))).click();
		Thread.sleep(3000);
		try
		{
		//Check AttachRequest Reasons page opened or not
		if(driver.findElement(By.xpath(excel.getData(5, 77, 1))).getText().equalsIgnoreCase("Attach Requests"))
		{
			test.log(LogStatus.PASS, "Attach Request Reasons page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Attach Request Reasons page loaded Failed");
			
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
	
	@Test(enabled = false,priority=182)
	public void Reason_Attach_Request_refreshAttachRequestReasons(WebDriver driver) throws InterruptedException
	{
		ExcelDataConfig excel;
		try {
			excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		
		Thread.sleep(5000);
		//Click the refresh button
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/a/i")).click();
		driver.findElement(By.xpath(excel.getData(5, 78, 1))).click();
		Thread.sleep(5000);
		//Check AttachRequest Reasons page opened or not
		if(driver.findElement(By.xpath(excel.getData(5, 77, 1))).getText().equalsIgnoreCase("Attach Requests"))
		{
			test.log(LogStatus.PASS, "Attach Request Reasons page refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Attach Request Reasons page refreshed Failed");
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Thread.sleep(5000);
	}
	}
	
	@Test(enabled = false,priority=184)
	public void Reason_Attach_Request_addAttachRequestReasons(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		for (int i = 0; i < 10; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		Thread.sleep(3000);
		try{
			
			if(driver.findElement(By.cssSelector(excel.getData(5, 79, 4))).isDisplayed())
			{
				//Click the Active/In Active Button
				driver.findElement(By.xpath(excel.getData(5, 80, 1))).click();
				Thread.sleep(3000);		
			}
		}
		catch(Exception e){}
		Thread.sleep(5000);
		//Click on the Add AttachRequest Reasons option
		driver.findElement(By.xpath(excel.getData(5, 81, 1))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 82, 1))).getText().equalsIgnoreCase("New Attach Request"))
		{
			test.log(LogStatus.PASS, "New Attach Request Reason form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Attach Request Reason form loaded Failed");
		}

		Thread.sleep(3000);
		
		//Clear the Reason field
		driver.findElement(By.xpath(excel.getData(5, 83, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(5, 83, 1))).sendKeys(Utility.getProperty("AttachRequestReason"));
		Thread.sleep(2000);
			
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(5, 63, 1))).click();
		Thread.sleep(2000);
		
		//Check whether the new discount saved or not
		if(driver.findElement(By.cssSelector(excel.getData(5, 64, 4))).getText().equalsIgnoreCase("Saved successfully"))
		{
			test.log(LogStatus.PASS, "New AttachRequest Reason Saved Sucessfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New AttachRequest Reason Save Failed");
		}

		Thread.sleep(5000);
	}
		
	@Test(enabled = false,priority=185)
	public void Reason_Attach_Request_editAttachRequestReasons(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 71, 1))).clear();
		Thread.sleep(5000);
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys(Utility.getProperty("AttachRequestReason")+"1");
		Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys(Keys.BACK_SPACE);
		Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(8000);
		//Click the Edit icon
		driver.findElement(By.cssSelector(excel.getData(5, 86, 4))).click();
		
		Thread.sleep(3000);
		//Clear the Reasons field
		driver.findElement(By.name(excel.getData(5, 67, 3))).clear();
		
		//Enter the Reasons
		driver.findElement(By.name(excel.getData(5, 67, 3))).sendKeys(Utility.getProperty("AttachRequestReason")+"1");


		//Click the update
		driver.findElement(By.xpath(excel.getData(5, 63, 1))).click();
		
		WebElement up=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,60);
		//Check weather the new Attach Request is updated or not
		if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New Attach Request Reason Updated Sucessfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Attach Request Reason Updated failed");
		}

		Thread.sleep(5000);

	}
	
	@Test(enabled = false,priority=186)
	public void Reason_Attach_Request_deleteAttachRequestReasons(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 71, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys(Utility.getProperty("AttachRequestReason")+"11");
		Thread.sleep(5000);driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys(Keys.BACK_SPACE);Thread.sleep(4000);
		Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(8000);
		//Click the Delete button
		driver.findElement(By.xpath(excel.getData(5, 68, 1))).click();
		//Click the Yes button in the popup
		Thread.sleep(1500);
		driver.findElement(By.cssSelector(excel.getData(5, 79, 4))).click();
		Thread.sleep(3000);
		//Check the menu item deleted or not
		if(driver.findElement(By.cssSelector(excel.getData(5, 64, 4))).getText().equalsIgnoreCase("Inactivated successfully"))
		{
			test.log(LogStatus.PASS, "Attach Request Reason is deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Attach Request Reason is deleted Failed");
		}

		Thread.sleep(7000);
		//Click the Active Button
		driver.findElement(By.xpath(excel.getData(5, 72, 1))).click();
		Thread.sleep(3000);		
		//Click the success button
		driver.findElement(By.cssSelector(excel.getData(5, 69, 4))).click();
		Thread.sleep(1000);
		
		//Click the Yes button in the popup
		Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(5, 69, 4))).click();
		Thread.sleep(3000);
		
		//Check the menu item activated or not
		if(driver.findElement(By.cssSelector(excel.getData(5, 64, 4))).getText().equalsIgnoreCase("Activated successfully"))
		{
			test.log(LogStatus.PASS, "Attach Request is activated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Attach Request is activated Failed");
		}
		
		Thread.sleep(5000);
	}

	@Test(enabled = false,priority=187)
	public void Reason_Attach_Request_closeButton(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Click the Active Button
		driver.findElement(By.xpath(excel.getData(5, 80, 1))).click();
		Thread.sleep(3000);
		//Click on the Add Tax Exempt Reasons option
		driver.findElement(By.id(excel.getData(5, 81, 2))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 82, 1))).getText().equalsIgnoreCase("New Attach Request"))
		{
			test.log(LogStatus.PASS, "New Attach Request Reason form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Attach Request Reason form loaded Failed");
		}

		Thread.sleep(3000);
		
		//Clear the Reason field
		driver.findElement(By.name(excel.getData(5, 82, 3))).clear();
		//Enter the Name
		driver.findElement(By.name(excel.getData(5, 82, 3))).sendKeys(Utility.getProperty("AttachRequestReason"));
		Thread.sleep(2000); 
			
		//Click the Cancel button
		driver.findElement(By.xpath(excel.getData(5, 87, 1))).click();
		Thread.sleep(3000);
		//Check whether the new TaxExemptReason canceled or not
		if(driver.findElement(By.id(excel.getData(5, 81, 2))).isDisplayed())
		{
			test.log(LogStatus.PASS, "New Attach Request Reason Canceled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Attach Request Reason Canceled Failed");
		}

		Thread.sleep(5000);
	}
		
	@Test(enabled = false,priority=188)
	public void Reason_Attach_Request_verifyActive_InActiveButton(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 85, 1))).clear();

		Thread.sleep(5000);
		//Click Active or In Active Button
		driver.findElement(By.xpath(excel.getData(5, 80, 1))).click();

		Thread.sleep(5000);
		//Check the New Display Group form is loaded or not
		if(driver.findElement(By.cssSelector(excel.getData(5, 79, 4))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Deleted groups are here, we are in InActive Page");
			Thread.sleep(10000);
			//Click Active or In Active Button
			driver.findElement(By.xpath(excel.getData(5, 80, 1))).click();

		}
		else if(driver.findElement(By.cssSelector(excel.getData(5, 74, 4))).isDisplayed())
		{
			test.log(LogStatus.FAIL, "Deleted groups are not here, we are in Active Page");
		}
		Thread.sleep(5000);
	}

}
