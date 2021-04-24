package com.example.algamoney.api.security.auth.ajax;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.example.algamoney.api.common.ErrorCode;
import com.example.algamoney.api.common.ErrorResponse;
import com.example.algamoney.api.security.exceptions.AuthMethodNotSupportedException;
import com.example.algamoney.api.security.exceptions.JWTExpiredTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 
 * @author vladimir.stankovic
 *
 * Aug 3, 2016
 */
@Component
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper mapper;
    
    @Autowired
    public AjaxAwareAuthenticationFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }	
    
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		String detail = e.getCause() != null ? e.getCause().toString() : e.toString();
		
		if (e instanceof BadCredentialsException) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			mapper.writeValue(response.getWriter(), ErrorResponse.of(e.getMessage(), detail, e.getClass().getName(), ErrorCode.AUTHENTICATION, HttpStatus.BAD_REQUEST));
		} else if (e instanceof JWTExpiredTokenException) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			mapper.writeValue(response.getWriter(), ErrorResponse.of("Token has expired", detail, e.getClass().getName(), ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
		} else if (e instanceof AuthMethodNotSupportedException) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		    mapper.writeValue(response.getWriter(), ErrorResponse.of(e.getMessage(), detail, e.getClass().getName(), ErrorCode.AUTHENTICATION, HttpStatus.BAD_REQUEST));
		} else if (e instanceof UsernameNotFoundException) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		    mapper.writeValue(response.getWriter(), ErrorResponse.of(e.getMessage(), detail, e.getClass().getName(), ErrorCode.AUTHENTICATION, HttpStatus.BAD_REQUEST));			
		} else if (e instanceof AuthenticationServiceException) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			mapper.writeValue(response.getWriter(), ErrorResponse.of(e.getMessage(), detail, e.getClass().getName(), ErrorCode.AUTHENTICATION, HttpStatus.BAD_REQUEST));		
		} else {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			mapper.writeValue(response.getWriter(), ErrorResponse.of("Authentication failed", detail, e.getClass().getName(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		}
	}
}