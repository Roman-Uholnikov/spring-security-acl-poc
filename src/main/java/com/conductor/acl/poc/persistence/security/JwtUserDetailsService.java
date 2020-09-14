package com.conductor.acl.poc.persistence.security;


import com.conductor.acl.poc.persistence.dao.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This service provide user details based on received JWT token
 */
@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRolesRepository userRolesRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //I expect that checking user identity should happen on monolith side
        // here we trust that it is user specified in JWT taken
        // No password checking (better option should be implemented here)
        List<SimpleGrantedAuthority> roles = userRolesRepository.findByUserId(Integer.valueOf(s))
                .stream()
                .map(e -> new SimpleGrantedAuthority(e.getRole()))
                .collect(Collectors.toList());
        return new User(s, "password", roles);
    }

    public UserDetails loadUserById(int i) throws UsernameNotFoundException {
        //I expect that checking user identity should happen on monolith side
        // here we trust that it is user specified in JWT taken
        // No password checking (better option should be implemented here)
        List<SimpleGrantedAuthority> roles = userRolesRepository.findByUserId(i)
                .stream()
                .map(e -> new SimpleGrantedAuthority(e.getRole()))
                .collect(Collectors.toList());
        return new User(String.valueOf(i), "password", roles);
    }
}
