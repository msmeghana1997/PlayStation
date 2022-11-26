package base;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author TestYantra
 */
public class ExcelUtility {
	static String  value;
	private static  Workbook workbook;
	private static  Sheet sheet;
	private static FileInputStream file;

	/**
	 * Use this method to read data from excel sheet and file path are taken from FilePaths interface
	 *
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @return Excel cell value in string
	 */
	public  static String getExcelData(String sheetName, int rowNo, int cellNo) {
		AppGenericLib.logger.info("data found in row: " + rowNo + " in cell: " + cellNo);
		try {
			sheet = workbook.getSheet(sheetName);	
			Row row = sheet.getRow(rowNo);
			DataFormatter format = new DataFormatter();
			Cell cell = row.getCell(cellNo);
			value=format.formatCellValue(cell);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return value;

	}


	/**
	 * Method read data based on key and the column name/test data cell
	 *
	 * @param sheetName
	 * @param testcaseID
	 * @param columnName
	 * @return Cell String value
	 */
	public  static String getExcelData(String sheetName, String key, String columnName)
	{
		AppGenericLib.logger.info("Reading data from excel from sheet: " + sheetName + "\t key: " + key + "\t cellName: " + columnName);
		try {
			sheet = workbook.getSheet(sheetName);
			int lastRow = sheet.getLastRowNum();
			int testRow = 0;
			for (int i = 0; i <= lastRow; i++)
			{
				try {
					String testcaseNum = sheet.getRow(i).getCell(0).getStringCellValue();
					if (testcaseNum.equalsIgnoreCase(key))
					{
						testRow = i;
						break;
					}
				} catch (NullPointerException e) 
				{

				}
			}
			int lastCell = sheet.getRow(testRow - 1).getLastCellNum();
			int testcell = 0;
			for (int i = 0; i <= lastCell; i++) {
				try
				{
					String cellData = sheet.getRow(0).getCell(i).getStringCellValue();
					if (cellData.equalsIgnoreCase(columnName))
					{
						testcell = i;
						break;
					}
				} catch (NullPointerException e) {
				}
			}
			return getExcelData(sheetName, testRow, testcell);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "No Data found";
	}
	
		/**
		 * Method used to write data into excel sheet
		 *
		 * @param sheetName
		 * @param rowNum
		 * @param cellNum
		 * @param cellValue
		 */
		public void writeDataToExcel(String language,String sheetName, int rowNum, int cellNum, String cellValue) {
			AppGenericLib.logger.info("writing data into excel: " + sheetName + "\t cell value: " + cellValue);
			FileOutputStream fileOut=null;
			try {
				workbook = WorkbookFactory.create(file);
				sheet = workbook.getSheet(sheetName);
				sheet.createRow(rowNum).createCell(cellNum).setCellValue(cellValue);
				switch (language.toLowerCase()) {
				case "hindi":
					fileOut = new FileOutputStream(FilePaths.EXCELDATA);
					workbook.write(fileOut);
					break;
				case "english":
					fileOut = new FileOutputStream(FilePaths.EXCELDATA);
					workbook.write(fileOut);
					break;
				default:
					System.out.println(language+":language not found");
					break;
				}
	
			}
			catch (Throwable e) {
				e.printStackTrace();
			}
		}
	
		public Object[][] readDataToExcel(String sheetName){
			AppGenericLib.logger.info("writing data into excel: " + sheetName );
			try {
				workbook = WorkbookFactory.create(file);
				sheet = workbook.getSheet(sheetName);
				int rowNum = sheet.getLastRowNum();
				int cellNum = sheet.getRow(0).getLastCellNum();
				Object[][] data=new Object[rowNum][cellNum];
				for (int i=0;i< rowNum;i++){
					for(int j=0;j<cellNum;j++)
					{
						data[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
					}
				}
				return data;
			} catch (Throwable e) {
				e.printStackTrace();
			}
			return new Object[0][0];
		}
}
