package epicList_Chrome_Account_User;


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
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;


public class Settings_Front_End_Receipt {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Front_End_Receipt");
	
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
			Front_End_Receipt_method_open_Front_End_Receipt(driver);
			Front_End_Receipt_method_update_FrontEndReceipt_Template(driver);
			Front_End_Receipt_method_verifyUpdatedDetails(driver);
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
	@Test(priority=2,enabled = false)
	public void Front_End_Receipt_method_open_Front_End_Receipt(WebDriver driver) throws Exception
	{

        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(15000);
/*		//Click the Settings option
		driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[5]/a/span[1]")).click();Thread.sleep(3000);
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Printer Configuration']"));
		//Scroll the page till the Printer Configuration option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); Thread.sleep(3000);
        //Click the Store Option		
		driver.findElement(By.xpath("//span[.='Printer Configuration']")).click();
*/		
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"newFrontEndReceiptTemplate");
		Thread.sleep(5000);
		try
		{
		//Check Front End Receipt page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 414, 1))).getText().equalsIgnoreCase("Front End Receipt Template"))
		{
			test.log(LogStatus.PASS, "Front End Receipt Template page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Front End Receipt Template page loaded Failed");
		
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
		
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));wb.close();
		//Thread.sleep(3000);watchTutorial();Thread.sleep(3000);
	}	
		
	@Test(priority=3,enabled = false)
	public void Front_End_Receipt_method_update_FrontEndReceipt_Template(WebDriver driver) throws Exception
	{

        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(8000);
		
			//Click the Right pull button in Print Template
			//driver.findElement(By.xpath(excel.getData(3, 2297, 1))).click();
			
		Thread.sleep(1000);
		//Select the Font Size
    	driver.findElement(By.xpath(excel.getData(3, 1821, 1))).click();
		//Enter Font Size
    	driver.findElement(By.xpath(excel.getData(3, 1822, 1))).sendKeys("Small");
    	//Enter key for Font Size Selection
    	driver.findElement(By.xpath(excel.getData(3, 1822, 1))).sendKeys(Keys.ENTER);
    		
    	//Select the Choose Template
    //	driver.findElement(By.xpath(excel.getData(3, 2298, 1))).click();
		//Enter Choose Template
    //	driver.findElement(By.xpath(excel.getData(3, 2299, 1))).sendKeys("Default Template");
    	//Enter key for Choose Template
    //	driver.findElement(By.xpath(excel.getData(3, 2299, 1))).sendKeys(Keys.ENTER);
    	
    	
		Thread.sleep(2000);
		//Click the Store Information 
		driver.findElement(By.xpath(excel.getData(3, 1823, 1))).click();
    	
		Thread.sleep(2000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		
		Thread.sleep(3000);
		//Check whether the store name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 417, 1))).isSelected()){}
		
		else
		{ 
			//Enable the Store name Option
			driver.findElement(By.xpath(excel.getData(3, 417, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Store Logo is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 416, 1))).isSelected()) {}
		
		else
		{
			//Enable the Store Logo option
			driver.findElement(By.xpath(excel.getData(3, 416, 1))).click();
			Thread.sleep(2000);
			//Select the required store logo
			driver.findElement(By.id("storeImage")).sendKeys(Utility.getProperty("Store_Logo_Path"));
		}
		
		Thread.sleep(2000);
		//Check whether the store Address name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1824, 1))).isSelected()){}
		
		else
		{
			//Enable the Store Address Option
			driver.findElement(By.xpath(excel.getData(3, 1824, 1))).click();
		}

		Thread.sleep(2000);
		//Check whether the store Email field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1825, 1))).isSelected()){}
		
		else
		{
			//Enable the Store Email Option
			driver.findElement(By.xpath(excel.getData(3, 1825, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the store Phone Number field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1826, 1))).isSelected()){}
				
				else
				{
					//Enable the Store Phone Number Option
					driver.findElement(By.xpath(excel.getData(3, 1826, 1))).click();
				}

				Thread.sleep(2000);
				//Enter Special Notes 
				driver.findElement(By.xpath(excel.getData(3, 1827, 1))).clear();
				//Enter Special notes
				driver.findElement(By.xpath(excel.getData(3, 1827, 1))).sendKeys("Eat Well");
	
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				
				Thread.sleep(2000);
				//Enable the table name Option
				driver.findElement(By.xpath(excel.getData(3, 1828, 1))).click();
		
				
				
			Thread.sleep(2000);	
		//Check whether the table name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 418, 1))).isSelected()){}
		
		else
		{
			//Enable the table name Option
			driver.findElement(By.xpath(excel.getData(3, 418, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the seat number field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 419, 1))).isSelected()){}
		
		else
		{
			//Enable the seat number Option
			driver.findElement(By.xpath(excel.getData(3, 419, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Check Open Server field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 420, 1))).isSelected()){}
		
		else
		{
			//Enable the Check Open Server Option
			driver.findElement(By.xpath(excel.getData(3, 420, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Check Close Server field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 421, 1))).isSelected()){}
		
		else
		{
			//Enable the Check Close Server Option
			driver.findElement(By.xpath(excel.getData(3, 421, 1))).click();
		}
		Thread.sleep(3000);
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		//Check whether the Check number field is enabled or not
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
			//Enable the Check number Option
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
		//Check whether the Open Date and Time field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 425, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Open Date and Time Option
			driver.findElement(By.xpath(excel.getData(3, 425, 1))).click();
		}
		
		
		Thread.sleep(2000);
		//Check whether the Close Date and Time field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 426, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Close Date and Time Option
			driver.findElement(By.xpath(excel.getData(3, 426, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Customer name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 427, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the customer name Option
			driver.findElement(By.xpath(excel.getData(3, 427, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Service Type field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 428, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Service Type Option
			driver.findElement(By.xpath(excel.getData(3, 428, 1))).click();
		}
		
		Thread.sleep(2000);
		//Click Order Summary Tab
		driver.findElement(By.xpath(excel.getData(3, 1833, 1))).click();
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		
		Thread.sleep(2000);
		//Check whether the Show Secondary menu name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 429, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the  Show Secondary menu name Option
			driver.findElement(By.xpath(excel.getData(3, 429, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Roll Out Modifier Price To Menu field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 430, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the  Roll Out Modifier Price To Menu Option
			driver.findElement(By.xpath(excel.getData(3, 430, 1))).click();
		}
		
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
		}
		Thread.sleep(2000);
		//Check whether the Roll Out Modifier Price & Quantity To Menu field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 446, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the  Roll Out Modifier Price & Quantity To Menu Option
			driver.findElement(By.xpath(excel.getData(3, 446, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Roll Out Modifier To Menu field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 447, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the  Roll Out Modifier To Menu Option
			driver.findElement(By.xpath(excel.getData(3, 447, 1))).click();
		}
		
		
		Thread.sleep(2000);
		//Check whether the Exclude zero price menu item field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 448, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Exclude zero price menu item Option
			driver.findElement(By.xpath(excel.getData(3, 448, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Exclude Zero Price Modifier field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 449, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Exclude Zero Price Modifier item Option
			driver.findElement(By.xpath(excel.getData(3, 449, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Tax Summary field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 450, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Tax Summary Option
			driver.findElement(By.xpath(excel.getData(3, 450, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Gratuity field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 451, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Gratuity Option
			driver.findElement(By.xpath(excel.getData(3, 451, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Tip field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 452, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Tip Option
			driver.findElement(By.xpath(excel.getData(3, 452, 1))).click();
		}
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
		}
		
		Thread.sleep(2000);
		//Check whether the Cash Discount field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 453, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Cash Discount Option
			driver.findElement(By.xpath(excel.getData(3, 453, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Check Total field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1834, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Check Total Option
			driver.findElement(By.xpath(excel.getData(3, 1834, 1))).click();
		}
		
		
		
		Thread.sleep(2000);
		//Check whether the Tender Details field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 454, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Tender Details Option
			driver.findElement(By.xpath(excel.getData(3, 454, 1))).click();
		}
		
		Thread.sleep(2000);
		//Click Customer Information Tab
		driver.findElement(By.xpath(excel.getData(3, 1835, 1))).click();
	
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		
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
		//Check whether the Email field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 460, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Email Option
			driver.findElement(By.xpath(excel.getData(3, 460, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Phone Number field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 461, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Phone Number Option
			driver.findElement(By.xpath(excel.getData(3, 461, 1))).click();
		}
		
		Thread.sleep(2000);
		//Clear Customer Information Notes
		driver.findElement(By.xpath(excel.getData(3, 1836, 1))).clear();
		//Enter Customer Information Notes
		driver.findElement(By.xpath(excel.getData(3, 1836, 1))).sendKeys("Visit Again...");
		
		Thread.sleep(2000);
		//Check whether the Print Primary/secondary Language field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1837, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Print Primary/Secondary Language Option
			driver.findElement(By.xpath(excel.getData(3, 1837, 1))).click();
		}
		  
		Thread.sleep(2000);
		//Click Additional Information Tab
		driver.findElement(By.xpath(excel.getData(3, 1838, 1))).click();
	
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		
		
		Thread.sleep(2000);
		//Check whether the Loyalty Amount field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 455, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Loyalty Amount Option
			driver.findElement(By.xpath(excel.getData(3, 455, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Tip Line field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 456, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Tip Line Option
			driver.findElement(By.xpath(excel.getData(3, 456, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Signature field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 457, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Signature Option
			driver.findElement(By.xpath(excel.getData(3, 457, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the E-Voice URL field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1817, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
					//Enable the Signature Option
					driver.findElement(By.xpath(excel.getData(3, 1817, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Tip Suggestion field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 458, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Tip Suggestion Option
			driver.findElement(By.xpath(excel.getData(3, 458, 1))).click();
		}
		
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
		}
		
				
		Thread.sleep(2000);
		//Check whether the Barcode field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 463, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Barcode Option
			driver.findElement(By.xpath(excel.getData(3, 463, 1))).click();
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
		//Check whether the Notes field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 466, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Notes Option
			driver.findElement(By.xpath(excel.getData(3, 466, 1))).click();
		}
				
		Thread.sleep(2000);
		//Check whether the Discount Under the Menu Item field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1839, 1))).isSelected()){}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Discount Under Menu Item print Option
			driver.findElement(By.xpath(excel.getData(3, 1839, 1))).click();
		}
	
		Thread.sleep(5000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		Thread.sleep(5000);
		//Click the update button
		driver.findElement(By.xpath(excel.getData(3, 467, 1))).click();
		
		Thread.sleep(4000);
		//Check weather the new denomination form saved or not
		if(driver.findElement(By.cssSelector(excel.getData(3, 239, 4))).getText().equalsIgnoreCase("Receipt template saved successfully"))
		{
			test.log(LogStatus.PASS, "Front End Receipt template Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Front End Receipt template Updated fail");
		}
		Thread.sleep(5000);wb.close();
		
		
	}
	
	@Test(priority=4,enabled = false)
	public void Front_End_Receipt_method_verifyUpdatedDetails(WebDriver driver) throws Exception
	{

        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		
		Thread.sleep(5000);
		//Click the Store Information 
		driver.findElement(By.xpath(excel.getData(3, 1823, 1))).click();
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		
		Thread.sleep(2000);
		//Check whether the store name field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 417, 1))).isSelected())
				{
					test.log(LogStatus.PASS, "Store name button is enabled");
				}
				else
				{
					test.log(LogStatus.FAIL, "Store name button is disabled");
				}
						
		Thread.sleep(1000);
		//Check whether the Store Logo is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 416, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Store Logo button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Store Logo button is disabled");
		}

		Thread.sleep(1000);
		//Check whether the store Address name field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1824, 1))).isSelected())
				{
					test.log(LogStatus.PASS, "Store Address name button is enabled");
				}
				else
				{
					test.log(LogStatus.FAIL, "Store Address name button is disabled");
				}
				
		
				Thread.sleep(1000);
				//Check whether the store Email field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1825, 1))).isSelected())
				{
					test.log(LogStatus.PASS, "Store  Email button is enabled");
				}
				else
				{
					test.log(LogStatus.FAIL, "Store  Email button is disabled");
				}
								
				Thread.sleep(1000);
				//Check whether the store Phone Number field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1826, 1))).isSelected())
				{
					test.log(LogStatus.PASS, "Store  Phone Number button is enabled");
				}
				else
				{
					test.log(LogStatus.FAIL, "Store Phone Number button is disabled");
				}
				
				Thread.sleep(1000);
				//Select Check Details
				driver.findElement(By.xpath(excel.getData(3, 1828, 1))).click();
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				Thread.sleep(2000);
		//Check whether the table name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 418, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Table name button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Table name button is disabled");
		}
		
		
		Thread.sleep(1000);
		//Check whether the seat number field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 419, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Seat Number button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Seat Number button is disabled");
		}
		
		
		Thread.sleep(1000);
		//Check whether the Check Open Server field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 420, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Open Server button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Open Server button is disabled");
		}
		
		
		Thread.sleep(1000);
		//Check whether the Check Close Server field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 421, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Close Server button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Close Server button is disabled");
		}
		Thread.sleep(3000);
		//Check whether the Check number field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 422, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Check number button is enabled");
		
			/*Thread.sleep(3000);
			//Check whether the Selected Font Size is displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 1840, 1))).getText().equalsIgnoreCase("2 Height"))		
			{
				test.log(LogStatus.PASS, "2 Height font size is displayed");
			}
			else
			{
				test.log(LogStatus.FAIL, "2 Height font size is not displayed");
			}*/
		}
		else
		{
			test.log(LogStatus.FAIL, "Check number button is disabled");
		}
		
		Thread.sleep(1000);
		//Check whether the Open Date and Time field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 425, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Open Date and Time button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Open Date and Time button is disabled");
		}
		
		
		Thread.sleep(1000);
		//Check whether the Close Date and Time field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 426, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Close Date and Time button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Close Date and Time button is disabled");
		}
		
		Thread.sleep(1000);
		//Check whether the Customer name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 427, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Customer name button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer name button is disabled");
		}
		
		
		Thread.sleep(1000);
		//Check whether the Service Type field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 428, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Service Type button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Service Type button is disabled");
		}
		
		Thread.sleep(2000);
		//Click Order Summary Tab
		driver.findElement(By.xpath(excel.getData(3, 1833, 1))).click();
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);

		
		Thread.sleep(2000);
		//Check whether the Show Secondary menu name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 429, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Secondary menu name button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Secondary menu name button is disabled");
		}
		
		Thread.sleep(1000);
		//Check whether the Roll Out Modifier Price To Menu field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 430, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Roll Out Modifier Price To Menu is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Roll Out Modifier Price To Menu button is disabled");
		}
		
				
		Thread.sleep(1000);
		//Check whether the Roll Out Modifier Price & Quantity To Menu field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 446, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Roll Out Modifier Price & Quantity To Menu button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Roll Out Modifier Price & Quantity To Menu button is not enabled");
		}
		
		Thread.sleep(1000);
		//Check whether the Roll Out Modifier To Menu field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 447, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Roll Out Modifier To Menu button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Roll Out Modifier To Menu button is not enabled");
		}
		
		Thread.sleep(1000);
		//Check whether the Exclude zero price menu item field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 448, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Exclude zero price menu item button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Exclude zero price menu item button is disabled");
		}
		
		Thread.sleep(1000);
		//Check whether the Exclude Zero Price Modifier field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 449, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Exclude Zero Price Modifier button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Exclude Zero Price Modifier button is disabled");
		}
		
		Thread.sleep(1000);
		//Check whether the Tax Summary field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 450, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Tax Summary button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tax Summary button is disabled");
		}
		
		Thread.sleep(1000);
		//Check whether the Gratuity field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 451, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Gratuity button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Gratuity button is disabled");
		}
		
		Thread.sleep(1000);
		//Check whether the Tip field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 452, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Tip button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tip button is disabled");
		}
		
		
		Thread.sleep(1000);
		//Check whether the Cash Discount field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 453, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Cash Discount button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Cash Discount button is not enabled");
		}
		
		Thread.sleep(1000);
		//Check whether the Check Total field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1834, 1))).isSelected())
				{
					test.log(LogStatus.PASS, "Check Total button is enabled");
				}
				else
				{
					test.log(LogStatus.FAIL, "Check Total button is not enabled");
				}
		
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);

				Thread.sleep(1000);
//Check whether the Tender Details field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 454, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Tender Details button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tender Details button is disabled");
		}
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
	
		Thread.sleep(4000);
		//Click Customer Information Tab
		Thread.sleep(2000);
		//Click Order Summary Tab
		driver.findElement(By.xpath(excel.getData(3, 1835, 1))).click();
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		
		
		Thread.sleep(2000);
		//Check whether the Address field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 459, 1))).isSelected())		
				{
					test.log(LogStatus.PASS, "Address button is enabled");
				}
				else
				{
					test.log(LogStatus.FAIL, "Address button is disabled");
				}		
		
				
				Thread.sleep(1000);
				//Check whether the Email field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 460, 1))).isSelected())		
				{
					test.log(LogStatus.PASS, "Email button is enabled");
				}
				else
				{
					test.log(LogStatus.FAIL, "Email button is disabled");
				}
				
				Thread.sleep(1000);
				//Check whether the Phone Number field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 461, 1))).isSelected())		
				{
					test.log(LogStatus.PASS, "Phone Number button is enabled");
				}
				else
				{
					test.log(LogStatus.FAIL, "Phone Number button is disabled");
				}

				Thread.sleep(1000);
				//Check whether the Print Primary/secondary Language field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1837, 1))).isSelected())		
				{
					test.log(LogStatus.PASS, "Print Primary/secondary Language button is enabled");
				}
				else
				{
					test.log(LogStatus.FAIL, "Print Primary/secondary Language button is disabled");
				}

				Thread.sleep(2000);
				//Click Additional Information Tab
				driver.findElement(By.xpath(excel.getData(3, 1838, 1))).click();
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				
		Thread.sleep(2000);
		//Check whether the Loyalty Amount field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 455, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Loyalty Amount button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Loyalty Amount button is disabled");
		}
		
		Thread.sleep(1000);
		//Check whether the Tip Line field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 456, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Tip Line button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tip Line button is disabled");
		}
		
		Thread.sleep(1000);
		//Check whether the Signature field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 457, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Signature button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Signature button is disabled");
		}
		
		
		Thread.sleep(1000);
		//Check whether the E-Voice URL field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1817, 1))).isSelected())		
				{
					test.log(LogStatus.PASS, "E-Voice URL button is enabled");
				}
				else
				{
					test.log(LogStatus.FAIL, "E-Voice URL button is disabled");
				}
		
				Thread.sleep(1000);
		//Check whether the Tip Suggestion field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 458, 1))).isSelected())	
		{
			test.log(LogStatus.PASS, "Tip Suggestion button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tip Suggestion button is disabled");
		}
		
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		
		Thread.sleep(1000);
		//Check whether the Barcode field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 463, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Barcode button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Barcode button is disabled");
		}
		
		
		Thread.sleep(1000);
		//Check whether the Cut Paper After Each print field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 465, 1))).isSelected())		
		{
			test.log(LogStatus.PASS, "Cut Paper After Each print button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Cut Paper After Each print button is disabled");
		}
		
		Thread.sleep(1000);
		//Check whether the Notes field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 466, 1))).isSelected())		
		{
			
			test.log(LogStatus.PASS, "Notes button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Notes button is disabled");
		}
		
		Thread.sleep(1000);
		//Check whether the Discount Under the Menu Item field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1839, 1))).isSelected())		
				{
					
					test.log(LogStatus.PASS, "Discount Under the Menu Item button is enabled");
				}
				else
				{
					test.log(LogStatus.FAIL, "Discount Under the Menu Item button is disabled");
				}
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		}
		Thread.sleep(5000);wb.close();
	}
	
}
