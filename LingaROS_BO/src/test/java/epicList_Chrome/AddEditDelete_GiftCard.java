package epicList_Chrome;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import epicList_Chrome_Account_User.Utility;
import newReadExcelFile_New.ExcelDataConfig;


public class AddEditDelete_GiftCard {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete_GiftCard");

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
		{
			Browser a = new Browser();
			a.Logout(driver, test);
			
			/*if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
			{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath("//b[.='User Login']")).getText().equalsIgnoreCase("User Login"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully for Dealer App");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed for Dealer App");
			}
			Thread.sleep(3000);
			//Close the Browser
			driver.close();
		}
		else
		{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath("//div[@id='x-content-band-1']/div/div[2]/h2")).getText().equalsIgnoreCase("Account Login"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully LingaPOs");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed LingaPos");
			}
			Thread.sleep(3000);
			//Close the Browser
			driver.close();
		}*/
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
			Giftcard_method_openGiftCard(driver);
			Giftcard_method_refreshGiftCard(driver);
			Giftcard_method_addGiftCard(driver);
			Giftcard_method_editGiftCard(driver);
			Giftcard_method_deleteGiftCard(driver);
			Giftcard_method_cancelButton(driver);
			Giftcard_method_sameName(driver);
			Giftcard_method_recharge(driver);
			Thread.sleep(1500);
		}
		
		@Test(enabled=false,priority=41)
		public void Giftcard_method_openGiftCard(WebDriver driver)
		{
			ExcelDataConfig excel;
			try {
				excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"giftcard");
			Thread.sleep(8000);
			
			
			
			//Check Gift Cards page opened or not
			if(driver.findElement(By.xpath(excel.getData(5, 240, 1))).getText().equalsIgnoreCase("Gift Cards"))
			{
				test.log(LogStatus.PASS, "Gift Card page loaded Successfully");
				
				
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Gift Card page loaded Failed");
				
				String scnShot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s1="data:image/png;base64,"+scnShot1;
				test.log(LogStatus.INFO,test.addScreenCapture(s1));
			}
			
			Thread.sleep(5000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
		}
		
		@Test(enabled=false,priority=42)
		public void Giftcard_method_refreshGiftCard(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			//Click the refresh button
		//	driver.findElement(By.xpath(excel.getData(5, 148, 1))).click();
			Thread.sleep(5000);
			//Check Check based discount page opened or not
			if(driver.findElement(By.xpath(excel.getData(5, 240, 1))).getText().equalsIgnoreCase("Gift Cards"))
			//if(driver.findElement(By.xpath(excel.getData(5, 240, 1))).getText().equalsIgnoreCase("Gift Cards"))
			{
				test.log(LogStatus.PASS, "Gift Card page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Gift Card page refreshed Failed");
			}
			
			
			Thread.sleep(5000);
	
		}
		
		@Test(enabled=false,priority=44)
		public void Giftcard_method_addGiftCard(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			for (int i = 0; i < 10; i++) {
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
			Thread.sleep(3000);
			
			Thread.sleep(5000);
			//Click on the Add Gift Card option
			driver.findElement(By.id(excel.getData(5, 242, 2))).click();
			Thread.sleep(3000);
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(5, 244, 1))).getText().equalsIgnoreCase("New Gift Card"))
			{
				test.log(LogStatus.PASS, "New Gift Card form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Gift Card form loaded Failed");
			}
	
			
			//Clear the Name field
			driver.findElement(By.name(excel.getData(5, 243, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(5, 243, 3))).sendKeys(Utility.getProperty("GiftCard_Number"));
			Thread.sleep(2000);
			
			//Clear the number field
			driver.findElement(By.name(excel.getData(5, 245, 3))).clear();
			//Enter the required balance
			driver.findElement(By.name(excel.getData(5, 245, 3))).sendKeys("600");
			Thread.sleep(3000);
			try
			{
			//Select the Gift card payment method
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[3]/div/div[3]/input")).click();
			}
			catch(Exception e) {}
			Thread.sleep(4000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(5, 246, 1))).click();
			Thread.sleep(3500);
			//Check whether the new discount saved or not
			if(driver.findElement(By.xpath(excel.getData(5, 10, 1))).getText().equalsIgnoreCase("Gift Card Saved Successfully"))
			{
				test.log(LogStatus.PASS, "Gift Card Saved Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Gift Card Saved failed");
			}
	
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=45)
		public void Giftcard_method_editGiftCard(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(6000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("GiftCard_Number")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
	
			Thread.sleep(2000);
			//Click the Edit icon
			driver.findElement(By.cssSelector(excel.getData(5, 63, 4))).click();
			
			Thread.sleep(4000);
			//Clear the Name field
			driver.findElement(By.name(excel.getData(5, 243, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(5, 243, 3))).sendKeys(Utility.getProperty("GiftCard_Number")+"1");
			
			Thread.sleep(3000);
			try
			{
			//Select the Gift card payment method
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[3]/div/div[3]/input")).click();
			}
			catch(Exception e) {}
	
			Thread.sleep(4000);
			//Click the update
			driver.findElement(By.xpath(excel.getData(5, 246, 1))).click();
			Thread.sleep(5000);
			//Check whether the Product Item updated successfully or not
			if(driver.findElement(By.xpath(excel.getData(5, 249, 1))).getText().equalsIgnoreCase("Gift card updated successfully"))
			{
				test.log(LogStatus.PASS, "Gift card is updated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Gift card is updated Failed");
			}
	
			Thread.sleep(5000);
	
		}
		
		@Test(enabled=false,priority=46)
		public void Giftcard_method_deleteGiftCard(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("GiftCard_Number")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
	
			Thread.sleep(2000);
			//Click the Delete button
			driver.findElement(By.cssSelector(excel.getData(5, 51, 4))).click();
			//Click the Yes button in the popup
			Thread.sleep(3000);
			driver.findElement(By.cssSelector(excel.getData(5, 69, 4))).click();
			Thread.sleep(5000);
			//Check the menu item deleted or not
			if(driver.findElement(By.xpath(excel.getData(5, 10, 1))).getText().equalsIgnoreCase("Gift card inactivated successfully"))
			{
				test.log(LogStatus.PASS, "Gift card deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Gift card deleted Failed");
			}
	
			Thread.sleep(7000);
			
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(5, 247, 1))).click();
			Thread.sleep(7000);		
			//Click the success button
			driver.findElement(By.cssSelector(excel.getData(5, 69, 4))).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector(excel.getData(5, 69, 4))).click();
			Thread.sleep(3500);
			
			//Check the field activated or not
			if(driver.findElement(By.xpath(excel.getData(5, 10, 1))).getText().equalsIgnoreCase("Gift card activated successfully"))
			{
				test.log(LogStatus.PASS, "Gift card is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Gift card is activated Failed");
			}
			Thread.sleep(3000);	
			
		}
	
		@Test(enabled=false,priority=47)
		public void Giftcard_method_cancelButton(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	     Thread.sleep(5000);
			
			//Click the Active Button
			driver.findElement(By.xpath(excel.getData(5, 247, 1))).click();
			
			Thread.sleep(5000);
			//Click on the Add Gift Card option
			driver.findElement(By.id(excel.getData(5, 242, 2))).click();
			Thread.sleep(3000);
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(5, 244, 1))).getText().equalsIgnoreCase("New Gift Card"))
			{
				test.log(LogStatus.PASS, "New Gift Card form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Gift Card form loaded Failed");
			}
	
			
			//Clear the Name field
			driver.findElement(By.name(excel.getData(5, 243, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(5, 243, 3))).sendKeys(Utility.getProperty("GiftCard_Number"));
			Thread.sleep(2000);
			
			//Click the close button
			driver.findElement(By.xpath(excel.getData(5, 250, 1))).click();
			Thread.sleep(2000);
			//Check whether the new discount saved or not
			if(driver.findElement(By.id(excel.getData(5, 242, 2))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Gift Card canceled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Gift Card canceled failed");
			}
	
			Thread.sleep(5000);
		}
			
		@Test(enabled=false,priority=48)
		public void Giftcard_method_sameName(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
			Thread.sleep(5000);
			//Click on the Add Gift Card option
			driver.findElement(By.id(excel.getData(5, 242, 2))).click();
			Thread.sleep(3000);
			
			//Clear the Name field
			driver.findElement(By.name(excel.getData(5, 243, 3))).clear();
			//Enter the Name
			driver.findElement(By.name(excel.getData(5, 243, 3))).sendKeys(Utility.getProperty("GiftCard_Number")+"1");
			Thread.sleep(2000);
			
			//Clear the number field
			driver.findElement(By.name(excel.getData(5, 245, 3))).clear();
			//Enter the required balance
			driver.findElement(By.name(excel.getData(5, 245, 3))).sendKeys("600");
			
			Thread.sleep(3000);
			try
			{
			//Select the Gift card payment method
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[3]/div/div[3]/input")).click();
			}
			catch(Exception e) {}
			
			Thread.sleep(3000);
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(5, 246, 1))).click();
			Thread.sleep(1500);
			
			//Check whether the Product Item updated successfully or not
			if(driver.findElement(By.xpath(excel.getData(5, 248, 1))).getText().equalsIgnoreCase("Validation Error(s)"))
			{
				test.log(LogStatus.PASS, "Gift card is updated failed and show the error message");
			}
			else
			{
				test.log(LogStatus.FAIL, "Gift Card is updated successfully");
			}
	
			Thread.sleep(5000);
		//Click the close button
			driver.findElement(By.xpath(excel.getData(5, 250, 1))).click();
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=49)
		public void Giftcard_method_recharge(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
			//Enter the required keyword
			driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("GiftCard_Number")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
	
			Thread.sleep(2000);
			//Click the Recharge icon
			driver.findElement(By.cssSelector(excel.getData(5, 252, 4))).click();
	
			Thread.sleep(5000);
			//Clear the Recharge field
			driver.findElement(By.name(excel.getData(5, 251, 3))).clear();
			Thread.sleep(5000);
			//Enter the recharge amount
			driver.findElement(By.name(excel.getData(5, 251, 3))).sendKeys("5000");
			
			Thread.sleep(5000);
			try
			{
			//Select the Gift card payment method
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[3]/form/div[3]/div/div[3]/input")).click();
			}
			catch(Exception e) {}
			
			//Click the Save button
			driver.findElement(By.xpath(excel.getData(3, 2241, 1))).click();
			Thread.sleep(3500);
			
			//Check whether the recharge successfully or not
			if(driver.findElement(By.xpath(excel.getData(5, 254, 1))).getText().equalsIgnoreCase("Gift card Amount credited successfully"))
			{
				test.log(LogStatus.PASS, "Gift card is recharged successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Gift card is recharged failed");
			}
	
			Thread.sleep(5000);
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);
			//watchTutorial(driver);
			Thread.sleep(5000);}
		}
		@Test(priority=3, enabled=false)
		public void watchTutorial(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(2000);
			//Click the Watch Tutorial Option
			driver.findElement(By.xpath(excel.getData(5, 52, 1))).click();
			WebElement iframe = driver.findElement(By.xpath(excel.getData(5, 124, 1)));
			driver.switchTo().frame(iframe);
			
			
			Thread.sleep(3500);
			try
			{
				if(driver.findElement(By.xpath(excel.getData(5, 255, 1))).isDisplayed()&&driver.findElement(By.xpath(excel.getData(5, 126, 1))).isDisplayed())
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
