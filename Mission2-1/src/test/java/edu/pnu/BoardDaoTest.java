package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.BoardDAO;
import edu.pnu.domain.BoardVO;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class BoardDaoTest {
	
	@DisplayName("BoardDAO Insert Test")
	//@Test
	@Order(0)
	public void testInsert() {
		BoardDAO boardDAO = new BoardDAO();
		int result = boardDAO.addBoard(BoardVO.builder()
						.writer("w3")
						.content("c3")
						.title("t3").build());
		System.out.println("[Insert] : "+result+"\n");
	}
	
	@DisplayName("BoardDAO Select ALL Test")
	@Test 
	@Order(1)
	public void testSelectAll() {
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> list = boardDAO.getBoards();
		
		System.out.println("[Select All]");
		for(BoardVO board : list) 
			System.out.println(board);
		System.out.println("\n");
	}
	
	@DisplayName("BoardDAO Select Test")
	//@Test
	@Order(2)
	public void testSelect() {
		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = boardDAO.getBoard(1);
		System.out.println("[Select] : "+board+"\n");
	}
	
	@DisplayName("BoardDAO Update Test")
	@Test
	@Order(0)
	public void testUpdate() {
		BoardDAO boardDAO = new BoardDAO();
		int result = boardDAO.updateBoard(BoardVO.builder()
						.seq(3)
						.writer("test33")
						.content("test33")
						.title("test33").build());
		System.out.println("[Update] : "+result+"\n");
	}
	
	@DisplayName("BoardDAO Delete Test")
	//@Test
	@Order(0)
	public void testDelete() {
		BoardDAO boardDAO = new BoardDAO();
		int result = boardDAO.removeBoard(2);
		System.out.println("[Delete] : "+result+"\n");
	}
}
