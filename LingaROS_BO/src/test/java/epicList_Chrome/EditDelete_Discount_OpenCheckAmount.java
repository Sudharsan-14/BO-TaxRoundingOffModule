package epicList_Chrome;


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


public class EditDelete_Discount_OpenCheckAmount {
	
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("EditDelete_Discount_OpenCheckAmount_And_Percentage");

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
			Open_Check_Discount_method_editDiscount_OpenCheck(driver);
			Open_Check_Discount_method_deleteOrActiveDiscount_OpenCheck(driver);
			Open_Check_Discount_method_cancelButton(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=91)
	public void Open_Check_Discount_method_editDiscount_OpenCheck(WebDriver driver) throws Exception
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
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"open_check/openDiscount");
		Thread.sleep(5000);
		try
		{
		//Check Check based discount page opened or not
		if(driver.findElement(By.xpath(excel.getData(0, 215, 1))).getText().equalsIgnoreCase("Discounts"))
		{
			test.log(LogStatus.PASS, "Open Check(OpenCheckAmount) page loaded Successfully");
			
			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Open Check(OpenCheckAmount) page loaded Failed");
		
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
		driver.findElement(By.id(excel.getData(0,254,2))).click();
		Thread.sleep(5000);
		//Clear the name field
		driver.findElement(By.xpath(excel.getData(0,216,1))).clear();
		//Enter the required name
		driver.findElement(By.xpath(excel.getData(0,216,1))).sendKeys(Utility.getProperty("DiscountName_OpenCheckAMT"));
		
		//Enable or Disable the Coupon only option
		driver.findElement(By.xpath(excel.getData(0,217,1))).click();
		Thread.sleep(2000);
		
		if(driver.findElement(By.name(excel.getData(0,255,3))).isSelected())
		{
			Thread.sleep(2000);
			//Clear the minimum percentage option
			driver.findElement(By.name(excel.getData(0,256,3))).clear();
			//Enter the required minimum value in minimum percentage
			driver.findElement(By.name(excel.getData(0,256,3))).sendKeys("4");
			//Clear the maximum percentage option
			driver.findElement(By.name(excel.getData(0,257,3))).clear();
			//Enter the required maximum value in maximum percentage
			driver.findElement(By.name(excel.getData(0,257,3))).sendKeys("8");

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
			driver.findElement(By.name(excel.getData(0,256,3))).sendKeys("2");
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
			driver.findElement(By.name(excel.getData(0,261,3))).sendKeys("8.00");
			//Clear the maximum amount option
			driver.findElement(By.name(excel.getData(0,262,3))).clear();
			//Enter the required maximum value in maximum amount
			driver.findElement(By.name(excel.getData(0,262,3))).sendKeys("10.00");

		}
		else
		{
			Thread.sleep(4000);
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
		discType.selectByVisibleText("Comp");
		
		//Clear the Safety Limit Percentage option
		driver.findElement(By.name(excel.getData(0,264,3))).clear();
		//Enter the required percentage
		driver.findElement(By.name(excel.getData(0,264,3))).sendKeys("10");
		
		//Select the any one tax from the give list
		Select applyDiscount = new Select(driver.findElement(By.name(excel.getData(0,265,3))));
		applyDiscount.selectByVisibleText("After Tax");
		
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
		
		//Choose any one color from the given
		driver.findElement(By.xpath(excel.getData(0,236,1))).click(); 	
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(3000);
		
		//Click the update
		driver.findElement(By.xpath(excel.getData(0,186,1))).click();
		Thread.sleep(3000);
		
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.xpath(excel.getData(0,39,1))).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New discount Open Item(OpenCheckAmount) is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Open Item(OpenCheckAmount) is updated Failed");
		}
		
		Thread.sleep(5000);
		
		//Click edit button
		driver.findElement(By.id(excel.getData(0,254,2))).click();
		Thread.sleep(3000);
		
		//Check weather name changes are updated or not
		if(driver.getPageSource().contains(Utility.getProperty("DiscountName_OpenCheckAMT")))
		{
			test.log(LogStatus.PASS, "Edited name is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Edits name is updated Failed");
		}
		
		Thread.sleep(1000);
		//Check the coupon only is enable or not
		if(driver.findElement(By.xpath(excel.getData(0,217,1))).isSelected())
		{
			test.log(LogStatus.PASS, "Coupon only field is enabled");
		}
		else
		{
			test.log(LogStatus.PASS, "Coupon only field is disabled");
		}
		Thread.sleep(1000);
		//Check the discount in percentage is enable or not
		if(driver.findElement(By.xpath(excel.getData(0,258,1))).isSelected())
		{
			test.log(LogStatus.PASS, "Discount in percentage field is enabled");
		}
		else
		{
			test.log(LogStatus.PASS, "Discount in percentage field is disabled");
		}
		Thread.sleep(1000);
		//Check the discount in amount is enable or not
		if(driver.findElement(By.xpath(excel.getData(0,259,1))).isSelected())
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
		Thread.sleep(5000);		wb.close();
	}
	
	@Test(enabled=false,priority=92)
	public void Open_Check_Discount_method_deleteOrActiveDiscount_OpenCheck(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(10000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);Thread.sleep(3000);
				//Click the Active/InActive button
				driver.findElement(By.xpath(excel.getData(0,266,1))).click();				//Click the Yes button in the popup
				Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(0,43,4))).click();
			
				Thread.sleep(3000);
				//Check the menu item deleted/Activated or not
				if(driver.findElement(By.xpath(excel.getData(0,39,1))).getText().equalsIgnoreCase("Discount activated Successfully"))
				{
					test.log(LogStatus.PASS, "New discount Open Check is Activated Successfully");
				}
				else
				{
					test.log(LogStatus.PASS, "New discount Open Check is Inactivated Successfully");
				}		wb.close();

	}

	@Test(enabled=false,priority=93)
	public void Open_Check_Discount_method_cancelButton(WebDriver driver) throws Exception
	{
	
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(3000);
		//Click edit button
		driver.findElement(By.id(excel.getData(0,254,2))).click();
		//Clear the name field
		driver.findElement(By.xpath(excel.getData(0,216,1))).clear();
		//Enter the required name
		driver.findElement(By.xpath(excel.getData(0,216,1))).sendKeys(Utility.getProperty("DiscountName_OpenCheckPer")+"1");
		
		Thread.sleep(3000);
		//Click the update
		driver.findElement(By.xpath(excel.getData(0,186,1))).click();
		//Check whether the Product Item canceled successfully or not
		if(driver.findElement(By.xpath(excel.getData(0,268,1))).getText().equalsIgnoreCase("Show open check discount"))
		{
			test.log(LogStatus.PASS, "New discount Open Item(OpenCheckAmount) is canceled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Open Item(OpenCheckAmount) is canceled Failed");
		}

		Thread.sleep(3000);
		wb.close();
	}

}
