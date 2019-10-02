package com.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.spring.*")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*@Autowired 
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception { 
		auth.inMemoryAuthentication().withUser("john").password("11 ").roles("USER"); 
		auth.inMemoryAuthentication().withUser("admin").password("22 ").roles("USER","ADMIN"); 
	}*/
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser("user1").password("{noop}11").roles("USER", "ADMIN")
          .and()
          .withUser("user2").password("{noop}22").roles("USER", "ADMIN")
          .and()
          .withUser("admin").password("{noop}admin").roles("ADMIN");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.formLogin()
		.loginPage("/login").usernameParameter("userId").passwordParameter("password")
		.permitAll();
		
		http.formLogin().defaultSuccessUrl("/product/all").failureUrl("/login?error");
		
		http.authorizeRequests()
		.antMatchers("/resources/**", "/about", "/login").permitAll()
		.antMatchers("/hello/**").hasRole("ADMIN")
		.antMatchers("/product/**").hasRole("USER")
		.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
		.anyRequest().authenticated()
		.and().formLogin();
		
		http.exceptionHandling().accessDeniedPage("/about");
		http.logout().logoutSuccessUrl("/login?logout");
		
		http.csrf().disable();
	}
}
