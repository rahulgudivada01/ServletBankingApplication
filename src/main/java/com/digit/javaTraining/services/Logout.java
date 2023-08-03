package com.digit.javaTraining.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Logout")
public class Logout extends HttpServlet{
	@Override
	public void service(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
		HttpSession session=req.getSession();
		session.invalidate();
		
		resp.sendRedirect("Welcome.html");
		
	}
	
	
}

