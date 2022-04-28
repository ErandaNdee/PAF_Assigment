package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Notice {
	
	private Connection connect(){

		 Connection con = null;
		 try{ 
		
			 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 	con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_assi", "root", "eranda"); 
		 }
		 catch (Exception e){e.printStackTrace();
		 }
		 
		 return con;
		 } 
		
		public String insertnotice(String NotID, String NotType, String UserID, String Msg) { 
		 
			String output = ""; 
		 
			try{ 
		 
				Connection con = connect(); 
		 
				if (con == null) {
			 
					return "Error while connecting to the database for inserting."; 
		 
				} 
		 
				// create a prepared statement
		
				String query = " insert into notice (`NotID`,`NotType`,`UserID`,`Msg`)"+ " values (?, ?, ?, ?)"; 
		 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
				// binding values
				preparedStmt.setInt(1, 0); 
				preparedStmt.setInt(2, Integer.parseInt(NotType)); 
				preparedStmt.setString(3, UserID); 
				preparedStmt.setInt(4, Integer.parseInt(Msg));
		
				
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Inserted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while inserting the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
			
		
		
		

		public String readnotice() {
			String output = ""; 
			 
			 try{
				 
				 Connection con = connect();
				 if (con == null) {
		
					 return "Error while connecting to the database for reading."; }
				 
				 // Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>Notice ID</th><th>Notice Type</th><th>User IDe</th>" +
						 "<th>Msg </th></tr>";
			
				 String query = "select * from notice";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 // iterate through the rows in the result set
				 while (rs.next()){
					 
					 String NotID = Integer.toString(rs.getInt("NotID"));
					 String NotType = Integer.toString(rs.getInt("NotType"));
					 String UserID = rs.getString("UserID");
					 String Msg = Integer.toString(rs.getInt("Msg"));
					 
					 // Add into the html table
					 output += "<td>" + NotID + "</td>";
					 output += "<td>" + NotType + "</td>";
					 output += "<td>" + UserID + "</td>";
					 output += "<td>" + Msg + "</td>";
					 
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							 + "<td><form method='post' action='items.jsp'>"+"<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							 + "<input name='project_id' type='hidden' value='" + NotID
							 + "'>" + "</form></td></tr>";
				 }
				 con.close();
				 // Complete the html table
				 output += "</table>";
			 }
			 catch (Exception e){
				 output = "Error while reading the items.";
				 System.err.println(e.getMessage());
			 }
			 return output;
		}

		public String updateCart(String notID, String notType, String userID, String msg) {

			String output = "";
		 
			 try {
				 
				 Connection con = connect();
				 
				 if (con == null){
					 return "Error while connecting to the database for updating."; 
				 }
				 
				 // create a prepared statement
				 String query = "UPDATE cart SET NotType=?,UserID=?,Msg=? WHERE NotID=?";
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				// NotID, NotType, UserID, Msg,notice
				 preparedStmt.setString(1,notType);
				 preparedStmt.setString(2, userID);
				 preparedStmt.setInt(3, Integer.parseInt(msg));
				 preparedStmt.setInt(4,Integer.parseInt(notID));
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Updated successfully";
			 }
			 catch (Exception e){
				 output = "Error while updating the item.";
				 System.err.println(e.getMessage());
			 }
			 return output;
		}

		public String deleteCart(String notID) {
			 String output = "";
			 
			 try {
					 
				 Connection con = connect();
				 
				 if (con == null){
					 return "Error while connecting to the database for deleting."; 
				 }
				 
				 // create a prepared statement
				 String query = "delete from cart where NotID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(notID));
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Deleted successfully";
			 }
			 catch (Exception e){
				 output = "Error while deleting the item.";
				 System.err.println(e.getMessage());
			 }
			 return output;
			 }
		} 


