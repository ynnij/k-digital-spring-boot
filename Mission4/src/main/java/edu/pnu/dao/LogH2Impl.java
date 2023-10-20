package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class LogH2Impl implements LogInterface {
	Connection con;
	
	public LogH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/mission2","sa","abcd");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addLog(String method, String query, boolean success) {
		String sql = "insert into dblog(method,sqlstring, success) values(?,?,?)";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1,method);
			psmt.setString(2, query);
			psmt.setBoolean(3, success);
			psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
