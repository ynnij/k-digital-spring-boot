package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDaoH2Impl;
import edu.pnu.dao.MemberDaoListImp;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	MemberInterface memberDAO;
	
	//@Autowired
	//private Environment env; //application 프로퍼티에 있는 데이터를 읽을 때 사용
	
	public MemberService(Environment env) {
		
		String type = env.getProperty("mywebservice.data.type");
		
		if(type.equals("h2")) {
			memberDAO = new MemberDaoH2Impl();
			System.out.println("h2 서비스");
		}
		else {
			memberDAO = new MemberDaoListImp();
			System.out.println("리스트 서비스");
		}
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
