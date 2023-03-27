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


public class Settings_Kitchen_Label_Template {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Kitchen_Label_Template");
	
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
			Kitchen_Label_Template_method_open_Kitchen_Label_Template(driver);
			Kitchen_Label_Template_method_update_Kitchen_Label_Template(driver);
			Verify_Kitchen_Label_Template_method_Kitchen_Label_Template(driver);
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
	
	@Test(priority=32,enabled = false)
	public void Kitchen_Label_Template_method_open_Kitchen_Label_Template(WebDriver driver) throws Exception
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
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"newKitchenLabelTemplate");
		Thread.sleep(5000);
		try
		{
		//Check Kitchen Label Template page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1950, 1))).getText().equalsIgnoreCase("Kitchen Label Template"))
		{
			test.log(LogStatus.PASS, "Kitchen Label Template page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Kitchen Label Template page loaded Failed");
		
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
		Thread.sleep(3000);wb.close();
	}	
		
	@Test(priority=33,enabled = false)
	public void Kitchen_Label_Template_method_update_Kitchen_Label_Template(WebDriver driver) throws Exception
	{

        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Clear the Width Field
		driver.findElement(By.xpath(excel.getData(3, 432, 1))).clear();
		//Enter the required Width Field
		driver.findElement(By.xpath(excel.getData(3, 432, 1))).sendKeys("1");
		
		Thread.sleep(2000);
		//Clear the Height field
		driver.findElement(By.xpath(excel.getData(3, 433, 1))).clear();
		//Enter the required Height field
		driver.findElement(By.xpath(excel.getData(3, 433, 1))).sendKeys("2");
		
		Thread.sleep(2500);
		//Select Header Font Size
		driver.findElement(By.xpath(excel.getData(3, 1951, 1))).click();
		Thread.sleep(1000);
		//Enter Select Header Font Size
		driver.findElement(By.xpath(excel.getData(3, 1952, 1))).sendKeys("2 width");
		//Enter the Header Font Size
		driver.findElement(By.xpath(excel.getData(3, 1952, 1))).sendKeys(Keys.ENTER);
		
		 Thread.sleep(2000);
		//Check whether the Show Date & Time enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 435, 1))).isSelected())
        {}
        else
        {
        	Thread.sleep(2000);
        	//Enable the Show Date & Time
        driver.findElement(By.xpath(excel.getData(3, 435, 1))).click();
        }
       
		Thread.sleep(2000);
    	 //Check whether the Order Type is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 436, 1))).isSelected())
		{}
		else
		{
			Thread.sleep(2000);
        		//Enable the Order Type option
			driver.findElement(By.xpath(excel.getData(3, 436, 1))).click();	
		}
				
		Thread.sleep(2000);
    			//Check whether the Sale number is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 437, 1))).isSelected())
		{}
		else
		{
			Thread.sleep(2000);
        		//Enable the SAle Number option
			driver.findElement(By.xpath(excel.getData(3, 437, 1))).click();
		}		
		
		Thread.sleep(2000);
    			//Check whether the Order number is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 438, 1))).isSelected())
		{}
		else
		{
			Thread.sleep(2000);
        		//Enable the Order Number option
			driver.findElement(By.xpath(excel.getData(3, 438, 1))).click();
		}
		
		Thread.sleep(2000);
    			//Check whether the Customer Name is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 439, 1))).isSelected())
		{}
		else
		{
			Thread.sleep(2000);
        		//Enable the Customer Name option
			driver.findElement(By.xpath(excel.getData(3, 439, 1))).click();
		}
		
		Thread.sleep(2000);
		//Select Header Font Size
		driver.findElement(By.xpath(excel.getData(3, 1953, 1))).click();
		//Enter Select Header Font Size
		driver.findElement(By.xpath(excel.getData(3, 1954, 1))).sendKeys("2 width");
		//Enter the Header Font Size
		driver.findElement(By.xpath(excel.getData(3, 1954, 1))).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(2000);
		//Select Header Font Size
		driver.findElement(By.xpath(excel.getData(3, 1955, 1))).click();
		//Enter Select Header Font Size
		driver.findElement(By.xpath(excel.getData(3, 1956, 1))).sendKeys("2 width");
		//Enter the Header Font Size
		driver.findElement(By.xpath(excel.getData(3, 1956, 1))).sendKeys(Keys.ENTER);
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		Thread.sleep(2000);
		//Check whether the Autocut is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 442, 1))).isSelected())
		{}
		else
		{
			Thread.sleep(2000);
        				//Enable the Autocut option
			driver.findElement(By.xpath(excel.getData(3, 442, 1))).click();
		}
		
		Thread.sleep(2000);
    			//Check whether the Show line seperator in Header is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 443, 1))).isSelected())
		{}
		else
		{
			Thread.sleep(2000);
        		//Enable the Show line seperator in Header option
			driver.findElement(By.xpath(excel.getData(3, 443, 1))).click();
		}
		
		Thread.sleep(2000);
    			//Check whether the Show line seperator in Footer is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 444, 1))).isSelected())
		{}
		else
		{
			Thread.sleep(2000);
        		//Enable the Show line seperator in Footer option
			driver.findElement(By.xpath(excel.getData(3, 444, 1))).click();
		}
		Thread.sleep(5000);
		
		//Click the update button
		driver.findElement(By.xpath(excel.getData(3, 445, 1))).click();
		
		Thread.sleep(3000);
    	//Check weather the new Label Template updated or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Label template saved successfully"))
		{
			test.log(LogStatus.PASS, "Kitchen Label template Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Kitchen Label template Updated fail");
		}
		Thread.sleep(3000);wb.close();
	}

	@Test(priority=34,enabled = false)
	public void Verify_Kitchen_Label_Template_method_Kitchen_Label_Template(WebDriver driver) throws Exception
	{
		
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(5000);
		//Check whether the Show Date & Time enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 435, 1))).isSelected())
        {
			test.log(LogStatus.PASS, "Show Date & Time enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Show Date & Time disabled in Kitchen Label Template");
        }
       
		Thread.sleep(4000);
		//Check whether the Order Type is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 436, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Order Type enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Order Type disabled in Kitchen Label Template");
        }
				
		Thread.sleep(4000);
		//Check whether the Sale number is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 437, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Sale number enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Sale number disabled in Kitchen Label Template");
        }

		Thread.sleep(3000);
		//Check whether the Order number is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 438, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Order number enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Order number disabled in Kitchen Label Template");
        }
		
		Thread.sleep(4000);
		//Check whether the Customer Name is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 439, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Customer Name enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Customer Name disabled in Kitchen Label Template"); 
        }
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}Thread.sleep(3000);
		
		Thread.sleep(4000);
		//Check whether the Autocut is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 442, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Autocut enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Autocut disabled in Kitchen Label Template");
        }
		
		Thread.sleep(4000);
		//Check whether the Show line seperator in Header is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 443, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Show line seperator in Header enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Show line seperator in Header disabled in Kitchen Label Template");
        }
		
		Thread.sleep(4000);
		//Check whether the Show line seperator in Footer is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 444, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Show line seperator in Footer enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Show line seperator in Footer disabled in Kitchen Label Template");
        }
		Thread.sleep(5000);wb.close();

	}

}
