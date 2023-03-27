package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

public class Update_Loyalty_Settings {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Update Loyalty Settings");

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
		{		Browser a = new Browser();
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
			Loyalty_page_Setting(driver);
			Loyalty_Settingpage(driver);
			Loyalty_SettingpageDisable(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=11)
	public void Loyalty_page_Setting(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		/*//Click the My Stores option 
		driver.findElement(By.xpath("//span[.='My Stores']")).click();
		JavascriptExecutor je1 = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element1 = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[8]/a/span"));
		//Scroll the page till the Reason option present
		je1.executeScript("arguments[0].scrollIntoView(true);",element1); 
		//Wait for 30 seconds
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
	    //Click the Loyalty Option		
		driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[8]/a/span")).click();
		*/
		Thread.sleep(3000);	
		//Enter the Coursing URL
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"loyalty");
			
		Thread.sleep(5000);
		try
		{
		//Check Loyalty page opened or not
		if(driver.findElement(By.xpath(excel.getData(2, 365, 1))).getText().equalsIgnoreCase("Loyalty"))

		{
			test.log(LogStatus.PASS, "Loyalty page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Loyalty page loaded Failed");
		
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
		wb.close();
		Thread.sleep(3000);
		
	}

	@Test(enabled=false,priority=12)
	public void Loyalty_Settingpage(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);
		
		//Check whether the Loyalty Account button is enabled or not
		if(driver.findElement(By.xpath(excel.getData(2, 366, 1))).isSelected()){}
		
		else
		{
			//Enable the Loyalty Account button Option
			driver.findElement(By.xpath(excel.getData(2, 366, 1))).click();
		}
		
		
		Thread.sleep(3000);
		//Clear the Points For Customer Enrollment
		driver.findElement(By.name(excel.getData(2, 511, 3))).clear();
		//Enter the Customer Enrollment value
		driver.findElement(By.name(excel.getData(2, 511, 3))).sendKeys(Utility.getProperty("customerEnrollment_Value"));
		
		//Clear the Points for Every Visit
		driver.findElement(By.name(excel.getData(2, 512, 3))).clear();
		//Enter the Points For Every Visit
		driver.findElement(By.name(excel.getData(2, 512, 3))).sendKeys(Utility.getProperty("Loyalty_visitPrice"));
		
		Thread.sleep(2000);
		//Actions act = new Actions(driver);
		//act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 373, 1)))).click().build().perform();
		
		try
		{
			if(driver.findElement(By.xpath(excel.getData(2, 369, 1))).isDisplayed())
			{
				//Clear the Points for amount
				driver.findElement(By.xpath(excel.getData(2, 370, 1))).clear();
				//Enter the Points for amount
				driver.findElement(By.xpath(excel.getData(2, 370, 1))).sendKeys(Utility.getProperty("Loyalty_purchasePoint"));
				
				//Clear the Points for amount
				driver.findElement(By.xpath(excel.getData(2, 371, 1))).clear();
				//Enter the Points for amount
				driver.findElement(By.xpath(excel.getData(2, 371, 1))).sendKeys(Utility.getProperty("Loyalty_purchasePoint"));
				
			}
		}
		catch(Exception e)
		{
			if(driver.findElement(By.xpath(excel.getData(2, 372, 1))).isDisplayed())
			{
				//Clear the Points for the Cash Payment
				driver.findElement(By.xpath(excel.getData(2, 373, 1))).clear();
				//Enter the Points for the Cash Payment
				driver.findElement(By.xpath(excel.getData(2, 373, 1))).sendKeys(Utility.getProperty("Loyalty_purchasePoint"));
				
				//Clear the Points for the Cash Payment
				driver.findElement(By.xpath(excel.getData(2, 374, 1))).clear();
				//Enter the Points for the Cash Payment
				driver.findElement(By.xpath(excel.getData(2, 374, 1))).sendKeys(Utility.getProperty("Loyalty_purchasePoint"));
	
				//Clear the Points for the Non Cash Payment
				driver.findElement(By.xpath(excel.getData(2, 370, 1))).clear();
				//Enter the Points for the Non Cash Payment
				driver.findElement(By.xpath(excel.getData(2, 370, 1))).sendKeys(Utility.getProperty("Loyalty_purchasePoint"));
				
				//Clear the Points for the Non Cash Payment
				driver.findElement(By.xpath(excel.getData(2, 371, 1))).clear();
				//Enter the Points for the Non Cash Payment
				driver.findElement(By.xpath(excel.getData(2, 371, 1))).sendKeys(Utility.getProperty("Loyalty_purchasePoint"));
			}
		}
		
		//Click Add Menu item Based
		driver.findElement(By.xpath(excel.getData(2, 377, 1))).click();
		
		//Click the Menu Item Option
		driver.findElement(By.xpath(excel.getData(2, 378, 1))).click();
		//Press the Enter Button
		driver.findElement(By.xpath(excel.getData(2, 396, 1))).sendKeys(Keys.ENTER);
		
		//Clear the Quantity Option
		driver.findElement(By.xpath(excel.getData(2, 379, 1))).clear();
		//Enter the Quantity
		driver.findElement(By.xpath(excel.getData(2, 379, 1))).sendKeys("5");
		
		//Clear the Points
		driver.findElement(By.xpath(excel.getData(2, 380, 1))).clear();
		//Enter the Points
		driver.findElement(By.xpath(excel.getData(2, 380, 1))).sendKeys("10");
		
		
		//Click the Add Button
		driver.findElement(By.xpath(excel.getData(2, 377, 1))).click();
		
		
		//Click the Close Button of newly added option
		driver.findElement(By.xpath(excel.getData(2, 382, 1))).click();
		
		Thread.sleep(2000);
		//Click the Yes Button
		driver.findElement(By.partialLinkText(excel.getData(2, 441, 5))).click();
		
		//Click Add Category Based
		driver.findElement(By.xpath(excel.getData(2, 384, 1))).click();
		
		//Click the Category Option
		driver.findElement(By.xpath(excel.getData(2, 385, 1))).click();
		//Press the Enter Button
		driver.findElement(By.xpath(excel.getData(2, 411, 1))).sendKeys(Keys.ENTER);
		
		//Clear the Quantity Option
		driver.findElement(By.xpath(excel.getData(2, 386, 1))).clear();
		//Enter the Quantity
		driver.findElement(By.xpath(excel.getData(2, 386, 1))).sendKeys("5");
		
		//Clear the Points
		driver.findElement(By.xpath(excel.getData(2, 387, 1))).clear();
		//Enter the Points
		driver.findElement(By.xpath(excel.getData(2, 387, 1))).sendKeys("8");
		
		//Click the Add Button
		driver.findElement(By.xpath(excel.getData(2, 384, 1))).click();
		
		Thread.sleep(5000);
		//Click the Close Button of newly added option
		driver.findElement(By.xpath(excel.getData(2, 389, 1))).click();
		
		Thread.sleep(2000);
		//Click the Yes Button
		driver.findElement(By.partialLinkText(excel.getData(2, 441, 5))).click();
		
		Thread.sleep(2000);
		//Enter the Redeem points
		driver.findElement(By.xpath(excel.getData(2, 390, 1))).clear();
		//Enter the Redeem points
		driver.findElement(By.xpath(excel.getData(2, 390, 1))).sendKeys("2.00");

		Thread.sleep(2000);
		//Enter the Minimum points required for redemption option
		driver.findElement(By.xpath(excel.getData(2, 391, 1))).clear();
		//Enter the Minimum points required for redemption option
		driver.findElement(By.xpath(excel.getData(2, 391, 1))).sendKeys("2");

		
		Thread.sleep(3000);
		//Click the update button
		driver.findElement(By.xpath(excel.getData(2, 102, 1))).click();
		
		WebElement update=driver.findElement(By.xpath(excel.getData(2, 108, 1)));
		WebDriverWait wait=new WebDriverWait(driver,60);
		
		//Check Loyalty updated or not
		if(wait.until(ExpectedConditions.visibilityOf(update)).getText().equalsIgnoreCase("loyalty Saved successfully"))
		{
			test.log(LogStatus.PASS, "Loyalty updated Successfully ");
		}
		else
		{ 
			test.log(LogStatus.FAIL, "Loyalty updated Failed");
		}
		wb.close();
		Thread.sleep(3000);
		}

	@Test(enabled=false,priority=13)
	public void Loyalty_SettingpageDisable(WebDriver driver) throws Exception
	{

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);

		//Check whether the Loyalty Account button is enabled or not
		if(!driver.findElement(By.xpath(excel.getData(2, 366, 1))).isEnabled())
		{}
		else
		{
			//Enable the Loyalty Account button Option
			driver.findElement(By.xpath(excel.getData(2, 366, 1))).click();
		}

		//Click the Close Button of newly added option - Menu Item
		driver.findElement(By.xpath(excel.getData(2, 394, 1))).click();
		
		Thread.sleep(2000);
		//Click the Yes Button
		driver.findElement(By.partialLinkText(excel.getData(2, 441, 5))).click();
		
		//Click the Close Button of newly added option - Category
		driver.findElement(By.xpath(excel.getData(2, 395, 1))).click();	
		
		Thread.sleep(2000);
		//Click the Yes Button
		driver.findElement(By.partialLinkText(excel.getData(2, 441, 5))).click();
		
		Thread.sleep(3000);
		//Click the update button
		driver.findElement(By.xpath(excel.getData(2, 102, 1))).click();
		
		WebElement update=driver.findElement(By.xpath(excel.getData(2, 108, 1)));
		WebDriverWait wait=new WebDriverWait(driver,60); 
		
		//Check Loyalty updated or not
		if(wait.until(ExpectedConditions.visibilityOf(update)).getText().equalsIgnoreCase("loyalty Saved successfully"))

		{
			test.log(LogStatus.PASS, "Loyalty updated Successfully for disabled option");
		}
		else
		{
			test.log(LogStatus.FAIL, "Loyalty updated Failed for disabled option");
		}
		wb.close();
		Thread.sleep(3000);
		if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);//watchTutorial(driver);
		Thread.sleep(5000);}
	}
	@Test(priority=3, enabled=false)
	public void watchTutorial(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		//Click the Watch Tutorial Option 
		driver.findElement(By.xpath("//span[.='Watch Tutorial']")).click();
		WebElement iframe = driver.findElement(By.xpath("//div[@class='modal-content']/span/iframe"));
		driver.switchTo().frame(iframe);
		Thread.sleep(3500);
		try
		{
			if(driver.findElement(By.xpath("//button/div[.='Watch later']")).isDisplayed()&&driver.findElement(By.xpath("//button/div[.='Share']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Watch Tutorial Video Played Well");
				Thread.sleep(2500);
			}
		}
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Watch Tutorial Video not Played");
		}
		driver.switchTo().defaultContent();
		
		Thread.sleep(2000);
		//Click the Close button
		driver.findElement(By.xpath("//span[.='x' and @title='close']")).click();
	}
}
