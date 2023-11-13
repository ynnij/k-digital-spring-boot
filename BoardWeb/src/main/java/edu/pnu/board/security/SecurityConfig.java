package edu.pnu.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	@Autowired
	private SecurityUserDetailsService userDetailsService;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security.userDetailsService(userDetailsService);
		
		security.authorizeHttpRequests(auth->auth
				//.requestMatchers(new AntPathRequestMatcher("/","/system/**")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/board/**")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAnyRole("ADMIN")
				.anyRequest().permitAll());
		security.csrf(csrf->csrf.disable());
		
		security.formLogin(frmLogin->frmLogin.loginPage("/system/login")
						.defaultSuccessUrl("/board/getBoardList",true));
		
		security.exceptionHandling(ex->ex.accessDeniedPage("/system/accessDenied"));
		
		security.logout(logout->logout
				.logoutUrl("/system/logout")
				.invalidateHttpSession(true).logoutSuccessUrl("/"));
		
		return security.build();
	}
	@Bean 
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();

	}
}
