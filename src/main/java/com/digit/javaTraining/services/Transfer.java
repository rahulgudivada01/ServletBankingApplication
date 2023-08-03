package com.digit.javaTraining.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Transfer")
public class Transfer extends HttpServlet{
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res1;
	private ResultSet res2;
	private ResultSet res3;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		HttpSession session=req.getSession();
		int cust_id=Integer.parseInt(req.getParameter("cust_id"));
		String bank_name=req.getParameter("bank_name");
		String IFSC=req.getParameter("IFSC");
		int sender_accno=Integer.parseInt(req.getParameter("sender_accno"));
		String receiver_IFSC=req.getParameter("receiver_IFSC");
		int receiver_accno=Integer.parseInt(req.getParameter("receiver_accno"));
		int amount=Integer.parseInt(req.getParameter("amount"));
		int id=ThreadLocalRandom.current().nextInt(1,100000);
		int pin=Integer.parseInt(req.getParameter("pin"));
		String url="jdbc:mysql://localhost:3306/Project1";
		String user="root";
		String pwd="Rahulg01*";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pwd);
			pstmt=con.prepareStatement("select* from bankapp where cust_id=? and ifsc_code=? and accno=? and pin=?");
			pstmt.setInt(1,cust_id);
			pstmt.setString(2,IFSC);
			pstmt.setInt(3,sender_accno);
			pstmt.setInt(4,pin);
			res1=pstmt.executeQuery();
			if(res1.next()==true)
			{
				pstmt=con.prepareStatement("select* from bankapp where ifsc_code=? and accno=?");
				pstmt.setString(1,receiver_IFSC);
				pstmt.setInt(2, receiver_accno);
				res2=pstmt.executeQuery();
				if(res2.next()==true)
				{
					pstmt=con.prepareStatement("select balance from bankapp where accno=?");
					pstmt.setInt(1,sender_accno);
					res3=pstmt.executeQuery();
					res3.next();
					int bal=res3.getInt(1);
					
					if(bal>amount)
					{
						pstmt=con.prepareStatement("update bankapp set balance=balance-? where accno=?");
						pstmt.setInt(1,amount);
						pstmt.setInt(2,sender_accno);
						int x1=pstmt.executeUpdate();
						if(x1>0)
						{
							pstmt=con.prepareStatement("update bankapp set balance=balance+? where accno=?");
							pstmt.setInt(1,amount);
							pstmt.setInt(2,receiver_accno);
							int x2=pstmt.executeUpdate();
							if(x2>0)
							{
								pstmt=con.prepareStatement("insert into transferstatus values (?,?,?,?,?,?,?,?)");
								pstmt.setInt(1, id);
								pstmt.setInt(2,cust_id);
								pstmt.setString(3,bank_name);
								pstmt.setString(4,IFSC);
								pstmt.setInt(5,sender_accno);
								pstmt.setString(6,receiver_IFSC);
								pstmt.setInt(7,receiver_accno);
								pstmt.setInt(8,amount);
								int x3=pstmt.executeUpdate();
								if(x3>0)
								{
									resp.sendRedirect("TransferSuccess.html");
								}
								else {
									session.setAttribute("message", "Error in TransferStatus Table.");
									resp.sendRedirect("transferfail.jsp");
								}
							}
							else {
								session.setAttribute("message", "Error in Updating Credit Amount.");
								resp.sendRedirect("transferfail.jsp");
							}
						}
						else
						{
							session.setAttribute("message", "Error in Updating debit Amount.");
							resp.sendRedirect("transferfail.jsp");
						}
					}
					else
					{
						session.setAttribute("message", "Sender Doesnt have sufficient Balance");
						resp.sendRedirect("transferfail.jsp");
					}
				}
				else
				{
					session.setAttribute("message", "Error in Receivers Check.");
					resp.sendRedirect("transferfail.jsp");
				}
			}
			else
			{
				session.setAttribute("message", "Error in senders check.");
				resp.sendRedirect("transferfail.jsp");
			}
			
		}catch(Exception e)
		{
			PrintWriter writer=resp.getWriter();
			writer.println(e);
		}
		
	}

}
