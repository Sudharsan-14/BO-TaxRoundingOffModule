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


public class Inventory_Ingredient_Sub_Categories {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Ingredient_Sub_Categories");

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
//			SendMail.snedMailWithAttachment();
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
			Inventory_Ingredient_Sub_Categories_openpage(driver);
			Inventory_Ingredient_Sub_Categories_refresh_page(driver);
			Inventory_Ingredient_Sub_Categories_add_Invetory_Category(driver);
			Inventory_Ingredient_Sub_Categories_edit_Invetory_Category(driver);
			Inventory_Ingredient_Sub_Categories_delete_Invetory_Category(driver);
			Inventory_Ingredient_Sub_Categories_closeButton(driver);
			Inventory_Ingredient_Sub_Categories_verifyActive_InActiveButton(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=5)
		public void Inventory_Ingredient_Sub_Categories_openpage(WebDriver driver) throws Exception
		{
            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(4000);
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"masters/subCategory");

			
			Thread.sleep(4000);
			try
			{
			//Check Ingredient Sub_Categories page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 2308, 1))).getText().equalsIgnoreCase("Sub Category"))
			{
				test.log(LogStatus.PASS, "Ingredient Sub_Categories page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Ingredient Sub_Categories page loaded Failed");
			
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
				
		@Test(enabled=false,priority=6)
		public void Inventory_Ingredient_Sub_Categories_refresh_page(WebDriver driver) throws Exception
		{

            File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(4000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(3, 1171, 1))).click();
			Thread.sleep(10000);
			//Check Inventory Sub_Categories page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 2308, 1))).getText().equalsIgnoreCase("Sub Category"))
			{
				test.log(LogStatus.PASS, "Inventory Sub_Categories page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inventory Sub_Categories page refreshed Failed");
			}
			Thread.sleep(4000);wb.close();
		
		}
			
		@Test(enabled=false,priority=7)
		public void Inventory_Ingredient_Sub_Categories_add_Invetory_Category(WebDriver driver) throws Exception
		{
			   File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(4000);
			//Click on the Add Category option
			driver.findElement(By.id("createSubCategory")).click();
			Thread.sleep(3000);
			
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 2309, 1))).getText().equalsIgnoreCase("New Sub Category"))
			{
				test.log(LogStatus.PASS, "New Sub Category form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Category form loaded Failed");
			}
		
			Thread.sleep(3000);
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 1175, 3))).clear();
//			Thread.sleep(3000);
			//Enter the Name
			driver.findElement(By.name(excel.getData(3, 1175, 3))).sendKeys(Utility.getProperty("Inventory_Ingredient_Sub_Category"));
			Thread.sleep(2000);
			 
			Thread.sleep(2000);
			//Click the Category
			driver.findElement(By.xpath(excel.getData(3, 2182, 1))).click();
			//Select the Category
			driver.findElement(By.xpath(excel.getData(3, 2183, 1))).sendKeys("FOOD");
			//Enter the Category
			driver.findElement(By.xpath(excel.getData(3, 2183, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the description field
			driver.findElement(By.xpath(excel.getData(3, 2310, 1))).clear();
			//Enter the description
			driver.findElement(By.xpath(excel.getData(3, 2310, 1))).sendKeys("Inventory Ingredient Sub Category");
			
			Thread.sleep(2000);
			//Clear the Ingredient Sub Category code
			driver.findElement(By.xpath(excel.getData(3, 2311, 1))).clear();
			//Enter the Code
			driver.findElement(By.xpath(excel.getData(3, 2311, 1))).sendKeys("1234");
			
			
			Thread.sleep(2000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			Thread.sleep(2000);
			WebElement ingCat=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
						
			//Check whether the New Sub Category saved or not
			if(wait.until(ExpectedConditions.visibilityOf(ingCat)).getText().equalsIgnoreCase("Sub Category saved successfully!."))
			{
				test.log(LogStatus.PASS, "New Ingredient Sub Category Saved Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Ingredient Sub Category Save Failed");
			}
		
			Thread.sleep(4000);wb.close();
		}
		 
		@Test(enabled=false,priority=8)
		public void Inventory_Ingredient_Sub_Categories_edit_Invetory_Category(WebDriver driver) throws Exception
		{
			
			  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(4000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
			Thread.sleep(3000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Inventory_Ingredient_Sub_Category"));
			Thread.sleep(4000);
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 1175, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(3, 1175, 3))).sendKeys(Utility.getProperty("Inventory_Ingredient_Sub_Category")+"1");
			
		
			Thread.sleep(2000);
			//Click the update
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			WebElement ingCat=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
						
			//Check whether the category Updated or not
			if(wait.until(ExpectedConditions.visibilityOf(ingCat)).getText().equalsIgnoreCase("Sub Category updated successfully!."))
			{
				test.log(LogStatus.PASS, "New Ingredient Sub Category updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Ingredient Sub Category updated Failed");
			}
		
			Thread.sleep(4000);wb.close();
		
		}
		
		@Test(enabled=false,priority=9)
		public void Inventory_Ingredient_Sub_Categories_delete_Invetory_Category(WebDriver driver) throws Exception
		{
			   File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(3,40, 1))).clear();
			Thread.sleep(4000);
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(Utility.getProperty("Inventory_Ingredient_Sub_Category")+"1");
			
			Thread.sleep(4000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).click();
			
			Thread.sleep(2000);
			//Click the Yes button in the popup
			driver.findElement(By.xpath(excel.getData(3, 545, 1))).click();
			
			WebElement ingCat=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,90);
						
			//Check whether the category deleted or not
			if(wait.until(ExpectedConditions.visibilityOf(ingCat)).getText().equalsIgnoreCase("Sub Category InActivated Successfully!."))
			{
				test.log(LogStatus.PASS, "New Ingredient Sub Category deleted Sucessfully");
			}
			else
			{ 
				test.log(LogStatus.FAIL, "New Ingredient Sub Category deleted Failed");wb.close();
			}
		}
		
		@Test(enabled=false,priority=10)
		public void Inventory_Ingredient_Sub_Categories_closeButton(WebDriver driver) throws Exception
		{ 
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//Click the Active Button			
			driver.findElement(By.xpath(excel.getData(3, 144, 1))).click();

			Thread.sleep(4000);
			//Click the success button
			driver.findElement(By.cssSelector(excel.getData(3, 209, 4))).click();
			Thread.sleep(1000);		
			//Click the Yes button in the popup
			driver.findElement(By.xpath(excel.getData(3, 545, 1))).click();
			
			
			Thread.sleep(3000);
			//Check the category activated or not
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Sub Category activated Successfully!."))
			{
				test.log(LogStatus.PASS, "Ingredient Sub Category is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Ingredient Sub Category is activated Failed");
			}
			
			Thread.sleep(4000);
			//Click on the Add Category option
			driver.findElement(By.id("createSubCategory")).click();
			Thread.sleep(3000);
			
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 2309, 1))).getText().equalsIgnoreCase("New Sub Category"))
			{
				test.log(LogStatus.PASS, "New Sub Category form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Sub Category form loaded Failed");
			}
		
			Thread.sleep(3000);
			
			//Clear the name field
			driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Inventory_Ingredient_Sub_Category"));
			Thread.sleep(2000);
				
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(3, 145, 1))).click();
			
			Thread.sleep(3000);
			//Check whether the new Ingredient Sub Category canceled or not
			if(driver.findElement(By.id("createSubCategory")).isDisplayed())
			{
				test.log(LogStatus.PASS, "New Ingredient Sub Category Canceled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Ingredient Sub Category Canceled Failed");
			} 
		
			Thread.sleep(4000);wb.close();
		}
				
		@Test(enabled=false,priority=11)
		public void Inventory_Ingredient_Sub_Categories_verifyActive_InActiveButton(WebDriver driver) throws Exception
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
		
			Thread.sleep(5000);
			//Check the Inactive page is loaded or not
			if(driver.findElement(By.cssSelector(excel.getData(3, 209, 4))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Deleted Sub_Categories are here, we are in InActive Page");
				Thread.sleep(10000);
				//Click Active or In Active Button
				driver.findElement(By.xpath(excel.getData(3, 144, 1))).click();
				} 
			else 
				
			if(driver.findElement(By.cssSelector(excel.getData(3, 244, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Deleted Category are not here, we are in Active Page");
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
