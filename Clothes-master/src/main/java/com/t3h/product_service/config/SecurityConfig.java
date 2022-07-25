package com.savvycom.product_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
prePostEnabled = true,
securedEnabled = true,
jsr250Enabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	 @Override
     public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/swagger-ui/index.html");
     }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
                .antMatchers("/user2").hasRole("ADMIN");
    }

}