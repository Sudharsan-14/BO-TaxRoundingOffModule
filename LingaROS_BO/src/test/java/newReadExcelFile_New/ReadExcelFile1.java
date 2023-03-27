package newReadExcelFile_New;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile1 {
	
	public static void main(String args[]) throws Exception{
		
		
		//PRINT THE PARTICULAR SINGLE DATA AND FIND NUMBER OF ROWS
	
	File src = new File("E:\\Automation\\WebPOSData.xlsx");
	
	FileInputStream fis = new FileInputStream(src);
	
	XSSFWorkbook wb = new XSSFWorkbook(fis);

	XSSFSheet sheet1 = wb.getSheetAt(0);
	
	int rowCount = sheet1.getLastRowNum();
	
	System.out.println("Total Row Count is : "+rowCount);
	
	for(int i = 0; i < rowCount; i++)
	{
		String data = sheet1.getRow(i).getCell(0).getStringCellValue();
		
		System.out.println("The Required Data is : "+data);
	}
	
	wb.close();
	
}
	
}
