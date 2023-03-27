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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class AddEditDelete_ModifierGroup {
	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete Modifier Group");

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
			Modifiers_Group_method_openModifier_Groups(driver);
			Modifiers_Group_method_refreshModifier_Groups_Page(driver);
			Modifiers_Group_method_add_Modifier_Groups(driver);
			Modifiers_Group_method_edit_Modifier_Group(driver);
			Modifiers_Group_method_edit_Modifier_Group_Customer_Sort(driver);
			Modifiers_Group_method_delete_Modifier(driver);
			Modifiers_Group_method_cancelModifier_Group(driver);
			Modifiers_Group_method_inactiveAndActive_Button(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=141)
		public void Modifiers_Group_method_openModifier_Groups(WebDriver driver) throws Exception
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
			WebElement element = driver.findElement(By.xpath("//span[.='Modifier Groups']"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
	        //Click the Modifier groups Option		
			driver.findElement(By.xpath("//span[.='Modifier Groups']")).click();*/
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"modifierGroups");
			Thread.sleep(8000);
			
			try
			{
			//Check Modifier groups page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 18, 1))).getText().equalsIgnoreCase("Modifier Groups"))
			{
				test.log(LogStatus.PASS, "Modifier Groups page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifier Groups loaded Failed");
				
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
			wb.close();
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=142)
		public void Modifiers_Group_method_refreshModifier_Groups_Page(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(3, 533, 1))).click();
			Thread.sleep(5000);
			
			//Check Modifier Groups page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 2097, 1))).getText().equalsIgnoreCase("Modifier Groups"))
			{
				test.log(LogStatus.PASS, "Modifier Groups page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifier Groups page loaded Failed");
			}
			wb.close();
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=144)
		public void Modifiers_Group_method_add_Modifier_Groups(WebDriver driver) throws Exception
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
			Thread.sleep(3000);
			//Click the Add Modifier Groups button
			driver.findElement(By.xpath(excel.getData(3, 2098, 1))).click();
			
			Thread.sleep(2000);
			//Check weather the new Modifiers Group form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 2099, 1))).getText().equalsIgnoreCase("New Modifier Group"))
			{
				test.log(LogStatus.PASS, "New Modifier Group form Loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier Group form Loaded Fail");
			}
			
			Thread.sleep(6000);
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(3, 2100, 1))).clear();
			//Enter the required name
			driver.findElement(By.xpath(excel.getData(3, 2100, 1))).sendKeys(Utility.getProperty("Modifier_Group_Name"));
			
			Thread.sleep(2000);
			//Click the Modifiers option
			driver.findElement(By.xpath(excel.getData(3, 2101, 1))).click();
			//Enter the required modifier
			driver.findElement(By.xpath(excel.getData(3, 2102, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2102, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Modifiers option
			driver.findElement(By.xpath(excel.getData(3, 2101, 1))).click();
			//Enter the required modifier
			driver.findElement(By.xpath(excel.getData(3, 2102, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2102, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Modifiers option
			driver.findElement(By.xpath(excel.getData(3, 2101, 1))).click();
			//Enter the required modifier
			driver.findElement(By.xpath(excel.getData(3, 2102, 1))).sendKeys("MUSHROOMS");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2102, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Modifiers option
			driver.findElement(By.xpath(excel.getData(3, 2101, 1))).click();
			//Enter the required modifier
			driver.findElement(By.xpath(excel.getData(3, 2102, 1))).sendKeys("OLIVES");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2102, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Modifiers option
			driver.findElement(By.xpath(excel.getData(3, 2101, 1))).click();
			//Enter the required modifier
			driver.findElement(By.xpath(excel.getData(3, 2102, 1))).sendKeys("PEPPERONI");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2102, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Modifiers option
			driver.findElement(By.xpath(excel.getData(3, 2101, 1))).click();
			//Enter the required modifier
			driver.findElement(By.xpath(excel.getData(3, 2102, 1))).sendKeys("SAUSAGE");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2102, 1))).sendKeys(Keys.ENTER);

			
			//Clear the priority field
			driver.findElement(By.name(excel.getData(3, 55, 3))).clear();
			//Enter the priority
			driver.findElement(By.name(excel.getData(3, 55, 3))).sendKeys("3");
			
			Thread.sleep(3000);
			//Enable or Disable the Pizza Topping Option
			driver.findElement(By.xpath(excel.getData(3, 2103, 1))).click();
			
			Thread.sleep(3000);
			//Enable or Disable the Set Price Here Option
			driver.findElement(By.xpath(excel.getData(3, 2104, 1))).click();
			
			Thread.sleep(3000);
			//Click the Add Serving Size Level
			driver.findElement(By.xpath(excel.getData(3, 2105, 1))).click();
			
			Thread.sleep(3000);
			//Click the Serving Size Level Option
			driver.findElement(By.xpath(excel.getData(3, 2106, 1))).click();
			//Enter the required Serving Size Level
			driver.findElement(By.xpath(excel.getData(3, 2107, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(3, 2107, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Price Option
			driver.findElement(By.name(excel.getData(3, 161, 3))).clear();
			//Enter the Price
			driver.findElement(By.name(excel.getData(3, 161, 3))).sendKeys("125");
			
			Thread.sleep(3000);
			//Click the Add Serving Size Level
			driver.findElement(By.xpath(excel.getData(3, 2105, 1))).click();

			//Click the Close button of Last Added SSL
			driver.findElement(By.xpath(excel.getData(3, 2108, 1))).click();
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.xpath(excel.getData(3, 545, 1))).click();

			Thread.sleep(5000);
			//Click the Sort Z-A button
			driver.findElement(By.xpath(excel.getData(3, 2109, 1))).click();
			Thread.sleep(5000);
			
			Thread.sleep(3000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 93, 1))).click();
			
			Thread.sleep(4000);
			//Check weather the new modifier group is saved or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Modifier group saved successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier group Saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier group saved fail");
			}
			wb.close();
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=145)
		public void Modifiers_Group_method_edit_Modifier_Group(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Modifier_Group_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the  Pizza Topping option
			driver.findElement(By.xpath(excel.getData(3, 2103, 1))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Set Price Here option
			driver.findElement(By.xpath(excel.getData(3, 2104, 1))).click();

			Thread.sleep(5000);
			//Click the sort Option
			driver.findElement(By.xpath(excel.getData(3, 211, 1))).click();

			Thread.sleep(10000);
			//Click the Update button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			Thread.sleep(3500);
			//Check weather the new Modifier Group is updated or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Modifier group updated successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier Group updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier Group updated fail");
			}
			wb.close();
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=146)
		public void Modifiers_Group_method_edit_Modifier_Group_Customer_Sort(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Modifier_Group_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
			
			Thread.sleep(2000);
			//Enable or Disable the  Pizza Topping option
			driver.findElement(By.xpath(excel.getData(3, 2103, 1))).click();
			
			Thread.sleep(2000);
			//Click the Taxes Option
			driver.findElement(By.xpath(excel.getData(3, 2110, 1))).click();

			Thread.sleep(5000);
			// Element which needs to drag.
			WebElement From = driver.findElement(By.xpath(excel.getData(3, 2112, 1)));
			// Element on which need to drop.
			WebElement To = driver.findElement(By.xpath(excel.getData(3, 2111, 1)));
			Thread.sleep(2000);
			// Using Action class for drag and drop.
			Actions act = new Actions(driver);
			Thread.sleep(2000);
			// Dragged and dropped.
			act.clickAndHold(From).moveToElement(To).release(To).build().perform();
			
			
			Thread.sleep(10000);
			//Click the Update button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			Thread.sleep(2000);
			//Check weather the new Modifier Group is updated or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Modifier group updated successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier Group updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier Group updated fail");
			}
			wb.close();
			Thread.sleep(5000);
		}
			
		@Test(enabled=false,priority=147)
		public void Modifiers_Group_method_delete_Modifier(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Modifier_Group_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).click();
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.xpath(excel.getData(3, 545, 1))).click();
			
			Thread.sleep(3000);
			//Check the modifier group deleted or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Modifier group inactivated successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier group is deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier group is deleted Failed");
			}
			Thread.sleep(3000);
			
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(3, 44, 1))).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.xpath(excel.getData(3, 1260, 1))).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.xpath(excel.getData(3, 545, 1))).click();
			Thread.sleep(3000);
			
			//Check the modifier activated or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Modifier group activated successfully"))
			{
				test.log(LogStatus.PASS, "Modifier Group is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifier Group is activated Failed");
			}
			
			Thread.sleep(5000);
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(3, 44, 1))).click();
			wb.close();
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=148)
		public void Modifiers_Group_method_cancelModifier_Group(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Modifier_Group_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(3000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
			
			Thread.sleep(5000);
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(3, 130, 1))).click();
			
			Thread.sleep(2000);
			//Check the course form is closed or not
			if(driver.findElement(By.xpath(excel.getData(3, 18, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Modifier Groups page is Closed after click the close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifier Groups page is displayed after click the close button");
			}
			wb.close();
			Thread.sleep(5000);
		}
			
		@Test(enabled=false,priority=149)
		public void Modifiers_Group_method_inactiveAndActive_Button(WebDriver driver) throws Exception
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
			Thread.sleep(1000);
			
			//Click the Active button
			driver.findElement(By.xpath(excel.getData(3, 44, 1))).click();
			Thread.sleep(1000);
			try
			{
			//Check the page
			if(driver.findElement(By.cssSelector(excel.getData(3, 157, 4))).isDisplayed())
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
			driver.findElement(By.xpath(excel.getData(3, 44, 1))).click();
			Thread.sleep(1000);
			
			//Check the page
			if(driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Active page is displayed after click the Inactive button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Active page is not displayed after click the Inactive button");
			}
			wb.close();
			Thread.sleep(5000);
		}

}
