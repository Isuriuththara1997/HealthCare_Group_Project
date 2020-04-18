package com;

import patient.Patient;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.parser.*;


@Path("/Patients")
public class PatientService {
		Patient patientObj = new Patient();

		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readPatient() {
			return patientObj.readPatient();
		}
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertPatient(
				@FormParam("patientName") String patientName, 
				@FormParam("patientAddress") String patientAddress,
				@FormParam("patientAge") String patientAge,
				@FormParam("patientEmail") String patientEmail,
				@FormParam("patientPhone") String patientPhone,
				@FormParam("patientNIC") String patientNIC) {
			String output = patientObj.insertPatient(patientName,patientAddress,patientAge,patientEmail,patientPhone,patientNIC);
			return output;
		}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePatient(String patientData) {
			// Convert the input string to a JSON object
			
			JsonObject patienObject = new JsonParser().parse(patientData).getAsJsonObject();

			// Read the values from the JSON object
			String patientID = patienObject.get("patientID").getAsString();
			String patientName = patienObject.get("patientName").getAsString();
			String patientAddress = patienObject.get("patientAddress").getAsString();
			String patientAge = patienObject.get("patientAge").getAsString();
			String patientEmail = patienObject.get("patientEmail").getAsString();
			String patientPhone = patienObject.get("patientPhone").getAsString();
			String patientNIC = patienObject.get("patientNIC").getAsString();
			String output = patientObj.updatePatient(patientID, patientName, patientAddress, patientAge, patientEmail, patientPhone,patientNIC);
			return output;
		}
		
		//Payment Condition update

		@PUT
		@Path("/payment")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePatientCondition(String paymentData) {
			// Convert the input string to a JSON object
			JsonObject doctorObject = new JsonParser().parse(paymentData).getAsJsonObject();

			// Read the values from the JSON object
			String id = doctorObject.get("id").getAsString();
			String status = doctorObject.get("status").getAsString();
		

			String output =  patientObj.updatePaymentStatus(id,status);

			return output;
		}
		

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePatient(String patientData) {
			// Convert the input string to an XML document
			Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());

			// Read the value from the element <itemID>
			String patientID = doc.select("patientID").text();
			String output = patientObj.deletePatient(patientID);
			return output;
		}

}
