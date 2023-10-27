package edu.pnu;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class DynamicQueryTest {
	@Autowired
	private DynamicBoardRepository boardRepo;
	
	//@Test
	public void testDynamicQuery() {
		String searchCondition = "title";
		String searchKeyword = "1";
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equals("title")) {
			builder.and(qboard.title.like("%"+searchKeyword+"%"));
		} else if(searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%"+searchKeyword+"%"));
		}
		
		Pageable paging = PageRequest.of(0, 5);
		
		Page<Board> boardList = boardRepo.findAll(builder, paging);
		
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("--->"+board.toString());
		}
		
	}
	
	@Test
	@Order(0)
	public void testInsert() {
		Random rd = new Random();
		for(int i=1;i<=100;i++) {
			boardRepo.save(Board.builder()
					.content("content"+i)
					.writer("writer"+i)
					.title("title"+i)
					.cnt(rd.nextLong(100))
					.createDate(new Date())
					.build());
		}
	}
	
	@Test
	@Order(1)
	public void testDynamicQuery1() {
		String searchKeyword = "title1";
		
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		builder.and(qboard.title.like("%"+searchKeyword+"%"));
		Iterable<Board> boardList = boardRepo.findAll(builder);
		
		System.out.println("[ title에 title1 포함된 데이터 검색 결과 ]");
		for(Board board : boardList) {
			System.out.println("--->"+board.toString());
		}
	}
	
	@Test
	@Order(2)
	public void testDynamicQuery2() {
		int searchCnt= 50;
		
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		builder.and(qboard.cnt.gt(searchCnt));
		Iterable<Board> boardList = boardRepo.findAll(builder);
		
		System.out.println("[ cnt가 50이상인 데이터 검색 결과 ]");
		for(Board board : boardList) {
			System.out.println("--->"+board.toString());
		}
	}
	
	@Test
	@Order(3)
	public void testDynamicQuery3() {
		String searchKeyword = "title1";
		
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		builder.and(qboard.title.like("%"+searchKeyword+"%"));
			
		Pageable paging = PageRequest.of(0, 10);
		Page<Board> boardList = boardRepo.findAll(builder, paging);
		
		System.out.println("[ title에 title1 포함된 데이터 검색 결과 + paging ]");
		for(Board board : boardList) {
			System.out.println("--->"+board.toString());
		}
	}
	
	@Test
	@Order(4)
	public void testDynamicQuery4() {
		int searchCnt = 50;
		
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		builder.and(qboard.cnt.gt(searchCnt));
		Pageable paging = PageRequest.of(0, 10);
		Page<Board> boardList = boardRepo.findAll(builder, paging);
		
		System.out.println("[ cnt가 50이상인 데이터 검색 결과 + paging ]");
		for(Board board : boardList) {
			System.out.println("--->"+board.toString());
		}
	}
	
}
