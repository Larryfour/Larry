package com.xuebaclass.sato.config;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.xuebaclass.sato.logging.AccessLoggingInteceptor;
import com.xuebaclass.sato.exception.CrmException;

import cz.jirutka.spring.exhandler.RestHandlerExceptionResolver;

/**
 * 监控异常统一处理
 * 
 * @author lee
 * @since 2017-07-13
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public RestHandlerExceptionResolver restExceptionResolver() {
		return RestHandlerExceptionResolver.builder().messageSource(httpErrorMessageSource())
				.defaultContentType(APPLICATION_JSON).addErrorMessageHandler(CrmException.class, BAD_REQUEST)
				.build();
	}

	@Bean
	public MessageSource httpErrorMessageSource() {
		ReloadableResourceBundleMessageSource m = new ReloadableResourceBundleMessageSource();
		m.setBasename("classpath:META-INF/error_messages");
		return m;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AccessLoggingInteceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
}
