package newReadExcelFile_New;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {
	
	public static void main(String args[]) throws Exception{
		
		//PRINT THE PARTICULAR SINGLE DATA
	
	File src = new File("E:\\Automation\\WebPOSData.xlsx");
	
	FileInputStream fis = new FileInputStream(src);
	
	XSSFWorkbook wb = new XSSFWorkbook(fis);

	XSSFSheet sheet1 = wb.getSheetAt(0);
	
	String data = sheet1.getRow(0).getCell(0).getStringCellValue();
	
	System.out.println("The Required Data is : "+data);
	
	wb.close();
	
}
	
}
