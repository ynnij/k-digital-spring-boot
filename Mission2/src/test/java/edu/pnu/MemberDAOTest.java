package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Order;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberVO;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class) // 테스트 순서를 지정할 수 있다. (TestMethodOrder)
public class MemberDAOTest {

	@DisplayName("MemberDAO Insert Test")
	@Test
	@Order(1) // 숫자 순서에 따라 실행
	public void testInsert() {
		MemberDAO dao = new MemberDAO();
		int ret = dao.addMember(MemberVO.builder()
									.pass("pass8")
									.name("user8")
									.build());
		
		System.out.println("[Insert] : "+ret);
	}
	
	@DisplayName("MemberDAO Select All Test")
	@Test
	@Order(4)
	public void testSelectAll() {
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.getMembers();
		
		System.out.println("[Select All]");
		for(MemberVO member : list)
			System.out.println(member);
	}
	
	@DisplayName("MemverDAO Select Test")
	@Test
	@Order(3)
	public void testSelect() {
		MemberDAO dao = new MemberDAO();
		MemberVO member = dao.getMember(7);
		System.out.println("[Select] "+member);
	}
	
	@DisplayName("MemverDAO Update Test")
	@Test
	@Order(2)
	public void testUpdate() {
		MemberDAO dao = new MemberDAO();
		int result = dao.updateMember(MemberVO.builder()
				.id(7)
				.pass("pwd7")

				.build());
		System.out.println("[Update] : "+ result);
	}
	
	@DisplayName("MemverDAO Delete Test")
	@Test
	@Order(0)
	public void testDelete() {
		MemberDAO dao = new MemberDAO();
		int result = dao.removeMember(4);
		System.out.println("[Delete] : "+ result);
	}
	
}
