package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import edu.pnu.domain.MemberVO;

public class MemberService {
	private List<MemberVO> list;
	
	public MemberService() {
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
	public List<MemberVO> getMembers(){
		return list;
	}
	
	public MemberVO findMember(int id) {
		for(MemberVO member : list)
			if(member.getId() == id) return member;
		return null;
	}
	
	public int getNextId() {
		int lastId = -1;
		for(MemberVO member : list) {
			if(member.getId() > lastId) lastId = member.getId();
		}
		return lastId+1;
	}
	
	public MemberVO addMember(MemberVO memberVO) {
		MemberVO member = findMember(memberVO.getId());
		if(member==null) {
			getMembers().add(
					MemberVO.builder().id(getNextId()).name(memberVO.getName())
					.pass(memberVO.getPass()).regidate(new Date()).build());
			return memberVO;
		}
			
		return null;
	}
	
	public MemberVO updateMembers(MemberVO memberVO) {
		MemberVO member = findMember(memberVO.getId());
		if(member!=null) {
			if(memberVO.getName()!=null) member.setName(memberVO.getName());
			if(memberVO.getPass()!=null) member.setPass(memberVO.getPass());
			return member;
		}
		
		return null;
	}
	public MemberVO removeMember(int id) {
		MemberVO member = findMember(id);
		if(member!=null) {
			getMembers().remove(member);
			return member;
		}
		return null;
	}
	
}
