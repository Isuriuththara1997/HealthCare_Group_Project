package patient;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Util.DB_Connection;

public class Patient {
	// A common method to connect to the DB
		/*private Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/helthcare", "root", "root");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}*/

		public String insertPatient(String pName, String pAddress, String pAge, String pEmail, String pPhone, String pNIC) {
			String output = "";
			try {
				DB_Connection DB = new DB_Connection();
				Connection con = DB.connect();
				if (con == null) {
					return "Error while connecting to the database for inserting patient details.";
				}
				// create a prepared statement
				String query = " insert into patient(`patientID`,`patientName`,`patientAddress`,`patientAge`,`patientEmail`,`patientPhone`,`patientNIC`)"
						+ " values (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, pName);
				preparedStmt.setString(3, pAddress);
				preparedStmt.setInt(4, Integer.parseInt(pAge));
				preparedStmt.setString(5, pEmail);
				preparedStmt.setInt(6, Integer.parseInt(pPhone));
				preparedStmt.setString(7, pNIC);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			} catch (Exception e) {
				output = "Error while inserting the patient.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String readPatient() {
			String output = "";
			try {
				DB_Connection DB = new DB_Connection();
				Connection con = DB.connect();
				if (con == null) {
					return "Error while connecting to the database for reading patient details.";
				}
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>Patient Name</th><th>Patient Address</th><th>Patient Age</th><th>Patient Email</th><th>Patient Phone</th><th>Patient NIC</th><th>Update Patient</th><th>Remove Patient</th></tr>";
				String query = "select * from patient";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()) {
					String patientID = Integer.toString(rs.getInt("patientID"));
					String patientName = rs.getString("patientName");
					String patientAddress = rs.getString("patientAddress");
					String patientAge = Integer.toString(rs.getInt("patientAge"));
					String patientEmail = rs.getString("patientEmail");
					String patientPhone = Integer.toString(rs.getInt("patientPhone"));
					String patientNIC = rs.getString("patientNIC");
					// Add into the html table
					output += "<tr><td>" + patientName + "</td>";
					output += "<td>" + patientAddress + "</td>";
					output += "<td>" + patientAge + "</td>";
					output += "<td>" + patientEmail + "</td>";
					output += "<td>" + patientPhone + "</td>";
					output += "<td>" + patientNIC + "</td>";
					// buttons
					output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update Patient\" class=\"btn btn-secondary\"></td>"
							+ "<td><form method=\"post\" action=\"patients.jsp\">"
							+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove Patient\" class=\"btn btn-danger\">"
							+ "<input name=\"patientID\" type=\"hidden\" value=\"" + patientID + "\">" + "</form></td></tr>";
				}
				con.close();
				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the patients.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String updatePatient(String pID, String pName, String pAddress, String pAge, String pEmail, String pPhone, String pNIC) {
			String output = "";
			try {
				DB_Connection DB = new DB_Connection();
				Connection con = DB.connect();
				if (con == null) {
					return "Error while connecting to the database for updating patient details.";
				}
				// create a prepared statement
				String query = "UPDATE patient SET patientName=?,patientAddress=?,patientAge=?,patientEmail=?,patientPhone=?,patientNIC=? WHERE patientID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, pName);
				preparedStmt.setString(2, pAddress);
				preparedStmt.setInt(3, Integer.parseInt(pAge));
				preparedStmt.setString(4, pEmail);
				preparedStmt.setInt(5, Integer.parseInt(pPhone));
				preparedStmt.setString(6, pNIC);
				preparedStmt.setInt(7, Integer.parseInt(pID));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			} catch (Exception e) {
				output = "Error while updating the patient.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String deletePatient(String pID) {
			String output = "";
			try {
				DB_Connection DB = new DB_Connection();
				Connection con = DB.connect();
				if (con == null) {
					return "Error while connecting to the database for deleting patient details.";
				}
				// create a prepared statement
				String query = "delete from patient where patientID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(pID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting the patient.";
				System.err.println(e.getMessage());
			}
			return output;
		}
}
