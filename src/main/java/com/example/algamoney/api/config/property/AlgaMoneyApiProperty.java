package com.example.algamoney.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("algamoney")
public class AlgaMoneyApiProperty {

	private final Security security  = new Security(); 
	private String allowedOrigin = "http://localhost8080";
	
	public String getAllowedOrigin() {
		return allowedOrigin;
	}

	public void setAllowedOrigin(String allowedOrigin) {
		this.allowedOrigin = allowedOrigin;
	}

	public Security getSecurity() {
		return security;
	}

	public static class Security {
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
	}
	
	
	
}
