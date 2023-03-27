package epicList_Chrome_Xpath;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
public static ExtentReports extent;
public static ExtentReports getInstance()
{
	if(extent == null)
	{
		extent = new ExtentReports("./reports.html", true);
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ExtentConfig.xml"));
	}
	return extent;
}
}
