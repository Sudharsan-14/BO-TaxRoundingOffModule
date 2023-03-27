package epicList_Chrome_Account_User;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AllClassCalling_Enterprise_Licenses_Account_Level_User 
{
	public WebDriver driver;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("BO-Enterprise Licenses");
	
	float unknownValue = 00;
	
	@AfterClass
	public void flushTest() throws Exception
	{
		Thread.sleep(2000);
		rep.endTest(test);
		rep.flush();
		Thread.sleep(15000);
		SendMail_Enterprise_Licenses.snedMailWithAttachment();    
		Thread.sleep(15000);
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
	a.Logout(driver, test);
	}
	
	@Test(priority=3)
	public void Start_Enterprise_Licenses()
	{
		test.log(LogStatus.INFO, "-------Enterprise Licenses Started (Account Level User Login)----");
	}

	
	@Test(priority=5)
	public void Enterprise_Licenses() throws Exception
	{
		Enterprise_Licenses a = new Enterprise_Licenses();
		a.Licenses_open_page(driver);
		a.Reset_Licenses(driver);
		a.Verify_UnLinked_Licenses(driver);
	}
	
	@Test(priority=6)
	public void Enterprise_License_Settings() throws Exception
	{
		Enterprise_License_Settings a = new Enterprise_License_Settings();
		a.Licenses_open_page(driver);
		a.Update_License_Settings(driver);
	}
	
	@Test(priority=7)
	public void Enterprise_Developer_API_Key() throws Exception
	{
		Enterprise_Developer_API a = new Enterprise_Developer_API();
		a.Developer_API_open_page(driver);
		a.Update_License_Count_Developer_API(driver);
	}
	
	@Test(priority=10)
	public void End_Enterprise_Licenses()
	{
		test.log(LogStatus.INFO, "-------Enterprise Licenses End----");
	}
}
