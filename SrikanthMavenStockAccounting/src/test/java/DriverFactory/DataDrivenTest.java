package DriverFactory;

import CommonLibrary.StockAccounting;
import Utilities.ExcelFileUtil;

public class DataDrivenTest {
	//this is for method commentbbbbbbbbbbbbbbbbbbbbb

	public static void main(String[] args) throws Throwable {
		StockAccounting app = new StockAccounting();
		ExcelFileUtil excel= new ExcelFileUtil();
		app.appLaunch("http://webapp.qedge.com");
		app.appLogin("admin", "master");
		for(int i=1; i<=excel.rowCount("SupplierData"); i++){
			String sName=excel.getData("SupplierData" , i, 0);
			String address=excel.getData("SupplierData" , i, 1);
			String city=excel.getData("SupplierData" , i, 2);
			String country=excel.getData("SupplierData" , i, 3);
			
			String cperson=excel.getData("SupplierData" , i, 4);
			String pNumber=excel.getData("SupplierData" , i, 5);
			String email=excel.getData("SupplierData" , i, 6);
			String mNumber=excel.getData("SupplierData" , i, 7);
			String notes=excel.getData("SupplierData" , i, 8);
			
			String ressult= app.SupplierCreation(sName, address, city, country, cperson, pNumber, email, mNumber, notes);
		excel.setData("SupplierData", i, 9, ressult);
		}
		app.appLogout();
		app.appClose();
		
	}

}
