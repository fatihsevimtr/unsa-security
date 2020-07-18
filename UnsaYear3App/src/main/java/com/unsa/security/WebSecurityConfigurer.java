package com.unsa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import static com.unsa.security.UserRole.*;

@EnableWebSecurity
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()		
			.antMatchers("/","index","/css/*","/js/*").permitAll()
			.antMatchers("/api/**").hasRole(STUDENT.name())
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		
		UserDetails boby=User.builder()
								.username("boby")
								.password(encoder().encode("1234"))
								//.roles("STUDENT") //ROLE_STUDENT
								.roles(STUDENT.name())
								.build();
		
		UserDetails kyle=User.builder()
								.username("kyle")
								.password(encoder().encode("admin123"))
								//.roles("ADMIN")
								.roles(ADMIN.name())
								.build();
		
		UserDetails gabriel=User.builder()
				.username("gabriel")
				.password(encoder().encode("admin123"))
				//.roles("ADMIN")
				.roles(ADMINTRANEE.name())
				.build();
		
		return new InMemoryUserDetailsManager(boby,kyle,gabriel);
		
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(10);
	}

	
}
