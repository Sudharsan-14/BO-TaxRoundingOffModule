package epicList_Chrome;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class Settings_Store_ScaleBarcode {

public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settins_Store_Scale_Barcode");

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
		{
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
			{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath("//b[.='User Login']")).getText().equalsIgnoreCase("User Login"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully for Dealer App");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed for Dealer App");
			}
			Thread.sleep(3000);
			//Close the Browser
			driver.close();
		}
		else
		{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath("//div[@id='content']/div/div/h2")).getText().equalsIgnoreCase("Sign in"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully LingaPOs");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed LingaPos");
			}
			Thread.sleep(3000);
			//Close the Browser
			driver.close();
		}
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
			Scale_Barcode_Open_Scale_Barcode(driver);
			Scale_Barcode_Refresh_Page(driver);
		//	Scale_Barcode_Pagination(driver);
			Scale_Barcode_Add(driver);
		    Scale_Barcode_Edit(driver);
		    Scale_Barcode_Inactivate(driver);
		    Scale_Barcode_Activate(driver);
		    Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=151)
		public void Scale_Barcode_Open_Scale_Barcode(WebDriver driver) throws Exception
		{	
			//ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"newScaleBarCodeSettings");
			Thread.sleep(5000);
			try
			{
			//Check POS Licenses page is opened or not
			if(driver.findElement(By.xpath("//a[.='Scale Barcode Settings']")).getText().equalsIgnoreCase("Scale Barcode Settings"))
			{
				test.log(LogStatus.PASS, "Scale Barcode Settings page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Scale Barcode Settings page loaded Failed");
			
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
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=152)
		public void Scale_Barcode_Refresh_Page(WebDriver driver) throws Exception
		{
			//ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath("//a[@class='btn btn-md btn-reload']")).click();
			
			Thread.sleep(4000);
			if(driver.findElement(By.xpath("//a[.='Scale Barcode Settings']")).getText().equalsIgnoreCase("Scale Barcode Settings"))
			{
				test.log(LogStatus.PASS, "Scale Barcode Settings page Refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Scale Barcode Settings page Refreshed Failed");
			}
			Thread.sleep(3000);
		}
		@Test(priority=153,enabled=false)
		public void Scale_Barcode_Pagination(WebDriver driver) throws InterruptedException
		{			
			
			try {
				if(driver.findElement(By.xpath("//tr//td[.='No records found']")).isDisplayed()) {
	            	test.log(LogStatus.INFO, "There is no Scale Barcode for this store as of now, so no pagination available");
				}
				}
				catch(Exception e)
				{
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			Thread.sleep(1000);
			
			//Click the Pagination option as 10
			driver.findElement(By.xpath("//div[@ng-table-pagination='params']//li[2]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 10");
			//Create the web element for Service Charge Table
			List<WebElement> results = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[3]/table"));
			for (WebElement result : results){						
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.xpath("//a[@class='btn btn-small btn-danger']"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(2000);
			//Click the Pagination option as 5
			driver.findElement(By.xpath("//div[@ng-table-pagination='params']//li[1]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 5");
			//Create the web element for Service Charge Table
			List<WebElement> results3 = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[3]/table"));
			for (WebElement result : results3){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.xpath("//a[@class='btn btn-small btn-danger']"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(2000);
		}
		}
		@Test(enabled=false,priority=154)
		public void Scale_Barcode_Add(WebDriver driver) throws Exception
		{	
		driver.findElement(By.xpath("//button[normalize-space()='Scale Barcode Length']")).click();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//span[normalize-space()='Scale Barcode Length']")).getText().equalsIgnoreCase("Scale Barcode Length"))
		{
			test.log(LogStatus.PASS, "Scale Barcode Length popup opened successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Scale Barcode Length popup opened fail");
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(Utility.getProperty("Settings_Store_Scale_Barcode_Nameone"));
		driver.findElement(By.xpath("//input[@name='prefix']")).sendKeys(Utility.getProperty("Settings_Store_Scale_Barcode_Prefixone"));
		driver.findElement(By.xpath("//input[@name='pluSp']")).sendKeys("2");
		driver.findElement(By.xpath("//input[@placeholder='No. of digits in PLU']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@name='priceSp']")).sendKeys("8");
		driver.findElement(By.xpath("//input[@placeholder='No. of digits in Price']")).sendKeys("5");
		driver.findElement(By.xpath("//span[normalize-space()='Save']")).click();
		Thread.sleep(2000);
		 if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Barcode Setting Saved Successfully"))
			{
				test.log(LogStatus.PASS, "Barcode Setting Saved Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Barcode Setting Saved Failed");
			} 
		 Thread.sleep(2000);
			driver.findElement(By.xpath("//button[normalize-space()='Scale Barcode Length']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='name']")).sendKeys(Utility.getProperty("Settings_Store_Scale_Barcode_Nametwo"));
			driver.findElement(By.xpath("//input[@name='prefix']")).sendKeys(Utility.getProperty("Settings_Store_Scale_Barcode_Prefixtwo"));
			driver.findElement(By.xpath("//input[@name='pluSp']")).sendKeys("2");
			driver.findElement(By.xpath("//input[@placeholder='No. of digits in PLU']")).sendKeys("5");
			driver.findElement(By.xpath("//input[@name='weightSp']")).sendKeys("8");
			driver.findElement(By.xpath("//input[@placeholder='No. of digits in Weight']")).sendKeys("5");
			driver.findElement(By.xpath("//span[normalize-space()='Save']")).click();
			Thread.sleep(2000);
			 if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Barcode Setting Saved Successfully"))
				{
					test.log(LogStatus.PASS, "Another Barcode Setting Saved Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Another Barcode Setting Saved Failed");
				} 
			 Thread.sleep(2000);
		}	
		@Test(enabled=false,priority=154)
		public void Scale_Barcode_Edit(WebDriver driver) throws Exception
		{	
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Settings_Store_Scale_Barcode_Nameone"));
            driver.findElement(By.xpath("//a[@ng-click='openBarcodeForm(barcodeSetting.id)']//i")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@name='priceSp']")).clear();
    		driver.findElement(By.xpath("//input[@placeholder='No. of digits in Price']")).clear();
    		Thread.sleep(2000);
    		driver.findElement(By.xpath("//input[@name='weightSp']")).sendKeys("8");
			driver.findElement(By.xpath("//input[@placeholder='No. of digits in Weight']")).sendKeys("5");
    		driver.findElement(By.xpath("//button[@type='submit']")).click();
    		WebElement ele=driver.findElement(By.xpath("//div[@role='alert']/span/span"));
			 
			 WebDriverWait wait=new WebDriverWait(driver,60);
			 
			 if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Barcode Setting Updated Successfully"))
				{
					test.log(LogStatus.PASS, "Barcode Setting Updated Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Barcode Setting Updated Failed");
				} 
			 driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			 Thread.sleep(2000);
			 driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Settings_Store_Scale_Barcode_Nametwo"));
	            driver.findElement(By.xpath("//a[@ng-click='openBarcodeForm(barcodeSetting.id)']//i")).click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("//input[@name='weightSp']")).clear();
	    		driver.findElement(By.xpath("//input[@placeholder='No. of digits in Weight']")).clear();
	    		Thread.sleep(2000);
	    		driver.findElement(By.xpath("//input[@name='priceSp']")).sendKeys("8");
				driver.findElement(By.xpath("//input[@placeholder='No. of digits in Price']")).sendKeys("5");
	    		driver.findElement(By.xpath("//button[@type='submit']")).click();
	    		WebElement ele1=driver.findElement(By.xpath("//div[@role='alert']/span/span"));
				 
				 WebDriverWait wait1=new WebDriverWait(driver,60);
				 
				 if(wait1.until(ExpectedConditions.visibilityOf(ele1)).getText().equalsIgnoreCase("Barcode Setting Updated Successfully"))
					{
						test.log(LogStatus.PASS, "Another Barcode Setting Updated Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Another Barcode Setting Updated Failed");
					} 
				 driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
					Thread.sleep(2000);

		}
		@Test(enabled=false,priority=154)
		public void Scale_Barcode_Inactivate(WebDriver driver) throws Exception
		{	
			 driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Settings_Store_Scale_Barcode_Nameone"));
			 driver.findElement(By.xpath("//i[@class='fa fa-trash-o']")).click();
			 Thread.sleep(2000);
			 driver.findElement(By.linkText("Yes")).click();
			 Thread.sleep(3000);
			 driver.findElement(By.xpath("//a[@class='btn btn-md btn-reload']")).click();
	try
	{
			 WebElement ele=driver.findElement(By.xpath("//div[@role='alert']/span/span"));
			 
			 WebDriverWait wait=new WebDriverWait(driver,60);
			 
			 if(wait.until(ExpectedConditions.visibilityOf(ele)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Barcode Setting Inactivated Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Barcode Setting Inactivated Failed");
				} 
	}
	catch(Exception e) {}
			 Thread.sleep(5000);
			 driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			 Thread.sleep(2000);
			 driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Settings_Store_Scale_Barcode_Nametwo"));
			 driver.findElement(By.xpath("//i[@class='fa fa-trash-o']")).click();
			 Thread.sleep(2000);
			 driver.findElement(By.linkText("Yes")).click();
			 Thread.sleep(2000);
			 driver.findElement(By.xpath("//a[@class='btn btn-md btn-reload']")).click();
			 WebElement ele1=driver.findElement(By.xpath("//div[@role='alert']/span/span"));
			 
			 WebDriverWait wait1=new WebDriverWait(driver,60);
			 
			 if(wait1.until(ExpectedConditions.visibilityOf(ele1)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Another Barcode Setting Inactivated Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Another Barcode Setting Inactivated Failed");
				} 
			 Thread.sleep(5000);
			 driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			 Thread.sleep(2000);
		}
		 @Test(enabled=false,priority=154)
		 public void Scale_Barcode_Activate(WebDriver driver) throws Exception
		 {
			 driver.findElement(By.xpath("//input[@type='checkbox']")).click();
			 driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Settings_Store_Scale_Barcode_Nameone"));
			 driver.findElement(By.xpath("//tbody/tr[1]/td[6]/span[2]/a[1]")).click();
			 driver.findElement(By.linkText("Yes")).click();
			
			 WebElement ele=driver.findElement(By.xpath("//div[@role='alert']/span/span"));
			 
			 WebDriverWait wait=new WebDriverWait(driver,60);
			 
			 if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Barcode Setting Activated Successfully"))
				{
					test.log(LogStatus.PASS, "Barcode Setting Activated Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Barcode Setting Activated Failed");
				} 	 
			 driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			 Thread.sleep(5000);
			 driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Settings_Store_Scale_Barcode_Nametwo"));
			 driver.findElement(By.xpath("//tbody/tr[1]/td[6]/span[2]/a[1]")).click();
			 driver.findElement(By.linkText("Yes")).click();
			 WebElement ele1=driver.findElement(By.xpath("//div[@role='alert']/span/span"));
			 
			 WebDriverWait wait1=new WebDriverWait(driver,60);
			 
			 if(wait1.until(ExpectedConditions.visibilityOf(ele1)).getText().equalsIgnoreCase("Barcode Setting Activated Successfully"))
				{
					test.log(LogStatus.PASS, "Another Barcode Setting Activated Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Another Barcode Setting Activated Failed");
				} 	 
			// driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		Thread.sleep(5000);
		}		
}




