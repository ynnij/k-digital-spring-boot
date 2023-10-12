package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

/*
 * C insert Post
 * R select Get
 * U update Put
 * D delete Delete
 *  
 */

@RestController // 객체를 리턴하면 자동으로 json 파일로 바뀌어서 넘어감
public class MemberController {
	List<MemberVO> list;

	public MemberController() {
		list = new ArrayList<>();
		for (int i = 1; i < 5; i++) {
			MemberVO m = new MemberVO();
			m.setId(i);
			m.setName("name" + i);
			m.setPass("pass" + i);
			m.setRegidate(new Date());
			list.add(m);
		
			//빌더 이용한 생성
			/*
			list.add(MemberVO.builder()
								.id(i)
								.name("name"+i)
								.pass("pass"+i)
								.rigidate(new Date())
								.build());
			*/
		}
		
		
	}
	
	private MemberVO findMember(int id) {
		for (MemberVO m : list)
			if (m.getId() == id)
				return m;
		return null;
	}

	// 현재 입력된 객체들의 id 중 최대값에 1을 더해서 return
	private int getNextId() {
		int maxid = -1;
		for (MemberVO m : list)
			if (m.getId() > maxid)
				maxid = m.getId();
		return maxid + 1;
	}


	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		return list;
	}

	// MemberVO에서 id가 {id}인 데이터를 찾아서 리턴
	@GetMapping("/member/{id}") // (예) http://localhost:8080/member/5 → id가 55인 데이터를 찾아 return
	public MemberVO getMember(@PathVariable Integer id) { // 경로상에 있는 변수 : pathVariable → restful API로 개발할 때 적절한 방식r
		return findMember(id);
	}

	@PostMapping("/member")
	public MemberVO addMember(MemberVO memberVO) {
		// 1. list에 같은 memberId가 있으면 삽입 실패하도록 하는 코드(id, name, pass입력)
		/*
		 * if (findMember(memberVO.getId()) != null) return null;
		 * memberVO.setRegidate(new Date()); list.add(memberVO);
		 */

		
		// 2. id는 자동으로 가장 큰 값으로 설정하는 코드(name, pass입력)
		if(memberVO.getName()==null||memberVO.getPass()==null) return null;
		memberVO.setId(getNextId()); // 자동으로 아이디값 계산하여 set
		memberVO.setRegidate(new Date());
		list.add(memberVO);

		return memberVO;
	}

	@PostMapping("/member1")
	public ResponseEntity<?> addMember1(MemberVO memberVO) {
		if(memberVO.getName()==null||memberVO.getPass()==null) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(memberVO);
		
		memberVO.setId(getNextId());
		memberVO.setRegidate(new Date());
		list.add(memberVO);

		return ResponseEntity.status(HttpStatus.OK).body(memberVO);
		// return ResponseEntity.ok(memberVO); //이렇게 써도 됨
	}

	
	@PutMapping("/member")
	public MemberVO updateMember(MemberVO memberVO) {
		if (memberVO == null)
			return null;

		MemberVO m = findMember(memberVO.getId());
		if (m == null)
			return null;

		if (memberVO.getName() != null)
			m.setName(memberVO.getName());
		if (memberVO.getPass() != null)
			m.setPass(memberVO.getPass());

		return m;
	}

	@DeleteMapping("/member/{id}")
	public MemberVO removeMember(@PathVariable Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				return m;
			}
		}

		return null;
	}

}
