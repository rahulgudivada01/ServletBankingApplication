package com.digit.javaTraining.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Loan")
public class Loan extends HttpServlet{
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;

	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
		String loan_name=req.getParameter("text");
		String url="jdbc:mysql://localhost:3306/Project1";
		String user="root";
		String pwd="Rahulg01*";
		HttpSession session=req.getSession();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pwd);
			String sql="select* from loan where l_type=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,loan_name);
			resultSet=pstmt.executeQuery();
			if(resultSet.next()==true)
			{
				session.setAttribute("lid", resultSet.getInt("lid"));
				session.setAttribute("l_type",resultSet.getString("l_type"));
				session.setAttribute("tenure",resultSet.getInt("tenure"));
				session.setAttribute("interest",resultSet.getInt("interest"));
				session.setAttribute("description",resultSet.getString("description"));
				resp.sendRedirect("LoanDetails.jsp");
			}
			else {
				resp.sendRedirect("LoanFail.html");
			}	
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}

}
