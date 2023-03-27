package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;


public class Tax_Round_Off_Validation_Account_Level_User {

	public WebDriver driver;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Production Groucho's validation");

	float unknownValue = 00;
	static float gobal_Total_Tax_Amount;
	static float gobal_Total_Tax_Exempt;
	static float gobal_actual_Tax_RoundOff;
	static String string_Value_Of_tax_Exempt;
	static String string_Value_Of_Tax_Amount;
	static String string_Value_Of_tax_Round_Off_Value;
	static float gobal_Tax_Amount_Web;
	String string_Value_Of_Expected;
	String string_Value_Of_Expected2;
	String string_Value_Of_Expected3;
	List<Float> gob_Act1 = new ArrayList<Float>();
	List<Float> gob_Act2 = new ArrayList<Float>();
	List<Float> gob_Act3 = new ArrayList<Float>();
	 float gobal_actual = 0;
	 float gobal_actual2 = 0;
	 float gobal_actual3 = 0;
	 float gobal_actual4 = 0;
	 float gobal_actual5 = 0;
	 float gobal_actual6 = 0;
	 float gobal_actual7 = 0;
	 float gobal_actual8 = 0;
	 float gobal_actual9 = 0;

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
		test.log(LogStatus.INFO, "---- Reports Started (Tax Round-Off Validation) ----");
	}

	@Test(priority = 3)
	public void calling() throws Exception {
		
		Thread.sleep(5000);
		Thread.sleep(1000);
		Tax_Report_Method_viewPage(driver);
		Tax_Report_Method_verify(driver);
		Department_Sale_Tax_Validation(driver);
		CategorySale_Sale_Report_Tax_Validation(driver);
//		SubCategory_Sale_Report_Tax_Validation(driver);
		MenuItemSale_Sale_Report_Tax_Validation(driver);
//		Modifier_Sale_Report_Tax_Validation(driver);
		HourlySale_Sale_Report_Tax_Validation(driver);
		DailySale_Sale_Report_Tax_Validation(driver);
//		SaleRecap_Sale_Report_Tax_Validation(driver);
//		Cashier_Sale_Report_Tax_Validation(driver);
		SaleSummary_Sale_Report_Tax_Validation(driver);
//		Discount_Report_Tax_Validation(driver);
//		online_Sale_Report_Tax_Validation(driver);
//		Comparision_Report_Tax_Validation(driver);
		Daily_Summary_Report_Tax_Validation(driver);
		Tax_Round_Off_Validation_Enterprise_Level_User enter_Tax = new Tax_Round_Off_Validation_Enterprise_Level_User();
		enter_Tax.calling(driver);
		Thread.sleep(1500);
		
	}

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

			String actualText_Tax_Amount = Tax_Amount.replaceAll("[a-zA-Z $ ₹ £ , : .]", "");

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

	public void Department_Sale_Tax_Validation(WebDriver driver) throws Exception {
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(10000);

		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "report/departmentSale");
		Thread.sleep(5000);
		try {
			// Check Sale Report page opened or not
			if (driver.findElement(By.xpath(excel.getData(3, 1512, 1))).getText().equalsIgnoreCase("Sale Report")) {
				test.log(LogStatus.PASS, "Department Sale report page loaded Successfully");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));

			} else {
				test.log(LogStatus.FAIL, "Department Sale report page loaded Failed");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
		} catch (Exception e) {
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.INFO, test.addScreenCapture(s));
		}

		Thread.sleep(3000);

		Thread.sleep(2000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

		Thread.sleep(5000);
		// Click the Department option
		driver.findElement(By.xpath(excel.getData(3, 1067, 1))).click();
		// Enter the required department
		driver.findElement(By.xpath(excel.getData(3, 1068, 1))).sendKeys("All");
		// Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 1068, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		// Click the Time Period option
		driver.findElement(By.xpath(excel.getData(3, 736, 1))).click();
		// Enter the required Time Period
		driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys("Date Range");
		// Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 738, 1))).clear();
		// Enter the date
		driver.findElement(By.xpath(excel.getData(3, 738, 1)))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 739, 1))).clear();
		// Enter the date
		driver.findElement(By.xpath(excel.getData(3, 739, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		// Check whether the Quantity sort is enabled or not
		if (driver.findElement(By.xpath(excel.getData(3, 1356, 1))).isSelected()) {

		} else {
			// Click the Quantity Sort option
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath(excel.getData(2, 9, 1)))).click().build().perform();
		}
		Thread.sleep(2000);
		// Click the Run Button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();

		long start = System.currentTimeMillis();

		Thread.sleep(8000);
		System.out.println("----Department Sale Started-----------");

//		WebDriverWait wait1 = new WebDriverWait(driver, 30);
//		WebElement ele11 = driver.findElement(By.xpath(excel.getData(3, 705, 1)));

		// Check weather the report is available for the selected time period
		if (driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed()) {
			test.log(LogStatus.FAIL, "Department Sale Report is not available for Specific Date");
		}

		else {
	
			test.log(LogStatus.PASS, "Department Sale Report available for Specific Date");
			if (driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed()) {
				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				System.out.println("Time in Millisecomds : " + totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//			long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime / 1000) / 6;
				System.out.println("MilSEc to Mins basic conversion : " + ActualfinishTimeDouble1);
//			test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : " + totalTime);
			}
			Thread.sleep(3000);
			// Check Weather the Top 5 department sale available or not
			if (driver.findElement(By.xpath(excel.getData(3, 705, 1))).isDisplayed()) {
				test.log(LogStatus.PASS, "Here Top 5 Department Sale Report available for Specific Date");

				Thread.sleep(3000);
				// No.of rows
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1078, 1)));

				// Replace all commo's with empty space
				String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale_Report_Sale_Amount element into float
				// value
				float expect1 = Float.parseFloat(expected1);
				
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(8000);

				// Check weather the Sale Amount Report is correct or not
				if (driver
						.findElement(
								By.xpath("//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
						.getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount"))) {
					test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Sale Amount");

					// Get the Total value of Sale Amount
					String actualText1 = driver
							.findElement(By.xpath(
									"//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Sale Amount Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Sale Amount Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Sale Amount Value is : " + actual);
				} else if (expect1 == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Check Count
					String actualText = driver
							.findElement(By.xpath(
									"//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
							.getText();

					System.out.println("The Actual Sale Amount value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Sale Amount value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL,
							"Actual and Expected Department Sale reports are different for Sale Amount");

					// Get the Total value of Sale Amount
					String actualText1 = driver
							.findElement(By.xpath(
									"//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the sale amount Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - expect1;

					// Print the different value
					System.out.println("Sale Amount Value different is : " + different);

					test.log(LogStatus.FAIL, "Sale Amount Value different is : " + different);
				}

				// Check weather the Tax Report is correct or not
				if (driver.findElement(By.xpath("//tr[" + rows.size() + "]/td[contains(@data-title-text,'Tax')]/span"))
						.getText().equals(string_Value_Of_Tax_Amount)) {
					test.log(LogStatus.PASS, "Actual and Expected Department Sale reports are same for Tax");

					// Get the Total value of Tax
					String actualText1 = driver
							.findElement(
									By.xpath("//tr[" + rows.size() + "]/td[contains(@data-title-text,'Tax')]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Tax Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Tax Value is : " + actual);
				}

				else if (gobal_Total_Tax_Amount == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Tax
					String actualText = driver
							.findElement(
									By.xpath("//tr[" + rows.size() + "]/td[contains(@data-title-text,'Tax')]/span"))
							.getText();

					System.out.println("The Actual Tax value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Tax value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Expected Department Sale reports are different for Tax");

					// Get the Total value of Tax
					String actualText1 = driver
							.findElement(
									By.xpath("//tr[" + rows.size() + "]/td[contains(@data-title-text,'Tax')]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - gobal_Total_Tax_Amount;

					// Print the different value
					System.out.println("Tax Value different is : " + different);

					test.log(LogStatus.FAIL, "Tax Value different is : " + different);
				}
			}
		}
	}

	public void CategorySale_Sale_Report_Tax_Validation(WebDriver driver) throws Exception {

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(5000);
		// Enter the Categories Url
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "report/categorySale");

		Thread.sleep(5000);

		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

		Thread.sleep(5000);
		// Click the Category option
		driver.findElement(By.xpath(excel.getData(3, 1350, 1))).click();
		// Enter the required Category
		driver.findElement(By.xpath(excel.getData(3, 1351, 1))).sendKeys("All");
		// Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 1351, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		// Click the Time Period option
		driver.findElement(By.xpath(excel.getData(3, 736, 1))).click();
		// Enter the required Time Period
		driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys("Date Range");
		// Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 737, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 738, 1))).clear();
		// Enter the date
		driver.findElement(By.xpath(excel.getData(3, 738, 1)))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 739, 1))).clear();
		// Enter the date
		driver.findElement(By.xpath(excel.getData(3, 739, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

		// Check whether the Quantity sort is enabled or not
		if (driver.findElement(By.xpath(excel.getData(3, 1356, 1))).isSelected()) {
			// Click the Quantity Sort option
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath(excel.getData(3, 1356, 1)))).click().build().perform();
		} else {
		}

		Thread.sleep(2000);
		// Click the Run Button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();

		long start = System.currentTimeMillis();

		Thread.sleep(8000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		// driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);

		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		WebElement ele11 = driver.findElement(By.xpath(excel.getData(3, 705, 1)));

		// Check weather the report is available for the selected time period
		if (driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed()) {
			test.log(LogStatus.FAIL, "Category Sale Report is not available for the Required Date Range");

			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.INFO, test.addScreenCapture(s));

		} else {
			test.log(LogStatus.PASS, "Category Sale Report available for the Required Date Range");

			Thread.sleep(5000);
			// Check Weather the Top 5 category sale available or not
			if (wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed()) {
				test.log(LogStatus.PASS, "Here Top 5 Category Sale Report available for the Required Date Range");

				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				System.out.println("Time in Millisecomds : " + totalTime);
				test.log(LogStatus.INFO, "Time in Millisecomds : " + totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime / 1000) / 6;
				System.out.println("MilSEc to Mins basic conversion : " + ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Total Time : " + ActualfinishTimeDouble1);

				Thread.sleep(3000);
				// No.of rows
				List<WebElement> rows = driver.findElements(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr"));

				// Replace all commo's with empty space
				String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale_Report_Sale_Amount element into float
				// value
				float expect1 = Float.parseFloat(expected1);

				// Replace all commo's with empty space
				String expected3 = Utility.getReportPropertyUser("Sale_Report_Tax").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale_Report_Tax element into float value
				float expect3 = Float.parseFloat(expected3);
				
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(8000);

				if (driver
						.findElement(
								By.xpath("//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
						.getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount"))) {
					test.log(LogStatus.PASS, "Actual and Expected Category Sale reports are same for Sale Amount");

					// Get the Total value of Sale Amount
					String actualText1 = driver
							.findElement(By.xpath(
									"//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Sale Amount Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Sale Amount Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Sale Amount Value is : " + actual);
				}

				else if (expect1 == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Sale Amount
					String actualText = driver
							.findElement(By.xpath(
									"//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
							.getText();

					System.out.println("The Actual Sale Amount value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Sale Amount value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Expected Category Sale reports are different for Sale Amount");

					// Get the Total value of Sale Amount
					String actualText1 = driver
							.findElement(By.xpath(
									"//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the sale amount Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - expect1;

					// Print the different value
					System.out.println("Sale Amount Value different is : " + different);

					test.log(LogStatus.FAIL, "Sale Amount Value different is : " + different);
				}

				// Check weather the Tax Report is correct or not
				if (driver.findElement(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
								+ rows.size() + "]/td[4]/span"))
						.getText().equals(string_Value_Of_Tax_Amount)) {
					test.log(LogStatus.PASS, "Actual and Expected Category Sale reports are same for Tax");

					// Get the Total value of Tax
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[4]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Tax Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Tax Value is : " + actual);
				}

				else if (gobal_Total_Tax_Amount == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Tax
					String actualText = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[4]/span"))
							.getText();

					System.out.println("The Actual Tax value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Tax value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Expected Category Sale reports are different for Tax");

					// Get the Total value of Tax
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[4]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - gobal_Total_Tax_Amount;

					// Print the different value
					System.out.println("Tax Value different is : " + different);

					test.log(LogStatus.FAIL, "Tax Value different is : " + different);
				}

			}

		}
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

	}

	public void SubCategory_Sale_Report_Tax_Validation(WebDriver driver) throws Exception {

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(3000);
		// Enter the Categories Url
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "report/subCategorySale");

		Thread.sleep(10000);

//	Thread.sleep(2000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

		Thread.sleep(15000);
		// Click the Category option
		driver.findElement(By.xpath(excel.getData(3, 2328, 1))).click();
		// Enter the required Category
		driver.findElement(By.xpath(excel.getData(3, 2329, 1))).sendKeys("All");
		// Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 2329, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		// Click the Sub Category option
		driver.findElement(By.xpath(excel.getData(3, 2330, 1))).click();
		// Enter the required Sub Category
		driver.findElement(By.xpath(excel.getData(3, 2331, 1))).sendKeys("All");
		// Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 2331, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		// Click the Time Period option
		driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
		// Enter the required Time Period
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
		// Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		// Enter the date
		driver.findElement(By.xpath(excel.getData(3, 1982, 1)))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		// Enter the date
		driver.findElement(By.xpath(excel.getData(3, 1983, 1)))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

		// Check whether the Quantity sort is enabled or not
		if (driver.findElement(By.xpath(excel.getData(1, 1356, 1))).isSelected()) {
			// Click the Quantity Sort option
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath(excel.getData(1, 1356, 1)))).click().build().perform();
		} else {
		}

		Thread.sleep(2000);
		// Click the Run Button
		driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();

		long start = System.currentTimeMillis();
		Thread.sleep(6000);
		// Check weather the report is available for the selected time period
		if (driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed()) {
			test.log(LogStatus.FAIL, "Sub Category Sale Report is not available for the Required Date Range");

			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.INFO, test.addScreenCapture(s));
		} else {
			test.log(LogStatus.PASS, "Sub Category Sale Report available for the Required Date Range");

			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.INFO, test.addScreenCapture(s));

			WebDriverWait wait1 = new WebDriverWait(driver, 30);
			WebElement ele11 = driver.findElement(By.xpath(excel.getData(3, 705, 1)));

			// Check Weather the Top 5 sub category sale available or not
			if (wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed()) {
				test.log(LogStatus.PASS, "Here Top 5 Sub Category Sale Report available for the Required Date Range");

				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				System.out.println("Time in Millisecomds : " + totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//			long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime / 1000) / 6;
				System.out.println("MilSEc to Mins basic conversion : " + ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : " + totalTime);

				Thread.sleep(2000);

				Thread.sleep(3000);
				// No.of rows
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(1, 1358, 1)));

				// Replace all commo's with empty space
				String expected1 = Utility.getReportPropertyUser("Sub_Category_Sale_Report_Sale_Amount").replace(",",
						"");

				// Convert the String value of the Sub_Category_Sale_Report_Sale_Amount element
				// into float value
				float expect1 = Float.parseFloat(expected1);

				// Replace all commo's with empty space
				String expected3 = Utility.getReportPropertyUser("Sub_Category_Sale_Report_Tax").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sub_Category_Sale_Report_Tax element into
				// float value
				float expect3 = Float.parseFloat(expected3);// System.out.println("TEST TAX AMOUNT IS : "+expected3);

				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(8000);
				
				// Check weather the Sale Amount Report is correct or not
				if (driver
						.findElement(
								By.xpath("//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
						.getText().equals(Utility.getReportPropertyUser("Sub_Category_Sale_Report_Sale_Amount"))) {
					test.log(LogStatus.PASS, "Actual and Expected Sub Category Sale reports are same for Sale Amount");

					// Get the Total value of Sale Amount
					String actualText1 = driver
							.findElement(By.xpath(
									"//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Sale Amount Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Sale Amount Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Sale Amount Value is : " + actual);
				}

				else if (expect1 == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Sale Amount
					String actualText = driver
							.findElement(By.xpath(
									"//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
							.getText();

					System.out.println("The Actual Sale Amount value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Sale Amount value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL,
							"Actual and Expected Sub Category Sale reports are different for Sale Amount");

					// Get the Total value of Sale Amount
					String actualText1 = driver
							.findElement(By.xpath(
									"//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the sale amount Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - expect1;

					// Print the different value
					System.out.println("Sale Amount Value different is : " + different);

					test.log(LogStatus.FAIL, "Sale Amount Value different is : " + different);
				}

				// Check weather the Tax Report is correct or not
				if (driver.findElement(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
								+ rows.size() + "]/td[4]/span"))
						.getText().equals(Utility.getReportPropertyUser("Sub_Category_Sale_Report_Tax"))) {
					test.log(LogStatus.PASS, "Actual and Expected Sub Category Sale reports are same for Tax");

					// Get the Total value of Tax
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[4]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Tax Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Tax Value is : " + actual);
				}

				else if (expect3 == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Tax
					String actualText = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[4]/span"))
							.getText();

					System.out.println("The Actual Tax value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Tax value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Expected Sub Category Sale reports are different for Tax");

					// Get the Total value of Tax
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[4]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - expect3;

					// Print the different value
					System.out.println("Tax Value different is : " + different);

					test.log(LogStatus.FAIL, "Tax Value different is : " + different);
				}

			}
		}
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);

	}

	public void MenuItemSale_Sale_Report_Tax_Validation(WebDriver driver) throws Exception {

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(3000);
		// Enter the Categories Url
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "report/menuItemSale");

		Thread.sleep(3000);
		try {
			// Check Menu Item Sale Report page opened or not
			if (driver.findElement(By.xpath("//label[.='Menu Item']")).getText().equalsIgnoreCase("Menu Item")) {
				test.log(LogStatus.PASS, "Menu Item Sale report page loaded Successfully");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			} else {
				test.log(LogStatus.FAIL, "Menu Item Sale report page loaded Failed");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
		} catch (Exception e) {
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.FAIL, test.addScreenCapture(s));
		}

		Thread.sleep(5000);

//		Thread.sleep(2000);
//		WebElement html = driver.findElement(By.tagName("html"));
//		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
//		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
//		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
//		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

//		Thread.sleep(5000);
//		// Click the Category option
//		driver.findElement(By.xpath("//form[@name='saleReport']/div[1]/div/div/a")).click();
//		// Enter the required Category
//		driver.findElement(By.xpath("//form[@name='saleReport']/div[1]/div/div/div/div/input")).sendKeys("All");
//		// Press the Enter Key
//		driver.findElement(By.xpath("//form[@name='saleReport']/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);

//		Thread.sleep(3000);
//		// Click the Sub Category option
//		driver.findElement(By.xpath("//form[@name='saleReport']/div[2]/div/div/a")).click();
//		// Enter the required Sub Category
//		driver.findElement(By.xpath("//form[@name='saleReport']/div[2]/div/div/div/div/input")).sendKeys("All");
//		// Press the Enter Key
//		driver.findElement(By.xpath("//form[@name='saleReport']/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);

//		Thread.sleep(3000);
//		// Click the Menu Item option
//		driver.findElement(By.xpath("//form[@name='saleReport']/div[3]/div/div/a")).click();
//		// Enter the required Menu Item
//		driver.findElement(By.xpath("//form[@name='saleReport']/div[3]/div/div/div/div/input")).sendKeys("All");
//		// Press the Enter Key
//		driver.findElement(By.xpath("//form[@name='saleReport']/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);

//		Thread.sleep(3000);
//		// Click the Serving Level option
//		driver.findElement(By.xpath("//form[@name='saleReport']/div[4]/div/div/a")).click();
//		// Enter the required Serving Level
//		driver.findElement(By.xpath("//form[@name='saleReport']/div[4]/div/div/div/div/input")).sendKeys("All");
//		// Press the Enter Key
//		driver.findElement(By.xpath("//form[@name='saleReport']/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);

//		// Check Whether the Split By Serving Size Level is enabled or not
//		if (driver.findElement(By.xpath("//input[@ng-model='query.splitBySS']")).isSelected()) {
//			Thread.sleep(2000);
//			// Change the option from enable to Disable the Split by Serving Level
//			Actions act = new Actions(driver);
//			act.moveToElement(driver.findElement(By.xpath("//input[@ng-model='query.splitBySS']/../span"))).click()
//					.build().perform();
//		}

//		Thread.sleep(3000);
//		// Click the Employee option
//		driver.findElement(By.xpath("//select[@ng-model='query.selectedEmployee']/../div/a")).click();
//		// Enter the required Employee
//		driver.findElement(By.xpath("//select[@ng-model='query.selectedEmployee']/../div/div/div/input"))
//				.sendKeys("All");
//		// Press the Enter Key
//		driver.findElement(By.xpath("//select[@ng-model='query.selectedEmployee']/../div/div/div/input"))
//				.sendKeys(Keys.ENTER);

		Thread.sleep(5000);
		// Click the Time Period option
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
		// Enter the required Time Period
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys("Date Range");
		// Press the Enter Key
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
		Thread.sleep(2000);
		// Enter the date
		driver.findElement(By.xpath("//input[@ng-model='query.fromDate']"))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
		Thread.sleep(2000);
		// Enter the date
		driver.findElement(By.xpath("//input[@ng-model='query.toDate']"))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

		Thread.sleep(2000);
		// Check whether the Quantity sort is enabled or not
		if (driver.findElement(By.xpath("//input[@ng-model='query.qtySort']")).isSelected()) {
			// Click the Quantity Sort option
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//input[@ng-model='query.qtySort']/../span"))).click()
					.build().perform();
		} else {
		}

		Thread.sleep(2000);
		// Click the Run Button
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		long start = System.currentTimeMillis();
		Thread.sleep(25000);

		try {

			// Check weather the report is available for the selected time period
			if (driver.findElement(By.xpath("//h3[.='No sale for selected time period']")).isDisplayed()) {
				test.log(LogStatus.FAIL, "Menu Item Sale Report is not available for the Required This Week");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
		} catch (Exception fgh) {
			test.log(LogStatus.PASS, "Menu Item Sale Report available for the Required This Week");

			WebDriverWait wait1 = new WebDriverWait(driver, 30);
			WebElement ele11 = driver.findElement(By.xpath(
					"//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div/div[1]/div/div[1]/div[1]"));
			// Check Weather the Top 5 menu Item sale available or not
			if (wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed()) {
				test.log(LogStatus.PASS, "Here Top 5 menu item Sale Report available for the Required Date Range");

				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				System.out.println("Time in Millisecomds : " + totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime / 1000) / 6;
				System.out.println("MilSEc to Mins basic conversion : " + ActualfinishTimeDouble1);
//				test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : " + totalTime);

				Thread.sleep(8000);
				// No.of rows
				List<WebElement> rows = driver.findElements(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr"));

				String expected1 = Utility.getReportPropertyUser("Sale_Report_Sale_Amount").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale_Report_Sale_Amount element into float
				// value
				float expect1 = Float.parseFloat(expected1);
				
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(8000);

				// Check weather the Sale Amount Report is correct or not
				if (driver
						.findElement(
								By.xpath("//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
						.getText().equals(Utility.getReportPropertyUser("Sale_Report_Sale_Amount"))) {
					test.log(LogStatus.PASS, "Actual and Expected menu item Sale reports are same for Sale Amount");

					// Get the Total value of Sale Amount
					String actualText1 = driver
							.findElement(By.xpath(
									"//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Sale Amount Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Sale Amount Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Sale Amount Value is : " + actual);
				}

				else if (expect1 == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Sale Amount
					String actualText = driver
							.findElement(By.xpath(
									"//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
							.getText();

					System.out.println("The Actual Sale Amount value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Sale Amount value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL,
							"Actual and Expected menu item Sale reports are different for Sale Amount");

					// Get the Total value of Sale Amount
					String actualText1 = driver
							.findElement(By.xpath(
									"//tr[" + rows.size() + "]/td[contains(@data-title-text,'Sale Amount')]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the sale amount Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - expect1;

					// Print the different value
					System.out.println("Sale Amount Value different is : " + different);

					test.log(LogStatus.FAIL, "Sale Amount Value different is : " + different);
				}

				// Check weather the Tax Report is correct or not
				if (driver.findElement(By.xpath("//tr[" + rows.size() + "]/td[contains(@data-title-text,'Tax')]/span"))
						.getText().equals(string_Value_Of_Tax_Amount)) {
					test.log(LogStatus.PASS, "Actual and Expected menu item Sale reports are same for Tax");

					// Get the Total value of Tax
					String actualText1 = driver
							.findElement(
									By.xpath("//tr[" + rows.size() + "]/td[contains(@data-title-text,'Tax')]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Tax Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Tax Value is : " + actual);
				}

				else if (gobal_Total_Tax_Amount == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Sale Amount
					String actualText = driver
							.findElement(
									By.xpath("//tr[" + rows.size() + "]/td[contains(@data-title-text,'Tax')]/span"))
							.getText();

					System.out.println("The Actual Tax value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Tax value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Expected menu item Sale reports are different for Tax");

					// Get the Total value of Tax
					String actualText1 = driver
							.findElement(
									By.xpath("//tr[" + rows.size() + "]/td[contains(@data-title-text,'Tax')]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - gobal_Total_Tax_Amount;

					// Print the different value
					System.out.println("Tax Value different is : " + different);

					test.log(LogStatus.FAIL, "Tax Value different is : " + different);
				}
			}
		}
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
	}

	public void Modifier_Sale_Report_Tax_Validation(WebDriver driver) throws Exception {

		Thread.sleep(8000);
		// Enter the Categories Url
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "report/modifierSale");

		Thread.sleep(3000);
		try {
			// Check weather the Modifier Sale Report page is loaded or not
			if (driver.findElement(By.xpath("//label[.='Modifier']")).getText().equalsIgnoreCase("Modifier")) {
				test.log(LogStatus.PASS, "Modifier Sale Report page loaded successfully");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			} else {
				test.log(LogStatus.FAIL, "Modifier Sale Report page loaded fail");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
		} catch (Exception e) {
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.INFO, test.addScreenCapture(s));
		}

		Thread.sleep(8000);

		Thread.sleep(2000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

		Thread.sleep(5000);
		// Click the Modifier option
		driver.findElement(By.xpath("//select[@ng-model='query.selectedModifierId']/../div/a")).click();
		Thread.sleep(3000);
		// Enter the required Modifier
		driver.findElement(By.xpath("//select[@ng-model='query.selectedModifierId']/../div/div/div/input"))
				.sendKeys("All");
		Thread.sleep(3000);
		// Press the Enter Key
		driver.findElement(By.xpath("//select[@ng-model='query.selectedModifierId']/../div/div/div/input"))
				.sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		// Click the Time Period option
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
		Thread.sleep(2000);
		// Enter the required Time Period
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys("Date Range");
		Thread.sleep(2000);
		// Press the Enter Key
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).clear();
		Thread.sleep(2000);
		// Enter the date
		driver.findElement(By.xpath("//input[@ng-model='query.fromDate']"))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath("//input[@ng-model='query.toDate']")).clear();
		Thread.sleep(2000);
		// Enter the date
		driver.findElement(By.xpath("//input[@ng-model='query.toDate']"))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

		Thread.sleep(2000);
		// Check whether the Quantity sort is enabled or not
		if (driver.findElement(By.xpath("//input[@ng-model='query.qtySort']")).isSelected()) {
			// Click the Quantity Sort option
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//input[@ng-model='query.qtySort']/../span"))).click()
					.build().perform();
		} else {
		}

		Thread.sleep(5000);
		// Click the Run Button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		long start = System.currentTimeMillis();

		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 30);
			WebElement ele11 = driver.findElement(By.xpath("//h3[.='No sale for selected time period']"));

			// Check weather the report is available for the selected time period
			if (wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed()) {
				test.log(LogStatus.FAIL, "Modifier Sale Report is not available for the Required Date Range");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
		} catch (Exception fd) {
			test.log(LogStatus.PASS, "Modifier Sale Report available for the Required Date Range");

			Thread.sleep(8000);
			// Check weather the table format report is available or not
			if (driver.findElement(By.xpath(
					"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/thead/tr/th/div/span[.='Modifier Name']"))
					.isDisplayed()) {
				test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");

				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				System.out.println("Time in Millisecomds : " + totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime / 1000) / 6;
				System.out.println("MilSEc to Mins basic conversion : " + ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : " + totalTime);

				Thread.sleep(3000);
				// No.of rows
				List<WebElement> rows = driver.findElements(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr"));

				// Replace all commo's with empty space
				String expected1 = Utility.getReportPropertyUser("Modifier_Sale_Report_Sale_Amount").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Modifier_Sale_Report_Sale_Amount element into
				// float value
				float expect1 = Float.parseFloat(expected1);

				// Replace all commo's with empty space
				String expected3 = Utility.getReportPropertyUser("Modifier_Sale_Report_Tax").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Modifier_Sale_Report_Tax element into float
				// value
				float expect3 = Float.parseFloat(expected3);
				
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(8000);

				// Check weather the Sale Amount Report is correct or not
				if (driver.findElement(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
								+ rows.size() + "]/td[3]"))
						.getText().equals(Utility.getReportPropertyUser("Modifier_Sale_Report_Sale_Amount"))) {
					test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Sale Amount");

					// Get the Total value of Sale Amount
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[3]"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Sale Amount Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Sale Amount Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Sale Amount Value is : " + actual);
				}

				else if (expect1 == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Sale Amount
					String actualText = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[3]"))
							.getText();

					System.out.println("The Actual Sale Amount value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Sale Amount value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Sale Amount");

					// Get the Total value of Sale Amount
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[3]"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the sale amount Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - expect1;

					// Print the different value
					System.out.println("Sale Amount Value different is : " + different);

					test.log(LogStatus.FAIL, "Sale Amount Value different is : " + different);
				}

				// Check weather the Tax Report is correct or not
				if (driver.findElement(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
								+ rows.size() + "]/td[5]"))
						.getText().equals(Utility.getReportPropertyUser("Modifier_Sale_Report_Tax"))) {
					test.log(LogStatus.PASS, "Actual and Expected Modifier Sale reports are same for Tax");

					// Get the Total value of Tax
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[5]"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Tax Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Tax Value is : " + actual);
				}

				else if (expect3 == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Percentage of Sale
					String actualText = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[5]"))
							.getText();

					System.out.println("The Actual Tax value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Tax value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Expected Modifier Sale reports are different for Tax");

					// Get the Total value of Tax
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[5]"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - expect3;

					// Print the different value
					System.out.println("Tax Value different is : " + different);

					test.log(LogStatus.FAIL, "Tax Value different is : " + different);
				}

				driver.findElement(By.tagName("html")).sendKeys(Keys.END);

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));

			}

		}

		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
	}

	public void HourlySale_Sale_Report_Tax_Validation(WebDriver driver) throws Exception {

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(3000);
		// Enter the Categories Url
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "report/hourlySale");

		Thread.sleep(3000);
		try {
			// Check weather the Modifier Sale Report page is loaded or not
			if (driver.findElement(By.xpath(excel.getData(3, 731, 1))).getText().equalsIgnoreCase("Hourly Sale")) {
				test.log(LogStatus.PASS, "Hourly Sale Report page loaded successfully");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			} else {
				test.log(LogStatus.FAIL, "Hourly Sale Report page loaded fail");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
		} catch (Exception e) {
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.INFO, test.addScreenCapture(s));
		}
		wb.close();
		Thread.sleep(3000);

		Thread.sleep(2000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

		Thread.sleep(5000);
		// Click the Time Period option
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
		// Enter the required Time Period
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys("Date Range");
		// Press the Enter Key
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1075, 1))).clear();
		Thread.sleep(2000);
		// Enter the date
		driver.findElement(By.xpath(excel.getData(3, 1075, 1)))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1076, 1))).clear();
		Thread.sleep(2000);
		// Enter the date
		driver.findElement(By.xpath(excel.getData(3, 1076, 1)))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

		Thread.sleep(2000);
		// Click the Run Button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();

		long start = System.currentTimeMillis();
		Thread.sleep(10000);

		// Check weather the report is available for the selected time period
		if (driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed()) {
			test.log(LogStatus.FAIL, "Hourly Sale Report is not available for Specific Date");
		} else {
			test.log(LogStatus.PASS, "Hourly Sale Report available for Specific Date");

//			WebDriverWait wait1 = new WebDriverWait(driver, 30);
//			WebElement ele11 = driver.findElement(By.xpath(excel.getData(3, 705, 1)));

			// Check Weather the Top 5 hourly sale available or not
			if (driver.findElement(By.xpath(
					"//div[contains(@class,'row ng-scope')]"))
					.isDisplayed()) {
				test.log(LogStatus.PASS, "Here Top 5 Hourly Sale Report available for Specific Date");

				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				System.out.println("Time in Millisecomds : " + totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime / 1000) / 6;
				System.out.println("MilSEc to Mins basic conversion : " + ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : " + totalTime);

				Thread.sleep(10000);
				// No.of rows
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1565, 1)));

				// Replace all commo's with empty space
				String expected1 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale_Report_Net_Sale element into float value
				float expect1 = Float.parseFloat(expected1);
				
				Thread.sleep(5000);
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(2000);
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(2000);
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(2000);

				// Check weather the Net Sale is correct or not
				if (driver
						.findElement(By.xpath(
								"//td[contains(.,'TOTAL')]/../td[4]"))
						.getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale"))) {
					test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Sale Amount");

					// Get the Total value of Sale Amount
					String actualText1 = driver.findElement(By.xpath(
							"//td[contains(.,'TOTAL')]/../td[4]"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Net Sale Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Net Sale Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Net Sale Value is : " + actual);
				}

				else if (expect1 == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Net Sale
					String actualText = driver.findElement(By.xpath(
							"//td[contains(.,'TOTAL')]/../td[4]"))
							.getText();

					System.out.println("The Actual Net Sale value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Net Sale value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Sale Amount");

					// Get the Total value of Sale Amount
					String actualText1 = driver.findElement(By.xpath(
							"//td[contains(.,'TOTAL')]/../td[4]"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the net sale amount Total element into float
					// value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - expect1;

					// Print the different value
					System.out.println("Net Sale Value different is : " + different);

					test.log(LogStatus.FAIL, "Net Sale Value different is : " + different);
				}

				// Check weather the Tax Report is correct or not
				if (driver.findElement(By
						.xpath("//td[contains(.,'TOTAL')]/../td[5]"))
						.getText().equals(string_Value_Of_Tax_Amount)) {
					test.log(LogStatus.PASS, "Actual and Expected Hourly Sale reports are same for Tax");

					// Get the Total value of Tax
					String actualText1 = driver.findElement(By.xpath(
							"//td[contains(.,'TOTAL')]/../td[5]"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Tax Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Tax Value is : " + actual);
				}

				else if (gobal_Total_Tax_Amount == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Tax
					String actualText = driver.findElement(By.xpath(
							"//td[contains(.,'TOTAL')]/../td[5]"))
							.getText();

					System.out.println("The Actual Tax value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Tax value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Expected Hourly Sale reports are different for Tax");

					// Get the Total value of Tax
					String actualText1 = driver.findElement(By.xpath(
							"//td[contains(.,'TOTAL')]/../td[5]"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - gobal_Total_Tax_Amount;

					// Print the different value
					System.out.println("Tax Value different is : " + different);

					test.log(LogStatus.FAIL, "Tax Value different is : " + different);
				}

			}
		}
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
	}

	public void DailySale_Sale_Report_Tax_Validation(WebDriver driver) throws Exception {

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(3000);
		// Enter the Categories Url
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + ("report/dailySale"));

		Thread.sleep(3000);
		try {
			// Check weather the Modifier Sale Report page is loaded or not
			if (driver.findElement(By.xpath(excel.getData(3, 1074, 1))).getText().equalsIgnoreCase("Daily Sale")) {
				test.log(LogStatus.PASS, "Daily Sale Sale Report page loaded successfully");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			} else {
				test.log(LogStatus.FAIL, "Daily Sale Sale Report page loaded fail");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
		} catch (Exception e) {
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.INFO, test.addScreenCapture(s));
		}

		Thread.sleep(2000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

		Thread.sleep(3000);
		// Click the Time Period option
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
		// Enter the required Time Period
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys("Date Range");
		// Press the Enter Key
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1075, 1))).clear();
		// Enter the date
		driver.findElement(By.xpath(excel.getData(3, 1075, 1)))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1076, 1))).clear();
		// Enter the date
		driver.findElement(By.xpath(excel.getData(3, 1076, 1)))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

		Thread.sleep(2000);
		// Click the Run Button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();

		long start = System.currentTimeMillis();
		Thread.sleep(10000);
		// Check weather the report is available for the selected time period
		if (driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed()) {
			test.log(LogStatus.FAIL, "Daily Sale Report is not available for Specific Date");
		} else {
			test.log(LogStatus.PASS, "Daily Sale Report available for Specific Date");

			WebDriverWait wait1 = new WebDriverWait(driver, 30);
			WebElement ele11 = driver.findElement(By.xpath(excel.getData(3, 705, 1)));

			// Check Weather the Top 5 daily sale available or not
			if (wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed()) {
				test.log(LogStatus.PASS, "Here Top 5 Daily Sale Report available for Specific Date");

				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				System.out.println("Time in Millisecomds : " + totalTime);
//				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//						long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime / 1000) / 6;
				System.out.println("MilSEc to Mins basic conversion : " + ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : " + totalTime);

				// No.of rows
				List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr[\"+ rows.size() + \"]/td[2]/span"));

				// Replace all commo's with empty space
				String expected1 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale_Report_Net_Sale element into float value
				float expect1 = Float.parseFloat(expected1);
				Thread.sleep(5000);
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
//				Thread.sleep(5000);
//				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
//				Thread.sleep(5000);
//				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
//				Thread.sleep(5000);

				Thread.sleep(8000);
				// Check weather the Net Sale is correct or not
				if (driver
						.findElement(
								By.xpath("//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+ rows.size() + "]/td[2]/span"))
						.getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale"))) {
					test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Net Sale");

					// Get the Total value of net Sale Amount
					String actualText1 = driver
							.findElement(By.xpath(
									"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+ rows.size() + "]/td[2]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Net Sale Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Net Sale Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Net Sale Value is : " + actual);
				}

				else if (expect1 == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Net Sale
					String actualText = driver
							.findElement(By.xpath(
									"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+ rows.size() + "]/td[2]/span"))
							.getText();

					System.out.println("The Actual Net Sale value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Net Sale value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Sale Amount");

					// Get the Total value of Sale Amount
					String actualText1 = driver
							.findElement(By.xpath(
									"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+ rows.size() + "]/td[2]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the net sale amount Total element into float
					// value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - expect1;

					// Print the different value
					System.out.println("Net Sale Value different is : " + different);

					test.log(LogStatus.FAIL, "Net Sale Value different is : " + different);
				}

				// Check weather the Tax Report is correct or not
				if (driver.findElement(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["+ rows.size() + "]/td[3]/span"))
						.getText().equals(string_Value_Of_Tax_Amount)) {
					test.log(LogStatus.PASS, "Actual and Expected Daily Sale reports are same for Tax");

					// Get the Total value of Tax
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[3]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Tax Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Tax Value is : " + actual);
				}

				else if (gobal_Total_Tax_Amount == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Tax
					String actualText = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[3]/span"))
							.getText();

					System.out.println("The Actual Tax value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Tax value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Expected Daily Sale reports are different for Tax");

					// Get the Total value of Tax
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[3]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - gobal_Total_Tax_Amount;

					// Print the different value
					System.out.println("Tax Value different is : " + different);

					test.log(LogStatus.FAIL, "Tax Value different is : " + different);
				}

			}
		}

		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
	}

	public void SaleRecap_Sale_Report_Tax_Validation(WebDriver driver) throws Exception {

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(3000);
		// Enter the SaleRecap Url
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "report/saleRecap");

		Thread.sleep(10000);
		try {
			// Check weather the Sale Recap Report page is loaded or not
			if (driver.findElement(By.xpath(excel.getData(1, 1091, 1))).getText().equalsIgnoreCase("Sale recap Type")) {
				test.log(LogStatus.PASS, "Sale Recap Report page loaded successfully");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			} else {
				test.log(LogStatus.FAIL, "Sale Recap Report page loaded fail");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
		} catch (Exception e) {
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.FAIL, test.addScreenCapture(s));
		}

		Thread.sleep(15000);
		// Click the Select Recap Type option
		driver.findElement(By.xpath("//select[@ng-model='query.recapFilter']/../div/a")).click();
		// Enter the required department
		driver.findElement(By.xpath("//select[@ng-model='query.recapFilter']/../div/div/div/input"))
				.sendKeys("Time period");
		// Press the Enter Key
		driver.findElement(By.xpath("//select[@ng-model='query.recapFilter']/../div/div/div/input"))
				.sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		// Click the Employee option
		driver.findElement(By.xpath("//select[@ng-model='query.selectedUserID']/../div/a")).click();
		// Enter the required employee
		driver.findElement(By.xpath("//select[@ng-model='query.selectedUserID']/../div/div/div/input")).sendKeys("All");
		// Press the Enter Key
		driver.findElement(By.xpath("//select[@ng-model='query.selectedUserID']/../div/div/div/input"))
				.sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		// Click the Time Period option
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
		// Enter the required Time Period
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys("Date Range");
		// Press the Enter Key
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath("//input[@ng-model='query.fromDate']/.")).clear();
		// Enter the date
		driver.findElement(By.xpath("//input[@ng-model='query.fromDate']/."))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath("//input[@ng-model='query.toDate']/.")).clear();
		// Enter the date
		driver.findElement(By.xpath("//input[@ng-model='query.toDate']/."))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

		Thread.sleep(2000);
		// Click the Run Button
		driver.findElement(By.xpath(excel.getData(1, 572, 1))).click();
		long start = System.currentTimeMillis();

//		WebDriverWait wait1=new WebDriverWait(driver, 30);
//		if(driver.getPageSource().contains("CHECK STATS"))
//		WebElement ele11=driver.findElement(By.xpath("//b[.='CHECK STATS']/."));

		Thread.sleep(15000);
		// Check weather the Check Status
		if (driver.getPageSource().contains("CHECK STATS")) {
			test.log(LogStatus.PASS, "Check Stats field is available");

			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			System.out.println("Time in Millisecomds : " + totalTime);
			double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//			long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
			long ActualfinishTimeDouble1 = (totalTime / 1000) / 6;
			System.out.println("MilSEc to Mins basic conversion : " + ActualfinishTimeDouble1);
//			test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
			test.log(LogStatus.INFO, "Time in Millisecomds : " + totalTime);

			Thread.sleep(5000);
			// Check the SALES
			if (driver.getPageSource().contains("SALES")) {
				test.log(LogStatus.INFO, "SALES is available");

				System.out.println("SALES is available");

				// Replace all commo's with empty space
				String expected2 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale_Report_Net_Sale element into float value
				float expect2 = Float.parseFloat(expected2);

				// Check weather the Net Sales total is same or not
				if (driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText()
						.equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale"))) {
					test.log(LogStatus.PASS, "Actual and Expected Net Sales Values are same");

					// Get the Total value of Net Sales
					String actualText1 = driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Net Sales element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Net Sales Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Net Sales Value is : " + actual);
				}

				else if (expect2 == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Net Sales
					String actualText = driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText();

					System.out.println("The Actual Net Sales value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Net Sales value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Expected Net Sales values are different");

					// Get the Total value of Gross Sales
					String actualText1 = driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Net Sales Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - expect2;

					// Print the different value
					System.out.println("Net Sales Value different is : " + different);

					test.log(LogStatus.FAIL, "Net Sales Value different is : " + different);
				}

			} else {
				test.log(LogStatus.INFO, "Sales is not available");

				driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(By.tagName("html")).sendKeys(Keys.DOWN);

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));

				System.out.println("Sales is not available");
			}
		}

		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);

		Thread.sleep(20000);
		// Check weather the Taxes Status
		if (driver.getPageSource().contains("TAXES")) {

			Thread.sleep(2000);
			// Check the Sale Tax
			if (driver
					.findElement(By
							.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]"))
					.getText().equals(string_Value_Of_Tax_Amount)) {
				test.log(LogStatus.PASS, "Actual and Expected sale Tax amount are same");

				// Get the Total value of Sale Tax
				String actualText1 = driver
						.findElement(By.xpath(
								"//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]"))
						.getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale Tax Total element into float value
				float actual = Float.parseFloat(actualText);

				// Print the actual value
				System.out.println("The Actual Rounding Off Value is : " + actual);

				test.log(LogStatus.PASS, "The Actual Sale Tax Amount Value is : " + actual);

			}

			else if (gobal_Total_Tax_Amount == unknownValue) {
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");

				// Get the Total value of Check Count
				String actualText = driver
						.findElement(By.xpath(
								"//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]"))
						.getText();

				System.out.println("The Actual Sale tax amount value is : " + actualText);

				test.log(LogStatus.INFO, "The Actual Sale Tax Amount value is : " + actualText);
			}

			else {
				test.log(LogStatus.FAIL, "Actual and Expected Rounding Off are different");

				// Get the Total value of Sale Tax
				String actualText1 = driver
						.findElement(By.xpath(
								"//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]"))
						.getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Total Sale Tax element into float value
				float actual = Float.parseFloat(actualText);

				// Get the different
				float different = actual - gobal_Total_Tax_Amount;

				// Print the different value
				System.out.println("Sale Tax Amount Value different is : " + different);

				test.log(LogStatus.FAIL, "Sale Tax Amount Value different is : " + different);

			}
		}

		Thread.sleep(15000);
		// Check weather the Taxes Status
		if (driver.getPageSource().contains("TAXES")) {

			Thread.sleep(2000);
			// Check the Sale Tax Exempt
			if (driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]"))
					.getText().equals(string_Value_Of_tax_Exempt)) {
				test.log(LogStatus.PASS, "Actual and Expected sale Tax amount are same");

				// Get the Total value of Sale Tax Exempt
				String actualText1 = driver
						.findElement(
								By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]"))
						.getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale Tax Exempt Total element into float
				// value
				float actual = Float.parseFloat(actualText);

				// Print the actual value
				System.out.println("The Actual Sale Tax Exempt amount Value is : " + actual);

				test.log(LogStatus.PASS, "The Actual Sale Tax Exempt Amount Value is : " + actual);

			}

			else if (gobal_Total_Tax_Exempt == unknownValue) {
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");

				// Get the Total value of Check Count
				String actualText = driver
						.findElement(
								By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]"))
						.getText();

				System.out.println("The Actual Sale tax Exempt amount value is : " + actualText);

				test.log(LogStatus.INFO, "The Actual Sale Tax Exempt Amount value is : " + actualText);
			}

			else {
				test.log(LogStatus.FAIL, "Actual and Expected Sale Tax Exempt are different");

				// Get the Total value of Sale Tax Exempt
				String actualText1 = driver
						.findElement(
								By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]"))
						.getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Total Sale Tax Exempt element into float
				// value
				float actual = Float.parseFloat(actualText);

				// Get the different
				float different = actual - gobal_Total_Tax_Exempt;

				// Print the different value
				System.out.println("Sale Tax Exempt Amount Value different is : " + different);

				test.log(LogStatus.FAIL, "Sale Tax Exempt Amount Value different is : " + different);

			}
		}

		Thread.sleep(15000);
		// Check weather the Taxes Status
		if (driver.getPageSource().contains("TAXES")) {
			if (driver
					.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]"))
					.getText().equals(string_Value_Of_tax_Round_Off_Value)) {
				test.log(LogStatus.PASS, "Actual and Expected Rounding Off are same");

				// Get the Total value of Rounding Off
				String actualText1 = driver
						.findElement(
								By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]"))
						.getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Rounding Off Total element into float value
				float actual = Float.parseFloat(actualText);

				// Print the actual value
				System.out.println("The Actual Rounding Off Value is : " + actual);

				test.log(LogStatus.PASS, "The Actual Rounding Off Value is : " + actual);

			}

			else if (gobal_actual_Tax_RoundOff == unknownValue) {
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");

				// Get the Total value of Check Count
				String actualText = driver
						.findElement(
								By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]"))
						.getText();

				System.out.println("The Actual Rounding Off value is : " + actualText);

				test.log(LogStatus.INFO, "The Actual Rounding Off value is : " + actualText);
			}

			else {
				test.log(LogStatus.FAIL, "Actual and Expected Rounding Off are different");

				// Get the Total value of Check Count
				String actualText1 = driver
						.findElement(
								By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]"))
						.getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Check Count Total element into float value
				float actual = Float.parseFloat(actualText);

				// Get the different
				float different = actual - gobal_actual_Tax_RoundOff;

				// Print the different value
				System.out.println("Rounding Off Value different is : " + different);

				test.log(LogStatus.FAIL, "Rounding Off Value different is : " + different);
			}
		}
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
	}

	public void Cashier_Sale_Report_Tax_Validation(WebDriver driver) throws Exception {

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		/*
		 * //Click the Reports option
		 * driver.findElement(By.xpath("//span[.='Reports']")).click(); // Create
		 * instance of Java script executor
		 */
		JavascriptExecutor je = (JavascriptExecutor) driver;
		// Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Sale']"));
		// Scroll the page till the Sale option present
		je.executeScript("arguments[0].scrollIntoView(true);", element);
		/*
		 * //Click the Sale Option
		 * driver.findElement(By.xpath("//span[.='Sale']")).click();
		 */

		Thread.sleep(3000);
		// Enter the Cashierout Url
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "report/cashierOut");

		Thread.sleep(3000);
		try {
			// Check weather the Cashier Out Report page is loaded or not
			if (driver.findElement(By.xpath(excel.getData(3, 1058, 1))).getText().equalsIgnoreCase("Cashier out")) {
				test.log(LogStatus.PASS, "Cashier Out Report page loaded successfully");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			} else {
				test.log(LogStatus.FAIL, "Cashier Out Report page loaded fail");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
		} catch (Exception e) {
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.INFO, test.addScreenCapture(s));
		}

		Thread.sleep(3000);
		// Click the Employee option
		driver.findElement(By.xpath("//select[@ng-model='query.selectedUserID']/../div/a")).click();
		// Enter the required employee
		driver.findElement(By.xpath("//select[@ng-model='query.selectedUserID']/../div/div/div/input")).sendKeys("All");
		// Press the Enter Key
		driver.findElement(By.xpath("//select[@ng-model='query.selectedUserID']/../div/div/div/input"))
				.sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		// Click the Time Period option
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
		// Enter the required Time Period
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys("Date Range");
		// Press the Enter Key
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath("//input[@ng-model='query.fromDate']/.")).clear();
		// Enter the date
		driver.findElement(By.xpath("//input[@ng-model='query.fromDate']/."))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath("//input[@ng-model='query.toDate']/.")).clear();
		// Enter the date
		driver.findElement(By.xpath("//input[@ng-model='query.toDate']/."))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

		Thread.sleep(2000);
		// Click the Run Button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		long start = System.currentTimeMillis();
		Thread.sleep(10000);
//	WebDriverWait wait1=new WebDriverWait(driver, 30);
//	WebElement ele11=driver.findElement(By.xpath("//b[.='CHECK STATS']/."));

		// Check weather the Check Status
		if (driver.getPageSource().contains("CHECK STATS")) {
			test.log(LogStatus.PASS, "Check Stats field is available");

			long finish = System.currentTimeMillis();
			long totalTime = finish - start;
			System.out.println("Time in Millisecomds : " + totalTime);
			double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//		long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
			long ActualfinishTimeDouble1 = (totalTime / 1000) / 6;
			System.out.println("MilSEc to Mins basic conversion : " + ActualfinishTimeDouble1);
//		test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
			test.log(LogStatus.INFO, "Time in Millisecomds : " + totalTime);

		}
		Thread.sleep(3000);
		// Check the SALES
		if (driver.getPageSource().contains("SALES")) {
			// Replace all commo's with empty space
			String expected2 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

			// Convert the String value of the Sale_Report_Net_Sale element into float value
			float expect2 = Float.parseFloat(expected2);

			// Check weather the Net Sales total is same or not
			if (driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText()
					.equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale"))) {
				test.log(LogStatus.PASS, "Actual and Expected Net Sales Values are same");

				// Get the Total value of Net Sales
				String actualText1 = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Net Sales element into float value
				float actual = Float.parseFloat(actualText);

				// Print the actual value
				System.out.println("The Actual Net Sales Value is : " + actual);

				test.log(LogStatus.PASS, "The Actual Net Sales Value is : " + actual);
			}

			else if (expect2 == unknownValue) {
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");

				// Get the Total value of Net Sales
				String actualText = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();

				System.out.println("The Actual Net Sales value is : " + actualText);

				test.log(LogStatus.INFO, "The Actual Net Sales value is : " + actualText);
			}

			else {
				test.log(LogStatus.FAIL, "Actual and Expected Net Sales values are different");

				// Get the Total value of Gross Sales
				String actualText1 = driver.findElement(By.xpath(excel.getData(3, 765, 1))).getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Net Sales Total element into float value
				float actual = Float.parseFloat(actualText);

				// Get the different
				float different = actual - expect2;

				// Print the different value
				System.out.println("Net Sales Value different is : " + different);

				test.log(LogStatus.FAIL, "Net Sales Value different is : " + different);
			}

		}
		
		
		Thread.sleep(2000);
		WebElement element1 = driver
				.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);

		Thread.sleep(15000);
		// Check weather the Taxes Status
		if (driver.getPageSource().contains("TAXES")) {

			Thread.sleep(2000);
			// Check the Sale Tax
			if (driver
					.findElement(By
							.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]"))
					.getText().equals(string_Value_Of_Tax_Amount)) {
				test.log(LogStatus.PASS, "Actual and Expected sale Tax amount are same");

				// Get the Total value of Sale Tax
				String actualText1 = driver
						.findElement(By.xpath(
								"//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]"))
						.getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale Tax Total element into float value
				float actual = Float.parseFloat(actualText);

				// Print the actual value
				System.out.println("The Actual Rounding Off Value is : " + actual);

				test.log(LogStatus.PASS, "The Actual Sale Tax Amount Value is : " + actual);

			}

			else if (gobal_Total_Tax_Amount == unknownValue) {
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");

				// Get the Total value of Check Count
				String actualText = driver
						.findElement(By.xpath(
								"//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]"))
						.getText();

				System.out.println("The Actual Sale tax amount value is : " + actualText);

				test.log(LogStatus.INFO, "The Actual Sale Tax Amount value is : " + actualText);
			}

			else {
				test.log(LogStatus.FAIL, "Actual and Expected Rounding Off are different");

				// Get the Total value of Sale Tax
				String actualText1 = driver
						.findElement(By.xpath(
								"//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[4]"))
						.getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Total Sale Tax element into float value
				float actual = Float.parseFloat(actualText);

				// Get the different
				float different = actual - gobal_Total_Tax_Amount;

				// Print the different value
				System.out.println("Sale Tax Amount Value different is : " + different);

				test.log(LogStatus.FAIL, "Sale Tax Amount Value different is : " + different);

			}
		}

		Thread.sleep(15000);
		// Check weather the Taxes Status
		if (driver.getPageSource().contains("TAXES")) {

			Thread.sleep(2000);
			// Check the Sale Tax Exempt
			if (driver.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]"))
					.getText().equals(string_Value_Of_tax_Exempt)) {
				test.log(LogStatus.PASS, "Actual and Expected sale Tax amount are same");

				// Get the Total value of Sale Tax Exempt
				String actualText1 = driver
						.findElement(
								By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]"))
						.getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale Tax Exempt Total element into float
				// value
				float actual = Float.parseFloat(actualText);

				// Print the actual value
				System.out.println("The Actual Sale Tax Exempt amount Value is : " + actual);

				test.log(LogStatus.PASS, "The Actual Sale Tax Exempt Amount Value is : " + actual);

			}

			else if (gobal_Total_Tax_Exempt == unknownValue) {
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");

				// Get the Total value of Check Count
				String actualText = driver
						.findElement(
								By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]"))
						.getText();

				System.out.println("The Actual Sale tax Exempt amount value is : " + actualText);

				test.log(LogStatus.INFO, "The Actual Sale Tax Exempt Amount value is : " + actualText);
			}

			else {
				test.log(LogStatus.FAIL, "Actual and Expected Sale Tax Exempt are different");

				// Get the Total value of Sale Tax Exempt
				String actualText1 = driver
						.findElement(
								By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Total Tax Collected')]/div[5]"))
						.getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Total Sale Tax Exempt element into float
				// value
				float actual = Float.parseFloat(actualText);

				// Get the different
				float different = actual - gobal_Total_Tax_Exempt;

				// Print the different value
				System.out.println("Sale Tax Exempt Amount Value different is : " + different);

				test.log(LogStatus.FAIL, "Sale Tax Exempt Amount Value different is : " + different);

			}
		}

		Thread.sleep(15000);
//		// Check weather the Taxes Status
		if (driver.getPageSource().contains("TAXES")) {
			if (driver
					.findElement(By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]"))
					.getText().equals(string_Value_Of_tax_Round_Off_Value)) {
				test.log(LogStatus.PASS, "Actual and Expected Rounding Off are same");

				// Get the Total value of Rounding Off
				String actualText1 = driver
						.findElement(
								By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]"))
						.getText();

				//Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Rounding Off Total element into float value
				float actual = Float.parseFloat(actualText);

				// Print the actual value
				System.out.println("The Actual Rounding Off Value is : " + actual);

				test.log(LogStatus.PASS, "The Actual Rounding Off Value is : " + actual);

			}

			else if (gobal_actual_Tax_RoundOff == unknownValue) {
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");

				// Get the Total value of Check Count
				String actualText = driver
						.findElement(
								By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]"))
						.getText();

				System.out.println("The Actual Rounding Off value is : " + actualText);

				test.log(LogStatus.INFO, "The Actual Rounding Off value is : " + actualText);
			}

			else {
				test.log(LogStatus.FAIL, "Actual and Expected Rounding Off are different");

				// Get the Total value of Check Count
				String actualText1 = driver
						.findElement(
								By.xpath("//label[contains(.,'TAXES')]/../../div[contains(.,'Rounding Off')]/div[4]"))
						.getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Check Count Total element into float value
				float actual = Float.parseFloat(actualText);

				// Get the different
				float different = actual - gobal_actual_Tax_RoundOff;

				// Print the different value
				System.out.println("Rounding Off Value different is : " + different);

				test.log(LogStatus.FAIL, "Rounding Off Value different is : " + different);
			}
		}
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
	}

	public void SaleSummary_Sale_Report_Tax_Validation(WebDriver driver) throws Exception {

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);

		Thread.sleep(3000);
		// Enter the Categories Url
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "report/saleSummary");

		Thread.sleep(4000);
		// Click the right arrow button
		driver.findElement(By.xpath(excel.getData(3, 753, 1))).click();
		driver.findElement(By.xpath(excel.getData(3, 753, 1))).click();
		driver.findElement(By.xpath(excel.getData(3, 753, 1))).click();
		driver.findElement(By.xpath(excel.getData(3, 753, 1))).click();

		// driver.findElement(By.xpath(excel.getData(3, 753, 1))).click();
		// driver.findElement(By.xpath(excel.getData(3, 753, 1))).click();

		Thread.sleep(3000);
		try {
			// Check weather the Sale Summary page is opened or not
			if (driver.findElement(By.xpath(excel.getData(3, 1566, 1))).getText().equalsIgnoreCase("Sale Summary")) {
				test.log(LogStatus.PASS, "Sale Summary Report Page Loaded Successfully");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			} else {
				test.log(LogStatus.FAIL, "Sale Summary Report Page Loaded Fail");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
		} catch (Exception e) {
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.FAIL, test.addScreenCapture(s));
		}

		Thread.sleep(2000);

		Thread.sleep(3000);
		// Click the Employee option
		driver.findElement(By.xpath("//select[@ng-model='query.selectedEmployee']/../div/a")).click();
		// Enter the required employee
		driver.findElement(By.xpath("//select[@ng-model='query.selectedEmployee']/../div/div/div/input"))
				.sendKeys("All");
		// Press the Enter Key
		driver.findElement(By.xpath("//select[@ng-model='query.selectedEmployee']/../div/div/div/input"))
				.sendKeys(Keys.ENTER);

		/*
		 * Thread.sleep(3000); //Click the Check Number Search option
		 * driver.findElement(By.xpath(excel.getData(3, 1585, 1))).clear(); //Enter the
		 * Check Number driver.findElement(By.xpath(excel.getData(3, 1585,
		 * 1))).sendKeys("0-007");
		 */

		Thread.sleep(3000);
		// Click the Time Period option
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
		// Enter the required Time Period
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys("Date Range");
		// Press the Enter Key
		driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input"))
				.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath("//input[@ng-model='query.fromDate']/.")).clear();
		// Enter the date
		driver.findElement(By.xpath("//input[@ng-model='query.fromDate']/."))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		Thread.sleep(2000);
		// Clear the date field
		driver.findElement(By.xpath("//input[@ng-model='query.toDate']/.")).clear();
		// Enter the date
		driver.findElement(By.xpath("//input[@ng-model='query.toDate']/."))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

		Thread.sleep(3000);
		// Click the Run Button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();

		long start = System.currentTimeMillis();

//		WebDriverWait wait1=new WebDriverWait(driver, 30);
//		WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 1077, 1)));

		Thread.sleep(40000);
		try {
		// Check weather the report is available for the selected time period
		if (driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed()) {
			test.log(LogStatus.FAIL, "Sale Summary Report is not available for the Required Date Range");

			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.INFO, test.addScreenCapture(s));
		}
		}
		
		catch (Exception e) {
		
			test.log(LogStatus.PASS, "Sale Summary Report available for the Required Date Range");
			Thread.sleep(15000);
			// Check weather the table format report is available or not
			if (driver.findElement(By.xpath(excel.getData(3, 1077, 1))).isDisplayed()) {
				test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");

			}
		
					// TODO: handle exception
				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				System.out.println("Time in Millisecomds : " + totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime / 1000) / 6;
				System.out.println("MilSEc to Mins basic conversion : " + ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : " + totalTime);

				Thread.sleep(3000);
				// No.of rows
				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1078, 1)));

				// Replace all commo's with empty space
				String expected1 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale_Report_Net_Sale element into float value
				float expect1 = Float.parseFloat(expected1);

				// Replace all commo's with empty space
				String expected3 = Utility.getReportPropertyUser("Sale_Report_Inclusive_Tax").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale_Report_Inclusive_Tax element into float
				// value
				float expect3 = Float.parseFloat(expected3);

				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(8000);
				
				// Check weather the Transaction Date Report is correct or not
				if (driver.findElement(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
								+ rows.size() + "]/td[6]/span"))
						.getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale"))) {
					test.log(LogStatus.PASS,
							"Actual and Expected Sale Summary reports are same for Net Transaction Date");

					// Get the Total value of Net Transaction Date
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[6]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Net Transaction Date Total element into float
					// value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Net Transaction Date Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Net Transaction Date Value is : " + actual);
				}

				else if (expect1 == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Check Count
					String actualText = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[6]/span"))
							.getText();

					System.out.println("The Actual Net Transaction Date value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Net Transaction Date value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL,
							"Actual and Expected Sale Summary reports are different for Net Transaction Date");

					// Get the Total value of Transaction Date
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[6]/span"))
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

				// Check weather the Inclusive Tax Report is correct or not
				if (driver.findElement(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
								+ rows.size() + "]/td[8]/span"))
						.getText().equals(Utility.getReportPropertyUser("Sale_Report_Inclusive_Tax"))) {
					test.log(LogStatus.PASS, "Actual and Expected Sale Summary reports are same for Inclusive Tax");

					// Get the Total value of Inclusive Tax
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[8]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Inclusive Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Inclusive Tax Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Inclusive Tax Value is : " + actual);
				}

				else if (expect3 == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Tax
					String actualText = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[8]/span"))
							.getText();

					System.out.println("The Actual Inclusive Tax value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Inclusive Tax value is : " + actualText);
				}

				else {
					test.log(LogStatus.INFO,
							"Actual and Expected Sale Summary reports are different for Inclusive Tax");

					// Get the Total value of Tax
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[8]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - expect3;

					// Print the different value
					System.out.println("Tax Value different is : " + different);

					test.log(LogStatus.INFO, "Tax Value different is : " + different);
				}

				// Check weather the Tax Report is correct or not
				if (driver.findElement(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
								+ rows.size() + "]/td[7]/span"))
						.getText().equals(Utility.getReportPropertyUser(string_Value_Of_Tax_Amount))) {
					test.log(LogStatus.PASS, "Actual and Expected Tax reports are same for Quantity");

					// Get the Total value of Tax
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[7]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Print the actual value
					System.out.println("The Actual Tax Value is : " + actual);

					test.log(LogStatus.PASS, "The Actual Tax Value is : " + actual);
				}

				else if (gobal_Total_Tax_Amount == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact expected value");

					// Get the Total value of Quantity
					String actualText = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[7]/span"))
							.getText();

					System.out.println("The Actual Tax value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Tax value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Expected Sale Summary reports are different for Tax");

					// Get the Total value of Quantity
					String actualText1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive table-condensed ng-scope ng-table']/tbody/tr["
									+ rows.size() + "]/td[7]/span"))
							.getText();

					// Replace all commo's with empty space
					String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Tax Total element into float value
					float actual = Float.parseFloat(actualText);

					// Get the different
					float different = actual - gobal_Total_Tax_Amount;

					// Print the different value
					System.out.println("Tax Value different is : " + different);

					test.log(LogStatus.FAIL, "Tax Value different is : " + different);
				}

			}

		
		}
	

//	@Test(priority = 4, enabled = false)
//	public void Weekly_SaleSummary_Sale_Report_Tax_Validation(WebDriver driver) throws Exception {
//
//		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
//			
//		 FileInputStream fis = new FileInputStream(src);
//				
//		 XSSFWorkbook wb = new XSSFWorkbook(fis);
//				
//	     XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
//	     ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
//	
//	     Thread.sleep(5000);
//			//Enter the Categories Url
//			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"report/weeklySale");
//			
//			// Create instance of Java script executor
//			JavascriptExecutor je1 = (JavascriptExecutor) driver;
//			//Identify the WebElement which will appear after scrolling down
//			WebElement element1 = driver.findElement(By.xpath("//span[.=' Weekly Summary ']"));
//			//Scroll the page till the Weekly Summary option present
//			je1.executeScript("arguments[0].scrollIntoView(true);",element1); 
//	
//			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
//			Thread.sleep(2000);
//			driver.findElement(By.xpath(excel.getData(1, 1454, 1))).click();
//			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/div/scrollable-tabset/div/button[2]")).click();
//			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/div/scrollable-tabset/div/button[2]")).click();
//			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/div/scrollable-tabset/div/button[2]")).click();
//			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/div/scrollable-tabset/div/button[2]")).click();
//			try
//			{
//			//Check weather the Weekly Summary Report page is loaded or not
//			if(driver.findElement(By.xpath(excel.getData(1, 1454, 1))).getText().equalsIgnoreCase("Weekly Summary"))
//			{
//				test.log(LogStatus.PASS, "Weekly Summary Report page loaded successfully");
//			
//				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//				String s="data:image/png;base64,"+scnShot;
//				test.log(LogStatus.INFO,test.addScreenCapture(s));
//			}
//			else
//			{
//				test.log(LogStatus.FAIL, "Weekly Summary Report page loaded fail");
//			
//				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//				String s="data:image/png;base64,"+scnShot;
//				test.log(LogStatus.INFO,test.addScreenCapture(s));
//			}
//			}
//			catch(Exception e)
//			{
//				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//				String s="data:image/png;base64,"+scnShot;
//				test.log(LogStatus.FAIL,test.addScreenCapture(s));
//			}
//
//			Thread.sleep(5000);
//	}

	public void online_Sale_Report_Tax_Validation(WebDriver driver) throws Exception {

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);

		Thread.sleep(3000);
		// Enter the Categories Url
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "report/onlineSales");

		// driver.findElement(By.xpath(excel.getData(3, 753, 1))).click();
		// driver.findElement(By.xpath(excel.getData(3, 753, 1))).click();

		Thread.sleep(3000);
		try {
			// Check weather the Sale Summary page is opened or not
			if (driver.findElement(By.xpath("//form[@ng-show='openFilter']//div/div/label[.='Source']")).getText()
					.equalsIgnoreCase("Source")) {
				test.log(LogStatus.PASS, "Online sale Report Page Loaded Successfully");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			} else {
				test.log(LogStatus.FAIL, "Online sale Report Page Loaded Fail");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
		} catch (Exception e) {
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.FAIL, test.addScreenCapture(s));
		}

		Thread.sleep(2000);

		Thread.sleep(10000);

		// Click on the Order type dropdwon.
		driver.findElement(By.xpath("//div//select[@ng-model='query.deliveryType']/../div/a")).click();
		// clear on the Order type dropdown.
		driver.findElement(By.xpath("//div//select[@ng-model='query.deliveryType']/../div/div//input[@type='text']"))
				.clear();
		// enter on the Order type dropdown.
		driver.findElement(By.xpath("//div//select[@ng-model='query.deliveryType']/../div/div//input[@type='text']"))
				.sendKeys("All");
		Thread.sleep(2000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ENTER);
		// Click on the Payment type dropdwon.
		driver.findElement(By.xpath("//div//select[@ng-model='query.paymentType']/../div/a")).click();
		// clear on the Payment type dropdown.
		driver.findElement(By.xpath("//div//select[@ng-model='query.paymentType']/../div/div//input[@type='text']"))
				.clear();
		// enter on the Payment type dropdown.
		driver.findElement(By.xpath("//div//select[@ng-model='query.paymentType']/../div/div//input[@type='text']")).sendKeys("All");
		Thread.sleep(2000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ENTER);
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
		// Click the Run Button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();

		long start = System.currentTimeMillis();

//		WebDriverWait wait1=new WebDriverWait(driver, 30);
//		WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 1077, 1)));

		Thread.sleep(10000);
		// Check weather the report is available for the selected time period
		if (driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed()) {
			test.log(LogStatus.FAIL, "Online sale Report is not available for the Required Date Range");

			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.INFO, test.addScreenCapture(s));
		}

		else {
			test.log(LogStatus.PASS, "Online sale Report available for the Required Date Range");
			Thread.sleep(15000);
			// Check weather the table format report is available or not
			if (driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']"))
					.isDisplayed()) {
				test.log(LogStatus.PASS, "Table Format Report is available for the Required Date Range");

				long finish = System.currentTimeMillis();
				long totalTime = finish - start;
				System.out.println("Time in Millisecomds : " + totalTime);
				double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//				long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
				long ActualfinishTimeDouble1 = (totalTime / 1000) / 6;
				System.out.println("MilSEc to Mins basic conversion : " + ActualfinishTimeDouble1);
				test.log(LogStatus.INFO, "Time in Millisecomds : " + totalTime);

				Thread.sleep(3000);
				// No.of rows
				List<WebElement> rows = driver
						.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr"));

				for (int i = 2; i < rows.size(); i++) {

					String text1 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive']/tbody/tr[" + i + "]/td[9]"))
							.getText();

					float sale_Amount = Float.parseFloat(text1);

					float sum_Sale_Amount = +sale_Amount;

					test.log(LogStatus.INFO, "The total value of the sale amoount in online sale: " + sum_Sale_Amount);

				}

				for (int i = 2; i < rows.size(); i++) {

					String text2 = driver.findElement(By.xpath(
							"//table[@class='table table-bordered ng-table-responsive']/tbody/tr[" + i + "]/td[11]"))
							.getText();

					float Tax_Amount = Float.parseFloat(text2);

					float sum_Tax_Amount = +Tax_Amount;

					this.gobal_Tax_Amount_Web = sum_Tax_Amount;

					test.log(LogStatus.INFO, "The total value of the Tax amoount in online sale: " + sum_Tax_Amount);
				}

				Thread.sleep(8000);
				// Enter the Categories Url
				driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "taxPerServiceType");
				Thread.sleep(4000);
				// click on the time period option.
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/a")).click();
				// clear the input box in time period.
				driver.findElement(
						By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input[@type='text']")).clear();
				// enter the time period
				driver.findElement(
						By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input[@type='text']"))
						.sendKeys("Date Range");
				
				driver.findElement(By.xpath("//select[@ng-model='query.dateOption']/../div/div/div/input[@type='text']")).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
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

				Thread.sleep(5000);
				// Click the Run Button
				driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
				
				
				Thread.sleep(30000);
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(1000);
				String tax_for_Web = driver.findElement(By.xpath(
						"(//table[@class='table table-bordered ng-table-responsive']//tbody/tr/th[@class='ng-binding'])[3]"))
						.getText().replaceAll("[a-z A-Z $ ₹ £ , :]", "");
				
				System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss - "+ tax_for_Web);

				float Tax_Amount_Web = Float.parseFloat(tax_for_Web);

				if (Tax_Amount_Web == gobal_Tax_Amount_Web) {

					test.log(LogStatus.PASS, "The Tax value is shown as correctly");

					test.log(LogStatus.INFO, "Tax amount for web: " + Tax_Amount_Web);

				} else {

					test.log(LogStatus.FAIL, "The tax value is shown as Wrongly");

					float difference = Tax_Amount_Web - gobal_Tax_Amount_Web;

					test.log(LogStatus.INFO, "Tax amount for web ordre difference is: " + difference);
				}

			}
		}
	}

	public void Comparision_Report_Tax_Validation(WebDriver driver) throws Exception {
	
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);

		Thread.sleep(3000);
		// Enter the Categories Url
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "comparisionReport");
		
		Thread.sleep(5000);
		try
		{
		//Check Comparision Report page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 1359, 1))).getText().equalsIgnoreCase("Comparison Report"))
		{
			test.log(LogStatus.PASS,"Comparison Report page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL,"Comparison Report page loaded Failed");
		
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
		Thread.sleep(3000);
		 Thread.sleep(5000);
			//Select the Time Period option   
		    driver.findElement(By.xpath(excel.getData(3, 1360, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 1361, 1))).sendKeys("Date Range");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 1361, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
			//clear the data in date from cloumn
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.fromDate'])[1]")).clear();
		    //enter the data in date from column.
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.fromDate'])[1]")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			Thread.sleep(2000);
			//clear the data in date to cloumn
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.toDate'])[1]")).clear();
			//enter the data in date to column.
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.toDate'])[1]")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		    
		    Thread.sleep(5000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 1362, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 1363, 1))).sendKeys("Date Range");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 1363, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
			//clear the data in date from cloumn
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.fromDate'])[2]")).clear();
		    //enter the data in date from column.
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.fromDate'])[2]")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			Thread.sleep(2000);
			//clear the data in date to cloumn
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.toDate'])[2]")).clear();
			//enter the data in date to column.
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.toDate'])[2]")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		    
		    Thread.sleep(5000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 1364, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 1365, 1))).sendKeys("Date Range");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 1365, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
			//clear the data in date from cloumn
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.fromDate'])[3]")).clear();
		    //enter the data in date from column.
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.fromDate'])[3]")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			Thread.sleep(2000);
			//clear the data in date to cloumn
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.toDate'])[3]")).clear();
			//enter the data in date to column.
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.toDate'])[3]")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		   
		    Thread.sleep(5000);
			//Select the Time Period option
		    driver.findElement(By.xpath(excel.getData(3, 1367, 1))).click();
		    //Enter the required Period of time for Specific date
		    driver.findElement(By.xpath(excel.getData(3, 1368, 1))).sendKeys("Date Range");
		    //Press the Enter key
		    driver.findElement(By.xpath(excel.getData(3, 1368, 1))).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);
			//clear the data in date from cloumn
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.fromDate'])[4]")).clear();
		    //enter the data in date from column.
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.fromDate'])[4]")).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
			Thread.sleep(2000);
			//clear the data in date to cloumn
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.toDate'])[4]")).clear();
			//enter the data in date to column.
			driver.findElement(By.xpath("(//div//div//input[@ng-model='picker.toDate'])[4]")).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		    
		    
		    Thread.sleep(3000);
		    //Click the Run
		    driver.findElement(By.cssSelector(excel.getData(3, 974 ,4))).click();
		    
		    long start = System.currentTimeMillis();
		    
		    Thread.sleep(45000);
		    driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		    

			try {
			
			// Check weather the report is available for the selected time period
			if (driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed()) {
				test.log(LogStatus.FAIL, "Online sale Report is not available for the Required Date Range");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
			}
			catch (Exception e) 
				
			{
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			    List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[1]/div"));
			    
			    String sale_Amount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[2]/div[2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "");
			    
			    // Replace all commo's with empty space
				String expected1 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale_Report_Net_Sale element into float value
				float expect1 = Float.parseFloat(expected1);
			    
			    
			    float sale_Amount1 = Float.parseFloat(sale_Amount);
				
				test.log(LogStatus.INFO, "The total value of the sale amoount in online sale: "+sale_Amount1);
				
			if (driver.findElement(By.xpath(
					"//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[2]/div[2]"))
					.getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale"))) {
				test.log(LogStatus.PASS,
						"Actual and Expected Sale Summary reports are same for Net Transaction Date");

				// Get the Total value of Net Transaction Date
				String actualText1 = driver.findElement(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[2]/div[2]"))
						.getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Net Transaction Date Total element into float
				// value
				float actual = Float.parseFloat(actualText);

				// Print the actual value
				System.out.println("The Actual Net Transaction Date Value is : " + actual);

				test.log(LogStatus.PASS, "The Actual Net Transaction Date Value is : " + actual);
			}

			else if (expect1 == unknownValue) {
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");

				// Get the Total value of Check Count
				String actualText = driver.findElement(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[2]/div[2]"))
						.getText();

				System.out.println("The Actual Net Transaction Date value is : " + actualText);

				test.log(LogStatus.INFO, "The Actual Net Transaction Date value is : " + actualText);
			}

			else {
				test.log(LogStatus.FAIL,
						"Actual and Expected Sale Summary reports are different for Net Transaction Date");

				// Get the Total value of Transaction Date
				String actualText1 = driver.findElement(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[2]/div[2]"))
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
	
	
			if (driver.findElement(By.xpath(
					"//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[2]/div[3]"))
					.getText().equals(string_Value_Of_Tax_Amount)) {
				test.log(LogStatus.PASS,
						"Actual and Expected Sale Summary reports are same for Net Transaction Date");

				// Get the Total value of Net Transaction Date
				String actualText1 = driver.findElement(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[2]/div[3]"))
						.getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Net Transaction Date Total element into float
				// value
				float actual = Float.parseFloat(actualText);

				// Print the actual value
				System.out.println("The Actual Net Transaction Date Value is : " + actual);

				test.log(LogStatus.PASS, "The Actual Net Transaction Date Value is : " + actual);
			}

			else if (gobal_Total_Tax_Amount == unknownValue) {
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");

				// Get the Total value of Check Count
				String actualText = driver.findElement(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[2]/div[3]"))
						.getText();

				System.out.println("The Actual Net Transaction Date value is : " + actualText);

				test.log(LogStatus.INFO, "The Actual Net Transaction Date value is : " + actualText);
			}

			else {
				test.log(LogStatus.FAIL,
						"Actual and Expected Sale Summary reports are different for Net Transaction Date");

				// Get the Total value of Transaction Date
				String actualText1 = driver.findElement(By.xpath(
						"//table[@class='table table-bordered ng-table-responsive']/tbody/tr[2]/td[2]/div[3]"))
						.getText();

				// Replace all commo's with empty space
				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the net Transaction Date Total element into float
				// value
				float actual = Float.parseFloat(actualText);

				// Get the different
				float different = actual - gobal_Total_Tax_Amount;

				// Print the different value
				System.out.println("Net Transaction Date Value different is : " + different);

				test.log(LogStatus.FAIL, "Net Transaction Date Value different is : " + different);
			}
			}
	}

	public void Daily_Summary_Report_Tax_Validation(WebDriver driver) throws Exception {
	
	
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
	    
	    Thread.sleep(45000);
	    driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
	    
	    List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr"));

	    String sale_Amount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[7]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
	    
	 // Replace all commo's with empty space
		String expected1 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

		// Convert the String value of the Sale_Report_Net_Sale element into float value
		float expect1 = Float.parseFloat(expected1);
	    
	    
	    float sale_Amount1 = Float.parseFloat(sale_Amount);
		
		test.log(LogStatus.INFO, "The total value of the sale amoount in Daily summary report: "+sale_Amount1);
		
		Thread.sleep(10000);
		
		try {
			
		 
		// Check weather the report is available for the selected time period
		if (driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed()) {
			test.log(LogStatus.FAIL, " Daily summary report is not available for the Required Date Range");

			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.INFO, test.addScreenCapture(s));
		}
		}

		catch (Exception e) {
			
		if (driver.findElement(By.xpath(
				"//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[7]"))
				.getText().equals(Utility.getReportPropertyUser("Sale_Report_Net_Sale"))) {
			test.log(LogStatus.PASS,
					"Actual and Expected Sale Summary reports are same for daily summary report");

			// Get the Total value of Net Transaction Date
			String actualText1 = driver.findElement(By.xpath(
					"//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[7]"))
					.getText();

			// Replace all commo's with empty space
			String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

			// Convert the String value of the Net Transaction Date Total element into float
			// value
			float actual = Float.parseFloat(actualText);

			// Print the actual value
			System.out.println("The Actual Net Transaction Date Value is : " + actual);

			test.log(LogStatus.PASS, "The Actual Net Transaction Date Value is : " + actual);
		}

		else if (expect1 == unknownValue) {
			test.log(LogStatus.PASS, "Here we don't have the exact expected value");

			// Get the Total value of Check Count
			String actualText = driver.findElement(By.xpath(
					"//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[7]"))
					.getText();

			System.out.println("The Actual Net Transaction Date value is : " + actualText);

			test.log(LogStatus.INFO, "The Actual Net Transaction Date value is : " + actualText);
		}

		else {
			test.log(LogStatus.FAIL,
					"Actual and Expected Sale Summary reports are different for Net Transaction Date");

			// Get the Total value of Transaction Date
			String actualText1 = driver.findElement(By.xpath(
					"//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[7]"))
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


		if (driver.findElement(By.xpath(
				"//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[8]"))
				.getText().equals(string_Value_Of_Tax_Amount)) {
			test.log(LogStatus.PASS,
					"Actual and Expected Sale Summary reports are same for Net Transaction Date");

			// Get the Total value of Net Transaction Date
			String actualText1 = driver.findElement(By.xpath(
					"//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[8]"))
					.getText();

			// Replace all commo's with empty space
			String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

			// Convert the String value of the Net Transaction Date Total element into float
			// value
			float actual = Float.parseFloat(actualText);

			// Print the actual value
			System.out.println("The Actual Net Transaction Date Value is : " + actual);

			test.log(LogStatus.PASS, "The Actual Net Transaction Date Value is : " + actual);
		}

		else if (gobal_Total_Tax_Amount == unknownValue) {
			test.log(LogStatus.PASS, "Here we don't have the exact expected value");

			// Get the Total value of Check Count
			String actualText = driver.findElement(By.xpath(
					"//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[8]"))
					.getText();

			System.out.println("The Actual Net Transaction Date value is : " + actualText);

			test.log(LogStatus.INFO, "The Actual Net Transaction Date value is : " + actualText);
		}

		else {
			test.log(LogStatus.FAIL,
					"Actual and Expected Sale Summary reports are different for Net Transaction Date");

			// Get the Total value of Transaction Date
			String actualText1 = driver.findElement(By.xpath(
					"//table[@class='table table-bordered ng-table-responsive']/tbody/tr["+rows.size()+"]/td[8]"))
					.getText();

			// Replace all commo's with empty space
			String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

			// Convert the String value of the net Transaction Date Total element into float
			// value
			float actual = Float.parseFloat(actualText);

			// Get the different
			float different = actual - gobal_Total_Tax_Amount;

			// Print the different value
			System.out.println("Net Transaction Date Value different is : " + different);

			test.log(LogStatus.FAIL, "Net Transaction Date Value different is : " + different);
		}
		}
	}
	
	public void Discount_Report_Tax_Validation(WebDriver driver) throws Exception {
		
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		/*Thread.sleep(2000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		
		//Click the Reports option
		driver.findElement(By.xpath("//span[.='Reports']")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Discount']"));
		//Scroll the page till the Discount option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Discount Option		
		driver.findElement(By.xpath("//span[.='Discount']")).click(); */
		
		Thread.sleep(3000);
		//Enter the Users URL
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"report/discountReport");
		
		Thread.sleep(5000);
		try
		{
		//Check Discount Report page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 870, 1))).getText().equalsIgnoreCase("Discount Report"))
		{
			test.log(LogStatus.PASS, "Discount report page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Discount report page loaded Failed");
		
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
		
		//Click the Report Type option
		driver.findElement(By.xpath(excel.getData(3, 871, 1))).click();
		Thread.sleep(1000);
		//Enter the required Report Type
		driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys("Summary");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys(Keys.ENTER);

		//Click the Discount option
		driver.findElement(By.xpath(excel.getData(3, 873, 1))).click();
		Thread.sleep(1000);
		//Enter the required Discount
		driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys("All");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys(Keys.ENTER);

		//Click the Sale created by option
		driver.findElement(By.xpath(excel.getData(2, 35, 1))).click();
		//Enter the required Employee
		driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys("All");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys(Keys.ENTER);

		//Click the Discount Coupon Type option
		driver.findElement(By.xpath(excel.getData(3, 875, 1))).click();
		//Enter the Required Coupon Type
		driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys("All");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys(Keys.ENTER);

		//Click the Time Period option
		driver.findElement(By.xpath(excel.getData(3, 877, 1))).click();
		//Enter the required Time Period
		driver.findElement(By.xpath(excel.getData(3, 878, 1))).sendKeys("Date Range");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 878, 1))).sendKeys(Keys.ENTER);
		
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 879, 1))).clear();
		//Enter the date
		driver.findElement(By.xpath(excel.getData(3, 879, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
	
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 880, 1))).clear();
		//Enter the date
		driver.findElement(By.xpath(excel.getData(3, 880, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(2000);
		//Click the Run Button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		
		Thread.sleep(3000);
		try
		{
			//Check weather the report is available for the selected time period
			if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Discount Report is not available for Specific Date");
			
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));

			}
		}
		catch(Exception fd)				
		{
			test.log(LogStatus.INFO, "******* The Below is Summary Report of Discount *******");
			
			System.out.println("******* The Below is Summary Report of Discount *******");
			
			test.log(LogStatus.PASS, "Discount Report available for Specific Date");
			
			Thread.sleep(8000);
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			
			//Get the Total discount value
			String total_Discount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive']/tbody/tr[contains(.,'Total Discounts')]/td[2]")).getText();
			
			//Print the Total
			System.out.println("Total Discount is : "+total_Discount);
			
			test.log(LogStatus.INFO, "Total Discount is : "+total_Discount);
			
			//Get the Percentage of Total discount
			String percentage_Of_Total = driver.findElement(By.xpath(excel.getData(3, 882, 1))).getText();
			
			//Print the percentage_Of_Total
			System.out.println("Percentage Of Total Discount is : "+percentage_Of_Total);
			
			test.log(LogStatus.INFO, "Percentage Of Total Discount is : "+percentage_Of_Total);
			
			//Get the Count value
			String count = driver.findElement(By.xpath(excel.getData(3, 883, 1))).getText();
			
			//Print the count
			System.out.println("Total number of count is : "+count);
			
			test.log(LogStatus.INFO, "Total number of count is : "+count);
			
			//Get the Average discount value
			String average = driver.findElement(By.xpath(excel.getData(3, 884, 1))).getText();
			
			//Print the Average
			System.out.println("Average Discount is : "+average);
			
			test.log(LogStatus.INFO, "Average Discount is : "+average);
			
			//Get the number of rows
			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 885, 1)));
			
			//Get the Total Checks
			String total_Checks = driver.findElement(By.xpath(excel.getData(3, 886, 1))).getText();
			
			//Print the Total Checks
			System.out.println("Total Checks count is : "+total_Checks);
			
			test.log(LogStatus.INFO, "Total Checks count is : "+total_Checks);
			
			//Get the Percentage of Checks discounted
			String percentage_Of_Checks_discounted = driver.findElement(By.xpath(excel.getData(3, 887, 1))).getText();
			
			//Print the percentage_Of_Checks_discounted
			System.out.println("Percentage of Checks discounted is : "+percentage_Of_Checks_discounted);
			
			test.log(LogStatus.INFO, "Percentage of Checks discounted is : "+percentage_Of_Checks_discounted);
			
			//Get the Gross Sales
			String gross_Sales = driver.findElement(By.xpath(excel.getData(3, 888, 1))).getText();
			
			//Print the Gross Sales
			System.out.println("Total Gross Sales is : "+gross_Sales);
			
			test.log(LogStatus.INFO, "Total Gross Sales is : "+gross_Sales);
			
			//Get the percentage discount of gross sales
			String percentage_Discount_Of_Gross_Sales = driver.findElement(By.xpath(excel.getData(3, 889, 1))).getText();
			
			//Print the percentage discount of gross sales
			System.out.println("Percentage of Gross Sales is : "+percentage_Discount_Of_Gross_Sales);
			
			test.log(LogStatus.INFO, "Percentage of Gross Sales is : "+percentage_Discount_Of_Gross_Sales);
			
			//Get the Checks Discounted
			String checks_Discounted = driver.findElement(By.xpath(excel.getData(3, 890, 1))).getText();
			
			//Print the Checks Discounted
			System.out.println("Total Checks discounted is : "+checks_Discounted);
			
			test.log(LogStatus.INFO, "Total Checks discounted is : "+checks_Discounted);
			
			Thread.sleep(3000);
			String Sale_Report_Discount_Expected = Utility.getReportPropertyUser("Sale_Report_Discount");
			
			// Replace all commo's with empty space
			String ReportText = total_Discount.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
			
			// Convert the String value of the Discount Total element into float value
			float actual2 = Float.parseFloat(ReportText);
			
			// Replace all commo's with empty space
			String actualText = Sale_Report_Discount_Expected.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

			// Convert the String value of the Discount Total element into float value
			float actual = Float.parseFloat(actualText);

			
			
			if (ReportText.equals(Sale_Report_Discount_Expected)) {
				test.log(LogStatus.PASS, "Actual and Expected DIscount Values are same");

				// Print the actual value
				System.out.println("The Actual Discount Value is : " + ReportText);

				test.log(LogStatus.PASS, "The Actual Discount Value is : " + ReportText);
			}

			else if (actual == unknownValue) {
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");

				System.out.println("The Actual Discount value is : " + ReportText);

				test.log(LogStatus.INFO, "The Actual Discount value is : " + ReportText);
			}

			else {
				test.log(LogStatus.FAIL, "Actual and Expected Discount values are different");

				// Get the different
				float different = actual - actual2;

				// Print the different value
				System.out.println("Discount Value different is : " + different);

				test.log(LogStatus.FAIL, "Discount Value different is : " + different);
			}
		
		}
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		
		//driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		
		String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		String s="data:image/png;base64,"+scnShot;
		test.log(LogStatus.INFO,test.addScreenCapture(s));
		

		Thread.sleep(2000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		
		
		
		Thread.sleep(3000);
		//Click the Report Type option
		driver.findElement(By.xpath(excel.getData(3, 871, 1))).click();
		//Enter the required Report Type
		driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys("By Employee");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys(Keys.ENTER);

		//Click the Discount option
		driver.findElement(By.xpath(excel.getData(3, 873, 1))).click();
		//Enter the required Discount
		driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys("All");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys(Keys.ENTER);

		//Click the Employee option
		driver.findElement(By.xpath(excel.getData(2, 35, 1))).click();
		//Enter the required Employee
		driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys("All");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(2000);
		//Click the Discount Coupon Type option
		driver.findElement(By.xpath(excel.getData(3, 875, 1))).click();
		//Enter the Required Coupon Type
		driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys("All");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		//Click the Time Period option
		driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
		//Enter the required Time Period
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);
		
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the date
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
	
		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the date
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(2000);
		//Click the Run Button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		
		Thread.sleep(3000);
		try
		{
			//Check weather the report is available for the selected time period
			if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Discount Report is not available for Specific Date");
			
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				
				String scnShot3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s3="data:image/png;base64,"+scnShot3;
				test.log(LogStatus.INFO,test.addScreenCapture(s3));

			}
		}
		catch(Exception fd)
		{
			test.log(LogStatus.INFO, "******* The Below is By Employee Report of Discount *******");
			
			System.out.println("******* The Below is By Employee Report of Discount *******");
			
			test.log(LogStatus.PASS, "Discount Report available for Specific Date");
			
			Thread.sleep(5000);
			
			String Sale_Report_Discount_Expected = Utility.getReportPropertyUser("Sale_Report_Discount");
			
            String page_Size = driver.findElement(By.xpath("//li[@ng-repeat='page in pages']//a[@ng-switch-when='last']//span")).getText();
            
            int size1 = Integer.parseInt(page_Size);
			
			for(int k=1;k<=size1;k++) {
				
				Thread.sleep(500);
				WebElement ele1=driver.findElement(By.xpath("//a[@ng-switch-when='next']"));
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].scrollIntoView(true);", ele1);
				
				
//				WebDriverWait wai2=new WebDriverWait(driver, 30);
//				wai2.until(ExpectedConditions.elementToBeClickable(ele1)).click();
				
				driver.findElement(By.xpath("//a[@ng-switch-when='next']")).click();
				
//				if (driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]")).isDisplayed()) {
					
				
			
				int size = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]")).size();
			
				for(int r=1; r<=size;r++ ) {
			
//          		 int size = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]")).size();
           
					String total_Discount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]["+r+"]/td[10]")).getText();
			
//					System.out.println("Total " + total_Discount);
					// Replace all commo's with empty space
					String ReportText = total_Discount.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
			
					this.string_Value_Of_Expected3 = ReportText;
			
					// Convert the String value of the Discount Total element into float value
					gobal_actual7 = Float.parseFloat(ReportText);
					
//					System.out.println("sum 1 " + gobal_actual);
			
					gob_Act3.add(gobal_actual7);
					
					System.out.println("sum 2 " + gobal_actual7);
			
					// Replace all commo's with empty space
					String actualText = Sale_Report_Discount_Expected.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Discount Total element into float value
					gobal_actual8 = Float.parseFloat(actualText);
			
//					gob_Act2.add(gobal_actual2);
			

				}
				
//				}
			
			}
			
			
			 for (int i = 0; i < gob_Act3.size(); i++) {
				 gobal_actual9 += gob_Act3.get(i);
		 
		        
		    }
			
			 System.out.println("sum 3 " + gobal_actual9);
			
			if (string_Value_Of_Expected3.equals(Sale_Report_Discount_Expected)) {
				test.log(LogStatus.PASS, "Actual and Expected DIscount Values are same");

				// Print the actual value
				System.out.println("The Actual Discount Value is : " + string_Value_Of_Expected3);

				test.log(LogStatus.PASS, "The Actual Discount Value is : " + string_Value_Of_Expected3);
			}

			else if (gobal_actual7 == unknownValue) {
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");

				System.out.println("The Actual Discount value is : " + string_Value_Of_Expected3);

				test.log(LogStatus.INFO, "The Actual Discount value is : " + string_Value_Of_Expected3);
			}

			else {
				test.log(LogStatus.FAIL, "Actual and Expected Discount values are different");

				// Get the different
				float different = gobal_actual9 - gobal_actual8;

				// Print the different value
				System.out.println("Net Sales Value different is : " + different);

				test.log(LogStatus.FAIL, "Net Sales Value different is : " + different);
			}
			
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			
			String scnShot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s2="data:image/png;base64,"+scnShot2;
			test.log(LogStatus.INFO,test.addScreenCapture(s2));
			
			
		//	Discount_Report_Search_Field_Discount_Report(driver);wb.close();
		
			
		}
			
//			
//			
//			if (driver.findElement(By.xpath("//li[@ng-repeat='page in pages']//a[@ng-switch-when='last']")).isDisplayed()) {
//				
//				driver.findElement(By.xpath("//li[@ng-repeat='page in pages']//a[@ng-switch-when='last']")).click();
//				
//			}
//			
//			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
//			
//			Thread.sleep(3000);
//			String Sale_Report_Discount_Expected = Utility.getReportPropertyUser("Sale_Report_Discount");
//			
////           int size = driver.findElements(By.xpath("//td[@data-title-text='Discount Amount']//span")).size();
//           
//           String total_Discount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]/td[10]")).getText();
//			
//			// Replace all commo's with empty space
//			String ReportText = total_Discount.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
//			
//			// Convert the String value of the Discount Total element into float value
//			float actual2 = Float.parseFloat(ReportText);
//			
//			// Replace all commo's with empty space
//			String actualText = Sale_Report_Discount_Expected.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
//
//			// Convert the String value of the Discount Total element into float value
//			float actual = Float.parseFloat(actualText);
//
//			
//			
//			if (ReportText.equals(Sale_Report_Discount_Expected)) {
//				test.log(LogStatus.PASS, "Actual and Expected DIscount Values are same");
//
//				// Print the actual value
//				System.out.println("The Actual Discount Value is : " + ReportText);
//
//				test.log(LogStatus.PASS, "The Actual Discount Value is : " + ReportText);
//			}
//
//			else if (actual == unknownValue) {
//				test.log(LogStatus.PASS, "Here we don't have the exact expected value");
//
//				System.out.println("The Actual Discount value is : " + ReportText);
//
//				test.log(LogStatus.INFO, "The Actual Discount value is : " + ReportText);
//			}
//
//			else {
//				test.log(LogStatus.FAIL, "Actual and Expected Discount values are different");
//
//				// Get the different
//				float different = actual - actual2;
//
//				// Print the different value
//				System.out.println("Net Sales Value different is : " + different);
//
//				test.log(LogStatus.FAIL, "Net Sales Value different is : " + different);
//			}
//			
//			
//			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
//			
//			String scnShot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//			String s2="data:image/png;base64,"+scnShot2;
//			test.log(LogStatus.INFO,test.addScreenCapture(s2));
//}
		
		
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		
		Thread.sleep(3000);
		//Click the Report Type option
		driver.findElement(By.xpath(excel.getData(3, 871, 1))).click();
		//Enter the required Report Type
		driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys("By Discount");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys(Keys.ENTER);

		//Click the Discount option
		driver.findElement(By.xpath(excel.getData(3, 873, 1))).click();
		//Enter the required Discount
		driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys("All");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys(Keys.ENTER);

		//Click the Employee option
		driver.findElement(By.xpath(excel.getData(2, 35, 1))).click();
		//Enter the required Employee
		driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys("All");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys(Keys.ENTER);
	
		//Click the Discount Coupon Type option
		driver.findElement(By.xpath(excel.getData(3, 875, 1))).click();
		//Enter the Required Coupon Type
		driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys("All");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys(Keys.ENTER);

		//Click the Time Period option
		driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
		//Enter the required Time Period
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);

		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the date
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the date
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(2000);
		//Click the Run Button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		
		Thread.sleep(3000);
		try
		{
			//Check weather the report is available for the selected time period
			if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Discount Report is not available for Specific Date");
			//	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				
				String scnShot4 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s4="data:image/png;base64,"+scnShot4;
				test.log(LogStatus.INFO,test.addScreenCapture(s4));

			}
		}
		catch(Exception ret)
		{
			test.log(LogStatus.INFO, "******* The Below is By Discount Report of Discount *******");
			
			test.log(LogStatus.INFO, "By Discount Report is available");
				
			test.log(LogStatus.PASS, "Discount Report available for Specific Date");
			
			
			Thread.sleep(6000);
			String Sale_Report_Discount_Expected = Utility.getReportPropertyUser("Sale_Report_Discount");
			
            String page_Size = driver.findElement(By.xpath("//li[@ng-repeat='page in pages']//a[@ng-switch-when='last']//span")).getText();
            
            int size1 = Integer.parseInt(page_Size);
			
			for(int k=1;k<=size1;k++) {
				
				Thread.sleep(500);
				WebElement ele1=driver.findElement(By.xpath("//a[@ng-switch-when='next']"));
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].scrollIntoView(true);", ele1);
				
				
//				WebDriverWait wai2=new WebDriverWait(driver, 30);
//				wai2.until(ExpectedConditions.elementToBeClickable(ele1)).click();
				
				driver.findElement(By.xpath("//a[@ng-switch-when='next']")).click();
			
				int size = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]")).size();
			
				for(int r=1; r<=size;r++ ) {
			
//          		 int size = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]")).size();
           
					String total_Discount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]["+r+"]/td[10]")).getText();
			
//					System.out.println("Total " + total_Discount);
					// Replace all commo's with empty space
					String ReportText = total_Discount.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
			
					this.string_Value_Of_Expected = ReportText;
			
					// Convert the String value of the Discount Total element into float value
					gobal_actual = Float.parseFloat(ReportText);
					
//					System.out.println("sum 1 " + gobal_actual);
			
					gob_Act1.add(gobal_actual);
					
					System.out.println("sum 2 " + gobal_actual);
			
					// Replace all commo's with empty space
					String actualText = Sale_Report_Discount_Expected.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Discount Total element into float value
					gobal_actual2 = Float.parseFloat(actualText);
			
//					gob_Act2.add(gobal_actual2);
			

				}
			
			}
			
			
			 for (int i = 0; i < gob_Act1.size(); i++) {
				 gobal_actual5 += gob_Act1.get(i);
		 
		        
		    }
			
			 System.out.println("sum 3 " + gobal_actual5);
			
			if (string_Value_Of_Expected.equals(Sale_Report_Discount_Expected)) {
				test.log(LogStatus.PASS, "Actual and Expected DIscount Values are same");

				// Print the actual value
				System.out.println("The Actual Discount Value is : " + string_Value_Of_Expected);

				test.log(LogStatus.PASS, "The Actual Discount Value is : " + string_Value_Of_Expected);
			}

			else if (gobal_actual == unknownValue) {
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");

				System.out.println("The Actual Discount value is : " + string_Value_Of_Expected);

				test.log(LogStatus.INFO, "The Actual Discount value is : " + string_Value_Of_Expected);
			}

			else {
				test.log(LogStatus.FAIL, "Actual and Expected Discount values are different");

				// Get the different
				float different = gobal_actual5 - gobal_actual2;

				// Print the different value
				System.out.println("Net Sales Value different is : " + different);

				test.log(LogStatus.FAIL, "Net Sales Value different is : " + different);
			}
			
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			
			String scnShot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s2="data:image/png;base64,"+scnShot2;
			test.log(LogStatus.INFO,test.addScreenCapture(s2));
			
			
		//	Discount_Report_Search_Field_Discount_Report(driver);wb.close();
		}
		
		
driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		
		Thread.sleep(3000);
		//Click the Report Type option
		driver.findElement(By.xpath(excel.getData(3, 871, 1))).click();
		//Enter the required Report Type
		driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys("By Discount Type");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 872, 1))).sendKeys(Keys.ENTER);

		//Click the Discount option
		driver.findElement(By.xpath(excel.getData(3, 873, 1))).click();
		//Enter the required Discount
		driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys("All");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 874, 1))).sendKeys(Keys.ENTER);

		//Click the Employee option
		driver.findElement(By.xpath(excel.getData(2, 35, 1))).click();
		//Enter the required Employee
		driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys("All");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(2, 36, 1))).sendKeys(Keys.ENTER);
	
		//Click the Discount Coupon Type option
		driver.findElement(By.xpath(excel.getData(3, 875, 1))).click();
		//Enter the Required Coupon Type
		driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys("All");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 876, 1))).sendKeys(Keys.ENTER);

		//Click the Time Period option
		driver.findElement(By.xpath(excel.getData(3, 2157, 1))).click();
		//Enter the required Time Period
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys("Date Range");
		//Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 2158, 1))).sendKeys(Keys.ENTER);

		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).clear();
		//Enter the date
		driver.findElement(By.xpath(excel.getData(3, 1982, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		//Clear the date field
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).clear();
		//Enter the date
		driver.findElement(By.xpath(excel.getData(3, 1983, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(2000);
		//Click the Run Button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		
		Thread.sleep(3000);
		try
		{
			//Check weather the report is available for the selected time period
			if(driver.findElement(By.xpath(excel.getData(3, 704, 1))).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Discount Report is not available for Specific Date");
			//	driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				
				String scnShot4 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s4="data:image/png;base64,"+scnShot4;
				test.log(LogStatus.INFO,test.addScreenCapture(s4));

			}
		}
		catch(Exception ret)
		{
			test.log(LogStatus.INFO, "******* The Below is By Discount Report of Discount *******");
			
			test.log(LogStatus.INFO, "By Discount Report is available");
				
			test.log(LogStatus.PASS, "Discount Report available for Specific Date");
			
			
			Thread.sleep(7000);
			String Sale_Report_Discount_Expected = Utility.getReportPropertyUser("Sale_Report_Discount");
			
            String page_Size = driver.findElement(By.xpath("//li[@ng-repeat='page in pages']//a[@ng-switch-when='last']//span")).getText();
            
            int size1 = Integer.parseInt(page_Size);
			
			for(int k=1;k<=size1;k++) {
				
				
				
				Thread.sleep(500);
				WebElement ele1=driver.findElement(By.xpath("//a[@ng-switch-when='next']"));
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].scrollIntoView(true);", ele1);
				
				
//				WebDriverWait wai2=new WebDriverWait(driver, 30);
//				wai2.until(ExpectedConditions.elementToBeClickable(ele1)).click();
				
				driver.findElement(By.xpath("//a[@ng-switch-when='next']")).click();
				
			
				int size = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]")).size();
			
				for(int r=1; r<=size;r++ ) {
			
//          		 int size = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]")).size();
           
					String total_Discount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]["+r+"]/td[10]")).getText();
			
//					System.out.println("Total " + total_Discount);
					// Replace all commo's with empty space
					String ReportText = total_Discount.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
			
					this.string_Value_Of_Expected2 = ReportText;
			
					// Convert the String value of the Discount Total element into float value
					gobal_actual3 = Float.parseFloat(ReportText);
					
//					System.out.println("sum 1 " + gobal_actual);
			
					gob_Act2.add(gobal_actual3);
					
					System.out.println("sum 2 " + gobal_actual3);
			
					// Replace all commo's with empty space
					String actualText = Sale_Report_Discount_Expected.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

					// Convert the String value of the Discount Total element into float value
					gobal_actual4 = Float.parseFloat(actualText);
			
//					gob_Act2.add(gobal_actual2);
			

				}
			
			}
			
			
			 for (int i = 0; i < gob_Act2.size(); i++) {
				 gobal_actual6 =gobal_actual6+ gob_Act2.get(i);
		 
		        System.out.println(i+" Added array values After Total : "+gobal_actual6);
		    }
			
			 System.out.println("sum 3 " + gobal_actual6);
			
			if (string_Value_Of_Expected.equals(Sale_Report_Discount_Expected)) {
				test.log(LogStatus.PASS, "Actual and Expected DIscount Values are same");

				// Print the actual value
				System.out.println("The Actual Discount Value is : " + string_Value_Of_Expected2);

				test.log(LogStatus.PASS, "The Actual Discount Value is : " + string_Value_Of_Expected2);
			}

			else if (gobal_actual == unknownValue) {
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");

				System.out.println("The Actual Discount value is : " + string_Value_Of_Expected2);

				test.log(LogStatus.INFO, "The Actual Discount value is : " + string_Value_Of_Expected2);
			}

			else {
				test.log(LogStatus.FAIL, "Actual and Expected Discount values are different");

				// Get the different
				float different = gobal_actual6 - gobal_actual4;

				// Print the different value
				System.out.println("Net Sales Value different is : " + different);

				test.log(LogStatus.FAIL, "Net Sales Value different is : " + different);
			}
			
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			
			String scnShot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s2="data:image/png;base64,"+scnShot2;
			test.log(LogStatus.INFO,test.addScreenCapture(s2));
			
			
		//	Discount_Report_Search_Field_Discount_Report(driver);wb.close();
		}
		
			
			
			
		}
	}
	

