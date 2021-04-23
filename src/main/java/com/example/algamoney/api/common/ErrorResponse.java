package com.example.algamoney.api.common;


import java.util.Date;

import org.springframework.http.HttpStatus;

/**
 * Error model for interacting with client.
 * 
 * @author antonio.vieira
 *
 * Apr 23, 2021
 */
public class ErrorResponse {
    // HTTP Response Status Code
    private final HttpStatus status;

    // General Error message
    private final String message;
    private final String detail;
    private final String type;

    // Error code
    private final ErrorCode errorCode;

    private final Date timestamp;

    protected ErrorResponse(final String message, final String detail, final String type, final ErrorCode errorCode, HttpStatus status) {
        this.message = message;
        this.detail = detail;
		this.type = type;
        this.errorCode = errorCode;
        this.status = status;
        this.timestamp = new java.util.Date();
    }

    public static ErrorResponse of(final String message, final String detail, final String type, final ErrorCode errorCode, HttpStatus status) {
        return new ErrorResponse(message, detail, type, errorCode, status);
    }

    public Integer getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

	public String getDetail() {
		return detail;
	}

	public String getType() {
		return type;
	}
    
}