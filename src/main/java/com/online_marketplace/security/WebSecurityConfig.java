package com.online_marketplace.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

import com.online_marketplace.exception.CustomAccessDeniedHandler;
import com.online_marketplace.exception.CustomBasicAuthenticationEntryPoint;
import com.online_marketplace.filter.CsrfCookieFilter;
import com.online_marketplace.filter.JWTTokenGeneratorFilter;
import com.online_marketplace.filter.JWTTokenValidatorFilter;
import com.online_marketplace.filter.RequestValidationBeforeFilter;
import com.online_marketplace.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                .csrf(csrfConfig -> {
                    try {
                        csrfConfig.disable()/*.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                                .ignoringRequestMatchers("/contact", "/auth/login", "/apiLogin", "/category/add")
                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                        .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)*/
                        .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                        .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                        .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                        //.requiresChannel(rcc -> rcc.anyRequest().requiresSecure()) // Only HTTPS
                        .authorizeHttpRequests((requests) -> requests/*.requestMatchers("/auth/**").permitAll()
                                                                    .requestMatchers("/products/find/**").hasRole("BUYER")

                                                                    
                                                                    .anyRequest().authenticated()*/.anyRequest().permitAll());
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                });
        http.formLogin(withDefaults());
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


      @Bean
    public AuthenticationManager authenticationManager(UserService userDetailsService,
            PasswordEncoder passwordEncoder) {
        ApplicationUsernamePasswordProvider authenticationProvider =
                new ApplicationUsernamePasswordProvider(userDetailsService, passwordEncoder);
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return  providerManager;
    }
}
