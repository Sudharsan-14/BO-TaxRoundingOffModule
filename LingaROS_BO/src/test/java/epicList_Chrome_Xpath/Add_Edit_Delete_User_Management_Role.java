package epicList_Chrome_Xpath;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class Add_Edit_Delete_User_Management_Role {
	public WebDriver driver;

	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Add_Edit_Delete_User_Management_Role");

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}
	}

	@AfterClass
	public void flushTest() throws Exception {
		Thread.sleep(2000);
		rep.endTest(test);
		rep.flush();
	//	SendMail.snedMailWithAttachment();

		// SendMail.snedMailWithAttachment();
		// SendMail.sendMailWithAttachment_Passed_And_Failed_Test_Methods();
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
			Browser a = new Browser();
			a.UPOS_login(driver, test);
		} else {
			Browser a = new Browser();
			a.Linga_login(driver, test);
		}
	}

	@Test(priority = 500)
	public void logout() throws Exception {
		Browser a = new Browser();
		a.Logout(driver, test);
	}

	@Test(priority = 100)
	public void calling() throws Exception {
		Thread.sleep(10000);
		try {
			Thread.sleep(1000);
			System.out.println("Minimize Chat Icon");
			driver.findElement(By.id("zsiq_minimize")).click();
		} catch (Exception e) {
			System.out.println("Minimize Chat Iconsdasdasdas");
		}
		// ViewDashBoardReports a = new ViewDashBoardReports();
		Thread.sleep(1000);
		Usermanagement_Role_Pageopen(driver);
		Usermanagement_Role_refreshPage(driver);
		Usermanagement_Role_addingpage(driver);
		Usermanagement_Role_RevertRoles(driver);
		Usermanagement_Role_CheckRevertedRoles(driver);
		Usermanagement_Role_editRoles(driver);
		Usermanagement_Role_Activestatus(driver);
		Usermanagement_Role_deleteRoles(driver);
		Usermanagement_Role_DeActivestatus(driver);
		Thread.sleep(1500);
	}

	@Test(enabled = false, priority = 2)
	public void Usermanagement_Role_Pageopen(WebDriver driver) throws Exception {
		Thread.sleep(15000);
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(2000);
		/*
		 * //Click the User Management option
		 * driver.findElement(By.xpath("//span[.='User Management']")).click(); //
		 * Create instance of Java script executor JavascriptExecutor je =
		 * (JavascriptExecutor) driver; //Identify the WebElement which will appear
		 * after scrolling down WebElement element =
		 * driver.findElement(By.xpath("//span[.='Roles']"));Thread.sleep(3000);
		 * //Scroll the page till the Sale option present
		 * je.executeScript("arguments[0].scrollIntoView(true);",element);
		 * 
		 * //Click the Roles Option
		 * driver.findElement(By.xpath("//span[.='Roles']")).click();
		 */
		Thread.sleep(4000);
		// Enter the Punchin-out URL
		driver.get(Utility.getProperty("baseURL") + Utility.getProperty("store_Id") + "roles");

		Thread.sleep(5000);
		// Check Roles page opened or not
		if (driver.findElement(By.xpath(excel.getData(3, 550, 1))).getText().equalsIgnoreCase("Roles")) {
			test.log(LogStatus.PASS, "Roles page loaded Successfully");
		} else {
			test.log(LogStatus.FAIL, "Roles page loaded Failed");
		}
		Thread.sleep(3000);wb.close();

	}

	@Test(priority = 12, enabled = false)
	public void watchTutorial(WebDriver driver) throws Exception {
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(2000);
		// Click the Watch Tutorial Option
		driver.findElement(By.xpath(excel.getData(3, 47, 1))).click();
		WebElement iframe = driver.findElement(By.xpath(excel.getData(3, 48, 1)));
		driver.switchTo().frame(iframe);
		Thread.sleep(3500);
		try {
			if (driver.findElement(By.xpath(excel.getData(3, 49, 1))).isDisplayed()
					&& driver.findElement(By.xpath(excel.getData(3, 551, 1))).isDisplayed()) {
				test.log(LogStatus.PASS, "Watch Tutorial Video Played Well");
				Thread.sleep(2500);
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Watch Tutorial Video not Played");
		}
		driver.switchTo().defaultContent();

		Thread.sleep(2000);
		// Click the Close button
		driver.findElement(By.xpath(excel.getData(3, 50, 1))).click();wb.close();
	}

	@Test(enabled = false, priority = 3)
	public void Usermanagement_Role_refreshPage(WebDriver driver) throws Exception {
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
		// Click the refresh button
		driver.findElement(By.xpath(excel.getData(0, 30, 1)))
				.click();
		Thread.sleep(3000);
		// Check Roles page opened or not
		if (driver.findElement(By.xpath(excel.getData(3, 550, 1))).getText().equalsIgnoreCase("Roles")) {
			test.log(LogStatus.PASS, "Roles page refresh loaded Successfully");
		} else {
			test.log(LogStatus.FAIL, "Roles page refresh loaded Failed");
		}
		Thread.sleep(3000);wb.close();
	
	}

	@Test(enabled = false, priority = 5)
	
	public void Usermanagement_Role_addingpage(WebDriver driver) throws Exception {
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(2000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(6000);
		// Click the Add Roles or Create new Role button
		driver.findElement(By.xpath(excel.getData(3, 552, 1))).click();
		Thread.sleep(3000);
		// Check New Role form page loaded or not
		if (driver.findElement(By.xpath(excel.getData(3, 553, 1))).getText().equalsIgnoreCase("New Role")) {
			test.log(LogStatus.PASS, "New Roles form loaded Successfully");
		} else {
			test.log(LogStatus.FAIL, "New Roles form loaded Failed");
		}
		Thread.sleep(3000);

		// Clear the name field
		driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
		// Enter the required name
		driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Roles_name"));

		Thread.sleep(1000);
		// Click the Priority Field
		driver.findElement(By.xpath(excel.getData(3, 554, 1))).click();
		// Enter the Required priority
		driver.findElement(By.xpath(excel.getData(3, 555, 1))).sendKeys("3");
		// Press the Enter Key
		driver.findElement(By.xpath(excel.getData(3, 555, 1))).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		// Click the Back Office link
		JavascriptExecutor je = (JavascriptExecutor) driver;
		// Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath(excel.getData(3, 556, 1)));
		// Scroll the page till the Reason option present
		je.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		driver.findElement(By.xpath(excel.getData(3, 556, 1))).click();

		List<WebElement> bockOfficeCount = driver
				.findElements(By.xpath(excel.getData(3, 557, 1)));
		bockOfficeCount.size();

		for (int i = 2; i <= bockOfficeCount.size(); i++) {
			// Click the required Check box
			driver.findElement(By.xpath(excel.getData(3, 558, 1))).click();
		}

		// Click the POS link
		JavascriptExecutor je1 = (JavascriptExecutor) driver;
		// Identify the WebElement which will appear after scrolling down
		WebElement element1 = driver.findElement(By.xpath(excel.getData(3, 559, 1)));
		// Scroll the page till the Reason option present
		je1.executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		driver.findElement(By.xpath(excel.getData(3, 559, 1))).click();
		Thread.sleep(2000);
		List<WebElement> posCount = driver.findElements(By.xpath(excel.getData(3, 560, 1)));
		posCount.size();

		for (int i = 10; i <= posCount.size(); i++) {
			// Click the required Check box
			driver.findElement(By.xpath(excel.getData(3, 561, 1))).click();
		}

		// Click the POS OPERATIONS link
		JavascriptExecutor je2 = (JavascriptExecutor) driver;
		// Identify the WebElement which will appear after scrolling down
		WebElement element2 = driver.findElement(By.xpath(excel.getData(3, 562, 1)));
		// Scroll the page till the Reason option present
		je2.executeScript("arguments[0].scrollIntoView(true);", element2);

		Thread.sleep(2000);
		// Click the POS Operations link
		driver.findElement(By.xpath(excel.getData(3, 562, 1))).click();

		List<WebElement> posOperationsCount = driver.findElements(By.xpath(excel.getData(3, 563, 1)));
		posOperationsCount.size();
		// Scroll the web page
		for (int i = 1; i <= 20; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		}
		Thread.sleep(3000);
		for (int i = 3; i <= posOperationsCount.size(); i++) {
			driver.findElement(By.xpath(excel.getData(3, 564, 1))).click();
		}

		// Click the REPORT ACCESS link
		JavascriptExecutor je3 = (JavascriptExecutor) driver;
		// Identify the WebElement which will appear after scrolling down
		WebElement element3 = driver.findElement(By.xpath(excel.getData(3, 565, 1)));
		// Scroll the page till the Reason option present
		je3.executeScript("arguments[0].scrollIntoView(true);", element3);
		Thread.sleep(2000);
		// Click the REPORT ACCESS link
		driver.findElement(By.xpath(excel.getData(3, 565, 1))).click();

		List<WebElement> reportAccessCount = driver.findElements(By.xpath(excel.getData(3, 566, 1)));

		// Scroll the web page
		for (int i = 1; i <= 20; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		}
		Thread.sleep(3000);
		for (int i = 3; i <= reportAccessCount.size(); i++) {
			// Click the required Check box
			driver.findElement(By.xpath(excel.getData(3, 567, 1))).click();
		}

		// Click the DASH BOARD link
		JavascriptExecutor je4 = (JavascriptExecutor) driver;
		// Identify the WebElement which will appear after scrolling down
		WebElement element4 = driver.findElement(By.xpath(excel.getData(3, 568, 1)));
		// Scroll the page till the Reason option present
		je4.executeScript("arguments[0].scrollIntoView(true);", element4);
		Thread.sleep(2000);

		// Click the DASH BOARD link
		driver.findElement(By.xpath(excel.getData(3, 568, 1))).click();

		List<WebElement> dashBoardCount = driver
				.findElements(By.xpath(excel.getData(3, 569, 1)));
		// Scroll the web page
		for (int i = 1; i <= 20; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		}
		Thread.sleep(3000);
		for (int i = 3; i <= dashBoardCount.size(); i++) {
			// Click the required Check box
			driver.findElement(By.xpath(excel.getData(3, 570, 1))).click();
		}
		Thread.sleep(3000);

		// Scroll the web page
		for (int i = 1; i <= 20; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
			Thread.sleep(2000);
	//		Thread.sleep(1000);
		}

		// Click the DASH BOARD link
	//	driver.findElement(By.xpath(excel.getData(3, 568, 1))).click();

		Thread.sleep(3000);
		// Click the Item Access ALl button
		//driver.findElement(By.xpath(excel.getData(3, 571, 1))).click();

		// Click the save button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		Thread.sleep(3000);
		// Check weather the new role saved successfully or not
		if (driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText()
				.equalsIgnoreCase("Role saved successfully")) {
			test.log(LogStatus.PASS, "New Role Saved Sucessfully");
		} else {
			test.log(LogStatus.FAIL, "New Role Save Failed");wb.close();
		}

	}

	@Test(enabled = false, priority = 6)
	public void Usermanagement_Role_RevertRoles(WebDriver driver) throws Exception {

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	  
		Thread.sleep(2000);
		// Clear the search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
		// Enter the required keyword
		driver.findElement(By.xpath(excel.getData(3, 40, 1)))
				.sendKeys("Cook");
		// Click the edit button
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
		Thread.sleep(2000);
		Thread.sleep(3000);
		// Click the Back Office link
		driver.findElement(By.xpath(excel.getData(3, 556, 1))).click();
		// Click the Create Menu
		driver.findElement(By.xpath(excel.getData(3, 573, 1))).click();
		// Click the Back Office link
		driver.findElement(By.xpath(excel.getData(3, 556, 1))).click();
		Thread.sleep(3000);
		// Click the save or update button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		Thread.sleep(3000);
		// Clear the search box
		driver.findElement(By.cssSelector(excel.getData(3, 574, 4))).clear();
		// Enter the required keyword in search box
		driver.findElement(By.cssSelector(excel.getData(3, 574, 4))).sendKeys("Cook");
		Thread.sleep(5000);
		// Click Revert Option
		driver.findElement(By.cssSelector(excel.getData(3, 575, 4))).click();

		// Click the yes button on popup
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(excel.getData(3, 209, 4))).click();

		Thread.sleep(5000);
		// Check weather the role reverted or not
		if (driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText()
				.equalsIgnoreCase("Privileges reverted successfully")) {
			test.log(LogStatus.PASS, "Privileges reverted successfully");
		} else {
			test.log(LogStatus.FAIL, "Privileges reverted Failed");wb.close();
		}

	}

	@Test(enabled = false, priority = 7)
	public void Usermanagement_Role_CheckRevertedRoles(WebDriver driver) throws Exception {
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	  
		Thread.sleep(2000);
		// Clear the search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1)))
				.clear();
		// Enter the required keyword
		driver.findElement(By.xpath(excel.getData(3, 40, 1)))
				.sendKeys("Cook");
		// Click the edit button
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
		Thread.sleep(2000);
		Thread.sleep(4000);
		// Click cancel Button
		driver.findElement(By.xpath(excel.getData(3, 130, 1))).click();

		Thread.sleep(2000);
		// Clear the search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1)))
				.clear();
		// Enter the required keyword
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys("Cook");
		// Click the edit button
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
		Thread.sleep(2000);
		// Click the Back Office link
		driver.findElement(By.xpath(excel.getData(3, 556, 1))).click();
		// Click the Edit Menu Price
		driver.findElement(By.xpath(excel.getData(3, 577, 1))).click();
		Thread.sleep(3000);
		// Click the Back Office link
		driver.findElement(By.xpath(excel.getData(3, 556, 1))).click();
		// Click the save or update button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		Thread.sleep(3000);
		// Check weather the new role was updated or not
		if (driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText()
				.equalsIgnoreCase("Role updated successfully")) {
			test.log(LogStatus.PASS, "Reverted Role Updated Sucessfully");
		} else {
			test.log(LogStatus.FAIL, "Reverted Role Update Failed");
		}
		// Clear the search box
		driver.findElement(By.cssSelector(excel.getData(3, 574, 4))).clear();wb.close();
	}

	@Test(enabled = false, priority = 8)
	public void Usermanagement_Role_editRoles(WebDriver driver) throws Exception {
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	  
		Thread.sleep(2000);
		// Clear the search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1)))
				.clear();
		// Enter the required keyword
		driver.findElement(By.xpath(excel.getData(3, 40, 1)))
				.sendKeys(Utility.getProperty("Roles_name"));
		// Click the edit button
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(excel.getData(3, 41, 4))).click();
		Thread.sleep(2000);
		// Clear the name field
		driver.findElement(By.name(excel.getData(3, 254, 3))).clear();
		// Enter the required name
		driver.findElement(By.name(excel.getData(3, 254, 3))).sendKeys(Utility.getProperty("Roles_name") + "1");

		Thread.sleep(2000);
		// Click the Back Office link
		driver.findElement(By.xpath(excel.getData(3, 556, 1))).click();
		// Click the Gratutity
		driver.findElement(By.xpath(excel.getData(3, 578, 1))).click();
		// Click the Gift Card
		driver.findElement(By.xpath(excel.getData(3, 579, 1))).click();
		// Click the Back Office link
		driver.findElement(By.xpath(excel.getData(3, 556, 1))).click();

		/*
		 * List<WebElement> bockOfficeCount = driver.findElements(By.xpath(
		 * "html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/uib-accordion/div/div[1]/div[2]/div/div"
		 * )); bockOfficeCount.size();
		 * 
		 * for(int i = 2; i <= bockOfficeCount.size(); i=i+3) { //Click the required
		 * Check box driver.findElement(By.xpath(
		 * "html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/uib-accordion/div/div[1]/div[2]/div/div["
		 * +i+"]/input")).click(); }
		 */

		Thread.sleep(2000);
		// Click the POS link
		driver.findElement(By.xpath(excel.getData(3, 559, 1))).click();
		// Click the GC PAYMENT
		driver.findElement(By.xpath(excel.getData(3, 580, 1))).click();
		// Click the Tax Exempt
		driver.findElement(By.xpath(excel.getData(3, 581, 1))).click();
		// Click the POS link
		driver.findElement(By.xpath(excel.getData(3, 559, 1))).click();
		/*
		 * List<WebElement> posCount = driver.findElements(By.xpath(
		 * "html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/uib-accordion/div/div[2]/div[2]/div/div"
		 * )); posCount.size();
		 * 
		 * for(int i = 2; i <= posCount.size(); i=i+3) { //Click the required Check box
		 * driver.findElement(By.xpath(
		 * "html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/uib-accordion/div/div[2]/div[2]/div/div["
		 * +i+"]/input")).click(); }
		 */

		// Click the POS Operations link
		driver.findElement(By.xpath(excel.getData(3, 562, 1))).click();
		// Click the hold Till
		driver.findElement(By.xpath(excel.getData(3, 582, 1))).click();
		// Click the Cash Drop
		driver.findElement(By.xpath(excel.getData(3, 583, 1))).click();

		// Click the POS Operations link
		driver.findElement(By.xpath(excel.getData(3, 562, 1))).click();
		/*
		 * List<WebElement> posOperationsCount = driver.findElements(By.xpath(
		 * "html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/uib-accordion/div/div[3]/div[2]/div/div"
		 * )); posOperationsCount.size();
		 * 
		 * for(int i = 2; i <= posOperationsCount.size(); i=i+3) { //Click the required
		 * Check box driver.findElement(By.xpath(
		 * "html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/uib-accordion/div/div[3]/div[2]/div/div["
		 * +i+"]/input")).click(); }
		 * 
		 */
		// Click the save or update button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		Thread.sleep(3000);
		// Check weather the new role was updated or not
		if (driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText()
				.equalsIgnoreCase("Role updated successfully")) {
			test.log(LogStatus.PASS, "Role Updated Sucessfully");
		} else {
			test.log(LogStatus.FAIL, "Role Update Failed");wb.close();
		}
	}

	@Test(enabled = false, priority = 9)
	public void Usermanagement_Role_Activestatus(WebDriver driver) throws Exception {
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	  
		Thread.sleep(2000);
		/*
		 * //Check active Roles driver.findElement(By.xpath(
		 * "//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div[1]/input"
		 * )).click();
		 */
		Thread.sleep(3000);
		// Clear the search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1)))
				.clear();
		// Enter the required keyword
		driver.findElement(By.xpath(excel.getData(3, 40, 1)))
				.sendKeys(Utility.getProperty("Roles_name") + "1");
		Thread.sleep(3000);
		// Clear the search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1)))
				.clear();

		Thread.sleep(3000);wb.close();
	}

	@Test(enabled = false, priority = 10)
	public void Usermanagement_Role_deleteRoles(WebDriver driver) throws Exception {
		// Clear the search field
       File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	  
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
		// Enter the required keyword
		driver.findElement(By.xpath(excel.getData(3, 40, 1)))
				.sendKeys(Utility.getProperty("Roles_name") + "1");
		Thread.sleep(2000);
		// Click on the delete button
		driver.findElement(By.cssSelector(excel.getData(3, 42, 4))).click();
		Thread.sleep(3000);
		// Click the yes button on popup
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(excel.getData(3, 209, 4))).click();
		Thread.sleep(3000);
		// Check weather the role deleted or not
		if (driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText()
				.equalsIgnoreCase("Role inactivated successfully")) {
			test.log(LogStatus.PASS, "Role deleted Sucessfully");
		} else {
			test.log(LogStatus.FAIL, "Role deleted Failed");wb.close();
		}
	}

	@Test(enabled = false, priority = 11)
	public void Usermanagement_Role_DeActivestatus(WebDriver driver) throws Exception {
        File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	  
		Thread.sleep(2000);
		// Check Deactivate Roles
		driver.findElement(By.xpath(excel.getData(3, 584, 1)))
				.click();
		Thread.sleep(3000);
		// Clear the search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1)))
				.clear();
		// Enter the required keyword
		driver.findElement(By.xpath(excel.getData(3, 40, 1)))
				.sendKeys(Utility.getProperty("Roles_name") + "1");
		Thread.sleep(2000);
		// Click the activate button
		driver.findElement(By.cssSelector(excel.getData(3, 157, 4))).click();
		Thread.sleep(2000);
		// Click the Yes button in the popup
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(excel.getData(3, 209, 4))).click();
		Thread.sleep(3000);
		// Check if we Deactivate or not
		if (driver.findElement(By.xpath(excel.getData(3, 576, 1))).getText()
				.equalsIgnoreCase("role activated successfully")) {
			test.log(LogStatus.PASS, "role activated successfully successfully");
		} else {
			test.log(LogStatus.FAIL, "role activated successfully is Failed");
		}

		if (Utility.getProperty("Product").equalsIgnoreCase("UPOS")) {
		} else {
			Thread.sleep(5000);
			watchTutorial(driver);
			Thread.sleep(5000);wb.close();
		}
	}

}
