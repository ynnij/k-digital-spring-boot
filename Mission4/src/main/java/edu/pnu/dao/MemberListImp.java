package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public class MemberListImp implements MemberInterface {
	private List<MemberVO> list;
	
	public MemberListImp() {
		list = new ArrayList<>();
		for(int i=1;i<=5;i++) {
			list.add(MemberVO.builder()
						.id(i)
						.name("name"+i)
						.pass("pass"+i)
						.regidate(new Date())
						.build());
		}
	}
	
	private MemberVO findMember(int id) {
		for(MemberVO member : list)
			if(member.getId() == id) return member;
		return null;
	}
	
	private int getNextId() {
		int lastId = -1;
		for(MemberVO member : list) {
			if(member.getId() > lastId) lastId = member.getId();
		}
		return lastId+1;
	}
	
	public Map<String, Object> getMembers(){
		Map<String, Object> map = new HashMap<>();
		map.put("data", list);
		map.put("sql","list getMembers");
		return map;
	}
	
	public Map<String, Object> getMember(int id) {
		Map<String, Object> map = new HashMap<>();
		map.put("data", findMember(id));
		map.put("sql","list getMember("+id+")");
		
		return map;
	}
	
	public Map<String, Object> addMember(MemberVO memberVO) {
		Map<String, Object> map = new HashMap<>();

		MemberVO member = findMember(memberVO.getId());
		if(member==null) {
			list.add(
					MemberVO.builder().id(getNextId()).name(memberVO.getName())
					.pass(memberVO.getPass()).regidate(new Date()).build());
			map.put("data", 1);
			map.put("sql","list addMemeber("+memberVO.getId()+")");
			return map;
		}
		
		
		map.put("data", 0);
		map.put("sql","list addMember("+memberVO.getId()+") failed");
		return map;
	}
	
	public Map<String, Object> updateMember(MemberVO memberVO) {
		Map<String, Object> map = new HashMap<>();

		MemberVO member = findMember(memberVO.getId());
		if(member!=null) {
			if(memberVO.getName()!=null) member.setName(memberVO.getName());
			if(memberVO.getPass()!=null) member.setPass(memberVO.getPass());
			map.put("data", 1);
			map.put("sql","list updateMember("+memberVO.getId()+")");
			return map;
		}
		map.put("data", 0);
		map.put("sql","list updateMember("+memberVO.getId()+") failed");
		return map;
	}
	public Map<String, Object> removeMember(int id) {
		Map<String, Object> map = new HashMap<>();

		MemberVO member = findMember(id);
		if(member!=null) {
			getMembers().remove(member);
			map.put("data", 1);
			map.put("sql","list removeMember("+id+")");
			return map;
		}
		map.put("data",0);
		map.put("sql","list removeMember("+id+") failed");
		return map;
	}
	
	
	
}
