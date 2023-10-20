package edu.pnu.dao;

import java.util.Map;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {
	Map<String, Object> addMember(MemberVO memberVO);

	Map<String, Object> getMembers();

	Map<String, Object> getMember(int id);

	Map<String, Object> updateMember(MemberVO memberVO);

	Map<String, Object> removeMember(int id);

}