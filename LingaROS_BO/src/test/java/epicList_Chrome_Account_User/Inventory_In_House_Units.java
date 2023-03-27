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


public class Inventory_In_House_Units {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_In_House_Units");

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
	//		SendMail.snedMailWithAttachment();
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
			Inventory_In_HouseUnits_openpage(driver);
			Inventory_In_HouseUnits_refresh(driver);
			Inventory_In_HouseUnits_add_InHouseUnits(driver);
			Inventory_In_HouseUnits_edit_Ingredient_In_House_Units(driver);
			Inventory_In_HouseUnits_delete(driver);
			Inventory_In_HouseUnits_closeButton(driver);
			Inventory_In_HouseUnits_verifyActive_InActiveButton(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=29)
		public void Inventory_In_HouseUnits_openpage(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"masters/inHouseUnit");

			Thread.sleep(4000);
			try
			{
			//Check In House Units page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1193, 1))).getText().equalsIgnoreCase("InHouse units"))
			{
				test.log(LogStatus.PASS, "In House Units page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "In House Units page loaded Failed");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			}
			catch(Exception e) {
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.FAIL,test.addScreenCapture(s));
			}
			
			Thread.sleep(4000);wb.close();
			
		}
		
		@Test(enabled=false,priority=30)
		public void Inventory_In_HouseUnits_refresh(WebDriver driver) throws Exception
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
			//Check In House Units page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1182, 1))).getText().equalsIgnoreCase("InHouse units"))
			{
				test.log(LogStatus.PASS, "In House Units page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "In House Units page refreshed Failed");
			}
			Thread.sleep(4000);wb.close();

		}
	
		@Test(enabled=false,priority=31)
		public void Inventory_In_HouseUnits_add_InHouseUnits(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(4000);
			//Click on the Add In House Units option
			driver.findElement(By.id("houseUnit")).click();
			Thread.sleep(3000);
			
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1194, 1))).getText().equalsIgnoreCase("New InHouse units"))
			{
				test.log(LogStatus.PASS, "New In House Units form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New In House Units form loaded Failed");
			}

			Thread.sleep(3000);
			
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Inventory_In_House_Units"));
			Thread.sleep(2000);
			
			//Check whether the measure type is Weight or not
			if(driver.findElement(By.xpath(excel.getData(3, 1195, 1))).isSelected()) 
			{
				//Click the Volume measure type
				driver.findElement(By.xpath(excel.getData(3, 1196, 1))).click();
			}
			
			//Check whether the measure type is Volume or not
			else if(driver.findElement(By.xpath(excel.getData(3, 1196, 1))).isSelected())
			{
				//Click the Weight measure type
				driver.findElement(By.xpath(excel.getData(3, 1195, 1))).click();
			}
			
			
			Thread.sleep(2000);
			//Click the Add Conversions option
			driver.findElement(By.xpath(excel.getData(3, 1197, 1))).click();
			
			Thread.sleep(2000);
			//Clear the number field
			driver.findElement(By.xpath(excel.getData(3, 1198, 1))).clear();
			//Enter the required amount
			driver.findElement(By.xpath(excel.getData(3, 1198, 1))).sendKeys("3");
			
			Thread.sleep(2000);
			//Click the Select an Option button
			driver.findElement(By.xpath(excel.getData(3, 1199, 1))).click();
			//Enter the Required option
			driver.findElement(By.xpath(excel.getData(3, 1200, 1))).sendKeys("Liter");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1200, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Add Conversions option
			driver.findElement(By.xpath(excel.getData(3, 1197, 1))).click();
			
			Thread.sleep(2000);
			//Clear the number field
			driver.findElement(By.xpath(excel.getData(3, 1201, 1))).clear();
			//Enter the required amount
			driver.findElement(By.xpath(excel.getData(3, 1201, 1))).sendKeys("2");
			
			Thread.sleep(2000);
			//Click the Select an Option button
			driver.findElement(By.xpath(excel.getData(3, 1202, 1))).click();
			//Enter the Required option
			driver.findElement(By.xpath(excel.getData(3, 1203, 1))).sendKeys("Liter");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1203, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(2000);
			//Click the Add Conversions option
			driver.findElement(By.xpath(excel.getData(3, 1197, 1))).click();
			
			Thread.sleep(2000);
			//Click the Close button
			driver.findElement(By.xpath(excel.getData(3, 1204, 1))).click();
			Thread.sleep(1000);
			
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			Thread.sleep(2000);
			
			WebElement inHouse=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
						
			//Check whether the In House Unit saved or not
			if(wait.until(ExpectedConditions.visibilityOf(inHouse)).getText().equalsIgnoreCase("Unit saved successfully"))
			{
				test.log(LogStatus.PASS, "New In House Units Saved Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New In House Units Save Failed");
			}

			Thread.sleep(4000);wb.close();
		}
		
		@Test(enabled=false,priority=33)
		public void Inventory_In_HouseUnits_edit_Ingredient_In_House_Units(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Inventory_In_House_Units"));
			Thread.sleep(5000);
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
			
			Thread.sleep(5000);
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Inventory_In_House_Units")+"1");
			Thread.sleep(3000);

			
			//Check whether the measure type is Weight or not
			if(driver.findElement(By.xpath(excel.getData(3, 1195, 1))).isSelected())
			{
				//Click the Volume measure type
				driver.findElement(By.xpath(excel.getData(3, 1196, 1))).click();
			}
			
			//Check whether the measure type is Volume or not
			else if(driver.findElement(By.xpath(excel.getData(3, 1196, 1))).isSelected())
			{
				//Click the Weight measure type
				driver.findElement(By.xpath(excel.getData(3, 1195, 1))).click();
			}
			Thread.sleep(2000);

			//Click the update
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			Thread.sleep(2000);
			
			WebElement inHouse=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
						
			//Check whether the In House Unit Updated or not
			if(wait.until(ExpectedConditions.visibilityOf(inHouse)).getText().equalsIgnoreCase("Unit updated successfully"))
			{
				test.log(LogStatus.PASS, "New In House Units updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New In House Units updated Failed");
			}

			Thread.sleep(4000);wb.close();
			

		}
		
		@Test(enabled=false,priority=34)
		public void Inventory_In_HouseUnits_delete(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Inventory_In_House_Units")+"1");
			Thread.sleep(5000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).click();
			Thread.sleep(2000);
			//Click the Yes button in the popup
			driver.findElement(By.xpath(excel.getData(3, 545, 1))).click();
			
			WebElement inHouse=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
						
			//Check whether the In House Unit deleted or not
			if(wait.until(ExpectedConditions.visibilityOf(inHouse)).getText().equalsIgnoreCase("Unit inactivated successfully"))
			{
				test.log(LogStatus.PASS, "New In House Units deleted Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New In House Units deleted Failed");
			}

			Thread.sleep(4000);
		

			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(3, 144, 1))).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector(excel.getData(3, 209, 4))).click();
			Thread.sleep(1000);
			 
			//Click the Yes button in the popup
			driver.findElement(By.xpath(excel.getData(3, 545, 1))).click();
			
			Thread.sleep(3000);
			//Check the In House Units activated or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Unit activated successfully"))
			{
				test.log(LogStatus.PASS, "In House Units is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "In House Units is activated Failed");
			}
			
			Thread.sleep(4000);wb.close();
		}

		@Test(enabled=false,priority=35)
		public void Inventory_In_HouseUnits_closeButton(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(4000);
			//Click on the Add In House Units option
			driver.findElement(By.id("houseUnit")).click();
			Thread.sleep(3000);
			
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1194, 1))).getText().equalsIgnoreCase("New InHouse units"))
			{
				test.log(LogStatus.PASS, "New In House Units form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New In House Units form loaded Failed");
			}

			Thread.sleep(3000);
			
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Inventory_In_House_Units"));
			Thread.sleep(2000);
				
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(3, 145, 1))).click();
			
			Thread.sleep(3000);
			//Check whether the new In House Units canceled or not 
			if(driver.findElement(By.id("houseUnit")).isDisplayed())
			{
				test.log(LogStatus.PASS, "New In House Units Canceled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New In House Units Canceled Failed");
			}

			Thread.sleep(4000);wb.close();
		}
				
		@Test(enabled=false,priority=36)
		public void Inventory_In_HouseUnits_verifyActive_InActiveButton(WebDriver driver) throws Exception
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
				test.log(LogStatus.FAIL, "Deleted In House Units are not here, we are in Active Page");
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
