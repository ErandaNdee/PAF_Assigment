package com;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For XML
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.parser.*;

//For JSON
import com.google.gson.*;


import model.comp; 




@Path("/Projects") 
public class comservice {
	
	
	 comp projectObj = new comp(); 
		@GET
		@Path("/") 
		@Produces(MediaType.TEXT_HTML) 
		public String readCom() 
		 { 
			 return projectObj.readCom();
		 } 
		
		

		 
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String addcomplaint(@FormParam("UserID") String UserID, 
		 @FormParam("User_Name") String User_Name, 
		 @FormParam("Phone") String Phone,
		 @FormParam("price") String price, 
		 @FormParam("Compain") String Compain,
		 @FormParam("phone") String phone) 
		{ 
			//ComID, UserID, User_Name, Phone, Compain
		 String output = projectObj.addcomplaint(UserID, User_Name, Phone,Compain); 
		return output; 
		}

		

		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updatecomplaint(String ProjectData) 
		{ 
		//Convert the input string to a JSON object 
		 JsonObject projectObject = new JsonParser().parse(ProjectData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String ComID = projectObject.get("ComID").getAsString(); 
		 String UserID = projectObject.get("UserID").getAsString(); 
		 String User_Name = projectObject.get("User_Name").getAsString(); 
		 String Phone = projectObject.get("Phone").getAsString(); 
		 String Compain = projectObject.get("Compain").getAsString(); 
		 String output = projectObj.updatecomplaint(ComID, UserID, User_Name, Phone, Compain); 
		return output; 
		}
		
		 
		@DELETE
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deleteProjects(String projectData) 
		{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(projectData, "ComID", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String ComID = doc.select("ComID").text(); 
		 String output = projectObj.deletecomplaint(ComID); 
		return output; 
		}


}
