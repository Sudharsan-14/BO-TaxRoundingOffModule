package epicList_Chrome;

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

public class AllClassCalling_Royalty_Franchise_Account_Level_User { 
	
	public WebDriver driver;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("BO-Royalty/Franchise (Account Level User Login)");
	
	float unknownValue = 00;
	
	@AfterClass
	public void flushTest() throws Exception
	{
		Thread.sleep(2000);
		rep.endTest(test);
		rep.flush();
		Thread.sleep(15000);
		SendMail_Royalty_Franchise.snedMailWithAttachment();  
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
	public void Start_Royalty_Franchise()
	{
		test.log(LogStatus.INFO, "-------Royalty Franchise Started (Account Level User Login)----");
	}
	
	@Test(priority=302)
	public void Royalty_And_Franchise_Settings() throws Exception
	{
		Royalty_Franchise_Settings a = new Royalty_Franchise_Settings();
		a.Enterprice_Royalty_Franchise_Settings_method_open_Enterprice_Royalty_Franchise_Settings(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Pagination(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Update_Settings(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Close_Button(driver);
	}
	
	@Test(priority=303)
	public void Royalty_And_Franchise_ACH_Settings() throws Exception
	{
		Royalty_Franchise_ACH_Settings a = new Royalty_Franchise_ACH_Settings();
		a.Enterprice_Royalty_Franchise_Settings_method_open_Enterprice_Royalty_Franchise_ACH_ettings(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Update_ACH_Settings(driver);
	}
	
	@Test(priority=304)
	public void Royalty_And_Franchise_Royalty_Report() throws Exception
	{
		Royalty_Franchise_Royalty_Report a = new Royalty_Franchise_Royalty_Report();
		a.Enterprice_Royalty_Franchise_Settings_method_open_Enterprice_Royalty_Franchise_Royalty_Report(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_Report_Store(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_Report_Group(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_Report_City(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_Report_State(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_Report_Zipcode(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_Report_Store_With_ExcludeLoyaltyRedemtion(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_Report_Group_With_ExcludeLoyaltyRedemtion(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_Report_City_With_ExcludeLoyaltyRedemtion(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_Report_State_With_ExcludeLoyaltyRedemtion(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_Report_Zipcode_With_ExcludeLoyaltyRedemtion(driver);
	}
	
	@Test(priority=305)
	public void Royalty_And_Franchise_ACH_Report() throws Exception
	{
		Royalty_Franchise_ACH_Report a = new Royalty_Franchise_ACH_Report();
		a.Enterprice_Royalty_Franchise_Settings_method_open_Enterprice_Royalty_Franchise_ACH_Report(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Store(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Group(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_City(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_State(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Zipcode(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_Report_ACH_Zipcode_CCD_Debit(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Store_With_ExcludeLoyaltyRedemtion(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Group_With_ExcludeLoyaltyRedemtion(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_City_With_ExcludeLoyaltyRedemtion(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_State_With_ExcludeLoyaltyRedemtion(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Zipcode_With_ExcludeLoyaltyRedemtion(driver);
		a.Enterprice_Royalty_Franchise_Settings_method_Royalty_ACH_Report_Zipcode_With_ExcludeLoyaltyRedemtion_PPD_Both(driver);
	}

	@Test(priority=490)
	public void End_Royalty_Franchise()
	{
		test.log(LogStatus.INFO, "-------Royalty Franchise Ends----");
	}
}