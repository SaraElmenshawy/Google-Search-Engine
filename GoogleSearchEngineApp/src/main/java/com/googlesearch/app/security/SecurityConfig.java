package com.googlesearch.app.security;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		JSONParser parser = new JSONParser();

		Object obj =new Object();
		try {
			obj = parser.parse(new FileReader("..\\GoogleSearchEngineApp\\src\\main\\resources\\templates\\Credentials.json"));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
 
		JSONObject jsonObject = (JSONObject) obj;
		
		String UserName=(String) jsonObject.get("UserName");
		String Password=(String) jsonObject.get("Password");
		auth.inMemoryAuthentication().withUser(UserName).password("{noop}"+Password).roles("ADMIN");
	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
	}
	
}
