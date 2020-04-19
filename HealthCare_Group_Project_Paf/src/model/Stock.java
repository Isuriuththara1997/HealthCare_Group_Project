package model;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import Util.DB_Connection;

public class Stock {
	
	public String insertStock(String id, String sname, String quantity, String expDate, String recDate) {
		String output = "";
		try {
			DB_Connection con = new DB_Connection();
			Connection conn = con.connect();
			
			if (con == null) 
			{ return "Error while connecting to the database for inserting.";}
			
			// create a prepared statement
			String query = " insert into stock ('id','sname','quantity','expDate','recDate')" + " values (?,?,?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, sname);
			preparedStmt.setString(3, quantity);
			preparedStmt.setString(4, expDate);
			preparedStmt.setString(5, recDate);
            //execute the statement
			preparedStmt.execute();
			conn.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the stock.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String readStock() {
		String output = "";
		try {
			DB_Connection con = new DB_Connection();
			Connection conn = con.connect();
			if (con == null) 
			{ return "Error while connecting to the database for reading."; }
            // Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Stock Id</th><th>Stock Name</th><th>Quantity</th><th>Expiry Date</th><th>Received Date</th></tr>";
			String query = "select * from stock";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String id = rs.getString("id");
				String sname = rs.getString("sname");
				String quantity = rs.getString("quantity");
				String expDate = rs.getString("expDate");
				String recDate = rs.getString("recDate");
				
// Add into the html table
				output += "<tr><td>" + id + "</td>";
				output += "<td>" + sname + "</td>";
				output += "<td>" + quantity + "</td>";
				output += "<td>" + expDate + "</td>";
				output += "<td>" + recDate + "</td>";
// buttons
				
			}
			conn.close();
// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the stocks.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	public String updateStock( String id, String sname, String quantity, String expDate,String recDate) {
		String output = "";
		try {
			DB_Connection con = new DB_Connection();
			Connection conn = con.connect();
			if (con == null) 
			{ return "Error while connecting to the database for updating."; }
// create a prepared statement
			String query = "UPDATE stock SET sname=?, quantity=?, expDate=?, recDate=? WHERE id=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
// binding values
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, sname);
			preparedStmt.setString(3, quantity);
			preparedStmt.setString(4, expDate);
			preparedStmt.setString(5, recDate);
			
// execute the statement
			preparedStmt.execute();
			conn.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	public String deleteStock(String id) {
		String output = "";
		try {
			DB_Connection con = new DB_Connection();
			Connection conn = con.connect();
			if (con == null) 
			{ return "Error while connecting to the database for deleting."; }
// create a prepared statement
			String query = "delete from stock where id=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
// execute the statement
			preparedStmt.execute();
			conn.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}


	

	

	

}
