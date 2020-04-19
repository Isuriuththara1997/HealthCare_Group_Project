package com;

import model.Nurse;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Nurse")

public class NurseService {

	Nurse nurseObj = new Nurse();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readNurse()
	{
		return nurseObj.readNurse();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertNurse(@FormParam("nurse_id") String nurse_id,
			@FormParam("nurse_name") String nurse_name,
			@FormParam("nurse_age") String nurse_age,
			@FormParam("nurse_tele") String nurse_tele,
			@FormParam("nurse_ward") String nurse_ward)
	{
	String output = nurseObj.insertNurse(nurse_id, nurse_name, nurse_age, nurse_tele,nurse_ward);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateNurse(String nurseData)
	{
		//Convert the input string to a JSON object
		JsonObject nurseObject = new JsonParser().parse(nurseData).getAsJsonObject();
		
		//Read the values from the JSON object
		String nurse_id = nurseObject.get("nurse_id").getAsString();
		String nurse_name = nurseObject.get("nurse_name").getAsString();
		String nurse_age = nurseObject.get("nurse_age").getAsString();
		String nurse_tele = nurseObject.get("nurse_tele").getAsString();
		String nurse_ward= nurseObject.get("nurse_ward").getAsString();
		String output = nurseObj.updateNurse(nurse_id, nurse_name, nurse_age, nurse_tele, nurse_ward);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
		
	public String deleteNurse(String nurseData)
	{
			
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(nurseData, "", Parser.xmlParser());
	
		//Read the value from the element <itemID>
		String nurse_id = doc.select("nurse_id").text();
		String output = nurseObj.deleteNurse(nurse_id);
		return output;
		}
}