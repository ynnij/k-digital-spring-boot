package edu.pnu;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class BoardTest {
	@Autowired
	private BoardRepository boardRepo; //BoardRepository 부트가 자동으로 클래스 만들어서 할당 
	
	@DisplayName("보드 한 개 입력 테스트")
	//@Test
	@Order(0)
	public void boardInsertOne() {
		Board board = new Board();
		board.setTitle("title");
		board.setContent("content");
		board.setWriter("writer");
		board.setCreateDate(new Date());
		board.setCnt(0L);
		
		boardRepo.save(board); //insert 하는 건 save
	}
	
	@DisplayName("보드 여러 개 입력 테스트")
	//@Test
	@Order(1)
	public void boardInsertMany() {
		Random rd = new Random();
		for(int i=0;i<5;i++) {
			Board board = new Board();
			board.setTitle("테스트 제목 "+i);
			board.setContent("content"+i);
			board.setWriter("writer"+i);
			board.setCreateDate(new Date());
			board.setCnt(rd.nextLong(50));
			
			boardRepo.save(board);
			
		}
	}
	
	@DisplayName("보드(빌더) 여러 개 입력 테스트")
	@Test
	@Order(2)
	public void boardInsertBuilder() {
		Random rd = new Random();
		for(int i=1;i<=50;i++) {
			boardRepo.save(Board.builder()
					.cnt(rd.nextLong(100))
					.content("con"+i)
					.createDate(new Date())
					.title("t"+i)
					.writer("w"+i)
					.build());
		}
	}
	
	//@Test
	@Order(4)
	public void testGetBoard() {
		Board board = boardRepo.findById(1L).get();
		System.out.println(board.toString());
	}
	
	//@Test
	@Order(3)
	public void testUpdateBoard() { 
		System.out.println("=== 1번 게시글 조회 ===");
		Board board = boardRepo.findById(1L).get();
		
		System.out.println("=== 1번 게시물 제목 수정 ===");
		board.setTitle("제목을 수정했습니다.");
		boardRepo.save(board);
	}
	
	//@Test
	@Order(5)
	public void testDeleteBoard() {
		boardRepo.deleteById(5L);
	}
	
	
}
