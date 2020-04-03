package com.cebrail.kodgemisi.hr.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(1)
public class ManagerSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("manager").password(passwordEncoder().encode("manager")).roles("MANAGER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/manager/**")
                .authorizeRequests()
                .anyRequest().hasRole("MANAGER")

                .and()
                .formLogin()
                .loginPage("/manager/login").permitAll()
                .defaultSuccessUrl("/manager/home").permitAll()
                .and()
                .logout()
                .logoutUrl("/manager/logout")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")
                .and();
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}