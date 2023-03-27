package epicList_Chrome;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

import epicList_Chrome.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Verify_Enterprise_Modifier_Sale_Report {

	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Enterprise_Modifier_Sale_Report");

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
			Enterprise_Modifier_Report_Method_For_openpage(driver);
			Enterprise_Modifier_Report_Method_For_Stores(driver);
			Enterprise_Modifier_Report_Method_For_Group(driver);
			Enterprise_Modifier_Report_Method_For_City(driver);
			Enterprise_Modifier_Report_Method_For_State(driver);
			Enterprise_Modifier_Report_Method_For_ZipCode(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=43)
		public void Enterprise_Modifier_Report_Method_For_openpage(WebDriver driver) throws Exception
		{
	/*		//Click the My Stores option
			driver.findElement(By.xpath("//span[.='My Stores']")).click();

			Thread.sleep(5000);
	        //Click the EnterPrise Reports Option		
			driver.findElement(By.xpath("//span[.='EnterPrise Reports']")).click();
			
			Thread.sleep(2000);
			//Click the Sale option
			driver.findElement(By.xpath("//span[.='Sale']")).click();*/
			
			Thread.sleep(2000);
			//Enter the URl
			driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/modifierSaleSummary");		
			Thread.sleep(5000);
			try
			{
			//Check Modifier Report page opened or not
			if(driver.findElement(By.xpath("//label[.='Modifier']")).getText().equalsIgnoreCase("Modifier"))
			{
				test.log(LogStatus.PASS, "Modifier Sale Report page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifier Sale Report page loaded Failed");
			
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
			Thread.sleep(5000);
			
		}
		
		
		@Test(enabled=false,priority=44)
		public void Enterprise_Modifier_Report_Method_For_Stores(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(2000);
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

			Thread.sleep(5000);
			System.out.println("********************* View The Store Report *************************");
			test.log(LogStatus.INFO, "********************* View The Store Report *************************");
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Group");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Stores");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);

			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			//Remove the store name
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[1]/div[2]/div/ul/li[1]/a")).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportProperty("Store"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Modifier Options
			driver.findElement(By.xpath(excel.getData(3, 2232, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2233, 1))).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2233, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			try
			{
				Thread.sleep(5000);
				//Check weather the report is available for the selected time period
				if(!driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "Modifier Sale Report available for Specific Date");
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(5000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath ("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr"));
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportProperty("Modifier_Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportProperty("Modifier_Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportProperty("Modifier_Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3); 
				        
				        //Check weather the Sale Amount Report is correct or not 
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText().equals(Utility.getReportProperty("Modifier_Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Sale Amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Sale Amount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Sale Amount Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Sale Amount
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Sale Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Sale Amount Value different is : "+different);	
				        }
				        
				        //Check weather the Quantity Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText().equals(Utility.getReportProperty("Modifier_Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Quantity Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Quantity Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Quantity Value is : "+ actual);
				        }
				        
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage of Sale
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Quantity Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Quantity Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Quantity Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText().equals(Utility.getReportProperty("Modifier_Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	

				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage of Sale
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date");
				
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

				
					
					}
				
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Modifier Sale Report is not available for Specific Date");

				
		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			Thread.sleep(5000);
			System.out.println("********************* End of Store Report *************************");
			test.log(LogStatus.INFO, "********************* End of Store Report *************************");

			
		}
		
		@Test(enabled=false,priority=45)
		public void Enterprise_Modifier_Report_Method_For_Group(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);
			System.out.println("********************* View The Group Report *************************");
			test.log(LogStatus.INFO, "********************* View The Group Report *************************");
			
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Group");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportProperty("Group"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Modifier Options
			driver.findElement(By.xpath(excel.getData(3, 2232, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2233, 1))).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2233, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			try
			{
				Thread.sleep(5000);
				//Check weather the report is available for the selected time period
				if(!driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "Modifier Sale Report available for Specific Date");
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(5000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath ("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr"));
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportProperty("Modifier_Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportProperty("Modifier_Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportProperty("Modifier_Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3); 
				        
				        //Check weather the Sale Amount Report is correct or not 
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText().equals(Utility.getReportProperty("Modifier_Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Sale Amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Sale Amount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Sale Amount Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Sale Amount
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Sale Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Sale Amount Value different is : "+different);	
				        }
				        
				        //Check weather the Quantity Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText().equals(Utility.getReportProperty("Modifier_Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Quantity Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Quantity Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Quantity Value is : "+ actual);
				        }
				        
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage of Sale
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Quantity Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Quantity Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Quantity Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText().equals(Utility.getReportProperty("Modifier_Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	

				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage of Sale
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date");
					
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));


						
					}
				
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Modifier Sale Report is not available for Specific Date");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(5000);
			System.out.println("********************* End of Group Report *************************");
			test.log(LogStatus.INFO, "********************* End of Group Report *************************");
		}
		
		@Test(enabled=false,priority=46)
		public void Enterprise_Modifier_Report_Method_For_City(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);
			System.out.println("********************* View The City Report *************************");
			test.log(LogStatus.INFO, "********************* View The City Report *************************");

			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("City");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportProperty("City"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Modifier Options
			driver.findElement(By.xpath(excel.getData(3, 2232, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2233, 1))).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2233, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			try
			{
				Thread.sleep(5000);
				//Check weather the report is available for the selected time period
				if(!driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "Modifier Sale Report available for Specific Date");
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(5000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath ("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr"));
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportProperty("Modifier_Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportProperty("Modifier_Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportProperty("Modifier_Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3); 
				        
				        //Check weather the Sale Amount Report is correct or not 
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText().equals(Utility.getReportProperty("Modifier_Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Sale Amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Sale Amount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Sale Amount Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Sale Amount
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Sale Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Sale Amount Value different is : "+different);	
				        }
				        
				        //Check weather the Quantity Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText().equals(Utility.getReportProperty("Modifier_Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Quantity Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Quantity Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Quantity Value is : "+ actual);
				        }
				        
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage of Sale
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Quantity Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Quantity Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Quantity Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText().equals(Utility.getReportProperty("Modifier_Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	

				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage of Sale
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date");
				
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

										
					}
				
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Modifier Sale Report is not available for Specific Date");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
							
			Thread.sleep(5000);
			System.out.println("********************* End of City Report *************************");
			test.log(LogStatus.INFO, "********************* End of City Report *************************");
		}
		
		@Test(enabled=false,priority=47)
		public void Enterprise_Modifier_Report_Method_For_State(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);
			System.out.println("********************* View The State Report *************************");
			test.log(LogStatus.INFO, "********************* View The State Report *************************");

			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("State");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportProperty("State"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Modifier Options
			driver.findElement(By.xpath(excel.getData(3, 2232, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2233, 1))).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2233, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			try
			{
				Thread.sleep(5000);
				//Check weather the report is available for the selected time period
				if(!driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "Modifier Sale Report available for Specific Date");
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(5000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath ("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr"));
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportProperty("Modifier_Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportProperty("Modifier_Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportProperty("Modifier_Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3); 
				        
				        //Check weather the Sale Amount Report is correct or not 
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText().equals(Utility.getReportProperty("Modifier_Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Sale Amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Sale Amount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Sale Amount Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Sale Amount
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Sale Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Sale Amount Value different is : "+different);	
				        }
				        
				        //Check weather the Quantity Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText().equals(Utility.getReportProperty("Modifier_Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Quantity Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Quantity Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Quantity Value is : "+ actual);
				        }
				        
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage of Sale
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Quantity Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Quantity Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Quantity Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText().equals(Utility.getReportProperty("Modifier_Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	

				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage of Sale
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date");
				
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));


					
					}
				
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Modifier Sale Report is not available for Specific Date");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(5000);
			System.out.println("********************* End of State Report *************************");
			test.log(LogStatus.INFO, "********************* End of State Report *************************");
		}
		
		@Test(enabled=false,priority=48)
		public void Enterprise_Modifier_Report_Method_For_ZipCode(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);
			System.out.println("********************* View The Zip Code Report *************************");
			test.log(LogStatus.INFO, "********************* View The Zip Code Report *************************");
			
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Zip Code");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportProperty("Zipcode"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Modifier Options
			driver.findElement(By.xpath(excel.getData(3, 2232, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2233, 1))).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2233, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			try
			{
				Thread.sleep(5000);
				//Check weather the report is available for the selected time period
				if(!driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "Modifier Sale Report available for Specific Date");
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(5000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath ("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr"));
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportProperty("Modifier_Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportProperty("Modifier_Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportProperty("Modifier_Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3); 
				        
				        //Check weather the Sale Amount Report is correct or not 
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText().equals(Utility.getReportProperty("Modifier_Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Sale Amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Sale Amount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Sale Amount Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Sale Amount
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Sale Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Sale Amount Value different is : "+different);	
				        }
				        
				        //Check weather the Quantity Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText().equals(Utility.getReportProperty("Modifier_Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Quantity Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Quantity Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Quantity Value is : "+ actual);
				        }
				        
						else if(expect2 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage of Sale
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Quantity Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect2;
				        	
				        	//Print the different value
				        	System.out.println("Quantity Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Quantity Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText().equals(Utility.getReportProperty("Modifier_Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	

				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage of Sale
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date");
					
						
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					
					
					}
				
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Modifier Sale Report is not available for Specific Date");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(5000);
			System.out.println("********************* End of Zip Code Report *************************");
			test.log(LogStatus.INFO, "********************* End of Zip Code Report *************************");

		}

}
