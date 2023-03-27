package epicList_Chrome_Account_User;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Compare_Inventory_Specific_Date_Methos {
	
	public WebDriver driver;
	ArrayList<Double> qty = new ArrayList<Double>();
	ArrayList<Double> price = new ArrayList<Double>();
	ArrayList<Double> qty1 = new ArrayList<Double>();
	ArrayList<Double> price1 = new ArrayList<Double>();
	
	double Specific_day_previousday_Qty, Specific_day_previousday_Price, m, Specific_day_currentday_Qty, Specific_day_currentday_Price;
	double Actual_OnHand_Quanity, Actual_OnHand_Price, Actual_OnHand_Quanity1, Actual_OnHand_Price1;
	String max;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Reports_Compare_Inventory_Specific_Date");
	
	float unknownValue = 00;
	
	@AfterMethod
	public void tearDown(ITestResult result) throws Throwable {
		if (ITestResult.FAILURE == result.getStatus()) {
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



	
	@Test(priority = 1)
	public void login() throws Exception {
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
			Browser_Admin_Login a = new Browser_Admin_Login();
			a.Linga_login(driver, test);
		}
	}

	@Test(priority = 500)
	public void logout() throws Exception {
		Browser_Account_Level_User a = new Browser_Account_Level_User();
		a.Logout(driver, test);
	}
	
	@Test(priority = 3)
	public void calling() throws Exception {
		Thread.sleep(10000);
		try {
			Thread.sleep(1000);
			System.out.println("Minimize Chat Icon");
			driver.findElement(By.id("zsiq_minimize")).click();
		} catch (Exception e) {
			System.out.println("Minimize Chat Icon Missing");
		}
		// ViewDashBoardReports a = new ViewDashBoardReports();
		Thread.sleep(1000);
		Inventory_Reports_Compare_Inventory_Openpage(driver);
		Verify_and_Enable_Compare_Inventory_Toggles(driver);		
		Inventory_Reports_Compare_Inventory_Menu_Item_Specific_Day(driver);

		Thread.sleep(1500);
	}
	
	@Test(enabled = false, priority = 45)
	public void Inventory_Reports_Compare_Inventory_Openpage(WebDriver driver) throws Exception {
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(5000);
		// Get the URl
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id1") + "compareInventory");

		Thread.sleep(5000);
		try {
			// Check Compare Inventory page opened or not
			if (driver.findElement(By.xpath(excel.getData(2, 250, 1))).getText()
					.equalsIgnoreCase("Compare Inventory")) {
				test.log(LogStatus.PASS, "Compare Inventory page loaded Successfully");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			} else {
				test.log(LogStatus.FAIL, "Compare Inventory Inventory page loaded Failed");

				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s = "data:image/png;base64," + scnShot;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
		} catch (Exception e) {
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s = "data:image/png;base64," + scnShot;
			test.log(LogStatus.FAIL, test.addScreenCapture(s));
		}
		wb.close();
		Thread.sleep(5000);

	}

	@Test(priority = 46, enabled = false)
	public void Verify_and_Enable_Compare_Inventory_Toggles(WebDriver driver) throws Exception {
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		test.log(LogStatus.INFO, "Central Inventory Report Starts for Toggle Validation");

		// Select type as ALL
		Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
		ALL.selectByVisibleText("All");

		Thread.sleep(3000);

		// Select type as ALL
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Date Range");

		// Select the From Date Range
		driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
		// Enter the required From Date Range
		driver.findElement(By.xpath(excel.getData(3, 1722, 1)))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_From"));

		// Select the TO Date Range
		driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
		// Enter the required TO Date Range
		driver.findElement(By.xpath(excel.getData(3, 1723, 1)))
				.sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

		Thread.sleep(1000);
		// Click the Run Comparison button
		driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();

		Thread.sleep(2000);
		// Check whether the Beginning Toggle Available or not
		if (driver.findElement(By.xpath(excel.getData(3, 2629, 1))).isDisplayed()) {
			test.log(LogStatus.PASS, "Beginning Toggle Available on the Report");

			// Check whether the Beginning enabled or not
			if (driver.findElement(By.xpath(excel.getData(3, 2629, 1))).isEnabled()) {
				// Check whether the Beginning Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2630, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Beginning Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Beginning Column Not Displayed on the Report");
				}
			} else {
				Thread.sleep(1000);
				// Enable the Beginning
				driver.findElement(By.xpath(excel.getData(3, 2631, 1))).click();

				Thread.sleep(1000);
				// Check whether the Beginning Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2630, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Beginning Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Beginning Column Not Displayed on the Report");
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Beginning Toggle Not Available on the Report");

		}

		Thread.sleep(2000);
		// Check whether the Purchase Toggle Available or not
		if (driver.findElement(By.xpath(excel.getData(3, 2632, 1))).isDisplayed()) {
			test.log(LogStatus.PASS, "Purchase Toggle Available on the Report");

			// Check whether the Purchase enabled or not
			if (driver.findElement(By.xpath(excel.getData(3, 2632, 1))).isEnabled()) {
				// Check whether the Purchase Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2633, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Purchase Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Purchase Column Not Displayed on the Report");
				}
			} else {
				Thread.sleep(1000);
				// Enable the Purchase
				driver.findElement(By.xpath(excel.getData(3, 2634, 1))).click();

				Thread.sleep(1000);
				// Check whether the Purchase Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2633, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Purchase Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Purchase Column Not Displayed on the Report");
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Purchase Toggle Not Available on the Report");

		}

		Thread.sleep(2000);
		// Check whether the Ideal Used Toggle Available or not
		if (driver.findElement(By.xpath(excel.getData(3, 2635, 1))).isDisplayed()) {
			test.log(LogStatus.PASS, "Ideal Used Toggle Available on the Report");

			// Check whether the Ideal Used enabled or not
			if (driver.findElement(By.xpath(excel.getData(3, 2635, 1))).isEnabled()) {
				// Check whether the Ideal Used Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2636, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Ideal Used Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Ideal Used Column Not Displayed on the Report");
				}
			} else {
				Thread.sleep(1000);
				// Enable the Ideal Used
				driver.findElement(By.xpath(excel.getData(3, 2637, 1))).click();

				Thread.sleep(1000);
				// Check whether the Ideal Used Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2636, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Ideal Used Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Ideal Used Column Not Displayed on the Report");
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Ideal Used Toggle Not Available on the Report");

		}

		Thread.sleep(2000);
		// Check whether the Actual Toggle Available or not
		if (driver.findElement(By.xpath(excel.getData(3, 2638, 1))).isDisplayed()) {
			test.log(LogStatus.PASS, "Actual Toggle Available on the Report");

			// Check whether the Actual enabled or not
			if (driver.findElement(By.xpath(excel.getData(3, 2638, 1))).isEnabled()) {
				// Check whether the Actual Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2639, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Actual Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Actual Column Not Displayed on the Report");
				}
			} else {
				Thread.sleep(1000);
				// Enable the Actual
				driver.findElement(By.xpath(excel.getData(3, 2640, 1))).click();

				Thread.sleep(1000);
				// Check whether the Actual Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2639, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Actual Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Actual Column Not Displayed on the Report");
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Actual Toggle Not Available on the Report");

		}

		Thread.sleep(2000);
		// Check whether the Variance Toggle Available or not
		if (driver.findElement(By.xpath(excel.getData(3, 2641, 1))).isDisplayed()) {
			test.log(LogStatus.PASS, "Variance Toggle Available on the Report");

			// Check whether the Variance enabled or not
			if (driver.findElement(By.xpath(excel.getData(3, 2641, 1))).isEnabled()) {
				// Check whether the Variance Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2642, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Variance Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Variance Column Not Displayed on the Report");
				}
			} else {
				Thread.sleep(1000);
				// Enable the Variance
				driver.findElement(By.xpath(excel.getData(3, 2643, 1))).click();

				Thread.sleep(1000);
				// Check whether the Variance Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2642, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Variance Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Variance Column Not Displayed on the Report");
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Variance Toggle Not Available on the Report");

		}

		Thread.sleep(2000);
		// Check whether the Waste Toggle Available or not
		if (driver.findElement(By.xpath(excel.getData(3, 2644, 1))).isDisplayed()) {
			test.log(LogStatus.PASS, "Waste Toggle Available on the Report");

			// Check whether the Waste enabled or not
			if (driver.findElement(By.xpath(excel.getData(3, 2644, 1))).isEnabled()) {
				// Check whether the Waste Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2645, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Waste Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Waste Column Not Displayed on the Report");
				}
			} else {
				Thread.sleep(1000);
				// Enable the Waste
				driver.findElement(By.xpath(excel.getData(3, 2646, 1))).click();

				Thread.sleep(1000);
				// Check whether the Waste Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2645, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Waste Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Waste Column Not Displayed on the Report");
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Waste Toggle Not Available on the Report");

		}

		Thread.sleep(2000);
		// Check whether the Usage Toggle Available or not
		if (driver.findElement(By.xpath(excel.getData(3, 2647, 1))).isDisplayed()) {
			test.log(LogStatus.PASS, "Usage Toggle Available on the Report");

			// Check whether the Usage enabled or not
			if (driver.findElement(By.xpath(excel.getData(3, 2647, 1))).isEnabled()) {
				// Check whether the Usage Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2648, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Usage Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Usage Column Not Displayed on the Report");
				}
			} else {
				Thread.sleep(1000);
				// Enable the Usage
				driver.findElement(By.xpath(excel.getData(3, 2649, 1))).click();

				Thread.sleep(1000);
				// Check whether the Usage Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2648, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Usage Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Usage Column Not Displayed on the Report");
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Usage Toggle Not Available on the Report");

		}

		Thread.sleep(2000);
		// Check whether the Transfer in Toggle Available or not
		if (driver.findElement(By.xpath(excel.getData(3, 2650, 1))).isDisplayed()) {
			test.log(LogStatus.PASS, "Transfer in Toggle Available on the Report");

			// Check whether the Transfer in enabled or not
			if (driver.findElement(By.xpath(excel.getData(3, 2650, 1))).isEnabled()) {
				// Check whether the Transfer in Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2651, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Transfer in Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Transfer in Column Not Displayed on the Report");
				}
			} else {
				Thread.sleep(1000);
				// Enable the Transfer in
				driver.findElement(By.xpath(excel.getData(3, 2652, 1))).click();

				Thread.sleep(1000);
				// Check whether the Transfer in Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2651, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Transfer in Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Transfer in Column Not Displayed on the Report");
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Transfer in Toggle Not Available on the Report");

		}

		Thread.sleep(2000);
		// Check whether the Transfer out Toggle Available or not
		if (driver.findElement(By.xpath(excel.getData(3, 2653, 1))).isDisplayed()) {
			test.log(LogStatus.PASS, "Transfer out Toggle Available on the Report");

			// Check whether the Transfer out enabled or not
			if (driver.findElement(By.xpath(excel.getData(3, 2653, 1))).isEnabled()) {
				// Check whether the Transfer out Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2654, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Transfer out Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Transfer out Column Not Displayed on the Report");
				}
			} else {
				Thread.sleep(1000);
				// Enable the Transfer out
				driver.findElement(By.xpath(excel.getData(3, 2655, 1))).click();

				Thread.sleep(1000);
				// Check whether the Transfer out Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2654, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Transfer out Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Transfer out Column Not Displayed on the Report");
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Transfer out Toggle Not Available on the Report");

		}

		Thread.sleep(2000);
		// Check whether the On hand Toggle Available or not
		if (driver.findElement(By.xpath(excel.getData(3, 2656, 1))).isDisplayed()) {
			test.log(LogStatus.PASS, "On hand Toggle Available on the Report");

			// Check whether the On hand enabled or not
			if (driver.findElement(By.xpath(excel.getData(3, 2656, 1))).isEnabled()) {
				// Check whether the On hand Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2657, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "On hand Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "On hand Column Not Displayed on the Report");
				}
			} else {
				Thread.sleep(1000);
				// Enable the On hand
				driver.findElement(By.xpath(excel.getData(3, 2658, 1))).click();

				Thread.sleep(1000);
				// Check whether the On hand Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2657, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "On hand Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "On hand Column Not Displayed on the Report");
				}
			}
		} else {
			test.log(LogStatus.FAIL, "On hand Toggle Not Available on the Report");

		}

		Thread.sleep(2000);
		// Check whether the Unit Toggle Available or not
		if (driver.findElement(By.xpath(excel.getData(3, 2659, 1))).isDisplayed()) {
			test.log(LogStatus.PASS, "Unit Toggle Available on the Report");

			// Check whether the Unit enabled or not
			if (driver.findElement(By.xpath(excel.getData(3, 2659, 1))).isEnabled()) {
				// Check whether the Unit Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2660, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Unit Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Unit Column Not Displayed on the Report");
				}
			} else {
				Thread.sleep(1000);
				// Enable the Unit
				driver.findElement(By.xpath(excel.getData(3, 2661, 1))).click();

				Thread.sleep(1000);
				// Check whether the Unit Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2660, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Unit Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Unit Column Not Displayed on the Report");
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Unit Toggle Not Available on the Report");

		}

		Thread.sleep(2000);
		// Check whether the Type Toggle Available or not
		if (driver.findElement(By.xpath(excel.getData(3, 2662, 1))).isDisplayed()) {
			test.log(LogStatus.PASS, "Type Toggle Available on the Report");

			// Check whether the Type enabled or not
			if (driver.findElement(By.xpath(excel.getData(3, 2662, 1))).isEnabled()) {
				// Check whether the Type Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2663, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Type Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Type Column Not Displayed on the Report");
				}
			} else {
				Thread.sleep(1000);
				// Enable the Type
				driver.findElement(By.xpath(excel.getData(3, 2664, 1))).click();

				Thread.sleep(1000);
				// Check whether the Type Column Displayed or not
				if (driver.findElement(By.xpath(excel.getData(3, 2663, 1))).isDisplayed()) {
					test.log(LogStatus.PASS, "Type Column Displayed on the Report");
				} else {
					test.log(LogStatus.FAIL, "Type Column Not Displayed on the Report");
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Type Toggle Not Available on the Report");

		}
		wb.close();
		test.log(LogStatus.INFO, "Central Inventory Report Ends for Toggles Validation");

	}
	
	@Test(enabled = false, priority = 48)
	public void Inventory_Reports_Compare_Inventory_Menu_Item_Specific_Day(WebDriver driver) throws Exception {
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		FileInputStream fis = new FileInputStream(src);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet1 = wb.getSheetAt(1);
		sheet1.getLastRowNum();

		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Central Inventory Report for Menu Item Selection Starts (Today)");
		// Select type as ALL
		Select Sub = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
		Sub.selectByVisibleText("Inventory Item");

		Thread.sleep(3000);
		// Click type as Time period
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Specific date");

		Thread.sleep(2000);
		WebElement sp = driver.findElement(By.xpath("//input[@ng-model='query.specificDate']"));
		sp.clear();
		sp.sendKeys("22-Aug-2022");
		
		for (int o = 1; o <= 2; o++) {
		
		driver.findElement(By.xpath("//div//a[@class = 'btn btn-success input-group-addon']")).click();
		Thread.sleep(1000);
//		driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm pull-left uib-left']")).click();
		
		Robot rob = new Robot();
		
		rob.keyPress(KeyEvent.VK_LEFT);
		rob.keyRelease(KeyEvent.VK_LEFT);
		
		rob.keyPress(KeyEvent.VK_ENTER);
		rob.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(2000);
		// Click the Run Comparison button
		driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();

		Thread.sleep(2000);
		try {
			if (driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText()
					.equalsIgnoreCase("No records found")) {
				test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
			}
		} catch (Exception k) {

			test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
			Thread.sleep(5000);
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,2000)");

			List<WebElement> leftPagination = driver.findElements(By.xpath("//ul[@class='pagination pull-right ng-scope']/li"));
			int LeftPageSize = leftPagination.size();
			LeftPageSize=LeftPageSize-1;
			System.out.println(LeftPageSize);
			//int lastLeftPageSize = LeftPageSize - 1;
//			System.out.println(LeftPageSize);
			

			// Click the pagination for Report row increase button
			String sizelimit = driver.findElement(By.xpath("//ul[@class='pagination pull-right ng-scope']//li["+LeftPageSize+"]/a")).getText();
			
			int pagenavi = Integer.parseInt(sizelimit);
			System.out.println("Last pagination number is : "+pagenavi);
			for (int i = 1; i <= pagenavi; i++) {
				
			driver.findElement(By.xpath("//li[@ng-switch='page.type']//a[@ng-switch-when='next']")).click();

			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

			Thread.sleep(2000);
			for (int j = 3; j <= rows.size(); j++) {
				test.log(LogStatus.INFO,
						"Item Name is : " + driver.findElement(By.xpath("//tr[" + j + "]/td[1]")).getText());

				String IdealQty = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.idealUsed.value'][1]"))
						.getText();
				double Ideal_Used_Quanity = Double.parseDouble(IdealQty);

				String VarianceQty = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.variance.value'][1]"))
						.getText();
				double Variance_Quanity = Double.parseDouble(VarianceQty);

//																						String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//																						double Wastage_Quanity=Double.parseDouble(WasteQty);

				double Expected_Usage_Quantity = Ideal_Used_Quanity + Variance_Quanity;

				String Actual_UsageQty = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.usage.value'][1]"))
						.getText();
				double Actual_Usage_Quanity = Double.parseDouble(Actual_UsageQty);

				// Check whether the Actual and Expected Usage Quantity is Equal or not
//				if (Actual_Usage_Quanity == Expected_Usage_Quantity) {
//					test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
//				} else {
//					double diff = Actual_Usage_Quanity - Expected_Usage_Quantity;
//					test.log(LogStatus.FAIL,
//							"Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "
//									+ diff);
//				}

				String IdealPrice = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.idealUsed.value'][2]"))
						.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				
				double Ideal_Used_Price = Double.parseDouble(IdealPrice);

				String VariancePrice = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.variance.value'][2]"))
						.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Variance_Price = Double.parseDouble(VariancePrice);

				double Expected_Usage_Price = Ideal_Used_Price + Variance_Price;

				String Actual_UsagePrice = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.usage.value'][2]"))
						.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Actual_Usage_Price = Double.parseDouble(Actual_UsagePrice);

				// Check whether the Actual and Expected Usage Price is Equal or not
//				if (Actual_Usage_Price == Expected_Usage_Price) {
//					test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
//				} else {
//					double diff = Actual_Usage_Price - Expected_Usage_Price;
//
//					test.log(LogStatus.FAIL,
//							"Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "
//									+ diff);
//				}

				// Get the Beginning Quantity
				String BeginningeQty = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.beginning.value'][1]"))
						.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Beginning_Quanity = Double.parseDouble(BeginningeQty);

				// Purchase Quantity
				String PurchaseQty = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.purchase.value'][1]"))
						.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Purchase_Quanity = Double.parseDouble(PurchaseQty);

				// Transfer In Quantity
				String TransferInQty = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.transferIn.value'][1]"))
						.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double TransferIn_Quanity = Double.parseDouble(TransferInQty);

				// Transfer Out Quantity
				String TransferOutQty = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.transferOut.value'][1]"))
						.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double TransferOut_Quanity = Double.parseDouble(TransferOutQty);

				double Expected_On_Hand_Qty = Beginning_Quanity + Purchase_Quanity - Actual_Usage_Quanity
						+ TransferIn_Quanity - TransferOut_Quanity;

				// Actual On Hand Quantity
				String OnHandQty = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.onHand.value'][1]"))
						.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Actual_OnHand_Quanity = Double.parseDouble(OnHandQty);

				// Check whether the Actual and Expected On Hand Quantity is Equal or not
				if (Actual_OnHand_Quanity == Expected_On_Hand_Qty) {
					test.log(LogStatus.PASS, "Actual and Expected On Hand Quantity is Equal in Compare Inventory.");
				} else {
					double diff = Actual_OnHand_Quanity - Expected_On_Hand_Qty;
					test.log(LogStatus.FAIL,
							"Actual and Expected On Hand Quantity is not Equal in Compare Inventory. Quantity Diff is : "
									+ diff);
				}
				
				// Get the Beginning Price
				String BeginningePrice = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.beginning.value'][2]"))
						.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Beginning_Price = Double.parseDouble(BeginningePrice);

				// Purchase Quantity
				String PurchasePrice = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.purchase.value'][2]"))
						.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Purchase_Price = Double.parseDouble(PurchasePrice);

				// Transfer In Quantity
				String TransferInPrice = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.transferIn.value'][2]"))
						.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double TransferIn_Price = Double.parseDouble(TransferInPrice);

				// Transfer Out Quantity
				String TransferOutPrice = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.transferOut.value'][2]"))
						.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double TransferOut_Price = Double.parseDouble(TransferOutPrice);

				double Expected_On_Hand_Price = Beginning_Price + Purchase_Price - Actual_Usage_Price
						+ TransferIn_Price - TransferOut_Price;

				// Actual On Hand Price
				String OnHandPrice = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.onHand.value'][2]"))
						.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Actual_OnHand_Price = Double.parseDouble(OnHandPrice);

				// Check whether the Actual and Expected On Hand Price is Equal or not
				if (Actual_OnHand_Price == Expected_On_Hand_Price) {
					test.log(LogStatus.PASS, "Actual and Expected On Hand Price is Equal in Compare Inventory.");
				} else {
					double diff = Actual_OnHand_Price - Expected_On_Hand_Price;
					test.log(LogStatus.FAIL,
							"Actual and Expected On Hand Price is not Equal in Compare Inventory. Quantity Diff is : "
									+ diff);
				}

			}
			}
				
				Thread.sleep(2000);
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("window.scrollBy(0,-2000)");
				
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div//a[@class = 'btn btn-success input-group-addon']")).click();
				Thread.sleep(1000);
//				driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm pull-left uib-left']")).click();
				
				Robot rob1 = new Robot();
				
				rob1.keyPress(KeyEvent.VK_RIGHT);
				rob1.keyRelease(KeyEvent.VK_RIGHT);
				
				rob.keyPress(KeyEvent.VK_ENTER);
				rob.keyRelease(KeyEvent.VK_ENTER);
				
				Thread.sleep(2000);
				// Click the Run Comparison button
				driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
				
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div//a[@class = 'btn btn-success input-group-addon']")).click();
				
				rob1.keyPress(KeyEvent.VK_RIGHT);
				rob1.keyRelease(KeyEvent.VK_RIGHT);
				
				rob.keyPress(KeyEvent.VK_ENTER);
				rob.keyRelease(KeyEvent.VK_ENTER);
				
				Thread.sleep(2000);
				try {
					if (driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText()
							.equalsIgnoreCase("No records found")) {
						test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
					}
				} catch (Exception k1) {

					test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
					Thread.sleep(5000);
					
					JavascriptExecutor js2 = (JavascriptExecutor) driver;
					js2.executeScript("window.scrollBy(0,2000)");

					List<WebElement> leftPagination2 = driver.findElements(By.xpath("//ul[@class='pagination pull-right ng-scope']/li"));
					int LeftPageSize2 = leftPagination2.size();
					LeftPageSize=LeftPageSize-1;

					// Click the pagination for Report row increase button
					String sizelimit2 = driver.findElement(By.xpath("//ul[@class='pagination pull-right ng-scope']/li["+LeftPageSize2+"]/a")).getText();
					
					int pagenavi2 = Integer.parseInt(sizelimit2);
					
					for (int b = 1; b <= pagenavi2; b++) {
						
					driver.findElement(By.xpath("//li[@ng-switch='page.type']//a[@ng-switch-when='next']")).click();

					List<WebElement> rows2 = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

					for (int m = 3; m <= rows2.size(); m++) {
						test.log(LogStatus.INFO,
								"Item Name is : " + driver.findElement(By.xpath("//tr[" + m + "]/td[1]")).getText());

						// Actual On Hand Quantity
						String OnHandQty2 = driver
								.findElement(By.xpath("//tr[" + m + "]/td[@ng-if='reportColumns.onHand.value'][1]")).getText()
								.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString().substring(1).toString();
//						double Actual_OnHand_Quanity = Double.parseDouble(OnHandQty);
						 NumberFormat f =NumberFormat.getNumberInstance(); 
				            Number num = f.parse(OnHandQty2);
				            max = num.toString();
				            Actual_OnHand_Quanity1 = Double.parseDouble(max);
//																									System.out.println(Yesterday_Onhand_Qty);

						// Actual On Hand Price
						String OnHandPrice2 = driver
								.findElement(By.xpath("//tr[" + m + "]/td[@ng-if='reportColumns.onHand.value'][2]")).getText()
								.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
//						double Actual_OnHand_Price = Double.parseDouble(OnHandPrice);
						 NumberFormat f1 =NumberFormat.getNumberInstance(); 
						Number num1 = f1.parse(OnHandPrice2);
			            max = num1.toString();
			            Actual_OnHand_Price1 = Double.parseDouble(max);
//																									System.out.println(Yesterday_Onhand_Price);

					}
					
					}
					
				}
			
			
		}
		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Central Inventory Report for Sub Recipe Selection End (Date Range)");

		wb.close();
	}
		
	}
}

	
