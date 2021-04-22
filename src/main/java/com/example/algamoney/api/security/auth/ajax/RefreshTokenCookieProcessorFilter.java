package com.example.algamoney.api.security.auth.ajax;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.algamoney.api.security.config.WebSecurityConfig;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookieProcessorFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		if (req.getRequestURI().equalsIgnoreCase(WebSecurityConfig.REFRESH_TOKEN_URL)
				&& req.getCookies() != null) {
			for (Cookie cookie : req.getCookies()) {
				if (cookie.getName().equals("refreshToken")) {
					String refreshToken = cookie.getValue();
					req = new MyServletRequestWrapper(req, refreshToken);
				}
			}
			 
		}
		
		chain.doFilter(req, response);
	}
	
	static class MyServletRequestWrapper extends HttpServletRequestWrapper {

		private String refreshToken;
		
		public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
			super(request);
			this.refreshToken = refreshToken;
		}
			
		@Override
		public Map<String, String[]> getParameterMap(){
			ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
			map.put("refreshToken", new String[] { refreshToken });
			map.setLocked(true);
			return map;
		}
		
	}
}
