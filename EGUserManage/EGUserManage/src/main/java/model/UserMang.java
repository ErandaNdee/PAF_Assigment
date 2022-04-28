package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserMang {
	
	//UserID, Name, Email, Phone, ZipCode, Address

	private Connection connect() 
	 { 
		 Connection con = null; 
		 try
		 { 
		 Class.forName("com.mysql.jdbc.Driver"); 
		 
		 //Provide the correct details: DBServer/DBName, username, password 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_assi", "root", "eranda"); 
		 } 
		 catch (Exception e) 
		 {e.printStackTrace();} 
		 return con;
		 
	 } 
	
	public String addProjects(String name, String Email, String Phone, String ZipCode, String Address) 
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for adding."; } 
		 // create a prepared statement for insert projects
		 String query = " insert into usermanage (`UserID`,`Name`,`Email`,`Phone`,`ZipCode`,`Address`)"+ " values ( ?, ?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, name); 
		 preparedStmt.setString(3, Email);
		 preparedStmt.setString(4, Phone);
		 preparedStmt.setString(6, ZipCode); 
		 preparedStmt.setString(7, Address);
		// execute insert statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "New project added successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while adding the project."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 } 
	
	public String read() 
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect();//UserID, Name, Email, Phone, ZipCode, Address,usermanage
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		 // Prepare table to display project details
		 output = "<table border='1'><tr><th>User ID </th><th>User Name</th>" +
		 "<th>Phone </th>" + 
		 "<th>Zip Code</th>" + 
		 "<th>Address</th>" + 
		 "<th>Update</th><th>Remove</th></tr>"; 
		 
		 String query = "select * from usermanage"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 while (rs.next()) 
			//UserID, Name, Email, Phone, ZipCode, Address,usermanage
		 { 
		 String UserID  = Integer.toString(rs.getInt("UserID")); 
		 String Name = rs.getString("Name"); 
		 String Email = rs.getString("Email");
		 String Phone = rs.getString("Phone"); 
		 String ZipCode = rs.getString("ZipCode"); 
		 String Address = rs.getString("Address"); 
		 // Add projects details into the table
		 output += "<tr><td>" + UserID + "</td>"; 
		 output += "<td>" + Name + "</td>"; 
		 output += "<td>" + Email + "</td>"; 
		 output += "<td>" + Phone + "</td>";
		 output += "<td>" + ZipCode + "</td>";
		 output += "<td>" + Address + "</td>"; 
		 
		 // Edit and Update buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='projects.jsp'>"
		+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='UserID' type='hidden' value='" + UserID 
		 + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the projects."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 }
	//UserID, Name, Email, Phone, ZipCode, Address,usermanage
	public String updateUser(String UserID,String name, String Email, String Phone, String ZipCode, String Address)
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement for update projects details
		 String query = "UPDATE usermanage SET Name=?,Email=?,Phone=?,ZipCode=?,Address=? WHERE UserID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, Email);
		 preparedStmt.setString(3, Phone);
		 preparedStmt.setString(5, ZipCode); 
		 preparedStmt.setString(6, Address); 
		 preparedStmt.setInt(7, Integer.parseInt(UserID)); 
		 // execute update statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Project Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the project."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 } 
	

	public String deleteProjects(String UserID) {
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 // create prepared statement for delete project details
		 String query = "delete from usermanage where UserID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(UserID)); 
		 // execute delete statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Project Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the project."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output;
	} 
}
