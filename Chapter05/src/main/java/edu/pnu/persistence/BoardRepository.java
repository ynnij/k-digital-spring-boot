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
	
	/* 쿼리 어노테이션 */
	@Query("select b from Board b where b.title like %?1% order by b.seq desc") //대소문자 구분 주의!! Board 클래스 앞에 대문자
	List<Board> queryAnnotationTest1(String searchKeyword);
	
	@Query("select b from Board b"
			+ " where b.title like %:searchKeyword% "
			+ " order by b.seq desc")
	List<Board> queryAnnotationTest1Param(@Param("searchKeyword") String searchKeyword);
	
	@Query("select b.seq, b.title, b.writer, b.createDate "
			+ " from Board b "
			+ " where b.title like %:searchKeyword% "
			+ " order by b.seq desc")
	List<Object[]> queryAnnotaionTest2(@Param("searchKeyword") String searchKeyword); // Object 리스트로 받아야한다
	
	// 네이티브 쿼리 사용하기
	@Query(value="select seq, title, writer, create_date "  
			+ " from board where title like '%'||?1||'%' " // 문자열 접합 연산자 || 사용
			+ " order by seq desc", nativeQuery = true) // 네이티브 쿼리임을 알려주는 속성 
	List<Object[]> queryAnnotationTest3(String searchKeyword); // 네이티브 쿼리일 때 Object 리스트로 받기
	
	// 페이징
	@Query("select b from Board b order by b.seq desc")
	List<Board> queryAnnotationTest4(Pageable paging);
	
	
	/* 실습 */
	//실습 title에 1이 포함되는 데이터 출력 
	List<Board> findByTitleContaining(String searchKeword);
	
	//title 1 포함 & cnt가 50보다 큼
	List<Board> findByTitleContainingAndCntGreaterThan(String keyword, int cnt);
	
	//cnt가 10~50 사이인 데이터를 seq 오름차순으로 출력 
	List<Board> findByCntGreaterThanEqualAndCntLessThanEqualOrderBySeqAsc(int cnt1, int cnt2);
	
	//title에 10이 포함되거나 content에 2가 포함되는 데이터를 seq 내림차순으로 출력
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String keyword1, String keyword2);
	
	
}
