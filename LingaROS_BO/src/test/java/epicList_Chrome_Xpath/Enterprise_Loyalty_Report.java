package epicList_Chrome_Xpath;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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


public class Enterprise_Loyalty_Report {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Enterprise_Loyalty_Report");

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
		{
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
			{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath("//b[.='User Login']")).getText().equalsIgnoreCase("User Login"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully for Dealer App");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed for Dealer App");
			}
			Thread.sleep(3000);
			//Close the Browser
			driver.close();
		}
		else
		{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath("//div[@id='x-content-band-1']/div/div[2]/h2")).getText().equalsIgnoreCase("Account Login"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully LingaPOs");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed LingaPos");
			}
			Thread.sleep(3000);
			//Close the Browser
			driver.close();
		}
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
			Loyalty_Report_open_page(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=15)
		public void Loyalty_Report_open_page(WebDriver driver) throws Exception
		{
			//Enter the URl
			Thread.sleep(3000);
			driver.get(Utility.getProperty("Enterprise_Base_URL")+"loyaltyReport");
		
			Thread.sleep(3000);
		//Click Time period option
		driver.findElement(By.xpath("//form[@name='loyaltyReport']/div[1]/div/div/a")).click();
		//Enter the required Time period option
		driver.findElement(By.xpath("//form[@name='loyaltyReport']/div[1]/div/div/div/div/input")).sendKeys("Date Range");
		//Click Time period option
		driver.findElement(By.xpath("//form[@name='loyaltyReport']/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Click Date_Range_From option
		driver.findElement(By.xpath("//form[@name='loyaltyReport']/div[2]/div/div/input[1]")).clear();
		//Enter the required Date_Range_From
		driver.findElement(By.xpath("//form[@name='loyaltyReport']/div[2]/div/div/input[1]")).sendKeys(Utility.getReportProperty("Date_Range_From"));
		//Enter Date_Range_From
		driver.findElement(By.xpath("//form[@name='loyaltyReport']/div[2]/div/div/input[1]")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Click Date_Range_To option
		driver.findElement(By.xpath("//form[@name='loyaltyReport']/div[2]/div/div/input[2]")).clear();
		//Enter the required Time Date_Range_To
		driver.findElement(By.xpath("//form[@name='loyaltyReport']/div[2]/div/div/input[2]")).sendKeys(Utility.getReportProperty("Date_Range_To"));
		//Enter Date_Range_To option
		driver.findElement(By.xpath("//form[@name='loyaltyReport']/div[2]/div/div/input[2]")).sendKeys(Keys.ENTER);
			
		Thread.sleep(3000);
		//Click Customer Name option
		driver.findElement(By.xpath("//form[@name='loyaltyReport']/div[3]/div/a")).click();
		//Enter the required Customer Name
		driver.findElement(By.xpath("//form[@name='loyaltyReport']/div[3]/div/div/div/input")).sendKeys(Utility.getProperty("Loyalty_Customer_Name"));
		//driver.findElement(By.xpath("//form[@name='loyaltyReport']/div[3]/div/div/div/input")).sendKeys(Utility.getProperty("Loyalty_Customer_Name"));
		//Enter Customer Name
		driver.findElement(By.xpath("//form[@name='loyaltyReport']/div[3]/div/div/div/input")).sendKeys(Keys.ENTER);
			
		Thread.sleep(3000);
		//Click run button
		driver.findElement(By.xpath("//form[@name='loyaltyReport']/div[4]/div/div/button")).click();
		
		Thread.sleep(10000);
		
		//Check weather the report is available for the selected time period
		if(driver.findElement(By.xpath("//h3[.='No transaction for selected time period']")).isDisplayed())
		{
			test.log(LogStatus.FAIL, "Transaction Report(By Tender Type) is not available for Specific Date");
		}
		//Check weather the table format report is available or not
		else if(driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]")).getText()!=null)
		{
			test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
			
			Thread.sleep(3000);
	        //No.of rows
	        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr"));
	      
	        for(int i = 2; i <= rows.size(); i++)
	        {
	        	if(i == rows.size())
	        	{
	        		test.log(LogStatus.INFO, "Customer Name(Loyalty Report) : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[1]")).getText());
	        		test.log(LogStatus.INFO, "Signup Date(Loyalty Report) : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[2]")).getText());
	        		test.log(LogStatus.INFO, "Last Redemption Date(Loyalty Report) : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[3]")).getText());
	        		test.log(LogStatus.INFO, "Last Accumulation Date(Loyalty Report) : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[4]")).getText());
	        		test.log(LogStatus.INFO, "Total Loyalty Points(Loyalty Report) : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[5]/span")).getText());
	        		test.log(LogStatus.INFO, "Total Redeemed Loyalty Points(Loyalty Report) : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText());
	        	}
	        }
		}
		else
		{
			test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date");
		}
	/*	        //Print number of Rows
	        System.out.println("Number of Rows are : "+rows);*/
	        
	    	
/*	    	//Replace all commo's with empty space
	    	String expected1 = Utility.getReportProperty("Loyalty_Customer_Name").replace(",", "");
	    	
	    	//Convert the String value of the Sale_Report_Sale_Amount element into float value
	    	float expect1 = Float.parseFloat(expected1);  
	    		        	
	    	//Replace all commo's with empty space
	    	String expected2 = Utility.getReportProperty("Loyalty_Sign_upDate").replace(",", "");
	    	
	    	Float.parseFloat(expected2);  
	    	
	    	//Replace all commo's with empty space
	    	String expected3 = Utility.getReportProperty("Loyalty_Last_Redemption_Date").replace(",", "");
	    	
	    	Float.parseFloat(expected3);  

	    	//Replace all commo's with empty space
	    	String expected4 = Utility.getReportProperty("Loyalty_Last_AccumulationDate").replace(",", "");
	    	
	    	Float.parseFloat(expected4);  
	    	
	    	//Replace all commo's with empty space
	    	String expected5 = Utility.getReportProperty("Total_Redeemed_LoyaltyPoints").replace(",", "");
	    	
	    	Float.parseFloat(expected5);                              
	        
	    	//Replace all commo's with empty space
	    	String expected6 = Utility.getReportProperty("Total_LoyaltyPoints").replace(",", "");
	    	
	    	Float.parseFloat(expected6);                  
	        //Check weather the Sale Amount Report is correct or not
	        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText().equals(Utility.getReportProperty("Sale_Report_Sale_Amount")))
	        {
	        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Sale Amount");
	        	
	        	//Get the Total value of Sale Amount
	        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
	        	
	        	//Replace all commo's with empty space
	        	String actualText= actualText1.replace(",", "");
	        	
	        	//Convert the String value of the Sale Amount Total element into float value
	        	float actual = Float.parseFloat(actualText);
	        	
	        	//Print the actual value
	        	System.out.println("The Actual Sale Amount Value is : "+actual);
	        	
	        	test.log(LogStatus.PASS, "The Last Accumulation Date is : "+ actual);
	        }
	        
			else if(expect1 == unknownValue)
			{
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");
				
	        	//Get the Total value of Check Count
	        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();

	        	System.out.println("The Actual Sale Amount value is : "+actualText);
	        	
	        	test.log(LogStatus.INFO, "Total Loyalty Points value is : "+actualText);
			}
	        
	        else
	        {
	        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Sale Amount");
	        	
	        	//Get the Total value of Sale Amount
	        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div/table/tbody/tr["+rows.size()+"]/td[2]/span")).getText();
	        	
	        	//Replace all commo's with empty space
	        	String actualText= actualText1.replace(",", "");
	        	
	        	//Convert the String value of the sale amount Total element into float value
	        	float actual = Float.parseFloat(actualText);
	        			        	
	        	//Get the different
	        	float different = actual - expect1;
	        	
	        	//Print the different value
	        	System.out.println("Sale Amount Value different is : "+different);
	        	
	        	test.log(LogStatus.FAIL, "Total Redeemed Loyalty Points different is : "+different);	
	        }
		}*/
		
		}

}
