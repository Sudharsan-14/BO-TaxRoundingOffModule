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
import newReadExcelFile_New.ExcelDataConfig;

import epicList_Chrome_Xpath.AddEditDelete_ProductItems_NEW;

public class AllClassCalling { 
	
	public WebDriver driver;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Linga - Back Office (BO)");
	
	float unknownValue = 00;
	
	@AfterClass
	public void flushTest() throws Exception
	{
		Thread.sleep(2000);
		rep.endTest(test);
		rep.flush();
		SendMail.snedMailWithAttachment();    
	}
	public AllClassCalling() throws Throwable
	{
		Thread.sleep(2000);
		//Call the chrome driver
		System.setProperty("webdriver.chrome.driver",Utility.getProperty("Chrome_Driver_Path"));

		this.driver = new ChromeDriver();
		
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

	
	@Test(priority=30)
	public void Store_Settings_Store_Information() throws Exception
	{
		Thread.sleep(5000);
		Settings_Store_Information a = new Settings_Store_Information();
		a.Store_Information_openpage(driver);
		a.Store_Information_editpage(driver);
	}
	
	@Test(priority=329)
	public void Store_Settings_POS_Licenses() throws Exception
	{
		Settings_Store_POSLicenses a=new Settings_Store_POSLicenses();
		a.POS_License_Open_POS_License_Page(driver);
		a.POS_License_Refresh_Page(driver);
		a.POS_Licenses_Pagination(driver);
		a.POS_Licenses_Revert(driver);
	}
	
	
	@Test(priority=330)
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
		//	33.....Table Layout 
*/
	
	@Test(priority=331)
	public void Store_Settings_Wait_List() throws Exception
	{
		Settings_Wait_List a = new Settings_Wait_List();
		a.Wait_List_Settings_Method_open(driver);
		a.Wait_List_Settings_Method_refresh(driver);
		a.Wait_List_Settings_Method_pagination(driver);
		a.Wait_List_Settings_Method_add(driver);
		a.Wait_List_Settings_Method_delete(driver);
		a.Wait_List_Settings_Method_close_Button(driver);
		//a.Wait_List_Reason_Settings_Method_pagination(driver);
		a.Wait_List_Reason_Settings_Method_add(driver);
		a.Wait_List_Reason_Settings_Method_delete(driver);
		a.Wait_List_Reason_Settings_Method_close_Button(driver);
	}
	
	@Test(priority=332)
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
	@Test(priority=333)
	public void Store_Settings_ScaleBarcode_Settings() throws Exception
	{
		Settings_Store_ScaleBarcode a=new Settings_Store_ScaleBarcode();
		a.Scale_Barcode_Open_Scale_Barcode(driver);
		a.Scale_Barcode_Refresh_Page(driver);
		a.Scale_Barcode_Pagination(driver);
		a.Scale_Barcode_Add(driver);
	    a.Scale_Barcode_Edit(driver);
	    a.Scale_Barcode_Inactivate(driver);
	    a.Scale_Barcode_Activate(driver);
	}
	
	
	@Test(priority=334)
	public void Store_Settings_Application_Settings() throws Exception
	{
		Setting_Application_settings a = new Setting_Application_settings();
		a.Application_Settings_Method_open(driver);
		a.Application_Settings_Method_edit(driver);
	}
	
	@Test(priority=335)
	public void Store_Settings_Payment_Settings() throws Exception
	{
		Settings_Payment a = new Settings_Payment();
		a.Settings_Payment_Method_open(driver);
		a.Payment_Settings_Method_edit(driver);
	}
	
	@Test(priority=39)
	public void Store_Settings_KDS_Configuration() throws Exception
	{
		Settings_KDS_Configuration a = new Settings_KDS_Configuration();
		a.KDS_Configuration_Open_Page(driver);
		a.KDS_Configuration_update_KDS_Configuration(driver);
		a.Verify_KDS_Configuration_method_update_KDS_Configuration(driver);	
	}
	
	@Test(priority=40)
	public void Store_Other_Payment_Receipt_Template() throws Exception
	{
		Settings_Other_Payment_Receipt_Template a = new Settings_Other_Payment_Receipt_Template();
		a.Other_Payment_method_open_Other_Payment_Receipt_Template(driver);
		a.Update_Other_Payment_method_Other_Payment_Receipt_Template(driver);
		a.Verify_Other_Payment_method_Other_Payment_Receipt_Template(driver);
	}
	
	@Test(priority=336)
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
	
	
	@Test(priority=337)
	public void Store_Settings_Delivery_Settings() throws Exception
	{
		Settings_Delivery a = new Settings_Delivery();
		a.Delivery_Settings_Method_open(driver);
		a.Delivery_Settings_Method_edit(driver);
	}
	
	
	@Test(priority=338)
	public void Store_Settings_PUC_Settings() throws Exception
	{
		Settings_Puc a = new Settings_Puc();
		a.PLU_Settings_Method_open(driver);
		a.PLU_Settings_Method_edit(driver);	
	}
	
	
	@Test(priority=44)
	public void Store_Settings_CDS_Settings() throws Exception
	{
		Settings_CDS_Settings a = new Settings_CDS_Settings();
		a.CDS_Settings_method_Open_CDS_Settings(driver);
		a.Update_CDS_Settings_method_CDS_Settings(driver);
		a.Verify_CDS_Settings_method_CDS_Settings(driver);
	}
	
	@Test(priority=45)
	public void Store_Settings_OT_Settings() throws Exception
	{
		Settings_OT_Setings a = new Settings_OT_Setings();
		a.OT_Settings_Method_openpage(driver);
		a.OT_Settings_Method_Add_Daily(driver);
		a.OT_Settings_Method_Add_Weekly(driver);
		a.OT_Settings_Method_close_Button(driver);
	}
	
	@Test(priority=46)
	public void Store_Settings_SaleRecapReport_Settings() throws Exception
	{
		Settings_SaleRecapReportSettings a = new Settings_SaleRecapReportSettings();
		a.Sale_Recap_Settings_Method_openpage(driver);
		a.Sale_Recap_Settings_Method_edit(driver);
		a.Verify_Sale_Recap_Settings_Method(driver);
	}

	@Test(priority=48)
	public void Store_Settings_Account_Balance_Settings() throws Exception
	{
		Settings_Account_Balance_Settings a=new Settings_Account_Balance_Settings();
		a.Account_Balance_Settings_Method_openpage(driver);
		a.Account_Balance_Settings_Save_Account_Balance_Settings(driver);
	}
	
	@Test(priority=49)
	public void Store_Settings_Tip_Out_Tip_Sharing_Settings() throws Exception
	{
		Settings_Tip_Out_Tip_Sharing a=new Settings_Tip_Out_Tip_Sharing();
		a.Tip_Out_Settings_Method_openpage(driver);
		a.Tip_Out_Sharing_Settings_Save_Settings(driver);
		a.Manual_Tip_Sharing_Settings_Save_Settings(driver);
	}
	
	@Test(priority=50)
	public void Store_Settings_Notification_Settings() throws Exception
	{
		Settings_Notification_Settings a = new Settings_Notification_Settings();
		a.Notification_Settings_Method_open(driver);
		a.Notification_Settings_Method_edit(driver);
	}
	

	@Test(priority=51)
	public void Store_Settings_Front_End_Receipt() throws Exception
	{
		Settings_Front_End_Receipt a = new Settings_Front_End_Receipt();
		a.Front_End_Receipt_method_open_Front_End_Receipt(driver);
		a.Front_End_Receipt_method_update_FrontEndReceipt_Template(driver);
		a.Front_End_Receipt_method_verifyUpdatedDetails(driver);
		}
	
	@Test(priority=52)
	public void Store_Settings_Kitchen_Receipt_Template() throws Exception
	{
		Settings_Kitchen_Receipt_Template a = new Settings_Kitchen_Receipt_Template();
		a.Kitchen_Receipt_Template_method_open_Kitchen_Receipt_Template(driver);
		a.Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template(driver);
		a.Verify_Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template(driver);
		a.Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template_Enable_Consolidate_Menu_In_Kitchen(driver);
		a.Verify_Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template_Enable_Consolidate_Menu_In_Kitchen(driver);
	}
	
	@Test(priority=53)
	public void Store_Settings_Email_Receipt_Template() throws Exception
	{
		Settings_Email_Receipt_Template a = new Settings_Email_Receipt_Template();
		a.Email_Receipt_Template_method_open_Email_Receipt_Template(driver);
		a.Email_Receipt_Template_method_update_Email_Receipt_Template(driver);
		a.Verify_Email_Receipt_Template_method_update_Email_Receipt_Template(driver);
	}
	
	@Test(priority=54)
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
	
	@Test(priority=55)
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
	
	@Test(priority=56)
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
	

	@Test(priority=57)
	public void Store_Settings_Kitchen_Label_Template() throws Exception
	{
		Settings_Kitchen_Label_Template a = new Settings_Kitchen_Label_Template();
		a.Kitchen_Label_Template_method_open_Kitchen_Label_Template(driver);
		a.Kitchen_Label_Template_method_update_Kitchen_Label_Template(driver);
		a.Verify_Kitchen_Label_Template_method_Kitchen_Label_Template(driver);
	}

	@Test(priority=58)
	public void Store_Settings_Label_Template() throws Exception
	{
		Settings_Label_Template a = new Settings_Label_Template();
		a.Label_Template_method_open_Label_Template(driver);
		a.Label_Template_method_update_Label_Template(driver);
		a.Label_Template_method_update_Label_Template1(driver);
		a.Label_Template_method_update_Label_Template2(driver);
	}
	
	@Test(priority=59)
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
		Thread.sleep(8000);
	}

	@Test(priority=340)
	public void Report_Verify_Department_Sale_Report() throws Exception
	{
		Thread.sleep(5000);
		Verify_DepartmentSale_Report a = new Verify_DepartmentSale_Report();
		a.Department_Sale_method_OpenReport(driver);
		a.Department_Sale_method_Report_For_Specific_Date(driver);
		a.Department_Sale_method_Report_For_Specific_Date_With_Quantity_Sort(driver);
		a.Department_Sale_method_Report_For_Specific_Date_Without_Quantity_Sort_With_Dynamic_Report(driver);
		a.Department_Sale_method_Report_For_Specific_Date_With_Quantity_Sort_And_Dynamic_Report(driver);
	}
	
	@Test(priority=341)
	public void Report_Verify_Category_Sale_Report() throws Exception
	{
		Verify_CategorySale_Report a = new Verify_CategorySale_Report();
		a.Category_Sale_method_openpage_Report(driver);
		a.Category_Sale_method__Report_For_Specific_Date(driver);
		a.Category_Sale_method__Report_For_Specific_Date_With_Quantity_Sort(driver);
		a.Category_Sale_method__Report_For_Specific_Date_WithOut_Quantity_Sort_With_Dynamic_Report(driver);
		a.Category_Sale_method__Report_For_Specific_Date_With_Quantity_Sort_And_Dynamic_Report(driver);
	}
	
	@Test(priority=342)
	public void Report_Verify_Sub_Category_Sale_Report() throws Exception
	{
		Verify_SubCategorySale_Report a =  new Verify_SubCategorySale_Report();
		a.Sub_Category_Sale_method__OpenPageReport(driver);
		a.Sub_Category_Sale_method_For_Specific_Date(driver);
		a.Sub_Category_Sale_method_For_Specific_Date_With_Quantity_Sort(driver);
		a.Sub_Category_Sale_method_For_Specific_Date_WithOut_Quantity_Sort_With_Dynamic_Report(driver);
		a.Sub_Category_Sale_method_For_Specific_Date_With_Quantity_And_With_Dynamic_Report(driver);
	}
	
	@Test(priority=343)
	public void Report_Verify_Menu_Item_Sale_Report() throws Exception
	{
		Verify_MenuItemSale_Report a = new Verify_MenuItemSale_Report();
		a.Menu_Item_Sale_method_openpage_Report(driver);
		a.Menu_Item_Sale_method__Report_For_Specific_Date(driver);
		a.Menu_Item_Sale_method__Report_For_Specific_Date_With_Quantity_Sort(driver);
		a.Menu_Item_Sale_method__Report_For_Specific_Date_With_Dynamic_Report_Without_Quantity_Sort(driver);
		a.Menu_Item_Sale_method__Report_For_Specific_Date_With_Quantity_Sort_And_Dynamic_Report(driver);
	}
	
	@Test(priority=344)
	public void Report_Verify_Modifier_Sale_Report() throws Exception
	{
		Verify_ModifierSale_Report a = new Verify_ModifierSale_Report();
		a.Modifier_Sale_method_openpage_Report(driver);
		a.Modifier_Sale_method_Report_For_Specific_Date(driver);
		a.Modifier_Sale_method_Report_For_Specific_Date_With_Quantity_Sort(driver);
		a.Modifier_Sale_method_Report_For_Specific_Date_With_Dynamic_Report(driver);
		a.Modifier_Sale_method_Report_For_Specific_Date_With_Quantity_Sort_And_Dynamic_Report(driver);
	}
	
	@Test(priority=345)
	public void Report_Verify_Hourly_Sale_Report() throws Exception
	{
		Verify_HourlySale_Report a = new Verify_HourlySale_Report();
		a.Hourly_Sale_method_openpage_Sale_Report(driver);
		a.Hourly_Sale_method_Report_For_Specific_Date(driver);
		a.Hourly_Sale_method_Report_For_Specific_Date_With_Dynamic_Report(driver);
	}
	
	@Test(priority=346)
	public void Report_Verify_Daily_Sale_Report() throws Exception
	{
		Verify_DailySale_Report a = new Verify_DailySale_Report();
		a.Daily_Sale_method_openpage_Report(driver);
		a.Daily_Sale_method_Report_For_Specific_Date(driver);
		a.Daily_Sale_method_Report_For_Specific_Date_With_Dynamic_Report(driver);
	}
	
	@Test(priority=347)
	public void Report_Verify_Sale_Recap_Report() throws Exception
	{
		Verify_SaleRecap_Report a = new Verify_SaleRecap_Report();
		a.Sale_Recap_Sale_method_openpage_Report(driver);
		a.Sale_Recap_Sale_method_Verify_Report(driver);
	}
	
	@Test(priority=348)
	public void Report_Verify_Cashier_Out_Report() throws Exception
	{
		Verify_Cashier_Report a = new Verify_Cashier_Report();
		a.Cashier_Out_Sale_method_open__Report(driver);
		a.Cashier_Out_Sale_method_verify_Report(driver);
	}
	
	@Test(priority=349)
	public void Report_Verify_Weekly_Summary_Report() throws Exception
	{
		Verify_Weekly_Report a = new Verify_Weekly_Report();
		a.Weekly_SaleSummary_method_Report_Openpage(driver);
		a.Weekly_Summary_Sale_method_Report_This_Week(driver);
		a.Weekly_Summary_Sale_method_Report_Last_Week(driver);
		a.Weekly_Summary_Sale_method_Report_Last7days(driver);
	}
	
	@Test(priority=350)
	public void Report_Verify_Sale_Summary_Report() throws Exception
	{
		Verify_SaleSummary_Report a = new Verify_SaleSummary_Report();
		a.Sale_Summary_Sale_method_Report_open_Page(driver);
		a.Sale_Saummary_Sale_method_Report_For_Specific_Date(driver);
		a.Sale_Saummary_Sale_method_Report_For_Specific_Date_WithService_Type(driver);
		a.Sale_Saummary_Sale_method_Report_For_Specific_Date_WithSearch(driver);
		a.Sale_Saummary_Sale_method_Report_For_Specific_Date_WithSearchAndServiceType(driver);
		a.Sale_Saummary_Sale_method_Report_For_Specific_Date_With_Dynamic_Report(driver);
		a.Sale_Saummary_Sale_method_Report_For_Specific_Date_WithService_Type_And_Dynamic_Report(driver);
		a.Sale_Saummary_Sale_method_Report_For_Specific_Date_WithSearch_And_Dynamic_Report(driver);
		a.Sale_Saummary_Sale_method_Report_For_Specific_Date_WithSearchAndServiceType_And_Dynamic_Report(driver);
	}
	
	@Test(priority=351)
	public void Report_Verify_Transaction_Report() throws Exception
	{
		Verify_Transaction_Report a = new Verify_Transaction_Report();
		a.Transaction_Report_Method_openpage(driver);
		a.Transaction_Report_Method_For_Tender_Type(driver);
		a.Transaction_Report_Method_For_Tender_Name(driver);
	}
	
	@Test(priority=352)
	public void Report_Verify_Void_Transaction_Report() throws Exception
	{
		Verify_Void_Transaction_Report a = new Verify_Void_Transaction_Report();
		a.Void_Transaction_Report_Method_openpage(driver);
		a.Void_Transaction_Report_Method__For_Tender_Type(driver);																																												 
		a.Void_Transaction_Report_Method__For_Tender_Name(driver);
	}
	
	@Test(priority=353)
	public void Report_Verify_Gift_Card_Report() throws Exception
	{
		Verify_Gift_Card_Report a = new Verify_Gift_Card_Report();
		a.Gift_Card_Report_openpage(driver);
		a.Gift_Card_Report_Verify(driver);
		a.Gift_Card_Report_Verify_Card_Search(driver);
	}
	
	@Test(priority=354)
	public void Report_Verify_GiveX_Card_Report() throws Exception
	{
		Verify_GiveX_Card_Report a = new Verify_GiveX_Card_Report();
		a.GiveX_Card_Report_Method_openpage(driver);
		a.GiveX_Card_Report_Method_Verify(driver);
		a.GiveX_Card_Report_Method_Verify_Card_Search(driver);
	}
	
	@Test(priority=355)
	public void Report_Verify_Discount_Report() throws Exception
	{
		Verify_Discount_Report a = new Verify_Discount_Report();
		a.Discount_Report_Method_OpenPage(driver);
		a.Discount_Report_Method_For_Summary(driver);
		a.Discount_Report_Method_For_ByEmployee(driver);
		a.Discount_Report_Method_For_ByDiscount(driver);
		a.Discount_Report_Method_For_ByDiscountType(driver);
		a.Discount_Report_Search_Field_Discount_Report(driver);
	}
	
	@Test(priority=356)
	public void Report_Verify_Void_Node_Report() throws Exception
	{
		Verify_Void_Node_Report a = new Verify_Void_Node_Report();
		a.Node_Report_Method_Openpage(driver);
		a.Node_Report_Method_Verify(driver);
	}
	
	@Test(priority=357)
	public void Report_Verify_Void_Employee_Report() throws Exception
	{
		Verify_Void_Employee_Report a = new Verify_Void_Employee_Report();
		a.Void_Node_Report_Method_Openpage(driver);
		a.Void_Node_Report_Method_Employees(driver);
	}
	
	@Test(priority=358)
	public void Report_Verify_Paid_In_And_Paid_Out_Report() throws Exception
	{
		Verify_PaidIn_Or_PaidOut_Report a = new Verify_PaidIn_Or_PaidOut_Report();
		a.PaidIn_Or_PaidOut_Report_Method_openpage(driver);
		a.PaidIn_Or_PaidOut_Report_Method_paidIn_VerifyALL(driver);
		a.PaidIn_Report_Method(driver);
		a.PaidOut_Report_Method(driver);
	}
	
	@Test(priority=359)
	public void Report_Verify_Employee_Attendance_Report() throws Exception
	{
		Verify_Employee_Attendance_Report a = new Verify_Employee_Attendance_Report();
		a.Employee_Attendance_Report_Method_OpenPage(driver);
		a.Employee_Attendance_Report_Method_For_InTime(driver);
		a.Employee_Attendance_Report_Method_For_InTime_With_Dynamic_Report(driver);
		a.Employee_Attendance_Report_Method_For_InHours(driver);
		a.Employee_Attendance_Report_Method_For_InHours_With_Dynamic_Report(driver);
	}
	
	@Test(priority=360)
	public void Report_Verify_Employee_Payroll_Report() throws Exception
	{
		Verify_Employee_Payroll_Reports a = new Verify_Employee_Payroll_Reports();
		a.Employee_Payroll_Report_Method_DailyReport_Openpage(driver);
		a.Employee_Payroll_Report_Method_verifyDailyReport(driver);
		a.Employee_Payroll_Report_Method_verifyDailyReport_With_Dynamic_Report(driver);
		a.Employee_Payroll_Report_Method_verifyWeeklyReport(driver);
		a.Employee_Payroll_Report_Method_verifyWeeklyReport_With_Dynamic_Report(driver);
	}
	
	@Test(priority=361)
	public void Report_Verify_Employee_Role_Based_Payroll_Report() throws Exception
	{
		Verify_Employee_Role_Based_Payroll_Reports a = new Verify_Employee_Role_Based_Payroll_Reports();
		a.Employee_Role_Based_Payroll_Report_Method_Page_Open(driver);
		a.Employee_Role_Based_Payroll_Report_Method_verifyDailyReport(driver);
		a.Employee_Role_Based_Payroll_Report_Method_verifyWeeklyReport(driver);
	}
	
	@Test(priority=362)
	public void Report_Verify_Employee_Labour_Report() throws Exception
	{
		Verify_Employee_Labour_Reports a = new Verify_Employee_Labour_Reports();
		a.Employee_Labour_Report_Method_DailyReport_Openpage(driver);
		a.Employee_Labour_Report_Method_verifyDailyReport(driver);
		a.Employee_Labour_Report_Method_verifyWeeklyReport(driver);
	}
	
	@Test(priority=363)
	public void Report_Verify_Employee_Labor_By_Job_Code_Report() throws Exception
	{
		Verify_Employee_Labour_By_Job_Code_Reports a = new Verify_Employee_Labour_By_Job_Code_Reports();
		a.Employee_Labour_By_Job_Code_Report_Method_PageOpen(driver);
		a.Employee_Labour_By_Job_Code_Report_Method_verifyDailyReport(driver);
		a.Employee_Labour_By_Job_Code_Report_Method_verifyWeeklyReport(driver);
	}
	
	@Test(priority=364)
	public void Report_Verify_Employee_Gratuity_Report() throws Exception
	{
		Verify_Employee_Gratuity_Reports a = new Verify_Employee_Gratuity_Reports();
		a.Employee_Gratuity_Report_Method_OpenPage(driver);
		a.Employee_Gratuity_Report_Method_Verify(driver);
	}
	
	@Test(priority=365)
	public void Report_Verify_Employee_Cash_Tip_Report() throws Exception
	{
		Verify_Employee_Cash_Tip_Reports a = new Verify_Employee_Cash_Tip_Reports();
		a.Employee_CashTip_Report_Method_OpenPage(driver);
		a.Employee_CashTip_Report_Method_verify(driver);
	}
	
	@Test(priority=366)
	public void Report_Verify_HA_Activity_Report() throws Exception
	{
		Verify_HouseAccount_Activity_Reports a = new Verify_HouseAccount_Activity_Reports();
		a.Employee_HAActivity_Repor_Method_Openpage(driver);
		a.Employee_HAActivity_Repor_Method_verify(driver);
		a.Employee_HAActivity_Repor_Method_verify_Card_Number(driver);
	}
	
	@Test(priority=367)
	public void Report_Verify_HA_Statement_Report() throws Exception
	{
		Verify_HouseAccount_Statement_Reports a = new Verify_HouseAccount_Statement_Reports();
		a.Employee_HAStatement_Report_Method_Openpage(driver);
		a.Employee_HAStatement_Report_Method_verifyALL(driver);
	}
	
	@Test(priority=368)
	public void Report_Verify_Driver_Report() throws Exception
	{
		Verify_Driver_Report a = new Verify_Driver_Report();
		a.Driver_Report_Method_Openpage(driver);
		a.Driver_Report_Method_verify(driver);
	}
	
	@Test(priority=369)
	public void Report_Verify_Batch_Report() throws Exception
	{
		Verify_Batch_Reports a = new Verify_Batch_Reports();
		a.Batch_Report_Method_OpenPage(driver);
		a.Batch_Report_Method_verify(driver);
	}
	
	@Test(priority=370)
	public void Report_Verify_Refund_Report() throws Exception
	{
		Verify_Refund_Report a = new Verify_Refund_Report();
		a.Refund_Report_Method_Openpage(driver);
		a.Refund_Report_Method_verify_By_Payment_Type(driver);
		a.Refund_Report_Method_verify_By_Payment_Name(driver);
	}
	
	@Test(priority=371)
	public void Report_Verify_Till_Report() throws Exception
	{
		Verify_Till_Reports a = new Verify_Till_Reports();
		a.Till_Report_Method_viewpage(driver);
		a.Till_Report_Method_Global(driver);
		a.Till_Report_Method_User(driver);
		a.Till_Report_Method_verifyAll(driver);
	}
	
	@Test(priority=372)
	public void Report_Verify_Till_Cash_Transaction_Report() throws Exception
	{
		Verify_Till_Cash_Transaction_Reports a = new Verify_Till_Cash_Transaction_Reports();
		a.CashTransaction_Report_Method_OpenPage(driver);
		a.CashTransaction_Report_Method_verifyAll(driver);
	}
	
	@Test(priority=373)
	public void Report_Verify_Comparision_Report() throws Exception
	{
		Verify_Comparision_Report a = new Verify_Comparision_Report();
		a.Comparision_Report_Method_viewOpage(driver);
		a.Comparision_Report_Method_verify_By_Payment_Type(driver);
	}
	
	@Test(priority=374)
	public void Report_Verify_Customer_Preference_Report() throws Exception
	{
		Verify_Customer_Performance_Report a = new Verify_Customer_Performance_Report();
		a.CustomerPerformance_Report_Method_viewpage(driver);
		a.CustomerPerformance_Report_Method_verify(driver);
		a.CustomerPerformance_Report_Method_WithoutCategorySubcategoryAdnServingSize(driver);
	}
	
	@Test(priority=375)
	public void Report_Verify_Daily_Tender_Report() throws Exception
	{
		Verify_DailyTender_Report a = new Verify_DailyTender_Report();
		a.DailyTender_Report_Method_viewPage(driver);
		a.DailyTender_Report_Method_verify(driver);
	}
	
	@Test(priority=376)
	public void Report_Verify_Tax_Report() throws Exception
	{
		Verify_Tax_Report a = new Verify_Tax_Report();
		a.Tax_Report_Method_viewPage(driver);
		a.Tax_Report_Method_verify(driver);
	}
	
	@Test(priority=377)
	public void Report_Verify_Revenue_Center_Report() throws Exception
	{
		Verify_Revenue_Center_Report a = new Verify_Revenue_Center_Report();
		a.Revenue_Center_Report_Open_Page_Report(driver);
		a.Revenue_Center_Report_By_All_Options_Selected(driver);
		a.Revenue_Center_Report_By_Required_Options_Selected(driver);
	}
	
	@Test(priority=378)
	public void Report_Verify_Account_Balance_Report() throws Exception
	{
		Verify_Account_Balance_Report a = new Verify_Account_Balance_Report();
		a.Account_Balance_Open_Account_Balance_Report(driver);
		a.Account_Balance_Report_By_All_Options_Selected(driver);
		Thread.sleep(4000);
	}
	
	@Test(priority=151)
	public void Report_Verify_Enterprise_Report() throws Exception
	{
		Thread.sleep(5000);
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
	
	@Test(priority=201)
	public void Product_And_Items_Departments() throws Exception
	{
		AddEditDelete_Departments a = new AddEditDelete_Departments();
		a.Department_method_openDepartments(driver);
		a.Department_method_refreshDepartments_Page(driver);
		a.Department_method_add_Department(driver);
		a.Department_method_edit_Department(driver);
		a.Department_method_delete_Department(driver);
		a.Department_method_cancelDepartemnt(driver);
		a.Department_method_inactiveAndActive_Button(driver);
	}
	
	@Test(priority=202)
	public void Product_And_Items_Coursing() throws Exception
	{
		AddEditDelete_Coursing a = new AddEditDelete_Coursing();
		a.Coursing_method_openCoursing(driver);
		a.Coursing_method_refreshCoursing_Page(driver);
		a.Coursing_method_add_Coursing(driver);
		a.Coursing_method_edit_Coursing(driver);
		a.Coursing_method_delete_Coursing(driver);
		a.Coursing_method_cancelCoursing(driver);
		a.Coursing_method_inactiveAndActive_Button(driver);
	}
	
	@Test(priority=203)
	public void Product_And_Items_Serving_Size_Levels() throws Exception
	{
		AddEditDelete_Serving_Size_Level a = new AddEditDelete_Serving_Size_Level();
		a.Serving_Size_Level_method_openServingSizeLevel(driver);
		a.Serving_Size_Level_method_refreshServingSizeLevel_Page(driver);
		a.Serving_Size_Level_method_add_ServingSizeLevel(driver);
		a.Serving_Size_Level_method_edit_ServingSizeLevel(driver);
		a.Serving_Size_Level_method_delete_ServingSizeLevel(driver);
		a.Serving_Size_Level_method_cancelCoursing(driver);
		a.Serving_Size_Level_method_inactiveAndActive_Button(driver);
	}
	
	@Test(priority=204)
	public void Product_And_Items_Taxes() throws Exception
	{
		AddEditDelete_Tax a = new AddEditDelete_Tax();
		a.Tax_method_openTax_Page(driver);
		a.Tax_method_refreshTax_Page(driver);
		a.Tax_method_add_Tax(driver);
		a.Tax_method_edit_Tax_Item(driver);
		a.Tax_method_edit_Tax_Check(driver);
		a.Tax_method_edit_Tax_TaxOnItemTax(driver);
		a.Tax_method_edit_Tax_TaxOnCheckTax(driver);
		a.Tax_method_delete_Tax(driver);
		a.Tax_method_cancelTax(driver);
		a.Tax_method_inactiveAndActive_Button(driver);
	}
	
	@Test(priority=205)
	public void Product_And_Items_Category() throws Exception
	{
		AddEditDelete_Category a = new AddEditDelete_Category();
		a.Category_method_openCategory_Page(driver);
		a.Category_method_refreshCategory_Page(driver);
		a.Category_method_add_Category(driver);
		a.Category_method_edit_Category_DaysOfWeek(driver);
		a.Category_method_edit_Category_DaysOfMonth(driver);
		a.Category_method_edit_Category_DateRange(driver);
		a.Category_method_edit_Category_SpecificDate(driver);
		a.Category_method_edit_Category_StartDateAndEndDate(driver);
		a.Category_method_delete_Category(driver);
		a.Category_method_cancelCategory(driver);
		a.Category_method_inactiveAndActive_Button(driver);
	}
	
	@Test(priority=206)
	public void Product_And_Items_Sub_Category() throws Exception
	{
		AddEditDelete_SubCategory a = new AddEditDelete_SubCategory();
		a.Subcategory_method_openSubCategory_Page(driver);
		a.Subcategory_method_refreshCategory_Page(driver);
		a.Subcategory_method_add_SubCategory(driver);
		a.Subcategory_method_edit_SubCategory_DaysOfWeek(driver);
		a.Subcategory_method_edit_Sub_Category_DaysOfMonth(driver);
		a.Subcategory_method_edit_Sub_Category_DateRange(driver);
		a.Subcategory_method_edit_Sub_Category_SpecificDate(driver);
		a.Subcategory_method_edit_Sub_Category_StartDateAndEndDate(driver);
		a.Subcategory_method_delete_Sub_Category(driver);
		a.Subcategory_method_cancelSubCategory(driver);
		a.Subcategory_method_inactiveAndActive_Button(driver);
	}
	
	@Test(priority=207)
	public void Product_And_Items_Modifiers() throws Exception
	{
		AddEditDelete_Modifiers a = new AddEditDelete_Modifiers();
		a.Modifiers_method_openModifiers(driver);
		a.Modifiers_method_refreshModifiers_Page(driver);
		a.Modifiers_method_add_Modifiers(driver);
		a.Modifiers_method_edit_Modifiers(driver);
		a.Modifiers_method_delete_Modifier(driver);
		a.Modifiers_method_cancelModifier_BasicDetails(driver);
		a.Modifiers_method_cancelModifier_Prefixes(driver);
		//a.Modifiers_method_add_Modifiers_Save_BasicDetails(driver);
		//a.Modifiers_method_inactiveAndActive_Button(driver);
	}
	
	@Test(priority=208)
	public void Product_And_Items_Modifier_Group() throws Exception
	{
		AddEditDelete_ModifierGroup a = new AddEditDelete_ModifierGroup();
		a.Modifiers_Group_method_openModifier_Groups(driver);
		a.Modifiers_Group_method_refreshModifier_Groups_Page(driver);
		a.Modifiers_Group_method_add_Modifier_Groups(driver);
		a.Modifiers_Group_method_edit_Modifier_Group(driver);
		a.Modifiers_Group_method_edit_Modifier_Group_Customer_Sort(driver);
		a.Modifiers_Group_method_delete_Modifier(driver);
		a.Modifiers_Group_method_cancelModifier_Group(driver);
		a.Modifiers_Group_method_inactiveAndActive_Button(driver);
	}

	@Test(priority=209)
	public void Product_And_Items_Menu_Item() throws Exception
	{
		AddEditDelete_ProductItems_NEW a = new AddEditDelete_ProductItems_NEW();
		a.Product_And_Item_method_openProductsItems(driver);
		a.Product_And_Item_method_refreshMenuItemPage(driver);
		a.Product_And_Item_method_refreshMenuItemPage(driver);
		a.Product_And_Item_method_addMenuItem(driver);
		a.Product_And_Item_method_editMenuItem(driver);
		a.Product_And_Item_method_deleteMenuItem(driver);
		a.Product_And_Item_method_addMenuItemByCopyMenuItem(driver);
		a.Product_And_Item_method_cancelMenuItemByCopyMenuItem_Basic(driver);
		a.Product_And_Item_method_cancelMenuItemByCopyMenuItem_Included_Modifiers(driver);
		a.Product_And_Item_method_cancelMenuItemByCopyMenuItem_Optional_Modifier_Groups(driver);
		a.Product_And_Item_method_cancelMenuItemByCopyMenuItem_Mandatory_Modifier(driver);
		a.Product_And_Item_method_saveMenuItemByCopyMenuItem(driver);
		a.Product_And_Item_method_saveAndContinueMenuItemByCopyMenuItem(driver);
	}

	@Test(priority=210)
	public void Product_And_Items_Bulk_Update() throws Exception
	{
		Bulk_Update a = new Bulk_Update();
		a.Bulk_Update_open_Bulk_Update(driver);
		a.Bulk_Update_refresh_Bulk_Update_Page(driver);
		a.Bulk_Update_pagination(driver);
		a.Bulk_Update_edit_menu_Item_Price_SingleServing_Size(driver);
		a.Bulk_Update_edit_menu_Item_Price_MultipleServing_Size(driver);
		a.Bulk_Update_edit_menu_Item_Price_Many_SingleServing_Size(driver);
	}
	
	@Test(priority=211)
	public void Product_And_Items_Display_Groups() throws Exception
	{
		AddEditDelete_DisplayGroups a = new AddEditDelete_DisplayGroups();
		a.Display_Groups_method_openDisplayGroups(driver);
		a.Display_Groups_method_addDisplayGroup_TimePeriod_AS_Always(driver);
		a.Display_Groups_method_editDisplayGroup(driver);
		a.Display_Groups_method_deleteDisplayGroup(driver);
		a.Display_Groups_method_editDisplayGroup_TimePeriod_AS_DaysOfWeek(driver);
		a.Display_Groups_method_editDisplayGroup_TimePeriod_AS_DaysOfMonth(driver);
		a.Display_Groups_method_editDisplayGroup_TimePeriod_AS_DateRange(driver);
		a.Display_Groups_method_editDisplayGroup_TimePeriod_AS_SpecificDate(driver);
		a.Display_Groups_method_editDisplayGroup_TimePeriod_AS_StartDateAndEndDateTime(driver);
		a.Display_Groups_method_verifyCancelButton(driver);
		a.Display_Groups_method_addSameName(driver);
	}
	
	@Test(priority=212)
	public void Product_And_Items_Item_Based_Discount() throws Exception
	{
		AddEditDelete_DiscountItemBased a = new AddEditDelete_DiscountItemBased();
		a.Item_Based_Discount_method_openDiscountsPage(driver);
		a.Item_Based_Discount_method_refreshDiscountsPage(driver);
		a.Item_Based_Discount_method_addDiscount_ItemBased_ApplicableTimePeriodAsDaysOfWeek(driver);
		a.Item_Based_Discount_method_editDiscount_ItemBased(driver);
		a.Item_Based_Discount_method_deleteDiscount_ItemBased(driver);
		a.Item_Based_Discount_method_cancelButton(driver);
		a.Item_Based_Discount_method_editDiscount_ItemBased_ApplicableTimePeriodAsAlways_SaveAndPublish(driver);
		a.Item_Based_Discount_method_editDiscount_ItemBased_ApplicableTimePeriodAsDaysOfMonth(driver);
		a.Item_Based_Discount_method_editDiscount_ItemBased_ApplicableTimePeriodAsDateRange(driver);
		a.Item_Based_Discount_method_editDiscount_ItemBased_ApplicableTimePeriodAsSpecificDate(driver);
		a.Item_Based_Discount_method_editDiscount_ItemBased_ApplicableTimePeriodAs_StartDateAndEndDate(driver);
		//a.Item_Based_Discount_method_verifyActive_InActiveButton(driver);
		a.Item_Based_Discount_method_addSameName(driver);
	}
	
	@Test(priority=213)
	public void Product_And_Items_Check_Based_Discount() throws Exception
	{
		AddEditDelete_DiscountCheckBased a = new AddEditDelete_DiscountCheckBased();
		a.Check_Based_Discount_method_openDiscount_CheckBasedPage(driver);
		a.Check_Based_Discount_method_refreshDiscount_CheckBasedPage(driver);
		a.Check_Based_Discount_method_addDiscount_CheckBased(driver);
		a.Check_Based_Discount_method_editDiscount_CheckBased(driver);
		a.Check_Based_Discount_method_deleteDiscount_CheckBased(driver);
		a.Check_Based_Discount_method_cancelButton(driver);
		a.Check_Based_Discount_method_editDiscount_CheckBased_TimePeriodAsAlways_SaveAndPublish(driver);
		a.Check_Based_Discount_method_editDiscount_CheckBased_TimePeriodAsDaysOfMonth(driver);
		a.Check_Based_Discount_method_editDiscount_CheckBased_TimePeriodasDateRange(driver);
		a.Check_Based_Discount_method_editDiscount_CheckBased_TimePeriodAsSpecificDate(driver);
		a.Check_Based_Discount_method_editDiscount_CheckBased_TimePeriodAsStartDateTimeAndEndDateTime(driver);
		a.Check_Based_Discount_method_verifyActive_InActiveButton(driver);
		a.Check_Based_Discount_method_addSameName(driver);
	}
	
	@Test(priority=214)
	public void Product_And_Items_Open_Item_Discount() throws Exception
	{
		EditDelete_Discount_OpenItemAmount a = new EditDelete_Discount_OpenItemAmount();
		a.Open_Item_Discount_method_editDiscount_OpenItem(driver);
		a.Open_Item_Discount_method_deleteOrActiveDiscount_OpenItem(driver);
		a.Open_Item_Discount_method_cancelButton(driver);
	}
	
	@Test(priority=215)
	public void Product_And_Items_Open_Check_Discount() throws Exception
	{
		EditDelete_Discount_OpenCheckAmount a = new EditDelete_Discount_OpenCheckAmount();
		a.Open_Check_Discount_method_editDiscount_OpenCheck(driver);
		a.Open_Check_Discount_method_deleteOrActiveDiscount_OpenCheck(driver);
		a.Open_Check_Discount_method_cancelButton(driver);
	}
	
	@Test(priority=216)
	public void Product_And_Items_Mix_And_Max_Discount() throws Exception
	{
		AddEditDelete_Discount_Mix_and_Match a = new AddEditDelete_Discount_Mix_and_Match();
		a.Mix_And_Match_method_open_And_refreshMixandMatch(driver);
		a.Mix_And_Match_method_addMixandMatch_ApplicableTimePeriodAsAlways(driver);
		a.Mix_And_Match_method_editMixandMatch(driver);
		a.Mix_And_Match_method_deleteAndActivated_MixandMatch(driver);
		a.Mix_And_Match_method_cancelButton(driver);
		a.Mix_And_Match_method_editMixandMatch_ApplicableTimePeriodAsDaysofWeek(driver);
		a.Mix_And_Match_method_editMixandMatch_ApplicableTimePeriodAsDaysOfMonth(driver);
		a.Mix_And_Match_method_editMixandMatch_ApplicableTimePeriodAsDateRange(driver);
		a.Mix_And_Match_method_editMixandMatch_ApplicableTimePeriodAsSpecificDate(driver);
		a.Mix_And_Match_method_editMixandMatch_ApplicableTimePeriodAsStartDateTimeAndEndDateTime(driver);
	}
	
	@Test(priority=217)
	public void Product_And_Items_Upcharges() throws Exception
	{
		AddEditDelete_UpCharges a = new AddEditDelete_UpCharges();
		a.Upcharges_method_openUpchargesPage(driver);
		a.Upcharges_method_refreshUpchargesPage(driver);
		a.Upcharges_method_addUpCharges_ApplicableTimePeriodAsAlways(driver);
		a.Upcharges_method_editUpCharges(driver);
		a.Upcharges_method_deleteUpCharges(driver);
		a.Upcharges_method_cancelButton(driver);
		a.Upcharges_method_editUpCharges_ApplicableTimePeriodAsDaysOfWeek(driver);
		a.Upcharges_method_editUpCharges_ApplicableTimePeriodAsDaysOfMonth(driver);
		a.Upcharges_method_editUpCharges_ApplicableTimePeriodAsDateRange(driver);
		a.Upcharges_method_editUpCharges_ApplicableTimePeriodAsSpecificDate(driver);
		a.Upcharges_method_editUpCharges_ApplicableTimePeriodAsStartDateTimeAndEndDateTime(driver);
	}
	
	@Test(priority=218)
	public void Product_And_Items_Retail_Item() throws Exception
	{
		AddEditDelete_ProductItems_RetailItem_NEW a = new AddEditDelete_ProductItems_RetailItem_NEW();
		a.Retail_Item_Open_Retail_Item_Page(driver);
		a.Retail_Item_Refresh_Page(driver);
		a.Retail_Item_Pagination(driver);
		a.Retail_Item_add_Retail_Item(driver);
		a.Retail_Item_Edit_Retail_Item(driver);
		a.Retail_Item_Delete_Retail_Item(driver);
		a.Retail_Item_Add_RetailItem_By_Copy_RetailItem(driver);
		a.Retail_Item_cancelRetailItemByCopyRetailItem_MenuDetails(driver);
		a.Retail_Item_cancelRetailItemByCopyRetailItem_Inventory_Details(driver);
		a.Retail_Item_cancelRetailItemByCopyRetailItem_Childs(driver);
		a.Retail_Item_saveAndContinueRetailItemByCopyRetailItem(driver);
	}
	
	@Test(priority=219)
	public void Product_And_Items_Sort_Menu_Configuration() throws Exception
	{
		Sort_Menu_Config a = new Sort_Menu_Config();
		a.Sort_Menu_Config_openSortMenuConfig(driver);
		a.Sort_Menu_Config_Category_Sorting(driver);
		a.Sort_Menu_Config_Subctegory_Sorting(driver);
		a.Sort_Menu_Config_MenuItem_Sorting(driver);
		a.Sort_Menu_Config_closeButton(driver);
	}
		
	@Test(priority=222)
	public void Product_And_Items_Gratuities() throws Exception
	{
		AddEditDelete_Gratuity a = new AddEditDelete_Gratuity();
		a.Gratuity_method_GratuitiesPage(driver);
		a.Gratuity_method_refreshGratuitiesPage(driver);
		a.Gratuity_method_addGratuities_Fixed(driver);
		a.Gratuity_method_editGratuity(driver);
		a.Gratuity_method_deleteGratuity(driver);
		a.Gratuity_method_closeButton(driver);
		a.Gratuity_method_sameName(driver);
	}
	
	@Test(priority=223)
	public void Product_And_Items_Gift_Cards() throws Exception
	{
		AddEditDelete_GiftCard a = new AddEditDelete_GiftCard();
		a.Giftcard_method_openGiftCard(driver);
		a.Giftcard_method_refreshGiftCard(driver);
		a.Giftcard_method_addGiftCard(driver);
		a.Giftcard_method_editGiftCard(driver);
		a.Giftcard_method_deleteGiftCard(driver);
		a.Giftcard_method_cancelButton(driver);
		a.Giftcard_method_sameName(driver);
		a.Giftcard_method_recharge(driver);
	}
	
	@Test(priority=224)
	public void Product_And_Items_Cut_And_Modify() throws Exception
	{
		Edit_CutAndModify a = new Edit_CutAndModify();
		a.Cut_and_Modify_Method_openCutAndModify(driver);
		a.Cut_and_Modify_Method_editCutAndModify(driver);
		a.Cut_and_Modify_Method_cancelButton(driver);
	}
	
	@Test(priority=225)
	public void Product_And_Items_Tare_Group() throws Exception
	{
		AddEditDelete_TareGroup a = new AddEditDelete_TareGroup();
		a.Tare_Group_method_openTareGroupPage(driver);
		a.Tare_Group_method_refreshTareGroupPage(driver);
		a.Tare_Group_method_addTareGroup(driver);
		a.Tare_Group_method_editTareGroup(driver);
		a.Tare_Group_method_deleteTareGroup(driver);
		a.Tare_Group_method_cancelButton(driver);
		a.Tare_Group_method_verifyActive_InActiveButton(driver);
		a.Tare_Group_method_sameName(driver);
	}
	
	@Test(priority=226)
	public void Product_And_Items_Tax_Exempt_Reason() throws Exception
	{
		AddEditDelete_TaxExemptReasons a = new AddEditDelete_TaxExemptReasons();
		a.Reasons_TaxExempt_method_openTaxExemptReasons(driver);
		a.Reasons_TaxExempt_method_refreshTaxExemptReasons(driver);
		a.Reasons_TaxExempt_method_addTaxExemptReasons(driver);
		a.Reasons_TaxExempt_method_editTaxExemptReasons(driver);
		a.Reasons_TaxExempt_method_deleteTaxExemptReasons(driver);
		a.Reasons_TaxExempt_method_closeButton(driver);
		a.Reasons_TaxExempt_method_verifyActive_InActiveButton(driver);
	}
	
	@Test(priority=227)
	public void Product_And_Items_Void_Reason() throws Exception
	{
		AddEditDelete_VoidReasons a = new AddEditDelete_VoidReasons();
		a.Reasons_Void_Reasons_method_openVoidReasons(driver);
		a.Reasons_Void_Reasons_method_refreshVoidReasons(driver);
		a.Reasons_Void_Reasons_method_addVoidReasons(driver);
		a.Reasons_Void_Reasons_method_editVoidReasons(driver);
		a.Reasons_Void_Reasons_method_deleteVoidReasons(driver);
		a.Reasons_Void_Reasons_method_closeButton(driver);
		a.Reasons_Void_Reasons_method_verifyActive_InActiveButton(driver);
	}
	
	@Test(priority=228)
	public void Product_And_Items_PaidIn_Reason() throws Exception
	{
		AddEditDelete_PaidIn_Reason a = new AddEditDelete_PaidIn_Reason();
		a.Reasons_PaidIn_method_openPaidInReasons(driver);
		a.Reasons_PaidIn_method_refreshPaidInReasons(driver);
		a.Reasons_PaidIn_method_addPaidInReasons(driver);
		a.Reasons_PaidIn_method_editPaidInReasons(driver);
		a.Reasons_PaidIn_method_deletePaidInReasons(driver);
		a.Reasons_PaidIn_method_closeButton(driver);
		a.Reasons_PaidIn_method_verifyActive_InActiveButton(driver);
	}
	
	@Test(priority=229)
	public void Product_And_Items_Paid_Out_Reason() throws Exception
	{
		AddEditDelete_PaidOut_Reason a = new AddEditDelete_PaidOut_Reason();
		a.Reasons_PaidOut_method_openPaidOutReasons(driver);
		a.Reasons_PaidOut_method_refreshPaidOutReasons(driver);
		a.Reasons_PaidOut_method_addPaidOutReasons(driver);
		a.Reasons_PaidOut_method_editPaidOutReasons(driver);
		a.Reasons_PaidOut_method_deletePaidOutReasons(driver);
		a.Reasons_PaidOut_method_closeButton(driver);
		a.Reasons_PaidOut_method_verifyActive_InActiveButton(driver);
	}
	
	@Test(priority=230)
	public void Product_And_Items_Over_Shortage_Reason() throws Exception
	{
		AddEditDelete_OverShortage_Reason a = new AddEditDelete_OverShortage_Reason();
		a.Reasons_Overshortage_method_openOverShortageReasons(driver);
		a.Reasons_Overshortage_method_refreshOverShortageReasons(driver);
		a.Reasons_Overshortage_method_addOverShortageReasons(driver);
		a.Reasons_Overshortage_method_editOverShortageReasons(driver);
		a.Reasons_Overshortage_method_deleteOverShortageReasons(driver);
		a.Reasons_Overshortage_method_closeButton(driver);
		a.Reasons_Overshortage_method_verifyActive_InActiveButton(driver);
	}
	
	@Test(priority=231)
	public void Product_And_Items_Attach_Request_Reason() throws Exception
	{
		AddEditDelete_AttachRequest_Reason a = new AddEditDelete_AttachRequest_Reason();
		a.Reason_Attach_Request_openAttachRequestReasons(driver);
		a.Reason_Attach_Request_refreshAttachRequestReasons(driver);
		a.Reason_Attach_Request_addAttachRequestReasons(driver);
		a.Reason_Attach_Request_editAttachRequestReasons(driver);
		a.Reason_Attach_Request_deleteAttachRequestReasons(driver);
		a.Reason_Attach_Request_closeButton(driver);
		a.Reason_Attach_Request_verifyActive_InActiveButton(driver);
	}
	
	@Test(priority=251)
	public void Inventory_Inventory_Home() throws Exception
	{
		Inventory_Home a = new Inventory_Home();
		a.Inventory_Home_openpage(driver);
		a.Inventory_Home_refresh_Page(driver);
		a.Inventory_Home_Verify_Reports(driver);
	}
	
	@Test(priority=252)
	public void Inventory_Ingredient_Categories() throws Exception
	{
		Inventory_Ingredient_Categories a = new Inventory_Ingredient_Categories();
		a.Inventory_Ingredient_Categories_openpage(driver);
		a.Inventory_Ingredient_Categories_refresh_page(driver);
		a.Inventory_Ingredient_Categories_add_Invetory_Category(driver);
		a.Inventory_Ingredient_Categories_edit_Invetory_Category(driver);
		a.Inventory_Ingredient_Categories_delete_Invetory_Category(driver);
		a.Inventory_Ingredient_Categories_closeButton(driver);
		a.Inventory_Ingredient_Categories_verifyActive_InActiveButton(driver);
	}
	
	@Test(priority=253)
	public void Inventory_Storage_Locations() throws Exception
	{
		Inventory_Storage_Locations a = new Inventory_Storage_Locations();
		a.Inventory_Storage_Locations_openpage(driver);
		a.Inventory_Storage_Locations_refresh(driver);
		a.Inventory_Storage_Locations_add_Invetory_Storage(driver);
		a.Inventory_Storage_Locations_edit_Storage_Locations(driver);
		a.Inventory_Storage_Locations_delete_Invetory_Storage(driver);
		a.Inventory_Storage_Locations_closeButton(driver);
		a.Inventory_Storage_Locations_verifyActive_InActiveButton(driver);
	}
	
	@Test(priority=254)
	public void Inventory_Vendors() throws Exception
	{
		Inventory_Vendors a = new Inventory_Vendors();
		a.Inventory_Vendors_openpage(driver);
		a.Inventory_Vendors_refreshpage(driver);
		a.Inventory_Vendors_add_Vendor(driver);
		a.Inventory_Vendors_edit_Invetory_Ingredient_Vendor(driver);
		a.Inventory_Vendors_delete(driver);
		a.Inventory_Vendors_closeButton(driver);
		a.Inventory_Vendors_verifyActive_InActiveButton(driver);
	}
	
	@Test(priority=255)
	public void Inventory_In_House_Units() throws Exception
	{
		Inventory_In_House_Units a = new Inventory_In_House_Units();
		a.Inventory_In_HouseUnits_openpage(driver);
		a.Inventory_In_HouseUnits_refresh(driver);
		a.Inventory_In_HouseUnits_add_InHouseUnits(driver);
		a.Inventory_In_HouseUnits_edit_Ingredient_In_House_Units(driver);
		a.Inventory_In_HouseUnits_delete(driver);
		a.Inventory_In_HouseUnits_closeButton(driver);
		a.Inventory_In_HouseUnits_verifyActive_InActiveButton(driver);
	}
	
	@Test(priority=256)
	public void Inventory_Inventory_Items() throws Exception
	{
		Inventory_Items a = new Inventory_Items();
		a.Inventory_Items_openpage(driver);
		a.Inventory_Items_refresh(driver);
		a.Inventory_item_pagination(driver);
		a.Inventory_Items_add(driver);
		a.Inventory_Items_edit_Inventory_Items(driver);
		a.Inventory_Items_delete(driver);
		a.Inventory_Items_closeButton(driver);
		a.Inventory_Items_verifyActive_InActiveButton(driver);
	}
	
	@Test(priority=257)
	public void Inventory_Sub_Receipes() throws Exception
	{
		Inventory_SubRecipes a = new Inventory_SubRecipes();
		a.Inventory_Sub_Recipes_openpage(driver);
		a.Inventory_Sub_Recipes_refresh(driver);
		a.Inventory_Sub_Recipes_add_Invetory_Sub_Recipes(driver);
		a.Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe(driver);
		a.Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_InvITEM_InvITEM(driver);
		a.Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_InvITEM_SubRecipe(driver);
		a.Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_InvITEM_Manual(driver);
		a.Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_SubRecipe_Manual(driver);
		a.Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_SubRecipe_SubRecipe(driver);
		a.Inventory_Sub_Recipes_edit_Invetory_Sub_Recipe_Manual_Manual(driver);
		a.Inventory_Sub_Recipes_delete_Invetory_Sub_Recipe(driver);
		a.Inventory_Sub_Recipes_closeButton_Sub_Recipe(driver);
		a.Inventory_Sub_Recipes_verifyActive_InActiveButton_Sub_Recipe(driver);
	}
	
	@Test(priority=258)
	public void Inventory_Modfiers() throws Exception
	{
		Inventory_Modifiers a = new Inventory_Modifiers();
		a.Inventory_Modifier_HomePage(driver);
		a.Inventory_Modifier(driver);
		a.Inventory_Modifier_Item_Update(driver);
		a.Inventory_Modifier_new_item_modifiers_Update(driver);
		a.Inventory_Modifier_SubRecipe_Update(driver);
		a.Inventory_Modifier__Add_Invetory_Sub_Recipe(driver);
		a.Inventory_Modifier__Add_Invetory_Menu_Item(driver);
		a.Inventory_Modifier__Add_Not_an_InventoryItem(driver);
	}
	
	@Test(priority=259)
	public void Inventory_Menu_Item() throws Exception
	{
		Inventory_Menu_Item a = new Inventory_Menu_Item();
		a.Inventory_Menu_Items_OpenPage(driver);
		a.Inventory_Menu_Items_refresh_page(driver);
		a.Inventory_Menu_Items_verify_UnLinked_TO_Linked(driver);
		a.Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Inv(driver);
		a.Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Inv_Sub(driver);
		a.Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Inv_Manual(driver);
		a.Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Sub_Manual(driver);
		a.Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Sub_Sub(driver);
		a.Inventory_Menu_Items_edit_Linked_Type_of_Menu_Item_Manual_Manual(driver);
		a.Inventory_Menu_Items_verify_Linked_Type_of_Menu_Items_TO_UnLinked_Type_of_Menu_Items(driver);
		a.Inventory_Menu_Items_verifyActive_InActiveButton_Sub_Recipe(driver);
	}
	
	@Test(priority=260)
	public void Inventory_Adjust_Inventory_Reasons() throws Exception
	{
		Inventory_Adjust_Inventory_Reasons a = new Inventory_Adjust_Inventory_Reasons();
		a.Adjust_Inventory_Reasons_openpage(driver);
		a.Adjust_Inventory_Reasons_refresh(driver);
		a.Adjust_Inventory_Reasons_add_PAge(driver);
		a.Adjust_Inventory_Reasons_edit(driver);
		a.Adjust_Inventory_Reasons_delete(driver);
		a.Adjust_Inventory_Reasons_closeButton(driver);
		a.Adjust_Inventory_Reasons_verifyActive_InActiveButton(driver);
	}
	
	@Test(priority=261)
	public void Inventory_Adjust_Inventory_Item() throws Exception
	{
		Inventory_Adjust_Inventory_Item a = new Inventory_Adjust_Inventory_Item();
		a.Adjust_Inventory_Openpage(driver);
		a.Adjust_Inventory_item_Close_button(driver);
		a.Adjust_Inventory_item_Update_button_Increase(driver);
		a.Adjust_Inventory_item_Update_button_Decrease(driver);
		a.Adjust_Inventory_item_Bulk_update(driver);
		a.Adjust_Subrecipe_Bulk_update(driver);
		a.Adjust_Bulk_update_Backbutton(driver);
	}
	
	@Test(priority=262)
	public void Inventory_Low_Stocks() throws Exception
	{
		Inventory_Low_Stacks a = new Inventory_Low_Stacks();
		a.Inventory_Low_stock_Openpage(driver);
		a.Inventory_Low_stock_prepare_SubRecipe(driver);
	}
	
	@Test(priority=263)
	public void Inventory_Store_Settings() throws Exception
	{
		Inventory_Store_Settings a = new Inventory_Store_Settings();
		a.Inventory_Store_setting_Pageopen(driver);
		a.Inventory_Store_setting_Process(driver);
	}
	
	@Test(priority=264)
	public void Inventory_Transfer_Request() throws Exception
	{
		Inventory_Transfer_Request a = new Inventory_Transfer_Request();
		a.Inventory_Transfer_Requests_Pageopen(driver);
		a.Inventory_TransferRequest_Refresh(driver);
		a.Inventory_TransferRequest_addCancel(driver);
		a.Inventory_TransferRequest_add(driver);
		a.Inventory_Centralwarehouse_Pageopen(driver);
		a.Inventory_Centralwarehouse_openTransfer_Centrallevel(driver);
		a.Inventory_Centralwarehouse_Backbutton(driver);
		a.Inventory_Centralwarehouse_Click_Transferbutton_Errormessage(driver);
	}
	
	@Test(priority=265)
	public void Inventory_Transfer_Logs() throws Exception
	{
		Inventory_Transfer_Logs a = new Inventory_Transfer_Logs();
		a.Inventory_Transfer_Logs_Pageopen(driver);
		a.Inventory_Transfer_Logs_ALL(driver);
		a.Inventory_Transfer_Logs_ALL_Transferred(driver);
		a.Inventory_Transfer_Logs_InventoryItem(driver);
		a.Inventory_Transfer_Logs_InventoryItem_Transferred(driver);
		a.Inventory_Transfer_Logs_SubRecipe(driver);
		a.Inventory_Transfer_Logs_SubRecipe_Transferred(driver);
	}
	
	@Test(priority=266)
	public void Inventory_Sync_Inventory() throws Exception
	{
		Inventory_Sync_Inventory a = new Inventory_Sync_Inventory();
		a.Inventory_open_Sync_Inventory(driver);
		a.Inventory_update_Sync_Inventory(driver);
	}
	
	@Test(priority=267)
	public void Inventory_Purchases_Purchase_Template() throws Exception
	{
		Inventory_Purchase_Template a = new Inventory_Purchase_Template();
		a.Inventory_Purchase_Purchase_Template_openPAGE(driver);
		a.Inventory_Purchase_Purchase_Template_add_new(driver);
		a.Inventory_Purchase_Purchase_Template_edit(driver);
		a.Inventory_Purchase_Purchase_Template_delete(driver);
		a.Inventory_Purchase_Purchase_Template_verify_Purchase_Order_Button(driver);
		a.Inventory_Purchase_Purchase_Template_verifyActive_InActiveButton_Sub_Recipe(driver);
	}
	
	@Test(priority=268)
	public void Inventory_Purchases_Purchase_Orders() throws Exception
	{
		Inventory_Purchase_Orders a = new Inventory_Purchase_Orders();
		a.Inventory_Purchase_Purchase_Order_Openpage(driver);
		a.Inventory_Purchase_Purchase_Order_Purchase_Orders_new_Print_add(driver);
		a.Inventory_Purchase_Purchase_Order_add_new_Purchase_Orders_email(driver);
		a.Inventory_Purchase_Purchase_Order_cancelButton(driver);
	}
	
	@Test(priority=269)
	public void Inventory_Purchases_Receive_Inventory_Item() throws Exception
	{
		Inventory_Purchase_Receive_Inventory_Item a = new Inventory_Purchase_Receive_Inventory_Item();
		a.Inventory_Purchase_Receive_Inventory_Item_Openpage(driver);
		a.Inventory_Purchase_Receive_Inventory_Item_Update(driver);
	}
	
	@Test(priority=270)
	public void Inventory_Purchases_Prepare_Sub_Recipe() throws Exception
	{
		Inventory_Purchase_Prepare_SubRecipe a = new Inventory_Purchase_Prepare_SubRecipe();
		a.Inventory_Purchase_Prepare_Subrecipe_Openpage(driver);
		a.Inventory_Purchase_Prepare_Subrecipe_Update(driver);
	}
	
	@Test(priority=271)
	public void Inventory_Purchases_Received_Logs() throws Exception
	{
		Inventory_Purchase_Received_Logs a = new Inventory_Purchase_Received_Logs();
		a.Inventory_Purchase_Received_Logs_openpage(driver);
		a.Inventory_Purchase_Received_Logs_verify_All_All(driver);
		a.Inventory_Purchase_Received_Logs_verify_All_NotStarted(driver);
		a.Inventory_Purchase_Received_Logs_verify_All_Progress(driver);
		a.Inventory_Purchase_Received_Logs_verify_All_Finished(driver);
		a.Inventory_Purchase_Received_Logs_verify_All_NotFinished(driver);
		a.Inventory_Purchase_Received_Logs_verify_Inv_All(driver);
		a.Inventory_Purchase_Received_Logs_verify_Inv_NotStarted(driver);
		a.Inventory_Purchase_Received_Logs_verify_Inv_Progress(driver);
		a.Inventory_Purchase_Received_Logs_verify_Inv_Finished(driver);
		a.Inventory_Purchase_Received_Logs_verify_Inv_NotFinished(driver);
		a.Inventory_Purchase_Received_Logs_verify_SubRecipe_All(driver);
		a.Inventory_Purchase_Received_Logs_verify_SubRecipe_NotStarted(driver);
		a.Inventory_Purchase_Received_Logs_verify_SubRecipe_Progress(driver);
		a.Inventory_Purchase_Received_Logs_verify_SubRecipe_Finished(driver);
		a.Inventory_Purchase_Received_Logs_verify_SubRecipe_NotFinished(driver);
	}
	
	@Test(priority=272)
	public void Inventory_Reports_Purchased_Items() throws Exception
	{
		Inventory_Reports_Purchased_Item a = new Inventory_Reports_Purchased_Item();
		a.Inventory_Reports_Purchased_Items_openpage(driver);
		a.Inventory_Reports_Purchased_Items_verify_Purchased_Items_Report_For_All(driver);
		a.Inventory_Reports_Purchased_Items_verify_Purchased_Items_Report_For_Inventory_Item(driver);
		a.Inventory_Reports_Purchased_Items_verify_Purchased_Items_Report_For_Sub_Recipe(driver);
	}
	
	@Test(priority=273)
	public void Inventory_Reports_Consumption_Log() throws Exception
	{
		Inventory_Reports_Consumption_Log a = new Inventory_Reports_Consumption_Log();
		a.Inventory_Reports_ConsumptionLog_Reports_Openpage(driver);
		a.Inventory_Reports_ConsumptionLog_Openpage(driver);
		a.Inventory_Reports_ConsumptionLog_Inventory_All(driver);
		a.Inventory_Reports_ConsumptionLog_Inventory_System(driver);
		a.Inventory_Reports_ConsumptionLog_Inventory_Manual(driver);
		a.Inventory_Reports_ConsumptionLog_Subrecipe_All(driver);
		a.Inventory_Reports_ConsumptionLog_Subrecipe_System(driver);
		a.Inventory_Reports_ConsumptionLog_Subrecipe_Manual(driver);
	}
	
	@Test(priority=274)
	public void Inventory_Reports_Compare_Inventory() throws Exception
	{
		Inventory_Reports_Compare_Inventory a = new Inventory_Reports_Compare_Inventory();
		a.Inventory_Reports_Compare_Inventory_Openpage(driver);
		a.Inventory_Reports_Compare_Inventory_ALL(driver);
		a.Inventory_Reports_Compare_Inventory_Item(driver);
		a.Inventory_Reports_Compare_SubRecipe(driver);
	}
	
	@Test(priority=275)
	public void Inventory_Reports_Count_Sheet() throws Exception
	{
		Inventory_Reports_Count_Sheet a = new Inventory_Reports_Count_Sheet();
		a.Inventory_Reports_Countsheet_Openpage(driver);
		a.Inventory_Reports_Count_Sheet_Inventory(driver);
		a.Inventory_Reports_Count_Inventory_Item(driver);
		a.Inventory_Reports_Count_SubRecipe(driver);
	}
	
	@Test(priority=276)
	public void Inventory_Reports_COGS_Report() throws Exception
	{
		Inventory_Reports_COGS_Report a = new Inventory_Reports_COGS_Report();
		a.Inventory_Reports_COGS_Report_openpage(driver);
		a.Inventory_Reports_COGS_verify_All_report(driver);
		a.Inventory_Reports_COGS_Report_verify_MenuItem(driver);
		a.Inventory_Reports_COGS_Report_verify_Modifier(driver);
	}
	
	@Test(priority=277)
	public void Inventory_Reports_Adjust_Inventory_Report() throws Exception
	{
		Inventory_Reports_Adjust_Inventory_Reports a = new Inventory_Reports_Adjust_Inventory_Reports();
		a.Inventory_Reports_Adjust_Inventory_Report_Openpage(driver);
		a.Inventory_Reports_Adjust_Inventory_Report(driver);
		a.Inventory_Reports_Adjust_Inventory_SingleReport(driver); 
	}
	
	@Test(priority=301)
	public void Enterprise_My_Enterprise_Reports() throws Exception
	{
		Verify_My_Enterprise_Report a = new Verify_My_Enterprise_Report();
		a.My_Enterprise_Report_open_(driver);
		a.My_Enterprise_Report_Stores(driver);
		a.My_Enterprise_Report_Group(driver);
		a.My_Enterprise_Report_City(driver);
		a.My_Enterprise_Report_State(driver);
		a.My_Enterprise_Report_ZipCode(driver);
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
	 
	@Test(priority=306)
	public void Enterprise_Account_Users() throws Exception
	{
		Enterprise_Account_User a = new Enterprise_Account_User();
		a.Account_User_openpage(driver);
		a.Account_User_pagination(driver);
		a.Account_User_add(driver);
		a.Account_User_edit(driver);
		a.Account_User_reset_Password(driver);
		a.NewAccountUser_Cancel_Button(driver);
		a.EditAccountUser_Cancel_Button(driver);
		a.Reset_Password_Cancel_Button(driver);
		a.Account_Users_delete(driver);
	}
		
	@Test(priority=308)
	public void Enterprise_Loyalty_Report() throws Exception
	{
		Enterprise_Loyalty_Report a = new Enterprise_Loyalty_Report();
		a.Loyalty_Report_open_page(driver);
	}
	
	@Test(priority=308)
	public void Enterprise_Customers() throws Exception
	{
		Enterprise_Customers a = new Enterprise_Customers();
		a.Enterprise_Customers_openpage(driver);
		a.Enterprise_Customer_add_(driver);
		a.Enterprise_Customer_edit(driver);
		a.Enterprise_Customer_delete(driver);
		a.Enterprise_Customer_cancelButton(driver);
		a.Enterprise_Customer_view_page(driver);
	}
	
	@Test(priority=309)
	public void Enterprise_Group() throws Exception
	{
		AddEditDelete_Group a = new AddEditDelete_Group();
		a.Group_Openpage(driver);
		a.Group_pagination(driver);
		a.Group_add(driver);
		a.Group_edit(driver);
		a.Group_delete(driver);
		a.Group_cancelButton(driver);
	}
	
	@Test(priority=310)
	public void Enterprise_Advertisement() throws Exception
	{
		Enterprise_Advertisements a = new Enterprise_Advertisements();
		a.Advertisement_page(driver);
		a.Advertisement_Refreshpage(driver);
		a.Advertisement_Setting_Method_pagination(driver);
		a.Advertisement_Newcreationpage(driver);
		a.Advertisement_New_Always(driver);
		a.Advertisement_Close(driver);
		a.Advertisement_Edit_Daysofweek(driver);
		a.Advertisement_delete(driver);
		a.Advertisement_deActive(driver);
		a.Advertisement_AS_DaysOfMonth(driver);
		a.Advertisement_AS_DateRange(driver);
		a.Advertisement_AS_Specific_date(driver);
		a.Advertisement_AS_Startdatetime_enddatetime(driver);
	}
	
	@Test(priority=311)
	public void Enterprise_MailChimp() throws Exception
	{
		Enterprise_Mail_Chimp a = new Enterprise_Mail_Chimp();
		a.Mail_Chimp_page(driver);
		a.Mail_Chimp_Verifypage(driver);
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
	
	@Test(priority=317)
	public void Customers_Customers_Info() throws Exception
	{
		View_Customer_Info a = new View_Customer_Info();
		a.Customer_Info_Method_open_Page(driver);
		a.Customer_Info_Method_viewpage(driver);
	}
	
	@Test(priority=318)
	public void Customers_HouseAccount() throws Exception
	{
		AddEditDelete_HouseAccounts a = new AddEditDelete_HouseAccounts();
		a.HouseAccount_Method_OpenPage(driver);
		a.HouseAccount_Method_refresh(driver);
		a.HouseAccount_Method_CreateNewHouseAccounts(driver);
		a.HouseAccount_Method_addHouseAccountsfor_Daily(driver);
		a.HouseAccount_Method_EditHouseAccountsfor_Weekly(driver);
		a.HouseAccount_Method_EditHouseAccountsfor_Monthly(driver);
		a.HouseAccount_Method_deleteHouseAccounts(driver);
		a.HouseAccount_Method_DeActivestatus(driver);
	}
	
	@Test(priority=319)
	public void User_Management_Roles() throws Exception
	{
		Add_Edit_Delete_User_Management_Role a = new Add_Edit_Delete_User_Management_Role();
		a.Usermanagement_Role_Pageopen(driver);
		a.Usermanagement_Role_refreshPage(driver);
		a.Usermanagement_Role_addingpage(driver);
		a.Usermanagement_Role_RevertRoles(driver);
		a.Usermanagement_Role_CheckRevertedRoles(driver);
		a.Usermanagement_Role_editRoles(driver);
		a.Usermanagement_Role_Activestatus(driver);
		a.Usermanagement_Role_deleteRoles(driver);
		a.Usermanagement_Role_DeActivestatus(driver);
	}
	
	@Test(priority=320)
	public void User_Management_Users() throws Exception
	{
		AddEditDelete_Users a = new AddEditDelete_Users();
		a.Usermanagement_User_Pageopen(driver);
		a.Usermanagement_User_refreshPage(driver);
		a.Usermanagement_User_addUsers(driver);
		a.Usermanagement_User_edit(driver);
		a.Usermanagement_User_givePasswordOREnableSignIn(driver);
		a.Usermanagement_User_givePasswordORDisableSignIn(driver);
		a.Usermanagement_User_delete(driver);
		a.Usermanagement_User_DeActivestatus(driver);
		a.UsersActivestatus(driver);
	}
	
	@Test(priority=321)
	public void User_Management_Punched_In_Out() throws Exception
	{
		Punched_In_Out a = new Punched_In_Out();
		a.Usermanagement_ForcedPunchInOut_OpenPage(driver);
		a.Usermanagement_ForcedPunchInOut_addTimeEvent(driver);
		a.Usermanagement_ForcedPunchInOut_cancelButton_punchin_out(driver);
		a.Usermanagement_ForcedPunchInOut_edit_Employee_clock_in(driver);
		a.Usermanagement_ForcedPunchInOut_DeleteEmplyoee_Clock_In(driver);
	}
	
	@Test(priority=322)
	public void Update_Loyalty_Setting() throws Exception
	{
		Update_Loyalty_Settings a = new Update_Loyalty_Settings();
		a.Loyalty_page_Setting(driver);
		a.Loyalty_Settingpage(driver);
		a.Loyalty_SettingpageDisable(driver);
	}
	
	@Test(priority=323)
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
	
	@Test(priority=324)
	public void Settings_LayAway_Setting() throws Exception
	{
		Settings_Lay_Away_Settings a = new Settings_Lay_Away_Settings();
		a.open_Lay_Away_Settings(driver);
		a.edit_Lay_Away_Settings_Percentage_Percentage(driver);
		a.edit_Lay_Away_Settings_Percentage_Amount(driver);
		a.edit_Lay_Away_Settings_Amount_Percentage(driver);
		a.edit_Lay_Away_Settings_Amount_Amount(driver);
	}
	
	@Test(priority=325)
	public void ViewDashBoardReport() throws Exception
	{
		ViewDashBoardReports a = new ViewDashBoardReports();
		a.refreshTheDashBoard_Sales(driver);
		a.viewDashboardElementsForToday_Sales(driver);
		a.viewDashboardElementsForYesterday_Sales(driver);
		a.viewDashboardElementsForLastNday_Sales(driver);
		a.viewDashboardElementsForThisWeek_Sales(driver);
		a.viewDashboardElementsForLastWeek_Sales(driver);
		a.viewDashboardElementsForLast7Days_Sales(driver);
		a.viewDashboardElementsForThisMonth_Sales(driver);
		a.viewDashboardElementsForLastMonth_Sales(driver);
		a.viewDashboardElementsForLast30Days_Sales(driver);
		a.viewDashboardElementsForSpecificDate_Sales(driver);
		a.viewDashboardElementsForDateRange_Sales(driver);
		a.refreshTheDashBoard_Customer(driver);
		a.viewDashboardElementsForToday_Customer(driver);
		a.viewDashboardElementsForYesterday_Customer(driver);
		a.viewDashboardElementsForLastNday_Customer(driver);
		a.viewDashboardElementsForThisWeek_Customer(driver);
		a.viewDashboardElementsForLastWeek_Customer(driver);
		a.viewDashboardElementsForLast7Days_Customer(driver);
		a.viewDashboardElementsForThisMonth_Customer(driver);
		a.viewDashboardElementsForLastMonth_Customer(driver);
		a.viewDashboardElementsForLast30Days_Customer(driver); 
		a.viewDashboardElementsForSpecificDate_Customer(driver);
		a.viewDashboardElementsForDateRange_Customer(driver);
		a.refreshTheDashBoard_Employee(driver);
		a.viewDashboardElementsForToday_Employee(driver);
		a.viewDashboardElementsForYesterday_Employee(driver);
		a.viewDashboardElementsForLastNday_Employee(driver);
		a.viewDashboardElementsForThisWeek_Employee(driver);
		a.viewDashboardElementsForLastWeek_Employee(driver);
		a.viewDashboardElementsForLast7Days_Employee(driver);
		a.viewDashboardElementsForThisMonth_Employee(driver);
		a.viewDashboardElementsForLastMonth_Employee(driver);
		a.viewDashboardElementsForLast30Days_Employee(driver);
		a.viewDashboardElementsForSpecificDate_Employee(driver);
		a.viewDashboardElementsForDateRange_Employee(driver);
	}
	
	@Test(priority=326)
	public void Enterprise_Licenses() throws Exception
	{
		Enterprise_Licenses a = new Enterprise_Licenses();
		a.Licenses_open_page(driver);
		a.Reset_Licenses(driver);
		a.Verify_UnLinked_Licenses(driver);
	}
	
	@Test(priority=327)
	public void Enterprise_License_Settings() throws Exception
	{
		Enterprise_License_Settings a = new Enterprise_License_Settings();
		a.Licenses_open_page(driver);
		a.Update_License_Settings(driver);
	}
	
	@Test(priority=328)
	public void Enterprise_Developer_API_Key() throws Exception
	{
		Enterprise_Developer_API a = new Enterprise_Developer_API();
		a.Developer_API_open_page(driver);
		a.Update_License_Count_Developer_API(driver);
	}
	
	

}
