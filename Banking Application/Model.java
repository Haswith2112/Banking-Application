package com.sbi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
	private String name;
	private String custid;
	private int accno;
	private String pwd;
	private int bal;
	private String email;
	private int raccno;
	
	private Connection con;	
 	private PreparedStatement pstmt;
	private ResultSet res;
	public ArrayList al = new ArrayList();
	public ArrayList sal = new ArrayList();
	public ArrayList ral = new ArrayList();

	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getBal() {
		return bal;
	}
	public void setBal(int bal) {
		this.bal = bal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRaccno() {
		return raccno;
	}
	public void setRaccno(int raccno) {
		this.raccno = raccno;
	}
	
	public Model() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");//Loading the driver
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapplication","root","Bhanu@6080");
		System.out.println("Loading the driver and establishing the connection is completed.");
 	}
	public boolean register() throws SQLException {
		 String sql = "insert into sbibank values(?,?,?,?,?,?)";//Incomplete query
		 pstmt = con.prepareStatement(sql);
		 pstmt.setString(1,name);
		 pstmt.setString(2, custid);
		 pstmt.setInt(3, accno);
		 pstmt.setString(4, pwd);
		 pstmt.setInt(5, bal);
		 pstmt.setString(6, email);
		 
		 int x = pstmt.executeUpdate();
		 
		 if(x > 0) {
			 return true;
		 }
		 
 		return false;

}
	public boolean login() throws SQLException {
		String sql = "select * from sbibank where custid=? and pwd=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, custid);
		pstmt.setString(2, pwd);
		
		ResultSet res = pstmt.executeQuery();
		
		while(res.next()==true) {
			accno = res.getInt("ACCNO");
			return true;
		}
		return false;
	}
	public boolean Checkbalance() throws SQLException {
		String sql = "select bal from sbibank where accno=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,accno);
		ResultSet res = pstmt.executeQuery();
		
		while(res.next()==true) {
			 bal = res.getInt("BAL");
			return true;
		}
 		return false;
	}
	public boolean ChangePwd() throws SQLException {
		String sql = "update sbibank set pwd=? where accno=?";
		pstmt = con.prepareStatement(sql);
        pstmt.setString(1, pwd);
        pstmt.setInt(2, accno);
        int x = pstmt.executeUpdate();
        
        if(x > 0) {
        	return true;
        }
 		return false;
	}
	public boolean Transfer() throws SQLException {
	    String sql1 = "select bal from sbibank where accno=?";
	    pstmt = con.prepareStatement(sql1);
	    pstmt.setInt(1, accno);  
	    res = pstmt.executeQuery();

	    if (res.next()) {
	        int currentBalance = res.getInt("bal");

 	        if (currentBalance >= bal) {
 	            String sql2 = "update sbibank set bal=bal-? where accno=?";
	            pstmt = con.prepareStatement(sql2);
	            pstmt.setInt(1, bal);
	            pstmt.setInt(2, accno);
	            int y1 = pstmt.executeUpdate();

	            if (y1 > 0) {
 	                String sql3 = "update sbibank set bal=bal+? where accno=?";
	                pstmt = con.prepareStatement(sql3);
	                pstmt.setInt(1, bal);
	                pstmt.setInt(2, raccno);
	                int y2 = pstmt.executeUpdate();

	                if (y2 > 0) {
 	                    String sql4 = "insert into GetStatement values(?,?,?)";
	                    pstmt = con.prepareStatement(sql4);
	                    pstmt.setInt(1, accno);
	                    pstmt.setInt(2, raccno);
	                    pstmt.setInt(3, bal);
	                    int y = pstmt.executeUpdate();

	                    if (y > 0) {
	                        return true;  
	                    }
	                }
	            }
	        }
	    }
	    return false; 
	}

	public ArrayList GetStatement() throws SQLException {
		String sql = "select * from getstatement where accno=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, accno);
		res = pstmt.executeQuery();
		
		while(res.next()==true) {
			 sal.add(res.getInt("ACCNO"));
			 ral.add(res.getInt("RACCNO"));
			 al.add(res.getInt("BAL"));
		}
  		return al;
	}
	public boolean ApplyLoan() throws SQLException {
		String sql = "select * from sbibank where accno=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, accno);
		res = pstmt.executeQuery();
		
		while(res.next()==true) {
			name = res.getString("NAME");
			email = res.getString("EMAIL");
			return true;
		}
 		return false;
	}
	 
}
