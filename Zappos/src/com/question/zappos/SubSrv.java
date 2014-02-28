package com.question.zappos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.question.DabatabaseAccess.DAOEnternDetails;

/**
 * Servlet implementation class SubSrv
 * This servlet receives AJAX post request from index.jsp 
 */
@WebServlet("/SubSrv")
public class SubSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		System.out.println("In post");
		boolean status=false;
		String email=request.getParameter("email");
		String prodID=request.getParameter("prodID");
		UserInfo info=new UserInfo(email, prodID);
		System.out.println(info);
		DAOEnternDetails dao=new DAOEnternDetails();
		try {
			//this method is used to update the database with user information
			dao.enterUserRequests(info);
			pw.write("Registered for notification");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			pw.write("Server side error please try again");
			e.printStackTrace();
		}
		finally{
			pw.close();
		}
		
		
	}

}
