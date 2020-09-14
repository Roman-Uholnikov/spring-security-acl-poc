package com.conductor.acl.poc.web;


import com.conductor.acl.poc.persistence.security.JwtTokenUtil;
import com.conductor.acl.poc.persistence.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    /**
     * Method generates authentication JWT token for the provided user|password
     * Ideally this functionality should happen in MONOLITH. And then JWT token is passed to service
     *
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Map<String, Object> authenticationRequest) throws Exception {
        String user = String.valueOf(authenticationRequest.get("user"));

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user);

        final String token = jwtTokenUtil.generateToken(userDetails, authenticationRequest);
        return ResponseEntity.ok("Bearer " + token);
    }

}