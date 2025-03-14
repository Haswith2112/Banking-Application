package com.sbi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.sbi.model.Model;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int accno = (int) session.getAttribute("accno");
		
	    String samt = request.getParameter("amt");
		int amt = Integer.parseInt(samt);
		
		String sraccno = request.getParameter("raccno");
		int  raccno = Integer.parseInt(sraccno);

		try {
			Model m = new Model();
			m.setAccno(accno);
			m.setRaccno(raccno);
			m.setBal(amt);
			boolean b = m.Transfer();
			if(b == true) {
				response.sendRedirect("/BankingApplication1/TransferSuccess.html");
			}
			else {
				response.sendRedirect("/BankingApplication1/TransferFail.html");

			}

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
