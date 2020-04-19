package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Stock;

@Path("/stocks")
public class stockService {
	
Stock stockObj = new Stock();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	public String readStock() {
		return stockObj.readStock();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertStock(@FormParam("id") String id,
						@FormParam("sname") String sname,
						@FormParam("quantity") String quantity,
						@FormParam("expDate") String expDate,
						@FormParam("recDate") String recDate)
	{
		String output = stockObj.insertStock(id, sname, quantity, expDate, recDate);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateStock(String stockData) {
		// Convert the input string to a JSON object
		JsonObject stockObject = new JsonParser().parse(stockData).getAsJsonObject();

		// Read the values from the JSON object
		String id = stockObject.get("id").getAsString();
		String sname = stockObject.get("sname").getAsString();
		String quantity = stockObject.get("quantity").getAsString();
		String expDate =stockObject.get("expDate").getAsString();
		String recDate = stockObject.get("recDate").getAsString();

		String output =  stockObj.updateStock(id, sname, quantity, expDate, recDate);

		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteStock(String stockData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(stockData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
    String id = doc.select("id").text();
	String output = stockObj.deleteStock(id);
	return output;
	}
}
