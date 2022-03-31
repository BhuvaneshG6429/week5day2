package assignment.week5day2;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.google.common.collect.Table.Cell;

public class ReadExcel {

	public static String[][] getData(String filePath) throws IOException {
		// TODO Auto-generated method stub
		
		//to open an excel file
		XSSFWorkbook book = new XSSFWorkbook(filePath);
		//to open sheet from the excel file
		XSSFSheet sheet = book.getSheetAt(0);
		//to get total number of rows
		int rowCount = sheet.getLastRowNum();
		System.out.println("row count is : "+rowCount);
		//to get active number of columns
		int colCount = sheet.getRow(0).getLastCellNum();
		System.out.println("col count is : "+colCount);
		
		String [][] data= new String[rowCount][colCount];
		
		DataFormatter formatter = new DataFormatter();
		for(int i=1;i<=rowCount;i++) {
			XSSFRow eachRow = sheet.getRow(i);
			
			for(int j=0;j<colCount;j++) {
				XSSFCell cell = eachRow.getCell(j);
				String value=formatter.formatCellValue(cell);
				data[i-1][j]=value;
				System.out.println(value);
			}
		}
		
		book.close();
		return data;
	}	

}
