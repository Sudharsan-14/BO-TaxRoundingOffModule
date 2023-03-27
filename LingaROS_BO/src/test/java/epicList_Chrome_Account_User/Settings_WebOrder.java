package epicList_Chrome_Account_User;
	import java.util.List;
	import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
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

	public class Settings_WebOrder { 


			public WebDriver driver;
			
			ExtentReports rep = ExtentManager.getInstance();
			ExtentTest test = rep.startTest("Settings_Web_Order_settings");
			
			float unknownValue = 00;
			
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
						Browser_Account_Level_User a = new Browser_Account_Level_User();
						a.UPOS_login(driver, test);
					}
					else 			
					{
						Browser_Account_Level_User a = new Browser_Account_Level_User();
						a.Linga_login(driver, test);
					}	
				}
					
				@Test(priority=500)
				public void logout() throws Exception
				{
					Browser_Account_Level_User a = new Browser_Account_Level_User();
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
		            Web_Order_Method_open(driver);
		            Web_Order_Method_Zenpepper(driver);
		            Web_Order_Method_WebOrder(driver);
		            Web_Order_Method_Settings(driver);
		            Web_Order_Method_Batch(driver);
		            Web_Order_Method_Stores(driver);
		           // Web_Order_Method_User(driver);
		            Web_Order_Method_NavigateLinga(driver);
		          
					Thread.sleep(1500);
				}

				@Test(enabled=false,priority=28)
				public void  Web_Order_Method_open(WebDriver driver2) throws Exception {
					// TODO Auto-generated method stub
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					//Enter the URL
					driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"saleDashboard");
					Thread.sleep(2000);
					//Click on Web order tab
					driver.findElement(By.xpath(excel.getData(5, 612, 1))).click();
					Thread.sleep(3000);
					
					 String url = "https://admin.zenpepper.com/#/account/stores";
				     // driver.get(url);
				      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				      // get current URL and print
				      String strUrl = driver.getCurrentUrl();
				      System.out.println("Current Url is:"+ strUrl);
				      try
				      {
				//Check weather the web order navigated to correct page or not
					if(url.equals(strUrl))
					{
						test.log(LogStatus.PASS, "Web Order navigated to correct page");
				
						String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
						String s="data:image/png;base64,"+scnShot;
						test.log(LogStatus.INFO,test.addScreenCapture(s));
					}
					else
					{
						test.log(LogStatus.FAIL, "Web order navigated to incorrect page");
					
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

				}
				
				public void Web_Order_Method_Zenpepper(WebDriver driver) throws Exception
				{
					ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
					//Click on Account tab in zenpepper
					driver.findElement(By.xpath(excel.getData(5, 613, 1))).click();
					
					//driver.findElement(By.xpath("//div[@name='accountImage']")).click();
				//	driver.findElement(By.xpath("//div/div[1]/div/div/p[@class='store-image-txt']")).sendKeys(Utility.getProperty("DisplayGroupImage1"));
			    //Account Name
				driver.findElement(By.xpath(excel.getData(5, 616, 1))).clear();
				driver.findElement(By.xpath(excel.getData(5, 616, 1))).sendKeys("lingak@mail.com");
				//Banner Description 
				driver.findElement(By.xpath(excel.getData(5, 617, 1))).click();
				driver.findElement(By.xpath(excel.getData(5, 617, 1))).sendKeys("desription");
				//Company Enable or disable
				if(driver.findElement(By.xpath(excel.getData(5, 618, 1))).isSelected()) 
				{
					driver.findElement(By.xpath(excel.getData(5, 618, 1))).click();
					 // Switching to Alert        
			        Alert alert = driver.switchTo().alert();		
			        		
			        // Capturing alert message.    
			        String alertMessage= driver.switchTo().alert().getText();		
			        		
			        // Displaying alert message		
			        System.out.println(alertMessage);	
			        Thread.sleep(5000);
			        		
			        // Accepting alert		
			        alert.accept();	
					 
				}
				//Future Order Enabled service
				driver.findElement(By.xpath(excel.getData(5, 619, 1))).click();
				//Enable or Disable ASAP in Service
				driver.findElement(By.xpath(excel.getData(5, 620, 1))).click();
				//Social Authentication for Facebook
				driver.findElement(By.xpath(excel.getData(5, 621, 1))).click();
				//Social Authentication for Google
				driver.findElement(By.xpath(excel.getData(5, 622, 1))).click();
				//Menu Layout default
				driver.findElement(By.xpath(excel.getData(5, 623, 1))).click();
		        //Large Image Check box
				driver.findElement(By.xpath(excel.getData(5, 624, 1))).click();
				//Small Image Check Box
				driver.findElement(By.xpath(excel.getData(5, 625, 1))).click();
				//No Image Check Box
				driver.findElement(By.xpath(excel.getData(5, 626, 1))).click();
				
				//Check the box for Play store
			if(	driver.findElement(By.xpath(excel.getData(5, 627, 1))).isSelected())
			{
			driver.findElement(By.xpath(excel.getData(5, 627, 1))).click();
			}	
				else {
					driver.findElement(By.xpath(excel.getData(5, 627, 1))).click();
					driver.findElement(By.xpath(excel.getData(5, 628, 1))).sendKeys("http://www.play.google.com");
				}
			
				//AppleStore Check box
				if(driver.findElement(By.xpath(excel.getData(5, 629, 1))).isSelected())
				{
					driver.findElement(By.xpath(excel.getData(5, 629, 1))).click();
				}
				else {
					driver.findElement(By.xpath(excel.getData(5, 629, 1))).click();
					driver.findElement(By.xpath(excel.getData(5, 630, 1))).sendKeys("https://www.appstore.com");
				}
				
				driver.findElement(By.xpath(excel.getData(5, 631, 1))).click();
				driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
				Thread.sleep(1000);
				if(driver.findElement(By.xpath(excel.getData(5, 614, 1))).getText().equalsIgnoreCase("Account details are updated successfully"))
				{
					test.log(LogStatus.PASS, "Account details are updated successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Account details are failed");
				}
				Thread.sleep(3000);
				}					
				
			
			public void  Web_Order_Method_WebOrder(WebDriver driver) throws Exception
			{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//Click on Web Url
				driver.findElement(By.xpath(excel.getData(5, 632, 1))).click();
				//Clear the url field
				driver.findElement(By.xpath(excel.getData(5, 633, 1))).clear();
				driver.findElement(By.xpath(excel.getData(5, 633, 1))).sendKeys("lingaPos");
				driver.findElement(By.xpath(excel.getData(5, 633, 1))).clear();
				driver.findElement(By.xpath(excel.getData(5, 633, 1))).sendKeys("lingabevo");
				//Click on launch
				driver.findElement(By.xpath(excel.getData(5, 634, 1))).click();
				driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
				Thread.sleep(1000);
				if(driver.findElement(By.xpath(excel.getData(5, 614, 1))).getText().equalsIgnoreCase("Website is launched successfully"))
				{
					test.log(LogStatus.PASS, "Website is launched successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Website is launched failed");
				}
				Thread.sleep(4000);
			}
			public void Web_Order_Method_Settings(WebDriver driver) throws Exception
			{	
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				//Click on Settings
				driver.findElement(By.xpath(excel.getData(5, 635, 1))).click();
				//Select the Theme as per your choice
				driver.findElement(By.xpath(excel.getData(5, 636, 1))).click();
				//Save Button
				
				driver.findElement(By.xpath(excel.getData(5, 637, 1))).click();
				driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
				Thread.sleep(2000);
				if(driver.findElement(By.xpath(excel.getData(5, 614, 1))).getText().equalsIgnoreCase("Theme has been changed"))
				{
					test.log(LogStatus.PASS, "Theme has been changed");
				}
				else
				{
					test.log(LogStatus.FAIL, "Theme has been Failed");
				}
				
			}
			public void  Web_Order_Method_Batch(WebDriver driver) throws Exception
			{	
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				driver.findElement(By.xpath(excel.getData(5, 638, 1))).click();
				driver.findElement(By.xpath(excel.getData(5, 639, 1))).click();
				Select store=new Select(driver.findElement(By.xpath(excel.getData(5, 640, 1))));
				store.selectByVisibleText("Linga_Auto_Test");
				Thread.sleep(1000);
				//store.deselectByVisibleText("Linga_Auto_Test");
				store.selectByIndex(1);
				driver.findElement(By.xpath(excel.getData(5, 148, 1))).click();
				driver.findElement(By.xpath(excel.getData(5, 641, 1))).clear();
				driver.findElement(By.xpath(excel.getData(5, 641, 1))).sendKeys("New");
				driver.findElement(By.xpath(excel.getData(5, 642, 1))).click();
				
			}
			public void Web_Order_Method_Stores(WebDriver driver) throws Exception
			{	
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				//Click on Stores Icon
				driver.findElement(By.xpath(excel.getData(5, 643, 1))).click();
				//search for required store 
				driver.findElement(By.xpath(excel.getData(5, 644, 1))).sendKeys("lingaw");
				driver.findElement(By.xpath(excel.getData(5, 645, 1))).click();
				//Clear the Restaurant Name 
				driver.findElement(By.xpath(excel.getData(5, 646, 1))).clear();
				//update the Restaurant Name
				driver.findElement(By.xpath(excel.getData(5, 646, 1))).sendKeys("lingaweb@mail.com");
				//Clear Description field
				driver.findElement(By.xpath(excel.getData(5, 647, 1))).click();
				//Enter the data into Description Field
				driver.findElement(By.xpath(excel.getData(5, 647, 1))).sendKeys("Store");
				//Clear Phone Number
				driver.findElement(By.xpath(excel.getData(5, 648, 1))).clear();
				//Enter the data into Phone Number
				driver.findElement(By.xpath(excel.getData(5, 648, 1))).sendKeys("+1-175-89245");
				//Clear the data for Notification Number
				driver.findElement(By.xpath(excel.getData(5, 649, 1))).clear();
				//Enter the data into Notification Number
				driver.findElement(By.xpath(excel.getData(5, 649, 1))).sendKeys("+12594895,+51582364");
				//check in or check out button for pending orders
				driver.findElement(By.xpath(excel.getData(5, 650, 1))).click();
				//check in or check out button for confirm Orders
				driver.findElement(By.xpath(excel.getData(5, 651, 1))).click();
				//check in or check out button for Cancelled orders
				driver.findElement(By.xpath(excel.getData(5, 652, 1))).click();
				
				//check in or check out button for Door Delivery
				if(driver.findElement(By.xpath(excel.getData(5, 653, 1))).isSelected())		
		
				{
					//Clear or Enter the data into minimum Delivery
					driver.findElement(By.xpath(excel.getData(5, 654, 1))).clear();
					driver.findElement(By.xpath(excel.getData(5, 654, 1))).sendKeys("100");
				}
				else {
					//check in or check out button for Door Delivery
					driver.findElement(By.xpath(excel.getData(5, 653, 1))).click();
					//Clear or Enter the data into minimum Delivery
					driver.findElement(By.xpath(excel.getData(5, 654, 1))).clear();
					driver.findElement(By.xpath(excel.getData(5, 654, 1))).sendKeys("100");
				}
		//	Check in or check out button for Pick up	
				driver.findElement(By.xpath(excel.getData(5, 655, 1))).click();
//				Check in or check out button for curbSide Pickup
				
				driver.findElement(By.xpath(excel.getData(5, 656, 1))).click();
				//Check in the charge Display Name
				if(driver.findElement(By.xpath(excel.getData(5, 657, 1))).isSelected())
				{
					driver.findElement(By.xpath(excel.getData(5, 657, 1))).click();
				}
				else {
					driver.findElement(By.xpath(excel.getData(5, 657, 1))).click();
					//Enter data into Charge Name
					driver.findElement(By.xpath(excel.getData(5, 658, 1))).sendKeys("Package");
					//Enter data into percentage

					driver.findElement(By.xpath(excel.getData(5, 659, 1))).sendKeys("100");
					//Enter data into Amount

					driver.findElement(By.xpath(excel.getData(5, 660, 1))).sendKeys("100");
				}
				
				//Check in or Check out for item Notes

				driver.findElement(By.id(excel.getData(5, 661, 2))).click();
				/*driver.findElement(By.id("onlinePayment")).click();
				Thread.sleep(2000);
				
				  Alert alert1 = driver.switchTo().alert();		
	        		
			        // Capturing alert message.    
			        String alertMessage= driver.switchTo().alert().getText();		
			        		
			        // Displaying alert message		
			        System.out.println(alertMessage);	
			        Thread.sleep(5000);
			        		
			        // Accepting alert		
			        alert1.accept();	*/
				
				//Check in or Check out for Cash Payment
				driver.findElement(By.id(excel.getData(5, 662, 2))).click();
				//driver.findElement(By.xpath("//input[@ng-model='storeCopy.addressLine1']")).clear();
				//save button
				driver.findElement(By.xpath(excel.getData(5, 615, 1))).click();
				Thread.sleep(1000);
				driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
				if(driver.findElement(By.xpath(excel.getData(5, 614, 1))).getText().equalsIgnoreCase("Restaurant details are updated successfully"))
				{
					test.log(LogStatus.PASS, "Restaurant details are updated successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Restaurant details are updated failed");
				}
				Thread.sleep(4000);
				//Click on Store Time 
				driver.findElement(By.xpath(excel.getData(5, 663, 1))).click();
				Thread.sleep(4000);
				/*WebElement element = driver.findElement(By.xpath("//select[@ng-model='storeCopy.timeZone']"));
				Select s = new Select(element);
				s.selectByVisibleText("(GMT-09:00) Alaska");
				driver.findElement(By.cssSelector("body.ng-scope:nth-child(2) div.ng-scope:nth-child(2) ui-view.ng-scope ui-view.ng-scope:nth-child(2) div.container.ng-scope div.row div.col-md-12 div.panel.panel-default div.panel-body div.col-md-8.col-md-offset-2 div.chosen-container.chosen-container-single:nth-child(3) a.chosen-single > span:nth-child(1)")).submit();
	            Select drpname = new  Select(driver.findElement(By.xpath("//select[@ng-model='storeCopy.timeZone']")));
	            driver.findElements(By.xpath("//body/div[1]/ui-view[1]/ui-view[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/select[1]/option[3]"));

	    drpname.getOptions();
	    drpname.getFirstSelectedOption();
	    drpname.selectByIndex(1);
	   
				driver.findElement(By.xpath("//body/div[1]/ui-view[1]/ui-view[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]")).click();
				driver.findElement(By.xpath("//body/div[1]/ui-view[1]/ui-view[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).click();
				driver.findElement(By.xpath("//body/div[1]/ui-view[1]/ui-view[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/select[1]/option[75]")).click();
				driver.findElement(By.xpath("//input[@class='chosen-search-input']")).sendKeys(Keys.ENTER);*/
				
				//click on Open close switch

				driver.findElement(By.xpath(excel.getData(5, 664, 1))).click();
				//Save Button
				driver.findElement(By.xpath(excel.getData(5, 665, 1))).click();
				//click on Open close switch
				driver.findElement(By.xpath(excel.getData(5, 664, 1))).click();
				
				//click on the from and to timings 

				driver.findElement(By.xpath(excel.getData(5, 666, 1))).click();
				//
				driver.findElement(By.xpath(excel.getData(5, 667, 1))).click();
				driver.findElement(By.xpath(excel.getData(5, 667, 1))).click();
				driver.findElement(By.xpath(excel.getData(5, 668, 1))).click();
				driver.findElement(By.xpath(excel.getData(5, 669, 1))).click();
				driver.findElement(By.xpath(excel.getData(5, 670, 1))).click();
				driver.findElement(By.xpath(excel.getData(5, 666, 1))).click();
				driver.findElement(By.xpath(excel.getData(5, 615, 1))).click();
				driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
				Thread.sleep(1000);
				if(driver.findElement(By.xpath(excel.getData(5, 614, 1))).getText().equalsIgnoreCase("Restaurant details are updated successfully"))
				{
					test.log(LogStatus.PASS, "Restaurant details are updated successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Restaurant details are updated failed");
				}
				//Click on delivery time
				driver.findElement(By.xpath(excel.getData(5, 671, 1))).click();
				//Click open and close switch
				if(	driver.findElement(By.xpath(excel.getData(5, 664, 1))).isSelected())
				{
					driver.findElement(By.xpath(excel.getData(5, 664, 1))).click();
				}
				else {
				driver.findElement(By.xpath(excel.getData(5, 664, 1))).click();
				
				//click on the from and to timings 

				driver.findElement(By.xpath(excel.getData(5, 666, 1))).click();
				//To increase the time in hours

				driver.findElement(By.xpath(excel.getData(5, 667, 1))).click();
				driver.findElement(By.xpath(excel.getData(5, 667, 1))).click();
				//To decrease the time in hours

				driver.findElement(By.xpath(excel.getData(5, 668, 1))).click();
				//To increase the time in minutes

				driver.findElement(By.xpath(excel.getData(5, 669, 1))).click();
				//To decrease the time in Minutes

				driver.findElement(By.xpath(excel.getData(5, 670, 1))).click();
				//click on the from and to timings
				
				driver.findElement(By.xpath(excel.getData(5, 666, 1))).click();
				}
				
				//Save Button
				driver.findElement(By.xpath(excel.getData(5, 615, 1))).click();
				driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
				Thread.sleep(1000);
				//To get the message
				if(driver.findElement(By.xpath(excel.getData(5, 614, 1))).getText().equalsIgnoreCase("Restaurant details are updated successfully"))
				{
					test.log(LogStatus.PASS, "Restaurant details are updated successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Restaurant details are updated failed");
				}
				//Click on Pick Up time
				driver.findElement(By.xpath(excel.getData(5, 672, 1))).click();
				//Click on Delivery zone

				driver.findElement(By.xpath(excel.getData(5, 673, 1))).click();
				driver.findElement(By.xpath(excel.getData(5, 673, 1))).click();
				//Click on Stores Icon
				driver.findElement(By.xpath(excel.getData(5, 674, 1))).click();
				
				}
			/*public void Web_Order_Method_User(WebDriver driver) throws Exception
			{
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//Click on Users
			driver.findElement(By.xpath("//a[@class='dropdown-toggle ng-binding']")).click();
			driver.findElement(By.xpath("//li[@ng-click='changePassword()']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='currentPassword']")).sendKeys("lingaweb1");
			driver.findElement(By.xpath("//input[@name='newPassword']")).sendKeys("lingaweb1");
			driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys("lingaweb1");
			driver.findElement(By.xpath(excel.getData(5, 631, 1))).click();
			
			driver.findElement(By.xpath("//a[@class='dropdown-toggle ng-binding']")).click();
			driver.findElement(By.xpath("//li[@ng-click='changePassword()']")).click();
			driver.findElement(By.xpath("//button[contains(.,'CANCEL')]")).click();
			
			driver.findElement(By.xpath("//a[@class='dropdown-toggle ng-binding']")).click();
			driver.findElement(By.xpath("//li[@ng-click='logout()']")).click();
			}*/
			
	public void Web_Order_Method_NavigateLinga(WebDriver driver) throws Exception
				{
	ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
	Thread.sleep(2000);
				driver.findElement(By.xpath(excel.getData(5, 675, 1))).click();
				Thread.sleep(3000);
				 String url = "http://mystore.lingapos.com/#/account/stores";
			     // driver.get(url);
			      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			      // get current URL and print
			      String strUrl = driver.getCurrentUrl();
			      
			//Check weather the web order navigated to correct page or not
				if(url.equals(strUrl))
				{
					test.log(LogStatus.PASS, "Linga Pos button navigated to correct page");
				}
				else
				{
					test.log(LogStatus.FAIL, "Linga Pos button navigated to incorrect page");
				}
				
				}
}
	