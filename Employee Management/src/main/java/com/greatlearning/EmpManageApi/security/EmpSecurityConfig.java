package com.greatlearning.EmpManageApi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.greatlearning.EmpManageApi.serviceImpl.AppUserServiceImpl;

@Configuration
@EnableWebSecurity
public class EmpSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/users/add", "/roles/add").permitAll()
				.antMatchers("/employee/add", "/employee/delete/**").hasAnyAuthority("ADMIN")
				.antMatchers("/employee/editById/**", "/employee/searchById/**", "/employee/list",
						"/employee/searchByName/**", "/employee/sort/**")
				.hasAnyAuthority("USER", "ADMIN").antMatchers("/employee/**").hasAuthority("TESTER")
				.antMatchers("/employee/list", "/home").permitAll().anyRequest().authenticated().and().httpBasic().and()
				.cors().and().csrf().disable();

		http.authenticationProvider(authenticationProvider());

		return http.build();

	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(PasswordEncoder());
		return authenticationProvider;
	}

	@Bean
	public BCryptPasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new AppUserServiceImpl();
	}

}