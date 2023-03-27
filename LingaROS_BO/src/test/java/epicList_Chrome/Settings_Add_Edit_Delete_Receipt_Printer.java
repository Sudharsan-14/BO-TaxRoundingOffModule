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

public class Settings_Add_Edit_Delete_Receipt_Printer {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_Add_Edit_Delete_Receipt_Printer");
	
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
			Receipt_Printer_method_open_Receipt_Printer(driver);
			Receipt_Printer_method_refreshReceipt_Printer(driver);
			Receipt_Printer_method_pagination(driver);
			Receipt_Printer_method_add_Receipt_Printer(driver);
			Receipt_Printer_method_edit_Receipt_Printer(driver);
			Receipt_Printer_method_delete_Receipt_Printer(driver);
			Receipt_Printer_method_close_Button(driver);
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
	@Test(priority=18,enabled = false)
	public void Receipt_Printer_method_open_Receipt_Printer(WebDriver driver) throws Exception
	{
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
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
			Thread.sleep(3000);
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"newReceiptPrinters");
		Thread.sleep(5000);
		try
		{
		//Check receipt Printer page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1814, 1))).getText().equalsIgnoreCase("Receipt printers"))
		{
			test.log(LogStatus.PASS, "Receipt printers page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt printers page loaded Failed");
		
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
			
	@Test(priority=19,enabled = false)
	public void Receipt_Printer_method_refreshReceipt_Printer(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
		Thread.sleep(8000);
		//Click the refresh button
	//	driver.findElement(By.xpath(excel.getData(3, 1849, 1))).click();
		
		Thread.sleep(5000);
		//Check Kitchen Printer page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1814, 1))).getText().equalsIgnoreCase("Receipt Printers"))
		{
			test.log(LogStatus.PASS, "Receipt Printer page refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt Printer page refreshed Failed");wb.close();
		}
		wb.close();
		Thread.sleep(3000);
	}
	
	@Test(priority=20,enabled = false)
	public void Receipt_Printer_method_pagination(WebDriver driver) throws Exception
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
		Thread.sleep(2000);
		
		//Click the Pagination option as 10
		driver.findElement(By.xpath(excel.getData(3, 1806, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Receipt Printer");
		//Create the web element for Receipt Printer Table
		List<WebElement> results = driver.findElements(By.xpath(excel.getData(3, 1807, 1)));
		for (WebElement result : results){						
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Receipt Printer");
		}
		Thread.sleep(5000);
		
		//Click the Pagination option as 15
		driver.findElement(By.xpath(excel.getData(3, 1808, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Receipt Printer");
		//Create the web element for Receipt Printer Table
		List<WebElement> results1 = driver.findElements(By.xpath(excel.getData(3, 1807, 1)));
		for (WebElement result : results1){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Receipt Printer");
		}
		Thread.sleep(5000);
		
		//Click the Pagination option as 20
		driver.findElement(By.xpath(excel.getData(3, 1809, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for Receipt Printer");
		//Create the web element for Receipt Printer Table
		List<WebElement> results2 = driver.findElements(By.xpath(excel.getData(3, 1807, 1)));
		for (WebElement result : results2){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Receipt Printer");
		}
		Thread.sleep(5000);
		
		//Click the Pagination option as 5
		driver.findElement(By.xpath(excel.getData(3, 1810, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Receipt Printer");
		//Create the web element for Receipt Printer Table
		List<WebElement> results3 = driver.findElements(By.xpath(excel.getData(3, 1807, 1)));
		for (WebElement result : results3){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Receipt Printer");
		}
		Thread.sleep(5000);wb.close();
	}
		
	@Test(priority=21,enabled = false)
	public void Receipt_Printer_method_add_Receipt_Printer(WebDriver driver) throws Exception
	{
       File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	  
		for(int i = 0; i <= 10; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		Thread.sleep(5000);
		//Click Add Receipt Printer Button
		driver.findElement(By.xpath(excel.getData(3, 1813, 1))).click();
		
		Thread.sleep(3000);
		//Check New Kitchen Printer form opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1815, 1))).getText().equalsIgnoreCase("New Receipt Printer"))
		{
			test.log(LogStatus.PASS, "Receipt printer form opened Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt printer form opened Failed");
		}
		Thread.sleep(5000);
		//Clear the name field
		driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
		//Enter the name
		driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys("New_Receipt_Print");
		
		Thread.sleep(1000);
		//Click Printer Model
		driver.findElement(By.xpath(excel.getData(3, 1897, 1))).click();
		//Select the Printer
		driver.findElement(By.xpath(excel.getData(3, 1898, 1))).sendKeys("Epson - TM-T88IV");
		//Enter the Printer 
		driver.findElement(By.xpath(excel.getData(3, 1898, 1))).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(1000);
		//Clear the IP Address field
		driver.findElement(By.name(excel.getData(3, 487, 3))).clear();
		//Enter the IP Address
		driver.findElement(By.name(excel.getData(3, 487, 3))).sendKeys("192.168.11.11");
		
		
		Thread.sleep(5000);
		//Check whether the default option is enable or not
		driver.findElement(By.xpath(excel.getData(3, 1899, 1))).click();
		
	
		Thread.sleep(2000);
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(3, 538, 1))).click();
		Thread.sleep(2000);
		
		//Check whether the Receipt printer was saved or not
		WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,60);
		
		if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Receipt Printer saved successfully"))
		{
			test.log(LogStatus.PASS, "Receipt Printer Saved successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt Printer Saved fail");
		}
		
		Thread.sleep(4000);
		//Receipt Printer Settings
		//Check whether the Auto Print Receipt field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 305, 1))).isSelected())
				{
					Thread.sleep(2000);
					//Check whether the Show Digital Receipt Screen enabled or not 
					if(driver.findElement(By.xpath(excel.getData(3, 307, 1))).isSelected())
					{
						Thread.sleep(2000);
						//Disable Show Digital Receipt Screen field
						driver.findElement(By.xpath(excel.getData(3, 307, 1))).click();
					}
					else
					{
						
					}
				}
										
					else
					{
						Thread.sleep(2000);
						//Enable the Auto Print Receipt Field Option
						driver.findElement(By.xpath(excel.getData(3, 305, 1))).click();
						Thread.sleep(2000);
						//Check whether the Show Digital Receipt Screen enabled or not 
						if(driver.findElement(By.xpath(excel.getData(3, 307, 1))).isSelected())
						{
							Thread.sleep(2000);
							//Disable Show Digital Receipt Screen field
							driver.findElement(By.xpath(excel.getData(3, 307, 1))).click();
						}
						else
						{
							
						}
					}
				
				Thread.sleep(2000);
				//Check whether the Show Signature Pad enabled or not 
				if(driver.findElement(By.xpath(excel.getData(3, 1900, 1))).isSelected())
				{
				
				}
				else
				{
					Thread.sleep(2000);
					//Enable Show Signature Pad field
					driver.findElement(By.xpath(excel.getData(3, 1900, 1))).click();	
				}
				
				Thread.sleep(2000);
				//Check whether the CC Receipt Screen enabled or not 
				if(driver.findElement(By.xpath(excel.getData(3, 1901, 1))).isSelected())
				{
				
				}
				else
				{
					Thread.sleep(2000);
					//Enable CC Receipt Screen field
					driver.findElement(By.xpath(excel.getData(3, 1901, 1))).click();	
				}
				
				
				Thread.sleep(2000);
				//Check whether the Print CC Merchant Copy enabled or not 
				if(driver.findElement(By.xpath(excel.getData(3, 1902, 1))).isSelected())
				{
				
				}
				else
				{
					Thread.sleep(2000);
					//Enable Print CC Merchant copy field
					driver.findElement(By.xpath(excel.getData(3, 1902, 1))).click();	
				}
				
				Thread.sleep(2000);
				//Check whether the Print CC Customer Copy enabled or not 
				if(driver.findElement(By.xpath(excel.getData(3, 1903, 1))).isSelected())
				{
				
				}
				else
				{
					Thread.sleep(2000);
					//Enable Print Customer copy field
					driver.findElement(By.xpath(excel.getData(3, 1903, 1))).click();	
				}
				
				Thread.sleep(2000);
				//Check whether the Remove Tip Line enabled or not 
				if(driver.findElement(By.xpath(excel.getData(3, 1904, 1))).isSelected())
				{
				
				}
				else
				{
					Thread.sleep(2000);
					//Enable Remove Tip Line field
					driver.findElement(By.xpath(excel.getData(3, 1904, 1))).click();	
				}
				
				Thread.sleep(2000);
				//Check whether the Tip Suggesstion on Credit Card Receipt enabled or not 
				if(driver.findElement(By.xpath(excel.getData(3, 1905, 1))).isSelected())
				{
				
				}
				else
				{
					Thread.sleep(2000);
					//Enable Tip Suggesstion on Credit Card Receipt field
					driver.findElement(By.xpath(excel.getData(3, 1905, 1))).click();	
				}
				
				Thread.sleep(4000);
				//Click Update button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		
				//Check whether the Receipt printer was saved or not
				WebElement ele1=driver.findElement(By.xpath(excel.getData(3, 39, 1)));

				WebDriverWait wait1=new WebDriverWait(driver,60);
				//Check whether the Receipt Printer Settings Toggles Updated 
				if(wait1.until(ExpectedConditions.visibilityOf(ele1)).getText().equalsIgnoreCase("Receipt Printers Updated successfully"))
				{
					test.log(LogStatus.PASS, "Auto Print Receipt Enabled in Receipt Printers Settings Toggles Updated successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Auto Print Receipt Enabled in Receipt Printers Settings Toggles Updated fail");
				}
				
		
		Thread.sleep(3000);wb.close();

	}
	
	@Test(priority=22,enabled = false)
	public void Receipt_Printer_method_edit_Receipt_Printer(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	   
		Thread.sleep(5000); 
		//Clear the Search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
		//Enter the required key word
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys("New_Receipt_Print");
		
		Thread.sleep(4000);
		//Click the Edit button
		driver.findElement(By.xpath(excel.getData(3, 1336, 1))).click();
		
		Thread.sleep(5000);
		//Clear the name field
		driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
		//Enter the name
		driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys("New_Receipt_Print1");
		
		Thread.sleep(1000);
		//Click Printer Model
		driver.findElement(By.xpath(excel.getData(3, 1897, 1))).click();
		//Select the Printer
		driver.findElement(By.xpath(excel.getData(3, 1898, 1))).sendKeys("Epson - TM-T88IV");
		//Enter the Printer 
		driver.findElement(By.xpath(excel.getData(3, 1898, 1))).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(5000);
		//Clear the IP Address field
		driver.findElement(By.name(excel.getData(3, 487, 3))).clear();
		//Enter the IP Address
		driver.findElement(By.name(excel.getData(3, 487, 3))).sendKeys("192.168.11.12");
		
		
		Thread.sleep(5000);
		//Check whether the default option is enable or not
		driver.findElement(By.xpath(excel.getData(3, 1899, 1))).click();
		
		Thread.sleep(2000);
		//Click the Update button
		driver.findElement(By.xpath(excel.getData(3, 538, 1))).click();
		Thread.sleep(2000);
		
		//Check whether the Receipt printer was updated or not
		WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,60);
		
		if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Receipt Printer updated successfully"))
		{
			test.log(LogStatus.PASS, "Receipt Printer Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt Printer Updated fail");
		}
	
		Thread.sleep(4000);
		//Receipt Printer Settings
		//Check whether the Auto Print Receipt field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 305, 1))).isSelected())
				{
					Thread.sleep(2000);
					//Disable Auto Print Receipt Screen field
					driver.findElement(By.xpath(excel.getData(3, 305, 1))).click();

					Thread.sleep(1000);
					//Check whether the Show Digital Receipt Screen enabled or not 
					if(driver.findElement(By.xpath(excel.getData(3, 307, 1))).isSelected())
					{
					}
					else
					{
						Thread.sleep(2000);
						//Enable Show Digital Receipt Screen field
						driver.findElement(By.xpath(excel.getData(3, 307, 1))).click();
						
					}
				}
										
					else
					{
						
						Thread.sleep(2000);
						//Check whether the Show Digital Receipt Screen enabled or not 
						if(driver.findElement(By.xpath(excel.getData(3, 307, 1))).isSelected())
						{
													}
						else
						{
							Thread.sleep(2000);
							//Enable Show Digital Receipt Screen field
							driver.findElement(By.xpath(excel.getData(3, 307, 1))).click();

						}
					}
				
				Thread.sleep(2000);
				//Check whether the Show Signature Pad enabled or not 
				if(driver.findElement(By.xpath(excel.getData(3, 1900, 1))).isSelected())
				{
				
				}
				else
				{
					Thread.sleep(2000);
					//Enable Show Signature Pad field
					driver.findElement(By.xpath(excel.getData(3, 1900, 1))).click();	
				}
				
				Thread.sleep(2000);
				//Check whether the CC Receipt Screen enabled or not 
				if(driver.findElement(By.xpath(excel.getData(3, 1901, 1))).isSelected())
				{
				
				}
				else
				{
					Thread.sleep(2000);
					//Enable CC Receipt Screen field
					driver.findElement(By.xpath(excel.getData(3, 1901, 1))).click();	
				}
				
				
				Thread.sleep(2000);
				//Check whether the Print CC Merchant Copy enabled or not 
				if(driver.findElement(By.xpath(excel.getData(3, 1902, 1))).isSelected())
				{
				
				}
				else
				{
					Thread.sleep(2000);
					//Enable Print CC Merchant copy field
					driver.findElement(By.xpath(excel.getData(3, 1902, 1))).click();	
				}
				
				Thread.sleep(2000);
				//Check whether the Print CC Customer Copy enabled or not 
				if(driver.findElement(By.xpath(excel.getData(3, 1903, 1))).isSelected())
				{
				
				}
				else
				{
					Thread.sleep(2000);
					//Enable Print Customer copy field
					driver.findElement(By.xpath(excel.getData(3, 1903, 1))).click();	
				}
				
				Thread.sleep(2000);
				//Check whether the Remove Tip Line enabled or not 
				if(driver.findElement(By.xpath(excel.getData(3, 1904, 1))).isSelected())
				{
					Thread.sleep(2000);
					//Disable Remove Tip Line field
					driver.findElement(By.xpath(excel.getData(3, 1904, 1))).click();	
				}
				else
				{
					
				}
				
				Thread.sleep(2000);
				//Check whether the Tip Suggesstion on Credit Card Receipt enabled or not 
				if(driver.findElement(By.xpath(excel.getData(3, 1905, 1))).isSelected())
				{
				
				}
				else
				{
					Thread.sleep(2000);
					//Enable Tip Suggesstion on Credit Card Receipt field
					driver.findElement(By.xpath(excel.getData(3, 1905, 1))).click();	
				}
				
				Thread.sleep(4000);
				//Click Update button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		
				//Check whether the Receipt printer was saved or not
				WebElement ele1=driver.findElement(By.xpath(excel.getData(3, 39, 1)));

				WebDriverWait wait1=new WebDriverWait(driver,60);
				//Check Receipt Printer Settings Toglles upadted or not
				if(wait1.until(ExpectedConditions.visibilityOf(ele1)).getText().equalsIgnoreCase("Receipt Printers Updated successfully"))
				{
					test.log(LogStatus.PASS, "Show Digital Receipt Screen Enabled in Receipt Printers Settings Toggles Updated successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Show Digital Receipt Screen in Receipt Printers Settings Toggles Updated fail");
				}
				
	
		Thread.sleep(3000);wb.close();
	}
	 
	@Test(priority=23,enabled = false)
	public void Receipt_Printer_method_delete_Receipt_Printer(WebDriver driver) throws Exception
	{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Clear the Search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
		//Enter the required key word
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys("New_Receipt_Print1");
		
		Thread.sleep(4000);
		//Click the Delete button
		driver.findElement(By.xpath(excel.getData(3, 544, 1))).click();
		
		//Click the Yes button in the popup 
		Thread.sleep(1500);driver.findElement(By.xpath(excel.getData(3, 545, 1))).click();
		
		Thread.sleep(3000);
		//Check the Kitchen Printer deleted or not
		WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,60);
		
		if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Receipt Printer deleted successfully"))
		{
			test.log(LogStatus.PASS, "New Receipt Printer is deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Receipt Printer is deleted Failed");
		}
		Thread.sleep(3000);
		//Clear the Search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();wb.close();
	}
	
	@Test(priority=24,enabled = false)
	public void Receipt_Printer_method_close_Button(WebDriver driver) throws Exception
	{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Click Add Receipt Printer Button
		driver.findElement(By.xpath(excel.getData(3, 1813, 1))).click();
		
		Thread.sleep(3000);
		//Check New Kitchen Printer form opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1815, 1))).getText().equalsIgnoreCase("New Receipt Printer"))
		{
			test.log(LogStatus.PASS, "Receipt printer form opened Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt printer form opened Failed");
		}
		Thread.sleep(3000);
		

		//Clear the name field
		driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
		//Enter the name
		driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys("New_Receipt_Print");
		
	
		Thread.sleep(2000);
		//Click the Close button
		driver.findElement(By.xpath(excel.getData(3, 145, 1))).click();
		Thread.sleep(2000);
		
		//Check whether the Kitchen printer form was closed or not
		if(driver.findElement(By.xpath(excel.getData(3, 1814, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Receipt Printer form Closed successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt Printer form Closed fail");
		}
		Thread.sleep(5000);wb.close();  

	}

}
