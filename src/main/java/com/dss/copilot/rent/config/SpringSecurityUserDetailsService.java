package com.dss.copilot.rent.config;

/**
 * implement UserDetailsService
 * add @Service annotation
 * implement loadUserByUsername method
 * inject UserDao using constructor injection
 */

import com.dss.copilot.rent.model.repository.UserDao;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class SpringSecurityUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    public SpringSecurityUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        return userDao.findByUserEmail(userEmail)
                .map(user -> org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUserEmail())
                        .password(user.getUserPassword())
                        .authorities(user.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority(role.getName()))
                                .collect(Collectors.toList()))
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}