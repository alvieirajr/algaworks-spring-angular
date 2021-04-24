package com.example.algamoney.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.algamoney.api.config.property.AlgaMoneyApiProperty;

/**
 * CustomCorsFilter
 * 
 * @author vladimir.stankovic
 *
 * Aug 3, 2016
 */
public class CustomCorsFilter extends CorsFilter {

	@Autowired
	private static AlgaMoneyApiProperty algaMoneyApiProperty = new AlgaMoneyApiProperty();
	
    public CustomCorsFilter() {
        super(configurationSource());
    }

    private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(algaMoneyApiProperty.getAllowedOrigin());
        config.addAllowedHeader("*");
        config.setMaxAge(36000L);
        config.setAllowedMethods(Arrays.asList("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);
        return source;
    }
}
