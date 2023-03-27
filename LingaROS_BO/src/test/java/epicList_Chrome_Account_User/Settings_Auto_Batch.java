package epicList_Chrome_Account_User;
	import java.util.List;
	import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.Test;

	import com.relevantcodes.extentreports.ExtentReports;
	import com.relevantcodes.extentreports.ExtentTest;
	import com.relevantcodes.extentreports.LogStatus;

	import newReadExcelFile_New.ExcelDataConfig;

	public class Settings_Auto_Batch {


			public WebDriver driver;
			
			ExtentReports rep = ExtentManager.getInstance();
			ExtentTest test = rep.startTest("Settings_AutoBatch_settings");
			
			float unknownValue = 00;
			
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
				{
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
		            Auto_Batch_Method_open(driver);
		            Auto_Batch_Method_edit(driver);
		 
					Thread.sleep(1500);
				}

				@Test(enabled=false,priority=28)
				public void   Auto_Batch_Method_open(WebDriver driver2) throws Exception {
					// TODO Auto-generated method stub
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					//Enter the URL
					driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"autoBatch");
					Thread.sleep(5000);
					try
					{
					if(driver.findElement(By.xpath(excel.getData(5, 602, 1))).getText().equalsIgnoreCase("Auto Batch"))
					{
						test.log(LogStatus.PASS,"Auto Batch page loaded Successfully");
					
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
					else
					{
						test.log(LogStatus.FAIL,"Auto Batch page loaded Failed");
						
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
				
				public void  Auto_Batch_Method_edit(WebDriver driver2) throws Exception {
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					Thread.sleep(1000);
					
if(driver.findElement(By.xpath(excel.getData(5, 603, 1))).isEnabled())
{
	driver.findElement(By.xpath(excel.getData(5, 603, 1))).click();
	Thread.sleep(1000);

}
					
else {
	
	driver.findElement(By.xpath(excel.getData(5, 603, 1))).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath(excel.getData(5, 604, 1))).click();
	driver.findElement(By.xpath(excel.getData(5, 605, 1))).click();
	driver.findElement(By.xpath(excel.getData(5, 606, 1))).click();
	driver.findElement(By.xpath(excel.getData(5, 607, 1))).click();
	driver.findElement(By.xpath(excel.getData(5, 608, 1))).click();
}
//Update the Auto Batch
    driver.findElement(By.xpath(excel.getData(5, 609, 1))).click();
    //To compare the Auto batch updated or not
    Thread.sleep(1000);
if(driver.findElement(By.cssSelector(excel.getData(5, 49, 4))).getText().equalsIgnoreCase("Auto batch Settings updated successfully"))
{										
	test.log(LogStatus.PASS,"Auto Batch Settings updated Successfully");
}
else
{
	test.log(LogStatus.FAIL,"Auto Batch Settings updated failed");
	driver.close();
}
				}
				
				}
				
