package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class DataInitTest {
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testDataInsert() {
		for(int i=1;i<=3;i++) {
			Board board = new Board();
			board.setWriter("둘리");
			board.setCreateDate(new Date());
			board.setTitle("둘리가 등록한 게시글 "+i);
			board.setContent("둘리가 등록한 게시글 내용 "+i);
			boardRepo.save(board);
		}
		
		for(int i=1;i<=3;i++) {
			Board board = new Board();
			board.setWriter("도우너");
			board.setCreateDate(new Date());
			board.setTitle("도우너가 등록한 게시글 "+i);
			board.setContent("도우너가 등록한 게시글 내용 "+i);
			boardRepo.save(board);
		}
	}
}
