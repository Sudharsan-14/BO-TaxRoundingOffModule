package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
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


public class EditDelete_Discount_OpenItemAmount {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("EditDelete_Discount_OpenItemAmount");

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
			Open_Item_Discount_method_editDiscount_OpenItem(driver);
			Open_Item_Discount_method_deleteOrActiveDiscount_OpenItem(driver);
			Open_Item_Discount_method_cancelButton(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=81)
	public void Open_Item_Discount_method_editDiscount_OpenItem(WebDriver driver) throws Exception
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
		//Scroll the page till the Reason option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Discounts Option		
		driver.findElement(By.xpath("//span[.='Discounts']")).click();*/
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"open_item/openDiscount");
		Thread.sleep(8000);
		try
		{
		//Check Check based discount page opened or not
		if(driver.findElement(By.xpath(excel.getData(0, 215, 1))).getText().equalsIgnoreCase("Discounts"))
		{
			test.log(LogStatus.PASS, "Open Item page loaded Successfully");
			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Open Item page loaded Failed");
		
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
		Thread.sleep(8000);
		//Click edit button
		driver.findElement(By.id(excel.getData(0,254,2))).click();Thread.sleep(3000);
		//Clear the name field
		driver.findElement(By.xpath(excel.getData(0,216,1))).clear();
		//Enter the required name
		driver.findElement(By.xpath(excel.getData(0,216,1))).sendKeys(Utility.getProperty("DiscountName_OpenItemAMT"));
		
		//Enable or Disable the Coupon only option
		driver.findElement(By.xpath(excel.getData(0,217,1))).click();
		Thread.sleep(2000);
		
		if(driver.findElement(By.name(excel.getData(0,255,3))).isSelected())
		{
			Thread.sleep(4000);
			//Clear the minimum percentage option
			driver.findElement(By.name(excel.getData(0,256,3))).clear();
			//Enter the required minimum value in minimum percentage
			driver.findElement(By.name(excel.getData(0,256,3))).sendKeys("4");
			//Clear the maximum percentage option
			driver.findElement(By.name(excel.getData(0,257,3))).clear();
			//Enter the required maximum value in maximum percentage
			driver.findElement(By.name(excel.getData(0,257,3))).sendKeys("5");

		}
		else
		{
			Thread.sleep(2000);
			//Click the Discount in percentage button
			driver.findElement(By.xpath(excel.getData(0,258,1))).click();
			Thread.sleep(3000);
			//Clear the minimum percentage option
			driver.findElement(By.name(excel.getData(0,256,3))).clear();
			//Enter the required minimum value in minimum percentage
			driver.findElement(By.name(excel.getData(0,256,3))).sendKeys("4");
			//Clear the maximum percentage option
			driver.findElement(By.name(excel.getData(0,257,3))).clear();
			//Enter the required maximum value in maximum percentage
			driver.findElement(By.name(excel.getData(0,257,3))).sendKeys("7");
			Thread.sleep(2000);
		}

		Thread.sleep(2000);
		if(driver.findElement(By.name(excel.getData(0,260,3))).isSelected())
		{
			
			Thread.sleep(3000);
			//Clear the minimum amount option
			driver.findElement(By.name(excel.getData(0,261,3))).clear();
			//Enter the required minimum value in minimum amount
			driver.findElement(By.name(excel.getData(0,261,3))).sendKeys("5.00");
			//Clear the maximum amount option
			driver.findElement(By.name(excel.getData(0,262,3))).clear();
			//Enter the required maximum value in maximum amount
			driver.findElement(By.name(excel.getData(0,262,3))).sendKeys("7.00");

		}
		else
		{
			Thread.sleep(2000);
			//Click the Discount in Amount button
			driver.findElement(By.xpath(excel.getData(0,259,1))).click();
			Thread.sleep(3000);
			//Clear the minimum amount option
			driver.findElement(By.name(excel.getData(0,261,3))).clear();
			//Enter the required minimum value in minimum amount
			driver.findElement(By.name(excel.getData(0,261,3))).sendKeys("4.00");
			//Clear the maximum amount option
			driver.findElement(By.name(excel.getData(0,262,3))).clear();
			//Enter the required maximum value in maximum amount
			driver.findElement(By.name(excel.getData(0,262,3))).sendKeys("5.00");
		}

		Select discType = new Select(driver.findElement(By.name(excel.getData(0,263,3))));
		discType.selectByVisibleText("Donation");
		
		for(int i=1;i<=3;i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		}
		
		//Clear the Safety Limit Percentage text box
		driver.findElement(By.name(excel.getData(0,264,3))).clear();
		//Enter the required percentage
		driver.findElement(By.name(excel.getData(0,264,3))).sendKeys("10");
	
		//Select the any one tax from the give list
		Select applyDiscount = new Select(driver.findElement(By.name(excel.getData(0,265,3))));
		applyDiscount.selectByVisibleText("After Tax");
		
		//Enable or Disable the Include Additional Modifiers
		driver.findElement(By.xpath(excel.getData(0, 234, 1))).click();
		Thread.sleep(3000);
		
		//Clear the priority field
		driver.findElement(By.name(excel.getData(0,231,3))).clear();
		//Enter the priority
		driver.findElement(By.name(excel.getData(0,231,3))).sendKeys("3");
		
		//CLick the Roles Option
		driver.findElement(By.xpath(excel.getData(0,232,1))).click();
		//Enter the required Role
		driver.findElement(By.xpath(excel.getData(0,233,1))).sendKeys("Driver");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(0,233,1))).sendKeys(Keys.ENTER);
		
		//Enable or Disable the isCustomer Attach
		driver.findElement(By.name(excel.getData(0,235,3))).click();
		
		//Clear the Discount Code
		//driver.findElement(By.name("discountCode")).clear();
		//Enter the Discount Code
		//driver.findElement(By.name("discountCode")).sendKeys(Utility.getProperty("OpenItem_Discount_Code"));
		
		//Choose any one color from the given
		driver.findElement(By.xpath(excel.getData(0,236,1))).click(); 	
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(3000);
		//Click the update
		driver.findElement(By.xpath(excel.getData(0,186,1))).click();
		
		//Thread.sleep(2000);
		WebElement ele=driver.findElement(By.xpath(excel.getData(0,39,1)));
		WebDriverWait wait=new WebDriverWait(driver,60);
		
		//Check whether the Product Item updated successfully or not
		if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New discount Open Item is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Open Item is updated Failed");
		}
		Thread.sleep(5000);

		//Click edit button
		driver.findElement(By.id(excel.getData(0,254,2))).click();
		Thread.sleep(3000);
		
		//Check weather name changes are updated or not
		if(driver.getPageSource().contains(Utility.getProperty("DiscountName_OpenItemAMT")))
		{
			test.log(LogStatus.PASS, "Edited name is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Edits name is updated Failed");
		}
		
		Thread.sleep(1000);
		//Check the coupon only is enable or not
		if(driver.findElement(By.xpath(excel.getData(0,217,1))).isEnabled())
		{
			test.log(LogStatus.PASS, "Coupon only field is enabled");
		}
		else
		{
			test.log(LogStatus.PASS, "Coupon only field is disabled");
		}
		Thread.sleep(1000);
		//Check the discount in percentage is enable or not
		if(driver.findElement(By.xpath(excel.getData(0,258,1))).isEnabled())
		{
			test.log(LogStatus.PASS, "Discount in percentage field is enabled");
		}
		else
		{
			test.log(LogStatus.PASS, "Discount in percentage field is disabled");
		}
		Thread.sleep(1000);
		//Check the discount in amount is enable or not
		if(driver.findElement(By.xpath(excel.getData(0,259,1))).isEnabled())
		{
			test.log(LogStatus.PASS, "Discount in amount field is enabled");
		}
		else
		{
			test.log(LogStatus.PASS, "Discount in amount field is disabled");
		}
		
		Thread.sleep(3000);
		//Click the Cancel button
		driver.findElement(By.xpath(excel.getData(0,130,1))).click();
		Thread.sleep(5000);wb.close();
	}
	
	@Test(enabled=false,priority=82)
	public void Open_Item_Discount_method_deleteOrActiveDiscount_OpenItem(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(10000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);Thread.sleep(3000);
				//Click the Active/InActive button
				driver.findElement(By.xpath(excel.getData(0,266,1))).click();
				//Click the Yes button in the popup
				Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(0,43,4))).click();
			
			//	Thread.sleep(3000);
				
				WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
				
				WebDriverWait wait=new WebDriverWait(driver,60);
				
				//Check the menu item deleted/Activated or not
				if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Discount activated Successfully"))
				{
					test.log(LogStatus.PASS, "New discount Open Item is Activated Successfully");
				}
				else
				{
					test.log(LogStatus.PASS, "New discount Open Item is Inactivated Successfully");
				}
				wb.close();
				Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=83)
	public void Open_Item_Discount_method_cancelButton(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(5000);
		//Click edit button
		driver.findElement(By.id(excel.getData(0,254,2))).click();
		//Clear the name field
		driver.findElement(By.xpath(excel.getData(0,216,1))).clear();
		//Enter the required name
		driver.findElement(By.xpath(excel.getData(0,216,1))).sendKeys(Utility.getProperty("DiscountName_OpenItemAMT")+"1");
		
		Thread.sleep(3000);
		//Click the update
		driver.findElement(By.xpath(excel.getData(0,186,1))).click();
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.xpath(excel.getData(0,267,1))).getText().equalsIgnoreCase("Show open item discount"))
		{
			test.log(LogStatus.PASS, "New discount Open Item is canceled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Open Item is canceled Failed");
		}
		Thread.sleep(3000);
		wb.close();
	}

}
