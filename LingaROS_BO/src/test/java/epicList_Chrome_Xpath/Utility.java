package epicList_Chrome_Xpath;

import java.io.File;import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility 
{
	public static String captureScreenshot(WebDriver driver,String screenshotName)
	{

		try 
		{
			TakesScreenshot ts=(TakesScreenshot)driver;

			File source=ts.getScreenshotAs(OutputType.FILE);


			FileUtils.copyFile(source, new File("res/"+screenshotName+".png"));
			return "res/"+screenshotName+".png";
			
		} 
		catch (Exception e)
		{

			System.out.println("Exception while taking screenshot "+e.getMessage());
			return e.getMessage();
		} 
	}

	public static String getProperty(String ProertyName) throws Exception {
		Properties prop = new Properties();
		InputStream is = null;

		try {
			is = new FileInputStream("./common_data.properties");
			prop.load(is);

			return prop.getProperty(ProertyName);

		} 
		catch(Throwable th)
		{
			System.out.println("Exception in loadConstantsProperties: "+th.getMessage());
			th.printStackTrace();
			return th.getMessage();
		} 

	}
	
	public static String getReportProperty(String ProertyName) throws Exception {
		Properties prop = new Properties();
		InputStream is = null;

		try {
			is = new FileInputStream("./report_Data.properties");
			prop.load(is);

			return prop.getProperty(ProertyName);

		} 
		catch(Throwable th)
		{
			System.out.println("Exception in loadConstantsProperties: "+th.getMessage());
			th.printStackTrace();
			return th.getMessage();
		} 

	}
	
	public static String getUnitConversion(String ProertyName) throws Exception {
		Properties prop = new Properties();
		InputStream is = null;

		try {
			is = new FileInputStream("./Unit_Conversion.properties");
			prop.load(is);

			return prop.getProperty(ProertyName);

		} 
		catch(Throwable th)
		{
			System.out.println("Exception in loadConstantsProperties: "+th.getMessage());
			th.printStackTrace();
			return th.getMessage();
		} 

	}
	
/*	public static String getURLProperty(String ProertyName) throws Exception {
		Properties prop = new Properties();
		InputStream is = null;

		try {
			is = new FileInputStream("./url_File.properties");
			prop.load(is);

			return prop.getProperty(ProertyName);

		} 
		catch(Throwable th)
		{
			System.out.println("Exception in loadConstantsProperties: "+th.getMessage());
			th.printStackTrace();
			return th.getMessage();
		} 

	}*/
	
	
	public static String Datetime() {
		Date dNow = new Date( );
	      SimpleDateFormat ft = new SimpleDateFormat ("MM.dd.hh.mm");
	      return ft.format(dNow).toString();
	}
	
	public static String uno() {
		Date dNow = new Date( );
	      SimpleDateFormat ft = new SimpleDateFormat ("MMddhhmmss");
	      return ft.format(dNow).toString();
	}

}