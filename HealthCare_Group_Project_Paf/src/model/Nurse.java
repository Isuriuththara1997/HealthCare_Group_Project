package model;

import java.sql.*;

public class Nurse {
	
	 //A common method to connect to the DB
		private Connection connect()
			{
				Connection con = null;
				try
			{
				Class.forName("com.mysql.jdbc.Driver");
		
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospitalmanagement", "root", "root");
			}
				catch (Exception e)
				{e.printStackTrace();}
				return con;
			}
		
		public String insertNurse(String id, String name, String age, String tele, String ward)
		{
			String output = "";
				try
				{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for inserting."; }
		
					// create a prepared statement
					String query = " insert into nurse(`nurse_id`,`nurse_name`,`nurse_age`,`nurse_tele`,`nurse_ward`)"
							+ " values (?, ?, ?, ?, ?)";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(id));
					preparedStmt.setString(2, name);
					preparedStmt.setInt(3, Integer.parseInt(age));
					preparedStmt.setInt(4,Integer.parseInt(tele));
					preparedStmt.setString(5, ward);
		
					// execute the statement
					preparedStmt.execute();
					con.close();
		
					output = "Inserted successfully";
				}
				catch (Exception e)
				{
					output = "Error while inserting the Nurse Details.";
					System.err.println(e.getMessage());
				}
				return output;
		}
		
		public String readNurse()
		{
			String output = "";
			
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for reading."; }
		
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>NurseID</th><th>NurseName</th><th>NurseAge</th><th>NurseTelePhone</th><th>NurseWard</th><th>Patient Name</th><th>Patient Telephone</th><th>Stock ID</th><th>Stock Name</th></tr>";
						
					String query = "select n.nurse_id, n.nurse_name, n.nurse_age, n.nurse_tele, n.nurse_ward, p.patientName, p.patientPhone, s.sname, s.quantity from nurse n , stock s, patient p where n.nurse_id = s.nurseID AND  n.nurse_id = p.nurseId";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
						
					// iterate through the rows in the result set
					while (rs.next())
					{
						String nurse_id = Integer.toString(rs.getInt("nurse_id"));
						String nurse_name = rs.getString("nurse_name");
						String nurse_age = Integer.toString(rs.getInt("nurse_age"));
						String nurse_tele = Integer.toString(rs.getInt("nurse_tele"));
						String nurse_ward = rs.getString("nurse_ward");
						String patientName = rs.getString("patientName");
						String patientPhone = Integer.toString(rs.getInt("patientPhone"));
						String stockname = rs.getString("sname");
						String stockQuantity = rs.getString("quantity");
		
						// Add into the html table
						output += "<tr><td>" + nurse_id + "</td>";
						output += "<td>" + nurse_name + "</td>";
						output += "<td>" + nurse_age + "</td>";
						output += "<td>" + nurse_tele + "</td>";
						output += "<td>" + nurse_ward + "</td>";
						output += "<td>" + patientName + "</td>";
						output += "<td>" + patientPhone + "</td>";
						output += "<td>" + stockname + "</td>";
						output += "<td>" + stockQuantity + "</td>";
		
						
					}
		
					con.close();
		
					// Complete the html table
					output += "</table>";
		
			}
			catch (Exception e)
			{
				output = "Error while reading the Nurse Details.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		public String updateNurse(String id, String name, String age, String tele, String ward)
		{
			String output = "";
		
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for updating."; }
				// create a prepared statement
		
				String query = "UPDATE Nurse SET nurse_name=?,nurse_age=?,nurse_tele=?,nurse_ward=?WHERE nurse_id=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				
				preparedStmt.setString(1, name);
				preparedStmt.setInt(2, Integer.parseInt(age));
				preparedStmt.setInt(3, Integer.parseInt(tele));
				preparedStmt.setString(4, ward);
				preparedStmt.setInt(5, Integer.parseInt(id));

		
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Updated successfully";
			}
			catch (Exception e)
			{
				output = "Error while updating the Nurse Details.-"+e;
				System.err.println(e.getMessage());
			}
		
			return output;
		}
		
		
		public String deleteNurse(String id)
		{
			String output = "";
		
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for deleting."; }
		
				// create a prepared statement
				String query = "delete from nurse where nurse_id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
		
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(id));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			}
			catch (Exception e)
			{
				output = "Error while deleting the nurse Details.";
				System.err.println(e.getMessage());
			}
		
			return output;
		}
		
}


