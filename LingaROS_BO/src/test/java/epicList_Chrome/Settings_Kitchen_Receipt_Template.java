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


public class Settings_Kitchen_Receipt_Template {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Kitchen_Receipt_Template");
	
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
			Kitchen_Receipt_Template_method_open_Kitchen_Receipt_Template(driver);
			Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template(driver);
			Verify_Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template(driver);
			Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template_Enable_Consolidate_Menu_In_Kitchen(driver);
			Verify_Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template_Enable_Consolidate_Menu_In_Kitchen(driver);
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
	
	@Test(priority=5,enabled = false)
	public void Kitchen_Receipt_Template_method_open_Kitchen_Receipt_Template(WebDriver driver) throws Exception
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
		
		for(int i = 0; i <= 10; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"newKitchenReceiptTemplate");
		
		Thread.sleep(8000);
		try
		{
		//Check whether the Kitchen Receipt Template
		if(driver.findElement(By.xpath(excel.getData(3, 523, 1))).getText().equalsIgnoreCase("Kitchen Receipt Template"))
		{
			test.log(LogStatus.PASS, "Kitchen Receipt Template page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Kitchen Receipt Template page loaded Failed");
		
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
		
	@Test(priority=6,enabled = false)
	public void Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	 
		Thread.sleep(10000);
		//Select the font size 
		driver.findElement(By.xpath(excel.getData(3, 1821, 1))).click();
		//Select the Font Size
		driver.findElement(By.xpath(excel.getData(3, 1822, 1))).sendKeys("Small");
		//Enter the Font Size
		driver.findElement(By.xpath(excel.getData(3, 1822, 1))).sendKeys(Keys.ENTER);


		//Check whether the Store name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 417, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Store Name Option
			driver.findElement(By.xpath(excel.getData(3, 417, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Table name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 418, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
				//Enable the Table Name Option
			driver.findElement(By.xpath(excel.getData(3, 418, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Server name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 420, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
				//Enable the Server Name Option
			driver.findElement(By.xpath(excel.getData(3, 420, 1))).click();
		}
		
		
		Thread.sleep(2000);
		//Check whether the Check Number field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 422, 1))).isSelected())
		{
			Thread.sleep(2000);
				//Select Position
			driver.findElement(By.xpath(excel.getData(3, 1829, 1))).click();
			//Input Position
			driver.findElement(By.xpath(excel.getData(3, 1830, 1))).sendKeys("P#1");
			//Enter Position
			driver.findElement(By.xpath(excel.getData(3, 1830, 1))).sendKeys(Keys.ENTER);
		
			
			Thread.sleep(2000);
			//Select Position
			driver.findElement(By.xpath(excel.getData(3, 1831, 1))).click();
			//Input Position
			driver.findElement(By.xpath(excel.getData(3, 1832, 1))).sendKeys("2 Height");
			//Enter Position
			driver.findElement(By.xpath(excel.getData(3, 1832, 1))).sendKeys(Keys.ENTER);

		}
		else
		{
			Thread.sleep(2000);
			//Enable the Check Number Option
			driver.findElement(By.xpath(excel.getData(3, 422, 1))).click();
			
			Thread.sleep(2000);
			//Select Position
			driver.findElement(By.xpath(excel.getData(3, 1829, 1))).click();
			//Input Position
			driver.findElement(By.xpath(excel.getData(3, 1830, 1))).sendKeys("P#1");
			//Enter Position
			driver.findElement(By.xpath(excel.getData(3, 1830, 1))).sendKeys(Keys.ENTER);
		
			
			Thread.sleep(2000);
			//Select Position
			driver.findElement(By.xpath(excel.getData(3, 1831, 1))).click();
			//Input Position
			driver.findElement(By.xpath(excel.getData(3, 1832, 1))).sendKeys("2 Height");
			//Enter Position
			driver.findElement(By.xpath(excel.getData(3, 1832, 1))).sendKeys(Keys.ENTER);

		}
		
		Thread.sleep(2000);
		//Check whether the Date & Time field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 425, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
				//Enable the Date & Time Option
			driver.findElement(By.xpath(excel.getData(3, 425, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Split By Course field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 546, 1))).isSelected()){}
				else
				{
					Thread.sleep(2000);
							//Enable the Split By Course Option
				driver.findElement(By.xpath(excel.getData(3, 546, 1))).click();
				}
				Thread.sleep(1000);
				
		
				Thread.sleep(2000);
				//Check whether the Customer name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 427, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Customer Name Option
			driver.findElement(By.xpath(excel.getData(3, 427, 1))).click();
		}
		Thread.sleep(1000);
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		

		Thread.sleep(2000);
		//Check whether the Customer Information Position field - Below is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 547, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
				//Enable the Customer Information Position Option - Below
			driver.findElement(By.xpath(excel.getData(3, 547, 1))).click();
		}
		
		
		Thread.sleep(2000);
		//Check whether the Service Type field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 529, 1))).isSelected())
		{
			Thread.sleep(2000);
				//Click the Service Type Required Position
			driver.findElement(By.xpath(excel.getData(3, 1846, 1))).click();
			//Enter Service type required position
			driver.findElement(By.xpath(excel.getData(3, 1847, 1))).sendKeys("P#2");
			//Enter Service Type Position
			driver.findElement(By.xpath(excel.getData(3, 1847, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
		}
		else
		{
			Thread.sleep(3000);
			//Enable the Service Type Option
			driver.findElement(By.xpath(excel.getData(3, 531, 1))).click();
			
			Thread.sleep(2000);
			//Click the Service Type Required Position
			driver.findElement(By.xpath(excel.getData(3, 1846, 1))).click();
			//Enter Service type required position
			driver.findElement(By.xpath(excel.getData(3, 1847, 1))).sendKeys("P#2");
			//Enter Service Type Position
			driver.findElement(By.xpath(excel.getData(3, 1847, 1))).sendKeys(Keys.ENTER);
			
			
		}
			
		Thread.sleep(2000);
		//Check whether the Email field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 460, 1))).isSelected()){}
				
				else
				{
					Thread.sleep(2000);
					//Enable the Email Option
					driver.findElement(By.xpath(excel.getData(3, 460, 1))).click();
				}
		
				
				Thread.sleep(2000);
				//Check whether the Cut Paper After Each print field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 465, 1))).isSelected()){}
				
				else
				{
					Thread.sleep(2000);
					//Enable the Cut Paper After Each print Option
					driver.findElement(By.xpath(excel.getData(3, 465, 1))).click();
				}
		
				Thread.sleep(2000);
				//Check whether the Item Summary field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1848, 1))).isSelected()){}
				
				else
				{
					Thread.sleep(2000);
					//Enable the Cut Paper After Each print Option
					driver.findElement(By.xpath(excel.getData(3, 1848, 1))).click();
				}
			
				Thread.sleep(2000);
				//Check whether the Notes field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 466, 1))).isSelected()){}
				
				else
				{
					Thread.sleep(2000);
					//Enable the Notes Option
					driver.findElement(By.xpath(excel.getData(3, 466, 1))).click();
				}

		
				Thread.sleep(2000);
				//Check whether the Address field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 459, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Address Option
			driver.findElement(By.xpath(excel.getData(3, 459, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Is Other Language Menu Name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 741, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Is Other Language Menu Name Option
			driver.findElement(By.xpath(excel.getData(3, 741, 1))).click();
		}
		
		
		Thread.sleep(2000);
		//Check whether the Phone number field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 461, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Phone Number Option
			driver.findElement(By.xpath(excel.getData(3, 461, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Print all Modifiers in Modifier field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 742, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Print all Modifiers in Modifier Option
			driver.findElement(By.xpath(excel.getData(3, 742, 1))).click();
		}
		
		
		Thread.sleep(2000);
		//Check whether the Print quantity before the menu name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 548, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Print quantity before the menu name Option
			driver.findElement(By.xpath(excel.getData(3, 548, 1))).click();
		}
		
		
		Thread.sleep(3000);
		//Check whether the Consolidate Menu In Kitchen field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 549, 1))).isSelected())
		{
			
			
			Thread.sleep(4000);
			//Disable the Consolidate Menu In Kitchen Option
			driver.findElement(By.xpath(excel.getData(3, 549, 1))).click();
		
			
			Thread.sleep(3000);
			//Check whether the split Menu Quantity field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 740, 1))).isSelected())
			{
				test.log(LogStatus.FAIL, "Split Menu Quantity field is enabled when Consolidate Menu In Kitchen Option is disabled");
			}
			else
			{
				Thread.sleep(2000);
				//Enable the split Menu Quantity Option
				driver.findElement(By.xpath(excel.getData(3, 740, 1))).click();
			}
		}
		
		
		
		Thread.sleep(4000);
		//Click the update button
		driver.findElement(By.xpath(excel.getData(3, 743, 1))).click();
		
		Thread.sleep(5000);
		//Check weather the new denomination form saved or not									
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Kitchen Receipt template saved successfully"))
		{
			test.log(LogStatus.PASS, "Receipt template Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt template Updated fail");
		}
		Thread.sleep(3000);wb.close(); 
		
		
	}

	@Test(priority=7,enabled = false)
	public void Verify_Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template(WebDriver driver) throws Exception
	{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	 
		Thread.sleep(5000);
		//Check Whether the Font Size is updated or not
		try
		{
			Thread.sleep(2000);
			if(driver.findElement(By.xpath(excel.getData(3, 415, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Font Size updated successfully in Kitchen Receipt Template");
			}
		}
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Font Size updated Fail in Kitchen Receipt Template");
		}

		
		Thread.sleep(2000);
		//Check whether the Store name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 417, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Store Name field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Store Name field is disabled in Kitchen Receipt Template");
		}
		
		Thread.sleep(1000);
		//Check whether the Table name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 418, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Table Name field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Table Name field is disabled in Kitchen Receipt Template");
		}
		
		Thread.sleep(1000);
		//Check whether the Server name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 420, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Server Name field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Server Name field is disabled in Kitchen Receipt Template");
		}
		
		
		Thread.sleep(1000);
		//Check whether the Check Number field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 422, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Check Number field is Enabled in Kitchen Receipt Template");
			
			Thread.sleep(1000);
			//Check Whether the Position is updated or not
			try
			{
				Thread.sleep(1000);
				if(driver.findElement(By.xpath(excel.getData(3, 744, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "Position - Check Number updated successfully in Kitchen Receipt Template");
				}
			}
			catch(Exception e)
			{
				test.log(LogStatus.FAIL, "Position - Check Number updated Fail in Kitchen Receipt Template");
			}
			
			Thread.sleep(1000);
			//Check Whether the Position Font Size is updated or not
			try
			{
				Thread.sleep(1000);
				if(driver.findElement(By.xpath(excel.getData(3, 745, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "Position Font Size - Check Number updated successfully in Kitchen Receipt Template");
				}
			}
			catch(Exception e)
			{
				test.log(LogStatus.FAIL, "Position Font Size - Check Number updated Fail in Kitchen Receipt Template");
			}
		}
		else
		{
			test.log(LogStatus.FAIL, "Check Number field is disabled in Kitchen Receipt Template");
		}
		
		Thread.sleep(1000);
		//Check whether the Date & Time field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 425, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Date & Time field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Date & Time field is disabled in Kitchen Receipt Template");
		}
		
		Thread.sleep(1000);
		//Check whether the Customer name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 427, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Customer Name field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer Name field is disabled in Kitchen Receipt Template");
		}
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		Thread.sleep(1000);
		//Check whether the Service Type field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 428, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Service Type field is Enabled in Kitchen Receipt Template");
			
			//Check Whether the Position is updated or not
			try
			{
				Thread.sleep(1000);
				if(driver.findElement(By.xpath(excel.getData(3, 428, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "Position - Service Type updated successfully in Kitchen Receipt Template");
				}
			}
			catch(Exception e)
			{
				test.log(LogStatus.FAIL, "Position - Service Type updated Fail in Kitchen Receipt Template");
			}

		}
		else
		{
			test.log(LogStatus.FAIL, "Service Type field is disabled in Kitchen Receipt Template");
		}
		
		
		Thread.sleep(1000);
		//Check whether the Customer field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 427, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Customer field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer field is disabled in Kitchen Receipt Template");
		}
		
		
		Thread.sleep(1000);
		//Check whether the Customer Information Position field - Below is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 547, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Customer Information Position field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer Information Position field is disabled in Kitchen Receipt Template");
		}
		
		
		Thread.sleep(1000);
		//Check whether the Address field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 529, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Address field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Address field is disabled in Kitchen Receipt Template");
		}
		
		Thread.sleep(1000);
		//Check whether the Email field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 460, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Email field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email field is disabled in Kitchen Receipt Template");
		}
		
		Thread.sleep(1000);
		//Check whether the Phone number field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 461, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Phone number field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Phone number field is disabled in Kitchen Receipt Template");
		}
		
		Thread.sleep(1000);
		//Check whether the Cut Paper After Each print field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 465, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Cut Paper After Each print field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Cut Paper After Each print field is disabled in Kitchen Receipt Template");
		}
		
		Thread.sleep(1000);
		//Check whether the Print quantity before the menu name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 548, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Print quantity before the menu name field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Print quantity before the menu name field is disabled in Kitchen Receipt Template");
		}
		
		
		Thread.sleep(1000);
		//Check whether the Notes field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 466, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Notes field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Notes field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
				
		try
		{
			Thread.sleep(1000);
				//Check whether the Consolidate Menu In Kitchen field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 549, 1))).isSelected())
			{
				test.log(LogStatus.FAIL, "Consolidate Menu In Kitchen field is Enabled in Kitchen Receipt Template");
			}
			else
			{
				test.log(LogStatus.PASS, "Consolidate Menu In Kitchen field is disabled in Kitchen Receipt Template");
			}
			Thread.sleep(1000);
			//Check whether the Split Menu Quantity is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 740, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Split Menu Quantity field is Enabled in Kitchen Receipt Template");
			}
			else
			{
				test.log(LogStatus.FAIL, "Split Menu Quantity field is disabled in Kitchen Receipt Template");
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		Thread.sleep(1000);
		//Check whether the Is Other Language Menu Name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 741, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Is Other Language Menu Name field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Is Other Language Menu Name field is disabled in Kitchen Receipt Template");
		}
		
		Thread.sleep(1000);
		//Check whether the Print all modifiers in Modifier print field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 742, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Print all modifiers in Modifier print field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Print all modifiers in Modifier print field is disabled in Kitchen Receipt Template");wb.close();
		}
		Thread.sleep(3000);
	}

	@Test(priority=8,enabled = false)
	public void Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template_Enable_Consolidate_Menu_In_Kitchen(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	 
		Thread.sleep(5000);

		
		
/*		try
		{
		
			Thread.sleep(3000);
			//Check whether the Consolidate Menu In Kitchen field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 549, 1))).isSelected())
			{
				//Disable the Consolidate Menu In Kitchen Option
				driver.findElement(By.xpath(excel.getData(3, 549, 1))).click();
			}	
			*/
			Thread.sleep(3000);
			//Check whether the split Menu Quantity field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 740, 1))).isSelected())
			{
				//Disable the split Menu Quantity Option
				driver.findElement(By.xpath(excel.getData(3, 740, 1))).click();
				
				Thread.sleep(3000);
				//Enable the Consolidate Menu In Kitchen Option
				driver.findElement(By.xpath(excel.getData(3, 549, 1))).click();

			}
			
			else
			{}
	/*	}
		catch(Exception e)
		{	
			//Check whether the Consolidate Menu In Kitchen field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 549, 1))).isSelected())
			{
				test.log(LogStatus.FAIL, "Consolidate Menu In Kitchen field is enabled when disable the Split Menu Quantity");
			}	
			else
			{
				//Enable the Consolidate Menu In Kitchen Option
				driver.findElement(By.xpath(excel.getData(3, 549, 1))).click();
			}
		}*/
		
		Thread.sleep(5000);
		//Click the update button
		driver.findElement(By.xpath(excel.getData(3, 743, 1))).click();
		
		Thread.sleep(5000);
		//Check weather the new denomination form saved or not									
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Kitchen Receipt template saved successfully"))
		{
			test.log(LogStatus.PASS, "Receipt template Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt template Updated fail");
		}
		Thread.sleep(3000);wb.close();
		
		
	}

	@Test(priority=9,enabled = false)
	public void Verify_Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template_Enable_Consolidate_Menu_In_Kitchen(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		 
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}

				
		try
		{
			Thread.sleep(1000);
			//Check whether the Consolidate Menu In Kitchen field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 549, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Consolidate Menu In Kitchen field is Enabled in Kitchen Receipt Template");
			}
			else
			{
				test.log(LogStatus.FAIL, "Consolidate Menu In Kitchen field is disabled in Kitchen Receipt Template");
			}
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			Thread.sleep(1000);
			//Check whether the Split Menu Quantity is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 740, 1))).isSelected())
			{
				test.log(LogStatus.FAIL, "Split Menu Quantity field is Enabled in Kitchen Receipt Template");
			}
			else
			{
				test.log(LogStatus.PASS, "Split Menu Quantity field is disabled in Kitchen Receipt Template");
			}
		}
		Thread.sleep(5000);wb.close();

	}

}
