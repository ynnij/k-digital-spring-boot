package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public class MemberH2Impl implements MemberInterface { // db에 직접 접근해서 데이터 핸들링
	Connection con;

	public MemberH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/mission2", "sa", "abcd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 서비스가 요청하는 메서드
	// 여기서 직접 데이터를 다룸
	@Override
	public Map<String, Object> addMember(MemberVO memberVO) {
		Map<String, Object> map = new HashMap<>();
		if (memberVO.getName() == null || memberVO.getPass() == null) {
			map.put("data",0);
			map.put("sql","");
			return map;
		}
			

		String sql = "insert into member (pass, name) values ('"
					+memberVO.getPass()+"','"
					+memberVO.getName()+"')";
		int result = 0;
		try {
			Statement st = con.createStatement();
			result = st.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		map.put("sql", sql);
		map.put("data", result);
		
		return map;
	}

	@Override
	public Map<String, Object> getMembers() {
		List<MemberVO> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		String sql = "select * from member";
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				MemberVO member = MemberVO.builder().id(rs.getInt("id")).pass(rs.getString("pass"))
						.name(rs.getString("name")).regidate(rs.getDate("regidate")).build();
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		map.put("sql", sql);
		map.put("data", list);
		
		return map;
	}

	@Override
	public Map<String, Object> getMember(int id) {
		String sql = "select * from member where id="+id;
		Map<String, Object> map = new HashMap<>();
		MemberVO member = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				member = MemberVO.builder().id(rs.getInt("id")).pass(rs.getString("pass")).name(rs.getString("name"))
						.regidate(rs.getDate("regidate")).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.put("sql", sql);
		map.put("data", member);
		return map;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO memberVO) {
		Map<String, Object> map = new HashMap<>();
		if (memberVO.getName() == null && memberVO.getPass() == null) {
			map.put("data",0);
			map.put("sql","");
			return map;
		}
			
		int result = 0;

		
		MemberVO m = (MemberVO)getMember(memberVO.getId()).get("data");
		String sql = "update member set ";
		
		
		if(memberVO.getName()!=null) sql+= (" name ='"+memberVO.getName()+"'");
		else						 sql+= (" name ='"+m.getName()+"'");
		if(memberVO.getPass()!=null) sql+= (", pass ='"+memberVO.getPass()+"'");
		else 						 sql+= (", pass ='"+m.getPass()+"'");
		sql += " where id=" + memberVO.getId();

		try {
			Statement st = con.createStatement();
			result = st.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

		map.put("data",result);
		map.put("sql",sql);
		return map;
	}

	@Override
	public Map<String, Object> removeMember(int id) {
		Map<String, Object> map = new HashMap<>();
		String sql = "delete member where id =" + id;
		int result = 0;

		try {
			Statement st = con.createStatement();
			result = st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

		map.put("data",result);
		map.put("sql",sql);
		return map;
	}
}
