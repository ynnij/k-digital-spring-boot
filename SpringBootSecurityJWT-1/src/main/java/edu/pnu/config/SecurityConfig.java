package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	@Autowired
	private MemberRepository memberRepository;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable());
		
		http.authorizeHttpRequests(auth-> auth
				.requestMatchers(new AntPathRequestMatcher("/member/**")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/manager/**")).hasAnyRole("MANAGER","ADMIN")
				.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAnyRole("ADMIN")
				.anyRequest().permitAll());
		
		http.formLogin(frmLogin->frmLogin.disable());
		http.httpBasic(basic->basic.disable());
		
		//세션을 유지하지 않겠다고 설정 -> url 호출 뒤 응답할 때 까지는 유지되지만 응답 후 삭제된다는 의미
		http.sessionManagement(ssmn->ssmn.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		//스프링 시큐리티가 등록한 필터체인의 뒤에 작성한 필터 추가
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
		
		//스프링 시큐리티가 등록한 필터들 중 AuthorizationFilter 앞에 앞에서 작성한 필터 삽입
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepository), AuthorizationFilter.class);
		
		return http.build();
	}
}
