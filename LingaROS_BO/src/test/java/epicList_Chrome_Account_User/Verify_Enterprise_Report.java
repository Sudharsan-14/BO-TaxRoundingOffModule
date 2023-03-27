package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome_Account_User.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Verify_Enterprise_Report {

	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_Enterprise_Report");

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
			Enterprise_Report_openpage(driver);
			Enterprise_Report_Stores(driver);
			Enterprise_Report_Group(driver);
			Enterprise_Report_City(driver);
			Enterprise_Report_State(driver);
			Enterprise_Report_ZipCode(driver);
			Thread.sleep(1500);
		}
	
	@Test(enabled=false,priority=2)
	public void Enterprise_Report_openpage(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		 FileInputStream fis = new FileInputStream(src);
				
		 XSSFWorkbook wb = new XSSFWorkbook(fis);
				
	     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
	     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(15000);
/*		//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();

		Thread.sleep(3000);
        //Click the EnterPrise Reports Option		
		driver.findElement(By.xpath("//span[.='EnterPrise Reports']")).click();
		
		Thread.sleep(2000);
		//Click the Sale option
		driver.findElement(By.xpath("//span[.='Sale']")).click();*/
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"reports");
		Thread.sleep(5000);
		try
		{
		//Check Consolidated Report page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1521, 1))).getText().equalsIgnoreCase("Consolidated Report"))
		{
			test.log(LogStatus.PASS, "Consolidated Report page loaded Successfully");
			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Consolidated Report page loaded Failed");
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
		Thread.sleep(3000);
		wb.close();
		
	}
	
	@Test(enabled=false,priority=4)
	public void Enterprise_Report_Stores(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		 FileInputStream fis = new FileInputStream(src);
				
		 XSSFWorkbook wb = new XSSFWorkbook(fis);
				
	     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
	     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(2000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

		Thread.sleep(3000);
		System.out.println("********************* View The Store Report *************************");
		test.log(LogStatus.INFO, "********************* View The Store Report *************************");
		//Click the Select option
		driver.findElement(By.xpath(excel.getData(3, 1522, 1))).click();
		//Remove the store name
		driver.findElement(By.xpath(excel.getData(3, 1523, 1))).sendKeys("Group");
		//Clear the required filter
		Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(3, 1523, 1))).sendKeys(Keys.ENTER);
		Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(3, 1522, 1))).click();
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 1523, 1))).sendKeys("Stores");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1523, 1))).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 1524, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 1525, 1))).sendKeys(Utility.getReportPropertyUser("Store"));
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1525, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(1500);
		//Click the Time Period Options
		driver.findElement(By.xpath(excel.getData(3, 1526, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 1527, 1))).sendKeys("Date Range");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1527, 1))).sendKeys(Keys.ENTER);
	
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1528, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1528, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1529, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1529, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		long start = System.currentTimeMillis();
    	
		try
		{
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 1530, 1)));
			
			//Check whether the Net sale is available or not
			if(wait1.until(ExpectedConditions.visibilityOf(ele11)).getText().equalsIgnoreCase(Utility.getReportPropertyUser("Store")))
			{
				test.log(LogStatus.PASS, "Enterprise Report is avilable for the selected Time Period");
				
				long finish = System.currentTimeMillis();
				long totalTime = finish- start; 
				System.out.println("Time in Millisecomds : "+totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime/1000)/6;
				System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//				test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
				
				//Get the rows count
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1531, 1)));
				rows.size();
				
				//Get the columns count
				List<WebElement> columns = driver.findElements(By.xpath(excel.getData(3, 1532, 1)));
				columns.size();
				
				for(int i = 2; i < rows.size() ; i++)
				{
					for(int j = 2; j <= columns.size() ; j++)
					{
						System.out.println(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+i+"]/td[1]")).getText()+" contain the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr[1]/th["+j+"]")).getText()+" Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td["+j+"]")).getText());
						test.log(LogStatus.INFO, driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+i+"]/td[1]")).getText()+" contain the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr[1]/th["+j+"]")).getText()+" Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td["+j+"]")).getText());
					
					
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				    
					
					}
				}
					
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Enterprise Report is not available");
	
	     	
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));

		
		}
				
		Thread.sleep(3000);
		System.out.println("********************* End of Store Report *************************");
		test.log(LogStatus.INFO, "********************* End of Store Report *************************");
		wb.close();
		
	}
	
	@Test(enabled=false,priority=5)
	public void Enterprise_Report_Group(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		 FileInputStream fis = new FileInputStream(src);
				
		 XSSFWorkbook wb = new XSSFWorkbook(fis);
				
	     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
	     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(3000);
		System.out.println("********************* View The Group Report *************************");
		test.log(LogStatus.INFO, "********************* View The Store Report *************************");
		
		//Click the Select option
		driver.findElement(By.xpath(excel.getData(3, 1522, 1))).click();
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 1523, 1))).sendKeys("Group");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1523, 1))).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 1524, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 1525, 1))).sendKeys(Utility.getReportPropertyUser("Group"));
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1525, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(1500);
		//Click the Time Period Options
		driver.findElement(By.xpath(excel.getData(3, 1526, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 1527, 1))).sendKeys("Date Range");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1527, 1))).sendKeys(Keys.ENTER);
	
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1528, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1528, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1529, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1529, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		
		long start = System.currentTimeMillis();
		
		try
		{
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 1530, 1)));
			
			//Check whether the Net sale is available or not
            if(wait1.until(ExpectedConditions.visibilityOf(ele11)).getText().equalsIgnoreCase(Utility.getReportPropertyUser("Store")))
			{
				test.log(LogStatus.PASS, "Enterprise Report is avilable for the selected Time Period");
				
				long finish = System.currentTimeMillis();
				long totalTime = finish- start; 
				System.out.println("Time in Millisecomds : "+totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime/1000)/6;
				System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//				test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
				
				//Get the rows count
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1531, 1)));
				rows.size();
				
				//Get the columns count
				List<WebElement> columns = driver.findElements(By.xpath(excel.getData(3, 1532, 1)));
				columns.size();
				
				for(int i = 2; i < rows.size() ; i++)
				{
					for(int j = 2; j <= columns.size() ; j++)
					{
						System.out.println(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+i+"]/td[1]")).getText()+" contain the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr[1]/th["+j+"]")).getText()+" Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td["+j+"]")).getText());
						test.log(LogStatus.INFO, driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+i+"]/td[1]")).getText()+" contain the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr[1]/th["+j+"]")).getText()+" Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td["+j+"]")).getText());
					
					
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				    
					
					}
				}
					
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Enterprise Report is not available");
	
		
	     	
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));

		
		}
				
		
		Thread.sleep(3000);
		System.out.println("********************* End of Group Report *************************");
		test.log(LogStatus.INFO, "********************* End of Group Report *************************");
		wb.close();
	}
	
	@Test(enabled=false,priority=6)
	public void Enterprise_Report_City(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		 FileInputStream fis = new FileInputStream(src);
				
		 XSSFWorkbook wb = new XSSFWorkbook(fis);
				
	     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
	     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(3000);
		System.out.println("********************* View The City Report *************************");
		test.log(LogStatus.INFO, "********************* View The City Report *************************");

		//Click the Select option
		driver.findElement(By.xpath(excel.getData(3, 1522, 1))).click();
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 1523, 1))).sendKeys("City");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1523, 1))).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 1524, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 1525, 1))).sendKeys(Utility.getReportPropertyUser("City"));
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1525, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(1500);
		//Click the Time Period Options
		driver.findElement(By.xpath(excel.getData(3, 1526, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 1527, 1))).sendKeys("Date Range");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1527, 1))).sendKeys(Keys.ENTER);
	
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1528, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1528, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1529, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1529, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		
		long start = System.currentTimeMillis();
		
		try
		{
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 1530, 1)));
			
			//Check whether the Net sale is available or not
			if(wait1.until(ExpectedConditions.visibilityOf(ele11)).getText().equalsIgnoreCase(Utility.getReportPropertyUser("Store")))
			{
				test.log(LogStatus.PASS, "Enterprise Report is avilable for the selected Time Period");
				
				long finish = System.currentTimeMillis();
				long totalTime = finish- start; 
				System.out.println("Time in Millisecomds : "+totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime/1000)/6;
				System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//				test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
				
				//Get the rows count
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1531, 1)));
				rows.size();
				
				//Get the columns count
				List<WebElement> columns = driver.findElements(By.xpath(excel.getData(3, 1532, 1)));
				columns.size();
				
				for(int i = 2; i < rows.size() ; i++)
				{
					for(int j = 2; j <= columns.size() ; j++)
					{
						System.out.println(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+i+"]/td[1]")).getText()+" contain the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr[1]/th["+j+"]")).getText()+" Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td["+j+"]")).getText());
						test.log(LogStatus.INFO, driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+i+"]/td[1]")).getText()+" contain the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr[1]/th["+j+"]")).getText()+" Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td["+j+"]")).getText());
					
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				    
					
					}
				}
					
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Enterprise Report is not available");
		
	      	
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));

			
		
		}
						
		Thread.sleep(3000);
		System.out.println("********************* End of City Report *************************");
		test.log(LogStatus.INFO, "********************* End of City Report *************************");
		wb.close();
	}
	
	@Test(enabled=false,priority=7)
	public void Enterprise_Report_State(WebDriver driver) throws Exception
	{

		Thread.sleep(2000);
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		 FileInputStream fis = new FileInputStream(src);
				
		 XSSFWorkbook wb = new XSSFWorkbook(fis);
				
	     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
	     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(3000);
		System.out.println("********************* View The State Report *************************");
		test.log(LogStatus.INFO, "********************* View The State Report *************************");

		//Click the Select option
		driver.findElement(By.xpath(excel.getData(3, 1522, 1))).click();
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 1523, 1))).sendKeys("State");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1523, 1))).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 1524, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 1525, 1))).sendKeys(Utility.getReportPropertyUser("State"));
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1525, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(1500);
		//Click the Time Period Options
		driver.findElement(By.xpath(excel.getData(3, 1526, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 1527, 1))).sendKeys("Date Range");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1527, 1))).sendKeys(Keys.ENTER);
	
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1528, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1528, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1529, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1529, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		long start = System.currentTimeMillis();
		
		try
		{
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 1530, 1)));
			
			//Check whether the Net sale is available or not
            if(wait1.until(ExpectedConditions.visibilityOf(ele11)).getText().equalsIgnoreCase(Utility.getReportPropertyUser("Store")))
			{
				test.log(LogStatus.PASS, "Enterprise Report is avilable for the selected Time Period");
				
				long finish = System.currentTimeMillis();
				long totalTime = finish- start; 
				System.out.println("Time in Millisecomds : "+totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime/1000)/6;
				System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//				test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
				
				//Get the rows count
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1531, 1)));
				rows.size();
				
				//Get the columns count
				List<WebElement> columns = driver.findElements(By.xpath(excel.getData(3, 1532, 1)));
				columns.size();
				
				for(int i = 2; i < rows.size() ; i++)
				{
					for(int j = 2; j <= columns.size() ; j++)
					{
						System.out.println(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+i+"]/td[1]")).getText()+" contain the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr[1]/th["+j+"]")).getText()+" Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td["+j+"]")).getText());
						test.log(LogStatus.INFO, driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+i+"]/td[1]")).getText()+" contain the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr[1]/th["+j+"]")).getText()+" Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td["+j+"]")).getText());
					
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				    
					
					}
				}
					
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Enterprise Report is not available");
	
			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
 
		
		}
				
		
		Thread.sleep(3000);
		System.out.println("********************* End of State Report *************************");
		test.log(LogStatus.INFO, "********************* End of State Report *************************");
		wb.close();
	
	}
	
	@Test(enabled=false,priority=8)
	public void Enterprise_Report_ZipCode(WebDriver driver) throws Exception
	{

		Thread.sleep(2000);
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		 FileInputStream fis = new FileInputStream(src);
				
		 XSSFWorkbook wb = new XSSFWorkbook(fis);
				
	     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
	     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		
		Thread.sleep(3000);
		System.out.println("********************* View The Zip Code Report *************************");
		test.log(LogStatus.INFO, "********************* View The Zip Code Report *************************");
		
		//Click the Select option
		driver.findElement(By.xpath(excel.getData(3, 1522, 1))).click();
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 1523, 1))).sendKeys("Zip Code");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1523, 1))).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 1524, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 1525, 1))).sendKeys(Utility.getReportPropertyUser("Zipcode"));
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1525, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(1500);
		//Click the Time Period Options
		driver.findElement(By.xpath(excel.getData(3, 1526, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 1527, 1))).sendKeys("Date Range");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 1527, 1))).sendKeys(Keys.ENTER);
	
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1528, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1528, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1529, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1529, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(3000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		long start = System.currentTimeMillis();
		
		try
		{
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 1530, 1)));
			
			//Check whether the Net sale is available or not
            if(wait1.until(ExpectedConditions.visibilityOf(ele11)).getText().equalsIgnoreCase(Utility.getReportPropertyUser("Store")))
			{
				test.log(LogStatus.PASS, "Enterprise Report is avilable for the selected Time Period");
				
				long finish = System.currentTimeMillis();
				long totalTime = finish- start; 
				System.out.println("Time in Millisecomds : "+totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime/1000)/6;
				System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//				test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
				
				//Get the rows count
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1531, 1)));
				rows.size();
				
				//Get the columns count
				List<WebElement> columns = driver.findElements(By.xpath(excel.getData(3, 1532, 1)));
				columns.size();
				
				for(int i = 2; i < rows.size() ; i++)
				{
					for(int j = 2; j <= columns.size() ; j++)
					{
						System.out.println(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+i+"]/td[1]")).getText()+" contain the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr[1]/th["+j+"]")).getText()+" Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td["+j+"]")).getText());
						test.log(LogStatus.INFO, driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+i+"]/td[1]")).getText()+" contain the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr[1]/th["+j+"]")).getText()+" Total is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[5]/table/tbody/tr["+rows.size()+"]/td["+j+"]")).getText());
				
				    	Thread.sleep(3000);
				    	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				    	
				    	String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

						Thread.sleep(3000);
						driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				    
					
					}
				}
					
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Enterprise Report is not available");
	
	       	
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));

		
		}
				
		
		Thread.sleep(3000);
		System.out.println("********************* End of Zip Code Report *************************");
		test.log(LogStatus.INFO, "********************* End of Zip Code Report *************************");
		if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);//watchTutorial(driver);
		Thread.sleep(5000);}
		wb.close();
	}
	@Test(priority=3, enabled=false)
	public void watchTutorial(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		//Click the Watch Tutorial Option
		driver.findElement(By.xpath("//span[.='Watch Tutorial']")).click();
		WebElement iframe = driver.findElement(By.xpath("//div[@class='modal-content']/span/iframe"));
		driver.switchTo().frame(iframe);
		Thread.sleep(3500);
		try
		{
			if(driver.findElement(By.xpath("//button/div[.='Watch later']")).isDisplayed()&&driver.findElement(By.xpath("//button/div[.='Share']")).isDisplayed())
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
		driver.findElement(By.xpath("//span[.='x' and @title='close']")).click();
	}
}
