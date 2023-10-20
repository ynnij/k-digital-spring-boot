package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import edu.pnu.dao.LogH2Impl;
import edu.pnu.dao.LogFileImp;
import edu.pnu.dao.LogInterface;
import edu.pnu.dao.MemberH2Impl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.dao.MemberListImp;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	MemberInterface memberDao;
	LogInterface logDao;
	
	public MemberService(Environment env) {
		String type = env.getProperty("mywebservice.data.type");
		if(type.equals("h2")) memberDao = new MemberH2Impl();
		else 				  memberDao = new MemberListImp();
		
		logDao = new LogH2Impl();
		//logDao = new LogFileImp();
	}
	
	public int addMember(MemberVO memberVO) {
		Map<String, Object> map = memberDao.addMember(memberVO);
		int result = (int)map.get("data");
		logDao.addLog("addMember",(String)map.get("sql") ,(result==1) ? true : false);
		return result;
	}
	
	public List<MemberVO> getMembers() {
		Map<String, Object> map = memberDao.getMembers();
		List<MemberVO> list = (List<MemberVO>) map.get("data");
		logDao.addLog("getMembers", (String) map.get("sql"), (list!=null) ? true : false);
		return list;
	}
	
	public MemberVO getMember(int id) {
		Map<String, Object> map = memberDao.getMember(id);
		MemberVO member = (MemberVO)map.get("data");
		logDao.addLog("getMember", (String)map.get("sql") ,(member!=null)? true : false);
		return member;
	}
	
	public int updateMember(MemberVO memberVO) {
		Map<String, Object> map = memberDao.updateMember(memberVO);
 		int result = (int)map.get("data");
		logDao.addLog("updateMember", (String)map.get("sql") ,(result==1) ? true : false);
		return result;
	}
	
	public int removeMember(int id) {
		Map<String, Object> map = memberDao.removeMember(id);
		int result = (int)map.get("data");
		logDao.addLog("removeMember", (String)map.get("sql"),(result==1) ? true : false);
		return result;
		
	}

}
