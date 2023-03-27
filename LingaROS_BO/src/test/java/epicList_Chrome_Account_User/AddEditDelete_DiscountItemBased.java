package epicList_Chrome_Account_User;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class AddEditDelete_DiscountItemBased 
{
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete_DiscountItemBased_AutoDiscount");

	float unknownValue = 00;
	
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
			Item_Based_Discount_method_openDiscountsPage(driver);
			Item_Based_Discount_method_refreshDiscountsPage(driver);
			Item_Based_Discount_method_addDiscount_ItemBased_ApplicableTimePeriodAsDaysOfWeek(driver);
			Item_Based_Discount_method_editDiscount_ItemBased(driver);
			Item_Based_Discount_method_deleteDiscount_ItemBased(driver);
			Item_Based_Discount_method_cancelButton(driver);
			Item_Based_Discount_method_editDiscount_ItemBased_ApplicableTimePeriodAsAlways_SaveAndPublish(driver);
			Item_Based_Discount_method_editDiscount_ItemBased_ApplicableTimePeriodAsDaysOfMonth(driver);
			Item_Based_Discount_method_editDiscount_ItemBased_ApplicableTimePeriodAsDateRange(driver);
			Item_Based_Discount_method_editDiscount_ItemBased_ApplicableTimePeriodAsSpecificDate(driver);
			Item_Based_Discount_method_editDiscount_ItemBased_ApplicableTimePeriodAs_StartDateAndEndDate(driver);
			Item_Based_Discount_method_verifyActive_InActiveButton(driver);
			Item_Based_Discount_method_addSameName(driver);
			Thread.sleep(1500);
		}
	
	@Test(enabled=false,priority=41)
	public void Item_Based_Discount_method_openDiscountsPage(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
/*		//Click the Products/Items option
		driver.findElement(By.xpath("//span[.='Products/Items']")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Discounts']"));
		//Scroll the page till the Discount option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Discounts Option		
		driver.findElement(By.xpath("//span[.='Discounts']")).click();*/
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"item_based/discount/list");
		
		Thread.sleep(8000);
		
		try
		{
		//Check items page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 2141, 1))).getText().equalsIgnoreCase("Item Based"))
		{
			test.log(LogStatus.PASS, "Item Based discount page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Item Based discount page loaded Failed");
			
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
		Thread.sleep(5000);wb.close();
	}
	
	@Test(enabled=false,priority=42)
	public void Item_Based_Discount_method_refreshDiscountsPage(WebDriver driver) throws Exception
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
		//Check Menu items page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 2141, 1))).getText().equalsIgnoreCase("Item Based"))
		{
			test.log(LogStatus.PASS, "Item Based discount page Refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Item Based discount page Refreshed Failed");
		}
		Thread.sleep(5000);wb.close();
	}
	
	@Test(enabled=false,priority=44)
	public void Item_Based_Discount_method_addDiscount_ItemBased_ApplicableTimePeriodAsDaysOfWeek(WebDriver driver) throws Exception
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
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id(excel.getData(0, 214, 2))).click();
		Thread.sleep(5000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(0, 215, 1))).getText().equalsIgnoreCase("Discounts"))
		{
			test.log(LogStatus.PASS, "New Discounts Item form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Discounts Item form loaded Failed");
		}

		Thread.sleep(3000);
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount"));
		
		Thread.sleep(2000);
		//Click the Coupon Only Option
		driver.findElement(By.xpath(excel.getData(0, 217, 1))).click();
		
		Thread.sleep(2000);
		//Click the Auto discount option
		driver.findElement(By.xpath(excel.getData(0, 218, 1))).click();
		Thread.sleep(3000);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		
		Thread.sleep(2000);
		//Clear the Coupon Code
		driver.findElement(By.xpath(excel.getData(0, 219, 1))).clear();
		//Enter the Coupon Code
		driver.findElement(By.xpath(excel.getData(0, 219, 1))).sendKeys(Utility.getProperty("ItemBased_Discount_Code"));
		
		Thread.sleep(2000);
		//Click the Level Field
		driver.findElement(By.xpath(excel.getData(3, 2168, 1))).click();
		//Enter the required Level
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKeys("Menu item");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Click the Menu Item Field
		driver.findElement(By.xpath(excel.getData(3, 2170, 1))).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath(excel.getData(3, 2171, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter keys
		driver.findElement(By.xpath(excel.getData(3, 2171, 1))).sendKeys(Keys.ENTER);	
		Thread.sleep(2000);
		
		Thread.sleep(2000);
		//Check whether the All Serving Size in Menu Item
		if(driver.findElement(By.xpath(excel.getData(3, 2172, 1))).isSelected())
		{}
		else
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath(excel.getData(3, 2172, 1))).click();
		}
		
		Thread.sleep(3000);
		//Clear the minimum Quantity field
		driver.findElement(By.name(excel.getData(0, 225, 3))).clear();
		//Enter the minimum Quantity
		driver.findElement(By.name(excel.getData(0, 225, 3))).sendKeys("100");
		
		Thread.sleep(2000);
		//Select the required Discount Type
		Select discType = new Select(driver.findElement(By.name(excel.getData(0, 226, 3))));
		discType.selectByVisibleText("Promo");
		
		Thread.sleep(2000);
		//Click the Discount option
		driver.findElement(By.xpath(excel.getData(0, 227, 1))).click();
		//Click the required option
		driver.findElement(By.xpath(excel.getData(0, 228, 1)))	.click();
		
		Thread.sleep(2000);
		//Clear the Discount Field DropDown Option
		driver.findElement(By.name(excel.getData(0, 229, 3))).clear();
		//Enter the required amount
		driver.findElement(By.name(excel.getData(0, 229, 3))).sendKeys("200");

		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
			
		Thread.sleep(2000);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath(excel.getData(3, 2174, 1))).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath(excel.getData(3, 2175, 1))).sendKeys("Days of Week");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath(excel.getData(3, 2175, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Days if Week Option
		driver.findElement(By.xpath(excel.getData(0, 176, 1 ))).click();
		//Enter the required DAY for Searching
		driver.findElement(By.xpath(excel.getData(0, 177, 1 ))).sendKeys("WEDNESDAY");
		//Press the Enter key after enter the keyword
		driver.findElement(By.xpath(excel.getData(0, 177, 1 ))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Enable or Disable the Restriction Time
		driver.findElement(By.xpath(excel.getData(0, 178, 1 ))).click();
		Thread.sleep(2000);
	
		Thread.sleep(2000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath(excel.getData(0, 179, 1 ))).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath(excel.getData(0, 180, 1 ))).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath(excel.getData(0, 179, 1 ))).click();
		}
		
		Thread.sleep(2000);
		Select applyDiscount = new Select(driver.findElement(By.name(excel.getData(0, 230, 3 ))));
		applyDiscount.selectByVisibleText("Before Tax");

		//Enable or Disable the Include Additional Modifier Option
		//driver.findElement(By.xpath(excel.getData(0, 234, 1))).click();
		
		Thread.sleep(2000);
		//Clear the priority field
		driver.findElement(By.name(excel.getData(0, 231, 3 ))).clear();
		//Enter the priority
		driver.findElement(By.name(excel.getData(0, 231, 3 ))).sendKeys("3");
		
		Thread.sleep(2000);
		//Click the Role field
		driver.findElement(By.xpath(excel.getData(0, 232, 1 ))).click();
		//Enter the required role
		driver.findElement(By.xpath(excel.getData(0, 233, 1 ))).sendKeys("Driver");
		//Press the Enter buttom
		driver.findElement(By.xpath(excel.getData(0, 233, 1 ))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Clear the Discount name
		//driver.findElement(By.name("discountCode")).clear();
		//Enter the Required name
		//driver.findElement(By.name("discountCode")).sendKeys(Utility.getProperty("ItemBased_Discount_Code"));
		
		Thread.sleep(2000);
		//Enable or Disable the Attach Customer Option
		driver.findElement(By.name(excel.getData(0, 235, 3))).click();
		
		Thread.sleep(2000);
		//Click the required display button
		driver.findElement(By.xpath(excel.getData(0, 236, 1))).click();
		
		Thread.sleep(2000);
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(0, 171, 1))).click();
		Thread.sleep(3000);driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Thread.sleep(2000);
		//Check whether the new discount saved or not
		if(driver.findElement(By.xpath(excel.getData(0, 39, 1))).getText().equalsIgnoreCase("Saved successfully"))
		{
			test.log(LogStatus.PASS, "New Item Based discount saved Successfully for Days of Week");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Item Based discount saved Failed for Days of Week");
		}

		Thread.sleep(5000);wb.close();
	}
	
	@Test(priority=43, enabled = false)
	public void Item_Based_Discount_method_pagination(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

		
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
		Thread.sleep(1000);
		
		Thread.sleep(2000);
		//Click the Pagination option as 10
		driver.findElement(By.xpath(excel.getData(0, 207, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Item Based Discount");
		//Create the web element for Display Group Table
		List<WebElement> results = driver.findElements(By.xpath(excel.getData(0, 210, 1)));
		for (WebElement result : results){						
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(0, 42, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" available(Discount Item Based) When click the Pagination option as 10");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 15
		driver.findElement(By.xpath(excel.getData(0, 208, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Item Based Discount");
		//Create the web element for Display Group Table
		List<WebElement> results1 = driver.findElements(By.xpath(excel.getData(0, 210, 1)));
		for (WebElement result : results1){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(0, 42, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" available(Discount Item Based) When click the Pagination option as 15");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 20
		driver.findElement(By.xpath(excel.getData(0, 209, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for Item Based Discount");
		//Create the web element for Display Group Table
		List<WebElement> results2 = driver.findElements(By.xpath(excel.getData(0, 210, 1)));
		for (WebElement result : results2){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(0, 42, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" available(Discount Item Based) When click the Pagination option as 20");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 5
		driver.findElement(By.xpath(excel.getData(0, 206, 1))).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Item Based Discount");
		//Create the web element for Display Group Table
		List<WebElement> results3 = driver.findElements(By.xpath(excel.getData(0, 210, 1)));
		for (WebElement result : results3){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector(excel.getData(0, 42, 4)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" available(Discount Item Based) When click the Pagination option as 5");
		}
		Thread.sleep(8000);wb.close();
	}
	
	@Test(enabled=false,priority=45)
	public void Item_Based_Discount_method_editDiscount_ItemBased(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"1");
		Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
		
		Thread.sleep(5000);
		//Click the Edit icon
		driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();
		
		Thread.sleep(3000);
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"10");

		
		Thread.sleep(2000);
		//Click the Level Field
		driver.findElement(By.xpath(excel.getData(3, 2168, 1))).click();
		//Enter the Level Option
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKeys("Category");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(60000);
		//Click the Category Field
		driver.findElement(By.xpath(excel.getData(3, 2176, 1))).click();
		//Enter the required Category
		driver.findElement(By.xpath(excel.getData(3, 2177, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter key
		driver.findElement(By.xpath(excel.getData(3, 2177, 1))).sendKeys(Keys.ENTER);	
		Thread.sleep(2000);
		
		Thread.sleep(2000);
		//Click the Category Field
		driver.findElement(By.xpath(excel.getData(3, 2176, 1))).click();
		//Enter the required Category
		driver.findElement(By.xpath(excel.getData(3, 2177, 1))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath(excel.getData(3, 2177, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter key
		driver.findElement(By.xpath(excel.getData(3, 2177, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
				
		Thread.sleep(2000);
		//Enable or Disable the Serving Size Level check box
		driver.findElement(By.xpath(excel.getData(3, 2172, 1))).click();
		
		Thread.sleep(1000);
		//Remove the Serving Size level
		//Get the number of Serving Sizes
		List<WebElement> ssize = driver.findElements(By.xpath(excel.getData(0, 237, 1)));
		for(int i = 1; i < ssize.size();i++)
		{
			//Click the Close button of Serving Sizes
			driver.findElement(By.xpath(excel.getData(0, 237, 1))).click();
		}
		
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)", "");
		
		Thread.sleep(2000);
		//Click the Discount Option Button
		driver.findElement(By.xpath(excel.getData(0, 227, 1))).click();
		//Choose the Option
		driver.findElement(By.xpath(excel.getData(0, 238, 1))).click();
		
		Thread.sleep(2000);
		//Clear the Offer Filed
		driver.findElement(By.name(excel.getData(0, 229, 3))).clear();
		//Enter the required offer
		driver.findElement(By.name(excel.getData(0, 229, 3))).sendKeys("1000");
		
		Thread.sleep(2000);
		//Enable or Disable the Include Additional Modifier Option
		driver.findElement(By.xpath(excel.getData(0, 234, 1))).click();

		Thread.sleep(3000);
		//Click the update
		driver.findElement(By.xpath(excel.getData(0, 186, 1))).click();
		
		Thread.sleep(3000);driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.xpath(excel.getData(0, 39, 1))).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is updated Failed");
		}

		Thread.sleep(15000);
		
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"101");Thread.sleep(5000);
		Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);

		Thread.sleep(3000);
		//Click the Edit icon
		driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();
		Thread.sleep(3000);
		
		//Check weather name changes are updated or not
		if(driver.getPageSource().contains(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"10"))
		{
			test.log(LogStatus.PASS, "Edited name is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Edits name is updated Failed");
		}
		Thread.sleep(5000);
		
		//Click the Cancel button
		driver.findElement(By.xpath(excel.getData(0, 130, 1))).click();wb.close();
	}
	
	@Test(enabled=false,priority=46)
	public void Item_Based_Discount_method_deleteDiscount_ItemBased(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
		
		Thread.sleep(3000);
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"101");
		Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
		
		Thread.sleep(5000);
		//Click the Delete button
		driver.findElement(By.cssSelector(excel.getData(0, 42, 4))).click();
		
		Thread.sleep(3000);
		//Click the Yes button in the popup
		driver.findElement(By.cssSelector(excel.getData(0, 43, 4))).click();
		
		
		//Thread.sleep(3000);
		WebElement ele=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,30);
		//Check the item deleted or not
		if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Discount Inactivated Successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is deleted Failed");
		}

		Thread.sleep(5000);
		
		//Click the Active or Inactive button
		driver.findElement(By.xpath(excel.getData(0, 44, 1))).click();
		
		Thread.sleep(2000);
		//Click the success button
		driver.findElement(By.cssSelector(excel.getData(0, 46, 4))).click();
		Thread.sleep(1000);
		
		//Click the Yes button in the popup
				driver.findElement(By.cssSelector(excel.getData(0, 43, 4))).click();
				Thread.sleep(3000);

		//Check the item activated or not
		if(driver.findElement(By.xpath(excel.getData(0, 39, 1))).getText().equalsIgnoreCase("Discount activated Successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is activated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is activated Failed");
		}
	
		Thread.sleep(5000);wb.close();
	}

	@Test(enabled=false,priority=47)
	public void Item_Based_Discount_method_cancelButton(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id(excel.getData(0, 214, 2))).click();
		Thread.sleep(4000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(0, 215, 1))).getText().equalsIgnoreCase("Discounts"))
		{
			test.log(LogStatus.PASS, "New Discounts Item form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Discounts Item form loaded Failed");
		}

		Thread.sleep(3000);
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount"));
		Thread.sleep(5000);
		
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath(excel.getData(0, 130, 1)));
		//Scroll the page till the Cancel option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
		
		Thread.sleep(2000);
		//Click the Cancel button
		driver.findElement(By.xpath(excel.getData(0, 130, 1))).click();
		Thread.sleep(3000);
		//Check whether the new discount saved or not
		if(driver.findElement(By.id(excel.getData(0, 214 , 2))).isDisplayed())
		{
			test.log(LogStatus.PASS, "New Item Based discount canceled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Item Based discount canceled Failed");
		}

		Thread.sleep(8000);wb.close();
	}
	
	@Test(enabled=false,priority=48)
	public void Item_Based_Discount_method_editDiscount_ItemBased_ApplicableTimePeriodAsAlways_SaveAndPublish(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"101");
		Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
		
		Thread.sleep(5000);
		//Click the Edit icon
		driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();
		
		Thread.sleep(3000);
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"20");

		Thread.sleep(2000);
		//Click the Coupon Only Option
		driver.findElement(By.xpath(excel.getData(0, 217 , 1))).click();
		Thread.sleep(2000);
		
		//Click the Auto discount option
		driver.findElement(By.xpath(excel.getData(0, 218 , 1))).click();
		Thread.sleep(3000);

		//Click the Level Field
		driver.findElement(By.xpath(excel.getData(3, 2168, 1))).click();
		//Enter the required Level
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKeys("Sub category");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(60000);
		//Click the Menu Item Field
		driver.findElement(By.xpath(excel.getData(3, 2176, 1))).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath(excel.getData(3, 2177, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter key
		driver.findElement(By.xpath(excel.getData(3, 2177, 1))).sendKeys(Keys.ENTER);	
		Thread.sleep(2000);
		
		//Clear the minimum Quantity field
		driver.findElement(By.name(excel.getData(0, 225, 3))).clear();
		//Enter the minimum Quantity
		driver.findElement(By.name(excel.getData(0, 225, 3))).sendKeys("2");
		
		Thread.sleep(2000);		
		//Click the Discount Field DropDown Option
		driver.findElement(By.xpath(excel.getData(0, 227, 1))).click();
		//Click the Free Item Field
		driver.findElement(By.xpath(excel.getData(0, 243,1))).click();

		Thread.sleep(2000);
		//Click the Add free item option
		driver.findElement(By.xpath(excel.getData(0, 244,1))).click();
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
		
		Thread.sleep(5000);
		//Click the Menu Item option
		driver.findElement(By.xpath(excel.getData(0, 245,1))).click();
		//Press the enter button
		driver.findElement(By.xpath(excel.getData(0, 246,1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(8000);
		//Click the Serving Size Level option
		driver.findElement(By.xpath(excel.getData(0, 247,1))).click();
		//Enter the required serving size level
		//driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[5]/div/div[4]/div[2]/div[1]/div/ng-form/div[2]/div/div/div/div/input")).sendKey("EACH");
		//Press the enter button
		driver.findElement(By.xpath(excel.getData(0, 248,1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the quantity of free item
		driver.findElement(By.name(excel.getData(0, 249,3))).clear();
		//Enter the quantity
		driver.findElement(By.name(excel.getData(0, 249,3))).sendKeys("2");
		
		Thread.sleep(1000);
		//Clear the price of free quantity
		driver.findElement(By.name(excel.getData(0, 250,3))).clear();
		//Enter the price
		driver.findElement(By.name(excel.getData(0, 250,3))).sendKeys("225");
		
		Thread.sleep(2000);
		//Click the Add button
		//driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[5]/div/div[4]/div[2]/div[1]/div/ng-form/div[6]/a")).click();
		
		Thread.sleep(2000);
		//Click the Add free item option
		driver.findElement(By.xpath(excel.getData(0, 251,1))).click();
		
		Thread.sleep(3000);
		//Click the Add free item option
		driver.findElement(By.xpath(excel.getData(0, 244,1))).click();
		
		Thread.sleep(4000);
		//Click the Menu Item option
		driver.findElement(By.xpath(excel.getData(0, 245,1))).click();
		//Enter the required menu Item
		driver.findElement(By.xpath(excel.getData(0, 246,1))).sendKeys(Keys.ARROW_DOWN);
		//Press the enter button
		driver.findElement(By.xpath(excel.getData(0, 246,1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(12000);
		//Click the Serving Size Level option
		driver.findElement(By.xpath(excel.getData(0, 247,1))).click();
		//Enter the required serving size level
		driver.findElement(By.xpath(excel.getData(0, 248,1))).sendKeys(Keys.ARROW_DOWN);
		//Press the enter button
		driver.findElement(By.xpath(excel.getData(0, 248,1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Clear the quantity of free item
		driver.findElement(By.name(excel.getData(0, 249,3))).clear();
		//Enter the quantity
		driver.findElement(By.name(excel.getData(0, 249,3))).sendKeys("2");
		
		Thread.sleep(1000);
		//Clear the price of free quantity
		driver.findElement(By.name(excel.getData(0, 250,3))).clear();
		//Enter the price
		driver.findElement(By.name(excel.getData(0, 250,3))).sendKeys("225");
		
		Thread.sleep(2000);
		//Click the Add button
		driver.findElement(By.xpath(excel.getData(0, 251,1))).click();
		
		 	WebElement element = driver.findElement(By.xpath(excel.getData(0, 252,1)));
		 
	        Actions action = new Actions(driver);
	 
	        action.moveToElement(element).moveToElement(driver.findElement(By.xpath(excel.getData(0, 252,1)))).click().build().perform();
		
			
		Thread.sleep(2000);
		//Click the Yes button in the popup
		Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(0, 43, 4))).click();
		
		Thread.sleep(3000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_UP);
		
		Thread.sleep(5000);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath(excel.getData(3, 2174, 1))).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath(excel.getData(3, 2175, 1))).sendKeys("Always");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath(excel.getData(3, 2175, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		Select applyDiscount = new Select(driver.findElement(By.name(excel.getData(0, 230, 3 ))));
		applyDiscount.selectByVisibleText("After Tax");

		Thread.sleep(1000);
		//Clear the priority field
		driver.findElement(By.name(excel.getData(0, 231, 3 ))).clear();
		//Enter the priority
		driver.findElement(By.name(excel.getData(0, 231, 3 ))).sendKeys("4");
		
		Thread.sleep(3000);
		//Clear the role field
		//driver.findElement(By.xpath("//input[@value='Select roles']")).clear();Thread.sleep(2000);
		//Enter the required role
		//driver.findElement(By.xpath("//input[@value='Select roles']")).sendKeys("Server");
		Thread.sleep(3000);
		//Press the Enter Key
		//driver.findElement(By.xpath("//input[@value='Select roles']")).sendKeys(Keys.ENTER);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);

		//Select the Color from the list
		//driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[2]/div[7]/div/div/div[2]/div/div[8]")).click();
			
		//form[@name='discountForm']/div[2]/div[2]/div[6]/div/div/div[2]/div/div[8]
		Thread.sleep(3000);
		//Click the Save and Publish button
		driver.findElement(By.xpath(excel.getData(0, 57,1))).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 150);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(excel.getData(0, 39,1))));
		
//		Thread.sleep(7000);driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String s1 = driver.findElement(By.xpath(excel.getData(0, 39,1))).getText();Thread.sleep(3000);
		System.out.println("Required String  is : "+s1);
		//Check whether the Product Item updated successfully or not
		if(s1.equalsIgnoreCase("Updated and published successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is updated and published Successfully for Time Period as Always");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is updated and published Failed for Time Priod as Always");
		}

		Thread.sleep(5000);wb.close();
	}
	
	@Test(enabled=false,priority=49)
	public void Item_Based_Discount_method_editDiscount_ItemBased_ApplicableTimePeriodAsDaysOfMonth(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(5000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"201");
		Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
		
		Thread.sleep(5000);
		//Click the Edit icon
		driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();
		Thread.sleep(3000);
		
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"21");
		
		Thread.sleep(5000);
		
		//Click the Coupon Only Option
		driver.findElement(By.xpath(excel.getData(0, 217 , 1))).click();
		Thread.sleep(2000);
		
		//Click the Auto discount option
		driver.findElement(By.xpath(excel.getData(0, 218 , 1))).click();
		Thread.sleep(3000);
		
		//Clear the Coupon Code
		driver.findElement(By.xpath(excel.getData(0, 219, 1))).clear();
		//Enter the Coupon Code
		driver.findElement(By.xpath(excel.getData(0, 219, 1))).sendKeys(Utility.getProperty("ItemBased_Discount_Code")+"a");

		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		
		
/*		//Click the Level Field
		driver.findElement(By.xpath(excel.getData(3, 2168, 1))).click();
		//Enter the required Level
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKey("Menu item");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKey(Keys.ENTER);
		
		//Click the Menu Item Field
		driver.findElement(By.xpath(excel.getData(3, 2176, 1))).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath(excel.getData(3, 2177, 1))).sendKey("COKE");
		//Press the Enter key
		driver.findElement(By.xpath(excel.getData(3, 2177, 1))).sendKey(Keys.ENTER);	*/
		Thread.sleep(2000);
		
		//Clear the minimum Quantity field
		driver.findElement(By.name(excel.getData(0, 225, 3))).clear();
		//Enter the minimum Quantity
		driver.findElement(By.name(excel.getData(0, 225, 3))).sendKeys("5");
		
		Thread.sleep(3000);
		//Click the Discount Field DropDown Option
		driver.findElement(By.xpath(excel.getData(0, 227, 1))).click();
		//Click the Free Item Field
		driver.findElement(By.xpath(excel.getData(0, 253,1))).click();
		//Clear the Offer field
		driver.findElement(By.name(excel.getData(0, 229, 3))).clear();
		//Enter the Offer
		driver.findElement(By.name(excel.getData(0, 229, 3))).sendKeys("250");
		
		Thread.sleep(3000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_UP);
		
		Thread.sleep(3000);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath(excel.getData(3, 2174, 1))).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath(excel.getData(3, 2175, 1))).sendKeys("Days of Month");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath(excel.getData(3, 2175, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Required Days of a Month
		driver.findElement(By.xpath(excel.getData(0, 181,1))).click();
		
		//Enable or Disable the Restriction months
		driver.findElement(By.xpath(excel.getData(0, 182,1))).click();
		
		Thread.sleep(2000);
		//Click the Months Option
		driver.findElement(By.xpath(excel.getData(0, 183,1))).click();
		//Enter the Required Month
		driver.findElement(By.xpath(excel.getData(0, 184,1))).sendKeys("MAY");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(0, 184,1))).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		//Enable or Disable theRestriction Time
		driver.findElement(By.xpath(excel.getData(0, 178,1))).click();
		
		Thread.sleep(1000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath(excel.getData(0, 179,1))).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath(excel.getData(0, 180,1))).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath(excel.getData(0, 179,1))).click();
		}
		
		Thread.sleep(2000);
		Select applyDiscount = new Select(driver.findElement(By.name(excel.getData(0, 230, 3 ))));
		applyDiscount.selectByVisibleText("Before Tax");

		Thread.sleep(2000);
		//Clear the priority field
		driver.findElement(By.name(excel.getData(0, 231, 3 ))).clear();
		//Enter the priority
		driver.findElement(By.name(excel.getData(0, 231, 3 ))).sendKeys("3");
		
		Thread.sleep(2000);
		//Enable or Disable the Attach Customer
		driver.findElement(By.name(excel.getData(0, 235, 3))).click();
		
		Thread.sleep(2000);
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(0, 186, 1))).click();
		
		Thread.sleep(3000);driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.xpath(excel.getData(0, 39, 1))).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is updated Successfully for Time Period as Days of Month");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is updated Failed for Time Priod as Days Of Month");
		}

		Thread.sleep(5000);wb.close();
	}
	
	@Test(enabled=false,priority=50)
	public void Item_Based_Discount_method_editDiscount_ItemBased_ApplicableTimePeriodAsDateRange(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(5000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"211");
		Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
		
		Thread.sleep(5000);
		//Click the Edit icon
		driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();
		Thread.sleep(3000);

		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"22");
		Thread.sleep(5000);
		
		//Click the Coupon Only Option
		driver.findElement(By.xpath(excel.getData(0, 217 , 1))).click();
		Thread.sleep(2000);
		
		//Click the Auto discount option
		driver.findElement(By.xpath(excel.getData(0, 218 , 1))).click();
		Thread.sleep(3000);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		
		
/*		//Click the Level Field
		driver.findElement(By.xpath(excel.getData(3, 2168, 1))).click();
		//Enter the required Level
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKey("Menu item");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKey(Keys.ENTER);
		
		//Click the Menu Item Field
		driver.findElement(By.xpath(excel.getData(3, 2176, 1))).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath(excel.getData(3, 2177, 1))).sendKey("COKE");
		//Press the Enter key
		driver.findElement(By.xpath(excel.getData(3, 2177, 1))).sendKey(Keys.ENTER);	*/
		Thread.sleep(2000);
		Thread.sleep(1000);
		//Clear the minimum Quantity field
		driver.findElement(By.name(excel.getData(0, 225, 3))).clear();
		//Enter the minimum Quantity
		driver.findElement(By.name(excel.getData(0, 225, 3))).sendKeys("5");
		Thread.sleep(1000);
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(2000);Thread.sleep(1000);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath(excel.getData(3, 2174, 1))).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath(excel.getData(3, 2175, 1))).sendKeys("Date Range");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath(excel.getData(3, 2175, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Clear the From date field
		driver.findElement(By.name(excel.getData(0, 123,3))).clear();
		//Enter the required from Date
		driver.findElement(By.name(excel.getData(0, 123,3))).sendKeys("25-May-2040");
		//Clear the To Date field
		driver.findElement(By.name(excel.getData(0, 124,3))).clear();
		//Enter the required To Date
		driver.findElement(By.name(excel.getData(0, 124,3))).sendKeys("28-May-2040");
		
		Thread.sleep(2000);
		//Enable or Disable the Restriction Days field
		driver.findElement(By.xpath(excel.getData(0, 185,1))).click();
		
		//Click the Days of a Week field
		driver.findElement(By.xpath(excel.getData(0, 176,1))).click();
		//Enter the Required Day to the Days of a Week field
		driver.findElement(By.xpath(excel.getData(0, 177,1))).sendKeys("FRIDAY");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(0, 177,1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Enable or Disable the restriction time period option
		driver.findElement(By.xpath(excel.getData(0, 178,1))).click();
		
		Thread.sleep(1000);Thread.sleep(1000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath(excel.getData(0, 179,1))).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath(excel.getData(0, 180,1))).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath(excel.getData(0, 179,1))).click();
		}
		Thread.sleep(1000);
		Select applyDiscount = new Select(driver.findElement(By.name(excel.getData(0, 230, 3 ))));
		applyDiscount.selectByVisibleText("Before Tax");

		Thread.sleep(1000);Thread.sleep(1000);
		//Clear the priority field
		driver.findElement(By.name(excel.getData(0, 231, 3 ))).clear();
		//Enter the priority
		driver.findElement(By.name(excel.getData(0, 231, 3 ))).sendKeys("3");
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(0, 186, 1))).click();
		
		Thread.sleep(3000);driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.xpath(excel.getData(0, 39, 1))).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is updated Successfully for Time Period as Date Range");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is updated Failed for Time Priod as Date Range");
		}

		Thread.sleep(5000);wb.close();
	}
	
	@Test(enabled=false,priority=51)
	public void Item_Based_Discount_method_editDiscount_ItemBased_ApplicableTimePeriodAsSpecificDate(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(5000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"221");
		Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
		
		Thread.sleep(5000);
		//Click the Edit icon
		driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();
		Thread.sleep(3000);
		
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"23");
		Thread.sleep(5000);
		
		//Click the Coupon Only Option
		driver.findElement(By.xpath(excel.getData(0, 217 , 1))).click();
		Thread.sleep(2000);
		
		//Click the Auto discount option
		driver.findElement(By.xpath(excel.getData(0, 218 , 1))).click();
		Thread.sleep(3000);
		
		//Clear the Coupon Code
		driver.findElement(By.xpath(excel.getData(0, 219, 1))).clear();
		//Enter the Coupon Code
		driver.findElement(By.xpath(excel.getData(0, 219, 1))).sendKeys(Utility.getProperty("ItemBased_Discount_Code")+"b");

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		
		
/*		//Click the Level Field
		driver.findElement(By.xpath(excel.getData(3, 2168, 1))).click();
		//Enter the required Level
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKey("Menu item");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKey(Keys.ENTER);
		
		//Click the Menu Item Field
		driver.findElement(By.xpath(excel.getData(3, 2176, 1))).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath(excel.getData(3, 2177, 1))).sendKey("COKE");
		//Press the Enter key
		driver.findElement(By.xpath(excel.getData(3, 2177, 1))).sendKey(Keys.ENTER);	*/
		Thread.sleep(2000);
		Thread.sleep(1000);
		//Clear the minimum Quantity field
		driver.findElement(By.name(excel.getData(0, 225, 3))).clear();
		//Enter the minimum Quantity
		driver.findElement(By.name(excel.getData(0, 225, 3))).sendKeys("5");
		
		Thread.sleep(1000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(2000);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath(excel.getData(3, 2174, 1))).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath(excel.getData(3, 2175, 1))).sendKeys("Specific date");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath(excel.getData(3, 2175, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Clear the Date field
		driver.findElement(By.name(excel.getData(0, 129,3))).clear();
		//Enter the required date
		driver.findElement(By.name(excel.getData(0, 129,3))).sendKeys("18-May-2040");
		
		Thread.sleep(2000);
		//Enable or Disable the restriction time period option
		driver.findElement(By.xpath(excel.getData(0, 178,1))).click();
		
		Thread.sleep(1000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath(excel.getData(0, 179, 1 ))).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath(excel.getData(0, 180, 1 ))).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath(excel.getData(0, 179, 1 ))).click();
		}
		Thread.sleep(1000);
		Select applyDiscount = new Select(driver.findElement(By.name(excel.getData(0, 230, 3 ))));
		applyDiscount.selectByVisibleText("Before Tax");

		Thread.sleep(1000);Thread.sleep(1000);
		//Clear the priority field
		driver.findElement(By.name(excel.getData(0, 231, 3 ))).clear();
		//Enter the priority
		driver.findElement(By.name(excel.getData(0, 231, 3 ))).sendKeys("3");
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(0, 186, 1))).click();
		
		Thread.sleep(3000);driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.xpath(excel.getData(0, 39, 1))).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is updated Successfully for Time Period as Specific Date");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is updated Failed for Time Priod as Specific Date");
		}

		Thread.sleep(5000);wb.close();
	}
	
	@Test(enabled=false,priority=52)
	public void Item_Based_Discount_method_editDiscount_ItemBased_ApplicableTimePeriodAs_StartDateAndEndDate(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(5000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"231");
		Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(0, 40, 1))).sendKeys(Keys.BACK_SPACE);
		
		Thread.sleep(5000);
		//Click the Edit icon
		driver.findElement(By.cssSelector(excel.getData(0, 41, 4))).click();
		Thread.sleep(3000);

		
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"24");
		Thread.sleep(5000);
		
		//Click the Coupon Only Option
		driver.findElement(By.xpath(excel.getData(0, 217 , 1))).click();
		Thread.sleep(2000);
		
		//Click the Auto discount option
		driver.findElement(By.xpath(excel.getData(0, 218 , 1))).click();
		Thread.sleep(3000);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		
		
/*		//Click the Level Field
		driver.findElement(By.xpath(excel.getData(3, 2168, 1))).click();
		//Enter the required Level
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKey("Menu item");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKey(Keys.ENTER);
		
		//Click the Menu Item Field
		driver.findElement(By.xpath(excel.getData(3, 2176, 1))).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath(excel.getData(3, 2177, 1))).sendKey("COKE");
		//Press the Enter key
		driver.findElement(By.xpath(excel.getData(3, 2177, 1))).sendKey(Keys.ENTER);*/	
		Thread.sleep(2000);
		Thread.sleep(1000);
		//Clear the minimum Quantity field
		driver.findElement(By.name(excel.getData(0, 225, 3))).clear();
		//Enter the minimum Quantity
		driver.findElement(By.name(excel.getData(0, 225, 3))).sendKeys("5");
		
		
		Thread.sleep(2000);
		//Click the Discount Field DropDown Option
		driver.findElement(By.xpath(excel.getData(0, 227, 1))).click();
		//Click the Percentage Field
		driver.findElement(By.xpath(excel.getData(0, 238,1))).click();
		//Clear the percentage option
		driver.findElement(By.name(excel.getData(0, 229, 3))).clear();
		//Enter the percentage
		driver.findElement(By.name(excel.getData(0, 229, 3))).sendKeys("525");
		
		Thread.sleep(1000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(2000);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath(excel.getData(3, 2174, 1))).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath(excel.getData(3, 2175, 1))).sendKeys("Start date time & end date time");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath(excel.getData(3, 2175, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Clear the From date field
		driver.findElement(By.name(excel.getData(0, 123,3))).clear();
		//Enter the required from Date
		driver.findElement(By.name(excel.getData(0, 123,3))).sendKeys("25-May-2040");
		//Clear the To Date field
		driver.findElement(By.name(excel.getData(0, 124,3))).clear();
		//Enter the required To Date
		driver.findElement(By.name(excel.getData(0, 124,3))).sendKeys("28-May-2040");

		Thread.sleep(2000);
		//Enable or Disable the Restriction Days option
		driver.findElement(By.xpath(excel.getData(0, 185,1))).click();
		
		Thread.sleep(1000);
		//Click the Days of a week option
		driver.findElement(By.xpath(excel.getData(0, 176,1))).click();
		//Enter the required day
		driver.findElement(By.xpath(excel.getData(0, 177,1))).sendKeys("FRIDAY");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(0, 177,1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath(excel.getData(0, 179,1))).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath(excel.getData(0, 180,1))).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath(excel.getData(0, 179,1))).click();
		}
		
		Select applyDiscount = new Select(driver.findElement(By.name(excel.getData(0, 230, 3 ))));
		applyDiscount.selectByVisibleText("After Tax");

		Thread.sleep(2000);
		//Enable or Disable the Include Additional Modifiers
		driver.findElement(By.xpath(excel.getData(0, 234,1))).click();
		
		Thread.sleep(1000);
		//Clear the priority field
		driver.findElement(By.name(excel.getData(0, 231, 3 ))).clear();
		//Enter the priority
		driver.findElement(By.name(excel.getData(0, 231, 3 ))).sendKeys("3");
		Thread.sleep(1000);
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(0, 57,1))).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(excel.getData(0, 39,1))));

		//Thread.sleep(7000);driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String s1 = driver.findElement(By.xpath(excel.getData(0, 39,1))).getText();Thread.sleep(3000);
		System.out.println("Required String  is : "+s1);
		//Check whether the Product Item updated successfully or not
		if(s1.equalsIgnoreCase("Updated and published successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is update and published Successfully for Time Period as Strat Date and End Date");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is update and published Failed for Time Priod as Start Date and End Date");
		}

		Thread.sleep(5000);wb.close();
	}
	
	@Test(enabled=false,priority=53)
	public void Item_Based_Discount_method_verifyActive_InActiveButton(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(10000);
		//Click Active or In Active Button
		driver.findElement(By.xpath(excel.getData(0, 44,1))).click();

		Thread.sleep(2000);
		try
		{
		//Check the New Display Group form is loaded or not
		if(driver.findElement(By.cssSelector(excel.getData(0, 46, 4))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Deleted groups are here, we are in InActive Page");
				Thread.sleep(10000);
				//Click the Reactivate button(Green)
				driver.findElement(By.cssSelector(excel.getData(0, 46, 4))).click();
				Thread.sleep(1000);
				Thread.sleep(1000);
				//Click the Yes button in the popup
				Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(0, 43, 4))).click();
	
				Thread.sleep(2000);
				
				//Check weather the Group is activate or not
				if(driver.findElement(By.xpath(excel.getData(0, 39,1))).getText().equalsIgnoreCase("Discount activated Successfully"))
				{
					test.log(LogStatus.PASS, "Discount is activated successfully");
					//Click Active or In Active Button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div/div/div[2]/div[1]/div[1]/input")).click();
	
				}
				else
				{
					test.log(LogStatus.FAIL, "Discount is activated Failed");
				}
			}
		}
		catch(Exception e)
		{
		if(driver.findElement(By.cssSelector(excel.getData(0, 42, 4))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Deleted groups are not here, we are in Active Page");
				//Click Active or In Active Button
				driver.findElement(By.xpath(excel.getData(0, 44,1))).click();
				Thread.sleep(10000);
				//Click the Reactivate button(Green)
				driver.findElement(By.cssSelector(excel.getData(0, 46, 4))).click();
				Thread.sleep(1000);
				//Click the Yes button in the popup
				Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(0, 43, 4))).click();
				Thread.sleep(3000);
	
				//Check weather the Group is activate or not
				if(driver.findElement(By.xpath(excel.getData(0, 39,1))).getText().equalsIgnoreCase("Discount activated Successfully"))
				{
					test.log(LogStatus.PASS, "Discount is activated successfully");
					//Click Active or In Active Button
					driver.findElement(By.xpath(excel.getData(0, 44,1))).click();
	
				}
				else
				{
					test.log(LogStatus.FAIL, "Discount is activated Failed");
				}
				
			}Thread.sleep(4000);
		}wb.close();
	}
	
	@Test(enabled=false,priority=54)
	public void Item_Based_Discount_method_addSameName(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id(excel.getData(0, 214, 2))).click();
				
		Thread.sleep(5000);
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).clear();
		Thread.sleep(1000);
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(0, 216, 1))).sendKeys(Utility.getProperty("DiscountName_ItemBased_AutoDiscount")+"24");
		Thread.sleep(5000);
		
		//Click the Coupon Only Option
		driver.findElement(By.xpath(excel.getData(0, 217 , 1))).click();
		Thread.sleep(2000);
		
		//Click the Auto discount option
		driver.findElement(By.xpath(excel.getData(0, 218 , 1))).click();
		Thread.sleep(3000);
		
		//Clear the Coupon Code
		driver.findElement(By.xpath(excel.getData(0, 219, 1))).clear();
		//Enter the Coupon Code
		driver.findElement(By.xpath(excel.getData(0, 219, 1))).sendKeys(Utility.getProperty("ItemBased_Discount_Code")+"c");
		
		Thread.sleep(1000);
		//Click the Level Field
		driver.findElement(By.xpath(excel.getData(3, 2168, 1))).click();
		//Enter the required Level
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKeys("Menu item");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 2169, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);Thread.sleep(1000);
		//Click the Menu Item Field
		driver.findElement(By.xpath(excel.getData(3, 2170, 1))).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath(excel.getData(3, 2171, 1))).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter key
		driver.findElement(By.xpath(excel.getData(3, 2171, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		//Clear the minimum Quantity field
		driver.findElement(By.name(excel.getData(0, 225, 3))).clear();
		//Enter the minimum Quantity
		driver.findElement(By.name(excel.getData(0, 225, 3))).sendKeys("5");
		
		Thread.sleep(2000);
		//Click the Discount Field DropDown Option
		driver.findElement(By.name(excel.getData(0, 229,3))).clear();
		//Click the Free Item Field
		driver.findElement(By.name(excel.getData(0, 229,3))).sendKeys("1500");
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);Thread.sleep(1000);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath(excel.getData(3, 2174, 1))).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath(excel.getData(3, 2175, 1))).sendKeys("Days of Week");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath(excel.getData(3, 2175, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);Thread.sleep(1000);
		//Click the Days if Week Option
		driver.findElement(By.xpath(excel.getData(0, 176, 1 ))).click();
		//Enter the required DAY for Searching
		driver.findElement(By.xpath(excel.getData(0, 177, 1 ))).sendKeys("WEDNESDAY");
		//Press the Enter key after enter the keyword
		driver.findElement(By.xpath(excel.getData(0, 177, 1 ))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		Select applyDiscount = new Select(driver.findElement(By.name(excel.getData(0, 230, 3 ))));
		applyDiscount.selectByVisibleText("Before Tax");

		Thread.sleep(1000);
		//Clear the priority field
		driver.findElement(By.name(excel.getData(0, 231, 3 ))).clear();
		//Enter the priority
		driver.findElement(By.name(excel.getData(0, 231, 3 ))).sendKeys("3");
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(0, 171, 1))).click();
		Thread.sleep(3000);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Check the New Display Group saved or not
		if(driver.findElement(By.xpath(excel.getData(0, 39, 1))).getText().equalsIgnoreCase("Validation Error(s)"))
		{
			test.log(LogStatus.PASS, "New Discount added Fail, User cannot be able to give the same group name");
			Thread.sleep(3000);
			//Find the validation message for the name field
			if(driver.findElement(By.xpath("//p[@class='help-block ng-binding']")).getText().equalsIgnoreCase("Name already exists"))
			{
				test.log(LogStatus.PASS, "Discount name is already exist");
			}

			
		}
		else
		{
			test.log(LogStatus.FAIL, "New Discount added success, User can be able to give the same group name");
		}

		Thread.sleep(5000);
		wb.close();
	}

}
