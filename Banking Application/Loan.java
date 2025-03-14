package com.sbi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.sbi.model.Model;

@WebServlet("/Loan")
public class Loan extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int accno = (int) session.getAttribute("accno");
		
		try {
			Model m = new Model();
			m.setAccno(accno);
			boolean b = m.ApplyLoan();
			
			if(b == true) {
				session.setAttribute("name", m.getName());
				session.setAttribute("email", m.getEmail());
				response.sendRedirect("/BankingApplication1/LoanSuccess.jsp");
			}
			else {
				response.sendRedirect("/BankingApplication1/LoanFail.html");

			}
		}
		catch (Exception e) {
			e.printStackTrace();
 		}

	}

}
