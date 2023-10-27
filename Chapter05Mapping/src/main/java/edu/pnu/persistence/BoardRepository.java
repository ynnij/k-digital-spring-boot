package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findByTitle(String searchKeyword); //글 제목으로 목록 조회
	// like 연산자 사용 - containing 
	List<Board> findByContentContaining(String searchKeyword); 
	// 여러 조건 사용(And, Or) 키워드 결합
	List<Board> findByTitleContainingOrContentContaining(String title, String Content); 
	// 데이터 정렬
	List<Board> findByTitleContainingOrderBySeqDesc(String keyword);
	// 페이징 처리, 정렬처리
	//List<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	
}
