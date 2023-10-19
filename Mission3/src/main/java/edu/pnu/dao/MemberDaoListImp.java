package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImp implements MemberInterface {
	private List<MemberVO> list;
	
	public MemberDaoListImp() {
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
	
	public List<MemberVO> getMembers(){
		return list;
	}
	
	public MemberVO getMember(int id) {
		return findMember(id);
	}
	
	public int addMember(MemberVO memberVO) {
		MemberVO member = findMember(memberVO.getId());
		if(member==null) {
			getMembers().add(
					MemberVO.builder().id(getNextId()).name(memberVO.getName())
					.pass(memberVO.getPass()).regidate(new Date()).build());
			return 1;
		}
			
		return 0;
	}
	
	public int updateMember(MemberVO memberVO) {
		MemberVO member = findMember(memberVO.getId());
		if(member!=null) {
			if(memberVO.getName()!=null) member.setName(memberVO.getName());
			if(memberVO.getPass()!=null) member.setPass(memberVO.getPass());
			return 1;
		}
		
		return 0;
	}
	public int removeMember(int id) {
		MemberVO member = findMember(id);
		if(member!=null) {
			getMembers().remove(member);
			return 1;
		}
		return 0;
	}
	
	
	
}
