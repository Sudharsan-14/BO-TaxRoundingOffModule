package epicList_Chrome;

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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;


public class AddEditDelete_SubCategory {
	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete Sub Category");

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
			Subcategory_method_openSubCategory_Page(driver);
			Subcategory_method_refreshCategory_Page(driver);
			Subcategory_method_add_SubCategory(driver);
			Subcategory_method_edit_SubCategory_DaysOfWeek(driver);
			Subcategory_method_edit_Sub_Category_DaysOfMonth(driver);
			Subcategory_method_edit_Sub_Category_DateRange(driver);
			Subcategory_method_edit_Sub_Category_SpecificDate(driver);
			Subcategory_method_edit_Sub_Category_StartDateAndEndDate(driver);
			Subcategory_method_delete_Sub_Category(driver);
			Subcategory_method_cancelSubCategory(driver);
			Subcategory_method_inactiveAndActive_Button(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=101)
		public void Subcategory_method_openSubCategory_Page(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"categories/2");
			Thread.sleep(8000);
			
			try
			{
			//Check Sub Categories page opened or not
			if(driver.findElement(By.xpath(excel.getData(0, 16, 1))).getText().equalsIgnoreCase("Sub Categories"))
			{
				test.log(LogStatus.PASS, "Sub Categories page loaded Successfully");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Sub Categories page loaded Failed");
				
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
		
		@Test(enabled=false,priority=102)
		public void Subcategory_method_refreshCategory_Page(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).click();
			Thread.sleep(5000);
			
			//Check Sub Categories page opened or not
			if(driver.findElement(By.xpath(excel.getData(0, 131, 1))).getText().equalsIgnoreCase("Sub Categories"))
			{
				test.log(LogStatus.PASS, "Sub Category page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Sub Category page loaded Failed");
			}
			Thread.sleep(3000);
			wb.close();
		}

		@Test(enabled=false,priority=104)
		public void Subcategory_method_add_SubCategory(WebDriver driver) throws Exception
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
			//Click the Add sub category button
			driver.findElement(By.xpath(excel.getData(0, 132, 1))).click();
			
			Thread.sleep(2000);
			//Check weather the New Sub Category form loaded or not
			if(driver.findElement(By.xpath(excel.getData(0, 133, 1))).getText().equalsIgnoreCase("NEW SUB CATEGORY"))
			{
				test.log(LogStatus.PASS, "New Sub Category form Loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Category form Loaded Fail");
			}
			
			Thread.sleep(8000);
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(0, 134, 1))).clear();
			//Enter the required name
			driver.findElement(By.xpath(excel.getData(0, 134, 1))).sendKeys(Utility.getProperty("Sub_Category_Name"));
			
			Thread.sleep(3000);
			//Click the Coursing Option
			driver.findElement(By.xpath(excel.getData(0, 135, 1))).click();
			//Click the input filed of Coursing
			driver.findElement(By.xpath(excel.getData(0, 136, 1))).click();
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(0, 136, 1))).sendKeys(Keys.ENTER);
			
			//Click the Category option
			driver.findElement(By.xpath(excel.getData(0, 137, 1))).click();
			//Enter the Category
			driver.findElement(By.xpath(excel.getData(0, 138, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(0, 138, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the Tare group Option
			driver.findElement(By.xpath(excel.getData(0, 139, 1))).click();
			//Enter the required tare group
			driver.findElement(By.xpath(excel.getData(0, 140, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 140, 1))).sendKeys(Keys.ENTER);

			if(driver.findElement(By.xpath(excel.getData(0, 141, 1))).isEnabled())
			{
				Thread.sleep(3000);
				//Click the default tare Option
				driver.findElement(By.xpath(excel.getData(0, 142, 1))).click();
				//Enter the default tare
				driver.findElement(By.xpath(excel.getData(0, 143, 1))).sendKeys(Keys.ARROW_DOWN);
				//Press the Enter Key
				driver.findElement(By.xpath(excel.getData(0, 143, 1))).sendKeys(Keys.ENTER);
			}
			
			Thread.sleep(2000);
			//Enable or Disable the Hide in POS option
			driver.findElement(By.xpath(excel.getData(0, 144, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Hide in Online order option
			driver.findElement(By.xpath(excel.getData(0, 145, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Hide in Kiosk option
			driver.findElement(By.xpath(excel.getData(0, 146, 1))).click();

			Thread.sleep(2000);
			//Enable or Disable the Coversational option
			driver.findElement(By.xpath(excel.getData(0, 147, 1))).click();

			Thread.sleep(2000);
			//Enable or Disable the Tax option
		//	driver.findElement(By.xpath(excel.getData(0, 148, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Kitchen Printers option
			driver.findElement(By.xpath(excel.getData(0, 149, 1))).click();

			Thread.sleep(2000);
			//Enable or Disable the Label Printers option
			driver.findElement(By.xpath(excel.getData(0, 150, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Restrict Printers option
			driver.findElement(By.xpath(excel.getData(0, 151, 1))).click();

			Thread.sleep(2000);
	/*		//Click the Add Tax button
			driver.findElement(By.xpath(excel.getData(0, 152, 1))).click();
			
			Thread.sleep(2000);
			//Check weather the new tax form loaded or not
			if(driver.findElement(By.xpath(excel.getData(0, 153, 1))).getText().equalsIgnoreCase("New Tax"))
			{
				test.log(LogStatus.PASS, "New Tax form loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tax form loaded fail");
			}
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(0, 154, 1))).clear();
			//Enter the tax name 
			driver.findElement(By.xpath(excel.getData(0, 154, 1))).sendKeys(Utility.getProperty("Sub_Category_Add_Tax_Name"));
			
			
			Thread.sleep(3000);
			//Select the required Apply To option
			Select applyTo = new Select(driver.findElement(By.name("applyTo")));
			applyTo.selectByValue("Item");
			//Enable or Disable the Default Tax option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[2]/div[1]/div/label/span")).click();
			
			Thread.sleep(3000);
			//Enable or Disable the Inclusive option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[2]/div[2]/div/label/span")).click();
			
			Thread.sleep(2000);
			//Clear the percentage field
			driver.findElement(By.name(excel.getData(0, 156, 3))).clear();
			//Enter the required percentage field
			driver.findElement(By.name(excel.getData(0, 156, 3))).sendKeys("1230");
			
			Thread.sleep(3000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(0, 157, 1))).click();
*/
			Thread.sleep(8000);
			try
			{				
				if(driver.findElement(By.xpath(excel.getData(3, 2142, 1))).isDisplayed())
				{
					test.log(LogStatus.INFO, "Newly Add Tax(Add in Category Page) is not ");
					
					Thread.sleep(2000);
					//Click the tax box field
					driver.findElement(By.xpath(excel.getData(0, 159, 1))).click();
					//Click the input field
					driver.findElement(By.xpath(excel.getData(0, 160, 1))).click();
					//Click the Enter button
					driver.findElement(By.xpath(excel.getData(0, 160, 1))).sendKeys(Keys.ENTER);
				}
			}
			catch(Exception e)
			{
				
			}
			
			Thread.sleep(5000);
			//Click the Kitchen Printers Option
			driver.findElement(By.xpath(excel.getData(0, 161, 1))).click();
			//Enter the required Kitchen Printers
			driver.findElement(By.xpath(excel.getData(0, 162, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 162, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the Label Printers Option
			driver.findElement(By.xpath(excel.getData(0, 163, 1))).click();
			//Enter the required Label Printers
			driver.findElement(By.xpath(excel.getData(0, 164, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 164, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(3000);
			//Click the Restrict Printers Option
			driver.findElement(By.xpath(excel.getData(0, 165, 1))).click();
			//Enter the Restrict Printers
			driver.findElement(By.xpath(excel.getData(0, 166, 1))).sendKeys("BarTab");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 166, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(3000);
			//Click any one display button
			driver.findElement(By.xpath(excel.getData(0, 167, 1))).click();
			
			Thread.sleep(5000);
			//Select the image from disk
			driver.findElement(By.xpath(excel.getData(0, 168, 1))).sendKeys(Utility.getProperty("Settings_Store_Information_Store_Image_Path"));
			
			Thread.sleep(5000);
			//Click the Applicable Time Period Option
			driver.findElement(By.xpath(excel.getData(0, 169, 1))).click();
			Thread.sleep(2000);
			//Enter the required time period
			driver.findElement(By.xpath(excel.getData(0, 170, 1))).sendKeys("Always");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 170, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(5000);
			//Click the save button
			driver.findElement(By.xpath(excel.getData(3, 540, 1))).click();
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//System.out.println(driver.findElement(By.xpath(excel.getData(0, 39, 1))).getText());
			Thread.sleep(5000);
			//Check weather the new sub category is saved or not
			if(driver.findElement(By.xpath(excel.getData(0, 39, 1))).getText().equalsIgnoreCase("Sub Category Saved Successfully"))
			{
				test.log(LogStatus.PASS, "New Sub Category Saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Category saved fail");
			}
			Thread.sleep(5000);wb.close();
		}
		
		@Test(enabled=false,priority=105)
		public void Subcategory_method_edit_SubCategory_DaysOfWeek(WebDriver driver) throws Exception
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
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("Sub_Category_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
		
			Thread.sleep(4000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();
			
			Thread.sleep(10000);
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(0, 134, 1))).clear();
			//Enter the required name
			driver.findElement(By.xpath(excel.getData(0, 134, 1))).sendKeys(Utility.getProperty("Sub_Category_Name")+"1");
			
			Thread.sleep(3000);
			//Click the CAtegory Option
			driver.findElement(By.xpath(excel.getData(0, 135, 1))).click();
			Thread.sleep(1000);
			//Enter the Category
			driver.findElement(By.xpath(excel.getData(0, 136, 1))).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(1000);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 136, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(3000);
			//Click the Serving Size Level Option
			driver.findElement(By.xpath(excel.getData(0, 172, 1))).click();
			//Enter the Serving Size Level
			driver.findElement(By.xpath(excel.getData(0, 173, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 173, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Enable or Disable the Hide in POS option
			driver.findElement(By.xpath(excel.getData(0, 144, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Hide in Online order option
			driver.findElement(By.xpath(excel.getData(0, 145, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Hide in Kiosk option
			driver.findElement(By.xpath(excel.getData(0, 146, 1))).click();

			Thread.sleep(2000);
			//Enable or Disable the Coversational option
			driver.findElement(By.xpath(excel.getData(0, 147, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Tax option
	//		driver.findElement(By.xpath(excel.getData(0, 148, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Kitchen Printers option
			driver.findElement(By.xpath(excel.getData(0, 149, 1))).click();

			Thread.sleep(2000);
			//Enable or Disable the Label Printers option
			driver.findElement(By.xpath(excel.getData(0, 150, 1))).click();
			
			Thread.sleep(3000);
			//Click the Taxes Option
			driver.findElement(By.xpath(excel.getData(0, 159, 1))).click();
			//Enter the Taxes
			driver.findElement(By.xpath(excel.getData(0, 160, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 160, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(5000);
			//Click the Select image from gallery option
			driver.findElement(By.xpath(excel.getData(0, 174, 1))).click();
			Thread.sleep(5000);
			//Click the required image from the option
			driver.findElement(By.xpath(excel.getData(0, 175, 1))).click();
			Thread.sleep(3000);
			
			//Click the Applicable Time Period field
			driver.findElement(By.xpath(excel.getData(0, 169, 1))).click();
			//Enter the required time option from the given item list
			driver.findElement(By.xpath(excel.getData(0, 170, 1))).sendKeys("Days of Week");
			//Press the Enter Key in the Applicable time period option field
			driver.findElement(By.xpath(excel.getData(0, 170, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Days of Week Option
			driver.findElement(By.xpath(excel.getData(0, 176, 1))).click();
			//Enter the required DAY for Searching
			driver.findElement(By.xpath(excel.getData(0, 177, 1))).sendKeys("WEDNESDAY");
			//Press the Enter key after enter the keyword
			driver.findElement(By.xpath(excel.getData(0, 177, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Enable or disable the Restriction Time Period option
			driver.findElement(By.xpath(excel.getData(0, 178, 1))).click();
			Thread.sleep(2000);
			
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath(excel.getData(0, 179, 1))).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath(excel.getData(0, 180, 1))).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath(excel.getData(0, 179, 1))).click();
			}


			Thread.sleep(3000);
			//Click the Update button
			driver.findElement(By.xpath(excel.getData(0, 186, 1))).click();Thread.sleep(3000);
			
		//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//System.out.println(driver.findElement(By.xpath(excel.getData(0, 39, 1))).getText());
			Thread.sleep(2000);
			//Check weather the new sub category is updated or not
			if(driver.findElement(By.xpath(excel.getData(0, 39, 1))).getText().equalsIgnoreCase("Sub Category Updated Successfully"))
			{
				test.log(LogStatus.PASS, "New Sub Category updated successfully for Days of Week");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Category updated fail for Days of Week");
			}
			Thread.sleep(5000);wb.close();
		}
		
		@Test(enabled=false,priority=106)
		public void Subcategory_method_edit_Sub_Category_DaysOfMonth(WebDriver driver) throws Exception
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
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("Sub_Category_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(4000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();	

			Thread.sleep(2000);
			//Enable or Disable the Restrict Printers option
			driver.findElement(By.xpath(excel.getData(0, 151, 1))).click();
			

			Thread.sleep(3000);
			
			//Click the Applicable Time Period field
			driver.findElement(By.xpath(excel.getData(0, 169, 1))).click();
			//Enter the required time option from the given item list
			driver.findElement(By.xpath(excel.getData(0, 170, 1))).sendKeys("Days of Month");
			//Press the Enter Key in the Applicable time period option field
			driver.findElement(By.xpath(excel.getData(0, 170, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Required Date form the Calendar
			driver.findElement(By.xpath(excel.getData(0, 181, 1))).click();
			Thread.sleep(2000);
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			
			Thread.sleep(2000);
			//Enable or Disable the Restriction Months option
			driver.findElement(By.xpath(excel.getData(0, 182, 1))).click();
			
			Thread.sleep(2000);
			//Click the months field
			driver.findElement(By.xpath(excel.getData(0, 183, 1))).click();
			//Enter the Required month
			driver.findElement(By.xpath(excel.getData(0, 184, 1))).sendKeys("MAY");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 184, 1))).sendKeys(Keys.ENTER);
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);

			
			Thread.sleep(2000);
			//Enable or Disable the Restriction Time option
			driver.findElement(By.xpath(excel.getData(0, 178, 1))).click();
			
			Thread.sleep(2000);
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath(excel.getData(0, 179, 1))).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath(excel.getData(0, 181, 1))).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath(excel.getData(0, 179, 1))).click();
			}
			

			Thread.sleep(4000);
			//Click the Update button
			driver.findElement(By.xpath(excel.getData(0, 186, 1))).click();Thread.sleep(3000);
			try {
			WebElement up=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,30);
			//Check weather the new sub category is updated or not
			if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Sub Category Updated Successfully"))
			{
				test.log(LogStatus.PASS, "New Sub Category updated successfully for Days of Month");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Category updated fail for Days of Month");
			}
			}
			catch(Exception e) {}
			Thread.sleep(5000);wb.close();
		}

		@Test(enabled=false,priority=107)
		public void Subcategory_method_edit_Sub_Category_DateRange(WebDriver driver) throws Exception
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
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("Sub_Category_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(4000);driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();	

			Thread.sleep(3000);
			
			//Click the Applicable Time Period field
			driver.findElement(By.xpath(excel.getData(0, 169, 1))).click();
			//Enter the required time option from the given item list
			driver.findElement(By.xpath(excel.getData(0, 170, 1))).sendKeys("Date Range");
			//Press the Enter Key in the Applicable time period option field
			driver.findElement(By.xpath(excel.getData(0, 170, 1))).sendKeys(Keys.ENTER);
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			//Clear the From date field
			driver.findElement(By.name(excel.getData(0, 123, 3))).clear();
			//Enter the required from Date
			driver.findElement(By.name(excel.getData(0, 123, 3))).sendKeys("25-May-2040");
			//Clear the To Date field
			driver.findElement(By.name(excel.getData(0, 124, 3))).clear();
			//Enter the required To Date
			driver.findElement(By.name(excel.getData(0, 124, 3))).sendKeys("28-May-2040");

			Thread.sleep(2000);
			//Enable or Disable the Restriction Days Option
			driver.findElement(By.xpath(excel.getData(0, 185, 1))).click();
			
			Thread.sleep(2000);
			//Click the Days of a Week option
			driver.findElement(By.xpath(excel.getData(0, 176, 1))).click();
			//Enter the Required Date
			driver.findElement(By.xpath(excel.getData(0, 177, 1))).sendKeys("FRIDAY");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 177, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Enable or Disable the Restriction Time option
			driver.findElement(By.xpath(excel.getData(0, 178, 1))).click();
			
			Thread.sleep(2000);
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath(excel.getData(0, 179, 1))).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath(excel.getData(0, 180, 1))).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath(excel.getData(0, 179, 1))).click();
			}

			
			Thread.sleep(3000);

			Thread.sleep(3000);
			//Click the Update button
			driver.findElement(By.xpath(excel.getData(0, 186, 1))).click();Thread.sleep(3000);
			
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement up=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,30);
			//Check weather the new sub category is updated or not
			if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Sub Category Updated Successfully"))
			{
				test.log(LogStatus.PASS, "New Sub Category updated successfully for Date Range");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Category updated fail for Date Range");
			}
			Thread.sleep(5000);wb.close();
		}
		
		@Test(enabled=false,priority=108)
		public void Subcategory_method_edit_Sub_Category_SpecificDate(WebDriver driver) throws Exception
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
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("Sub_Category_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();	

			Thread.sleep(3000);
			
			//Click the Applicable Time Period field
			driver.findElement(By.xpath(excel.getData(0, 169, 1))).click();
			//Enter the required time option from the given item list
			driver.findElement(By.xpath(excel.getData(0, 170, 1))).sendKeys("Specific date");
			//Press the Enter Key in the Applicable time period option field
			driver.findElement(By.xpath(excel.getData(0, 170, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Date field
			driver.findElement(By.name(excel.getData(0, 129, 3))).clear();
			//Enter the required date
			driver.findElement(By.name(excel.getData(0, 129, 3))).sendKeys("15-May-2040");
			
			Thread.sleep(2000);
			//Enable or Disable the Restriction Time option
			driver.findElement(By.xpath(excel.getData(0, 178, 1))).click();
			
			Thread.sleep(2000);
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath(excel.getData(0, 179, 1))).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath(excel.getData(0, 180, 1))).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath(excel.getData(0, 179, 1))).click();
			}

			


			Thread.sleep(3000);
			//Click the Update button
			driver.findElement(By.xpath(excel.getData(0, 186, 1))).click();Thread.sleep(3000);
			
		//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//	Thread.sleep(2500);
			WebElement up=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,30);
			//Check weather the new sub category is updated or not
			if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Sub Category Updated Successfully"))
			{
				test.log(LogStatus.PASS, "New Sub Category updated successfully for Specific Date");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Category updated fail for Specific Date");
			}
			Thread.sleep(5000);
			wb.close();
		}
		
		@Test(enabled=false,priority=109)
		public void Subcategory_method_edit_Sub_Category_StartDateAndEndDate(WebDriver driver) throws Exception
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
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("Sub_Category_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(4000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();	

			Thread.sleep(3000);
			
			//Click the Applicable Time Period field
			driver.findElement(By.xpath(excel.getData(0, 169, 1))).click();
			//Enter the required time option from the given item list
			driver.findElement(By.xpath(excel.getData(0, 170, 1))).sendKeys("Start date time & end date time");
			//Press the Enter Key in the Applicable time period option field
			driver.findElement(By.xpath(excel.getData(0, 170, 1))).sendKeys(Keys.ENTER);
						
			Thread.sleep(3000);
			//Clear the From date field
			driver.findElement(By.name(excel.getData(0, 123, 3))).clear();
			//Enter the required from Date
			driver.findElement(By.name(excel.getData(0, 123, 3))).sendKeys("25-May-2049");
			//Clear the To Date field
			driver.findElement(By.name(excel.getData(0, 124, 3))).clear();
			//Enter the required To Date
			driver.findElement(By.name(excel.getData(0, 124, 3))).sendKeys("28-May-2049");
			
			Thread.sleep(2000);
			//Enable or Disable the Restriction Days Option
			driver.findElement(By.xpath(excel.getData(0, 185, 1))).click();
			
			Thread.sleep(2000);
			//Click the Days of a Week option
			driver.findElement(By.xpath(excel.getData(0, 176, 1))).click();
			//Enter the Required Date
			driver.findElement(By.xpath(excel.getData(0, 177, 1))).sendKeys("FRIDAY");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(0, 177, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath(excel.getData(0, 179, 1))).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath(excel.getData(0, 181, 1))).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath(excel.getData(0, 179, 1))).click();
			}

			Thread.sleep(4000);
			//Click the Update button
			driver.findElement(By.xpath(excel.getData(0, 186, 1))).click();Thread.sleep(3000);
			
			WebElement up=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,30);
			//Check weather the new sub category is updated or not
			if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Sub Category Updated Successfully"))
			{
				test.log(LogStatus.PASS, "New Sub Category updated successfully for Start Date and End Date");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Category updated fail for Start Date and End Date");
			}
			Thread.sleep(5000);wb.close();
		}
		
		@Test(enabled=false,priority=110)
		public void Subcategory_method_delete_Sub_Category(WebDriver driver) throws Exception
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
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("Sub_Category_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(0, 42, 4))).click();
			
			Thread.sleep(1000);
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(0, 43, 4))).click();
			
			WebElement up=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,30);
			//Check weather the new sub category is updated or not
			if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Sub Category Inactivated Successfully"))
			{
				test.log(LogStatus.PASS, "New Sub Category is deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Category is deleted Failed");
			}
			Thread.sleep(5000);
			
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(0, 44, 1))).click();
			Thread.sleep(5000);
			
			//Click the success button
			driver.findElement(By.cssSelector(excel.getData(0, 46, 4))).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(0, 43, 4))).click();
	try
	{
			WebElement up1=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			WebDriverWait wait1=new WebDriverWait(driver,30);
			//Check weather the new sub category is updated or not
			if(wait1.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Sub Category activated successfully"))
			{
				test.log(LogStatus.PASS, "Sub Category is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Sub Category is activated Failed");
			}
	}
	catch(Exception e) {}
			Thread.sleep(5000);
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(0, 44, 1))).click();
			Thread.sleep(3000);wb.close();
		}

		@Test(enabled=false,priority=111)
		public void Subcategory_method_cancelSubCategory(WebDriver driver) throws Exception
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
			driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("Sub_Category_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(4000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();
			
			Thread.sleep(5000);
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(0, 130, 1))).click();
			
			Thread.sleep(2000);
			//Check the course form is closed or not
			if(driver.findElement(By.xpath(excel.getData(0, 16, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Sub Category page is Closed after click the close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Sub Category page is displayed after click the close button");
			}
			
			Thread.sleep(5000);wb.close();
		}
		
		@Test(enabled=false,priority=112)
		public void Subcategory_method_inactiveAndActive_Button(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
			Thread.sleep(5000);wb.close();
		}
}
