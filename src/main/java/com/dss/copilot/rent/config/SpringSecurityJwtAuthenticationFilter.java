package com.dss.copilot.rent.config;

import com.dss.copilot.rent.model.data.UserLoginData;
import com.dss.copilot.rent.model.entity.User;
import com.dss.copilot.rent.model.repository.UserDao;
import com.dss.copilot.rent.model.response.Response;
import com.dss.copilot.rent.model.response.ResponseUserLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

/**
 * SpringSecurityJwtAuthenticationFilter class.
 * extend UsernamePasswordAuthenticationFilter.
 * add constructor with AuthenticationManager object as parameter.
 * override attemptAuthentication method.
 * override successfulAuthentication method.
 * override unsuccessfulAuthentication method.
 * inject UserDao using constructor injection.
 * add Logger object.
 */

public class SpringSecurityJwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringSecurityJwtAuthenticationFilter.class);
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;

    public SpringSecurityJwtAuthenticationFilter(AuthenticationManager authenticationManager, UserDao userDao) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        LOGGER.info("Attempting authentication");
        String userEmail = null;
        String userPassword = null;
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            userEmail = user.getUserEmail();
            userPassword = user.getUserPassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, userPassword));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {

        LOGGER.info("Authentication successful");
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        String userEmail = user.getUsername();
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();

        Claims claims = Jwts.claims()
                .add("authorities", new ObjectMapper().writeValueAsString(authorities))
                .add("userEmail", userEmail).build();

        String jwt = Jwts.builder()
                .subject(userEmail).signWith(TokenJwtConfig.SECRET_KEY)
                .setClaims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + TokenJwtConfig.EXPIRATION_TIME))
                .compact();

        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);

        User userEntity = userDao.findByUserEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));

        ResponseUserLogin responseUserLogin = ResponseUserLogin.builder().build();
        responseUserLogin.setStatus("success");
        responseUserLogin.setMessage("Login successful");
        responseUserLogin.setData(UserLoginData.builder().userName(userEntity.getUserName()).userEmail(userEmail)
                .proofId(userEntity.getProofId()).build());

        response.getWriter().write(new ObjectMapper().writeValueAsString(responseUserLogin));
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        LOGGER.error("Authentication failed");
        Response userLoginResponse = new Response();
        userLoginResponse.setStatus("error");
        userLoginResponse.setMessage("Authentication failed, check your credentials");
        response.getWriter().write(new ObjectMapper().writeValueAsString(userLoginResponse));
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
