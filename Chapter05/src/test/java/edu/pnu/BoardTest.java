package edu.pnu;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class BoardTest {
	@Autowired
	private BoardRepository boardRepo; //BoardRepository 부트가 자동으로 클래스 만들어서 할당 
	
	@DisplayName("보드 한 개 입력 테스트")
	@Test
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
	@Test
	public void boardInsertMany() {
		Random rd = new Random();
		for(int i=0;i<10;i++) {
			Board board = new Board();
			board.setTitle("title"+i);
			board.setContent("content"+i);
			board.setWriter("writer"+i);
			board.setCreateDate(new Date());
			board.setCnt(rd.nextLong(30));
			
			boardRepo.save(board);
			
		}
	}
	
	@DisplayName("보드(빌더) 여러 개 입력 테스트")
	@Test
	public void boardInsertBuilder() {
		Random rd = new Random();
		for(int i=0;i<10;i++) {
			boardRepo.save(Board.builder()
					.cnt(rd.nextLong(10))
					.content("con"+i)
					.createDate(new Date())
					.title("t"+i)
					.writer("w"+i)
					.build());
			
		}
	}
}
