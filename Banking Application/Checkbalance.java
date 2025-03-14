package com.sbi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.sbi.model.Model;

 
@WebServlet("/Checkbalance")
public class Checkbalance extends HttpServlet { 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		 int accno = (int) session.getAttribute("accno");
		 
		 try {
			 Model m = new Model();
			 m.setAccno(accno);
			 
			 boolean b = m.Checkbalance();
			 
			 if(b == true) {
				 session.setAttribute("bal", m.getBal());
				 response.sendRedirect("/BankingApplication1/BalanceView.jsp");
			 }
			 else {
				 response.sendRedirect("/BankingApplication1/BalanceFail.html");
			 }
		 }
		catch (Exception e) {
			e.printStackTrace();
 		}
 	}

}
