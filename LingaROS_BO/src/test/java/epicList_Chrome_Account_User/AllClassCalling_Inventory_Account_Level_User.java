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

public class AllClassCalling_Inventory_Account_Level_User { 
	
	public WebDriver driver;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("BO-Inventory (Account User Level)");
	
	float unknownValue = 00;
	
	@AfterClass
	public void flushTest() throws Exception
	{
		Thread.sleep(2000);
		rep.endTest(test);
		rep.flush();
		Thread.sleep(15000);
		//SendMail_Inventory.snedMailWithAttachment();  
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
	public void Start_Inventory()
	{
		test.log(LogStatus.INFO, "-------Inventory Started (Account Level User Login)----");
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
	
	@Test(priority = 253)
	public void Inventory_Ingredient_Sub_Categories() throws Exception
	{
		Inventory_Ingredient_Sub_Categories a=new Inventory_Ingredient_Sub_Categories();
		a.Inventory_Ingredient_Sub_Categories_openpage(driver);
		a.Inventory_Ingredient_Sub_Categories_refresh_page(driver);
		a.Inventory_Ingredient_Sub_Categories_add_Invetory_Category(driver);
		a.Inventory_Ingredient_Sub_Categories_edit_Invetory_Category(driver);
		a.Inventory_Ingredient_Sub_Categories_delete_Invetory_Category(driver);
		a.Inventory_Ingredient_Sub_Categories_closeButton(driver);
		a.Inventory_Ingredient_Sub_Categories_verifyActive_InActiveButton(driver);
	}
	@Test(priority=254)
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
	
	@Test(priority=255)
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
	
	@Test(priority=256)
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
	
	@Test(priority=257)
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
	
	@Test(priority=258)
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
	
	@Test(priority=259)
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
	
	@Test(priority=260)
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
	
	@Test(priority=261)
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
	
	@Test(priority=262)
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
	
	@Test(priority=263) 
	public void Inventory_Low_Stocks() throws Exception
	{
		Inventory_Low_Stacks a = new Inventory_Low_Stacks();
		a.Inventory_Low_stock_Openpage(driver);
		a.Inventory_Low_stock_prepare_SubRecipe(driver);
	}
	
	@Test(priority=264)
	public void Inventory_Store_Settings() throws Exception
	{
		Inventory_Store_Settings a = new Inventory_Store_Settings();
		a.Inventory_Store_setting_Pageopen(driver);
		a.Inventory_Store_setting_Process(driver);
	}
	
	@Test(priority=281)
	public void Inventory_Matrix_Report_Settings() throws Exception
	{
		Inventory_Matrix_Report_Setting a=new Inventory_Matrix_Report_Setting();
		a.Inventory_Matrix_Report_setting_Pageopen(driver);
		a.Inventory_Update_Matrix_Report_setting(driver);
	}
	
	@Test(priority=265)
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
	
	@Test(priority=266)
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
	
	@Test(priority=267)
	public void Inventory_Sync_Inventory() throws Exception
	{
		Inventory_Sync_Inventory a = new Inventory_Sync_Inventory();
		a.Inventory_open_Sync_Inventory(driver);
		a.Inventory_update_Sync_Inventory(driver);
	}
	
	@Test(priority=268)
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
	 
	@Test(priority=450)
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
		a.Verify_and_Enable_Compare_Inventory_Toggles(driver);
		a.Inventory_Reports_Compare_Inventory_ALL_Today(driver);
		a.Inventory_Reports_Compare_Inventory_ALL_Yesterday(driver);
		a.Inventory_Reports_Compare_Inventory_ALL_Last_N_days(driver);
		a.Inventory_Reports_Compare_Inventory_ALL_This_Week(driver);
		a.Inventory_Reports_Compare_Inventory_ALL_Last_Week(driver);
		a.Inventory_Reports_Compare_Inventory_ALL_Last_7_days(driver);
		a.Inventory_Reports_Compare_Inventory_ALL_This_Month(driver);
		a.Inventory_Reports_Compare_Inventory_ALL_Last_Month(driver);
		a.Inventory_Reports_Compare_Inventory_ALL_Last_30_days(driver);
		a.Inventory_Reports_Compare_Inventory_ALL_Specific_Date(driver);
		a.Inventory_Reports_Compare_Inventory_ALL_Date_Range(driver);
		a.Inventory_Reports_Compare_Inventory_Inventory_Item(driver);
		a.Inventory_Reports_Compare_Inventory_SubRecipe(driver);
		a.Inventory_Reports_Compare_Inventory_Menu_Item(driver);
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
	
	
	@Test(priority=278)
	public void Inventory_Reports_Inventory_Item_History() throws Exception
	{
		Inventory_Reports_Item_History a=new Inventory_Reports_Item_History();
		a.Inventory_Item_History_Openpage(driver);
		a.Inventory_Reports_Verify_Inventory_Item_History(driver);
	}
	
	@Test(priority=279)
	public void Inventory_Reports_Matrix_Reports() throws Exception
	{
		Inventory_Reports_Matrix_Report a=new Inventory_Reports_Matrix_Report();
		a.Inventory_Reports_Matrix_Openpage(driver);
		a.Inventory_Reports_Verify_Matrix_Report(driver);
		a.Inventory_Reports_Verify_Matrix_Report_for_Category(driver);
		a.Inventory_Reports_Verify_Matrix_Report_for_Sub_Category(driver);
	}
	
	@Test(priority=280)
	public void Inventory_Wastage_Report() throws Exception
	{
		Inventory_Reports_Wastage_Report a=new Inventory_Reports_Wastage_Report();
		a.Inventory_Reports_Wastage_Openpage(driver);
		a.Inventory_Reports_Verify_Wastage_Report(driver);
	}
	
	@Test(priority=282)
	public void Inventory_Purchase_Invoice() throws Exception
	{
		Inventory_Purchase_Invoice a=new Inventory_Purchase_Invoice();
		a.Inventory_Purchase_Invoice_Open_Page(driver);
		a.Edit_and_Update_Inventory_Purchase_Invoice(driver);
		
	}
	
	@Test(priority=283)
	public void Inventory_Expired_Purchase_Item() throws Exception
	{
		Inventory_Purchases_Expired_Purchase_Item a=new Inventory_Purchases_Expired_Purchase_Item();
		a.Inventory_Expired_Purchase_Item_Open_Page(driver);
		a.Inventory_Purchase_Verify_Expired_Purchase_Item(driver);
	}
	@Test(priority=490)
	public void End_Inventory()
	{
		test.log(LogStatus.INFO, "-------Inventory Started----");
	}
}
