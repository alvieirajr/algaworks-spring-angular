package com.example.algamoney.api.security.exceptions;

public class JWTTokenWithoutUsernameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1531311935587264099L;

	public JWTTokenWithoutUsernameException(String msg) {
		super(msg);
	}

	public JWTTokenWithoutUsernameException() {
		// TODO Auto-generated constructor stub
	}

}
