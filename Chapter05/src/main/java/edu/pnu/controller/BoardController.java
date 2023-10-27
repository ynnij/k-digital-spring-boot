package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@RestController
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@GetMapping("/board")
	public Iterable<Board> getBoards(String title) {
		return boardService.getBoards(title);
	}
	
}
