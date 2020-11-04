package com.java.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.jayway.jsonpath.JsonPath;

import net.sf.json.JSONObject;


public class FileUtil {
	private static final Logger logger = LogManager.getLogger();
	
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		recordset.close();
		con.close();
	}
	
	/**
	 * @author Justin
	 * transfer file to jsonObject
	 */
	public static JSONObject getJsonObject(File jsonFile) {
		logger.info("get json object");
		JSONObject jsonObject = null;
		try {
			String jString = FileUtils.readFileToString(jsonFile);
			jsonObject = JSONObject.fromObject(jString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	/**
	 * @author Justin
	 * transfer file to json string
	 */
	public static String getJsonObjectString(File jsonFile) {
		String jString = null;
		try {
			jString = FileUtils.readFileToString(jsonFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jString;
	}
	
	/**
	 * 
	 * @param new excel
	 */
	public void getExcel() {
//		HSSFWorkbook wb = new HSSFWorkbook();
//		HSSFSheet sheet = wb.createSheet("");
//		HSSFRow firstRow = sheet.createRow(0);
//		HSSFCell firstCell = firstRow.createCell(0);
//		String [] excelCellName = {"objectname","cit","sit"};
//		for (int i = 0; i < 3; i++) {
//			
//		}

	}
	
	
	
	public static void main(String[] args) {
		String path = "src//test//resources//Jsons//api_1.json";
		File file = new File(path);
		try {
			String json = JsonPath.read(file, "$.teacher[2]");
			System.out.println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		readFile(file);
	}
}
