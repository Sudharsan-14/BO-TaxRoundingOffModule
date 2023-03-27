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

public class SaleUsingExcelFile2 {
	
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
		driver.findElement(By.xpath("html/body/ion-app/ng-component/ion-nav/page-punchin/ion-content/div[2]/app-retailer-punching/div/div[1]/div[2]/div/mat-grid-list/div/mat-grid-tile[3]/figure/button/span")).click();
								   

			Thread.sleep(1000);
			//Click Yes button in the Data lose popup
			driver.findElement(By.xpath("//button[@id='alert-yes']")).click();
			
					
					Thread.sleep(2000);
					//Click the correct pin
					driver.findElement(By.xpath("//span[.='1']")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//span[.='2']")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//span[.='3']")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//span[.='4']")).click();
					
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
		
		File src = new File("E:\\Automation\\WebPOSData.xlsx");
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);
					
		int rowCount = sheet1.getLastRowNum();
		
		int columnCount = sheet1.getRow(0).getLastCellNum();
		
		System.out.println("Total Row Count is : "+rowCount);
		
		System.out.println("Total Column is : "+columnCount);
		
		ExcelDataConfig excel = new ExcelDataConfig("E:\\Automation\\WebPOSData.xlsx");
		
		//ForMandatoryModifier manMod = new ForMandatoryModifier();
		
		for(int i = 1; i <= rowCount; i++)
		{
			
			//Click the required Category
			driver.findElement(By.xpath("//h6[.='"+excel.getData(0, i, 0)+"']")).click();
			
			
			
			//IF SUB CATEGORY IS AVAILABLE THEN THE BELOW CODE WILL RUN
			//Check whether the sub category is available or not
			if(!excel.getData(0, i, 1).equals("Not Available"))
			{
				//Click the required Sub Category
				driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 1)+"']")).click();
				
				//Check whether the menu item is available or not
				if(!excel.getData(0, i, 2).equals("Not Available"))
				{
					//Click the Menu Item
					driver.findElement(By.xpath("//p[.='"+excel.getData(0, i, 2)+"']")).click();
					
					//Check whether the Serving size and Modifiers are available or not
					if(excel.getData(0, i, 3).equals("Not Available"))
					{
						
						//FOR MANDATORY MODIFIRES
						if(!excel.getData(0, i, 20).equals("Not Available"))
							{
						
								if(excel.getData(0, i, 20).equals("Not Available")&&excel.getData(0, i, 31).equals("Not Available")&&excel.getData(0, i, 42).equals("Not Available")&&excel.getData(0, i, 53).equals("Not Available")&&excel.getData(0, i, 64).equals("Not Available"))
								{
									//Click the OPTIONAL MODIFIER
									
								}
								if(excel.getData(0, i, 20).equals("Not Available"))
								{
									
								}
								else
								{
									
									if(excel.getNumericData(0, i, 75)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 75)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 75)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 75)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 24).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 26).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 27)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 28).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 28)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 75)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 24).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 26).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 27)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 28).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 28)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 29)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 30).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 30)+"']")).click();
										}
									}
										
										

								}
							
								if(excel.getData(0, i, 31).equals("Not Available"))
								{
									
								}
								else
								{

									
									if(excel.getNumericData(0, i, 76)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 76)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 76)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 37).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 76)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 37).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 39).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 76)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 37).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 39).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 40)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 41).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 41)+"']")).click();
										}
									}

								}
								
								if(excel.getData(0, i, 42).equals("Not Available"))
								{
									
								}
								else
								{

									if(excel.getNumericData(0, i, 77)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 77)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 77)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 47)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 48).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 48)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 77)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 47)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 48).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 48)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 49)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 50).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 50)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 77)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 47)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 48).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 48)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 49)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 50).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 50)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 51)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 52).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 52)+"']")).click();
										}
									}

								
								}
								
								if(excel.getData(0, i, 53).equals("Not Available"))
								{
									
								}
								else
								{


									if(excel.getNumericData(0, i, 78)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 78)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 78)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 58)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 59).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 59)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 78)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 58)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 59).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 59)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 60)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 61).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 61)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 78)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 58)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 59).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 59)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 60)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 61).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 61)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 62)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 63).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 63)+"']")).click();
										}
									}

								}
								
								if(excel.getData(0, i, 64).equals("Not Available"))
								{
									
								}
								else
								{



									if(excel.getNumericData(0, i, 79)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 79)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 79)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 69)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 70).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 70)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 79)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 69)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 70).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 70)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 71)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 72).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 72)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 79)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 69)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 70).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 70)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 71)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 72).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 72)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 73)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 74).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 74)+"']")).click();
										}
									}

								
								}
							
						}

					
						
						for (int j = 5; j == 17; j=j+3)
						{
							//Check whether the  optional modifier is available or not
							if(excel.getData(0, i, j).equals("Not Available"))
							{

							}
							else
							{
								//Click the optional modifier group
								//driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
								Thread.sleep(1000);
								//Click the modifier
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
								Thread.sleep(1000);
								if(!excel.getData(0, i, j+2).equals("Not Available"))
								{
									//Click the modifier - Prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
								}
								else
								{
									
								}
							}
						
						}
						//Click the required Category
						driver.findElement(By.xpath("//h6[.='"+excel.getData(0, i, 0)+"']")).click();
						
					}
					
					// IF MODIFIER OR SERVING SIZE IS AVAILABLE THEN WE WRITE THE RESPECTIVE CODE HERE

					
					else
					{
						//Click the serving size
						driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, 3)+"']")).click();
						Thread.sleep(1000);
						//Click the serving size option
						driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 4)+"']")).click();
						Thread.sleep(1000);
						
						
						
						//FOR MANDATORY MODIFIRES
						if(!excel.getData(0, i, 20).equals("Not Available"))
							{
						
								if(excel.getData(0, i, 20).equals("Not Available")&&excel.getData(0, i, 31).equals("Not Available")&&excel.getData(0, i, 42).equals("Not Available")&&excel.getData(0, i, 53).equals("Not Available")&&excel.getData(0, i, 64).equals("Not Available"))
								{
									//Click the OPTIONAL MODIFIER
									
								}
								if(excel.getData(0, i, 20).equals("Not Available"))
								{
									
								}
								else
								{
									
									if(excel.getNumericData(0, i, 75)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 75)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 75)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 75)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 24).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 26).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 27)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 28).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 28)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 75)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 24).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 26).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 27)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 28).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 28)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 29)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 30).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 30)+"']")).click();
										}
									}
										
										

								}
							
								if(excel.getData(0, i, 31).equals("Not Available"))
								{
									
								}
								else
								{

									
									if(excel.getNumericData(0, i, 76)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 76)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 76)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 37).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 76)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 37).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 39).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 76)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 37).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 39).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 40)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 41).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 41)+"']")).click();
										}
									}

								}
								
								if(excel.getData(0, i, 42).equals("Not Available"))
								{
									
								}
								else
								{

									if(excel.getNumericData(0, i, 77)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 77)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 77)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 47)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 48).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 48)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 77)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 47)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 48).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 48)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 49)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 50).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 50)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 77)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 47)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 48).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 48)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 49)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 50).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 50)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 51)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 52).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 52)+"']")).click();
										}
									}

								
								}
								
								if(excel.getData(0, i, 53).equals("Not Available"))
								{
									
								}
								else
								{


									if(excel.getNumericData(0, i, 78)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 78)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 78)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 58)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 59).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 59)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 78)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 58)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 59).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 59)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 60)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 61).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 61)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 78)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 58)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 59).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 59)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 60)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 61).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 61)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 62)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 63).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 63)+"']")).click();
										}
									}

								}
								
								if(excel.getData(0, i, 64).equals("Not Available"))
								{
									
								}
								else
								{



									if(excel.getNumericData(0, i, 79)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 79)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 79)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 69)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 70).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 70)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 79)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 69)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 70).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 70)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 71)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 72).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 72)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 79)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 69)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 70).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 70)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 71)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 72).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 72)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 73)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 74).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 74)+"']")).click();
										}
									}

								
								}
							
						}

					
						
						for (int j = 5; j == 17; j=j+3)
						{
							//Check whether the  optional modifier is available or not
							if(excel.getData(0, i, j).equals("Not Available"))
							{

							}
							else
							{
								//Click the optional modifier group
								//driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
								Thread.sleep(1000);
								//Click the modifier
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
								Thread.sleep(1000);
								if(!excel.getData(0, i, j+2).equals("Not Available"))
								{
									//Click the modifier - Prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
								}
								else
								{
									
								}
							}
						
						}
					}
									
				}
				else
				{
					System.out.println("There Is No Menu Item available under the Sub Category");
					
					test.log(LogStatus.INFO, "There Is No Menu Item available under the Sub Category");
				}
			
			}
			
			//IF SUB CATEGORY IS AVAILABLE THEN THE ABOVE CODE WILL RUN
			
			//IF SUB CATEGORY IS NOT AVAILABLE THEN THE BELOW CODE WILL RUN
			else
			{
				//System.out.println("There Is No Sub Category available under Category");
				
				//test.log(LogStatus.INFO, "There Is No Sub Category available under Category");
				
				//Check whether the menu item is available or not
				if(excel.getData(0, i, 2).equals("Not Available"))
				{
					System.out.println("There Is No Menu Item available under Category");
					
					test.log(LogStatus.INFO, "There Is No Menu Item available under Category");
				}
				
				else
					
				{
					
					//System.out.println(excel.getData(0, i, 2));
					
					//Click the Menu Item
					driver.findElement(By.xpath("//p[.='"+excel.getData(0, i, 2)+"']")).click();
					
					
					//Check whether the Serving Size is available or not
					if(excel.getData(0, i, 3).equals("Not Available"))
					{
						

						
						//FOR MANDATORY MODIFIRES
						if(!excel.getData(0, i, 20).equals("Not Available"))
							{
						
								if(excel.getData(0, i, 20).equals("Not Available")&&excel.getData(0, i, 31).equals("Not Available")&&excel.getData(0, i, 42).equals("Not Available")&&excel.getData(0, i, 53).equals("Not Available")&&excel.getData(0, i, 64).equals("Not Available"))
								{
									//Click the OPTIONAL MODIFIER
									
								}
								if(excel.getData(0, i, 20).equals("Not Available"))
								{
									
								}
								else
								{
									
									if(excel.getNumericData(0, i, 75)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 75)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 75)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 75)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 24).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 26).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 27)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 28).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 28)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 75)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 24).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 26).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 27)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 28).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 28)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 29)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 30).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 30)+"']")).click();
										}
									}
										
										

								}
							
								if(excel.getData(0, i, 31).equals("Not Available"))
								{
									
								}
								else
								{

									
									if(excel.getNumericData(0, i, 76)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 76)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 76)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 37).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 76)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 37).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 39).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 76)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 37).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 39).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 40)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 41).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 41)+"']")).click();
										}
									}

								}
								
								if(excel.getData(0, i, 42).equals("Not Available"))
								{
									
								}
								else
								{

									if(excel.getNumericData(0, i, 77)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 77)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 77)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 47)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 48).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 48)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 77)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 47)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 48).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 48)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 49)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 50).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 50)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 77)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 47)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 48).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 48)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 49)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 50).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 50)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 51)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 52).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 52)+"']")).click();
										}
									}

								
								}
								
								if(excel.getData(0, i, 53).equals("Not Available"))
								{
									
								}
								else
								{


									if(excel.getNumericData(0, i, 78)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 78)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 78)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 58)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 59).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 59)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 78)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 58)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 59).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 59)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 60)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 61).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 61)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 78)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 58)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 59).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 59)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 60)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 61).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 61)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 62)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 63).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 63)+"']")).click();
										}
									}

								}
								
								if(excel.getData(0, i, 64).equals("Not Available"))
								{
									
								}
								else
								{



									if(excel.getNumericData(0, i, 79)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 79)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 79)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 69)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 70).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 70)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 79)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 69)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 70).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 70)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 71)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 72).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 72)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 79)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 69)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 70).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 70)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 71)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 72).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 72)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 73)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 74).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 74)+"']")).click();
										}
									}

								
								}
							
						}

					
						
						for (int j = 5; j == 17; j=j+3)
						{
							//Check whether the  optional modifier is available or not
							if(excel.getData(0, i, j).equals("Not Available"))
							{

							}
							else
							{
								
								
								//Click the optional modifier group
								driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
								Thread.sleep(1000);
								//Click the modifier
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
								Thread.sleep(1000);
								if(!excel.getData(0, i, j+2).equals("Not Available"))
								{
									//Click the modifier - Prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
								}
								else
								{
									
								}
							}
						
						}
					}
					
					else
					{
						//Click the serving size
						driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, 3)+"']")).click();
						Thread.sleep(1000);
						//Click the serving size option
						driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 4)+"']")).click();
						
						
						//FOR MANDATORY MODIFIRES
						if(!excel.getData(0, i, 20).equals("Not Available"))
							{
						
								if(excel.getData(0, i, 20).equals("Not Available")&&excel.getData(0, i, 31).equals("Not Available")&&excel.getData(0, i, 42).equals("Not Available")&&excel.getData(0, i, 53).equals("Not Available")&&excel.getData(0, i, 64).equals("Not Available"))
								{
									//Click the OPTIONAL MODIFIER
									
								}
								if(excel.getData(0, i, 20).equals("Not Available"))
								{
									
								}
								else
								{
									
									if(excel.getNumericData(0, i, 75)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 75)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 75)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 75)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 24).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 26).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 27)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 28).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 28)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 75)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 22).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 24).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 26).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 27)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 28).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 28)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 29)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 30).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 30)+"']")).click();
										}
									}
										
										

								}
							
								if(excel.getData(0, i, 31).equals("Not Available"))
								{
									
								}
								else
								{

									
									if(excel.getNumericData(0, i, 76)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 76)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 76)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 37).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 76)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 37).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 39).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 76)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 33).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 35).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 37).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 39).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 40)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 41).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 41)+"']")).click();
										}
									}

								}
								
								if(excel.getData(0, i, 42).equals("Not Available"))
								{
									
								}
								else
								{

									if(excel.getNumericData(0, i, 77)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 77)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 77)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 47)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 48).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 48)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 77)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 47)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 48).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 48)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 49)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 50).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 50)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 77)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 44).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 46).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 47)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 48).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 48)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 49)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 50).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 50)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 51)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 52).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 52)+"']")).click();
										}
									}

								
								}
								
								if(excel.getData(0, i, 53).equals("Not Available"))
								{
									
								}
								else
								{


									if(excel.getNumericData(0, i, 78)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 78)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 78)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 58)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 59).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 59)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 78)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 58)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 59).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 59)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 60)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 61).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 61)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 78)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 55).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 57).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 58)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 59).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 59)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 60)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 61).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 61)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 62)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 63).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 63)+"']")).click();
										}
									}

								}
								
								if(excel.getData(0, i, 64).equals("Not Available"))
								{
									
								}
								else
								{



									if(excel.getNumericData(0, i, 79)==1)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 79)==2)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									}
										
									if(excel.getNumericData(0, i, 79)==3)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 69)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 70).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 70)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 79)==4)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 69)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 70).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 70)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 71)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 72).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 72)+"']")).click();
										}
									}
									
									if(excel.getNumericData(0, i, 79)==5)
									{
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 66).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
										}
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 68).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 69)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 70).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 70)+"']")).click();
										}
																
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 71)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 72).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 72)+"']")).click();
										}
									
										
										//Click the modifier 
										driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 73)+"']")).click();
										
								
										Thread.sleep(1000);
										//Check whether the prefix is available or not
										if(excel.getData(0, i, 74).equals("Not Available"))
										{

										}
										else
										{
											//Click the modifier - Prefix
											driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 74)+"']")).click();
										}
									}

								
								}
							
						}

										
						for (int j = 5; j == 17; j=j+3)
						{
							//Check whether the  optional modifier is available or not
							if(excel.getData(0, i, j).equals("Not Available"))
							{

							}
							else
							{
								//Click the optional modifier group
								driver.findElement(By.xpath("//span[.='"+excel.getData(0, i, j)+"']")).click();
								Thread.sleep(1000);
								//Click the modifier
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+1)+"']")).click();
								Thread.sleep(1000);
								if(!excel.getData(0, i, j+2).equals("Not Available"))
								{
									//Click the modifier - Prefix
									driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, j+2)+"']")).click();
								}
								else
								{
									
								}
							}
						
						}
					}
					

				}

			}
		
		}
		
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if(driver.findElement(By.xpath("//div[.='check close succesfully']")).getText().equalsIgnoreCase("check close succesfully"))
		{
			test.log(LogStatus.PASS, "Payment success");
		}
		else
		{
			test.log(LogStatus.FAIL, "Payment fail");
		}
		Thread.sleep(3000);
		
		
		
		wb.close();
		
	
	}

}
