package com.dss.copilot.rent.config;

import com.dss.copilot.rent.model.repository.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * add @Configuration annotation.
 * add @EnableWebSecurity annotation.
 * add filterChain method. Return SecurityFilterChain object. Add HttpSecurity object as parameter. add Throws Exception to method signature.
 * add passwordEncoder method. Return PasswordEncoder object.
 * add inMemoryUserDetailsManager method. Return UserDetailsManager object.
 * add @Bean annotation to passwordEncoder method, inMemoryUserDetailsManager method and filterChain method.
 * inject AuthenticatioConfiguration using constructor injection.
 * inject UserDao using constructor injection.
 * add authenticationManager method. Return AuthenticationManager object. Add Throws Exception to method signature. Add @Bean annotation.
 */
@Configuration
public class SpringSecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserDao userDao;

    public SpringSecurityConfig(AuthenticationConfiguration authenticationConfiguration, UserDao userDao) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.userDao = userDao;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                                .requestMatchers(HttpMethod.GET, "/getPackages").permitAll()
                                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilter(new SpringSecurityJwtAuthenticationFilter(authenticationManager(), userDao))
                .addFilter(new SpringSecurityJwtValidationFilter(authenticationManager()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
