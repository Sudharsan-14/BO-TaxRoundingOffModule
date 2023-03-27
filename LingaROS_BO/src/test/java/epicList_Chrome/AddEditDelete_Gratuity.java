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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;


public class AddEditDelete_Gratuity {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete_Gratuity");

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
		{		
			Browser a = new Browser();
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
			Gratuity_method_GratuitiesPage(driver);
			Gratuity_method_refreshGratuitiesPage(driver);
			Gratuity_method_addGratuities_Fixed(driver);
			Gratuity_method_editGratuity(driver);
			Gratuity_method_deleteGratuity(driver);
			Gratuity_method_closeButton(driver);
			Gratuity_method_sameName(driver);
			Thread.sleep(1500);
		} 
			
	@Test(enabled=false,priority=31)
	public void Gratuity_method_GratuitiesPage(WebDriver driver) throws Exception
	{ 
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		//Thread.sleep(5000);
	driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"gratuity");
	Thread.sleep(8000);
	
	try
	{
	//Check items page opened or not
	if(driver.findElement(By.xpath(excel.getData(4, 24, 1))).getText().equalsIgnoreCase("Gratuities"))
	{
		test.log(LogStatus.PASS, "Gratuities page loaded Successfully");
	
		String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		String s="data:image/png;base64,"+scnShot;
		test.log(LogStatus.INFO,test.addScreenCapture(s));
	}  
	else
	{
		test.log(LogStatus.FAIL, "Gratuities page loaded Failed");
		
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
	Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=32)
	public void Gratuity_method_refreshGratuitiesPage(WebDriver driver) throws Exception
	{
		
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Click the refresh button
		driver.findElement(By.xpath(excel.getData(4, 3, 1))).click();
		Thread.sleep(5000);
		//Check Check based discount page opened or not
		if(driver.findElement(By.xpath(excel.getData(4, 24, 1))).getText().equalsIgnoreCase("Gratuities"))
		{
			test.log(LogStatus.PASS, "Gratuities page refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Gratuities page refreshed Failed");
		}
		Thread.sleep(5000);

	}
	
	@Test(enabled=false,priority=34)
	public void Gratuity_method_addGratuities_Fixed(WebDriver driver) throws Exception
	{
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		for (int i = 0; i < 10; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		
		Thread.sleep(5000);
		//Click on the Add Gratuities option
		driver.findElement(By.id(excel.getData(4, 24,2))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
				if(driver.findElement(By.xpath(excel.getData(4, 25,1))).getText().equalsIgnoreCase("New Gratuity"))
				{
			test.log(LogStatus.PASS, "New Gratuity form loaded Successfully");
		}
				else
		{
			test.log(LogStatus.FAIL, "New Gratuity form loaded Failed");
		}

		Thread.sleep(2000);
		//Clear the Name field
		driver.findElement(By.name(excel.getData(4,26,3))).clear();
		//Enter the Name
		driver.findElement(By.name(excel.getData(4,26,3))).sendKeys(Utility.getProperty("GratuityName_Fixed"));
		Thread.sleep(2000);
		
		//Create the object for the Select Class
		Select applyTo = new Select(driver.findElement(By.name(excel.getData(4, 27, 3))));
		applyTo.selectByVisibleText("Fixed");
		
		//Clear the percentage field
		driver.findElement(By.name(excel.getData(4, 28, 3))).clear();
		//Enter the required percentage
		driver.findElement(By.name(excel.getData(4, 28, 3))).sendKeys("5");
		
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(3, 572,1))).click();
		Thread.sleep(2000);
		//Check whether the new discount saved or not
		if(driver.findElement(By.xpath(excel.getData(3, 39,1))).getText().equalsIgnoreCase("Gratuity saved successfully"))
		{
			test.log(LogStatus.PASS, "Gratuity saved Successfully for Fixed");
		}
		else
		{
			test.log(LogStatus.FAIL, "Gratuity saved Failed for Fixed");
		}

		Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=35)
	public void Gratuity_method_editGratuity(WebDriver driver) throws Exception
	{
	
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(3, 40,1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(3, 40,1))).sendKeys(Utility.getProperty("GratuityName_Fixed")+"1");
		Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(3, 40,1))).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath(excel.getData(3, 40,1))).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		//Click the Edit icon
		
		WebDriverWait o = new WebDriverWait(driver, 10);
		WebElement ele = o.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("i.fa.fa-pencil")));
		ele.click();
		//driver.findElement(By.cssSelector(excel.getData(4, 11, 4))).click();
		
		Thread.sleep(5000);

		//Clear the Name field
		driver.findElement(By.name(excel.getData(4,26,3))).clear();
		//Enter the Name
		driver.findElement(By.name(excel.getData(4,26,3))).sendKeys(Utility.getProperty("GratuityName_Fixed")+"1");
		Thread.sleep(2000);
		
		//Create the object for the Select Class
		Select applyTo = new Select(driver.findElement(By.name(excel.getData(4, 27, 3))));
		applyTo.selectByVisibleText("Varying");
		
		//Clear the minimum percentage field
		driver.findElement(By.name(excel.getData(4, 30,3))).clear();
		//Enter the required minimum percentage
		driver.findElement(By.name(excel.getData(4, 30,3))).sendKeys("5");
		//Clear the maximum percentage field
		driver.findElement(By.name(excel.getData(4, 31,3))).clear();
		//Enter the required maximum percentage
		driver.findElement(By.name(excel.getData(4, 31,3))).sendKeys("7");
		
		Thread.sleep(3000);
		//Click the Save button
		driver.findElement(By.cssSelector(excel.getData(4, 12, 4))).click();
		Thread.sleep(2000);
		
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.xpath(excel.getData(3, 39,1))).getText().equalsIgnoreCase("Gratuity updated successfully"))
		{
			test.log(LogStatus.PASS, "Gratuity is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Gratuity is updated Failed");
		}

		Thread.sleep(5000);

	}
	
	@Test(enabled=false,priority=36)
	public void Gratuity_method_deleteGratuity(WebDriver driver) throws Exception
	{
	
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(3, 40,1))).clear();
		Thread.sleep(5000);
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(3, 40,1))).sendKeys(Utility.getProperty("GratuityName_Fixed")+"11");
		Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(1, 40,1))).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath(excel.getData(3, 40,1))).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		//Click the Delete button			
		driver.findElement(By.cssSelector(excel.getData(3, 42,4))).click();
		//Click the Yes button in the popup
		Thread.sleep(1500);
		driver.findElement(By.cssSelector(excel.getData(4, 14,4))).click();
		
		Thread.sleep(3000);
		//Check the menu item deleted or not
		if(driver.findElement(By.xpath(excel.getData(3, 39,1))).getText().equalsIgnoreCase("Gratuity inactivated successfully"))
		{
			test.log(LogStatus.PASS, "Gratuity is deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Gratuity is deleted Failed");
		}

		Thread.sleep(5000);	
		//Click the Active Button
		driver.findElement(By.xpath(excel.getData(3, 44,1))).click();
		
		Thread.sleep(3000);
		//Click the success button
		driver.findElement(By.cssSelector(excel.getData(4, 33,4))).click();
		////Click the Yes button in the popup
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(excel.getData(4, 14,4))).click();
		Thread.sleep(3000);
		//Check the field activated or not
		if(driver.findElement(By.xpath(excel.getData(3, 39,1))).getText().equalsIgnoreCase("gratuity activated successfully"))
		{
			test.log(LogStatus.PASS, "Gratuity is activated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Gratuity is activated Failed");
		}
		Thread.sleep(3000);
	}

	@Test(enabled=false,priority=37)
	public void Gratuity_method_closeButton(WebDriver driver) throws Exception
	{

		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(2000);
		//Click the Active Button
		driver.findElement(By.xpath(excel.getData(3, 44,1))).click();
		Thread.sleep(3000);
				
		Thread.sleep(5000);
		//Click on the Add Gratuity option
		driver.findElement(By.id(excel.getData(4,24,2))).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(4,25,1))).getText().equalsIgnoreCase("New Gratuity"))
		{
			test.log(LogStatus.PASS, "New Gratuity form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Gratuity form loaded Failed");
		}

		
		//Clear the Name field
		driver.findElement(By.name(excel.getData(4,26,3))).clear();
		//Enter the Name
		driver.findElement(By.name(excel.getData(4,26,3))).sendKeys(Utility.getProperty("GratuityName_Fixed"));
		Thread.sleep(2000);
			
		//Click the Cancel button
		driver.findElement(By.xpath(excel.getData(3, 45,1))).click();
		
		//Check whether the new discount canceled or not
		if(driver.findElement(By.xpath(excel.getData(4, 24,1))).getText().equalsIgnoreCase("Gratuities"))
		{
			test.log(LogStatus.PASS, "Gratuities Canceled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Gratuities Canceled Failed");
		}

		Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=38)
	public void Gratuity_method_sameName(WebDriver driver) throws Exception
	{
		
		
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		//Click on the Add Gratuities option
		driver.findElement(By.id(excel.getData(4,24,2))).click();
		Thread.sleep(3000);

		//Clear the Name field
		driver.findElement(By.name(excel.getData(4,26,3))).clear();
		//Enter the Name
		driver.findElement(By.name(excel.getData(4,26,3))).sendKeys(Utility.getProperty("GratuityName_Fixed")+"1");
		Thread.sleep(2000);
		
		//Create the object for the Select Class
		Select applyTo = new Select(driver.findElement(By.name("type")));
		applyTo.selectByVisibleText("Varying");
		
		//Clear the minimum percentage field
		driver.findElement(By.name(excel.getData(4,30,3))).clear();
		//Enter the required minimum percentage
		driver.findElement(By.name(excel.getData(4,30,3))).sendKeys("5");
		//Clear the maximum percentage field
		driver.findElement(By.name(excel.getData(4,31,3))).clear();
		//Enter the required maximum percentage
		driver.findElement(By.name(excel.getData(4,31,3))).sendKeys("7");
		
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(3, 572,1))).click();
		Thread.sleep(3000);
		
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.xpath("//p[contains(.,'Name already exists')]")).getText().equalsIgnoreCase("Name already exists"))
		{
			test.log(LogStatus.PASS, "Gratuity is updated failed and show the error message");
		}
		else
		{
			test.log(LogStatus.FAIL, "Gratuity is updated successfully");
		}

		//Click the Cancel button
		driver.findElement(By.xpath(excel.getData(3, 45,1))).click();

		
		
		Thread.sleep(5000);
		if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);//watchTutorial(driver);
		Thread.sleep(5000);
		}
	   
	}
	@Test(priority=3, enabled=false)
	public void watchTutorial(WebDriver driver) throws Exception
	{
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(2000);
		//Click the Watch Tutorial Option
		driver.findElement(By.xpath(excel.getData(4,34,1))).click();
		WebElement iframe = driver.findElement(By.xpath(excel.getData(4,35,1)));
		driver.switchTo().frame(iframe);
		Thread.sleep(3500);
		try
		{
			if(driver.findElement(By.xpath(excel.getData(4,36,1))).isDisplayed()&&driver.findElement(By.xpath("//button/div[.='Share']")).isDisplayed())
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
		driver.findElement(By.xpath(excel.getData(4,37,1))).click();
	}
}
