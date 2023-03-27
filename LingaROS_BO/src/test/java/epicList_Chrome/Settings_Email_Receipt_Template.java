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
import org.openqa.selenium.interactions.Actions;
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


public class Settings_Email_Receipt_Template {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Email_Receipt_Template");
	
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
			Email_Receipt_Template_method_open_Email_Receipt_Template(driver);
			Email_Receipt_Template_method_update_Email_Receipt_Template(driver);
			Verify_Email_Receipt_Template_method_update_Email_Receipt_Template(driver);
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
	@Test(priority=8,enabled = false)
	public void Email_Receipt_Template_method_open_Email_Receipt_Template(WebDriver driver) throws Exception
	{

        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		/*//Click the Settings option
		driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[5]/a/span[1]")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Printer Configuration']"));
		//Scroll the page till the Printer Configuration option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Store Option		
		driver.findElement(By.xpath("//span[.='Printer Configuration']")).click();*/
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"newEmailTemplate");
		Thread.sleep(8000);
		try
		{
		//Check Email Receipt Template page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 469, 1))).getText().equalsIgnoreCase("Email Receipt Template"))
		{
			test.log(LogStatus.PASS, "Email Receipt Template page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Email Receipt Template page loaded Failed");
		
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
		
	@Test(priority=9,enabled = false)
	public void Email_Receipt_Template_method_update_Email_Receipt_Template(WebDriver driver) throws Exception
	{ 
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
	
		Thread.sleep(3000);
		try
		{
		//Select the required Banner Background using Act Class
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div[1]/div[2]/div[1]/div/div/div/div[8]")).click();
		}
		catch(Exception e) {}
		
		Thread.sleep(5000);	
		//Check whether the Store Image is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 471, 1))).isSelected())
		{
			
		}
		else
		{
			//Enable the Store Image option
			driver.findElement(By.xpath(excel.getData(3, 471, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Customer Feedback field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 483, 1))).isSelected()){
		
		}
		else
		{
			//Enable the Customer Feedback Option
			driver.findElement(By.xpath(excel.getData(3, 483, 1))).click();
		}
		
		Thread.sleep(2000);
		//Clear Logo URL
		driver.findElement(By.xpath(excel.getData(3, 1842, 1))).clear();
		//Enter Logo URL
		driver.findElement(By.xpath(excel.getData(3, 1842, 1))).sendKeys("http://www.rajasaferider.com");
	
		
		Thread.sleep(2000);
		//Check whether the seat number field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 419, 1))).isSelected()){
		}
		else
		{
			//Enable the seat number Option
			driver.findElement(By.xpath(excel.getData(3, 419, 1))).click();
		}
				
		Thread.sleep(2000);
		//Check whether the table name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 418, 1))).isSelected()){
		}
		else
		{
			//Enable the table name Option
			driver.findElement(By.xpath(excel.getData(3, 418, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Check number field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 422, 1))).isSelected())
		{
		}
		else
		{
			//Enable the Check number Option
			driver.findElement(By.xpath(excel.getData(3, 422, 1))).click();
					
		}

		
			Thread.sleep(2000);
		//Check whether the Server name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 420, 1))).isSelected()){
		}
		else
		{
			//Enable the Server name Option
			driver.findElement(By.xpath(excel.getData(3, 420, 1))).click();
		}

		//Check whether the Customer name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 427, 1))).isSelected()){
		}
		else
		{
			//Enable the customer name Option
			driver.findElement(By.xpath(excel.getData(3, 427, 1))).click();
		}

				
		Thread.sleep(2000);
		//Check whether the Check Total field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1834, 1))).isSelected())
				{
				}
				else
				{
					//Enable the Check Total Option
					driver.findElement(By.xpath(excel.getData(3, 1834, 1))).click();
							

				}
				

				//Check whether the Order Summary field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 484, 1))).isSelected()){
				}
				else
				{
					//Enable the Order Summary Option
					driver.findElement(By.xpath(excel.getData(3, 484, 1))).click();
				}
				
		//Check whether the Date field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 425, 1))).isSelected()){
		}
		else
		{
			//Enable the Date Option
			driver.findElement(By.xpath(excel.getData(3, 425, 1))).click();
		}
	
		
		//Check whether the Roll Out Modifier Price To Menu field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 430, 1))).isSelected()){
		}
		else
		{
			//Enable the  Roll Out Modifier Price To Menu Option
			driver.findElement(By.xpath(excel.getData(3, 430, 1))).click();
		}

		
		//Check whether the Service Type field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 428, 1))).isSelected()){
		}
		else
		{
			//Enable the Service Type Option
			driver.findElement(By.xpath(excel.getData(3, 428, 1))).click();
		}		
		
		for(int i=1; i <= 8; i++)
	    {
	    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
	    	Thread.sleep(1000);
	    }
		
		//Check whether the Exclude Zero Price Modifier field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 449, 1))).isSelected()){
		}
		else
		{
			//Enable the Exclude Zero Price Modifier item Option
			driver.findElement(By.xpath(excel.getData(3, 449, 1))).click();
		}

		
		//Check whether the Show Secondary menu name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 429, 1))).isSelected()){
		}
		else
		{
			//Enable the  Show Secondary menu name Option
			driver.findElement(By.xpath(excel.getData(3, 429, 1))).click();
		}
	
		
		//Check whether the Tax Summary field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 450, 1))).isSelected()){
		}
		else
		{
			//Enable the Tax Summary Option
			driver.findElement(By.xpath(excel.getData(3, 450, 1))).click();
		}
	
		//Check whether the Exclude zero price menu item field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 448, 1))).isSelected()){
		}
		else
		{
			//Enable the Exclude zero price menu item Option
			driver.findElement(By.xpath(excel.getData(3, 448, 1))).click();
		}
		
		//Check whether the Signature field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 457, 1))).isSelected()){
		}
		else
		{
			//Enable the Signature Option
			driver.findElement(By.xpath(excel.getData(3, 457, 1))).click();
		}

		
		for(int i =0; i <=5; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		Thread.sleep(3000);
		
				
		//Check whether the Tip field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 452, 1))).isSelected()){
		}
		else
		{
			//Enable the Tip Option
			driver.findElement(By.xpath(excel.getData(3, 452, 1))).click();
		}
		

		Thread.sleep(1000);
		//Check whether the Address field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 469, 1))).isSelected()){
		}
		else
		{
			//Enable the Address Option
			driver.findElement(By.xpath(excel.getData(3, 469, 1))).click();
		}
		
		Thread.sleep(2000);
		//Check whether the Tender Details field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 454, 1))).isSelected()){
		}
		else
		{
			//Enable the Tender Details Option
			driver.findElement(By.xpath(excel.getData(3, 454, 1))).click();
		}
	
		//Check whether the Phone Number field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 461, 1))).isSelected()){
		}
		else
		{
			//Enable the Phone Number Option
			driver.findElement(By.xpath(excel.getData(3, 461, 1))).click();
		}


		//Check whether the Barcode field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 463, 1))).isSelected()){
		}
		else
		{
			//Enable the Barcode Option
			driver.findElement(By.xpath(excel.getData(3, 463, 1))).click();
		}
		

		//Check whether the Social Icons field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 475, 1))).isSelected())
		{
			//Check whether the Face book Icons field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 476, 1))).isSelected())
			{
				Thread.sleep(2000);
				//Clear the FB Link
				driver.findElement(By.xpath(excel.getData(3, 1843, 1))).clear();
				//Enter the Facebook Link
				driver.findElement(By.xpath(excel.getData(3, 1843, 1))).sendKeys("http://facebook.com/raja9220");
				
			} 
			
			else
			{
				//Enable the Face book Icons Option
				driver.findElement(By.xpath(excel.getData(3, 476, 1))).click();
				Thread.sleep(2000);
				//Clear the FB Link
				driver.findElement(By.xpath(excel.getData(3, 1843, 1))).clear();
				//Enter the Facebook Link
				driver.findElement(By.xpath(excel.getData(3, 1843, 1))).sendKeys("http://facebook.com/raja9220");
			}
			
			//Check whether the Twitter Icons field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 478, 1))).isSelected())
			{
				
				Thread.sleep(2000);
				//Clear the FB Link
				driver.findElement(By.xpath(excel.getData(3, 1844, 1))).clear();
				//Enter the Facebook Link
				driver.findElement(By.xpath(excel.getData(3, 1844, 1))).sendKeys("http://twitter.com/raja9220");
			}
			
			else
			{
				//Enable the Twitter Icons Option
				driver.findElement(By.xpath(excel.getData(3, 478, 1))).click();
				Thread.sleep(2000);
				//Clear the Twitter Link
				driver.findElement(By.xpath(excel.getData(3, 1844, 1))).clear();
				//Enter the Twitter Link
				driver.findElement(By.xpath(excel.getData(3, 1844, 1))).sendKeys("http://twitter.com/raja9220");
			}
			
			//Check whether the Linked In Icons field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 479, 1))).isSelected())
			{
				
				Thread.sleep(2000);
				//Clear the LinkedIn Link
				driver.findElement(By.xpath(excel.getData(3, 1845, 1))).clear();
				//Enter the LinkedIn Link
				driver.findElement(By.xpath(excel.getData(3, 1845, 1))).sendKeys("http://linkedin.com/raja9220");
			}
			
			else
			{
				//Enable the Linked In Icons Option
				driver.findElement(By.xpath(excel.getData(3, 479, 1))).click();
				Thread.sleep(2000);
				//Clear the FB Link
				driver.findElement(By.xpath(excel.getData(3, 1845, 1))).clear();
				//Enter the Facebook Link
				driver.findElement(By.xpath(excel.getData(3, 1845, 1))).sendKeys("http://linkedin.com/raja9220");
			}
		}
		
		else
		{
			//Enable the Social Icons Option
			driver.findElement(By.xpath(excel.getData(3, 475, 1))).click();
		
			//Check whether the Face book Icons field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 476, 1))).isSelected())
			{
			
				Thread.sleep(2000);
				//Clear the FB Link
				driver.findElement(By.xpath(excel.getData(3, 1843, 1))).clear();
				//Enter the Facebook Link
				driver.findElement(By.xpath(excel.getData(3, 1843, 1))).sendKeys("http://facebook.com/raja9220");
			}
			
			else
			{
				//Enable the Face book Icons Option
				driver.findElement(By.xpath(excel.getData(3, 476, 1))).click();
				Thread.sleep(2000);
				//Clear the FB Link
				driver.findElement(By.xpath(excel.getData(3, 1843, 1))).clear();
				//Enter the Facebook Link
				driver.findElement(By.xpath(excel.getData(3, 1843, 1))).sendKeys("http://facebook.com/raja9220");
			}
			
			//Check whether the Twitter Icons field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 478, 1))).isSelected())
			{
				Thread.sleep(2000);
				//Clear the Twitter Link
				driver.findElement(By.xpath(excel.getData(3, 1844, 1))).clear();
				//Enter the Twitter Link
				driver.findElement(By.xpath(excel.getData(3, 1844, 1))).sendKeys("http://twitter.com/raja9220");

			}
			
			else
			{
				//Enable the Twitter Icons Option
				driver.findElement(By.xpath(excel.getData(3, 478, 1))).click();
				Thread.sleep(2000);
				//Clear the FB Link
				driver.findElement(By.xpath(excel.getData(3, 1844, 1))).clear();
				//Enter the Facebook Link
				driver.findElement(By.xpath(excel.getData(3, 1844, 1))).sendKeys("http://twitter.com/raja9220");

			}
			
			//Check whether the Linked In Icons field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 479, 1))).isSelected())
			{

				Thread.sleep(2000);
				//Clear the LinkedIn Link
				driver.findElement(By.xpath(excel.getData(3, 1845, 1))).clear();
				//Enter the LinkedIn Link
				driver.findElement(By.xpath(excel.getData(3, 1845, 1))).sendKeys("http://linkedin.com/raja9220");
	
			}
			
			else
			{
				//Enable the Linked In Icons Option
				driver.findElement(By.xpath(excel.getData(3, 479, 1))).click();

				Thread.sleep(2000);
				//Clear the LinkedIn Link
				driver.findElement(By.xpath(excel.getData(3, 1845, 1))).clear();
				//Enter the LinkedIn Link
				driver.findElement(By.xpath(excel.getData(3, 1845, 1))).sendKeys("http://linkedin.com/raja9220");

			}
		}


		//Check whether the Email field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 460, 1))).isSelected()){
		}
		else
		{
			//Enable the Email Option
			driver.findElement(By.xpath(excel.getData(3, 460, 1))).click();
		}
		
		//Check whether the Discount Under Menu Item field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1839, 1))).isSelected()){
		}
		else
		{
			//Enable the Phone Number Option
			driver.findElement(By.xpath(excel.getData(3, 1839, 1))).click();
		}

			
			 //Scroll the web page
		    for(int i=1; i <= 5; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		    	Thread.sleep(1000);
		    }
		    
					
		Thread.sleep(2000);
		//Check whether the Show Linga POS Promotion "Powered by Linga POS" field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 481, 1))).isSelected()){
		
		}
		else
		{
			//Enable the Show Linga POS Promotion "Powered by Linga POS" Option
			driver.findElement(By.xpath(excel.getData(3, 481, 1))).click();
		}							
		
		//Clear the Notes
		driver.findElement(By.xpath(excel.getData(3, 1836, 1))).clear();
		//Enter the Notes
		driver.findElement(By.xpath(excel.getData(3, 1836, 1))).sendKeys("Have a nice day, Visit us again!");

		
	//	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		Thread.sleep(5000);
		//Click the update button
		driver.findElement(By.xpath(excel.getData(3, 482, 1))).click();
		Thread.sleep(4000);
		//Check weather the new denomination form saved or not
		WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,60);
		
		if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Email template saved successfully"))
		{
			test.log(LogStatus.PASS, "Email Receipt template saved successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email Receipt template saved fail");
		}
		Thread.sleep(4000);wb.close();		
	
	}

	@Test(priority=10,enabled = false)
	public void Verify_Email_Receipt_Template_method_update_Email_Receipt_Template(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
		 FileInputStream fis = new FileInputStream(src);
			
		 XSSFWorkbook wb = new XSSFWorkbook(fis);
			
		 XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
		 ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);
			
		//Check whether the Store Image is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 471, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Store Image Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Store Image Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Customer Feedback field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 483, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Customer Feedback Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer Feedback Field is Disabled in the Email Receipt Template");
		}
		
		
		//Check whether the seat number field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 419, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Seat Number Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Seat Number Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the table name field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 418, 1))).isSelected())
				{
					test.log(LogStatus.PASS, "Table Name Field is Enabled in the Email Receipt Template");
				}
				else
				{
					test.log(LogStatus.FAIL, "Table Name Field is Disabled in the Email Receipt Template");
				}
	
				
				//Check whether the Check number field is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 422, 1))).isSelected())
				{
					test.log(LogStatus.PASS, "Check number Field is Enabled in the Email Receipt Template");
				}
				else
				{
					test.log(LogStatus.FAIL, "Check number Field is Disabled in the Email Receipt Template");
				}
		
		//Check whether the Server name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 420, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Server Name Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Server Name Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Customer name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 427, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Customer name Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer name Field is Disabled in the Email Receipt Template");
		}
	
		//Check whether the Check Total field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1834, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Check Total Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Check Total Field is Disabled in the Email Receipt Template");
		}
	
		//Check whether the Order Summary field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 484, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Order Summary Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Order Summary Field is Disabled in the Email Receipt Template");
		}

		//Check whether the Date field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 425, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Date Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Date Field is Disabled in the Email Receipt Template");
		}
			
		//Check whether the Roll Out Modifier Price To Menu field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 430, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Roll Out Modifier Price To Menu Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Roll Out Modifier Price To Menu Field is Disabled in the Email Receipt Template");
		}
	
		//Check whether the Service Type field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 428, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Service Type Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Service Type Field is Disabled in the Email Receipt Template");
		}		
		
	
		//Check whether the Exclude zero price menu item field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 448, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Exclude zero price menu item Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Exclude zero price menu item Field is Disabled in the Email Receipt Template");
		}

		//Check whether the Show Secondary menu name field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 429, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Show Secondary menu name Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Show Secondary menu name Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Tax Summary field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 450, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Tax Summary Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tax Summary Field is Disabled in the Email Receipt Template");
		}

		
		for(int i =0; i <=12; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
			
		//Check whether the Exclude Zero Price Modifier field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 449, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Exclude Zero Price Modifier Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Exclude Zero Price Modifier Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Signature field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 457, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Signature Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Signature Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Tip field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 452, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Tip Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tip Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Address field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 459, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Address Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Address Field is Disabled in the Email Receipt Template");
		}
			
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		//Check whether the Tender Details field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 454, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Tender Details Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tender Details Field is Disabled in the Email Receipt Template");
		}
				
		//Check whether the Phone Number field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 461, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Phone Number Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Phone Number Field is Disabled in the Email Receipt Template");
		}
	

		//Check whether the Barcode field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 463, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Barcode Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Barcode Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Social Icons field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 475, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Social Icons Field is Enabled in the Email Receipt Template");
			//Check whether the Face book Icons field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 476, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Face book Icons Field is Enabled in the Email Receipt Template");
			}
			else
			{
				test.log(LogStatus.FAIL, "Face book Icons Field is Disabled in the Email Receipt Template");
			}
			
			//Check whether the Twitter Icons field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 478, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Twitter Icons Field is Enabled in the Email Receipt Template");
			}
			else
			{
				test.log(LogStatus.FAIL, "Twitter Icons Field is Disabled in the Email Receipt Template");
			}
			
			//Check whether the Linked In Icons field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 479, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Linked In Icons Field is Enabled in the Email Receipt Template");
			}
			else
			{
				test.log(LogStatus.FAIL, "Linked In Icons Field is Disabled in the Email Receipt Template");
			}
		}
		
		else
		{
			test.log(LogStatus.FAIL, "Social Icons Field is Disabled in the Email Receipt Template");
		}

		
		//Check whether the Email field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 460, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Email Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Discount Under the Menu Item field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 460, 1))).isSelected())
		{
		test.log(LogStatus.PASS, "Discount Under the Menu Item Field is Enabled in the Email Receipt Template");
		}
		else
		{
		test.log(LogStatus.FAIL, "Discount Under the Menu Item Field is Disabled in the Email Receipt Template");
		}
		
		
		//Check whether the Show Linga POS Promotion "Powered by Linga POS" field is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 481, 1))).isSelected())
		{
			test.log(LogStatus.PASS, "Show Linga POS Promotion 'Powered by Linga POS' Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Show Linga POS Promotion 'Powered by Linga POS' Field is Disabled in the Email Receipt Template");
		}									

		Thread.sleep(5000);wb.close();
		
	}

}
