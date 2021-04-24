package com.example.algamoney.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.algamoney.api.common.ErrorCode;
import com.example.algamoney.api.common.ErrorResponse;
import com.example.algamoney.api.security.exceptions.InvalidJwtToken;
import com.example.algamoney.api.security.exceptions.JWTTokenWithoutUsernameException;
import com.example.algamoney.api.security.exceptions.MissingRefreshTokenCookieException;
import com.example.algamoney.api.security.exceptions.UserWithoutPrivilegesException;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String detail = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<ErrorResponse> erros = Arrays.asList(ErrorResponse.of(message, detail, ex.getClass().getName(), ErrorCode.GLOBAL, HttpStatus.BAD_REQUEST));

		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);

	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		List<ErrorResponse> erros = criarListaErro(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, status, request);
	}
	
	private List<ErrorResponse> criarListaErro(BindingResult bindingResult) {
		List<ErrorResponse> errors = new ArrayList();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String detail = fieldError.toString();

			errors.add(ErrorResponse.of(message, detail, "MethodArgumentNotValidException", ErrorCode.GLOBAL, HttpStatus.BAD_REQUEST));
		}
		return errors;
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest req) {
		
		String message = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String detail = ex.toString();
		List<ErrorResponse> erros = Arrays.asList(ErrorResponse.of(message, detail, ex.getClass().getName(), ErrorCode.GLOBAL, HttpStatus.NOT_FOUND));

		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, req);
		
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String message = messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
		String detail = ExceptionUtils.getRootCauseMessage(ex);
		List<ErrorResponse> erros = Arrays.asList(ErrorResponse.of(message, detail, ex.getClass().getName(), ErrorCode.GLOBAL, HttpStatus.BAD_REQUEST));
	
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ MissingRefreshTokenCookieException.class })
	public ResponseEntity<Object> handleMissingRefreshTokenCookieException(MissingRefreshTokenCookieException ex, WebRequest request) {
		String message = messageSource.getMessage("recurso.MissingRefreshTokenCookieException", null, LocaleContextHolder.getLocale());
		String detail = ExceptionUtils.getRootCauseMessage(ex);
		List<ErrorResponse> erros = Arrays.asList(ErrorResponse.of(message, detail, ex.getClass().getName(), ErrorCode.AUTHENTICATION, HttpStatus.BAD_REQUEST));
			
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ InvalidJwtToken.class })
	public ResponseEntity<Object> handleInvalidJwtToken(InvalidJwtToken ex, WebRequest request) {
		String message = messageSource.getMessage("recurso.InvalidJwtToken", null, LocaleContextHolder.getLocale());
		String detail = ExceptionUtils.getRootCauseMessage(ex);
		List<ErrorResponse> erros = Arrays.asList(ErrorResponse.of(message, detail, ex.getClass().getName(), ErrorCode.AUTHENTICATION, HttpStatus.BAD_REQUEST));
			
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ BadCredentialsException.class })
	public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
		String message = messageSource.getMessage("recurso.BadCredentialsException", null, LocaleContextHolder.getLocale());
		String detail = ExceptionUtils.getRootCauseMessage(ex);
		List<ErrorResponse> erros = Arrays.asList(ErrorResponse.of(message, detail, ex.getClass().getName(), ErrorCode.AUTHENTICATION, HttpStatus.BAD_REQUEST));
			
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ UsernameNotFoundException.class })
	public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
		String message = messageSource.getMessage("recurso.UsernameNotFoundException", null, LocaleContextHolder.getLocale());
		String detail = ExceptionUtils.getRootCauseMessage(ex);
		List<ErrorResponse> erros = Arrays.asList(ErrorResponse.of(message, detail, ex.getClass().getName(), ErrorCode.AUTHENTICATION, HttpStatus.BAD_REQUEST));
			
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}	
	
	@ExceptionHandler({ InsufficientAuthenticationException.class })
	public ResponseEntity<Object> handleInsufficientAuthenticationException(InsufficientAuthenticationException ex, WebRequest request) {
		String message = messageSource.getMessage("recurso.InsufficientAuthenticationException", null, LocaleContextHolder.getLocale());
		String detail = ExceptionUtils.getRootCauseMessage(ex);
		List<ErrorResponse> erros = Arrays.asList(ErrorResponse.of(message, detail, ex.getClass().getName(), ErrorCode.AUTHENTICATION, HttpStatus.BAD_REQUEST));
			
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}		
	
	@ExceptionHandler({ JWTTokenWithoutUsernameException.class })
	public ResponseEntity<Object> handleJWTTokenWithoutUsernameException(JWTTokenWithoutUsernameException ex, WebRequest request) {
		String message = ex.getMessage();
		String detail = ExceptionUtils.getRootCauseMessage(ex);
		List<ErrorResponse> erros = Arrays.asList(ErrorResponse.of(message, detail, ex.getClass().getName(), ErrorCode.AUTHENTICATION, HttpStatus.BAD_REQUEST));
			
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}		
	
	
	@ExceptionHandler({ UserWithoutPrivilegesException.class })
	public ResponseEntity<Object> handleUserWithoutPrivilegesException(UserWithoutPrivilegesException ex, WebRequest request) {
		String message = ex.getMessage();
		String detail = ExceptionUtils.getRootCauseMessage(ex);
		List<ErrorResponse> erros = Arrays.asList(ErrorResponse.of(message, detail, ex.getClass().getName(), ErrorCode.GLOBAL, HttpStatus.INTERNAL_SERVER_ERROR));
			
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}		
	
}
