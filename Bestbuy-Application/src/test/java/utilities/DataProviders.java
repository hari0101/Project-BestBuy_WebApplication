package utilities;


import org.testng.annotations.DataProvider;

public class DataProviders {

//Create account invalid input to the fields.
@DataProvider(name="Create_Account_Invalid_Input")	
public String[][] invalid_Create_Account_Data() {
		
		String excel_Path ="D:\\NEW PROJECT\\DataProvider_Excel.xlsx";
		
		ExcelUtility excel = new ExcelUtility(excel_Path);
		int rowsize = excel.getRowLength("SIGNUP");
		int cellsize = excel.getCellLength("SIGNUP", 0);
		
		String data[][] = new String[rowsize][cellsize];
		
		for(int i=1; i<=rowsize; i++){
			
			for(int j=0; j<cellsize; j++) {
				
				data[i-1][j]= excel.getCellData("SIGNUP", i, j);
				
			}
			
		}
		return data;
	}	

//SignIn account invalid input to the fields.
@DataProvider(name="SignIn_Account_Invalid_Input")	
public String[][] invalid_SignIn_Data() {
		
		String excel_Path ="D:\\NEW PROJECT\\DataProvider_Excel.xlsx";
		
		ExcelUtility excel = new ExcelUtility(excel_Path);
		int rowsize = excel.getRowLength("SIGNIN");
		int cellsize = excel.getCellLength("SIGNIN", 0);
		
		String data[][] = new String[rowsize][cellsize];
		
		for(int i=1; i<=rowsize; i++){
			
			for(int j=0; j<cellsize; j++) {
				
				data[i-1][j]= excel.getCellData("SIGNIN", i, j);
				
			}
			
		}
		return data;
	}	






	
	
	
	
	
	
}
