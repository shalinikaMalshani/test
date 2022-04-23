package model;

import java.sql.*;

public class Item {
	
	//A common method to connect to the DB
	private Connection connect()
	{
	Connection con = null;
	try
	{
	Class.forName("com.mysql.jdbc.Driver");
	//Provide the correct details: DBServer/DBName, username, password
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/item_management","root", "");
	//For testing
			System.out.print("Successfully connected");
			
	}
	
	catch (Exception e)
	{e.printStackTrace();}
	return con;
	}
	
	//insert items
	public String insertItem(String code, String name, String price, String desc)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
		return "Error while connecting to the database for inserting.";
	}
	
	// create a prepared statement
	String query = " insert into item (`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)" + " values (?, ?, ?, ?, ?)";
	
	//allow to write parameterized queirs.PreparedStatement,
	//the Database uses an already compiled and defined access plan, this allows the prepared statement query to run 
	//faster than a normal query
	PreparedStatement preparedStmt = con.prepareStatement(query);
	
	// binding values
	preparedStmt.setInt(1, 0);
	preparedStmt.setString(2, code);
	preparedStmt.setString(3, name);
	preparedStmt.setDouble(4, Double.parseDouble(price));
	preparedStmt.setString(5, desc);
	
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
	
	
	//read items
	public String readItems()
	{
		
	String output = "";
	
	try
	{
	Connection con = connect();
	
	if (con == null)
	{
		return "Error while connecting to the database for reading.";
		
	}
	
	// Prepare the html table to be displayed
	output = "<table border='1'><tr><th>Item Code</th><th>Item Name</th>" +
	"<th>Item Price</th>" +
	"<th>Item Description</th>" +
	"<th>Update</th><th>Remove</th></tr>";
	
	String query = "select * from item";
	
	Statement stmt = con.createStatement();
	
	ResultSet rs = stmt.executeQuery(query);
	
	// iterate through the rows in the result set
	while (rs.next())
	{
	String itemID = Integer.toString(rs.getInt("itemID"));
	String itemCode = rs.getString("itemCode");
	String itemName = rs.getString("itemName");
	String itemPrice = Double.toString(rs.getDouble("itemPrice"));
	String itemDesc = rs.getString("itemDesc");
	
	// Add into the html table
	output += "<tr><td>" + itemCode + "</td>";
	output += "<td>" + itemName + "</td>";
	output += "<td>" + itemPrice + "</td>";
	output += "<td>" + itemDesc + "</td>";
	
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	+ "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	+ "<input name='itemID' type='hidden' value='" + itemID
	+ "'>" + "</form></td></tr>";
	}
	
	con.close();
	
	// Complete the html table
	output += "</table>";
	}
	
	catch (Exception e)
	{
	output = "Error while reading the items.";
	
	System.err.println(e.getMessage());
	}
	
	return output;
	}
	
	
	//update items
	public String updateItem(String ID, String code, String name, String price, String desc)
	{
		String output = "";
		try
		{
		Connection con = connect();
		
		if (con == null)
		{
			return "Error while connecting to the database for updating.";
			
		}
		// create a prepared statement
		String query = "UPDATE item SET itemCode=?,itemName=?,itemPrice=?,itemDesc=? WHERE itemID=?";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setString(1, code);
		preparedStmt.setString(2, name);
		preparedStmt.setDouble(3, Double.parseDouble(price));
		preparedStmt.setString(4, desc);
		preparedStmt.setInt(5, Integer.parseInt(ID));
		
		// execute the statement
		preparedStmt.execute();
		
		con.close();
		
		output = "Updated successfully";
		}
		
		catch (Exception e)
		{
		output = "Error while updating the item.";
		System.err.println(e.getMessage());
		}
		
		return output;
		}
	
	
	//delete item
		public String deleteItem(String itemID)
		{
			
		String output = "";
		
		try
		{
			
		Connection con = connect();
		
		if (con == null)
			
		{
			return "Error while connecting to the database for deleting.";
			
		}
		// create a prepared statement
		String query = "delete from item where itemID=?";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(itemID));
		
		// execute the statement
		preparedStmt.execute();
		
		con.close();
		
		output = "Deleted successfully";
		}
		catch (Exception e)
		{
		output = "Error while deleting the item.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		
		//get item
				public String getItem(String itemId)
				{
					
				String output = "";
				
				try
				{
					
				Connection con = connect();
				
				if (con == null)
					
				{
					return "Error while connecting to the database for getting data.";
					
				}
				// create a prepared statement
				String query = "select * from item where itemId=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(itemId));
				
				ResultSet rs = preparedStmt.executeQuery();
				
				// iterate through the rows in the result set
				while (rs.next())
				{
				String itemID = Integer.toString(rs.getInt("itemId"));
				String itemCode = rs.getString("itemCode");
				String itemName = rs.getString("itemName");
				String itemPrice = Double.toString(rs.getDouble("itemPrice"));
				String itemDesc = rs.getString("itemDesc");
				
					
				
				output += "<p>"+itemID+"</p>";
				output += "<p>"+itemCode+"</p>";
				output += "<p>"+itemName+"</p>";
				output += "<p>"+itemPrice+"</p>";
				output += "<p>"+itemDesc+"</p>";
				
				}
				con.close();
				}
				catch (Exception e)
				{
				output = "Error while getting the item.";
				System.err.println(e.getMessage());
				}
				return output;
				}
			
		
		
		
		//insert items bill
		public String insertItemBill(int cusId, String month, int units, int total)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{
			return "Error while connecting to the database for inserting.";
		}
		
		// create a prepared statement
		String query = " insert into bill (`billId`,`customerId`,`month`,`units`,`total`)" + " values (?, ?, ?, ?, ?)";
		
		//allow to write parameterized queirs.PreparedStatement,
		//the Database uses an already compiled and defined access plan, this allows the prepared statement query to run 
		//faster than a normal query
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setInt(2, cusId);
		preparedStmt.setString(3, month);
		preparedStmt.setInt(4,units);
		preparedStmt.setInt(5, total);
		
		// execute the statement
		preparedStmt.execute();
		
		con.close();
		
		output = "Bill Inserted successfully";
		}
		
		catch (Exception e)
		{
		output = "Error while inserting the item.";
		System.err.println(e.getMessage());
		}
		
		return output;
		}
		
		
		//read items
		public String readItemsBill()
		{
			
		String output = "";
		
		try
		{
		Connection con = connect();
		
		if (con == null)
		{
			return "Error while connecting to the database for reading.";
			
		}
		
		// Prepare the html table to be displayed
		output = "<table border='1'><tr><th>Customer Id</th>" +
		"<th>Month</th>" +
		"<th>Units</th>" +
		"<th>Total</th>" +
		"<th>Update</th><th>Remove</th></tr>";
		
		String query = "select * from bill";
		
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		// iterate through the rows in the result set
		while (rs.next())
		{
		String billId = Integer.toString(rs.getInt("billId"));
		String customerId = Integer.toString(rs.getInt("customerId"));
		String month = rs.getString("month");
		String units = Integer.toString(rs.getInt("units"));
		String total =Integer.toString(rs.getInt("total")); 
		
		// Add into the html table
		output += "<tr><td>" + customerId + "</td>";
		output += "<td>" + month + "</td>";
		output += "<td>" + units + "</td>";
		output += "<td>" + total + "</td>";
		
		// buttons
		output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		+ "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		+ "<input name='itemID' type='hidden' value='" + billId
		+ "'>" + "</form></td></tr>";
		}
		
		con.close();
		
		// Complete the html table
		output += "</table>";
		}
		
		catch (Exception e)
		{
		output = "Error while reading the items.";
		
		System.err.println(e.getMessage());
		}
		
		return output;
		}
		
		
		//update items
		public String updateItemBill(String billId, String customerId, String month, String units, String total)
		{
			String output = "";
			try
			{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
				
			}
			// create a prepared statement
			String query = "UPDATE bill SET customerId=?,month=?,units=?,total=? WHERE billId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(customerId));
			preparedStmt.setString(2, month);
			preparedStmt.setInt(3, Integer.parseInt(units));
			preparedStmt.setInt(4, Integer.parseInt(total));
			preparedStmt.setInt(5, Integer.parseInt(billId));
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			
			output = "Bill Updated successfully";
			}
			
			catch (Exception e)
			{
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
			}
			
			return output;
			}
		
		
		//delete item
			public String deleteItemBill(String billId)
			{
				
			String output = "";
			
			try
			{
				
			Connection con = connect();
			
			if (con == null)
				
			{
				return "Error while connecting to the database for deleting.";
				
			}
			// create a prepared statement
			String query = "delete from bill where billId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(billId));
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			
			output = "Bill Deleted successfully";
			}
			catch (Exception e)
			{
			output = "Error while deleting the bill.";
			System.err.println(e.getMessage());
			}
			return output;
			}
		
		
		
}
