package newReadExcelFile_New;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile3 {
	
//READ ALL DATA(BOTH ROW AND COLUMN)


		public static void main(String args[]) throws Exception{
			

			
			File src = new File("E:\\Automation\\WebPOSData.xlsx");
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(0);
						
			int rowCount = sheet1.getLastRowNum();
			
			int columnCount = sheet1.getRow(0).getLastCellNum();
			
			System.out.println("Total Row Count is : "+rowCount);
			
			System.out.println("Total Column is : "+columnCount);
			
			for(int i = 1; i < rowCount; i++)
			{
				
				for(int j = 0 ; j < columnCount; j++)
				{

					String data = sheet1.getRow(i).getCell(j).getStringCellValue();
					
					System.out.println(i +" row and "+ j + " column have "+ data);
					
				}

			}
			
			wb.close();
			

		}
}
