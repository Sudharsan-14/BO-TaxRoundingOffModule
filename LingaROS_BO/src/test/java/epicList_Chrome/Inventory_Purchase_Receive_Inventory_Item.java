package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;


public class Inventory_Purchase_Receive_Inventory_Item {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Purchase_Receive_Inventory_Item");

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
		a.Logout(driver, test);}
	
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
			Inventory_Purchase_Receive_Inventory_Item_Openpage(driver);
			Inventory_Purchase_Receive_Inventory_Item_Update(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=13)
	public void Inventory_Purchase_Receive_Inventory_Item_Openpage(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		/*//Click the Inventory option
		driver.findElement(By.xpath("//span[.='Inventory']")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[contains(.,'Purchases')]"));
		//Scroll the page till the Inventory Items option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Inventory Items Option		
		driver.findElement(By.xpath("//span[contains(.,'Purchases')]")).click();
		 //driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[4]/ul/li[11]/a/i")).click();
		*/
		Thread.sleep(3000);
		//Get the URl
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"receivedLogs");
		
		Thread.sleep(5000);
		try
		{
		//Check Inventory Items page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1243, 1))).getText().equalsIgnoreCase("Received Log"))
		{
			test.log(LogStatus.PASS, "Purchase page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Purchase Inventory page loaded Failed");
		
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
		Thread.sleep(5000);wb.close();
		
	}

	@Test(enabled=false,priority=14)
	public void Inventory_Purchase_Receive_Inventory_Item_Update(WebDriver driver) throws Exception
	{

        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		//Click Receive Inventory
		driver.findElement(By.xpath("//uib-tab-heading[contains(.,'Receive Inventory Item')]")).click();
		
/*		Thread.sleep(2000); 
		//Clear the Barcode option
		driver.findElement(By.name("name")).clear();
		//Enter the Barcode option
	    driver.findElement(By.name("name")).sendKeys(Utility.getProperty("Receive_Barcode"));*/
	  
		Thread.sleep(5000);
	    //Click Inventory item
		driver.findElement(By.xpath(excel.getData(3, 1390, 1))).click();
		Thread.sleep(1000);
		//Click the inventory item, input field
		driver.findElement(By.xpath(excel.getData(3, 1391, 1))).click();
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1391, 1))).sendKeys(Keys.ENTER);
	   
	    
	  /*  Thread.sleep(3000);
	    //Change the quantity
	    driver.findElement(By.name(excel.getData(3, 160, 1))).clear();
	    //Enter the quantity
	    driver.findElement(By.name(excel.getData(3, 160, 1))).sendKeys("2");
	    */
	    
	    Thread.sleep(5000);
	    //Click save and continue button
	    driver.findElement(By.xpath(excel.getData(3, 1215, 1))).click();
		
	    Thread.sleep(5000);
		//Check Receive Inventory saved or not
		if(driver.findElement(By.xpath(excel.getData(3, 1184, 1))).getText().equalsIgnoreCase("purchase item saved successfully"))
		{
			test.log(LogStatus.PASS, "purchase item saved successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "purchase item saved Failed");
		}
		
		Thread.sleep(5000);
		if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);//watchTutorial(driver);
		Thread.sleep(5000);wb.close();
		}
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
}
