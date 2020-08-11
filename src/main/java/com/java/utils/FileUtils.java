package com.java.utils;

import static org.hamcrest.CoreMatchers.nullValue;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class FileUtils {

	
	public static void getExcelFile(String filePath) {
		Fillo fillo = new Fillo();
		Connection con = null;
		Recordset recordset = null;
		try {
			con = fillo.getConnection(filePath);
			String strSql = "select * from tortoise where id = 'env'";
			recordset = con.executeQuery(strSql);
			while (recordset.next()) {
				System.out.println(recordset.getField("sit"));
			}
		} catch (FilloException e) {
			e.printStackTrace();
		}
		recordset.close();
		con.close();
	}
	
	
	/**
	 * 
	 * @param new excel
	 */
	public void name() {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("");
		HSSFRow firstRow = sheet.createRow(0);
		HSSFCell firstCell = firstRow.createCell(0);
		String [] excelCellName = {"objectname","cit","sit"};
		for (int i = 0; i < 3; i++) {
			
			
		}
	}
	
	public static void main(String[] args) {
		String path = "E:\\\\java file\\\\env.xlsx";
		getExcelFile(path);
	}
}
