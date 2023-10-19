package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoH2Impl implements MemberInterface { // db에 직접 접근해서 데이터 핸들링
	Connection con;

	public MemberDaoH2Impl() {
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
	public int addMember(MemberVO memberVO) {
		if (memberVO.getName() == null || memberVO.getPass() == null)
			return 0;

		String sql = "insert into member (pass, name) values (?,?)";
		int result = 0;
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			result = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<MemberVO> getMembers() {
		List<MemberVO> list = new ArrayList<>();

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

		return list;
	}

	@Override
	public MemberVO getMember(int id) {
		String sql = "select * from member where id=?";
		MemberVO member = null;
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				member = MemberVO.builder().id(rs.getInt("id")).pass(rs.getString("pass")).name(rs.getString("name"))
						.regidate(rs.getDate("regidate")).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return member;
	}

	@Override
	public int updateMember(MemberVO memberVO) {
		if (memberVO.getName() == null && memberVO.getPass() == null)
			return 0;
		int result = 0;

		MemberVO m = getMember(memberVO.getId());
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

		return result;
	}

	@Override
	public int removeMember(int id) {
		String sql = "delete member where id =" + id;
		int result = 0;

		try {
			Statement st = con.createStatement();
			result = st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
