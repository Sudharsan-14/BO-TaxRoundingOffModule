package epicList_Chrome_Account_User;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AllClassCalling_Report {
	
	public WebDriver driver;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("BO - Reports (Account Lever User Login)");
	
	float unknownValue = 00;
	
	@Test(priority=100000)
	public void flushTest() throws Exception
	{
		Thread.sleep(2000);
		rep.endTest(test);
		rep.flush();
		SendMail.snedMailWithAttachment();
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


	@Test(priority=101)
	public void Report_Verify_Department_Sale_Report() throws Exception
	{
		Thread.sleep(3000);
		try
		{
			if(driver.findElement(By.xpath("//em[@class='siqico-min']")).isDisplayed())
			{
				Thread.sleep(1000);System.out.println("Minimize Chat Icon");
				driver.findElement(By.xpath("//em[@class='siqico-min']")).click();
			}
		}
		catch(Exception e)
		{
			
		}
		Verify_DepartmentSale_Report a = new Verify_DepartmentSale_Report();
		a.Department_Sale_method_OpenReport(driver);
		a.Department_Sale_method_Report_For_Specific_Date(driver);
		a.Department_Sale_method_Report_For_Specific_Date_With_Quantity_Sort(driver);
		a.Department_Sale_method_Report_For_Specific_Date_Without_Quantity_Sort_With_Dynamic_Report(driver);
		a.Department_Sale_method_Report_For_Specific_Date_With_Quantity_Sort_And_Dynamic_Report(driver);
	}
	
	@Test(priority=102)
	public void Report_Verify_Category_Sale_Report() throws Exception
	{
		Verify_CategorySale_Report a = new Verify_CategorySale_Report();
		a.Category_Sale_method_openpage_Report(driver);
		a.Category_Sale_method__Report_For_Specific_Date(driver);
		a.Category_Sale_method__Report_For_Specific_Date_With_Quantity_Sort(driver);
		a.Category_Sale_method__Report_For_Specific_Date_WithOut_Quantity_Sort_With_Dynamic_Report(driver);
		a.Category_Sale_method__Report_For_Specific_Date_With_Quantity_Sort_And_Dynamic_Report(driver);
	}
	
	@Test(priority=103)
	public void Report_Verify_Sub_Category_Sale_Report() throws Exception
	{
		Verify_SubCategorySale_Report a =  new Verify_SubCategorySale_Report();
		a.Sub_Category_Sale_method__OpenPageReport(driver);
		a.Sub_Category_Sale_method_For_Specific_Date(driver);
		a.Sub_Category_Sale_method_For_Specific_Date_With_Quantity_Sort(driver);
		a.Sub_Category_Sale_method_For_Specific_Date_WithOut_Quantity_Sort_With_Dynamic_Report(driver);
		a.Sub_Category_Sale_method_For_Specific_Date_With_Quantity_And_With_Dynamic_Report(driver);
	}
	
	@Test(priority=104)
	public void Report_Verify_Menu_Item_Sale_Report() throws Exception
	{
		Verify_MenuItemSale_Report a = new Verify_MenuItemSale_Report();
		a.Menu_Item_Sale_method_openpage_Report(driver);
		a.Menu_Item_Sale_method__Report_For_Specific_Date(driver);
		a.Menu_Item_Sale_method__Report_For_Specific_Date_With_Quantity_Sort(driver);
		a.Menu_Item_Sale_method__Report_For_Specific_Date_With_Dynamic_Report_Without_Quantity_Sort(driver);
		a.Menu_Item_Sale_method__Report_For_Specific_Date_With_Quantity_Sort_And_Dynamic_Report(driver);
	}
	
	@Test(priority=105)
	public void Report_Verify_Modifier_Sale_Report() throws Exception
	{
		Verify_ModifierSale_Report a = new Verify_ModifierSale_Report();
		a.Modifier_Sale_method_openpage_Report(driver);
		a.Modifier_Sale_method_Report_For_Specific_Date(driver);
		a.Modifier_Sale_method_Report_For_Specific_Date_With_Quantity_Sort(driver);
		a.Modifier_Sale_method_Report_For_Specific_Date_With_Dynamic_Report(driver);
		a.Modifier_Sale_method_Report_For_Specific_Date_With_Quantity_Sort_And_Dynamic_Report(driver);
	}
	
	@Test(priority=106)
	public void Report_Verify_Hourly_Sale_Report() throws Exception
	{
		Verify_HourlySale_Report a = new Verify_HourlySale_Report();
		a.Hourly_Sale_method_openpage_Sale_Report(driver);
		a.Hourly_Sale_method_Report_For_Specific_Date(driver);
		a.Hourly_Sale_method_Report_For_Specific_Date_With_Dynamic_Report(driver);
	}
	
	@Test(priority=107)
	public void Report_Verify_Daily_Sale_Report() throws Exception
	{
		Verify_DailySale_Report a = new Verify_DailySale_Report();
		a.Daily_Sale_method_openpage_Report(driver);
		a.Daily_Sale_method_Report_For_Specific_Date(driver);
		a.Daily_Sale_method_Report_For_Specific_Date_With_Dynamic_Report(driver);
	}
	
	@Test(priority=108)
	public void Report_Verify_Sale_Recap_Report() throws Exception
	{
		Verify_SaleRecap_Report a = new Verify_SaleRecap_Report();
		a.Sale_Recap_Sale_method_openpage_Report(driver);
		a.Sale_Recap_Sale_method_Verify_Report(driver);
	}
	
	@Test(priority=109)
	public void Report_Verify_Cashier_Out_Report() throws Exception
	{
		Verify_Cashier_Report a = new Verify_Cashier_Report();
		a.Cashier_Out_Sale_method_open__Report(driver);
		a.Cashier_Out_Sale_method_verify_Report(driver);
	}
	
	@Test(priority=110)
	public void Report_Verify_Weekly_Summary_Report() throws Exception
	{
		Verify_Weekly_Report a = new Verify_Weekly_Report();
		a.Weekly_SaleSummary_method_Report_Openpage(driver);
		a.Weekly_Summary_Sale_method_Report_This_Week(driver);
		a.Weekly_Summary_Sale_method_Report_Last_Week(driver);
		a.Weekly_Summary_Sale_method_Report_Last7days(driver);
	}
	
	@Test(priority=111)
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
	
	@Test(priority=112)
	public void Report_Verify_Transaction_Report() throws Exception
	{
		Verify_Transaction_Report a = new Verify_Transaction_Report();
		a.Transaction_Report_Method_openpage(driver);
		a.Transaction_Report_Method_For_Tender_Type(driver);
		a.Transaction_Report_Method_For_Tender_Name(driver);
	}
	
	@Test(priority=113)
	public void Report_Verify_Void_Transaction_Report() throws Exception
	{
		Verify_Void_Transaction_Report a = new Verify_Void_Transaction_Report();
		a.Void_Transaction_Report_Method_openpage(driver);
		a.Void_Transaction_Report_Method__For_Tender_Type(driver);																																												 
		a.Void_Transaction_Report_Method__For_Tender_Name(driver);
	}
	
	@Test(priority=114)
	public void Report_Verify_Gift_Card_Report() throws Exception
	{
		Verify_Gift_Card_Report a = new Verify_Gift_Card_Report();
		a.Gift_Card_Report_openpage(driver);
		a.Gift_Card_Report_Verify(driver);
		a.Gift_Card_Report_Verify_Card_Search(driver);
	}
	
	@Test(priority=115)
	public void Report_Verify_GiveX_Card_Report() throws Exception
	{
		Verify_GiveX_Card_Report a = new Verify_GiveX_Card_Report();
		a.GiveX_Card_Report_Method_openpage(driver);
		a.GiveX_Card_Report_Method_Verify(driver);
		a.GiveX_Card_Report_Method_Verify_Card_Search(driver);
	}
	
	@Test(priority=116)
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
	
	@Test(priority=117)
	public void Report_Verify_Void_Node_Report() throws Exception
	{
		Verify_Void_Node_Report a = new Verify_Void_Node_Report();
		a.Node_Report_Method_Openpage(driver);
		a.Node_Report_Method_Verify(driver);
	}
	
	@Test(priority=118)
	public void Report_Verify_Void_Employee_Report() throws Exception
	{
		Verify_Void_Employee_Report a = new Verify_Void_Employee_Report();
		a.Void_Node_Report_Method_Openpage(driver);
		a.Void_Node_Report_Method_Employees(driver);
	}
	
	@Test(priority=119)
	public void Report_Verify_Paid_In_And_Paid_Out_Report() throws Exception
	{
		Verify_PaidIn_Or_PaidOut_Report a = new Verify_PaidIn_Or_PaidOut_Report();
		a.PaidIn_Or_PaidOut_Report_Method_openpage(driver);
		a.PaidIn_Or_PaidOut_Report_Method_paidIn_VerifyALL(driver);
		a.PaidIn_Report_Method(driver);
		a.PaidOut_Report_Method(driver);
	}
	
	@Test(priority=120)
	public void Report_Verify_Employee_Attendance_Report() throws Exception
	{
		Verify_Employee_Attendance_Report a = new Verify_Employee_Attendance_Report();
		a.Employee_Attendance_Report_Method_OpenPage(driver);
		a.Employee_Attendance_Report_Method_For_InTime(driver);
		a.Employee_Attendance_Report_Method_For_InTime_With_Dynamic_Report(driver);
		a.Employee_Attendance_Report_Method_For_InHours(driver);
		a.Employee_Attendance_Report_Method_For_InHours_With_Dynamic_Report(driver);
	}
	
	@Test(priority=121)
	public void Report_Verify_Employee_Payroll_Report() throws Exception
	{
		Verify_Employee_Payroll_Reports a = new Verify_Employee_Payroll_Reports();
		a.Employee_Payroll_Report_Method_DailyReport_Openpage(driver);
		a.Employee_Payroll_Report_Method_verifyDailyReport(driver);
		a.Employee_Payroll_Report_Method_verifyDailyReport_With_Dynamic_Report(driver);
		a.Employee_Payroll_Report_Method_verifyWeeklyReport(driver);
		a.Employee_Payroll_Report_Method_verifyWeeklyReport_With_Dynamic_Report(driver);
	}
	
	@Test(priority=122)
	public void Report_Verify_Employee_Role_Based_Payroll_Report() throws Exception
	{
		Verify_Employee_Role_Based_Payroll_Reports a = new Verify_Employee_Role_Based_Payroll_Reports();
		a.Employee_Role_Based_Payroll_Report_Method_Page_Open(driver);
		a.Employee_Role_Based_Payroll_Report_Method_verifyDailyReport(driver);
		a.Employee_Role_Based_Payroll_Report_Method_verifyWeeklyReport(driver);
	}
	
	@Test(priority=123)
	public void Report_Verify_Employee_Labour_Report() throws Exception
	{
		Verify_Employee_Labour_Reports a = new Verify_Employee_Labour_Reports();
		a.Employee_Labour_Report_Method_DailyReport_Openpage(driver);
		a.Employee_Labour_Report_Method_verifyDailyReport(driver);
		a.Employee_Labour_Report_Method_verifyWeeklyReport(driver);
	}
	
	@Test(priority=124)
	public void Report_Verify_Employee_Labor_By_Job_Code_Report() throws Exception
	{
		Verify_Employee_Labour_By_Job_Code_Reports a = new Verify_Employee_Labour_By_Job_Code_Reports();
		a.Employee_Labour_By_Job_Code_Report_Method_PageOpen(driver);
		a.Employee_Labour_By_Job_Code_Report_Method_verifyDailyReport(driver);
		a.Employee_Labour_By_Job_Code_Report_Method_verifyWeeklyReport(driver);
	}
	
	@Test(priority=125)
	public void Report_Verify_Employee_Gratuity_Report() throws Exception
	{
		Verify_Employee_Gratuity_Reports a = new Verify_Employee_Gratuity_Reports();
		a.Employee_Gratuity_Report_Method_OpenPage(driver);
		a.Employee_Gratuity_Report_Method_Verify(driver);
	}
	
	@Test(priority=126)
	public void Report_Verify_Employee_Cash_Tip_Report() throws Exception
	{
		Verify_Employee_Cash_Tip_Reports a = new Verify_Employee_Cash_Tip_Reports();
		a.Employee_CashTip_Report_Method_OpenPage(driver);
		a.Employee_CashTip_Report_Method_verify(driver);
	}
	
	@Test(priority=127)
	public void Report_Verify_HA_Activity_Report() throws Exception
	{
		Verify_HouseAccount_Activity_Reports a = new Verify_HouseAccount_Activity_Reports();
		a.Employee_HAActivity_Repor_Method_Openpage(driver);
		a.Employee_HAActivity_Repor_Method_verify(driver);
		a.Employee_HAActivity_Repor_Method_verify_Card_Number(driver);
	}
	
	@Test(priority=128)
	public void Report_Verify_HA_Statement_Report() throws Exception
	{
		Verify_HouseAccount_Statement_Reports a = new Verify_HouseAccount_Statement_Reports();
		a.Employee_HAStatement_Report_Method_Openpage(driver);
		a.Employee_HAStatement_Report_Method_verifyALL(driver);
	}
	
	@Test(priority=129)
	public void Report_Verify_Driver_Report() throws Exception
	{
		Verify_Driver_Report a = new Verify_Driver_Report();
		a.Driver_Report_Method_Openpage(driver);
		a.Driver_Report_Method_verify(driver);
	}
	
	@Test(priority=130)
	public void Report_Verify_Batch_Report() throws Exception
	{
		Verify_Batch_Reports a = new Verify_Batch_Reports();
		a.Batch_Report_Method_OpenPage(driver);
		a.Batch_Report_Method_verify(driver);
	}
	
	@Test(priority=131)
	public void Report_Verify_Refund_Report() throws Exception
	{
		Verify_Refund_Report a = new Verify_Refund_Report();
		a.Refund_Report_Method_Openpage(driver);
		a.Refund_Report_Method_verify_By_Payment_Type(driver);
		a.Refund_Report_Method_verify_By_Payment_Name(driver);
	}
	
	@Test(priority=132)
	public void Report_Verify_Till_Report() throws Exception
	{
		Verify_Till_Reports a = new Verify_Till_Reports();
		a.Till_Report_Method_viewpage(driver);
		a.Till_Report_Method_Global(driver);
		a.Till_Report_Method_User(driver);
		a.Till_Report_Method_verifyAll(driver);
	}
	
	@Test(priority=133)
	public void Report_Verify_Till_Cash_Transaction_Report() throws Exception
	{
		Verify_Till_Cash_Transaction_Reports a = new Verify_Till_Cash_Transaction_Reports();
		a.CashTransaction_Report_Method_OpenPage(driver);
		a.CashTransaction_Report_Method_verifyAll(driver);
	}
	
	@Test(priority=134)
	public void Report_Verify_Comparision_Report() throws Exception
	{
		Verify_Comparision_Report a = new Verify_Comparision_Report();
		a.Comparision_Report_Method_viewOpage(driver);
		a.Comparision_Report_Method_verify_By_Payment_Type(driver);
	}
	
	@Test(priority=135)
	public void Report_Verify_Customer_Preference_Report() throws Exception
	{
		Verify_Customer_Performance_Report a = new Verify_Customer_Performance_Report();
		a.CustomerPerformance_Report_Method_viewpage(driver);
		a.CustomerPerformance_Report_Method_verify(driver);
		a.CustomerPerformance_Report_Method_WithoutCategorySubcategoryAdnServingSize(driver);
	}
	
	@Test(priority=136)
	public void Report_Verify_Daily_Tender_Report() throws Exception
	{
		Verify_DailyTender_Report a = new Verify_DailyTender_Report();
		a.DailyTender_Report_Method_viewPage(driver);
		a.DailyTender_Report_Method_verify(driver);
	}
	
	@Test(priority=137)
	public void Report_Verify_Tax_Report() throws Exception
	{
		Verify_Tax_Report a = new Verify_Tax_Report();
		a.Tax_Report_Method_viewPage(driver);
		a.Tax_Report_Method_verify(driver);
	}
	
	@Test(priority=138)
	public void Report_Verify_Revenue_Center_Report() throws Exception
	{
		Verify_Revenue_Center_Report a = new Verify_Revenue_Center_Report();
		a.Revenue_Center_Report_Open_Page_Report(driver);
		a.Revenue_Center_Report_By_All_Options_Selected(driver);
		a.Revenue_Center_Report_By_Required_Options_Selected(driver);
	}
	
	@Test(priority=139)
	public void Report_Verify_Account_Balance_Report() throws Exception
	{
		Verify_Account_Balance_Report a = new Verify_Account_Balance_Report();
		a.Account_Balance_Open_Account_Balance_Report(driver);
		a.Account_Balance_Report_By_All_Options_Selected(driver);
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
		//a.SaleRecap_Enterprise_Report_Method_For_Non_Close_Sales(driver);
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
		
	@Test(priority=308)
	public void Enterprise_Loyalty_Report() throws Exception
	{
		Enterprise_Loyalty_Report a = new Enterprise_Loyalty_Report();
		a.Loyalty_Report_open_page(driver);
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
}
