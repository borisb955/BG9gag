package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private static DBManager instance;
	private Connection conn;
	
	public DBManager() {
		final String DB_IP = "localhost";
		final String DB_PORT = "3306";
		final String DB_DBNAME = "9gag";
		final String DB_USER = "root";
		final String DB_PASS = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+DB_IP+":"+DB_PORT+"/"+DB_DBNAME
						,DB_USER, DB_PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized DBManager getInstance() {
		if(instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	public Connection getConn() {
		return conn;
	}
	
	public void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
