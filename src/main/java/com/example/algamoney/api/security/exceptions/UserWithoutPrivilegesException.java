package com.example.algamoney.api.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserWithoutPrivilegesException extends AuthenticationException {

	/**
	 * Constructs an <code>UserWithoutPrivilegesException</code> with the specified
	 * message.
	 * @param msg the detail message
	 */
	public UserWithoutPrivilegesException(String msg) {
		super(msg);
	}

	/**
	 * Constructs an <code>UserWithoutPrivilegesException</code> with the specified
	 * message and root cause.
	 * @param msg the detail message
	 * @param cause root cause
	 */
	public UserWithoutPrivilegesException(String msg, Throwable cause) {
		super(msg, cause);
	}



}
