package fr.caensup.sio.todolist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class webConfiguration {

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeHttpRequests()
		//.requestMatchers("/master/**").hasRole("manager")
		.anyRequest().authenticated()
		.and()
		.formLogin();
		return http.build();
		}
}
