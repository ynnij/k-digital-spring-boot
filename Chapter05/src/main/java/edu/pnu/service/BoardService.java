package edu.pnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@Service
public class BoardService {
	@Autowired
	DynamicBoardRepository boardRepo;
	
	QBoard qboard = QBoard.board;
	BooleanBuilder builder;
	
	public BoardService() {
		builder = new BooleanBuilder();
	}
	
	public Iterable<Board> getBoards(String title){
		builder.and(qboard.title.like("%"+title+"%"));

		return boardRepo.findAll(builder);
	}
	
}
