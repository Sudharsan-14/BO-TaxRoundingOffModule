package newReadExcelFile_New;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

public class SaleUsingExcelFile {
	
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
		driver.findElement(By.name("txtpassword")).sendKeys("linga123");
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
	
	@SuppressWarnings("unlikely-arg-type")
	@Test(priority=2)
	public void make_Sale() throws Exception
	{
		File src = new File("E:\\Automation\\WebPOSData.xlsx");
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);
					
		int rowCount = sheet1.getLastRowNum();
		
		int columnCount = sheet1.getRow(0).getLastCellNum();
		
		System.out.println("Total Row Count is : "+rowCount);
		
		System.out.println("Total Column is : "+columnCount);
		
		ExcelDataConfig excel = new ExcelDataConfig("E:\\Automation\\WebPOSData.xlsx");
		
		for(int i = 1; i < rowCount; i++)
		{
			
/*			for(int j = 0 ; j < columnCount; j++)
			{}*/

				//Click the required Category
				driver.findElement(By.xpath("//h6[.='"+excel.getData(0, i, 0)+"']")).click();
				
				if(excel.getData(0, i, 1).equals("Not Available"))
				{
					System.out.println("There Is No Sub Category available under Category");
				}
				else
				{
					//Click the required Sub Category
					driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 1)+"']")).click();
					
					if(excel.getData(0, i, 2).equals("Not Available"))
					{
						System.out.println("There Is No Menu Item available under the Sub Category");
					}
					
					else
					{
						//Click the Menu Item
						driver.findElement(By.xpath("//p[.='"+excel.getData(0, i, 2)+"']")).click();
						
						if(excel.getData(0, i, 3).equals("Not Available") && excel.getData(0, i, 5).equals("Not Available") && excel.getData(0, i, 20).equals("Not Available"))
						{
							System.out.println("There Is No Serving Size and No Modifiers are available");
							
							test.log(LogStatus.INFO, "There Is No Serving Size and No Modifiers are available");
						}
						
						else if(excel.getData(0, i, 3).equals("Not Available") && excel.getData(0, i, 5).equals("Not Available") && !excel.getData(0, i, 20).equals("Not Available"))
						{
							System.out.println("There is only Mandatory Modifier is available");
							
							test.log(LogStatus.INFO, "There is only Mandatory Modifier is available");
							
							String mandatoryModifierCount = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
							
							mandatoryModifierCount = mandatoryModifierCount.replace(" ", "");
							
							int modCount = Integer.parseInt(mandatoryModifierCount);
							
							//Get the maximum modifier count
							System.out.println("Maximum modifier Count is : "+modCount);
							
							
							
							//Click the mandatory modifier group
							driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, 20)+"']")).click();
							
							
							if(excel.getData(0, i, 21).equals("Not Available"))
							{}else{
							//Click the modifier
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
							}
							
							if(excel.getData(0, i, 22).equals("Not Available"))
							{}else{
							//Click the prefix
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
							}
							
							//Check whether the minimum modifier count is reached or not							
							if(driver.findElement(By.xpath("//div[@class='qsr-badge ng-star-inserted']")).getSize().equals(modCount))
							{
								/*//Click the done button
								driver.findElement(By.id("qsr-sell-popup-doneMod")).click();*/			
								
								if(excel.getData(0, i, 31).equals("Not Available"))
								{
									System.out.println("There is no more Mandatory Modifiers are not available");
									
									test.log(LogStatus.INFO, "There is no more Mandatory Modifiers are not available");
								
									break;
								}
								
								else
								{
									String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
									
									mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
									
									int modCount1 = Integer.parseInt(mandatoryModifierCount1);
									
									//Get the maximum modifier count
									System.out.println("Maximum modifier Count is : "+modCount1);

									
									if(excel.getData(0, i, 32).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
									}
									
									if(excel.getData(0, i, 33).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
									}
									
									if(excel.getData(0, i, 34).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
									}
																
									if(excel.getData(0, i, 35).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
									}

									if(excel.getData(0, i, 36).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
									}
									
									if(excel.getData(0, i, 37).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
									}

									if(excel.getData(0, i, 38).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
									}
									
									if(excel.getData(0, i, 39).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
									}

									if(excel.getData(0, i, 40).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 40)+"']")).click();
									}
									
									if(excel.getData(0, i, 41).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 41)+"']")).click();
									}
								}
								
								
							}
							
							
							if(excel.getData(0, i, 23).equals("Not Available"))
							{}else{
							//Click the modifier
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
							}
														
							if(excel.getData(0, i, 24).equals("Not Available"))
							{}else{
							//Click the prefix
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
							}
							
							//Check whether the minimum modifier count is reached or not							
							if(driver.findElement(By.xpath("//div[@class='qsr-badge ng-star-inserted']")).getSize().equals(modCount))
							{
								/*//Click the done button
								driver.findElement(By.id("qsr-sell-popup-doneMod")).click();*/			
								
								if(excel.getData(0, i, 31).equals("Not Available"))
								{
									System.out.println("There is no more Mandatory Modifiers are not available");
									
									test.log(LogStatus.INFO, "There is no more Mandatory Modifiers are not available");
								
									break;
								}
								
								else
								{
									String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
									
									mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
									
									int modCount1 = Integer.parseInt(mandatoryModifierCount1);
									
									//Get the maximum modifier count
									System.out.println("Maximum modifier Count is : "+modCount1);

									
									if(excel.getData(0, i, 32).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
									}
									
									if(excel.getData(0, i, 33).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
									}
									
									if(excel.getData(0, i, 34).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
									}
																
									if(excel.getData(0, i, 35).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
									}

									if(excel.getData(0, i, 36).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
									}
									
									if(excel.getData(0, i, 37).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
									}

									if(excel.getData(0, i, 38).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
									}
									
									if(excel.getData(0, i, 39).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
									}

									if(excel.getData(0, i, 40).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 40)+"']")).click();
									}
									
									if(excel.getData(0, i, 41).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 41)+"']")).click();
									}
								}
								
								
							}
							
							
							if(excel.getData(0, i, 25).equals("Not Available"))
							{}else{
							//Click the modifier
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
							}
							
							if(excel.getData(0, i, 26).equals("Not Available"))
							{}else{
							//Click the prefix
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
							}
							
							//Check whether the minimum modifier count is reached or not							
							if(driver.findElement(By.xpath("//div[@class='qsr-badge ng-star-inserted']")).getSize().equals(modCount))
							{
								/*//Click the done button
								driver.findElement(By.id("qsr-sell-popup-doneMod")).click();*/			
								
								if(excel.getData(0, i, 31).equals("Not Available"))
								{
									System.out.println("There is no more Mandatory Modifiers are not available");
									
									test.log(LogStatus.INFO, "There is no more Mandatory Modifiers are not available");
								
									break;
								}
								
								else
								{
									String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
									
									mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
									
									int modCount1 = Integer.parseInt(mandatoryModifierCount1);
									
									//Get the maximum modifier count
									System.out.println("Maximum modifier Count is : "+modCount1);

									
									if(excel.getData(0, i, 32).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
									}
									
									if(excel.getData(0, i, 33).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
									}
									
									if(excel.getData(0, i, 34).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
									}
																
									if(excel.getData(0, i, 35).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
									}

									if(excel.getData(0, i, 36).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
									}
									
									if(excel.getData(0, i, 37).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
									}

									if(excel.getData(0, i, 38).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
									}
									
									if(excel.getData(0, i, 39).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
									}

									if(excel.getData(0, i, 40).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 40)+"']")).click();
									}
									
									if(excel.getData(0, i, 41).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 41)+"']")).click();
									}
								}
								
								
							}
							
							
							if(excel.getData(0, i, 27).equals("Not Available"))
							{}else{
							//Click the modifier
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 27)+"']")).click();
							}
							
							if(excel.getData(0, i, 28).equals("Not Available"))
							{}else{
							//Click the prefix
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 28)+"']")).click();
							}
							
							//Check whether the minimum modifier count is reached or not							
							if(driver.findElement(By.xpath("//div[@class='qsr-badge ng-star-inserted']")).getSize().equals(modCount))
							{
								/*//Click the done button
								driver.findElement(By.id("qsr-sell-popup-doneMod")).click();*/			
								
								if(excel.getData(0, i, 31).equals("Not Available"))
								{
									System.out.println("There is no more Mandatory Modifiers are not available");
									
									test.log(LogStatus.INFO, "There is no more Mandatory Modifiers are not available");
								
									break;
								}
								
								else
								{
									String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
									
									mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
									
									int modCount1 = Integer.parseInt(mandatoryModifierCount1);
									
									//Get the maximum modifier count
									System.out.println("Maximum modifier Count is : "+modCount1);

									
									if(excel.getData(0, i, 32).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
									}
									
									if(excel.getData(0, i, 33).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
									}
									
									if(excel.getData(0, i, 34).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
									}
																
									if(excel.getData(0, i, 35).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
									}

									if(excel.getData(0, i, 36).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
									}
									
									if(excel.getData(0, i, 37).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
									}

									if(excel.getData(0, i, 38).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
									}
									
									if(excel.getData(0, i, 39).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
									}

									if(excel.getData(0, i, 40).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 40)+"']")).click();
									}
									
									if(excel.getData(0, i, 41).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 41)+"']")).click();
									}
								}
								
								
							}
							
							
							if(excel.getData(0, i, 29).equals("Not Available"))
							{}else{
							//Click the modifier
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 29)+"']")).click();
							}
							
							if(excel.getData(0, i, 30).equals("Not Available"))
							{}else{
							//Click the prefix
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 30)+"']")).click();
							}

							//Check whether the minimum modifier count is reached or not							
							if(driver.findElement(By.xpath("//div[@class='qsr-badge ng-star-inserted']")).getSize().equals(modCount))
							{
								/*//Click the done button
								driver.findElement(By.id("qsr-sell-popup-doneMod")).click();*/			
								
								if(excel.getData(0, i, 31).equals("Not Available"))
								{
									System.out.println("There is no more Mandatory Modifiers are not available");
									
									test.log(LogStatus.INFO, "There is no more Mandatory Modifiers are not available");
								
									break;
								}
								
								else
								{
									String mandatoryModifierCount1 = driver.findElement(By.id("qsr-sell-remaining-mod-qnty")).getText();
									
									mandatoryModifierCount1 = mandatoryModifierCount1.replace(" ", "");
									
									int modCount1 = Integer.parseInt(mandatoryModifierCount1);
									
									//Get the maximum modifier count
									System.out.println("Maximum modifier Count is : "+modCount1);

									
									if(excel.getData(0, i, 32).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
									}
									
									if(excel.getData(0, i, 33).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
									}
									
									if(excel.getData(0, i, 34).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
									}
																
									if(excel.getData(0, i, 35).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
									}

									if(excel.getData(0, i, 36).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
									}
									
									if(excel.getData(0, i, 37).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
									}

									if(excel.getData(0, i, 38).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
									}
									
									if(excel.getData(0, i, 39).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
									}

									if(excel.getData(0, i, 40).equals("Not Available"))
									{}else{
									//Click the modifier
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 40)+"']")).click();
									}
									
									if(excel.getData(0, i, 41).equals("Not Available"))
									{}else{
									//Click the prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 41)+"']")).click();
									}
								}
								
								
							}
						}
					}
				}
			

		}
		
		wb.close();
		
		
	}

}
