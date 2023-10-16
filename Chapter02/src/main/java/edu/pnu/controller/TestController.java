package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	public TestController() {
		System.out.println("TestController 생성자 호출");
	}
	
	@GetMapping("/hello")
	public String hello(String name) {
		return "Hello : "+name;
	}
}
