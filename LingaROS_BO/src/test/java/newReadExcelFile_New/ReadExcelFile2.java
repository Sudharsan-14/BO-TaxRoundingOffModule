package newReadExcelFile_New;

public class ReadExcelFile2 {
	

//PRINT THE PARTICULAR SHEET, ROW, COLUMN USING EXCEL DATA CONFIG FILE

		public static void main(String args[]) throws Exception{
			
			ExcelDataConfig excel = new ExcelDataConfig("E:\\Automation\\WebPOSData.xlsx");
			
			
			System.out.println(excel.getData(0, 3, 5));
			
			//System.out.println(excel.getNumericData(0, 3, 5));
		}
}
