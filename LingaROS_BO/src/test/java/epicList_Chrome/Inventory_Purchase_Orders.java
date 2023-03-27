package epicList_Chrome;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;


public class Inventory_Purchase_Orders {
	
			public WebDriver driver;
			
			ExtentReports rep = ExtentManager.getInstance();
			ExtentTest test = rep.startTest("Inventory_Purchase_Orders");
		
		@AfterMethod
		public void tearDown(ITestResult result)
		{
			if(ITestResult.FAILURE == result.getStatus())
			{
				Utility.captureScreenshot(driver, result.getName());
			}
		}
			
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
		a.Logout(driver, test);}
	
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
			Inventory_Purchase_Purchase_Order_Openpage(driver);
			Inventory_Purchase_Purchase_Order_Purchase_Orders_new_Print_add(driver);
			Inventory_Purchase_Purchase_Order_add_new_Purchase_Orders_email(driver);
			Inventory_Purchase_Purchase_Order_cancelButton(driver);
			Thread.sleep(1500);
		}
	
			@Test(enabled=false,priority=9)
			public void Inventory_Purchase_Purchase_Order_Openpage(WebDriver driver) throws Exception
			{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				/*//Click the Inventory option
				driver.findElement(By.xpath("//span[.='Inventory']")).click();
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[contains(.,'Purchases')]"));
				//Scroll the page till the Inventory Menu Items option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Purchases Option		
				driver.findElement(By.xpath("//span[contains(.,'Purchases')]")).click();
			*/	Thread.sleep(5000);
				
				//Get the URl
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"purchaseOrders");
		
				Thread.sleep(5000);
		        //Click the Purchases Order Option		
				driver.findElement(By.xpath(excel.getData(3, 1217, 1))).click();
				Thread.sleep(5000);
				try
				{
				//Check Storage Locations page opened or not
				if(driver.findElement(By.xpath(excel.getData(3, 1218, 1))).getText().equalsIgnoreCase("Purchase Orders"))
				{
					test.log(LogStatus.PASS, "Purchase orders page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase orders page loaded Failed");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				}
				catch(Exception e)
				{
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.FAIL,test.addScreenCapture(s));
				}
				
				Thread.sleep(5000);wb.close();
				
			}
			
			@Test(enabled=false,priority=10)
			public void Inventory_Purchase_Purchase_Order_Purchase_Orders_new_Print_add(WebDriver driver) throws Exception
			{
				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			    
				Thread.sleep(5000);
				//Click the add purchase order option
				driver.findElement(By.xpath(excel.getData(3, 1219, 1))).click();
				
				Thread.sleep(6000);
				//Click the Vendor Option
				driver.findElement(By.xpath(excel.getData(3, 1664, 1))).click();
				Thread.sleep(2000);
				//Enter the Vendor Option 
				driver.findElement(By.xpath(excel.getData(3, 1665, 1))).sendKeys("Sysco Food");
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(3, 1665, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(1500);
				//Clear the Expecting Date
				driver.findElement(By.name(excel.getData(3, 1222, 3))).clear(); 
				//Enter the Required Date
				driver.findElement(By.name(excel.getData(3, 1222, 3))).sendKeys("21-Dec-2030");
				
				Thread.sleep(5000); 
				//Select the required placed order via option
				Select placeOrederVia = new Select(driver.findElement(By.xpath(excel.getData(3, 1666, 1))));
				placeOrederVia.selectByVisibleText("Email");
				 
		/*		Thread.sleep(1500);
				//Clear the CC Option
				driver.findElement(By.name("storePoEmails")).clear();
				//Enter the required mail id to send the mail
				driver.findElement(By.name("storePoEmails")).sendKeys("testpurchaseorder@mail.com");
		*/		
				Thread.sleep(4000);
				//Add Inventory Item
				driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();
				
				Thread.sleep(5000);
				//Click the Inventory Item Option
				driver.findElement(By.xpath(excel.getData(3, 1224, 1))).click();
				Thread.sleep(1500);
				//Enter the Required Inventory Option
				driver.findElement(By.xpath(excel.getData(3, 1225, 1))).click();
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(3, 1225, 1))).sendKeys(Keys.ENTER);
				try {
				Thread.sleep(2000);
				//Brand
				driver.findElement(By.xpath("//select[@name='vendorItem']/../div/a")).click();
				//Select Brand
				driver.findElement(By.xpath("//select[@name='vendorItem']/../div/div/div/input")).click();
				//Enter
				driver.findElement(By.xpath("//select[@name='vendorItem']/../div/div/div/input")).sendKeys(Keys.ENTER);
				} catch(Exception h) {}
				Thread.sleep(1000);
				//Clear the Quantity
				driver.findElement(By.xpath(excel.getData(3, 1226, 1))).clear();
				//Enter the Required Quantity
				driver.findElement(By.xpath(excel.getData(3, 1226, 1))).sendKeys("2");
				
				Thread.sleep(2000);
				//Add Inventory Item
				driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();
				
				//Click the Close button
				driver.findElement(By.xpath(excel.getData(3, 1227, 1))).click();
		
				
				Thread.sleep(5000);
				//Click the Place Order Option
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
		try
		{
				WebElement up=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
				WebDriverWait wait=new WebDriverWait(driver,60);
				//Check weather the new Purchase Order is updated or not
				if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Purchase order saved successfully!."))
				{
					test.log(LogStatus.PASS, "Purchase order saved Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order saved Failed");
				}
				Thread.sleep(5000);
		}
		catch(Exception e) {}
			//	driver.switchTo().defaultContent();
		
		String currentscreen = driver.getWindowHandle(); //for identifying the current window
		Set<String> tabSet = driver.getWindowHandles(); //to get total window
		for (String tab : tabSet) {
		    if (!tab.equals(currentscreen)) {
		       driver.switchTo().window(tab); 
		       //and do the actions you need here  
		       String url1 = driver.getCurrentUrl();
		       System.out.println(url1);
		    //   driver.get(url1);
		       Thread.sleep(5000);
		       driver.switchTo().window(currentscreen);
		    }
		}
		     //  driver.get(url1);
		/*       Robot r=new Robot();
		       r.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		       r.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		       

		       r.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		       r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		       
		       r.keyPress(KeyEvent.VK_ENTER);
		       */
		    
		
		       Thread.sleep(4000);
		//       verifyPDFContent(url1, "Sysco Food");
		       
		     //  driver.switchTo().window(currentscreen);
				
		/*		//Clear the Search Field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				//Enter the Required Keyword
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys("Sysco Food");
				*/
				Thread.sleep(5000);
				//Click the Cancel Order Option
				driver.findElement(By.xpath(excel.getData(3, 1667, 1))).click();
				
				Thread.sleep(2000);
				//Click the Yes Button
				driver.findElement(By.xpath(excel.getData(3, 545, 1))).click();
				
				Thread.sleep(2500);
		/*		//Check whether the Receive information updated or not
				if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Purchase order updated."))
				{
					test.log(LogStatus.PASS, "Purchase order updated Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order updated Failed");
				} */
			//	Thread.sleep(5000);
				
	/*			//Clear the Search Field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				//Enter the Required Keyword
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys("Sysco Food");
			*/	
				Thread.sleep(1500);
				//Check whether the order is canceled or not
				if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Purchase order Cancelled."))
				{
					test.log(LogStatus.PASS, "Purchase order CANCELED Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order CANCELED Failed");
				}
				Thread.sleep(5000);wb.close();
				//driver.close();
			}
			
			@Test(enabled=false,priority=11)
			public void Inventory_Purchase_Purchase_Order_add_new_Purchase_Orders_email(WebDriver driver) throws Exception
			{   
                File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			    
				
				Thread.sleep(10000);
				//Click the add purchase order option
				driver.findElement(By.xpath(excel.getData(3, 1219, 1))).click();
				
				Thread.sleep(7000);
				//Click the Vendor Option
				driver.findElement(By.xpath(excel.getData(3, 1664, 1))).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath(excel.getData(3, 1665, 1))).sendKeys("Sysco Food");
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(3, 1665, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(1500);
				//Clear the Expecting Date
				driver.findElement(By.name(excel.getData(3, 1222, 3))).clear();
				//Enter the Required Date
				driver.findElement(By.name(excel.getData(3, 1222, 3))).sendKeys("21-Dec-2030");
				
				Thread.sleep(5000);
				//Select the required placed order via option
				Select placeOrederVia = new Select(driver.findElement(By.xpath(excel.getData(3, 1666, 1))));
				placeOrederVia.selectByVisibleText("Email");
				
				Thread.sleep(1500);
		/*		//Clear the CC Option
				driver.findElement(By.name(excel.getData(3, 1230, 3))).clear();
				//Enter the required mail id to send the mail
				driver.findElement(By.name(excel.getData(3, 1230, 3))).sendKeys("testpurchaseorder@mail.com");
			*/	
				Thread.sleep(2000);
				//Add Inventory Item
				driver.findElement(By.xpath(excel.getData(2, 53, 1))).click();
				
				Thread.sleep(1500);
				//Click the Inventory Item Option
				driver.findElement(By.xpath(excel.getData(3, 1224, 1))).click();
				//Enter the Required Inventory Option
			//	driver.findElement(By.xpath(excel.getData(3, 1225, 1))).sendKeys("Sugar");
				driver.findElement(By.xpath(excel.getData(3, 1225, 1))).click();
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(3, 1225, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(1000);
				//Clear the Quantity
				driver.findElement(By.xpath(excel.getData(3, 1226, 1))).clear();
				//Enter the Required Quantity
				driver.findElement(By.xpath(excel.getData(3, 1226, 1))).sendKeys("2");
				
				Thread.sleep(2000);
				//Add Inventory Item
				driver.findElement(By.xpath(excel.getData(3, 1223, 3))).click();
				
				//Click the Close button
				driver.findElement(By.xpath(excel.getData(3, 1227, 1))).click();
				
				Thread.sleep(2000);
				//Click the Place Order Option
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				Thread.sleep(2500);
				//Check whether the order is placed or not
				if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Purchase order saved successfully!."))
				{
					test.log(LogStatus.PASS, "Purchase order saved Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order saved Failed");
				}
				Thread.sleep(5000);

				String currentscreen = driver.getWindowHandle(); //for identifying the current window
				Set<String> tabSet = driver.getWindowHandles(); //to get total window
				for (String tab : tabSet) {
				    if (!tab.equals(currentscreen)) {
				       driver.switchTo().window(tab); 
				       //and do the actions you need here  
				       String url1 = driver.getCurrentUrl();
				       System.out.println(url1);
				      // driver.get(url1);
				       Thread.sleep(5000);
				       driver.switchTo().window(currentscreen);
				    }
				}
				
				Thread.sleep(3000);
		/*		//Clear the Search Field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				//Enter the Required Keyword
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys("Sysco Food");
				*/
				Thread.sleep(1500);
				//Click the Receiving Order Option
				driver.findElement(By.cssSelector(excel.getData(3, 1232, 3))).click();
				
				Thread.sleep(2000);
				//Clear the value of Price/Unit option
				driver.findElement(By.xpath(excel.getData(3, 1233, 1))).clear();
				//Enter the required amount
				driver.findElement(By.xpath(excel.getData(3, 1233, 1))).sendKeys("1500");
				
				//CLear the Received Quantity Option 
				driver.findElement(By.xpath(excel.getData(3, 1234, 1))).clear();
				//Enter the Required Quantity
				driver.findElement(By.xpath(excel.getData(3, 1234, 1))).sendKeys("3");
				
				Thread.sleep(1000);
				//Click the Cancel Button
				driver.findElement(By.xpath(excel.getData(3, 130, 1))).click();
				
		/*		Thread.sleep(4000);
				//Clear the Search Field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				//Enter the Required Keyword
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys("Sysco Food");
		*/		
				Thread.sleep(1500);
				//Click the Receiving Order Option
				driver.findElement(By.cssSelector(excel.getData(3, 1232, 3))).click();
				
				Thread.sleep(2000);
				//Clear the value of Price/Unit option
				driver.findElement(By.xpath(excel.getData(3, 1233, 1))).clear();
				//Enter the required amount
				driver.findElement(By.xpath(excel.getData(3, 1233, 1))).sendKeys("1500");
				
				//Clear the Received Quantity Option
				driver.findElement(By.xpath(excel.getData(3, 1234, 1))).clear();
				//Enter the Required Quantity
				driver.findElement(By.xpath(excel.getData(3, 1234, 1))).sendKeys("3");
		
				Thread.sleep(2000);
				//Click the Submit button
				driver.findElement(By.xpath(excel.getData(3, 545, 1))).click();
				
				Thread.sleep(2500);
				//Check whether the Receive information updated or not
				if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Receive information updated."))
				{
					test.log(LogStatus.PASS, "Receive information updated Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Receive information updated Failed");
				}
				Thread.sleep(5000);
				//Clear the Search Field
				driver.findElement(By.xpath(excel.getData(3,40, 1))).clear();
				//Enter the Required Keyword
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys("Sysco Food");
				
				Thread.sleep(1500);
				//Check whether the order is canceled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1229, 1))).getText().equalsIgnoreCase("RECEIVED"))
				{
					test.log(LogStatus.PASS, "Purchase order RECEIVED Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order RECEIVED Failed");
				}
				Thread.sleep(5000);
				
				Thread.sleep(5000);
				//Clear the Search Field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();wb.close();
			}
			
			@Test(enabled=false,priority=12)
			public void Inventory_Purchase_Purchase_Order_cancelButton(WebDriver driver) throws Exception
			{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
				//Click the add purchase order option
				driver.findElement(By.xpath(excel.getData(3, 1219, 1))).click();
				
				Thread.sleep(2000);
				//Click the Vendor Option
				driver.findElement(By.xpath(excel.getData(3, 1664, 1))).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath(excel.getData(3, 1665, 1))).sendKeys("Sysco Food");
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(3, 1665, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(1500);
				//Clear the Expecting Date
				driver.findElement(By.name(excel.getData(3, 122, 1))).clear();
				//Enter the Required Date
				driver.findElement(By.name(excel.getData(3, 122, 1))).sendKeys("21-Dec-2020");
				
				//Select the required placed order via option
				Select placeOrederVia = new Select(driver.findElement(By.xpath(excel.getData(3, 1666, 1))));
				placeOrederVia.selectByVisibleText("Email");
				
				Thread.sleep(2000);
				//Click the Cancel button
				driver.findElement(By.xpath(excel.getData(3, 130, 1))).click();
				
				Thread.sleep(3000);
				//Check whether the add purchase order option is present or not
				if(driver.findElement(By.xpath(excel.getData(3, 1219, 1))).isDisplayed())
				{
					test.log(LogStatus.PASS, "Cancel button works fine"); 
				}
				else
				{
					test.log(LogStatus.FAIL, "Cancel button works fail");
				}
				if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);//watchTutorial(driver);
				Thread.sleep(5000);wb.close();
				}
			}
			@Test(priority=3, enabled=false)
			public void watchTutorial(WebDriver driver) throws Exception
			{
				 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					
					FileInputStream fis = new FileInputStream(src);
					
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					
					XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
					
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(2000);
				//Click the Watch Tutorial Option
				driver.findElement(By.xpath(excel.getData(3, 47, 1))).click();
				WebElement iframe = driver.findElement(By.xpath(excel.getData(3, 48, 1)));
				driver.switchTo().frame(iframe);
				Thread.sleep(3500);
				try
				{
					if(driver.findElement(By.xpath(excel.getData(3, 49, 1))).isDisplayed()&&driver.findElement(By.xpath("//button/div[.='Share']")).isDisplayed())
					{
						test.log(LogStatus.PASS, "Watch Tutorial Video Played Well");
						Thread.sleep(2500);
					}
				}
				catch(Exception e)
				{
					test.log(LogStatus.FAIL, "Watch Tutorial Video not Played");
				}
				driver.switchTo().defaultContent();
				
				Thread.sleep(2000);
				//Click the Close button
				driver.findElement(By.xpath(excel.getData(3, 50, 1))).click();wb.close();
			}
			
}
