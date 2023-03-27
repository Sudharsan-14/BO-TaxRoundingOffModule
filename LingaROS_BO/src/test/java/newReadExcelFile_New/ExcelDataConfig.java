package newReadExcelFile_New;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {
	
	XSSFWorkbook wb;
	
	XSSFSheet sheet1,sheet2,sheet3,sheet4,sheet5,sheet6,sheet7,sheet8,sheet9,sheet10;
	
	public ExcelDataConfig(String excelPath)
	{
		try {
			File src = new File(excelPath);
			
			FileInputStream fis = new FileInputStream(src);
			
			wb = new XSSFWorkbook(fis);
			
			
		} 
		
		catch (Exception e) {

			System.out.println(e.getMessage());

		}
		
	}
	
	public String getData(int sheetNumber, int row, int column)
	{

		sheet1 = wb.getSheetAt(sheetNumber);
		
		String data = sheet1.getRow(row).getCell(column).getStringCellValue();
		
				
		return data;

	}
	
	public Double getNumericData(int sheetNumber, int row, int column)
	{

		sheet1 = wb.getSheetAt(sheetNumber);
		
		Double data = (Double) sheet1.getRow(row).getCell(column).getNumericCellValue();
		
				
		return data;

	}

}
