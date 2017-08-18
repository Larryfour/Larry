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
		MapperScannerConfigurer M = new MapperScannerConfigurer();
		M.setBasePackage("com.xuebaclass.sato.mapper.sato");
		M.setSqlSessionFactoryBeanName("satoDBSqlSessionFactoryBean");
		return M;
	}

	@Bean
	public MapperScannerConfigurer crmDBMapperScannerConfigurer() {
		MapperScannerConfigurer M = new MapperScannerConfigurer();
		M.setBasePackage("com.xuebaclass.sato.mapper.crm");
		M.setSqlSessionFactoryBeanName("crmDBSqlSessionFactoryBean");
		return M;
	}

}
