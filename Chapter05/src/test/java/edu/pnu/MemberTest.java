package edu.pnu;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MemberTest {
	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	@Order(1)
	public void memberInsertOne() {
		memberRepo.save(Member.builder()
				.id("user6")
				.password("pwd")
				.name("name")
				.role("devleop")
				.build());
	}
	
	@Test
	@Order(0)
	public void memberInsertMany() {
		for(int i=1;i<=5;i++) {
			memberRepo.save(Member.builder()
					.id("user"+i)
					.password("pwd"+i)
					.name("name"+i)
					.role("devleop")
					.build());
		}
	}
	
	@Test
	@Order(3)
	public void getMember() {
		Member member = memberRepo.findById("user3").get();
		System.out.println(member.toString());
	}
	
	@Test
	@Order(2)
	public void updateMember() {
		Member member = memberRepo.findById("user3").get();
		member.setName("수정된 이름");
		memberRepo.save(member);
	}
	
	@Test
	@Order(4)
	public void deleteMember() {
		memberRepo.deleteById("user1");
	}
	
}
