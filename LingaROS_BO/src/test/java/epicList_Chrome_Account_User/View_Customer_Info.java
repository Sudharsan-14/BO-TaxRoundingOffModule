package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

import epicList_Chrome_Account_User.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class View_Customer_Info {	
	public WebDriver driver;
ExtentReports rep = ExtentManager.getInstance();
ExtentTest test = rep.startTest("View_Customer_Info");
	
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
			Customer_Info_Method_open_Page(driver);
			Customer_Info_Method_viewpage(driver);
			Thread.sleep(1500);
		}
		
	@Test(enabled=false,priority=2)
	public void Customer_Info_Method_open_Page(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	  
		Thread.sleep(3000);
/*		//Click the Customers option
		driver.findElement(By.xpath("//span[.='Customers']")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Customers Info']"));
		//Scroll the page till the Customers Info option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
	    //Click the Customers Info Option		
		driver.findElement(By.xpath("//span[.='Customers Info']")).click();*/
		
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"customers");
		Thread.sleep(5000);
		//Check Customers Info page opened or not
		if(driver.findElement(By.xpath(excel.getData(5, 90, 1))).getText().equalsIgnoreCase("Customers"))
		{
			test.log(LogStatus.PASS,"Customers Info page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL,"Customers Info page loaded Failed");
		}
		Thread.sleep(2000);wb.close();
	}
	
	@Test(enabled=false,priority=2)
	public void Customer_Info_Method_viewpage(WebDriver driver) throws Exception
	{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	  

		Thread.sleep(2000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 71, 1))).clear();
		//Enter the key word
		driver.findElement(By.xpath(excel.getData(5, 71, 1))).sendKeys("Linga bo");
		//Click the search button
		//driver.findElement(By.xpath("//a[@class='btn customer-search-icon']")).click();
		
		Thread.sleep(2000);
		//Click the View button
		driver.findElement(By.xpath(excel.getData(5,91, 1))).click();
		
		Thread.sleep(5000);
		//Refresh button
		driver.findElement(By.xpath(excel.getData(5, 98, 1))).click();
		
		/*WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		 html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		*/
		 Thread.sleep(5000);
		   //Scroll the web page
		    for(int i=1; i <= 15; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		    }
		    
		    Thread.sleep(5000);
			//Check whether the Total Spent report is available or not
			if(driver.findElement(By.xpath(excel.getData(5, 92, 1))).getText().equalsIgnoreCase("Total Spent"))
			{
				test.log(LogStatus.PASS, "There is Total Spent Report is available");
			}
			else
			{
				test.log(LogStatus.FAIL, "Total Spent report is not available");
			}
			
			Thread.sleep(5000);
			//Check whether the Weekly Spent report is available or not
			if(driver.findElement(By.xpath(excel.getData(5, 93, 1))).getText().equalsIgnoreCase("Weekly Spent"))
			{
				test.log(LogStatus.PASS, "There is Weekly Spent Report is available");
			}
			else
			{
				test.log(LogStatus.FAIL, "Weekly Spent report is not available");
			}
			
			Thread.sleep(5000);
		    //Check Whether the NO. OF VISIT and Sale amount Available or not
		    if(driver.findElement(By.xpath(excel.getData(5, 94, 1))).getText().equalsIgnoreCase("NO. OF VISIT") && driver.findElement(By.xpath(excel.getData(5, 96, 1))).getText().equalsIgnoreCase("SALE AMOUNT"))
		    {
	
		    	test.log(LogStatus.PASS, "Number of visit count is : "+driver.findElement(By.xpath(excel.getData(5, 95, 1))).getText());
		    	
		    	test.log(LogStatus.PASS, "Total Sale amount is : "+driver.findElement(By.xpath(excel.getData(5, 97, 1))).getText());
		    }
		    
		    else if(driver.findElement(By.xpath(excel.getData(5, 94, 1))).getText().equalsIgnoreCase("NO. OF VISIT") || driver.findElement(By.xpath(excel.getData(5, 96, 1))).getText().equalsIgnoreCase("SALE AMOUNT"))
		    {
		    	if(driver.findElement(By.xpath(excel.getData(5 ,95, 1))).getText().equalsIgnoreCase("NO. OF VISIT"))
		    	{
		    		test.log(LogStatus.PASS, "Number of visit count is : "+driver.findElement(By.xpath(excel.getData(5, 97, 1))).getText());
		    		
		    		test.log(LogStatus.FAIL, "Sale amount field is not available");
		    	}
		    	
		    	else if(driver.findElement(By.xpath(excel.getData(5, 96, 1))).getText().equalsIgnoreCase("SALE AMOUNT"))
		    	{
		    		test.log(LogStatus.FAIL, "Number of visit field is not available");	
		    		
		    		test.log(LogStatus.PASS, "Total Sale amount is : "+driver.findElement(By.xpath(excel.getData(5, 97, 1))).getText());
		
		    	}
		    }
		    else
		    {
		    	test.log(LogStatus.FAIL, "Number of visit field is not available");
		    	
		    	test.log(LogStatus.FAIL, "Sale amount field is not available");
		    }
		    
			Thread.sleep(5000);
		    //Check Whether the LOYALTY REWARD and LOYALTY VALUE Available or not
		    if(driver.findElement(By.xpath(excel.getData(5, 101, 1))).getText().equalsIgnoreCase("LOYALTY REWARD") && driver.findElement(By.xpath(excel.getData(5, 102, 1))).getText().equalsIgnoreCase("LOYALTY VALUE"))
		    {
	
		    	test.log(LogStatus.PASS, "LOYALTY REWARD is : "+driver.findElement(By.xpath(excel.getData(5, 101, 1))).getText());
		    	
		    	test.log(LogStatus.PASS, "LOYALTY VALUE is : "+driver.findElement(By.xpath(excel.getData(5, 102, 1))).getText());
		    }
		    
		    else if(driver.findElement(By.xpath(excel.getData(5, 101, 1))).getText().equalsIgnoreCase("LOYALTY REWARD") || driver.findElement(By.xpath(excel.getData(5,102, 1))).getText().equalsIgnoreCase("LOYALTY VALUE"))
		    {
		    	if(driver.findElement(By.xpath(excel.getData(5,101, 1))).getText().equalsIgnoreCase("LOYALTY REWARD"))
		    	{
		    		test.log(LogStatus.PASS, "LOYALTY REWARD is : "+driver.findElement(By.xpath(excel.getData(5, 104, 1))).getText());
		    		
		    		test.log(LogStatus.FAIL, "LOYALTY VALUE field is not available");
		    	}
		    	
		    	else if(driver.findElement(By.xpath(excel.getData(5, 102, 1))).getText().equalsIgnoreCase("LOYALTY VALUE"))
		    	{
		    		test.log(LogStatus.FAIL, "LOYALTY REWARD field is not available");
		    		
		    		test.log(LogStatus.PASS, "LOYALTY VALUE is : "+driver.findElement(By.xpath(excel.getData(5, 103, 1))).getText());
		
		    	}
		    }
		    else
		    {
		    	test.log(LogStatus.FAIL, "LOYALTY REWARD field is not available");
		    	
		    	test.log(LogStatus.FAIL, "LOYALTY VALUE field is not available");
		    }
		    
			Thread.sleep(5000);
		    //Check Whether the FEEDBACKS Available or not
		    if(driver.findElement(By.xpath(excel.getData(5, 99, 1))).getText().equalsIgnoreCase("FEEDBACKS"))
		    {
		    	test.log(LogStatus.PASS, "FEEDBACKS field is available");
		    	
		    	test.log(LogStatus.INFO, "Positive Feedback Count is : "+driver.findElement(By.xpath(excel.getData(5, 100, 1))).getText());
		    	
		    	test.log(LogStatus.INFO, "Negative Feedback Count is : "+driver.findElement(By.xpath(excel.getData(5, 100, 1))).getText());
		    }
		    else
		    {
		    	test.log(LogStatus.FAIL, "FEEDBACKS field is not available");
		    }
		    Thread.sleep(5000);
		    //view the Sales
		  	driver.findElement(By.xpath(excel.getData(5, 105, 1))).click();
		  	
		  	try
		  	{
			  	Thread.sleep(2000);
			  	//Click the check
			  	driver.findElement(By.xpath(excel.getData(5, 106, 1))).click();
			  	Thread.sleep(5000);
			    //Click the mail id Enter box
			  	Thread.sleep(2000);           //
			  	driver.findElement(By.xpath(excel.getData(5, 107, 1))).click();
			   	//clear the mail id
			  	driver.findElement(By.xpath(excel.getData(5, 107, 1))).clear();
			  	//Enter the mail id
			  	driver.findElement(By.xpath(excel.getData(5, 107, 1))).sendKeys("nbchandana@lingaros.com");
			  	Thread.sleep(5000);
			  	//click the sendEmailReceipt button
			  	driver.findElement(By.xpath(excel.getData(5, 108, 1))).click();
			  	Thread.sleep(5000);
			    	
			    //Scroll the web page
			    for(int i=1; i <= 60; i++)
			    {
			    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			    }
			    
			    	test.log(LogStatus.INFO, "The Check number is : "+driver.findElement(By.xpath(excel.getData(5, 109, 1))).getText());
			    	
			    	test.log(LogStatus.INFO, "Date is : "+driver.findElement(By.xpath(excel.getData(5, 110, 1))).getText());
			    	
			    	test.log(LogStatus.INFO, "Table is : "+driver.findElement(By.xpath(excel.getData(5, 111, 1))).getText());
			    	
			    	test.log(LogStatus.INFO, "Seat Number is : "+driver.findElement(By.xpath(excel.getData(5, 112, 1))).getText());
			    	
			    	test.log(LogStatus.INFO, "Server Name is : "+driver.findElement(By.xpath(excel.getData(5, 113, 1))).getText());
			    	
			    	test.log(LogStatus.INFO, "Gratuity is : "+driver.findElement(By.xpath(excel.getData(5, 114, 1))).getText());
			    	
			    	test.log(LogStatus.INFO, "Discount is : "+driver.findElement(By.xpath(excel.getData(5, 115, 1))).getText());
			    	
			    	test.log(LogStatus.INFO, "CC Service Charge is : "+driver.findElement(By.xpath(excel.getData(5, 116, 1))).getText());
			    	
			    	test.log(LogStatus.INFO, "Total is : "+driver.findElement(By.xpath(excel.getData(5, 117, 1))).getText());
			    	
			    	test.log(LogStatus.INFO, "Total Tip is : "+driver.findElement(By.xpath(excel.getData(5, 118, 1))).getText());
			   
			    	//Get the row size of tax table
			    	List<WebElement> taxRow = driver.findElements(By.xpath(excel.getData(5, 119, 1)));
			    	
			    	Thread.sleep(1000);
			    	test.log(LogStatus.PASS, "Total Tax amount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[3]/div/div[1]/table/tbody/tr["+taxRow.size()+"]/td[2]")).getText());
			    		
			    	//Check whether the Payment summary is available or not
			    	if(driver.findElement(By.xpath(excel.getData(5, 120, 1))) != null)
			    	{
			    		test.log(LogStatus.PASS, "Payment Summary report is available");
			    	}
			    	
			    	else
			    	{
			    		test.log(LogStatus.FAIL, "Payment Summary report is not available");
			    	}
			      //Get the row size of order summary table
			    	List<WebElement> orderSummaryRow = driver.findElements(By.xpath(excel.getData(5, 121, 1)));
			    	
			    		Thread.sleep(1000);
			    		test.log(LogStatus.PASS, "Order Summary subtotal is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[3]/div[4]/div[2]/div/div/div[2]/div/div[2]/table/tbody/tr["+orderSummaryRow.size()+"]/td[2]")).getText());
		
			    		Thread.sleep(5000);
			    		
			    		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			    		for(int i = 1; i <= 15; i++)
			    		{
			    			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			    		}
			    		
						Thread.sleep(3500);
			    		//click the Back button
					  	driver.findElement(By.xpath(excel.getData(5, 122, 1))).click();
					  	Thread.sleep(5000);
					  	
		  	}
		  	catch(Exception g)
		  	{
		  		test.log(LogStatus.INFO, "There is no sales available");
		  	}
					
				  	driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				  	
				  	Thread.sleep(3000);
				  	//Click the Back Button
				  	driver.findElement(By.xpath(excel.getData(5, 122, 1))).click();

		    		Thread.sleep(5000);
		    		//Clear the search field
		    		driver.findElement(By.xpath(excel.getData(5, 71, 1))).clear();
		    		//Click the search button
		    		//driver.findElement(By.xpath("//a[@ class='btn customer-search-icon']")).click();
		    	
		    		if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);
		    		//watchTutorial(driver);
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
		driver.findElement(By.xpath(excel.getData(5, 123, 1))).click();
		WebElement iframe = driver.findElement(By.xpath(excel.getData(5, 124, 1)));
		driver.switchTo().frame(iframe);
		Thread.sleep(3500);
		try
		{
			if(driver.findElement(By.xpath(excel.getData(5, 123, 1))).isDisplayed()&&driver.findElement(By.xpath(excel.getData(5, 126, 1))).isDisplayed())
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
		driver.findElement(By.xpath(excel.getData(5, 124, 1))).click();
		
		wb.close();
		
	}
}
