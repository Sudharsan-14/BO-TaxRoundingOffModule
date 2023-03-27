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

public class Setting_EMV_Setting {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Setting_EMV_Setting");
	
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
			EMV_Settings_Method_openpage(driver);
			EMV_Settings_Method_refresh(driver);
			EMV_Settings_Method_pagination(driver);
			EMV_Settings_Method_add(driver);
			EMV_Settings_Method_edit(driver);
			EMV_Settings_Method_delete(driver);
			EMV_Settings_Method_close_Button(driver);
			Thread.sleep(1500);
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

	@Test(enabled=false,priority=16)
	public void EMV_Settings_Method_openpage(WebDriver driver) throws Exception
		{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			//Thread.sleep(5000);
			//Enter the URl 
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"emvSettings");
			
			Thread.sleep(3000);
			try
			{
			//Check weather the Emv Settings page is loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1796, 1))).getText().equalsIgnoreCase("Emv Settings"))
			{
				test.log(LogStatus.PASS, "Emv Settings page loaded successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{ 
				test.log(LogStatus.FAIL, "Emv Settings page loaded fail");
			
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
		
	@Test(enabled=false,priority=17)
	public void EMV_Settings_Method_refresh(WebDriver driver) throws Exception
		{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		//Click the refresh button
			driver.findElement(By.xpath(excel.getData(2, 227, 1))).click();
			Thread.sleep(3000);
			
			//Check weather the page is refreshed or not
			if(driver.findElement(By.xpath(excel.getData(3, 1797, 1))).getText().equalsIgnoreCase("Emv Settings"))
			{
				test.log(LogStatus.PASS, "Emv Settings Page refreshed successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Emv Settings Page refreshed successfully");
			}
		wb.close();	
		}
		
	@Test(enabled=false,priority=18)
	public void EMV_Settings_Method_pagination(WebDriver driver) throws Exception
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
			Thread.sleep(1000);
			
			//Click the Pagination option as 10
			driver.findElement(By.xpath(excel.getData(3, 1798, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for EMV Settings");
			//Create the web element for Emv Settings Table
			List<WebElement> results = driver.findElements(By.xpath(excel.getData(3, 1799, 1)));
			for (WebElement result : results){						
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for EMV Settings");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 15
			driver.findElement(By.xpath(excel.getData(3, 1800, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for EMV Settings");
			//Create the web element for Emv Settings Table
			List<WebElement> results1 = driver.findElements(By.xpath(excel.getData(3, 1799, 1)));
			for (WebElement result : results1){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for EMV Settings");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 20
			driver.findElement(By.xpath(excel.getData(3, 1801, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for EMV Settings");
			//Create the web element for Emv Settings Table
			List<WebElement> results2 = driver.findElements(By.xpath(excel.getData(3, 1799, 1)));
			for (WebElement result : results2){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for EMV Settings");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 5
			driver.findElement(By.xpath(excel.getData(3, 1802, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for EMV Settings");
			//Create the web element for Emv Settings Table
			List<WebElement> results3 = driver.findElements(By.xpath(excel.getData(3, 1799, 1)));
			for (WebElement result : results3){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for EMV Settings");
			}
			wb.close();
			Thread.sleep(3000);
		}
		
	@Test(enabled=false,priority=19)
	public void EMV_Settings_Method_add(WebDriver driver) throws Exception
		{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);
			//Click the Add EMV settings button
			driver.findElement(By.xpath(excel.getData(3, 1803, 1))).click();
			Thread.sleep(2000);
			
			//Check weather the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1804, 1))).getText().equalsIgnoreCase("New EMV Settings"))
			{
				test.log(LogStatus.PASS, "New EMV Settings form or page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New EMV Settings form or page loaded fail");
			}
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys("New_EMVSR");
			
			Thread.sleep(2000);
			//Clear the IP Address field
			driver.findElement(By.name(excel.getData(3, 1805, 3))).clear();
			//Enter the IP Address
			driver.findElement(By.name(excel.getData(3, 1805, 3))).sendKeys("192.100.100.148");
			
			//Select the required type 
			Select type = new Select(driver.findElement(By.name("type")));
			type.selectByVisibleText("OPTOMANY");
			
			//Clear the port field
			driver.findElement(By.name("optomanyPort")).clear();
			//Enter the PORT number
			driver.findElement(By.name("optomanyPort")).sendKeys("3");
			
			Thread.sleep(5000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			Thread.sleep(3500);
			
			//Check weather the new denomination form saved or not
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("EMV Saved Successfully"))
			{
				test.log(LogStatus.PASS, "New EMV saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New EMV saved fail");
			}
			wb.close();
		} 
				
	@Test(enabled=false,priority=20)
	public void EMV_Settings_Method_edit(WebDriver driver) throws Exception
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
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys("New_EMVSR");
			
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys("New_EMVSR1");
			
			Thread.sleep(2000);
			//Clear the IP Address field
			driver.findElement(By.name(excel.getData(3, 1805, 3))).clear();
			//Enter the IP Address
			driver.findElement(By.name(excel.getData(3, 1805, 3))).sendKeys("192.100.100.124");
			
			Thread.sleep(4000);
			//Click the update button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			Thread.sleep(4000);
			//Check weather the new denomination form saved or not
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("EMV Updated Successfully"))
			{
				test.log(LogStatus.PASS, "New EMV Updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New EMV Updated fail");
			}
			wb.close();
			Thread.sleep(3000);
			
			
		}
				
	@Test(enabled=false,priority=21)
	public void EMV_Settings_Method_delete(WebDriver driver) throws Exception
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
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys("New_EMVSR1");
			
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).click();
			
			//Click the Yes button in the popup
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(excel.getData(3, 175, 4))).click();
			
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			//Check the denomination deleted or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("EMV Settings removed successfully"))
			{
				test.log(LogStatus.PASS, "EMV settings deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "EMV Settings deleted Failed"); 
			}
			wb.close();
			Thread.sleep(3000);
		}
		
	@Test(enabled=false,priority=22)
	public void EMV_Settings_Method_close_Button(WebDriver driver) throws Exception
		{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(5000);
			//Click the Add EMV settings button
			driver.findElement(By.xpath(excel.getData(3, 1803, 1))).click();
			Thread.sleep(2000);
			
			//Check weather the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1804, 1))).getText().equalsIgnoreCase("New EMV Settings"))
			{
				test.log(LogStatus.PASS, "New EMV Settings form or page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New EMV Settings form or page loaded fail");
			}
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys("New_EMV");
			
			Thread.sleep(2000);
			//Clear the IP Address field
			driver.findElement(By.name(excel.getData(3, 1805, 3))).clear();
			//Enter the IP Address
			driver.findElement(By.name(excel.getData(3, 1805, 3))).sendKeys("192.100.100.111");
			
			Thread.sleep(1000);
			//Click the Close button
			driver.findElement(By.xpath(excel.getData(3, 45, 1))).click();
			Thread.sleep(2000);
			
			//Check weather the new emv settings form closed or not
			if(driver.findElement(By.xpath(excel.getData(3, 1803, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "EMV Settings form Closed successfully");
			}
			else 
			{ 
				test.log(LogStatus.FAIL, "EMV Settings form Closed fail");
			}
			wb.close();
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);}
	}
	
}
