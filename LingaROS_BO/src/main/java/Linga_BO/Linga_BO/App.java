package Linga_BO.Linga_BO;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	long start = System.currentTimeMillis();
    	long finish = System.currentTimeMillis();
		long totalTime = finish- start; 
		System.out.println("Time in Millisecomds : "+totalTime);
		double ActualfinishTime = TimeUnit.MILLISECONDS.toMinutes(totalTime);
//		long ActualfinishTimeDouble = ((totalTime%86400)%3600)/60;
		long ActualfinishTimeDouble1 = (totalTime/1000)/6;
		System.out.println("MilSEc to Mins basic conversion : "+ActualfinishTimeDouble1);
//		test.log(LogStatus.INFO, "Total Time : "+ActualfinishTimeDouble1);
		test.log(LogStatus.INFO, "Time in Millisecomds : "+totalTime);
		
		WebDriverWait wait1=new WebDriverWait(driver, 30);
		WebElement ele11=driver.findElement(By.xpath(excel.getData(3, 704, 1)));
		if(!wait1.until(ExpectedConditions.visibilityOf(ele11)).isDisplayed())
    }
}
