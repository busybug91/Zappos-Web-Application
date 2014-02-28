package com.question.DabatabaseAccess;
import com.question.zappos.*;

import java.sql.*;
/*This class adds user's request for future notifications into the database
 * */


public class DAOEnternDetails {



	public void enterUserRequests(UserInfo info) throws SQLException
	{
		DatabaseAccess dbAccess= new DatabaseAccess();
		Connection conn=dbAccess.getConn();
		PreparedStatement check=conn.prepareStatement("SELECT * FROM zappos.product_data WHERE productID=?");
		check.setString(1, info.getProductID());
		boolean presence=check.executeQuery().next();
		check.close();
		PreparedStatement ps=null;
		if(!presence)
		{
			/*If the product is already in the product_data table then it is skipped
			 * because we already know that people want to know about that product.
			 * Else product id is saved in database with initial default discount of 0%.
			 * */
			ps=conn.prepareStatement("INSERT INTO zappos.product_data (productID) VALUES(?)");
			ps.setString(1, info.getProductID());
			System.out.println(ps.toString());
			ps.execute();
			ps.close();
		}
		/*User's email ID and requested product is stored in notify_data table
		 * */
		ps=conn.prepareStatement("INSERT INTO zappos.notify_data (emailID,productID) VALUES(?,?)");
		ps.setString(1, info.getEmail());
		ps.setString(2, info.getProductID());
		System.out.println(ps.toString());
		ps.execute();
		ps.close();
		dbAccess.closeConneciton();
		conn.close();
	}


}
