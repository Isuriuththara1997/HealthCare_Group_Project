package Util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
	
	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/payment", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
