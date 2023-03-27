package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Tax_Round_Off_Validation_Enterprise_Level_User {
	
	public WebDriver driver;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Tax Round Off Validation BO-Enterprise Reports (Account User Level)"); 
	
	float unknownValue = 00;
	 float gobal_Total_Tax_Amount = Tax_Round_Off_Validation_Account_Level_User.gobal_Total_Tax_Amount;
	 float gobal_Total_Tax_Exempt = Tax_Round_Off_Validation_Account_Level_User.gobal_Total_Tax_Exempt;
	 float gobal_actual_Tax_RoundOff = Tax_Round_Off_Validation_Account_Level_User.gobal_actual_Tax_RoundOff;
	 String string_Value_Of_tax_Exempt = Tax_Round_Off_Validation_Account_Level_User.string_Value_Of_tax_Exempt;
	 String string_Value_Of_Tax_Amount = Tax_Round_Off_Validation_Account_Level_User.string_Value_Of_Tax_Amount;
	 String string_Value_Of_tax_Round_Off_Value = Tax_Round_Off_Validation_Account_Level_User.string_Value_Of_tax_Round_Off_Value;
	 float gobal_Tax_Amount_Web = Tax_Round_Off_Validation_Account_Level_User.gobal_Tax_Amount_Web;
	
	@AfterMethod
	public void flushTest() throws Exception {
		Thread.sleep(2000);
		rep.endTest(test);
		rep.flush();
		Thread.sleep(15000);
		// SendMail_Reports.snedMailWithAttachment();
		Thread.sleep(15000);
	}
	
	@Test(priority = 1)
	public void login() throws Exception {
		Thread.sleep(2000);
		// Call the chrome driver
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

	@Test(priority = 3)
	public void Start_Reports() {
		test.log(LogStatus.INFO, "-------Tax report Validation Reports Started (Tax Round-Off Validation)----");
	}

	@Test(priority = 100)
	public void calling(WebDriver driver) throws Exception {
		
//		Thread.sleep(1000);
//		Tax_Report_Method_viewPage(driver);
//		Tax_Report_Method_verify(driver);
		Thread.sleep(4000);
	Enterprise_Report_openpage(driver);
//	Enterprise_Report_Tax_Validation_Stores(driver);
	Enterprise_Department_Report_Tax_Validation_Stores(driver);
	Enterprise_Category_Report_Tax_Validation_Stores(driver);
//	Enterprise_Sub_Category_Report_Tax_Validation_Stores(driver);
	Enterprise_Menu_Item_Sale_Report_Tax_Validation_Stores(driver);
//	Enterprise_Modifier_Sale_Report_Tax_Validation_Stores(driver);
	Enterprise_Hourly_Sale_Report_Tax_Validation_Stores(driver);
	Enterprise_Daily_Sale_Report_Tax_Validation_Stores(driver);
	Enterprise_SaleRecap_Report_Tax_Validation_Stores(driver);
	Enterprise_Cashier_Out_Report_Tax_Validation_Stores(driver);
	Enterprise_Sale_Summary_Report_Tax_Validation_Stores(driver);

	Thread.sleep(5000);
	}
	
	@Test(enabled = false, priority = 22)
	public void Tax_Report_Method_viewPage(WebDriver driver) throws Exception {

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(3000);
		// Enter the Users URL
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "taxReport");

		try {
			Thread.sleep(5000);
			// Check Customer Preference Report page opened or not
			if (driver.findElement(By.xpath(excel.getData(1, 1059, 1))).getText().equalsIgnoreCase("Tax Report")) {
				test.log(LogStatus.PASS, "Tax Report page loaded Successfully");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			} else {
				test.log(LogStatus.FAIL, "Tax Report page loaded Failed");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
		} catch (Exception e) {
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.FAIL, test.addScreenCapture(s));
		}
		Thread.sleep(3000);
		wb.close();

	}

	@Test(enabled = false, priority = 23)
	public void Tax_Report_Method_verify(WebDriver driver) throws Exception {

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(5000);
		// Select the Time Period option
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
		// Enter the required Period of time for Specific date
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys("Date Range");
		// Press the Enter key
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		Thread.sleep(3000);
		// Clear the Date field
		driver.findElement(By.xpath("//input[@ng-model='query.fromDate']/.")).clear();
		// Enter the required date
		driver.findElement(By.xpath("//input[@ng-model='query.fromDate']/."))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		Thread.sleep(3000);
		// Clear the Date field
		driver.findElement(By.xpath("//input[@ng-model='query.toDate']/.")).clear();
		// Enter the required date
		driver.findElement(By.xpath("//input[@ng-model='query.toDate']/."))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

		// Click the Run
		driver.findElement(By.cssSelector(excel.getData(1, 974, 4))).click();
		Thread.sleep(8000);

		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		Thread.sleep(8000);
		
		List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 825, 1)));

		// Check weather the report is available for the selected time period
		if (rows.size() == 2)

		{
			test.log(LogStatus.FAIL, "Tax is not available for Specific Date");

			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.INFO, test.addScreenCapture(s));

		}

		else if (driver.findElement(By.xpath(excel.getData(1, 1064, 1))).getText() != null) {
			
			Thread.sleep(3000);
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);

			test.log(LogStatus.PASS, "Tax is available for Specific Date");

			String Tax_Amount = driver.findElement(By.xpath("//tr[contains(.,'Total')]/th[4]")).getText();

			String actualText_Tax_Amount = Tax_Amount.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

			this.string_Value_Of_Tax_Amount = actualText_Tax_Amount;

			float total_Tax_Amount = Float.parseFloat(actualText_Tax_Amount);

			this.gobal_Total_Tax_Amount = total_Tax_Amount;

			System.out.println("Total Tax Amount is : "
					+ driver.findElement(By.xpath("//tr[contains(.,'Total')]/th[4]")).getText());

			test.log(LogStatus.INFO, "Total Tax Amount is : "
					+ driver.findElement(By.xpath("//tr[contains(.,'Total')]/th[4]")).getText());

			String tax_Exempt = driver.findElement(By.xpath("//tr[contains(.,'Total')]/th[5]")).getText();

			String actualText_tax_Exempt = tax_Exempt.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

			this.string_Value_Of_tax_Exempt = actualText_tax_Exempt;

			float total_Tax_Exempt = Float.parseFloat(actualText_tax_Exempt);

			this.gobal_Total_Tax_Exempt = total_Tax_Exempt;

			System.out.println("Total Tax Exempt is : "
					+ driver.findElement(By.xpath("//tr[contains(.,'Total')]/th[5]")).getText());

			test.log(LogStatus.INFO, "Total Tax Exempt is : "
					+ driver.findElement(By.xpath("//tr[contains(.,'Total')]/th[5]")).getText());

			
			String Tax_Round_off_Amount = driver.findElement(By.xpath("//tr[contains(.,'Rounding Off')]/td[4]")).getText();

			String actualText_Tax_Round_off_Amount = Tax_Round_off_Amount.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

			this.string_Value_Of_tax_Round_Off_Value = actualText_Tax_Round_off_Amount;

			float total_Tax_Amount_Round_off_Amount = Float.parseFloat(actualText_Tax_Round_off_Amount);

			this.gobal_actual_Tax_RoundOff = total_Tax_Amount_Round_off_Amount;

			System.out.println("Total Tax Amount is : "
					+ driver.findElement(By.xpath("//tr[contains(.,'Rounding Off')]/td[4]")).getText());

			test.log(LogStatus.INFO, "Total Tax Amount is : "
					+ driver.findElement(By.xpath("//tr[contains(.,'Rounding Off')]/td[4]")).getText());
			
		
			
			Thread.sleep(3000);
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);

			Thread.sleep(3000);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);


			String scnShot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s1 = "data:image/png;base64," + scnShot1;
			test.log(LogStatus.INFO, test.addScreenCapture(s1));

		}
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
		
		Thread.sleep(5000);	
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
	public void Enterprise_Report_Tax_Validation_Stores(WebDriver driver) throws Exception
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
		
		Thread.sleep(15000);	
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"reports");
		
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
		Thread.sleep(15000);
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
				List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr//td[7]"));
				rows.size();
				
				//Get the columns count
				List<WebElement> columns = driver.findElements(By.xpath(excel.getData(3, 1532, 1)));
				columns.size();
				
				Thread.sleep(1000);
				String Net_Sale_Amount = driver.findElement(By.xpath("//table//tbody//tr["+rows.size()+"]//td[7]")).getText().substring(1);
				
				float float1 = Float.parseFloat(Net_Sale_Amount);
				
				 // Replace all commo's with empty space
				String expected1 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replace(",", "");

				// Convert the String value of the Sale_Report_Net_Sale element into float value
				float expect1 = Float.parseFloat(expected1);
			    
				test.log(LogStatus.INFO, "The total value of the sale amoount in online sale: "+float1);
				
				if (float1 == expect1) {
					
					test.log(LogStatus.PASS,
							"Actual and Expected sale amount value in reports are same in enterprise report");
					String actualText1 = driver.findElement(By.xpath("//table//tbody//tr["+rows.size()+"]//td[7]")).getText().substring(1);
					
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					
					float float3 = Float.parseFloat(actualText);
					
					test.log(LogStatus.INFO,
							"Actual sale amount value in reports are same in enterprise report :"+ float3);
					
					
				} else if (expect1 == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Check Count
					String actualText = driver.findElement(By.xpath(
							"//table//tbody//tr["+rows.size()+"]//td[7]"))
							.getText();

					System.out.println("The Actual Net Transaction Date value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Net Transaction Date value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL,
							"Actual and Expected Sale Summary reports are different for Net Transaction Date");

					// Get the Total value of Transaction Date
					String actualText1 = driver.findElement(By.xpath(
							"//table//tbody//tr["+rows.size()+"]//td[7]"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the net Transaction Date Total element into float
					// value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - expect1;

					// Print the different value
					System.out.println("Net Transaction Date Value different is : " + different);

					test.log(LogStatus.FAIL, "Net Transaction Date Value different is : " + different);
				}
				
				// Check weather the Net Sales total is same or not
				if (driver.findElement(By.xpath("//table//tbody//tr["+rows.size()+"]//td[7]")).getText()
						.equals(string_Value_Of_Tax_Amount)) {
					test.log(LogStatus.PASS, "Actual and Expected Total tax Values are same");

					// Get the Total value of Total tax
					String actualText1 = driver.findElement(By.xpath("//table//tbody//tr["+rows.size()+"]//td[7]")).getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Net Sales element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Net Sales Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Net Sales Value is : " + actual);
				}

				else if (gobal_Total_Tax_Amount == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Net Sales
					String actualText = driver.findElement(By.xpath("//table//tbody//tr["+rows.size()+"]//td[7]")).getText();

					System.out.println("The Actual Net Sales value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Net Sales value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Expected Net Sales values are different");

					// Get the Total value of Gross Sales
					String actualText1 = driver.findElement(By.xpath("//table//tbody//tr["+rows.size()+"]//td[7]")).getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Net Sales Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - gobal_Total_Tax_Amount;

					// Print the different value
					System.out.println("Net Sales Value different is : " + different);

					test.log(LogStatus.FAIL, "Net Sales Value different is : " + different);
				}
				
			}
			 else
				{
					test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date");
			
			    	
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

				}
	
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Enterprise Report is not available");
	
	     	
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));

		
		}
		
	}
		



	
	@Test(enabled=false,priority=4)
	public void Enterprise_Department_Report_Tax_Validation_Stores(WebDriver driver) throws Exception
	{
	
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis); 
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	  
	/*	//Click the My Stores option'
		driver.findElement(By.xpath("//span[.='My Stores']")).click();

		Thread.sleep(5000);
        //Click the EnterPrise Reports Option		
		driver.findElement(By.xpath("//span[.='EnterPrise Reports']")).click();
		
		Thread.sleep(2000);
		//Click the Sale option
		driver.findElement(By.xpath("//span[.='Sale']")).click(); */
		
		Thread.sleep(2000);
		//Enter the URl
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/departmentSaleSummary");
		
		Thread.sleep(5000);
		try
		{
		//Check Department Report page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 693, 1))).getText().equalsIgnoreCase("Department"))
		{
			test.log(LogStatus.PASS, "Department Sale Report page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Department Sale Report page loaded Failed");
		
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
		Thread.sleep(5000);
		
		Thread.sleep(2000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

		Thread.sleep(5000);
		System.out.println("********************* View The Store Report *************************");
		test.log(LogStatus.INFO, "********************* View The Store Report *************************");
		//Click the Select option
		driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Group");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click the Select option
		driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Stores");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);

		
		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
		//Remove the store name
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[1]/div[2]/div/ul/li[1]/a")).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("Store"));
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(1500);
		//Click the Department Options
		driver.findElement(By.xpath(excel.getData(3, 698, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 699, 1))).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 699, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(1500);
		//Click the Time Period Options
		driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
	
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		
		long start = System.currentTimeMillis();
		
		try
		{
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 705, 1)));
	      
			//Check weather the report is available for the selected time period
			if(!driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Department Sale Report available for Specific Date");
				
				
				//Check Weather the Top 5 department sale available or not
				if(wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Here Top 5 Department Sale Report available for Specific Date");
					
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 711, 3))).isDisplayed())
					{								
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(30000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        	
			        	Thread.sleep(3000);
			        	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			        	
			        	
			        	 //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Sale Amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Sale Amount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Sale Amount Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Sale Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Sale Amount Value different is : "+different);	
				        }
				        
				      //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText().equals(string_Value_Of_Tax_Amount))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	

				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(gobal_Total_Tax_Amount == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - gobal_Total_Tax_Amount;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
	
				}
					 else
						{
							test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date");
					
					    	
							String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
							String s="data:image/png;base64,"+scnShot;
							test.log(LogStatus.INFO,test.addScreenCapture(s));

						}
	
				}
			}
		}
		catch(Exception e)
		{

			test.log(LogStatus.FAIL, "Department Sale Report is not available for Specific Date");
	   
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
	    
		}
				
		Thread.sleep(5000);
		System.out.println("********************* End of Store Report *************************");
		test.log(LogStatus.INFO, "********************* End of Store Report *************************");wb.close();

		
	}
	
	@Test(enabled=false,priority=4)
	public void Enterprise_Category_Report_Tax_Validation_Stores(WebDriver driver) throws Exception
	{
		
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	  
	/*	//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();

		Thread.sleep(5000);
        //Click the EnterPrise Reports Option		
		driver.findElement(By.xpath("//span[.='EnterPrise Reports']")).click();
		
		Thread.sleep(2000);
		//Click the Sale option
		driver.findElement(By.xpath("//span[.='Sale']")).click();  */
		
		Thread.sleep(2000);
		//Enter the URl
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/categorySaleSummary");

		Thread.sleep(5000);
		try
		{
		//Check Category Report page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 718, 1))).getText().equalsIgnoreCase("Category"))
		{
			test.log(LogStatus.PASS, "Category Sale Report page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Category Sale Report page loaded Failed");
		
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
		Thread.sleep(5000);
		
		Thread.sleep(2000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

		Thread.sleep(5000);
		System.out.println("********************* View The Store Report *************************");
		test.log(LogStatus.INFO, "********************* View The Store Report *************************");
		//Click the Select option
		driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Group");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1500);
		//Click the Select option
		driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Stores");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
		//Remove the store name
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[1]/div[2]/div/ul/li[1]/a")).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("Store"));
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(1500);
		//Click the Category Options
		driver.findElement(By.xpath(excel.getData(3, 719, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 720, 1))).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 720, 1))).sendKeys(Keys.ENTER);

		//Check whether the Split by menu item is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 721, 1))).isSelected())
		{
			driver.findElement(By.xpath(excel.getData(3, 721, 1))).click();
		}
		else
		{
			
		}
		
		Thread.sleep(1500);
		//Click the Time Period Options
		driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
	
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
	
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		long start = System.currentTimeMillis();
		
		try
		{
			Thread.sleep(3000);
			//Check weather the report is available for the selected time period
			if(!driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Department Sale Report available for Specific Date");
				
				WebDriverWait wait1=new WebDriverWait(driver, 30);
				WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 705, 1)));
				
				//Check Weather the Top 5 department sale available or not
				if(wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Here Top 5 Department Sale Report available for Specific Date");
					
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 711, 1))).isDisplayed())
					{								
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(30000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
				        
		/*	        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
			        	
			        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
			        	float expect1 = Float.parseFloat(expected1);  
			        	
			        	Thread.sleep(3000);
			        	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		
			        	 //Check weather the Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Sale Amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Sale Amount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Sale Amount Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Sale Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Sale Amount Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText().equals(string_Value_Of_Tax_Amount))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	

				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(gobal_Total_Tax_Amount == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[4]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - gobal_Total_Tax_Amount;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
		
					}
					 else
						{
							test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date");
					
					    	
							String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
							String s="data:image/png;base64,"+scnShot;
							test.log(LogStatus.INFO,test.addScreenCapture(s));

						}
				}
			}
		}
		catch(Exception e)
		{

			test.log(LogStatus.FAIL, "Department Sale Report is not available for Specific Date");

			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));

		
		}
				
		Thread.sleep(5000);
		System.out.println("********************* End of Store Report *************************");
		test.log(LogStatus.INFO, "********************* End of Store Report *************************");wb.close();

	}
	
	@Test(enabled=false,priority=4)
	public void Enterprise_Sub_Category_Report_Tax_Validation_Stores(WebDriver driver) throws Exception
	{
	
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		 FileInputStream fis = new FileInputStream(src);
				
		 XSSFWorkbook wb = new XSSFWorkbook(fis);
				
	     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
	     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	     
	/*	//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();

		Thread.sleep(5000);
       //Click the EnterPrise Reports Option		
		driver.findElement(By.xpath("//span[.='EnterPrise Reports']")).click();
		
		Thread.sleep(2000);
		//Click the Sale option
		driver.findElement(By.xpath("//span[.='Sale']")).click();		*/
		
		Thread.sleep(2000);
		//Enter the URl
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/subCategorySaleSummary");

		Thread.sleep(5000);
		try
		{
		//Check Sub Category Report page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 728, 1))).getText().equalsIgnoreCase("Sub Category"))
		{
			test.log(LogStatus.PASS, "Sub Category Sale Report page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Sub Category Sale Report page loaded Failed");
		
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
		Thread.sleep(5000);
		
		Thread.sleep(2000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

		Thread.sleep(5000);
		System.out.println("********************* View The Store Report *************************");
		test.log(LogStatus.INFO, "********************* View The Store Report *************************");
		//Click the Select option
		driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Group");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1500);
		//Click the Select option
		driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Stores");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);

		
		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
		//Remove the Sub Category Options
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[1]/div[2]/div/ul/li[1]/a")).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("Store"));
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(1500);
		//Click the Sub Category Options
		driver.findElement(By.xpath(excel.getData(3, 729, 1))).click();
	
		
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 730, 1))).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 730, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(1500);
		//Click the Time Period Options
		driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
	
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		
		long start = System.currentTimeMillis();
		
		WebDriverWait wait1=new WebDriverWait(driver, 30);
		WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
		
		
		try
		{
			
			//Check weather the report is available for the selected time period
			if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
			{
				test.log(LogStatus.PASS, "Sub Category Sale Report available for Specific Date");
				
				long finish = System.currentTimeMillis();
				long totalTime = finish- start; 
				System.out.println("Time in Millisecomds : "+totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime/1000)/6;
				System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//				test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
	
				Thread.sleep(5000);
				//Check weather the table format report is available or not
				if(driver.findElement(By.xpath(excel.getData(3, 711, 1))).isDisplayed())
				{								
					test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
					
					Thread.sleep(30000);
			        //No.of rows
			        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
			        
	/*	        //Print number of Rows
			        System.out.println("Number of Rows are : "+rows);*/
			        
		        	
		        	//Replace all commo's with empty space
		        	String expected1 = Utility.getReportPropertyUser("Sub_Category_Sale_Report_Sale_Amount").replace(",", "");
		        	
		        	//Convert the String value of the Sub_Category_Sale_Report_Sale_Amount element into float value
		        	float expect1 = Float.parseFloat(expected1); 
		        	
		        	//Replace all commo's with empty space
		        	String expected3 = Utility.getReportPropertyUser("Sub_Category_Sale_Report_Tax").replace(",", "");
		        	
		        	//Convert the String value of the Sub_Category_Sale_Report_Tax element into float value
		        	float expect31 = Float.parseFloat(expected3);  
		        	
		        	Thread.sleep(3000);
		        	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
	
		        	//Check weather the Sale Amount Report is correct or not
			        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sub_Category_Sale_Report_Sale_Amount")))
			        {
			        	test.log(LogStatus.PASS, "Actual and Expected Sub Category Sale reports are same for Sale Amount");
			        	
			        	//Get the Total value of Sale Amount
			        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Sale Amount Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        	
			        	//Print the actual value
			        	System.out.println("The Actual Sale Amount Value is : "+actual);
			        	
			        	test.log(LogStatus.PASS, "The Actual Sale Amount Value is : "+ actual);
			        }
			        
					else if(expect1 == unknownValue)
					{
						test.log(LogStatus.PASS, "Here we don't have the exact expected value");
						
			        	//Get the Total value of Check Count
			        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

			        	System.out.println("The Actual Sale Amount value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.FAIL, "Actual and Expected Sub Category Sale reports are different for Sale Amount");
			        	
			        	//Get the Total value of Sale Amount
			        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the sale amount Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        			        	
			        	//Get the different
			        	float different = actual - expect1;
			        	
			        	//Print the different value
			        	System.out.println("Sale Amount Value different is : "+different);
			        	
			        	test.log(LogStatus.FAIL, "Sale Amount Value different is : "+different);
			        	
			        	 //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText().equals(Utility.getReportPropertyUser("Sub_Category_Sale_Report_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sub Category Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText2 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText3= actualText2.replace(",", "");   	

				        	//Convert the String value of the Tax Total element into float value
				        	float actual1 = Float.parseFloat(actualText3);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual1);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual1);
				        }
				        
						else if(expect31 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Tax
				        	String actualText4 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText4);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText4);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sub Category Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText12 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText13 = actualText12.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual12 = Float.parseFloat(actualText13);
				        			        	
				        	//Get the different
				        	float different1 = actual12 - expect31;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
			        	
			        }
	
				}
				
			}
		}
		catch(Exception e)
		{

			test.log(LogStatus.FAIL, "Sub Category Sale Report is not available for Specific Date");
			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));


		}
				
		Thread.sleep(5000);
		System.out.println("********************* End of Store Report *************************");
		test.log(LogStatus.INFO, "********************* End of Store Report *************************");wb.close();

	}
	
	@Test(enabled=false,priority=4)
	public void Enterprise_Menu_Item_Sale_Report_Tax_Validation_Stores(WebDriver driver) throws Exception
	{
		
		
		Thread.sleep(8000);
		//Enter the URl
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/menuItemSaleSummary");
		
		Thread.sleep(5000);
		try
		{
		//Check Menu Item Report page opened or not
		if(driver.findElement(By.xpath("//label[.='Menu Item']")).getText().equalsIgnoreCase("Menu Item"))
		{
			test.log(LogStatus.PASS, "Menu Item Sale Report page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Menu Item Sale Report page loaded Failed");
		
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
		Thread.sleep(5000);
	
	
		Thread.sleep(2000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

		Thread.sleep(5000);
		System.out.println("********************* View The Store Report *************************");
		test.log(LogStatus.INFO, "********************* View The Store Report *************************");

		//Click the Select option
		driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/a")).click();	
		//Enter the required filter
		driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys("Group");
		//Press the Enter button
		driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1500);
		//Click the Select option
		driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/a")).click();	
		//Enter the required filter
		driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys("Stores");
		//Press the Enter button
		driver.findElement(By.xpath("//select[@ng-model='query.option']/../div/div/div/input")).sendKeys(Keys.ENTER);

		
		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath("//select[@name='stores']/../div/ul")).click();
		//Remove the store name
		//driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li[1]/a")).click();
		//Enter the required option
		driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Utility.getReportPropertyUser("Store"));
		//Press the Enter button
		driver.findElement(By.xpath("//select[@name='stores']/../div/ul/li/input")).sendKeys(Keys.ENTER);
	
		Thread.sleep(1500);
		//Click the Category Options
		driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/a")).click();
		//Enter the required option
		driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath("//select[@ng-model='query.selectedCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

//		Thread.sleep(1500);
//		//Click the Sub Category Options
//		driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/a")).click();
//		//Enter the required option
//		driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys("All");
//		//Press the Enter button
//		driver.findElement(By.xpath("//select[@ng-model='query.selectedSubCatName']/../div/div/div/input")).sendKeys(Keys.ENTER);

//		Thread.sleep(1500);
//		//Click the Menu Item Options
//		driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/a")).click();
//		//Enter the required option
//		driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys("All");
//		//Press the Enter button
//		driver.findElement(By.xpath("//select[@ng-model='query.selectedMenuID']/../div/div/div/input")).sendKeys(Keys.ENTER);

		Thread.sleep(1500);
		//Click the Serving Size level Options
		driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/a")).click();
		//Enter the required option
		driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath("//select[@ng-model='query.selectedServingSizeID']/../div/div/div/input")).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		//Check whether the Split by serving size level option is enabled or not
		if(driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).isSelected())
		{
			//Enable or disable the Split by Serving Size Level
			driver.findElement(By.xpath("//label[contains(.,'Split By ServingLevel')]/../div/input")).click();
		}
		else
		{
			
		}
		
		Thread.sleep(3000);
		//Click the Menu Item Options
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/a")).click();
		//Enter the required option
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys("All");
		//Press the Enter button
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[7]/div/div/div/div/input")).sendKeys(Keys.ENTER);

		Thread.sleep(1500);
		//Click the Time Period Options
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
		//Enter the required option
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys("Date Range");
		//Press the Enter button
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input")).sendKeys(Keys.ENTER);
	
		Thread.sleep(1500);
		//Clear the date field
		driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		Thread.sleep(1500);
		//Clear the date field
		driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1500);
		//Click the Run button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		long start = System.currentTimeMillis();
		
		WebDriverWait wait1=new WebDriverWait(driver, 30);
		WebElement ele11=driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));
		
		try
		{
			
			//Check weather the report is available for the selected time period
			if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
			{
				test.log(LogStatus.PASS, "Menu Item Sale Report available for Specific Date");
				
				long finish = System.currentTimeMillis();
				long totalTime = finish- start; 
				System.out.println("Time in Millisecomds : "+totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime/1000)/6;
				System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//				test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
				
				Thread.sleep(5000);
				//Check weather the table format report is available or not
				if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table")).isDisplayed())
				{								
					test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
					
					Thread.sleep(30000);
			        //No.of rows
			        List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr"));
			        
	/*	        //Print number of Rows
			        System.out.println("Number of Rows are : "+rows);*/
			        
		        	
		        	//Replace all commo's with empty space
		        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replace(",", "");
		        	
		        	//Convert the String value of the Sale_Report_Sale_Amount element into float value
		        	float expect1 = Float.parseFloat(expected1);  
		        	
		        	Thread.sleep(3000);
		        	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
	
	
		        	 //Check weather the Sale Amount Report is correct or not
			        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount")))
			        {
			        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Sale Amount");
			        	
			        	//Get the Total value of Sale Amount
			        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Sale Amount Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        	
			        	//Print the actual value
			        	System.out.println("The Actual Sale Amount Value is : "+actual);
			        	
			        	test.log(LogStatus.PASS, "The Actual Sale Amount Value is : "+ actual);
			        }
			        
					else if(expect1 == unknownValue)
					{
						test.log(LogStatus.PASS, "Here we don't have the exact expected value");
						
			        	//Get the Total value of Check Count
			        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

			        	System.out.println("The Actual Sale Amount value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Sale Amount");
			        	
			        	//Get the Total value of Sale Amount
			        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the sale amount Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        			        	
			        	//Get the different
			        	float different = actual - expect1;
			        	
			        	//Print the different value
			        	System.out.println("Sale Amount Value different is : "+different);
			        	
			        	test.log(LogStatus.FAIL, "Sale Amount Value different is : "+different);	
			        }
			        
			        //Check weather the Tax Report is correct or not
			        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText().equals(string_Value_Of_Tax_Amount))
			        {
			        	test.log(LogStatus.PASS, "Actual and Expected Menu Item Sale reports are same for Tax");
			        	
			        	//Get the Total value of Tax
			        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");   	

			        	//Convert the String value of the Tax Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        	
			        	//Print the actual value
			        	System.out.println("The Actual Tax Value is : "+actual);
			        	
			        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
			        }
			        
					else if(gobal_Total_Tax_Amount == unknownValue)
					{
						test.log(LogStatus.PASS, "Here we don't have the exact expected value");
						
			        	//Get the Total value of Tax
			        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();

			        	System.out.println("The Actual Tax value is : "+actualText);		 
			        	
			        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.FAIL, "Actual and Expected Menu Item Sale reports are different for Tax");
			        	
			        	//Get the Total value of Tax
			        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Tax')]/span")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Tax Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        			        	
			        	//Get the different
			        	float different = actual - gobal_Total_Tax_Amount;
			        	
			        	//Print the different value
			        	System.out.println("Tax Value different is : "+different);
			        	
			        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
			        }
	
				}
				
				 else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date");
				
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
	
			}
		}
		catch(Exception e)
		{

			test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for Specific Date");

			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));

		
		}
				
		Thread.sleep(5000);
		System.out.println("********************* End of Store Report *************************");
		test.log(LogStatus.INFO, "********************* End of Store Report *************************");

	}
	
	@Test(enabled=false,priority=4)
	public void Enterprise_Modifier_Sale_Report_Tax_Validation_Stores(WebDriver driver) throws Exception
	{
	
		Thread.sleep(10000);
		//Enter the URl
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/modifierSaleSummary");		
		Thread.sleep(5000);
		try
		{
		//Check Modifier Report page opened or not
		if(driver.findElement(By.xpath("//label[.='Modifier']")).getText().equalsIgnoreCase("Modifier"))
		{
			test.log(LogStatus.PASS, "Modifier Sale Report page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Modifier Sale Report page loaded Failed");
		
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
		Thread.sleep(5000);
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
		Thread.sleep(2000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

		Thread.sleep(5000);
		System.out.println("********************* View The Store Report *************************");
		test.log(LogStatus.INFO, "********************* View The Store Report *************************");
		//Click the Select option
		driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Group");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(1500);
		//Click the Select option
		driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Stores");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);

		
		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
		//Remove the store name
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/form/div[1]/div[2]/div/ul/li[1]/a")).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("Store"));
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(1500);
		//Click the Modifier Options
		driver.findElement(By.xpath(excel.getData(3, 2232, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 2233, 1))).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 2233, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(1500);
		//Click the Time Period Options
		driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
	
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		long start = System.currentTimeMillis();
    	
		WebDriverWait wait1=new WebDriverWait(driver, 30);
		WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
		
		try
		{
			
			//Check weather the report is available for the selected time period
			if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
			{
				test.log(LogStatus.PASS, "Modifier Sale Report available for Specific Date");
				
				long finish = System.currentTimeMillis();
				long totalTime = finish- start; 
				System.out.println("Time in Millisecomds : "+totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime/1000)/6;
				System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//				test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
	
				Thread.sleep(5000);
				//Check weather the table format report is available or not
				if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
					
					Thread.sleep(30000);
			        //No.of rows
			        List<WebElement> rows = driver.findElements(By.xpath ("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr"));
			        
	/*		        //Print number of Rows
			        System.out.println("Number of Rows are : "+rows);*/
			        
		        	//Replace all commo's with empty space
		        	String expected1 = Utility.getReportPropertyUser("Modifier_Sale_Report_Sale_Amount").replace(",", "");
		        	
		        	//Convert the String value of the Modifier_Sale_Report_Sale_Amount element into float value
		        	float expect1 = Float.parseFloat(expected1);  
		        	
		        	//Replace all commo's with empty space
		        	String expected3 = Utility.getReportPropertyUser("Modifier_Sale_Report_Tax").replace(",", "");
		        	
		        	//Convert the String value of the Modifier_Sale_Report_Tax element into float value
		        	float expect3 = Float.parseFloat(expected3); 
		        	
		        	Thread.sleep(3000);
		        	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
	
		        	//Check weather the Sale Amount Report is correct or not 
			        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Modifier_Sale_Report_Sale_Amount")))
			        {
			        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Sale Amount");
			        	
			        	//Get the Total value of Sale Amount
			        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Sale Amount Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        	
			        	//Print the actual value
			        	System.out.println("The Actual Sale Amount Value is : "+actual);
			        	
			        	test.log(LogStatus.PASS, "The Actual Sale Amount Value is : "+ actual);
			        }
			        
					else if(expect1 == unknownValue)
					{
						test.log(LogStatus.PASS, "Here we don't have the exact expected value");
						
			        	//Get the Total value of Sale Amount
			        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

			        	System.out.println("The Actual Sale Amount value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Sale Amount value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Sale Amount");
			        	
			        	//Get the Total value of Sale Amount
			        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the sale amount Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        			        			        	
			        	//Get the different
			        	float different = actual - expect1;
			        	
			        	//Print the different value
			        	System.out.println("Sale Amount Value different is : "+different);
			        	
			        	test.log(LogStatus.FAIL, "Sale Amount Value different is : "+different);	
			        }
			        
			      //Check weather the Tax Report is correct or not
			        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText().equals(Utility.getReportPropertyUser("Modifier_Sale_Report_Tax")))
			        {
			        	test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Tax");
			        	
			        	//Get the Total value of Tax
			        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");   	

			        	//Convert the String value of the Tax Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        	
			        	//Print the actual value
			        	System.out.println("The Actual Tax Value is : "+actual);
			        	
			        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
			        }
			        
					else if(expect3 == unknownValue)
					{
						test.log(LogStatus.PASS, "Here we don't have the exact expected value");
						
			        	//Get the Total value of Percentage of Sale
			        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();

			        	System.out.println("The Actual Tax value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Tax");
			        	
			        	//Get the Total value of Tax
			        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[5]")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Tax Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        			        	
			        	//Get the different
			        	float different = actual - expect3;
			        	
			        	//Print the different value
			        	System.out.println("Tax Value different is : "+different);
			        	
			        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
			        }
	
				}
				 else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date");
				
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					}
	
			}
		}catch(Exception e)
		{

			test.log(LogStatus.FAIL, "Modifier Sale Report is not available for Specific Date");

			
	    	
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));


		}
				
		Thread.sleep(5000);
		System.out.println("********************* End of Store Report *************************");
		test.log(LogStatus.INFO, "********************* End of Store Report *************************");

	}
	
	@Test(enabled=false,priority=4)
	public void Enterprise_Hourly_Sale_Report_Tax_Validation_Stores(WebDriver driver) throws Exception
	{
	
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(15000);
/*			//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();

		Thread.sleep(3000);
     //Click the EnterPrise Reports Option		
		driver.findElement(By.xpath("//span[.='EnterPrise Reports']")).click();
		
		Thread.sleep(2000);
		//Click the Sale option
		driver.findElement(By.xpath("//span[.='Sale']")).click();
		
		Thread.sleep(2000);
		//Click the Hourly Sale option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/div/scrollable-tabset/div/div/div/ul/li[7]/a/uib-tab-heading/span")).click();
*/			
		Thread.sleep(2000);
		//Enter the URl
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/hourlySaleSummary");

		Thread.sleep(5000);
		try
		{
		//Check Hourly Report page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 796, 1))).getText().equalsIgnoreCase("Hourly Sale"))
		{
			test.log(LogStatus.PASS, "Hourly Sale Report page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Hourly Sale Report page loaded Failed");
		
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
		driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Group");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1500);
		//Click the Select option
		driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Stores");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);

		
		Thread.sleep(5000);
		//Click the Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();	
		//Remove the Select Some Options
		//driver.findElement(By.xpath("//form[@name='saleReport']/div[1]/div[2]/div/ul/li[1]/a")).click();	
		
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("Store"));
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(1500);
		//Click the Time Period Options
		driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();	
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
	
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
		
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the required date
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		long start = System.currentTimeMillis();
		
		WebDriverWait wait1=new WebDriverWait(driver, 30);
		WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
		
		try
		{
			
			//Check weather the report is available for the selected time period
			if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
			{
				test.log(LogStatus.PASS, "Hourly Sale Report available for Specific Date");
				long finish = System.currentTimeMillis();
				long totalTime = finish- start; 
				System.out.println("Time in Millisecomds : "+totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime/1000)/6;
				System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//				test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
				
				//Check weather the table format report is available or not
				if(driver.findElement(By.xpath(excel.getData(3, 711, 2))).isDisplayed())
				{
					test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
					
					Thread.sleep(3000);
					
					try {
						driver.findElement(By.xpath("//li/a[@ng-switch-when='last']/span")).click();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						
					}
			        //No.of rows
					Thread.sleep(30000);
			        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr"));
			        System.out.println("Rows Size is : "+rows.size());
			        
			        WebElement rosS=driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]"));
			        JavascriptExecutor js=(JavascriptExecutor)driver;
			        js.executeScript("arguments[0].scrollIntoView(true)", rosS);
			        
	/*		        //Print number of Rows
			        System.out.println("Number of Rows are : "+rows);*/
			        
					//Replace all commo's with empty space
					String expected1 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replace(",", "");
					
					//Convert the String value of the Sale_Report_Net_Sale element into float value
					float expect1 = Float.parseFloat(expected1);
					
					Thread.sleep(3000);
		        	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
	
					 //Check weather the Net Sale is correct or not
			        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
			        {
			        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Sale Amount");
			        	
			        	//Get the Total value of Sale Amount
			        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Net Sale Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        	
			        	//Print the actual value
			        	System.out.println("The Actual Net Sale Value is : "+actual);
			        	
			        	test.log(LogStatus.PASS, "The Actual Net Sale Value is : "+ actual);
			        }
			        
					else if(expect1 == unknownValue)
					{
						test.log(LogStatus.PASS, "Here we don't have the exact expected value");
						
			        	//Get the Total value of Net Sale
			        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

			        	System.out.println("The Actual Net Sale value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Sale Amount");
			        	
			        	//Get the Total value of Sale Amount
			        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the net sale amount Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        			        			        	
			        	//Get the different
			        	float different = actual - expect1;
			        	
			        	//Print the different value
			        	System.out.println("Net Sale Value different is : "+different);
			        	
			        	test.log(LogStatus.FAIL, "Net Sale Value different is : "+different);	
			        }
			        
			        //Check weather the Tax Report is correct or not
			        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText().equals(string_Value_Of_Tax_Amount))
			        {
			        	test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Tax");
			        	
			        	//Get the Total value of Tax
			        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Tax Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        	
			        	//Print the actual value
			        	System.out.println("The Actual Tax Value is : "+actual);
			        	
			        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
			        }
			        
					else if(gobal_Total_Tax_Amount == unknownValue)
					{
						test.log(LogStatus.PASS, "Here we don't have the exact expected value");
						
			        	//Get the Total value of Tax
			        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();

			        	System.out.println("The Actual Tax value is : "+actualText);
			        	
			        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
					}
			        
			        else
			        {
			        	test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Tax");
			        	
			        	//Get the Total value of Tax
			        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]/td[4]")).getText();
			        	
			        	//Replace all commo's with empty space
			        	String actualText= actualText1.replace(",", "");
			        	
			        	//Convert the String value of the Tax Total element into float value
			        	float actual = Float.parseFloat(actualText);
			        			        	
			        	//Get the different
			        	float different = actual - gobal_Total_Tax_Amount;
			        	
			        	//Print the different value
			        	System.out.println("Tax Value different is : "+different);
			        	
			        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
			        }
	
				}
				
				else
				{
					test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date for store");
				
			    	
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));

			
				
				}
	
			}
			}
		catch(Exception e)
		{

			test.log(LogStatus.FAIL, "Hourly Sale Report is not available for Specific Date for store");

	    	
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));

	
		}
				
		Thread.sleep(3000);
		System.out.println("********************* End of Store Report *************************");
		test.log(LogStatus.INFO, "********************* End of Store Report *************************");wb.close();

	}
	
	@Test(enabled=false,priority=4)
	public void Enterprise_Daily_Sale_Report_Tax_Validation_Stores(WebDriver driver) throws Exception
	{
	
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		 FileInputStream fis = new FileInputStream(src);
				
		 XSSFWorkbook wb = new XSSFWorkbook(fis);
				
	     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
	     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	/*	//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();

		Thread.sleep(3000);
       //Click the EnterPrise Reports Option		
		driver.findElement(By.xpath("//span[.='EnterPrise Reports']")).click();
		
		Thread.sleep(2000);
		//Click the Sale option
		driver.findElement(By.xpath("//span[.='Sale']")).click();	*/
		
	     Thread.sleep(2000);
			//Enter the URl
			driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/dailySaleSummary");

			Thread.sleep(5000);
			try
			{
			//Check Daily Report page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 731, 1))).getText().equalsIgnoreCase("Daily Sale"))
			{
				test.log(LogStatus.PASS, "Daily Sale Report page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Daily Sale Report page loaded Failed");
			
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
			
			Thread.sleep(2000);
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

			Thread.sleep(5000);
			System.out.println("********************* View The Store Report *************************");
			test.log(LogStatus.INFO, "********************* View The Store Report *************************");

			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Group");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Store");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);

			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			//Remove the store name
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[1]/div[2]/div/ul/li[1]/a")).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("Store"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();			
			Thread.sleep(1000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
			
			try
			{
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Daily Sale Report available for Specific Date");
					long start = System.currentTimeMillis();
			    	long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(8000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 711, 2))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date For Stores");
						
                         Thread.sleep(3000);
						
//						driver.findElement(By.xpath("//li/a[@ng-switch-when='last']/span")).click();
				        //No.of rows
						Thread.sleep(30000);
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr"));
				        System.out.println("Rows Size is : "+rows.size());
				        
				        WebElement rosS=driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[1]/table/tbody/tr["+rows.size()+"]"));
				        JavascriptExecutor js=(JavascriptExecutor)driver;
				        js.executeScript("arguments[0].scrollIntoView(true)", rosS);
				        
		/*		        //Print number of Rows
				        System.out.println("Number of Rows are : "+rows);*/
				        
						//Replace all commo's with empty space
						String expected1 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replace(",", "");
						
						//Convert the String value of the Sale_Report_Net_Sale element into float value
						float expect1 = Float.parseFloat(expected1); 
						
						Thread.sleep(3000);
			        	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
	
						 //Check weather the Net Sale is correct or not
				        if(driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Sale Amount For Stores");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Net Sale Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Net Sale Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Net Sale Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Net Sale
				        	String actualText = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();

				        	System.out.println("The Actual Net Sale value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Sale Amount For Stores");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//tr["+rows.size()+"]/td[contains(@data-title-text,'Sale Amount')]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the net sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Net Sale Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Net Sale Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText().equals(string_Value_Of_Tax_Amount))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(gobal_Total_Tax_Amount == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/table/tbody/tr["+rows.size()+"]/td[3]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - gobal_Total_Tax_Amount;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
					}
					
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date For Stores");
				
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));


					
					}
	
				}
				}
			catch(Exception e)
			{

					test.log(LogStatus.FAIL, "Daily Sale Report is not available for Specific Date For Stores");

			    	
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));


				}
						
				Thread.sleep(3000);
				System.out.println("********************* End of Store Report *************************");
				test.log(LogStatus.INFO, "********************* End of Store Report *************************");

	
	}
	
	@Test(enabled=false,priority=4)
	public void Enterprise_SaleRecap_Report_Tax_Validation_Stores(WebDriver driver) throws Exception
	{
	
		
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	/*	//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();

		Thread.sleep(3000);
	 //Click the EnterPrise Reports Option		
		driver.findElement(By.xpath("//span[.='EnterPrise Reports']")).click();
		
		Thread.sleep(2000);
		//Click the Sale option
		driver.findElement(By.xpath("//span[.='Sale']")).click();		*/
		
		Thread.sleep(2000);
		//Enter the URl
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/saleRecapSummary");

		Thread.sleep(8000);
		try
		{
		//Check SaleRecap Report page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 798, 1))).getText().equalsIgnoreCase("Sale recap report"))
		{
			test.log(LogStatus.PASS, "SaleRecap Sale Report page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "SaleRecap Sale Report page loaded Failed");
		
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
		Thread.sleep(5000);
		
		//Click the Select option
		driver.findElement(By.xpath(excel.getData(3, 755, 1))).click();
		Thread.sleep(1000);
		//Enter the required filter
		driver.findElement(By.xpath(excel.getData(3, 756, 1))).sendKeys(Utility.getReportPropertyUser("Store"));
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 756, 1))).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(1500);
		//Click the Select reacp Options
		driver.findElement(By.xpath(excel.getData(3, 799, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 800, 1))).sendKeys("Time period");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 800, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(1500);
		//Click the Employee Options
		driver.findElement(By.xpath(excel.getData(3, 801, 1))).click();
		//Enter the required option
		driver.findElement(By.xpath(excel.getData(3, 802, 1))).sendKeys("All");
		//Press the Enter button
		driver.findElement(By.xpath(excel.getData(3, 802, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(3000);
		//Click the Time Period option
		driver.findElement(By.xpath(excel.getData(3, 803, 1))).click();
		//Enter the required Time Period
		driver.findElement(By.xpath(excel.getData(3, 804, 1))).sendKeys("Date Range");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 804, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 805, 1))).clear();
		//Enter the date
		driver.findElement(By.xpath(excel.getData(3, 805, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		Thread.sleep(2000);
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 806, 1))).clear();
		//Enter the date
		driver.findElement(By.xpath(excel.getData(3, 806, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

		
		Thread.sleep(1000);
		//Click the Run button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		
		long start = System.currentTimeMillis();
    	
		WebDriverWait wait1=new WebDriverWait(driver, 30);
		WebElement ele11=driver.findElement(By.xpath("//b[.='SALES']/."));
		
		System.out.println("******************** Sale Recap Type as Time Period ********************************");
		test.log(LogStatus.INFO, "******************** Sale Recap Type as Time Period ********************************");
		
		
		//Check weather the Check Status 
		if(wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
		{
			test.log(LogStatus.PASS, "Check Stats field is available");
			long finish = System.currentTimeMillis();
			long totalTime = finish- start; 
			System.out.println("Time in Millisecomds : "+totalTime);
			double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//			long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
			long ActualfinishTimeDouble1 = (totalTime/1000)/6;
			System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//			test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
			test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
			
	
        	Thread.sleep(5000);
			//Check the SALES
			if(driver.getPageSource().contains("SALES"))
			{
				test.log(LogStatus.INFO, "SALES is available");
				
				System.out.println("SALES is available");

				//Replace all commo's with empty space
	        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replace(",", "");
	        	
	        	//Convert the String value of the Sale_Report_Net_Sale element into float value
	        	float expect2 = Float.parseFloat(expected2);
	        	
	        	 WebElement rosS=driver.findElement(By.xpath(excel.getData(3, 765, 1)));
			        JavascriptExecutor js=(JavascriptExecutor)driver;
			        js.executeScript("arguments[0].scrollIntoView(true)", rosS);
	
	
	        	//Check weather the Net Sales total is same or not
	        	if(driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
	        	{
		        	test.log(LogStatus.PASS, "Actual and Expected Net Sales Values are same");
		        	
		        	//Get the Total value of Net Sales
		        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();
		        	
		        	//Replace all commo's with empty space
		        	String actualText= actualText1.replace(",", "");
		        	
		        	//Convert the String value of the Net Sales element into float value
		        	float actual = Float.parseFloat(actualText);
		        	
		        	//Print the actual value
		        	System.out.println("The Actual Net Sales Value is : "+actual);
		        	
		        	test.log(LogStatus.PASS, "The Actual Net Sales Value is : "+ actual);
		        }
		        
				else if(expect2 == unknownValue)
				{
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");
					
		        	//Get the Total value of Net Sales
		        	String actualText = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();

		        	System.out.println("The Actual Net Sales value is : "+actualText);
		        	
		        	test.log(LogStatus.INFO, "The Actual Net Sales value is : "+actualText);
				}
		        
		        else
		        {
		        	test.log(LogStatus.INFO, "Actual and Expected Net Sales values are different");
		        	
		        	//Get the Total value of Gross Sales
		        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();
		        	
		        	//Replace all commo's with empty space
		        	String actualText= actualText1.replace(",", "");
		        	
		        	//Convert the String value of the Net Sales Total element into float value
		        	float actual = Float.parseFloat(actualText);
		        			        	
		        	//Get the different
		        	float different = actual - expect2;
		        	
		        	//Print the different value
		        	System.out.println("Net Sales Value different is : "+different);
		        	
		        	test.log(LogStatus.INFO, "Net Sales Value different is : "+different);	
		        }
	
			}
			
			else
			{
				test.log(LogStatus.INFO, "Sales is not available");
				
				System.out.println("Sales is not available");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));

			}
			
			
			Thread.sleep(15000);
			//Check weather the Taxes Status 
			if(driver.getPageSource().contains("TAXES"))
			{
				test.log(LogStatus.PASS, "Tax Stats field is available");
				
				Thread.sleep(2000);
				WebElement rosS=driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]"));
		        JavascriptExecutor js=(JavascriptExecutor)driver;
		        js.executeScript("arguments[0].scrollIntoView(true)", rosS);
				
				Thread.sleep(5000);
				//Check the Rounding Off
				if(driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]")).getText().equals(string_Value_Of_tax_Round_Off_Value))
				{
					test.log(LogStatus.PASS, "Actual and Expected Rounding Off are same");
					
		        	//Get the Total value of Rounding Off
		        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]")).getText();
		        	
		        	//Replace all commo's with empty space
		        	String actualText= actualText1.replace(",", "");
		        	
		        	//Convert the String value of the Rounding Off Total element into float value
		        	float actual = Float.parseFloat(actualText);
		        	
		        	//Print the actual value
		        	System.out.println("The Actual Rounding Off Value is : "+actual);
		        	
		        	test.log(LogStatus.PASS, "The Actual Rounding Off Value is : "+ actual);

				}
				
				else if(gobal_actual_Tax_RoundOff == unknownValue)
				{
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");
					
		        	//Get the Total value of Check Count
		        	String actualText = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]")).getText();

		        	System.out.println("The Actual Rounding Off value is : "+actualText);
		        	
		        	test.log(LogStatus.INFO, "The Actual Rounding Off value is : "+actualText);
				}
				
				else
				{
					test.log(LogStatus.FAIL, "Actual and Expected Rounding Off are different");
					
		        	//Get the Total value of Check Count
		        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]")).getText();
		        	
		        	//Replace all commo's with empty space
		        	String actualText= actualText1.replace(",", "");
		        	
		        	//Convert the String value of the Check Count Total element into float value
		        	float actual = Float.parseFloat(actualText);
		        			        	
		        	//Get the different
		        	float different = actual - gobal_actual_Tax_RoundOff;
		        	
		        	//Print the different value
		        	System.out.println("Rounding Off Value different is : "+different);
		        	
		        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	

				}
				
			}
			else
			{
				test.log(LogStatus.INFO, "Sales is not available");
				
				System.out.println("Sales is not available");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));

			}
			
			Thread.sleep(15000);
			//Check weather the Taxes Status 
			if(driver.getPageSource().contains("TAXES"))
			{
				test.log(LogStatus.PASS, "Tax Stats field is available");
				
				Thread.sleep(2000);
				//Check the Rounding Off
				if(driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]")).getText().equals(string_Value_Of_Tax_Amount))
				{
					test.log(LogStatus.PASS, "Actual and Expected Rounding Off are same");
					
		        	//Get the Total value of Rounding Off
		        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]")).getText();
		        	
		        	//Replace all commo's with empty space
		        	String actualText= actualText1.replace(",", "");
		        	
		        	//Convert the String value of the Rounding Off Total element into float value
		        	float actual = Float.parseFloat(actualText);
		        	
		        	//Print the actual value
		        	System.out.println("The Actual Rounding Off Value is : "+actual);
		        	
		        	test.log(LogStatus.PASS, "The Actual Rounding Off Value is : "+ actual);

				}
				
				else if(gobal_Total_Tax_Amount == unknownValue)
				{
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");
					
		        	//Get the Total value of Check Count
		        	String actualText = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]")).getText();

		        	System.out.println("The Actual Rounding Off value is : "+actualText);
		        	
		        	test.log(LogStatus.INFO, "The Actual Rounding Off value is : "+actualText);
				}
				
				else
				{
					test.log(LogStatus.FAIL, "Actual and Expected Rounding Off are different");
					
		        	//Get the Total value of Check Count
		        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]")).getText();
		        	
		        	//Replace all commo's with empty space
		        	String actualText= actualText1.replace(",", "");
		        	
		        	//Convert the String value of the Check Count Total element into float value
		        	float actual = Float.parseFloat(actualText);
		        			        	
		        	//Get the different
		        	float different = actual - gobal_Total_Tax_Amount;
		        	
		        	//Print the different value
		        	System.out.println("Rounding Off Value different is : "+different);
		        	
		        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	

				}
				
			}
			else
			{
				test.log(LogStatus.INFO, "Sales is not available");
				
				System.out.println("Sales is not available");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));

			}
			
			Thread.sleep(15000);
			//Check weather the Taxes Status 
			if(driver.getPageSource().contains("TAXES"))
			{
				test.log(LogStatus.PASS, "Tax Stats field is available");
				
				Thread.sleep(2000);
				//Check the Rounding Off
				if(driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]")).getText().equals(string_Value_Of_tax_Exempt))
				{
					test.log(LogStatus.PASS, "Actual and Expected Rounding Off are same");
					
		        	//Get the Total value of Rounding Off
		        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]")).getText();
		        	
		        	//Replace all commo's with empty space
		        	String actualText= actualText1.replace(",", "");
		        	
		        	//Convert the String value of the Rounding Off Total element into float value
		        	float actual = Float.parseFloat(actualText);
		        	
		        	//Print the actual value
		        	System.out.println("The Actual Rounding Off Value is : "+actual);
		        	
		        	test.log(LogStatus.PASS, "The Actual Rounding Off Value is : "+ actual);

				}
				
				else if(gobal_Total_Tax_Exempt == unknownValue)
				{
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");
					
		        	//Get the Total value of Check Count
		        	String actualText = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]")).getText();

		        	System.out.println("The Actual Rounding Off value is : "+actualText);
		        	
		        	test.log(LogStatus.INFO, "The Actual Rounding Off value is : "+actualText);
				}
				
				else
				{
					test.log(LogStatus.FAIL, "Actual and Expected Rounding Off are different");
					
		        	//Get the Total value of Check Count
		        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]")).getText();
		        	
		        	//Replace all commo's with empty space
		        	String actualText= actualText1.replace(",", "");
		        	
		        	//Convert the String value of the Check Count Total element into float value
		        	float actual = Float.parseFloat(actualText);
		        			        	
		        	//Get the different
		        	float different = actual - gobal_Total_Tax_Exempt;
		        	
		        	//Print the different value
		        	System.out.println("Rounding Off Value different is : "+different);
		        	
		        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	

				}
				
			}
			else
			{
				test.log(LogStatus.INFO, "Sales is not available");
				
				System.out.println("Sales is not available");
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));

			}
			
		}
		else
		{
			test.log(LogStatus.INFO, "Sales is not available");
			
			System.out.println("Sales is not available");
			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));

		}
		
	
	}
	
	@Test(enabled=false,priority=4)
	public void Enterprise_Cashier_Out_Report_Tax_Validation_Stores(WebDriver driver) throws Exception
	{
	
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(2000);
		//Enter the URl
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/cashierOutSummary");

	Thread.sleep(3000);
	driver.findElement(By.xpath(excel.getData(3, 753, 1))).click();
	driver.findElement(By.xpath(excel.getData(3, 753, 1))).click();
	driver.findElement(By.xpath(excel.getData(3, 753, 1))).click();
	driver.findElement(By.xpath(excel.getData(3, 753, 1))).click();
	driver.findElement(By.xpath(excel.getData(3, 753, 1))).click();
			
			
	Thread.sleep(5000);
	try
	{
	//Check CashierOut Report page opened or not
	if(driver.findElement(By.xpath(excel.getData(3, 754, 1))).getText().equalsIgnoreCase("Cashier out"))
	{
		test.log(LogStatus.PASS, "CashierOut Sale Report page loaded Successfully");
	
		String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		String s="data:image/png;base64,"+scnShot;
		test.log(LogStatus.INFO,test.addScreenCapture(s));
	}
	else
	{
		test.log(LogStatus.FAIL, "CashierOut Sale Report page loaded Failed");
	
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
	
	Thread.sleep(3000);
	//Click the Select option
	driver.findElement(By.xpath(excel.getData(3, 755, 1))).click();
	//Enter the required filter
	driver.findElement(By.xpath(excel.getData(3, 756, 1))).sendKeys("Linga_Auto_Test");
	//Press the Enter button
	driver.findElement(By.xpath(excel.getData(3, 756, 1))).sendKeys(Keys.ENTER);
	
	
	Thread.sleep(1500);
	//Click the Employee Options
	driver.findElement(By.xpath(excel.getData(3, 757, 1))).click();
	//Enter the required option
	driver.findElement(By.xpath(excel.getData(3, 758, 1))).sendKeys("All");
	//Press the Enter button
	driver.findElement(By.xpath(excel.getData(3, 758, 1))).sendKeys(Keys.ENTER);

	
	Thread.sleep(3000);
	//Click the Time Period option
	driver.findElement(By.xpath(excel.getData(3, 759, 1))).click();
	//Enter the required Time Period
	driver.findElement(By.xpath(excel.getData(3, 760, 1))).sendKeys("Date Range");
	//Press the Enter Key
	driver.findElement(By.xpath(excel.getData(3, 760, 1))).sendKeys(Keys.ENTER);
	
	Thread.sleep(2000);
	//Clear the date field
	driver.findElement(By.xpath(excel.getData(3, 761, 1))).clear();
	//Enter the date
	driver.findElement(By.xpath(excel.getData(3, 761, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

	//Clear the date field
	driver.findElement(By.xpath(excel.getData(3, 762, 1))).clear();
	//Enter the date
	driver.findElement(By.xpath(excel.getData(3, 762, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

	
	Thread.sleep(5000);
	//Click the Run button
	driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
    long start = System.currentTimeMillis();
    
//	WebDriverWait wait1=new WebDriverWait(driver, 60);
//	WebElement ele11=driver.findElement(By.xpath("//b[.='CHECK STATS']/."));
//	driver.getPageSource()
    Thread.sleep(5000);
	//Check weather the Check Status 
	if(driver.getPageSource().contains("SALES"))
	{
		test.log(LogStatus.PASS, "Check Stats field is available");
		long finish = System.currentTimeMillis();
		long totalTime = finish- start; 
		System.out.println("Time in Millisecomds : "+totalTime);
		double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//		long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
		long ActualfinishTimeDouble1 = (totalTime/1000)/6;
		System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//		test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
		test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
		
	
		Thread.sleep(8000);
		//Check the SALES
		if(driver.getPageSource().contains("SALES"))
		{
			test.log(LogStatus.INFO, "SALES is available");
			
			System.out.println("SALES is available");
	
			//Replace all commo's with empty space
        	String expected2 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replace(",", "");
        	
        	//Convert the String value of the Sale_Report_Net_Sale element into float value
        	float expect2 = Float.parseFloat(expected2);
        	
        	Thread.sleep(2000);
			WebElement rosS=driver.findElement(By.xpath(excel.getData(3, 765, 1)));
	        JavascriptExecutor js=(JavascriptExecutor)driver;
	        js.executeScript("arguments[0].scrollIntoView(true)", rosS);
	
	
        	//Check weather the Net Sales total is same or not
        	if(driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
        	{
	        	test.log(LogStatus.PASS, "Actual and Expected Net Sales Values are same");
	        	
	        	//Get the Total value of Net Sales
	        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();
	        	
	        	//Replace all commo's with empty space
	        	String actualText= actualText1.replace(",", "");
	        	
	        	//Convert the String value of the Net Sales element into float value
	        	float actual = Float.parseFloat(actualText);
	        	
	        	//Print the actual value
	        	System.out.println("The Actual Net Sales Value is : "+actual);
	        	
	        	test.log(LogStatus.PASS, "The Actual Net Sales Value is : "+ actual);
	        }
	        
			else if(expect2 == unknownValue)
			{
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");
				
	        	//Get the Total value of Net Sales
	        	String actualText = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();

	        	System.out.println("The Actual Net Sales value is : "+actualText);
	        	
	        	test.log(LogStatus.INFO, "The Actual Net Sales value is : "+actualText);
			}
	        
	        else
	        {
	        	test.log(LogStatus.FAIL, "Actual and Expected Net Sales values are different");
	        	
	        	//Get the Total value of Gross Sales
	        	String actualText1 = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();
	        	
	        	//Replace all commo's with empty space
	        	String actualText= actualText1.replace(",", "");
	        	
	        	//Convert the String value of the Net Sales Total element into float value
	        	float actual = Float.parseFloat(actualText);
	        			        	
	        	//Get the different
	        	float different = actual - expect2;
	        	
	        	//Print the different value
	        	System.out.println("Net Sales Value different is : "+different);
	        	
	        	test.log(LogStatus.FAIL, "Net Sales Value different is : "+different);	
	        }
		}
		else
		{
			test.log(LogStatus.INFO, "Sales is not available");
			
			System.out.println("Sales is not available");
			
	    	
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));


		}
	
        	Thread.sleep(15000);
			//Check weather the Taxes Status 
			if(driver.getPageSource().contains("TAXES"))
			{
				test.log(LogStatus.PASS, "Tax Stats field is available");
			
				Thread.sleep(2000);
				WebElement rosS=driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]"));
		        JavascriptExecutor js=(JavascriptExecutor)driver;
		        js.executeScript("arguments[0].scrollIntoView(true)", rosS);
				
				
				Thread.sleep(2000);
				//Check the Rounding Off
				if(driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]")).getText().equals(string_Value_Of_tax_Round_Off_Value))
				{
					test.log(LogStatus.PASS, "Actual and Expected Rounding Off are same");
					
		        	//Get the Total value of Rounding Off
		        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]")).getText();
		        	
		        	//Replace all commo's with empty space
		        	String actualText= actualText1.replace(",", "");
		        	
		        	//Convert the String value of the Rounding Off Total element into float value
		        	float actual = Float.parseFloat(actualText);
		        	
		        	//Print the actual value
		        	System.out.println("The Actual Rounding Off Value is : "+actual);
		        	
		        	test.log(LogStatus.PASS, "The Actual Rounding Off Value is : "+ actual);

				}
				
				else if(gobal_actual_Tax_RoundOff == unknownValue)
				{
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");
					
		        	//Get the Total value of Check Count
		        	String actualText = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]")).getText();

		        	System.out.println("The Actual Rounding Off value is : "+actualText);
		        	
		        	test.log(LogStatus.INFO, "The Actual Rounding Off value is : "+actualText);
				}
				
				else
				{
					test.log(LogStatus.FAIL, "Actual and Expected Rounding Off are different");
					
		        	//Get the Total value of Check Count
		        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]")).getText();
		        	
		        	//Replace all commo's with empty space
		        	String actualText= actualText1.replace(",", "");
		        	
		        	//Convert the String value of the Check Count Total element into float value
		        	float actual = Float.parseFloat(actualText);
		        			        	
		        	//Get the different
		        	float different = actual - gobal_actual_Tax_RoundOff;
		        	
		        	//Print the different value
		        	System.out.println("Rounding Off Value different is : "+different);
		        	
		        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	

				}
			}
	
			else
			{
				test.log(LogStatus.INFO, "Sales is not available");
				
				System.out.println("Sales is not available");
				
		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
	
			Thread.sleep(15000);
			//Check weather the Taxes Status 
			if(driver.getPageSource().contains("TAXES"))
			{
				test.log(LogStatus.PASS, "Tax Stats field is available");
				
				Thread.sleep(2000);
				//Check the Rounding Off
				if(driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]")).getText().equals(string_Value_Of_Tax_Amount))
				{
					test.log(LogStatus.PASS, "Actual and Expected Rounding Off are same");
					
		        	//Get the Total value of Rounding Off
		        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]")).getText();
		        	
		        	//Replace all commo's with empty space
		        	String actualText= actualText1.replace(",", "");
		        	
		        	//Convert the String value of the Rounding Off Total element into float value
		        	float actual = Float.parseFloat(actualText);
		        	
		        	//Print the actual value
		        	System.out.println("The Actual Rounding Off Value is : "+actual);
		        	
		        	test.log(LogStatus.PASS, "The Actual Rounding Off Value is : "+ actual);

				}
				
				else if(gobal_Total_Tax_Amount == unknownValue)
				{
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");
					
		        	//Get the Total value of Check Count
		        	String actualText = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]")).getText();

		        	System.out.println("The Actual Rounding Off value is : "+actualText);
		        	
		        	test.log(LogStatus.INFO, "The Actual Rounding Off value is : "+actualText);
				}
				
				else
				{
					test.log(LogStatus.FAIL, "Actual and Expected Rounding Off are different");
					
		        	//Get the Total value of Check Count
		        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]")).getText();
		        	
		        	//Replace all commo's with empty space
		        	String actualText= actualText1.replace(",", "");
		        	
		        	//Convert the String value of the Check Count Total element into float value
		        	float actual = Float.parseFloat(actualText);
		        			        	
		        	//Get the different
		        	float different = actual - gobal_Total_Tax_Amount;
		        	
		        	//Print the different value
		        	System.out.println("Rounding Off Value different is : "+different);
		        	
		        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	

				}
			}
	
			else
			{
				test.log(LogStatus.INFO, "Sales is not available");
				
				System.out.println("Sales is not available");
				
		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
	
			Thread.sleep(15000);
			//Check weather the Taxes Status 
			if(driver.getPageSource().contains("TAXES"))
			{
				test.log(LogStatus.PASS, "Tax Stats field is available");
				
				Thread.sleep(2000);
				//Check the Rounding Off
				if(driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]")).getText().equals(string_Value_Of_tax_Exempt))
				{
					test.log(LogStatus.PASS, "Actual and Expected Rounding Off are same");
					
		        	//Get the Total value of Rounding Off
		        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]")).getText();
		        	
		        	//Replace all commo's with empty space
		        	String actualText= actualText1.replace(",", "");
		        	
		        	//Convert the String value of the Rounding Off Total element into float value
		        	float actual = Float.parseFloat(actualText);
		        	
		        	//Print the actual value
		        	System.out.println("The Actual Rounding Off Value is : "+actual);
		        	
		        	test.log(LogStatus.PASS, "The Actual Rounding Off Value is : "+ actual);

				}
				
				else if(gobal_Total_Tax_Exempt == unknownValue)
				{
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");
					
		        	//Get the Total value of Check Count
		        	String actualText = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]")).getText();

		        	System.out.println("The Actual Rounding Off value is : "+actualText);
		        	
		        	test.log(LogStatus.INFO, "The Actual Rounding Off value is : "+actualText);
				}
				
				else
				{
					test.log(LogStatus.FAIL, "Actual and Expected Rounding Off are different");
					
		        	//Get the Total value of Check Count
		        	String actualText1 = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]")).getText();
		        	
		        	//Replace all commo's with empty space
		        	String actualText= actualText1.replace(",", "");
		        	
		        	//Convert the String value of the Check Count Total element into float value
		        	float actual = Float.parseFloat(actualText);
		        			        	
		        	//Get the different
		        	float different = actual - gobal_Total_Tax_Exempt;
		        	
		        	//Print the different value
		        	System.out.println("Rounding Off Value different is : "+different);
		        	
		        	test.log(LogStatus.FAIL, "Rounding Off Value different is : "+different);	

				}
			}
	
			else
			{
				test.log(LogStatus.INFO, "Sales is not available");
				
				System.out.println("Sales is not available");
				
		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
		
	}
	
	}
	
	@Test(enabled=false,priority=4)
	public void Enterprise_Sale_Summary_Report_Tax_Validation_Stores(WebDriver driver) throws Exception
	{
	
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
			
			Thread.sleep(2000);
			//Enter the URl
			driver.get(Utility.getProperty("Enterprise_Base_URL")+"enterPriseReport/saleSummary");
			Thread.sleep(15000);
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			driver.findElement(By.xpath(excel.getData(3, 767, 1))).click();
			
			Thread.sleep(3000);
			try
			{
			//Check Sale_Summary Report page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 768, 1))).getText().equalsIgnoreCase("Sale Summary"))
			{
				test.log(LogStatus.PASS, "Sale_Summary Report page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Sale_Summary Report page loaded Failed");
			
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
			
			Thread.sleep(3000);
			System.out.println("********************* View The Store Report *************************");
			test.log(LogStatus.INFO, "********************* View The Store Report *************************");

			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Group");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);
	
			Thread.sleep(1500);
			//Click the Select option
			driver.findElement(By.xpath(excel.getData(3, 694, 1))).click();
			//Enter the required filter
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys("Stores");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 695, 1))).sendKeys(Keys.ENTER);

			
			Thread.sleep(1500);
			//Click the Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 696, 1))).click();
			//Remove the store name
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[1]/div[2]/div/ul/li[1]/a")).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Utility.getReportPropertyUser("Store"));
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 697, 1))).sendKeys(Keys.ENTER);
		
			Thread.sleep(1500);
			//Click the Select Employee Options
			driver.findElement(By.xpath(excel.getData(3, 2234, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2235, 1))).sendKeys("All");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2235, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(1500);
			//Click the Time Period Options
			driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
			//Enter the required option
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			
			//Clear the date field
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
			//Enter the required date
			driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(3000);
			//Click the Service Type
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul")).click();
			//Enter the required Service type
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li/input")).sendKeys(Utility.getReportPropertyUser("Service_Type"));
			//Press the Enter button
			//driver.findElement(By.xpath("//form[@name='saleReport']/div[5]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the Run button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			long start = System.currentTimeMillis();
			
			WebDriverWait wait1=new WebDriverWait(driver, 30);
			WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
			
			try
			{
				//Check weather the report is available for the selected time period
				if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
				{
					test.log(LogStatus.PASS, "Sale Summary Report available for Specific Date");
					long finish = System.currentTimeMillis();
					long totalTime = finish- start; 
					System.out.println("Time in Millisecomds : "+totalTime);
					double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//					long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
					long ActualfinishTimeDouble1 = (totalTime/1000)/6;
					System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//					test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
					test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
					
					Thread.sleep(5000);
					//Check weather the table format report is available or not
					if(driver.findElement(By.xpath(excel.getData(3, 769, 1))).isDisplayed())
					{
						test.log(LogStatus.PASS, "Table Format Report is available for Specific Date");
						
						Thread.sleep(30000);
				        //No.of rows
				        List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr"));
				        
				        int rowSize=rows.size();
				        System.out.println("Row size : "+rowSize);
		                 //Print number of Rows
				        //System.out.println("Number of Rows are : "+rows);
		        	//Replace all commo's with empty space
			        	String expected1 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Net_Sale element into float value
			        	float expect1 = Float.parseFloat(expected1);  
		
	
			        	//Replace all commo's with empty space
			        	String expected3 = Utility.getReportPropertyUser("Sale_Report_Inclusive_Tax").replace(",", "");
			        	
			        	//Convert the String value of the Sale_Report_Inclusive_Tax element into float value
			        	float expect3 = Float.parseFloat(expected3); 
			        	
			        	
			        	WebElement netSalf=driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rowSize+"]/td[6]/span"));
			        	JavascriptExecutor js=(JavascriptExecutor)driver;
			        	js.executeScript("argument[0].scrollIntoView(true);", netSalf);
			        	String netSal =driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rowSize+"]/td[6]/span")).getText();
			        	System.out.println("Taken Net Sale    "+netSal);
			        	
			        	Thread.sleep(3000);
			        	driver.findElement(By.xpath("html")).sendKeys(Keys.END);
				       
			        	//Check weather the Net Sale Amount Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Net Sale Amount");
				        	
				        	//Get the Total value of Net Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Net Sale Amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Net Sale Amount Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Net Sale Amount Value is : "+ actual);
				        }
				        
						else if(expect1 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Check Count
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();

				        	System.out.println("The Actual Net Sale Amount value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Net Sale Amount value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Net Sale Amount");
				        	
				        	//Get the Total value of Sale Amount
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[6]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the net sale amount Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect1;
				        	
				        	//Print the different value
				        	System.out.println("Net Sale Amount Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Net Sale Amount Value different is : "+different);	
				        }
				        
				        //Check weather the Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText().equals(string_Value_Of_Tax_Amount))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Tax reports are same for Quantity");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Tax Value is : "+ actual);
				        }
				        
						else if(gobal_Total_Tax_Amount == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total Tax
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();

				        	System.out.println("The Actual Tax value is : "+actualText);
				        	
				        	test.log(LogStatus.INFO, "The Actual Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Tax");
				        	
				        	//Get the Total Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[7]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Get the different
				        	float different = actual - gobal_Total_Tax_Amount;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
				        
				        //Check weather the Inclusive Tax Report is correct or not
				        if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText().equals(Utility.getReportPropertyUser("Sale_Report_Inclusive_Tax")))
				        {
				        	test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Inclusive Tax");
				        	
				        	//Get the Total value of Inclusive Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");   	

				        	//Convert the String value of the Inclusive Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        	
				        	//Print the actual value
				        	System.out.println("The Actual Inclusive Tax Value is : "+actual);
				        	
				        	test.log(LogStatus.PASS, "The Actual Inclusive Tax Value is : "+ actual);
				        }
				        
						else if(expect3 == unknownValue)
						{
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");
							
				        	//Get the Total value of Tax
				        	String actualText = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();

				        	System.out.println("The Actual Inclusive Tax value is : "+actualText);		 
				        	
				        	test.log(LogStatus.INFO, "The Actual Inclusive Tax value is : "+actualText);
						}
				        
				        else
				        {
				        	test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Inclusive Tax");
				        	
				        	//Get the Total value of Tax
				        	String actualText1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/table/tbody/tr["+rows.size()+"]/td[8]/span")).getText();
				        	
				        	//Replace all commo's with empty space
				        	String actualText= actualText1.replace(",", "");
				        	
				        	//Convert the String value of the Tax Total element into float value
				        	float actual = Float.parseFloat(actualText);
				        			        	
				        	//Get the different
				        	float different = actual - expect3;
				        	
				        	//Print the different value
				        	System.out.println("Tax Value different is : "+different);
				        	
				        	test.log(LogStatus.FAIL, "Tax Value different is : "+different);	
				        }
			        	
					
					}
					
					else
					{
						test.log(LogStatus.FAIL, "Table Format Report is not available for Specific Date");
					
				    	
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));

					

					
					}

				}
			}
			catch(Exception e)
			{

				test.log(LogStatus.FAIL, "Sale_Summary Sale Report is not available for Specific Date");

				
		    	
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));


			}
					
			Thread.sleep(3000);
			System.out.println("********************* End of Store Report *************************");
			test.log(LogStatus.INFO, "********************* End of Store Report *************************");wb.close();

			
	}
	}
