package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
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

public class Central_Inventory_Add_Edit_Transfer_Request {
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Central_Inventory_Add_Edit_Transfer_Request");

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
			Central_Transfer_Request_openpage(driver);
			Central_Transfer_Request_Cancle(driver);
			Central_Transfer_Request_Request(driver);
			Central_Transfer_Request_QuantityNotAvailable(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=19)
	public void Central_Transfer_Request_openpage(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
		/*//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();

		Thread.sleep(3000); 
        //Click the Central Inventory Reports Option		
		driver.findElement(By.xpath("//span[.='Central Inventory']")).click();
		
		Thread.sleep(2000);
		//Click the Central WareHouse option
		driver.findElement(By.xpath("//span[contains(.,'Transfer Request')]")).click();
*/
		Thread.sleep(3000);	
		//Enter the Coursing URL
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"centralStores/transferRequest");
		
		Thread.sleep(8000);
		try
		{
		//Check Central Warehouse page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1381, 1))).getText().equalsIgnoreCase("Transfer Requests"))
		{
			test.log(LogStatus.PASS, "Central Transfer Requests page loaded Successfully");
			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Central Transfer Requests page loaded Failed");
			
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
	
	@Test(enabled=false,priority=20)
	public void Central_Transfer_Request_Cancle(WebDriver driver) throws Exception
	{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
	Thread.sleep(5000);
	//Click refresh button
	driver.findElement(By.cssSelector(excel.getData(3, 1383, 4))).click();
	Thread.sleep(3000);
	//Click Transfer Request button
	driver.findElement(By.cssSelector(excel.getData(3, 1384, 4))).click();Thread.sleep(3000);
	//Check Central Warehouse page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1382, 1))).getText().equalsIgnoreCase("New Transfer Request"))
			{
				test.log(LogStatus.PASS, "New Transfer Request page Successfully loaded ");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Transfer Request page Successfully loaded is failed");
			}
			
			Thread.sleep(2000);
		//Click From field to select store
		driver.findElement(By.xpath(excel.getData(3, 1385, 1))).click();
		
		Thread.sleep(1000);
		//Click From field to select store
		//driver.findElement(By.xpath(excel.getData(3, 1386, 1))).sendKeys(Utility.getProperty("Central_Transfer_from"));
		
		Thread.sleep(1000);
		//Click From field to select store
		driver.findElement(By.xpath(excel.getData(3, 1386, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click TO field to select store
		driver.findElement(By.xpath(excel.getData(3, 1387, 1))).click();
		
		Thread.sleep(1000);
		//Click To field to select store
		driver.findElement(By.xpath(excel.getData(3, 1388, 1))).sendKeys(Keys.ARROW_DOWN);
		
		Thread.sleep(1000);
		//Click To field to select store
		driver.findElement(By.xpath(excel.getData(3, 1388, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);
		//Click new Inventory item button
		driver.findElement(By.cssSelector(excel.getData(3, 1389, 4))).click();
		
		Thread.sleep(2000);
		//Select Inventory item 
		driver.findElement(By.xpath(excel.getData(3, 1390, 1))).click();
		//Enter the Required Inventory Item
		driver.findElement(By.xpath(excel.getData(3, 1391, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Clear the quantity value
		driver.findElement(By.xpath(excel.getData(3, 1392, 1))).clear();
		//Enter the quantity value
		driver.findElement(By.xpath(excel.getData(3, 1392, 1))).sendKeys("1");
		Thread.sleep(1000);
		//Click remove button for inventory item
		driver.findElement(By.cssSelector(excel.getData(3, 1393, 4))).click();
		Thread.sleep(2000);
		//Cancel button for inventory item
		driver.findElement(By.xpath(excel.getData(3, 130, 1))).click();
		Thread.sleep(3000);
		wb.close();
	}
	
	@Test(enabled=false,priority=21)
	public void Central_Transfer_Request_Request(WebDriver driver) throws Exception
	{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			Thread.sleep(2000);
			//Click refresh button
			driver.findElement(By.cssSelector(excel.getData(3, 1383, 4))).click();
			Thread.sleep(3000);
			//Click Transfer Request button
			driver.findElement(By.cssSelector(excel.getData(3, 1384, 4))).click();Thread.sleep(3000);
			//Check Central Warehouse page opened or not
					if(driver.findElement(By.xpath(excel.getData(3, 1382, 1))).getText().equalsIgnoreCase("New Transfer Request"))
					{
						test.log(LogStatus.PASS, "New Transfer Request page Successfully loaded ");
					}
					else
					{
						test.log(LogStatus.FAIL, "New Transfer Request page Successfully loaded is failed");
					}
					
					Thread.sleep(2000);
				//Click From field to select store
				driver.findElement(By.xpath(excel.getData(3, 1385, 1))).click();
				
				Thread.sleep(1000);
				//Click From field to select store
				//driver.findElement(By.xpath(excel.getData(3, 1386, 1))).sendKeys(Utility.getProperty("Central_Transfer_from"));
				
				Thread.sleep(1000);
				//Click From field to select store
				driver.findElement(By.xpath(excel.getData(3, 1386, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Click TO field to select store
				driver.findElement(By.xpath(excel.getData(3, 1387, 1))).click();
				
				Thread.sleep(1000);
				//Click To field to select store
				driver.findElement(By.xpath(excel.getData(3, 1388, 1))).sendKeys(Keys.ARROW_DOWN);
				
				Thread.sleep(1000);
				//Click To field to select store
				driver.findElement(By.xpath(excel.getData(3, 1388, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Click new Inventory item button
				driver.findElement(By.cssSelector(excel.getData(3, 1389, 4))).click();
				
				Thread.sleep(2000);
				//Select Inventory item 
				driver.findElement(By.xpath(excel.getData(3, 1390, 1))).click();
				//Enter the Required Inventory Item
				driver.findElement(By.xpath(excel.getData(3, 1391, 1))).sendKeys(Keys.ENTER);

				Thread.sleep(1000);
				//Clear the quantity value
				driver.findElement(By.xpath(excel.getData(3, 1392, 1))).clear();
				//Enter the quantity value
				driver.findElement(By.xpath(excel.getData(3, 1392, 1))).sendKeys("1");
				Thread.sleep(1000);
			
			//Click Request button for inventory item
			driver.findElement(By.xpath(excel.getData(3, 1394, 1))).click();
			Thread.sleep(3000);	
			//Check Central Warehouse page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Transfer request created successfully!."))
			{
				test.log(LogStatus.PASS, "Transfer request created successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Transfer request created is Failed");
			}
			Thread.sleep(5000);
			wb.close();
		}

	@Test(enabled=false,priority=22)
	public void Central_Transfer_Request_QuantityNotAvailable(WebDriver driver) throws Exception
	{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
		Thread.sleep(5000);
		//Click the yes button
		List<WebElement> min = driver.findElements(By.xpath(excel.getData(3, 1395, 1)));
		min.size();
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div/table/tbody/tr[1]/td[6]/span/a[2]/i")).click();
		Thread.sleep(2000);
		//Click that Transfer item button
		driver.findElement(By.cssSelector(excel.getData(3, 1396, 4))).click();

		Thread.sleep(1000);
		try
		{
			//Check whether the Received Items are Loaded Or not
			if(driver.findElement(By.xpath(excel.getData(3, 1787, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Requested Inventory Items / Sub Recipes not found in this store.Hence Transfer count field is disabled.");
			}
		}
		catch(Exception e)		
		{
			test.log(LogStatus.PASS, "Requested Inventory Items / Sub Recipes not found in this store.Hence Transfer count field is Enabled.");
						
			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1397, 1)));
			for(int i = 1; i <= rows.size(); i++)
			{
			 //Get the store status
			Thread.sleep(3000);
			test.log(LogStatus.PASS,"The source of the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[1]")).getText()+" store status is "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText()+ " Updated");
						
			test.log(LogStatus.PASS,"The Destination of the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText()+" store status is" +driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[4]")).getText()+ " Updated");
			}
		}
		Thread.sleep(2000);	
		wb.close();
	}
 
}
