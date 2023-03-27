package newReadExcelFile_New;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForMandatoryModifier {
	
	public WebDriver driver;
	
	

	public void mandatoryModifier() throws Exception
	{
		File src = new File("E:\\Automation\\WebPOSData.xlsx");
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(0);
					
		int rowCount = sheet1.getLastRowNum();
		


		ExcelDataConfig excel = new ExcelDataConfig("E:\\Automation\\WebPOSData.xlsx");
		for(int i = 1; i < rowCount; i++)
			{	
			//FOR MANDATORY MODIFIRES
			if(!excel.getData(0, i, 20).equals("Not Available"))
				{
			
					if(excel.getData(0, i, 20).equals("Not Available")&&excel.getData(0, i, 31).equals("Not Available")&&excel.getData(0, i, 42).equals("Not Available")&&excel.getData(0, i, 53).equals("Not Available")&&excel.getData(0, i, 64).equals("Not Available"))
					{
						//Click the OPTIONAL MODIFIER
						
					}
					if(excel.getData(0, i, 20).equals("Not Available"))
					{
						
					}
					else
					{
						
						if(excel.getNumericData(0, i, 75)==1)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 22).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
							}
						}
						
						if(excel.getNumericData(0, i, 75)==2)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 22).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 22).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
							}
						}
							
						if(excel.getNumericData(0, i, 75)==3)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 22).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 22).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 22).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
							}
						}
						
						if(excel.getNumericData(0, i, 75)==4)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 22).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 24).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 26).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 27)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 28).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 28)+"']")).click();
							}
						}
						
						if(excel.getNumericData(0, i, 75)==5)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 21)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 22).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 22)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 23)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 24).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 24)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 25)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 26).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 26)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 27)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 28).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 28)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 29)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 30).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 30)+"']")).click();
							}
						}
							
							

					}
				
					if(excel.getData(0, i, 31).equals("Not Available"))
					{
						
					}
					else
					{

						
						if(excel.getNumericData(0, i, 76)==1)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 33).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
							}
						}
						
						if(excel.getNumericData(0, i, 76)==2)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 33).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 35).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
							}
						}
							
						if(excel.getNumericData(0, i, 76)==3)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 33).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 35).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 37).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
							}
						}
						
						if(excel.getNumericData(0, i, 76)==4)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 33).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 35).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 37).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
							}
													
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 39).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
							}
						}
						
						if(excel.getNumericData(0, i, 76)==5)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 32)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 33).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 33)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 34)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 35).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 35)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 36)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 37).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 37)+"']")).click();
							}
													
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 38)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 39).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 39)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 40)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 41).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 41)+"']")).click();
							}
						}

					}
					
					if(excel.getData(0, i, 42).equals("Not Available"))
					{
						
					}
					else
					{

						if(excel.getNumericData(0, i, 77)==1)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 44).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
							}
						}
						
						if(excel.getNumericData(0, i, 77)==2)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 44).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 46).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
							}
						}
							
						if(excel.getNumericData(0, i, 77)==3)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 44).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 46).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 47)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 48).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 48)+"']")).click();
							}
						}
						
						if(excel.getNumericData(0, i, 77)==4)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 44).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 46).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 47)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 48).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 48)+"']")).click();
							}
													
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 49)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 50).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 50)+"']")).click();
							}
						}
						
						if(excel.getNumericData(0, i, 77)==5)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 43)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 44).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 44)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 45)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 46).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 46)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 47)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 48).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 48)+"']")).click();
							}
													
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 49)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 50).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 50)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 51)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 52).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 52)+"']")).click();
							}
						}

					
					}
					
					if(excel.getData(0, i, 53).equals("Not Available"))
					{
						
					}
					else
					{


						if(excel.getNumericData(0, i, 78)==1)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 55).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
							}
						}
						
						if(excel.getNumericData(0, i, 78)==2)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 55).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 57).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
							}
						}
							
						if(excel.getNumericData(0, i, 78)==3)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 55).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 57).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 58)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 59).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 59)+"']")).click();
							}
						}
						
						if(excel.getNumericData(0, i, 78)==4)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 55).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 57).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 58)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 59).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 59)+"']")).click();
							}
													
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 60)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 61).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 61)+"']")).click();
							}
						}
						
						if(excel.getNumericData(0, i, 78)==5)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 54)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 55).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 55)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 56)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 57).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 57)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 58)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 59).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 59)+"']")).click();
							}
													
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 60)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 61).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 61)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 62)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 63).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 63)+"']")).click();
							}
						}

					}
					
					if(excel.getData(0, i, 64).equals("Not Available"))
					{
						
					}
					else
					{



						if(excel.getNumericData(0, i, 79)==1)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 66).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
							}
						}
						
						if(excel.getNumericData(0, i, 79)==2)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 66).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 68).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
							}
						}
							
						if(excel.getNumericData(0, i, 79)==3)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 66).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 68).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 69)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 70).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 70)+"']")).click();
							}
						}
						
						if(excel.getNumericData(0, i, 79)==4)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 66).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 68).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 69)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 70).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 70)+"']")).click();
							}
													
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 71)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 72).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 72)+"']")).click();
							}
						}
						
						if(excel.getNumericData(0, i, 79)==5)
						{
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 65)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 66).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 66)+"']")).click();
							}
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 67)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 68).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 68)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 69)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 70).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 70)+"']")).click();
							}
													
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 71)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 72).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 72)+"']")).click();
							}
						
							
							//Click the modifier 
							driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 73)+"']")).click();
							
					
							Thread.sleep(1000);
							//Check whether the prefix is available or not
							if(excel.getData(0, i, 74).equals("Not Available"))
							{

							}
							else
							{
								//Click the modifier - Prefix
								driver.findElement(By.xpath("//div[.='"+excel.getData(0, i, 74)+"']")).click();
							}
						}

					
					}
				
			}

		}
	wb.close();
	}
	
}
