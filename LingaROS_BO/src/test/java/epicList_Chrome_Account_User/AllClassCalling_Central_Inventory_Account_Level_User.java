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

public class AllClassCalling_Central_Inventory_Account_Level_User 
{ 
	
	public WebDriver driver;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("BO-Central Inventory (Account Lever User Login)");
	
	float unknownValue = 00;
	
	@AfterClass
	public void flushTest() throws Exception
	{
		Thread.sleep(2000);
		rep.endTest(test);
		rep.flush();
		Thread.sleep(15000);
		SendMail_Central_Inventory.snedMailWithAttachment();    
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
	
	@Test(priority=300)
	public void Start_Central_Inventory()
	{
		test.log(LogStatus.INFO, "-------Central Inventory Started (Account Level User Login)----");
	}
	
	
	@Test(priority=312)
	public void Central_Inventory_Central_WareHouse() throws Exception
	{
		Central_Inventory_Add_Edit_Central_WareHouse a = new Central_Inventory_Add_Edit_Central_WareHouse();
		a.Central_WareHouse_openpage(driver);
		a.Central_WareHouse_Creation(driver); 
		a.Central_WareHouse_Edit(driver);
		a.Central_WareHouse_Dashboard(driver);
		a.Central_warehouse_Reports(driver);
	}
 
	@Test(priority=313)
	public void Central_Inventory_Central_Kitchen() throws Exception
	{
		Central_Inventory_Add_Edit_Central_Kitchen a = new Central_Inventory_Add_Edit_Central_Kitchen();
		a.Central_Kitchen_openpage(driver);
		a.Central_Kitchen_Creation(driver);
		a.Central_Kitchen_Edit(driver);
		a.Central_Kitchen_Dashboard(driver);
		a.Central_Kitchen_Reports(driver);
	}
	
	@Test(priority=314)
	public void Central_Inventory_Central_Inventory_Menu_Configuration() throws Exception
	{
		Central_Inventory_MenuConfiguration a = new Central_Inventory_MenuConfiguration();
		a.Inventory_MenuConfiguration_openpage(driver);
		a.Inventory_MenuConfiguration_Store_option(driver);
		a.Inventory_MenuConfiguration_Centralwarehouse_option(driver);
		a.Inventory_MenuConfiguration_CentralKitchen_option(driver);
		a.Inventory_MenuConfig_Store_To_Centralware(driver);
		a.Inventory_MenuConfig_Centralware_To_Centralkitchen(driver);
		a.Inventory_MenuConfig_CentralKitchen_To_Store(driver);
	}
	
	@Test(priority=315)
	public void Central_Inventory_Transfer_Request() throws Exception
	{
		Central_Inventory_Add_Edit_Transfer_Request a = new Central_Inventory_Add_Edit_Transfer_Request();
		a.Central_Transfer_Request_openpage(driver);
		a.Central_Transfer_Request_Cancle(driver);
		a.Central_Transfer_Request_Request(driver);
		a.Central_Transfer_Request_QuantityNotAvailable(driver);
	}
	
	@Test(priority=316)
	public void Central_Inventory_Sync_Inventory() throws Exception
	{
		Central_Inventory_Sync_Inventory a =  new Central_Inventory_Sync_Inventory();
		a.Central_Sync_Inventory_openpage(driver);
		a.Central_Sync_Inventory_update(driver);
	}

	@Test(priority=320)
	public void End_Central_Inventory()
	{
		test.log(LogStatus.INFO, "-------Central Inventory Ended----");
	}
}
