package epicList_Chrome_Xpath;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class AddEditDelete_Departments {
	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete Departments");

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
			if(driver.findElement(By.xpath("//div[@id='x-content-band-1']/div/div[2]/h2")).getText().equalsIgnoreCase("Account Login"))
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
			Department_method_openDepartments(driver);
			Department_method_refreshDepartments_Page(driver);
			Department_method_add_Department(driver);
			Department_method_edit_Department(driver);
			Department_method_delete_Department(driver);
			Department_method_cancelDepartemnt(driver);
			Department_method_inactiveAndActive_Button(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=2)
		public void Department_method_openDepartments(WebDriver driver) throws Exception
		{
			Thread.sleep(15000);
/*			//Click the Products/Items option
			driver.findElement(By.xpath("//span[.='Products/Items']")).click();
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='Departments']"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			Thread.sleep(5000);
			//Click the Departments Option		
			driver.findElement(By.xpath("//span[.='Departments']")).click();
*/			
			//Enter the Coursing URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"department");
			
			Thread.sleep(5000);
			//Check Departments page opened or not
			if(driver.findElement(By.xpath("//a[.='Departments']")).getText().equalsIgnoreCase("Departments"))
			{
				test.log(LogStatus.PASS, "Departments page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Departments page loaded Failed");
			}
			Thread.sleep(3000);
		}
				
		@Test(enabled=false,priority=3)
		public void Department_method_refreshDepartments_Page(WebDriver driver) throws InterruptedException
		{
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(8000);
			//Click the refresh button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a[1]/i")).click();
			Thread.sleep(5000);
			
			//Check Departments page opened or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[1]/div/h3")).getText().equalsIgnoreCase("Departments"))
			{
				test.log(LogStatus.PASS, "Departments page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Departments page loaded Failed");
			}
			Thread.sleep(3000);
		}
			
		@Test(enabled=false,priority=4)
		public void Department_method_add_Department(WebDriver driver) throws Exception
		{
			for (int i = 0; i < 10; i++) {
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
			Thread.sleep(8000);
			//Click the Add department button
			driver.findElement(By.xpath("//button[@id='department']")).click();
			
			Thread.sleep(2000);
			//Check weather the new Department form loaded or not
			if(driver.findElement(By.xpath("//span[.='New Department']")).getText().equalsIgnoreCase("New Department"))
			{
				test.log(LogStatus.PASS, "New Department form Loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Department form Loaded Fail");
			}
			
			Thread.sleep(6000);
			//Clear the name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[1]/input")).clear();
			//Enter the required name
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[1]/input")).sendKeys(Utility.getProperty("Department_Name"));
			
			//Clear the description field
			driver.findElement(By.name("description")).clear();
			//Enter the required description field
			driver.findElement(By.name("description")).sendKeys("Department Description");
			
			//Clear the Department Code Option
			driver.findElement(By.name("departmentCode")).clear();
			//Enter the Department Code Option
			driver.findElement(By.name("departmentCode")).sendKeys(Utility.getProperty("Department_Code"));
			
			Thread.sleep(5000);
			//Click the save button
			driver.findElement(By.xpath("//button[@id='saveDepartment']")).click();
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
			//driver.navigate().refresh();
			//Check weather the new department is saved or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Department Saved Successfully"))
			{
				test.log(LogStatus.PASS, "New Department Saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Department saved fail");
			}
			Thread.sleep(5000);
			
		}
		
		@Test(enabled=false,priority=5)
		public void Department_method_edit_Department(WebDriver driver) throws Exception
		{
	
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Department_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[1]/input")).clear();
			//Enter the required name
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[1]/input")).sendKeys(Utility.getProperty("Department_Name")+"1");
	
			//Clear the Department Code Option
			driver.findElement(By.name("departmentCode")).clear();
			//Enter the Department Code Option
			driver.findElement(By.name("departmentCode")).sendKeys(Utility.getProperty("Department_Code")+"1");
	
			
			Thread.sleep(1000);
			//Click the Update button
			driver.findElement(By.xpath("//button[@id='saveDepartment']")).click();
			
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			//driver.navigate().refresh();
			//Check weather the new department is updated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Department Updated Successfully"))
			{
				test.log(LogStatus.PASS, "New Department updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Department updated fail");
			}
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=6)
		public void Department_method_delete_Department(WebDriver driver) throws Exception
		{
			Thread.sleep(5000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Department_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Delete button
			driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			
			Thread.sleep(3000);
			//Check the menu item deleted or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Department Inactivated Successfully"))
			{
				test.log(LogStatus.PASS, "New Department is deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Department is deleted Failed");
			}
			Thread.sleep(3000);
			
			//Click the Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(5000);
			
			//Click the success button
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(5000);
			
			//Check the department activated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Department Activated Successfully"))
			{
				test.log(LogStatus.PASS, "Department is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Department is activated Failed");
			}
			
			Thread.sleep(5000);		
			//Click the Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/input")).click();
	
		}
	
		@Test(enabled=false,priority=7)
		public void Department_method_cancelDepartemnt(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Department_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(5000);
			//Click the Cancel button
			driver.findElement(By.xpath("//a[.='Close']")).click();
			
			Thread.sleep(2000);
			//Check the department form is closed or not
			if(driver.findElement(By.xpath("//button[@id='department']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Department page is Closed after click the close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Department page is displayed after click the close button");
			}
			
			Thread.sleep(5000);
		}
				
		@Test(enabled=false,priority=8)
		public void Department_method_inactiveAndActive_Button(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			Thread.sleep(1000);
			
			//Click the Active button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(1000);
			
			//Check the Page
			if(driver.findElement(By.cssSelector("i.fa.fa-check")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Inactive page is displayed after click the Active button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inactive page is not displayed after click the Active button");
			}
			
			Thread.sleep(3000);
			//Click the InActive button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/input")).click();
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
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);}
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
