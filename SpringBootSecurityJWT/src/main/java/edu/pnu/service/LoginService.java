package edu.pnu.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import edu.pnu.domain.Member;

@Service
public class LoginService {
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	public String loginProc(Member member) throws Exception {
		//security에게 로그인 요청에 필요한 객체 생성
		Authentication authToken = new UsernamePasswordAuthenticationToken(
				member.getUsername(), member.getPassword());
		
		//로그인을 실행하는 Security 객체 생성
		AuthenticationManager authenticationManager =
				authenticationConfiguration.getAuthenticationManager();
		
		//인증 진행 -> USerDetailsService를 상속받은 클래스의 loadUserByUsername 호출
		Authentication auth = authenticationManager.authenticate(authToken);
		System.out.println("auth:"+auth);
		
		//토큰 만들기
		String token = JWT.create()
				.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*10)) //유효시간 10분 
				.withClaim("username", member.getUsername()) //토큰에 등록할 데이터
				.sign(Algorithm.HMAC256("edu.pnu.jwt")); // 토큰 암호화(알고리즘 및 암호화 키)
		
		//Bearer : JWT임을 나타내는 헤더; Basic : "Basic "+Base64(username:password)
		return "Bearer "+token;
	}
}
