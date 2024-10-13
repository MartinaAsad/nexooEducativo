/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;



/**
 *
 * @author Martina
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    //en filterChain como se manjean las URL si tiene que estar logueado
    
    //userDetailsService cambiar a gusto
     @Bean
    public UserDetailsService userDetailsService(){

        UserDetails john = User.builder()
                .username("john")
                .password(passwordEncoder().encode("john"))
                .roles("USER")
                .build();

        UserDetails sam = User.builder()
                .username("sam")
                .password(passwordEncoder().encode("sam"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john,sam);
    }
}
