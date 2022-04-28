package com;




import javax.ws.rs.core.MediaType;



//For REST Service
import javax.ws.rs.*;
//For JSON
import com.google.gson.*;

import model.Notice;


//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/Not")
public class NoticeService {

	
	Notice ItemObj = new Notice();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readnotice(){
		
	 return ItemObj.readnotice();
	}	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertnotice(@FormParam("NotID") String NotID, 
	@FormParam("NotType") String NotType, 
	@FormParam("UserID") String UserID, 
	@FormParam("Msg") String Msg)
	{ 
		
		String output = ItemObj.insertnotice(NotID, NotType, UserID, Msg); 
		return output; 
		
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCart(String itemData){
		
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 String NotID = itemObject.get("NotID").getAsString();
	 String NotType = itemObject.get("NotType").getAsString();
	 String UserID = itemObject.get("UserID").getAsString();
	 String Msg = itemObject.get("Msg").getAsString();
	 
	 String output = ItemObj.updateCart(NotID, NotType, UserID, Msg );
	
	 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCart(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String NotID = doc.select("NotID").text();
	 String output = ItemObj.deleteCart(NotID);
	
	 return output;
	}

	
	
}
