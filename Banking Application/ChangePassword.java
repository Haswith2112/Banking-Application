package com.sbi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.sbi.model.Model;

 
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pwd = request.getParameter("npwd");
		HttpSession session = request.getSession();
		
		int accno = (int) session.getAttribute("accno");
		
		try {
			Model m = new Model();
			m.setAccno(accno);
			m.setPwd(pwd);
			boolean b = m.ChangePwd();
			
			if(b == true) {
				 response.sendRedirect("/BankingApplication1/ChangePwdSuccess.html");
			}
			else {
				 response.sendRedirect("/BankingApplication1/ChangePwdFail.html");
			}
		}
		catch (Exception e) {
 
		e.printStackTrace();}
		
 	}

}
