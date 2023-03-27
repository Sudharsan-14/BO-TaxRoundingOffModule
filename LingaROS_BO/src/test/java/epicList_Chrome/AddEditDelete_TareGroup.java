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

import newReadExcelFile_New.ExcelDataConfig;

public class AddEditDelete_TareGroup {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete_TareGroup");

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
			Tare_Group_method_openTareGroupPage(driver);
			Tare_Group_method_refreshTareGroupPage(driver);
			Tare_Group_method_addTareGroup(driver);
			Tare_Group_method_editTareGroup(driver);
			Tare_Group_method_deleteTareGroup(driver);
			Tare_Group_method_cancelButton(driver);
			Tare_Group_method_verifyActive_InActiveButton(driver);
			Tare_Group_method_sameName(driver);
			Thread.sleep(1500);
		}

		@Test(enabled = false, priority=61)
		public void Tare_Group_method_openTareGroupPage(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"tare");
			Thread.sleep(5000);
			try
			{
			//Check Tare Group page opened or not
			if(driver.findElement(By.xpath(excel.getData(5, 587, 1))).getText().equalsIgnoreCase("Tare Group"))
			{
				test.log(LogStatus.PASS, "Tare Group page loaded Successfully");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Tare Group page loaded Failed");
			
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
		}
		
		@Test(enabled = false,priority=62)
		public void Tare_Group_method_refreshTareGroupPage(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(5, 148, 1))).click();
			Thread.sleep(5000);
			//Check Tare Group page opened or not
			if(driver.findElement(By.xpath(excel.getData(5, 587, 1))).getText().equalsIgnoreCase("Tare Group"))
			{
				test.log(LogStatus.PASS, "Tare Group page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Tare Group page loaded Failed");
			}
		}
		
		@Test(enabled = false,priority=64)
		public void Tare_Group_method_addTareGroup(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			for (int i = 0; i < 10; i++) {
				driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
				driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
				driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
			}
			Thread.sleep(3000);
			Thread.sleep(3000);
			//Click the Add or new Tare Group button
			driver.findElement(By.xpath(excel.getData(5, 588, 1))).click();
			Thread.sleep(2000);
			
			//Check whether the new Tare Group form loaded or not
			if(driver.findElement(By.xpath(excel.getData(5, 589, 1))).getText().equalsIgnoreCase("New TareGroup"))
			{
				test.log(LogStatus.PASS, "New TareGroup form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New TareGroup form loaded Failed");
			}
			
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(5, 590, 1))).clear();
			//Enter the required name
			driver.findElement(By.xpath(excel.getData(5, 590, 1))).sendKeys(Utility.getProperty("TareGroupNameWithWeight"));
			Thread.sleep(2000);
			
			//Enable the Default button
			driver.findElement(By.xpath(excel.getData(5, 591, 1))).click();
			
			//Click the Add Weight button
			driver.findElement(By.xpath(excel.getData(5, 592, 1))).click();
			Thread.sleep(2000);
			
			//Enter the Seq.Number
			driver.findElement(By.name(excel.getData(5, 593, 3))).sendKeys("3");
			
			//Enter the name
			driver.findElement(By.xpath(excel.getData(5, 594, 1))).sendKeys("Weight");
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			
			//Click the required option
			driver.findElement(By.xpath(excel.getData(5, 595, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(5, 596, 1))).sendKeys("kg");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(5, 596, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the amount field
			driver.findElement(By.name(excel.getData(5, 597, 3))).clear();
			//Enter the Required Amount
			driver.findElement(By.name(excel.getData(5, 597, 3))).sendKeys("20000");
			
			//Click the Save button
			driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
			WebElement up=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
			//Check weather the new Tare Group is Saved or not
			if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Tare Group Saved Successfully"))
			{
				test.log(LogStatus.PASS, "New Tare Group Saved Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tare Group saved Failed");
			}
			
		
		}
	
		@Test(enabled = false,priority=65)
		public void Tare_Group_method_editTareGroup(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("TareGroupNameWithWeight")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
	
			Thread.sleep(2000);
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(5, 86, 4))).click();
			Thread.sleep(3000);
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(5, 590, 1))).clear();
			Thread.sleep(5000);
			//Enter the required name
			driver.findElement(By.xpath(excel.getData(5, 590, 1))).sendKeys(Utility.getProperty("TareGroupNameWithWeight")+"1");
	
			Thread.sleep(2000);
			//Click the Add weight symbol
			driver.findElement(By.xpath(excel.getData(5, 592, 1))).click();
			Thread.sleep(1000);
			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(5, 598, 1)));
			//Click the close button of new weight button
			int row = rows.size() + 2;
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/div/div["+row+"]/ng-form/div/div[4]/div/a/i")).click();
			
			Thread.sleep(2000);
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(5, 39, 4))).click();
			
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,300)", "");
			Thread.sleep(3000);
			//Click the update
			driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
			
			WebElement up=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,60);
			if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Tare Group Updated Successfully"))
			{
				test.log(LogStatus.PASS, "Tare updated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Tare Updated Failed");
			}
			Thread.sleep(3000);
	
		}
		
		@Test(enabled = false,priority=66)
		public void Tare_Group_method_deleteTareGroup(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
			
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("TareGroupNameWithWeight")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
	
			Thread.sleep(2000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(5, 51, 4))).click();
			
			//Click the Yes button in the popup
			Thread.sleep(1500);
			driver.findElement(By.cssSelector(excel.getData(5, 39, 4))).click();
			Thread.sleep(3000);
			
			//Check the menu item deleted or not
			if(driver.findElement(By.cssSelector(excel.getData(5, 6, 4))).getText().equalsIgnoreCase("Tare group inactivated successfully"))
			{
				test.log(LogStatus.PASS, "Tare Group is deleted Successfully");
			}
			else
			{
				test.log(LogStatus.INFO, "Tare Group is deleted Failed");
			}
			Thread.sleep(7000);
		
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(5, 599, 1))).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector(excel.getData(5, 39, 4))).click();
			
			
			//Click the Yes button in the popup
			Thread.sleep(1500);
			driver.findElement(By.cssSelector(excel.getData(5, 39, 4))).click();
			Thread.sleep(5000);
			
			//Check the item activated or not
			if(driver.findElement(By.cssSelector(excel.getData(5, 6, 4))).getText().equalsIgnoreCase("Tare group activated successfully"))
			{
				test.log(LogStatus.PASS, "Tare group is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Tare group is activated Failed");
			}
		
			Thread.sleep(5000);
		}
		
		@Test(enabled = false,priority=67)
		public void Tare_Group_method_cancelButton(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(6000);
			
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(5, 599, 1))).click();
			Thread.sleep(3000);
			
			
			//Click the Add or new Tare Group button
			driver.findElement(By.xpath(excel.getData(5, 588, 1))).click();
			Thread.sleep(2000);
			//Check whether the new Tare Group form loaded or not
			if(driver.findElement(By.xpath(excel.getData(5, 589, 1))).getText().equalsIgnoreCase("New TareGroup"))
			{
				test.log(LogStatus.PASS, "New TareGroup form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New TareGroup form loaded Failed");
			}
			
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(5, 590, 1))).clear();
			//Enter the required name
			driver.findElement(By.xpath(excel.getData(5, 590, 1))).sendKeys(Utility.getProperty("TareGroupNameWithWeight")+"1");
			Thread.sleep(2000);
	
			//Click the Cancel button
			driver.findElement(By.xpath(excel.getData(5, 250, 1))).click();
			Thread.sleep(2000);
			//Check whether the new discount canceled or not
			if(driver.findElement(By.xpath(excel.getData(5, 588, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "TareGroup Canceled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "TareGroup Canceled Failed");
			}
	
			Thread.sleep(5000);
		}
		
		@Test(enabled = false,priority=68)
		public void Tare_Group_method_verifyActive_InActiveButton(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
		
			Thread.sleep(5000);
			//Click Active or In Active Button
			driver.findElement(By.xpath(excel.getData(5, 599, 1))).click();
	
			Thread.sleep(5000);
			//Check the New Display Group form is loaded or not
			if(driver.findElement(By.cssSelector(excel.getData(5, 39, 4))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Deleted groups are here, we are in InActive Page");
				Thread.sleep(10000);
				//Click Active or In Active Button
				driver.findElement(By.xpath(excel.getData(5, 599, 1))).click();
	
			}
			else if(driver.findElement(By.cssSelector("a.btn.btn-small.btn-danger")).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Deleted groups are not here, we are in Active Page");
			}
			Thread.sleep(5000);
		}
		
		@Test(enabled = false,priority=69)
		public void Tare_Group_method_sameName(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			//Click the Add or new Tare Group button
			driver.findElement(By.xpath(excel.getData(5, 588, 1))).click();
			Thread.sleep(5000);
			
			
			//Clear the name field
			driver.findElement(By.xpath(excel.getData(5, 590, 1))).clear();
			//Enter the required name
			driver.findElement(By.xpath(excel.getData(5, 590, 1))).sendKeys(Utility.getProperty("TareGroupNameWithWeight")+"1");
			Thread.sleep(2000);
			
			//Enable the Default button
			driver.findElement(By.xpath(excel.getData(5, 591, 1))).click();
			
			//Click the Add Weight button
			driver.findElement(By.xpath(excel.getData(5, 592, 1))).click();
			Thread.sleep(2000);
			
			//Enter the Seq.Number
			driver.findElement(By.name(excel.getData(5, 593, 3))).sendKeys("3");
			
			//Enter the name
			driver.findElement(By.xpath(excel.getData(5, 594, 1))).sendKeys("Weight");
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			
			//Click the required option
			driver.findElement(By.xpath(excel.getData(5, 595, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(5, 596, 1))).sendKeys("kg");
			//Press the Enter Key
			driver.findElement(By.xpath(excel.getData(5, 596, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the amount field
			driver.findElement(By.name(excel.getData(5, 597, 3))).clear();
			//Enter the Required Amount
			driver.findElement(By.name(excel.getData(5, 597, 3))).sendKeys("20000");
			
			//Click the Save button
			driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
			Thread.sleep(3000);
			
			//Check whether the new up charges form loaded or not
			if(driver.findElement(By.cssSelector(excel.getData(5, 6, 4))).getText().equalsIgnoreCase("Validation Error(s)"))
			{
				test.log(LogStatus.PASS, "New Tare Saved Failed and show error message");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tare saved Successfully");
			}
			
		
		}

}