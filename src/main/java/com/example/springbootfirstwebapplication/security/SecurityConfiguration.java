package com.example.springbootfirstwebapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//конфигурационный файл который создает доступ по каким ссылкам кт оможет входить куда
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter { // configuration file will help to add more configuration
//override few methods to add our own security configuration

    //Create user in28Minutes/dummy
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        //we will b eusing in memory authentication and Added one user in28Minutes/dummy
        auth.inMemoryAuthentication().withUser("in28Minutes").password("{noop}dummy").roles("USER", "ADMIN");
    }

    //Create a login form / show it when user is not authorized
    //by overriding a method
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
                .formLogin();
    }

    //saying for /login permit anybody
    //but if someone wants to go to any TO DO pages or root page we need to see if he has a role of USER
    // if he does not have a user - show him a form login

}
