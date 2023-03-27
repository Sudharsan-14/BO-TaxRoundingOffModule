package epicList_Chrome_Account_User;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome_Account_User.Utility;

public class Verify_Enterprise_Menu_Item_Sale_Report {

	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Enterprise_Menu_Item_Sale_Report");

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
		{
/*			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
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
			//Close the Browser_Account_Level_User
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
			//Close the Browser_Account_Level_User
			driver.close();
		}*/
			Browser_Account_Level_User a=new Browser_Account_Level_User();
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
			Enterprise_Menu_Item_Report_Method_For_openpage(driver);
			Enterprise_Menu_Item_Report_Method_For_Stores(driver);
			Enterprise_Menu_Item_Report_Method_For_Group(driver);
			Enterprise_Menu_Item_Report_Method_For_City(driver);
			Enterprise_Menu_Item_Report_Method_For_State(driver);
			Enterprise_Menu_Item_Report_Method_For_ZipCode(driver);
			Enterprise_Menu_Item_Report_Method_For_Stores_WithSplitByServingSize(driver);
			Enterprise_Menu_Item_Report_Method_For_Group_WithSplitByServingSize(driver);
			Enterprise_Menu_Item_Report_Method_For_City_WithSplitByServingSize(driver);
			Enterprise_Menu_Item_Report_Method_For_State_WithSplitByServingSize(driver);
			Enterprise_Menu_Item_Report_Method_For_ZipCode_WithSplitByServingSize(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=32)
		public void Enterprise_Menu_Item_Report_Method_For_openpage(WebDriver driver) throws Exception
		{
		/*	//Click the My Stores option
			driver.findElement(By.xpath("//span[.='My Stores']")).click();

			Thread.sleep(5000);
	        //Click the EnterPrise Reports Option		
			driver.findElement(By.xpath("//span[.='EnterPrise Reports']")).click();
			
			Thread.sleep(2000);
			//Click the Sale option
			driver.findElement(By.xpath("//span[.='Sale']")).click();	*/
			
			Thread.sleep(8000);
			//Enter the URl
			driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/menuItemSaleSummary");
			
			Thread.sleep(5000);
			try
			{
			//Check Menu Item Report page opened or not
			if(driver.findElement(By.xpath("//label[.='Menu Item']")).getText().equalsIgnoreCase("Menu Item"))
			{
				test.log(LogStatus.PASS, "Menu Item Sale Report page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Menu Item Sale Report page loaded Failed");
			
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
		
		@Test(enabled=false,priority=33)
		public void Enterprise_Menu_Item_Report_Method_For_Stores(WebDriver driver) throws Exception
		{

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
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/a")).click();	
			//Enter the required filter
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys("Group");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(1500);
			//Click the Select option
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/a")).click();	
			//Enter the required filter
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys("Stores");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys(Keys.ENTER);

			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul")).click();
			//Remove the store name
			//driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li[1]/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Utility.getReportPropertyUser("Store"));
			//Press the Enter button
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Sub Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Menu Item Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Serving Size level Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Check whether the Split by serving size level option is enabled or not
			if(driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).isSelected())
			{
				//Enable or disable the Split by Serving Size Level
				driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).click();
			}
			else
			{
				
			}
			
			Thread.sleep(3000);
			//Click the Menu Item Options
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/a")).click();
			//Enter the required option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys("All");
			//Press the Enter button
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			Thread.sleep(1500);
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1500);
			//Click the Run button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));
			
			try
			{
				
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Menu Item Sale Report available for Specific Date");
					
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(5000);
					//Check Weather the Top 5 Menu Item sale available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[1]")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Menu Item Sale Report available for Specific Date");
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);
						
						//Click the Pie chart option
						driver.findElement(By.xpath("//a[.='Pie']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Pie Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Polar Area chart option
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/ul/li[2]/a")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Polar Area Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Bar chart option
						driver.findElement(By.xpath("//a[.='Bar']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Bar Chart available");
						Thread.sleep(5000);
					}
					else
					{
						test.log(LogStatus.FAIL, "Here Top 5 Menu Item Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[2]/div/canvas")).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Menu Item sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table")).isDisplayed())
					{								
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(5000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  

			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Percentage_Of_Sale element into float value
			        	float expect5 = Float.parseFloat(expected5);                              
				        
				        //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
				        
				        //Check weather the Percentage Of Sale Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Percentage Of Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Percentage Of Sale Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage Of Sale
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();

				        	System.out.println("The Actual Percentage Of Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Percentage Of Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage Of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Percentage Of Sale Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Percentage Of Sale Value different is : "+different);	
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
				
				else
				{
					test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");
			
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				
				
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");

				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));

			
			}
					
			Thread.sleep(5000);
			System.out.println("********************* End of Store Report *************************");
			test.log(LogStatus.INFO, "********************* End of Store Report *************************");

			
		}
		
		@Test(enabled=false,priority=34)
		public void Enterprise_Menu_Item_Report_Method_For_Group(WebDriver driver) throws Exception
		{

			Thread.sleep(5000);
			System.out.println("********************* View The Group Report *************************");
			test.log(LogStatus.INFO, "********************* View The Group Report *************************");
			
			//Click the Select option
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/a")).click();
			//Enter the required filter
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys("Group");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys((Utility.getReportPropertyUser("Group")));
			//Press the Enter button
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Sub Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Menu Item Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the serving size Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			//Check whether the Split by serving size level option is enabled or not
			if(driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).isSelected())
			{
				//Enable or disable the Split by Serving Size Level
				driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).click();
			}
			else
			{
				
			}
			
			Thread.sleep(1500);
			//Click the Menu Item Options
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/a")).click();
			//Enter the required option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys("All");
			//Press the Enter button
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			long start = System.currentTimeMillis();
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));
			
			
			try
			{
				
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Menu Item Sale Report available for Specific Date");
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					
					Thread.sleep(5000);
					//Check Weather the Top 5 Menu Item sale available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[1]")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Menu Item Sale Report available for Specific Date");
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);
						
						//Click the Pie chart option
						driver.findElement(By.xpath("//a[.='Pie']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Pie Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Polar Area chart option
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/ul/li[2]/a")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Polar Area Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Bar chart option
						driver.findElement(By.xpath("//a[.='Bar']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Bar Chart available");
						Thread.sleep(5000);
					}
					else
					{
						test.log(LogStatus.FAIL, "Here Top 5 Menu Item Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[2]/div/canvas")).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Menu Item sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table")).isDisplayed())
					{								
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(5000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  

			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Percentage_Of_Sale element into float value
			        	float expect5 = Float.parseFloat(expected5);                              
				        
				        //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
				        
				        //Check weather the Percentage Of Sale Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Percentage Of Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Percentage Of Sale Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage Of Sale
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();

				        	System.out.println("The Actual Percentage Of Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Percentage Of Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage Of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Percentage Of Sale Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Percentage Of Sale Value different is : "+different);	
				        }
				        
				        //Check weather the Rounding Off Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Rounding_Off")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Rounding Off");
				        	
				        	//Get the Total value of Rounding Off
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Rounding Off Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Rounding Off Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Rounding Off Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Rounding Off
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
		
				        	System.out.println("The Actual Rounding Off value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Rounding Off value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Rounding Off");
				        	
				        	//Get the Total value of Rounding Off
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Rounding Off Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Rounding Off Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	
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
				
				else
				{
					test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");
				
			    	
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));


				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");

				
		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(5000);
			System.out.println("********************* End of Group Report *************************");
			test.log(LogStatus.INFO, "********************* End of Group Report *************************");
		}
		
		@Test(enabled=false,priority=35)
		public void Enterprise_Menu_Item_Report_Method_For_City(WebDriver driver) throws Exception
		{

			Thread.sleep(5000);
			System.out.println("********************* View The City Report *************************");
			test.log(LogStatus.INFO, "********************* View The City Report *************************");

			//Click the Select option
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/a")).click();
			//Enter the required filter
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys("City");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Utility.getReportPropertyUser("City"));
			//Press the Enter button
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Sub Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Menu Item Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Serving size level Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			//Check whether the Split by serving size level option is enabled or not
			if(driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).isSelected())
			{
				//Enable or disable the Split by Serving Size Level
				driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).click();
			}
			else
			{
				
			}
			
			Thread.sleep(1500);
			//Click the Menu Item Options
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/a")).click();
			//Enter the required option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys("All");
			//Press the Enter button
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));
			
			
			try
			{
				
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Menu Item Sale Report available for Specific Date");
					
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(5000);
					//Check Weather the Top 5 Menu Item sale available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[1]")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Menu Item Sale Report available for Specific Date");
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);
						
						//Click the Pie chart option
						driver.findElement(By.xpath("//a[.='Pie']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Pie Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Polar Area chart option
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/ul/li[2]/a")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Polar Area Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Bar chart option
						driver.findElement(By.xpath("//a[.='Bar']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Bar Chart available");
						Thread.sleep(5000);
					}
					else
					{
						test.log(LogStatus.FAIL, "Here Top 5 Menu Item Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[2]/div/canvas")).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Menu Item sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table")).isDisplayed())
					{								
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(5000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  

			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Percentage_Of_Sale element into float value
			        	float expect5 = Float.parseFloat(expected5);                              
				        
				        //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
				        
				        //Check weather the Percentage Of Sale Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Percentage Of Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Percentage Of Sale Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage Of Sale
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();

				        	System.out.println("The Actual Percentage Of Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Percentage Of Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage Of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Percentage Of Sale Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Percentage Of Sale Value different is : "+different);	
				        }
				        
				        //Check weather the Rounding Off Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Rounding_Off")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Rounding Off");
				        	
				        	//Get the Total value of Rounding Off
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Rounding Off Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Rounding Off Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Rounding Off Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Rounding Off
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
		
				        	System.out.println("The Actual Rounding Off value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Rounding Off value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Rounding Off");
				        	
				        	//Get the Total value of Rounding Off
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Rounding Off Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Rounding Off Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	
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
				
				else
				{
					test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");
			
			    	
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));


				
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
							
			Thread.sleep(5000);
			System.out.println("********************* End of City Report *************************");
			test.log(LogStatus.INFO, "********************* End of City Report *************************");
		}
		
		@Test(enabled=false,priority=36)
		public void Enterprise_Menu_Item_Report_Method_For_State(WebDriver driver) throws Exception
		{

			Thread.sleep(5000);
			System.out.println("********************* View The State Report *************************");
			test.log(LogStatus.INFO, "********************* View The State Report *************************");

			//Click the Select option
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/a")).click();
			//Enter the required filter
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys("State");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Utility.getReportPropertyUser("State"));
			//Press the Enter button
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Sub Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Menu Item Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Menu Item Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			//Check whether the Split by serving size level option is enabled or not
			if(driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).isSelected())
			{
				//Enable or disable the Split by Serving Size Level
				driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).click();
			}
			else
			{
				
			}
			
			Thread.sleep(1500);
			//Click the Menu Item Options
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/a")).click();
			//Enter the required option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys("All");
			//Press the Enter button
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));
			
			try
			{
				
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Menu Item Sale Report available for Specific Date");
					
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(5000);
					//Check Weather the Top 5 Menu Item sale available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[1]")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Menu Item Sale Report available for Specific Date");
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);
						
						//Click the Pie chart option
						driver.findElement(By.xpath("//a[.='Pie']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Pie Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Polar Area chart option
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/ul/li[2]/a")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Polar Area Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Bar chart option
						driver.findElement(By.xpath("//a[.='Bar']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Bar Chart available");
						Thread.sleep(5000);
					}
					else
					{
						test.log(LogStatus.FAIL, "Here Top 5 Menu Item Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[2]/div/canvas")).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Menu Item sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table")).isDisplayed())
					{								
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(5000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  

			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Percentage_Of_Sale element into float value
			        	float expect5 = Float.parseFloat(expected5);                              
				        
				        //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
				        
				        //Check weather the Percentage Of Sale Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Percentage Of Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Percentage Of Sale Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage Of Sale
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();

				        	System.out.println("The Actual Percentage Of Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Percentage Of Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage Of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Percentage Of Sale Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Percentage Of Sale Value different is : "+different);	
				        }
				        
				        //Check weather the Rounding Off Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Rounding_Off")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Rounding Off");
				        	
				        	//Get the Total value of Rounding Off
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Rounding Off Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Rounding Off Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Rounding Off Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Rounding Off
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
		
				        	System.out.println("The Actual Rounding Off value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Rounding Off value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Rounding Off");
				        	
				        	//Get the Total value of Rounding Off
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Rounding Off Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Rounding Off Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	
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
				
				else
				{
					test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");
		
			    	
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));


				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(5000);
			System.out.println("********************* End of State Report *************************");
			test.log(LogStatus.INFO, "********************* End of State Report *************************");
		}
		
		@Test(enabled=false,priority=37)
		public void Enterprise_Menu_Item_Report_Method_For_ZipCode(WebDriver driver) throws Exception
		{

			Thread.sleep(5000);
			System.out.println("********************* View The Zip Code Report *************************");
			test.log(LogStatus.INFO, "********************* View The Zip Code Report *************************");
			
			//Click the Select option
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/a")).click();
			//Enter the required filter
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys("Zip Code");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Utility.getReportPropertyUser("Zipcode"));
			//Press the Enter button
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Sub Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Menu Item Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Menu Item Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			//Check whether the Split by serving size level option is enabled or not
			if(driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).isSelected())
			{
				//Enable or disable the Split by Serving Size Level
				driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).click();
			}
			else
			{
				
			}
			
			Thread.sleep(1500);
			//Click the Menu Item Options
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/a")).click();
			//Enter the required option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys("All");
			//Press the Enter button
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			long start = System.currentTimeMillis();
	    	
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));
			
			try
			{
				
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Menu Item Sale Report available for Specific Date");
					
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					
					Thread.sleep(5000);
					//Check Weather the Top 5 Menu Item sale available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[1]")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Menu Item Sale Report available for Specific Date");
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);
						
						//Click the Pie chart option
						driver.findElement(By.xpath("//a[.='Pie']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Pie Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Polar Area chart option
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/ul/li[2]/a")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Polar Area Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Bar chart option
						driver.findElement(By.xpath("//a[.='Bar']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Bar Chart available");
						Thread.sleep(5000);
					}
					else
					{
						test.log(LogStatus.FAIL, "Here Top 5 Menu Item Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[2]/div/canvas")).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Menu Item sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table")).isDisplayed())
					{								
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(5000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  

			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Percentage_Of_Sale element into float value
			        	float expect5 = Float.parseFloat(expected5);                              
				        
				        //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
				        
				        //Check weather the Percentage Of Sale Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Percentage Of Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Percentage Of Sale Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage Of Sale
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();

				        	System.out.println("The Actual Percentage Of Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Percentage Of Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage Of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Percentage Of Sale Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Percentage Of Sale Value different is : "+different);	
				        }
				        
				        //Check weather the Rounding Off Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Rounding_Off")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Rounding Off");
				        	
				        	//Get the Total value of Rounding Off
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Rounding Off Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Rounding Off Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Rounding Off Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Rounding Off
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
		
				        	System.out.println("The Actual Rounding Off value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Rounding Off value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Rounding Off");
				        	
				        	//Get the Total value of Rounding Off
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Rounding Off Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Rounding Off Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	
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
				
				else
				{
					test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");
				
				
			    	
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));


				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(5000);
			System.out.println("********************* End of Zip Code Report *************************");
			test.log(LogStatus.INFO, "********************* End of Zip Code Report *************************");

		}
		
		@Test(enabled=false,priority=38)
		public void Enterprise_Menu_Item_Report_Method_For_Stores_WithSplitByServingSize(WebDriver driver) throws Exception
		{

			Thread.sleep(2000);
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

			Thread.sleep(5000);
			System.out.println("********************* View The Store Report(Split By Serving Size Level) *************************");
			test.log(LogStatus.INFO, "********************* View The Store Report(Split By Serving Size Level) *************************");

			//Click the Select option
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/a")).click();
			//Enter the required filter
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys("Stores");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Utility.getReportPropertyUser("Store"));
			//Press the Enter button
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Sub Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Menu Item Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Serving Size level Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			//Check whether the Split by serving size level option is enabled or not
			if(driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).isSelected())
			{
			}
			else
			{
				//Enable or disable the Split by Serving Size Level
				driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).click();

			}
			
			Thread.sleep(1500);
			//Click the Menu Item Options
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/a")).click();
			//Enter the required option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys("All");
			//Press the Enter button
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			long start = System.currentTimeMillis();
	    	
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));
			
			
			try
			{
				
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Menu Item Sale Report available for Specific Date");
					
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					
					Thread.sleep(5000);
					//Check Weather the Top 5 Menu Item sale available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[1]")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Menu Item Sale Report available for Specific Date");
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);
						
						//Click the Pie chart option
						driver.findElement(By.xpath("//a[.='Pie']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Pie Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Polar Area chart option
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/ul/li[2]/a")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Polar Area Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Bar chart option
						driver.findElement(By.xpath("//a[.='Bar']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Bar Chart available");
						Thread.sleep(5000);
					}
					else
					{
						test.log(LogStatus.FAIL, "Here Top 5 Menu Item Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[2]/div/canvas")).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Menu Item sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table")).isDisplayed())
					{								
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(5000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  

			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Percentage_Of_Sale element into float value
			        	float expect5 = Float.parseFloat(expected5);                              
				        
				        //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
				        
				        //Check weather the Percentage Of Sale Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Percentage Of Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Percentage Of Sale Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage Of Sale
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();

				        	System.out.println("The Actual Percentage Of Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Percentage Of Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage Of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Percentage Of Sale Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Percentage Of Sale Value different is : "+different);	
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
				
				else
				{
					test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");
			
			    	
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));


				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			Thread.sleep(5000);
			System.out.println("********************* End of Store Report(Split By Serving Size Level) *************************");
			test.log(LogStatus.INFO, "********************* End of Store Report(Split By Serving Size Level) *************************");

			
		}
		
		@Test(enabled=false,priority=39)
		public void Enterprise_Menu_Item_Report_Method_For_Group_WithSplitByServingSize(WebDriver driver) throws Exception
		{

			Thread.sleep(5000);
			System.out.println("********************* View The Group Report(Split By Serving Size Level) *************************");
			test.log(LogStatus.INFO, "********************* View The Group Report(Split By Serving Size Level) *************************");
			
			//Click the Select option
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/a")).click();
			//Enter the required filter
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys("Group");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Utility.getReportPropertyUser("Group"));
			//Press the Enter button
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Sub Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Menu Item Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the serving size Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			//Check whether the Split by serving size level option is enabled or not
			if(driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).isSelected())
			{
			}
			else
			{
				//Enable or disable the Split by Serving Size Level
				driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).click();

			}
			
			Thread.sleep(1500);
			//Click the Menu Item Options
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/a")).click();
			//Enter the required option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys("All");
			//Press the Enter button
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			long start = System.currentTimeMillis();
	    	
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));
			
			try
			{
				
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Menu Item Sale Report available for Specific Date");
					
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					
					Thread.sleep(5000);
					//Check Weather the Top 5 Menu Item sale available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[1]")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Menu Item Sale Report available for Specific Date");
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);
						
						//Click the Pie chart option
						driver.findElement(By.xpath("//a[.='Pie']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Pie Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Polar Area chart option
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/ul/li[2]/a")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Polar Area Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Bar chart option
						driver.findElement(By.xpath("//a[.='Bar']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Bar Chart available");
						Thread.sleep(5000);
					}
					else
					{
						test.log(LogStatus.FAIL, "Here Top 5 Menu Item Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[2]/div/canvas")).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Menu Item sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table")).isDisplayed())
					{								
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(5000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  

			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Percentage_Of_Sale element into float value
			        	float expect5 = Float.parseFloat(expected5);                              
				        
				        //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
				        
				        //Check weather the Percentage Of Sale Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Percentage Of Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Percentage Of Sale Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage Of Sale
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();

				        	System.out.println("The Actual Percentage Of Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Percentage Of Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage Of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Percentage Of Sale Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Percentage Of Sale Value different is : "+different);	
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
				
				else
				{
					test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");
				
			    	
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));


				
				
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(5000);
			System.out.println("********************* End of Group Report(Split By Serving Size Level) *************************");
			test.log(LogStatus.INFO, "********************* End of Group Report(Split By Serving Size Level) *************************");
		}
		
		@Test(enabled=false,priority=40)
		public void Enterprise_Menu_Item_Report_Method_For_City_WithSplitByServingSize(WebDriver driver) throws Exception
		{

			Thread.sleep(5000);
			System.out.println("********************* View The City Report(Split By Serving Size Level) *************************");
			test.log(LogStatus.INFO, "********************* View The City Report(Split By Serving Size Level) *************************");

			//Click the Select option
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/a")).click();
			//Enter the required filter
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys("City");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Utility.getReportPropertyUser("City"));
			//Press the Enter button
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Sub Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Menu Item Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Serving size level Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			//Check whether the Split by serving size level option is enabled or not
			if(driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).isSelected())
			{
			}
			else
			{
				//Enable or disable the Split by Serving Size Level
				driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).click();

			}
			
			Thread.sleep(1500);
			//Click the Menu Item Options
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/a")).click();
			//Enter the required option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys("All");
			//Press the Enter button
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));
			
			
			try
			{
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Menu Item Sale Report available for Specific Date");
					
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(5000);
					//Check Weather the Top 5 Menu Item sale available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[1]")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Menu Item Sale Report available for Specific Date");
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);
						
						//Click the Pie chart option
						driver.findElement(By.xpath("//a[.='Pie']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Pie Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Polar Area chart option
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/ul/li[2]/a")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Polar Area Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Bar chart option
						driver.findElement(By.xpath("//a[.='Bar']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Bar Chart available");
						Thread.sleep(5000);
					}
					else
					{
						test.log(LogStatus.FAIL, "Here Top 5 Menu Item Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[2]/div/canvas")).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Menu Item sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table")).isDisplayed())
					{								
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(5000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  

			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Percentage_Of_Sale element into float value
			        	float expect5 = Float.parseFloat(expected5);                              
				        
				        //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
				        
				        //Check weather the Percentage Of Sale Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Percentage Of Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Percentage Of Sale Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage Of Sale
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();

				        	System.out.println("The Actual Percentage Of Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Percentage Of Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage Of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Percentage Of Sale Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Percentage Of Sale Value different is : "+different);	
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
				
				else
				{
					test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");

			    	
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));


				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
							
			Thread.sleep(5000);
			System.out.println("********************* End of City Report(Split By Serving Size Level) *************************");
			test.log(LogStatus.INFO, "********************* End of City Report(Split By Serving Size Level) *************************");
		}
		
		@Test(enabled=false,priority=41)
		public void Enterprise_Menu_Item_Report_Method_For_State_WithSplitByServingSize(WebDriver driver) throws Exception
		{

			Thread.sleep(5000);
			System.out.println("********************* View The State Report(Split By Serving Size Level) *************************");
			test.log(LogStatus.INFO, "********************* View The State Report(Split By Serving Size Level) *************************");

			//Click the Select option
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/a")).click();
			//Enter the required filter
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys("State");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Utility.getReportPropertyUser("State"));
			//Press the Enter button
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Sub Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Menu Item Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Menu Item Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			//Check whether the Split by serving size level option is enabled or not
			if(driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).isSelected())
			{
			}
			else
			{
				//Enable or disable the Split by Serving Size Level
				driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).click();

			}
			
			Thread.sleep(1500);
			//Click the Menu Item Options
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/a")).click();
			//Enter the required option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys("All");
			//Press the Enter button
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			long start = System.currentTimeMillis();
	    	
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));
			
			try
			{
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Menu Item Sale Report available for Specific Date");
					
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					
					Thread.sleep(5000);
					//Check Weather the Top 5 Menu Item sale available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[1]")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Menu Item Sale Report available for Specific Date");
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);
						
						//Click the Pie chart option
						driver.findElement(By.xpath("//a[.='Pie']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Pie Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Polar Area chart option
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/ul/li[2]/a")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Polar Area Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Bar chart option
						driver.findElement(By.xpath("//a[.='Bar']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Bar Chart available");
						Thread.sleep(5000);
					}
					else
					{
						test.log(LogStatus.FAIL, "Here Top 5 Menu Item Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[2]/div/canvas")).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Menu Item sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table")).isDisplayed())
					{								
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(5000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  

			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Percentage_Of_Sale element into float value
			        	float expect5 = Float.parseFloat(expected5);                              
				        
				        //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
				        
				        //Check weather the Percentage Of Sale Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Percentage Of Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Percentage Of Sale Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage Of Sale
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();

				        	System.out.println("The Actual Percentage Of Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Percentage Of Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage Of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Percentage Of Sale Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Percentage Of Sale Value different is : "+different);	
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
				
				else
				{
					test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");
				
			    	
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));


				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");

		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(5000);
			System.out.println("********************* End of State Report(Split By Serving Size Level) *************************");
			test.log(LogStatus.INFO, "********************* End of State Report(Split By Serving Size Level) *************************");
		}
		
		@Test(enabled=false,priority=42)
		public void Enterprise_Menu_Item_Report_Method_For_ZipCode_WithSplitByServingSize(WebDriver driver) throws Exception
		{

			Thread.sleep(5000);
			System.out.println("********************* View The Zip Code Report(Split By Serving Size Level) *************************");
			test.log(LogStatus.INFO, "********************* View The Zip Code Report(Split By Serving Size Level) *************************");
			
			//Click the Select option
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/a")).click();
			//Enter the required filter
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys("Zip Code");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys((Utility.getReportPropertyUser("Zipcode")));
			//Press the Enter button
			driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Sub Category Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Menu Item Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Serving size level Options
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys(Keys.ENTER);

			//Check whether the Split by serving size level option is enabled or not
			if(driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).isSelected())
			{
			}
			else
			{
				//Enable or disable the Split by Serving Size Level
				driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).click();

			}
			
			Thread.sleep(1500);
			//Click the Menu Item Options
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/a")).click();
			//Enter the required option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys("All");
			//Press the Enter button
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
			//Enter the required date
			driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));
			
			
			try
			{
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Menu Item Sale Report available for Specific Date");
					
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(5000);
					//Check Weather the Top 5 Menu Item sale available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[1]")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Here Top 5 Menu Item Sale Report available for Specific Date");
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);
						
						//Click the Pie chart option
						driver.findElement(By.xpath("//a[.='Pie']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Pie Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Polar Area chart option
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/ul/li[2]/a")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Polar Area Chart available");
						Thread.sleep(5000);
						
						Thread.sleep(2000);
						//Click the down arrow button of chart type
						driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[2]/div/button[2]")).click();
						Thread.sleep(1000);

						//Click the Bar chart option
						driver.findElement(By.xpath("//a[.='Bar']")).click();
						Thread.sleep(2000);
						test.log(LogStatus.INFO, "There is Bar Chart available");
						Thread.sleep(5000);
					}
					else
					{
						test.log(LogStatus.FAIL, "Here Top 5 Menu Item Sale Report not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the report graph is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[2]/div/canvas")).isDisplayed())
					{
						test.log(LogStatus.PASS, "There is Graphical type of Menu Item sale report is available for Specific Date");
					}
					else
					{
						test.log(LogStatus.FAIL, "Graphical report is not available for Specific Date");
					}
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table")).isDisplayed())
					{								
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(5000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        		        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3);  

			        	//Replace all commo's with empty space
			        	String expected4 = Utility.getReportPropertyUser("Sale_Report_Discount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Discount element into float value
			        	float expect4 = Float.parseFloat(expected4);  
			        	
			        	//Replace all commo's with empty space
			        	String expected5 = Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Percentage_Of_Sale element into float value
			        	float expect5 = Float.parseFloat(expected5);                              
				        
				        //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Quantity
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Quantity')]/span")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
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
				        
				        //Check weather the Discount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Discount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Discount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Discount Value is : "+ actual);
				        }
				        
						else if(expect4 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Discount
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();

				        	System.out.println("The Actual Discount value is : "+actualText);	
				        	
				        	test.log(LogStatus.INFO, "The Actual Discount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Discount");
				        	
				        	//Get the Total value of Discount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Discount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Discount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect4;
				        	
				        	//Print the different value
				        	System.out.println("Discount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Discount Value different is : "+different);	
				        }
				        
				        //Check weather the Percentage Of Sale Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Percentage_Of_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Percentage Of Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Percentage Of Sale Value is : "+ actual);
				        }
				        
						else if(expect5 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Percentage Of Sale
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();

				        	System.out.println("The Actual Percentage Of Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Percentage Of Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Percentage Of Sale");
				        	
				        	//Get the Total value of Percentage Of Sale
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'% of Sale')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Percentage Of Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect5;
				        	
				        	//Print the different value
				        	System.out.println("Percentage Of Sale Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Percentage Of Sale Value different is : "+different);	
				        }
				        
				        //Check weather the Rounding Off Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Rounding_Off")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Rounding Off");
				        	
				        	//Get the Total value of Rounding Off
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Rounding Off Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Rounding Off Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Rounding Off Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Rounding Off
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
		
				        	System.out.println("The Actual Rounding Off value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Rounding Off value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Rounding Off");
				        	
				        	//Get the Total value of Rounding Off
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Rounding Off')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Rounding Off Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Rounding Off Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	
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
				
				else
				{
					test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");
				
				
			    	
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));


				
				}
			}
			
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");

				
		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			
			Thread.sleep(5000);
			System.out.println("********************* End of Zip Code Report(Split By Serving Size Level) *************************");
			test.log(LogStatus.INFO, "********************* End of Zip Code Report(Split By Serving Size Level) *************************");

		}

}
