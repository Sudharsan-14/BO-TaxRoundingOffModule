package epicList_Chrome_Xpath;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Test1 {
	@Test
public void tests() throws Exception
{
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Clear All Previous Results");
	
	test.log(LogStatus.INFO, "Clear All Previous Results");




rep.endTest(test);
rep.flush();

SendMail.snedMailWithAttachment();
}
}
