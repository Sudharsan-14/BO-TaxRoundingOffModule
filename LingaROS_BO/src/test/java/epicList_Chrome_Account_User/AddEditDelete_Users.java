package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
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



public class AddEditDelete_Users {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete_Users");

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
			//SendMail.snedMailWithAttachment();
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
			Usermanagement_User_Pageopen(driver);
			Usermanagement_User_refreshPage(driver);
			Usermanagement_User_addUsers(driver);
			Usermanagement_User_edit(driver);
			Usermanagement_User_givePasswordOREnableSignIn(driver);
			Usermanagement_User_givePasswordORDisableSignIn(driver);
			Usermanagement_User_delete(driver);
			Usermanagement_User_DeActivestatus(driver);
			UsersActivestatus(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=12)
    public void Usermanagement_User_Pageopen(WebDriver driver) throws Exception
		{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	  
			//Click the User option
			//driver.findElement(By.xpath("//span[.='Users']")).click();
			Thread.sleep(3000);
			//Enter the Users URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"users");
			Thread.sleep(3000);
			try
			{
			//Check  Users page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 585, 1))).getText().equalsIgnoreCase("Users"))
			{
				test.log(LogStatus.PASS,"Users page loaded Successfully");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL,"Users page loaded Failed");wb.close();
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
			
		}
		
	@Test(enabled=false,priority=13)
    public void Usermanagement_User_refreshPage(WebDriver driver) throws Exception
		{
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	   
			Thread.sleep(3000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(3, 133, 1))).click();
			Thread.sleep(3000);
			//Check  Users page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 585, 1))).getText().equalsIgnoreCase("Users"))
			{
				test.log(LogStatus.PASS,"Users page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL,"Users page loaded Failed");wb.close();
			}
		}

	@Test(enabled=false,priority=14)
    public void Usermanagement_User_addUsers(WebDriver driver) throws Exception
		{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		   
			Thread.sleep(3000);
			//Click the Add User or Create new User button
			driver.findElement(By.xpath(excel.getData(2, 37, 1))).click();
			Thread.sleep(3000);
			//Check  New User form page loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 586, 1))).getText().equalsIgnoreCase("NEW USER"))
			{
				test.log(LogStatus.PASS,"New Users form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL,"New Users form loaded Failed");
			}
			Thread.sleep(3000);
			
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

			//Clear the first name field
			driver.findElement(By.name(excel.getData(3, 587, 3))).clear();
			//Enter the required first name
			driver.findElement(By.name(excel.getData(3, 587, 3))).sendKeys(Utility.getProperty("UMFirstName"));
			//Clear the last name field
			driver.findElement(By.name(excel.getData(3, 588, 3))).clear();
			//Enter the last first name
			driver.findElement(By.name(excel.getData(3, 588, 3))).sendKeys(Utility.getProperty("UMLastName"));
			//Click the required Gender(Male)
			driver.findElement(By.xpath(excel.getData(3, 589, 1))).click();
			//Clear the date of birth field
			driver.findElement(By.name(excel.getData(3, 590, 3))).clear();
			//Enter the date of birth
			driver.findElement(By.name(excel.getData(3, 590, 3))).sendKeys("25-May-1992");
			//Clear the phone number field
			driver.findElement(By.name(excel.getData(3, 591, 3))).clear();
			//Enter the phone number
			driver.findElement(By.name(excel.getData(3, 591, 3))).sendKeys(Utility.getProperty("UMPhoneNumber"));
			//Click the Enable Sign in option
			driver.findElement(By.xpath(excel.getData(3, 592, 1))).click();
			//Clear the email id field
			driver.findElement(By.name(excel.getData(3, 593, 3))).clear();
			//Enter the email id
			driver.findElement(By.name(excel.getData(3, 593, 3))).sendKeys(Utility.getProperty("Usermanagement_user_emailid"));	
			//Click the password
			driver.findElement(By.xpath(excel.getData(3, 594, 1))).click();
			//Enter the password
			driver.findElement(By.xpath(excel.getData(3, 594, 1))).sendKeys("8596abcd");
			//Enter the Confirm password
			driver.findElement(By.xpath(excel.getData(3, 594, 1))).sendKeys(Keys.ENTER);
			
			//Click the password
			driver.findElement(By.xpath(excel.getData(3, 595, 1))).click();
			//Enter the password
			driver.findElement(By.xpath(excel.getData(3, 595, 1))).sendKeys("8596abcd");
			//Enter the Confirm password
			driver.findElement(By.xpath(excel.getData(3, 595, 1))).sendKeys(Keys.ENTER);
			
		
			Thread.sleep(3000);
			//Click the Roles option
			driver.findElement(By.xpath(excel.getData(3, 596, 1))).click();
			//Enter the required role		
			driver.findElement(By.xpath(excel.getData(2, 38, 1))).sendKeys("Precook");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 38, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			//Click the Language option
			driver.findElement(By.xpath(excel.getData(3, 597, 1))).click();
			//Enter the required Language
			driver.findElement(By.xpath(excel.getData(2, 39, 1))).sendKeys("English");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 39, 1))).sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			//Clear the employee pin field
			driver.findElement(By.name(excel.getData(3, 598, 3))).clear();
			//Enter the required pin number2
			driver.findElement(By.name(excel.getData(3, 598, 3))).sendKeys(Utility.getProperty("Usermanagement_user_Employeepin"));
			//Clear the date of join field
			driver.findElement(By.name(excel.getData(3, 599, 3))).clear();
			//Enter the date of join pin number
			driver.findElement(By.name(excel.getData(3, 599, 3))).sendKeys("15-Jan-2011");
			
			//Clear the termination date field
			driver.findElement(By.name(excel.getData(3, 600, 3))).clear();
			//Enter the termination date
			driver.findElement(By.name(excel.getData(3, 600, 3))).sendKeys("09-Aug-2019");
			//Clear the termination reason
			driver.findElement(By.name(excel.getData(3, 601, 3))).clear();
			//Enter the required reason
			driver.findElement(By.name(excel.getData(3, 601, 3))).sendKeys("For Testing purpose");
			//Enter the iPad Initial Screen
			driver.findElement(By.name(excel.getData(3, 602, 3))).sendKeys("Table Screen");
			Thread.sleep(3000);
			//Click the ClockIn Required option
			driver.findElement(By.xpath(excel.getData(3, 603, 1))).click();
			Thread.sleep(3000);
			//Enable the Do Auto CashierOut
			driver.findElement(By.name(excel.getData(3, 604, 3))).click();
			//Enable the Cash Tip
			driver.findElement(By.name(excel.getData(3, 605, 3))).click();
			//Clear the Address1 field
			driver.findElement(By.name(excel.getData(3, 288, 3))).clear();
			//Enter the required Address
			driver.findElement(By.name(excel.getData(3, 288, 3))).sendKeys("Address 1 for the user");
			//Clear the Address2 field
			driver.findElement(By.name(excel.getData(3, 289, 3))).clear();
			//Enter the required Address
			driver.findElement(By.name(excel.getData(3, 289, 3))).sendKeys("Address 2 for the user");
			//Clear the City field
			driver.findElement(By.name(excel.getData(3, 290, 3))).clear();
			//Enter the required city
			driver.findElement(By.name(excel.getData(3, 290, 3))).sendKeys("Chennai");
			//Clear the State field
			driver.findElement(By.name(excel.getData(3, 291, 3))).clear();
			//Enter the required State
			driver.findElement(By.name(excel.getData(3, 291, 3))).sendKeys("TN");
			//Clear the Zip code field
			driver.findElement(By.name(excel.getData(3, 292, 3))).clear();
			//Enter the required Zip Code
			driver.findElement(By.name(excel.getData(3, 292, 3))).sendKeys("123456");
			
			//Click the image option for upload the image
			//driver.findElement(By.xpath("//input[@id='userImage']")).sendKeys("C:\\Users\\hariprasanth_j\\Desktop\\RetailItem.jpg");
			
			Thread.sleep(3000);
			//Click the save and next button
					driver.findElement(By.xpath(excel.getData(3, 606, 1))).click();
					Thread.sleep(3000);
					//Check weather the new User Saved Successfully or not
					if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("User Saved Successfully"))
					{
						test.log(LogStatus.PASS,"New User Saved Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL,"New User Saved Failed");
					}
					
			Thread.sleep(2000);
			//Click the add payments option   
			driver.findElement(By.xpath(excel.getData(3, 614, 1))).click();
			//Select the required Wages by
			Select wagesBy = new Select(driver.findElement(By.xpath(excel.getData(3, 615, 1))));
			wagesBy.selectByVisibleText("Monthly");
			//Clear the effectiveDate field
		   driver.findElement(By.name(excel.getData(3, 616, 3))).clear();
			//Enter effective date
			driver.findElement(By.name(excel.getData(3, 616, 3))).sendKeys(Utility.getProperty("UMDATE1"));
			//Clear the Salary amount
			driver.findElement(By.name(excel.getData(3, 617, 3))).clear();
			//Enter the Salary amount
		    driver.findElement(By.name(excel.getData(3, 617, 3))).sendKeys("1500");
		   //Click cancel button
	       driver.findElement(By.xpath(excel.getData(3, 618, 1))).click();
	       Thread.sleep(2000);
	       //Click the add payments option
	       driver.findElement(By.xpath(excel.getData(3, 614, 1))).click();
	       //Select the required Wages by
	       Select wagesBy1 = new Select(driver.findElement(By.xpath(excel.getData(3, 615, 1))));
	       wagesBy1.selectByVisibleText("Hourly");
	       //Clear the effectiveDate field
		   driver.findElement(By.name(excel.getData(3, 616, 3))).clear();
	       //Enter effective date
	       driver.findElement(By.name(excel.getData(3, 616, 3))).sendKeys(Utility.getProperty("UMDATE2"));
		   //Enter the Salary amount
		   driver.findElement(By.xpath(excel.getData(3, 619, 1))).sendKeys("1500");
		   Thread.sleep(3000);
		   //Click the Save button     
		   driver.findElement(By.cssSelector(excel.getData(3, 620, 4))).click();
		   Thread.sleep(3000);
		   //Check weather the payment added Successfully or not
			if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Payment added successfully"))
			{
				test.log(LogStatus.PASS,"Payment added successfully");
			}
			else
			{
				test.log(LogStatus.FAIL,"Payment added Failed");
			}
			Thread.sleep(3000);
			//Click the Close button
		driver.findElement(By.cssSelector(excel.getData(3, 610, 4))).click();wb.close();
		}
		
	@Test(enabled=false,priority=16)
    public void Usermanagement_User_edit(WebDriver driver) throws Exception
		{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
        FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	   
			Thread.sleep(3000);
			//Clear the search box
			driver.findElement(By.cssSelector(excel.getData(3, 574, 4))).clear();
			//Enter the required keyword in search box
			driver.findElement(By.cssSelector(excel.getData(3, 574, 4))).sendKeys(Utility.getProperty("UMFirstName"));
			//Click the edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(3,41, 4))).click();Thread.sleep(2000);
			//Clear the first name field
			driver.findElement(By.name(excel.getData(3, 587, 3))).clear();
			//Enter the required first name
			driver.findElement(By.name(excel.getData(3, 587, 3))).sendKeys(Utility.getProperty("UMFirstName")+"1");
			Thread.sleep(3000);
			//Click the update and next button
			driver.findElement(By.xpath(excel.getData(3, 609, 1))).click();
			Thread.sleep(3000);
			//Check weather the new role was updated or not
			if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("User updated successfully"))
			{							
				test.log(LogStatus.PASS,"User updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL,"User updated Failed");
			}
			Thread.sleep(3000);
			//Click the Close button
			driver.findElement(By.cssSelector(excel.getData(3, 610, 4))).click();wb.close();
		}
	
	@Test(enabled=false,priority=18)
    public void Usermanagement_User_givePasswordOREnableSignIn(WebDriver driver) throws Exception
		{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
        FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	   
			
			Thread.sleep(2000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("UMFirstName")+"1");
			
			//Click the edit button
			driver.findElement(By.cssSelector(excel.getData(3, 611, 4))).click();
			Thread.sleep(3000);
			//Click yes button
			driver.findElement(By.cssSelector(excel.getData(3, 209, 4))).click();
			
			Thread.sleep(2000);
			//Check weather the Sign in disabled successfully or not
			if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Sign in disabled successfully"))
			{
				test.log(LogStatus.PASS,"Sign in disabled successfully");
			}
			else
			{
				test.log(LogStatus.FAIL,"Sign in disabled successfully is  Failed");wb.close();
			}

			
		}
		
	@Test(enabled=false,priority=19)
    public void Usermanagement_User_givePasswordORDisableSignIn(WebDriver driver) throws Exception
		{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
        FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	   
			Thread.sleep(2000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("UMFirstName")+"1");
			
			//Click the edit button
			driver.findElement(By.cssSelector(excel.getData(3, 611, 4))).click();
			Thread.sleep(3000);
			//Clear the Password field
			driver.findElement(By.xpath(excel.getData(3, 612, 1))).clear();
			//Enter the required Password
			Thread.sleep(3000);
			driver.findElement(By.xpath(excel.getData(3, 612, 1))).sendKeys("test1235");
			Thread.sleep(3000);
			//Clear the Confirm Password field
			driver.findElement(By.xpath(excel.getData(3, 613, 1))).clear();
			Thread.sleep(3000);
			//Enter the required Confirm Password
			Thread.sleep(3000);
			driver.findElement(By.xpath(excel.getData(3, 613, 1))).sendKeys("test1235");
			Thread.sleep(3000);
			//Click the Submit button
			driver.findElement(By.xpath(excel.getData(2, 41, 1))).click();
			
			Thread.sleep(2000);
			//Check weather the Sign in Enabled successfully or not
			if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("Sign in enabled successfully"))
			{
				test.log(LogStatus.PASS,"Sign in enabled successfully");
			}
			else
			{
				test.log(LogStatus.FAIL,"Sign in enabled successfully is  Failed");
			}
			
			Thread.sleep(2000);wb.close();
		}
		
	@Test(enabled=false,priority=21)
    public void Usermanagement_User_delete(WebDriver driver) throws Exception
		{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
        FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	   
			driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
			//Clear the search box
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required keyword in search box
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("UMFirstName")+"1");
			Thread.sleep(3000);
			//Click on the delete button	
			driver.findElement(By.xpath(excel.getData(3, 607, 1))).click();
			
			Thread.sleep(3000);
			//Click the yes button
			driver.findElement(By.cssSelector(excel.getData(3, 209, 4))).click();
				
			Thread.sleep(3000);
			//Check weather the role deleted or not
			if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("User inactivated successfully"))
			{
				test.log(LogStatus.PASS,"User deleted Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL,"User deleted Failed");wb.close();
			}
			}

	@Test(enabled=false,priority=22)
    public void Usermanagement_User_DeActivestatus (WebDriver driver) throws Exception
			{
	    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
        FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	   
				
		
			Thread.sleep(2000);
			//Check Deactivate User
			driver.findElement(By.xpath(excel.getData(3, 608, 1))).click();
			Thread.sleep(3000);   		
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("UMFirstName")+"1");
			Thread.sleep(2000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(3, 157, 4))).click();
			Thread.sleep(2000);
			//Click the Yes button in the popup
			Thread.sleep(5000);driver.findElement(By.cssSelector(excel.getData(3, 209, 4))).click();  
			Thread.sleep(3000);
			//Check if we Deactivate or not
			if(driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText().equalsIgnoreCase("User activated successfully"))
			{
				test.log(LogStatus.PASS,"User activated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL,"User activated successfully is Failed");
			}
			Thread.sleep(3000);wb.close();
				}
							
	@Test(enabled=false,priority=20)
	public void UsersActivestatus (WebDriver driver) throws Exception	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		   
					
				Thread.sleep(2000);
				//Clear the search field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				
				//Enter the required keyword
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("UMFirstName")+"1");
			   Thread.sleep(3000);
			   //Clear the search field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				
				
				if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);
				//watchTutorial(driver);
				Thread.sleep(5000);wb.close();}
				}
	
	@Test(priority=12, enabled=false)
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

}  
