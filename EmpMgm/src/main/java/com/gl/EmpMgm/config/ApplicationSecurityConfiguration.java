package com.gl.EmpMgm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsService domainUserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		authenticationManagerBuilder
							.userDetailsService(this.domainUserDetailsService)
							.passwordEncoder(passwordEncoder());
		}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
				.disable()
			.cors()
				.disable()
			.headers()
				.frameOptions().disable();
		http
			.authorizeRequests()
				.antMatchers("/login**", "/logout**", "/contact-us**","/h2-console/**","/login/*")
					.permitAll()
				.antMatchers(HttpMethod.POST, "/api/employees**")
					.hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/employees**")
					.hasAnyRole("USER","ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/employees**")
					.hasAnyRole("USER","ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/employees/**")
					.hasAnyRole("USER","ADMIN")
				.anyRequest()
					.authenticated()
			.and()
				.formLogin()
				.and()
				.httpBasic();
				
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
