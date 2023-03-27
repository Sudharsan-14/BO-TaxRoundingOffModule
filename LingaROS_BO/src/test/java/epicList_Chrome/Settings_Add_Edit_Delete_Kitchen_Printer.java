package epicList_Chrome;

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
import org.openqa.selenium.support.ui.ExpectedCondition;
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


public class Settings_Add_Edit_Delete_Kitchen_Printer {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_Add_Edit_Delete_Kitchen_Printer");
	
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
			Kitchen_Printer_method_open_Kitchen_Printer(driver);
			Kitchen_Printer_method_refreshKitchen_Printer(driver);
			Kitchen_Printer_method_pagination(driver);
			Kitchen_Printer_method_add_Kitchen_Printer(driver);
			Kitchen_Printer_method_edit_Kitchen_Printer(driver);
			Kitchen_Printer_method_delete_Kitchen_Printer(driver);
			Kitchen_Printer_method_close_Button(driver);
			Thread.sleep(1500);
		}

/*	@Test(priority=3, enabled=false)
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
	*/
	@Test(priority=11,enabled = false)
	public void Kitchen_Printer_method_open_Kitchen_Printer(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	//	Thread.sleep(5000);
/*		//Click the Settings option
		driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[5]/a/span[1]")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Printer Configuration']"));
		//Scroll the page till the Printer Configuration option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Store Option		
		driver.findElement(By.xpath("//span[.='Printer Configuration']")).click();*/
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"newKitchenPrinters");
		Thread.sleep(5000);
		try
		{
		//Check Kitchen Printer page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 485, 1))).getText().equalsIgnoreCase("Kitchen Printers"))
		{
			test.log(LogStatus.PASS, "Kitchen printers page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Kitchen printers page loaded Failed");
		
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
			
	@Test(priority=12,enabled = false)
	public void Kitchen_Printer_method_refreshKitchen_Printer(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(2000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(5000); 
		//Click the refresh button
		driver.findElement(By.xpath(excel.getData(3, 1849, 1))).click();
		
		Thread.sleep(5000);
		//Check Kitchen Printer page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 485, 1))).getText().equalsIgnoreCase("Kitchen Printers"))
		{
			test.log(LogStatus.PASS, "Kitchen Printer page refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Kitchen Printer page refreshed Failed");wb.close();
		}
		Thread.sleep(3000);
		wb.close();
	}
	
	@Test(priority=13,enabled = false)
	public void Kitchen_Printer_method_pagination(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
		
		Thread.sleep(5000);
		//Click the Pagination option as 10
		driver.findElement(By.xpath(excel.getData(3, 1806, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Kitchen Printer");
		//Create the web element for Kitchen Printer Table
		List<WebElement> results = driver.findElements(By.xpath(excel.getData(3, 1807, 1)));
		for (WebElement result : results){						
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.xpath(excel.getData(3, 544, 1)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Kitchen Printer");
		}
		Thread.sleep(5000);
		
		//Click the Pagination option as 15
		driver.findElement(By.xpath(excel.getData(3, 1808, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Kitchen Printer");
		//Create the web element for Kitchen Printer Table
		List<WebElement> results1 = driver.findElements(By.xpath(excel.getData(3, 1807, 1)));
		for (WebElement result : results1){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.xpath(excel.getData(3, 544, 1)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Kitchen Printer");
		}
		Thread.sleep(5000);
		
		//Click the Pagination option as 20
		driver.findElement(By.xpath(excel.getData(3, 1809, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for Kitchen Printer");
		//Create the web element for Kitchen Printer Table
		List<WebElement> results2 = driver.findElements(By.xpath(excel.getData(3, 1807, 1)));
		for (WebElement result : results2){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.xpath(excel.getData(3, 544, 1)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Kitchen Printer");
		}
		Thread.sleep(5000);
		
		//Click the Pagination option as 5
		driver.findElement(By.xpath(excel.getData(3, 1810, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Kitchen Printer");
		//Create the web element for Kitchen Printer Table
		List<WebElement> results3 = driver.findElements(By.xpath(excel.getData(3, 1807, 1)));
		for (WebElement result : results3){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.xpath(excel.getData(3, 544, 1)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Kitchen Printer");
		}
		Thread.sleep(5000);wb.close();
	}
		 
	@Test(priority=14,enabled = false)
	public void Kitchen_Printer_method_add_Kitchen_Printer(WebDriver driver) throws Exception
	{

        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		 
		FileInputStream fis = new FileInputStream(src);
		 
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);		
		for(int i = 0; i <= 10; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}

				
				
		Thread.sleep(5000);
		//Click Add Kitchen Printer Button
		driver.findElement(By.xpath(excel.getData(3, 1811, 1))).click();
		
		Thread.sleep(3000);
		//Check New Kitchen Printer form opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1853, 1))).getText().equalsIgnoreCase("New Kitchen Printer"))
		{
			test.log(LogStatus.PASS, "Kitchen printer form opened Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Kitchen printer form opened Failed");
		}
		
		Thread.sleep(3000);
		//Click the Types Option
		driver.findElement(By.xpath(excel.getData(3, 1854, 1))).click();
		//Enter the required type
		driver.findElement(By.xpath(excel.getData(3, 1855, 1))).sendKeys("Printer");
		//Press Enter button
		driver.findElement(By.xpath(excel.getData(3, 1855, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Printer model
		driver.findElement(By.xpath(excel.getData(3, 1856, 1))).click();
		//Click
		driver.findElement(By.xpath(excel.getData(3, 1857, 1))).click();
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1857, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000); 
		//Clear the name field
		driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
		//Enter the name
		driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Kitchen_Printer_Name"));
		
		Thread.sleep(1000);
		//Clear the IP Address field 
		driver.findElement(By.name(excel.getData(3, 487, 3))).clear();
		//Enter the IP Address
		driver.findElement(By.name(excel.getData(3, 487, 3))).sendKeys(Utility.getProperty("Kitchen_Printer_IP"));
		
		Thread.sleep(2000);
		//Click the Altetnative Printer model
		driver.findElement(By.xpath(excel.getData(3, 1858, 1))).click();
		//Click
		driver.findElement(By.xpath(excel.getData(3, 1859, 1))).click();
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1859, 1))).sendKeys(Keys.ENTER);

		
		Thread.sleep(1000);
		//Check whether the Enable Service Type Restriction option is enable or not
		if(driver.findElement(By.name(excel.getData(3, 486, 3))).isSelected())
		{
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath(excel.getData(3, 1861, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys("QSR");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath(excel.getData(3, 1861, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys("ToGo");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath(excel.getData(3, 1861, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys("WEBORDER");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys(Keys.ENTER);
		}
		
		else
		{
			Thread.sleep(1000);
			//Enable or disable service type
			driver.findElement(By.name(excel.getData(3, 486, 3))).click();
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath(excel.getData(3, 1861, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys("QSR");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath(excel.getData(3, 1861, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys("ToGo");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath(excel.getData(3, 1861, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys("WEBORDER");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys(Keys.ENTER);
			
		}
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		Thread.sleep(1000);
		//Check whether the Apply to all Categories is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1863, 1))).isSelected())
		{
			Thread.sleep(1000);
			//Check whether the Apply to all menu items is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1864, 1))).isSelected())
			{
				
			}
			
			else
			{
				
				Thread.sleep(1000);
				//Enable the Apply to all menu items
				driver.findElement(By.xpath(excel.getData(3, 1864, 1))).click();
				
				//Click the Yes button
				driver.findElement(By.partialLinkText("Yes")).click();
			}
		}
		
		else
		{
			Thread.sleep(1000);
			//Enable the Apply to all Categories
			driver.findElement(By.xpath(excel.getData(3, 1863, 1))).click();
			Thread.sleep(1000);
			
			Thread.sleep(1000);
			//Check whether the Apply to all menu items is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1864, 1))).isSelected())
			{
				
			}
			
			else
			{
				Thread.sleep(1000);
				//Enable the Apply to all menu items
				driver.findElement(By.xpath(excel.getData(3, 1864, 1))).click();
				
				
			}
		}
		
		Thread.sleep(2000);
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(3, 491, 1))).click();
		Thread.sleep(2500);
		
		//Check whether the Kitchen printer was saved or not
		WebElement ele1=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
		WebDriverWait wait1=new WebDriverWait(driver,60);
		
		if(wait1.until(ExpectedConditions.visibilityOf(ele1)).getText().equalsIgnoreCase("Printer saved successfully"))
		{
			test.log(LogStatus.PASS, "Kitchen Printer Saved successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Kitchen Printer Saved fail");
		}

		Thread.sleep(5000);
		//Update Toggles in Kitchen Printers
		//Check whether the Print Voided Items to the Kitchen is Enabled or Not
		if(driver.findElement(By.xpath(excel.getData(3, 1850, 1))).isSelected())
		{
			
		}
		else
		{
			//Enable Print Voided Items to the Kitchen 
			driver.findElement(By.xpath(excel.getData(3, 1850, 1))).click();
		}
		
		//Check whether the Print Included Modifier In Kitchen is Enabled or Not
				if(driver.findElement(By.xpath(excel.getData(3, 1851, 1))).isSelected())
				{
					
				}
				else
				{
					//Enable Print Included Modifier In Kitchen 
					driver.findElement(By.xpath(excel.getData(3, 1851, 1))).click();
				}
		
				//Check whether the Seat Ordering Override is Enabled or Not
				if(driver.findElement(By.xpath(excel.getData(3, 1852, 1))).isSelected())
				{
					
				}
				else
				{
					//Enable Seat Ordering Override 
					driver.findElement(By.xpath(excel.getData(3, 1852, 1))).click();
				}
		
				
				//Update Button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				//Check whether the Kitchen printer was saved or not
				WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
				WebDriverWait wait=new WebDriverWait(driver,60);
				
				if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Kitchen Printers Updated successfully"))
				{
					test.log(LogStatus.PASS, "Kitchen Printers Updated successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Kitchen Printers Updated fail");
				}
				

		Thread.sleep(3000);wb.close();

	}
	
	@Test(priority=15,enabled = false)
	public void Kitchen_Printer_method_edit_Kitchen_Printer(WebDriver driver) throws Exception
	{

        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(2000);		
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(2000);
		//Clear the Search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
		//Enter the required key word
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Kitchen_Printer_Name"));
		
		Thread.sleep(1000);
		//Click the Edit button
		driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
		
		Thread.sleep(5000);
		//Click the Types Option
		driver.findElement(By.xpath(excel.getData(3, 1854, 1))).click();
		//Enter the required type
		driver.findElement(By.xpath(excel.getData(3, 1855, 1))).sendKeys("KDS");
		//Press Enter button
		driver.findElement(By.xpath(excel.getData(3, 1855, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Check whether the Expo Device field enabled ot not
		//Click the Types Option
		driver.findElement(By.xpath(excel.getData(3, 1865, 1))).click();
		//Enter the required Required Export IP Address
		driver.findElement(By.xpath(excel.getData(3, 1866, 1))).clear();;
		//Press Enter button
		driver.findElement(By.xpath(excel.getData(3, 1866, 1))).sendKeys(Utility.getProperty("Kitchen_Printer_IP_UP"));	
		
		Thread.sleep(1000);
		//Clear the name field
		driver.findElement(By.xpath(excel.getData(3,532, 1))).clear();
		//Enter the name
		driver.findElement(By.xpath(excel.getData(3,532, 1))).sendKeys(Utility.getProperty("Kitchen_Printer_Name")+"a");
		
		Thread.sleep(1000);
		//Clear the IP Address field
		driver.findElement(By.xpath(excel.getData(3, 487, 1))).clear();
		//Enter the IP Address
		driver.findElement(By.xpath(excel.getData(3, 487, 1))).sendKeys(Utility.getProperty("Kitchen_Printer_IPS"));
		
/*		Thread.sleep(2000);
		//Click the Printer model
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[5]/div/a")).click();
		//Press the down arrow button
		Thread.sleep(1000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(1000);
		//Click
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[5]/div/div/div/input")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[5]/div/div/div/input")).sendKeys(Keys.ENTER);
*/
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		try
		{
		Thread.sleep(1000);
		//Check whether the Enable Service Type Restriction option is enable or not
		if(driver.findElement(By.xpath(excel.getData(3, 486, 1))).isSelected())
		{
			
			
			for(int i = 1; i <= 3; i++)
			{
				driver.findElement(By.xpath(excel.getData(3, 488, 1))).click();
			}
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath(excel.getData(3, 1861, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys("DELIVERY");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath(excel.getData(3, 1861, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys("FORHERE");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath(excel.getData(3, 1861, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys("PHONEORDER");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys(Keys.ENTER);
		}
		}
		catch(Exception g)
		{
		try
		{
			Thread.sleep(1000);
		
			//Enable or disable service type
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 486, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
		} catch(Exception j) {}	
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath(excel.getData(3, 1861, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys("DELIVERY");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath(excel.getData(3, 1861, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys("FORHERE");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Options list box
			driver.findElement(By.xpath(excel.getData(3, 1861, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys("PHONEORDER");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1862, 1))).sendKeys(Keys.ENTER);
			
		}
		
		Thread.sleep(2000);
		//Click the Update button
		driver.findElement(By.xpath(excel.getData(3, 491, 1))).click();
		Thread.sleep(2000);
		
		//Check whether the Kitchen printer was updated or not
		WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,60);
		
		if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Printer updated successfully"))
		{
			test.log(LogStatus.PASS, "Kitchen Printer Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Kitchen Printer Updated fail");
		}
		/*
		Thread.sleep(3000);
		//Click Close button
		driver.findElement(By.xpath(excel.getData(3, 145, 1))).click();*/
		
		Thread.sleep(2000);wb.close();
	}
	
	@Test(priority=16,enabled = false)
	public void Kitchen_Printer_method_delete_Kitchen_Printer(WebDriver driver) throws Exception
	{

        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(2000);		
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		
		Thread.sleep(3000);
		//Clear the Search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();

		Thread.sleep(2000);
		//Clear the Search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
		//Enter the required key word
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Kitchen_Printer_Name")+"a");
		
		Thread.sleep(1000);
		//Click the Delete button
		driver.findElement(By.xpath(excel.getData(3, 544, 1))).click();
		
		//Click the Yes button in the popup
		Thread.sleep(1500);driver.findElement(By.xpath(excel.getData(3, 545, 1))).click();
		
		Thread.sleep(3000);
		//Check the Kitchen Printer deleted or not
		WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,150);
		
		if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Printer deleted successfully"))
		{
			test.log(LogStatus.PASS, "New Kitchen Printer is deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Kitchen Printer is deleted Failed");
		}
		Thread.sleep(3000);
		
		//Clear the Search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
		//Enter the required key word
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Kitchen_Printer_Name")+"a");
		
		Thread.sleep(2000);
		try{
			if(driver.findElement(By.xpath(excel.getData(3, 492, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Deleted Printer available here");
			}
		}

		catch(Exception e)
		{
			test.log(LogStatus.PASS, "Deleted Printer not available here");
		}
		//Clear the Search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
		Thread.sleep(2000);wb.close();
	}
	
	@Test(priority=17,enabled = false)
	public void Kitchen_Printer_method_close_Button(WebDriver driver) throws Exception
	{

        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(2000);		
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		
		
		Thread.sleep(5000);
		//Click Add Kitchen Printer Button
		driver.findElement(By.xpath(excel.getData(3, 1811, 1))).click();
		
		Thread.sleep(3000);
		//Check New Kitchen Printer form opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1853, 1))).getText().equalsIgnoreCase("New Kitchen Printer"))
		{
			test.log(LogStatus.PASS, "Kitchen printer form opened Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Kitchen printer form opened Failed");
		}
		Thread.sleep(3000);
		
		//Click the Types Option
		driver.findElement(By.xpath(excel.getData(3, 1854, 1))).click();
		//Enter the required type
		driver.findElement(By.xpath(excel.getData(3, 1855, 1))).sendKeys("Printer");
		//Press Enter button
		driver.findElement(By.xpath(excel.getData(3, 1855, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the name field
		driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
		//Enter the name
		driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys("New_Kitch_PrintB1");
		
	
		Thread.sleep(3000);
		//Click the Close button 
		driver.findElement(By.xpath(excel.getData(3, 145, 1))).click();
		Thread.sleep(2000);
		
		//Check whether the Kitchen printer form was closed or not
		if(driver.findElement(By.xpath(excel.getData(3, 1811, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Kitchen Printer form Closed successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Kitchen Printer form Closed fail");
		}
		Thread.sleep(3000);wb.close();
 
	}

}
