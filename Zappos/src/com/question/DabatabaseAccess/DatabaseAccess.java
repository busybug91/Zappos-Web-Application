package com.question.DabatabaseAccess;

import java.sql.*;
public class DatabaseAccess {

	private Connection conn=null;
	private Statement stmt=null;
	private PreparedStatement pStmt=null;
	private ResultSet resultSet= null;
	private String db_driver="com.mysql.jdbc.Driver";
	private String dabataseName="zappos_database";
	public String getDabataseName() {
		return dabataseName;
	}
	public void setDabataseName(String dabataseName) {
		this.dabataseName = dabataseName;
	}
	private String db_url="jdbc:mysql://localhost:3306/"+dabataseName;
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public Statement getStmt() {
		return stmt;
	}
	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}
	public PreparedStatement getpStmt() {
		return pStmt;
	}
	public void setpStmt(PreparedStatement pStmt) {
		this.pStmt = pStmt;
	}
	public ResultSet getResultSet() {
		return resultSet;
	}
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	public String getDb_driver() {
		return db_driver;
	}
	public void setDb_driver(String db_driver) {
		this.db_driver = db_driver;
	}
	public String getDb_url() {
		return db_url;
	}
	public void setDb_url(String db_url) {
		this.db_url = db_url;
	}

	public DatabaseAccess() {

		try{
			Class.forName(db_driver);
			conn= DriverManager.getConnection(db_url,"root","root");
			System.out.println("Connection to database established");
		}
		catch (Exception e)
		{
			System.out.println("Unable to establish connection");
			System.err.println(e);
		}
	}
	public boolean closeConneciton()
	{
		try{
			if(conn!=null)
			{
				conn.close();
				return true;
			}
			else
				return false;
		}
		catch(Exception e)
		{
			System.err.println(e);
			return false;
		}
	}
	public void doQuery()
	{
		//just to see how things go with jdbc
		try
		{
			pStmt=conn.prepareStatement("SELECT user_id, password FROM login_data");
			resultSet=pStmt.executeQuery();
			while(resultSet.next())
			{
				System.out.println(resultSet.getString("user_id"));
				System.out.println(resultSet.getString("password"));

			}	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}




}
