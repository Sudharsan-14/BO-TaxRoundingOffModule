package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Inventory_Transfer_Request {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Transfer_Request");

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
			Inventory_Transfer_Requests_Pageopen(driver);
			Inventory_TransferRequest_Refresh(driver);
			Inventory_TransferRequest_addCancel(driver);
			Inventory_TransferRequest_add(driver);
			Inventory_Centralwarehouse_Pageopen(driver);
			Inventory_Centralwarehouse_openTransfer_Centrallevel(driver);
			Inventory_Centralwarehouse_Backbutton(driver);
			Inventory_Centralwarehouse_Click_Transferbutton_Errormessage(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=44)
		public void Inventory_Transfer_Requests_Pageopen(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis); 
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);

			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"transfer");
			
			Thread.sleep(5000);
			try
			{
			//Check Inventory Items page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1702, 1))).getText().equalsIgnoreCase("Transfer Request"))
			{
				test.log(LogStatus.PASS, " Transfer Requests page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, " Transfer Requests page loaded Failed");
			
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
			wb.close();
			Thread.sleep(3000);
			
		}
	
		@Test(enabled=false,priority=45)
		public void Inventory_TransferRequest_Refresh(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis); 
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);

			Thread.sleep(2000);
			//Click refresh the button for Transfer request
			driver.findElement(By.xpath(excel.getData(3, 1703, 1))).click();
			wb.close();
		
		}
	
		@Test(enabled=false,priority=47)
		public void Inventory_TransferRequest_addCancel(WebDriver driver) throws Exception
		{
			
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis); 
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);
	
		Thread.sleep(3000);
		//Click Transfer Request button
		driver.findElement(By.cssSelector(excel.getData(3, 1384, 4))).click();
		//Check New Transfer Request page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1382, 1))).getText().equalsIgnoreCase("New Transfer Request"))
		{
			test.log(LogStatus.PASS, "New Transfer Request page Successfully loaded ");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Transfer Request page Successfully loaded is failed");
		}
		Thread.sleep(2000);
		try
		{
		//Click Destination field to select store
		driver.findElement(By.xpath(excel.getData(3, 2304, 1))).click();
		Thread.sleep(2000);
		//Click Destination field to select store
	//	driver.findElement(By.xpath(excel.getData(3, 2305, 1))).sendKeys(Utility.getProperty("Inventory_Menuconfic_From_CentralWareHouse"));
		driver.findElement(By.xpath(excel.getData(3, 2305, 1))).click();

		//Click Destination field to select store
		driver.findElement(By.xpath(excel.getData(3, 2305, 1))).sendKeys(Keys.ENTER);
		}
		catch(Exception c) {}
	 
		Thread.sleep(5000); 
		//Click new Inventory item button
		driver.findElement(By.xpath(excel.getData(3, 1706, 1))).click();
		
		Thread.sleep(2000);
		//Select new Inventory item
		driver.findElement(By.xpath(excel.getData(3, 1710, 1))).click();
		
		
		Thread.sleep(3000);
		//select Inventory item
		driver.findElement(By.xpath(excel.getData(3, 1711, 1))).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
	
		//select Inventory item
		driver.findElement(By.xpath(excel.getData(3, 1711, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//Clear the quantity value
		driver.findElement(By.xpath(excel.getData(3, 1712, 1))).clear();
		//Enter the quantity value
		driver.findElement(By.xpath(excel.getData(3, 1712, 1))).sendKeys("1");
	
	
						Thread.sleep(1000);
						//Click remove inventory item button
						driver.findElement(By.xpath(excel.getData(3, 1713, 1))).click();
						
						Thread.sleep(3000);
						//Click cancel the inventory item
						driver.findElement(By.cssSelector(excel.getData(3, 197, 4))).click();
						wb.close();
						Thread.sleep(3000);
						
		}	
		
		@Test(enabled=false,priority=48)
		public void Inventory_TransferRequest_add(WebDriver driver) throws Exception
		{
						
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis); 
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);

			Thread.sleep(3000);
		//Click Transfer Request button
		driver.findElement(By.cssSelector(excel.getData(3, 1384, 4))).click();
		//Check New Transfer Request page opened or not
				if(driver.findElement(By.xpath(excel.getData(3, 1382, 1))).getText().equalsIgnoreCase("New Transfer Request"))
				{
					test.log(LogStatus.PASS, "New Transfer Request page Successfully loaded ");
				}
				else
				{
					test.log(LogStatus.FAIL, "New Transfer Request page Successfully loaded is failed");
				}
				Thread.sleep(2000);
				try
				{
				//Click Destination field to select store
				driver.findElement(By.xpath(excel.getData(3, 2304, 1))).click();
				
				Thread.sleep(1000);
				//Click Destination field to select store
				//driver.findElement(By.xpath(excel.getData(3, 2305, 1))).sendKeys(Utility.getProperty("Inventory_Menuconfic_From_CentralWareHouse"));
				driver.findElement(By.xpath(excel.getData(3, 2305, 1))).click();

				
				Thread.sleep(1000);
				//Click Destination field to select store
				driver.findElement(By.xpath(excel.getData(3, 2305, 1))).sendKeys(Keys.ENTER);
				}
				catch(Exception h) {}
			
				Thread.sleep(2000);
				//Click new Inventory item button
				driver.findElement(By.xpath(excel.getData(3, 1706, 1))).click();
				
				Thread.sleep(2000);
				//Select new Inventory item
				driver.findElement(By.xpath(excel.getData(3, 1710, 1))).click();
				
				
				Thread.sleep(1000);
				//select Inventory item
				driver.findElement(By.xpath(excel.getData(3, 1711, 1))).sendKeys(Keys.ARROW_DOWN);
				Thread.sleep(1000);
			
				//select Inventory item
				driver.findElement(By.xpath(excel.getData(3, 1711, 1))).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				
				//Clear the quantity value
				driver.findElement(By.xpath(excel.getData(3, 1712, 1))).clear();
				//Enter the quantity value
				driver.findElement(By.xpath(excel.getData(3, 1712, 1))).sendKeys("1");
	
	
				Thread.sleep(2000);
				//Click new Inventory item button
				driver.findElement(By.xpath(excel.getData(3, 1706, 1))).click();
				
				Thread.sleep(2000);
				//Select new Inventory item
				driver.findElement(By.xpath(excel.getData(3, 1707, 1))).click();
				
				
				Thread.sleep(1000);
				//select Inventory item		
			//	driver.findElement(By.xpath(excel.getData(3, 1708, 1))).sendKeys(Utility.getProperty("Transfer_Inventory_item2"));
				driver.findElement(By.xpath(excel.getData(3, 1708, 1))).click();

				Thread.sleep(1000);
			
				//select Inventory item
				driver.findElement(By.xpath(excel.getData(3, 1708, 1))).sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				
				//Clear the quantity value
				driver.findElement(By.xpath(excel.getData(3, 1709, 1))).clear();
				//Enter the quantity value
				driver.findElement(By.xpath(excel.getData(3, 1709, 1))).sendKeys("1");
	
			
			//Click Request button for inventory item
			driver.findElement(By.cssSelector(excel.getData(3, 1463, 4))).click();
			Thread.sleep(3000);	
			//Check Central Warehouse page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 243, 1))).getText().equalsIgnoreCase("Transfer request created successfully!."))
			{
				test.log(LogStatus.PASS, "Transfer request created successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Transfer request created is Failed");
			}
			try		
			{			
			if(driver.findElement(By.xpath(excel.getData(3, 1331, 1))).isDisplayed())			
			{				
			test.log(LogStatus.FAIL, "There is no record available for Transfer Requests(store level inventory)");			
			}		
			}		
			catch (Exception e)		
			{			
			test.log(LogStatus.PASS, "Record available for Transfer Requests(store level inventory)");					
			 
			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1687, 1)));
				for(int i = 2; i <= rows.size(); i++)
				{
				 //Get the Transfer request
				test.log(LogStatus.PASS,"Source of the  "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[1]")).getText()+"  "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText()+" to "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText()+" on " +driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[4]")).getText());
				}
			}
			wb.close();
			}
		
		@Test(enabled=false,priority=49)
		public void Inventory_Centralwarehouse_Pageopen(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis); 
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);

			/*	JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[4]/a/span[1]"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			Thread.sleep(3000);
			//Click the Inventory option
//			driver.findElement(By.xpath("//span[.='Inventory']")).click();
			//Click the My Stores option
			driver.findElement(By.xpath("//span[.='My Stores']")).click();
			Thread.sleep(3000);
			*/
				wb.close();
		}
		
		@Test(enabled=false,priority=50)
		public void Inventory_Centralwarehouse_openTransfer_Centrallevel(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis); 
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);

			/*	Thread.sleep(1000);
	        //Click the Central Inventory Reports Option		
			driver.findElement(By.xpath("//span[.='Central Inventory']")).click();
			
			Thread.sleep(2000);
			//Click the Central WareHouse option
			driver.findElement(By.xpath("//span[.='Central WareHouse']")).click();
			
				Thread.sleep(5000);
				if(driver.findElement(By.xpath("//div[.='"+Utility.getProperty("Inventory_Menuconfic_From_CentralWareHouse")+"']")).isDisplayed())
				{
					driver.findElement(By.xpath("//div[.='"+Utility.getProperty("Inventory_Menuconfic_From_CentralWareHouse")+"']")).click();				
					
					JavascriptExecutor je = (JavascriptExecutor) driver;
					//Identify the WebElement which will appear after scrolling down
					WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[2]/ul/li[12]/a/span"));
					//Scroll the page till the Reason option present
					je.executeScript("arguments[0].scrollIntoView(true);",element); 
					//Wait for 30 seconds
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 		//Click the  Transfer Option		
					driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[2]/ul/li[12]/a/span")).click();
				}*/
				wb.close();
		}
				
		@Test(enabled=false,priority=51)
		public void Inventory_Centralwarehouse_Backbutton(WebDriver driver) throws Exception		
		{	
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis); 
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);

			/*		Thread.sleep(2000);
				//Click Accept button
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr/td[5]/span/a[2]/i")).click();//NEED TO CHECK
				//Clear the search field
			//	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div/label/input")).clear();
				//Enter the search field
			//	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div/label/input")).sendKeys(Utility.getProperty("Inventory_Menuconfic_From_CentralWareHouse"));
				
				Thread.sleep(2000);
				//Clear the Transfer Count 
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[6]/input")).clear();
				Thread.sleep(2000);

				//Enter the Transfer Count
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[6]/input")).sendKeys("3");
				Thread.sleep(2000);

				//Click back button after entering the values
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/button")).click();
			*/	
				wb.close();
				}
				
		@Test(enabled=false,priority=52)
		public void Inventory_Centralwarehouse_Click_Transferbutton_Errormessage(WebDriver driver) throws Exception		
		{	
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis); 
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				Thread.sleep(5000);

			/*				{
				Thread.sleep(2000);
				//Click Accept button
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr/td[5]/span/a[2]/i")).click();//NEED TO CHECK
				//Clear the search field
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div/label/input")).clear();
				//Enter the search field
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div/label/input")).sendKeys(Utility.getProperty("Inventory_Menuconfic_From_CentralWareHouse"));
				
				Thread.sleep(2000);
				//Clear the Transfer Count
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[6]/input")).clear();
				Thread.sleep(2000);

				//Enter the Transfer Count
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[6]/input")).sendKeys("1");
				Thread.sleep(2000);
				//Click Transfer Items button
				driver.findElement(By.cssSelector("button.btn.btn-small.btn-success.btn-border-green.ng-binding")).click();
				
				//Check Central Warehouse page opened or not
				if(driver.findElement(By.xpath(excel.getData(3, 243, 1))).getText().equalsIgnoreCase("item(s) are not transferred"))
				{
					test.log(LogStatus.PASS, "Transfer request accepted successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Transfer request accepted is Failed");
				}
				}*/
				wb.close();
				}

}
