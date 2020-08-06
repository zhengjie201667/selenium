package Utils;

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
		Fillo fillo = new Fillo();
		
		try {
			rSet = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = (ResultSetMetaData) rSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rSet.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					String columnValue = rSet.getString(i);
					System.out.print(rsmd.getColumnName(i) + " " + columnValue);
				}
				System.out.println("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try {
//			rSet = stmt.executeQuery(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//        try {
//			while (rSet.next()) {
//				String elementType = rSet.getString(2);
//				String locator = rSet.getString(3);
//				rSetMap.put(elementType, locator);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		return rSetMap;
	}
	
	public static void main(String[] args) {
//		String pathString ="INSERT INTO Eteam_ui (elementType,locator) VALUES ('xpath','username')";
//		executeSql(DbType.mysql, pathString);
		String sqlString = "SELECT * FROM Eteam_ui";
		Map<String, String> map = getResultSetMap(sqlString);
		System.out.println(map);
	}
}
