package model; 
import java.sql.*; 
public class customer 
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", ""); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 

//method to insert new customers
public String insertCustomers(String name, String nic, String address, String email, String contact) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into customer (`customerID`,`customerName`,`customerNIC`,`customerAddress`,`customerEmail`, `customerContact`)"
 + " values (?, ?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, name); 
 preparedStmt.setString(3, nic); 
 preparedStmt.setString(4, address); 
 preparedStmt.setString(5, email); 
 preparedStmt.setString(6, contact);
 // execute the statement
 
 preparedStmt.execute(); 
 con.close(); 
 String newCustomers = readCustomers(); 
 output = "{\"status\":\"success\", \"data\": \"" + 
		 newCustomers + "\"}"; 
 } 
 catch (Exception e) 
 { 
 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
 System.err.println(e.getMessage()); 
 }
 return output; 
 } 


//method to read customers
public String readCustomers() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr>"+
 "<th>User Name</th><th>"+
 "User NIC</th>" +
 "<th>User Address</th>" + 
 "<th>User Email</th>" +
 "<th> User Contact-No</th><th>Update</th><th>Remove</th>" +
 "</tr>"; 
 
 String query = "select * from customer"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String customerID = Integer.toString(rs.getInt("customerID")); 
 String customerName = rs.getString("customerName"); 
 String customerNIC = rs.getString("customerNIC"); 
 String customerAddress = rs.getString("customerAddress");  
 String customerEmail = rs.getString("customerEmail"); 
 String customerContact = rs.getString("customerContact"); 
 // Add into the html table
 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' "
 		+ " type='hidden' value='" + customerID + "'>"
		 + customerName + "</td>"; 
 output += "<td>" + customerNIC + "</td>"; 
 output += "<td>" + customerAddress + "</td>"; 
 output += "<td>" + customerEmail + "</td>"; 
 output += "<td>" + customerContact + "</td>"; 
//buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-itemid='" + customerID + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-itemid='" + customerID + "'></td></tr>";  
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading connections."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 



//method to update items
public String updateCustomers(String ID, String name, String nic, String address, String email, String contact) 
 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for updating."; } 
 // create a prepared statement
 String query = "UPDATE customer SET customerName=?,customerNIC=?,customerAddress=?,customerEmail=?,customerContact=? WHERE customerID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, name); 
 preparedStmt.setString(2, nic); 
 preparedStmt.setString(3, address); 
 preparedStmt.setString(4, email); 
 preparedStmt.setString(5, contact); 
 preparedStmt.setInt(6, Integer.parseInt(ID)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 String newCustomers = readCustomers(); 
 output = "{\"status\":\"success\", \"data\": \"" + 
		 newCustomers + "\"}"; 
 } 
 catch (Exception e) 
 { 
 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 



//method to delete customers
public String deleteCustomers(String customerID) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for deleting."; } 
 // create a prepared statement
 String query = "delete from customer where customerID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(customerID)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 String newCustomers = readCustomers(); 
 output = "{\"status\":\"success\", \"data\": \"" + 
		 newCustomers + "\"}"; 
 } 
 catch (Exception e) 
 { 
 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
} 
