package com.googlesearch.app.security;

import org.json.simple.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.googlesearch.app.general.JsonTemplatesReader;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		//read credentials json file
		String filePath="..\\GoogleSearchEngineApp\\src\\main\\resources\\templates\\Credentials.json";
		JSONObject jsonObject = JsonTemplatesReader.jsonFileReader(filePath);	
		
		String UserName=(String) jsonObject.get("UserName");
		String Password=(String) jsonObject.get("Password");
		
		//basic authentication using username and password saved in the credentials file.
		auth.inMemoryAuthentication().withUser(UserName).password("{noop}"+Password).roles("ADMIN");
	
	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
	}
	
}
