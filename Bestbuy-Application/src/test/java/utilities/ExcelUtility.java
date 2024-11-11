package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;
import org.testng.Assert;

public class ExcelUtility {

public XSSFWorkbook workbook;
public XSSFSheet sheet;
public XSSFRow rows;
public XSSFCell cells;
public File file;

	//Constructor to get Dynamic path.
	public ExcelUtility(String path) {  
	
		file = new File(path);

		try {
			if (file.exists()) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} catch (AssertionError ae) {
			System.out.println("Excel File does not exist! " + ae.getMessage());
			System.exit(1);
		}

		try {
			FileInputStream input = new FileInputStream(file);
			workbook = new XSSFWorkbook(input);
		} catch (FileNotFoundException fnfe) {
			System.out.println("Unable to Read the File! " + fnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println("An IO error occurred! " + ioe.getMessage());
		}
	}

// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX	EXCEL METHODS XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	
public int getRowLength(String sheetname) {
	
//				<------------------------------------- LastRow Count -------------------------------------->
		 int sheetIndex =workbook.getSheetIndex(sheetname);
		 
		 if(sheetIndex<0) {
			return 0; 
		 }
		 
		sheet = workbook.getSheetAt(sheetIndex);
		int rownum = sheet.getLastRowNum();
		return rownum;
//				<------------------------------------------------------------------------------------------>
}

public short getCellLength(String sheetname, int rowcount) {
	
//			<------------------------------------- LastCell Count -------------------------------------->
		int sheetIndex =workbook.getSheetIndex(sheetname);
	 
		if(sheetIndex<0) {
		return 0; 
		}
		if(rowcount<0) {
		return 0;	
		}
		
		sheet = workbook.getSheetAt(sheetIndex);
		rows = sheet.getRow(rowcount);
		short rowCellSize = rows.getLastCellNum();
		return rowCellSize;
//			<------------------------------------------------------------------------------------------>
	}	
	
public String getCellData(String sheetname, int rowcount, int cellcount) {
	
		if(((rowcount < 0) && (cellcount < 0))) {
			return " ";
		}
		
		int sheetIndex = workbook.getSheetIndex(sheetname);
		if(sheetIndex < 0)
		{
			return " ";
		}
		
		sheet = workbook.getSheetAt(sheetIndex);
		rows = sheet.getRow(rowcount);
		cells = rows.getCell(cellcount);
		
		
		if(cells==null) {  return ""; }
		//Values type based return as String
		switch(cells.getCellType())
		{
		case STRING : 
			return cells.getStringCellValue();	
		case NUMERIC : 
			return String.valueOf(cells.getNumericCellValue());
		case BOOLEAN : 
			return String.valueOf(cells.getBooleanCellValue());
		case FORMULA : 
			return cells.getCellFormula();	
		default : 
			return "";	
		
		}
		
		
		
}	
	
}
