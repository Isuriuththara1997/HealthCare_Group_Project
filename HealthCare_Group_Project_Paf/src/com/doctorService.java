package com;



import model.doctor;

//For REST Service 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/doctor")

public class doctorService {

	
	doctor docObj = new doctor();
	
	//Read doctor details from doctor table and Patient condition detail from patient table
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctor() {
		return docObj.readDoctor();
	}
	
	
	
	//Insert Doctor details into Doctor table
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(@FormParam("Name") String name, 
			@FormParam("Specialization") String specialization,
			@FormParam("NIC") String nic,
			@FormParam("Mobile") String mobile, 
			@FormParam("Email") String email,
			@FormParam("DoctorFee") String doctorFee) {
		String output = docObj.insertDoctor(name, specialization, nic, mobile, email,doctorFee);
		return output;
	}
	
	
	
	
	
	
	
	//Doctor details update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(String doctorData) {
		// Convert the input string to a JSON object
		JsonObject doctorObject = new JsonParser().parse(doctorData).getAsJsonObject();

		// Read the values from the JSON object
		String did = doctorObject.get("DID").getAsString();
		String name = doctorObject.get("Name").getAsString();
		String specialization = doctorObject.get("Specialization").getAsString();
		String nic = doctorObject.get("NIC").getAsString();
		String mobile = doctorObject.get("Mobile").getAsString();
		String email = doctorObject.get("Email").getAsString();
		String doctorFee = doctorObject.get("DoctorFee").getAsString();

		String output =  docObj.updateDoctor(did,name,specialization,nic,mobile,email,doctorFee);

		return output;
	}
	
	
	
	
	
	
	
	
	//Patient Condition update

	@PUT
	@Path("/patient")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatientCondition(String patientData) {
		// Convert the input string to a JSON object
		JsonObject doctorObject = new JsonParser().parse(patientData).getAsJsonObject();

		// Read the values from the JSON object
		String pname = doctorObject.get("patientID").getAsString();
		String patientCondition = doctorObject.get("patientCondition").getAsString();
	

		String output =  docObj.updatePatientCondition(pname,patientCondition);

		return output;
	}
	
	
	
	
	//Delete doctor details
	// we can't delete or update parent row, because foreign key constraint fails
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctor(String doctorData) 
	{ // Convert the input string to XML document 
		Document doc = Jsoup.parse(doctorData, "",Parser.xmlParser()); 
		//Read the value from the element <itemID> 
		String DID =doc.select("DID").text();

		String output = docObj.deleteDoctor(DID);

		return output;
	}
	
}