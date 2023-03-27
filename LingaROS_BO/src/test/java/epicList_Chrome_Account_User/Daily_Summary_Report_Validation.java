package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;


public class Daily_Summary_Report_Validation {
	
	public WebDriver driver;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("***Daily Summary Report Validation***");
	
	List<Float> gob_Act3 = new ArrayList<Float>();
	float gobal_actual6;
	float Expeceted_Gross_Sale_Value;
	float Expected_Discount_Value;
	float Expected_Tax_Value;
	float Expected_Net_Sale_value;
	float Expected_Total_After_Tax_Value;
	float Expected_CC_Total_Value;
	float unknownValue = 0.0f;
	
	
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

	@Test(priority = 2)
	public void Start_Reports() {
		test.log(LogStatus.INFO, "----** Daily Summary Report Validation Start **----");
	}

	@Test(priority = 3)
	public void calling() throws Exception {
		
		Thread.sleep(5000);
		Daily_Summary_Report_Allvalues_Validation(driver);
		
	}
	
	public void Daily_Summary_Report_Allvalues_Validation(WebDriver driver) throws Exception {
		
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);

		Thread.sleep(3000);
		// Enter the Categories Url
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "dailySummaryReport");
		
		Thread.sleep(5000);
		try
		{
		//Check Comparision Report page opened or not
		if(driver.findElement(By.xpath("//ul//li/a[.='Daily Summary Report']")).getText().equalsIgnoreCase("Daily Summary Report"))
		{
			test.log(LogStatus.PASS,"Daily Summary Report page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL,"Daily Summary Report page loaded Failed");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		}
		catch(Exception e)
		{
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		Thread.sleep(8000);
		// click on the time period option.
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
				// clear the input box in time period.
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input[@type='text']"))
						.clear();
				// enter the time period
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input[@type='text']"))
						.sendKeys("Date Range");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input[@type='text']")).sendKeys(Keys.ENTER);
				Thread.sleep(8000);
				// clear the data in date from cloumn
				driver.findElement(By.xpath("//div//div//input[@ng-model='query.fromDate']")).clear();
				// enter the data in date from column.
				driver.findElement(By.xpath("//div//div//input[@ng-model='query.fromDate']"))
						.sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
				Thread.sleep(2000);
				// clear the data in date to cloumn
				driver.findElement(By.xpath("//div//div//input[@ng-model='query.toDate']")).clear();
				// enter the data in date to column.
				driver.findElement(By.xpath("//div//div//input[@ng-model='query.toDate']"))
						.sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

	    
	    Thread.sleep(3000);
	    //Click the Run
	    driver.findElement(By.cssSelector(excel.getData(3, 974 ,4))).click();
	    
	    long start = System.currentTimeMillis();
	    
	    
	    Thread.sleep(10000);
		// Check weather the Check Status
		if (driver.findElement(By.xpath("//label//span[contains(.,'Gross Sales')]")).isDisplayed()) {
			test.log(LogStatus.PASS, "Daily summary report is available field is available");
			
	    driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
	    
	    Thread.sleep(500);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[contains(.,'Total')]/td[3]")));
		String Gross_Sale = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[contains(.,'Total')]/td[3]")).getText();
		String Gross_Sale2 = Gross_Sale.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		float Gross_Sale_Value = Float.parseFloat(Gross_Sale2);
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[contains(.,'Total')]/td[4]")));
		String Comps_Dis = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[contains(.,'Total')]/td[4]")).getText();
		String Comps_Dis2 = Comps_Dis.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		float Comps_Dis_Value = Float.parseFloat(Comps_Dis2);
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[contains(.,'Total')]/td[5]")));
		String Promo_Dis = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[contains(.,'Total')]/td[5]")).getText();
		String Promo_Dis2 = Promo_Dis.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		float Promo_Dis_Value = Float.parseFloat(Promo_Dis2);
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[contains(.,'Total')]/td[7]")));
		String Net_sale = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[contains(.,'Total')]/td[7]")).getText();
		String Net_sale2 = Net_sale.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		float Net_sale_Value = Float.parseFloat(Net_sale2);
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[contains(.,'Total')]/td[8]")));
		String Total_Tax = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[contains(.,'Total')]/td[8]")).getText();
		String Total_Tax2 = Total_Tax.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		float Total_Tax_Value = Float.parseFloat(Total_Tax2);
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[contains(.,'Total After Tax')]/td[2]")));
		String Total_After_Tax = driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[contains(.,'Total After Tax')]/td[2]")).getText();
		String Total_After_Tax2 = Total_After_Tax.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		float Total_After_Tax_Value = Float.parseFloat(Total_After_Tax2);
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//table[@class='table table-bordered']/tbody/tr[contains(.,' Item Tax')]/td[2])[1]")));
		String Item_Tax = driver.findElement(By.xpath("(//table[@class='table table-bordered']/tbody/tr[contains(.,' Item Tax')]/td[2])[1]")).getText();
		String Item_Tax2 = Item_Tax.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		float Item_Tax_Value = Float.parseFloat(Item_Tax2);
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[contains(.,'Check Tax')]/td[2]")));
		String Check_Tax = driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[contains(.,'Check Tax')]/td[2]")).getText();
		String Check_Tax2 = Check_Tax.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		float Check_Tax_Value = Float.parseFloat(Check_Tax2);
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[contains(.,'Tax On Item Tax')]/td[2]")));
		String Tax_On_Item_Tax = driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[contains(.,'Tax On Item Tax')]/td[2]")).getText();
		String Tax_On_Item_Tax2 = Tax_On_Item_Tax.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		float Tax_On_Item_Tax_Value = Float.parseFloat(Tax_On_Item_Tax2);
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[contains(.,'Tax Rounding Off')]/td[2]")));
		String Tax_Rounding_Off = driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[contains(.,'Tax Rounding Off')]/td[2]")).getText();
		String Tax_Rounding_Off2 = Tax_Rounding_Off.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		float Tax_Rounding_Off_Value = Float.parseFloat(Tax_Rounding_Off2);
		
		int size = driver.findElements(By.xpath("//b[.='Credit card summary']/../../div")).size();
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='Credit card summary']/../../div["+size+"]/div[2]")));
		String CC_Amount_Total = driver.findElement(By.xpath("//b[.='Credit card summary']/../../div["+size+"]/div[2]")).getText();
		String CC_Amount_Total2 = CC_Amount_Total.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		float CC_Amount_Total_Value = Float.parseFloat(CC_Amount_Total2);
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='Credit card summary']/../../div["+size+"]/div[3]")));
		String CC_Tip_Total = driver.findElement(By.xpath("//b[.='Credit card summary']/../../div["+size+"]/div[3]")).getText();
		String CC_Tip_Total2 = CC_Tip_Total.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		float CC_Tip_Total_Value = Float.parseFloat(CC_Tip_Total2);
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='Credit card summary']/../../div["+size+"]/div[4]")));
		String CC_total_Total = driver.findElement(By.xpath("//b[.='Credit card summary']/../../div["+size+"]/div[4]")).getText();
		String CC_total_Total2 = CC_total_Total.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		float CC_total_Total_Value = Float.parseFloat(CC_total_Total2);
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='Credit card summary']/../../div["+size+"]/div[5]")));
		String CC_Net_Total = driver.findElement(By.xpath("//b[.='Credit card summary']/../../div["+size+"]/div[5]")).getText();
		String CC_Net_Total2 = CC_Net_Total.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		float CC_Net_Total_Value = Float.parseFloat(CC_Net_Total2);
		
		List<WebElement> Deposits_Total = driver.findElements(By.xpath("//b[.='Deposits']/../../div/div[2]"));
		
		int size1 = Deposits_Total.size();
		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='Deposits']/../../div["+size1+"]/div[2]")));
		String Deposits_Total_Act = driver.findElement(By.xpath("//b[.='Deposits']/../../div["+size1+"]/div[2]")).getText();
		String Deposits_Total_Act2 = Deposits_Total_Act.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		float Deposits_Total_Act_Value = Float.parseFloat(Deposits_Total_Act2);
		
		for (int i = 2; i < size1; i++) {
			
			String total_Dep = driver.findElement(By.xpath("(//b[.='Deposits']/../../div/div[2])["+i+"]")).getText();
			
//			System.out.println("Total " + total_Discount);
			// Replace all commo's with empty space
			String ReportText = total_Dep.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
	
			// Convert the String value of the Discount Total element into float value
			float gobal_actual7 = Float.parseFloat(ReportText);
			
//			System.out.println("sum 1 " + gobal_actual);
	
			gob_Act3.add(gobal_actual7);
			
		}
		
		for (int k = 0; k < gob_Act3.size(); k++) {
			 gobal_actual6 =gobal_actual6+ gob_Act3.get(k);
	 
	        System.out.println(" Added array values After Total : "+gobal_actual6);
	    }
		
		System.out.println("Total values After Total : "+gobal_actual6);
		
		Expected_Discount_Value = Comps_Dis_Value + Promo_Dis_Value;
		
		Expeceted_Gross_Sale_Value = Comps_Dis_Value + Promo_Dis_Value + Net_sale_Value;
		
		Expected_Total_After_Tax_Value = Net_sale_Value + Comps_Dis_Value + Promo_Dis_Value + Item_Tax_Value + Check_Tax_Value + Tax_On_Item_Tax_Value + Tax_Rounding_Off_Value;
		
		Expected_CC_Total_Value = CC_Amount_Total_Value + CC_Tip_Total_Value;
		
		Expected_Tax_Value = Item_Tax_Value + Check_Tax_Value + Tax_On_Item_Tax_Value + Tax_Rounding_Off_Value;
		
		String Sale_Report_Discount1 = Utility.getReportPropertyUser("Sale_Report_Discount").replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		
		float Sale_Report_Discount_Expected = Float.parseFloat(Sale_Report_Discount1);
		
		if (Expected_Discount_Value == Sale_Report_Discount_Expected) {
			test.log(LogStatus.PASS, "Actual and Expected DIscount Values are same");

			// Print the actual value
			System.out.println("The Actual Discount Value is : " + Expected_Discount_Value);

			test.log(LogStatus.PASS, "The Actual Discount Value is : " + Expected_Discount_Value);
		}

		else if (Sale_Report_Discount_Expected == unknownValue) {
			test.log(LogStatus.PASS, "Here we don't have the exact expected value");

			System.out.println("The Actual Discount value is : " + Expected_Discount_Value);

			test.log(LogStatus.INFO, "The Actual Discount value is : " + Expected_Discount_Value);
		}

		else {
			test.log(LogStatus.FAIL, "Actual and Expected Discount values are different");

			// Get the different
			float different = Expected_Discount_Value - Sale_Report_Discount_Expected;

			// Print the different value
			System.out.println("Discount Value different is : " + different);

			test.log(LogStatus.FAIL, "Discount Value different is : " + different);
		}
		
          String Sale_Report_Gross_Value = Utility.getReportPropertyUser("Daily_Gross_Sales").replaceAll("[a-zA-Z $ ₹ £ , :]", "");
		
		  float Sale_Report_Gross_Value_Expected = Float.parseFloat(Sale_Report_Gross_Value);
		
		
		if (Expeceted_Gross_Sale_Value == Sale_Report_Gross_Value_Expected) {
			test.log(LogStatus.PASS, "Actual and Expected Gross Sale Values are same");

			// Print the actual value
			System.out.println("The Actual Gross Sale Values is : " + Expeceted_Gross_Sale_Value);

			test.log(LogStatus.PASS, "The Actual Gross Sale Value is : " + Expeceted_Gross_Sale_Value);
		}

		else if (Sale_Report_Gross_Value_Expected == unknownValue) {
			test.log(LogStatus.PASS, "Here we don't have the exact expected value");

			System.out.println("The Actual Gross Sale value is : " + Expeceted_Gross_Sale_Value);

			test.log(LogStatus.INFO, "The Actual Gross Sale value is : " + Expeceted_Gross_Sale_Value);
		}

		else {
			test.log(LogStatus.FAIL, "Actual and Expected Gross Sale values are different");

			// Get the different
			float different = Expeceted_Gross_Sale_Value - Sale_Report_Gross_Value_Expected;

			// Print the different value
			System.out.println("Gross Sale Value different is : " + different);

			test.log(LogStatus.FAIL, "Gross Sale Value different is : " + different);
		}
		
		 String Sale_Report_Net_Value = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replaceAll("[a-zA-Z $ ₹ £ , :]", "");
			
		  float Sale_Report_Net_Value_Expected = Float.parseFloat(Sale_Report_Net_Value);
		
		
		if (Sale_Report_Net_Value_Expected == Net_sale_Value) {
			test.log(LogStatus.PASS, "Actual and Expected Net Sale Values are same");

			// Print the actual value
			System.out.println("The Actual Net Sale Values is : " + Net_sale_Value);

			test.log(LogStatus.PASS, "The Actual Net Sale Value is : " + Net_sale_Value);
		}

		else if (Sale_Report_Net_Value_Expected == unknownValue) {
			test.log(LogStatus.PASS, "Here we don't have the exact expected value");

			System.out.println("The Actual Net Sale value is : " + Net_sale_Value);

			test.log(LogStatus.INFO, "The Actual Net Sale value is : " + Net_sale_Value);
		}

		else {
			test.log(LogStatus.FAIL, "Actual and Expected Net Sale values are different");

			// Get the different
			float different = Sale_Report_Net_Value_Expected - Net_sale_Value;

			// Print the different value
			System.out.println("Net Sale Value different is : " + different);

			test.log(LogStatus.FAIL, "Net Sale Value different is : " + different);
		}
		
		
		 String Sale_Report_Tax_Value = Utility.getReportPropertyUser("Sale_Report_Tax").replaceAll("[a-zA-Z $ ₹ £ , :]", "");
			
		  float Sale_Report_Tax_Value_Expected = Float.parseFloat(Sale_Report_Tax_Value);
		
		
		if (Sale_Report_Tax_Value_Expected == Total_Tax_Value) {
			test.log(LogStatus.PASS, "Actual and Expected Tax Values are same");

			// Print the actual value
			System.out.println("The Actual Tax Values is : " + Total_Tax_Value);

			test.log(LogStatus.PASS, "The Actual Tax Value is : " + Total_Tax_Value);
		}

		else if (Sale_Report_Tax_Value_Expected == unknownValue) {
			test.log(LogStatus.PASS, "Here we don't have the exact expected value");

			System.out.println("The Actual Tax value is : " + Total_Tax_Value);

			test.log(LogStatus.INFO, "The Actual Tax value is : " + Total_Tax_Value);
		}

		else {
			test.log(LogStatus.FAIL, "Actual and Expected Tax values are different");

			// Get the different
			float different = Sale_Report_Tax_Value_Expected - Total_Tax_Value;

			// Print the different value
			System.out.println("Tax Value different is : " + different);

			test.log(LogStatus.FAIL, "Tax Value different is : " + different);
		}
		
		//Total tax validation
		
		if (Expected_Tax_Value == Total_Tax_Value) {
			test.log(LogStatus.PASS, "Actual and Expected Tax Values are same");

			// Print the actual value
			System.out.println("The Actual Tax Values is : " + Total_Tax_Value);

			test.log(LogStatus.PASS, "The Actual Tax Value is : " + Total_Tax_Value);
		}

		else if (Sale_Report_Tax_Value_Expected == unknownValue) {
			test.log(LogStatus.PASS, "Here we don't have the exact expected value");

			System.out.println("The Actual Tax value is : " + Total_Tax_Value);

			test.log(LogStatus.INFO, "The Actual Tax value is : " + Total_Tax_Value);
		}

		else {
			test.log(LogStatus.FAIL, "Actual and Expected Tax values are different");

			// Get the different
			float different = Sale_Report_Tax_Value_Expected - Total_Tax_Value;

			// Print the different value
			System.out.println("Tax Value different is : " + different);

			test.log(LogStatus.FAIL, "Tax Value different is : " + different);
		}
		
		
		//Total After tax value validations
		
		if (Expected_Total_After_Tax_Value == Total_After_Tax_Value) {
			test.log(LogStatus.PASS, "Actual and Expected Total After tax Values are same");

			// Print the actual value
			System.out.println("The Actual Total After tax Values is : " + Expected_Total_After_Tax_Value);

			test.log(LogStatus.PASS, "The Actual Total After tax Value is : " + Expected_Total_After_Tax_Value);
		}

		else if (Total_After_Tax_Value == unknownValue) {
			test.log(LogStatus.PASS, "Here we don't have the exact expected value");

			System.out.println("The Actual Total After tax value is : " + Total_After_Tax_Value);

			test.log(LogStatus.INFO, "The Actual Total After tax value is : " + Total_After_Tax_Value);
		}

		else {
			test.log(LogStatus.FAIL, "Actual and Expected Total After tax values are different");

			// Get the different
			float different = Expected_Total_After_Tax_Value - Total_After_Tax_Value;

			// Print the different value
			System.out.println("Total After tax Value different is : " + different);

			test.log(LogStatus.FAIL, "Total After tax Value different is : " + different);
		}
		
		
	//CC Total value validations
		
		if (Expected_CC_Total_Value == CC_total_Total_Value) {
			test.log(LogStatus.PASS, "Actual and Expected CC Total Values are same");

			// Print the actual value
			System.out.println("The Actual CC Total Values is : " + Expected_CC_Total_Value);

			test.log(LogStatus.PASS, "The Actual CC Total Value is : " + Expected_CC_Total_Value);
		}

		else if (CC_total_Total_Value == unknownValue) {
			test.log(LogStatus.PASS, "Here we don't have the exact expected value");

			System.out.println("The Actual CC Total value is : " + CC_total_Total_Value);

			test.log(LogStatus.INFO, "The Actual CC Total value is : " + CC_total_Total_Value);
		}

		else {
			test.log(LogStatus.FAIL, "Actual and Expected CC Total values are different");

			// Get the different
			float different = Expected_CC_Total_Value - CC_total_Total_Value;

			// Print the different value
			System.out.println("CC Total Value different is : " + different);

			test.log(LogStatus.FAIL, "CC Total Value different is : " + different);
		}
		
		//Deposits Total value validations
		
				if (gobal_actual6 == Deposits_Total_Act_Value) {
					test.log(LogStatus.PASS, "Actual and Expected Deposits Total Values are same");

					// Print the actual value
					System.out.println("The Actual Deposits Total Values is : " + gobal_actual6);

					test.log(LogStatus.PASS, "The Actual Deposits Total Value is : " + gobal_actual6);
				}

				else if (Deposits_Total_Act_Value == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					System.out.println("The Actual Deposits Total value is : " + Deposits_Total_Act_Value);

					test.log(LogStatus.INFO, "The Actual Deposits Total value is : " + Deposits_Total_Act_Value);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Expected Deposits Total values are different");

					// Get the different
					float different = gobal_actual6 - Deposits_Total_Act_Value;

					// Print the different value
					System.out.println("Deposits Total Value different is : " + different);

					test.log(LogStatus.FAIL, "Deposits Total Value different is : " + different);
				}
		
		}
	
	}

}
