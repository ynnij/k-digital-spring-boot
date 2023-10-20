package edu.pnu;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.MemberH2Impl;
import edu.pnu.dao.MemberListImp;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class) // 테스트 순서를 지정할 수 있다. (TestMethodOrder)
public class MemberDaoTest {
	@DisplayName("MemberDaoListImp Select All Test")
	@Test
	public void testSelectAllList() {
		MemberInterface dao = new MemberListImp();
		Map<String, Object> map = dao.getMembers();
		List<MemberVO> list = (List<MemberVO>) map.get("data");
		System.out.println("[Select All List]");
		for (MemberVO member : list)
			System.out.println(member);
		System.out.println("\n");
	}

	@DisplayName("MemberDaoListImp Select Test")
	@Test
	public void testSelectList() {
		MemberInterface dao = new MemberListImp();
		Map<String, Object> map = dao.getMember(6);
		MemberVO member = (MemberVO) map.get("data");
		System.out.println("[Select List] : " + member + "\n");
	}

	@DisplayName("MemberDaoListImp Insert Test")
	@Test
	public void testInsertList() {
		MemberInterface dao = new MemberListImp();
		Map<String, Object> map = dao.addMember(MemberVO.builder().name("홍길동").pass("패스워드").build());
		int result = (int) map.get("data");
		System.out.println("[Insert List] : " + result);
		System.out.println(dao.getMember(6) + "\n");
	}

	@DisplayName("MemberDaoListImp Update Test")
	@Test
	public void testUpdateList() {
		MemberInterface dao = new MemberListImp();
		System.out.println("업데이트 전 \n" + dao.getMember(3));
		Map<String, Object> map = dao.updateMember(MemberVO.builder().id(3).pass("pwd3333").build());
		int result = (int) map.get("data");
		System.out.println("[Update List] : " + result);
		System.out.println(dao.getMember(3) + "\n");
	}

	@DisplayName("MemberDaoListImp Delete Test")
	@Test
	public void testDeleteList() {
		MemberInterface dao = new MemberListImp();
		Map<String, Object> map = dao.removeMember(1);
		int result = (int) map.get("data");
		System.out.println("[Delete List] : " + result + "\n");

	}

	/* H2 Test */
	@DisplayName("MemberDaoH2Impl Insert Test")
	// @Test
	@Order(1) // 숫자 순서에 따라 실행
	public void testInsert() {
		MemberInterface dao = new MemberH2Impl();
		Map<String, Object> map = dao.addMember(MemberVO.builder().pass("pass8").name("user8").build());
		int ret = (int) map.get("data");

		System.out.println("[Insert] : " + ret);
	}

	@DisplayName("MemberDaoH2Impl Select All Test")
	// @Test
	@Order(4)
	public void testSelectAll() {
		MemberInterface dao = new MemberH2Impl();
		
		Map<String, Object> map = dao.getMembers();
		List<MemberVO> list = (List<MemberVO>) map.get("data");
		
		System.out.println("[Select All]");
		for (MemberVO member : list)
			System.out.println(member);
		System.out.println("\n");
	}

	@DisplayName("MemberDaoH2Impl Select Test")
	// @Test
	@Order(3)
	public void testSelect() {
		MemberInterface dao = new MemberH2Impl();
		Map<String, Object> map= dao.getMember(6);
		MemberVO member = (MemberVO) map.get("data");
		System.out.println("[Select] " + member + "\n");
	}

	@DisplayName("MemberDaoH2Impl Update Test")
	// @Test
	@Order(2)
	public void testUpdate() {
		MemberInterface dao = new MemberH2Impl();
		Map<String, Object> map=dao.updateMember(MemberVO.builder().id(7).pass("pwd7")
				.build());

		int result = (int) map.get("data");	
		System.out.println("[Update] : " + result + "\n");
	}

	@DisplayName("MemberDaoH2Impl Delete Test")
	// @Test
	@Order(0)
	public void testDelete() {
		MemberInterface dao = new MemberH2Impl();
		Map<String, Object> map=dao.removeMember(4);
		int result = (int) map.get("data");	
		System.out.println("[Delete] : " + result + "\n");
	}

}
