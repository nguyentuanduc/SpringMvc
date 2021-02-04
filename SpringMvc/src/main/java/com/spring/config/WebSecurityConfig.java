package com.spring.config;

import com.spring.authority.UserDetailsServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	 /* @Autowired 
	  public void configureGlobalSecurity(AuthenticationManagerBuilder
	  auth) throws Exception {
	  auth.inMemoryAuthentication().withUser("john").password("11 ").roles("USER");
	  auth.inMemoryAuthentication().withUser("admin").password("22 ").roles("USER",
	  "ADMIN"); }*/
	  
	  
	 /* @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		  auth.inMemoryAuthentication()
	  .withUser("user1").password("{noop}11").roles("USER") .and()
	  .withUser("user2").password("{noop}22").roles("USER") .and()
	  .withUser("admin").password("{noop}admin").roles("USER", "ADMIN"); 
		  }*/
	 

	@Autowired
	private UserDetailsServiceImp userDetailsServiceImp;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};
	
	
	/*@Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsServiceImp).passwordEncoder(passwordEncoder());
	  }*/
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.formLogin()
		.loginPage("/login").usernameParameter("username").passwordParameter("password")
		.permitAll();
		
		http.formLogin().defaultSuccessUrl("/product/all").failureUrl("/login?error");
		
		http.authorizeRequests()
		.antMatchers("/resources/**", "/about", "/","/signup", "/login", "/logingoogle", "/login-google", "/login-facebook", "/user-google", "/admin-google", "/403", "/rest/**").permitAll()
		.antMatchers("/hello/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/product/**").access("hasRole('ROLE_USER')")
		.antMatchers("/db/**").access("hasRole('ROLE_ADMIN') and hasRole('DBA')")
		.anyRequest().authenticated()
		.and().formLogin();
		
		http.exceptionHandling().accessDeniedPage("/deny");
		http.logout().logoutSuccessUrl("/login?logout");
		
		
		http.csrf().disable();
	}
	
	
	@Override
	   public UserDetailsService userDetailsService() {
	       return userDetailsServiceImp;
	   }
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").usernameParameter("userId").passwordParameter("password").permitAll();

		http.formLogin().defaultSuccessUrl("/product/all").failureUrl("/login?error");

		http.authorizeRequests().antMatchers("/resources/**", "/about", "/login").permitAll().
		antMatchers("/hello/**")
				.hasRole("ADMIN").
		antMatchers("/product/**").hasRole("USER").antMatchers("/db/**")
				.access("hasRole('ADMIN') and hasRole('DBA')").anyRequest().authenticated().and().formLogin();

		http.exceptionHandling().accessDeniedPage("/about");
		http.logout().logoutSuccessUrl("/login?logout");

		http.csrf().disable();
	}*/
}
