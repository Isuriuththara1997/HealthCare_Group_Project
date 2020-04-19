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

@Path("/stocks")
public class StockService {
	public static final String STOCK_URI="http://localhost:8081/StockService/StockService/Stock";

	@GET
	@Path("/read")
	@Produces(MediaType.TEXT_PLAIN)
	public String readNurse() throws URISyntaxException {
		URI uri=new URI(STOCK_URI+"/read/");
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
	public String insertStock(@FormParam("id") String id, @FormParam("sname") String sname,
			@FormParam("quantity") String quantity, 
			@FormParam("expDate") String expDate, 
			@FormParam("recDate") String recDate) throws ParseException {
		
		Form form =new Form();
		form.param("id", id);
		form.param("sname", sname);
		form.param("quantity", quantity);
		form.param("expDate", expDate);
		form.param(recDate, recDate);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(STOCK_URI+"/insert/");
		String response = target.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .post(Entity.form(form), String.class);

		return response;
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateStock(@FormParam("id") String id, @FormParam("sname") String sname,
			@FormParam("quantity") String quantity, 
			@FormParam("expDate") String expDate, 
			@FormParam("recDate") String recDate) throws ParseException {
		Form form =new Form();
		form.param("id", id);
		form.param("sname", sname);
		form.param("quantity", quantity);
		form.param("expDate", expDate);
		form.param("recDate", recDate);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(STOCK_URI+"/update/");
		String response = target.request(MediaType.APPLICATION_JSON)
		                        .accept(MediaType.TEXT_PLAIN_TYPE)
		                        .put(Entity.form(form), String.class);

		return response;
	}
	
	@GET
	@Path("/search/{id}")
	@Produces({ MediaType.TEXT_PLAIN })
	public String searchStock(@PathParam("id")String id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(STOCK_URI+"/search");
		String response = target.path(id)
								.request(MediaType.APPLICATION_JSON)
		                        .get(String.class);

		return response;
	}

}

