package com.prapps.tutorial.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Configuration
    @Order(1)
    public static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

		public static final String TOKEN_BASED_REST_ENTRY_POINT = "/rest/secured/**";
		public static final String TOKEN_BASED_SOAP_ENTRY_POINT = "/ws";

		@Autowired @Qualifier("webAuthenticationSuccessHandler") AuthenticationSuccessHandler webAuthenticationSuccessHandler;
		@Autowired RestAuthenticationManager restAuthenticationManager;

		@Override
		protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication()
				.withUser("admin").password("admin").roles("ADMIN")
				.and().withUser("user").password("user").roles("USER");
			restAuthenticationManager.setRestAuthenticationManager(auth);
		}

		/** Uncomment the following 2 methods and block the last method to enable Spring Security **/
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.csrf().ignoringAntMatchers("/rest/**", "/ws/**").and().authorizeRequests()
				.antMatchers("/login.html", "/rest/error", "/ws/**", "/rest/secured/manage", "/rest/secured/hello").permitAll()
				.antMatchers("/index.html").hasAnyRole("USER", "ADMIN")
				.antMatchers("/rest/secured/manage").hasAnyRole("ADMIN")
				.antMatchers("/rest/secured/hello").hasAnyRole("USER", "ADMIN")
				.and()
					.formLogin()
						.loginPage("/login.html").loginProcessingUrl("/login")
						.successHandler(webAuthenticationSuccessHandler)
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/index.html").invalidateHttpSession(true)
				.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler())
				.and().csrf().and()
				.authorizeRequests()
	                .antMatchers(TOKEN_BASED_REST_ENTRY_POINT).authenticated() // Protected API End-points
	                .and()
	                .addFilterBefore(createJwtTokenProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
	                .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
		};

		public JwtTokenProcessingFilter createJwtTokenProcessingFilter() {
			return new JwtTokenProcessingFilter(restAuthenticationManager, TOKEN_BASED_REST_ENTRY_POINT);
		}

		public AccessDeniedHandler accessDeniedHandler() {
			return new AccessDeniedHandler() {
				@Override
				public void handle(HttpServletRequest request, HttpServletResponse response,
						AccessDeniedException accessDeniedException) throws IOException, ServletException {
					if ("application/json".equals(request.getContentType())) {
						response.sendRedirect(request.getContextPath()+"/error/unauthorised");
					} else {
						response.sendRedirect(request.getContextPath()+"/error");
					}
				}
			};
		}
		
		@Bean
		public AuthenticationFailureHandler authenticationFailureHandler() {
			return new AuthenticationFailureHandler() {	
				@Override
				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
						AuthenticationException exception) throws IOException {
					if ("application/json".equals(request.getContentType())) {
						response.sendRedirect(request.getContextPath()+"/error/unauthorised");
					} else {
						response.sendRedirect("/error");
					}
				}
			};
		}
		
		/** Uncomment the following lines to disable and comment the above 2 methods Spring Security **/
		/*@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.csrf().ignoringAntMatchers("/rest/**", "/ws/**").and().authorizeRequests()
				.antMatchers("/**").permitAll();
		};*/
    }
}
