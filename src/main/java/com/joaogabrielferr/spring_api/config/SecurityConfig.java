package com.joaogabrielferr.spring_api.config;

import com.joaogabrielferr.spring_api.security.jwt.JwtTokenFilter;
import com.joaogabrielferr.spring_api.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){

          return new BCryptPasswordEncoder();

//        Map<String,PasswordEncoder> encoderMap = new HashMap<>();
//        encoderMap.put("pbkdf2", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
//        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2",encoderMap);
//
//        passwordEncoder.setDefaultPasswordEncoderForMatches(Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
//
//        return passwordEncoder;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers(
                                        "/auth/login",
                                        "/auth/signup"
//                                        "/swagger-ui/**",
                                ).permitAll()
                                .requestMatchers("/api/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE,"/api/person/v1/**").hasRole("ADMIN")
                )
                .cors(cors->{})
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }


}
