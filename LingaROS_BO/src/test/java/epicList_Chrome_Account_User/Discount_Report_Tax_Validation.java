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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Discount_Report_Tax_Validation {
	
	
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
			Browser_Admin_Login a = new Browser_Admin_Login();
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
		Discount_Report_Tax_Validation1(driver);
		
	}
	
	public void Discount_Report_Tax_Validation1(WebDriver driver) throws Exception {
		
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
					
					
//					WebDriverWait wai2=new WebDriverWait(driver, 30);
//					wai2.until(ExpectedConditions.elementToBeClickable(ele1)).click();
					
					driver.findElement(By.xpath("//a[@ng-switch-when='next']")).click();
					
//					if (driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]")).isDisplayed()) {
						
					
				
					int size = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]")).size();
				
					for(int r=1; r<=size;r++ ) {
				
//              		 int size = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]")).size();
               
						String total_Discount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]["+r+"]/td[10]")).getText();
				
//						System.out.println("Total " + total_Discount);
						// Replace all commo's with empty space
						String ReportText = total_Discount.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
				
						this.string_Value_Of_Expected3 = ReportText;
				
						// Convert the String value of the Discount Total element into float value
						gobal_actual7 = Float.parseFloat(ReportText);
						
//						System.out.println("sum 1 " + gobal_actual);
				
						gob_Act3.add(gobal_actual7);
						
						System.out.println("sum 2 " + gobal_actual7);
				
						// Replace all commo's with empty space
						String actualText = Sale_Report_Discount_Expected.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

						// Convert the String value of the Discount Total element into float value
						gobal_actual8 = Float.parseFloat(actualText);
				
//						gob_Act2.add(gobal_actual2);
				

					}
					
//					}
				
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
//				if (driver.findElement(By.xpath("//li[@ng-repeat='page in pages']//a[@ng-switch-when='last']")).isDisplayed()) {
//					
//					driver.findElement(By.xpath("//li[@ng-repeat='page in pages']//a[@ng-switch-when='last']")).click();
//					
//				}
//				
//				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
//				
//				Thread.sleep(3000);
//				String Sale_Report_Discount_Expected = Utility.getReportPropertyUser("Sale_Report_Discount");
//				
////               int size = driver.findElements(By.xpath("//td[@data-title-text='Discount Amount']//span")).size();
//               
//               String total_Discount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]/td[10]")).getText();
//				
//				// Replace all commo's with empty space
//				String ReportText = total_Discount.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
//				
//				// Convert the String value of the Discount Total element into float value
//				float actual2 = Float.parseFloat(ReportText);
//				
//				// Replace all commo's with empty space
//				String actualText = Sale_Report_Discount_Expected.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
//
//				// Convert the String value of the Discount Total element into float value
//				float actual = Float.parseFloat(actualText);
//
//				
//				
//				if (ReportText.equals(Sale_Report_Discount_Expected)) {
//					test.log(LogStatus.PASS, "Actual and Expected DIscount Values are same");
//
//					// Print the actual value
//					System.out.println("The Actual Discount Value is : " + ReportText);
//
//					test.log(LogStatus.PASS, "The Actual Discount Value is : " + ReportText);
//				}
//
//				else if (actual == unknownValue) {
//					test.log(LogStatus.PASS, "Here we don't have the exact expected value");
//
//					System.out.println("The Actual Discount value is : " + ReportText);
//
//					test.log(LogStatus.INFO, "The Actual Discount value is : " + ReportText);
//				}
//
//				else {
//					test.log(LogStatus.FAIL, "Actual and Expected Discount values are different");
//
//					// Get the different
//					float different = actual - actual2;
//
//					// Print the different value
//					System.out.println("Net Sales Value different is : " + different);
//
//					test.log(LogStatus.FAIL, "Net Sales Value different is : " + different);
//				}
//				
//				
//				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
//				
//				String scnShot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//				String s2="data:image/png;base64,"+scnShot2;
//				test.log(LogStatus.INFO,test.addScreenCapture(s2));
//	}
			
			
			
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
					
					
//					WebDriverWait wai2=new WebDriverWait(driver, 30);
//					wai2.until(ExpectedConditions.elementToBeClickable(ele1)).click();
					
					driver.findElement(By.xpath("//a[@ng-switch-when='next']")).click();
				
					int size = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]")).size();
				
					for(int r=1; r<=size;r++ ) {
				
//              		 int size = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]")).size();
               
						String total_Discount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]["+r+"]/td[10]")).getText();
				
//						System.out.println("Total " + total_Discount);
						// Replace all commo's with empty space
						String ReportText = total_Discount.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
				
						this.string_Value_Of_Expected = ReportText;
				
						// Convert the String value of the Discount Total element into float value
						gobal_actual = Float.parseFloat(ReportText);
						
//						System.out.println("sum 1 " + gobal_actual);
				
						gob_Act1.add(gobal_actual);
						
						System.out.println("sum 2 " + gobal_actual);
				
						// Replace all commo's with empty space
						String actualText = Sale_Report_Discount_Expected.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

						// Convert the String value of the Discount Total element into float value
						gobal_actual2 = Float.parseFloat(actualText);
				
//						gob_Act2.add(gobal_actual2);
				

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
					
					
//					WebDriverWait wai2=new WebDriverWait(driver, 30);
//					wai2.until(ExpectedConditions.elementToBeClickable(ele1)).click();
					
					driver.findElement(By.xpath("//a[@ng-switch-when='next']")).click();
					
				
					int size = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]")).size();
				
					for(int r=1; r<=size;r++ ) {
				
//              		 int size = driver.findElements(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]")).size();
               
						String total_Discount = driver.findElement(By.xpath("//table[@class='table table-bordered ng-table-responsive ng-scope ng-table']/tbody/tr[contains(.,'Total')]["+r+"]/td[10]")).getText();
				
//						System.out.println("Total " + total_Discount);
						// Replace all commo's with empty space
						String ReportText = total_Discount.replaceAll("[a-zA-Z $ ₹ £ , :]", "");
				
						this.string_Value_Of_Expected2 = ReportText;
				
						// Convert the String value of the Discount Total element into float value
						gobal_actual3 = Float.parseFloat(ReportText);
						
//						System.out.println("sum 1 " + gobal_actual);
				
						gob_Act2.add(gobal_actual3);
						
						System.out.println("sum 2 " + gobal_actual3);
				
						// Replace all commo's with empty space
						String actualText = Sale_Report_Discount_Expected.replaceAll("[a-zA-Z $ ₹ £ , :]", "");

						// Convert the String value of the Discount Total element into float value
						gobal_actual4 = Float.parseFloat(actualText);
				
//						gob_Act2.add(gobal_actual2);
				

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
