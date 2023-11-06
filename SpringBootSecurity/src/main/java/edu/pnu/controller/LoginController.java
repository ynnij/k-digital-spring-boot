package edu.pnu.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	@GetMapping("/login")
	public void login() {}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {};
	
	@GetMapping("/accessDenied")
	public void accessDenied() {};
	
	
	@GetMapping("/auth") //세션에 올라가는 정보 확인하기
	public @ResponseBody String auth(@AuthenticationPrincipal User user) {
		return user.toString();
	}
}
