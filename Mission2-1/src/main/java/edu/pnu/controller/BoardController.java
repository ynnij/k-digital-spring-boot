package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.BoardVO;
import edu.pnu.service.BoardService;

@RestController
public class BoardController {
	BoardService boardService;
	public BoardController() {
		boardService = new BoardService();
	}
	
	@GetMapping("/board")
	public List<BoardVO> getBoards(){
		return boardService.getBoards();
	}
	@GetMapping("/board/{seq}")
	public BoardVO getBoard(@PathVariable Integer seq) {
		return boardService.getBoard(seq);
	}
	@GetMapping("/board2")
	public BoardVO getBoard2(Integer seq) {
		return boardService.getBoard(seq);
	}
	@PostMapping("/board")
	public int addBoard(BoardVO board) {
		return boardService.addBoard(board);
	}
	@PutMapping("/board")
	public int updateBoard(BoardVO board) {
		return boardService.updateBoard(board);
	}
	@DeleteMapping("/board/{seq}")
	public int removeBoard(@PathVariable Integer seq) {
		return boardService.removeBoard(seq);
	}
	@DeleteMapping("/board")
	public int removeBoard2(Integer seq) {
		return boardService.removeBoard(seq);
	}
}
