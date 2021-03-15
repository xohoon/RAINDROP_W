package dev.drop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import dev.drop.models.member.service.MemberService;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity //Spring Security 활성화
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter { // Security setting class
	private MemberService memberService;

	@Bean // password security using encoder 미리 빈으로 등록 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void configure(WebSecurity web) throws Exception { // 21 line override
		// static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// 페이지 권한 설정
				.antMatchers("test", "url").permitAll() // 누구나 접근 허용
				.antMatchers("/admin/**").hasRole("ADMIN") // ADMIN만 접근
				.antMatchers("/member/myinfo").hasRole("MEMBER") // MEMBER, ADMIN 접근
				.antMatchers("/**")
				.permitAll()
				
			.and() // 로그인 설정
				.formLogin()
				.loginPage("/member/signin")
				.defaultSuccessUrl("/member/signin/result") // login direct url
				.permitAll()
				
			.and() // 로그아웃 설정
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				 // 로그아웃을 실행 할 주소
				.logoutSuccessUrl("/member/logout/result") // logout direct url
				.invalidateHttpSession(true) // session
				
			.and()
				.exceptionHandling()
				.accessDeniedPage("/member/denied"); // 403 예외처리 핸들링
	}

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	}

	/*
	 * 
	 * 2.** 버전부터 authenticationManager를 노출시키기 위해 WebSecurityConfigurerAdapter의
	 * authenticationManagerBean를 오버라이드 하고 bean으로 등록 후에 사용 해야 한다고 한다.
	 * 
	 */

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
