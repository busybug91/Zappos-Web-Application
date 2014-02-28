package com.question.DabatabaseAccess;
import com.question.zappos.*;

import java.sql.*;
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
			ps=conn.prepareStatement("INSERT INTO zappos.product_data (productID) VALUES(?)");
			ps.setString(1, info.getProductID());
			System.out.println(ps.toString());
			ps.execute();
			ps.close();
		}
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
