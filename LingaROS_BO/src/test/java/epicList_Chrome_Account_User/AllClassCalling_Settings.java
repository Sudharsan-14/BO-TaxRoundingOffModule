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

public class AllClassCalling_Settings 
{
	public WebDriver driver;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("BO-Settings (New UI)  (Account Lever User Login)");
	
	float unknownValue = 00;
	
	@AfterClass
	public void flushTest() throws Exception
	{
		Thread.sleep(2000);
		rep.endTest(test);
		rep.flush();
		Thread.sleep(15000);
		SendMail_Settings.snedMailWithAttachment();  
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
	{		Browser a = new Browser();
	a.Logout(driver, test);
	}
	
	@Test(priority=3)
	public void Start_Settings()
	{
		test.log(LogStatus.INFO, "-------Settings Started----");
	}
	/*
	@Test(priority=2)
	public void Store_Settings_EMV_Setting() throws Exception
	{
		Setting_EMV_Setting a = new Setting_EMV_Setting();
		a.EMV_Settings_Method_openpage(driver);
		a.EMV_Settings_Method_refresh(driver);
		a.EMV_Settings_Method_pagination(driver);
		a.EMV_Settings_Method_add(driver);
		a.EMV_Settings_Method_edit(driver);
		a.EMV_Settings_Method_delete(driver);
		a.EMV_Settings_Method_close_Button(driver);
	}
	
	@Test(priority=3)
	public void Store_Settings_Cash_Discount() throws Exception
	{
		Settings_Cash_Discount a = new Settings_Cash_Discount();
		a.Cash_Discount_Method_openpage(driver);
		a.Cash_Discount_Method_Edit_Amount(driver);
		a.Cash_Discount_Method_Edit_Percentage(driver);
	}

	@Test(priority=4)
	public void Store_Settings_Denomination() throws Exception
	{
		Settings_Denomination a = new Settings_Denomination();
		a.Denominations_Setting_Method_Pageopen(driver);
		a.Denominations_Setting_Method_refresh(driver);
		a.Denominations_Setting_Method_pagination(driver);
		a.Denominations_Setting_Method_add(driver);
		a.Denominations_Setting_Method_delete(driver);
		a.Denominations_Setting_Method_close_Button(driver);
	}
*/
	@Test(priority=5)
	public void Store_Settings_Store_Information() throws Exception
	{
		//Thread.sleep(5000);
		Settings_Store_Information a = new Settings_Store_Information();
		a.Store_Information_openpage(driver);
		a.Store_Information_editpage(driver);
	}

	@Test(priority=6)
	public void Store_Settings_POS_Licenses() throws Exception
	{
		Settings_Store_POSLicenses a=new Settings_Store_POSLicenses();
		a.POS_License_Open_POS_License_Page(driver);
		a.POS_License_Refresh_Page(driver);
		a.POS_Licenses_Pagination(driver);
		a.POS_Licenses_Revert(driver);
	}
	
	
	@Test(priority=7)
	public void Store_Hourly_Settings() throws Exception
	{
		Settings_Store_Hours a = new Settings_Store_Hours();
		a.Store_Hours_Setting_Method_pageopen(driver);
		a.StoreHours_Setting_Method_process(driver);
		a.StoreHours_hour_update(driver);
		a.StoreHours_hour_Shift(driver);
		//StoreHours_DayPart(driver);
	}
	
	/*
		//	8.....Table Layout 
*/
	
	@Test(priority=9)
	public void Store_Settings_Wait_List() throws Exception
	{
		Settings_Wait_List a = new Settings_Wait_List();
		a.Wait_List_Settings_Method_open(driver);
		a.Wait_List_Settings_Method_refresh(driver);
	//	a.Wait_List_Settings_Method_pagination(driver);
		a.Wait_List_Settings_Method_add(driver);
		a.Wait_List_Settings_Method_delete(driver);
		a.Wait_List_Settings_Method_close_Button(driver);
		//a.Wait_List_Reason_Settings_Method_pagination(driver);
		a.Wait_List_Reason_Settings_Method_add(driver);
		a.Wait_List_Reason_Settings_Method_delete(driver);
		a.Wait_List_Reason_Settings_Method_close_Button(driver);
	}
	
	@Test(priority=10)
	public void Settings_Service_Charges() throws Exception
	{
		Settings_Service_Charge a = new Settings_Service_Charge();
		a.Service_Charge_Open_Service_Charge_Page(driver);
		a.Service_Charge_Refresh(driver);
		//a.Service_Charge_Pagination(driver);
		a.Service_Charge_Add_Service_Charge(driver);
		a.Service_Charge_Edit_Service_Charge(driver);
		a.Service_Charge_Delete_Service_Charge(driver);
		a.Service_Charge_Close_Button(driver);
	}
	
	@Test(priority=11)
	public void Store_Settings_Revenue_Center_Settings() throws Exception
	{
		Settings_Store_Revenue_Center a=new Settings_Store_Revenue_Center();
		a.Revenue_Center_Open_Revenue_Center(driver);
		a.Revenue_Center_Refresh_Page(driver);
		a.Revenue_Center_Add(driver);
		a.Scale_Barcode_Pagination(driver);
	    a.Revenue_Center_Edit(driver);
	    a.Revenue_Center_Delete(driver);
	}
	@Test(priority=12)
	public void Store_Settings_ScaleBarcode_Settings() throws Exception
	{
		Settings_Store_ScaleBarcode a=new Settings_Store_ScaleBarcode();
		a.Scale_Barcode_Open_Scale_Barcode(driver);
		a.Scale_Barcode_Refresh_Page(driver);
	//	a.Scale_Barcode_Pagination(driver);
		a.Scale_Barcode_Add(driver);
	    a.Scale_Barcode_Edit(driver);
	    a.Scale_Barcode_Inactivate(driver);
	    a.Scale_Barcode_Activate(driver);
	}
	

	
	@Test(priority=109)
	public void Store_Settings_Application_Settings() throws Exception
	{
		Setting_Application_settings a = new Setting_Application_settings();
		a.Application_Settings_Method_open(driver);
		a.Application_Settings_Method_edit(driver);
	}
	
	@Test(priority=110)
	public void Store_Settings_Payment_Settings() throws Exception
	{
		Settings_Payment a = new Settings_Payment();
		a.Settings_Payment_Method_open(driver);
		a.Payment_Settings_Method_edit(driver);
	}
	
	
	@Test(priority=15)
	public void Store_Settings_Front_End_Receipt() throws Exception
	{
		Settings_Front_End_Receipt a = new Settings_Front_End_Receipt();
		a.Front_End_Receipt_method_open_Front_End_Receipt(driver);
		a.Front_End_Receipt_method_update_FrontEndReceipt_Template(driver);
		a.Front_End_Receipt_method_verifyUpdatedDetails(driver);
	}
	
	@Test(priority=16)
	public void Store_Settings_Kitchen_Receipt_Template() throws Exception
	{
		Settings_Kitchen_Receipt_Template a = new Settings_Kitchen_Receipt_Template();
		a.Kitchen_Receipt_Template_method_open_Kitchen_Receipt_Template(driver);
		a.Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template(driver);
		a.Verify_Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template(driver);
		a.Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template_Enable_Consolidate_Menu_In_Kitchen(driver);
		a.Verify_Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template_Enable_Consolidate_Menu_In_Kitchen(driver);
	}
	
	
	@Test(priority=17)
	public void Store_Settings_Email_Receipt_Template() throws Exception
	{
		Settings_Email_Receipt_Template a = new Settings_Email_Receipt_Template();
		a.Email_Receipt_Template_method_open_Email_Receipt_Template(driver);
		a.Email_Receipt_Template_method_update_Email_Receipt_Template(driver);
		a.Verify_Email_Receipt_Template_method_update_Email_Receipt_Template(driver);
	}
	
	
	@Test(priority=18)
	public void Store_Settings_KDS_Configuration() throws Exception
	{
		Settings_KDS_Configuration a = new Settings_KDS_Configuration();
		a.KDS_Configuration_Open_Page(driver);
		a.KDS_Configuration_update_KDS_Configuration(driver);
		a.Verify_KDS_Configuration_method_update_KDS_Configuration(driver);	
	}
	
	@Test(priority=19)
	public void Store_Settings_Kitchen_Printers() throws Exception
	{
		Settings_Add_Edit_Delete_Kitchen_Printer a = new Settings_Add_Edit_Delete_Kitchen_Printer();
		a.Kitchen_Printer_method_open_Kitchen_Printer(driver);
		a.Kitchen_Printer_method_refreshKitchen_Printer(driver);
		a.Kitchen_Printer_method_pagination(driver);
		a.Kitchen_Printer_method_add_Kitchen_Printer(driver);
		a.Kitchen_Printer_method_edit_Kitchen_Printer(driver);
		a.Kitchen_Printer_method_delete_Kitchen_Printer(driver);
		a.Kitchen_Printer_method_close_Button(driver);
	}
	
	@Test(priority = 120)
	public void Store_Settings_Printer_Reroute() throws Exception
	{
		Settings_Printer_Reroute a=new Settings_Printer_Reroute();
		a.Printer_Reroute_Open_Printer_Reroute(driver);
		a.Printer_Reroute_refresh_Page(driver);
		a.Printer_Reroute_pagination(driver);
		a.Printer_Reroute_Add_Printer_Reroute_By_Node(driver);
		a.Printer_Reroute_Edit_Printer_Reroute_By_Node_Days_Of_Week(driver);
		a.Printer_Reroute_Edit_Printer_Reroute_By_Node_Days_Of_Month(driver);
		a.Printer_Reroute_Edit_Printer_Reroute_By_Node_Date_Range(driver);
		a.Printer_Reroute_Edit_Printer_Reroute_By_Node_Specific_Date(driver);
		a.Printer_Reroute_Edit_Printer_Reroute_By_Node_Start_Date_And_End_Date_Time(driver);
		a.Printer_Reroute_Same_From_And_TO_Printer(driver);
		a.Printer_Reroute_Delete_Printer_Reroute_By_Node(driver);
		a.Printer_Reroute_Add_Printer_Reroute_By_Role(driver);
		a.Printer_Reroute_Edit_Printer_Reroute_By_Role_Days_Of_Week(driver);
		a.Printer_Reroute_Edit_Printer_Reroute_By_Role_Days_Of_Month(driver);
		a.Printer_Reroute_Edit_Printer_Reroute_By_Role_Date_Range(driver);
		a.Printer_Reroute_Edit_Printer_Reroute_By_Role_Specific_Date(driver);
		a.Printer_Reroute_Edit_Printer_Reroute_By_Role_Start_Date_And_End_Date_Time(driver);
		a.Printer_Reroute_Delete_Printer_Reroute_By_Role(driver);
		a.Printer_Reroute_Verify_Kitchen_KDS_Available_Or_Not(driver);

	}
	
	@Test(priority=21)
	public void Store_Settings_Receipt_Printers() throws Exception
	{
		Settings_Add_Edit_Delete_Receipt_Printer a = new Settings_Add_Edit_Delete_Receipt_Printer();
		a.Receipt_Printer_method_open_Receipt_Printer(driver);
		a.Receipt_Printer_method_refreshReceipt_Printer(driver);
		a.Receipt_Printer_method_pagination(driver);
		a.Receipt_Printer_method_add_Receipt_Printer(driver);
		a.Receipt_Printer_method_edit_Receipt_Printer(driver);
		a.Receipt_Printer_method_delete_Receipt_Printer(driver);
		a.Receipt_Printer_method_close_Button(driver);
	}
	
	@Test(priority=22)
	public void Store_Settings_Label_Printers() throws Exception
	{
		Settings_Add_Edit_Delete_Label_Printer a = new Settings_Add_Edit_Delete_Label_Printer();
		a.Label_Printer_method_open_Label_Printer(driver);
		a.Label_Printer_method_refreshLabel_Printer(driver);
		a.Label_Printer_method_pagination(driver);
		a.Label_Printer_method_add_Label_Printer(driver);
		a.Label_Printer_method_edit_Label_Printer(driver);
		a.Label_Printer_method_delete_Label_Printer(driver);
		a.Label_Printer_method_close_Button(driver);
	}
	
	@Test(priority=23)
	public void Store_Settings_Kitchen_Label_Template() throws Exception
	{
		Settings_Kitchen_Label_Template a = new Settings_Kitchen_Label_Template();
		a.Kitchen_Label_Template_method_open_Kitchen_Label_Template(driver);
		a.Kitchen_Label_Template_method_update_Kitchen_Label_Template(driver);
		a.Verify_Kitchen_Label_Template_method_Kitchen_Label_Template(driver);
	}
	
	@Test(priority=24)
	public void Store_Settings_Label_Template() throws Exception
	{
		Settings_Label_Template a = new Settings_Label_Template();
		a.Label_Template_method_open_Label_Template(driver);
		a.Label_Template_method_update_Label_Template(driver);
		a.Label_Template_method_update_Label_Template1(driver);
		a.Label_Template_method_update_Label_Template2(driver);
	}
	
	@Test(priority=25)
	public void Store_Other_Payment_Receipt_Template() throws Exception
	{
		Settings_Other_Payment_Receipt_Template a = new Settings_Other_Payment_Receipt_Template();
		a.Other_Payment_method_open_Other_Payment_Receipt_Template(driver);
		a.Update_Other_Payment_method_Other_Payment_Receipt_Template(driver);
		a.Verify_Other_Payment_method_Other_Payment_Receipt_Template(driver);
	}
	
	@Test(priority=26)
	public void Store_Settings_Till_Settings() throws Exception
	{
		Settings_Till a = new Settings_Till();
		a.Till_Settings_Method_open(driver);
		a.Till_Settings_Method_edit(driver);
		a.Till_Settings_Method_Denomination(driver);
		a.Till_Settings_Pagination(driver);
		a.Till_Settings_Select_Search(driver);
		a.Till_Settings_BankDeposit_Add(driver);	
	}
	
	
	@Test(priority=115)
	public void Store_Settings_Delivery_Settings() throws Exception
	{
		Settings_Delivery a = new Settings_Delivery();
		a.Delivery_Settings_Method_open(driver);
		a.Delivery_Settings_Method_edit(driver);
	}
	
	
	@Test(priority=28)
	public void Store_Settings_PUC_Settings() throws Exception
	{
		Settings_Puc a = new Settings_Puc();
		a.PLU_Settings_Method_open(driver);
		a.PLU_Settings_Method_edit(driver);	
	}
	
	
	@Test(priority=29)
	public void Store_Settings_CDS_Settings() throws Exception
	{
		Settings_CDS_Settings a = new Settings_CDS_Settings();
		a.CDS_Settings_method_Open_CDS_Settings(driver);
		a.Update_CDS_Settings_method_CDS_Settings(driver);
		a.Verify_CDS_Settings_method_CDS_Settings(driver);
	}
	
	@Test(priority=30)
	public void Store_Settings_OT_Settings() throws Exception
	{
		Settings_OT_Setings a = new Settings_OT_Setings();
		a.OT_Settings_Method_openpage(driver);
		a.OT_Settings_Method_Add_Daily(driver);
		a.OT_Settings_Method_Add_Weekly(driver);
		a.OT_Settings_Method_close_Button(driver);
	}
	
	@Test(priority=31)
	public void Store_Settings_SaleRecapReport_Settings() throws Exception
	{
		Settings_SaleRecapReportSettings a = new Settings_SaleRecapReportSettings();
		a.Sale_Recap_Settings_Method_openpage(driver);
		a.Sale_Recap_Settings_Method_edit(driver);
		a.Verify_Sale_Recap_Settings_Method(driver);
	}

	@Test(priority=32)
	public void Store_Settings_Account_Balance_Settings() throws Exception
	{
		Settings_Account_Balance_Settings a=new Settings_Account_Balance_Settings();
		a.Account_Balance_Settings_Method_openpage(driver);
		a.Account_Balance_Settings_Save_Account_Balance_Settings(driver);
	}
	
	@Test(priority=33)
	public void Store_Settings_Tip_Out_Tip_Sharing_Settings() throws Exception
	{
		Settings_Tip_Out_Tip_Sharing a=new Settings_Tip_Out_Tip_Sharing();
		a.Tip_Out_Settings_Method_openpage(driver);
		a.Tip_Out_Sharing_Settings_Save_Settings(driver);
		a.Manual_Tip_Sharing_Settings_Save_Settings(driver);
	}
	
	@Test(priority=34)
	public void Store_Settings_Notification_Settings() throws Exception
	{
		Settings_Notification_Settings a = new Settings_Notification_Settings();
		a.Notification_Settings_Method_open(driver);
		a.Notification_Settings_Method_edit(driver);
	}
	
	@Test(priority=490)
	public void End_Settings()
	{
		test.log(LogStatus.INFO, "-------Settings Ends----");
	}
}
