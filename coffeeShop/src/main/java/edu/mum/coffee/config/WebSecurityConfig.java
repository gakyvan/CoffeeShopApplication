package edu.mum.coffee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/signin","/user/createProfile", "/user/userSignUpForm", "/css/**", "/img/**").permitAll();

		http.authorizeRequests().antMatchers("/product/all","/product/edit","/person/**", "/persons/**", "/orders/**")
				.hasRole("ADMINISTRATOR").anyRequest().authenticated().and().formLogin().loginPage("/signin")
				.loginProcessingUrl("/authenticateTheUser").permitAll().defaultSuccessUrl("/product/all").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").permitAll();
		
		http.authorizeRequests().antMatchers("/user/userDetails","/user/edit", "/product/menu").hasRole("CUSTOMER").anyRequest().authenticated().and().formLogin().loginPage("/signin")
		.loginProcessingUrl("/authenticateTheUser").permitAll().defaultSuccessUrl("/product/menu").and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("super").password("pw").roles("ADMINISTRATOR");
		auth.inMemoryAuthentication().withUser("gakyvan").password("password").roles("CUSTOMER");
	}
}