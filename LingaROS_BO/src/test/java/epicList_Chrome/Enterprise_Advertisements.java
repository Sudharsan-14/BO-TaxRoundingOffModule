package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

public class Enterprise_Advertisements {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Enterprise_Advertisement");
	
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
			Advertisement_page(driver);
			Advertisement_Refreshpage(driver);
			Advertisement_Setting_Method_pagination(driver);
			Advertisement_Newcreationpage(driver);
			Advertisement_New_Always(driver);
			Advertisement_Close(driver);
			Advertisement_Edit_Daysofweek(driver);
			Advertisement_delete(driver);
			Advertisement_deActive(driver);
			Advertisement_AS_DaysOfMonth(driver);
			Advertisement_AS_DateRange(driver);
			Advertisement_AS_Specific_date(driver);
			Advertisement_AS_Startdatetime_enddatetime(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=28)
		public void Advertisement_page(WebDriver driver) throws Exception
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
			WebElement element1 = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[12]/a/span"));
			//Scroll the page till the Reason option present
			je1.executeScript("arguments[0].scrollIntoView(true);",element1); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
		    //Click the Advertisement Option		
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[12]/a/span")).click();
			
			*/
			//Enter the URl
			Thread.sleep(3000);
			driver.get(Utility.getProperty("Enterprise_Base_URL")+"advertisements");
			
			Thread.sleep(5000);
			try
			{
			//Check Advertisement page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1733, 1))).getText().equalsIgnoreCase("Advertisement"))

			{
				test.log(LogStatus.PASS, "Advertisement page loaded Successfully");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Advertisement page loaded Failed");
				
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
		
		@Test(enabled=false,priority=29)
		public void Advertisement_Refreshpage(WebDriver driver) throws Exception
		{
		
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));


			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(3, 1734, 1))).click();
			Thread.sleep(3000);
			
			//Check weather the page is refreshed or not
			if(driver.findElement(By.xpath(excel.getData(3, 1733, 1))).getText().equalsIgnoreCase("Advertisement"))

			{
				test.log(LogStatus.PASS, "Advertisement Page refreshed successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Advertisement Page refreshed successfully");
			}
			wb.close();
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=30)
		public void Advertisement_Setting_Method_pagination(WebDriver driver) throws Exception
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
			Thread.sleep(3000);
			
			//Click the Pagination option as 10
			driver.findElement(By.xpath(excel.getData(3, 1735, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Advertisement");
			//Create the web element for Advertisement Table
			List<WebElement> results = driver.findElements(By.xpath(excel.getData(3, 1736, 1)));
			for (WebElement result : results){						
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			} 
			Thread.sleep(3000);
			
			//Click the Pagination option as 15
			driver.findElement(By.xpath(excel.getData(3, 1742, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Advertisement");
			//Create the web element for Advertisement Table
			List<WebElement> results1 = driver.findElements(By.xpath(excel.getData(3, 1736, 1)));
			for (WebElement result : results1){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 20
			driver.findElement(By.xpath(excel.getData(3, 1743, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for Advertisement");
			//Create the web element for Advertisement Table
			List<WebElement> results2 = driver.findElements(By.xpath(excel.getData(3, 1736, 1)));
			for (WebElement result : results2){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 5
			driver.findElement(By.xpath(excel.getData(3, 1744, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Advertisement");
			//Create the web element for Advertisement Table
			List<WebElement> results3 = driver.findElements(By.xpath(excel.getData(3, 1736, 1)));
			for (WebElement result : results3){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}wb.close();
			Thread.sleep(3000);
		}
				
		@Test(enabled=false,priority=31)
		public void Advertisement_Newcreationpage(WebDriver driver) throws Exception
		{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));


			//Scroll the web page
		    for(int i=1; i <= 15; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		    	Thread.sleep(1000);
		    }
		    
					
			//Click Advertisement creation button
			driver.findElement(By.xpath(excel.getData(3, 1745, 1))).click();
		
			Thread.sleep(2000);
			if(driver.findElement(By.xpath(excel.getData(3, 1746, 1))).getText().equalsIgnoreCase("Advertisement"))
			{
				test.log(LogStatus.PASS, "New Advertisement Page refreshed successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Advertisement Page refreshed successfully");
			}
			wb.close();
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=32)
		public void Advertisement_New_Always(WebDriver driver) throws Exception
		{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));


			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			//Clear name field
			driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
			Thread.sleep(2000);
			//Enter name field
	     	driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("Advertisement_name"));
			
	     	//Click the Applicable Time Period option
	 		driver.findElement(By.xpath(excel.getData(3, 1737, 1))).click();
	 		//Enter the required Time Period
	 		driver.findElement(By.xpath(excel.getData(3, 1738, 1))).sendKeys("Always");
	 		//Press the Enter button
	 		driver.findElement(By.xpath(excel.getData(3, 1738, 1))).sendKeys(Keys.ENTER);
	 		
	 		//Click the Level  option
	 		driver.findElement(By.xpath(excel.getData(3, 1747, 1))).click();
	 		//Enter the required Level
	 		driver.findElement(By.xpath(excel.getData(3, 1748, 1))).sendKeys("Stores");
	 		//Press the Enter button
	 		driver.findElement(By.xpath(excel.getData(3, 1748, 1))).sendKeys(Keys.ENTER);
	 		
	 		//Select the Store
	 		//driver.findElement(By.xpath(excel.getData(3, 1750, 1))).click();
	 		Thread.sleep(3000);
	 		//Click remove store
	 		driver.findElement(By.xpath(excel.getData(3, 1749, 1))).click();	
	 		
	 		Thread.sleep(2000);
	 		//Select the Store
	 		driver.findElement(By.xpath(excel.getData(3, 1750, 1))).click();
	 		//Enter the required stores
	 		driver.findElement(By.xpath(excel.getData(3, 1751, 1))).sendKeys(Utility.getProperty("Advertisement_Store"));
	 		//Enter the required stores
	 		driver.findElement(By.xpath(excel.getData(3, 1751, 1))).sendKeys(Keys.ENTER);
	 	
	 		
	 		//Click the select image button
	 		//driver.findElement(By.xpath("//id[.='advertisementImage']")).click();
			//driver.findElement(By.xpath("//span[@id='denomination']")).click();
			
	 		Thread.sleep(3000);
			//Choose the required image
			driver.findElement(By.xpath(excel.getData(3, 1752, 1))).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
			
			Thread.sleep(2000);
			//Click the Remove image
			driver.findElement(By.xpath(excel.getData(3, 1753, 1))).click();
			
			Thread.sleep(2000); 
			//Choose the required image
			driver.findElement(By.id(excel.getData(3, 1754, 2))).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
			Thread.sleep(5000);
			     //Click the Save button
					driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
					
					WebElement Ad=driver.findElement(By.xpath(excel.getData(3, 576, 1)));
					WebDriverWait wait=new WebDriverWait(driver,120);
				
					if(wait.until(ExpectedConditions.visibilityOf(Ad)).getText().equalsIgnoreCase("Advertisement saved successfully"))
					{
						test.log(LogStatus.PASS, "New Advertisement saved successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "New Advertisement saved failed");
					}
					wb.close();
					Thread.sleep(3000);
		}

		@Test(enabled=false,priority=33)
		public void Advertisement_Close(WebDriver driver) throws Exception
		{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));


			Thread.sleep(3000);
			//Click Advertisement creation button
			driver.findElement(By.xpath(excel.getData(3, 1745, 1))).click();
			
			Thread.sleep(2000);
			if(driver.findElement(By.xpath(excel.getData(3, 1746, 1))).getText().equalsIgnoreCase("Advertisement"))
			{
				test.log(LogStatus.PASS, "New Advertisement Page refreshed successfully for close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Advertisement Page refreshed successfully for close button");
			}
		
			//Clear name field
			driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
			Thread.sleep(2000);
			//Enter name field
	     	driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("Advertisement_name"));
			
	     	//Click the Applicable Time Period option
	 		driver.findElement(By.xpath(excel.getData(3, 1737, 1))).click();
	 		//Enter the required Time Period
	 		driver.findElement(By.xpath(excel.getData(3, 1738, 1))).sendKeys("Always");
	 		//Press the Enter button
	 		driver.findElement(By.xpath(excel.getData(3, 1738, 1))).sendKeys(Keys.ENTER);
	 		
	 		//Click the Level  option
	 		driver.findElement(By.xpath(excel.getData(3, 1747, 1))).click();
	 		//Enter the required Level
	 		driver.findElement(By.xpath(excel.getData(3, 1748, 1))).sendKeys("Stores");
	 		//Press the Enter button
	 		driver.findElement(By.xpath(excel.getData(3, 1748, 1))).sendKeys(Keys.ENTER);
	 		
	 		//Select the Store
	 		//driver.findElement(By.xpath(excel.getData(3, 1750, 1))).click();
	 		Thread.sleep(3000);
	 		//Click remove store
	 		driver.findElement(By.xpath(excel.getData(3, 1749, 1))).click();	
	 		
	 		Thread.sleep(2000);
	 		//Select the Store
	 		driver.findElement(By.xpath(excel.getData(3, 1750, 1))).click();
	 		//Enter the required stores
	 		driver.findElement(By.xpath(excel.getData(3, 1751, 1))).sendKeys(Utility.getProperty("Advertisement_Store"));
	 		//Enter the required stores
	 		driver.findElement(By.xpath(excel.getData(3, 1751, 1))).sendKeys(Keys.ENTER);
	 	
	 		
	 		//Click the select image button
	 		//driver.findElement(By.xpath("//id[.='advertisementImage']")).click();
			//driver.findElement(By.xpath("//span[@id='denomination']")).click();
			
	 		Thread.sleep(3000);
			//Choose the required image
			driver.findElement(By.id(excel.getData(3, 1754, 2))).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
			
			Thread.sleep(2000);
			//Click the Remove image
			driver.findElement(By.xpath(excel.getData(3, 1753, 1))).click();
			
			Thread.sleep(2000);
			//Choose the required image
			driver.findElement(By.id(excel.getData(3, 1754, 2))).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
			Thread.sleep(3000);
			     //Click the Close  button
			driver.findElement(By.xpath(excel.getData(3, 45, 1))).click();
			Thread.sleep(5000);
			//Check Advertisement page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1733, 1))).getText().equalsIgnoreCase("Advertisement"))

			{
				test.log(LogStatus.PASS, "Advertisement page loaded Successfully for close button action");
			}
			else
			{
				test.log(LogStatus.FAIL, "Advertisement page loaded Failed for close button action");
			}
			wb.close();
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=34)
		public void Advertisement_Edit_Daysofweek(WebDriver driver) throws Exception
		{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));


			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Advertisement_name"));
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
			Thread.sleep(3000);
			
			
			//Clear name field
			driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
			Thread.sleep(2000);
			//Enter name field
		 	driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("Advertisement_name")+"1");
			
			//Click the Applicable Time Period Option
			driver.findElement(By.xpath(excel.getData(3, 1737, 1))).click();
			//Enter the required Time Period
			driver.findElement(By.xpath(excel.getData(3, 1738, 1))).sendKeys("Days of Week");
			//Press Enter Key
			driver.findElement(By.xpath(excel.getData(3, 1738, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(5000);
			//Click the Days of a week Option
			driver.findElement(By.xpath(excel.getData(3, 1756, 1))).click();
			//Enter the required Day
			driver.findElement(By.xpath(excel.getData(3, 1757, 1))).sendKeys("WEDNESDAY");
			//Press Enter Key
			driver.findElement(By.xpath(excel.getData(3, 1757, 1))).sendKeys(Keys.ENTER);
			
			//Enable or Disable the Restriction Time
			driver.findElement(By.xpath(excel.getData(3, 1755, 1))).click();

			Thread.sleep(1000);
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath(excel.getData(3, 1758, 1))).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath(excel.getData(3, 1758, 1))).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath(excel.getData(3, 1758, 1))).click();
			}
			
			Thread.sleep(3000);
			//Click the Level  option
			driver.findElement(By.xpath(excel.getData(3, 1747, 1))).click();
			//Enter the required Level
			driver.findElement(By.xpath(excel.getData(3, 1748, 1))).sendKeys("Stores");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1748, 1))).sendKeys(Keys.ENTER);
			
			//Select the Store
			//driver.findElement(By.xpath(excel.getData(3, 1750, 1))).click();
			Thread.sleep(3000);
			//Click remove store
			driver.findElement(By.xpath(excel.getData(3, 1759, 1))).click();	
			
			Thread.sleep(2000);
			//Select the Store
			driver.findElement(By.xpath(excel.getData(3, 1760, 1))).click();
			//Enter the required stores
			driver.findElement(By.xpath(excel.getData(3, 1761, 1))).sendKeys(Utility.getProperty("Advertisement_Store"));
			//Enter the required stores
			driver.findElement(By.xpath(excel.getData(3, 1761, 1))).sendKeys(Keys.ENTER);
		
			
			//Click the select image button
			//driver.findElement(By.xpath("//id[.='advertisementImage']")).click();
			//driver.findElement(By.xpath("//span[@id='denomination']")).click();
			
			Thread.sleep(3000);
			//Choose the required image
			driver.findElement(By.id(excel.getData(3, 1754, 2))).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
			
			Thread.sleep(2000);
			//Click the Remove image
			driver.findElement(By.xpath(excel.getData(3, 1753, 1))).click();
			
			Thread.sleep(2000);
			//Choose the required image
			driver.findElement(By.id(excel.getData(3, 1754, 2))).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
			Thread.sleep(4000);
			     //Click the Save button
					driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
					
					Thread.sleep(5000);
					if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Advertisement Updated Successfully"))
					{
						test.log(LogStatus.PASS, "Advertisement Updated Successfully for Days of week");
					}
					else
					{
						test.log(LogStatus.FAIL, "Advertisement Updated failed for Days of week");
					}
					wb.close();
					Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=35)
		public void Advertisement_delete(WebDriver driver) throws Exception
		{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Advertisement_name")+"1");
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).click();
			Thread.sleep(3000);
			//Click the yes button
			driver.findElement(By.cssSelector(excel.getData(3, 175, 4))).click();
			Thread.sleep(3000);
			//Check the menu item deleted or not 
			if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Advertisement Inactivated Successfully"))
			{
				test.log(LogStatus.PASS, "Advertisement deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Advertisement deleted Successfully Failed");
			}
			wb.close();
			Thread.sleep(5000);
		}
			
		@Test(enabled=false,priority=36)
		public void Advertisement_deActive(WebDriver driver) throws Exception
		{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			
			//Click the DeActive button
			driver.findElement(By.xpath(excel.getData(3, 1762, 1))).click();
			
			Thread.sleep(3000);
			//Click the success button
			driver.findElement(By.xpath(excel.getData(3, 1763, 1))).click();
			
			
			Thread.sleep(3000);
			//Click the yes button
			driver.findElement(By.cssSelector(excel.getData(3, 175, 4))).click();
			
			Thread.sleep(3000);
			//Check the menu item activated or not
			if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Advertisement Activated Successfully"))
			{
				test.log(LogStatus.PASS, "Advertisement Activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Advertisement Activated Successfully Failed");
			}
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			wb.close();
			Thread.sleep(5000);
			
		}
			
		@Test(enabled=false,priority=37)
		public void Advertisement_AS_DaysOfMonth(WebDriver driver) throws Exception
		{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));


			//Clear the search field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				//Enter the required keyword
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Advertisement_name")+"1");
				//Click the Edit icon
				driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
				Thread.sleep(3000);
				
				
				//Clear name field
				driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
				Thread.sleep(2000);
				//Enter name field
			 	driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("Advertisement_name")+"2");

				//Click the Applicable Time Period Option
				driver.findElement(By.xpath(excel.getData(3, 1737, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(3, 1738, 1))).sendKeys("Days of Month");
				//Press Enter Key
				driver.findElement(By.xpath(excel.getData(3, 1738, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(5000);
				//Click the Required Date from the Days of a Month
				driver.findElement(By.xpath(excel.getData(3, 1764, 1))).click();

				//Enable or Disable the Restriction Time
				driver.findElement(By.xpath(excel.getData(3, 1755, 1))).click();
				//Click the Months OIption
				driver.findElement(By.xpath(excel.getData(3, 1765, 1))).click();
				//Enter the Required Month
				driver.findElement(By.xpath(excel.getData(3, 1766, 1))).sendKeys("MAY");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 1766, 1))).sendKeys(Keys.ENTER);
				
				//Enable or Disable the Restriction Time
				driver.findElement(By.xpath(excel.getData(3, 1767, 1))).click();

				Thread.sleep(1000);
				//Check it is AM or PM in the Start Time Option
				if(driver.findElement(By.xpath(excel.getData(3, 1768, 1))).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath(excel.getData(3, 1768, 1))).click();
				}
				else
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the Start Time
					driver.findElement(By.xpath(excel.getData(3, 1768, 1))).click();
				}
				
				//Click the select image button
				//driver.findElement(By.xpath("//id[.='advertisementImage']")).click();
				//driver.findElement(By.xpath("//span[@id='denomination']")).click();
				
				Thread.sleep(3000);
				//Choose the required image
				driver.findElement(By.id(excel.getData(3, 1754, 2))).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				
				Thread.sleep(2000);
				//Click the Remove image
				driver.findElement(By.xpath(excel.getData(3, 1753, 1))).click();
				
				Thread.sleep(2000);
				//Choose the required image
				driver.findElement(By.id(excel.getData(3, 1754, 2))).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				Thread.sleep(4000);
				     //Click the Save button
						driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
						
						Thread.sleep(5000);
						if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Advertisement Updated Successfully"))
						{
							test.log(LogStatus.PASS, "Advertisement Updated Successfully for Days of MONTH");
						}
						else
						{
							test.log(LogStatus.FAIL, "Advertisement Updated failed for Days of MONTH");
						}
						wb.close();
						Thread.sleep(3000);
			}
			
		@Test(enabled=false,priority=38)
		public void Advertisement_AS_DateRange(WebDriver driver) throws Exception
		{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));


			//Clear the search field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				//Enter the required keyword
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Advertisement_name")+"2");
				//Click the Edit icon
				driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
				Thread.sleep(3000);
				
				
				//Clear name field
				driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
				Thread.sleep(2000);
				//Enter name field
			 	driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("Advertisement_name")+"3");
				
			 	//Click the Applicable Time Period Option
				driver.findElement(By.xpath(excel.getData(3, 1737, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(3, 1738, 1))).sendKeys("Date Range");
				//Press Enter Key
				driver.findElement(By.xpath(excel.getData(3, 1738, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(5000);
				//Clear the From Date Field
				driver.findElement(By.name(excel.getData(3, 123, 3))).clear();
				//Enter the From Date
				driver.findElement(By.name(excel.getData(3, 123, 3))).sendKeys("16-May-2020");
				//Clear the To Date Field
				driver.findElement(By.name(excel.getData(3, 124, 3))).clear();
				//Enter the To Date
				driver.findElement(By.name(excel.getData(3, 124, 3))).sendKeys("20-May-2020");
			
			
				Thread.sleep(3000);
				//Enable or Disable Restriction days
				driver.findElement(By.xpath(excel.getData(3, 1739, 1))).click();
				//Click the DAys of a week
				driver.findElement(By.xpath(excel.getData(3, 1740, 1))).click();
				//Enter the Required Day
				driver.findElement(By.xpath(excel.getData(3, 1741, 1))).sendKeys("FRIDAY");
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(3, 1741, 1))).sendKeys(Keys.ENTER);
				
				//Enable or Enable the Restriction Time
				driver.findElement(By.xpath(excel.getData(3, 1769, 1))).click();

				Thread.sleep(1000);
				//Check it is AM or PM in the Start Time Option
				if(driver.findElement(By.xpath(excel.getData(3, 1770, 1))).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath(excel.getData(3, 1770, 1))).click();
				}
				else
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the Start Time
					driver.findElement(By.xpath(excel.getData(3, 1770, 1))).click();
				}
				
				
				//Click the select image button
				//driver.findElement(By.xpath("//id[.='advertisementImage']")).click();
				//driver.findElement(By.xpath("//span[@id='denomination']")).click();
				
				Thread.sleep(3000);
				//Choose the required image
				driver.findElement(By.id(excel.getData(3, 1754, 2))).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				
				Thread.sleep(2000);
				//Click the Remove image
				driver.findElement(By.xpath(excel.getData(3, 1753, 1))).click();
				
				Thread.sleep(2000);
				//Choose the required image
				driver.findElement(By.id(excel.getData(3, 1754, 2))).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				Thread.sleep(4000);
				     //Click the Save button
						driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
						
						Thread.sleep(5000);
						if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Advertisement Updated Successfully"))
						{
							test.log(LogStatus.PASS, "Advertisement Updated Successfully for DateRange");
						}
						else
						{
							test.log(LogStatus.FAIL, "Advertisement Updated failed for DateRange");
						}
						wb.close();
						Thread.sleep(3000);
			}
		
		@Test(enabled=false,priority=39)
		public void Advertisement_AS_Specific_date(WebDriver driver) throws Exception
		{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));


			//Clear the search field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				//Enter the required keyword
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Advertisement_name")+"3");
				//Click the Edit icon
				driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
				Thread.sleep(3000);
				
				
				//Clear name field
				driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
				Thread.sleep(2000);
				//Enter name field
			 	driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("Advertisement_name")+"4");
				
				//Click the Applicable Time Period Option
				driver.findElement(By.xpath(excel.getData(3, 1737, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(3, 1738, 1))).sendKeys("Specific date");
				//Press Enter Key
				driver.findElement(By.xpath(excel.getData(3, 1738, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(5000);
				//Clear the Date Field
				driver.findElement(By.name(excel.getData(3, 129, 3))).clear();
				//Enter the Date
				driver.findElement(By.name(excel.getData(3, 129, 3))).sendKeys("16-May-2020");

				//Enable or Disable the Restriction Time
				driver.findElement(By.xpath(excel.getData(3, 1755, 1))).click();

				Thread.sleep(1000);
				//Check it is AM or PM in the Start Time Option
				if(driver.findElement(By.xpath(excel.getData(3, 1758, 1))).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath(excel.getData(3, 1758, 1))).click();
				}
				else
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the Start Time
					driver.findElement(By.xpath(excel.getData(3, 1758, 1))).click();
				}
				//Click the select image button
				//driver.findElement(By.xpath("//id[.='advertisementImage']")).click();
				//driver.findElement(By.xpath("//span[@id='denomination']")).click();
				
				Thread.sleep(3000);
				//Choose the required image
				driver.findElement(By.id(excel.getData(3, 1754, 2))).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				
				Thread.sleep(2000);
				//Click the Remove image
				driver.findElement(By.xpath(excel.getData(3, 1753, 1))).click();
				
				Thread.sleep(2000);
				//Choose the required image
				driver.findElement(By.id(excel.getData(3, 1754, 2))).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				Thread.sleep(4000);
				     //Click the Save button
						driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
						
						Thread.sleep(5000);
						if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Advertisement Updated Successfully"))
						{
							test.log(LogStatus.PASS, "Advertisement Updated Successfully for Specific date");
						}
						else
						{
							test.log(LogStatus.FAIL, "Advertisement Updated failed for Specific date");
						}
						wb.close();
						Thread.sleep(3000);
			}
			
		@Test(enabled=false,priority=40)
		public void Advertisement_AS_Startdatetime_enddatetime(WebDriver driver) throws Exception
		{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));


			//Clear the search field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				//Enter the required keyword
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Advertisement_name")+"4");
				//Click the Edit icon
				driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
				Thread.sleep(3000);
				
				
				//Clear name field
				driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
				Thread.sleep(2000);
				//Enter name field
			 	driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("Advertisement_name")+"5");
				
				//Click the Applicable Time Period Option
				driver.findElement(By.xpath(excel.getData(3, 1737, 1))).click();
				//Enter the required Time Period
				driver.findElement(By.xpath(excel.getData(3, 1738, 1))).sendKeys("Start date time & end date time");
				//Press Enter Key
				driver.findElement(By.xpath(excel.getData(3, 1738, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(5000);
				//Clear the From Date Field
				driver.findElement(By.name(excel.getData(3, 123, 3))).clear();
				//Enter the From Date
				driver.findElement(By.name(excel.getData(3, 123, 3))).sendKeys("16-May-2020");
				//Clear the To Date Field
				driver.findElement(By.name(excel.getData(3, 124, 3))).clear();
				//Enter the To Date
				driver.findElement(By.name(excel.getData(3, 124, 3))).sendKeys("20-May-2020");
				
				//Enable or Disable the Restriction days option
				driver.findElement(By.xpath(excel.getData(3, 1739, 1))).click();
				
				//Click the Days of a Week Function
				driver.findElement(By.xpath(excel.getData(3, 1740, 1))).click();
				//Enter the Required day
				driver.findElement(By.xpath(excel.getData(3, 1741, 1))).sendKeys("FRIDAY");
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(3, 1741, 1))).sendKeys(Keys.ENTER);

				Thread.sleep(2000);
				//Check it is AM or PM in the Start Time Option
				if(driver.findElement(By.xpath(excel.getData(3, 1768, 1))).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath(excel.getData(3, 1768, 1))).click();
				}
				else
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the Start Time
					driver.findElement(By.xpath(excel.getData(3, 1768, 1))).click();
				}
				
				
				//Click the select image button
				//driver.findElement(By.xpath("//id[.='advertisementImage']")).click();
				//driver.findElement(By.xpath("//span[@id='denomination']")).click();
				
				Thread.sleep(3000);
				//Choose the required image
				driver.findElement(By.id(excel.getData(3, 1754, 2))).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				
				Thread.sleep(2000);
				//Click the Remove image
				driver.findElement(By.xpath(excel.getData(3, 1753, 1))).click();
				
				Thread.sleep(2000);
				//Choose the required image
				driver.findElement(By.id(excel.getData(3, 1754, 2))).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				Thread.sleep(4000);
				     //Click the Save button
						driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
						
						Thread.sleep(5000);
						if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Advertisement Updated Successfully"))
						{
							test.log(LogStatus.PASS, "Advertisement Updated Successfully for Start date time & end date time");
						}
						else
						{
							test.log(LogStatus.FAIL, "Advertisement Updated failed for Start date time & end date time");
						}
						wb.close();
						Thread.sleep(3000);
			}

}
