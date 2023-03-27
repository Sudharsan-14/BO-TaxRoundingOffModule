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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Inventory_Purchase_Invoice 
{
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Purchase Invoice");

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
			// Call the chrome driver by using local path
			System.setProperty("webdriver.chrome.driver", Utility.getProperty("Chrome_Driver_Path"));
			// Open the Chrome window
			driver = new ChromeDriver();
			// Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Maximize the Chrome window
			driver.manage().window().maximize();
			Thread.sleep(1000);
			// Launch the URL
			driver.get(Utility.getProperty("appURL"));
			if (Utility.getProperty("Product").equalsIgnoreCase("UPOS")) {
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.UPOS_login(driver, test);
			} else {
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.Linga_login(driver, test);
			}
		}

		@Test(priority = 500)
		public void logout() throws Exception {
			Browser_Account_Level_User a = new Browser_Account_Level_User();
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
			Inventory_Purchase_Invoice_Open_Page(driver);
			Edit_and_Update_Inventory_Purchase_Invoice(driver);
			Thread.sleep(1500);
		}
		
		@Test(priority=11,enabled=false)
		public void Inventory_Purchase_Invoice_Open_Page(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"purchaseInvoice");
			Thread.sleep(5000);
			try
			{
			//Check Inventory Purchase Invoice page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 2281, 1))).getText().equalsIgnoreCase("Purchase Invoice"))
			{
				test.log(LogStatus.PASS, "Purchase Invoice page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Purchase Invoice page loaded Failed");

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
			Thread.sleep(5000);
		
		}
		
		@Test(priority=12,enabled=false)
		public void Edit_and_Update_Inventory_Purchase_Invoice(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			Thread.sleep(3000);
			//Click the Refrash button
			driver.findElement(By.xpath(excel.getData(3, 133, 1))).click();
			
			Thread.sleep(5000);
			try
			{
				//Check whether the Purchase Invoice Available or not
				if(driver.findElement(By.xpath(excel.getData(3, 2275, 1))).getText().equalsIgnoreCase("No records found"))
				{
					test.log(LogStatus.FAIL, "There is No Inventory Purchase Invoice Available");
					
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
			}
			catch(Exception e)
			{
				test.log(LogStatus.PASS, "Inventory Purchase Invoice Available");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
				
				//List of Purchase Order
				List rows=driver.findElements(By.xpath("//table[@ng-table='tableParams']/tbody/tr"));
				
				int rowSize=rows.size();
				
				for(int i=1;i<=rowSize;i++)
				{
					test.log(LogStatus.INFO, "POS Invoice Number - "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[2]")).getText()+" Received Date - "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[3]")).getText()+" Status - "+driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[7]")).getText());
				}
				
				Thread.sleep(2000);
				//Last Invoice number
				String EditInv=driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+rowSize+"]/td[2]")).getText();
				
				//Clear Invoice in Search box
				driver.findElement(By.xpath(excel.getData(3, 2282, 1))).clear();
				//Enter the Purchase number
				driver.findElement(By.xpath(excel.getData(3, 2282, 1))).sendKeys(EditInv+"1");
				Thread.sleep(1500);
				driver.findElement(By.xpath(excel.getData(3, 2282, 1))).sendKeys(Keys.BACK_SPACE);

				Thread.sleep(4000);
				driver.findElement(By.xpath(excel.getData(3, 2283, 1))).click();
				
				
				Thread.sleep(3000);
				//Check whether the Edit Purchase Invoice page opened or not
				if(driver.findElement(By.xpath(excel.getData(3, 2284, 1))).getText().equalsIgnoreCase("Edit Purchase Invoice"))
				{
					test.log(LogStatus.PASS, "Edit Purchase Invoice page opened Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Edit Purchase Invoice page Open Failed");
				}
				
				Thread.sleep(2000);
				//Clear Vendor Invoice number
				driver.findElement(By.xpath(excel.getData(3, 2285, 1))).clear();
				//Enter the Vendor Invoice Number
				driver.findElement(By.xpath(excel.getData(3, 2285, 1))).sendKeys("Rec"+EditInv);
				
				Thread.sleep(2000);
				//Click the Submit button
				driver.findElement(By.xpath(excel.getData(3, 2286, 1))).click();
				
				WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
				WebDriverWait wait=new WebDriverWait(driver,90);
				
				//Check whether the Purchase Invoice Updated or not
				if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Invoice Updated Successfully"))
				{
					test.log(LogStatus.PASS, "Purchase Invoice Updated successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase Invoice updated Failed");
				}
				
				
				wb.close();
			}
		  
		}
}