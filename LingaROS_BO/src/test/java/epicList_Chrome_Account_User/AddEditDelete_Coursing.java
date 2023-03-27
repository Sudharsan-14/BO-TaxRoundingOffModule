package epicList_Chrome_Account_User;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AddEditDelete_Coursing {
	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	
	ExtentTest test = rep.startTest("AddEditDelete Coursing");

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
			//Close the Browser_Account_Level_User
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
			if(driver.findElement(By.xpath("//div[@id='x-content-band-1']/div/div[2]/h2")).getText().equalsIgnoreCase("Account Login"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully LingaPOs");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed LingaPos");
			}
			Thread.sleep(3000);
			//Close the Browser_Account_Level_User
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
			Coursing_method_openCoursing(driver);
			Coursing_method_refreshCoursing_Page(driver);
			Coursing_method_add_Coursing(driver);
			Coursing_method_edit_Coursing(driver);
			Coursing_method_delete_Coursing(driver);
			Coursing_method_cancelCoursing(driver);
			Coursing_method_inactiveAndActive_Button(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=21)
		public void Coursing_method_openCoursing(WebDriver driver) throws Exception
		{

			
			//Enter the Coursing URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"course");
			
			Thread.sleep(8000);
			
			try
			{
			//Check Coursing page opened or not
			if(driver.findElement(By.xpath("//a[.='Coursing']")).getText().equalsIgnoreCase("Coursing"))
			{
				test.log(LogStatus.PASS, "Coursing page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Coursing page loaded Failed");
				
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
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=22)
		public void Coursing_method_refreshCoursing_Page(WebDriver driver) throws Exception
		{
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a[1]/i")).click();
			Thread.sleep(5000);
			
			//Check Coursing page opened or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[1]/div/h3")).getText().equalsIgnoreCase("Coursing"))
			{
				test.log(LogStatus.PASS, "Coursing page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Coursing page loaded Failed");
			}
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=24)
		public void Coursing_method_add_Coursing(WebDriver driver) throws Exception
		{
			for (int i = 0; i < 10; i++) {
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
			Thread.sleep(5000);
			//Click the Add coursing button
			driver.findElement(By.xpath("//button[@id='course']")).click();
			
			Thread.sleep(2000);
			//Check weather the new coursing form loaded or not
			if(driver.findElement(By.xpath("//span[.='New Coursing']")).getText().equalsIgnoreCase("New Coursing"))
			{
				test.log(LogStatus.PASS, "New Course form Loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Course form Loaded Fail");
			}
			
			Thread.sleep(6000);
			//Clear the name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[1]/input")).clear();
			//Enter the required name
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[1]/input")).sendKeys(Utility.getProperty("Coursing_Name"));
			
			//Clear the priority field
			driver.findElement(By.name("priority")).clear();
			//Enter the required priority field
			driver.findElement(By.name("priority")).sendKeys("2");
			
			Thread.sleep(2000);
			//Click the save button
			driver.findElement(By.xpath("//button[@id='saveCourse']")).click();
			
			Thread.sleep(2000);
			//Check weather the new course is saved or not
			if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Coursing saved successfully"))
			{
				test.log(LogStatus.PASS, "New Course Saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Course saved fail");
			}
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=25)
		public void Coursing_method_edit_Coursing(WebDriver driver) throws Exception
		{

			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Coursing_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[1]/input")).clear();
			//Enter the required name
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[1]/input")).sendKeys(Utility.getProperty("Coursing_Name")+"1");

			Thread.sleep(1000);
			//Click the Update button
			driver.findElement(By.xpath("//button[@id='saveCourse']")).click();
			
			WebElement up=driver.findElement(By.xpath("//div[@role='alert']/span/span"));
			WebDriverWait wait=new WebDriverWait(driver,60);
			//Check weather the new coursing is updated or not
			if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Coursing updated successfully"))
			{
				test.log(LogStatus.PASS, "New Course updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Course updated fail");
			}
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=26)
		public void Coursing_method_delete_Coursing(WebDriver driver) throws Exception
		{
			Thread.sleep(5000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Coursing_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(5000);
			//Click the Delete button
			driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
			
			Thread.sleep(1500);
			//Click the Yes button in the popup
			driver.findElement(By.linkText("Yes")).click();
			
			WebElement ele=driver.findElement(By.xpath("//div[@role='alert']/span/span"));
			
			WebDriverWait wait=new WebDriverWait(driver,40);
			
			//Check the course	 deleted or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Coursing inactivated successfully"))
			{
				test.log(LogStatus.PASS, "New Course is deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Course is deleted Failed");
			}
			Thread.sleep(5000);
			
			//Click the Active Button
			driver.findElement(By.xpath("//input[@ng-model='activeStatus']")).click();
			Thread.sleep(5000);
			
			//Click the success button
			driver.findElement(By.xpath("//i[@class='fa fa-check']")).click();
			Thread.sleep(3000);
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.linkText("Yes")).click();

			WebElement ele1=driver.findElement(By.xpath("//div[@role='alert']/span/span"));
			
			WebDriverWait wait1=new WebDriverWait(driver,40);
			
			//Check the course	 deleted or not
			if(wait1.until(ExpectedConditions.visibilityOf(ele1)).getText().equalsIgnoreCase("Coursing activated successfully"))
			{
				test.log(LogStatus.PASS, "Course is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Course is activated Failed");
			}
			
			Thread.sleep(5000);
			//Click the Active Button
			driver.findElement(By.xpath("//input[@ng-model='activeStatus']")).click();

		}

		@Test(enabled=false,priority=27)
		public void Coursing_method_cancelCoursing(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Coursing_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(5000);
			//Click the Cancel button
			driver.findElement(By.xpath("//a[.='Close']")).click();
			
			Thread.sleep(2000);
			//Check the course form is closed or not
			if(driver.findElement(By.xpath("//button[@id='course']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Course page is Closed after click the close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Course page is displayed after click the close button");
			}
			
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=28)
		public void Coursing_method_inactiveAndActive_Button(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			Thread.sleep(3000);
			
			//Click the Active button
			driver.findElement(By.xpath("//input[@ng-model='activeStatus']")).click();
			Thread.sleep(1000);
			try
			{
			//Check the page
			if(driver.findElement(By.cssSelector("i.fa.fa-check")).isDisplayed())
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
			driver.findElement(By.xpath("//input[@ng-model='activeStatus']")).click();
			Thread.sleep(1000);
			
			//Check the page
			if(driver.findElement(By.cssSelector("i.fa.fa-trash-o")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Active page is displayed after click the Inactive button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Active page is not displayed after click the Inactive button");
			}
			Thread.sleep(5000);
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);//watchTutorial(driver);
			Thread.sleep(5000);}
		}
		@Test(priority=3, enabled=false)
		public void watchTutorial(WebDriver driver) throws Exception
		{
			Thread.sleep(2000);
			//Click the Watch Tutorial Option
			driver.findElement(By.xpath("//span[.='Watch Tutorial']")).click();
			WebElement iframe = driver.findElement(By.xpath("//div[@class='modal-content']/span/iframe"));
			driver.switchTo().frame(iframe);
			Thread.sleep(3500);
			try
			{
				if(driver.findElement(By.xpath("//button/div[.='Watch later']")).isDisplayed()&&driver.findElement(By.xpath("//button/div[.='Share']")).isDisplayed())
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
			driver.findElement(By.xpath("//span[.='x' and @title='close']")).click();
		}
}
