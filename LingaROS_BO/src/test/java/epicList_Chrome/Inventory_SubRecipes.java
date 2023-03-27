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

public class Inventory_SubRecipes {
	
public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_SubRecipes");

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
			// Call the chrome driver by using local path
			System.setProperty("webdriver.chrome.driver", Utility.getProperty("Chrome_Driver_Path"));
			// Open the Chrome window
			driver = new ChromeDriver();
			// Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Maximize the Chrome window
			driver.manage().window().maximize();
			Thread.sleep(1000);
			// Launch the URL
			driver.get(Utility.getProperty("appURL"));
			if (Utility.getProperty("Product").equalsIgnoreCase("UPOS")) {
				Browser a = new Browser();
				a.UPOS_login(driver, test);
			} else {
				Browser a = new Browser();
				a.Linga_login(driver, test);
			}
		}

		/*
		 * @Test(priority = 500) public void logout() throws Exception { Browser a = new
		 * Browser(); a.Logout(driver, test);
		 */
		//}	
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
			Inventory_Sub_Recipes_openpage(driver);
			Inventory_Sub_Recipes_refresh(driver);
			Inventory_Sub_Recipes_add_Invetory_Sub_Recipes(driver);
			Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe(driver);
			Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_InvITEM_InvITEM(driver);
			Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_InvITEM_SubRecipe(driver);
			Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_InvITEM_Manual(driver);
			Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_SubRecipe_Manual(driver);
			Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_SubRecipe_SubRecipe(driver);
			Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_Manual_Manual(driver);
			Inventory_Sub_Recipes_delete_Invetory_Sub_Recipe(driver);
			Inventory_Sub_Recipes_closeButton_Sub_Recipe(driver);
			Inventory_Sub_Recipes_verifyActive_InActiveButton_Sub_Recipe(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=45)
		public void Inventory_Sub_Recipes_openpage(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(2000);

	
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"subRecipes");

			Thread.sleep(4000);
			try
			{
			//Check Storage Locations page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1990, 1))).getText().equalsIgnoreCase("Sub Recipe"))
  			{
				test.log(LogStatus.PASS, "Sub Recipes page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
  			}
			else
			{
				test.log(LogStatus.FAIL, "Sub Recipes page loaded Failed");
			
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
			
			Thread.sleep(4000);
			
		}
		
		@Test(enabled=false,priority=46)
		public void Inventory_Sub_Recipes_refresh(WebDriver driver) throws Exception 
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(2000);

			
			
			Thread.sleep(2000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(2, 94, 1))).click();
			
			Thread.sleep(2000);
			
			//Check Sub Recipes page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1990, 1))).getText().equalsIgnoreCase("Sub Recipe"))
			{
				test.log(LogStatus.PASS, "Sub Recipes page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Sub Recipes page refreshed Failed");
			}
			wb.close();
			Thread.sleep(3000);


		}
		
		@SuppressWarnings("unused")
		@Test(enabled=false,priority=47)
		public void Inventory_Sub_Recipes_add_Invetory_Sub_Recipes(WebDriver driver) throws Exception
		{
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(2000);

			
			  //Scroll the web page
		    for(int i=1; i <= 20; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		    	Thread.sleep(1000);
		    } 
		    
			Thread.sleep(5000);
			//Click on the Add Sub Recipe option
			driver.findElement(By.xpath(excel.getData(2, 49, 1))).click();
			Thread.sleep(3000);
			
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath("//span[.='New undefined']")).getText().equalsIgnoreCase("New undefined"))
			{
				test.log(LogStatus.PASS, "New SubRecipe form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New SubRecipe form loaded Failed");
			}

			Thread.sleep(3000);
			
			//Clear the name field
			driver.findElement(By.name(excel.getData(2, 51, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(2, 51, 3))).sendKeys(Utility.getProperty("Inventory_SubRecipe_Name"));
			Thread.sleep(2000);
			
			//Enable the Calculate COGS On Cost Price button
			driver.findElement(By.name(excel.getData(2, 52, 3))).click();
			
			
			Thread.sleep(2000);
			//Add Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();
			
			Thread.sleep(1000);
			//Click the Category Option
			driver.findElement(By.xpath(excel.getData(3, 1991, 1))).click();
			//Enter the required input
			driver.findElement(By.xpath(excel.getData(3, 1992, 1))).sendKeys(Keys.ARROW_DOWN);  
			//EPress the Enter button
			Thread.sleep(1000);
			driver.findElement(By.xpath(excel.getData(3, 1992, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(10000);
			//Click the Item
			driver.findElement(By.xpath(excel.getData(2, 56, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Item
			driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter button
			Thread.sleep(2000);
			driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 58, 1))).click();
			//Enter the Required Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 59, 1))).click();
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 59, 1))).sendKeys(Keys.ENTER);		
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 60, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 60, 1))).sendKeys("3");
			
			Thread.sleep(2000);
			//Add Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 62, 1))).click();

			Thread.sleep(1000);
			//Click the Item
			driver.findElement(By.xpath(excel.getData(2, 63, 1))).click();
			Thread.sleep(2000);
			//Enter the Required Item
			driver.findElement(By.xpath(excel.getData(2, 64, 1))).click();
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 64, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 65, 1))).click();
			//Enter the Required Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 66, 1))).click();
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 66, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 67, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 67, 1))).sendKeys("3");

			Thread.sleep(2000);
			//Add Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 68, 1))).click();

			Thread.sleep(1000);
			//clear the Required Item
			driver.findElement(By.xpath(excel.getData(2, 69, 1))).clear();
			Thread.sleep(1000);
			//Enter the required Item
			driver.findElement(By.xpath(excel.getData(2, 69, 1))).sendKeys("Test");
			
			Thread.sleep(2000);
			//clear the Price per Unit Option
			driver.findElement(By.xpath(excel.getData(2, 83, 1))).clear();
			//Enter the Price per Unit
			driver.findElement(By.xpath(excel.getData(2, 83, 1))).sendKeys("500");
			
			Thread.sleep(1000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 70, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 70, 1))).sendKeys("2");

			Thread.sleep(2000);
			//Add Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();
			
			/*
			 * //Click the Close button for Inventory Item
			 * driver.findElement(By.xpath(excel.getData(2, 71, 1))).click();
			 * Thread.sleep(1000); //Click the Yes Button
			 * driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			 */
			
			
			Thread.sleep(2000);
			//Add Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 62, 1))).click();
			
			/*
			 * //Click the Close button for Sub Recipe
			 * driver.findElement(By.xpath(excel.getData(2, 71, 1))).click();
			 * Thread.sleep(1000); //Click the Yes Button
			 * driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			 */
			
			
			Thread.sleep(2000);
			//Add Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 68, 1))).click();
			
			/*
			 * //Click the Close button for Manual Entry
			 * driver.findElement(By.xpath(excel.getData(2, 71, 1))).click();
			 * Thread.sleep(1000); //Click the Yes Button
			 * driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			 */
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,200)");
			Thread.sleep(1000);
			List<WebElement> del =  driver.findElements(By.xpath("//tbody/tr/td[9]/a/i"));
			for(int i=1;i<=3;i++) {
				 driver.findElement(By.xpath("//tbody/tr[4]/td[9]/a/i")).click();
				 Thread.sleep(1000); 
				 //Click the Yes Button
				  driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
				  Thread.sleep(1000);
			}
			Thread.sleep(2000);
			//Clear the notes field
			driver.findElement(By.xpath(excel.getData(2, 84, 1))).clear();
			//Enter the notes
			driver.findElement(By.xpath(excel.getData(2, 84, 1))).sendKeys("Inventory Sub Recipe Notes");
			Thread.sleep(2000);
				
			//Clear the Quantity Field
			driver.findElement(By.xpath(excel.getData(2, 85, 1))).clear();
			//Enter the Quantity
			driver.findElement(By.xpath(excel.getData(2, 85, 1))).sendKeys("2");
			
			Thread.sleep(1000);
			//Click the Inventory Unit
			driver.findElement(By.xpath(excel.getData(2, 86, 1))).click();
			//Enter the Required Inventory Unit
			driver.findElement(By.xpath(excel.getData(2, 87, 1))).sendKeys("Liter");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 87, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Clear the Par Level Option
			driver.findElement(By.xpath(excel.getData(2, 88, 1))).clear();
			//Enter the Par Level
			driver.findElement(By.xpath(excel.getData(2, 88, 1))).sendKeys("1");
			
			Thread.sleep(1000);
			//Clear the Yield field
			driver.findElement(By.xpath(excel.getData(2, 89, 1))).clear();
			//Enter the Required Yield
			driver.findElement(By.xpath(excel.getData(2, 89, 1))).sendKeys("20");
			Thread.sleep(1000);
			
		    js.executeScript("window.scrollBy(0,200)");
			
			Thread.sleep(3000);
			//Click the Price per unit field
			driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[3]/div[1]/div[2]/div[5]/div[1]/input[1]")).click();
			//Clear the Price per unit field
			driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[3]/div[1]/div[2]/div[5]/div[1]/input[1]")).clear();
			//Enter the Price per unit Yield
			driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[3]/div[1]/div[2]/div[5]/div[1]/input[1]")).sendKeys("2.00");
			
			
			
			Thread.sleep(4000);
			//Click Add Primary Storage Location
			driver.findElement(By.xpath(excel.getData(2, 91, 1))).click();
			
			Thread.sleep(1500);
			//Check whether the New Storage form loaded or not
			if(driver.findElement(By.xpath(excel.getData(2, 99, 1))).getText().equalsIgnoreCase("New Storage"))
			{
				test.log(LogStatus.PASS, "New Storage form Loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Storage form Loaded Failed");
			}
			Thread.sleep(2000);
			
			//Clear the name field
			driver.findElement(By.id(excel.getData(2, 100, 2))).clear();
			//Enter the name
			driver.findElement(By.id(excel.getData(2, 100, 2))).sendKeys(Utility.getProperty("Inventory_SubRecipe_Primary_Storage_Name"));
			
			Thread.sleep(1000);
			//Clear the Description
			driver.findElement(By.id(excel.getData(2, 101, 2))).clear();
			//Enter the Description
			driver.findElement(By.id(excel.getData(2, 101, 2))).sendKeys("Desc of Primary Storage");
			
			Thread.sleep(2000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(2, 102, 1))).click();
			
			for(int i=1;i<=8;i++)
			{
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			
			}
		
			Thread.sleep(4000);
			//Click the Secondary Storage Location
			driver.findElement(By.xpath(excel.getData(2, 103, 1))).click();
			Thread.sleep(4000);
			//Enter the Secondary Storage Location
			driver.findElement(By.xpath(excel.getData(2, 104, 1))).sendKeys("Freezer");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 104, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Input field
			driver.findElement(By.xpath(excel.getData(3, 1999, 1))).clear();
			//Enter the required Input
			driver.findElement(By.xpath(excel.getData(3, 1999, 1))).sendKeys("2");
			
			Thread.sleep(2000);
			//Click the Required option
			driver.findElement(By.xpath(excel.getData(3, 1997, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Option
			driver.findElement(By.xpath(excel.getData(3, 1998, 1))).sendKeys("Liter");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1998, 1))).sendKeys(Keys.ENTER);
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(2, 107, 1))).click();
			
			Thread.sleep(4000);
			//Check whether the new storage location saved or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Sub Recipe saved successfully!."))
			{
				test.log(LogStatus.PASS, "New Sub Recipe Saved Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Recipe Save Failed");
			}
			wb.close();
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=48)
		public void Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe(WebDriver driver) throws Exception
		{

			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(2000);

			
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
			Thread.sleep(5000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Utility.getProperty("Inventory_SubRecipe_Name")+"1");
			Thread.sleep(5000);
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Keys.BACK_SPACE);
			/*
			 * Thread.sleep(1000); driver.findElement(By.xpath(excel.getData(2, 93,
			 * 1))).sendKeys(Keys.ENTER);
			 */
			Thread.sleep(8000);	
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(2, 109, 4))).click();
			
			Thread.sleep(5000);
			//Clear the name field
			driver.findElement(By.name(excel.getData(2, 51, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(2, 51, 3))).sendKeys(Utility.getProperty("Inventory_SubRecipe_Name")+"1");
            
			Thread.sleep(5000);
			//Enable the Calculate COGS On Cost Price button
			driver.findElement(By.name(excel.getData(2, 52, 3))).click();
			
			Thread.sleep(3000);
			//Click the Close button for Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 415, 1))).click();
			Thread.sleep(2000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			Thread.sleep(3000);
			//Click the Close button for Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 416, 1))).click();
			Thread.sleep(2000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			Thread.sleep(3000);
			//Click the Close button for Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 417, 1))).click();
			Thread.sleep(2000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			Thread.sleep(3000);
			//Clear the Price Per Unit Option
			driver.findElement(By.xpath(excel.getData(3, 2095, 1))).clear();
			Thread.sleep(1000);
			//Enter the Price Per Unit
			driver.findElement(By.xpath(excel.getData(3, 2095, 1))).sendKeys("2000");
			
	/*		Thread.sleep(1500);
			//Clear the Recipe Units
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[4]/div/div/div/div[1]/div/div[1]/ng-form/div/div/div[4]/div/input")).clear();
			//Enter the Required amount
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[4]/div/div/div/div[1]/div/div[1]/ng-form/div/div/div[4]/div/input")).sendKeys("1500");
	*/		
			Thread.sleep(3000);
			//Click the add recipe unit option
	//		driver.findElement(By.xpath(excel.getData(3, 2096, 1))).click();
			
	/*		Thread.sleep(1000);
			//Clear the Input field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[4]/div/div/div/div[1]/div/div[2]/ng-form/div/div/div[2]/input")).clear();
			//Enter the required Input
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[4]/div/div/div/div[1]/div/div[2]/ng-form/div/div/div[2]/input")).sendKeys("2");
		
			Thread.sleep(2000);
			//Click the Required option
			driver.findElement(By.xpath(excel.getData(2, 419, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Option
			driver.findElement(By.xpath(excel.getData(2, 420, 1))).sendKeys("Milli liter");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 420, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Clear the Recipe Units
			driver.findElement(By.xpath(excel.getData(2, 421, 1))).clear();
			Thread.sleep(1000);
			//Enter the Required amount
			driver.findElement(By.xpath(excel.getData(2, 421, 1))).sendKeys("1500");

			Thread.sleep(2000);
			//Click the add recipe unit option
			driver.findElement(By.xpath(excel.getData(3, 2096, 1))).click();
			
			Thread.sleep(2000);
			//Click the Close Button of Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 422, 1))).click();
			
			Thread.sleep(2000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			Thread.sleep(1500);
	*/	
			for(int i=1;i<=8;i++)
			{
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			
			}
			
			Thread.sleep(4000);
			//Click the update
			driver.findElement(By.xpath(excel.getData(2, 107, 1))).click();

			Thread.sleep(4000);
			//Check whether the storage locations updated successfully or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Sub Recipe updated successfully!."))
			{
				test.log(LogStatus.PASS, "New Sub Recipe updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Recipe updated Failed");
			}
			wb.close();

			Thread.sleep(5000);

		}

		@Test(enabled=false,priority=49)
		public void Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_InvITEM_InvITEM(WebDriver driver) throws Exception
		{

			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(2000);

			
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
			Thread.sleep(5000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Utility.getProperty("Inventory_SubRecipe_Name")+"1"+"1");
			Thread.sleep(5000);
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(5000);
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(2, 109, 4))).click();
			
			Thread.sleep(5000);
			//Add Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();
			
			Thread.sleep(3000);
			//Click the Category Option
			driver.findElement(By.xpath(excel.getData(3, 1991, 1))).click();
			Thread.sleep(1000);
			//Enter the required input
			driver.findElement(By.xpath(excel.getData(3, 1992, 1))).sendKeys("FOOD");
			//EPress the Enter button
			Thread.sleep(1000);driver.findElement(By.xpath(excel.getData(3, 1992, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Item
			driver.findElement(By.xpath(excel.getData(2, 56, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Item
			driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys("Parotta");
			//Press the Enter button
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 58, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 59, 1))).sendKeys("Kilo gram");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 59, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 60, 1))).clear();
			Thread.sleep(1000);
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 60, 1))).sendKeys("3");

			Thread.sleep(2000);
			//Add Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();
			
			Thread.sleep(2000);
			//Click the Category Option
			driver.findElement(By.xpath(excel.getData(2, 423, 1))).click();
			Thread.sleep(1000);
			//Enter the required input
			driver.findElement(By.xpath(excel.getData(2, 424, 1))).sendKeys("FOOD");
			//EPress the Enter button
			driver.findElement(By.xpath(excel.getData(2, 424, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Item
			driver.findElement(By.xpath(excel.getData(2, 63, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Item
			driver.findElement(By.xpath(excel.getData(2, 64, 1))).sendKeys("Parotta");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 64, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 65, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 66, 1))).sendKeys("Kilo gram");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 66, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 67, 1))).clear();
			Thread.sleep(1000);
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 67, 1))).sendKeys("3");

			for(int i=1;i<=8;i++)
			{
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			
			}
			
			Thread.sleep(3000);
			//Click the update
			driver.findElement(By.xpath(excel.getData(2, 107, 1))).click();

			Thread.sleep(4000);
			//Check whether the storage locations updated successfully or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Sub Recipe updated successfully!."))
			{
				test.log(LogStatus.PASS, "New Sub Recipe updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Recipe updated Failed");
			}
			wb.close();
			Thread.sleep(5000);

		}
		
		@Test(enabled=false,priority=50)
		public void Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_InvITEM_SubRecipe(WebDriver driver) throws Exception
		{

			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(2000);

			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
			Thread.sleep(5000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Utility.getProperty("Inventory_SubRecipe_Name")+"1"+"1");
			Thread.sleep(5000);
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Keys.BACK_SPACE);
			Thread.sleep(5000);
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(2, 109, 4))).click();
			
			Thread.sleep(3000);
			//Click the Close button for Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 416, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			Thread.sleep(2000);
			//Click the Close button for Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 417, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			Thread.sleep(2000);
			//Add Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();
			
			Thread.sleep(2000);
			//Click the Category Option
			driver.findElement(By.xpath(excel.getData(3, 1991, 1))).click();
			Thread.sleep(1000);
			//Enter the required input
			driver.findElement(By.xpath(excel.getData(3, 1992, 1))).sendKeys("FOOD");
			//EPress the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1992, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Item
			driver.findElement(By.xpath(excel.getData(2, 56, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Item
			driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys("Parotta");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 58, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 59, 1))).sendKeys("Kilo gram");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 59, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 60, 1))).clear();
			Thread.sleep(1000);
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 60, 1))).sendKeys("3");

			Thread.sleep(2000);
			//Add Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 62, 1))).click();

			Thread.sleep(2000);
			//Click the Item
			driver.findElement(By.xpath(excel.getData(2, 63, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Item
			driver.findElement(By.xpath(excel.getData(2, 64, 1))).sendKeys("Aruku");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 64, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 65, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 66, 1))).sendKeys("Gram");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 66, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 67, 1))).clear();
			Thread.sleep(1000);
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 67, 1))).sendKeys("3");

			for(int i=1;i<=8;i++)
			{
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			
			}
		
			Thread.sleep(3000);
			//Click the update
			driver.findElement(By.xpath(excel.getData(2, 107, 1))).click();

			Thread.sleep(4000);
			//Check whether the storage locations updated successfully or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Sub Recipe updated successfully!."))
			{
				test.log(LogStatus.PASS, "New Sub Recipe updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Recipe updated Failed");
			}wb.close();

			Thread.sleep(5000);

		}
		
		@Test(enabled=false,priority=51)
		public void Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_InvITEM_Manual(WebDriver driver) throws Exception
		{

			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(2000);

			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
			Thread.sleep(5000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Utility.getProperty("Inventory_SubRecipe_Name")+"1"+"1");
			Thread.sleep(5000);
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Keys.BACK_SPACE);
			Thread.sleep(5000);
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(2, 109, 4))).click();
			
			Thread.sleep(3000);
			//Click the Close button for Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 416, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			Thread.sleep(3000);
			//Click the Close button for Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 417, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			Thread.sleep(3000);
			//Add Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();
			
			Thread.sleep(2000);
			//Click the Category Option
			driver.findElement(By.xpath(excel.getData(3, 1991, 1))).click();
			Thread.sleep(1000);
			//Enter the required input
			driver.findElement(By.xpath(excel.getData(3, 1992, 1))).sendKeys("FOOD");
			//EPress the Enter button
			driver.findElement(By.xpath(excel.getData(3, 1992, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Item
			driver.findElement(By.xpath(excel.getData(2, 56, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Item
			driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys("Parotta");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 58, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 59, 1))).sendKeys("Kilo gram");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 59, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 60, 1))).clear();
			Thread.sleep(1000);
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 60, 1))).sendKeys("3");

			Thread.sleep(2000);
			//Add Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 68, 1))).click();

			Thread.sleep(2000);
			//clear the Required Item
			driver.findElement(By.xpath(excel.getData(2, 425, 1))).clear();
			Thread.sleep(1000);
			//Enter the required Item
			driver.findElement(By.xpath(excel.getData(2, 425, 1))).sendKeys("Test");
			
			Thread.sleep(2000);
			//clear the Price per Unit Option
			driver.findElement(By.xpath(excel.getData(2, 426, 1))).clear();
			Thread.sleep(1000);
			//Enter the Price per Unit
			driver.findElement(By.xpath(excel.getData(2, 426, 1))).sendKeys("500");
			
			Thread.sleep(2000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 67, 1))).clear();
			Thread.sleep(1000);
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 67, 1))).sendKeys("2");
			
			for(int i=1;i<=8;i++)
			{
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			
			}
			
			Thread.sleep(3000);
			//Click the update
			driver.findElement(By.xpath(excel.getData(2, 107, 1))).click();

			Thread.sleep(4000);
			//Check whether the storage locations updated successfully or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Sub Recipe updated successfully!."))
			{
				test.log(LogStatus.PASS, "New Sub Recipe updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Recipe updated Failed");
			}wb.close();

			Thread.sleep(5000);

		}

		@Test(enabled=false,priority=52)
		public void Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_SubRecipe_Manual(WebDriver driver) throws Exception
		{

			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  			
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
			Thread.sleep(1000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Utility.getProperty("Inventory_SubRecipe_Name")+"1"+"1");
			Thread.sleep(5000);
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Keys.BACK_SPACE);
			Thread.sleep(5000);
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(2, 109, 4))).click();
			
			Thread.sleep(2000);
			//Click the Close button for Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 416, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			Thread.sleep(2000);
			//Click the Close button for Inventory Item
			driver.findElement(By.xpath(excel.getData(2, 417, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			
			Thread.sleep(3000);
			//Add Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 62, 1))).click();

			Thread.sleep(2000);
			//Click the Item
			driver.findElement(By.xpath(excel.getData(2, 56, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Item
			driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys("Aruku");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 58, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 59, 1))).sendKeys("Gram");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 59, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 60, 1))).clear();
			Thread.sleep(1000);
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 60, 1))).sendKeys("3");

			Thread.sleep(2000);
			//Add Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 68, 1))).click();

			Thread.sleep(2000);
			//clear the Required Item
			driver.findElement(By.xpath(excel.getData(2, 425, 1))).clear();
			Thread.sleep(1000);
			//Enter the required Item
			driver.findElement(By.xpath(excel.getData(2, 425, 1))).sendKeys("Test");
			
			Thread.sleep(2000);
			//clear the Price per Unit Option
			driver.findElement(By.xpath(excel.getData(2, 426, 1))).clear();
			Thread.sleep(1000);
			//Enter the Price per Unit
			driver.findElement(By.xpath(excel.getData(2, 426, 1))).sendKeys("500");
			
			Thread.sleep(2000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 67, 1))).clear();
			Thread.sleep(1000);
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 67, 1))).sendKeys("2");

			for(int i=1;i<=8;i++)
			{
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			
			}
			
			Thread.sleep(3000);
			//Click the update
			driver.findElement(By.xpath(excel.getData(2, 107, 1))).click();

			Thread.sleep(4000);
			//Check whether the storage locations updated successfully or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Sub Recipe updated successfully!."))
			{
				test.log(LogStatus.PASS, "New Sub Recipe updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Recipe updated Failed");
			}wb.close();

			Thread.sleep(5000);

		}

		@Test(enabled=false,priority=53)
		public void Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_SubRecipe_SubRecipe(WebDriver driver) throws Exception
		{

			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(2000);

			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
			Thread.sleep(2000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Utility.getProperty("Inventory_SubRecipe_Name")+"1"+"1");
			Thread.sleep(5000);
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Keys.BACK_SPACE);
			Thread.sleep(5000);
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(2, 109, 4))).click();
			
			Thread.sleep(3000);
			//Click the Close button for Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 416, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			Thread.sleep(1500);
			//Click the Close button for Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 417, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			Thread.sleep(2000);
			//Add Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 62, 1))).click();

			Thread.sleep(2000);
			//Click the Item
			driver.findElement(By.xpath(excel.getData(2, 56, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Item
			driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys("Aruku");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 57, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 58, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 59, 1))).sendKeys("Gram");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 59, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 60, 1))).clear();
			Thread.sleep(1000);
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 60, 1))).sendKeys("3");

			Thread.sleep(2000);
			//Add Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 62, 1))).click();

			Thread.sleep(2000);
			//Click the Item
			driver.findElement(By.xpath(excel.getData(2, 63, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Item
			driver.findElement(By.xpath(excel.getData(2, 64, 1))).sendKeys("Onion Fry");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 64, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 65, 1))).click();
			Thread.sleep(1000);
			//Enter the Required Recipe Unit
			driver.findElement(By.xpath(excel.getData(2, 66, 1))).sendKeys("Kilo gram");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(2, 66, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 67, 1))).clear();
			Thread.sleep(1000);
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 67, 1))).sendKeys("3");
			
			for(int i=1;i<=8;i++)
			{
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			
			}
			
			Thread.sleep(3000);
			//Click the update
			driver.findElement(By.xpath(excel.getData(2, 107, 1))).click();

			Thread.sleep(4000);
			//Check whether the storage locations updated successfully or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Sub Recipe updated successfully!."))
			{
				test.log(LogStatus.PASS, "New Sub Recipe updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Recipe updated Failed");
			}
			wb.close();

			Thread.sleep(3000);

		}

		@Test(enabled=false,priority=54)
		public void Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_Manual_Manual(WebDriver driver) throws Exception
		{

			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(2000);

			
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
			Thread.sleep(2000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Utility.getProperty("Inventory_SubRecipe_Name")+"1"+"1");
			Thread.sleep(5000);
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Keys.BACK_SPACE);
			Thread.sleep(5000);
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(2, 109, 4))).click();
			
			Thread.sleep(3000);
			//Click the Close button for Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 416, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			Thread.sleep(2000);
			//Click the Close button for Sub Recipe
			driver.findElement(By.xpath(excel.getData(2, 417, 1))).click();
			Thread.sleep(1000);
			//Click the Yes Button
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			Thread.sleep(3000);
			//Add Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 68, 1))).click();

			Thread.sleep(2000);
			//clear the Required Item
			driver.findElement(By.xpath(excel.getData(2, 427, 1))).clear();
			//Enter the required Item
			driver.findElement(By.xpath(excel.getData(2, 427, 1))).sendKeys("Test");
			
			Thread.sleep(2000);
			//clear the Price per Unit Option
			driver.findElement(By.xpath(excel.getData(2, 428, 1))).clear();
			//Enter the Price per Unit
			driver.findElement(By.xpath(excel.getData(2, 428, 1))).sendKeys("500");
			
			Thread.sleep(2000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 60, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 60, 1))).sendKeys("2");

			Thread.sleep(2000);
			//Add Manual Entry
			driver.findElement(By.xpath(excel.getData(2, 68, 1))).click();

			Thread.sleep(2000);
			//clear the Required Item
			driver.findElement(By.xpath(excel.getData(2, 425, 1))).clear();
			//Enter the required Item
			driver.findElement(By.xpath(excel.getData(2, 425, 1))).sendKeys("Test");
			
			Thread.sleep(2000);
			//clear the Price per Unit Option
			driver.findElement(By.xpath(excel.getData(2, 426, 1))).clear();
			//Enter the Price per Unit
			driver.findElement(By.xpath(excel.getData(2, 426, 1))).sendKeys("500");
			
			Thread.sleep(2000);
			//Clear the Quantity
			driver.findElement(By.xpath(excel.getData(2, 67, 1))).clear();
			//Enter the required quantity
			driver.findElement(By.xpath(excel.getData(2, 67, 1))).sendKeys("2");

			for(int i=1;i<=8;i++)
			{
				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			
			}
			
			Thread.sleep(3000);
			//Click the update
			driver.findElement(By.xpath(excel.getData(2, 107, 1))).click();

			Thread.sleep(4000);
			//Check whether the storage locations updated successfully or not
			if(driver.findElement(By.xpath(excel.getData(2, 108, 1))).getText().equalsIgnoreCase("Sub Recipe updated successfully!."))
			{
				test.log(LogStatus.PASS, "New Sub Recipe updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Recipe updated Failed");
			}
			wb.close();
			Thread.sleep(5000);

		}

		@Test(enabled=false,priority=55)
		public void Inventory_Sub_Recipes_delete_Invetory_Sub_Recipe(WebDriver driver) throws Exception
		{

			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  

			
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
			Thread.sleep(2000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Utility.getProperty("Inventory_SubRecipe_Name")+"1"+"1");
			Thread.sleep(5000);
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).sendKeys(Keys.BACK_SPACE);
			Thread.sleep(6000);		
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(2, 116, 4))).click();
			Thread.sleep(1000);
			//Click the Yes button in the popup
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			Thread.sleep(13000);
			WebElement ele=driver.findElement(By.xpath(excel.getData(2, 108, 1)));
			WebDriverWait wait=new WebDriverWait(driver,100);
			
			//Check the storage locations deleted or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("inactivated successfully"))
			{
				test.log(LogStatus.PASS, "New Sub Recipe deleted Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Recipe deleted Failed");
			}
			wb.close();

			Thread.sleep(5000);
		

			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(2, 117, 1))).click();
			
			Thread.sleep(6000);
			//Click the success button
			driver.findElement(By.cssSelector(excel.getData(2, 121, 4))).click();
			
			Thread.sleep(2000);
			//Click the Yes button in the popup
			driver.findElement(By.xpath(excel.getData(2, 119, 1))).click();
			
			WebElement ac=driver.findElement(By.xpath(excel.getData(2, 108, 1)));
			WebDriverWait wait1=new WebDriverWait(driver,150);
			//Check the Storage Location activated or not
			if(wait1.until(ExpectedConditions.visibilityOf(ac)).getText().equalsIgnoreCase("inactivated successfully"))
			{
				test.log(LogStatus.PASS, "Sub recipe activated Successfully");
			}
			else
			{
			 	test.log(LogStatus.FAIL, "Sub recipe activated Failed");
			}
			wb.close();
			Thread.sleep(5000);
		}

		@Test(enabled=false,priority=56)
		public void Inventory_Sub_Recipes_closeButton_Sub_Recipe(WebDriver driver) throws Exception
		{

			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);  
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  

			
			Thread.sleep(5000);
			//Click on the Add Sub Recipe option
			driver.findElement(By.xpath(excel.getData(2, 49, 1))).click();
			Thread.sleep(3000);
			
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath("//span[.='New undefined']")).getText().equalsIgnoreCase("New undefined"))
			{
				test.log(LogStatus.PASS, "New SubRecipe form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New SubRecipe form loaded Failed");
			}

			Thread.sleep(3000);
			
			//Clear the name field
			driver.findElement(By.name(excel.getData(2, 51, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(2, 51, 3))).sendKeys(Utility.getProperty("Inventory_SubRecipe_Name"));
			Thread.sleep(2000);
			
			//Enable the Calculate COGS On Cost Price button
			driver.findElement(By.name(excel.getData(2, 52, 3))).click();
			
				
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(2, 120, 1))).click();
			
			Thread.sleep(3000);
			//Check whether the new Storage Location canceled or not
			if(driver.findElement(By.id(excel.getData(2, 49, 2))).isDisplayed())
			{
				test.log(LogStatus.PASS, "New Sub Recipe Canceled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Recipe Canceled Failed");
			}
			wb.close();
			Thread.sleep(5000);
		}
				
		@Test(enabled=false,priority=56)
		public void Inventory_Sub_Recipes_verifyActive_InActiveButton_Sub_Recipe(WebDriver driver) throws Exception
		{

			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			  
				Thread.sleep(2000);

			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(2, 93, 1))).clear();
			
			Thread.sleep(5000);
			//Click Active or In Active Button
			driver.findElement(By.xpath(excel.getData(2, 92, 1))).click();

			Thread.sleep(5000);
			//Check the Inactive page is loaded or not
			if(driver.findElement(By.cssSelector(excel.getData(2, 118, 4))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Deleted Sub Recipes are here, we are in InActive Page");
				Thread.sleep(10000);
				//Click Active or In Active Button
				driver.findElement(By.xpath(excel.getData(2, 117, 1))).click();

			}
			else if(driver.findElement(By.cssSelector(excel.getData(2, 429, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Deleted Sub Recipes are not here, we are in Active Page");
			}
			wb.close();
			Thread.sleep(5000);
			
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);
			//watchTutorial(driver);
			Thread.sleep(5000);}
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

			
			Thread.sleep(2000);
			//Click the Watch Tutorial Option
			driver.findElement(By.xpath(excel.getData(2, 294, 1))).click();
			WebElement iframe = driver.findElement(By.xpath(excel.getData(2, 295, 1)));
			driver.switchTo().frame(iframe);
			Thread.sleep(3500);
			try
			{
				if(driver.findElement(By.xpath(excel.getData(2, 296, 1))).isDisplayed()&&driver.findElement(By.xpath(excel.getData(2, 292, 1))).isDisplayed())
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
			driver.findElement(By.xpath(excel.getData(2, 297, 1))).click();
			wb.close();
		}
}
