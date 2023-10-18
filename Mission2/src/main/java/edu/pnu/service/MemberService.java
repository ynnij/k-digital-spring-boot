package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberVO;

public class MemberService {
	MemberDAO memberDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
	public int addMember(MemberVO memberVO) {
		return memberDAO.addMember(memberVO);
	}
	
	public List<MemberVO> getMembers() {
		return memberDAO.getMembers();
	}
	
	public MemberVO getMember(int id) {
		return memberDAO.getMember(id);
	}
	
	public int updateMember(MemberVO memberVO) {
		return memberDAO.updateMember(memberVO);
	}
	
	public int removeMember(int id) {
		return memberDAO.removeMember(id);
	}

}
