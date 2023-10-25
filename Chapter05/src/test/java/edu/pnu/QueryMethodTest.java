package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class QueryMethodTest {
	@Autowired
	private BoardRepository boardRepo;

	// @Test
	public void testFindByTitle() {
		List<Board> boardList = boardRepo.findByTitle("1");

		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}

	// @Test
	public void testByContentContaining() {
		List<Board> boardList = boardRepo.findByContentContaining("content");

		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}

	// @Test
	public void testByTitleContainingOrContentContaining() {
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContaining("4", "4");

		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}

	// @Test
	public void testByTitleContainingOrderBySeqDesc() {
		List<Board> boardList = boardRepo.findByTitleContainingOrderBySeqDesc("2");

		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}

	@Test
	public void testFindByTitleContaining() {
		/*
		Pageable paging = PageRequest.of(0, 5);
		
		List<Board> boardList = boardRepo.findByTitleContaining("t", paging);
		  
		System.out.println("paging 검색결과"); 
		for(Board board : boardList) {
			System.out.println("--->"+board.toString()); 
		}
		*/
		
		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");
		Page<Board> pageInfo = boardRepo.findByTitleContaining("t", paging);

		System.out.println("page size:" + pageInfo.getSize());
		System.out.println("total pages:" + pageInfo.getTotalPages());
		System.out.println("total count:" + pageInfo.getTotalElements());
		System.out.println("next:" + pageInfo.nextPageable());

		//List<Board> boardList = pageInfo.getContent(); //list로 받을 필요 없이 pageInfo로 바로 돌 수 있다.
		System.out.println("paging 검색결과");
		for (Board board : pageInfo) {
			System.out.println("--->" + board.toString());
		}

	}

	/* 실습 */
	@Test
	@Order(1)
	public void testByTitleContaining() {
		List<Board> boardList = boardRepo.findByTitleContaining("1");

		System.out.println("title에 1이 포함되는 데이터 출력");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}

	}

	@Test
	@Order(2)
	public void testByTitleContainingAndCntGreaterThan() {
		List<Board> boardList = boardRepo.findByTitleContainingAndCntGreaterThan("1", 50);

		System.out.println("title에 1이 포함되고 cnt가 50보다 큰 데이터");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}

	}

	@Test
	@Order(3)
	public void testByCntGreaterThanEqualAndCntLessThanEqualAndOrderBySeqAsc() {
		List<Board> boardList = boardRepo.findByCntGreaterThanEqualAndCntLessThanEqualOrderBySeqAsc(10, 50);

		System.out.println("cnt가 10이상 50이하인 데이터를 seq 오름차순으로 출력");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}

	@Test
	@Order(4)
	public void testByTitleContainingOrContentContainingOrderBySeqDesc() {
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContainingOrderBySeqDesc("10", "2");

		System.out.println("title에 10이 포함되거나 content에 2가 포함되는 데이터를 seq 내림차순으로 출력");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
}
