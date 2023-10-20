package edu.pnu.dao;

public interface LogInterface {
	void addLog(String method, String sql, boolean success);
}
