package com.digit.javaTraining.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/changepassword")
public class changepassword extends HttpServlet{
	private Connection con;
	private PreparedStatement pstmt;

	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
		HttpSession session=req.getSession();
		int accno=(int)session.getAttribute("accno");
		int password=Integer.parseInt(req.getParameter("oldpassword"));
		int newpassword=Integer.parseInt(req.getParameter("newpassword"));
		int renewpassword=Integer.parseInt(req.getParameter("renewpassword"));
		if(renewpassword==newpassword)
		{
			String url="jdbc:mysql://localhost:3306/Project1";
			String user="root";
			String pwd="Rahulg01*";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url,user,pwd);
				String sql="update BankApp set pin=? where accno=? and pin=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,newpassword);
				pstmt.setInt(2,accno);
				pstmt.setInt(3,password);
				int x=pstmt.executeUpdate();
				resp.sendRedirect("passwordchangesuccess.html");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			resp.sendRedirect("passwordchangefailure.html");
		}



	}

}
