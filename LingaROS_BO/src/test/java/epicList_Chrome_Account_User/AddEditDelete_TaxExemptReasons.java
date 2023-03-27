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

public class AddEditDelete_TaxExemptReasons {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete_TaxExemptReasons");

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
			Reasons_TaxExempt_method_openTaxExemptReasons(driver);
			Reasons_TaxExempt_method_refreshTaxExemptReasons(driver);
			Reasons_TaxExempt_method_addTaxExemptReasons(driver);
			Reasons_TaxExempt_method_editTaxExemptReasons(driver);
			Reasons_TaxExempt_method_deleteTaxExemptReasons(driver);
			Reasons_TaxExempt_method_closeButton(driver);
			Reasons_TaxExempt_method_verifyActive_InActiveButton(driver);
			Thread.sleep(1500);
		}
	
	@Test(enabled=false,priority=81)
	public void Reasons_TaxExempt_method_openTaxExemptReasons(WebDriver driver) throws Exception
	{
		Thread.sleep(15000);

			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(2000);
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
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"reasons");
		Thread.sleep(5000);
		try
		{
		//Check items page opened or not
		if(driver.findElement(By.xpath(excel.getData(5, 1, 1))).getText().equalsIgnoreCase("Tax Exempt Reasons"))
		{
			test.log(LogStatus.PASS, "Tax Exempt Reasons page loaded Successfully");
			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Tax Exempt Reasons page loaded Failed");
			
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
	
	@Test(enabled=false,priority=82)
	public void Reasons_TaxExempt_method_refreshTaxExemptReasons(WebDriver driver) throws Exception
	{
		
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(5000);
		//Click the refresh button
		driver.findElement(By.xpath(excel.getData(5, 22, 1))).click();
		Thread.sleep(5000);
		//Check Check based discount page opened or not
		if(driver.findElement(By.xpath(excel.getData(5, 1, 1))).getText().equalsIgnoreCase("Tax Exempt Reasons"))
		{
			test.log(LogStatus.PASS, "Tax Exempt Reasons page refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tax Exempt Reasons page refreshed Failed");
		}
		Thread.sleep(5000);

	}
	
	@Test(enabled=false,priority=84)
	public void Reasons_TaxExempt_method_addTaxExemptReasons(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		/* for (int i = 0; i < 10; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}*/
		Thread.sleep(3000);
		try{
			if(driver.findElement(By.cssSelector(excel.getData(5, 48, 4))).isDisplayed())
			{
				//Click the Active/In Active Button
				driver.findElement(By.xpath(excel.getData(5, 16, 1))).click();
				Thread.sleep(3000);		
			}
		}
		catch(Exception e){}
		Thread.sleep(5000);
		//Click on the Add Tax Exempt Reasons option
		driver.findElement(By.id(excel.getData(5, 3, 2))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 4, 1))).getText().equalsIgnoreCase("New Tax Exempt Reason"))
		{
			test.log(LogStatus.PASS, "New Tax Exempt Reason form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Tax Exempt Reason form loaded Failed");
		}

		Thread.sleep(3000);
		
		//Clear the Reason field
		driver.findElement(By.name(excel.getData(5, 3, 3))).clear();
		//Enter the Name
		driver.findElement(By.name(excel.getData(5, 3, 3))).sendKeys(Utility.getProperty("TaxExemptReason"));
		Thread.sleep(2000);
			
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(5, 5, 1))).click();
		Thread.sleep(3000);
		
		//Check whether the new discount saved or not
		if(driver.findElement(By.xpath(excel.getData(5, 10, 1))).getText().equalsIgnoreCase("Saved successfully"))
		{
			test.log(LogStatus.PASS, "New Tax Exepmt Reason Saved Sucessfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Tax Exempt Reason Save Failed");
		}

		Thread.sleep(5000);
	}
		
	@Test(enabled=false,priority=85)
	public void Reasons_TaxExempt_method_editTaxExemptReasons(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 7, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 7, 1))).sendKeys(Utility.getProperty("TaxExemptReason")+"1");
		Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 7, 1))).sendKeys(Keys.BACK_SPACE);
		Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 7, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(8000);
		//Click the Edit icon
		//driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		driver.findElement(By.xpath(excel.getData(5, 9, 1))).click();
		Thread.sleep(3000);
		//Clear the Reasons field
		driver.findElement(By.name(excel.getData(5, 3, 3))).clear();
		//Enter the Reasons
		driver.findElement(By.name(excel.getData(5, 3, 3))).sendKeys(Utility.getProperty("TaxExemptReason")+"1");


		//Click the update
		driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
		
		WebElement up=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,60);
		//Check weather the new Tare Exempt is updated or not
		if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New Tax Exepmt Reason Updated Sucessfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Tax Exepmt Reason Updated failed");
		}

		Thread.sleep(5000);

	}
	
	@Test(enabled=false,priority=86)
	public void Reasons_TaxExempt_method_deleteTaxExemptReasons(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 7, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 7, 1))).sendKeys(Utility.getProperty("TaxExemptReason")+"11");
		Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 7, 1))).sendKeys(Keys.BACK_SPACE);
		Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 7, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(8000);
		//Click the Delete button
		//driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
		driver.findElement(By.xpath(excel.getData(5, 13, 1))).click();
		Thread.sleep(3000);
		//Click the Yes button in the popup
		Thread.sleep(1500);
		//driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
		driver.findElement(By.xpath(excel.getData(5, 19, 1))).click();
		Thread.sleep(3000);
		//Check the menu item deleted or not
		// System.out.println(driver.switchTo().alert().getText());
		/* WebElement test1;
		test1= Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(excel.getData(5, 10, 1))));
		test1.click();*/
		if(driver.findElement(By.xpath(excel.getData(5, 10, 1))).getText().equalsIgnoreCase("Inactivated successfully"))
		{
			test.log(LogStatus.PASS, "Tax Exepmt Reason is deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tax Exepmt Reason is deleted Failed");
		}

		Thread.sleep(7000);
		
		//Click the Active Button
		driver.findElement(By.xpath(excel.getData(5, 16, 1))).click();
		Thread.sleep(3000);
		//Click the success button
		driver.findElement(By.cssSelector(excel.getData(5, 39, 4))).click();
		//Click the Yes button in the popup
		Thread.sleep(1500);
		driver.findElement(By.cssSelector(excel.getData(5, 39, 4))).click();
		Thread.sleep(3000);
		
		//Check the menu item activated or not
		if(driver.findElement(By.xpath(excel.getData(5, 10, 1))).getText().equalsIgnoreCase("Activated successfully"))
		{
			test.log(LogStatus.PASS, "Tax Exepmt Reason is activated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tax Exepmt Reason is activated Failed");
		}
		
		Thread.sleep(5000);
	
	}

	@Test(enabled=false,priority=87)
	public void Reasons_TaxExempt_method_closeButton(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Click the Active Button
		driver.findElement(By.xpath(excel.getData(5, 16, 1))).click();
		
		
		Thread.sleep(5000);
		//Click on the Add Tax Exempt Reasons option
		driver.findElement(By.id(excel.getData(5, 3, 2))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(5, 4, 1))).getText().equalsIgnoreCase("New Tax Exempt Reason"))
		{
			test.log(LogStatus.PASS, "New Tax Exempt Reason form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Tax Exempt Reason form loaded Failed");
		}

		Thread.sleep(3000);
		
		//Clear the Reason field
		driver.findElement(By.name(excel.getData(5, 3, 3))).clear();
		//Enter the Name
		driver.findElement(By.name(excel.getData(5, 3, 3))).sendKeys(Utility.getProperty("TaxExemptReason"));
		Thread.sleep(2000); 
			
		//Click the Cancel button
		driver.findElement(By.xpath(excel.getData(5, 8, 1))).click();
		Thread.sleep(2000);
		//Check whether the new TaxExemptReason canceled or not
		if(driver.findElement(By.id(excel.getData(5, 3, 2))).isDisplayed())
		{
			test.log(LogStatus.PASS, "New Tax Exempt Reason Canceled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Tax Exempt Reason Canceled Failed");
		}

		Thread.sleep(5000);
	}
		
	@Test(enabled=false,priority=88)
	public void Reasons_TaxExempt_method_verifyActive_InActiveButton(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 7, 1))).clear();
		
		Thread.sleep(5000);
		//Click Active or In Active Button
		driver.findElement(By.xpath(excel.getData(5, 16, 1))).click();

//		Thread.sleep(10000);
		//Check the New Display Group form is loaded or not
//		if(driver.findElement(By.cssSelector(excel.getData(5, 39, 4))).isDisplayed())
//		{
//			test.log(LogStatus.PASS, "Deleted groups are here, we are in InActive Page");
//			Thread.sleep(10000);
//			//Click Active or In Active Button
//			//"//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[1]/div[1]/input"
//			driver.findElement(By.xpath(excel.getData(5, 16, 1))).click();
//
//		}
//		else if(driver.findElement(By.cssSelector(excel.getData(5, 50, 4))).isDisplayed())
//		{
//			test.log(LogStatus.FAIL, "Deleted groups are not here, we are in Active Page");
//		}
		Thread.sleep(5000);
	}

}
