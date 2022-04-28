package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Pay {


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
		
		public String insertPay(String bill_ID, String card_Holder, String card_type, String card_No, String cvv,String amount) { 
		 
			String output = ""; 
		 
			try{ 
		 
				Connection con = connect(); 
		 
				if (con == null) {
			 
					return "Error while connecting to the database for inserting."; 
		 
				} 
		 
				// create a prepared statement               
		
				String query = " insert into paymet (`payID`,`bill_ID`,`card_type`,`card_No`,`cvv`,`amount`)"+ " values (?, ?, ?, ?, ?, ?)"; 
		 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
				// binding values
				preparedStmt.setInt(1, 0); 
				preparedStmt.setInt(2, Integer.parseInt(bill_ID));
				preparedStmt.setString(3, card_Holder);
				preparedStmt.setString(3, card_type); 
				preparedStmt.setInt(4, Integer.parseInt(card_No));
				preparedStmt.setInt(4, Integer.parseInt(card_No));
				preparedStmt.setDouble(6, Double.parseDouble(amount));
				
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
			
	
		public String deleteCart(String payID) {
			 String output = "";
			 
			 try {
					 
				 Connection con = connect();
				 
				 if (con == null){
					 return "Error while connecting to the database for deleting."; 
				 }
				 
				 // create a prepared statement
				 String query = "delete from paymet where payID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(payID));
				 
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

		public String readPay() {
			String output = ""; 
			 
			 try{
				 
				 Connection con = connect();
				 if (con == null) {
					 // ,payID, bill_ID, card_Holder, card_type, card_No, cvv, amount
					 return "Error while connecting to the database for reading."; }
				 
				 // Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>Pay ID</th><th>Bill ID</th><th>Name </th><th>Card Type</th><th>CVV</th><th>Amount</th>" +
						 "<th>card_No </th></tr>";
			
				 String query = "select * from paymet";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 // iterate through the rows in the result set
				 while (rs.next()){
					 
					 String bill_ID = Integer.toString(rs.getInt("bill_ID"));
					 String card_Holder = rs.getString("card_Holder");
					 String card_type = rs.getString("card_type");
					 String card_No = Integer.toString(rs.getInt("card_No"));
					 String cvv = Integer.toString(rs.getInt("cvv"));
					 String amount = Double.toString(rs.getDouble("amount"));
					 String payID = Integer.toString(rs.getInt("payID"));
					 
					 // Add into the html table
					 output += "<td>" + payID + "</td>";
					 output += "<td>" + bill_ID + "</td>";
					 output += "<td>" + card_Holder + "</td>";
					 output += "<td>" + card_type + "</td>";
					 output += "<td>" + card_No + "</td>";
					 output += "<td>" + cvv + "</td>";
					 output += "<td>" + amount + "</td>";
					 
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							 + "<td><form method='post' action='items.jsp'>"+"<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							 + "<input name='project_id' type='hidden' value='" + payID
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

		public String updatePay(String payID, String bill_ID, String card_Holder, String card_type, String card_No,
				String cvv, String amount) {
			String output = "";
			 
			 try {
				 
				 Connection con = connect();
				 
				 if (con == null){
					 return "Error while connecting to the database for updating."; 
				 }
				 
				 // create a prepared statement
				 String query = "UPDATE paymet SET bill_ID=?,card_Holder=?,card_type=?,card_No=?,cvv=? ,amount=? WHERE, payID=?";
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				// ,payID, bill_ID, card_Holder, card_type, card_No, cvv, amount
				 preparedStmt.setString(1,bill_ID);
				 preparedStmt.setString(2, card_Holder);
				 preparedStmt.setString(3, card_type);
				 preparedStmt.setInt(4, Integer.parseInt(card_No));
				 preparedStmt.setInt(5, Integer.parseInt(cvv));
				 preparedStmt.setString(6, amount);
				 
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
	

}
