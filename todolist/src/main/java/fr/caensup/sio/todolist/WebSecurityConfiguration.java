package fr.caensup.sio.todolist;

	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.security.web.SecurityFilterChain;
	import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

	import fr.caensup.sio.todolist.services.DbUserService;

	@Configuration
	@EnableWebSecurity
	public class WebSecurityConfiguration {
        @Bean
        SecurityFilterChain configure(HttpSecurity http) throws Exception {
	        http.csrf().disable().authorizeHttpRequests()
	        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"),
	        		AntPathRequestMatcher.antMatcher("/createUser/**"),
	        		AntPathRequestMatcher.antMatcher("/login/**")
	        		).permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .formLogin().loginPage("/login")
	            .and()
	            .headers().frameOptions().sameOrigin();
	        return http.build();
	        
	        /*
	         http.csrf(csrf -> csrf.disable()).authorizeHttpRequests()
                    .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"),
                            AntPathRequestMatcher.antMatcher("/createUser/**"),
                            AntPathRequestMatcher.antMatcher("/login/**")
                    ).permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin(login -> login.loginPage("/login"))
                    .headers(headers -> headers.frameOptions().sameOrigin());
	        return http.build();
	         */
	    	
	    }
	    
	    @Bean
	    UserDetailsService getUserDetailsService() {
	    	return new DbUserService();
	    }
	    
	    @Bean
	    PasswordEncoder getPasswordEncoder() {
	    	return new BCryptPasswordEncoder();
	    }

}
