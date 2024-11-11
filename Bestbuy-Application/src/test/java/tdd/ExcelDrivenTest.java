package tdd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;



public class ExcelDrivenTest {

	public static String path = "D:\\NEW PROJECT\\SignInData.xlsx";
	
	public static void main(String[] args) {
		
		ExcelDrivenTest test = new ExcelDrivenTest();
		test.getCellLength("DataDriven");
		
	}

	public void getCellLength (String sheetname) {
		
		File file = new File(path);
		
		try {
		if(file.exists()) { Assert.assertTrue(true); }
		else { Assert.assertTrue(false); }
		}
		catch(AssertionError ae) { System.out.println("Excel File does not exist! " + ae.getMessage()); 
		System.exit(1);}
		
		
		try {
		FileInputStream input = new FileInputStream(file);
		
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = workbook.getSheet(sheetname);
		XSSFRow rows = sheet.getRow(0);
		int lastrow = rows.getLastCellNum();
		short lastCell = rows.getLastCellNum();
		System.out.println(lastCell);
		
		
		
		
		workbook.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("Unable to Read the File! " + fnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println("An IO error occurred! " + ioe.getMessage());
		}
		
		
		System.out.println("Test execution");
	}
	
	
	
}
