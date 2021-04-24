package com.example.algamoney.api.resource;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.model.Usuario;
import com.example.algamoney.api.security.auth.jwt.extractor.TokenExtractor;
import com.example.algamoney.api.security.auth.jwt.verifier.TokenVerifier;
import com.example.algamoney.api.security.config.JwtSettings;
import com.example.algamoney.api.security.config.WebSecurityConfig;
import com.example.algamoney.api.security.exceptions.InvalidJWTTokenException;
import com.example.algamoney.api.security.exceptions.MissingRefreshTokenCookieException;
import com.example.algamoney.api.security.model.UserContext;
import com.example.algamoney.api.security.model.token.JwtToken;
import com.example.algamoney.api.security.model.token.JwtTokenFactory;
import com.example.algamoney.api.security.model.token.RawAccessJwtToken;
import com.example.algamoney.api.security.model.token.RefreshToken;
import com.example.algamoney.api.service.UsuarioService;

/**
 * RefreshTokenEndpoint
 * 
 * @author vladimir.stankovic
 *
 *         Aug 17, 2016
 */
@RestController
public class RefreshTokenEndpoint {
	@Autowired
	private JwtTokenFactory tokenFactory;
	@Autowired
	private JwtSettings jwtSettings;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private TokenVerifier tokenVerifier;

	@RequestMapping(value = "/api/auth/token", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String refreshTokenOnCookie = null;
		if (req.getCookies() == null) {
			throw new MissingRefreshTokenCookieException();
		} else {
			for (Cookie cookie : req.getCookies()) {
				if (cookie.getName().equals("refreshToken")) {
					refreshTokenOnCookie = cookie.getValue();
				}
			}
		}

		RawAccessJwtToken rawToken = new RawAccessJwtToken(refreshTokenOnCookie);
		RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey())
				.orElseThrow(() -> new InvalidJWTTokenException());

		String jti = refreshToken.getJti();
		if (!tokenVerifier.verify(jti)) {
			throw new InvalidJWTTokenException();
		}

		String subject = refreshToken.getSubject();
		Usuario user = usuarioService.getByUsername(subject)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + subject));

		if (user.getRoles() == null)
			throw new InsufficientAuthenticationException("User has no roles assigned");
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
				.collect(Collectors.toList());

		UserContext userContext = UserContext.create(user.getUsername(), authorities);

		return tokenFactory.createAccessJwtToken(userContext);
	}

}