package Util;

import java.io.FileInputStream;

import java.io.IOException;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
public static FileInputStream  XL;
	
	public static XSSFWorkbook wb;
	
	public static XSSFSheet sh;
	
	public static int rc;
	
	public static XSSFRow row;
	
	public static XSSFCell cell;
	
	public static int col_Num;
	
	public static String celldata ;
	
	

	
	public static int  getRowCount( String Sheetname, String path) throws IOException
	{
XL = new FileInputStream (path);
		
	 wb = new XSSFWorkbook(XL);
         sh = wb.getSheet(Sheetname);
        rc = sh.getLastRowNum()+1;
        
        return rc;
      
	}
      
	public static String getStringCellData( String sheetName, String colName, int rowNum) throws IOException
	{
        
		sh = wb.getSheet(sheetName);
		row = sh.getRow(0);
		for (int i = 0; i < row.getLastCellNum(); i++) {
			// System.out.println(row.getCell(i).getStringCellValue().trim());
			if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				col_Num = i;
		}
		

	
		row = sh.getRow(rowNum -1);
		if (row == null)
			return "";
		
		cell = row.getCell(col_Num);
		
		if (cell == null)
			return "";

		if (cell.getCellType().name().equals("STRING"))
			return cell.getStringCellValue();

		
		else if ((cell.getCellType().name().equals("NUMERIC"))) {

			celldata = String.valueOf(cell.getNumericCellValue());
			 

			}
		
		
		
		
		

			return celldata;
		
		
		 
		} 
	

}
