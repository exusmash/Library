package com.example.springproject.filmLibrary.config;

import com.example.springproject.filmLibrary.service.userdetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import static com.example.springproject.filmLibrary.constants.SecurityConstants.*;
import static com.example.springproject.filmLibrary.constants.UserRolesConstants.ADMIN;
import static com.example.springproject.filmLibrary.constants.UserRolesConstants.FILMER;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final CustomUserDetailsService customUserDetailsService;

    public WebSecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder, CustomUserDetailsService customUserDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(RESOURCES_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(FILMS_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(USER_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(FILMS_PERMISSIONS_LIST.toArray(String[]::new)).hasAnyRole(ADMIN, FILMER)
                        .anyRequest().authenticated()
                )
                //Настройка для входа в систему
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/main")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                );

        return httpSecurity.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}

