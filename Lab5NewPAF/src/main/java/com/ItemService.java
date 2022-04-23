package com;//this class implemet the restful api

import model.Item;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


	@Path("/Items")
	public class ItemService
	{
	Item itemObj = new Item();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
	return itemObj.readItems();
	
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("itemCode") String itemCode,
	@FormParam("itemName") String itemName,
	@FormParam("itemPrice") String itemPrice,
	@FormParam("itemDesc") String itemDesc)
	{
		
	String output = itemObj.insertItem(itemCode, itemName, itemPrice, itemDesc);
	return output;
	}
	
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
		
	//Convert the input string to a JSON object
	JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	
	//Read the values from the JSON object
	String itemID = itemObject.get("itemId").getAsString();
	String itemCode = itemObject.get("itemCode").getAsString();
	String itemName = itemObject.get("itemName").getAsString();
	String itemPrice = itemObject.get("itemPrice").getAsString();
	String itemDesc = itemObject.get("itemDesc").getAsString();
	
	String output = itemObj.updateItem(itemID, itemCode, itemName, itemPrice, itemDesc);
	return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
		
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
	
	//Read the value from the element <itemID>
	String itemID = doc.select("itemId").text();
	
	String output = itemObj.deleteItem(itemID);
	return output;
	}
	
	@GET
	@Path("/getItem")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String getItem(String itemData)
	{
		
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
	
	//Read the value from the element <itemID>
	String itemID = doc.select("itemId").text();
	
	String output = itemObj.getItem(itemID);
	return output;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@POST
	@Path("/billAdd")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItemBill(@FormParam("customerId") int customerId,
	@FormParam("month") String month,
	@FormParam("units") int units,
	@FormParam("total") int total)
	{
		
	String output = itemObj.insertItemBill(customerId, month, units, total);
	return output;
	}
	
	
	@GET
	@Path("/bill")
	@Produces(MediaType.TEXT_HTML)
	public String readItemsBill()
	{
	return itemObj.readItemsBill();
	
	}
	
	
	@PUT
	@Path("/billUpdate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItemBill(String itemBillData)
	{
		
	//Convert the input string to a JSON object
	JsonObject itemBillObject = new JsonParser().parse(itemBillData).getAsJsonObject();
	
	//Read the values from the JSON object
	String billID = itemBillObject.get("billId").getAsString();
	String cusId = itemBillObject.get("customerId").getAsString();
	String month = itemBillObject.get("month").getAsString();
	String units = itemBillObject.get("units").getAsString();
	String total = itemBillObject.get("total").getAsString();
	
	String output = itemObj.updateItemBill(billID, cusId, month, units, total);
	return output;
	}
	
	
	@DELETE
	@Path("/billDelete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItemBill(String itemBillData)
	{
		
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(itemBillData, "", Parser.xmlParser());
	
	//Read the value from the element <itemID>
	String billID = doc.select("billId").text();
	
	String output = itemObj.deleteItemBill(billID);
	return output;
	}
	
	
	
	}
	

