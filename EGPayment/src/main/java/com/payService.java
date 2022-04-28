package com;

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

import model.Pay;


public class payService {
	
	public class NoticeService {

		
		Pay ItemObj = new Pay();
		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readnotice(){
			
		 return ItemObj.readPay();
		}	
		
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String insertnotice(
		@FormParam("payID") String NotID, 
		@FormParam("bill_ID") String bill_ID, 
		@FormParam("bill_ID") String card_Holder,
		@FormParam("bill_ID") String card_type,
		@FormParam("bill_ID") String card_No,
		@FormParam("bill_ID") String cvv,
		@FormParam("bill_ID") String amount)
		

		{ 
			//payID, bill_ID, card_Holder, card_type, card_No, cvv,amount  
			String output = ItemObj.insertPay(bill_ID, card_Holder, card_type, card_No, cvv, amount ); 
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
		 String payID = itemObject.get("payID").getAsString();
		 String bill_ID = itemObject.get("bill_ID").getAsString();
		 String card_Holder = itemObject.get("card_Holder").getAsString();
		 String card_type = itemObject.get("card_type").getAsString();
		 String card_No = itemObject.get("card_No").getAsString();
		 String cvv = itemObject.get("cvv").getAsString();
		 String amount = itemObject.get("amount").getAsString();
		 
		 
		//payID, bill_ID, card_Holder, card_type, card_No, cvv,amount  
		 String output = ItemObj.updatePay(payID, bill_ID, card_Holder, card_type,card_No, cvv, amount);
		
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
	}
