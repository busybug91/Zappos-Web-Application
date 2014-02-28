package com.nitin.java.zappos;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/*
 * This class the has methods to get product IDs from the database
 * and to find users eligible for notificaiton for the selected product
 */

public class DAOProductIDs {
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	HashSet<String> productIds=null;
	public DAOProductIDs()
	{
		productIds= new HashSet<String>();
	}
	public HashSet<String> retreiveAllProductIDs() throws SQLException
	{
		productIds.clear();
		DatabaseAccess access=new DatabaseAccess();
		conn= access.getConn();
		ps= conn.prepareStatement("SELECT productID FROM zappos.notify_data");
		rs=ps.executeQuery();
		while(rs.next())
		{
			productIds.add(rs.getString("productID"));		
		}
		ps.close();
		conn.close();
		return productIds;
	}
	public HashSet<String> retreiveRelevantEmailIDs(Product product) throws SQLException
	{
		
		/*Not all users for the selected product will be notified.
		 * If the discount is greater than 20 but same as our previous search then
		 * we notify only the new users else we notify all users about the new discount.
		 * */
		HashSet<String> users= new HashSet<String>();
		DatabaseAccess access=new DatabaseAccess();
		conn= access.getConn();
		ps=conn.prepareStatement("SELECT recentOff FROM zappos.product_data WHERE productID=?");
		ps.setString(1,product.getProductID());
		ResultSet rs= ps.executeQuery();
		rs.next();
		int recentOff=rs.getInt("recentOff");
		ps.close();
		//this
		if(recentOff!=product.getPercentOff())
		{
			System.out.println("New discount have been found for product "+product.getProductID());
			ps= conn.prepareStatement("SELECT * FROM zappos.notify_data WHERE (productID=?)");
			ps.setString(1, product.getProductID());
			ResultSet rss= ps.executeQuery();
			while(rss.next())
			{
				users.add(rss.getString("emailID"));
			}
			ps.close();
			ps=conn.prepareStatement("UPDATE `zappos`.`product_data` SET `recentOff` =? WHERE `productID` = ?");
			ps.setInt(1, product.getPercentOff());
			ps.setString(2, product.getProductID());
			ps.execute();
			conn.close();
			access.closeConneciton();
			return users;	
		}
		else 
		{
			System.out.println("Discount for "+product.getProductID()+" is same as our pre"
					+ "vious search.");
			ps= conn.prepareStatement("SELECT * FROM zappos.notify_data WHERE (productID=?) AND (notify_status=?)");
			ps.setString(1, product.getProductID());
			ps.setInt(2, 0);
			ResultSet rss= ps.executeQuery();
			while(rss.next())
			{
				users.add(rss.getString("emailID"));
			}
			ps.close();
			conn.close();
			access.closeConneciton();
			return users;	
		}
	}
}

