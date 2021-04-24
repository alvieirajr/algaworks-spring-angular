package com.example.algamoney.api.security.exceptions;

import org.springframework.security.core.AuthenticationException;

import com.example.algamoney.api.security.model.token.JwtToken;


/**
 * 
 * @author vladimir.stankovic
 *
 * Aug 3, 2016
 */
public class JWTExpiredTokenException extends AuthenticationException {
    private static final long serialVersionUID = -5959543783324224864L;
    
    private JwtToken token;

    public JWTExpiredTokenException(String msg) {
        super(msg);
    }

    public JWTExpiredTokenException(JwtToken token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String token() {
        return this.token.getToken();
    }
}
