package epicList_Chrome;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.apache.commons.codec.binary.Base64;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;
public class Add_Edit_Delete_Item_Service_Charge {
	
		
		public WebDriver driver;
		
		ExtentReports rep = ExtentManager.getInstance();
		ExtentTest test = rep.startTest("AddEditDelete_Item_Service_Charge");

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
			{
				Browser a = new Browser();
				a.Logout(driver, test);
				
			}
		
			@Test(priority = 100)
			public void calling() throws Exception
			{
				Thread.sleep(10000);
				try
				{
						Thread.sleep(1000);System.out.println("Minimize Chat Icon");
						driver.findElement(By.id("zsiq_minimize")).click();			
				}
				catch(Exception e)
				{
					System.out.println("Minimize Chat Icon Missing");
				}
				//ViewDashBoardReports a = new ViewDashBoardReports();
				Thread.sleep(1000);
				item_Service_Charge_Open(driver);
				item_Service_Charge_refresh(driver);
				item_Service_Charge_Add_item_Service_Charge(driver);
				item_Service_Charge_edit(driver);
				item_Service_Charge_delete(driver);
				item_Service_Charge_cancelButton(driver);
				item_Service_Charge_method_sameName(driver);
				Thread.sleep(1500);
			}
			
			@Test(enabled=false,priority=41)
			public void item_Service_Charge_Open(WebDriver driver) throws Exception
			{
				
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					try {
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"itemServiceCharge");
				Thread.sleep(10000);
				
				
			//	test.log(LogStatus.FAIL, test.addBase64ScreenShot(base64)));
				//Check Item Service Charge page opened or not
				if(driver.findElement(By.xpath(excel.getData(5, 577, 1))).getText().equalsIgnoreCase("Item Service Charge"))
				{
					test.log(LogStatus.PASS, "Item Service Charge Page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Item Service Charge page loaded Failed");
					
					
					//Thread.sleep(10000);
					String scnShot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s1="data:image/png;base64,"+scnShot1;
					test.log(LogStatus.INFO,test.addScreenCapture(s1));
/*					Utility us1=new Utility();
					us1.capture(driver);

					test.log(LogStatus.FAIL, test.addScreenCapture(us1.capture(driver)));
					test.log(LogStatus.FAIL, test.addBase64ScreenShot(base64));*/
					
					
				}
				
				Thread.sleep(5000);
				} catch (Exception e) {
				
					e.printStackTrace();
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
			}
			
			@Test(enabled=false,priority=42)
			public void item_Service_Charge_refresh(WebDriver driver) throws Exception
			{
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Click the refresh button
				driver.findElement(By.xpath(excel.getData(5, 148, 1))).click();
				Thread.sleep(5000);
				//Check Item Service Charge page opened or not
				if(driver.findElement(By.xpath(excel.getData(5, 577, 1))).getText().equalsIgnoreCase("Item Service Charge"))
				{
					test.log(LogStatus.PASS, "Item Service Charge Page loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Item Service Charge page loaded Failed");
				}
				
				
				Thread.sleep(5000);
		
			}
			
			@Test(enabled=false,priority=44)
			public void item_Service_Charge_Add_item_Service_Charge(WebDriver driver) throws Exception
			{
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				for (int i = 0; i < 10; i++) {
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
					driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				}
				Thread.sleep(3000);
				
				Thread.sleep(5000);
				//Click on the Item service Charge option
				driver.findElement(By.xpath(excel.getData(5, 578, 1))).click();
				Thread.sleep(3000);
				//Check whether the item service charge form loaded or not
				if(driver.findElement(By.xpath(excel.getData(5, 579, 1))).getText().equalsIgnoreCase("New Item Service Charge"))
				{
					test.log(LogStatus.PASS, "New Item Service Charge form loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "New Item Service Charge form loaded Failed");
				}
		
				
				//Enter the Name
				driver.findElement(By.xpath(excel.getData(5, 580, 1))).sendKeys(Utility.getProperty("Item_Service_Charge_Name"));
				Thread.sleep(2000);
				
				//enter data the Percentage field
				driver.findElement(By.xpath(excel.getData(5, 581, 1))).sendKeys("6000");
				
				//enter data the Taxes field
				driver.findElement(By.xpath(excel.getData(5, 582, 1))).click();
				//enter data into taxes field
				driver.findElement(By.xpath(excel.getData(5, 583, 1))).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.xpath(excel.getData(5, 583, 1))).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.xpath(excel.getData(5, 583, 1))).sendKeys(Keys.ENTER);
				Thread.sleep(5000);
				//Click the Save button
				driver.findElement(By.xpath(excel.getData(5, 246, 1))).click();
				Thread.sleep(3500);
				//Check whether theitem service charge saved or not
				if(driver.findElement(By.cssSelector(excel.getData(5, 6, 4))).getText().equalsIgnoreCase("Item Service Charge Saved Successfully"))
				{
					test.log(LogStatus.PASS, "Item Service Charge Saved Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Item Service Charge failed");
				}
		
				Thread.sleep(5000);
			}
			
			@Test(enabled=false,priority=45)
			public void item_Service_Charge_edit(WebDriver driver) throws Exception
			{
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(6000);
				//Clear the search field
				driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
				//Enter the required keyword
				driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("Item_Service_Charge_Name")+"1");
				Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
		
				Thread.sleep(2000);
				//Click the Edit icon
				driver.findElement(By.cssSelector(excel.getData(5, 63, 4))).click();
				
				Thread.sleep(4000);
				//Clear the Name field
				driver.findElement(By.xpath(excel.getData(5, 580, 1))).clear();			
				//Enter the Name
				driver.findElement(By.xpath(excel.getData(5, 580, 1))).sendKeys(Utility.getProperty("Item_Service_Charge_Name")+"1");
		
		
				Thread.sleep(3000);
				//Click the update
				driver.findElement(By.xpath(excel.getData(5, 246, 1))).click();
				WebElement up=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
				WebDriverWait wait=new WebDriverWait(driver,60);
				//Check weather the new Item Service Charge is updated or not
				if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Item Service Charge updated successfully"))
				{
					test.log(LogStatus.PASS, "Item Service Charge updated Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Item Service Charge updated Failed");
				}
		
				Thread.sleep(5000);
		
			}
			
			@Test(enabled=false,priority=46)
			public void item_Service_Charge_delete(WebDriver driver) throws Exception
			{
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Clear the search field
				driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
				//Enter the required keyword
				driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("Item_Service_Charge_Name")+"11");
				Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
		
				Thread.sleep(2000);
				//Click the Delete button
				driver.findElement(By.cssSelector(excel.getData(5, 51, 4))).click();
				//Click the Yes button in the popup
				Thread.sleep(3000);
				driver.findElement(By.cssSelector(excel.getData(5, 69, 4))).click();
				Thread.sleep(5000);
				//Check the item service charge deleted or not
				if(driver.findElement(By.cssSelector(excel.getData(5, 6, 4))).getText().equalsIgnoreCase("Item Service Charge inactivated successfully"))
				{
					test.log(LogStatus.PASS, "Item Service Charge deleted Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Item Service Charge deleted Failed");
				}
		
				Thread.sleep(7000);
				
				//Click the Active Button
				driver.findElement(By.xpath(excel.getData(5, 247, 1))).click();
				Thread.sleep(7000);		
				//Click the success button
				driver.findElement(By.cssSelector(excel.getData(5, 69, 4))).click();
				Thread.sleep(1000);
				
				//Click the Yes button in the popup
				Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(5, 69, 4))).click();
				Thread.sleep(3500);
				
				//Check the item service charge activated or not
				if(driver.findElement(By.cssSelector(excel.getData(5, 6, 4))).getText().equalsIgnoreCase("Item Service Charge activated successfully"))
				{
					test.log(LogStatus.PASS, "Item Service Charge activated Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Item Service Charge activated Failed");
				}
				Thread.sleep(3000);	
				
			}
		
			@Test(enabled=false,priority=47)
			public void item_Service_Charge_cancelButton(WebDriver driver) throws Exception
			{
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		     Thread.sleep(5000);
				
				//Click the Active Button
				driver.findElement(By.xpath(excel.getData(5, 247, 1))).click();
				
				Thread.sleep(5000);
				//Click on the Add New item service charge option
				driver.findElement(By.xpath(excel.getData(5, 578, 1))).click();
				Thread.sleep(3000);
				//Check whether the item service charge form loaded or not
				if(driver.findElement(By.xpath(excel.getData(5, 579, 1))).getText().equalsIgnoreCase("New Item Service Charge"))
				{
					test.log(LogStatus.PASS, "New Item Service Charge form loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "New Item Service Charge form loaded Failed");
				}
				
				//Clear the Name field
				driver.findElement(By.xpath(excel.getData(5, 580, 1))).clear();
				//Enter the Name
				driver.findElement(By.xpath(excel.getData(5, 580, 1))).sendKeys(Utility.getProperty("Item_Service_Charge_Name"));
				Thread.sleep(2000);
				
				//Click the close button
				driver.findElement(By.xpath(excel.getData(5, 250, 1))).click();
				Thread.sleep(2000);
				//Check whether the item service charge saved or not
				if(driver.findElement(By.xpath(excel.getData(5, 577, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "item service charge canceled Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "item service charge canceled failed");
				}
		
				Thread.sleep(5000);
			}
				
			@Test(enabled=false,priority=48)
			public void item_Service_Charge_method_sameName(WebDriver driver) throws Exception
			{
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
				Thread.sleep(5000);
				//Click on the Add Gift Card option
				driver.findElement(By.xpath(excel.getData(5, 578, 1))).click();
				Thread.sleep(3000);
				
				//Clear the Name field
			//	driver.findElement(By.xpath(excel.getData(5, 580, 1))).clear();
				//Enter the Name
				driver.findElement(By.xpath(excel.getData(5, 580, 1))).sendKeys(Utility.getProperty("Item_Service_Charge_Name")+"1");
				Thread.sleep(2000);
				
				//enter data the Percentage field
				driver.findElement(By.xpath(excel.getData(5, 581, 1))).sendKeys("6000");
				
				//enter data the Taxes field
				driver.findElement(By.xpath(excel.getData(5, 582, 1))).click();
				//enter data into taxes field
				driver.findElement(By.xpath(excel.getData(5, 583, 1))).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.xpath(excel.getData(5, 583, 1))).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.xpath(excel.getData(5, 583, 1))).sendKeys(Keys.ENTER);
				//driver.findElement(By.xpath(excel.getData(5, 584, 1))).click();
				Thread.sleep(5000);
				
				Thread.sleep(3000);
				//Click the Save button
				driver.findElement(By.xpath(excel.getData(5, 246, 1))).click();
				Thread.sleep(1500);
				
				//Check whether the item service charge updated successfully or not
				if(driver.findElement(By.cssSelector(excel.getData(5, 6, 4))).getText().equalsIgnoreCase("Validation Error(s)"))
				{
					test.log(LogStatus.PASS, "item service charge is updated failed and show the error message");
				}
				else
				{
					test.log(LogStatus.FAIL, "item service charge is updated successfully");
				}
		
				Thread.sleep(5000);
			//Click the close button
				driver.findElement(By.xpath(excel.getData(5, 250, 1))).click();
				Thread.sleep(5000);
			}
			

			
	}


