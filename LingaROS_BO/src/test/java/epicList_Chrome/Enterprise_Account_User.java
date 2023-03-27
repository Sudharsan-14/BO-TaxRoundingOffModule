package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import newReadExcelFile_New.ExcelDataConfig;

public class Enterprise_Account_User {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Enterprise_Account_User");

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
		{		Browser a = new Browser();
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
			Account_User_openpage(driver);
			Account_User_pagination(driver);
			Account_User_add(driver);
			Account_User_edit(driver);
			Account_User_reset_Password(driver);
			NewAccountUser_Cancel_Button(driver);
			EditAccountUser_Cancel_Button(driver);
			Reset_Password_Cancel_Button(driver);
			Account_Users_delete(driver);
			Thread.sleep(1500);
		}
		
		@Test(enabled=false,priority=2)
		public void Account_User_openpage(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(15000);
/*			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			//Click the My Stores option
			driver.findElement(By.xpath("//span[.='My Stores']")).click();
	
			Thread.sleep(3000);
	        //Click the EnterPrise Reports Option		
			driver.findElement(By.xpath("//span[.='EnterPrise Reports']")).click();
			
			Thread.sleep(2000);
			//Click the Sale option
			driver.findElement(By.xpath("//span[.='Account Users']")).click();*/
			
			//Get the URL
			driver.get(Utility.getProperty("Enterprise_Base_URL")+"users");
			Thread.sleep(5000);
			try
			{
			//Check Department Report page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1441, 1))).getText().equalsIgnoreCase("Account Users"))
			{
				test.log(LogStatus.PASS, "Account Users page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			
			}
			else
			{
				test.log(LogStatus.FAIL, "Account Users page loaded Failed");
				
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
			wb.close();
			Thread.sleep(3000);
			
		}
		
		@Test(enabled=false,priority=3)
		public void Account_User_pagination(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis); 
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			Thread.sleep(1000);
			
			//Click the Pagination option as 10
			driver.findElement(By.xpath(excel.getData(3, 1788, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 10");
			//Create the web element for Account Users Table
			List<WebElement> results = driver.findElements(By.xpath(excel.getData(3, 1789, 1)));
			for (WebElement result : results){						
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(8000);
			
			//Click the Pagination option as 15
			driver.findElement(By.xpath(excel.getData(3, 1790, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 15");
			//Create the web element for Account Users Table
			List<WebElement> results1 = driver.findElements(By.xpath(excel.getData(3, 1789, 1)));
			for (WebElement result : results1){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(8000);
			
			//Click the Pagination option as 20
			driver.findElement(By.xpath(excel.getData(3, 1791, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 20");
			//Create the web element for Account Users Table
			List<WebElement> results2 = driver.findElements(By.xpath(excel.getData(3, 1789, 1)));
			for (WebElement result : results2){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(8000);
			
			//Click the Pagination option as 5
			driver.findElement(By.xpath(excel.getData(3, 1792, 1))).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 5");
			//Create the web element for Account Users Table
			List<WebElement> results3 = driver.findElements(By.xpath(excel.getData(3, 1789, 1)));
			for (WebElement result : results3){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(3, 42, 4)));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			wb.close();
			Thread.sleep(8000);
		}
		
		@Test(enabled=false,priority=4)
		public void Account_User_add(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(1000);
			//Click the Add Account Users Button
			driver.findElement(By.id(excel.getData(3, 1793, 2))).click();
			
			Thread.sleep(1000);
			//Check whether the New Account User form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1442, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "New Account User form loaded successfully");
			}
			else
			{ 
				test.log(LogStatus.FAIL, "New Account User form loaded fail");
			}
			Thread.sleep(2000);
			
			//Clear the First name field
			driver.findElement(By.name(excel.getData(3, 587, 3))).clear();
			//Enter the First Name
			driver.findElement(By.name(excel.getData(3, 587, 3))).sendKeys(Utility.getProperty("Enterprise_Account_User_First_Name"));
			
			Thread.sleep(1000);
			//Clear the Last field
			driver.findElement(By.name(excel.getData(3, 588, 3))).clear();
			//Enter the Last Name
			driver.findElement(By.name(excel.getData(3, 588, 3))).sendKeys("LName");
			
			Thread.sleep(1000);
			//Enable or Select the Gender as Male 
			if(driver.findElement(By.xpath(excel.getData(3, 1420, 1))).isSelected())
			{
				
			}
			else
			{
				Thread.sleep(1000);
				//Click or select the male radio button
				driver.findElement(By.xpath(excel.getData(3, 1420, 1))).click();
			}
			
			Thread.sleep(1000);
			//Clear the Date of birth field
			driver.findElement(By.name(excel.getData(3, 590, 3))).clear();
			//Enter the Date of birth
			driver.findElement(By.name(excel.getData(3, 590, 3))).sendKeys("04-Nov-1993");
			
			Thread.sleep(1000);
			//Clear the phone number field
			driver.findElement(By.name(excel.getData(3, 591, 3))).clear();
			//Enter the phone number
			driver.findElement(By.name(excel.getData(3, 591, 3))).sendKeys("5484958624");
			
			Thread.sleep(1000);
			//Clear the email id field
			driver.findElement(By.name(excel.getData(3, 593, 3))).clear();
			//Enter the email id field
			driver.findElement(By.name(excel.getData(3, 593, 3))).sendKeys(Utility.getProperty("Enterprise_Account_User_Email_Id"));
			
			Thread.sleep(1000);
			//Clear the password field
			driver.findElement(By.name(excel.getData(3, 1443, 3))).clear();
			//Enter the Password
			driver.findElement(By.name(excel.getData(3, 1443, 3))).sendKeys(Utility.getProperty("Enterprise_Account_User_Password"));
			
			Thread.sleep(1000);
			//Clear the Confirm Password field
			driver.findElement(By.name(excel.getData(3, 1444, 3))).clear();
			//Enter the Confirm password
			driver.findElement(By.name(excel.getData(3, 1444, 3))).sendKeys(Utility.getProperty("Enterprise_Account_User_Password"));
			
			Thread.sleep(1000);
			//Click the Language field
			driver.findElement(By.xpath(excel.getData(3, 1445, 1))).click();
			//Enter the required Language
			driver.findElement(By.xpath(excel.getData(3, 1446, 1))).sendKeys("English");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1446, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Stores option
			driver.findElement(By.xpath(excel.getData(3, 1447, 1))).click();
			//Enter the required store
			driver.findElement(By.xpath(excel.getData(3, 1448, 1))).sendKeys(Utility.getReportProperty("Store"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1448, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1000);
			//Clear the Date of Joining field
			driver.findElement(By.name(excel.getData(3, 1449, 3))).clear();
			//Enter the date of joining
			driver.findElement(By.name(excel.getData(3, 1449, 3))).sendKeys("05-Apr-2018");
			
			Thread.sleep(1000);
			//Clear the Address1 field
			driver.findElement(By.name(excel.getData(3, 288, 3))).clear();
			//Enter the Address1
			driver.findElement(By.name(excel.getData(3, 288, 3))).sendKeys("38/21, Navalar Street");
			
			Thread.sleep(1000);
			//Clear the Address2 field
			driver.findElement(By.name(excel.getData(3, 289, 3))).clear();
			//Enter the Address2 field
			driver.findElement(By.name(excel.getData(3, 289, 3))).sendKeys("Arumbakkam");
			
			Thread.sleep(1000);
			//Clear the city field
			driver.findElement(By.name(excel.getData(3, 290, 3))).clear();
			//Enter the city field
			driver.findElement(By.name(excel.getData(3, 290, 3))).sendKeys("Chennai");
			
			Thread.sleep(1000);
			//Clear the State field
			driver.findElement(By.name(excel.getData(3, 291, 3))).clear();
			//Enter the state
			driver.findElement(By.name(excel.getData(3, 291, 3))).sendKeys("Tamil Nadu");
			
			Thread.sleep(1000);
			//Clear the Zip Code
			driver.findElement(By.name(excel.getData(3, 292, 3))).clear();
			//Enter the Zip Code
			driver.findElement(By.name(excel.getData(3, 292, 3))).sendKeys("600106");
			
			Thread.sleep(1000);
			//Select the Employee image
			driver.findElement(By.id(excel.getData(3, 1794, 2))).sendKeys(Utility.getProperty("Enterprise_Account_Users_Employee_Img"));
			
			
			Thread.sleep(2000);
			//Click the Submit button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			Thread.sleep(2000);
			//Check whether the account user is saved or not	
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 576, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("User Saved Successfully"))
			{
				test.log(LogStatus.PASS, "New Account user is saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Account user is saved fail");
			}
			wb.close();
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=5)
		public void Account_User_edit (WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(1000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required key word
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Enterprise_Account_User_First_Name"));
			
			Thread.sleep(1000);
			//Click the pencil icon
			driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
			
			Thread.sleep(1000);
			//Clear the First name field
			driver.findElement(By.name(excel.getData(3, 587, 3))).clear();
			//Enter the First Name
			driver.findElement(By.name(excel.getData(3, 587, 3))).sendKeys(Utility.getProperty("Enterprise_Account_User_First_Name")+"10");
			
			Thread.sleep(1000);
			//Clear the Last field
			driver.findElement(By.name(excel.getData(3, 588, 3))).clear();
			//Enter the Last Name
			driver.findElement(By.name(excel.getData(3, 588, 3))).sendKeys("LName");
			
			Thread.sleep(1000);
			//Enable or Select the Gender as Male
			if(driver.findElement(By.xpath(excel.getData(3, 1420, 1))).isSelected())
			{
				
			}
			else
			{
				Thread.sleep(1000);
				//Click or select the male radio button
				driver.findElement(By.xpath(excel.getData(3, 1420, 1))).click();
			}
			
			Thread.sleep(1000);
			//Clear the Date of birth field
			driver.findElement(By.name(excel.getData(3, 590, 3))).clear();
			//Enter the Date of birth
			driver.findElement(By.name(excel.getData(3, 590, 3))).sendKeys("04-Nov-1993");
			
			Thread.sleep(1000);
			//Clear the phone number field
			driver.findElement(By.name(excel.getData(3, 591, 3))).clear();
			//Enter the phone number
			driver.findElement(By.name(excel.getData(3, 591, 3))).sendKeys("5484958625");
			
			Thread.sleep(1000);
			//Clear the email id field
			driver.findElement(By.name(excel.getData(3, 593, 3))).clear();
			//Enter the email id field
			driver.findElement(By.name(excel.getData(3, 593, 3))).sendKeys(Utility.getProperty("Enterprise_Account_User_Email_Id")+".in");
			
			Thread.sleep(2000);
			//Click the update button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			Thread.sleep(2500);
			//Check whether the account user is updated or not
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 576, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("User updated successfully"))
			{
				test.log(LogStatus.PASS, "New Account user is updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Account user is updated fail");
			}
			wb.close();
			Thread.sleep(3000);
			
			
		}
		
		@Test(enabled=false,priority=6)
		public void Account_User_reset_Password(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(1000);
			Thread.sleep(1000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required key word
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Enterprise_Account_User_First_Name"));
			
			Thread.sleep(1000);
			//Click the pencil icon
			driver.findElement(By.cssSelector(excel.getData(3, 1450, 4))).click();
			
			Thread.sleep(1000);
			//clear the new password field
			driver.findElement(By.name(excel.getData(3, 1451, 3))).clear();
			//Enter the new password
			driver.findElement(By.name(excel.getData(3, 1451, 3))).sendKeys(Utility.getProperty("Enterprise_Account_User_Password")+"a");
			
			Thread.sleep(1000);
			//clear the confirm password field
			driver.findElement(By.name(excel.getData(3, 1452, 3))).clear();
			//Enter the confirm password
			driver.findElement(By.name(excel.getData(3, 1452, 3))).sendKeys(Utility.getProperty("Enterprise_Account_User_Password")+"a");
	
			Thread.sleep(1000);
			//Click the Reset button
			driver.findElement(By.xpath(excel.getData(3, 1453, 1))).click();
	
			Thread.sleep(2000);
			
			//Check whether the password is reseted or not
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 576, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Your Password has been reset successfully"))
			{
				test.log(LogStatus.PASS, "New Account user password is updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Account user password is updated fail");
			}
			wb.close();
			Thread.sleep(2000);
			
		}
		
		@Test(enabled=false,priority=7)
		public void NewAccountUser_Cancel_Button(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(1000);
			//Click the Add Account Users Button
			driver.findElement(By.id(excel.getData(3, 1793, 2))).click();
			
			//Clear the First name field
			driver.findElement(By.name(excel.getData(3, 587, 3))).clear();
			//Enter the First Name
			driver.findElement(By.name(excel.getData(3, 587, 3))).sendKeys(Utility.getProperty("Enterprise_Account_User_First_Name"));
			
			Thread.sleep(1000);
			//Clear the Last field
			driver.findElement(By.name(excel.getData(3, 588, 3))).clear();
			//Enter the Last Name
			driver.findElement(By.name(excel.getData(3, 588, 3))).sendKeys("LName");
			
			Thread.sleep(1000);
			//Enable or Select the Gender as Male
			if(driver.findElement(By.xpath(excel.getData(3, 1420, 1))).isSelected())
			{
				
			}
			else
			{
				Thread.sleep(1000);
				//Click or select the male radio button
				driver.findElement(By.xpath(excel.getData(3, 1420, 1))).click();
			}
			
			Thread.sleep(2000);
			//Click the Close button
			driver.findElement(By.xpath(excel.getData(3, 130, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the cancel button is working or not
			if(driver.findElement(By.id(excel.getData(3, 1793, 2))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Cancel button is working fine in New Account User Create Page");
			}
			else
			{
				test.log(LogStatus.FAIL, "Cancel button is not working in New Account User Create Page");
			}
			wb.close();
		}
		
		@Test(enabled=false,priority=8)
		public void EditAccountUser_Cancel_Button(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(1000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required key word
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Enterprise_Account_User_First_Name")+"10");
			
			Thread.sleep(1000);
			//Click the pencil icon
			driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
			
			Thread.sleep(1000);
			//Clear the First name field
			driver.findElement(By.name(excel.getData(3, 587, 3))).clear();
			//Enter the First Name
			driver.findElement(By.name(excel.getData(3, 587, 3))).sendKeys(Utility.getProperty("Enterprise_Account_User_First_Name")+"11");
			
			Thread.sleep(1000);
			//Clear the Last field
			driver.findElement(By.name(excel.getData(3, 588, 3))).clear();
			//Enter the Last Name
			driver.findElement(By.name(excel.getData(3, 588, 3))).sendKeys("LName");
			
			Thread.sleep(1000);
			//Enable or Select the Gender as Male
			if(driver.findElement(By.xpath(excel.getData(3, 1420, 1))).isSelected())
			{
				
			}
			else
			{
				Thread.sleep(1000);
				//Click or select the male radio button
				driver.findElement(By.xpath(excel.getData(3, 1420, 1))).click();
			}
			
			Thread.sleep(2000);
			//Click the Close button
			driver.findElement(By.xpath(excel.getData(3, 130, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the cancel button is working or not
			if(driver.findElement(By.id(excel.getData(3, 1793, 2))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Cancel button is working fine in Edit Account User Create Page");
			}
			else
			{
				test.log(LogStatus.FAIL, "Cancel button is not working in Edit Account User Create Page");
			}
			wb.close();
		}
		
		@Test(enabled=false,priority=9)
		public void Reset_Password_Cancel_Button(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(1000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required key word
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Enterprise_Account_User_First_Name"));
			
			Thread.sleep(1000);
			//Click the pencil icon
			driver.findElement(By.cssSelector(excel.getData(3, 1450, 4))).click();
			
			Thread.sleep(1000);
			//clear the new password field
			driver.findElement(By.name(excel.getData(3, 1451, 3))).clear();
			//Enter the new password
			driver.findElement(By.name(excel.getData(3, 1451, 3))).sendKeys(Utility.getProperty("Enterprise_Account_User_Password")+"a");
			
			Thread.sleep(1000);
			//clear the confirm password field
			driver.findElement(By.name(excel.getData(3, 1452, 3))).clear();
			//Enter the confirm password
			driver.findElement(By.name(excel.getData(3, 1452, 3))).sendKeys(Utility.getProperty("Enterprise_Account_User_Password")+"a");
	
			Thread.sleep(2000);
			//Click the Close button
			driver.findElement(By.xpath(excel.getData(3, 45, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the cancel button is working or not
			if(driver.findElement(By.id(excel.getData(3, 1793, 2))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Cancel button is working fine in Reset Password Page");
			}
			else
			{
				test.log(LogStatus.FAIL, "Cancel button is not working in Reset Password Page");
			}
			wb.close();
			Thread.sleep(2000);
			
		}
		
		@Test(enabled=false,priority=10)
		public void Account_Users_delete(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(1000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required key word
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Enterprise_Account_User_First_Name")+"10");
			
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).click();
			
			//Click the Yes button in the popup
			driver.findElement(By.linkText(excel.getData(3, 151, 3))).click();
			
			Thread.sleep(3000);
			//Check the menu item deleted or not
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 576, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("User inactivated successfully"))
			{
				test.log(LogStatus.PASS, "Account User deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Account User deleted Failed");
			}
	
			Thread.sleep(5000);
		
	
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(3, 1795, 1))).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector(excel.getData(3, 175, 4))).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			driver.findElement(By.linkText(excel.getData(3, 151, 3))).click();
			Thread.sleep(3000);
			
			//Check the menu item activated or not
			WebElement ele1=driver.findElement(By.xpath(excel.getData(3, 576, 1)));
			WebDriverWait wait1=new WebDriverWait(driver,60);
			
			if(wait1.until(ExpectedConditions.visibilityOf(ele1)).getText().equalsIgnoreCase("User activated successfully"))
			{
				test.log(LogStatus.PASS, "Account User activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Account User activated Failed");
			}
			wb.close(); 
			Thread.sleep(5000);
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);}
		}
		@Test(priority=3, enabled=false)
		public void watchTutorial(WebDriver driver) throws Exception
		{
			Thread.sleep(2000);
			//Click the Watch Tutorial Option
			driver.findElement(By.xpath("//span[.='Watch Tutorial']")).click();
			WebElement iframe = driver.findElement(By.xpath("//div[@class='modal-content']/span/iframe"));
			driver.switchTo().frame(iframe);
			Thread.sleep(3500);
			try
			{
				if(driver.findElement(By.xpath("//button/div[.='Watch later']")).isDisplayed()&&driver.findElement(By.xpath("//button/div[.='Share']")).isDisplayed())
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
			driver.findElement(By.xpath("//span[.='x' and @title='close']")).click();
		}
}
