package com.xuebaclass.sato.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.github.pagehelper.PageInterceptor;

/**
 * mybatis config
 *
 * @author lee
 * @since 2017-07-13
 */
@Configuration
@ComponentScan(value = "com.xuebaclass.sato.service")
@Import({DBSource.class, MyBatisMapperScannerConfig.class})
public class MyBatisConfig implements TransactionManagementConfigurer {


    @Autowired
    @Qualifier("satoDBDataSource")
    DataSource satoDBDatasource;

    @Autowired
    @Qualifier("crmDBDataSource")
    DataSource crmDBDataSource;

    @Bean
    @Primary
    public SqlSessionFactoryBean crmDBSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(crmDBDataSource);

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);

        Properties properties = new Properties();
        PageInterceptor pageHelper = new PageInterceptor();// 配置pagehelper分页插件
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "count");
        properties.setProperty("count", "countSql");
        properties.setProperty("autoRuntimeDialect", "true");
        pageHelper.setProperties(properties);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});

        return sqlSessionFactoryBean;
//        return createFactoryByDataSource(crmDBDataSource);
    }

    @Bean
    public SqlSessionFactoryBean satoDBSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(satoDBDatasource);

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);

        Properties properties = new Properties();
        PageInterceptor pageHelper = new PageInterceptor();// 配置pagehelper分页插件
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "count");
        properties.setProperty("count", "countSql");
        properties.setProperty("autoRuntimeDialect", "true");
        pageHelper.setProperties(properties);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});

        return sqlSessionFactoryBean;
//        return createFactoryByDataSource(satoDBDatasource);
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager dtm1 = new DataSourceTransactionManager(crmDBDataSource);
        DataSourceTransactionManager dtm2 = new DataSourceTransactionManager(satoDBDatasource);

        ChainedTransactionManager ctm = new ChainedTransactionManager(dtm1, dtm2);
        return ctm;
    }

    private SqlSessionFactoryBean createFactoryByDataSource(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);

        Properties properties = new Properties();
        PageInterceptor pageHelper = new PageInterceptor();// 配置pagehelper分页插件
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "count");
        properties.setProperty("count", "countSql");
        properties.setProperty("autoRuntimeDialect", "true");
        pageHelper.setProperties(properties);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});

        return sqlSessionFactoryBean;
    }

}
