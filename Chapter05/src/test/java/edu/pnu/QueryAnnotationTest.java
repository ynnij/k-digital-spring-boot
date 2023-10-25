package edu.pnu;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class QueryAnnotationTest {
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testQueryAnnotationTest1() {
		List<Board> boardList = boardRepo.queryAnnotationTest1("0");
		
		System.out.println("Test1 검색 결과");
		for(Board board : boardList) {
			System.out.println("--->"+board.toString());
		}
	}
	
	@Test
	public void testQueryAnnotaionTest2() {
		List<Object[]> boardList = boardRepo.queryAnnotaionTest2("0");
		
		System.out.println("Test2 검색 결과");
		for(Object[] row : boardList) {
			System.out.println("---> "+Arrays.toString(row));
		}
	}
	
	@Test
	public void testQueryAnnotationTest3() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest3("0");
		System.out.println("Test3 검색 결과");
		
		for(Object[] row : boardList) {
			System.out.println("---> "+Arrays.toString(row));
		}
	}
	
	@Test
	public void testQueryAnnotationTest4() {
		Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "seq");
		List<Board> boardList = boardRepo.queryAnnotationTest4(paging);
		
		System.out.println("Test4 검색 결과");
		for(Board board : boardList) {
			System.out.println("---> "+board.toString());
		}
	}
}
