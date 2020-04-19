package com.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

@Path("/Nurse")
public class NurseService {

	public static final String HOSPITAL_URI="http://localhost:8081/NurseService/NurseService/Nurse";

	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_PLAIN)
	public String readNurse() throws URISyntaxException {
		URI uri=new URI(HOSPITAL_URI+"/read/");
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri);
		// you can map it to a pojo, no need to have a string or map
		String jsonString = target.request(MediaType.TEXT_PLAIN).get(String.class);
		return jsonString;
	}
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertNurse(@FormParam("NurseName") String NurseName, @FormParam("NurseAge") String NurseAge,
			@FormParam("TelePhone") String TelePhone, 
			@FormParam("NurseWard") String NurseWard) throws ParseException {
		
		Form form =new Form();
		form.param("NurseName", NurseName);
		form.param("NurseAge", NurseAge);
		form.param("TelePhone", TelePhone);
		form.param("NurseWard", NurseWard);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HOSPITAL_URI+"/insert/");
		String response = target.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .post(Entity.form(form), String.class);

		return response;
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateNurse(@FormParam("NurseName") String NurseName, @FormParam("NurseAge") String NurseAge,
			@FormParam("TelePhone") String TelePhone, 
			@FormParam("NurseWard") String NurseWard) throws ParseException {
		
		Form form =new Form();
		form.param("NurseName", NurseName);
		form.param("NurseAge", NurseAge);
		form.param("TelePhone", TelePhone);
		form.param("NurseWard", NurseWard);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HOSPITAL_URI+"/update/");
		String response = target.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .put(Entity.form(form), String.class);

		return response;
	}
	
	@GET
	@Path("/search/{id}")
	@Produces({ MediaType.TEXT_PLAIN })
	public String searchNurse(@PathParam("id")String NurseID) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HOSPITAL_URI+"/search");
		String response = target.path(NurseID)
								.request(MediaType.APPLICATION_JSON)
		                        .get(String.class);

		return response;
	}
}
