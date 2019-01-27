package com.til.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private AuthenticationEntryPoint authEntryPoint;
	@Autowired
	APIConfiguration apiConfiguration;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.anyRequest().authenticated()
				.and().httpBasic()
				.authenticationEntryPoint(authEntryPoint);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(new CustomPasswordEncoder().decode("dXNlcg==")).password("{noop}"+new CustomPasswordEncoder().decode("cGFzcw==")).roles("USER");
	}


	public static void main(String[] args) {
		System.out.println(new CustomPasswordEncoder().encode("pass"));
	}
}

class CustomPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence charSequence) {
		return new String(java.util.Base64.getEncoder().encode(charSequence.toString().trim().getBytes()));
	}

	public String decode(CharSequence charSequence) {
		return new String(java.util.Base64.getDecoder().decode(charSequence.toString().trim().getBytes(StandardCharsets.UTF_8)));
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return new String(java.util.Base64.getEncoder().encode(rawPassword.toString().getBytes())).equals(encodedPassword);
	}
}