package tdd;

import java.util.Arrays;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import utilities.ExcelUtility;

public class UtilsCheck  {

	public static void main(String[] args) {
	
		excelDataOne();
	
	

	
	}


	
	
	
public static void excelDataOne() {
	
	String excel_Path ="D:\\NEW PROJECT\\CreateAccount_Invalid_Data.xlsx";
	
	ExcelUtility excel = new ExcelUtility(excel_Path);
	int rowsize = excel.getRowLength("TESTDATA");
	int cellsize = excel.getCellLength("TESTDATA", 0);
	
	System.out.println(rowsize);
	System.out.println(cellsize);
	
	String data[][] = new String[rowsize][cellsize];
	
	for(int i=1; i<=rowsize; i++){
		
		for(int j=0; j<cellsize; j++) {
			
			data[i-1][j]= excel.getCellData("TESTDATA", i, j);
			
		}
	
	}
	for(int i=0; i<data.length;i++) {
		
		for(int j=0; j<data[i].length; j++) {
			
			System.out.print(data[i][j] + ",");
		}
		
	System.out.println();	
	}
	
	
	
}







}
