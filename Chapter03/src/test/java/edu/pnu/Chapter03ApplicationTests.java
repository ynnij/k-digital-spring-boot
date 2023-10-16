package edu.pnu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
class Chapter03ApplicationTests {
	
	@Autowired
	Environment environment;
	
	@Test
	public void testMethod() {
		System.out.println("이름 : "+environment.getProperty("author.name"));
		System.out.println("나이 : "+environment.getProperty("author.age"));
		System.out.println("국가 : "+environment.getProperty("author.nation"));
	}
	
	@DisplayName("속성 출력 테스트")
	@Test
	void contextLoads() {
		System.out.println("test");
	}

}
