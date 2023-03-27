package newReadExcelFile_New;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SaleWithoutUsingExcelFile {
	
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("SaleUsingExcelFile");
	
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
		//Call the chrome driver
		System.setProperty("webdriver.chrome.driver","E:\\Muthu Files\\Software\\chromedriver_win32\\chromedriver.exe");
		//Open the Chrome window
		driver = new ChromeDriver();
		//Wait for 30 seconds
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Maximize the Firefox window
		driver.manage().window().maximize();

		//Launch the URL
		driver.get("http://dev.lingapos.com:8081/");
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		
		Thread.sleep(2000);
		//Clear the text from the user name text box
		driver.findElement(By.name("txtusername")).clear();
		//Enter the user name
		driver.findElement(By.name("txtusername")).sendKeys("gateway@mail.com");
		//Clear the password from the password text box
		driver.findElement(By.name("txtpassword")).clear();
		//Enter the password
		driver.findElement(By.name("txtpassword")).sendKeys("gateway1");
		Thread.sleep(2000);
		//Click the login button
		driver.findElement(By.xpath("//button[@id='loginpage-loginButton']")).click();
		Thread.sleep(5000);
		//Check if we logged in or not
		if(driver.findElement(By.xpath("//span[.='To login enter your ID number or swipe the card']")).getText().equalsIgnoreCase("To login enter your ID number or swipe the card"))
		{
			test.log(LogStatus.PASS,"User Logged in Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL,"User Logged in Failed");
		}
		Thread.sleep(5000);
		//Enter the required ID number
		driver.findElement(By.id("retailer-punching-enterPinNumber-1")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("retailer-punching-enterPinNumber-2")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("retailer-punching-enterPinNumber-3")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("retailer-punching-enterPinNumber-4")).click();
		Thread.sleep(1000);
		
/*			//Click the Enter or Go button
		driver.findElement(By.id("retailer-punching-btn-sizehide-circle")).click();
		Thread.sleep(2000);
		//Click the Admin option
		driver.findElement(By.xpath("//li[.='Admin']")).click();
		Thread.sleep(2000);
		//Click the OK button
		driver.findElement(By.xpath("//span[.='Ok']")).click();*/
					
		Thread.sleep(5000);
		//Check Whether the user reach the QSR screen or not
		if(driver.findElement(By.xpath("//div[.='Void']")).getText().equalsIgnoreCase("Void"))
		{
			test.log(LogStatus.PASS, "User Reach the QSR or Retails page");
		}
		else
		{
			test.log(LogStatus.FAIL, "User not Reach the QSR or Retails page");
		}
		Thread.sleep(3000);
		
		
	}
		
	@Test(priority=30)
	public void logout() throws InterruptedException
	{

		Thread.sleep(3000);
		//Wait for 30 seconds
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  
		// Click on Log off button
		driver.findElement(By.xpath("//div[.='Log Off']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	    Thread.sleep(10000);
		// Click on Sign out button
		driver.findElement(By.xpath("html/body/ion-app/ng-component/ion-nav/page-punchin/ion-content/div[2]/app-retailer-punching/div/div[1]/div[3]/div/mat-grid-list/div/mat-grid-tile[3]/figure/button")).click();
								   

			Thread.sleep(1000);
			//Click Yes button in the Data lose popup
			driver.findElement(By.xpath("//button[@id='alert-yes']")).click();
			
					
					Thread.sleep(2000);
					//Click the correct pin
					driver.findElement(By.xpath("//div[@class='numberpad-sell']/div[1]/button[1]")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//div[@class='numberpad-sell']/div[1]/button[2]")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//div[@class='numberpad-sell']/div[1]/button[3]")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//div[@class='numberpad-sell']/div[1]/button[4]")).click();
					
					Thread.sleep(1000);
					//Click the Continue button
					driver.findElement(By.xpath("//span[.='Continue']")).click();
					
					
					Thread.sleep(3000);
				   //Check whether user get logged out or not
					if(driver.findElement(By.xpath("//h1[.='Account Login']")).getText().equalsIgnoreCase("Account Login"))
					{
				    	test.log(LogStatus.PASS,"User Logged out Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL,"User Logged out Failed");
					}
					Thread.sleep(2000);
					//Close the Browser
					driver.close();

	}
	
	@Test(priority=2)
	public void make_Sale() throws Exception
	{
		Thread.sleep(2000);
		//Get the Count of Category
		List<WebElement> catCount = driver.findElements(By.xpath("//nav[@id='box']/div/div/div/h6"));
		catCount.size();
		
		for(int i = 1; i <= catCount.size(); i++)
		{
			//Click the category
			driver.findElement(By.xpath("//nav[@id='box']/div/div["+i+"]/div/h6")).click();
			
			
			//Get the Sub Category count
			List<WebElement> subCatCount = driver.findElements(By.xpath("//div[@id='sub-cat']/div/div/div"));
			subCatCount.size();
			System.out.println("Sub Category Count is : "+subCatCount.size());
			
			//*********  THIS TRY CATCH BLOCK FOR SUB CATEGORY   **********//
			try {
					//Check whether the Sub Category is available or not
					if(driver.findElement(By.xpath("//div[@id='sub-cat']/div[1]/div/div")).isDisplayed())
					{
						
						
						for(int j = 0; j <= subCatCount.size(); j++)
						{
							
							
							Thread.sleep(1500);
							//Click the sub category
							driver.findElement(By.xpath("//div[@id='sub-cat']/div["+j+"]/div")).click();
							System.out.println("Enter");
							
							//*********  THIS TRY CATCH BLOCK FOR MENU ITEM OF SUB CATEGORY  ***********//
							
							try {
								//Check whether the menu item is available or not
								if(driver.findElement(By.xpath("//div[@id='sub-cat-food-name']/div/div/p")).isDisplayed())
								{
									//Get the Count of Menu Items Under the Sub Category
									List<WebElement> menuItemOfSubCat = driver.findElements(By.xpath("//div[@id='sub-cat-food-name']/div/div/p"));
									menuItemOfSubCat.size();
									
									for(int k = 0; k <= menuItemOfSubCat.size(); k++)
									{
										//Click the menu item
										driver.findElement(By.xpath("//div[@id='sub-cat-food-name']/div["+k+"]/div/p")).click();
										
										//Get the count of modifiers
										List<WebElement> modifierCount = driver.findElements(By.id("qsr-sell-popup-breadcrumbItem"));
										modifierCount.size();
										
										//************** THIS TRY CATCH BLOCK FOR MODIFIERS  *************//
										
										//Check whether the selected modifier is mandatory modifier or not
										if(driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).isDisplayed())
										{
											String mandatoryModifierCount = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
											
											mandatoryModifierCount = mandatoryModifierCount.replace(" ", "");
											
											int modCount = Integer.parseInt(mandatoryModifierCount);
											
											//Get the maximum modifier count
											System.out.println("Maximum modifier Count is : "+modCount);
											
											test.log(LogStatus.INFO, "Maximum modifier Count is : "+modCount);
											
											for(int l = 1; l <= modCount; l++)
											{
												//Click the modifier
												driver.findElement(By.xpath("//div[@class='qsr-cat-mdi-u qsr-modifi-over-scrol']/div/div["+l+"]/div/div")).click();
												
												try {
													//Check whether the prefix is available or not
													if(driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).isDisplayed())
													{
														//Click the first prefix
														driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).click();
													}
												} catch (Exception e) {
													//Click the modifier
													driver.findElement(By.xpath("//div[@class='qsr-cat-mdi-u qsr-modifi-over-scrol']/div/div["+l+1+"]/div/div")).click();
													
													try {
														//Check whether the prefix is available or not
														if(driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).isDisplayed())
														{
															//Click the first prefix
															driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).click();
														}
													} catch (Exception e1) {
														
													}
												}

											}
											
											List<WebElement> mandatoryModifierGroupCount = driver.findElements(By.xpath("//li[@class='text-white ng-star-inserted isMandatory']"));
											mandatoryModifierGroupCount.size();
											
											// Check whether the another modifier group is available or not
											if(mandatoryModifierGroupCount.size() != 0)
											{
												for(int m = 0; m <= mandatoryModifierGroupCount.size(); m++)
												{

													String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
													
													mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
													
													int modCount1 = Integer.parseInt(mandatoryModifierCount1);
													
													//Get the maximum modifier count
													System.out.println("Maximum modifier Count is : "+modCount1);
													
													test.log(LogStatus.INFO, "Maximum modifier Count is : "+modCount1);
													
													for(int l = 1; l <= modCount1; l++)
													{
														//Click the modifier
														driver.findElement(By.xpath("//div[@class='qsr-cat-mdi-u qsr-modifi-over-scrol']/div/div["+l+"]/div/div")).click();
														
														try {
															//Check whether the prefix is available or not
															if(driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).isDisplayed())
															{
																//Click the first prefix
																driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).click();
															}
														} catch (Exception e) {
															//Click the modifier
															driver.findElement(By.xpath("//div[@class='qsr-cat-mdi-u qsr-modifi-over-scrol']/div/div["+l+1+"]/div/div")).click();
															
															try {
																//Check whether the prefix is available or not
																if(driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).isDisplayed())
																{
																	//Click the first prefix
																	driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).click();
																}
															} catch (Exception e1) {
																
															}
														}

													}

												}
											}
											
											
											for(int n = 1; n <= modifierCount.size(); n++)
											{
												// Click the remaining modifiers
												driver.findElement(By.xpath("//div[@class='row mt-3']/div/div/ol/li["+n+"]/a/span")).click();
												
												//Click the first modifier
												driver.findElement(By.xpath("//div[@class='qsr-cat-mdi-u qsr-modifi-over-scrol']/div/div[1]/div/div")).click();
												
												try {
													//Check whether the prefix is available or not
													if(driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).isDisplayed())
													{
														//Click the first prefix
														driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).click();
													}
												} catch (Exception e) {
													}
												}
											
											try {
												//Check whether the max count is reached or not
												if(driver.findElement(By.xpath("//mat-dialog-content[.='You reached the maximum count']")).isDisplayed())
												{
													//Click the Done button
													driver.findElement(By.xpath("//span[.='Done']")).click();
												}
											} catch (Exception e) {
												
												e.printStackTrace();
											}
											
											
										}
										
										
										else
										{
											for(int n = 1; n <= modifierCount.size(); n++)
											{
												// Click the remaining modifiers
												driver.findElement(By.xpath("//div[@class='row mt-3']/div/div/ol/li["+n+"]/a/span")).click();
												
												//Click the first modifier
												driver.findElement(By.xpath("//div[@class='qsr-cat-mdi-u qsr-modifi-over-scrol']/div/div[1]/div/div")).click();
												
												try {
													//Check whether the prefix is available or not
													if(driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).isDisplayed())
													{
														//Click the first prefix
														driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).click();
													}
												} catch (Exception e) {
													}
												}
											
											try {
												//Check whether the max count is reached or not
												if(driver.findElement(By.xpath("//mat-dialog-content[.='You reached the maximum count']")).isDisplayed())
												{
													//Click the Done button
													driver.findElement(By.xpath("//span[.='Done']")).click();
												}
											} catch (Exception e) {
												
												e.printStackTrace();
											}
										}
									}

								}
							} catch (Exception e) {
								//Click the category
								driver.findElement(By.xpath("//nav[@id='box']/div/div["+i+"]/div/h6")).click();
								
							}
							
							 List<WebElement> itemCount = driver.findElements(By.xpath("//div[@class='qsr-min-hi']/div/table/tbody/tr"));
							 int totalItem = itemCount.size();
							 
							 if(totalItem == 100)
							 {
								 
								 	//print the Check number
								 	System.out.println("Check number is : "+driver.findElement(By.xpath("//div[@class='col-lg-6 mt-1 qsr-sell-pl1p2 qsr-check']")).getText());
								 	
								 	test.log(LogStatus.INFO, "Check number is : "+driver.findElement(By.xpath("//div[@class='col-lg-6 mt-1 qsr-sell-pl1p2 qsr-check']")).getText());

								 	//Print the Total amount
								 	System.out.println("Total amount is : "+driver.findElement(By.xpath("//div[@class='card-footer qsr-sell-pad-tot']/div/div[3]/div[2]/span")).getText());
								 	
								 	test.log(LogStatus.INFO, "Total amount is : "+driver.findElement(By.xpath("//div[@class='card-footer qsr-sell-pad-tot']/div/div[3]/div[2]/span")).getText());
								 
							 
									Thread.sleep(1000);
									// Click on Fast Cash button
									driver.findElement(By.xpath("//div[.='Fast Cash']")).click();
									driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
									
									Thread.sleep(2000);
									//Click the Amount button
									driver.findElement(By.id("fast-cash-bal-amt")).click();
									
									Thread.sleep(1000);
									//Click the Enter button
									driver.findElement(By.id("fastCashEnter")).click();
									Thread.sleep(5000);
									
									if(driver.findElement(By.tagName("simple-snack-bar")).getText().equalsIgnoreCase("check close succesfully"))
									{
										test.log(LogStatus.PASS, "Payment success");
									}
									else
									{
										test.log(LogStatus.FAIL, "Payment fail");
									}
									Thread.sleep(3000);
									
									break;
							 }
						}
					}
				} 
			catch (Exception e) {
					//Click the category
					driver.findElement(By.xpath("//nav[@id='box']/div/div["+i+"]/div/h6")).click();
				}
			
			
			
			
			
			
			
			
			//*********  THIS TRY CATCH BLOCK FOR MENU ITEM OF CATEGORY  ***********//
			
			try {
				//Check whether the menu item is available or not
				if(driver.findElement(By.xpath("//div[@id='sub-cat-food-name']/div/div/p")).isDisplayed())
				{
					//Get the Count of Menu Items Under the Sub Category
					List<WebElement> menuItemOfSubCat = driver.findElements(By.xpath("//div[@id='sub-cat-food-name']/div/div/p"));
					menuItemOfSubCat.size();
					
					for(int k = 0; k <= menuItemOfSubCat.size(); k++)
					{
						//Click the menu item
						driver.findElement(By.xpath("//div[@id='sub-cat-food-name']/div["+k+"]/div/p")).click();
						
						//Get the count of modifiers
						List<WebElement> modifierCount = driver.findElements(By.id("qsr-sell-popup-breadcrumbItem"));
						modifierCount.size();
						
						//************** THIS TRY CATCH BLOCK FOR MODIFIERS  *************//
						
						//Check whether the selected modifier is mandatory modifier or not
						if(driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).isDisplayed())
						{
							String mandatoryModifierCount = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
							
							mandatoryModifierCount = mandatoryModifierCount.replace(" ", "");
							
							int modCount = Integer.parseInt(mandatoryModifierCount);
							
							//Get the maximum modifier count
							System.out.println("Maximum modifier Count is : "+modCount);
							
							test.log(LogStatus.INFO, "Maximum modifier Count is : "+modCount);
							
							for(int l = 1; l <= modCount; l++)
							{
								//Click the modifier
								driver.findElement(By.xpath("//div[@class='qsr-cat-mdi-u qsr-modifi-over-scrol']/div/div["+l+"]/div/div")).click();
								
								try {
									//Check whether the prefix is available or not
									if(driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).isDisplayed())
									{
										//Click the first prefix
										driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).click();
									}
								} catch (Exception e) {
									//Click the modifier
									driver.findElement(By.xpath("//div[@class='qsr-cat-mdi-u qsr-modifi-over-scrol']/div/div["+l+1+"]/div/div")).click();
									
									try {
										//Check whether the prefix is available or not
										if(driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).isDisplayed())
										{
											//Click the first prefix
											driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).click();
										}
									} catch (Exception e1) {
										
									}
								}

							}
							
							List<WebElement> mandatoryModifierGroupCount = driver.findElements(By.xpath("//li[@class='text-white ng-star-inserted isMandatory']"));
							mandatoryModifierGroupCount.size();
							
							// Check whether the another modifier group is available or not
							if(mandatoryModifierGroupCount.size() != 0)
							{
								for(int m = 0; m <= mandatoryModifierGroupCount.size(); m++)
								{

									String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
									
									mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
									
									int modCount1 = Integer.parseInt(mandatoryModifierCount1);
									
									//Get the maximum modifier count
									System.out.println("Maximum modifier Count is : "+modCount1);
									
									test.log(LogStatus.INFO, "Maximum modifier Count is : "+modCount1);
									
									for(int l = 1; l <= modCount1; l++)
									{
										//Click the modifier
										driver.findElement(By.xpath("//div[@class='qsr-cat-mdi-u qsr-modifi-over-scrol']/div/div["+l+"]/div/div")).click();
										
										try {
											//Check whether the prefix is available or not
											if(driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).isDisplayed())
											{
												//Click the first prefix
												driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).click();
											}
										} catch (Exception e) {
											//Click the modifier
											driver.findElement(By.xpath("//div[@class='qsr-cat-mdi-u qsr-modifi-over-scrol']/div/div["+l+1+"]/div/div")).click();
											
											try {
												//Check whether the prefix is available or not
												if(driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).isDisplayed())
												{
													//Click the first prefix
													driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).click();
												}
											} catch (Exception e1) {
												
											}
										}

									}

								}
							}
							
							
							for(int n = 1; n <= modifierCount.size(); n++)
							{
								// Click the remaining modifiers
								driver.findElement(By.xpath("//div[@class='row mt-3']/div/div/ol/li["+n+"]/a/span")).click();
								
								//Click the first modifier
								driver.findElement(By.xpath("//div[@class='qsr-cat-mdi-u qsr-modifi-over-scrol']/div/div[1]/div/div")).click();
								
								try {
									//Check whether the prefix is available or not
									if(driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).isDisplayed())
									{
										//Click the first prefix
										driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).click();
									}
								} catch (Exception e) {
									}
								}
							
							try {
								//Check whether the max count is reached or not
								if(driver.findElement(By.xpath("//mat-dialog-content[.='You reached the maximum count']")).isDisplayed())
								{
									//Click the Done button
									driver.findElement(By.xpath("//span[.='Done']")).click();
								}
							} catch (Exception e) {
								
								e.printStackTrace();
							}
							
							
						}
						
						
						else
						{
							for(int n = 1; n <= modifierCount.size(); n++)
							{
								// Click the remaining modifiers
								driver.findElement(By.xpath("//div[@class='row mt-3']/div/div/ol/li["+n+"]/a/span")).click();
								
								//Click the first modifier
								driver.findElement(By.xpath("//div[@class='qsr-cat-mdi-u qsr-modifi-over-scrol']/div/div[1]/div/div")).click();
								
								try {
									//Check whether the prefix is available or not
									if(driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).isDisplayed())
									{
										//Click the first prefix
										driver.findElement(By.xpath("//div[@class='grid-container grid-cat-prefix']/div[1]/div/div")).click();
									}
								} catch (Exception e) {
									}
								}
							
							try {
								//Check whether the max count is reached or not
								if(driver.findElement(By.xpath("//mat-dialog-content[.='You reached the maximum count']")).isDisplayed())
								{
									//Click the Done button
									driver.findElement(By.xpath("//span[.='Done']")).click();
								}
							} catch (Exception e) {
								
								e.printStackTrace();
							}
						}
					}
					
					 List<WebElement> itemCount = driver.findElements(By.xpath("//div[@class='qsr-min-hi']/div/table/tbody/tr"));
					 int totalItem = itemCount.size();
					 
					 if(totalItem == 100)
					 {
						 
						 	//print the Check number
						 	System.out.println("Check number is : "+driver.findElement(By.xpath("//div[@class='col-lg-6 mt-1 qsr-sell-pl1p2 qsr-check']")).getText());
						 	
						 	test.log(LogStatus.INFO, "Check number is : "+driver.findElement(By.xpath("//div[@class='col-lg-6 mt-1 qsr-sell-pl1p2 qsr-check']")).getText());

						 	//Print the Total amount
						 	System.out.println("Total amount is : "+driver.findElement(By.xpath("//div[@class='card-footer qsr-sell-pad-tot']/div/div[3]/div[2]/span")).getText());
						 	
						 	test.log(LogStatus.INFO, "Total amount is : "+driver.findElement(By.xpath("//div[@class='card-footer qsr-sell-pad-tot']/div/div[3]/div[2]/span")).getText());
						 
					 
							Thread.sleep(1000);
							// Click on Fast Cash button
							driver.findElement(By.xpath("//div[.='Fast Cash']")).click();
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							
							Thread.sleep(2000);
							//Click the Amount button
							driver.findElement(By.id("fast-cash-bal-amt")).click();
							
							Thread.sleep(1000);
							//Click the Enter button
							driver.findElement(By.id("fastCashEnter")).click();
							Thread.sleep(5000);
							
							if(driver.findElement(By.tagName("simple-snack-bar")).getText().equalsIgnoreCase("check close succesfully"))
							{
								test.log(LogStatus.PASS, "Payment success");
							}
							else
							{
								test.log(LogStatus.FAIL, "Payment fail");
							}
							Thread.sleep(3000);
							
							break;
					 }

				}
			} catch (Exception e) {
				//Click the category
				driver.findElement(By.xpath("//nav[@id='box']/div/div["+i+"]/div/h6")).click();
				
			}
			
			
		
		}
	}

}
