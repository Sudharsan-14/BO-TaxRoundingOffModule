package epicList_Chrome_Account_User;

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

public class Enterprise_License_Settings 
{
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Enterprise_License_Settings");

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
			Licenses_open_page(driver);
			Update_License_Settings(driver);
			//Verify_License_Settings(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=15)
		public void Licenses_open_page(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			//Enter the URl
			Thread.sleep(3000);
			driver.get(Utility.getProperty("Enterprise_Base_URL")+"licenseSettings");
			
			Thread.sleep(5000);
			try
			{
			//Check whether the Licenses page Opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 2127, 1))).getText().equalsIgnoreCase("License Settings"))
			{
				test.log(LogStatus.PASS, "License Settings page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "License Settings page loaded Failed");
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
		
		
	@Test(enabled=false,priority=16)
	public void Update_License_Settings(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(5000);
		//List of Rows
		List<WebElement> rows=driver.findElements(By.xpath(excel.getData(3, 2131, 1)));
		
		for(int i=1;i<=rows.size();i++)
		{
	
			Thread.sleep(3000);
			//Check whether the all Web Order Check box Enabled or not in Web Order row
			if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[2]/input")).isSelected())
			{
				Thread.sleep(3000);
				//Disable all Web Order check box
				driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[2]/input")).click();
			
			}
			else
			{
				
			}
		}
		Thread.sleep(3000);
		//Get the Web Order Maximum Count
		String webOrderMax=driver.findElement(By.xpath(excel.getData(3, 2132, 1))).getText();
		webOrderMax=webOrderMax.replaceAll("[^0-9]", "");
		int webMax=Integer.parseInt(webOrderMax);
		int wo = webMax + 1;
		
		for(int i=1;i<=wo;i++)
		{
			Thread.sleep(3000);
			//Enable Web Order 
			driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[2]/input")).click();

		}
		
		Thread.sleep(3000);
		//Check whether Web Order Max Count Enable Passed or not
		if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+wo+"]/td[2]/input")).isSelected())
		{
		test.log(LogStatus.FAIL, "Web Order Max Count Enable Failed");	
		}
		else
		{
			test.log(LogStatus.PASS, "Web Order Max Store Count Enable Passed");
		}
			
		/*	List<WebElement> Selected=driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr/td[2]/input"));
			int size=Selected.size();
			for(WebElement ele:Selected)
			{
				System.out.println(ele.isSelected());
				
				if(ele.getSize().equals(webOrderMax))
				{
					test.log(LogStatus.PASS, "Enabled Reached Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Enabled count excess");
				}
			}
			*/
		Thread.sleep(3000);
		for(int i=1;i<=rows.size();i++)
		{
	
			Thread.sleep(3000);
			//Check whether the all Texting Check box Enabled or not in Web Order row
			if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[3]/input")).isSelected())
			{
				Thread.sleep(3000);
				//Disable all Texting check box
				driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[3]/input")).click();
			
			}
			else
			{
				
			}
		}
		
		//Get the Texting Maximum Count
		String Texting=driver.findElement(By.xpath(excel.getData(3, 2133, 1))).getText();
		Texting=Texting.replaceAll("[^0-9]", "");
		int TextingMax=Integer.parseInt(Texting);
		int t = TextingMax + 1;
		
		for(int i=1;i<=t;i++)
		{
			//Enable Texting 
			driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[3]/input")).click();

		}
		
		Thread.sleep(3000);
		//Check whether Texting Max Count Enable Passed or not
		if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+t+"]/td[3]/input")).isSelected())
		{
		test.log(LogStatus.FAIL, "Texting Max Count Enable Failed");	
		}
		else
		{
			test.log(LogStatus.PASS, "Texting Max Store Count Enable Passed");
		}

		Thread.sleep(3000);
		for(int i=1;i<=rows.size();i++)
		{
	
			Thread.sleep(3000);
			//Check whether the all Customer Display Check box Enabled or not in Web Order row
			if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[4]/input")).isSelected())
			{
				Thread.sleep(3000);
				//Disable all Customer Display check box
				driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[4]/input")).click();
			
			}
			else
			{
				
			}
		}
		
		//Get the Custometr Display Maximum Count
		String CusDisplay=driver.findElement(By.xpath(excel.getData(3, 2134, 1))).getText();
		CusDisplay=CusDisplay.replaceAll("[^0-9]", "");
		int CusDisMax=Integer.parseInt(CusDisplay);
		int cD = CusDisMax + 1;
		
		for(int i=1;i<=cD;i++)
		{
			//Enable Customer Display 
			driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[4]/input")).click();

		}
		
		Thread.sleep(3000);
		//Check whether Customer Display Max Count Enable Passed or not
		if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+cD+"]/td[4]/input")).isSelected())
		{
		test.log(LogStatus.FAIL, "Customer Display Max Count Enable Failed");	
		}
		else
		{
			test.log(LogStatus.PASS, "Customer Display Max Store Count Enable Passed");
		}

		Thread.sleep(3000);
		for(int i=1;i<=rows.size();i++)
		{
	
			Thread.sleep(3000);
			//Check whether the all Caller ID Check box Enabled or not in Web Order row
			if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[5]/input")).isSelected())
			{
				Thread.sleep(3000);
				//Disable all Caller ID check box
				driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[5]/input")).click();
			
			}
			else
			{
				
			}
		}
		
		//Get the Caller ID Maximum Count
		String CallerID=driver.findElement(By.xpath(excel.getData(3, 2135, 1))).getText();
		CallerID=CallerID.replaceAll("[^0-9]", "");
		int CallIDMax=Integer.parseInt(CallerID);
		int cId = CallIDMax + 1;
		
		for(int i=1;i<=cId;i++)
		{
			//Enable Customer Display 
			driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[5]/input")).click();

		}
		
		Thread.sleep(3000);
		//Check whether Customer Display Max Count Enable Passed or not
		if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+cId+"]/td[5]/input")).isSelected())
		{
		test.log(LogStatus.FAIL, "Caller ID Max Count Enable Failed");	
		}
		else
		{
			test.log(LogStatus.PASS, "Caller ID Max Store Count Enable Passed");
		}

		Thread.sleep(3000);
		for(int i=1;i<=rows.size();i++)
		{

			Thread.sleep(3000);
			//Check whether the all Wait List Check box Enabled or not
			if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[6]/input")).isSelected())
			{
				Thread.sleep(3000);
				//Disable all Wait List check box
				driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[6]/input")).click();
	
			}
			else
			{
		
			}
		}

		//Get the Wait List Maximum Count
		String WaitList=driver.findElement(By.xpath(excel.getData(3, 2136, 1))).getText();
		WaitList=WaitList.replaceAll("[^0-9]", "");
		int waitLiMax=Integer.parseInt(WaitList);
		int wl = waitLiMax + 1;

		for(int i=1;i<=wl;i++)
		{
			//Enable Wait List 
			driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[6]/input")).click();

		}

		Thread.sleep(3000);
		//Check whether Wait List Max Count Enable Passed or not
		if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+wl+"]/td[6]/input")).isSelected())
		{
			test.log(LogStatus.FAIL, "Wait List Max Count Enable Failed");	
		}
		else
		{
			test.log(LogStatus.PASS, "Wait List Max Store Count Enable Passed");
		}

		Thread.sleep(3000);
		for(int i=1;i<=rows.size();i++)
		{

			Thread.sleep(3000);
			//Check whether the all Call Center Check box Enabled or not in Web Order row
			if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[7]/input")).isSelected())
			{
				Thread.sleep(3000);
				//Disable all Call Center check box
				driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[7]/input")).click();
	
			}
			else
			{
		
			}
		}

		//Get the Call Center Maximum Count
		String CallCenter=driver.findElement(By.xpath(excel.getData(3, 2137, 1))).getText();
		CallCenter=CallCenter.replaceAll("[^0-9]", "");
		int callCenterMax=Integer.parseInt(CallCenter);
		int cc = callCenterMax + 1;

		for(int i=1;i<=cc;i++)
		{
			//Enable Call Center
			driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[7]/input")).click();

		}

		Thread.sleep(3000);
		//Check whether Call Center Max Count Enable Passed or not
		if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+cc+"]/td[7]/input")).isSelected())
		{
			test.log(LogStatus.FAIL, "Call Center Max Count Enable Failed");	
		}
		else
		{
			test.log(LogStatus.PASS, "Call Center Max Store Count Enable Passed");
		}

	
	Thread.sleep(3000);
	for(int i=1;i<=rows.size();i++)
	{

		Thread.sleep(3000);
		//Check whether the all Schedule Check box Enabled or not 
		if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[8]/input")).isSelected())
		{
			Thread.sleep(3000);
			//Disable all Schedule check box
			driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[8]/input")).click();

		}
		else
		{
	
		}
	}

	//Get the Schedule Maximum Count
	String Schedule=driver.findElement(By.xpath(excel.getData(3, 2138, 1))).getText();
	Schedule=Schedule.replaceAll("[^0-9]", "");
	int ScheduleMax=Integer.parseInt(Schedule);
	int s = ScheduleMax + 1;

	for(int i=1;i<=s;i++)
	{
		//Enable Schedule
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[8]/input")).click();

	}

	Thread.sleep(3000);
	//Check whether Schedule Max Count Enable Passed or not
	if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+s+"]/td[8]/input")).isSelected())
	{
		test.log(LogStatus.FAIL, "Schedule Max Count Enable Failed");	
	}
	else
	{
		test.log(LogStatus.PASS, "Schedule Max Store Count Enable Passed");
	}
		

	Thread.sleep(3000);
	for(int i=1;i<=rows.size();i++)
	{

		Thread.sleep(3000);
		//Check whether the all Integration Check box Enabled or not 
		if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[9]/input")).isSelected())
		{
			Thread.sleep(3000);
			//Disable all Integration check box
			driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[9]/input")).click();

		}
		else
		{
	
		}
	}

	//Get the Integration Maximum Count
	String Integration=driver.findElement(By.xpath(excel.getData(3, 2139, 1))).getText();
	Integration=Integration.replaceAll("[^0-9]", "");
	int IntegrationMax=Integer.parseInt(Integration);
	int it = IntegrationMax + 1;

	for(int i=1;i<=it;i++)
	{
		//Enable Integration
		driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[9]/input")).click();

	}

	Thread.sleep(3000);
	//Check whether Integration Max Count Enable Passed or not
	if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+it+"]/td[9]/input")).isSelected())
	{
		test.log(LogStatus.FAIL, "Integration Max Count Enable Failed");	
	}
	else
	{
		test.log(LogStatus.PASS, "Integration Max Store Count Enable Passed");
	}
		
		Thread.sleep(3000);
		for(int i=1;i<=rows.size();i++)
		{

			Thread.sleep(3000);
			//Check whether the all Virtual Kiosk Check box Enabled or not 
			if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[10]/input")).isSelected())
			{
				Thread.sleep(3000);
				//Disable all Virtual Kiosk check box
				driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[10]/input")).click();

			}
			else
			{
			
			}
		}

		//Get the Virtual Kiosk Maximum Count
		String VirtualKioskCount=driver.findElement(By.xpath(excel.getData(3, 2140, 1))).getText();
		VirtualKioskCount=VirtualKioskCount.replaceAll("[^0-9]", "");
		int VirtualKioskCountMax=Integer.parseInt(VirtualKioskCount);
		int vk = VirtualKioskCountMax + 1;

		for(int i=1;i<=vk;i++)
		{
			//Enable Virtual Kiosk
			driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+i+"]/td[10]/input")).click();

		}

		Thread.sleep(3000);
		//Check whether Virtual Kiosk Max Count Enable Passed or not
		if(driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+vk+"]/td[10]/input")).isSelected())
		{
			test.log(LogStatus.FAIL, "Virtual Kiosk Max Count Enable Failed");	
		}
		else
		{
			test.log(LogStatus.PASS, "Virtual Kiosk Max Store Count Enable Passed");
		}
		
		Thread.sleep(3000);
		//Click Update Button
		driver.findElement(By.xpath(excel.getData(3, 405, 1))).click();
	
		Thread.sleep(4000);
		//Check whether the License Settings Updated or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("License Settings Updated Successfully"))
		{
			test.log(LogStatus.PASS, "License Settings Updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "License Settings Updated Failed");
		}
		wb.close();
		Thread.sleep(4000);
	}

}
