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

	
	
	public DbUtils() {
		System.out.println("Super");
		// TODO Auto-generated constructor stub
	}

	public static Statement dbConnect(Enum dbType) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/ssm";
		Statement stmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "root", "root");
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("connect");
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
		Statement stmt = dbConnect(DbType.MYSQL);
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
	public static Map <String,Map<String, String>> getResultSetMap (String sql) {
		Map <String,Map<String, String>> rSetMap = new HashMap<>();
		Map<String, String> rMap = new HashMap<>();
		ResultSet rSet = null;
		Statement stmt = dbConnect(DbType.MYSQL);
		
		try {
			rSet = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = (ResultSetMetaData) rSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			
			rSet.last();
			for (int j = 1; j <= rSet.getRow(); j++)  {
				rMap = new HashMap<>();
				for (int i = 2; i <= columnsNumber; i++) {
					System.out.print(rsmd.getColumnName(i)+" ");
					System.out.print(rSet.getString(i));
					rMap.put(rsmd.getColumnName(i), rSet.getString(i));
				}
				
				rSetMap.put(String.valueOf(j), rMap);
				
				System.out.println(rSetMap);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return null;
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
		String sqlString = "SELECT * FROM users";
		Statement statement = dbConnect(DbType.MYSQL);
		
		
	}
}
