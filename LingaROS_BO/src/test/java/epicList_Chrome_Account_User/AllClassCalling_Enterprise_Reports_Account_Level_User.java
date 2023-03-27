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

public class AllClassCalling_Enterprise_Reports_Account_Level_User 
{ 
	 
	public WebDriver driver;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("BO-Enterprise Reports (Account User Level)"); 
	
	float unknownValue = 00;
	
	@AfterClass
	public void flushTest() throws Exception
	{
		Thread.sleep(2000);
		rep.endTest(test);
		rep.flush();
		Thread.sleep(15000);
	//	SendMail_Enterprise_Reports.snedMailWithAttachment();    
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

	@Test(priority=4)
	public void Start_Enterprise_Reports()
	{
		test.log(LogStatus.INFO, "-------Enterprise Reports Started----");
	}
	
	@Test(priority=151)
	public void Report_Verify_Enterprise_Report() throws Exception
	{ 
		Verify_Enterprise_Report a = new Verify_Enterprise_Report();
		a.Enterprise_Report_openpage(driver); 
		a.Enterprise_Report_Stores(driver);
		a.Enterprise_Report_Group(driver);
		a.Enterprise_Report_City(driver); 
		a.Enterprise_Report_State(driver);
		a.Enterprise_Report_ZipCode(driver);
	}
	
	@Test(priority=152)
	public void Report_Verify_Enterprise_Department_Sale_Report() throws Exception
	{
		Verify_Enterprise_Department_Sale_Report a = new Verify_Enterprise_Department_Sale_Report();
		a.Enterprise_Department_Report_Method_For_openpage(driver);
		a.Enterprise_Department_Report_Method_For_Stores(driver);
		a.Enterprise_Department_Report_Method_For_Group(driver);
		a.Enterprise_Department_Report_Method_For_City(driver);
		a.Enterprise_Department_Report_Method_For_State(driver);
		a.Enterprise_Department_Report_Method_For_ZipCode(driver);
	}
	
	@Test(priority=153)
	public void Report_Verify_Enterprise_Category_Sale_Report() throws Exception
	{ 
		Verify_Enterprise_Category_Sale a = new Verify_Enterprise_Category_Sale();
		a.Enterprise_Category_Report_Method_For_openpage(driver);
		a.Enterprise_Category_Report_Method_For_Stores(driver);
		a.Enterprise_Category_Report_Method_For_Group(driver);
		a.Enterprise_Category_Report_Method_For_City(driver);
		a.Enterprise_Category_Report_Method_For_State(driver);
		a.Enterprise_Category_Report_Method_For_ZipCode(driver);
		a.Enterprise_Category_Report_Method_For_Store_BY_SplitByMenuItem(driver);
		a.Enterprise_Category_Report_Method_For_Group_SplitByMenuItem(driver);
		a.Enterprise_Category_Report_Method_For_City_SplitByMenuItem(driver);
		a.Enterprise_Category_Report_Method_For_State_SlitByMenuItem(driver);
		a.Enterprise_Category_Report_Method_For_ZipCode_SPlitByMenuItem(driver);
	}
	
	@Test(priority=154)
	public void Report_Verify_Enterprise_Sub_Category_Sale_Report() throws Exception
	{
		Verify_Enterprise_SubCategory_Sale_Report a = new Verify_Enterprise_SubCategory_Sale_Report();
		a.Enterprise_Sub_Category_Report_Method_For_openpage(driver);
		a.Enterprise_Sub_Category_Report_Method_For_Stores(driver);
		a.Enterprise_Sub_Category_Report_Method_For_Group(driver);
		a.Enterprise_Sub_Category_Report_Method_For_City(driver);
		a.Enterprise_Sub_Category_Report_Method_For_State(driver);
		a.Enterprise_Sub_Category_Report_Method_For_ZipCode(driver);
	}
	
	@Test(priority=155)
	public void Report_Verify_Enterprise_Menu_Item_Sale_Report() throws Exception
	{
		Verify_Enterprise_Menu_Item_Sale_Report a = new Verify_Enterprise_Menu_Item_Sale_Report();
		a.Enterprise_Menu_Item_Report_Method_For_openpage(driver);
		a.Enterprise_Menu_Item_Report_Method_For_Stores(driver);
		a.Enterprise_Menu_Item_Report_Method_For_Group(driver);
		a.Enterprise_Menu_Item_Report_Method_For_City(driver);
		a.Enterprise_Menu_Item_Report_Method_For_State(driver);
		a.Enterprise_Menu_Item_Report_Method_For_ZipCode(driver);
		a.Enterprise_Menu_Item_Report_Method_For_Stores_WithSplitByServingSize(driver);
		a.Enterprise_Menu_Item_Report_Method_For_Group_WithSplitByServingSize(driver);
		a.Enterprise_Menu_Item_Report_Method_For_City_WithSplitByServingSize(driver);
		a.Enterprise_Menu_Item_Report_Method_For_State_WithSplitByServingSize(driver);
		a.Enterprise_Menu_Item_Report_Method_For_ZipCode_WithSplitByServingSize(driver);
	}
	
	@Test(priority=156)
	public void Report_Verify_Enterprise_Modifier_Sale_Report() throws Exception
	{
		Verify_Enterprise_Modifier_Sale_Report a = new Verify_Enterprise_Modifier_Sale_Report();
		a.Enterprise_Modifier_Report_Method_For_openpage(driver);
		a.Enterprise_Modifier_Report_Method_For_Stores(driver);
		a.Enterprise_Modifier_Report_Method_For_Group(driver);
		a.Enterprise_Modifier_Report_Method_For_City(driver);
		a.Enterprise_Modifier_Report_Method_For_State(driver);
		a.Enterprise_Modifier_Report_Method_For_ZipCode(driver);
	}
	
	@Test(priority=157)
	public void Report_Verify_Enterprise_Hourly_Sale_Report() throws Exception
	{
		Verify_Enterprise_Hourly_Sale_Report a = new Verify_Enterprise_Hourly_Sale_Report();
		a.Hourly_Enterprise_Report_Method_openpage(driver);
		a.Hourly_Enterprise_Report_Method_Stores(driver);
		a.Hourly_Enterprise_Report_Method_Group(driver);
		a.Hourly_Enterprise_Report_Method_City(driver);
		a.Hourly_Enterprise_Report_Method_State(driver);
		a.Hourly_Enterprise_Report_Method_ZipCode(driver);
	}
	
	@Test(priority=158)
	public void Report_Verify_Enterprise_Daily_Sale_Report() throws Exception
	{
		Verify_Enterprise_Daily_Sale_Report a = new Verify_Enterprise_Daily_Sale_Report();
		a.Daily_Enterprise_Report_Method_OpenpageReport(driver);
		a.Daily_Enterprise_Report_Method_For_Stores(driver);
		a.Daily_Enterprise_Report_Method_For_Group(driver);
		a.Daily_Enterprise_Report_Method_For_City(driver);
		a.Daily_Enterprise_Report_Method_For_State(driver);
		a.Daily_Enterprise_Report_Method_For_ZipCode(driver);
	}
	
	@Test(priority=159)
	public void Report_Verify_Enterprise_Sale_Recap_Report() throws Exception
	{
		Verify_Enterprise_SaleRecap_Report a = new Verify_Enterprise_SaleRecap_Report();
		a.SaleRecap_Enterprise_Report_Method_For_openpage(driver);
		a.SaleRecap_Enterprise_Report_Method_For_Non_Close_Sales(driver);
		a.SaleRecap_Enterprise_Report_Method_For_Timeperiod(driver);
	}
	 
	@Test(priority=160)
	public void Report_Verify_Enterprise_Cashier_Out_Report() throws Exception
	{
		Verify_Enterprise_Cashier_Out_Report a = new Verify_Enterprise_Cashier_Out_Report();
		a.CashierOut_Enterprise_Report_Method_For_openpage(driver);
		a.CashierOut_Enterprise_Report_Verify(driver);
	}
	 
	@Test(priority=161)
	public void Report_Verify_Enterprise_Weekly_Summary_Report() throws Exception
	{
		Verify_Enterprise_Weekly_Summary_Report a = new Verify_Enterprise_Weekly_Summary_Report();
		a.Weekly_Summary_Enterprise_Report_Method_For_openpage(driver);
		a.Weekly_Summary_Enterprise_Report_Method_For_This_Week(driver);
		a.Weekly_Summary_Enterprise_Report_Method_For_Last_Week(driver);
		a.Weekly_Summary_Enterprise_Report_Method_For_Last7days(driver);
	}
	 
	@Test(priority=162) 
	public void Report_Verify_Enterprise_Sale_Summary_Report() throws Exception
	{
		Verify_Enterprise_Sale_Summary_Report a = new Verify_Enterprise_Sale_Summary_Report();
		a.Sale_Summary_Enterprise_Report_Method_For_openpage(driver);
		a.Sale_Summary_Enterprise_Report_Method_For_Stores(driver);
		a.Sale_Summary_Enterprise_Report_Method_For_Group(driver);
		a.Sale_Summary_Enterprise_Report_Method_For_City(driver);
		a.Sale_Summary_Enterprise_Report_Method_For_State(driver);
		a.Sale_Summary_Enterprise_Report_Method_For_ZipCode(driver);
	}
	
	
	
	@Test(priority=488)
	public void End_Enterprise_Reports()
	{
		test.log(LogStatus.INFO, "-------Enterprise Reports Ended----");
	}

}