package com.prapps.tutorial.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Configuration
    @Order(1)
    public static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Autowired AccessDeniedHandler accessDeniedHandler;
		
		@Override
		protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers("/", "/index.html").permitAll()
				.antMatchers("/manage/login").permitAll()
				.antMatchers("/manage/**").hasAnyRole("ADMIN")
				.and()
					.formLogin()
						.loginPage("/manage/login").permitAll()
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
				.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		};
    }
}
