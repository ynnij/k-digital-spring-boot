package edu.pnu.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;

public class LogFileImp implements LogInterface {
	
	public void addLog(String method, String sql, boolean success) {
		String log = method+", "+sql+", "+new Date()+", "+success; 
		
		try {			
			//true : 기존 파일에 이어서 작성
			FileWriter fw = new FileWriter("Log.txt",true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(log);
			bw.newLine();
			bw.flush();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
