package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그 출력하고 싶을 때 사용하는 롬복
@RestController
public class BoardController {
	public BoardController() {
		log.info("BoardController() 생성"); // 로그쓰는 방법 
	}
	
	
	@GetMapping("/hello")
	public String hello(String name) {
		log.info("Hello 호출 : param="+name);
		return "Hello : "+ name;
	}
}
