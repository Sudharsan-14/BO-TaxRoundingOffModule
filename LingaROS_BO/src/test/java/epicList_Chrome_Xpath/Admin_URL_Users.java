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

public class Admin_URL_Users {
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Admin Staging - User Details for Different Users");
	
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
		if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
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
			//Clear the text from the user name text box
			driver.findElement(By.name("emailId")).clear();
			//Enter the user name
			driver.findElement(By.name("emailId")).sendKeys(Utility.getProperty("userName"));
			Thread.sleep(1000);
			//Clear the password from the password text box
			driver.findElement(By.name("password")).clear();
			//Enter the password
			driver.findElement(By.name("password")).sendKeys(Utility.getProperty("password"));
			Thread.sleep(3000);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
			//Click the login button
			driver.findElement(By.id("round1")).click();
			//Check if we logged in or not
			if(driver.findElement(By.xpath("//div[@id='navigation']/ul[1]/li/a")).getText().equalsIgnoreCase("My Stores"))
			{
				test.log(LogStatus.PASS, "User Logged in Successfully for Dealer Enterprise ");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged in Failed for Dealer Enterprise ");
			}
			Thread.sleep(2000);
			//Click the refresh button 
			driver.findElement(By.cssSelector("button.btn.btn-sm.btn-primary")).click();
			
			Thread.sleep(2000);
			//Clear the search field 
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the search field 
		     driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Store"));
			
			//Click the Entered store Dashboard page
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[4]/div/div/div[2]/div/div[1]/a/h5")).click();
			
			//Check if we logged in or not
			if(driver.findElement(By.xpath("//span[.='Live Updates']")).getText().equalsIgnoreCase("Live Updates"))
			{
				test.log(LogStatus.PASS, "Dealer Store level Dashboard page loaded sucessfully ");
			}
			else
			{
				test.log(LogStatus.FAIL, "Dealer Store level Dashboard page loaded failed ");
			}
			Thread.sleep(5000);
		}
		
		
		else if(Utility.getProperty("Product").equalsIgnoreCase("Linga BO"))		
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
			//Launch the URL
			driver.get(Utility.getProperty("appURL"));
			//Clear the text from the user name text box
			driver.findElement(By.name("txtusername")).clear();
			//Enter the user name
			driver.findElement(By.name("txtusername")).sendKeys(Utility.getProperty("userName"));
			//Clear the password from the password text box
			driver.findElement(By.name("txtpassword")).clear();
			//Enter the password
			driver.findElement(By.name("txtpassword")).sendKeys(Utility.getProperty("password"));
			Thread.sleep(4000);

			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
		
			//Click the login button
			driver.findElement(By.name("submitlogin")).click();
			//Check if we logged in or not
			if(driver.findElement(By.xpath("//div[@id='navigation']/ul[1]/li/a")).getText().equalsIgnoreCase("My Stores"))
			{
				test.log(LogStatus.PASS, "User Logged in Successfully for Linga Enterprise ");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged in Failed for Linga Enterprise ");
			}
			Thread.sleep(5000);
			//Click the refresh button 
			driver.findElement(By.cssSelector("button.btn.btn-sm.btn-primary")).click();
			
			Thread.sleep(2000);
			//Clear the search field 
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the search field 
		     driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Store"));
			
			//Click the Entered store Dashboard page
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[4]/div/div/div[2]/div/div[1]/a/h5")).click();
			
			//Check if we logged in or not
			if(driver.findElement(By.xpath("//span[.='Live Updates']")).getText().equalsIgnoreCase("Live Updates"))
			{
				test.log(LogStatus.PASS, "Linga Store level Dashboard page loaded sucessfully ");
			}
			else
			{
				test.log(LogStatus.FAIL, "Linga Store level Dashboard page loaded failed ");
			}
			Thread.sleep(5000);
		}
		
		else if(Utility.getProperty("Product").equalsIgnoreCase("Staging Admin"))		
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
			//Launch the URL
			driver.get(Utility.getProperty("Staging_Admin_URL"));
			Thread.sleep(5000);
			//Clear the text from the user name text box
			driver.findElement(By.name("emailId")).clear();
			//Enter the user name
			driver.findElement(By.name("emailId")).sendKeys(Utility.getProperty("Staging_Admin_UserName"));
			//Clear the password from the password text box
			driver.findElement(By.name("password")).clear();
			//Enter the password
			driver.findElement(By.name("password")).sendKeys(Utility.getProperty("Staging_Admin_Password"));
			Thread.sleep(4000);

			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
		
			//Click the login button
			driver.findElement(By.xpath("//body[@id='body']/div[1]/div/div/div/div/div/div[1]/form/div[4]/button")).click();
			//Check if we logged in or not
			if(driver.findElement(By.xpath("//a[.='Dashboard']")).getText().equalsIgnoreCase("Dashboard"))
			{
				test.log(LogStatus.PASS, "User Logged in Successfully for Staging Admin");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged in Failed for Staging Admin");
			}
			Thread.sleep(5000);
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
		else if(Utility.getProperty("Product").equalsIgnoreCase("Linga BO"))
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
		
		else if(Utility.getProperty("Product").equalsIgnoreCase("Staging Admin"))		
		{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//i[@class='icon-logout icon-sm icon-bold']"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath("//i[@class='icon-logout icon-sm icon-bold']")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath("//h2[.='ADMIN LOGIN']")).getText().equalsIgnoreCase("ADMIN LOGIN"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully for Staging Admin");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed for Staging Admin");
			}
			Thread.sleep(3000);
			//Close the Browser
			driver.close();
		}
		
	}
	
	@Test(priority=3)
	public void Strater_Plan() throws InterruptedException
	{

				
		Thread.sleep(3000);
		//Click the Refresh button
		driver.findElement(By.xpath("//button[@class='btn btn-info btn-small pull-right']")).click();
		Thread.sleep(5000);
		
		//Clear the Search field
		driver.findElement(By.xpath("//input[@placeholder='Search']")).clear();
		//Enter the keyword
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("stagetest2@rsvhr.com");
		
		Thread.sleep(1000);
		//Click the required store
		driver.findElement(By.xpath("//tr[2]/td[1]/a")).click();
		
		Thread.sleep(15000);
		//Check whether Account Name is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div[2]")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Account Name is available for Strater Plan(stagetest2@rsvhr.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Account Name is unavailable for Strater Plan(stagetest2@rsvhr.com)");
		}
		
		//Check whether Phone Number is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Phone Number is available for Strater Plan(stagetest2@rsvhr.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Phone Number is unavailable for Strater Plan(stagetest2@rsvhr.com)");
		}
		
		//Check whether Email Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Email Id is available for Strater Plan(stagetest2@rsvhr.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email Id is unavailable for Strater Plan(stagetest2@rsvhr.com)");
		}
		
		//Check whether Subscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Subscription Id is available for Strater Plan(stagetest2@rsvhr.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Subscription Id is unavailable for Strater Plan(stagetest2@rsvhr.com)");
		}
		
		//Check whether Customer Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[6]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Customer Id is available for Strater Plan(stagetest2@rsvhr.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer Id is unavailable for Strater Plan(stagetest2@rsvhr.com)");
		}
		
		//Check whether ZohoSubscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[7]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "ZohoSubscription Id is available for Strater Plan(stagetest2@rsvhr.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "ZohoSubscription Id is available for Strater Plan(stagetest2@rsvhr.com)");
		}
		
		//Check whether GDPR is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[8]/div[2]/label")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "GDPR is available for Strater Plan(stagetest2@rsvhr.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "GDPR is unavailable for Strater Plan(stagetest2@rsvhr.com)");
		}
		
		//Check whether Plan is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[1]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Plan is available for Strater Plan(stagetest2@rsvhr.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Plan is unavailable for Strater Plan(stagetest2@rsvhr.com)");
		}
		
		//Check whether License is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "License is available for Strater Plan(stagetest2@rsvhr.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "License is unavailable for Strater Plan(stagetest2@rsvhr.com)");
		}
		
		//Check whether Date Created is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[4]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Date Created is available for Strater Plan(stagetest2@rsvhr.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Date Created is unavailable for Strater Plan(stagetest2@rsvhr.com)");
		}
		
		//Check whether Last Ipad Access Date is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Last Ipad Access Date is available for Strater Plan(stagetest2@rsvhr.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Last Ipad Access Date is unavailable for Strater Plan(stagetest2@rsvhr.com)");
		}
		
		Thread.sleep(10000);
		//Click the Back button
		driver.findElement(By.xpath("//i[@class='fa fa-arrow-left']")).click();
		Thread.sleep(15000);
		
		//Clear the Search field
		driver.findElement(By.xpath("//input[@placeholder='Search']")).clear();
		//Enter the keyword
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("aishusundar1993@gmail.com");
		
		Thread.sleep(1000);
		//Click the required store
		driver.findElement(By.xpath("//tr[2]/td[1]/a")).click();
		
		Thread.sleep(15000);
		//Check whether Account Name is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div[2]")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Account Name is available for Strater Plan(aishusundar1993@gmail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Account Name is unavailable for Strater Plan(aishusundar1993@gmail.com)");
		}
		
		//Check whether Phone Number is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Phone Number is available for Strater Plan(aishusundar1993@gmail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Phone Number is unavailable for Strater Plan(aishusundar1993@gmail.com)");
		}
		
		//Check whether Email Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Email Id is available for Strater Plan(aishusundar1993@gmail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email Id is unavailable for Strater Plan(aishusundar1993@gmail.com)");
		}
		
		//Check whether Subscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Subscription Id is available for Strater Plan(aishusundar1993@gmail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Subscription Id is unavailable for Strater Plan(aishusundar1993@gmail.com)");
		}
		
		//Check whether Customer Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[6]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Customer Id is available for Strater Plan(aishusundar1993@gmail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer Id is unavailable for Strater Plan(aishusundar1993@gmail.com)");
		}
		
		//Check whether ZohoSubscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[7]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "ZohoSubscription Id is available for Strater Plan(aishusundar1993@gmail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "ZohoSubscription Id is available for Strater Plan(aishusundar1993@gmail.com)");
		}
		
		//Check whether GDPR is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[8]/div[2]/label")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "GDPR is available for Strater Plan(aishusundar1993@gmail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "GDPR is unavailable for Strater Plan(aishusundar1993@gmail.com)");
		}
		
		//Check whether Plan is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[1]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Plan is available for Strater Plan(aishusundar1993@gmail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Plan is unavailable for Strater Plan(aishusundar1993@gmail.com)");
		}
		
		//Check whether License is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "License is available for Strater Plan(aishusundar1993@gmail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "License is unavailable for Strater Plan(aishusundar1993@gmail.com)");
		}
		
		//Check whether Date Created is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[4]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Date Created is available for Strater Plan(aishusundar1993@gmail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Date Created is unavailable for Strater Plan(aishusundar1993@gmail.com)");
		}
		
		//Check whether Last Ipad Access Date is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Last Ipad Access Date is available for Strater Plan(aishusundar1993@gmail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Last Ipad Access Date is unavailable for Strater Plan(aishusundar1993@gmail.com)");
		}
		
		Thread.sleep(10000);
		//Click the Back button
		driver.findElement(By.xpath("//i[@class='fa fa-arrow-left']")).click();
		Thread.sleep(15000);
	}

	@Test(priority=4)
	public void Enterprise_Plan() throws InterruptedException
	{
		Thread.sleep(3000);
		//Clear the Search field
		driver.findElement(By.xpath("//input[@placeholder='Search']")).clear();
		//Enter the keyword
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("autotest@mail.com");
		
		Thread.sleep(1000);
		//Click the required store
		driver.findElement(By.xpath("//tr[2]/td[1]/a")).click();
		
		Thread.sleep(15000);
		//Check whether Account Name is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div[2]")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Account Name is available for Enterprise Plan(autotest@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Account Name is unavailable for Enterprise Plan(autotest@mail.com)");
		}
		
		//Check whether Phone Number is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Phone Number is available for Enterprise Plan(autotest@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Phone Number is unavailable for Enterprise Plan(autotest@mail.com)");
		}
		
		//Check whether Email Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Email Id is available for Enterprise Plan(autotest@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email Id is unavailable for Enterprise Plan(autotest@mail.com)");
		}
		
		//Check whether Subscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Subscription Id is available for Enterprise Plan(autotest@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Subscription Id is unavailable for Enterprise Plan(autotest@mail.com)");
		}
		
		//Check whether Customer Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[6]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Customer Id is available for Enterprise Plan(autotest@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer Id is unavailable for Enterprise Plan(autotest@mail.com)");
		}
		
		//Check whether ZohoSubscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[7]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "ZohoSubscription Id is available for Enterprise Plan(autotest@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "ZohoSubscription Id is available for Enterprise Plan(autotest@mail.com)");
		}
		
		//Check whether GDPR is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[8]/div[2]/label")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "GDPR is available for Enterprise Plan(autotest@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "GDPR is unavailable for Enterprise Plan(autotest@mail.com)");
		}
		
		//Check whether Plan is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[1]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Plan is available for Enterprise Plan(autotest@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Plan is unavailable for Enterprise Plan(autotest@mail.com)");
		}
		
		//Check whether License is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "License is available for Enterprise Plan(autotest@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "License is unavailable for Enterprise Plan(autotest@mail.com)");
		}
		
		//Check whether Date Created is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[4]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Date Created is available for Enterprise Plan(autotest@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Date Created is unavailable for Enterprise Plan(autotest@mail.com)");
		}
		
		//Check whether Last Ipad Access Date is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Last Ipad Access Date is available for Enterprise Plan(autotest@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Last Ipad Access Date is unavailable for Enterprise Plan(autotest@mail.com)");
		}
		
		Thread.sleep(10000);
		//Click the Back button
		driver.findElement(By.xpath("//i[@class='fa fa-arrow-left']")).click();
		Thread.sleep(15000);
		
		//Clear the Search field
		driver.findElement(By.xpath("//input[@placeholder='Search']")).clear();
		//Enter the keyword
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("swiggy@mail.com");
		
		Thread.sleep(1000);
		//Click the required store
		driver.findElement(By.xpath("//tr[2]/td[1]/a")).click();
		
		Thread.sleep(15000);
		//Check whether Account Name is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div[2]")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Account Name is available for Enterprise Plan(swiggy@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Account Name is unavailable for Enterprise Plan(swiggy@mail.com)");
		}
		
		//Check whether Phone Number is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Phone Number is available for Enterprise Plan(swiggy@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Phone Number is unavailable for Enterprise Plan(swiggy@mail.com)");
		}
		
		//Check whether Email Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Email Id is available for Enterprise Plan(swiggy@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email Id is unavailable for Enterprise Plan(swiggy@mail.com)");
		}
		
		//Check whether Subscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Subscription Id is available for Enterprise Plan(swiggy@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Subscription Id is unavailable for Enterprise Plan(swiggy@mail.com)");
		}
		
		//Check whether Customer Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[6]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Customer Id is available for Enterprise Plan(swiggy@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer Id is unavailable for Enterprise Plan(swiggy@mail.com)");
		}
		
		//Check whether ZohoSubscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[7]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "ZohoSubscription Id is available for Enterprise Plan(swiggy@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "ZohoSubscription Id is available for Enterprise Plan(swiggy@mail.com)");
		}
		
		//Check whether GDPR is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[8]/div[2]/label")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "GDPR is available for Enterprise Plan(swiggy@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "GDPR is unavailable for Enterprise Plan(swiggy@mail.com)");
		}
		
		//Check whether Plan is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[1]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Plan is available for Enterprise Plan(swiggy@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Plan is unavailable for Enterprise Plan(swiggy@mail.com)");
		}
		
		//Check whether License is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "License is available for Enterprise Plan(swiggy@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "License is unavailable for Enterprise Plan(swiggy@mail.com)");
		}
		
		//Check whether Date Created is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[4]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Date Created is available for Enterprise Plan(swiggy@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Date Created is unavailable for Enterprise Plan(swiggy@mail.com)");
		}
		
		//Check whether Last Ipad Access Date is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Last Ipad Access Date is available for Enterprise Plan(swiggy@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Last Ipad Access Date is unavailable for Enterprise Plan(swiggy@mail.com)");
		}
		
		Thread.sleep(10000);
		//Click the Back button
		driver.findElement(By.xpath("//i[@class='fa fa-arrow-left']")).click();
		Thread.sleep(15000);
	}

	@Test(priority=5)
	public void Pro_Plan() throws InterruptedException
	{
		Thread.sleep(3000);
		//Clear the Search field
		driver.findElement(By.xpath("//input[@placeholder='Search']")).clear();
		//Enter the keyword
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("store01@mail.com");
		
		Thread.sleep(1000);
		//Click the required store
		driver.findElement(By.xpath("//tr[2]/td[1]/a")).click();
		
		Thread.sleep(15000);
		//Check whether Account Name is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div[2]")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Account Name is available for PRO Plan(store01@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Account Name is unavailable for PRO Plan(store01@mail.com)");
		}
		
		//Check whether Phone Number is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Phone Number is available for PRO Plan(store01@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Phone Number is unavailable for PRO Plan(store01@mail.com)");
		}
		
		//Check whether Email Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Email Id is available for PRO Plan(store01@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email Id is unavailable for PRO Plan(store01@mail.com)");
		}
		
		//Check whether Subscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Subscription Id is available for PRO Plan(store01@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Subscription Id is unavailable for PRO Plan(store01@mail.com)");
		}
		
		//Check whether Customer Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[6]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Customer Id is available for PRO Plan(store01@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer Id is unavailable for PRO Plan(store01@mail.com)");
		}
		
		//Check whether ZohoSubscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[7]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "ZohoSubscription Id is available for PRO Plan(store01@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "ZohoSubscription Id is available for PRO Plan(store01@mail.com)");
		}
		
		//Check whether GDPR is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[8]/div[2]/label")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "GDPR is available for PRO Plan(store01@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "GDPR is unavailable for PRO Plan(store01@mail.com)");
		}
		
		//Check whether Plan is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[1]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Plan is available for PRO Plan(store01@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Plan is unavailable for PRO Plan(store01@mail.com)");
		}
		
		//Check whether License is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "License is available for PRO Plan(store01@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "License is unavailable for PRO Plan(store01@mail.com)");
		}
		
		//Check whether Date Created is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[4]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Date Created is available for PRO Plan(store01@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Date Created is unavailable for PRO Plan(store01@mail.com)");
		}
		
		//Check whether Last Ipad Access Date is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Last Ipad Access Date is available for PRO Plan(store01@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Last Ipad Access Date is unavailable for PRO Plan(store01@mail.com)");
		}
		
		Thread.sleep(10000);
		//Click the Back button
		driver.findElement(By.xpath("//i[@class='fa fa-arrow-left']")).click();
		Thread.sleep(15000);
		
		//Clear the Search field
		driver.findElement(By.xpath("//input[@placeholder='Search']")).clear();
		//Enter the keyword
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("pepsi@mail.com");
		
		Thread.sleep(1000);
		//Click the required store
		driver.findElement(By.xpath("//tr[2]/td[1]/a")).click();
		
		Thread.sleep(15000);
		//Check whether Account Name is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div[2]")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Account Name is available for PRO Plan(pepsi@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Account Name is unavailable for PRO Plan(pepsi@mail.com)");
		}
		
		//Check whether Phone Number is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Phone Number is available for PRO Plan(pepsi@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Phone Number is unavailable for PRO Plan(pepsi@mail.com)");
		}
		
		//Check whether Email Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Email Id is available for PRO Plan(pepsi@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email Id is unavailable for PRO Plan(pepsi@mail.com)");
		}
		
		//Check whether Subscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Subscription Id is available for PRO Plan(pepsi@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Subscription Id is unavailable for PRO Plan(pepsi@mail.com)");
		}
		
		//Check whether Customer Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[6]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Customer Id is available for PRO Plan(pepsi@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer Id is unavailable for PRO Plan(pepsi@mail.com)");
		}
		
		//Check whether ZohoSubscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[7]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "ZohoSubscription Id is available for PRO Plan(pepsi@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "ZohoSubscription Id is available for PRO Plan(pepsi@mail.com)");
		}
		
		//Check whether GDPR is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[8]/div[2]/label")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "GDPR is available for PRO Plan(pepsi@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "GDPR is unavailable for PRO Plan(pepsi@mail.com)");
		}
		
		//Check whether Plan is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[1]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Plan is available for PRO Plan(pepsi@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Plan is unavailable for PRO Plan(pepsi@mail.com)");
		}
		
		//Check whether License is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "License is available for PRO Plan(pepsi@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "License is unavailable for PRO Plan(pepsi@mail.com)");
		}
		
		//Check whether Date Created is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[4]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Date Created is available for PRO Plan(pepsi@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Date Created is unavailable for PRO Plan(pepsi@mail.com)");
		}
		
		//Check whether Last Ipad Access Date is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Last Ipad Access Date is available for PRO Plan(pepsi@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Last Ipad Access Date is unavailable for PRO Plan(pepsi@mail.com)");
		}
		
		Thread.sleep(10000);
		//Click the Back button
		driver.findElement(By.xpath("//i[@class='fa fa-arrow-left']")).click();
		Thread.sleep(15000);
	}
	
	@Test(priority=6)
	public void Basic_Plan() throws InterruptedException
	{
		Thread.sleep(3000);
		//Clear the Search field
		driver.findElement(By.xpath("//input[@placeholder='Search']")).clear();
		//Enter the keyword
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("ashokzoho8@mail.com");
		
		Thread.sleep(1000);
		//Click the required store
		driver.findElement(By.xpath("//tr[2]/td[1]/a")).click();
		
		Thread.sleep(15000);
		//Check whether Account Name is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div[2]")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Account Name is available for Basic Plan(ashokzoho8@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Account Name is unavailable for Basic Plan(ashokzoho8@mail.com)");
		}
		
		//Check whether Phone Number is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Phone Number is available for Basic Plan(ashokzoho8@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Phone Number is unavailable for Basic Plan(ashokzoho8@mail.com)");
		}
		
		//Check whether Email Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Email Id is available for Basic Plan(ashokzoho8@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email Id is unavailable for Basic Plan(ashokzoho8@mail.com)");
		}
		
		//Check whether Subscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Subscription Id is available for Basic Plan(ashokzoho8@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Subscription Id is unavailable for Basic Plan(ashokzoho8@mail.com)");
		}
		
		//Check whether Customer Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[6]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Customer Id is available for Basic Plan(ashokzoho8@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer Id is unavailable for Basic Plan(ashokzoho8@mail.com)");
		}
		
		//Check whether ZohoSubscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[7]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "ZohoSubscription Id is available for Basic Plan(ashokzoho8@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "ZohoSubscription Id is available for Basic Plan(ashokzoho8@mail.com)");
		}
		
		//Check whether GDPR is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[8]/div[2]/label")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "GDPR is available for Basic Plan(ashokzoho8@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "GDPR is unavailable for Basic Plan(ashokzoho8@mail.com)");
		}
		
		//Check whether Plan is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[1]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Plan is available for Basic Plan(ashokzoho8@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Plan is unavailable for Basic Plan(ashokzoho8@mail.com)");
		}
		
		//Check whether License is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "License is available for Basic Plan(ashokzoho8@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "License is unavailable for Basic Plan(ashokzoho8@mail.com)");
		}
		
		//Check whether Date Created is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[4]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Date Created is available for Basic Plan(ashokzoho8@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Date Created is unavailable for Basic Plan(ashokzoho8@mail.com)");
		}
		
		//Check whether Last Ipad Access Date is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Last Ipad Access Date is available for Basic Plan(ashokzoho8@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Last Ipad Access Date is unavailable for Basic Plan(ashokzoho8@mail.com)");
		}
		
		Thread.sleep(10000);
		//Click the Back button
		driver.findElement(By.xpath("//i[@class='fa fa-arrow-left']")).click();
		Thread.sleep(15000);
		
		//Clear the Search field
		driver.findElement(By.xpath("//input[@placeholder='Search']")).clear();
		//Enter the keyword
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("store10@mail.com");
		
		Thread.sleep(1000);
		//Click the required store
		driver.findElement(By.xpath("//tr[2]/td[1]/a")).click();
		
		Thread.sleep(15000);
		//Check whether Account Name is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div[2]")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Account Name is available for Basic Plan(store10@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Account Name is unavailable for Basic Plan(store10@mail.com)");
		}
		
		//Check whether Phone Number is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Phone Number is available for Basic Plan(store10@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Phone Number is unavailable for Basic Plan(store10@mail.com)");
		}
		
		//Check whether Email Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Email Id is available for Basic Plan(store10@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email Id is unavailable for Basic Plan(store10@mail.com)");
		}
		
		//Check whether Subscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Subscription Id is available for Basic Plan(store10@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Subscription Id is unavailable for Basic Plan(store10@mail.com)");
		}
		
		//Check whether Customer Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[6]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Customer Id is available for Basic Plan(store10@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer Id is unavailable for Basic Plan(store10@mail.com)");
		}
		
		//Check whether ZohoSubscription Id is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[7]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "ZohoSubscription Id is available for Basic Plan(store10@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "ZohoSubscription Id is available for Basic Plan(store10@mail.com)");
		}
		
		//Check whether GDPR is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[8]/div[2]/label")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "GDPR is available for Basic Plan(store10@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "GDPR is unavailable for Basic Plan(store10@mail.com)");
		}
		
		//Check whether Plan is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[1]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Plan is available for Basic Plan(store10@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Plan is unavailable for Basic Plan(store10@mail.com)");
		}
		
		//Check whether License is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[2]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "License is available for Basic Plan(store10@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "License is unavailable for Basic Plan(store10@mail.com)");
		}
		
		//Check whether Date Created is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[4]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Date Created is available for Basic Plan(store10@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Date Created is unavailable for Basic Plan(store10@mail.com)");
		}
		
		//Check whether Last Ipad Access Date is available or not
		if(!driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div[4]/div[5]/div[2]/div")).getText().isEmpty())
		{
			test.log(LogStatus.PASS, "Last Ipad Access Date is available for Basic Plan(store10@mail.com)");
		}
		else
		{
			test.log(LogStatus.FAIL, "Last Ipad Access Date is unavailable for Basic Plan(store10@mail.com)");
		}
		
		Thread.sleep(10000);
		//Click the Back button
		driver.findElement(By.xpath("//i[@class='fa fa-arrow-left']")).click();
		Thread.sleep(15000);
	}
}
