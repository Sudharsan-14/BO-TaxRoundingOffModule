package epicList_Chrome_Account_User;

import java.io.File;import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.jsoup.Connection.Base;
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
		catch(Exception e)
		{

			System.out.println("Exception while taking screenshot "+e.getMessage());
			return e.getMessage();
		} 
	}
//	test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test Failed");
	public static String capture(WebDriver driver) throws IOException {
	File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	File Dest = new File("src/../ErrImages/" + System.currentTimeMillis()
	+ ".png");
	String errflpath = Dest.getAbsolutePath();
	FileUtils.copyFile(scrFile, Dest);
	return errflpath;
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
	
	public static String getReportPropertyUser(String ProertyName) throws Exception {
		Properties prop = new Properties();
		InputStream is = null;

		try {
			is = new FileInputStream("./report_Data1.properties");
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