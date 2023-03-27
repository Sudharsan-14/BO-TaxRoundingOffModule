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

public class AllClassCalling_Product_Items 
{
	public WebDriver driver;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Product/Items--BO");
	
	float unknownValue = 00;
	
	@AfterClass
	public void flushTest() throws Exception
	{
		Thread.sleep(2000);
		rep.endTest(test);
		rep.flush();
		Thread.sleep(15000);
		SendMail_Product_Items.snedMailWithAttachment();   
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

	@Test(priority=4)
	public void Start_Product_and_Items()
	{
		test.log(LogStatus.INFO, "-------Product & Items Started----");
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
		AddEditDelete_ProductItem_MenuItem a = new AddEditDelete_ProductItem_MenuItem();
		a.Product_And_Item_method_openProductsItems(driver);
		a.Product_And_Item_method_refreshMenuItemPage(driver);
		a.Product_And_Item_method_pagination(driver);
		a.Product_And_Item_method_addMenuItem(driver);
		//a.Product_And_Item_method_editMenuItem(driver);
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

	@Test(priority=211)
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
	
	
	@Test(priority=212)
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
	
	@Test(priority=213)
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
	
	@Test(priority=214)
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
	
	@Test(priority=215)
	public void Product_And_Items_Open_Item_Discount() throws Exception
	{
		EditDelete_Discount_OpenItemAmount a = new EditDelete_Discount_OpenItemAmount();
		a.Open_Item_Discount_method_editDiscount_OpenItem(driver);
		a.Open_Item_Discount_method_deleteOrActiveDiscount_OpenItem(driver);
		a.Open_Item_Discount_method_cancelButton(driver);
	}
	
	@Test(priority=216)
	public void Product_And_Items_Open_Check_Discount() throws Exception
	{
		EditDelete_Discount_OpenCheckAmount a = new EditDelete_Discount_OpenCheckAmount();
		a.Open_Check_Discount_method_editDiscount_OpenCheck(driver);
		a.Open_Check_Discount_method_deleteOrActiveDiscount_OpenCheck(driver);
		a.Open_Check_Discount_method_cancelButton(driver);
	}
	
	@Test(priority=217)
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
	
	@Test(priority=218)
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
	
	@Test(priority=219,enabled=false)
	public void Product_And_Items_Sort_Menu_Configuration() throws Exception
	{
		Sort_Menu_Config a = new Sort_Menu_Config();
		a.Sort_Menu_Config_openSortMenuConfig(driver);
		a.Sort_Menu_Config_Category_Sorting(driver);
		a.Sort_Menu_Config_Subctegory_Sorting(driver);
		a.Sort_Menu_Config_MenuItem_Sorting(driver);
		a.Sort_Menu_Config_closeButton(driver);
	}
	
	@Test(priority=220)
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
	
	@Test(priority=221)
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
	
	
	@Test(priority=222)
	public void Product_And_Items_Cut_And_Modify() throws Exception
	{
		Edit_CutAndModify a = new Edit_CutAndModify();
		a.Cut_and_Modify_Method_openCutAndModify(driver);
		a.Cut_and_Modify_Method_editCutAndModify(driver);
		a.Cut_and_Modify_Method_cancelButton(driver);
	}
	
	@Test(priority=223)
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
	
	@Test(priority=224)
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
	
	@Test(priority=225)
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
	
	@Test(priority=226)
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
	
	@Test(priority=227)
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
	
	@Test(priority=228)
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
	
	@Test(priority=229)
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

	@Test(priority = 230)
	public void Product_And_Items_Item_Service_Charge() throws Exception
	{
		Add_Edit_Delete_Item_Service_Charge a=new Add_Edit_Delete_Item_Service_Charge();
		a.item_Service_Charge_Open(driver);
		a.item_Service_Charge_refresh(driver);
		a.item_Service_Charge_Add_item_Service_Charge(driver);
		a.item_Service_Charge_edit(driver);
		a.item_Service_Charge_delete(driver);
		a.item_Service_Charge_cancelButton(driver);
		a.item_Service_Charge_method_sameName(driver);
	}
	
	@Test(priority=490)
	public void End_Product_and_Items()
	{
		test.log(LogStatus.INFO, "-------Product and Items Ended----");
	}

}
