package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome_Account_User.Utility;
import newReadExcelFile_New.ExcelDataConfig;

public class Bulk_Update {
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Bulk Update");

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
			Bulk_Update_open_Bulk_Update(driver);
			Bulk_Update_refresh_Bulk_Update_Page(driver);
			Bulk_Update_pagination(driver);
			Bulk_Update_edit_menu_Item_Price_SingleServing_Size(driver);
			Bulk_Update_edit_menu_Item_Price_MultipleServing_Size(driver);
			Bulk_Update_edit_menu_Item_Price_Many_SingleServing_Size(driver);
			Thread.sleep(1500);
		}
		
	@Test(enabled=false,priority=14)
	public void Bulk_Update_open_Bulk_Update(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

/*		//Click the Products/Items option
		driver.findElement(By.xpath("//span[.='Products/Items']")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[3]/ul/li[10]/a/span"));
		//Scroll the page till the Reason option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Products/Items Option		
		driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[3]/ul/li[10]/a/span")).click();
		Thread.sleep(3000);
		
		//Click the Bulk Update
		driver.findElement(By.xpath("//span[.=' Bulk Update ']")).click();*/
		
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"bulkMenuUpdate");
		
		Thread.sleep(5000);
		try
		{
		//Check Bulk Update page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 2113, 1))).getText().equalsIgnoreCase("Bulk Menu Update"))
		{
			test.log(LogStatus.PASS, "Bulk Update page loaded Successfully");
			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Bulk Update page loaded Failed");
		
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
		wb.close();
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=15)
	public void Bulk_Update_refresh_Bulk_Update_Page(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		for(int i = 1; i <= 10; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		Thread.sleep(5000);
		//Click the refresh button
		driver.findElement(By.xpath(excel.getData(3, 2114, 1))).click();
		Thread.sleep(5000);
		//Check Bulk Update page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 2113, 1))).getText().equalsIgnoreCase("Bulk Menu Update"))
		{
			test.log(LogStatus.PASS, "Bulk Update page refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Bulk Update page refreshed Failed");
		}
		wb.close();
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=16)
	public void Bulk_Update_pagination(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
		Thread.sleep(1000);
		
		//Click the Pagination option as 10
		driver.findElement(By.xpath(excel.getData(3, 1806, 1))).click();Thread.sleep(3000);
		test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Bulk Update");
		//Create the web element for menu item Table
		List<WebElement> results = driver.findElements(By.xpath(excel.getData(3, 1807, 1)));
		for (WebElement result : results){						
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.xpath(excel.getData(3, 2115, 1)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Bulk Update, when we click 10");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 15
		driver.findElement(By.xpath(excel.getData(3, 1808, 1))).click();Thread.sleep(3000);
		test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Bulk Update");
		//Create the web element for menu item Table
		List<WebElement> results1 = driver.findElements(By.xpath(excel.getData(3, 1807, 1)));
		for (WebElement result : results1){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.xpath(excel.getData(3, 2115, 1)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Bulk Update, when we click 15");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 20
		driver.findElement(By.xpath(excel.getData(3, 1809, 1))).click();Thread.sleep(3000);
		test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for Bulk Update");
		//Create the web element for menu item Table
		List<WebElement> results2 = driver.findElements(By.xpath(excel.getData(3, 1807, 1)));
		for (WebElement result : results2){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.xpath(excel.getData(3, 2115, 1)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Bulk Update, when we click 20");
		}
		Thread.sleep(8000);
		
		//Click the Pagination option as 5
		driver.findElement(By.xpath(excel.getData(3, 1810, 1))).click();Thread.sleep(3000);
		test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Bulk Update");
		//Create the web element for menu item Table
		List<WebElement> results3 = driver.findElements(By.xpath(excel.getData(3, 1807, 1)));
		for (WebElement result : results3){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.xpath(excel.getData(3, 2115, 1)));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Bulk Update, when we click 5");
		}
		wb.close();
		 Thread.sleep(3000);
		  
	}
	
	@Test(enabled=false,priority=17)
	public void Bulk_Update_edit_menu_Item_Price_SingleServing_Size(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		for(int i = 0; i <= 20; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		Thread.sleep(3000);
		//Click the pagination option as 20
		driver.findElement(By.xpath(excel.getData(3, 1809, 1))).click();
		Thread.sleep(3000);
		for(int i = 1; i <= 20; i++)
		{
			List<WebElement> inp = driver.findElements(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]/div/input"));
			if(inp.size() == 1)
			{
				//Get the Menu Item name from Bulk update page
				String mname = driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[1]")).getText();

				//Click the Price field of 3rd Row
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[4]/div/input")).click();
				//Get the Price of the Menu item, and store it into WebElement 
				WebElement amt = driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]/div[1]/input"));
				//Store the value of menu item as string
				String amtVal = amt.getAttribute("value");
				//Change the value of menu item from sting to double
				double changingAmt = Double.parseDouble(amtVal);
				//Add 1 to the menu item price
				changingAmt = changingAmt + 1;changingAmt = Math.round(changingAmt * 100D) / 100D;
				String change = new DecimalFormat("0.00").format(changingAmt);	
				
				//Clear the amount
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]/div[1]/input")).clear();
				
				//Enter the new amount
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]/div[1]/input")).sendKeys(change);
				Thread.sleep(10000);
				
				//Click the Update Menu Items Button
				driver.findElement(By.xpath(excel.getData(3, 2116, 1))).click();
				Thread.sleep(3000);
				
				//Check whether the menu item price is increase or not
				if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Updated successfully"))
				{
					test.log(LogStatus.PASS, "Menu Item Price Update Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Menu Item Price Update Fail");
				}
				Thread.sleep(5000);//span[contains(.,'Menu Item')]
				//Click the Menu Item tab Option
				driver.findElement(By.xpath(excel.getData(3, 2117, 1))).click();
				Thread.sleep(25000);
				
				//Clear the Search field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				//Enter the required menuitem
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(mname);
				//Click the Search Button
				driver.findElement(By.xpath(excel.getData(3, 2118, 1))).click();
				Thread.sleep(10000);
				
				//Replace all commo's with empty space
				String expected1 = amtVal;
				
				//Convert the String value of the Sale_Report_Net_Sale element into double value
				double expect1 = Double.parseDouble(expected1); expect1 = expect1+1;
				
				String actual = driver.findElement(By.xpath(excel.getData(3, 2119, 1))).getText().replaceAll("[a-zA-Z $ ₹ , :]", "").substring(1).toString();
				
				double actual1 = Double.parseDouble(actual);
				
				System.out.println("AMT in BULK UPDATE : "+changingAmt);
				
				System.out.println("AMT in MENU ITEM : "+actual1);
				
				if(changingAmt == actual1)
				{
					test.log(LogStatus.PASS, "Updated value, reflected in menu item Correctly, Current Price is : "+actual1);
				}
				else
				{
					double diff = expect1 - actual1;
					test.log(LogStatus.FAIL, "Updated value, reflected in menu item Wrongly, Difference is : "+diff);
				}
				break;
			}
		}
			wb.close();
			Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=18)
	public void Bulk_Update_edit_menu_Item_Price_MultipleServing_Size(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(4000);
		//Click the Bulk Update
		driver.findElement(By.xpath(excel.getData(3, 2120, 1))).click();
		Thread.sleep(3000);
		for(int i = 0; i <= 10; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		Thread.sleep(3000);
		//Click the pagination option as 20
		driver.findElement(By.xpath(excel.getData(3, 1809, 1))).click();
		Thread.sleep(3000);
		for(int i = 1; i <= 20; i++)
		{
			//Get the Menu Item name from Bulk update page
			String mname = driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[1]")).getText();

			List<WebElement> inp = driver.findElements(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]/div/input"));
			if(inp.size() > 1)
			{
				// FOR FIRST SERVING SIZE LEVEL
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]/div[1]/input")).click();
				//Clear the amount
				WebElement amt1 = driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]/div[1]/input"));
				String amtVal1 = amt1.getAttribute("value");
				double changingAmt1 = Double.parseDouble(amtVal1);
				changingAmt1 = changingAmt1 + 1;changingAmt1 = Math.round(changingAmt1 * 100D) / 100D;
				String change = new DecimalFormat("0.00").format(changingAmt1);
				
				//Enter the amount
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]/div[1]/input")).clear();
				
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]/div[1]/input")).sendKeys(change);
				Thread.sleep(10000);
				
				// FOR SECOND SERVING SIZE LEVEL
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]/div[2]/input")).click();
				//Clear the amount
				WebElement amt2 = driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]/div[2]/input"));
				String amtVal2 = amt2.getAttribute("value");
				double changingAmt2 = Double.parseDouble(amtVal2);
				changingAmt2 = changingAmt2 + 1;changingAmt2 = Math.round(changingAmt2 * 100D) / 100D;
				String change1 = new DecimalFormat("0.00").format(changingAmt2);
				
				//Enter the amount
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]/div[2]/input")).clear();
				
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr["+i+"]/td[4]/div[2]/input")).sendKeys(change1);
				Thread.sleep(10000);driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);Thread.sleep(3000);
				
				//Click the Update Menu Items Button
				driver.findElement(By.xpath(excel.getData(3, 2116, 1))).click();
				Thread.sleep(3000);
				
				//Check whether the menu item price is increase or not
				if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Updated successfully"))
				{
					test.log(LogStatus.PASS, "Menu Item Price Update Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Menu Item Price Update Fail");
				}
				Thread.sleep(5000);//span[contains(.,'Menu Item')]
				//Click the Menu Item tab Option
				driver.findElement(By.xpath(excel.getData(3, 2117, 1))).click();
				Thread.sleep(25000);
				
				//Clear the Search field
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).clear();
				//Enter the required menuitem
				driver.findElement(By.xpath(excel.getData(3, 40, 1))).sendKeys(mname);
				//Click the Search Button
				driver.findElement(By.xpath(excel.getData(3, 2118, 1))).click();
				Thread.sleep(10000);
				
				//Replace all commo's with empty space
				String expected12 = amtVal1;
				
				//Convert the String value of the Sale_Report_Net_Sale element into double value
				double expect12 = Double.parseDouble(expected12); expect12 = expect12 + 1;
				
				String actual2 = driver.findElement(By.xpath(excel.getData(3, 2121, 1))).getText().replaceAll("[a-zA-Z $ â‚¹ , :]", "").substring(1).toString();
				
				double actual12 = Double.parseDouble(actual2);
				
				if(expect12 == actual12)
				{
					test.log(LogStatus.PASS, "Updated value, reflected in menu item Correctly for 1st Serving Size Level, Current Price is : "+actual12);
				}
				else
				{
					double diff = expect12 - actual12;
					test.log(LogStatus.FAIL, "Updated value, reflected in menu item Wrongly for 1st Serving Size Level, Difference is : "+diff);
				}
			
			
				//Replace all commo's with empty space
				String expected13 = amtVal2;
				
				//Convert the String value of the Sale_Report_Net_Sale element into double value
				double expect13 = Double.parseDouble(expected13);  expect13 = expect13 + 1 ;
				
				String actual3 = driver.findElement(By.xpath(excel.getData(3, 2121, 1))).getText().replaceAll("[a-zA-Z $ â‚¹ , :]", "").substring(1).toString();
				
				double actual13 = Double.parseDouble(actual3);
				
				if(expect13 == actual13)
				{
					test.log(LogStatus.PASS, "Updated value, reflected in menu item Correctly for 2nd Serving Size Level, Current Price is : "+actual13);
				}
				else
				{
					double diff = expect13 - actual13;
					test.log(LogStatus.FAIL, "Updated value, reflected in menu item Wrongly for 2nd Serving Size Level, Difference is : "+diff);
				}
				
				break;
			}
		}wb.close();
		Thread.sleep(3000);

		
	}
	
	@Test(enabled=false,priority=19)
	public void Bulk_Update_edit_menu_Item_Price_Many_SingleServing_Size(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(4000);
		//Click the Bulk Update
		driver.findElement(By.xpath(excel.getData(3, 2120, 1))).click();
		Thread.sleep(3000);
		for(int i = 0; i <= 20; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		Thread.sleep(3000);


				//Click the Price field of 1st Row
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr[1]/td[4]/div/input")).click();
				//Get the Price of the Menu item, and store it into WebElement 
				WebElement amt = driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr[1]/td[4]/div[1]/input"));
				//Store the value of menu item as string
				String amtVal = amt.getAttribute("value");
				//Change the value of menu item from sting to double
				double changingAmt = Double.parseDouble(amtVal);
				//Add 1 to the menu item price
				changingAmt = changingAmt + 1; changingAmt = Math.round(changingAmt * 100D) / 100D;
				String change = new DecimalFormat("0.00").format(changingAmt);
				
				//Clear the amount
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr[1]/td[4]/div[1]/input")).clear();
				
				//Enter the new amount
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr[1]/td[4]/div[1]/input")).sendKeys(change);
				
				//Click the Price field of 2nd Row
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr[2]/td[4]/div/input")).click();
				//Get the Price of the Menu item, and store it into WebElement 
				WebElement amt2 = driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr[2]/td[4]/div[1]/input"));
				//Store the value of menu item as string
				String amtVal2 = amt2.getAttribute("value");
				//Change the value of menu item from sting to double
				double changingAmt2 = Double.parseDouble(amtVal2);
				//Add 1 to the menu item price
				changingAmt2 = changingAmt2 + 1;changingAmt2 = Math.round(changingAmt2 * 100D) / 100D;
				String change2 = new DecimalFormat("0.00").format(changingAmt2);
				
				//Clear the amount
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr[2]/td[4]/div[1]/input")).clear();
				
				//Enter the new amount
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr[2]/td[4]/div[1]/input")).sendKeys(change2);

				//Click the Price field of 3rd Row
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr[3]/td[4]/div/input")).click();
				//Get the Price of the Menu item, and store it into WebElement 
				WebElement amt3 = driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr[3]/td[4]/div[1]/input"));
				//Store the value of menu item as string
				String amtVal3 = amt3.getAttribute("value");
				//Change the value of menu item from sting to double
				double changingAmt3 = Double.parseDouble(amtVal3);
				//Add 1 to the menu item price
				changingAmt3 = changingAmt3 + 1;changingAmt3 = Math.round(changingAmt3 * 100D) / 100D;
				String change3 = new DecimalFormat("0.00").format(changingAmt3);
				
				//Clear the amount
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr[3]/td[4]/div[1]/input")).clear();
				
				//Enter the new amount
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr[3]/td[4]/div[1]/input")).sendKeys(change3);

				//Click the Price field of 4th Row
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr[4]/td[4]/div/input")).click();
				//Get the Price of the Menu item, and store it into WebElement 
				WebElement amt4 = driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr[4]/td[4]/div[1]/input"));
				//Store the value of menu item as string
				String amtVal4 = amt4.getAttribute("value");
				//Change the value of menu item from sting to double
				double changingAmt4 = Double.parseDouble(amtVal4);
				//Add 1 to the menu item price
				changingAmt4 = changingAmt4 + 1;changingAmt4 = Math.round(changingAmt4 * 100D) / 100D;
				String change4 = new DecimalFormat("0.00").format(changingAmt4);
				
				//Clear the amount
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr[4]/td[4]/div[1]/input")).clear();
				
				//Enter the new amount
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr[4]/td[4]/div[1]/input")).sendKeys(change4);

				//Click the Price field of 3rd Row
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr[5]/td[4]/div/input")).click();
				//Get the Price of the Menu item, and store it into WebElement 
				WebElement amt5 = driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr[5]/td[4]/div[1]/input"));
				//Store the value of menu item as string
				String amtVal5 = amt5.getAttribute("value");
				//Change the value of menu item from sting to double
				double changingAmt5 = Double.parseDouble(amtVal5);
				//Add 1 to the menu item price
				changingAmt5 = changingAmt5 + 1;changingAmt5 = Math.round(changingAmt5 * 100D) / 100D;
				String change5 = new DecimalFormat("0.00").format(changingAmt5);
				
				//Clear the amount
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr[5]/td[4]/div[1]/input")).clear();
				
				//Enter the new amount
				driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr[5]/td[4]/div[1]/input")).sendKeys(change5);
				
				
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				Thread.sleep(10000);
				
				//Click the Update Menu Items Button
				driver.findElement(By.xpath(excel.getData(3, 2116, 1))).click();
				Thread.sleep(3000);
				
				//Check whether the menu item price is increase or not
				if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Updated successfully"))
				{
					test.log(LogStatus.PASS, "Menu Item Price Update Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Menu Item Price Update Fail");
				}
				Thread.sleep(5000);//span[contains(.,'Menu Item')]
				//Click the Menu Item tab Option
				driver.findElement(By.xpath(excel.getData(3, 2117, 1))).click();
				Thread.sleep(25000);

				
				//Replace all commo's with empty space
				String expected1 = amtVal;
				
				//Convert the String value of the Sale_Report_Net_Sale element into double value
				double expect1 = Double.parseDouble(expected1); expect1 = expect1+1;
				
				String actual = driver.findElement(By.xpath("//tr[1]/td/div/div/span")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "").substring(1).toString();
				
				double actual1 = Double.parseDouble(actual);
				
				System.out.println("AMT in BULK UPDATE : "+changingAmt);
				
				System.out.println("AMT in MENU ITEM : "+actual1);
				
				if(changingAmt == actual1)
				{
					test.log(LogStatus.PASS, "Updated value, reflected in menu item Correctly, Current Price is : "+actual1);
				}
				else
				{
					double diff = expect1 - actual1;
					test.log(LogStatus.FAIL, "Updated value, reflected in menu item Wrongly, Difference is : "+diff);
				}
				
				//Replace all commo's with empty space
				String expected2 = amtVal2;
				
				//Convert the String value of the Sale_Report_Net_Sale element into double value
				double expect12 = Double.parseDouble(expected2); expect12 = expect12+1;
				
				String actual2 = driver.findElement(By.xpath("//tr[2]/td/div/div/span")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "").substring(1).toString();
				
				double actual12 = Double.parseDouble(actual2);
				
				System.out.println("AMT in BULK UPDATE : "+changingAmt2);
				
				System.out.println("AMT in MENU ITEM : "+actual12);
				
				if(changingAmt2 == actual12)
				{
					test.log(LogStatus.PASS, "Updated value, reflected in menu item Correctly, Current Price is : "+actual12);
				}
				else
				{
					double diff = expect12 - actual12;
					test.log(LogStatus.FAIL, "Updated value, reflected in menu item Wrongly, Difference is : "+diff);
				}
				
				//Replace all commo's with empty space
				String expected13 = amtVal3;
				
				//Convert the String value of the Sale_Report_Net_Sale element into double value
				double expect13 = Double.parseDouble(expected13); expect13 = expect13+1;
				
				String actual3 = driver.findElement(By.xpath("//tr[3]/td/div/div/span")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "").substring(1).toString();
				
				double actual13 = Double.parseDouble(actual3);
				
				System.out.println("AMT in BULK UPDATE : "+changingAmt3);
				
				System.out.println("AMT in MENU ITEM : "+actual13);
				
				if(changingAmt3 == actual13)
				{
					test.log(LogStatus.PASS, "Updated value, reflected in menu item Correctly, Current Price is : "+actual13);
				}
				else
				{
					double diff = expect13 - actual13;
					test.log(LogStatus.FAIL, "Updated value, reflected in menu item Wrongly, Difference is : "+diff);
				}
				
				//Replace all commo's with empty space
				String expected14 = amtVal4;
				
				//Convert the String value of the Sale_Report_Net_Sale element into double value
				double expect14 = Double.parseDouble(expected14); expect14 = expect14+1;
				
				String actual4 = driver.findElement(By.xpath("//tr[4]/td/div/div/span")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "").substring(1).toString();
				
				double actual14 = Double.parseDouble(actual4);
				
				System.out.println("AMT in BULK UPDATE : "+changingAmt4);
				
				System.out.println("AMT in MENU ITEM : "+actual14);
				
				if(changingAmt4 == actual14)
				{
					test.log(LogStatus.PASS, "Updated value, reflected in menu item Correctly, Current Price is : "+actual14);
				}
				else
				{
					double diff = expect14 - actual14;
					test.log(LogStatus.FAIL, "Updated value, reflected in menu item Wrongly, Difference is : "+diff);
				}
				
				//Replace all commo's with empty space
				String expected15 = amtVal5;
				
				//Convert the String value of the Sale_Report_Net_Sale element into double value
				double expect15 = Double.parseDouble(expected15); expect15 = expect15+1;
				
				String actual5 = driver.findElement(By.xpath("//tr[5]/td/div/div/span")).getText().replaceAll("[a-zA-Z $ ₹ , :]", "").substring(1).toString();
				
				double actual15 = Double.parseDouble(actual5);
				
				System.out.println("AMT in BULK UPDATE : "+changingAmt5);
				
				System.out.println("AMT in MENU ITEM : "+actual15);
				
				if(changingAmt5 == actual15)
				{
					test.log(LogStatus.PASS, "Updated value, reflected in menu item Correctly, Current Price is : "+actual15);
				}
				else
				{
					double diff = expect15 - actual15;
					test.log(LogStatus.FAIL, "Updated value, reflected in menu item Wrongly, Difference is : "+diff);
				}
				wb.close();
				Thread.sleep(5000);
	}
}
