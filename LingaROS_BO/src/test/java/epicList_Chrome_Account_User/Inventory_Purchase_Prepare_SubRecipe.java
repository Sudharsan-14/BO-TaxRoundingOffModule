package epicList_Chrome_Account_User;

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

public class Inventory_Purchase_Prepare_SubRecipe {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Purchase_Prepare_SubRecipe");

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
			Inventory_Purchase_Prepare_Subrecipe_Openpage(driver);
			Inventory_Purchase_Prepare_Subrecipe_Update(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=15)
	public void Inventory_Purchase_Prepare_Subrecipe_Openpage(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
/*		//Click the Inventory option
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
		//Get the URl
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"receivedLogs");
		Thread.sleep(5000);
		try
		{
		//Check Inventory Items page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1235, 1))).getText().equalsIgnoreCase("Received Log"))
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

	@Test(enabled=false,priority=16)
	public void Inventory_Purchase_Prepare_Subrecipe_Update(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(4000);
		//Click Prepare SubRecipe
		driver.findElement(By.xpath(excel.getData(3, 1236, 1))).click();
		
		Thread.sleep(5000);
		//Check New primary storage	page loaded successfully or not
		if(driver.findElement(By.xpath(excel.getData(3, 1668, 1))).getText().equalsIgnoreCase("Prepare SubRecipe"))
		{
			test.log(LogStatus.PASS, "Prepare SubRecipe page loaded successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Prepare SubRecipe page loaded Failed");
		}
		
		
		Thread.sleep(8000);
		//Click the SubRecipe option
		driver.findElement(By.xpath(excel.getData(3, 1237, 1))).click();
		Thread.sleep(6000);
		//Enter the SubRecipe option
	    driver.findElement(By.xpath(excel.getData(3, 1238, 1))).click();
	  //Enter the SubRecipe option
	   driver.findElement(By.xpath(excel.getData(3, 1238, 1))).sendKeys(Keys.ENTER);
	    
	   Thread.sleep(2000);
		//Clear the quantity option
		driver.findElement(By.name(excel.getData(3, 160, 3))).clear();
		Thread.sleep(2000);
		//Enter the quantity option
	    driver.findElement(By.name(excel.getData(3, 160, 3))).sendKeys("2");

	    
		//Thread.sleep(4000);
				//Click the primary storage
				//driver.findElement(By.xpath("//div[@id='psl_chosen']/a")).click();
				//Thread.sleep(2000);
				//Enter the primary storage
			   // driver.findElement(By.xpath("//div[@id='psl_chosen']/div/div/input")).sendKeys(Utility.getProperty("Inventory_Primary_Storage_Name_Prepare_SubRecipe"));
			  //Enter the primary storage
			   //driver.findElement(By.xpath("//div[@id='psl_chosen']/div/div/input")).sendKeys(Keys.ENTER);
		
	    Thread.sleep(2000);
	 //Click New primary storage
	 driver.findElement(By.xpath(excel.getData(3, 2273, 1))).click();
					
				Thread.sleep(2000);
				//Check New primary storage	page loaded successfully or not
				if(driver.findElement(By.xpath(excel.getData(3, 1274, 1))).getText().equalsIgnoreCase("New Storage"))
				{
					test.log(LogStatus.PASS, "New primary storage page loaded successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "New primary storage page loaded Failed");
				}
				
			  
				Thread.sleep(2000);
			 //Click the New primary storage
			driver.findElement(By.name(excel.getData(3, 1241, 3))).clear();
			Thread.sleep(2000);
			//Enter the New primary storage	    
			driver.findElement(By.name(excel.getData(3, 1241, 3))).sendKeys(Utility.getProperty("Inventory_Primary_Storage_Name_Prepare_SubRecipe")+"a");	  
	
			 	
			Thread.sleep(4000);
			//Click the New primary storage
				driver.findElement(By.name(excel.getData(3, 1174, 3))).clear();
				Thread.sleep(2000);
				//Enter the New primary storage	    
				driver.findElement(By.name(excel.getData(3, 1174, 3))).sendKeys("Need a place to keep");	  
			
				Thread.sleep(4000);
			//try {	//Issue here
				//Click the Save button
				driver.findElement(By.xpath(excel.getData(3, 2189, 1))).click();
				//}
		//	catch(Exception k) { 
			//	Thread.sleep(4000);
			//	driver.findElement(By.xpath(excel.getData(3, 45, 1))).click();}
						
			Thread.sleep(10000);
			//Click the Secondary storage
			driver.findElement(By.xpath(excel.getData(3, 1213, 1))).click();
			Thread.sleep(2000);
			//Enter the Secondary storage
		    driver.findElement(By.xpath(excel.getData(3, 1214, 1))).sendKeys(Utility.getProperty("Inventory_Secondary_Storage_Name"));
		  //Enter the Secondary storage
		   driver.findElement(By.xpath(excel.getData(3, 1214, 1))).sendKeys(Keys.ENTER);
		   
/*		 //Click New Secondary storage
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div[2]/div/div[2]/form/div[1]/div[3]/div[2]/a/i")).click();
				
			Thread.sleep(2000);
			//Check New Secondary storage	page loaded successfully or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div[1]/div/div[1]/h3/span")).getText().equalsIgnoreCase("New Storage"))
			{
				test.log(LogStatus.PASS, "New Secondary storage page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Secondary storage page loaded Failed");
			}
			
		 
			Thread.sleep(2000);
		 //Click the New Secondary storage
		driver.findElement(By.name("masterName")).clear();
		Thread.sleep(2000);
		//Enter the New Secondary storage	    
		driver.findElement(By.name("masterName")).sendKeys(Utility.getProperty("Inventory_Primary_Storage_Name_Prepare_SubRecipe")+"1");	  
		//Enter the New Secondary storage 	                          
		driver.findElement(By.name("masterName")).sendKeys(Keys.ENTER);
		 	
		Thread.sleep(2000);
		//Click the New Secondary storage
			driver.findElement(By.name("description")).clear();
			Thread.sleep(2000);
			//Enter the New Secondary storage	    
			driver.findElement(By.name("description")).sendKeys("Need a place to keep");	  
			//Enter the New Secondary storage 	                          
			driver.findElement(By.name("description")).sendKeys(Keys.ENTER);
			 
			Thread.sleep(3000);
			//Click the Save button
				driver.findElement(By.xpath("//button[@type='submit']")).click();
					
		
		Thread.sleep(2000);
		//Check New Secondary storage page loaded successfully or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div[1]/div/div[1]/h3/span")).getText().equalsIgnoreCase("New Storage"))
		{
			test.log(LogStatus.PASS, "New Secondary storage page loaded successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Secondary storage page loaded Failed");
		}*/

		
	 
	   
	  //Click the Save button
	 //driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div[2]/div/div[2]/form/div[2]/div/div/button")).click();
		 Thread.sleep(4000);
	 driver.findElement(By.xpath(excel.getData(3, 1215, 1))).click();
	
	  //Thread.sleep(4000);
	 	WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
	 	WebDriverWait wait=new WebDriverWait(driver,90);
	 
		//Check Receive Inventory saved or not
		if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Sub recipe prepared successfully"))
		{
			test.log(LogStatus.PASS, "Sub recipe prepared successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Sub recipe prepared Failed");
		}
		
		Thread.sleep(8000);
		
		if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);//watchTutorial(driver);
		Thread.sleep(5000);wb.close();}
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
