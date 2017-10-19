package com.xuebaclass.sato.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis
 * 
 * @author lee
 * @since 2017-07-13
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

	@Bean
	public MapperScannerConfigurer satoDBMapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.xuebaclass.sato.mapper.sato");
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("satoDBSqlSessionFactoryBean");
		return mapperScannerConfigurer;
	}

	@Bean
	public MapperScannerConfigurer crmDBMapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.xuebaclass.sato.mapper.crm");
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("crmDBSqlSessionFactoryBean");
		return mapperScannerConfigurer;
	}

}
