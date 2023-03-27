package epicList_Chrome_Account_User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
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

import epicList_Chrome_Account_User.Utility;

public class Verify_ModifierSale_Report {

	public WebDriver driver;
	
	float unknownValue = 00;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Modifier Sale_Report");
	
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
			Modifier_Sale_method_openpage_Report(driver);
			Modifier_Sale_method_Report_For_Specific_Date(driver);
			Modifier_Sale_method_Report_For_Specific_Date_With_Quantity_Sort(driver);
			Modifier_Sale_method_Report_For_Specific_Date_With_Dynamic_Report(driver);
			Modifier_Sale_method_Report_For_Specific_Date_With_Quantity_Sort_And_Dynamic_Report(driver);
			Thread.sleep(1500);
		}
	
			@Test(enabled=false,priority=80)
			public void Modifier_Sale_method_openpage_Report(WebDriver driver) throws Exception
			{
				/*//Click the Reports option
				driver.findElement(By.xpath("//span[.='Reports']")).click();
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[.='Sale']"));
				//Scroll the page till the Sale option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Sale Option		
				driver.findElement(By.xpath("//span[.='Sale']")).click(); */
			
				Thread.sleep(3000);
				//Enter the Categories Url
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"report/modifierSale");
				
				Thread.sleep(3000);
				try
				{
				//Check weather the Modifier Sale Report page is loaded or not
				if(driver.findElement(By.xpath("//label[.='Modifier']")).getText().equalsIgnoreCase("Modifier"))
				{
					test.log(LogStatus.PASS, "Modifier Sale Report page loaded successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Modifier Sale Report page loaded fail");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				}
				catch(Exception e)
				{
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}

				Thread.sleep(5000);
			}
			
			@Test(enabled=false,priority=81)
			public void Modifier_Sale_method_Report_For_Specific_Date(WebDriver driver) throws Exception
			{
				Thread.sleep(2000);
				WebElement html = driver.findElement(By.tagName("html"));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

				Thread.sleep(5000);
				//Click the Modifier option
				driver.findElement(By.xpath("//select[@ng-model='query.selectedModifierId']/../div/a")).click();
				Thread.sleep(3000);
				//Enter the required Modifier
				driver.findElement(By.xpath("//select[@ng-model='query.selectedModifierId']/../div/div/div/input")).sendKeys("All");
				Thread.sleep(3000);
				//Press the Enter Key
				driver.findElement(By.xpath("//select[@ng-model='query.selectedModifierId']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
				Thread.sleep(2000);
				//Enter the required Time Period
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
				Thread.sleep(2000);
				//Press the Enter Key
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
				Thread.sleep(2000);
				//Enter the date
				driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
				Thread.sleep(2000);
				//Enter the date
				driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				Thread.sleep(2000);
				//Check whether the Quantity sort is enabled or not
				if(driver.findElement(By.xpath("//input[@ng-model='query.qtySort']")).isSelected())
				{
					//Click the Quantity Sort option
					Actions act = new Actions(driver);
					act.moveToElement(driver.findElement(By.xpath("//input[@ng-model='query.qtySort']/../span"))).click().build().perform();
				}
				else
				{
				}
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				long start = System.currentTimeMillis();
				
				
				try
				{
					WebDriverWait wait1=new WebDriverWait(driver, 30);
					WebElement ele11=driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));
					
					
					//Check weather the report is available for the selected time period
					if(wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Modifier Sale Report is not available for the Required Date Range");
				
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				}
				catch(Exception fd)
				{
					test.log(LogStatus.PASS, "Modifier Sale Report available for the Required Date Range");
					
				
					Thread.sleep(3000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr/th/div/span[.='Modifier Name']")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");
						
						long finish = System.currentTimeMillis();
						long totalTime = finish- start; 
						System.out.println("Time in Millisecomds : "+totalTime);
						double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//						long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
						long ActualfinishTimeDouble1 = (totalTime/1000)/6;
						System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
						test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath ("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr"));
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Modifier_Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Modifier_Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Modifier_Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3); 
				        
				        //Check weather the Sale Amount Report is correct or not 
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]")).getText().equals(Utility.getReportPropertyUser("Modifier_Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText().equals(Utility.getReportPropertyUser("Modifier_Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]")).getText().equals(Utility.getReportPropertyUser("Modifier_Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]")).getText();
				        	
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
				        
				        driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				        
				        String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for the Required Date Range");
				
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
				}
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}

			@Test(enabled=false,priority=82)
			public void Modifier_Sale_method_Report_For_Specific_Date_With_Quantity_Sort(WebDriver driver) throws Exception
			{
				Thread.sleep(2000);
				WebElement html = driver.findElement(By.tagName("html"));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

				Thread.sleep(3000);
				//Click the Modifier option
				driver.findElement(By.xpath("//select[@ng-model='query.selectedModifierId']/../div/a")).click();
				//Enter the required Modifier
				driver.findElement(By.xpath("//select[@ng-model='query.selectedModifierId']/../div/div/div/input")).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath("//select[@ng-model='query.selectedModifierId']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
				//Enter the required Time Period
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
				//Enter the date
				driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
				//Enter the date
				driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				//Check whether the Quantity sort is enabled or not
				if(driver.findElement(By.xpath("//input[@ng-model='query.qtySort']")).isSelected())
				{
					
				}
				else
				{
					//Click the Quantity Sort option
					Actions act = new Actions(driver);
					act.moveToElement(driver.findElement(By.xpath("//input[@ng-model='query.qtySort']/../span"))).click().build().perform();
				}
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				long start = System.currentTimeMillis();
				
				Thread.sleep(3000);
				//Number of rows
				List<WebElement> rowss = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr"));
				int rowa = rowss.size()-1;
				ArrayList<Integer> array = new ArrayList<Integer>();
				for(int i =1; i <= rowa; i++)
				{
					String quan = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+i+"]/td[4]")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
					Integer dquan = Integer.parseInt(quan);
					//int quantity = quan.intValue();
					array.add(dquan);
				}
				Collections.sort(array);
				for(int i =0; i < rowa; i++)
				{	int s = i+1;
					String e = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+s+"]/td[4]")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "");
					double actual = Double.parseDouble(e);
					double expect = array.get(i);
					if(actual != expect)
					{
						test.log(LogStatus.PASS, "Sorted by Quantity is Fail");
					}
				}
				
			
				try
				{
					WebDriverWait wait1=new WebDriverWait(driver, 30);
					WebElement ele11=driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));
					
					
					//Check weather the report is available for the selected time period
					if(wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Modifier Sale Report is not available for the Required Date Range");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				}
				catch(Exception fd)
				{
					test.log(LogStatus.PASS, "Modifier Sale Report available for the Required Date Range");
					
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(3000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr/th/div/span[.='Modifier Name']")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");
						
						Thread.sleep(3000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath ("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr"));
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Modifier_Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        	
			        	//Replace all commo's with empty space
			        	String expected2 = Utility.getReportPropertyUser("Modifier_Sale_Report_Quantity").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Quantity element into float value
			        	float expect2 = Float.parseFloat(expected2);  
			        	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Modifier_Sale_Report_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Modifier_Sale_Report_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3); 
				        
				        //Check weather the Sale Amount Report is correct or not 
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]")).getText().equals(Utility.getReportPropertyUser("Modifier_Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[3]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText().equals(Utility.getReportPropertyUser("Modifier_Sale_Report_Quantity")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText();

				        	System.out.println("The Actual Quantity value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Quantity value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Quantity");
				        	
				        	//Get the Total value of Quantity
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[4]")).getText();
				        	
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
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]")).getText().equals(Utility.getReportPropertyUser("Modifier_Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]")).getText();
				        	
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
				        	String actualText = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+rows.size()+"]/td[5]")).getText();
				        	
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
				        driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				        
				        String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for the Required Date Range");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
				}
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}

			@Test(enabled=false,priority=83)
			public void Modifier_Sale_method_Report_For_Specific_Date_With_Dynamic_Report(WebDriver driver) throws Exception
			{
				Thread.sleep(2000);
				WebElement html = driver.findElement(By.tagName("html"));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

				Thread.sleep(3000);
				//Click the Modifier option
				driver.findElement(By.xpath("//select[@ng-model='query.selectedModifierId']/../div/a")).click();
				//Enter the required Modifier
				driver.findElement(By.xpath("//select[@ng-model='query.selectedModifierId']/../div/div/div/input")).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath("//select[@ng-model='query.selectedModifierId']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
				//Enter the required Time Period
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
				//Enter the date
				driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
				//Enter the date
				driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				//Check whether the Quantity sort is enabled or not
				if(driver.findElement(By.xpath("//input[@ng-model='query.qtySort']")).isSelected())
				{
					//Click the Quantity Sort option
					Actions act = new Actions(driver);
					act.moveToElement(driver.findElement(By.xpath("//input[@ng-model='query.qtySort']/../span"))).click().build().perform();
				}
				else
				{
				}
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				long start = System.currentTimeMillis();
				
				Thread.sleep(3000);
				for(int i = 1; i <= 8; i++)
				{
					driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				}
				
				try
				{
					WebDriverWait wait1=new WebDriverWait(driver, 30);
					WebElement ele11=driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));
					
					
					if(wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Modifier Sale Report is not available for the Required Date Range");
				
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
				}
				catch(Exception fd)
				{
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
					//Check Whether all the field is selected or not
					if(driver.findElement(By.xpath("//span[contains(.,'Item Name')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'Sale Amount')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'Quantity')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'Tax')]/../input")).isSelected())
					{
						//Check weather the table format report is available or not
						if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']")).isDisplayed())
						{
							test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");
							
							long finish = System.currentTimeMillis();
							long totalTime = finish- start; 
							System.out.println("Time in Millisecomds : "+totalTime);
							double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//							long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
							long ActualfinishTimeDouble1 = (totalTime/1000)/6;
							System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
							test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
							
							driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
							
							//Check whether all the filed is available or not
							if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Item Name')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Sale Amount')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Quantity')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Tax')]")).isDisplayed())
							{
								test.log(LogStatus.PASS, "All the Neccessary fields are displayed");
							}
							else
							{
								test.log(LogStatus.FAIL, "Neccessary fields are not displayed");
							}
							
							//Change the option from enable to Disable the Item Name Option
							if(driver.findElement(By.xpath("//span[contains(.,'Item Name')]/../input")).isSelected())
							{
								//Click the Enabled Item Name Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Item Name')]"))).click().build().perform();
								try
								{
									//Check whether the Item Name Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Item Name')]")).isDisplayed())
									{
										test.log(LogStatus.FAIL, "Item Name is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "Item Name is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Item Name Option is Disabled When user enter the required date for date range");
							}
							
							//Change the option from enable to Disable the Sale Amount Option
							if(driver.findElement(By.xpath("//span[contains(.,'Sale Amount')]/../input")).isSelected())
							{
								//Click the Enabled Sale Amount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Sale Amount')]"))).click().build().perform();
								try
								{
									//Check whether the Sale Amount Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Sale Amount')]")).isDisplayed())
									{
										test.log(LogStatus.FAIL, "Sale Amount is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "Sale Amount is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Sale Amount Option is Disabled When user enter the required date for date range");
							}
											
							//Change the option from enable to Disable the Quantity Option
							if(driver.findElement(By.xpath("//span[contains(.,'Quantity')]/../input")).isSelected())
							{
								//Click the Enabled Quantity Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Quantity')]"))).click().build().perform();
								try
								{
									//Check whether the Quantity Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Quantity')]")).isDisplayed())
									{
										test.log(LogStatus.FAIL, "Quantity is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "Quantity is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Quantity Option is Disabled When user enter the required date for date range");
							}
							
							//Change the option from enable to Disable the Tax Option
							if(driver.findElement(By.xpath("//span[contains(.,'Tax')]/../input")).isSelected())
							{
								//Click the Enabled Tax Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Tax')]"))).click().build().perform();
								try
								{
									//Check whether the Tax Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Tax')]")).isDisplayed())
									{
										test.log(LogStatus.FAIL, "Tax is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "Tax is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Tax Option is Disabled When user enter the required date for date range");
							}
							
							//Enable the Item Name Option if it is in disabled status
							if(driver.findElement(By.xpath("//span[contains(.,'Item Name')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Item Name Option is Enabled after user click(Disable) the Item Name option");

							}
							else
							{
								//Click the Enabled Item Name Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Item Name')]"))).click().build().perform();
								try
								{
									//Check whether the Item Name Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Item Name')]")).isDisplayed())
									{
										test.log(LogStatus.PASS, "Item Name is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Item Name is not displayed");
								}				
							}
							
							//Enable the Sale Amount Option if it is in disabled status
							if(driver.findElement(By.xpath("//span[contains(.,'Sale Amount')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Sale Amount Option is Enabled after user click(Disable) the Sale Amount option");

							}
							else
							{
								//Click the Enabled Sale Amount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Sale Amount')]"))).click().build().perform();
								try
								{
									//Check whether the Sale Amount Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Sale Amount')]")).isDisplayed())
									{
										test.log(LogStatus.PASS, "Sale Amount is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Sale Amount is not displayed");
								}				
							}
													
							//Disable the required option from enable to disable Quantity Option
							if(driver.findElement(By.xpath("//span[contains(.,'Quantity')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Quantity Option is Enabled after user click(Disable) the Quantity option");

							}
							else
							{

								//Click the Enabled Quantity Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Quantity')]"))).click().build().perform();
								try
								{
									//Check whether the Quantity Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Quantity')]")).isDisplayed())
									{
										test.log(LogStatus.PASS, "Quantity is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Quantity is not displayed");
								}
							}
							
							//Disable the required option from enable to disable Tax Option
							if(driver.findElement(By.xpath("//span[contains(.,'Tax')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Tax Option is Enabled after user click(Disable) the Tax option");

							}
							else
							{

								//Click the Enabled Tax Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Tax')]"))).click().build().perform();
								try
								{
									//Check whether the Tax Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Tax')]")).isDisplayed())
									{
										test.log(LogStatus.PASS, "Tax is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Tax is not displayed");
								}
							}

						}
						else
						{
							test.log(LogStatus.FAIL, "Table Format Report is not available for the Required Date Range");
						}
						
					}
					else
					{
						test.log(LogStatus.FAIL, "All dynamic option is not selected");
					}
				}				
			}

			@Test(enabled=false,priority=84)
			public void Modifier_Sale_method_Report_For_Specific_Date_With_Quantity_Sort_And_Dynamic_Report(WebDriver driver) throws Exception
			{
				Thread.sleep(2000);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

				Thread.sleep(3000);
				//Click the Modifier option
				driver.findElement(By.xpath("//select[@ng-model='query.selectedModifierId']/../div/a")).click();
				//Enter the required Modifier
				driver.findElement(By.xpath("//select[@ng-model='query.selectedModifierId']/../div/div/div/input")).sendKeys("All");
				//Press the Enter Key
				driver.findElement(By.xpath("//select[@ng-model='query.selectedModifierId']/../div/div/div/input")).sendKeys(Keys.ENTER);
			
				Thread.sleep(3000);
				//Click the Time Period option
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
				//Enter the required Time Period
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
				//Press the Enter Key
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
				//Enter the date
				driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

				Thread.sleep(2000);
				//Clear the date field
				driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
				//Enter the date
				driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				//Check whether the Quantity sort is enabled or not
				if(driver.findElement(By.xpath("//input[@ng-model='query.qtySort']")).isSelected())
				{
					
				}
				else
				{
					//Click the Quantity Sort option
					Actions act = new Actions(driver);
					act.moveToElement(driver.findElement(By.xpath("//input[@ng-model='query.qtySort']/../span"))).click().build().perform();
				}
				
				Thread.sleep(2000);
				//Click the Run Button
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				long start = System.currentTimeMillis();
				
				Thread.sleep(3000);
				for(int i = 1; i <= 8; i++)
				{
					driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				}
				
				try
				{
					WebDriverWait wait1=new WebDriverWait(driver, 30);
					WebElement ele11=driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));
					
					
					if(wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
					{
						test.log(LogStatus.FAIL, "Modifier Sale Report is not available for the Required Date Range");
					}
				}
				catch(Exception gh)
				{
					//Check Whether all the field is selected or not
					if(driver.findElement(By.xpath("//span[contains(.,'Item Name')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'Sale Amount')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'Quantity')]/../input")).isSelected()
							&&driver.findElement(By.xpath("//span[contains(.,'Tax')]/../input")).isSelected())
					{
						//Check weather the table format report is available or not
						if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']")).isDisplayed())
						{
							test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");
							
							long finish = System.currentTimeMillis();
							long totalTime = finish- start; 
							System.out.println("Time in Millisecomds : "+totalTime);
							double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//							long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
							long ActualfinishTimeDouble1 = (totalTime/1000)/6;
							System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
							test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
							
							driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
							//Check whether all the filed is available or not
							if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Item Name')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Sale Amount')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Quantity')]")).isDisplayed()
									&&driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Tax')]")).isDisplayed())
							{
								test.log(LogStatus.PASS, "All the Neccessary fields are displayed");
							}
							else
							{
								test.log(LogStatus.FAIL, "Neccessary fields are not displayed");
							}
							
							//Change the option from enable to Disable the Item Name Option
							if(driver.findElement(By.xpath("//span[contains(.,'Item Name')]/../input")).isSelected())
							{
								//Click the Enabled Item Name Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Item Name')]"))).click().build().perform();
								try
								{
									//Check whether the Item Name Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Item Name')]")).isDisplayed())
									{
										test.log(LogStatus.FAIL, "Item Name is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "Item Name is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Item Name Option is Disabled When user enter the required date for date range");
							}
							
							//Change the option from enable to Disable the Sale Amount Option
							if(driver.findElement(By.xpath("//span[contains(.,'Sale Amount')]/../input")).isSelected())
							{
								//Click the Enabled Sale Amount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Sale Amount')]"))).click().build().perform();
								try
								{
									//Check whether the Sale Amount Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Sale Amount')]")).isDisplayed())
									{
										test.log(LogStatus.FAIL, "Sale Amount is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "Sale Amount is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Sale Amount Option is Disabled When user enter the required date for date range");
							}
											
							//Change the option from enable to Disable the Quantity Option
							if(driver.findElement(By.xpath("//span[contains(.,'Quantity')]/../input")).isSelected())
							{
								//Click the Enabled Quantity Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Quantity')]"))).click().build().perform();
								try
								{
									//Check whether the Quantity Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Quantity')]")).isDisplayed())
									{
										test.log(LogStatus.FAIL, "Quantity is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "Quantity is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Quantity Option is Disabled When user enter the required date for date range");
							}
							
							//Change the option from enable to Disable the Tax Option
							if(driver.findElement(By.xpath("//span[contains(.,'Tax')]/../input")).isSelected())
							{
								//Click the Enabled Tax Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Tax')]"))).click().build().perform();
								try
								{
									//Check whether the Tax Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Tax')]")).isDisplayed())
									{
										test.log(LogStatus.FAIL, "Tax is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.PASS, "Tax is not displayed");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Tax Option is Disabled When user enter the required date for date range");
							}
							
							//Enable the Item Name Option if it is in disabled status
							if(driver.findElement(By.xpath("//span[contains(.,'Item Name')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Item Name Option is Enabled after user click(Disable) the Item Name option");

							}
							else
							{
								//Click the Enabled Item Name Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Item Name')]"))).click().build().perform();
								try
								{
									//Check whether the Item Name Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Item Name')]")).isDisplayed())
									{
										test.log(LogStatus.PASS, "Item Name is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Item Name is not displayed");
								}				
							}
							
							//Enable the Sale Amount Option if it is in disabled status
							if(driver.findElement(By.xpath("//span[contains(.,'Sale Amount')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Sale Amount Option is Enabled after user click(Disable) the Sale Amount option");

							}
							else
							{
								//Click the Enabled Sale Amount Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Sale Amount')]"))).click().build().perform();
								try
								{
									//Check whether the Sale Amount Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Sale Amount')]")).isDisplayed())
									{
										test.log(LogStatus.PASS, "Sale Amount is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Sale Amount is not displayed");
								}				
							}
													
							//Disable the required option from enable to disable Quantity Option
							if(driver.findElement(By.xpath("//span[contains(.,'Quantity')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Quantity Option is Enabled after user click(Disable) the Quantity option");

							}
							else
							{

								//Click the Enabled Quantity Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Quantity')]"))).click().build().perform();
								try
								{
									//Check whether the Quantity Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Quantity')]")).isDisplayed())
									{
										test.log(LogStatus.PASS, "Quantity is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Quantity is not displayed");
								}
							}
							
							//Disable the required option from enable to disable Tax Option
							if(driver.findElement(By.xpath("//span[contains(.,'Tax')]/../input")).isSelected())
							{
								test.log(LogStatus.FAIL, "Tax Option is Enabled after user click(Disable) the Tax option");

							}
							else
							{

								//Click the Enabled Tax Option
								Actions act = new Actions(driver);
								act.moveToElement(driver.findElement(By.xpath("//input/../span[contains(.,'Tax')]"))).click().build().perform();
								try
								{
									//Check whether the Tax Option is displayed or not
									if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr[1]/th/div/span[contains(.,'Tax')]")).isDisplayed())
									{
										test.log(LogStatus.PASS, "Tax is displayed");
									}
								}
								catch(Exception e)
								{
									test.log(LogStatus.FAIL, "Tax is not displayed");
								}
							}

						}
						else
						{
							test.log(LogStatus.FAIL, "Table Format Report is not available for the Required Date Range");
						}
						
					}
					else
					{
						test.log(LogStatus.FAIL, "All dynamic option is not selected");
					}
				}
			}
}
