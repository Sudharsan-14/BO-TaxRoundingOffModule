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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;


public class AddEditDelete_Modifiers {
	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete Modifiers");

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
			Modifiers_method_openModifiers(driver);
//			Modifiers_method_refreshModifiers_Page(driver);
//			Modifiers_method_add_Modifiers(driver);
//			Modifiers_method_edit_Modifiers(driver);
//			Modifiers_method_delete_Modifier(driver);
//			Modifiers_method_cancelModifier_BasicDetails(driver);
//			Modifiers_method_cancelModifier_Prefixes(driver);
			Modifiers_method_add_Modifiers_Save_BasicDetails(driver);
			Modifiers_method_inactiveAndActive_Button(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=121)
		public void Modifiers_method_openModifiers(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

	/*		//Click the Products/Items option
			driver.findElement(By.xpath("//span[.='Products/Items']")).click();
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='Modifiers']"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
	        //Click the Modifiers Option		
			driver.findElement(By.xpath("//span[.='Modifiers']")).click();*/
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"modifiers");
			Thread.sleep(8000);
			
			try
			{
			//Check Modifiers page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 17, 1))).getText().equalsIgnoreCase("Modifiers"))
			{
				test.log(LogStatus.PASS, "Modifiers page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifiers page loaded Failed");
				
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
			Thread.sleep(3000);wb.close();
		}
		
		@Test(enabled=false,priority=122)
		public void Modifiers_method_refreshModifiers_Page(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(3, 1171, 1))).click();
			Thread.sleep(5000);
			
			//Check Modifiers page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 17, 1))).getText().equalsIgnoreCase("Modifiers"))
			{
				test.log(LogStatus.PASS, "Modifiers page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifiers page loaded Failed");
			}
			Thread.sleep(3000);wb.close();
		}
		
		@Test(enabled=false,priority=124)
		public void Modifiers_method_add_Modifiers(WebDriver driver) throws Exception
		{
			    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			for (int i = 0; i < 10; i++) {
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
			Thread.sleep(5000);
			//Click the Add Modifiers button
			driver.findElement(By.xpath(excel.getData(3, 134, 1))).click();
			
			Thread.sleep(2000);
			//Check weather the new Modifiers form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1626, 1))).getText().equalsIgnoreCase("NEW MODIFIER"))
			{
				test.log(LogStatus.PASS, "New Modifiers form Loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifiers form Loaded Fail");
			}
			
			Thread.sleep(4000);
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(3, 1627, 1))).clear();
			//Enter the required name
			driver.findElement(By.xpath(excel.getData(3, 1627, 1))).sendKeys(Utility.getProperty("Modifier_Name"));
			
			Thread.sleep(3000);
		/*	WebDriverWait wait=new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(excel.getData(3, 1628, 1))));*/
			//Enable or Disable the Include Default button
			driver.findElement(By.xpath(excel.getData(3, 1628, 1))).click();
			                       
			Thread.sleep(2000);
			//Click the Add Tax button                                             
			driver.findElement(By.xpath(excel.getData(3, 2457, 1))).click();
			
			Thread.sleep(2000);
			//Check weather the new tax form is loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1630, 1))).getText().equalsIgnoreCase("New tax"))
			{
				test.log(LogStatus.PASS, "New Tax form is loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tax form loaded fail");
			}
			
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(3, 532, 1))).clear();
			//Enter the name field
			driver.findElement(By.xpath(excel.getData(3, 532, 1))).sendKeys(Utility.getProperty("Modifier_Tax_Name"));
			
			//Select the Tax Type
			WebElement selTax=driver.findElement(By.name("taxType"));
			
			Select sel=new Select(selTax);
			sel.selectByVisibleText("Percentage");
			
			Thread.sleep(500);
			//Enable or disable the Default tax option
			driver.findElement(By.xpath("//span[contains(.,'Default Tax')]/../../label")).click();
			
			Thread.sleep(500);
			//Enable or disable the Inclusive Tax
			driver.findElement(By.xpath("//span[contains(.,'Inclusive Tax')]/../../label")).click();
			
			Thread.sleep(2000);
			//Clear the percentage field
			driver.findElement(By.name(excel.getData(3, 92, 3))).clear();
			Thread.sleep(2000);
			//Enter the required percentage field
			driver.findElement(By.name(excel.getData(3, 92, 3))).sendKeys("1230");
			
			Thread.sleep(2000);
			//Click the save button
			driver.findElement(By.xpath(excel.getData(3, 2189, 1))).click();
			
			Thread.sleep(5000);
			//Add the Kitchen printer button   
			driver.findElement(By.xpath("//label[contains(.,'Kitchen Printers')]/../div[2]/a")).click();
			
//			Thread.sleep(3000);
//			//Check weather the new kitchen printer form is loaded or not
//			if(driver.findElement(By.xpath(excel.getData(3, 1631, 1))).getText().equalsIgnoreCase("New kitchenPrinter"))
//			{
//				test.log(LogStatus.PASS, "New kitchen printer form is loaded successfully");
//			}
//			else
//			{
//				test.log(LogStatus.FAIL, "New kitchen printer form loaded fail");
//			}
			
			Thread.sleep(3000);
			//Click the Type field
			driver.findElement(By.xpath(excel.getData(3, 2153, 1))).click();
			Thread.sleep(1000);
			//Enter the required input
			driver.findElement(By.xpath(excel.getData(3, 2154, 1))).sendKeys("KDS");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2154, 1))).sendKeys(Keys.ENTER);
			
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(3, 532, 1))).clear();
			//Enter the name field
			driver.findElement(By.xpath(excel.getData(3, 532, 1))).sendKeys(Utility.getProperty("Modifier_Kitchen_Printer_Name"));

			//Clear the IP field
			driver.findElement(By.name(excel.getData(3, 487, 3))).clear();
			//Enter the IP field
			driver.findElement(By.name(excel.getData(3, 487, 3))).sendKeys(Utility.getProperty("Modifier_Kitchen_Printer_IP"));
			
			//Enable or disable the Enable Service Type Restriction option
			driver.findElement(By.xpath(excel.getData(3, 1633, 1))).click();
			
			Thread.sleep(2000);
			//Click the Service Type Option
			driver.findElement(By.xpath(excel.getData(3, 2155, 1))).click();
			Thread.sleep(1000);
			//Enter the required service type
			driver.findElement(By.xpath(excel.getData(3, 2156, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2156, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Enable or disable the Apply to all categories
			driver.findElement(By.xpath(excel.getData(3, 1634, 1))).click();
			
			Thread.sleep(2000);
			//Enable or disable to apply all menu item
			driver.findElement(By.xpath(excel.getData(3, 1635, 1))).click();		
			
			Thread.sleep(2000);
			//Click the save button
			driver.findElement(By.xpath(excel.getData(3, 2189, 1))).click();

			Thread.sleep(60000);
			//CLeare the maximum number of time
			driver.findElement(By.name(excel.getData(3, 164, 3))).clear();
			//Enter the maximum number of time
			driver.findElement(By.name(excel.getData(3, 164, 3))).sendKeys("3");
			
			Thread.sleep(2000);
			//Click the Modify With Option	
			driver.findElement(By.xpath(excel.getData(3, 2458, 1))).click();
			//Enter the required option	//
			driver.findElement(By.xpath(excel.getData(3, 2459, 1))).sendKeys("TOPPINGS");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2459, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Enable or disable the Set Price Here Option
			driver.findElement(By.xpath(excel.getData(3, 1636, 1))).click();
			
			Thread.sleep(3000);
			//Enable or disable the Show modifier
			driver.findElement(By.xpath(excel.getData(3, 1637, 1))).click();

			Thread.sleep(3000);
			//Click the Next button
			driver.findElement(By.xpath(excel.getData(3, 172, 1))).click();
			
			Thread.sleep(3000);
			//Click the Add Prefixes button
			driver.findElement(By.xpath(excel.getData(3, 2460, 1))).click();
			
			Thread.sleep(2000);
			//CLear the prefix name field 
			driver.findElement(By.xpath("//ng-form[@name='prefixForm']/div/div[1]/input")).clear();
			//Enter the required prefix name
			driver.findElement(By.xpath("//ng-form[@name='prefixForm']/div/div[1]/input")).sendKeys("Add");
			
			Thread.sleep(2000);
			//Clear the price field
			driver.findElement(By.xpath("//ng-form[@name='prefixForm']/div/div[2]/input")).clear();
			//Enter the Price
			driver.findElement(By.xpath("//ng-form[@name='prefixForm']/div/div[2]/input")).sendKeys("125");
			
			Thread.sleep(2000);
			//Enable or disable the Override modifier price
			driver.findElement(By.xpath(excel.getData(3, 1639, 1))).click();
			
			Thread.sleep(5000);
			//Click the Add Prefixes button
			driver.findElement(By.xpath(excel.getData(3, 2460, 1))).click();

			Thread.sleep(2000);
			//Click the Close button of Last Added Prefix
			driver.findElement(By.xpath("//ng-form[@name='prefixForm']/../../../div[2]/div/div/a")).click();
			
			Thread.sleep(1000);
			//Click the Yes button in the popup
			Thread.sleep(1500);
			driver.findElement(By.cssSelector(excel.getData(3, 43, 4))).click();
			
			Thread.sleep(3000);
			//Click the Add Serving Size Level
			driver.findElement(By.xpath(excel.getData(3, 2461, 1))).click();
			
			Thread.sleep(3000);
			//Click the Serving Size Level Option
			driver.findElement(By.xpath(excel.getData(3, 2462, 1))).click();
			//Enter the required Serving Size Level
			driver.findElement(By.xpath(excel.getData(3, 2463, 1))).sendKeys("EACH");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2463, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Price Option
			driver.findElement(By.xpath("//th[contains(.,'Serving Size Level')]/../../../../table/tbody/tr[1]/td[2]/ng-form/div/div/input")).clear();
			//Enter the Price
			driver.findElement(By.xpath("//th[contains(.,'Serving Size Level')]/../../../../table/tbody/tr[1]/td[2]/ng-form/div/div/input")).sendKeys("125");
		 	  
			Thread.sleep(2000);
			//Clear the Prefix Price Option
			driver.findElement(By.xpath("//th[contains(.,'Serving Size Level')]/../../../../table/tbody/tr[1]/td[3]/div/input")).clear();
			//Enter the Prefix Price
			driver.findElement(By.xpath("//th[contains(.,'Serving Size Level')]/../../../../table/tbody/tr[1]/td[3]/div/input")).sendKeys("125");

			Thread.sleep(3000);
			//Click the Add Serving Size Level
			driver.findElement(By.xpath(excel.getData(3, 2461, 1))).click();
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			//Thread.sleep(1500);
			Thread.sleep(1000);
			//Click the Close button of Last Added SSL
			driver.findElement(By.xpath(excel.getData(3, 2464, 1))).click();
			 
			Thread.sleep(1000);
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(3, 43, 4))).click();

			Thread.sleep(4000);
			//Click the previous button
			driver.findElement(By.xpath(excel.getData(3, 177, 1))).click();
			
			Thread.sleep(3000);
			//Click the Next button
			driver.findElement(By.xpath(excel.getData(3, 172, 1))).click();
			
			Thread.sleep(3000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 1642, 1))).click();
			
			Thread.sleep(2000);
			//Check weather the new modifier is saved or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Modifier saved successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier Saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier saved fail");
			}
			Thread.sleep(5000);wb.close();
		}
		
		@Test(enabled=false,priority=125)
		public void Modifiers_method_edit_Modifiers(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			Thread.sleep(5000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Modifier_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(4000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
			
			Thread.sleep(5000);
			//Enable or Disable the Include default taxes option
			driver.findElement(By.xpath(excel.getData(3, 1628, 1))).click();
			
			Thread.sleep(2000);
			//Click the Taxes Option
			driver.findElement(By.xpath(excel.getData(3, 2201, 1))).click();
			//Enter the required Tax
			driver.findElement(By.xpath(excel.getData(3, 2202, 1))).sendKeys("GST");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2202, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(2000);
			//Click the Kitchen Printer Option 
			driver.findElement(By.xpath(excel.getData(3, 2465, 1))).click();
			Thread.sleep(1000);
			//Enter the Kitchen Printer
			driver.findElement(By.xpath(excel.getData(3, 2466, 1))).sendKeys("KitchenPrinter_1");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2466, 1))).sendKeys(Keys.ENTER);

			
			Thread.sleep(3000);
			//Click the Update button
			driver.findElement(By.xpath(excel.getData(3, 1642, 1))).click();
			
			Thread.sleep(4000);
			//Check weather the new Modifier is updated or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Modifier updated successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier updated fail");
			}
			Thread.sleep(5000);wb.close();
		}
			
		@Test(enabled=false,priority=126)
		public void Modifiers_method_delete_Modifier(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Modifier_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(4000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).click();
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(3, 43, 4))).click();
			
			Thread.sleep(4000);
			//Check the modifier deleted or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Modifier inactivated successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier is deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier is deleted Failed");
			}
			Thread.sleep(3000);
			
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(3, 174, 1))).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector(excel.getData(3, 175, 4))).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(3, 43, 4))).click();
			Thread.sleep(3000);
			
			//Check the modifier activated or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Modifier activated successfully"))
			{
				test.log(LogStatus.PASS, "Modifier is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifier is activated Failed");
			}
			
			Thread.sleep(5000);
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(3, 174, 1))).click();
			Thread.sleep(3000);wb.close();
		}
		 
		@Test(enabled=false,priority=127)
		public void Modifiers_method_cancelModifier_BasicDetails(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Modifier_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);

			Thread.sleep(4000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
			
			Thread.sleep(5000);
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(3, 173, 1))).click();
			
			Thread.sleep(2000);
			//Check the course form is closed or not
			if(driver.findElement(By.xpath(excel.getData(3, 17, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Modifiers page is Closed after click the close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifiers page is displayed after click the close button");
			}
			
			Thread.sleep(5000);wb.close();
		}
		
		@Test(enabled=false,priority=128)
		public void Modifiers_method_cancelModifier_Prefixes(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Modifier_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);

			Thread.sleep(4000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
			
			Thread.sleep(3000);
			//Click the Next button
			driver.findElement(By.xpath(excel.getData(3, 172, 1))).click();
			
			Thread.sleep(5000);
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(3, 173, 1))).click();
			
			Thread.sleep(2000);
			//Check the course form is closed or not
			if(driver.findElement(By.xpath(excel.getData(3, 17, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Modifiers page is Closed after click the close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifiers page is displayed after click the close button");
			}
			
			Thread.sleep(5000);wb.close();
		}
		
		@Test(enabled=false,priority=129)
		public void Modifiers_method_add_Modifiers_Save_BasicDetails(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			//Click the Add Modifiers button
			driver.findElement(By.xpath(excel.getData(3, 134, 1))).click();
			
			Thread.sleep(3000);
			//Check weather the new Modifiers form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1626, 1))).getText().equalsIgnoreCase("NEW MODIFIER"))
			{
				test.log(LogStatus.PASS, "New Modifiers form Loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifiers form Loaded Fail");
			}
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(3, 1627, 1))).clear();
			//Enter the required name
			driver.findElement(By.xpath(excel.getData(3, 1627, 1))).sendKeys(Utility.getProperty("Modifier_Name")+1);
			
			Thread.sleep(3000);
			//Enable or Disable the Include Default button
			driver.findElement(By.xpath(excel.getData(3, 1628, 1))).click();
			
			Thread.sleep(2000);
			//Click the Taxes Option
			driver.findElement(By.xpath(excel.getData(3, 2201, 1))).click();
			//Enter the required Tax
			driver.findElement(By.xpath(excel.getData(3, 2202, 1))).sendKeys("GST");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2202, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(2000);
			//Click the Kitchen Printer Option
			driver.findElement(By.xpath(excel.getData(3, 2465, 1))).click();
			//Enter the Kitchen Printer
			driver.findElement(By.xpath(excel.getData(3, 2466, 1))).sendKeys("KitchenPrinter_1");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2466, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the maximum number of time 
			driver.findElement(By.name(excel.getData(3, 164, 3))).clear();
			//Enter the maximum number of time
			driver.findElement(By.name(excel.getData(3, 164, 3))).sendKeys("2");
			
			Thread.sleep(8000);
			//Click the Modify With Option
			driver.findElement(By.xpath(excel.getData(3, 2458, 1))).click();
			Thread.sleep(2000);
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2459, 1))).sendKeys("TOPPINGS");
			Thread.sleep(2000);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2459, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Enable or disable the Set Price Here Option
			driver.findElement(By.xpath(excel.getData(3, 1636, 1))).click();
							
			Thread.sleep(2000);
			//Enable or disable the Show modifier
			driver.findElement(By.xpath(excel.getData(3, 1637, 1))).click();
			
			Thread.sleep(3000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 1642, 1))).click();
			
			Thread.sleep(3000);
			//Check weather the new modifier is saved or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Modifier saved successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier Saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier saved fail");
			}
			Thread.sleep(5000);wb.close();
		}
		
		@Test(enabled=false,priority=130)
		public void Modifiers_method_inactiveAndActive_Button(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			Thread.sleep(3000);
			
			Thread.sleep(2000);
			//Click the Active button
			driver.findElement(By.xpath(excel.getData(3, 44, 1))).click();
			Thread.sleep(2000);
			try
			{
			//Check the page
			if(driver.findElement(By.cssSelector(excel.getData(3, 46, 4))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Inactive page is displayed after click the Active button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inactive page is not displayed after click the Active button");
			}
			}
			catch(Exception e) {}
			
			Thread.sleep(3000);
			//Click the InActive button
			driver.findElement(By.xpath(excel.getData(3, 44, 1))).click();
			Thread.sleep(2000);
			
			//Check the page
			if(driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).isDisplayed())
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
