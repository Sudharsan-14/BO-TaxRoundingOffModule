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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome_Account_User.Browser_Account_Level_User;
import newReadExcelFile_New.ExcelDataConfig;

public class AddEditDelete_Category {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete Category");

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
			Category_method_openCategory_Page(driver);
			Category_method_refreshCategory_Page(driver);
			Category_method_add_Category(driver);
			Category_method_edit_Category_DaysOfWeek(driver);
			Category_method_edit_Category_DaysOfMonth(driver);
			Category_method_edit_Category_DateRange(driver);
			Category_method_edit_Category_SpecificDate(driver);
			Category_method_edit_Category_StartDateAndEndDate(driver);
			Category_method_delete_Category(driver);
			Category_method_cancelCategory(driver);
			Category_method_inactiveAndActive_Button(driver);
			Thread.sleep(1500);
		}

		@Test(enabled = false,priority=81)
		public void Category_method_openCategory_Page(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"categories/1");
			Thread.sleep(10000);
			
			try
			{
			//Check Categories page opened or not
			if(driver.findElement(By.xpath(excel.getData(0, 15, 1))).getText().equalsIgnoreCase("Categories"))
			{
				test.log(LogStatus.PASS, "Categories page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Categories page loaded Failed");
				String scnShot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s1="data:image/png;base64,"+scnShot1;
				test.log(LogStatus.INFO,test.addScreenCapture(s1));
			}
			}
			catch(Exception e)
			{
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.FAIL,test.addScreenCapture(s));
			}
			Thread.sleep(3000);
			wb.close();
		}
		
		@Test(enabled = false,priority=82)
		public void Category_method_refreshCategory_Page(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(0, 30, 1))).click();
			Thread.sleep(5000);
			
			//Check Categories page opened or not
			if(driver.findElement(By.xpath(excel.getData(0, 62, 1))).getText().equalsIgnoreCase("Categories"))
			{
				test.log(LogStatus.PASS, "Category page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Category page loaded Failed");
			}
			Thread.sleep(3000);
			wb.close();
		}
		
		@Test(enabled = false,priority=84)
		public void Category_method_add_Category(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			for (int i = 0; i < 10; i++) {
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
			Thread.sleep(3000);
			//Click the Add category button
			driver.findElement(By.xpath(excel.getData(0, 63, 1))).click();
			
			Thread.sleep(2000);
			//Check weather the New Category form loaded or not
			if(driver.findElement(By.xpath(excel.getData(0, 64, 1))).getText().equalsIgnoreCase("New Category"))
			{
				test.log(LogStatus.PASS, "New Category form Loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Category form Loaded Fail");
			}
			
			Thread.sleep(6000);
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(0, 65, 1))).clear();
			//Enter the required name
			driver.findElement(By.xpath(excel.getData(0, 65, 1))).sendKeys(Utility.getProperty("Category_Name"));
			
			//Click the Add Department button
			driver.findElement(By.xpath(excel.getData(0, 66, 1))).click();
			
			Thread.sleep(2000);
			//Check weather the new department form loaded or not
			if(driver.findElement(By.xpath(excel.getData(0, 67, 1))).getText().equalsIgnoreCase("New department"))
			{
				test.log(LogStatus.PASS, "New department form loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New department form loaded fail");
			}
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(0, 68, 1))).clear();
			//Enter the department name 
			driver.findElement(By.xpath(excel.getData(0, 68, 1))).sendKeys(Utility.getProperty("Category_Add_Dept_Name"));
			
			//Clear the description field
			driver.findElement(By.xpath(excel.getData(0, 35, 1))).clear();
			//Enter the required description field
			driver.findElement(By.xpath(excel.getData(0, 35, 1))).sendKeys("New Department Description");
			
			Thread.sleep(1000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(0, 69, 1))).click();
			
			Thread.sleep(2000);
			//click the role button option
			driver.findElement(By.xpath(excel.getData(0, 70, 1))).click();
			//Enter the required role
			driver.findElement(By.xpath(excel.getData(0, 71, 1))).sendKeys("Driver");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(0, 71, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the Add Coursing button
			driver.findElement(By.xpath(excel.getData(0, 72, 1))).click();
			
			Thread.sleep(2000);
			//Check weather the new coursing form loaded or not
			if(driver.findElement(By.xpath(excel.getData(0, 53, 1))).getText().equalsIgnoreCase("New Coursing"))
			{
				test.log(LogStatus.PASS, "New coursing form loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New coursing form loaded fail");
			}
			
			Thread.sleep(4000);
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(0, 68, 1))).clear();
			//Enter the course name 
			driver.findElement(By.xpath(excel.getData(0, 68, 1))).sendKeys(Utility.getProperty("Category_Add_Course_Name"));
			
			Thread.sleep(3000);
			//Clear the priority field
			driver.findElement(By.name(excel.getData(0, 55, 3))).clear();
			//Enter the required priority field
			driver.findElement(By.name(excel.getData(0, 55, 3))).sendKeys("3");
			
			Thread.sleep(3000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(0, 69, 1))).click();

			Thread.sleep(4000);
			//Click the Serving Size Level Option
			driver.findElement(By.xpath(excel.getData(0, 73, 1))).click();
			//Enter the required serving size level
			driver.findElement(By.xpath(excel.getData(0, 74, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 74, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the Tare group Option
			driver.findElement(By.xpath(excel.getData(0, 75, 1))).click();
			//Enter the required tare group
			driver.findElement(By.xpath(excel.getData(0, 76, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 76, 1))).sendKeys(Keys.ENTER);

			if(driver.findElement(By.xpath(excel.getData(0, 77, 1))).isEnabled())
			{
				Thread.sleep(4000);
				//Click the default tare Option
				driver.findElement(By.xpath(excel.getData(0, 78, 1))).click();
				//Enter the default tare
				driver.findElement(By.xpath(excel.getData(0, 79, 1))).sendKeys(Keys.ARROW_DOWN);
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(0, 79, 1))).sendKeys(Keys.ENTER);
			}
			
			Thread.sleep(2000);
			//Enable or Disable the Hide in POS option
			driver.findElement(By.xpath(excel.getData(0, 80, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Hide in ZenPepper option
			driver.findElement(By.xpath(excel.getData(0, 81, 1))).click();

			Thread.sleep(2000);
			//Enable or Disable the Hide in Kiosk option
			driver.findElement(By.xpath(excel.getData(0, 82, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Is Conventional option
			driver.findElement(By.xpath(excel.getData(0, 83, 1))).click();

			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			
			Thread.sleep(2000);
			//Enable or Disable the Tax option
			driver.findElement(By.xpath(excel.getData(0, 84, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Kitchen Printers option
			driver.findElement(By.xpath(excel.getData(0, 85, 1))).click();

			Thread.sleep(2000);
			//Enable or Disable the Label Printers option
			driver.findElement(By.xpath(excel.getData(0, 86, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Restrict Printers option
			driver.findElement(By.xpath(excel.getData(0, 87, 1))).click();

			Thread.sleep(2000);
			//Click the Add Tax button
			driver.findElement(By.xpath(excel.getData(0, 88, 1))).click();
			
			Thread.sleep(3000);
			//Check weather the new tax form loaded or not
			if(driver.findElement(By.xpath(excel.getData(0, 89, 1))).getText().equalsIgnoreCase("New Tax"))
			{
				test.log(LogStatus.PASS, "New Tax form loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tax form loaded fail");
			}
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(0, 90, 1))).clear();
			//Enter the tax name 
			driver.findElement(By.xpath(excel.getData(0, 90, 1))).sendKeys(Utility.getProperty("Category_Add_Tax_Name"));
			
			
			Thread.sleep(2000);
			//Select the required Apply To option
			Select applyTo = new Select(driver.findElement(By.name(excel.getData(0, 91, 3))));
			applyTo.selectByValue("Item");
			//Enable or Disable the Default Tax option
			//driver.findElement(By.xpath("//form[@name='catDependForm']/div[2]/div[1]/div/label/span")).click();
		try {	
			Thread.sleep(2000);
			//Select the EverTec Tax Type
			WebElement evertec=driver.findElement(By.name("EvertecTaxType"));
			Select evrtec=new Select(evertec);
			evrtec.selectByVisibleText("None");
		} catch(Exception k) {}
			
			Thread.sleep(2000);
			//Enable or Disable the Inclusive option
			//driver.findElement(By.xpath("//form[@name='catDependForm']/div[2]/div[2]/div/label/span")).click();
			
			//Clear the percentage field
			driver.findElement(By.name(excel.getData(0, 92, 3))).clear();
			//Enter the required percentage field
			driver.findElement(By.name(excel.getData(0, 92, 3))).sendKeys("1230");
			
			Thread.sleep(4000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();

			Thread.sleep(8000);
			try
			{
				if(driver.findElement(By.xpath(excel.getData(0, 94, 1))).isDisplayed())
				{
					test.log(LogStatus.INFO, "Newly Add Tax(Add in Category Page) is not ");
					
					Thread.sleep(2000);
					//Click the tax box field
					driver.findElement(By.xpath(excel.getData(0, 95, 1))).click();
					//Click the input field
					driver.findElement(By.xpath(excel.getData(0, 96, 1))).click();
					//Click the Enter button
					driver.findElement(By.xpath(excel.getData(0, 96, 1))).sendKeys(Keys.ENTER);
				}
			}
			catch(Exception e)
			{
				
			}
			Thread.sleep(5000);
			//Click the Kitchen Printers Option
			driver.findElement(By.xpath(excel.getData(0, 97, 1))).click();
			//Enter the required Kitchen Printers
			driver.findElement(By.xpath(excel.getData(0, 98, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 98, 1))).sendKeys(Keys.ENTER);
/*			
			Thread.sleep(5000);
			//Click the Label Printers Option
			driver.findElement(By.xpath(excel.getData(0, 99, 1))).click();
			Thread.sleep(1000);
			//Enter the required Label Printers
			driver.findElement(By.xpath(excel.getData(0, 100, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 100, 1))).sendKeys(Keys.ENTER);
*/
			Thread.sleep(3000);
			//Click the Restrict Printers Option
			driver.findElement(By.xpath(excel.getData(0, 101, 1))).click();
			//Enter the Restrict Printers
			driver.findElement(By.xpath(excel.getData(0, 102, 1))).sendKeys("BarTab");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 102, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(5000);
			//Select the image from disk
			driver.findElement(By.xpath(excel.getData(0, 103, 1))).sendKeys(Utility.getProperty("Settings_Store_Information_Store_Image_Path"));
			
			Thread.sleep(5000);
			//Click the Applicable Time Period Option
			driver.findElement(By.xpath(excel.getData(0, 104, 1))).click();
			//Enter the required time period
			driver.findElement(By.xpath(excel.getData(0, 105, 1))).sendKeys("Always");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 105, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(5000);
			//Click the save button
			driver.findElement(By.xpath(excel.getData(0, 106, 1))).click();
			
		//	Thread.sleep(4000);
			
WebElement ele=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			
			WebDriverWait wait=new WebDriverWait(driver,120);
			
			//Check weather the new course is saved or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Category saved successfully"))
			{
				test.log(LogStatus.PASS, "New Category Saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Category saved fail");
			}
			Thread.sleep(5000);
			
			wb.close();
		}
		
		@Test(enabled = false,priority=85)
		public void Category_method_edit_Category_DaysOfWeek(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("Category_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);

			Thread.sleep(3000);
			//Click the Edit button
			driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();
			
			Thread.sleep(10000);
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(0, 65, 1))).clear();
			//Enter the required name
			driver.findElement(By.xpath(excel.getData(0, 65, 1))).sendKeys(Utility.getProperty("Category_Name")+"1");
			
			Thread.sleep(3000);
			//Click the Department Option
			driver.findElement(By.xpath(excel.getData(0, 107, 1))).click();
			//Enter the Department
			driver.findElement(By.xpath(excel.getData(0, 108, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 108, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(3000);
			//Click the Coursing Option
			driver.findElement(By.xpath(excel.getData(0, 109, 1))).click();
			//Enter the Coursing
			driver.findElement(By.xpath(excel.getData(0, 110, 1))).sendKeys("ENTREE");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 110, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Enable or Disable the Hide in POS option
			driver.findElement(By.xpath(excel.getData(0, 80, 1))).click();

			Thread.sleep(2000);
			//Enable or Disable the Place online order option
			driver.findElement(By.xpath(excel.getData(0, 81, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Hide in Kiosk option
			driver.findElement(By.xpath(excel.getData(0, 82, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the is conventional option
			driver.findElement(By.xpath(excel.getData(0, 83, 1))).click();

			Thread.sleep(2000);
			//Enable or Disable the Tax option
			//driver.findElement(By.xpath(excel.getData(0, 84, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Kitchen Printers option
			driver.findElement(By.xpath(excel.getData(0, 85, 1))).click();

			Thread.sleep(2000);
			//Enable or Disable the Label Printers option
			driver.findElement(By.xpath(excel.getData(0, 86, 1))).click();
			

			Thread.sleep(5000);
/*			//Click the Select image from gallery option
			driver.findElement(By.xpath(excel.getData(0, 111, 1))).click();
			Thread.sleep(15000);
			//Click the required image from the option
			driver.findElement(By.xpath(excel.getData(0, 112, 1))).click();*/
			Thread.sleep(3000);
			
			//Click the Applicable Time Period field
			driver.findElement(By.xpath(excel.getData(0, 104, 1))).click();
			//Enter the required time option from the given item list
			driver.findElement(By.xpath(excel.getData(0, 105, 1))).sendKeys("Days of Week");
			//Press the Enter Key in the Applicable time period option field
			driver.findElement(By.xpath(excel.getData(0, 105, 1))).sendKeys(Keys.ENTER);
			
			//Click the Days of Week Option
			driver.findElement(By.xpath(excel.getData(0, 113, 1))).click();
			//Enter the required DAY for Searching
			driver.findElement(By.xpath(excel.getData(0, 114, 1))).sendKeys("WEDNESDAY");
			//Press the Enter key after enter the keyword
			driver.findElement(By.xpath(excel.getData(0, 114, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Enable or disable the Restriction Time Period option
			driver.findElement(By.xpath(excel.getData(3, 276, 1))).click();
			Thread.sleep(2000);
			
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath(excel.getData(3, 2290, 1))).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath(excel.getData(3, 2291, 1))).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath(excel.getData(3, 2290, 1))).click();
			}


			Thread.sleep(3000);
			//Click the Update button
			driver.findElement(By.xpath(excel.getData(0, 106, 1))).click();
			
		//	Thread.sleep(4000);
			
			WebElement ele=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			//Check weather the new category is updated or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Category updated successfully"))
			{
				test.log(LogStatus.PASS, "New Category updated successfully for Days of Week");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Category updated fail for Days of Week");
			}
			Thread.sleep(5000);
			wb.close();
		}
		
		@Test(enabled = false,priority=86)
		public void Category_method_edit_Category_DaysOfMonth(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("Category_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);

			Thread.sleep(4000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();	

			Thread.sleep(2000);
			//Enable or Disable the Restrict Printers option
			driver.findElement(By.xpath(excel.getData(0, 87, 1))).click();
			
			Thread.sleep(3000);
			//Click the Close Option in restrict printers option
			driver.findElement(By.xpath(excel.getData(0, 118, 1))).click();
			

			Thread.sleep(3000);
			
			//Click the Applicable Time Period field
			driver.findElement(By.xpath(excel.getData(0, 104, 1))).click();
			//Enter the required time option from the given item list
			driver.findElement(By.xpath(excel.getData(0, 105, 1))).sendKeys("Days of Month");
			//Press the Enter Key in the Applicable time period option field
			driver.findElement(By.xpath(excel.getData(0, 105, 1))).sendKeys(Keys.ENTER);
			
			//Click the Required Date form the Calendar
			driver.findElement(By.xpath(excel.getData(0, 119, 1))).click();
			Thread.sleep(3000);
			
			//Enable or Disable the Restriction Months option
			driver.findElement(By.xpath(excel.getData(3, 279, 1))).click();
			
			Thread.sleep(2000);
			//Click the months field
			driver.findElement(By.xpath(excel.getData(0, 120, 1))).click();
			//Enter the Required month
			driver.findElement(By.xpath(excel.getData(0, 121, 1))).sendKeys("MAY");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 121, 1))).sendKeys(Keys.ENTER);
			
			//Enable or Disable the Restriction Time option
			driver.findElement(By.xpath(excel.getData(3, 276, 1))).click();
			
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath(excel.getData(3, 2290, 1))).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath(excel.getData(3, 2291, 1))).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath(excel.getData(3, 2290, 1))).click();
			}
			
			Thread.sleep(3000);

			//Click the Update button
			driver.findElement(By.xpath(excel.getData(0, 106, 1))).click();
			
		//	Thread.sleep(4000);
			WebElement ele=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			//Check weather the new category is updated or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Category updated successfully"))
			{
				test.log(LogStatus.PASS, "New Category updated successfully for Days of Month");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Category updated fail for Days of Month");
			}
			Thread.sleep(5000);
			
			wb.close();
		}

		@Test(enabled = false,priority=87)
		public void Category_method_edit_Category_DateRange(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("Category_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);

			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();	

			Thread.sleep(3000);
			
			//Click the Applicable Time Period field
			driver.findElement(By.xpath(excel.getData(0, 104, 1))).click();
			//Enter the required time option from the given item list
			driver.findElement(By.xpath(excel.getData(0, 105, 1))).sendKeys("Date Range");
			//Press the Enter Key in the Applicable time period option field
			driver.findElement(By.xpath(excel.getData(0, 105, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(3000);
			//Clear the From date field
			driver.findElement(By.name(excel.getData(0, 123, 3))).clear();
			//Enter the required from Date
			driver.findElement(By.name(excel.getData(0, 123, 3))).sendKeys("25-May-2040");
			//Clear the To Date field
			driver.findElement(By.name(excel.getData(0, 124, 3))).clear();
			//Enter the required To Date
			driver.findElement(By.name(excel.getData(0, 124, 3))).sendKeys("28-May-2040");

			//Enable or Disable the Restriction Days Option
			driver.findElement(By.xpath(excel.getData(0, 125, 1))).click();
			
			Thread.sleep(2000);
			//Click the Days of a Week option
			driver.findElement(By.xpath(excel.getData(0, 126, 1))).click();
			//Enter the Required Date
			driver.findElement(By.xpath(excel.getData(0, 127, 1))).sendKeys("FRIDAY");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 127, 1))).sendKeys(Keys.ENTER);

			//Enable or Disable the Restriction Time option
			driver.findElement(By.xpath(excel.getData(3, 276, 1))).click();
			
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath(excel.getData(3, 2290, 1))).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath(excel.getData(3, 2291, 1))).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath(excel.getData(3, 2290, 1))).click();
			}

			
			Thread.sleep(3000);

			//Click the Update button
			driver.findElement(By.xpath(excel.getData(0, 106, 1))).click();
			
	//		Thread.sleep(4000);
			WebElement ele=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			//Check weather the new category is updated or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Category updated successfully"))
			{
				test.log(LogStatus.PASS, "New Category updated successfully for Date Range");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Category updated fail for Date Range");
			}
			Thread.sleep(5000);
			wb.close();
		}
		
		@Test(enabled = false,priority=88)
		public void Category_method_edit_Category_SpecificDate(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("Category_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);

			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();	

			Thread.sleep(3000);
			
			//Click the Applicable Time Period field
			driver.findElement(By.xpath(excel.getData(0, 104, 1))).click();
			//Enter the required time option from the given item list
			driver.findElement(By.xpath(excel.getData(0, 105, 1))).sendKeys("Specific date");
			//Press the Enter Key in the Applicable time period option field
			driver.findElement(By.xpath(excel.getData(0, 105, 1))).sendKeys(Keys.ENTER);
			
			//Clear the Date field
			driver.findElement(By.name(excel.getData(0, 129, 3))).clear();
			//Enter the required date
			driver.findElement(By.name(excel.getData(0, 129, 3))).sendKeys("15-May-2040");
			
			//Enable or Disable the Restriction Time option
			driver.findElement(By.xpath(excel.getData(3, 276, 1))).click();
			
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath(excel.getData(3, 2290, 1))).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath(excel.getData(3, 2291, 1))).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath(excel.getData(3, 2290, 1))).click();
			}

			
			Thread.sleep(3000);

			//Click the Update button
			driver.findElement(By.xpath(excel.getData(0, 106, 1))).click();


WebElement ele=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			//Check weather the new category is updated or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Category updated successfully"))
			{
				test.log(LogStatus.PASS, "New Category updated successfully for Specific Date");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Category updated fail for Specific Date");
			}
			Thread.sleep(5000);
			wb.close();
		}
		
		@Test(enabled = false,priority=89)
		public void Category_method_edit_Category_StartDateAndEndDate(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("Category_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);

			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();	

			Thread.sleep(3000);
			
			//Click the Applicable Time Period field
			driver.findElement(By.xpath(excel.getData(0, 104, 1))).click();
			//Enter the required time option from the given item list
			driver.findElement(By.xpath(excel.getData(0, 105, 1))).sendKeys("Start date time & end date time");
			//Press the Enter Key in the Applicable time period option field
			driver.findElement(By.xpath(excel.getData(0, 105, 1))).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(3000);
			//Clear the From date field
			driver.findElement(By.name(excel.getData(0, 123, 3))).clear();
			//Enter the required from Date
			driver.findElement(By.name(excel.getData(0, 123, 3))).sendKeys("25-May-2040");
			//Clear the To Date field
			driver.findElement(By.name(excel.getData(0, 124, 3))).clear();
			//Enter the required To Date
			driver.findElement(By.name(excel.getData(0, 124, 3))).sendKeys("28-May-2040");
			
			//Enable or Disable the Restriction Days Option
			driver.findElement(By.xpath(excel.getData(0, 125, 1))).click();
			
			Thread.sleep(2000);
			//Click the Days of a Week option
			driver.findElement(By.xpath(excel.getData(0, 126, 1))).click();
			//Enter the Required Date
			driver.findElement(By.xpath(excel.getData(0, 127, 1))).sendKeys("FRIDAY");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 127, 1))).sendKeys(Keys.ENTER);

			//Check whether the 
			
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath("//div[@ng-model='availableTimePeriod.startTime']/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//div[@ng-model='availableTimePeriod.endTime']/table/tbody/tr[2]/td[6]/button")).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath("//div[@ng-model='availableTimePeriod.startTime']/table/tbody/tr[2]/td[6]/button")).click();
			}


			
			Thread.sleep(3000);

			//Click the Update button
			driver.findElement(By.xpath(excel.getData(0, 106, 1))).click();
			
		//	Thread.sleep(4000);
			
WebElement ele=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			//Check weather the new category is updated or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Category updated successfully"))
			{
				test.log(LogStatus.PASS, "New Category updated successfully for Start Date and End Date");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Category updated fail for Start Date and End Date");
			}
			Thread.sleep(5000);
			wb.close();
		}
		
		@Test(enabled = false,priority=90)
		public void Category_method_delete_Category(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("Category_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);

			Thread.sleep(2000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(0, 42, 4))).click();
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(0, 43, 4))).click();
			
			Thread.sleep(4000);
			//Check the course	 deleted or not
			if(driver.findElement(By.xpath(excel.getData(0, 39, 1))).getText().equalsIgnoreCase("Category inactivated successfully"))
			{
				test.log(LogStatus.PASS, "New Category is deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Category is deleted Failed");
			}
			Thread.sleep(3000);
			
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(0, 44, 1))).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector(excel.getData(0, 46, 4))).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(0, 43, 4))).click();
			//Thread.sleep(4000);
			
WebElement ele=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			
			WebDriverWait wait=new WebDriverWait(driver,60);
			
			//Check the course activated or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Category activated successfully"))
			{
				test.log(LogStatus.PASS, "Category is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Category is activated Failed");
			}
			
			Thread.sleep(5000);
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(0, 44, 1))).click();
			Thread.sleep(30000);
			
			wb.close();
		}

		@Test(enabled = false,priority=91)
		public void Category_method_cancelCategory(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("Category_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);

			Thread.sleep(4000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();
			
			Thread.sleep(5000);
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(0, 130, 1))).click();
			
			Thread.sleep(4000);
			//Check the course form is closed or not
			if(driver.findElement(By.xpath(excel.getData(0, 62, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Category page is Closed after click the close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Category page is displayed after click the close button");
			}
			
			Thread.sleep(5000);
			
			wb.close();
		}
		
		@Test(enabled = false,priority=92)
		public void Category_method_inactiveAndActive_Button(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
			Thread.sleep(1000);
			
			//Click the Active button
			driver.findElement(By.xpath(excel.getData(0, 44, 1))).click();
			Thread.sleep(1000);
			try
			{
			//Check the page
			if(driver.findElement(By.cssSelector(excel.getData(0, 46, 4))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Inactive page is displayed after click the Active button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inactive page is not displayed after click the Active button");
			}
			}
			catch(Exception d) {}
			Thread.sleep(3000);
			//Click the InActive button
			driver.findElement(By.xpath(excel.getData(0, 44, 1))).click();
			Thread.sleep(1000);
			
			//Check the page
			if(driver.findElement(By.cssSelector(excel.getData(0, 42, 4))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Active page is displayed after click the Inactive button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Active page is not displayed after click the Inactive button");
			}
			Thread.sleep(5000);
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);//watchTutorial(driver);
			Thread.sleep(5000);wb.close();
			}
		
		}
		@Test(priority=3, enabled=false)
		public void watchTutorial(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(2000);
			//Click the Watch Tutorial Option
			driver.findElement(By.xpath(excel.getData(0, 47, 1))).click();
			WebElement iframe = driver.findElement(By.xpath(excel.getData(0, 48, 1)));
			driver.switchTo().frame(iframe);
			Thread.sleep(3500);
			try
			{
				if(driver.findElement(By.xpath(excel.getData(0, 49, 1))).isDisplayed()&&driver.findElement(By.xpath("//button/div[.='Share']")).isDisplayed())
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
			driver.findElement(By.xpath(excel.getData(0, 50, 1))).click();
			wb.close();
		}
}
