package com.xuebaclass.sato.config;

import org.keycloak.adapters.AdapterDeploymentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.xuebaclass.sato.oauth2.KeycloakRestTemplateFactory;

/**
 * RestTemplate
 * 
 * @author lee
 * @since 2017-07-13
 */
@Configuration
public class RestTemplateConfig {
	private static final Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);

	@Bean
	public RestTemplate restTemplate(@Autowired AdapterDeploymentContext keycloakContext) {
		if (logger.isInfoEnabled()) {
			logger.info("init sato-rest-template");
		}

		RestTemplate template = KeycloakRestTemplateFactory
				.createOAuth2RestTemplate(keycloakContext.resolveDeployment(null));
		// template.setErrorHandler(new MyResponseErrorHandler());
		// SimpleClientHttpRequestFactory factory =
		// (SimpleClientHttpRequestFactory)template.getRequestFactory();
		// factory.setConnectTimeout(30 * 1000);
		// factory.setReadTimeout(30 * 1000);
		return template;
	}
}
