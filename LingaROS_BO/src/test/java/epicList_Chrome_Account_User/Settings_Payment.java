
	package epicList_Chrome_Account_User;
	import java.util.List;
	import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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

	public class Settings_Payment { 


			public WebDriver driver;
			
			ExtentReports rep = ExtentManager.getInstance();
			ExtentTest test = rep.startTest("Settings_Payment_settings");
			
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
					Browser_Account_Level_User a = new Browser_Account_Level_User();
					a.Logout(driver, test);
				}
					/*if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
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
					Thread.sleep(3000);*/
					//Close the Browser_Account_Level_User
					
		
			
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
		            Settings_Payment_Method_open(driver);
					Payment_Settings_Method_edit(driver);
					Thread.sleep(1500);
				}

				@Test(priority=28,enabled=false)
				public void Settings_Payment_Method_open(WebDriver driver) throws Exception {
					// TODO Auto-generated method stub
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					//Enter the URL
					driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"newStorePayments");
					
					
					WebElement html = driver.findElement(By.tagName(excel.getData(5, 174, 5)));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				
					Thread.sleep(5000);
					try
					{
					//Check weather the Payment Settings page is loaded or not
					if(driver.findElement(By.xpath(excel.getData(5, 450, 1))).getText().equalsIgnoreCase("Payment Settings"))
					{
						test.log(LogStatus.PASS, "Payment Settings page loaded successfully");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
					else
					{
						test.log(LogStatus.FAIL, "Payment Settings page loaded fail");
					
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

					Thread.sleep(3000);
				}

				
			@Test(priority=29,enabled=false)
			public void Payment_Settings_Method_edit(WebDriver driver) throws Exception
				{
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
					Thread.sleep(5000);
					//disable service charge
				if(driver.findElement(By.xpath("//label[contains(.,'Service Charge')]/../../div/div/input")).isSelected())
				{
					driver.findElement(By.xpath("//label[contains(.,'Service Charge')]/../../div/div/input")).click();
				}
					//Cash Discount enabled or not
					if(driver.findElement(By.xpath(excel.getData(5, 449, 1))).isSelected())
					{
						//disbale the cash discount
						driver.findElement(By.xpath(excel.getData(5, 449, 1))).click();	
					}
					else {
						//enable the cash discount
						driver.findElement(By.xpath(excel.getData(5, 449, 1))).click();
						Thread.sleep(3000);
						//enter fee name
						driver.findElement(By.xpath(excel.getData(5, 451, 1))).clear();
						driver.findElement(By.xpath(excel.getData(5, 451, 1))).sendKeys("Newfeenm");
						//enter new discount name
						driver.findElement(By.xpath(excel.getData(5, 452, 1))).clear();
						driver.findElement(By.xpath(excel.getData(5, 452, 1))).sendKeys("New discount");
						//Enter Cash Discount Rate for new payment
						driver.findElement(By.xpath(excel.getData(5, 453, 1))).clear();
						driver.findElement(By.xpath(excel.getData(5, 453, 1))).sendKeys("10");
						//select Amount or percentage
						driver.findElement(By.xpath(excel.getData(5, 454, 1))).click();
						driver.findElement(By.xpath(excel.getData(5, 454, 1))).sendKeys(Keys.ARROW_DOWN);
						driver.findElement(By.xpath(excel.getData(5, 454, 1))).sendKeys(Keys.ENTER);
					}
					Thread.sleep(1500);
					//Add Payment Button
					driver.findElement(By.xpath(excel.getData(5, 455, 1))).click();
				//Payment type
					driver.findElement(By.xpath(excel.getData(5, 456, 1))).click();
					//Payment type input field
					driver.findElement(By.xpath(excel.getData(5, 457, 1))).click();
					driver.findElement(By.xpath(excel.getData(5, 457, 1))).sendKeys("Cash");
					//payment name
				    driver.findElement(By.xpath(excel.getData(5, 457, 1))).sendKeys(Keys.ENTER);
				    driver.findElement(By.xpath(excel.getData(5, 458, 1))).sendKeys(Utility.getProperty("Payment_Name"));
				    //code in payment
			       driver.findElement(By.name(excel.getData(5, 459, 3))).sendKeys("1562");
			       //Prority
				    driver.findElement(By.xpath(excel.getData(5, 460, 1))).sendKeys("6");
				    //Enable or disable the cash drawer
				    driver.findElement(By.xpath(excel.getData(5, 461, 1))).click();
				    Thread.sleep(3000);
				    //Enable or disable the Multi Currency
				    driver.findElement(By.xpath(excel.getData(5, 462, 1))).click();
				    Thread.sleep(1500);
				    if(driver.findElement(By.xpath(excel.getData(5, 462, 1))).isSelected())
				    {
				    	//select the required currency
				    	Select s1 = new Select(driver.findElement(By.xpath(excel.getData(5, 463, 1))));
				    			s1.selectByVisibleText("Indian rupee - India");	
				    			//Exchange rate of currency
				    			driver.findElement(By.xpath(excel.getData(5, 464, 1))).sendKeys("1250");
				    			//Add new payment method
				    			driver.findElement(By.xpath(excel.getData(5, 465, 1))).click();
				    			
				    }
				    else {
				    	//Add new payment method
				    	driver.findElement(By.xpath(excel.getData(5, 465, 1))).click();
				    }  
				    
				    driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
				    Thread.sleep(1000);
				    //Click Add payment Button
				    driver.findElement(By.xpath(excel.getData(5, 455, 1))).click();
				    //cancel the newly opened payment method
				    driver.findElement(By.xpath(excel.getData(5, 466, 1))).click();
							//close all the given data
							driver.findElement(By.xpath(excel.getData(5, 149, 1))).click();
							//switching to alert
							Alert alert = driver.switchTo().alert();		
			        		
					        // Capturing alert message.    
					        String alertMessage= driver.switchTo().alert().getText();		
					        		
					        // Displaying alert message		
					        System.out.println(alertMessage);	
					        Thread.sleep(10000); 
					        		
					        // Accepting alert		
					        alert.accept();	
					        //add new payment using sppax
					        
					        driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
					        Thread.sleep(1000);
					        driver.findElement(By.xpath(excel.getData(5, 455, 1))).click();
							//Payment type
								driver.findElement(By.xpath(excel.getData(5, 456, 1))).click();
								//Payment type input field
								driver.findElement(By.xpath(excel.getData(5, 457, 1))).click();
								driver.findElement(By.xpath(excel.getData(5, 457, 1))).sendKeys("SPPax");
								//payment name
							    driver.findElement(By.xpath(excel.getData(5, 457, 1))).sendKeys(Keys.ENTER);
							    driver.findElement(By.xpath(excel.getData(5, 458, 1))).sendKeys(Utility.getProperty("Sppax_Payment_Name"));
							    //code in payment
						       driver.findElement(By.name(excel.getData(5, 459, 3))).sendKeys("15615");
						       //Prority
							    driver.findElement(By.xpath(excel.getData(5, 460, 1))).sendKeys("1");
							    //Enable or disable the cash drawer
							    driver.findElement(By.xpath(excel.getData(5, 461, 1))).click();
							   Thread.sleep(1000);
							    //enable or disable ebt
							    driver.findElement(By.xpath(excel.getData(5, 485, 1))).click();
							    Thread.sleep(1000);
							    //enable or disable Factor4 Cards
							    driver.findElement(By.xpath(excel.getData(5, 486, 1))).click();
							    //enable or disbale Valvetec
							    driver.findElement(By.xpath(excel.getData(5, 487, 1))).click();
							  //Add new payment method
				    			driver.findElement(By.xpath(excel.getData(5, 465, 1))).click();
							   
					        //new payment type using credit card
					        driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
					        Thread.sleep(1000);
					        driver.findElement(By.xpath(excel.getData(5, 455, 1))).click();
					        
							//Payment type
								driver.findElement(By.xpath(excel.getData(5, 456, 1))).click();
								//Payment type input field
								driver.findElement(By.xpath(excel.getData(5, 457, 1))).click();
								driver.findElement(By.xpath(excel.getData(5, 457, 1))).sendKeys("Credit Card");
								//payment name
							    driver.findElement(By.xpath(excel.getData(5, 457, 1))).sendKeys(Keys.ENTER);
							    driver.findElement(By.xpath(excel.getData(5, 458, 1))).sendKeys(Utility.getProperty("Credit_Payment_Name"));
							    //code in payment
						       driver.findElement(By.name(excel.getData(5, 459, 3))).sendKeys("15625");
						       //Prority
							    driver.findElement(By.xpath(excel.getData(5, 460, 1))).sendKeys("6");
							    //Enable or disable the cash drawer
							    driver.findElement(By.xpath(excel.getData(5, 461, 1))).click();
							    //add payment button
							    driver.findElement(By.xpath(excel.getData(5, 465, 1))).click();
							for(int i=0;i<=2;i++)
							{
							    // new payment using dejavoo
							    driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.ARROW_DOWN);
							}  
							Thread.sleep(5000);
							
							driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
							driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
							driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
							driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);

							Thread.sleep(3000);
							WebElement addpay=driver.findElement(By.xpath(excel.getData(5, 455, 1)));
						//	JavascriptExecutor js=(JavascriptExecutor)driver;
						//	js.executeScript("argument[0].scrollIntoView(true);", addpay);
							addpay.click();
							    Thread.sleep(2000);
							    
						        Thread.sleep(1000);
								//Payment type
									driver.findElement(By.xpath(excel.getData(5, 456, 1))).click();
									//Payment type input field
								//	driver.findElement(By.xpath(excel.getData(5, 457, 1))).click();
									driver.findElement(By.xpath(excel.getData(5, 457, 1))).sendKeys("Side CC");
									//payment name
								    driver.findElement(By.xpath(excel.getData(5, 457, 1))).sendKeys(Keys.ENTER);
								    driver.findElement(By.xpath(excel.getData(5, 458, 1))).sendKeys(Utility.getProperty("Dejavo_Payment_Name"));
								    //code in payment
							       driver.findElement(By.name(excel.getData(5, 459, 3))).sendKeys("15628");
							       //Prority
								    driver.findElement(By.xpath(excel.getData(5, 460, 1))).sendKeys("6");
								    //Enable or disable the cash drawer
								    driver.findElement(By.xpath(excel.getData(5, 461, 1))).click();
								  Thread.sleep(3000);
								    //enable or disable ebt
								    driver.findElement(By.xpath("//input[@ng-model='paymentMethod.isMultiCurrency']")).click();
								   Thread.sleep(2000);
								    //Select the Currency Id
								    WebElement currencyID=driver.findElement(By.xpath("//select[@name='currencyId']"));
								    Select CurID=new Select(currencyID);
								    CurID.selectByVisibleText("Euro - Austria");
								    
								    //Clear Exchange Rate
								    driver.findElement(By.xpath("//input[@name='exchangeRateStr']")).clear();
								    //Enter Exchange Rate
								    driver.findElement(By.xpath("//input[@name='exchangeRateStr']")).sendKeys("2.000");
								    
								    Thread.sleep(2000);
								    //add payment button
								    driver.findElement(By.xpath(excel.getData(5, 465, 1))).click();
								    
					
				/*				    
								 // new payment using MPPG Credit crad
								    driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
								    Thread.sleep(1000);
							        driver.findElement(By.xpath(excel.getData(5, 455, 1))).click();
							        Thread.sleep(1000);
									//Payment type
										driver.findElement(By.xpath(excel.getData(5, 456, 1))).click();
										//Payment type input field
										driver.findElement(By.xpath(excel.getData(5, 457, 1))).click();
										driver.findElement(By.xpath(excel.getData(5, 457, 1))).sendKeys("MPPG Credit Card");
										//payment name
									    driver.findElement(By.xpath(excel.getData(5, 457, 1))).sendKeys(Keys.ENTER);
									    driver.findElement(By.xpath(excel.getData(5, 458, 1))).sendKeys(Utility.getProperty("MPPG_Credit_Card_Payment_Name"));
									    //code in payment
								       driver.findElement(By.name(excel.getData(5, 459, 3))).sendKeys("15627");
								       //Prority
									    driver.findElement(By.xpath(excel.getData(5, 460, 1))).sendKeys("1");
									    //Enable or disable the cash drawer
									    driver.findElement(By.xpath(excel.getData(5, 461, 1))).click();
									//select Sale 
									   if(driver.findElement(By.xpath(excel.getData(5, 488, 1))).isSelected())
									   {
										  //select authorise
										   driver.findElement(By.xpath(excel.getData(5, 489, 1))).click();
									   }
									   else {
										   //select sale
										   driver.findElement(By.xpath(excel.getData(5, 488, 1))).click();
									   }
									    */
								    Thread.sleep(3000);
								    try {
									    //add payment button
									    driver.findElement(By.xpath(excel.getData(5, 465, 1))).click();} catch(Exception j) {}
									    
					//Enable and disable of open cash drawer
					driver.findElement(By.xpath(excel.getData(5, 467, 1))).click();
					
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
				    Thread.sleep(1000);
					if(driver.findElement(By.xpath(excel.getData(5, 449, 1))).isSelected())
					{
						driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
					    Thread.sleep(1000);
					//For tokenize credit card	
						driver.findElement(By.xpath(excel.getData(5, 469, 1))).click();
						//For tokenize credit card input field
						driver.findElement(By.xpath(excel.getData(5, 469, 1))).sendKeys("Phone");
						driver.findElement(By.xpath(excel.getData(5, 469, 1))).sendKeys(Keys.ENTER);

					}
					else {
						
						// enable or disable the service charge field
						driver.findElement(By.xpath(excel.getData(5, 470, 1))).click();
						Thread.sleep(5000);
						//Discount fee
						driver.findElement(By.xpath(excel.getData(5, 471, 1))).clear();
						driver.findElement(By.xpath(excel.getData(5, 471, 1))).sendKeys("Service");
						//driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[2]/div[4]/div[1]/div[2]/div[1]/ul[1]/li[1]/a[1]")).click();
						driver.findElement(By.xpath(excel.getData(5, 469, 1))).click();
						//For tokenize credit card input field
						driver.findElement(By.xpath(excel.getData(5, 469, 1))).sendKeys("QSR");
						driver.findElement(By.xpath(excel.getData(5, 469, 1))).sendKeys(Keys.ENTER);
						//For tokenize credit card input field
						driver.findElement(By.xpath(excel.getData(5, 469, 1))).sendKeys("Dine-in");
						driver.findElement(By.xpath(excel.getData(5, 469, 1))).sendKeys(Keys.ENTER);
		    			
					}
					//Delete the existing emv
	    			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
					List<WebElement> s = driver.findElements(By.xpath(excel.getData(5, 472, 1)));
	    			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
					System.out.println("Size is : "+s.size());
					for(int i=1; i<=s.size(); i++)
					{
						//close all the given data
		    			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		    			Thread.sleep(1000);
						driver.findElement(By.xpath(excel.getData(5, 473, 1))).click();
						Thread.sleep(2000);
		    			//confirmation of deletion
		    			driver.findElement(By.xpath(excel.getData(5, 474, 1))).click();
		    			//driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		    			Thread.sleep(2000);
					}
		    	
					Thread.sleep(2000);
					//Add new EMV Setting
					driver.findElement(By.xpath(excel.getData(5, 475, 1))).click();
					//Give Name for Emv setting
					driver.findElement(By.xpath(excel.getData(5, 476, 1))).sendKeys("Emv10");
					
					Thread.sleep(1000);
					//enter new ip Address
					driver.findElement(By.xpath(excel.getData(5, 477, 1))).sendKeys("192.50.8.1");
					//select Type update
					Select s2 = new Select(driver.findElement(By.xpath(excel.getData(5, 478, 1))));
	    			s2.selectByVisibleText("EVERTEC");	
	    			Thread.sleep(10000);
	    			//Terminal ID
	    			driver.findElement(By.xpath(excel.getData(5, 480, 1))).sendKeys("10");
	    			Thread.sleep(500);
	    			//Port No
	    			driver.findElement(By.xpath(excel.getData(5, 481, 1))).sendKeys("1");
	    			Thread.sleep(2000);
					//Add button
	    			driver.findElement(By.xpath(excel.getData(5, 482, 1))).click();
	    			
	    			Thread.sleep(3000);
/*	    			//add new emv settings
	    			driver.findElement(By.xpath(excel.getData(5, 475, 1))).click();
	    			Thread.sleep(1000);
					//Give Name for Emv setting
					driver.findElement(By.xpath(excel.getData(5, 476, 1))).sendKeys("Emv21");
					Thread.sleep(1000);
					//ip address
					driver.findElement(By.xpath(excel.getData(5, 477, 1))).sendKeys("192.50.82.2");
					Thread.sleep(1500);
					//type
					Select s21 = new Select(driver.findElement(By.xpath(excel.getData(5, 478, 1))));
	    			s21.selectByVisibleText("SP-PAX");
	    			try {
	    			//Serial No
	    			Select s3=new Select(driver.findElement(By.xpath(excel.getData(5, 479, 1))));
	    			s3.selectByIndex(1);
					//Add button
	    			driver.findElement(By.xpath(excel.getData(5, 482, 1))).click();
	    			}catch(Exception k) {}*/
	    			
	    			Thread.sleep(3000);
	    			//Add new EMV Setting
					driver.findElement(By.xpath(excel.getData(5, 475, 1))).click();
					Thread.sleep(1000);
					//EMV NAME
	    			driver.findElement(By.xpath(excel.getData(5, 476, 1))).sendKeys("Emv13");
	    			Thread.sleep(1000);
	    			//IP address
					driver.findElement(By.xpath(excel.getData(5, 477, 1))).sendKeys("192.50.9.2");
					Thread.sleep(1000);
					//Type
					Select s5 = new Select(driver.findElement(By.xpath(excel.getData(5, 478, 1))));
	    			s5.selectByVisibleText("OPTOMANY");		
	    			Thread.sleep(1000);

	    			
	    			//port
	    			driver.findElement(By.xpath(excel.getData(5, 481, 1))).sendKeys("4");
					Thread.sleep(1000);
					//Add button
	    			driver.findElement(By.xpath(excel.getData(5, 482, 1))).click();
					Thread.sleep(1000);

	    			//Add new EMV Setting
					driver.findElement(By.xpath(excel.getData(5, 475, 1))).click();
					Thread.sleep(1000);
					//Emv name
	    			driver.findElement(By.xpath(excel.getData(5, 476, 1))).sendKeys("Emv14");
	    			Thread.sleep(1000);
	    			//Ip Address
					driver.findElement(By.xpath(excel.getData(5, 477, 1))).sendKeys("192.50.91.6");
					Thread.sleep(1000);
					//TYpe
					Select s6 = new Select(driver.findElement(By.xpath(excel.getData(5, 478, 1))));
	    			s6.selectByVisibleText("INGENICO");	
	    			Thread.sleep(1000);
	    			//terminal Id
	    			driver.findElement(By.xpath(excel.getData(5, 481, 1))).sendKeys("5");
	    			Thread.sleep(1000);
	    			//Add button
	    			driver.findElement(By.xpath(excel.getData(5, 482, 1))).click();
	    			
	    			Thread.sleep(1000);
	    			//Add new EMV Setting
					driver.findElement(By.xpath(excel.getData(5, 475, 1))).click();
					Thread.sleep(1000);
					//Name
	    			driver.findElement(By.xpath(excel.getData(5, 476, 1))).sendKeys("Emv51");
	    			Thread.sleep(1000);
	    			//Ip address
					driver.findElement(By.xpath(excel.getData(5, 477, 1))).sendKeys("192.50.96.7");
					Thread.sleep(1000);
					//Type
					Select s7 = new Select(driver.findElement(By.xpath(excel.getData(5, 478, 1))));
	    			s7.selectByVisibleText("CLOVER");	
	    			Thread.sleep(1000);
	    			//Port ID
	    			driver.findElement(By.xpath(excel.getData(5, 481, 1))).sendKeys("4");
	    			Thread.sleep(1000);
	    			//Add button
	    			driver.findElement(By.xpath(excel.getData(5, 482, 1))).click();
	    			
	    			Thread.sleep(1000);
	    			//Add new EMV Setting
					driver.findElement(By.xpath(excel.getData(5, 475, 1))).click();
					Thread.sleep(1000);
					//name
	    			driver.findElement(By.xpath(excel.getData(5, 476, 1))).sendKeys("Emv16");
	    			Thread.sleep(1000);
	    			//ip ADdress
					driver.findElement(By.xpath(excel.getData(5, 477, 1))).sendKeys("192.50.97.8");
					Thread.sleep(1000);
					//Select type
					Select s8 = new Select(driver.findElement(By.xpath(excel.getData(5, 478, 1))));
	    			s8.selectByVisibleText("MONERIS");	
	    			Thread.sleep(1000);
	    			//Terminal No
	    			driver.findElement(By.xpath(excel.getData(5, 481, 1))).sendKeys("3");
	    			Thread.sleep(1000);
	    			//Add button
	    			driver.findElement(By.xpath(excel.getData(5, 482, 1))).click();
	    			
	    			Thread.sleep(1000);
	    			//Add new EMV Setting
					driver.findElement(By.xpath(excel.getData(5, 475, 1))).click();
					Thread.sleep(1000);
					//Emv Name
	    			driver.findElement(By.xpath(excel.getData(5, 476, 1))).sendKeys("Emv25");
	    			Thread.sleep(1000);
	    			//type
					Select s9 = new Select(driver.findElement(By.xpath(excel.getData(5, 478, 1))));
	    			s9.selectByVisibleText("INGENICO TR");	
	    			Thread.sleep(1000);
	    			//terminal Id
	    			driver.findElement(By.xpath(excel.getData(5, 480, 1))).sendKeys("10");
	    			Thread.sleep(1000);
	    			//Add button
	    			driver.findElement(By.xpath(excel.getData(5, 482, 1))).click();
	    			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
	    			Thread.sleep(2000);
					//Update the Existing one
	    			driver.findElement(By.xpath(excel.getData(5, 483, 1))).click();
	    			Thread.sleep(1000);
	    			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
	    			Thread.sleep(1000);
	    			//NAME UPDATE
	    			driver.findElement(By.xpath(excel.getData(5, 476, 1))).clear();
	    			driver.findElement(By.xpath(excel.getData(5, 476, 1))).sendKeys("Emupdate");
	    			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
	    			Thread.sleep(1000);
	    			//teRMINAL ID
	    			try {
	    			driver.findElement(By.xpath(excel.getData(5, 480, 1))).clear();
	    			driver.findElement(By.xpath(excel.getData(5, 480, 1))).sendKeys("1");
					driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
	    			Thread.sleep(1000);
	    			//Port No
				    driver.findElement(By.xpath(excel.getData(5, 481, 1))).clear();
	    			driver.findElement(By.xpath(excel.getData(5, 481, 1))).sendKeys("10");} catch(Exception h) {}
	    			Thread.sleep(1000);
	    			//update the existing one
	    			driver.findElement(By.xpath(excel.getData(5, 484, 1))).click();
	    			Thread.sleep(3000);
	    			//Click the update button
					driver.findElement(By.xpath(excel.getData(5, 445, 1))).click();
					Thread.sleep(3000);
					
					//Check weather the Payments setting is updated or not  Payments Settings updated successfully
					if(driver.findElement(By.cssSelector(excel.getData(5, 6, 4))).getText().equalsIgnoreCase("Payments updated successfully"))
					{
						test.log(LogStatus.PASS, "Payment Settings updated successfully");
					}
					
					else{
						test.log(LogStatus.FAIL, "Payment Settings updated fail");
					}
					
				}

	



}
