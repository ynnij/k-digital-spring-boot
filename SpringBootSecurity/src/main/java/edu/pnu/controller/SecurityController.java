package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	@GetMapping({"/", "/index"})
	public String index() { //String : return명.html 호출
		System.out.println("index 요청입니다.");
		return "index"; //index.html
	}
	
	@GetMapping("/member") //member.html
	public void member() { // void : url명.html을 호출
		System.out.println("Member 요청입니다.");
	}
	
	@GetMapping("/manager")
	public void manager() {
		System.out.println("Manager 요청입니다.");
	}
	
	@GetMapping("/admin")
	public void admin() { 
		System.out.println("Admin 요청입니다.");
	}
}
