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


public class Inventory_Vendors {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Vendors");

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
		//	SendMail.snedMailWithAttachment();
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
			Inventory_Vendors_openpage(driver);
			Inventory_Vendors_refreshpage(driver);
			Inventory_Vendors_add_Vendor(driver);
			Inventory_Vendors_edit_Invetory_Ingredient_Vendor(driver);
			Inventory_Vendors_delete(driver);
			Inventory_Vendors_closeButton(driver);
			Inventory_Vendors_verifyActive_InActiveButton(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=21)
		public void Inventory_Vendors_openpage(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"vendors");

			Thread.sleep(4000);
			try
			{
			//Check Vendors page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1186, 1))).getText().equalsIgnoreCase("Vendors"))
			{
				test.log(LogStatus.PASS, "Vendors page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Vendors page loaded Failed");
			
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
			Thread.sleep(4000);wb.close(); 
			
		}
		
		@Test(enabled=false,priority=22)
		public void Inventory_Vendors_refreshpage(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(4000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(3, 1181, 1))).click();
			Thread.sleep(4000);
			//Check Vendors page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1714, 1))).getText().equalsIgnoreCase("Vendor"))
			{
				test.log(LogStatus.PASS, "Vendors page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Vendors page refreshed Failed");
			}
			Thread.sleep(4000);wb.close();

		}
		
		@Test(enabled=false,priority=23)
		public void Inventory_Vendors_add_Vendor(WebDriver driver) throws Exception
		{  
			    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//Scroll the web page
		    for(int i=1; i <= 15; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		    	Thread.sleep(1000);
		    }
		    
		    Thread.sleep(3000);
			//Check the search button is displayed
			driver.findElement(By.cssSelector(excel.getData(3, 1187, 1))).isDisplayed();
			Thread.sleep(4000);
			//Click on the Add Vendor option
			driver.findElement(By.xpath(excel.getData(3, 1188, 1))).click();
			Thread.sleep(3000);
			
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1189, 1))).getText().equalsIgnoreCase("New Vendor"))
			{
				test.log(LogStatus.PASS, "New Vendor form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Vendor form loaded Failed");
			}

			Thread.sleep(3000);
			
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Inventory_Vendor"));
			Thread.sleep(2000);
			
			//Clear the description field
			driver.findElement(By.name(excel.getData(3, 1174, 3))).clear();
			//Enter the description
			driver.findElement(By.name(excel.getData(3, 1174, 3))).sendKeys("Inventory Vendor");
			Thread.sleep(2000);
			
			//Clear the phoneNumber field
			driver.findElement(By.name(excel.getData(3, 591, 3))).clear();
			//Enter the phoneNumber
			driver.findElement(By.name(excel.getData(3, 591, 3))).sendKeys("1234567899");
			Thread.sleep(2000);
			
			//Clear the emailId field
			driver.findElement(By.name(excel.getData(3, 593, 3))).clear();
			//Enter the emailId
			driver.findElement(By.name(excel.getData(3, 593, 3))).sendKeys("testset@mail.com");
			Thread.sleep(2000);
			
			//Clear the contactName field
			driver.findElement(By.name(excel.getData(3, 1190, 3))).clear();
			//Enter the contactName
			driver.findElement(By.name(excel.getData(3, 1190, 3))).sendKeys("Testset");
			Thread.sleep(2000);
			
			//Clear the contactNumber field
			driver.findElement(By.name(excel.getData(3, 1191, 3))).clear();
			//Enter the contactNumber
			driver.findElement(By.name(excel.getData(3, 1191, 3))).sendKeys("1234567899");
			Thread.sleep(2000);
			
			//Clear the addressLine1 field
			driver.findElement(By.name(excel.getData(3, 288, 3))).clear();
			//Enter the addressLine1
			driver.findElement(By.name(excel.getData(3, 288, 3))).sendKeys("38/21_Navalar_Street");
			Thread.sleep(2000);
			
			//Clear the addressLine2 field
			driver.findElement(By.name(excel.getData(3, 289, 3))).clear();
			//Enter the addressLine2
			driver.findElement(By.name(excel.getData(3, 289, 3))).sendKeys("Arumbakkam");
			Thread.sleep(2000);
			
			//Clear the city field
			driver.findElement(By.name(excel.getData(3, 290, 3))).clear();
			//Enter the city
			driver.findElement(By.name(excel.getData(3, 290, 3))).sendKeys("Chennai");
			Thread.sleep(2000);
			
			//Clear the state field
			driver.findElement(By.name(excel.getData(3, 291, 3))).clear();
			//Enter the state
			driver.findElement(By.name(excel.getData(3, 291, 3))).sendKeys("Tamil Nadu");
			Thread.sleep(2000);
			
			//Clear the zipCode field
			driver.findElement(By.name(excel.getData(3, 292, 3))).clear();
			//Enter the zipCode
			driver.findElement(By.name(excel.getData(3, 292, 3))).sendKeys("123456");
			Thread.sleep(2000);
				
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			//Thread.sleep(2000);
			
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,30);
			
			//Check whether the new Vendor saved or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Vendor saved successfully"))
			{
				test.log(LogStatus.PASS, "New Vendor Saved Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Vendor Save Failed");
			}

			Thread.sleep(4000);wb.close();
		}
		
		@Test(enabled=false,priority=25)
		public void Inventory_Vendors_edit_Invetory_Ingredient_Vendor(WebDriver driver) throws Exception
		{
			    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			Thread.sleep(4000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Inventory_Vendor"));
		
			Thread.sleep(5000);
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
			Thread.sleep(4000);
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Inventory_Vendor")+"1");
			Thread.sleep(2000);

			
			//Clear the description field
			driver.findElement(By.name(excel.getData(3, 1174, 3))).clear();
			//Enter the description
			driver.findElement(By.name(excel.getData(3, 1174, 3))).sendKeys("Inventory Vendor1");
			Thread.sleep(2000);
			
			//Clear the phoneNumber field
			driver.findElement(By.name(excel.getData(3, 591, 3))).clear();
			//Enter the phoneNumber
			driver.findElement(By.name(excel.getData(3, 591, 3))).sendKeys("1234567898");
			Thread.sleep(2000);
			
			//Clear the emailId field
			driver.findElement(By.name(excel.getData(3, 593, 3))).clear();
			//Enter the emailId
			driver.findElement(By.name(excel.getData(3, 593, 3))).sendKeys("testset1@mail.com");
			Thread.sleep(2000);
			
			//Clear the contactName field
			driver.findElement(By.name(excel.getData(3, 1190, 3))).clear();
			//Enter the contactName
			driver.findElement(By.name(excel.getData(3, 1190, 3))).sendKeys("Testset1");
			Thread.sleep(2000);
			
			//Clear the contactNumber field
			driver.findElement(By.name(excel.getData(3, 1191, 3))).clear();
			//Enter the contactNumber
			driver.findElement(By.name(excel.getData(3, 1191, 3))).sendKeys("1234567898");
			Thread.sleep(2000);
			
			//Clear the addressLine1 field
			driver.findElement(By.name(excel.getData(3, 288, 3))).clear();
			//Enter the addressLine1
			driver.findElement(By.name(excel.getData(3, 288, 3))).sendKeys("38/22_Navalar_Street");
			Thread.sleep(2000);
			
			//Clear the addressLine2 field
			driver.findElement(By.name(excel.getData(3, 289, 3))).clear();
			//Enter the addressLine2
			driver.findElement(By.name(excel.getData(3, 289, 3))).sendKeys("MMDA");
			Thread.sleep(2000);
			
			//Clear the city field
			driver.findElement(By.name(excel.getData(3, 290, 3))).clear();
			//Enter the city
			driver.findElement(By.name(excel.getData(3, 290, 3))).sendKeys("Chennai");
			Thread.sleep(2000);
			
			//Clear the state field
			driver.findElement(By.name(excel.getData(3, 291, 3))).clear();
			//Enter the state
			driver.findElement(By.name(excel.getData(3, 291, 3))).sendKeys("Tamil Nadu");
			Thread.sleep(2000);
			
			//Clear the zipCode field
			driver.findElement(By.name(excel.getData(3, 292, 3))).clear();
			//Enter the zipCode
			driver.findElement(By.name(excel.getData(3, 292, 3))).sendKeys("123455");
			Thread.sleep(2000);
				

			//Click the update
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		//	Thread.sleep(2000);
			
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			
			WebDriverWait wait=new WebDriverWait(driver,50);

			//Check whether the Vendor updated successfully or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Vendor updated successfully"))
			{
				test.log(LogStatus.PASS, "New Vendor updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Vendor updated Failed");
			}

			Thread.sleep(4000);wb.close();

		}
		
		@Test(enabled=false,priority=26)
		public void Inventory_Vendors_delete(WebDriver driver) throws Exception
		{
			
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			Thread.sleep(4000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Inventory_Vendor")+"1");
			Thread.sleep(4000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(3, 156, 4))).click();
			
			Thread.sleep(3000);
			//Click the Yes button in the popup
			driver.findElement(By.linkText("Yes")).click();
			
			//Thread.sleep(5000);
			WebElement wa=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,90);
			
			//Check the Vendor deleted or not
			if(wait.until(ExpectedConditions.visibilityOf(wa)).getText().equalsIgnoreCase("Vendor InActivated successfully"))
			{
				test.log(LogStatus.PASS, "New Vendor deleted Sucessfully");
			}
			else
			{
				test.log(LogStatus.INFO, "New Vendor deleted Failed");
			}

			Thread.sleep(4000);
		

			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(3, 144, 1))).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector(excel.getData(3, 209, 4))).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			driver.findElement(By.linkText("Yes")).click();
			Thread.sleep(3000);
			
			//Check the Vendor activated or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Vendor Activated successfully"))
			{
				test.log(LogStatus.PASS, "Vendor is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Vendor is activated Failed");
			}
			
			Thread.sleep(4000);wb.close();
		}

		@Test(enabled=false,priority=27)
		public void Inventory_Vendors_closeButton(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(4000);
			//Click on the Add Vendor option
			driver.findElement(By.xpath(excel.getData(3, 1188, 1))).click();
			Thread.sleep(3000);
			
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1189, 1))).getText().equalsIgnoreCase("New Vendor"))
			{
				test.log(LogStatus.PASS, "New Vendor form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Vendor form loaded Failed");
			}

			Thread.sleep(3000);
			
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Inventory_Vendor"));
			Thread.sleep(2000);
				
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(3, 130, 1))).click();
			
			Thread.sleep(3000);
			//Check whether the new Vendor canceled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1714, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "New Vendor Canceled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Vendor Canceled Failed");
			}

			Thread.sleep(4000);wb.close();
		}
				
		@Test(enabled=false,priority=28)
		public void Inventory_Vendors_verifyActive_InActiveButton(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			
			Thread.sleep(4000);
			//Click Active or In Active Button
			driver.findElement(By.xpath(excel.getData(3, 144, 1))).click();

			Thread.sleep(4000);
			//Check the Inactive page is loaded or not
			if(driver.findElement(By.cssSelector(excel.getData(3, 209, 4))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Deleted categories are here, we are in InActive Page");
				Thread.sleep(10000);
				//Click Active or In Active Button
				driver.findElement(By.xpath(excel.getData(3, 144, 1))).click();

			}
			else if(driver.findElement(By.cssSelector(excel.getData(3, 244, 4))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Deleted Vendor are not here, we are in Active Page");
			}
			Thread.sleep(4000); 
			
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);//watchTutorial(driver);
			Thread.sleep(5000);wb.close();}
		}
		
		@Test(priority=3, enabled=false)
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
