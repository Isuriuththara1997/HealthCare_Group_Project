package com;

import patient.Patient;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import appointment.appointment;

//For XML
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.parser.*;

@Path("/Appointments")
public class appointmentService {

	appointment appointmentObj = new appointment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppoinment() {
		return appointmentObj.readAppoinment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("app_Date") String appoinmentDate,
			@FormParam("app_Venue") String appoinmentVenue, @FormParam("app_Doctor_Id") String docId,
			@FormParam("app_Patient_Id") String patientId) {
		String output = appointmentObj.insertAppointment(appoinmentDate, appoinmentVenue, docId, patientId);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppoinment(String appId) {

		JsonObject appoinmentObject = new JsonParser().parse(appId).getAsJsonObject();

		String appID = appoinmentObject.get("app_Id").getAsString();
		String appDate = appoinmentObject.get("app_Date").getAsString();
		String appVenue = appoinmentObject.get("app_Venue").getAsString();
		String appDocId = appoinmentObject.get("app_Doctor_Id").getAsString();
		String appPatientId = appoinmentObject.get("app_Patient_Id").getAsString();
		String output = appointmentObj.updateAppoinment(appID, appDate, appVenue, appDocId, appPatientId);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppoinment(String appId) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(appId, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String appid = doc.select("app_Id").text();
		String output = appointmentObj.deleteAppoinment(appid);
		return output;
	}

}
