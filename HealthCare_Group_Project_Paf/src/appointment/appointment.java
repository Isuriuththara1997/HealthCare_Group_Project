package appointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import Util.DB_Connection;
public class appointment {



	public String insertAppointment(String date, String venue, String docName, String patientId) {
		String output = "";
		try {
			DB_Connection dbc = new DB_Connection();
			Connection con = dbc.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting appoinment details.";
			}

			String insert = " insert into appointment (`app_Id`,`app_Date`,`app_Venue`,`app_Doctor_Id`,`app_Patient_Id`) values (?,?,?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(insert);

			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, venue);
			preparedStatement.setString(4, docName);
			preparedStatement.setString(5, patientId);

			preparedStatement.execute();
			output = "Inserted successfully";

		} catch (Exception e) {
			output = "Error while inserting the Appoinment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readAppoinment() {
		String output = "";
		try {
			DB_Connection dbc = new DB_Connection();
			Connection con = dbc.connect();
			if (con == null) {
				return "Error while connecting to the database for reading appoinment details.";
			}
			output = "<table border=\"1\"><tr><th>Appointment Id</th><th>Appointment Date</th><th>Appointment Venue</th><th>Doctor Assign</th><th>Patient Id</th>";
			String query = "select * from appointment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String appId = Integer.toString(rs.getInt("app_Id"));
				String appDate = rs.getString("app_Date");
				String appVenue = rs.getString("app_Venue");
				String docId = Integer.toString(rs.getInt("app_Doctor_Id"));
				String patientId = Integer.toString(rs.getInt("app_Patient_Id"));

				output += "<tr><td>" + appId + "</td>";
				output += "<td>" + appDate + "</td>";
				output += "<td>" + appVenue + "</td>";
				output += "<td>" + docId + "</td>";
				output += "<td>" + patientId + "</td>";
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update Appoinment\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"patients.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove Appoinment\" class=\"btn btn-danger\">"
						+ "<input name=\"patientID\" type=\"hidden\" value=\"" + appId + "\">" + "</form></td></tr>";
			}
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Appoinment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateAppoinment(String appId, String date, String venue, String docName, String patientId) {
		String output = "";
		try {
			DB_Connection dbc = new DB_Connection();
			Connection con = dbc.connect();
			if (con == null) {
				return "Error while connecting to the database for updating patient details.";
			}
			String update = "UPDATE appointment SET app_Date=?,app_Venue=?,app_Doctor_Id=?,app_Patient_Id=? WHERE app_Id=?";
			PreparedStatement preparedStatement = con.prepareStatement(update);

			preparedStatement.setString(1, date);
			preparedStatement.setString(2, venue);
			preparedStatement.setString(3, docName);
			preparedStatement.setString(4, patientId);
			preparedStatement.setInt(5, Integer.parseInt(appId));

			preparedStatement.execute();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Appoinment";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteAppoinment(String appId) {
		String output = "";
		try {
			DB_Connection dbc = new DB_Connection();
			Connection con = dbc.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting patient details.";
			}
			String delete = "delete from appointment where app_Id=?";
			PreparedStatement preparedStatement = con.prepareStatement(delete);

			preparedStatement.setInt(1, Integer.parseInt(appId));

			preparedStatement.execute();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the appoinment";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
