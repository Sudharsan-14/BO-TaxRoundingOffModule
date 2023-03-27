package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
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

public class Settings_Denomination {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_Denomination");
	
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
		{		Browser_Account_Level_User a = new Browser_Account_Level_User();
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
			Denominations_Setting_Method_Pageopen(driver);
			Denominations_Setting_Method_refresh(driver);
			Denominations_Setting_Method_pagination(driver);
			Denominations_Setting_Method_add(driver);
			Denominations_Setting_Method_delete(driver);
			Denominations_Setting_Method_close_Button(driver);
			Thread.sleep(1500);
		}
	
	@Test(priority=3, enabled=false)
	public void watchTutorial(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(2000);
		//Click the Watch Tutorial Option
		driver.findElement(By.xpath(excel.getData(3, 47, 1))).click();
		WebElement iframe = driver.findElement(By.xpath(excel.getData(3, 48, 1)));
		driver.switchTo().frame(iframe);
		Thread.sleep(3500);
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 49, 1))).isDisplayed()&&driver.findElement(By.xpath("//button/div[.='Share']")).isDisplayed())
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
		driver.findElement(By.xpath(excel.getData(3, 50, 1))).click();wb.close();
	}
	
	@Test(enabled=false,priority=10)
	public void Denominations_Setting_Method_Pageopen(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(3000);
		//Enter the URl
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"denominations");
		
		Thread.sleep(3000);
		try
		{
		//Check weather the denomination page is loaded or not
		if(driver.findElement(By.xpath(excel.getData(3, 361, 1))).getText().equalsIgnoreCase("Denominations"))
		{
			test.log(LogStatus.PASS, "Denomination page loaded successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Denomination page loaded fail");
		
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

		Thread.sleep(3000);wb.close();

	}	
	
	@Test(enabled=false,priority=11)
	public void Denominations_Setting_Method_refresh(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(3000);
		//Click the refresh button
		driver.findElement(By.xpath(excel.getData(3, 133, 1))).click();

		
		//Check weather the page is refreshed or not
		if(driver.findElement(By.xpath(excel.getData(3, 362, 1))).getText().equalsIgnoreCase("Denomination"))
		{
			test.log(LogStatus.PASS, "Denominations Page refreshed successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Denominations Page refreshed successfully");wb.close();
		}
		
	}
	
	@Test(enabled=false,priority=12)
	public void Denominations_Setting_Method_pagination(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
		
		//Click the Pagination option as 10
		driver.findElement(By.xpath(excel.getData(3, 1806, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Denominations for Denomination");
		//Create the web element for denomination Table
		List<WebElement> results = driver.findElements(By.xpath(excel.getData(3, 367, 1)));
		for (WebElement result : results){						
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Denomination");
		}
		Thread.sleep(3000);
		
		//Click the Pagination option as 15
		driver.findElement(By.xpath(excel.getData(3, 1808, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Denominations for Denomination");
		//Create the web element for denomination Table
		List<WebElement> results1 = driver.findElements(By.xpath(excel.getData(3, 367, 1)));
		for (WebElement result : results1){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Denomination");
		}
		Thread.sleep(3000);
		
		//Click the Pagination option as 20
		driver.findElement(By.xpath(excel.getData(3, 1809, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for Denominations for Denomination");
		//Create the web element for denomination Table
		List<WebElement> results2 = driver.findElements(By.xpath(excel.getData(3, 367, 1)));
		for (WebElement result : results2){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Denomination");
		}
		Thread.sleep(3000);
		
		//Click the Pagination option as 5
		driver.findElement(By.xpath(excel.getData(3, 1810, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Denominations for Denomination");
		//Create the web element for denomination Table
		List<WebElement> results3 = driver.findElements(By.xpath(excel.getData(3, 367, 1)));
		for (WebElement result : results3){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Denomination");
		}
		Thread.sleep(3000);wb.close();
	}
	
	@Test(enabled=false,priority=13)
	public void Denominations_Setting_Method_add(WebDriver driver) throws Exception
	{
       File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Click the Add denomination button
		driver.findElement(By.xpath(excel.getData(3, 370, 1))).click();
		Thread.sleep(2000);
		
		//Check weather the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(3, 368, 1))).getText().equalsIgnoreCase("New Denomination"))
		{
			test.log(LogStatus.PASS, "New Denomination form or page loaded successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Denomination form or page loaded fail");
		}
		
		Thread.sleep(2000);
		//Clear the Display name field
		driver.findElement(By.xpath(excel.getData(3, 542, 1))).clear();
		//Enter the name
		driver.findElement(By.xpath(excel.getData(3, 542, 1))).sendKeys("New_Denon");
		
		Thread.sleep(2000);
		//Clear the amount field
		driver.findElement(By.xpath(excel.getData(3, 543, 1))).clear();
		//Enter the amount
		driver.findElement(By.xpath(excel.getData(3, 543, 1))).sendKeys("135");
		
		Thread.sleep(3000);
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(3, 93, 1))).click();
		Thread.sleep(4000);
		
		//Check weather the new denomination form saved or not
		WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,60);
		
		if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Denomination saved successfully"))
		{
			test.log(LogStatus.PASS, "New Denomination saved successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Denomination saved fail");wb.close();
		}
	}
	
	@Test(enabled=false,priority=14)
	public void Denominations_Setting_Method_delete(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(2000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
		//enter the keyword
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys("New_Denon");
		
		//Click the Delete button
		driver.findElement(By.xpath(excel.getData(3, 544, 1))).click();
		
		//Click the Yes button in the popup
		Thread.sleep(2000);
		driver.findElement(By.xpath(excel.getData(3, 545, 1))).click();
		
		Thread.sleep(3000);
		//Check the denomination deleted or not
		WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,60);
		
		if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Denomination Deleted successfully"))
		{
			test.log(LogStatus.PASS, "New Denomination is deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Denomination is deleted Failed");
		}
		Thread.sleep(3000);wb.close();
		
		
	}
		
	@Test(enabled=false,priority=15)
	public void Denominations_Setting_Method_close_Button(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(2000);
		//Click the Add denomination button
		driver.findElement(By.xpath(excel.getData(3, 370, 1))).click();
		Thread.sleep(2000);
		
		//Check weather the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(3, 368, 1))).getText().equalsIgnoreCase("New Denomination"))
		{
			test.log(LogStatus.PASS, "New Denomination form or page loaded successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Denomination form or page loaded fail");
		}
		
		Thread.sleep(2000);
		//Clear the Display name field
		driver.findElement(By.xpath(excel.getData(3, 542, 1))).clear();
		//Enter the name
		driver.findElement(By.xpath(excel.getData(3, 542, 1))).sendKeys("New_Denon");
		
		Thread.sleep(2000);
		//Clear the amount field
		driver.findElement(By.xpath(excel.getData(3, 543, 1))).clear();
		//Enter the amount
		driver.findElement(By.xpath(excel.getData(3, 543, 1))).sendKeys("135");
		
		Thread.sleep(1000);
		//Click the Close button
		driver.findElement(By.xpath(excel.getData(3, 145, 1))).click();
		Thread.sleep(2000);
		
		//Check weather the new denomination form closed or not
		if(driver.findElement(By.xpath(excel.getData(3, 370, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Denomination Closed successfully for Daily");
		}
		else
		{ 
			test.log(LogStatus.FAIL, "Denomination Closed fail");
		}
		Thread.sleep(3000);wb.close();
	}
}
