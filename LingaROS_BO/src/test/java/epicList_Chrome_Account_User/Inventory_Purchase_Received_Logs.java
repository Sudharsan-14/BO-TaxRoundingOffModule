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
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Inventory_Purchase_Received_Logs {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Received_Logs");

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
			Inventory_Purchase_Received_Logs_openpage(driver);
			Inventory_Purchase_Received_Logs_verify_All_All(driver);
			Inventory_Purchase_Received_Logs_verify_All_NotStarted(driver);
			Inventory_Purchase_Received_Logs_verify_All_Progress(driver);
			Inventory_Purchase_Received_Logs_verify_All_Finished(driver);
			Inventory_Purchase_Received_Logs_verify_All_NotFinished(driver);
			Inventory_Purchase_Received_Logs_verify_Inv_All(driver);
			Inventory_Purchase_Received_Logs_verify_Inv_NotStarted(driver);
			Inventory_Purchase_Received_Logs_verify_Inv_Progress(driver);
			Inventory_Purchase_Received_Logs_verify_Inv_Finished(driver);
			Inventory_Purchase_Received_Logs_verify_Inv_NotFinished(driver);
			Inventory_Purchase_Received_Logs_verify_SubRecipe_All(driver);
			Inventory_Purchase_Received_Logs_verify_SubRecipe_NotStarted(driver);
			Inventory_Purchase_Received_Logs_verify_SubRecipe_Progress(driver);
			Inventory_Purchase_Received_Logs_verify_SubRecipe_Finished(driver);
			Inventory_Purchase_Received_Logs_verify_SubRecipe_NotFinished(driver);
			Thread.sleep(1500);
		}
	
	@Test(enabled=false,priority=17)
	public void Inventory_Purchase_Received_Logs_openpage(WebDriver driver) throws Exception
	{
		
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
		Thread.sleep(5000);
	
		/*//Click the Inventory option
		driver.findElement(By.xpath("//span[.='Inventory']")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[contains(.,'Purchases')]"));
		//Scroll the page till the Inventory Menu Items option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Purchases Option		
		driver.findElement(By.xpath("//span[contains(.,'Purchases')]")).click();
		Thread.sleep(5000);
		
        //Click the Purchases Order Option		
		driver.findElement(By.xpath("//span[contains(.,'Received Logs')]")).click();
		Thread.sleep(5000);
		*/
		Thread.sleep(5000);
		//Get the URl
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"receivedLogs");
				
		try
		{
		//Check Storage Locations page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1243, 1))).getText().equalsIgnoreCase("Received Log"))
		{
			test.log(LogStatus.PASS, "Received Log page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Received Log page loaded Failed");
		
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
		Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=18)
	public void Inventory_Purchase_Received_Logs_verify_All_All(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	

	
		Thread.sleep(5000);
		//Select the Required Type
		Select type = new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
		type.selectByVisibleText("All");
		
		Thread.sleep(2000);
		//Click the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1979, 1))).click();
		//Select the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys("All");
		//Enter the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys(Keys.ENTER);

		
		Thread.sleep(2000);
		//Select the Time Period Option
		Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(1500);
		//Clear the Date Range from Option
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

		Thread.sleep(1500);
		//Clear the Date Range To Option
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
		
		Thread.sleep(1000);
		//Click the Search button
		driver.findElement(By.xpath(excel.getData(3, 1984, 1))).click();
		
		Thread.sleep(10000);
		try
		{
			//Check whether the Received Items are Loaded Or not
			if(driver.findElement(By.xpath(excel.getData(3, 1985, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for All(All)");
			}
		}
		catch(Exception e)//else// if(driver.findElement(By.xpath(excel.getData(3, 1986, 1))) != null)
		{
			test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for All(All)");
			test.log(LogStatus.INFO, "**********   The below is for All filter data   **********");
			List<WebElement> data = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
			data.size();
			
			for(int i=1;i<=data.size();i++)
			{
				test.log(LogStatus.INFO, "Name : "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[1]")).getText()+", Type : "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[2]")).getText());
			}
			
		}
/*		else
		{
			test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for All(All)");
		}*/
			wb.close();		
	}   
	
	@Test(enabled=false,priority=19)
	public void Inventory_Purchase_Received_Logs_verify_All_NotStarted(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	

		Thread.sleep(5000);
		//Select the Required Type
		Select type = new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
		type.selectByVisibleText("All");
		
		Thread.sleep(2000);
		//Click the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1979, 1))).click();
		//Select the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys("Not Started");
		//Enter the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys(Keys.ENTER);	
		
		Thread.sleep(2000);
		//Select the Time Period Option
		Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(1500);
		//Clear the Date Range from Option
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

		Thread.sleep(1500);
		//Clear the Date Range To Option
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
		
		Thread.sleep(1000);
		//Click the Search button
		driver.findElement(By.xpath(excel.getData(3, 1984, 1))).click();
		
		Thread.sleep(10000);
		//Check whether the Received Items are Loaded Or not
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 1985, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for Not Started(All)");
			}
		}
		catch (Exception e)
		{
		
			test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Not Started(All)");			
		
		}

				wb.close();	
	}
	
	@Test(enabled=false,priority=20)
	public void Inventory_Purchase_Received_Logs_verify_All_Progress(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	

		Thread.sleep(5000);
		//Select the Required Type
		Select type = new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
		type.selectByVisibleText("All");
		
		Thread.sleep(2000);
		//Click the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1979, 1))).click();
		//Select the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys("Progress");
		//Enter the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Select the Time Period Option
		Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(1500);
		//Clear the Date Range from Option
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

		Thread.sleep(1500);
		//Clear the Date Range To Option
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
		
		Thread.sleep(1000);
		//Click the Search button
		driver.findElement(By.xpath(excel.getData(3, 1984, 1))).click();
		
		Thread.sleep(10000);
		//Check whether the Received Items are Loaded Or not
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 1985, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for Progress(All)");
			}
		}
		catch (Exception e)
		{
		
			test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Progress(All)");			
		
		}
			wb.close();		
	}
	
	@Test(enabled=false,priority=21)
	public void Inventory_Purchase_Received_Logs_verify_All_Finished(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	

		Thread.sleep(5000);
		//Select the Required Type
		Select type = new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
		type.selectByVisibleText("All");
		
		Thread.sleep(2000);
		//Click the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1979, 1))).click();
		//Select the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys("Finished");
		//Enter the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(2000);
		//Select the Time Period Option
		Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(1500);
		//Clear the Date Range from Option
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

		Thread.sleep(1500);
		//Clear the Date Range To Option
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
		
		Thread.sleep(1000);
		//Click the Search button
		driver.findElement(By.xpath(excel.getData(3, 1984, 1))).click();
		
		Thread.sleep(10000);
		//Check whether the Received Items are Loaded Or not
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 1985, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for Finished(All)");
			}
		}
		catch (Exception e)
		{
			test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Finished(All)");			
		}

			wb.close();		
	}
	
	@Test(enabled=false,priority=22)
	public void Inventory_Purchase_Received_Logs_verify_All_NotFinished(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	

		Thread.sleep(5000);
		//Select the Required Type
		Select type = new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
		type.selectByVisibleText("All");
		
		Thread.sleep(2000);
		//Click the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1979, 1))).click();
		//Select the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys("Not Finished");
		//Enter the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(2000);
		//Select the Time Period Option
		Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(1500);
		//Clear the Date Range from Option
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

		Thread.sleep(1500);
		//Clear the Date Range To Option
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
		
		Thread.sleep(1000);
		//Click the Search button
		driver.findElement(By.xpath(excel.getData(3, 1984, 1))).click();
		
		Thread.sleep(10000);
		//Check whether the Received Items are Loaded Or not
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 1985, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for Not Finished(All)");
			}
		}
		catch (Exception e)
		{
			test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Not Finished(All)");			
		}

		wb.close();			
	}
	
	@Test(enabled=false,priority=23)
	public void Inventory_Purchase_Received_Logs_verify_Inv_All(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	

		Thread.sleep(5000);
		//Select the Required Type
		Select type = new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
		type.selectByVisibleText("Inventory Item");
		
		Thread.sleep(1000);
		//Click the Category Option
		Select catLevel=new Select(driver.findElement(By.xpath(excel.getData(3, 1987, 1))));
		catLevel.selectByVisibleText("All");
	
		
		Thread.sleep(1000);
		//Click the Inventory Item Option
		driver.findElement(By.xpath(excel.getData(3, 1988, 1))).click();
		//Enter the required Keyword
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1979, 1))).click();
		//Select the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys("All");
		//Enter the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys(Keys.ENTER);

		
		Thread.sleep(2000);
		//Select the Time Period Option
		Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(1500);
		//Clear the Date Range from Option
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

		Thread.sleep(1500);
		//Clear the Date Range To Option
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
		
		Thread.sleep(1000);
		//Click the Search button
		driver.findElement(By.xpath(excel.getData(3, 1984, 1))).click();
		
		Thread.sleep(10000);
		//Check whether the Received Items are Loaded Or not
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 1985, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for All(Inventory Item)");
			}
		}
		catch (Exception e)
		{
			test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for All(Inventory Item)");			
		}

		wb.close();
	}
	
	@Test(enabled=false,priority=24)
	public void Inventory_Purchase_Received_Logs_verify_Inv_NotStarted(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	

		Thread.sleep(5000);
		//Select the Required Type
		Select type = new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
		type.selectByVisibleText("Inventory Item");
		
		Thread.sleep(1000);
		//Click the Category Option
		Select catLevel=new Select(driver.findElement(By.xpath(excel.getData(3, 1987, 1))));
		catLevel.selectByVisibleText("All");
		
		Thread.sleep(1000);
		//Click the Inventory Item Option
		driver.findElement(By.xpath(excel.getData(3, 1988, 1))).click();
		//Enter the required Keyword
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1979, 1))).click();
		//Select the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys("Not Started");
		//Enter the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys(Keys.ENTER);

		
		Thread.sleep(2000);
		//Select the Time Period Option
		Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(1500);
		//Clear the Date Range from Option
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

		Thread.sleep(1500);
		//Clear the Date Range To Option
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
		
		Thread.sleep(1000);
		//Click the Search button
		driver.findElement(By.xpath(excel.getData(3, 1984, 1))).click();
		
		Thread.sleep(10000);
		//Check whether the Received Items are Loaded Or not
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 1985, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for Not Started(Inventory Item)");
			}
		}
		catch (Exception e)
		{
			test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Not Started(Inventory Item)");			
		}

		wb.close();
	}
	
	@Test(enabled=false,priority=25)
	public void Inventory_Purchase_Received_Logs_verify_Inv_Progress(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	

		Thread.sleep(5000);
		//Select the Required Type
		Select type = new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
		type.selectByVisibleText("Inventory Item");
		
		Thread.sleep(1000);
		//Click the Category Option
		Select catLevel=new Select(driver.findElement(By.xpath(excel.getData(3, 1987, 1))));
		catLevel.selectByVisibleText("All");
	
		Thread.sleep(1000);
		//Click the Inventory Item Option
		driver.findElement(By.xpath(excel.getData(3, 1988, 1))).click();
		//Enter the required Keyword
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1979, 1))).click();
		//Select the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys("Progress");
		//Enter the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(2000);
		//Select the Time Period Option
		Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(1500);
		//Clear the Date Range from Option
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

		Thread.sleep(1500);
		//Clear the Date Range To Option
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
		
		Thread.sleep(1000);
		//Click the Search button
		driver.findElement(By.xpath(excel.getData(3, 1984, 1))).click();
		
		Thread.sleep(10000);
		//Check whether the Received Items are Loaded Or not
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 1985, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for Progress(Inventory Item)");
			}
		}
		catch (Exception e)
		{
			test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Progress(Inventory Item)");			
		}

		wb.close();
	}
	
	@Test(enabled=false,priority=26)
	public void Inventory_Purchase_Received_Logs_verify_Inv_Finished(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	

		Thread.sleep(5000);
		//Select the Required Type
		Select type = new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
		type.selectByVisibleText("Inventory Item");
		
		Thread.sleep(1000);
		//Click the Category Option
		Select catLevel=new Select(driver.findElement(By.xpath(excel.getData(3, 1987, 1))));
		catLevel.selectByVisibleText("All");
		
		Thread.sleep(1000);
		//Click the Inventory Item Option
		driver.findElement(By.xpath(excel.getData(3, 1988, 1))).click();
		//Enter the required Keyword
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1979, 1))).click();
		//Select the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys("Finished");
		//Enter the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Select the Time Period Option
		Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(1500);
		//Clear the Date Range from Option
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

		Thread.sleep(1500);
		//Clear the Date Range To Option
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
		
		Thread.sleep(1000);
		//Click the Search button
		driver.findElement(By.xpath(excel.getData(3, 1984, 1))).click();
		
		Thread.sleep(10000);
		//Check whether the Received Items are Loaded Or not
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 1985, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for Finished(Inventory Item)");
			}
		}
		catch (Exception e)
		{
			test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Finished(Inventory Item)");			
		}

		wb.close();
	}
	
	@Test(enabled=false,priority=27)
	public void Inventory_Purchase_Received_Logs_verify_Inv_NotFinished(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	

		Thread.sleep(5000);
		//Select the Required Type
		Select type = new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
		type.selectByVisibleText("Inventory Item");
		
		Thread.sleep(1000);
		//Click the Category Option
		Select catLevel=new Select(driver.findElement(By.xpath(excel.getData(3, 1987, 1))));
		catLevel.selectByVisibleText("All");
	
		Thread.sleep(1000);
		//Click the Inventory Item Option
		driver.findElement(By.xpath(excel.getData(3, 1988, 1))).click();
		//Enter the required Keyword
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1979, 1))).click();
		//Select the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys("Not Finished");
		//Enter the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Select the Time Period Option
		Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(1500);
		//Clear the Date Range from Option
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

		Thread.sleep(1500);
		//Clear the Date Range To Option
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
		
		Thread.sleep(1000);
		//Click the Search button
		driver.findElement(By.xpath(excel.getData(3, 1984, 1))).click();
		
		Thread.sleep(10000);
		//Check whether the Received Items are Loaded Or not
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 1985, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for Not Finished(Inventory Item)");
			}
		}
		catch (Exception e)
		{
			test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Not Finished(Inventory Item)");			
		}
		wb.close();
	}
	
	@Test(enabled=false,priority=28)
	public void Inventory_Purchase_Received_Logs_verify_SubRecipe_All(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	

		Thread.sleep(5000);
		//Select the Required Type
		Select type = new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
		type.selectByVisibleText("Sub Recipe");
		
		Thread.sleep(1000);
		//Click the Sub Recipe Option
		driver.findElement(By.xpath(excel.getData(3, 1988, 1))).click();
		//Enter the required Keyword
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys(Keys.ENTER);
			
		Thread.sleep(2000);
		//Click the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1979, 1))).click();
		//Select the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys("All");
		//Enter the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		//Select the Time Period Option
		Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(1500);
		//Clear the Date Range from Option
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

		Thread.sleep(1500);
		//Clear the Date Range To Option
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
		
		Thread.sleep(1000);
		//Click the Search button
		driver.findElement(By.xpath(excel.getData(3, 1984, 1))).click();
		
		Thread.sleep(10000);
		//Check whether the Received Items are Loaded Or not
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 1985, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for All(Sub Recipe)");
			}
		}
		catch (Exception e)
		{
			test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for All(Sub Recipe)");			
		}

		wb.close();
	}
	
	@Test(enabled=false,priority=29)
	public void Inventory_Purchase_Received_Logs_verify_SubRecipe_NotStarted(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	

		Thread.sleep(5000);
		//Select the Required Type
		Select type = new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
		type.selectByVisibleText("Sub Recipe");
		
		Thread.sleep(1000);
		//Click the Sub Recipe Option
		driver.findElement(By.xpath(excel.getData(3, 1988, 1))).click();
		//Enter the required Keyword
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys(Keys.ENTER);
			
		Thread.sleep(2000);
		//Click the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1979, 1))).click();
		//Select the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys("Not Started");
		//Enter the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(2000);
		//Select the Time Period Option
		Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(1500);
		//Clear the Date Range from Option
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

		Thread.sleep(1500);
		//Clear the Date Range To Option
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
		
		Thread.sleep(1000);
		//Click the Search button
		driver.findElement(By.xpath(excel.getData(3, 1984, 1))).click();
		
		Thread.sleep(10000);
		//Check whether the Received Items are Loaded Or not
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 1985, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for Not Started(Sub Recipe)");
			}
		}
		catch (Exception e)
		{
			test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Not Started(Sub Recipe)");			
		}

		wb.close();
	}
	
	@Test(enabled=false,priority=30)
	public void Inventory_Purchase_Received_Logs_verify_SubRecipe_Progress(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	

		Thread.sleep(5000);
		//Select the Required Type	
		Select type = new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
		type.selectByVisibleText("Sub Recipe");
		
		Thread.sleep(1000);
		//Click the Sub Recipe Option
		driver.findElement(By.xpath(excel.getData(3, 1988, 1))).click();
		//Enter the required Keyword
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys(Keys.ENTER);
			
		Thread.sleep(2000);
		//Click the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1979, 1))).click();
		//Select the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys("Progress");
		//Enter the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		//Select the Time Period Option
		Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(1500);
		//Clear the Date Range from Option
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

		Thread.sleep(1500);
		//Clear the Date Range To Option
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
		
		Thread.sleep(1000);
		//Click the Search button
		driver.findElement(By.xpath(excel.getData(3, 1984, 1))).click();
		
		Thread.sleep(10000);
		//Check whether the Received Items are Loaded Or not
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 1985, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for Progress(Sub Recipe)");
			}
		}
		catch (Exception e)
		{
			test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Progress(Sub Recipe)");			
		}
		wb.close();
	}
	
	@Test(enabled=false,priority=31)
	public void Inventory_Purchase_Received_Logs_verify_SubRecipe_Finished(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	

		Thread.sleep(5000);
		//Select the Required Type	
		Select type = new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
		type.selectByVisibleText("Sub Recipe");
		
		Thread.sleep(1000);
		//Click the Sub Recipe Option
		driver.findElement(By.xpath(excel.getData(3, 1988, 1))).click();
		//Enter the required Keyword
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys(Keys.ENTER);
			
		Thread.sleep(2000);
		//Click the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1979, 1))).click();
		//Select the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys("Finished");
		//Enter the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		//Select the Time Period Option
		Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(1500);
		//Clear the Date Range from Option
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

		Thread.sleep(1500);
		//Clear the Date Range To Option
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
		
		Thread.sleep(1000);
		//Click the Search button
		driver.findElement(By.xpath(excel.getData(3, 1984, 1))).click();
		
		Thread.sleep(10000);
		//Check whether the Received Items are Loaded Or not
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 1985, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for Finished(Sub Recipe)");
			}
		}
		catch (Exception e)
		{
			test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Finished(Sub Recipe)");			
		}

		wb.close();
	}
	
	@Test(enabled=false,priority=32)
	public void Inventory_Purchase_Received_Logs_verify_SubRecipe_NotFinished(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	

		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		//Select the Required Type	
		Select type = new Select(driver.findElement(By.xpath(excel.getData(3, 1978, 1))));
		type.selectByVisibleText("Sub Recipe");
		
		Thread.sleep(1000);
		//Click the Sub Recipe Option
		driver.findElement(By.xpath(excel.getData(3, 1988, 1))).click();
		//Enter the required Keyword
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1989, 1))).sendKeys(Keys.ENTER);
			
		Thread.sleep(2000);
		//Click the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1979, 1))).click();
		//Select the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys("Not Finished");
		//Enter the Consumption Option
		driver.findElement(By.xpath(excel.getData(3, 1980, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		//Select the Time Period Option
		Select timePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1981, 1))));
		timePeriod.selectByVisibleText("Date Range");
		
		Thread.sleep(1500);
		//Clear the Date Range from Option
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_From_Date"));

		Thread.sleep(1500);
		//Clear the Date Range To Option
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getProperty("Inventory_Received_Logs_To_Date"));
		
		Thread.sleep(1000);
		//Click the Search button
		driver.findElement(By.xpath(excel.getData(3, 1984, 1))).click();
		
		Thread.sleep(10000);
		//Check whether the Received Items are Loaded Or not
		try
		{
			if(driver.findElement(By.xpath(excel.getData(3, 1985, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for Not Finished(Sub Recipe)");
			}
		}
		catch (Exception e)
		{
			test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Not Finished(Sub Recipe)");			
		}
		
		if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);//watchTutorial(driver);
		Thread.sleep(5000);}
	wb.close();
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
