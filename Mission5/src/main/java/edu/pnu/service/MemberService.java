package edu.pnu.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepo;
	
	public List<Member> getMembers() {
		List<Member> list = memberRepo.findAll();
		
		return list;
	}
	
	public Member getMember(int id) {
		Optional<Member> opt = memberRepo.findById(id);
		if(opt.isPresent())
			return opt.get();
		return null;		
	}
	
	public int addMember(Member member) {
		Member m = memberRepo.save(Member.builder()
				.pass(member.getPass())
				.name(member.getName())
				.regidate(new Date())
				.build());
		return m.getId();

	}
	
	public int updateMember(Member member) {
		Optional<Member> opt = memberRepo.findById(member.getId());
		if(opt.isPresent()) {
			Member m = opt.get();
			if(member.getName()!=null) m.setName(member.getName());
			if(member.getPass()!=null) m.setPass(member.getPass());
			return memberRepo.save(m).getId();
		}
		
		return 0;
	}
	
	public void removeMember(int id) {
		memberRepo.deleteById(id);
	}

}
