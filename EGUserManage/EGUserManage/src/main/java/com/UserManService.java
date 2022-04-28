package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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

import model.UserMang;



public class UserManService {
	
	UserMang projectObj = new UserMang(); 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String addProjects(@FormParam("Name") String Name, 
	 @FormParam("Email") String Email, 
	 @FormParam("Phone") String Phone, 
	 @FormParam("ZipCode") String ZipCode,
	 @FormParam("Address") String Address) 
	{ 
	 String output = projectObj.addProjects(Name, Email, Phone,ZipCode,Address); 
	return output; 
	}

	

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProjects(String projectData) 
	{ 
		
		
	//Convert the input string to a JSON object 
	 JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String UserID = projectObject.get("UserID").getAsString(); 
	 String Name = projectObject.get("Name").getAsString(); 
	 String Email = projectObject.get("Email").getAsString(); 
	 String Phone = projectObject.get("Phone").getAsString(); 
	 String ZipCode = projectObject.get("ZipCode").getAsString();
	 String Address = projectObject.get("Address").getAsString();
	 String output = projectObj.updateUser(UserID,Name, Email,Phone,ZipCode,Address); 
	return output; 
	}
	
	
	
	 
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProjects(String projectData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(projectData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String projectID = doc.select("UserID").text(); 
	 String output = projectObj.deleteProjects(projectID); 
	return output; 
	}


}
