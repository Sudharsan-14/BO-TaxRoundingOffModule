package epicList_Chrome_Account_User;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

public class Browser_Admin_Login {

	
	
	
	@Test(priority=1)
	public void Linga_login(WebDriver driver,ExtentTest test) throws Exception
	{			
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try
		{
			if(driver.findElement(By.xpath("//div[@id='popmake-26044']/button")).isDisplayed())
			{Thread.sleep(1000);
				driver.findElement(By.xpath("//div[@id='popmake-26044']/button")).click();
			}
		}
		catch(Exception e)
		{}
		try
		{
			if(driver.findElement(By.xpath("//a[.='Accept']")).isDisplayed())
			{
				driver.findElement(By.xpath("//a[.='Accept']")).click();
			}
		}
		catch(Exception e)
		{}
		//Clear the text from the user name text box
		driver.findElement(By.name("emailId")).clear();
		//Enter the user name
		driver.findElement(By.name("emailId")).sendKeys(Utility.getProperty("userName_Account_User"));
		//Clear the password from the password text box
		driver.findElement(By.name("password")).clear();
		//Enter the password
		driver.findElement(By.name("password")).sendKeys(Utility.getProperty("password"));
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
	
		Thread.sleep(3000);
		//Click the login button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
		Thread.sleep(10000);
		//Click the Accounts
		driver.findElement(By.xpath("//a[contains(.,'Accounts ')]")).click();
		
		Thread.sleep(20000);
		//Clear the Search box
		driver.findElement(By.xpath("//input[@ng-model='searchText.plan']")).clear();
		//Enter the Store to Serach
		driver.findElement(By.xpath("//input[@ng-model='searchText.plan']")).sendKeys(Utility.getProperty("Store1"));
		
		Thread.sleep(1000);
		//Click the Store
		driver.findElement(By.xpath("//table[@ng-table='tableParams']/tbody/tr/td[1]/a")).click();
		
		
		String parent1=driver.getWindowHandle();
		Set<String> childWins1=driver.getWindowHandles();
		
		for(String child1:childWins1)
		{
			if(!parent1.equals(child1))
			{
				driver.switchTo().window(child1);
			}
		}
		
		
		
		
		
		//Click the Login button
		driver.findElement(By.xpath("//a[contains(.,' Login')]")).click();
		
		
		String parent2=driver.getWindowHandle();
		Set<String> childWins2=driver.getWindowHandles();
		
		for(String child2:childWins2)
		{
			if(!parent1.equals(child2))
			{
				driver.switchTo().window(child2);
			}
		}
		
		
		Thread.sleep(10000);
		//Enter the Store
	//	driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("Burn By Rocky Patel ATL");
		
		//Click the Entered store Dashboard page
	//	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[4]/div/div/div[2]/div/div[1]/a/h5")).click();
		
		
		Thread.sleep(3000);
		
		
		
		
	}
}
