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

public class Sale_Recap_Report_Validation {
	
	
	public WebDriver driver;
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Sale_Recap_Report_Validation");
	
	float unknownValue = 00;
	float Expected_Net_Sale_Value;
	float Expected_Gross_Sale_Value;
	float Expected_Gross_Receipt_Value;
	float Expected_Grand_Sale_Value;
	float Expected_All_Sale_Value;
	float Expected_Total_Tax_value;
	float Expected_Deposit_value;
	float Expected_Non_Sale_Revenue_Sale_Value;
	float Expected_Rounding_Off_Value_actua;
	float Calculated_Tax_Netsale_Amount;
	float GC_Sold_Actual;
	float HA_Sold_Actual;
	float Deposit_Value;
	float Credit_Crad_Amount_Value;
	float Credit_Crad_Tip_Value;
	float GiveX_Sold_Actual;
	float Gratuity_Actual;
	float Delivery_Compensation_Actual;
	float Service_Charge_Actual;
	float Sum_Of_All_Payment_Actual;
	float Total_Tax_Value;
	float gobal_actual = 0;
	float gobal_actual2 = 0;
	float gobal_tax_Netsale = 0;
	float expect_Deposit_Value1;
	String Deposit_Value1;
	float gobal_actual_Report_net_Sale_Tax;
	List<Float> gob_Act3 = new ArrayList<Float>();
	List<Float> gob_Act_netsale = new ArrayList<Float>();
	
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
		Sale_Recap_Report_Tax_Validation(driver);
		
	}
	
	public void Sale_Recap_Report_Tax_Validation(WebDriver driver) throws Exception {
	
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(3000);
		// Enter the SaleRecap Url
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "report/saleRecap");

		Thread.sleep(20000);
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
		
		Thread.sleep(10000);
		// Check weather the Check Status
		if (driver.findElement(By.xpath("//b[.='SALES']")).isDisplayed()) {
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
			
			Thread.sleep(500);
			JavascriptExecutor js=(JavascriptExecutor)driver;
			
			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Gross Sales')]/div[2]")));
			String Gross_Sale = driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Gross Sales')]/div[2]")).getText();
			String Gross_Sale2 = Gross_Sale.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
			float Gross_Sale_actual = Float.parseFloat(Gross_Sale2);
			
			int size3 = driver.findElements(By.xpath("//b[.='TAXES']/../../../div[contains(.,'Total Tax Collected')][1]/div")).size();
			
			if(size3 == 2) {
			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='TAXES']/../../../div[contains(.,'Total Tax Collected')][1]/div[2]")));
			String Tax_Value = driver.findElement(By.xpath("//b[.='TAXES']/../../../div[contains(.,'Total Tax Collected')][1]/div[2]")).getText();
			String Tax_Value2 = Tax_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
			float Tax_Value_actual = Float.parseFloat(Tax_Value2);
			this.Total_Tax_Value = Tax_Value_actual;
			test.log(LogStatus.INFO, "The Actual Total Tax value is : " + Total_Tax_Value);
			}else {
				js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='TAXES']/../../../div[contains(.,'Total Tax Collected')][1]/div[4]")));
				String Tax_Value = driver.findElement(By.xpath("//b[.='TAXES']/../../../div[contains(.,'Total Tax Collected')][1]/div[4]")).getText();
				String Tax_Value2 = Tax_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
				float Tax_Value_actual = Float.parseFloat(Tax_Value2);
				this.Total_Tax_Value = Tax_Value_actual;
				test.log(LogStatus.INFO, "The Actual Total Tax value is : " + Total_Tax_Value);
			}
			
			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Gross Void')][1]/div[2]")));
			String Gross_Void_Sale = driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Gross Void')][1]/div[2]")).getText();
			String Gross_Void_Sale2 = Gross_Void_Sale.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
			float Gross_Void_Sale_actual = Float.parseFloat(Gross_Void_Sale2);
			
			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Total')]/div[2]")));
			String Discount_Value = driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Total')]/div[2]")).getText();
			String Discount_Value2 = Discount_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
			float Discount_Value_actual = Float.parseFloat(Discount_Value2);
			
			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Discount Tax')][1]/div[2]")));
			String Discount_Tax_Value = driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Discount Tax')][1]/div[2]")).getText();
			String Discount_Tax_Value2 = Discount_Tax_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
			float Discount_Tax_Value_actual = Float.parseFloat(Discount_Tax_Value2);
			
			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Void Discount')][1]/div[2]")));
			String Void_Discount_Value = driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Void Discount')][1]/div[2]")).getText();
			String Void_Discount_Value2 = Void_Discount_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
			float Void_Discount_Value_actual = Float.parseFloat(Void_Discount_Value2);
			
			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Void Discount Tax')][1]/div[2]")));
			String Void_Discount_Tax_Value = driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Void Discount Tax')][1]/div[2]")).getText();
			String Void_Discount_Tax_Value2 = Void_Discount_Tax_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
			float Void_Discount_Tax_Value_actual = Float.parseFloat(Void_Discount_Tax_Value2);

			Expected_Net_Sale_Value = Gross_Sale_actual - Total_Tax_Value - Gross_Void_Sale_actual - Discount_Value_actual - Discount_Tax_Value_actual
					                    - Void_Discount_Value_actual - Void_Discount_Tax_Value_actual;
			
			test.log(LogStatus.INFO, "The Calculated Net sale value with formula : " + Expected_Net_Sale_Value);
			
			
			int size4 = driver.findElements(By.xpath("//b[.='TAXES']/../../../div[contains(.,'Total Tax Collected')][1]/div")).size();
			
			
			if(size4 == 4) {
				
				int size1 =  driver.findElements(By.xpath("//b[.='TAXES']/../../../div//div[3]")).size();
				
				int size = size1 - 3;
				 
				 for(int r=1; r<=size;r++ ) {
					 
					 if(r == 1) {
					 continue;
					 }else {
						 
						 String net_Sale_Tax = driver.findElement(By.xpath("(//b[.='TAXES']/../../../div//div[3])["+r+"]")).getText();
						 
						 String Report_net_Sale_Tax = net_Sale_Tax.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
						 
						 gobal_actual_Report_net_Sale_Tax = Float.parseFloat(Report_net_Sale_Tax);
						 
						 gob_Act_netsale.add(gobal_actual_Report_net_Sale_Tax);
							
						System.out.println("sum 2 " + gob_Act_netsale);
						 
					 }
				 }
				}else {
					
					//nothing -- its useless
				}
			
			
			
			
			 

			 for (int i = 0; i < gob_Act_netsale.size(); i++) {
				 gobal_tax_Netsale += gob_Act_netsale.get(i);
		    }
			 
			 
			 
			 if(size4 == 4) {
//				    String Rounding_Off_Value = driver.findElement(By.xpath("//b[.='TAXES']/../../../div[contains(.,'Rounding Off')][1]/div[2]")).getText();
//					String Rounding_Off_Value2 = Rounding_Off_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
//					float Rounding_Off_Value_actual = Float.parseFloat(Rounding_Off_Value2);
					this.Calculated_Tax_Netsale_Amount = gobal_tax_Netsale;
					}else {
//						    String Rounding_Off_Value = driver.findElement(By.xpath("//b[.='TAXES']/../../../div[contains(.,'Rounding Off')][1]/div[4]")).getText();
//							String Rounding_Off_Value2 = Rounding_Off_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
//							float Rounding_Off_Value_actual = Float.parseFloat(Rounding_Off_Value2);
							this.Calculated_Tax_Netsale_Amount = Expected_Net_Sale_Value;
					}
			 
			 
			 
			 
			
			Thread.sleep(5000);
			// Check the SALES
			if (driver.findElement(By.xpath("//b[.='SALES']")).isDisplayed()) {
				test.log(LogStatus.INFO, "SALES is available");
				test.log(LogStatus.INFO, "Net sale validation is stated");

				System.out.println("SALES is available");

				// Replace all commo's with empty space
				String expected2 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale_Report_Net_Sale element into float value
				float expect2 = Float.parseFloat(expected2);
				
				// Replace all commo's with empty space
				String expected1 = driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "");

				// Convert the String value of the Sale_Report_Net_Sale element into float value
				float expect1 = Float.parseFloat(expected1);

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
				
				// Check weather the Net Sales total is same or not
				if (expect1 == Expected_Net_Sale_Value) {
					test.log(LogStatus.PASS, "Actual and Calculated_Expected Net Sales Values are same");

					// Print the actual value
					System.out.println("The Actual Net Sales Value is : " + Expected_Net_Sale_Value);

					test.log(LogStatus.PASS, "The Actual Net Sales Value is : " + Expected_Net_Sale_Value);
				}

				else if (expect2 == unknownValue) {
					test.log(LogStatus.PASS, "Here we don't have the exact Calculated_expected value");

					// Get the Total value of Net Sales
					String actualText = driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText();

					System.out.println("The Actual Net Sales value is : " + actualText);

					test.log(LogStatus.INFO, "The Actual Net Sales value is : " + actualText);
				}

				else {
					test.log(LogStatus.FAIL, "Actual and Calculated_Expected Net Sales values are different");

					// Get the different
					float different = expect1 - Expected_Net_Sale_Value;

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
			
			// Replace all commo's with empty space
			String expected2 = Utility.getReportPropertyUser("Sale_Report_Net_Sale").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

			// Convert the String value of the Sale_Report_Net_Sale element into float value
			float expect2 = Float.parseFloat(expected2);
			
			// Check weather the Tax Net Sales total is same or not
			if (Calculated_Tax_Netsale_Amount == expect2) {
				test.log(LogStatus.PASS, "Actual and Expected TAX Net Sales Values are same");

//				// Get the Total value of Net Sales
//				String actualText1 = driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText();
//
//				// Replace all commo's with empty space
//				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
//
//				// Convert the String value of the Net Sales element into float value
//				float actual = Float.parseFloat(actualText);

				// Print the actual value
				System.out.println("The Actual Net Sales Value is : " + Calculated_Tax_Netsale_Amount);

				test.log(LogStatus.PASS, "The Actual Net Sales Value is : " + Calculated_Tax_Netsale_Amount);
			}

			else if (expect2 == unknownValue) {
				test.log(LogStatus.PASS, "Here we don't have the exact expected value");

				// Get the Total value of Net Sales
				String actualText = driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText();

				System.out.println("The Actual Net Sales value is : " + actualText);

				test.log(LogStatus.INFO, "The Actual Net Sales value is : " + actualText);
			}

			else {
				test.log(LogStatus.FAIL, "Actual and Expected TAX Net Sales values are different");

//				// Get the Total value of Gross Sales
//				String actualText1 = driver.findElement(By.xpath(excel.getData(1, 765, 1))).getText();
//
//				// Replace all commo's with empty space
//				String actualText = actualText1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
//
//				// Convert the String value of the Net Sales Total element into float value
//				float actual = Float.parseFloat(actualText);

				// Get the different
				float different = Calculated_Tax_Netsale_Amount - expect2;

				// Print the different value
				System.out.println("Tax Net Sales Value different is : " + different);

				test.log(LogStatus.FAIL, "Tax Net Sales Value different is : " + different);
			}
		}
		
		// Check weather the Check Status
				if (driver.findElement(By.xpath("//b[.='SALES']")).isDisplayed()) {
					test.log(LogStatus.INFO, "Gross sale validation is stated");
		
					Thread.sleep(500);
					JavascriptExecutor js=(JavascriptExecutor)driver;
					
					js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Gross Sales')]/div[2]")));
					String Gross_Sale = driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Gross Sales')]/div[2]")).getText();
					String Gross_Sale2 = Gross_Sale.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					float expect1 = Float.parseFloat(Gross_Sale2);
					
					js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[contains(.,'PAYMENT SUMMARY')]/../../../div[contains(.,'Total')]/div[2]")));
					String Sum_Of_All_Payment_Value = driver.findElement(By.xpath("//b[contains(.,'PAYMENT SUMMARY')]/../../../div[contains(.,'Total')]/div[2]")).getText();
					String Sum_Of_All_Payment_Value2 = Sum_Of_All_Payment_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					float Sum_Of_All_Payment_Value_actual = Float.parseFloat(Sum_Of_All_Payment_Value2);
					this.Sum_Of_All_Payment_Actual = Sum_Of_All_Payment_Value_actual;
					
					js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Gross Void')][1]/div[2]")));
					String Gross_Void_Sale = driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Gross Void')][1]/div[2]")).getText();
					String Gross_Void_Sale2 = Gross_Void_Sale.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					float Gross_Void_Sale_actual = Float.parseFloat(Gross_Void_Sale2);
					
					js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Total')]/div[2]")));
					String Discount_Value = driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Total')]/div[2]")).getText();
					String Discount_Value2 = Discount_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					float Discount_Value_actual = Float.parseFloat(Discount_Value2);
					
					js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Discount Tax')][1]/div[2]")));
					String Discount_Tax_Value = driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Discount Tax')][1]/div[2]")).getText();
					String Discount_Tax_Value2 = Discount_Tax_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					float Discount_Tax_Value_actual = Float.parseFloat(Discount_Tax_Value2);
					
					js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Void Discount')][1]/div[2]")));
					String Void_Discount_Value = driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Void Discount')][1]/div[2]")).getText();
					String Void_Discount_Value2 = Void_Discount_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					float Void_Discount_Value_actual = Float.parseFloat(Void_Discount_Value2);
					
					js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Void Discount Tax')][1]/div[2]")));
					String Void_Discount_Tax_Value = driver.findElement(By.xpath("//b[.='DISCOUNT ']/../../../div[contains(.,'Void Discount Tax')][1]/div[2]")).getText();
					String Void_Discount_Tax_Value2 = Void_Discount_Tax_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					float Void_Discount_Tax_Value_actual = Float.parseFloat(Void_Discount_Tax_Value2);
					
					
					try {
					if(driver.findElement(By.xpath("//b[.='NONSALE REVENUE ']/../../../div[contains(.,'Gift Card Sold')][1]/div[2]")).isDisplayed()) {
						js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='NONSALE REVENUE ']/../../../div[contains(.,'Gift Card Sold')][1]/div[2]")));
					String Gift_Card_Sold_Value = driver.findElement(By.xpath("//b[.='NONSALE REVENUE ']/../../../div[contains(.,'Gift Card Sold')][1]/div[2]")).getText();
					String Gift_Card_Sold_Value2 = Gift_Card_Sold_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					float Gift_Card_Sold_Value_actual = Float.parseFloat(Gift_Card_Sold_Value2);
					this.GC_Sold_Actual = Gift_Card_Sold_Value_actual;
					}
					}catch (Exception e) {
						this.GC_Sold_Actual = 0.00f;
					} 
						
					try {
					if(driver.findElement(By.xpath("//b[.='NONSALE REVENUE ']/../../../div[contains(.,'GiveX Sold')][1]/div[2]")).isDisplayed()) {
						js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='NONSALE REVENUE ']/../../../div[contains(.,'GiveX Sold')][1]/div[2]")));
					String GiveX_Sold_Value = driver.findElement(By.xpath("//b[.='NONSALE REVENUE ']/../../../div[contains(.,'GiveX Sold')][1]/div[2]")).getText();
					String GiveX_Sold_Value2 = GiveX_Sold_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					float GiveX_Sold_Value_actual = Float.parseFloat(GiveX_Sold_Value2);
					this.GiveX_Sold_Actual = GiveX_Sold_Value_actual;
					}
				     }catch (Exception g) {
					      this.GiveX_Sold_Actual = 0.00f;
				     }
					
					try {
					if(driver.findElement(By.xpath("//b[.='NONSALE REVENUE ']/../../../div[contains(.,'House Account Sold')][1]/div[2]")).isDisplayed()) {
						js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='NONSALE REVENUE ']/../../../div[contains(.,'House Account Sold')][1]/div[2]")));
					String House_Account_Sold_Value = driver.findElement(By.xpath("//b[.='NONSALE REVENUE ']/../../../div[contains(.,'House Account Sold')][1]/div[2]")).getText();
					String House_Account_Sold_Value2 = House_Account_Sold_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					float House_Account_Sold_Value_actual = Float.parseFloat(House_Account_Sold_Value2);
					this.HA_Sold_Actual = House_Account_Sold_Value_actual;
					}
				     }catch (Exception g) {
					      this.HA_Sold_Actual = 0.00f;
				     }
					
					try {
					if(driver.findElement(By.xpath("//b[.='NONSALE REVENUE ']/../../../div[contains(.,'Delivery Charge Cost')][1]/div[2]")).isDisplayed()) {
						js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='NONSALE REVENUE ']/../../../div[contains(.,'Delivery Charge Cost')][1]/div[2]")));
					String Delivery_Charge_Cost_Value = driver.findElement(By.xpath("//b[.='NONSALE REVENUE ']/../../../div[contains(.,'Delivery Charge Cost')][1]/div[2]")).getText();
					String Delivery_Charge_Cost_Value2 = Delivery_Charge_Cost_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					float Delivery_Charge_Cost_Value_actual = Float.parseFloat(Delivery_Charge_Cost_Value2);
					this.Delivery_Compensation_Actual = Delivery_Charge_Cost_Value_actual;
					}
				     }catch (Exception g) {
					      this.Delivery_Compensation_Actual = 0.00f;
				     }
					
					try {
					if(driver.findElement(By.xpath("//b[.='TIP / GRATUITY ']/../../../div[contains(.,'Gratuity')][1]/div[2]")).isDisplayed()) {
						js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='TIP / GRATUITY ']/../../../div[contains(.,'Gratuity')][1]/div[2]")));
					String Gratuity_Value = driver.findElement(By.xpath("//b[.='TIP / GRATUITY ']/../../../div[contains(.,'Gratuity')][1]/div[2]")).getText();
					String Gratuity_Value2 = Gratuity_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					float Gratuity_Value_actual = Float.parseFloat(Gratuity_Value2);
					this.Gratuity_Actual = Gratuity_Value_actual;
					}
			            }catch (Exception p) {
				      this.Gratuity_Actual = 0.00f;
			          }
					
					try {
					if(driver.findElement(By.xpath("(//b[.='CREDIT CARD']/../../../div[contains(.,'Total')]//div[5])[1]")).isDisplayed()) {
						js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//b[.='CREDIT CARD']/../../../div[contains(.,'Total')]//div[5])[1]")));
					String Service_Charge_Value = driver.findElement(By.xpath("(//b[.='CREDIT CARD']/../../../div[contains(.,'Total')]//div[5])[1]")).getText();
					String Service_Charge_Value2 = Service_Charge_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					float Service_Charge_Value_actual = Float.parseFloat(Service_Charge_Value2);
					this.Service_Charge_Actual = Service_Charge_Value_actual;
					}
		            }catch (Exception p) {
			      this.Service_Charge_Actual = 0.00f;
		          }
					
					Expected_All_Sale_Value = Sum_Of_All_Payment_Value_actual + Gross_Void_Sale_actual + Discount_Value_actual + Discount_Tax_Value_actual
							                    + Void_Discount_Value_actual + Void_Discount_Tax_Value_actual;
					
					Expected_Non_Sale_Revenue_Sale_Value = GC_Sold_Actual+GiveX_Sold_Actual+HA_Sold_Actual+Gratuity_Actual+
							                               Delivery_Compensation_Actual+Service_Charge_Actual;
					
					Expected_Gross_Sale_Value = Expected_All_Sale_Value - Expected_Non_Sale_Revenue_Sale_Value;
					
					Thread.sleep(5000);
					// Check the SALES
					if (driver.findElement(By.xpath("//b[.='SALES']")).isDisplayed()) {
						test.log(LogStatus.INFO, "SALES is available");

						System.out.println("SALES is available");

						// Replace all commo's with empty space
						String expected2 = Utility.getReportPropertyUser("Gross_Sales").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

						// Convert the String value of the Sale_Report_Gross_Sale element into float value
						float expect2 = Float.parseFloat(expected2);
						
						// Check weather the Gross Sales total is same or not
						if (Gross_Sale
								.equals(Utility.getReportPropertyUser("Gross_Sales"))) {
							test.log(LogStatus.PASS, "Actual and Expected Gross Sales Values are same");

							// Print the actual value
							System.out.println("The Actual Gross Sales Value is : " + expect1);

							test.log(LogStatus.PASS, "The Actual Gross Sales Value is : " + expect1);
						}

						else if (expect2 == unknownValue) {
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");

							System.out.println("The Actual Gross Sales value is : " + Gross_Sale);

							test.log(LogStatus.INFO, "The Actual Gross Sales value is : " + Gross_Sale);
						}

						else {
							test.log(LogStatus.FAIL, "Actual and Expected Gross Sales values are different");

							// Get the different
							float different = expect1 - expect2;

							// Print the different value
							System.out.println("Gross Sales Value different is : " + different);

							test.log(LogStatus.FAIL, "Gross Sales Value different is : " + different);
						}
						
						// Check weather the Gross Sales total is same or not
						if (expect1 == Expected_Gross_Sale_Value) {
							test.log(LogStatus.PASS, "Actual and Calculated_Expected Gross Sales Values are same");

							// Print the actual value
							System.out.println("The Actual Gross Sales Value is : " + Expected_Net_Sale_Value);

							test.log(LogStatus.PASS, "The Actual Gross Sales Value is : " + Expected_Net_Sale_Value);
						}

						else if (expect1 == unknownValue) {
							test.log(LogStatus.PASS, "Here we don't have the exact Calculated_expected value");

							System.out.println("The Actual Gross Sales value is : " + Gross_Sale);

							test.log(LogStatus.INFO, "The Actual Gross Sales value is : " + Gross_Sale);
						}

						else {
							test.log(LogStatus.FAIL, "Actual and Calculated_Expected Gross Sales values are different");

							// Get the different
							float different = expect1 - Expected_Gross_Sale_Value;

							// Print the different value
							System.out.println("Gross Sales Value different is : " + different);

							test.log(LogStatus.FAIL, "Gross Sales Value different is : " + different);
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
				
				
				// Check weather the Check Status
				if (driver.findElement(By.xpath("//b[.='SALES']")).isDisplayed()) {
					test.log(LogStatus.INFO, "Gross Receipt sale validation is stated");
		
					Thread.sleep(500);
					JavascriptExecutor js=(JavascriptExecutor)driver;	
					
					js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Gross Receipt')]/div[2]")));
					String Gross_Receipt = driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Gross Receipt')]/div[2]")).getText();
					String Gross_Receipt2 = Gross_Receipt.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					float expect1 = Float.parseFloat(Gross_Receipt2);
					
					Expected_Gross_Receipt_Value = Sum_Of_All_Payment_Actual;
				
					Thread.sleep(5000);
					// Check the SALES
					if (driver.findElement(By.xpath("//b[.='SALES']")).isDisplayed()) {
						test.log(LogStatus.INFO, "SALES is available");

						System.out.println("SALES is available");

						// Replace all commo's with empty space
						String expected2 = Utility.getReportPropertyUser("Gross_Receipt").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

						// Convert the String value of the Sale_Report_Gross_Receipt_Sale element into float value
						float expect2 = Float.parseFloat(expected2);
						
						// Check weather the Gross Receipt total is same or not
						if (Gross_Receipt
								.equals(Utility.getReportPropertyUser("Gross_Receipt"))) {
							test.log(LogStatus.PASS, "Actual and Expected Gross Receipt Values are same");

							// Print the actual value
							System.out.println("The Actual Gross Receipt Value is : " + expect1);

							test.log(LogStatus.PASS, "The Actual Gross Receipt Value is : " + expect1);
						}

						else if (expect2 == unknownValue) {
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");

							System.out.println("The Actual Gross Receipt value is : " + Gross_Receipt);

							test.log(LogStatus.INFO, "The Actual Gross Receipt value is : " + Gross_Receipt);
						}

						else {
							test.log(LogStatus.FAIL, "Actual and Expected Gross Receipt values are different");

							// Get the different
							float different = expect1 - expect2;

							// Print the different value
							System.out.println("Gross Receipt Value different is : " + different);

							test.log(LogStatus.FAIL, "Gross Receipt Value different is : " + different);
						}
						
						// Check weather the Gross Receipt total is same or not
						if (expect1 == Expected_Gross_Receipt_Value) {
							test.log(LogStatus.PASS, "Actual and Calculated_Expected Gross Receipt Values are same");

							// Print the actual value
							System.out.println("The Actual Gross Receipt Value is : " + Expected_Gross_Receipt_Value);

							test.log(LogStatus.PASS, "The Actual Gross Receipt Value is : " + Expected_Gross_Receipt_Value);
						}

						else if (expect1 == unknownValue) {
							test.log(LogStatus.PASS, "Here we don't have the exact Calculated_expected value");

							System.out.println("The Actual Gross Receipt value is : " + Expected_Gross_Receipt_Value);

							test.log(LogStatus.INFO, "The Actual Gross Receipt value is : " + Expected_Gross_Receipt_Value);
						}

						else {
							test.log(LogStatus.FAIL, "Actual and Calculated_Expected Gross Receipt values are different");

							// Get the different
							float different = expect1 - Expected_Gross_Receipt_Value;

							// Print the different value
							System.out.println("Gross Receipt Value different is : " + different);

							test.log(LogStatus.FAIL, "Gross Receipt Value different is : " + different);
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
				
				
				// Check weather the Check Status
				if (driver.findElement(By.xpath("//b[.='SALES']")).isDisplayed()) {
					test.log(LogStatus.INFO, "Grand sale Value validation is stated");
		
					Thread.sleep(500);
					JavascriptExecutor js=(JavascriptExecutor)driver;	
					
					js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Grand Sales')]/div[2]")));
					String Gross_Sale = driver.findElement(By.xpath("//b[.='SALES']/../../../div[contains(.,'Grand Sales')]/div[2]")).getText();
					String Gross_Sale2 = Gross_Sale.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
					float expect1 = Float.parseFloat(Gross_Sale2);
					
					int size3 = driver.findElements(By.xpath("//b[.='TAXES']/../../../div[contains(.,'Total Tax Collected')][1]/div")).size();
					
					
					if(size3 == 2) {
						
						int size1 =  driver.findElements(By.xpath("//b[.='TAXES']/../../../div//div[2]")).size();
						
						int size = size1 - 3;
						 
						 for(int r=1; r<=size;r++ ) {
							 
							 if(r == 1) {
							 continue;
							 }else {
								 
								 String Taxes = driver.findElement(By.xpath("(//b[.='TAXES']/../../../div//div[2])["+r+"]")).getText();
								 
								 String Report_Taxes = Taxes.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
								 
								 gobal_actual = Float.parseFloat(Report_Taxes);
								 
								 gob_Act3.add(gobal_actual);
									
								System.out.println("sum 2 " + gobal_actual);
								 
							 }
						 }
						}else {
							
							int size1 =  driver.findElements(By.xpath("//b[.='TAXES']/../../../div//div[4]")).size();
							
							int size = size1 - 3;
							 
							 for(int r=1; r<=size;r++ ) {
								 
								 if(r == 1) {
								 continue;
								 }else {
									 
									 String Taxes = driver.findElement(By.xpath("(//b[.='TAXES']/../../../div//div[4])["+r+"]")).getText();
									 
									 String Report_Taxes = Taxes.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
									 
									 gobal_actual = Float.parseFloat(Report_Taxes);
									 
									 gob_Act3.add(gobal_actual);
										
									System.out.println("sum 2 " + gobal_actual);
									 
									 
								 }
							 }
						}
					
					
					
					
					 

					 for (int i = 0; i < gob_Act3.size(); i++) {
						 gobal_actual2 += gob_Act3.get(i);
				 
				        
				    }
					 
					 
					 
					 if(size3 == 2) {
						    String Rounding_Off_Value = driver.findElement(By.xpath("//b[.='TAXES']/../../../div[contains(.,'Rounding Off')][1]/div[2]")).getText();
							String Rounding_Off_Value2 = Rounding_Off_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
							float Rounding_Off_Value_actual = Float.parseFloat(Rounding_Off_Value2);
							this.Expected_Rounding_Off_Value_actua = Rounding_Off_Value_actual;
							}else {
								    String Rounding_Off_Value = driver.findElement(By.xpath("//b[.='TAXES']/../../../div[contains(.,'Rounding Off')][1]/div[4]")).getText();
									String Rounding_Off_Value2 = Rounding_Off_Value.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
									float Rounding_Off_Value_actual = Float.parseFloat(Rounding_Off_Value2);
									this.Expected_Rounding_Off_Value_actua = Rounding_Off_Value_actual;
							}
					 
					  
						
						Expected_Total_Tax_value = gobal_actual2 + Expected_Rounding_Off_Value_actua;
					 
						Expected_Grand_Sale_Value = Expected_Net_Sale_Value + Expected_Total_Tax_value;
				
					Thread.sleep(5000);
					// Check the SALES
					if (driver.findElement(By.xpath("//b[.='SALES']")).isDisplayed()) {
						test.log(LogStatus.INFO, "SALES is available");

						System.out.println("SALES is available");

						// Replace all commo's with empty space
						String expected2 = Utility.getReportPropertyUser("Sale_Report_Grand_Sale").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

						// Convert the String value of the Sale_Report_Grand sale_Sale element into float value
						float expect2 = Float.parseFloat(expected2);
						
						// Check weather the Grand sale total is same or not
						if (Gross_Sale
								.equals(Utility.getReportPropertyUser("Sale_Report_Grand_Sale"))) {
							test.log(LogStatus.PASS, "Actual and Expected Grand sale Values are same");

							// Print the actual value
							System.out.println("The Actual Grand sale Value is : " + expect1);

							test.log(LogStatus.PASS, "The Actual Grand sale Value is : " + expect1);
						}

						else if (expect2 == unknownValue) {
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");

							System.out.println("The Actual Grand sale value is : " + Gross_Sale);

							test.log(LogStatus.INFO, "The Actual Grand sale value is : " + Gross_Sale);
						}

						else {
							test.log(LogStatus.FAIL, "Actual and Expected Grand sale values are different");

							// Get the different
							float different = expect1 - expect2;

							// Print the different value
							System.out.println("Grand sale Value different is : " + different);

							test.log(LogStatus.FAIL, "Grand sale Value different is : " + different);
						}
						
						// Check weather the Grand sale total is same or not
						if (expect1 == Expected_Grand_Sale_Value) {
							test.log(LogStatus.PASS, "Actual and Calculated_Expected Grand sale Values are same");

							// Print the actual value
							System.out.println("The Actual Grand sale Value is : " + Expected_Grand_Sale_Value);

							test.log(LogStatus.PASS, "The Actual Grand sale Value is : " + Expected_Grand_Sale_Value);
						}

						else if (expect1 == unknownValue) {
							test.log(LogStatus.PASS, "Here we don't have the exact Calculated_expected value");

							System.out.println("The Actual Grand sale value is : " + Expected_Grand_Sale_Value);

							test.log(LogStatus.INFO, "The Actual Grand sale value is : " + Expected_Grand_Sale_Value);
						}

						else {
							test.log(LogStatus.FAIL, "Actual and Calculated_Expected Grand sale values are different");

							// Get the different
							float different = expect1 - Expected_Grand_Sale_Value;

							// Print the different value
							System.out.println("Grand sale Value different is : " + different);

							test.log(LogStatus.FAIL, "Grand sale Value different is : " + different);
						}
						
						// Check weather the Total Tax value total is same or not
						if (Total_Tax_Value == Expected_Total_Tax_value) {
							test.log(LogStatus.PASS, "Actual and Calculated_Expected Total Tax Values are same");

							// Print the actual value
							System.out.println("The Actual Total Tax Value is : " + Expected_Total_Tax_value);

							test.log(LogStatus.PASS, "The Actual Total Tax Value is : " + Expected_Total_Tax_value);
						}

						else if (Total_Tax_Value == unknownValue) {
							test.log(LogStatus.PASS, "Here we don't have the exact Calculated_expected value");

							System.out.println("The Actual Total Tax value is : " + Expected_Total_Tax_value);

							test.log(LogStatus.INFO, "The Actual Total Tax value is : " + Expected_Total_Tax_value);
						}

						else {
							test.log(LogStatus.FAIL, "Actual and Calculated_Expected Total Tax values are different");

							// Get the different
							float different = Total_Tax_Value - Expected_Total_Tax_value;

							// Print the different value
							System.out.println("Total Tax Value different is : " + different);

							test.log(LogStatus.FAIL, "Total Tax Value different is : " + different);
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
				
				// Check weather the Check Status
				if (driver.findElement(By.xpath("//b[.='SALES']")).isDisplayed()) {
					test.log(LogStatus.INFO, "Deposite in Credit card and split up Value validation is stated");
		
					Thread.sleep(500);
					JavascriptExecutor js=(JavascriptExecutor)driver;	
					
					try {
					if(driver.findElement(By.xpath("(//b[.='CREDIT CARD']/../../../div[contains(.,'Total')]//div[3])[1]")).isDisplayed()) {
						js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//b[.='CREDIT CARD']/../../../div[contains(.,'Total')]//div[6])[1]")));
						String Deposit_Value1 = driver.findElement(By.xpath("(//b[.='CREDIT CARD']/../../../div[contains(.,'Total')]//div[6])[1]")).getText();
						String Deposit_Value2 = Deposit_Value1.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
						float expect1 = Float.parseFloat(Deposit_Value2);
					    this.Deposit_Value = expect1;
					    this.Deposit_Value1 = Deposit_Value1;
					}
	                  }catch (Exception p) {
		                  this.Deposit_Value = 0.00f;
		                  this.Deposit_Value1 = "0.00";
	                  }
					    
					try {
					if(driver.findElement(By.xpath("(//b[.='CREDIT CARD']/../../../div[contains(.,'Total')]//div[3])[1]")).isDisplayed()) {
						js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//b[.='CREDIT CARD']/../../../div[contains(.,'Total')]//div[3])[1]")));
						String Credit_Crad_Amount = driver.findElement(By.xpath("(//b[.='CREDIT CARD']/../../../div[contains(.,'Total')]//div[3])[1]")).getText();
						String Credit_Crad_Amount2 = Credit_Crad_Amount.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
						float Credit_Crad_Amount_Value = Float.parseFloat(Credit_Crad_Amount2);
					    this.Credit_Crad_Amount_Value = Credit_Crad_Amount_Value;
					}
	                  }catch (Exception p) {
		                  this.Credit_Crad_Amount_Value = 0.00f;
	                  }
					
					try {
					if(driver.findElement(By.xpath("(//b[.='CREDIT CARD']/../../../div[contains(.,'Total')]//div[4])[1]")).isDisplayed()) {
						js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//b[.='CREDIT CARD']/../../../div[contains(.,'Total')]//div[4])[1]")));
						String Credit_Crad_Tip = driver.findElement(By.xpath("(//b[.='CREDIT CARD']/../../../div[contains(.,'Total')]//div[4])[1]")).getText();
						String Credit_Crad_Tip2 = Credit_Crad_Tip.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
						float Credit_Crad_Tip_Value = Float.parseFloat(Credit_Crad_Tip2);
					    this.Credit_Crad_Tip_Value = Credit_Crad_Tip_Value;
					}
	                  }catch (Exception p) {
		                  this.Credit_Crad_Tip_Value = 0.00f;
	                  }
					
					
					Expected_Deposit_value = Credit_Crad_Amount_Value + Credit_Crad_Tip_Value;
					
					Thread.sleep(5000);
					// Check the SALES
					if (driver.findElement(By.xpath("//b[.='SALES']")).isDisplayed()) {
						test.log(LogStatus.INFO, "SALES is available");

						System.out.println("SALES is available");

						// Replace all commo's with empty space
						String expected2 = Utility.getReportPropertyUser("Deposit_Value").replaceAll("[a-zA-Z $ ₹ £ , :]", "");

						// Convert the String value of the Sale_Report_Deposite in Credit card and split up element into float value
						float expect2 = Float.parseFloat(expected2);
						
						// Check weather the Deposite in Credit card and split up total is same or not
						if (Deposit_Value1
								.equals(Utility.getReportPropertyUser("Deposit_Value"))) {
							test.log(LogStatus.PASS, "Actual and Expected Deposit Value are same");

							// Print the actual value
							System.out.println("The Actual Deposit Value is : " + Deposit_Value);

							test.log(LogStatus.PASS, "The Actual Deposit Value is : " + Deposit_Value);
						}

						else if (expect2 == unknownValue) {
							test.log(LogStatus.PASS, "Here we don't have the exact expected value");

							System.out.println("The Actual Deposit value is : " + Deposit_Value);

							test.log(LogStatus.INFO, "The Actual Deposit value is : " + Deposit_Value);
						}

						else {
							test.log(LogStatus.FAIL, "Actual and Expected Deposit values are different");

							// Get the different
							float different = Deposit_Value - expect2;

							// Print the different value
							System.out.println("Deposit Value different is : " + different);

							test.log(LogStatus.FAIL, "Deposit Value different is : " + different);
						}
						
						// Check weather the Deposit total is same or not
						if (Deposit_Value == Expected_Deposit_value) {
							test.log(LogStatus.PASS, "Actual and Calculated_Expected Deposit Values are same");

							// Print the actual value
							System.out.println("The Actual Deposit Value is : " + Expected_Deposit_value);

							test.log(LogStatus.PASS, "The Actual Deposit Value is : " + Expected_Deposit_value);
						}

						else if (Deposit_Value == unknownValue) {
							test.log(LogStatus.PASS, "Here we don't have the exact Calculated_expected value");

							System.out.println("The Actual Deposit value is : " + Expected_Deposit_value);

							test.log(LogStatus.INFO, "The Actual Deposit value is : " + Expected_Deposit_value);
						}

						else {
							test.log(LogStatus.FAIL, "Actual and Calculated_Expected Deposit values are different");

							// Get the different
							float different = Deposit_Value - Expected_Deposit_value;

							// Print the different value
							System.out.println("Deposit Value different is : " + different);

							test.log(LogStatus.FAIL, "Deposit Value different is : " + different);
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
				
				
				
	}
	
}
