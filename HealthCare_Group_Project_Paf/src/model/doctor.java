package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Util.DB_Connection;

public class doctor {
	
	
	
	
	
	
	

		
	
	
	
	//Insert doctor details into doctor table
	// Insert
	public String insertDoctor( String name, String specialization, String nic, String mobile, String email, String doctorFee) {
		String output = "";

		try {
		DB_Connection obj_DB_Connection= new DB_Connection();
			Connection con = obj_DB_Connection.connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement for insert values into database 
			String query = "insert into doctor(`DID`,`Name`,`Specialization`,`NIC`,`Mobile`,`Email`, `DoctorFee`)"
					+ " values (?, ?, ?, ?, ?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, specialization);
			preparedStmt.setString(4, nic);
			preparedStmt.setInt(5, Integer.parseInt(mobile));
			preparedStmt.setString(6, email);
			preparedStmt.setDouble(7, Double.parseDouble(doctorFee));
			

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Inserted doctor details successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	
	
	
	
	

	
	//Read doctor details
	//Read Doctor Name, Specialization, NIC, Mobile, Email, Doctor fee from Doctor Table
	//Read Patient Condition from Patient Table , where DoctorID (patient table) is equals to DID (doctor table).
	

		public String readDoctor() {
			String output = "";

			try {
				DB_Connection obj_DB_Connection= new DB_Connection();
				Connection con = obj_DB_Connection.connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}

				// Prepare the html table to be displayed
				//Patient name is a foreign key. Retrieve patient name from patient table
				//DID is primary key in the Doctor tale, doctorID is forign key in the Patient table
				output = "<table border=\"1\"><tr><th>Doctor Name</th>" + "<th>Specialization</th><th>NIC</th>"
						+ "<th>Mobile</th>" + "<th>Email</th>" + "<th>Doctor Fee</th>" + "<th>Patient</th>"+"<th>Patient Condition</th></tr>";

				String query = "select d.DID,d.Name,d.Specialization,d.NIC,d.Mobile,d.Email,d.DoctorFee, p.patientName, p.PatientCondition  from doctor d LEFT JOIN patient p ON d.DID=p.doctorID";
				
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				// iterate through the rows in the result set
				while (rs.next()) {
					String DID = Integer.toString(rs.getInt("DID"));
					String Name = rs.getString("Name");
					String Specialization = rs.getString("Specialization");
					String NIC = rs.getString("NIC");
					String Mobile = Integer.toString(rs.getInt("Mobile"));
					String Email = rs.getString("Email");
					String DoctorFee = Double.toString(rs.getDouble("DoctorFee"));
					String Pname = rs.getString("patientName");
					String PatientCondition = rs.getString("patientCondition");
					

					// Add into the html table
					output += "<tr><td>" + Name + "</td>";
					output += "<td>" + Specialization + "</td>";
					output += "<td>" + NIC + "</td>";
					output += "<td>" + Mobile + "</td>";
					output += "<td>" + Email + "</td>";
					output += "<td>" + DoctorFee + "</td>";
					output += "<td>" + Pname + "</td>";
					output += "<td>" + PatientCondition + "</td>";
					

					// buttons
					
				}
				con.close();

				// Complete the html table
				output += "</table>";

			} catch (Exception e) {
				output = "Error while reading the doctor.";
				System.err.println(e.getMessage());
			}

			return output;

		}
		
		
		
		
		//Update doctor details
		// update

		public String updateDoctor(String ID, String name, String specialization, String nic, String mobile, String email, String doctorFee) {
			String output = "";

			try {
				DB_Connection obj_DB_Connection= new DB_Connection();
				Connection con = obj_DB_Connection.connect();

				if (con == null) {
					return "Error while connecting to the database for updating.";
				}

				// create a prepared statement
				String query = "UPDATE doctor SET Name=?,Specialization=?,NIC=?,Mobile=?,Email=?, DoctorFee=? WHERE DID=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values doctor table
				preparedStmt.setString(1, name);
				preparedStmt.setString(2, specialization);
				preparedStmt.setString(3,nic);
				preparedStmt.setInt(4, Integer.parseInt(mobile));
				preparedStmt.setString(5, email);
				preparedStmt.setDouble(6, Double.parseDouble(doctorFee));
				preparedStmt.setInt(7, Integer.parseInt(ID));
				
				// execute the statement 
				preparedStmt.execute(); 
				con.close();

				output = "Updated doctor details successfully";
			} catch (Exception e) {
				output = "Error while updating the doctor.";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		
		
		
		//Update Patient Condition 
				// update

				public String updatePatientCondition(String patientID, String patientCondition) {
					String output = "";

					try {
						DB_Connection obj_DB_Connection= new DB_Connection();
						Connection con = obj_DB_Connection.connect();

						if (con == null) {
							return "Error while connecting to the database for updating.";
						}

						// create a prepared statement
						String query = "UPDATE patient SET patientCondition=? WHERE patientID=?";

						PreparedStatement preparedStmt = con.prepareStatement(query);

						// binding values patient table
						
						preparedStmt.setString(1, patientCondition);
						preparedStmt.setInt(2, Integer.parseInt(patientID));
					
						
						// execute the statement 
						preparedStmt.execute(); 
						con.close();

						output = "Updated patient successfully";
					} catch (Exception e) {
						output = "Error while updating the patient.";
						System.err.println(e.getMessage());
					}

					return output;
				}
				
		
		
		
		
	
		
		// Delete

		public String deleteDoctor(String DID) {
			String output = "";

			try {
				DB_Connection obj_DB_Connection= new DB_Connection();
				Connection con = obj_DB_Connection.connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}

				// create a prepared statement
				// we can't delete or update parent row, because foreign key constraint fails
				//can delete ,doctors who has Patient name and condition value is null.
				String query = "delete from doctor where DID=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1, Integer.parseInt(DID));
				// execute the statement
				preparedStmt.execute();
				con.close();

				output = "Deleted successfully";

			} catch (Exception e) {
				output = "Error while deleting the item.";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		
		

}
