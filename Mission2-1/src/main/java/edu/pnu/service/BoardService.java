package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.BoardDAO;
import edu.pnu.domain.BoardVO;

public class BoardService {
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	public List<BoardVO> getBoards(){
		return boardDAO.getBoards();
	}
	public BoardVO getBoard(int seq) {
		return boardDAO.getBoard(seq);
	}
	public int addBoard(BoardVO board) {
		return boardDAO.addBoard(board);
	}
	public int updateBoard(BoardVO board) {
		return boardDAO.updateBoard(board);
	}
	public int removeBoard(int seq) {
		return boardDAO.removeBoard(seq);
	}
}
