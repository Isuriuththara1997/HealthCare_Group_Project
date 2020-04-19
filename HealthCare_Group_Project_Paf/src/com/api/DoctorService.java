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

@Path("/Doctor")
public class DoctorService {

	public static final String HOSPITAL_URI="http://localhost:8081/DoctorService/DoctorService/Doctor";

	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_PLAIN)
	public String readDoctor() throws URISyntaxException {
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
	public String insertDoctor(@FormParam("Name") String name, @FormParam("Specialization") String specialization,
			@FormParam("NIC") String nic, 
			@FormParam("Mobile") String mobile,
			@FormParam("Email") String email,
			@FormParam("DoctorFee") String doctorFee) throws ParseException {
		
		Form form =new Form();
		form.param("Name", name);
		form.param("Specialization", specialization);
		form.param("NIC", nic);
		form.param("Mobile", mobile);
		form.param("Email", email);
		form.param("DoctorFee", doctorFee);
		
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
	public String updateDoctor(@FormParam("Name") String name, @FormParam("Specialization") String specialization,
			@FormParam("NIC") String nic, 
			@FormParam("Mobile") String mobile,
			@FormParam("Email") String email) throws ParseException {
		
		Form form =new Form();
		form.param("Name", name);
		form.param("Specialization", specialization);
		form.param("NIC", nic);
		form.param("Mobile", mobile);
		form.param("Email", email);
		
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
	public String searchDoctor(@PathParam("id")String DID) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HOSPITAL_URI+"/search");
		String response = target.path(DID)
								.request(MediaType.APPLICATION_JSON)
		                        .get(String.class);

		return response;
	}
}
