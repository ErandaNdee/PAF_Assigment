package model;



import java.sql.*; 

public class comp {
	
	private Connection connect() 
	 { 
		 Connection con = null; 
		 try
		 { 
		 Class.forName("com.mysql.jdbc.Driver"); 
		 
		  
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/newdb", "root", ""); 
		 } 
		 catch (Exception e) 
		 {e.printStackTrace();} 
		 return con; 
		 
	 } 
	
	public String addcomplaint(String UserID, String User_Name, String Phone,  String Compain) 
	 { 
	
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for adding."; } 
		 // create a prepared statement for insert complaint
		 String query = " insert into complaint (`ComID`,`UserID`,`User_Name`,`Phone`,`Compain`)"+ " values (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 	
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, UserID); 
		 preparedStmt.setString(3, User_Name);
		 preparedStmt.setString(4, Phone);
		 preparedStmt.setString(5, Compain); 
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
	
		 
	public String updatecomplaint(String ComID ,String UserID, String User_Name,  String Phone,String Compain)
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement for update complaint details
		 String query = "UPDATE complaint SET UserID=?,User_Name=?,Phone=?,price=?,Compain=?,Phone=? WHERE ComID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, UserID); 
		 preparedStmt.setString(2, User_Name);
		 preparedStmt.setString(3, Phone);
		 preparedStmt.setString(5, Compain); 
		 preparedStmt.setString(6, Phone);
		 preparedStmt.setInt(7, Integer.parseInt(ComID)); 
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
	
	public String deletecomplaint(String ComID) 
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 // create prepared statement for delete project details
		 String query = "delete from complaint where ComID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(ComID)); 
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

	public String readCom() {
		String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		 // Prepare table to display project details
		 output = "<table border='1'><tr><th>Complaint ID</th><th>User ID</th>" +
		 "<th>User Name</th>" + 
		 "<th>Phone</th>" + 
		 "<th>Copain</th>" +
		 
		 "<th>Update</th><th>Remove</th></tr>"; 
		//ComID, UserID, User_Name, Phone, Compain
		 String query = "select * from complaint"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 while (rs.next()) 
		 { 
		 String ComID  = Integer.toString(rs.getInt("ComID")); 
		 String UserID = rs.getString("UserID"); 
		 String User_Name = rs.getString("User_Name");
		 String Phone = rs.getString("Phone"); 
		 String phone = rs.getString("phone"); 
		 String Compain = rs.getString("Compain");
		 // Add complaint details into the table
		 output += "<tr><td>" + ComID + "</td>"; 
		 output += "<td>" + UserID + "</td>"; 
		 output += "<td>" + User_Name + "</td>"; 
		 output += "<td>" + Phone + "</td>";
		 output += "<td>" + Compain + "</td>"; 
		 output += "<td>" + phone + "</td>";
		 // Edit and Update buttons
		 output += "<td><input UserID='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='complaint.jsp'>"
		+ "<input UserID='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input UserID='ComID' type='hidden' value='" + ComID 
		 + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the complaint."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 }
	
}
