package com.java.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.codoid.products.fillo.Fillo;
import com.java.enums.DbType;
import com.mysql.jdbc.ResultSetMetaData;

public class DbUtils {
	public static Connection con = null;

	public static Statement dbConnect(Enum dbType) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/selenium";
		Statement stmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "root", "123456");
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
	public static void executeSql(Enum dbType,String sql) {
		Statement stmt = dbConnect(dbType);
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet getResultSet (String sql) {
		ResultSet rSet = null;
		Statement stmt = dbConnect(DbType.mysql);
		try {
			rSet = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rSet;
	}
	
	/*
	 * download the webElement from DB
	 * 
	 */
	public static Map<String, String> getResultSetMap (String sql) {
		Map<String, String> rSetMap = new HashMap<String, String> ();
		ResultSet rSet = null;
		Statement stmt = dbConnect(DbType.mysql);
		
		try {
			rSet = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = (ResultSetMetaData) rSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			for (int i = 1; i <= columnsNumber; i++) {
				System.out.println(rsmd.getColumnName(i));
			}
			
			while (rSet.next()) {
				
				System.out.println("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return rSetMap;
	}
	
	/**
	 * @author Justin
	 * 
	 */
	public void name() {
		
	}
	
	public static void main(String[] args) {
//		String pathString ="INSERT INTO Eteam_ui (elementType,locator) VALUES ('xpath','username')";
//		executeSql(DbType.mysql, pathString);
		String sqlString = "SELECT * FROM Eteam_ui";
		Map<String, String> map = getResultSetMap(sqlString);
		System.out.println(map);
	}
}
