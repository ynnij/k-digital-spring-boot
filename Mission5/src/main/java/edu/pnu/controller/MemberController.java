package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	MemberService memberService;

	@GetMapping("/member")
	public List<Member> getMembers() {
		return memberService.getMembers();
	}
	
	@GetMapping("/member/{id}") //PathVariable
	public ResponseEntity<?> getMember(@PathVariable Integer id) {
		Member member = memberService.getMember(id);
		if(member!=null)
			return ResponseEntity.ok(member);
		return ResponseEntity.badRequest().body("not found user");
	}
	@GetMapping("/member2") // 쿼리스트링
	public ResponseEntity<?> getMember2(Integer id) {
		Member member = memberService.getMember(id);
		if(member!=null)
			return ResponseEntity.ok(member);
		return ResponseEntity.badRequest().body("not found user");
	}
	
	@PostMapping("/member") 
	public int addMember(Member member) {
		return memberService.addMember(member);
	}
	
	@PutMapping("/member")
	public ResponseEntity<?> updateMember(Member member) {
		int result = memberService.updateMember(member);
		if(result != 0)
			return ResponseEntity.ok(result);
		return ResponseEntity.badRequest().body("invalid value");
	}
	
	@DeleteMapping("/member/{id}") //PathVariable
	public void removeMember(@PathVariable Integer id) {
		memberService.removeMember(id);
	}
	@DeleteMapping("/member") //쿼리스트링
	public void removeMember2(Integer id) {
		memberService.removeMember(id);
	}
	
}
