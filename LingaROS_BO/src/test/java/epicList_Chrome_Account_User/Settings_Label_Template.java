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


public class Settings_Label_Template {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Label_Template");
	
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
			Label_Template_method_open_Label_Template(driver);
			Label_Template_method_update_Label_Template(driver);
			Label_Template_method_update_Label_Template1(driver);
			Label_Template_method_update_Label_Template2(driver);
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
	
	@Test(priority=35,enabled = false)
	public void Label_Template_method_open_Label_Template(WebDriver driver) throws Exception
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
		Thread.sleep(15000);
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"newLabelTemplate");
		Thread.sleep(5000);		
		//Click Right Home arrow button
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/scrollable-tabset/div/button[2]")).click();
/*		driver.findElement(By.xpath("//div[@class='ui-tabs-scrollable']/button[2]")).click();
		driver.findElement(By.xpath("//div[@class='ui-tabs-scrollable']/button[2]")).click();*/

		Thread.sleep(4000);
		try
		{
		//Check Label Template page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1957, 1))).getText().equalsIgnoreCase("Label Template"))
		{
			test.log(LogStatus.PASS, "Label Template page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Label Template page loaded Failed");
		
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
		Thread.sleep(4000);wb.close();
	}	
		
	@Test(priority=36,enabled = false)
	public void Label_Template_method_update_Label_Template(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
		Thread.sleep(10000);
		//Click Template Option
		driver.findElement(By.xpath(excel.getData(3, 1958, 1))).click();
		//Select Template
		driver.findElement(By.xpath(excel.getData(3, 1959, 1))).sendKeys("Template#0");
		//Enter Template
		driver.findElement(By.xpath(excel.getData(3, 1959, 1))).sendKeys(Keys.ENTER);

	
		Thread.sleep(2000);
		//Clear the Width Field
		driver.findElement(By.xpath(excel.getData(3, 432, 1))).clear();
		//Enter the required Width Field
		driver.findElement(By.xpath(excel.getData(3, 432, 1))).sendKeys("3");
		
		
		Thread.sleep(2000);
		//Clear the Height field
		driver.findElement(By.xpath(excel.getData(3, 433, 1))).clear();
		//Enter the required Height field
		driver.findElement(By.xpath(excel.getData(3, 433, 1))).sendKeys("3");
				
		Thread.sleep(2000);
		//Select Header Font Size
		driver.findElement(By.xpath(excel.getData(3, 1968, 1))).click();
		//Enter Select Header Font Size
		driver.findElement(By.xpath(excel.getData(3, 1969, 1))).sendKeys("2 width");
		//Enter the Header Font Size
		driver.findElement(By.xpath(excel.getData(3, 1969, 1))).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(2000);
		//Select Customer Font Size
		driver.findElement(By.xpath(excel.getData(3, 1962, 1))).click();
		//Enter Select Customer Font Size
		driver.findElement(By.xpath(excel.getData(3, 1963, 1))).sendKeys("Normal");
		//Enter the Customer Font Size
		driver.findElement(By.xpath(excel.getData(3, 1963, 1))).sendKeys(Keys.ENTER);
		
		
		//Scroll the web page
	    for(int i=1; i <= 10; i++)
	    {
	    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
	    	Thread.sleep(1000);
	    }
	    
	    

		Thread.sleep(2000);
		//Select Menu Font Size
		driver.findElement(By.xpath(excel.getData(3, 1970, 1))).click();
		//Enter Select Menu Font Size
		driver.findElement(By.xpath(excel.getData(3, 1971, 1))).sendKeys("Emphasis");
		//Enter the Menu Font Size
		driver.findElement(By.xpath(excel.getData(3, 1971, 1))).sendKeys(Keys.ENTER);

		
		Thread.sleep(2000);
		//Select Modifier Note Font Size
		driver.findElement(By.xpath(excel.getData(3, 1966, 1))).click();
		//Enter Select Modifier Note Font Size
		driver.findElement(By.xpath(excel.getData(3, 1967, 1))).sendKeys("Normal");
		//Enter the Modifier Note Font Size
		driver.findElement(By.xpath(excel.getData(3, 1967, 1))).sendKeys(Keys.ENTER);
	    

	    Thread.sleep(2000);
		//Select Font Size
		driver.findElement(By.xpath(excel.getData(3, 1821, 1))).click();
		//Enter Font Size
		driver.findElement(By.xpath(excel.getData(3, 1822, 1))).sendKeys("Normal");
		//Enter the Font Size
		driver.findElement(By.xpath(excel.getData(3, 1822, 1))).sendKeys(Keys.ENTER);

		
		
		//Check Whether the Autocut is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 520, 1))).isSelected())
		{
			
		}
		else
		{
			Thread.sleep(3000);
			//Check whether the Autocut is enabled or not
			driver.findElement(By.xpath(excel.getData(3, 520, 1))).click();
		}
		
		Thread.sleep(3000);
		//Click the update button
		driver.findElement(By.xpath(excel.getData(3, 445, 1))).click();
		
		Thread.sleep(3000);
		//Check weather the new Label Template updated or not
				if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Delivery label template saved successfully"))
				{
					test.log(LogStatus.PASS, "Label template Updated successfully for Template#0");
				}
				else
				{
					test.log(LogStatus.FAIL, "Label template Updated fail for Template#0");
				}
				Thread.sleep(5000);wb.close();
							
		
	}
	
	@Test(priority=37,enabled = false)
	public void Label_Template_method_update_Label_Template1(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	 
		Thread.sleep(5000);
		//Click Template Option
		driver.findElement(By.xpath(excel.getData(3, 1958, 1))).click();
		//Select Template
		driver.findElement(By.xpath(excel.getData(3, 1959, 1))).sendKeys("Template#1");
		//Enter Template
		driver.findElement(By.xpath(excel.getData(3, 1959, 1))).sendKeys(Keys.ENTER);

	
		Thread.sleep(2000);
		//Clear the Width Field
		driver.findElement(By.xpath(excel.getData(3, 432, 1))).clear();
		//Enter the required Width Field
		driver.findElement(By.xpath(excel.getData(3, 432, 1))).sendKeys("2.5");
		
		
		Thread.sleep(2000);
		//Clear the Height field
		driver.findElement(By.xpath(excel.getData(3, 433, 1))).clear();
		//Enter the required Height field
		driver.findElement(By.xpath(excel.getData(3, 433, 1))).sendKeys("2");
				
		Thread.sleep(2000);
		//Select Address Font Size
		driver.findElement(By.xpath(excel.getData(3, 1960, 1))).click();
		//Enter Select Address Font Size
		driver.findElement(By.xpath(excel.getData(3, 1961, 1))).sendKeys("Normal");
		//Enter the Address Font Size
		driver.findElement(By.xpath(excel.getData(3, 1961, 1))).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(2000);
		//Select Customer Font Size
		driver.findElement(By.xpath(excel.getData(3, 1962, 1))).click();
		//Enter Select Customer Font Size
		driver.findElement(By.xpath(excel.getData(3, 1963, 1))).sendKeys("Normal");
		//Enter the Customer Font Size
		driver.findElement(By.xpath(excel.getData(3, 1963, 1))).sendKeys(Keys.ENTER);
	
		
		   Thread.sleep(2000);
			//Select Delivery Note Font Size
			driver.findElement(By.xpath(excel.getData(3, 1964, 1))).click();
			//Enter Select Delivery Note Font Size
			driver.findElement(By.xpath(excel.getData(3, 1965, 1))).sendKeys("Normal");
			//Enter the Delivery Note Font Size
			driver.findElement(By.xpath(excel.getData(3, 1965, 1))).sendKeys(Keys.ENTER);
		    
			
			Thread.sleep(2000);
			//Select Modifier Note Font Size
			driver.findElement(By.xpath(excel.getData(3, 1966, 1))).click();
			//Enter Select Modifier Note Font Size
			driver.findElement(By.xpath(excel.getData(3, 1967, 1))).sendKeys("Normal");
			//Enter the Modifier Note Font Size
			driver.findElement(By.xpath(excel.getData(3, 1967, 1))).sendKeys(Keys.ENTER);
		
			
			//Scroll the web page
		    for(int i=1; i <= 10; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		    	Thread.sleep(1000);
		    }

			Thread.sleep(2000);
			//Select Header Font Size
			driver.findElement(By.xpath(excel.getData(3, 1968, 1))).click();
			//Enter Select Header Font Size
			driver.findElement(By.xpath(excel.getData(3, 1969, 1))).sendKeys("2 width");
			//Enter the Header Font Size
			driver.findElement(By.xpath(excel.getData(3, 1969, 1))).sendKeys(Keys.ENTER);

		    
			Thread.sleep(2000);
			//Select Menu Font Size
			driver.findElement(By.xpath(excel.getData(3, 1970, 1))).click();
			//Enter Select Menu Font Size
			driver.findElement(By.xpath(excel.getData(3, 1971, 1))).sendKeys("Normal");
			//Enter the Menu Font Size
			driver.findElement(By.xpath(excel.getData(3, 1971, 1))).sendKeys(Keys.ENTER);
			
		
		//Check Whether the Autocut is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 520, 1))).isSelected())
		{
			
		}
		else
		{
			Thread.sleep(3000);
			//Check whether the Autocut is enabled or not
			driver.findElement(By.xpath(excel.getData(3, 520, 1))).click();
		}
		
		Thread.sleep(5000);
		//Click the update button
		driver.findElement(By.xpath(excel.getData(3, 445, 1))).click();
		
		
		Thread.sleep(4000);
		//Check weather the new Label Template updated or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Delivery label template saved successfully"))
		{
			test.log(LogStatus.PASS, "Label template Updated successfully for Template#1");
		}
		else
		{
			test.log(LogStatus.FAIL, "Label template Updated fail for Template#1");
		}
		Thread.sleep(3000);wb.close();
		
				
	}
	
	@Test(priority=38,enabled = false)
	public void Label_Template_method_update_Label_Template2(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	 
		Thread.sleep(5000);
		//Click Template Option
		driver.findElement(By.xpath(excel.getData(3, 1958, 1))).click();
		//Select Template
		driver.findElement(By.xpath(excel.getData(3, 1959, 1))).sendKeys("Template#2");
		//Enter Template
		driver.findElement(By.xpath(excel.getData(3, 1959, 1))).sendKeys(Keys.ENTER);


		//Clear the Width Field
		driver.findElement(By.xpath(excel.getData(3, 432, 1))).clear();
		//Enter the required Width Field
		driver.findElement(By.xpath(excel.getData(3, 432, 1))).sendKeys("2.5");
		
		
		//Clear the Height field
		driver.findElement(By.xpath(excel.getData(3, 433, 1))).clear();
		//Enter the required Height field
		driver.findElement(By.xpath(excel.getData(3, 433, 1))).sendKeys("3");
				
		Thread.sleep(2000);
		//Select Address Font Size
		driver.findElement(By.xpath(excel.getData(3, 1960, 1))).click();
		//Enter Select Address Font Size
		driver.findElement(By.xpath(excel.getData(3, 1961, 1))).sendKeys("2 width");
		//Enter the Address Font Size
		driver.findElement(By.xpath(excel.getData(3, 1961, 1))).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(2000);
		//Select Customer Font Size
		driver.findElement(By.xpath(excel.getData(3, 1962, 1))).click();
		//Enter Select Customer Font Size
		driver.findElement(By.xpath(excel.getData(3, 1963, 1))).sendKeys("2 width");
		//Enter the Customer Font Size
		driver.findElement(By.xpath(excel.getData(3, 1963, 1))).sendKeys(Keys.ENTER);
	
		
	    Thread.sleep(2000);
			//Select Delivery Note Font Size
			driver.findElement(By.xpath(excel.getData(3, 1964, 1))).click();
			//Enter Select Delivery Note Font Size
			driver.findElement(By.xpath(excel.getData(3, 1965, 1))).sendKeys("2 width");
			//Enter the Delivery Note Font Size
			driver.findElement(By.xpath(excel.getData(3, 1965, 1))).sendKeys(Keys.ENTER);
		    
			
			Thread.sleep(2000);
			//Select Modifier Note Font Size
			driver.findElement(By.xpath(excel.getData(3, 1966, 1))).click();
			//Enter Select Modifier Note Font Size
			driver.findElement(By.xpath(excel.getData(3, 1967, 1))).sendKeys("Emphasis");
			//Enter the Modifier Note Font Size
			driver.findElement(By.xpath(excel.getData(3, 1967, 1))).sendKeys(Keys.ENTER);
		
			 //Scroll the web page
		    for(int i=1; i <= 10; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		    	Thread.sleep(1000);
		    }
		    

			Thread.sleep(2000);
			//Select Header Font Size
			driver.findElement(By.xpath(excel.getData(3, 1968, 1))).click();
			//Enter Select Header Font Size
			driver.findElement(By.xpath(excel.getData(3, 1969, 1))).sendKeys("Normal");
			//Enter the Header Font Size
			driver.findElement(By.xpath(excel.getData(3, 1969, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Select Menu Font Size
			driver.findElement(By.xpath(excel.getData(3, 1970, 1))).click();
			//Enter Select Menu Font Size
			driver.findElement(By.xpath(excel.getData(3, 1971, 1))).sendKeys("Emphasis");
			//Enter the Menu Font Size
			driver.findElement(By.xpath(excel.getData(3, 1971, 1))).sendKeys(Keys.ENTER);
			

		
		//Check Whether the Autocut is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 520, 1))).isSelected())
		{
			
		}
		else
		{
			Thread.sleep(3000);
			//Check whether the Autocut is enabled or not
			driver.findElement(By.xpath(excel.getData(3, 520, 1))).click();
		}	
		
		Thread.sleep(5000);
		//Click the update button
		driver.findElement(By.xpath(excel.getData(3,445, 1))).click();
		
		Thread.sleep(4000);
		//Check weather the new Label Template updated or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Delivery label template saved successfully"))
		{
			test.log(LogStatus.PASS, "Label template Updated successfully for Template#2");
		}
		else
		{
			test.log(LogStatus.FAIL, "Label template Updated fail for Template#2");
		}
		Thread.sleep(3000);wb.close();
		
				
	}
}
