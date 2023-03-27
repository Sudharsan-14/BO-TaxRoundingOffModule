package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
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


public class Inventory_Home {

	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Home");

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
		//	SendMail.snedMailWithAttachment();
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
			Inventory_Home_openpage(driver);
			Inventory_Home_refresh_Page(driver);
			Inventory_Home_Verify_Reports(driver);
			Thread.sleep(1500);
		}
		
	   @Test(enabled=false,priority=2)
		public void Inventory_Home_openpage(WebDriver driver) throws Exception
		{
		   File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(15000);		
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"inventoryHome");
			Thread.sleep(4000);
			try
			{
			//Check Inventory Home page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1154, 1))).getText().equalsIgnoreCase("Inventory Home"))
			{
				test.log(LogStatus.PASS, "Inventory Home page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Inventory Home page loaded Failed");wb.close();
			
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
		}
/*	   
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
		*/
	   @Test(enabled=false,priority=3)
		public void Inventory_Home_refresh_Page(WebDriver driver) throws Exception
		{
		   File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(4000);
			//Click the refresh button
			driver.findElement(By.xpath(excel.getData(3, 1155, 1))).click();
			Thread.sleep(4000);
			//Check Inventory Home page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1156, 1))).getText().equalsIgnoreCase("Live Updates"))
			{
				test.log(LogStatus.PASS, "Inventory Home page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inventory Home page refreshed Failed");wb.close();
			}
		}
			
		@Test(enabled=false,priority=4)
		public void Inventory_Home_Verify_Reports(WebDriver driver) throws Exception
		{
			    File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(4000);
			//Check the Total Value On Hand value
			if(driver.findElement(By.xpath(excel.getData(3, 1157, 1))).getText().equalsIgnoreCase("Total Value On Hand"))
			{
				test.log(LogStatus.PASS, "Total Value On Hand report is available");
				
				test.log(LogStatus.INFO, "Total Value On Hand amount is : "+driver.findElement(By.xpath(excel.getData(3, 1158, 1))).getText());
				
			}
		
			else
			{
				test.log(LogStatus.FAIL, "Total Value On Hand report is not available");
			}
			
			Thread.sleep(4000);
			//Check the COGS value
			if(driver.findElement(By.xpath(excel.getData(3, 1159, 1))).getText().equalsIgnoreCase("COGS"))
			{
				test.log(LogStatus.PASS, "COGS report is available");
				
				test.log(LogStatus.INFO, "Total COGS value is : "+driver.findElement(By.xpath(excel.getData(3, 1160, 1))).getText());
				
			}
		
			else
			{
				test.log(LogStatus.FAIL, "COGS report is not available");
			}
			
			Thread.sleep(4000);
			/*//Check the LOW STOCKS value
			if(driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[1]/div[1]/h4/a/span/span[2]"))!=null)
			{
				test.log(LogStatus.PASS, "LOW STOCKS report is available");
				
				test.log(LogStatus.INFO, "Total LOW STOCKS value is : "+driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[1]/div[1]/h4/a/span/span[2]")).getText());
		*/
	//		driver.findElement(By.xpath(excel.getData(3, 1654, 1))).click();
			
			
			//Check the LOW STOCKS value
			if(driver.findElement(By.xpath(excel.getData(3, 1654, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "LOW STOCKS report is available");
						
				test.log(LogStatus.INFO, "Total LOW STOCKS value is : "+driver.findElement(By.xpath(excel.getData(3, 1658, 1))).getText());
						
				driver.findElement(By.xpath(excel.getData(3, 1659, 1))).click();
				
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1656, 1)));
				
				for(int i = 1; i <= rows.size(); i++)
				{
					
				test.log(LogStatus.INFO, driver.findElement(By.xpath("//div[@class='panel-collapse in collapse']/div/div[2]/div[1]/div/table/tbody/tr["+i+"]/td[1]")).getText()+" available quantity is "+driver.findElement(By.xpath("//div[@class='panel-collapse in collapse']/div/div[2]/div[1]/div/table/tbody/tr["+i+"]/td[3]")).getText()+" but the minimum quantity is "+driver.findElement(By.xpath("//div[@class='panel-collapse in collapse']/div/div[2]/div[1]/div/table/tbody/tr["+i+"]/td[2]")).getText());
				
				}
			}
		
			else
			{
				test.log(LogStatus.FAIL, "LOW STOCKS report is not available");
			}
			
			
		/*	Thread.sleep(4000);
			//Check the Non Consume Orders value
			if(driver.findElement(By.xpath(excel.getData(3, 1165, 1)))!=null)
			{
				test.log(LogStatus.PASS, "Non Consume Orders report is available");
				
				test.log(LogStatus.INFO, "Total Non Consume Order is : "+driver.findElement(By.xpath(excel.getData(3, 1165, 1))).getText());
				
				Thread.sleep(3000);
				//Click the Non Consume Orders option 
				driver.findElement(By.xpath(excel.getData(3, 1166, 1))).click();
				
				Thread.sleep(3000);
				
				if(driver.findElement(By.xpath(excel.getData(3, 1167, 1))).getText().equalsIgnoreCase("No Records Found"))
				{
					test.log(LogStatus.FAIL, "No Records Found for Consume order");
				}
				
				else
				{
					Thread.sleep(2000);
					//Click the Consume Order Option
					driver.findElement(By.xpath(excel.getData(3, 1168, 1))).click();
					Thread.sleep(2000);
				
						Thread.sleep(3000);
					//Check Consume order finished or not
					if(driver.findElement(By.xpath(excel.getData(3, 1169, 1))).getText().equalsIgnoreCase("Consume finished successfully"))
					{
						test.log(LogStatus.PASS, "Consume order finished Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Consume order finished Failed");
					}
				}
			
			}
			else
			{
				test.log(LogStatus.FAIL, "Non Consume Orders is not available");
				
			}*/
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);Thread.sleep(5000);wb.close();}
		}
		
}
