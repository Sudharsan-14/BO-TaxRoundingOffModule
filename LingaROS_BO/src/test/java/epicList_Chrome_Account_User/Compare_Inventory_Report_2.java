package epicList_Chrome_Account_User;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.Date;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Compare_Inventory_Report_2 {
	public WebDriver driver;
	double Yesterday_Onhand_Qty, Yesterday_Onhand_Price, The_previousday_Of_Yesterday_Onhand_Qty,
			The_previousday_Of_Yesterday_Onhand_Price, The_Last_Number_Of_Days_Previousday_Qty,
			The_Last_Number_Of_Days_Previousday_Price, This_Week_Previous_Sunday_Qty, This_Week_Previous_Sunday_Price,
			Last_Week_Previous_Sunday_Qty, Last_Week_Previous_Sunday_Price, Last_7_Days_previousday_Qty,
			Last_7_Days_previousday_Price, Last_30_Days_previousday_Qty, Last_30_Days_previousday_price,
			Specific_day_previousday_Qty, Specific_day_previousday_Price;

	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Reports_Compare_Inventory");

	float unknownValue = 00;
	double DateRange_previousday_Qty;
	double DateRange_previousday_Price;
    double this_Month_Pervious_day_Onhand_Qty;
    double this_Month_Pervious_day_Onhand_Price;
    double last_Month_Pervious_day_Onhand_Qty;
    double last_Month_Pervious_day_Onhand_Price;
    
    
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}
	}

	@AfterMethod
	public void flushTest() throws Exception {
		Thread.sleep(2000);
		rep.endTest(test);
	//	rep.flush();
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
//		Inventory_Reports_Compare_Inventory_Menu_Item_Today(driver);
//		Inventory_Reports_Compare_Inventory_Menu_Item_Yesterday(driver);
//		Inventory_Reports_Compare_Inventory_Menu_Item_Last_Number_Of_Days(driver);
//		Inventory_Reports_Compare_Inventory_Menu_Item_This_Week(driver);
//		Inventory_Reports_Compare_Inventory_Menu_Item_Last_Week(driver);
//		Inventory_Reports_Compare_Inventory_Menu_Item_Last_7_Days(driver);
//		Inventory_Reports_Compare_Inventory_Menu_Item_This_Month(driver);
//		Inventory_Reports_Compare_Inventory_Menu_Item_Last_Month(driver);
//		Inventory_Reports_Compare_Inventory_Menu_Item_Last_30_Days(driver);
		Inventory_Reports_Compare_Inventory_Menu_Item_Specific_Day(driver);
//		Inventory_Reports_Compare_Inventory_Menu_Item_Date_Range(driver);

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

		Thread.sleep(2000);
		/*
		 * //Click the Inventory option
		 * driver.findElement(By.xpath("//span[.='Inventory']")).click(); // Create
		 * instance of Java script executor JavascriptExecutor je = (JavascriptExecutor)
		 * driver; //Identify the WebElement which will appear after scrolling down
		 * WebElement element =
		 * driver.findElement(By.xpath("//span[contains(.,'Reports')]")); //Scroll the
		 * page till the Inventory Reports option present
		 * je.executeScript("arguments[0].scrollIntoView(true);",element); //Click the
		 * Inventory Reports Option
		 * driver.findElement(By.xpath("//span[contains(.,'Reports')]")).click();
		 * //driver.findElement(By.xpath(
		 * "//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[4]/ul/li[11]/a/i")).click(
		 * );
		 */
		
		
		
		
//		Thread.sleep(5000);
//		//Click on the store.
//		driver.findElement(By.xpath("//a//h5[@title='NFS Technology Group Demo']/.")).click();
		
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
	public void Inventory_Reports_Compare_Inventory_Menu_Item_Today(WebDriver driver) throws Exception {
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
		Sub.selectByVisibleText("Menu Item");

		Thread.sleep(3000);
		// Click type as Time period
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Yesterday");

		Thread.sleep(1000);
		// Click the Run Comparison button
		driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();

		Thread.sleep(2000);
		try {
			if (driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText()
					.equalsIgnoreCase("No records found")) {
				test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
			}
		} catch (Exception d) {

			test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
			Thread.sleep(5000);

			List<WebElement> leftPagination = driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
			int LeftPageSize = leftPagination.size();

			
			// Click the pagination for Report row increase button
			driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

			for (int i = 3; i <= rows.size(); i++) {
				test.log(LogStatus.INFO,
						"Item Name is : " + driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText());

				// Actual On Hand Quantity
				String OnHandQty = driver
						.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][1]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString().substring(1).toString();
				double Actual_OnHand_Quanity = Double.parseDouble(OnHandQty);
				this.Yesterday_Onhand_Qty = Actual_OnHand_Quanity;
//					System.out.println(Yesterday_Onhand_Qty);

				// Actual On Hand Price
				String OnHandPrice = driver
						.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][2]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Actual_OnHand_Price = Double.parseDouble(OnHandPrice);
				this.Yesterday_Onhand_Price = Actual_OnHand_Price;
//					System.out.println(Yesterday_Onhand_Price);

			}

			// Select the From Date Range
//				driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
//				//Enter the required From Date Range
//				driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
//						
//				//Select the TO Date Range
//				driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
//				//Enter the  required TO Date Range
//				driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

			// Select type as ALL
			Thread.sleep(3000);
			// Click type as Time period
			Select TimePeriod1 = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
			TimePeriod1.selectByVisibleText("Today");

			Thread.sleep(1000);
			// Click the Run Comparison button
			driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();

			Thread.sleep(2000);
			try {
				if (driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText()
						.equalsIgnoreCase("No records found")) {
					test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
				}
			} catch (Exception g) {

				test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
				Thread.sleep(5000);

				List<WebElement> leftPagination1 = driver
						.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
				int LeftPageSize1 = leftPagination.size();

				// Click the pagination for Report row increase button
				driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

				List<WebElement> rows1 = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

				for (int i = 3; i <= rows1.size(); i++) {

					test.log(LogStatus.INFO,
							"Item Name is : " + driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText());

					String IdealQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][1]"))
							.getText();
					double Ideal_Used_Quanity = Double.parseDouble(IdealQty);

					String VarianceQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][1]"))
							.getText();
					double Variance_Quanity = Double.parseDouble(VarianceQty);

//					String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//					double Wastage_Quanity=Double.parseDouble(WasteQty);

					double Expected_Usage_Quantity = Ideal_Used_Quanity + Variance_Quanity;

					String Actual_UsageQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][1]"))
							.getText();
					double Actual_Usage_Quanity = Double.parseDouble(Actual_UsageQty);

					// Check whether the Actual and Expected Usage Quantity is Equal or not
					if (Actual_Usage_Quanity == Expected_Usage_Quantity) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
					} else {
						double diff = Actual_Usage_Quanity - Expected_Usage_Quantity;
						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String IdealPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					;
					double Ideal_Used_Price = Double.parseDouble(IdealPrice);

					String VariancePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Variance_Price = Double.parseDouble(VariancePrice);

					String WastePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.wastage.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Wastage_Price = Double.parseDouble(WastePrice);

					double Expected_Usage_Price = Ideal_Used_Price + Variance_Price;

					String Actual_UsagePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Actual_Usage_Price = Double.parseDouble(Actual_UsagePrice);

					// Check whether the Actual and Expected Usage Price is Equal or not
					if (Actual_Usage_Price == Expected_Usage_Price) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
					} else {
						double diff = Actual_Usage_Price - Expected_Usage_Price;

						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "
										+ diff);
					}

					// Get the Beginning Quantity
					String BeginningeQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Quanity = Double.parseDouble(BeginningeQty);

					// Purchase Quantity
					String PurchaseQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Quanity = Double.parseDouble(PurchaseQty);

					// Transfer In Quantity
					String TransferInQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Quanity = Double.parseDouble(TransferInQty);

					// Transfer Out Quantity
					String TransferOutQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Quanity = Double.parseDouble(TransferOutQty);

					double Expected_On_Hand_Qty = Beginning_Quanity + Purchase_Quanity - Actual_Usage_Quanity
							+ TransferIn_Quanity - TransferOut_Quanity;

					// Actual On Hand Quantity
					String OnHandQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][1]"))
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

					// Check whether the Beginning Quantity and On Hand Quantity is Equal or not
					if (Yesterday_Onhand_Qty == Beginning_Quanity) {
						test.log(LogStatus.PASS,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Quanity - Yesterday_Onhand_Qty;
						test.log(LogStatus.FAIL,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					// Get the Beginning Price
					String BeginningePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Price = Double.parseDouble(BeginningePrice);

					// Purchase Quantity
					String PurchasePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Price = Double.parseDouble(PurchasePrice);

					// Transfer In Quantity
					String TransferInPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Price = Double.parseDouble(TransferInPrice);

					// Transfer Out Quantity
					String TransferOutPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Price = Double.parseDouble(TransferOutPrice);

					double Expected_On_Hand_Price = Beginning_Price + Purchase_Price - Actual_Usage_Price
							+ TransferIn_Price - TransferOut_Price;

					// Actual On Hand Price
					String OnHandPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][2]"))
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

					// Check whether the Beginning Price and On Hand Price is Equal or not
					if (Yesterday_Onhand_Price == Beginning_Price) {
						test.log(LogStatus.PASS,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Price - Yesterday_Onhand_Price;
						test.log(LogStatus.FAIL,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String selTypeText = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.type.value']")).getText();
					// Check whether the selected type displays in the Type column or not
					if (selTypeText.equalsIgnoreCase("MENUITEM")) {
						test.log(LogStatus.PASS, "Type column shows selected Type");
					} else {
						test.log(LogStatus.FAIL, "Type column shows Incorrect type for selected type: " + selTypeText);
					}
				}
			}
			

			Thread.sleep(2000);
			test.log(LogStatus.INFO, "Central Inventory Report for Sub Recipe Selection End (Date Range)");

			wb.close();
		}
	}

	@Test(enabled = false, priority = 48)
	public void Inventory_Reports_Compare_Inventory_Menu_Item_Yesterday(WebDriver driver) throws Exception {
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
		Sub.selectByVisibleText("Menu Item");

		Thread.sleep(3000);
		// Click type as Time period
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Specific date");

		Thread.sleep(2000);
		// Clear the Specific date
		driver.findElement(By.xpath(excel.getData(3, 2454, 1))).clear();

		Thread.sleep(2000);
		// Click on the day option in the date selection tab.
		driver.findElement(By.xpath("//i[@class='fa fa-calendar']//..")).click();

		Robot rob = new Robot();

		Thread.sleep(2000);
		for (int i = 1; i < 3; i++) {
			rob.keyPress(KeyEvent.VK_LEFT);
			rob.keyRelease(KeyEvent.VK_LEFT);
		}

		Thread.sleep(2000);
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
		} catch (Exception d) {

			test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
			Thread.sleep(5000);

			List<WebElement> leftPagination = driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
			int LeftPageSize = leftPagination.size();

			// Click the pagination for Report row increase button
			driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

			for (int i = 3; i <= rows.size(); i++) {
				test.log(LogStatus.INFO,
						"Item Name is : " + driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText());

				// Actual On Hand Quantity
				String OnHandQty = driver
						.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][1]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Actual_OnHand_Quanity = Double.parseDouble(OnHandQty);
				this.The_previousday_Of_Yesterday_Onhand_Qty = Actual_OnHand_Quanity;

				// Actual On Hand Price
				String OnHandPrice = driver
						.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][2]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Actual_OnHand_Price = Double.parseDouble(OnHandPrice);
				this.The_previousday_Of_Yesterday_Onhand_Price = Actual_OnHand_Price;

			}

			// Select the From Date Range
//							driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
//							//Enter the required From Date Range
//							driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
//									
//							//Select the TO Date Range
//							driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
//							//Enter the  required TO Date Range
//							driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

			Thread.sleep(3000);
			Select TimePeriod1 = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
			TimePeriod1.selectByVisibleText("Yesterday");

			Thread.sleep(2000);
			// Click the Run Comparison button
			driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();

			Thread.sleep(2000);
			try {
				if (driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText()
						.equalsIgnoreCase("No records found")) {
					test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
				}
			} catch (Exception g) {

				test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
				Thread.sleep(5000);

				List<WebElement> leftPagination1 = driver
						.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
				int LeftPageSize1 = leftPagination.size();

				// Click the pagination for Report row increase button
				driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

				List<WebElement> rows1 = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

				for (int i = 3; i <= rows1.size(); i++) {

					test.log(LogStatus.INFO,
							"Item Name is : " + driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText());

					String IdealQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][1]"))
							.getText();
					double Ideal_Used_Quanity = Double.parseDouble(IdealQty);

					String VarianceQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][1]"))
							.getText();
					double Variance_Quanity = Double.parseDouble(VarianceQty);

//								String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//								double Wastage_Quanity=Double.parseDouble(WasteQty);

					double Expected_Usage_Quantity = Ideal_Used_Quanity + Variance_Quanity;

					String Actual_UsageQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][1]"))
							.getText();
					double Actual_Usage_Quanity = Double.parseDouble(Actual_UsageQty);

					// Check whether the Actual and Expected Usage Quantity is Equal or not
					if (Actual_Usage_Quanity == Expected_Usage_Quantity) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
					} else {
						double diff = Actual_Usage_Quanity - Expected_Usage_Quantity;
						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String IdealPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					;
					double Ideal_Used_Price = Double.parseDouble(IdealPrice);

					String VariancePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Variance_Price = Double.parseDouble(VariancePrice);

//								String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//								double Wastage_Price=Double.parseDouble(WastePrice);

					double Expected_Usage_Price = Ideal_Used_Price + Variance_Price;

					String Actual_UsagePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Actual_Usage_Price = Double.parseDouble(Actual_UsagePrice);

					// Check whether the Actual and Expected Usage Price is Equal or not
					if (Actual_Usage_Price == Expected_Usage_Price) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
					} else {
						double diff = Actual_Usage_Price - Expected_Usage_Price;

						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "
										+ diff);
					}

					// Get the Beginning Quantity
					String BeginningeQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Quanity = Double.parseDouble(BeginningeQty);

					// Purchase Quantity
					String PurchaseQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Quanity = Double.parseDouble(PurchaseQty);

					// Transfer In Quantity
					String TransferInQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Quanity = Double.parseDouble(TransferInQty);

					// Transfer Out Quantity
					String TransferOutQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Quanity = Double.parseDouble(TransferOutQty);

					double Expected_On_Hand_Qty = Beginning_Quanity + Purchase_Quanity - Actual_Usage_Quanity
							+ TransferIn_Quanity - TransferOut_Quanity;

					// Actual On Hand Quantity
					String OnHandQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][1]"))
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

					// Check whether the Beginning Quantity and On Hand Quantity is Equal or not
					if (The_previousday_Of_Yesterday_Onhand_Qty == Actual_OnHand_Quanity) {
						test.log(LogStatus.PASS,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Actual_OnHand_Quanity - The_previousday_Of_Yesterday_Onhand_Qty;
						test.log(LogStatus.FAIL,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					// Get the Beginning Price
					String BeginningePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Price = Double.parseDouble(BeginningePrice);

					// Purchase Quantity
					String PurchasePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Price = Double.parseDouble(PurchasePrice);

					// Transfer In Quantity
					String TransferInPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Price = Double.parseDouble(TransferInPrice);

					// Transfer Out Quantity
					String TransferOutPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Price = Double.parseDouble(TransferOutPrice);

					double Expected_On_Hand_Price = Beginning_Price + Purchase_Price - Actual_Usage_Price
							+ TransferIn_Price - TransferOut_Price;

					// Actual On Hand Price
					String OnHandPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][2]"))
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

					// Check whether the Beginning Price and On Hand Price is Equal or not
					if (The_previousday_Of_Yesterday_Onhand_Price == Actual_OnHand_Price) {
						test.log(LogStatus.PASS,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Actual_OnHand_Price - The_previousday_Of_Yesterday_Onhand_Price;
						test.log(LogStatus.FAIL,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String selTypeText = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.type.value']")).getText();
					// Check whether the selected type displays in the Type column or not
					if (selTypeText.equalsIgnoreCase("MENUITEM")) {
						test.log(LogStatus.PASS, "Type column shows selected Type");
					} else {
						test.log(LogStatus.FAIL, "Type column shows Incorrect type for selected type: " + selTypeText);
					}
				}
			}

			Thread.sleep(2000);
			test.log(LogStatus.INFO, "Central Inventory Report for Sub Recipe Selection End (Date Range)");

			wb.close();
		}
	}

	@Test(enabled = false, priority = 48)
	public void Inventory_Reports_Compare_Inventory_Menu_Item_Last_Number_Of_Days(WebDriver driver) throws Exception {
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
		Sub.selectByVisibleText("Menu Item");

		Thread.sleep(3000);
		// Click type as Time period
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Specific date");

		Thread.sleep(2000);
		// Click on the day option in the date selection tab.
		driver.findElement(By.xpath("//i[@class='fa fa-calendar']//..")).click();

		Robot rob = new Robot();

		Thread.sleep(2000);
		for (int i = 1; i < 62; i++) {
			rob.keyPress(KeyEvent.VK_LEFT);
			rob.keyRelease(KeyEvent.VK_LEFT);
		}

		Thread.sleep(2000);
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
		} catch (Exception d) {

			test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
			Thread.sleep(5000);

			List<WebElement> leftPagination = driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
			int LeftPageSize = leftPagination.size();

			// Click the pagination for Report row increase button
			driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

			for (int i = 3; i <= rows.size(); i++) {
				test.log(LogStatus.INFO,
						"Item Name is : " + driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText());

				// Actual On Hand Quantity
				String OnHandQty = driver
						.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][1]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString().substring(1).toString();
				double Actual_OnHand_Quanity = Double.parseDouble(OnHandQty);
				this.The_Last_Number_Of_Days_Previousday_Qty = Actual_OnHand_Quanity;
//											System.out.println(Yesterday_Onhand_Qty);

				// Actual On Hand Price
				String OnHandPrice = driver
						.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][2]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Actual_OnHand_Price = Double.parseDouble(OnHandPrice);
				this.The_Last_Number_Of_Days_Previousday_Price = Actual_OnHand_Price;
//											System.out.println(Yesterday_Onhand_Price);

			}

			// Select the From Date Range
//										driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
//										//Enter the required From Date Range
//										driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
//												
//										//Select the TO Date Range
//										driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
//										//Enter the  required TO Date Range
//										driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

			// Select type as ALL
			Thread.sleep(3000);
			// Select type as ALL
			Select TimePeriod1 = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
			TimePeriod1.selectByVisibleText("Last 'N' days");

			// Clear the No of days
			driver.findElement(By.xpath(excel.getData(3, 2453, 1))).clear();
			// Enter the No of days
			driver.findElement(By.xpath(excel.getData(3, 2453, 1))).sendKeys("60");

			Thread.sleep(2000);
			// Click the Run Comparison button
			driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();

			Thread.sleep(2000);
			try {
				if (driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText()
						.equalsIgnoreCase("No records found")) {
					test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
				}
			} catch (Exception g) {

				test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
				Thread.sleep(5000);

				List<WebElement> leftPagination1 = driver
						.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
				int LeftPageSize1 = leftPagination.size();

				// Click the pagination for Report row increase button
				driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

				List<WebElement> rows1 = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

				for (int i = 3; i <= rows1.size(); i++) {

					test.log(LogStatus.INFO,
							"Item Name is : " + driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText());

					String IdealQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][1]"))
							.getText();
					double Ideal_Used_Quanity = Double.parseDouble(IdealQty);

					String VarianceQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][1]"))
							.getText();
					double Variance_Quanity = Double.parseDouble(VarianceQty);

//											String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//											double Wastage_Quanity=Double.parseDouble(WasteQty);

					double Expected_Usage_Quantity = Ideal_Used_Quanity + Variance_Quanity;

					String Actual_UsageQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][1]"))
							.getText();
					double Actual_Usage_Quanity = Double.parseDouble(Actual_UsageQty);

					// Check whether the Actual and Expected Usage Quantity is Equal or not
					if (Actual_Usage_Quanity == Expected_Usage_Quantity) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
					} else {
						double diff = Actual_Usage_Quanity - Expected_Usage_Quantity;
						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String IdealPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					;
					double Ideal_Used_Price = Double.parseDouble(IdealPrice);

					String VariancePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Variance_Price = Double.parseDouble(VariancePrice);

//											String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//											double Wastage_Price=Double.parseDouble(WastePrice);

					double Expected_Usage_Price = Ideal_Used_Price + Variance_Price;

					String Actual_UsagePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Actual_Usage_Price = Double.parseDouble(Actual_UsagePrice);

					// Check whether the Actual and Expected Usage Price is Equal or not
					if (Actual_Usage_Price == Expected_Usage_Price) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
					} else {
						double diff = Actual_Usage_Price - Expected_Usage_Price;

						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "
										+ diff);
					}

					// Get the Beginning Quantity
					String BeginningeQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Quanity = Double.parseDouble(BeginningeQty);

					// Purchase Quantity
					String PurchaseQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Quanity = Double.parseDouble(PurchaseQty);

					// Transfer In Quantity
					String TransferInQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Quanity = Double.parseDouble(TransferInQty);

					// Transfer Out Quantity
					String TransferOutQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Quanity = Double.parseDouble(TransferOutQty);

					double Expected_On_Hand_Qty = Beginning_Quanity + Purchase_Quanity - Actual_Usage_Quanity
							+ TransferIn_Quanity - TransferOut_Quanity;

					// Actual On Hand Quantity
					String OnHandQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][1]"))
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

					// Check whether the Beginning Quantity and On Hand Quantity is Equal or not
					if (The_Last_Number_Of_Days_Previousday_Qty == Beginning_Quanity) {
						test.log(LogStatus.PASS,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Quanity - The_Last_Number_Of_Days_Previousday_Qty;
						test.log(LogStatus.FAIL,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					// Get the Beginning Price
					String BeginningePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Price = Double.parseDouble(BeginningePrice);

					// Purchase Quantity
					String PurchasePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Price = Double.parseDouble(PurchasePrice);

					// Transfer In Quantity
					String TransferInPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Price = Double.parseDouble(TransferInPrice);

					// Transfer Out Quantity
					String TransferOutPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Price = Double.parseDouble(TransferOutPrice);

					double Expected_On_Hand_Price = Beginning_Price + Purchase_Price - Actual_Usage_Price
							+ TransferIn_Price - TransferOut_Price;

					// Actual On Hand Price
					String OnHandPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][2]"))
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

					// Check whether the Beginning Price and On Hand Price is Equal or not
					if (The_Last_Number_Of_Days_Previousday_Price == Beginning_Price) {
						test.log(LogStatus.PASS,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Price - The_Last_Number_Of_Days_Previousday_Price;
						test.log(LogStatus.FAIL,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String selTypeText = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.type.value']")).getText();
					// Check whether the selected type displays in the Type column or not
					if (selTypeText.equalsIgnoreCase("MENUITEM")) {
						test.log(LogStatus.PASS, "Type column shows selected Type");
					} else {
						test.log(LogStatus.FAIL, "Type column shows Incorrect type for selected type: " + selTypeText);
					}
				}
			}

			Thread.sleep(2000);
			test.log(LogStatus.INFO, "Central Inventory Report for Sub Recipe Selection End (Date Range)");

			wb.close();
		}
	}

	@Test(enabled = false, priority = 48)
	public void Inventory_Reports_Compare_Inventory_Menu_Item_This_Week(WebDriver driver) throws Exception {
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
		Sub.selectByVisibleText("Menu Item");

		Thread.sleep(3000);
		// Click type as Time period
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Specific date");

//		Thread.sleep(2000);
//		// Click on the day option in the date selection tab.
//		driver.findElement(By.xpath("//i[@class='fa fa-calendar']//..")).click();

		System.out.println(java.time.LocalDate.now());

		LocalDate curr_date = java.time.LocalDate.now();

		System.out.println(curr_date);

		Date d = new Date();
		System.out.println(d.getDay());

		int Current_day = d.getDay();
		LocalDate start_day, last_day;

//		Thread.sleep(2000);
//		if (Current_day == '0') {
//			start_day = curr_date;
//			System.out.println(start_day);
//			String String_Of_Day = start_day.toString();
//			String sup = String_Of_Day.substring(8);
//			System.out.println(sup);
//			int i = Integer.parseInt(sup);
////			driver.findElement(By.xpath("//td//button[@type='button']//span[.=" + i + "]")).click();
//
//			Thread.sleep(2000);
//			//Click on the Input bax to enter date
//			driver.findElement(By.xpath("//input[@ng-model='query.specificDate']")).sendKeys(i+"-May-2022");
//			
//			
//		} else {
//			int temp = 0 + Current_day;
//			start_day = curr_date.minusDays(temp);
//			System.out.println(start_day);
//
//			String String_Of_Day = start_day.toString();
//			String sup = String_Of_Day.substring(8);
//			System.out.println(sup);
//			int i = Integer.parseInt(sup);
//			driver.findElement(By.xpath("//td//button[@type='button']//span[.=" + i + "]")).click();
//
//		}

		
		Thread.sleep(2000);
		//Click on the Input bax to enter date
		driver.findElement(By.xpath("//input[@ng-model='query.specificDate']")).sendKeys("08-May-2022");
		
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

			List<WebElement> leftPagination = driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
			int LeftPageSize = leftPagination.size();

			// Click the pagination for Report row increase button
			driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

			for (int i = 3; i <= rows.size(); i++) {
				test.log(LogStatus.INFO,
						"Item Name is : " + driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText());

				// Actual On Hand Quantity
				String OnHandQty = driver
						.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][1]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString().substring(1).toString();
				double Actual_OnHand_Quanity = Double.parseDouble(OnHandQty);
				this.This_Week_Previous_Sunday_Qty = Actual_OnHand_Quanity;
//																			System.out.println(Yesterday_Onhand_Qty);

				// Actual On Hand Price
				String OnHandPrice = driver
						.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][2]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Actual_OnHand_Price = Double.parseDouble(OnHandPrice);
				this.This_Week_Previous_Sunday_Price = Actual_OnHand_Price;
//																			System.out.println(Yesterday_Onhand_Price);

			}

			// Select the From Date Range
//																		driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
//																		//Enter the required From Date Range
//																		driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
//																				
//																		//Select the TO Date Range
//																		driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
//																		//Enter the  required TO Date Range
//																		driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

			// Select type as ALL
			Thread.sleep(3000);
			// Select type as ALL
			Select TimePeriod1 = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
			TimePeriod1.selectByVisibleText("This week");

			Thread.sleep(2000);
			// Click the Run Comparison button
			driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();

			Thread.sleep(2000);
			try {
				if (driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText()
						.equalsIgnoreCase("No records found")) {
					test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
				}
			} catch (Exception g) {

				test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
				Thread.sleep(5000);

				List<WebElement> leftPagination1 = driver
						.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
				int LeftPageSize1 = leftPagination.size();

				// Click the pagination for Report row increase button
				driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

				List<WebElement> rows1 = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

				for (int i = 3; i <= rows1.size(); i++) {

					test.log(LogStatus.INFO,
							"Item Name is : " + driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText());

					String IdealQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][1]"))
							.getText();
					double Ideal_Used_Quanity = Double.parseDouble(IdealQty);

					String VarianceQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][1]"))
							.getText();
					double Variance_Quanity = Double.parseDouble(VarianceQty);

//																			String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//																			double Wastage_Quanity=Double.parseDouble(WasteQty);

					double Expected_Usage_Quantity = Ideal_Used_Quanity + Variance_Quanity;

					String Actual_UsageQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][1]"))
							.getText();
					double Actual_Usage_Quanity = Double.parseDouble(Actual_UsageQty);

					// Check whether the Actual and Expected Usage Quantity is Equal or not
					if (Actual_Usage_Quanity == Expected_Usage_Quantity) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
					} else {
						double diff = Actual_Usage_Quanity - Expected_Usage_Quantity;
						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String IdealPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					;
					double Ideal_Used_Price = Double.parseDouble(IdealPrice);

					String VariancePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Variance_Price = Double.parseDouble(VariancePrice);

//																			String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//																			double Wastage_Price=Double.parseDouble(WastePrice);

					double Expected_Usage_Price = Ideal_Used_Price + Variance_Price;

					String Actual_UsagePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Actual_Usage_Price = Double.parseDouble(Actual_UsagePrice);

					// Check whether the Actual and Expected Usage Price is Equal or not
					if (Actual_Usage_Price == Expected_Usage_Price) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
					} else {
						double diff = Actual_Usage_Price - Expected_Usage_Price;

						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "
										+ diff);
					}

					// Get the Beginning Quantity
					String BeginningeQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Quanity = Double.parseDouble(BeginningeQty);

					// Purchase Quantity
					String PurchaseQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Quanity = Double.parseDouble(PurchaseQty);

					// Transfer In Quantity
					String TransferInQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Quanity = Double.parseDouble(TransferInQty);

					// Transfer Out Quantity
					String TransferOutQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Quanity = Double.parseDouble(TransferOutQty);

					double Expected_On_Hand_Qty = Beginning_Quanity + Purchase_Quanity - Actual_Usage_Quanity
							+ TransferIn_Quanity - TransferOut_Quanity;

					// Actual On Hand Quantity
					String OnHandQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][1]"))
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

					// Check whether the Beginning Quantity and On Hand Quantity is Equal or not
					if (This_Week_Previous_Sunday_Qty == Beginning_Quanity) {
						test.log(LogStatus.PASS,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Quanity - This_Week_Previous_Sunday_Qty;
						test.log(LogStatus.FAIL,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					// Get the Beginning Price
					String BeginningePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Price = Double.parseDouble(BeginningePrice);

					// Purchase Quantity
					String PurchasePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Price = Double.parseDouble(PurchasePrice);

					// Transfer In Quantity
					String TransferInPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Price = Double.parseDouble(TransferInPrice);

					// Transfer Out Quantity
					String TransferOutPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Price = Double.parseDouble(TransferOutPrice);

					double Expected_On_Hand_Price = Beginning_Price + Purchase_Price - Actual_Usage_Price
							+ TransferIn_Price - TransferOut_Price;

					// Actual On Hand Price
					String OnHandPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][2]"))
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

					// Check whether the Beginning Price and On Hand Price is Equal or not
					if (This_Week_Previous_Sunday_Price == Beginning_Price) {
						test.log(LogStatus.PASS,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Price - This_Week_Previous_Sunday_Price;
						test.log(LogStatus.FAIL,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String selTypeText = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.type.value']")).getText();
					// Check whether the selected type displays in the Type column or not
					if (selTypeText.equalsIgnoreCase("MENUITEM")) {
						test.log(LogStatus.PASS, "Type column shows selected Type");
					} else {
						test.log(LogStatus.FAIL, "Type column shows Incorrect type for selected type: " + selTypeText);
					}
				}
			}

			Thread.sleep(2000);
			test.log(LogStatus.INFO, "Central Inventory Report for Sub Recipe Selection End (Date Range)");

			wb.close();
		}
	}

	@Test(enabled = false, priority = 48)
	public void Inventory_Reports_Compare_Inventory_Menu_Item_Last_Week(WebDriver driver) throws Exception {
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
		Sub.selectByVisibleText("Menu Item");

		Thread.sleep(3000);
		// Click type as Time period
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Specific date");

		Thread.sleep(2000);
//		// Click on the day option in the date selection tab.
//		driver.findElement(By.xpath("//i[@class='fa fa-calendar']//..")).click();

		System.out.println(java.time.LocalDate.now());

		LocalDate curr_date = java.time.LocalDate.now();

		System.out.println(curr_date);

		Date d = new Date();
		System.out.println(d.getDay());

		int Current_day = d.getDay();
		LocalDate start_day, last_day;
//
//		Thread.sleep(2000);
//		if (Current_day == '0') {
//			start_day = curr_date;
//			System.out.println(start_day);
//			String String_Of_Day = start_day.toString();
//			String sup = String_Of_Day.substring(8);
//			System.out.println(sup);
//			int i = curr_date.minusDays(sup);
////			int i = Integer.parseInt(sup) - 7;
//			
//			
//			
//
//		} else {
//			int temp = 0 + Current_day;
//			start_day = curr_date.minusDays(temp);
//			System.out.println(start_day);
//
//			String String_Of_Day = start_day.toString();
//			String sup = String_Of_Day.substring(8);
//			System.out.println(sup);
//			int i = Integer.parseInt(sup) - 7;
////			driver.findElement(By.xpath("//td//button[@type='button']//span[.=" + i + "]")).click();

			Thread.sleep(2000);
			//Click on the Input bax to enter date
			driver.findElement(By.xpath("//input[@ng-model='query.specificDate']")).sendKeys("01-May-2022");
			
			
//		}

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

			List<WebElement> leftPagination = driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
			int LeftPageSize = leftPagination.size();

			// Click the pagination for Report row increase button
			driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

			for (int i = 3; i <= rows.size(); i++) {
				test.log(LogStatus.INFO,
						"Item Name is : " + driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText());

				// Actual On Hand Quantity
				String OnHandQty = driver
						.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][1]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString().substring(1).toString();
				double Actual_OnHand_Quanity = Double.parseDouble(OnHandQty);
				this.Last_Week_Previous_Sunday_Qty = Actual_OnHand_Quanity;
//																				System.out.println(Yesterday_Onhand_Qty);

				// Actual On Hand Price
				String OnHandPrice = driver
						.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][2]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Actual_OnHand_Price = Double.parseDouble(OnHandPrice);
				this.Last_Week_Previous_Sunday_Price = Actual_OnHand_Price;
//																				System.out.println(Yesterday_Onhand_Price);

			}

			// Select the From Date Range
//																			driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
//																			//Enter the required From Date Range
//																			driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
//																					
//																			//Select the TO Date Range
//																			driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
//																			//Enter the  required TO Date Range
//																			driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

			// Select type as ALL
			Thread.sleep(3000);
			// Select type as ALL
			Select TimePeriod1 = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
			TimePeriod1.selectByVisibleText("Last week");

			Thread.sleep(2000);
			// Click the Run Comparison button
			driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();

			Thread.sleep(2000);
			try {
				if (driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText()
						.equalsIgnoreCase("No records found")) {
					test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
				}
			} catch (Exception g) {

				test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
				Thread.sleep(5000);

				List<WebElement> leftPagination1 = driver
						.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
				int LeftPageSize1 = leftPagination.size();

				// Click the pagination for Report row increase button
				driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

				List<WebElement> rows1 = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

				for (int i = 3; i <= rows1.size(); i++) {

					test.log(LogStatus.INFO,
							"Item Name is : " + driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText());

					String IdealQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][1]"))
							.getText();
					double Ideal_Used_Quanity = Double.parseDouble(IdealQty);

					String VarianceQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][1]"))
							.getText();
					double Variance_Quanity = Double.parseDouble(VarianceQty);

//																				String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//																				double Wastage_Quanity=Double.parseDouble(WasteQty);

					double Expected_Usage_Quantity = Ideal_Used_Quanity + Variance_Quanity;

					String Actual_UsageQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][1]"))
							.getText();
					double Actual_Usage_Quanity = Double.parseDouble(Actual_UsageQty);

					// Check whether the Actual and Expected Usage Quantity is Equal or not
					if (Actual_Usage_Quanity == Expected_Usage_Quantity) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
					} else {
						double diff = Actual_Usage_Quanity - Expected_Usage_Quantity;
						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String IdealPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					;
					double Ideal_Used_Price = Double.parseDouble(IdealPrice);

					String VariancePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Variance_Price = Double.parseDouble(VariancePrice);

//																				String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//																				double Wastage_Price=Double.parseDouble(WastePrice);

					double Expected_Usage_Price = Ideal_Used_Price + Variance_Price;

					String Actual_UsagePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Actual_Usage_Price = Double.parseDouble(Actual_UsagePrice);

					// Check whether the Actual and Expected Usage Price is Equal or not
					if (Actual_Usage_Price == Expected_Usage_Price) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
					} else {
						double diff = Actual_Usage_Price - Expected_Usage_Price;

						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "
										+ diff);
					}

					// Get the Beginning Quantity
					String BeginningeQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Quanity = Double.parseDouble(BeginningeQty);

					// Purchase Quantity
					String PurchaseQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Quanity = Double.parseDouble(PurchaseQty);

					// Transfer In Quantity
					String TransferInQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Quanity = Double.parseDouble(TransferInQty);

					// Transfer Out Quantity
					String TransferOutQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Quanity = Double.parseDouble(TransferOutQty);

					double Expected_On_Hand_Qty = Beginning_Quanity + Purchase_Quanity - Actual_Usage_Quanity
							+ TransferIn_Quanity - TransferOut_Quanity;

					// Actual On Hand Quantity
					String OnHandQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][1]"))
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

					// Check whether the Beginning Quantity and On Hand Quantity is Equal or not
					if (Last_Week_Previous_Sunday_Qty == Beginning_Quanity) {
						test.log(LogStatus.PASS,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Quanity - Last_Week_Previous_Sunday_Qty;
						test.log(LogStatus.FAIL,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					// Get the Beginning Price
					String BeginningePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Price = Double.parseDouble(BeginningePrice);

					// Purchase Quantity
					String PurchasePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Price = Double.parseDouble(PurchasePrice);

					// Transfer In Quantity
					String TransferInPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Price = Double.parseDouble(TransferInPrice);

					// Transfer Out Quantity
					String TransferOutPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Price = Double.parseDouble(TransferOutPrice);

					double Expected_On_Hand_Price = Beginning_Price + Purchase_Price - Actual_Usage_Price
							+ TransferIn_Price - TransferOut_Price;

					// Actual On Hand Price
					String OnHandPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][2]"))
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

					// Check whether the Beginning Price and On Hand Price is Equal or not
					if (Last_Week_Previous_Sunday_Price == Beginning_Price) {
						test.log(LogStatus.PASS,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Price - Last_Week_Previous_Sunday_Price;
						test.log(LogStatus.FAIL,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String selTypeText = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.type.value']")).getText();
					// Check whether the selected type displays in the Type column or not
					if (selTypeText.equalsIgnoreCase("MENUITEM")) {
						test.log(LogStatus.PASS, "Type column shows selected Type");
					} else {
						test.log(LogStatus.FAIL, "Type column shows Incorrect type for selected type: " + selTypeText);
					}
				}
			}

			Thread.sleep(2000);
			test.log(LogStatus.INFO, "Central Inventory Report for Sub Recipe Selection End (Date Range)");

			wb.close();
		}
	}

	@Test(enabled = false, priority = 48)
	public void Inventory_Reports_Compare_Inventory_Menu_Item_Last_7_Days(WebDriver driver) throws Exception {
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
		Sub.selectByVisibleText("Menu Item");

		Thread.sleep(3000);
		// Click type as Time period
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Specific date");

		Thread.sleep(2000);
		// Click on the day option in the date selection tab.
//		driver.findElement(By.xpath("//i[@class='fa fa-calendar']//..")).click();

		System.out.println(java.time.LocalDate.now());

		LocalDate curr_date = java.time.LocalDate.now();

		System.out.println(curr_date);

		Date d = new Date();
		System.out.println(d.getDay());

//		int Current_day = d.getDay();
//		LocalDate start_day, last_day;
//
//		start_day = curr_date;
//		System.out.println(start_day);
//		String String_Of_Day = start_day.toString();
//		String sup = String_Of_Day.substring(8);
//		System.out.println(sup);
//		
//		int o = Integer.parseInt(sup) - 7;
////		driver.findElement(By.xpath("//td//button[@type='button']//span[.=" + o + "]")).click();
		
		Thread.sleep(2000);
		//Click on the Input bax to enter date
		driver.findElement(By.xpath("//input[@ng-model='query.specificDate']")).sendKeys("03-May-2022");
		

//		    	Thread.sleep(2000);
//		    	if (Current_day == '0') {
//		    		start_day = curr_date;
//		    		System.out.println(start_day);
//		    		String String_Of_Day = start_day.toString();
//		    		String sup = String_Of_Day.substring(8);
//		    		System.out.println(sup);
//		    		int i=Integer.parseInt(sup)-7;
//		    		driver.findElement(By.xpath("//td//button[@type='button']//span[.="+i+"]")).click();
//		    		
//		    	} else {
//		    		int temp = 0 + Current_day;
//		    		start_day = curr_date.minusDays(temp);
//		    		System.out.println(start_day);
//		    	
//		    		String String_Of_Day = start_day.toString();
//		    		String sup = String_Of_Day.substring(8);
//		    		System.out.println(sup);
//		    		int i=Integer.parseInt(sup)-7; 
//		    		driver.findElement(By.xpath("//td//button[@type='button']//span[.="+i+"]")).click();
//		    		
//		    		
//		    	}

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

			List<WebElement> leftPagination = driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
			int LeftPageSize = leftPagination.size();

			// Click the pagination for Report row increase button
			driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

			for (int j = 3; j <= rows.size(); j++) {
				test.log(LogStatus.INFO,
						"Item Name is : " + driver.findElement(By.xpath("//tr[" + j + "]/td[1]")).getText());

				// Actual On Hand Quantity
				String OnHandQty = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.onHand.value'][1]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString().substring(1).toString();
				double Actual_OnHand_Quanity = Double.parseDouble(OnHandQty);
				this.Last_7_Days_previousday_Qty = Actual_OnHand_Quanity;
//																					System.out.println(Yesterday_Onhand_Qty);

				// Actual On Hand Price
				String OnHandPrice = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.onHand.value'][2]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Actual_OnHand_Price = Double.parseDouble(OnHandPrice);
				this.Last_7_Days_previousday_Price = Actual_OnHand_Price;
//																					System.out.println(Yesterday_Onhand_Price);

			}

			// Select the From Date Range
//																				driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
//																				//Enter the required From Date Range
//																				driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
//																						
//																				//Select the TO Date Range
//																				driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
//																				//Enter the  required TO Date Range
//																				driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

			// Select type as ALL
			Thread.sleep(3000);
			// Select type as ALL
			Select TimePeriod1 = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
			TimePeriod1.selectByVisibleText("Last 7 days");

			Thread.sleep(2000);
			// Click the Run Comparison button
			driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();

			Thread.sleep(2000);
			try {
				if (driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText()
						.equalsIgnoreCase("No records found")) {
					test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
				}
			} catch (Exception g) {

				test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
				Thread.sleep(5000);

				List<WebElement> leftPagination1 = driver
						.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
				int LeftPageSize1 = leftPagination.size();

				// Click the pagination for Report row increase button
				driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

				List<WebElement> rows1 = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

				for (int i = 3; i <= rows1.size(); i++) {

					test.log(LogStatus.INFO,
							"Item Name is : " + driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText());

					String IdealQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][1]"))
							.getText();
					double Ideal_Used_Quanity = Double.parseDouble(IdealQty);

					String VarianceQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][1]"))
							.getText();
					double Variance_Quanity = Double.parseDouble(VarianceQty);

//																					String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//																					double Wastage_Quanity=Double.parseDouble(WasteQty);

					double Expected_Usage_Quantity = Ideal_Used_Quanity + Variance_Quanity;

					String Actual_UsageQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][1]"))
							.getText();
					double Actual_Usage_Quanity = Double.parseDouble(Actual_UsageQty);

					// Check whether the Actual and Expected Usage Quantity is Equal or not
					if (Actual_Usage_Quanity == Expected_Usage_Quantity) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
					} else {
						double diff = Actual_Usage_Quanity - Expected_Usage_Quantity;
						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String IdealPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					;
					double Ideal_Used_Price = Double.parseDouble(IdealPrice);

					String VariancePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Variance_Price = Double.parseDouble(VariancePrice);

//																					String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//																					double Wastage_Price=Double.parseDouble(WastePrice);

					double Expected_Usage_Price = Ideal_Used_Price + Variance_Price;

					String Actual_UsagePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Actual_Usage_Price = Double.parseDouble(Actual_UsagePrice);

					// Check whether the Actual and Expected Usage Price is Equal or not
					if (Actual_Usage_Price == Expected_Usage_Price) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
					} else {
						double diff = Actual_Usage_Price - Expected_Usage_Price;

						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "
										+ diff);
					}

					// Get the Beginning Quantity
					String BeginningeQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Quanity = Double.parseDouble(BeginningeQty);

					// Purchase Quantity
					String PurchaseQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Quanity = Double.parseDouble(PurchaseQty);

					// Transfer In Quantity
					String TransferInQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Quanity = Double.parseDouble(TransferInQty);

					// Transfer Out Quantity
					String TransferOutQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Quanity = Double.parseDouble(TransferOutQty);

					double Expected_On_Hand_Qty = Beginning_Quanity + Purchase_Quanity - Actual_Usage_Quanity
							+ TransferIn_Quanity - TransferOut_Quanity;

					// Actual On Hand Quantity
					String OnHandQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][1]"))
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

					// Check whether the Beginning Quantity and On Hand Quantity is Equal or not
					if (Last_7_Days_previousday_Qty == Beginning_Quanity) {
						test.log(LogStatus.PASS,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Quanity - Last_7_Days_previousday_Qty;
						test.log(LogStatus.FAIL,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					// Get the Beginning Price
					String BeginningePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Price = Double.parseDouble(BeginningePrice);

					// Purchase Quantity
					String PurchasePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Price = Double.parseDouble(PurchasePrice);

					// Transfer In Quantity
					String TransferInPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Price = Double.parseDouble(TransferInPrice);

					// Transfer Out Quantity
					String TransferOutPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Price = Double.parseDouble(TransferOutPrice);

					double Expected_On_Hand_Price = Beginning_Price + Purchase_Price - Actual_Usage_Price
							+ TransferIn_Price - TransferOut_Price;

					// Actual On Hand Price
					String OnHandPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][2]"))
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

					// Check whether the Beginning Price and On Hand Price is Equal or not
					if (Last_7_Days_previousday_Price == Beginning_Price) {
						test.log(LogStatus.PASS,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Price - Last_7_Days_previousday_Price;
						test.log(LogStatus.FAIL,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String selTypeText = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.type.value']")).getText();
					// Check whether the selected type displays in the Type column or not
					if (selTypeText.equalsIgnoreCase("MENUITEM")) {
						test.log(LogStatus.PASS, "Type column shows selected Type");
					} else {
						test.log(LogStatus.FAIL, "Type column shows Incorrect type for selected type: " + selTypeText);
					}
				}
			}

			Thread.sleep(2000);
			test.log(LogStatus.INFO, "Central Inventory Report for Sub Recipe Selection End (Date Range)");

			wb.close();
		}
	}

	@Test(enabled = false, priority = 48)
	public void Inventory_Reports_Compare_Inventory_Menu_Item_Last_30_Days(WebDriver driver) throws Exception {
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
		Sub.selectByVisibleText("Menu Item");

		Thread.sleep(3000);
		// Click type as Time period
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Specific date");

		Thread.sleep(2000);
		// Click on the day option in the date selection tab.
//		driver.findElement(By.xpath("//i[@class='fa fa-calendar']//..")).click();

		System.out.println(java.time.LocalDate.now());

		LocalDate curr_date = java.time.LocalDate.now();

		System.out.println(curr_date);

		Date d = new Date();
//		System.out.println(d.getDay());
//
//		int Current_day = d.getDay();
//		LocalDate start_day, last_day;
//
//		start_day = curr_date;
//		System.out.println(start_day);
//		start_day = curr_date.minusDays(30);
//		String String_Of_Day = start_day.toString();
//		String sup = String_Of_Day.substring(8);
//		System.out.println(sup);
//		int o = Integer.parseInt(sup);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@ng-model='query.specificDate']")).sendKeys("09-Apr-2022");
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//tr//th[1]//button//span[@class='sr-only']")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//td//button[@type='button']//span[.=" + o + "]")).click();

//			    	Thread.sleep(2000);
//			    	if (Current_day == '0') {
//			    		start_day = curr_date;
//			    		System.out.println(start_day);
//			    		String String_Of_Day = start_day.toString();
//			    		String sup = String_Of_Day.substring(8);
//			    		System.out.println(sup);
//			    		int i=Integer.parseInt(sup)-7;
//			    		driver.findElement(By.xpath("//td//button[@type='button']//span[.="+i+"]")).click();
//			    		
//			    	} else {
//			    		int temp = 0 + Current_day;
//			    		start_day = curr_date.minusDays(temp);
//			    		System.out.println(start_day);
//			    	
//			    		String String_Of_Day = start_day.toString();
//			    		String sup = String_Of_Day.substring(8);
//			    		System.out.println(sup);
//			    		int i=Integer.parseInt(sup)-7; 
//			    		driver.findElement(By.xpath("//td//button[@type='button']//span[.="+i+"]")).click();
//			    		
//			    		
//			    	}

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

			List<WebElement> leftPagination = driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
			int LeftPageSize = leftPagination.size();

			// Click the pagination for Report row increase button
			driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

			for (int j = 3; j <= rows.size(); j++) {
				test.log(LogStatus.INFO,
						"Item Name is : " + driver.findElement(By.xpath("//tr[" + j + "]/td[1]")).getText());

				// Actual On Hand Quantity
				String OnHandQty = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.onHand.value'][1]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString().substring(1).toString();
				double Actual_OnHand_Quanity = Double.parseDouble(OnHandQty);
				this.Last_30_Days_previousday_Qty = Actual_OnHand_Quanity;
//																						System.out.println(Yesterday_Onhand_Qty);

				// Actual On Hand Price
				String OnHandPrice = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.onHand.value'][2]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Actual_OnHand_Price = Double.parseDouble(OnHandPrice);
				this.Last_30_Days_previousday_price = Actual_OnHand_Price;
//																						System.out.println(Yesterday_Onhand_Price);

			}

			// Select the From Date Range
//																					driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
//																					//Enter the required From Date Range
//																					driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
//																							
//																					//Select the TO Date Range
//																					driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
//																					//Enter the  required TO Date Range
//																					driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

			// Select type as ALL
			Thread.sleep(3000);
			// Select type as ALL
			Select TimePeriod1 = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
			TimePeriod1.selectByVisibleText("Last 30 days");

			Thread.sleep(2000);
			// Click the Run Comparison button
			driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();

			Thread.sleep(2000);
			try {
				if (driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText()
						.equalsIgnoreCase("No records found")) {
					test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
				}
			} catch (Exception g) {

				test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
				Thread.sleep(5000);

				List<WebElement> leftPagination1 = driver
						.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
				int LeftPageSize1 = leftPagination.size();

				// Click the pagination for Report row increase button
				driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

				List<WebElement> rows1 = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

				for (int i = 3; i <= rows1.size(); i++) {

					test.log(LogStatus.INFO,
							"Item Name is : " + driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText());

					String IdealQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][1]"))
							.getText();
					double Ideal_Used_Quanity = Double.parseDouble(IdealQty);

					String VarianceQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][1]"))
							.getText();
					double Variance_Quanity = Double.parseDouble(VarianceQty);

//																						String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//																						double Wastage_Quanity=Double.parseDouble(WasteQty);

					double Expected_Usage_Quantity = Ideal_Used_Quanity + Variance_Quanity;

					String Actual_UsageQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][1]"))
							.getText();
					double Actual_Usage_Quanity = Double.parseDouble(Actual_UsageQty);

					// Check whether the Actual and Expected Usage Quantity is Equal or not
					if (Actual_Usage_Quanity == Expected_Usage_Quantity) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
					} else {
						double diff = Actual_Usage_Quanity - Expected_Usage_Quantity;
						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String IdealPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					;
					double Ideal_Used_Price = Double.parseDouble(IdealPrice);

					String VariancePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Variance_Price = Double.parseDouble(VariancePrice);

//																						String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//																						double Wastage_Price=Double.parseDouble(WastePrice);

					double Expected_Usage_Price = Ideal_Used_Price + Variance_Price;

					String Actual_UsagePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Actual_Usage_Price = Double.parseDouble(Actual_UsagePrice);

					// Check whether the Actual and Expected Usage Price is Equal or not
					if (Actual_Usage_Price == Expected_Usage_Price) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
					} else {
						double diff = Actual_Usage_Price - Expected_Usage_Price;

						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "
										+ diff);
					}

					// Get the Beginning Quantity
					String BeginningeQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Quanity = Double.parseDouble(BeginningeQty);

					// Purchase Quantity
					String PurchaseQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Quanity = Double.parseDouble(PurchaseQty);

					// Transfer In Quantity
					String TransferInQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Quanity = Double.parseDouble(TransferInQty);

					// Transfer Out Quantity
					String TransferOutQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Quanity = Double.parseDouble(TransferOutQty);

					double Expected_On_Hand_Qty = Beginning_Quanity + Purchase_Quanity - Actual_Usage_Quanity
							+ TransferIn_Quanity - TransferOut_Quanity;

					// Actual On Hand Quantity
					String OnHandQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][1]"))
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

					// Check whether the Beginning Quantity and On Hand Quantity is Equal or not
					if (Last_30_Days_previousday_Qty == Beginning_Quanity) {
						test.log(LogStatus.PASS,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Quanity - Last_30_Days_previousday_Qty;
						test.log(LogStatus.FAIL,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					// Get the Beginning Price
					String BeginningePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Price = Double.parseDouble(BeginningePrice);

					// Purchase Quantity
					String PurchasePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Price = Double.parseDouble(PurchasePrice);

					// Transfer In Quantity
					String TransferInPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Price = Double.parseDouble(TransferInPrice);

					// Transfer Out Quantity
					String TransferOutPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Price = Double.parseDouble(TransferOutPrice);

					double Expected_On_Hand_Price = Beginning_Price + Purchase_Price - Actual_Usage_Price
							+ TransferIn_Price - TransferOut_Price;

					// Actual On Hand Price
					String OnHandPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][2]"))
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

					// Check whether the Beginning Price and On Hand Price is Equal or not
					if (Last_30_Days_previousday_price == Beginning_Price) {
						test.log(LogStatus.PASS,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Price - Last_30_Days_previousday_price;
						test.log(LogStatus.FAIL,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String selTypeText = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.type.value']")).getText();
					// Check whether the selected type displays in the Type column or not
					if (selTypeText.equalsIgnoreCase("MENUITEM")) {
						test.log(LogStatus.PASS, "Type column shows selected Type");
					} else {
						test.log(LogStatus.FAIL, "Type column shows Incorrect type for selected type: " + selTypeText);
					}
				}
			}

			Thread.sleep(2000);
			test.log(LogStatus.INFO, "Central Inventory Report for Sub Recipe Selection End (Date Range)");

			wb.close();
		}
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
		Sub.selectByVisibleText("Menu Item");

		Thread.sleep(3000);
		// Click type as Time period
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Specific date");

//		Thread.sleep(2000);
//		// Click on the day option in the date selection tab.
//		driver.findElement(By.xpath("//i[@class='fa fa-calendar']//..")).click();

		Thread.sleep(2000);
		WebElement sp = driver.findElement(By.xpath("//input[@ng-model='query.specificDate']"));
		sp.clear();
		sp.sendKeys("01-Aug-2022");
		
		for (int o = 1; o <= 31; o++) {
			
		
		
		
		driver.findElement(By.xpath("//div//a[@class = 'btn btn-success input-group-addon']")).click();
		Thread.sleep(1000);
//		driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm pull-left uib-left']")).click();
		
		Robot rob = new Robot();
		
		rob.keyPress(KeyEvent.VK_LEFT);
		rob.keyRelease(KeyEvent.VK_LEFT);
		
		rob.keyPress(KeyEvent.VK_ENTER);
		rob.keyRelease(KeyEvent.VK_ENTER);
		
//		Thread.sleep(4000);
//		WebElement sp = driver.findElement(By.xpath("//input[@ng-model='query.specificDate']"));
//		sp.clear();
//		sp.sendKeys("31-Jul-2022");
		
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
			

			List<WebElement> leftPagination = driver.findElements(By.xpath("//ul[@class='pagination pull-right ng-scope']/li"));
			int LeftPageSize = leftPagination.size();

			// Click the pagination for Report row increase button
			String sizelimit = driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + (LeftPageSize-1) + "]/a")).getText();
			
			int pagenavi = Integer.parseInt(sizelimit);
			
			for (int i = 1; i <= pagenavi; i++) {
				
			

			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

			for (int j = 3; j <= rows.size(); j++) {
				test.log(LogStatus.INFO,
						"Item Name is : " + driver.findElement(By.xpath("//tr[" + j + "]/td[1]")).getText());

				// Actual On Hand Quantity
				String OnHandQty = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.onHand.value'][1]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString().substring(1).toString();
				double Actual_OnHand_Quanity = Double.parseDouble(OnHandQty);
				Actual_OnHand_Quanity += Actual_OnHand_Quanity;
//				this.Specific_day_previousday_Qty = Actual_OnHand_Quanity;
//																							System.out.println(Yesterday_Onhand_Qty);

				// Actual On Hand Price
				String OnHandPrice = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.onHand.value'][2]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Actual_OnHand_Price = Double.parseDouble(OnHandPrice);
				Actual_OnHand_Price += Actual_OnHand_Price;
//				this.Specific_day_previousday_Price = Actual_OnHand_Price;
//																							System.out.println(Yesterday_Onhand_Price);

			}
			
//			this.Specific_day_previousday_Qty = Actual_OnHand_Quanity;
//			
//			this.Specific_day_previousday_Price = Actual_OnHand_Price;
			
			
			}
			
		}
			// Select the From Date Range
//																						driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
//																						//Enter the required From Date Range
//																						driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
//																								
//																						//Select the TO Date Range
//																						driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
//																						//Enter the  required TO Date Range
//																						driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

			// Select type as ALL
			Thread.sleep(10000);
			// Select type as ALL
			Select TimePeriod1 = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
			TimePeriod1.selectByVisibleText("Specific date");
//
//			Thread.sleep(2000);
//			// Click on the day option in the date selection tab.
//			driver.findElement(By.xpath("//i[@class='fa fa-calendar']//..")).click();
//
			
			Thread.sleep(3000);
			//Click on the Input bax to enter date
			WebElement sp1 = driver.findElement(By.xpath("//input[@ng-model='query.specificDate']"));
			sp1.clear();
			sp1.sendKeys("01-Aug-2022");
//			Thread.sleep(3000);
//			driver.findElement(By.xpath("//td//button[@type='button']//span[.=" + 3 + "]")).click();
			
//			Robot rob = new Robot();
//
//			Thread.sleep(2000);
//
//			rob.keyPress(KeyEvent.VK_RIGHT);
//			rob.keyRelease(KeyEvent.VK_RIGHT);
//
//			Thread.sleep(2000);
//			rob.keyPress(KeyEvent.VK_ENTER);
//			rob.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(2000);
			// Click the Run Comparison button
			driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();

			Thread.sleep(2000);
			try {
				if (driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText()
						.equalsIgnoreCase("No records found")) {
					test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
				}
			} catch (Exception g) {

				test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
				Thread.sleep(5000);

				List<WebElement> leftPagination1 = driver
						.findElements(By.xpath("//ul[@class='pagination pull-right ng-scope']/li"));
				int LeftPageSize1 = leftPagination1.size();

				// Click the pagination for Report row increase button
				driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + (LeftPageSize1-1) + "]/a")).click();

				List<WebElement> rows1 = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

				for (int k = 3; k <= rows1.size(); k++) {

					test.log(LogStatus.INFO,
							"Item Name is : " + driver.findElement(By.xpath("//tr[" + k + "]/td[1]")).getText());

					String IdealQty = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.idealUsed.value'][1]"))
							.getText();
					double Ideal_Used_Quanity = Double.parseDouble(IdealQty);

					String VarianceQty = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.variance.value'][1]"))
							.getText();
					double Variance_Quanity = Double.parseDouble(VarianceQty);

//																							String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//																							double Wastage_Quanity=Double.parseDouble(WasteQty);

					double Expected_Usage_Quantity = Ideal_Used_Quanity + Variance_Quanity;

					String Actual_UsageQty = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.usage.value'][1]"))
							.getText();
					double Actual_Usage_Quanity = Double.parseDouble(Actual_UsageQty);

					// Check whether the Actual and Expected Usage Quantity is Equal or not
					if (Actual_Usage_Quanity == Expected_Usage_Quantity) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
					} else {
						double diff = Actual_Usage_Quanity - Expected_Usage_Quantity;
						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String IdealPrice = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.idealUsed.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					;
					double Ideal_Used_Price = Double.parseDouble(IdealPrice);

					String VariancePrice = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.variance.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Variance_Price = Double.parseDouble(VariancePrice);

//																							String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//																							double Wastage_Price=Double.parseDouble(WastePrice);

					double Expected_Usage_Price = Ideal_Used_Price + Variance_Price;

					String Actual_UsagePrice = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.usage.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Actual_Usage_Price = Double.parseDouble(Actual_UsagePrice);

					// Check whether the Actual and Expected Usage Price is Equal or not
					if (Actual_Usage_Price == Expected_Usage_Price) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
					} else {
						double diff = Actual_Usage_Price - Expected_Usage_Price;

						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "
										+ diff);
					}

					// Get the Beginning Quantity
					String BeginningeQty = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.beginning.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Quanity = Double.parseDouble(BeginningeQty);

					// Purchase Quantity
					String PurchaseQty = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.purchase.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Quanity = Double.parseDouble(PurchaseQty);

					// Transfer In Quantity
					String TransferInQty = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.transferIn.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Quanity = Double.parseDouble(TransferInQty);

					// Transfer Out Quantity
					String TransferOutQty = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.transferOut.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Quanity = Double.parseDouble(TransferOutQty);

					double Expected_On_Hand_Qty = Beginning_Quanity + Purchase_Quanity - Actual_Usage_Quanity
							+ TransferIn_Quanity - TransferOut_Quanity;

					// Actual On Hand Quantity
					String OnHandQty = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.onHand.value'][1]"))
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

					// Check whether the Beginning Quantity and On Hand Quantity is Equal or not
					if (Specific_day_previousday_Qty == Beginning_Quanity) {
						test.log(LogStatus.PASS,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Quanity - Specific_day_previousday_Qty;
						test.log(LogStatus.FAIL,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					// Get the Beginning Price
					String BeginningePrice = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.beginning.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Price = Double.parseDouble(BeginningePrice);

					// Purchase Quantity
					String PurchasePrice = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.purchase.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Price = Double.parseDouble(PurchasePrice);

					// Transfer In Quantity
					String TransferInPrice = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.transferIn.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Price = Double.parseDouble(TransferInPrice);

					// Transfer Out Quantity
					String TransferOutPrice = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.transferOut.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Price = Double.parseDouble(TransferOutPrice);

					double Expected_On_Hand_Price = Beginning_Price + Purchase_Price - Actual_Usage_Price
							+ TransferIn_Price - TransferOut_Price;

					// Actual On Hand Price
					String OnHandPrice = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.onHand.value'][2]"))
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

					// Check whether the Beginning Price and On Hand Price is Equal or not
					if (Specific_day_previousday_Price == Beginning_Price) {
						test.log(LogStatus.PASS,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Price - Specific_day_previousday_Price;
						test.log(LogStatus.FAIL,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String selTypeText = driver
							.findElement(By.xpath("//tr[" + k + "]/td[@ng-if='reportColumns.type.value']")).getText();
					// Check whether the selected type displays in the Type column or not
					if (selTypeText.equalsIgnoreCase("MENUITEM")) {
						test.log(LogStatus.PASS, "Type column shows selected Type");
					} else {
						test.log(LogStatus.FAIL, "Type column shows Incorrect type for selected type: " + selTypeText);
					}
				}
			}

			Thread.sleep(2000);
			test.log(LogStatus.INFO, "Central Inventory Report for Sub Recipe Selection End (Date Range)");

			wb.close();
		}
	}

	@Test(enabled = false, priority = 48)
	public void Inventory_Reports_Compare_Inventory_Menu_Item_Date_Range(WebDriver driver) throws Exception {
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
		Sub.selectByVisibleText("Menu Item");

		Thread.sleep(3000);
		// Click type as Time period
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Today");

		Thread.sleep(3000);
		TimePeriod.selectByVisibleText("Specific date");
		
		Thread.sleep(2000);
		// Click on the day option in the date selection tab.
//		driver.findElement(By.xpath("//i[@class='fa fa-calendar']//..")).click();
//
//		System.out.println(java.time.LocalDate.now());
//
//		LocalDate curr_date = java.time.LocalDate.now();
//
//		System.out.println(curr_date);
//
//		Date d = new Date();
//		System.out.println(d.getDay());
//
//		int Current_day = d.getDay();
//		LocalDate start_day, last_day;
//
//		start_day = curr_date;
//		System.out.println(start_day);
//		start_day = curr_date.minusDays(60);
//		String String_Of_Day = start_day.toString();
//		String sup = String_Of_Day.substring(8);
//		System.out.println(sup);
//		int o = Integer.parseInt(sup);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@ng-model='query.specificDate']")).sendKeys("10-Mar-2022");
		
//		driver.findElement(By.xpath("//tr//th[1]//button//span[@class='sr-only']")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//tr//th[1]//button//span[@class='sr-only']")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//td//button[@type='button']//span[.=" + o + "]")).click();

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

			List<WebElement> leftPagination = driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
			int LeftPageSize = leftPagination.size();

			// Click the pagination for Report row increase button
			driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

			for (int j = 3; j <= rows.size(); j++) {
				test.log(LogStatus.INFO,
						"Item Name is : " + driver.findElement(By.xpath("//tr[" + j + "]/td[1]")).getText());

				// Actual On Hand Quantity
				String OnHandQty = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.onHand.value'][1]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString().substring(1).toString();
				double Actual_OnHand_Quanity = Double.parseDouble(OnHandQty);
				this.DateRange_previousday_Qty = Actual_OnHand_Quanity;
//																								System.out.println(Yesterday_Onhand_Qty);

				// Actual On Hand Price
				String OnHandPrice = driver
						.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.onHand.value'][2]")).getText()
						.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
				double Actual_OnHand_Price = Double.parseDouble(OnHandPrice);
				this.DateRange_previousday_Price = Actual_OnHand_Price;
//																								System.out.println(Yesterday_Onhand_Price);

			}

			// Select the From Date Range
//																							driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
//																							//Enter the required From Date Range
//																							driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
//																									
//																							//Select the TO Date Range
//																							driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
//																							//Enter the  required TO Date Range
//																							driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

			// Select type as ALL
			Thread.sleep(3000);
			// Select type as ALL
			Select TimePeriod1 = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
			TimePeriod1.selectByVisibleText("Date Range");

//			int op = o + 1;

			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys("06-Mar-2022");

			Thread.sleep(2000);
			// Click on the day option in the date selection tab.
			driver.findElement(By.xpath("//input[@ng-model='query.fromDate']")).sendKeys("10-May-2022");

			Thread.sleep(2000);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ENTER);

//			driver.findElement(By.tagName("html")).sendKeys(keys.);
			
			Thread.sleep(4000);
			// Click the Run Comparison button
			driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();

			Thread.sleep(2000);
			try {
				if (driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText()
						.equalsIgnoreCase("No records found")) {
					test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
				}
			} catch (Exception g) {

				test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
				Thread.sleep(5000);

				List<WebElement> leftPagination1 = driver
						.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
				int LeftPageSize1 = leftPagination.size();

				// Click the pagination for Report row increase button
				driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

				List<WebElement> rows1 = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

				for (int i = 3; i <= rows1.size(); i++) {

					test.log(LogStatus.INFO,
							"Item Name is : " + driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText());

					String IdealQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][1]"))
							.getText();
					double Ideal_Used_Quanity = Double.parseDouble(IdealQty);

					String VarianceQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][1]"))
							.getText();
					double Variance_Quanity = Double.parseDouble(VarianceQty);

//																								String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//																								double Wastage_Quanity=Double.parseDouble(WasteQty);

					double Expected_Usage_Quantity = Ideal_Used_Quanity + Variance_Quanity;

					String Actual_UsageQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][1]"))
							.getText();
					double Actual_Usage_Quanity = Double.parseDouble(Actual_UsageQty);

					// Check whether the Actual and Expected Usage Quantity is Equal or not
					if (Actual_Usage_Quanity == Expected_Usage_Quantity) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
					} else {
						double diff = Actual_Usage_Quanity - Expected_Usage_Quantity;
						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String IdealPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					;
					double Ideal_Used_Price = Double.parseDouble(IdealPrice);

					String VariancePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Variance_Price = Double.parseDouble(VariancePrice);

//																								String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//																								double Wastage_Price=Double.parseDouble(WastePrice);

					double Expected_Usage_Price = Ideal_Used_Price + Variance_Price;

					String Actual_UsagePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Actual_Usage_Price = Double.parseDouble(Actual_UsagePrice);

					// Check whether the Actual and Expected Usage Price is Equal or not
					if (Actual_Usage_Price == Expected_Usage_Price) {
						test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
					} else {
						double diff = Actual_Usage_Price - Expected_Usage_Price;

						test.log(LogStatus.FAIL,
								"Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "
										+ diff);
					}

					// Get the Beginning Quantity
					String BeginningeQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Quanity = Double.parseDouble(BeginningeQty);

					// Purchase Quantity
					String PurchaseQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Quanity = Double.parseDouble(PurchaseQty);

					// Transfer In Quantity
					String TransferInQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Quanity = Double.parseDouble(TransferInQty);

					// Transfer Out Quantity
					String TransferOutQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][1]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Quanity = Double.parseDouble(TransferOutQty);

					double Expected_On_Hand_Qty = Beginning_Quanity + Purchase_Quanity - Actual_Usage_Quanity
							+ TransferIn_Quanity - TransferOut_Quanity;

					// Actual On Hand Quantity
					String OnHandQty = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][1]"))
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

					// Check whether the Beginning Quantity and On Hand Quantity is Equal or not
					if (DateRange_previousday_Qty == Beginning_Quanity) {
						test.log(LogStatus.PASS,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Quanity - DateRange_previousday_Qty;
						test.log(LogStatus.FAIL,
								"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					// Get the Beginning Price
					String BeginningePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Beginning_Price = Double.parseDouble(BeginningePrice);

					// Purchase Quantity
					String PurchasePrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Purchase_Price = Double.parseDouble(PurchasePrice);

					// Transfer In Quantity
					String TransferInPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferIn_Price = Double.parseDouble(TransferInPrice);

					// Transfer Out Quantity
					String TransferOutPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][2]"))
							.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double TransferOut_Price = Double.parseDouble(TransferOutPrice);

					double Expected_On_Hand_Price = Beginning_Price + Purchase_Price - Actual_Usage_Price
							+ TransferIn_Price - TransferOut_Price;

					// Actual On Hand Price
					String OnHandPrice = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][2]"))
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

					// Check whether the Beginning Price and On Hand Price is Equal or not
					if (DateRange_previousday_Price == Beginning_Price) {
						test.log(LogStatus.PASS,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory.");
					} else {
						double diff = Beginning_Price - DateRange_previousday_Price;
						test.log(LogStatus.FAIL,
								"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory. Quantity Diff is : "
										+ diff);
					}

					String selTypeText = driver
							.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.type.value']")).getText();
					// Check whether the selected type displays in the Type column or not
					if (selTypeText.equalsIgnoreCase("MENUITEM")) {
						test.log(LogStatus.PASS, "Type column shows selected Type");
					} else {
						test.log(LogStatus.FAIL, "Type column shows Incorrect type for selected type: " + selTypeText);
					}
				}
			}

			Thread.sleep(2000);
			test.log(LogStatus.INFO, "Central Inventory Report for Sub Recipe Selection End (Date Range)");

			wb.close();
		}
		}
		
		@Test(enabled = false, priority = 48)
		public void Inventory_Reports_Compare_Inventory_Menu_Item_This_Month(WebDriver driver) throws Exception {
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
			Sub.selectByVisibleText("Menu Item");

			Thread.sleep(3000);
			// Click type as Time period
			Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
			TimePeriod.selectByVisibleText("Specific date");

			Thread.sleep(3000);
			//Click on the Input bax to enter date
			driver.findElement(By.xpath("//input[@ng-model='query.specificDate']")).sendKeys("30-Apr-2022");
			
			
//			// Click on the day option in the date selection tab.
//			driver.findElement(By.xpath("//i[@class='fa fa-calendar']//..")).click();
//
//			System.out.println(java.time.LocalDate.now());
//
//			LocalDate curr_date = java.time.LocalDate.now();
//
//			System.out.println(curr_date);
//
//			Date d = new Date();
//			System.out.println(d.getDay());
//
//			int Current_day = d.getDay();
//			LocalDate start_day, last_day;
//
//			start_day = curr_date;
//			System.out.println(start_day);
//			start_day = curr_date.minusDays(60);
//			String String_Of_Day = start_day.toString();
//			String sup = String_Of_Day.substring(8);
//			System.out.println(sup);
//			int o = Integer.parseInt(sup);
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//tr//th[1]//button//span[@class='sr-only']")).click();
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//tr//th[1]//button//span[@class='sr-only']")).click();
//			Thread.sleep(3000);
//			driver.findElement(By.xpath("//td//button[@type='button']//span[.=" + o + "]")).click();

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

				List<WebElement> leftPagination = driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
				int LeftPageSize = leftPagination.size();

				// Click the pagination for Report row increase button
				driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

				List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

				for (int j = 3; j <= rows.size(); j++) {
					test.log(LogStatus.INFO,
							"Item Name is : " + driver.findElement(By.xpath("//tr[" + j + "]/td[1]")).getText());

					// Actual On Hand Quantity
					String OnHandQty = driver
							.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.onHand.value'][1]")).getText()
							.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString().substring(1).toString();
					double Actual_OnHand_Quanity = Double.parseDouble(OnHandQty);
					this.this_Month_Pervious_day_Onhand_Qty = Actual_OnHand_Quanity;
//																									System.out.println(Yesterday_Onhand_Qty);

					// Actual On Hand Price
					String OnHandPrice = driver
							.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.onHand.value'][2]")).getText()
							.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
					double Actual_OnHand_Price = Double.parseDouble(OnHandPrice);
					this.this_Month_Pervious_day_Onhand_Price = Actual_OnHand_Price;
//																									System.out.println(Yesterday_Onhand_Price);

				}

				// Select the From Date Range
//																								driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
//																								//Enter the required From Date Range
//																								driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
//																										
//																								//Select the TO Date Range
//																								driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
//																								//Enter the  required TO Date Range
//																								driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

				// Select type as ALL
				Thread.sleep(3000);
				// Select type as ALL
				Select TimePeriod1 = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
				TimePeriod1.selectByVisibleText("This month");

//				Thread.sleep(2000);
//				// Click on the day option in the date selection tab.
//				driver.findElement(By.xpath("//a[2]//i[@class='fa fa-calendar']//..")).click();
//
//				Robot rob = new Robot();
//
//				Thread.sleep(2000);
//				rob.keyPress(KeyEvent.VK_ENTER);
//				rob.keyRelease(KeyEvent.VK_ENTER);

				Thread.sleep(2000);
				// Click the Run Comparison button
				driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();

				Thread.sleep(2000);
				try {
					if (driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText()
							.equalsIgnoreCase("No records found")) {
						test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
					}
				} catch (Exception g) {

					test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
					Thread.sleep(5000);

					List<WebElement> leftPagination1 = driver
							.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
					int LeftPageSize1 = leftPagination.size();

					// Click the pagination for Report row increase button
					driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

					List<WebElement> rows1 = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

					for (int i = 3; i <= rows1.size(); i++) {

						test.log(LogStatus.INFO,
								"Item Name is : " + driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText());

						String IdealQty = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][1]"))
								.getText();
						double Ideal_Used_Quanity = Double.parseDouble(IdealQty);

						String VarianceQty = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][1]"))
								.getText();
						double Variance_Quanity = Double.parseDouble(VarianceQty);

//																									String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//																									double Wastage_Quanity=Double.parseDouble(WasteQty);

						double Expected_Usage_Quantity = Ideal_Used_Quanity + Variance_Quanity;

						String Actual_UsageQty = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][1]"))
								.getText();
						double Actual_Usage_Quanity = Double.parseDouble(Actual_UsageQty);

						// Check whether the Actual and Expected Usage Quantity is Equal or not
						if (Actual_Usage_Quanity == Expected_Usage_Quantity) {
							test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
						} else {
							double diff = Actual_Usage_Quanity - Expected_Usage_Quantity;
							test.log(LogStatus.FAIL,
									"Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "
											+ diff);
						}

						String IdealPrice = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][2]"))
								.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
						;
						double Ideal_Used_Price = Double.parseDouble(IdealPrice);

						String VariancePrice = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][2]"))
								.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
						double Variance_Price = Double.parseDouble(VariancePrice);

//																									String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//																									double Wastage_Price=Double.parseDouble(WastePrice);

						double Expected_Usage_Price = Ideal_Used_Price + Variance_Price;

						String Actual_UsagePrice = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][2]"))
								.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
						double Actual_Usage_Price = Double.parseDouble(Actual_UsagePrice);

						// Check whether the Actual and Expected Usage Price is Equal or not
						if (Actual_Usage_Price == Expected_Usage_Price) {
							test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
						} else {
							double diff = Actual_Usage_Price - Expected_Usage_Price;

							test.log(LogStatus.FAIL,
									"Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "
											+ diff);
						}

						// Get the Beginning Quantity
						String BeginningeQty = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][1]"))
								.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
						double Beginning_Quanity = Double.parseDouble(BeginningeQty);

						// Purchase Quantity
						String PurchaseQty = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][1]"))
								.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
						double Purchase_Quanity = Double.parseDouble(PurchaseQty);

						// Transfer In Quantity
						String TransferInQty = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][1]"))
								.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
						double TransferIn_Quanity = Double.parseDouble(TransferInQty);

						// Transfer Out Quantity
						String TransferOutQty = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][1]"))
								.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
						double TransferOut_Quanity = Double.parseDouble(TransferOutQty);

						double Expected_On_Hand_Qty = Beginning_Quanity + Purchase_Quanity - Actual_Usage_Quanity
								+ TransferIn_Quanity - TransferOut_Quanity;

						// Actual On Hand Quantity
						String OnHandQty = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][1]"))
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

						// Check whether the Beginning Quantity and On Hand Quantity is Equal or not
						if (this_Month_Pervious_day_Onhand_Qty == Beginning_Quanity) {
							test.log(LogStatus.PASS,
									"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory.");
						} else {
							double diff = Beginning_Quanity - this_Month_Pervious_day_Onhand_Qty;
							test.log(LogStatus.FAIL,
									"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory. Quantity Diff is : "
											+ diff);
						}

						// Get the Beginning Price
						String BeginningePrice = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][2]"))
								.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
						double Beginning_Price = Double.parseDouble(BeginningePrice);

						// Purchase Quantity
						String PurchasePrice = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][2]"))
								.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
						double Purchase_Price = Double.parseDouble(PurchasePrice);

						// Transfer In Quantity
						String TransferInPrice = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][2]"))
								.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
						double TransferIn_Price = Double.parseDouble(TransferInPrice);

						// Transfer Out Quantity
						String TransferOutPrice = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][2]"))
								.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
						double TransferOut_Price = Double.parseDouble(TransferOutPrice);

						double Expected_On_Hand_Price = Beginning_Price + Purchase_Price - Actual_Usage_Price
								+ TransferIn_Price - TransferOut_Price;

						// Actual On Hand Price
						String OnHandPrice = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][2]"))
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

						// Check whether the Beginning Price and On Hand Price is Equal or not
						if (this_Month_Pervious_day_Onhand_Price == Beginning_Price) {
							test.log(LogStatus.PASS,
									"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory.");
						} else {
							double diff = Beginning_Price - this_Month_Pervious_day_Onhand_Price;
							test.log(LogStatus.FAIL,
									"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory. Quantity Diff is : "
											+ diff);
						}

						String selTypeText = driver
								.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.type.value']")).getText();
						// Check whether the selected type displays in the Type column or not
						if (selTypeText.equalsIgnoreCase("MENUITEM")) {
							test.log(LogStatus.PASS, "Type column shows selected Type");
						} else {
							test.log(LogStatus.FAIL, "Type column shows Incorrect type for selected type: " + selTypeText);
						}
					}
				}

				Thread.sleep(2000);
				test.log(LogStatus.INFO, "Central Inventory Report for Sub Recipe Selection End (Date Range)");

				wb.close();
			}
		}
			
			@Test(enabled = false, priority = 48)
			public void Inventory_Reports_Compare_Inventory_Menu_Item_Last_Month(WebDriver driver) throws Exception {
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
				Sub.selectByVisibleText("Menu Item");

				Thread.sleep(3000);
				// Click type as Time period
				Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
				TimePeriod.selectByVisibleText("Specific date");

				Thread.sleep(2000);
				//Click on the Input bax to enter date
				driver.findElement(By.xpath("//input[@ng-model='query.specificDate']")).sendKeys("31-Mar-2022");
				
				
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

					List<WebElement> leftPagination = driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
					int LeftPageSize = leftPagination.size();

					// Click the pagination for Report row increase button
					driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

					List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

					for (int j = 3; j <= rows.size(); j++) {
						test.log(LogStatus.INFO,
								"Item Name is : " + driver.findElement(By.xpath("//tr[" + j + "]/td[1]")).getText());

						// Actual On Hand Quantity
						String OnHandQty = driver
								.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.onHand.value'][1]")).getText()
								.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString().substring(1).toString();
						double Actual_OnHand_Quanity = Double.parseDouble(OnHandQty);
						this.last_Month_Pervious_day_Onhand_Qty = Actual_OnHand_Quanity;
//																										System.out.println(Yesterday_Onhand_Qty);

						// Actual On Hand Price
						String OnHandPrice = driver
								.findElement(By.xpath("//tr[" + j + "]/td[@ng-if='reportColumns.onHand.value'][2]")).getText()
								.replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
						double Actual_OnHand_Price = Double.parseDouble(OnHandPrice);
						this.last_Month_Pervious_day_Onhand_Price = Actual_OnHand_Price;
//																										System.out.println(Yesterday_Onhand_Price);

					}

					// Select the From Date Range
//																									driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
//																									//Enter the required From Date Range
//																									driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
//																											
//																									//Select the TO Date Range
//																									driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
//																									//Enter the  required TO Date Range
//																									driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));

					// Select type as ALL
					Thread.sleep(8000);
					// Select type as ALL
					Select TimePeriod1 = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
					TimePeriod1.selectByVisibleText("Last month");

					
					Thread.sleep(2000);
					// Click the Run Comparison button
					driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();

					Thread.sleep(2000);
					try {
						if (driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText()
								.equalsIgnoreCase("No records found")) {
							test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
						}
					} catch (Exception g) {

						test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
						Thread.sleep(5000);

						List<WebElement> leftPagination1 = driver
								.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
						int LeftPageSize1 = leftPagination.size();

						// Click the pagination for Report row increase button
						driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li[" + LeftPageSize + "]/a")).click();

						List<WebElement> rows1 = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));

						for (int i = 3; i <= rows1.size(); i++) {

							test.log(LogStatus.INFO,
									"Item Name is : " + driver.findElement(By.xpath("//tr[" + i + "]/td[1]")).getText());

							String IdealQty = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][1]"))
									.getText();
							double Ideal_Used_Quanity = Double.parseDouble(IdealQty);

							String VarianceQty = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][1]"))
									.getText();
							double Variance_Quanity = Double.parseDouble(VarianceQty);

//																										String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//																										double Wastage_Quanity=Double.parseDouble(WasteQty);

							double Expected_Usage_Quantity = Ideal_Used_Quanity + Variance_Quanity;

							String Actual_UsageQty = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][1]"))
									.getText();
							double Actual_Usage_Quanity = Double.parseDouble(Actual_UsageQty);

							// Check whether the Actual and Expected Usage Quantity is Equal or not
							if (Actual_Usage_Quanity == Expected_Usage_Quantity) {
								test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
							} else {
								double diff = Actual_Usage_Quanity - Expected_Usage_Quantity;
								test.log(LogStatus.FAIL,
										"Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "
												+ diff);
							}

							String IdealPrice = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.idealUsed.value'][2]"))
									.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
							;
							double Ideal_Used_Price = Double.parseDouble(IdealPrice);

							String VariancePrice = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.variance.value'][2]"))
									.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
							double Variance_Price = Double.parseDouble(VariancePrice);

//																										String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//																										double Wastage_Price=Double.parseDouble(WastePrice);

							double Expected_Usage_Price = Ideal_Used_Price + Variance_Price;

							String Actual_UsagePrice = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.usage.value'][2]"))
									.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
							double Actual_Usage_Price = Double.parseDouble(Actual_UsagePrice);

							// Check whether the Actual and Expected Usage Price is Equal or not
							if (Actual_Usage_Price == Expected_Usage_Price) {
								test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
							} else {
								double diff = Actual_Usage_Price - Expected_Usage_Price;

								test.log(LogStatus.FAIL,
										"Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "
												+ diff);
							}

							// Get the Beginning Quantity
							String BeginningeQty = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][1]"))
									.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
							double Beginning_Quanity = Double.parseDouble(BeginningeQty);

							// Purchase Quantity
							String PurchaseQty = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][1]"))
									.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
							double Purchase_Quanity = Double.parseDouble(PurchaseQty);

							// Transfer In Quantity
							String TransferInQty = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][1]"))
									.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
							double TransferIn_Quanity = Double.parseDouble(TransferInQty);

							// Transfer Out Quantity
							String TransferOutQty = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][1]"))
									.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
							double TransferOut_Quanity = Double.parseDouble(TransferOutQty);

							double Expected_On_Hand_Qty = Beginning_Quanity + Purchase_Quanity - Actual_Usage_Quanity
									+ TransferIn_Quanity - TransferOut_Quanity;

							// Actual On Hand Quantity
							String OnHandQty = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][1]"))
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

							// Check whether the Beginning Quantity and On Hand Quantity is Equal or not
							if (last_Month_Pervious_day_Onhand_Qty == Beginning_Quanity) {
								test.log(LogStatus.PASS,
										"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory.");
							} else {
								double diff = Beginning_Quanity - last_Month_Pervious_day_Onhand_Qty;
								test.log(LogStatus.FAIL,
										"The Beginning Quantity of the perivous day and On Hand Quantity of the Current day is Equal in Compare Inventory. Quantity Diff is : "
												+ diff);
							}

							// Get the Beginning Price
							String BeginningePrice = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.beginning.value'][2]"))
									.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
							double Beginning_Price = Double.parseDouble(BeginningePrice);

							// Purchase Quantity
							String PurchasePrice = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.purchase.value'][2]"))
									.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
							double Purchase_Price = Double.parseDouble(PurchasePrice);

							// Transfer In Quantity
							String TransferInPrice = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferIn.value'][2]"))
									.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
							double TransferIn_Price = Double.parseDouble(TransferInPrice);

							// Transfer Out Quantity
							String TransferOutPrice = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.transferOut.value'][2]"))
									.getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();
							double TransferOut_Price = Double.parseDouble(TransferOutPrice);

							double Expected_On_Hand_Price = Beginning_Price + Purchase_Price - Actual_Usage_Price
									+ TransferIn_Price - TransferOut_Price;

							// Actual On Hand Price
							String OnHandPrice = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.onHand.value'][2]"))
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

							// Check whether the Beginning Price and On Hand Price is Equal or not
							if (last_Month_Pervious_day_Onhand_Price == Beginning_Price) {
								test.log(LogStatus.PASS,
										"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory.");
							} else {
								double diff = Beginning_Price - last_Month_Pervious_day_Onhand_Price;
								test.log(LogStatus.FAIL,
										"The Beginning Price of the perivous day and On Hand Price of the Current day is Equal in Compare Inventory. Quantity Diff is : "
												+ diff);
							}
							if (Actual_Usage_Price == Expected_Usage_Price) {
								test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
							} else {
								double diff = Actual_Usage_Price - Expected_Usage_Price;

								test.log(LogStatus.FAIL,
										"Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "
												+ diff);
							}

							String selTypeText = driver
									.findElement(By.xpath("//tr[" + i + "]/td[@ng-if='reportColumns.type.value']")).getText();
							// Check whether the selected type displays in the Type column or not
							if (selTypeText.equalsIgnoreCase("MENUITEM")) {
								test.log(LogStatus.PASS, "Type column shows selected Type");
							} else {
								test.log(LogStatus.FAIL, "Type column shows Incorrect type for selected type: " + selTypeText);
							}
						}
					}

					Thread.sleep(2000);
					test.log(LogStatus.INFO, "Central Inventory Report for Sub Recipe Selection End (Date Range)");

					wb.close();
				}

	}
}
