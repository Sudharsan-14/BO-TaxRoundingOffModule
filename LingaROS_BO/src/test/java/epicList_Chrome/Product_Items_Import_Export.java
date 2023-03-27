package epicList_Chrome;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.Wait;

import bsh.util.Util;
import newReadExcelFile_New.ExcelDataConfig;

public class Product_Items_Import_Export 
{

	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Product/Items - Import/Export Validation");

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
			Thread.sleep(15000);
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
			open_Enterprise_Page(driver);
			create_New_Store(driver);
			Open_import_Emport(driver);
			//download_Template_import_Emport(driver); 
			//download_Data_import_Emport(driver);
			Export_Data_import_Emport(driver);
			Thread.sleep(1500);
		}
		
		@Test(priority=4,enabled=false)
		public void open_Enterprise_Page(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//To Get URL
			driver.get((Utility.getProperty("Enterprise_Base_URL")));
			
			Thread.sleep(5000);
			
			try
			{
				if(driver.findElement(By.xpath("//span[.='Do You Want to Take a look.. ']")).isDisplayed())
				{
					driver.findElement(By.xpath("//button[contains(.,'Not now')]")).click();
				}		
			}
			catch(Exception e) {}
			try {
			Thread.sleep(5000);
			//Check whether the Enterprise page loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 2219, 1))).getText().equalsIgnoreCase("My Stores"))
			{
				test.log(LogStatus.PASS, "Enterprise Page Loaded successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Enterprise page loaded Failed");
			
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
		
		@Test(priority=5,enabled=false)
		public void create_New_Store(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			Thread.sleep(5000);
			//Click Add Store button
			driver.findElement(By.xpath(excel.getData(3, 2220, 1))).click();
			
			Thread.sleep(3000);
			//Check whether the New Store creation page loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 2221, 1))).getText().equalsIgnoreCase("Store"))
			{
				test.log(LogStatus.PASS, "New Store creation page Loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Creation page Loaded Failed");
			}
			
			Thread.sleep(2000);
			//Enter the Store Name
			driver.findElement(By.xpath(excel.getData(3, 2222, 1))).sendKeys(Utility.getProperty("New_Store_Name"));
		
			Thread.sleep(2000);
			//Enter the Phone Number
			driver.findElement(By.name(excel.getData(3, 286, 3))).sendKeys(Utility.getProperty("New_Store_Phone"));
			
			Thread.sleep(2000);
			//Enter the Store Email ID
			driver.findElement(By.name(excel.getData(3, 287, 3))).sendKeys(Utility.getProperty("New_Store_Email"));
			
			Thread.sleep(2000);
			//Enter the Address Line 1
			driver.findElement(By.name(excel.getData(3, 288, 3))).sendKeys(Utility.getProperty("New_Store_Address_Line1"));
			
			Thread.sleep(2000);
			//Enter the Address Line 2
			driver.findElement(By.name(excel.getData(3, 289, 3))).sendKeys(Utility.getProperty("New_Store_Address_Line2"));

			Thread.sleep(2000);
			//Enter the City
			driver.findElement(By.name(excel.getData(3, 290, 3))).sendKeys(Utility.getProperty("New_Store_City"));

			Thread.sleep(2000);
			//Enter the State
			driver.findElement(By.name(excel.getData(3, 291, 3))).sendKeys(Utility.getProperty("New_Store_State"));

			Thread.sleep(2000);
			//Enter the Zipcode
			driver.findElement(By.name(excel.getData(3, 292, 3))).sendKeys(Utility.getProperty("New_Store_ZipCode"));

			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			
			Thread.sleep(2000);
			//Click the Country
			driver.findElement(By.xpath(excel.getData(3, 2223, 1))).click();
			Thread.sleep(1000);
			//Select the Country
			driver.findElement(By.xpath(excel.getData(3, 2224, 1))).sendKeys("India");
			//enter the Country
			driver.findElement(By.xpath(excel.getData(3, 2224, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Time Zone
			driver.findElement(By.xpath(excel.getData(3, 2225, 1))).click();
			Thread.sleep(1000);
			//Select the Time Zone
			driver.findElement(By.xpath(excel.getData(3, 2226, 1))).sendKeys("(GMT+05:30) Chennai, Kolkata, Mumbai, New Delhi");
			//enter the Time Zone
			driver.findElement(By.xpath(excel.getData(3, 2226, 1))).sendKeys(Keys.ENTER);
		
		/*	//Click the Language
			driver.findElement(By.xpath(excel.getData(3, 2227, 1))).click();
			//Select the Language 
			driver.findElement(By.xpath(excel.getData(3, 2228, 1))).sendKeys("Tamil");
			//enter the Language 
			driver.findElement(By.xpath(excel.getData(3, 2228, 1))).sendKeys(Keys.ENTER);*/
		
			Thread.sleep(4000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			WebElement NwStore=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,30);
			//Chevk whether the New Store Saved or not
			if(wait.until(ExpectedConditions.visibilityOf(NwStore)).getText().equalsIgnoreCase("Store saved successfully"))
			{
				test.log(LogStatus.PASS, "New Store Created Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Store Created Failed");
			}
			
			Thread.sleep(10000);
			//click the Refresh button
			driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();
			
			Thread.sleep(5000);
			//Clear the Search in Exterprise page
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the Store Name
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("New_Store_Name"));
			
			Thread.sleep(3000);
			//Click the Entered store Dashboard page
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[4]/div/div/div[2]/div/div[1]/a/h5")).click();
			
			//Check if we logged in or not
			try
			{
				Thread.sleep(3000);
				if(driver.findElement(By.xpath("//span[.='Dashboard']")).getText().equalsIgnoreCase("Dashboard"))
				{
					test.log(LogStatus.PASS, "Linga Store level Dashboard page loaded sucessfully ");
				}
			}
			catch(Exception e)
			{
				test.log(LogStatus.FAIL, "Linga Store level Dashboard page loaded failed ");
			}
			Thread.sleep(5000);
			
			
				try
			{
				if(driver.findElement(By.xpath("//span[.='Do You Want to Take a look.. ']")).isDisplayed())
				{
					driver.findElement(By.xpath("//button[contains(.,'Not now')]")).click();
				}		
			}
			catch(Exception e) {}
			
			
		}
		
		@Test(priority=6,enabled=false)
		public void Open_import_Emport(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			Thread.sleep(5000);
			
		//	driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"importExportMenu");
			
			Thread.sleep(5000);
			//Click the Product and Items in Menu bar
			driver.findElement(By.xpath("//span[.='Products/Items']/../span[2]")).click();
			
			Thread.sleep(2000);
			//Click the Import/Export 
			driver.findElement(By.xpath("//span[.='Import/Export']")).click();
			
			Thread.sleep(5000);
			//Chcek whetether the Import/Export page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 2211, 1))).getText().equalsIgnoreCase("Menu Import/Export"))
			{
				test.log(LogStatus.PASS, "Import/Export page Opened Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Import/Export page Open Failed");
			}
		}
		@Test(priority=7,enabled=false)
		public void download_Template_import_Emport(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			Thread.sleep(3000);
		//	driver.findElement(By.xpath(excel.getData(3, 2212, 1))).click();
			
			
			Thread.sleep(2000);
			//Click the Download Template 
			driver.findElement(By.xpath(excel.getData(3, 2214, 1))).click();
			
			Thread.sleep(15000);
			//Check whether the Template was downloded or not
			isFileDownloaded("C:\\Users\\krishnark\\Downloads\\File", "Linga_Auto_Test's Menu WB.xlsx");
			
		}
		public boolean isFileDownloaded(String downloadPath, String fileName) throws Exception
		{
			File dir=new File(downloadPath);
			File[] dirContents=dir.listFiles();
			
			for(int i=0;i<dirContents.length;i++)
			{
				if(dirContents[i].getName().equals(fileName))
				{
					test.log(LogStatus.PASS, "Template/Data Downloaded successfully");
					Thread.sleep(4000);
					dirContents[i].delete();
					return true;
				}
				else
				{
					test.log(LogStatus.FAIL, "Menu Template/Data Dwonloaded Failed");
				}
			}
			return false;
		}
		
		@Test(priority=8,enabled=false)
		public void download_Data_import_Emport(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			Thread.sleep(3000);
		//	driver.findElement(By.xpath(excel.getData(3, 2212, 1))).click();
			
			
			Thread.sleep(2000);
			//Click the Download Template 
			driver.findElement(By.xpath(excel.getData(3, 2215, 1))).click();
			
			Thread.sleep(15000);
			//Check whether the Template was downloded or not
			isFileDownloaded("C:\\Users\\krishnark\\Downloads\\File", "Linga_Auto_Test's Menu WB.xlsx");
			
			
		}
		
		@Test(priority=9,enabled=false)
		public void Export_Data_import_Emport(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			Thread.sleep(3000);
		//	driver.findElement(By.xpath(excel.getData(3, 2212, 1))).click();
		//	String s=System.getenv(Utility.getProperty("Import_Export_Template"));
			
			Thread.sleep(2000);
			//Click the Browse Button 
			//driver.findElement(By.xpath(excel.getData(3, 2216, 1))).sendKeys(Utility.getProperty("Import_Export_Template_Path"));
			WebElement upl=driver.findElement(By.xpath(excel.getData(3, 2216, 1)));
			upl.sendKeys("C:\\Users\\krishnark\\Desktop\\Raja\\zam zam's Menu WB.xlsx");
			
			Thread.sleep(4000);	
			//Click the Upload Button
			driver.findElement(By.xpath(excel.getData(3, 2218, 1))).click();
			
		/*	 FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver).withTimeout(50, TimeUnit.SECONDS).pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
				WebElement export=wait.until(new Function <WebDriver,WebElement>() {

					@Override
					public WebElement apply(WebDriver driver) {
						// TODO Auto-generated method stub
						WebElement export=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
						String ExportText=export.getText();
						//Chevk whether the File was Exported( Uploaded) or not
						if(ExportText.equalsIgnoreCase("Menu Import is processing now. Please check back in few minutes!."))
						{
							test.log(LogStatus.PASS, "Menu Uploaded / Exported Successfully");
						}
						else
						{
							test.log(LogStatus.INFO, "Menu Uploaded / Exported Failed");
						}

						
						return null;
					}
				});
		*/
			
			//Thread.sleep(25000);
			try
			{
				WebElement export=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
				WebDriverWait wait=new WebDriverWait(driver,300);

				
			//Chevk whether the File was Exported( Uploaded) or not
			if(wait.until(ExpectedConditions.visibilityOf(export)).getText().equalsIgnoreCase("Menu Import is processing now. Please check back in few minutes!."))
			{
				test.log(LogStatus.PASS, "Menu Uploaded / Exported Successfully");
			}
			else
			{
				test.log(LogStatus.INFO, "Menu Uploaded / Exported Failed");
			}
	}
	catch(Exception e) {}
			Thread.sleep(100000);
			Verify_Import_Export_Menus_New_Store a=new Verify_Import_Export_Menus_New_Store();
			a.verify_ExportedData_Departments(driver);
			a.verify_ExportedData_Coursing(driver);
			a.verify_ExportedData_Serving_Size_Levels(driver);
			a.verify_ExportedData_Tax(driver);
			a.verify_ExportedData_Categories(driver);
			a.verify_ExportedData_Sub_Categories(driver);
			a.verify_ExportedData_Modifiers(driver);
			a.verify_ExportedData_Modifier_Groups(driver);
		}
		
}
