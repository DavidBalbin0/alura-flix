package com.challenge.aluraflix.configuration

import com.challenge.aluraflix.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration(){
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain{
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().build()
    }
    @Bean
    fun authMananger(configuration: AuthenticationConfiguration): AuthenticationManager{
        return configuration.authenticationManager
    }
    @Bean
    fun passwordEncoder(): PasswordEncoder{
        return BCryptPasswordEncoder();
    }
}