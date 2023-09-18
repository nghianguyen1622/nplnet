package com.npl.global.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String SPRING_SECURITY_FORM_COMID_KEY = "comid";
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
        throws AuthenticationException {

        if (!request.getMethod()
            .equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        UsernamePasswordAuthenticationToken authRequest = getAuthRequest(request);
        setDetails(request, authRequest);
        return this.getAuthenticationManager()
            .authenticate(authRequest);
    }

    private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String comid = obtainComid(request);

        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }
        if (comid == null) {
        	comid = "";
        }

        String usernameComid = String.format("%s%s%s", comid, 
            String.valueOf(Character.LINE_SEPARATOR), username.trim());
        return new UsernamePasswordAuthenticationToken(usernameComid, password);        
    }

    private String obtainComid(HttpServletRequest request) {
        return request.getParameter(SPRING_SECURITY_FORM_COMID_KEY);
    }
}