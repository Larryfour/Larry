package com.xuebaclass.sato.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Primary;

/**
 * 数据源配置
 * @author lee
 * @since 2017-07-13
 */
@Configuration
public class DBSource {

    private static final Logger logger = LoggerFactory.getLogger(DBSource.class);

    @Value("${spring.datasource.sato.url}")
    private String satoDBurl;

    @Value("${spring.datasource.sato.username}")
    private String satoDBusername;

    @Value("${spring.datasource.sato.password}")
    private String satoDBpassword;

    @Value("${spring.datasource.crm.url}")
    private String crmDBurl;

    @Value("${spring.datasource.crm.username}")
    private String crmDBusername;

    @Value("${spring.datasource.crm.password}")
    private String crmDBpassword;



    @Bean(name="crmDBDataSource", initMethod = "init", destroyMethod = "close")
    @Qualifier("crmDBDataSource")
    @Primary
    public DataSource crmDBDatasource() throws SQLException {
        logger.debug("Sato DataSource: url: {} username: {} password: {}",
                crmDBurl, crmDBusername, crmDBpassword);

        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl(crmDBurl);
        ds.setUsername(crmDBusername);
        ds.setPassword(crmDBpassword);
        // 最大并发连接数
        ds.setMaxActive(20);
        // 初始化连接数量
        ds.setInitialSize(1);
        // 配置获取连接等待超时的时间
        ds.setMaxWait(60000);
        // 最小空闲连接数
        ds.setMinIdle(1);
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        ds.setTimeBetweenEvictionRunsMillis(60000);
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        ds.setMinEvictableIdleTimeMillis(300000);
        ds.setValidationQuery("SELECT 1");
        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(false);
        ds.setTestOnReturn(false);
        ds.setPoolPreparedStatements(true);
        ds.setMaxPoolPreparedStatementPerConnectionSize(50);
        ds.setFilters("stat");
//       打开removeAbandoned功能
        //ds.setRemoveAbandoned(true);
//       1800秒，也就是30分钟
        //ds.setRemoveAbandonedTimeout(removeAbandonedTimeout);
//       关闭abanded连接时输出错误日志
        ds.setLogAbandoned(true);
        return ds;
    }


    @Bean(name="satoDBDataSource", initMethod = "init", destroyMethod = "close")
    @Qualifier("satoDBDataSource")
    public DataSource satoDBDatasource() throws SQLException {
        logger.debug("Sato DataSource: url: {} username: {} password: {}",
                satoDBurl, satoDBusername, satoDBpassword);

        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl(satoDBurl);
        ds.setUsername(satoDBusername);
        ds.setPassword(satoDBpassword);
        // 最大并发连接数
        ds.setMaxActive(20);
        // 初始化连接数量
        ds.setInitialSize(1);
        // 配置获取连接等待超时的时间
        ds.setMaxWait(60000);
        // 最小空闲连接数
        ds.setMinIdle(1);
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        ds.setTimeBetweenEvictionRunsMillis(60000);
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        ds.setMinEvictableIdleTimeMillis(300000);
        ds.setValidationQuery("SELECT 1");
        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(false);
        ds.setTestOnReturn(false);
        ds.setPoolPreparedStatements(true);
        ds.setMaxPoolPreparedStatementPerConnectionSize(50);
        ds.setFilters("stat");
//       打开removeAbandoned功能
        //ds.setRemoveAbandoned(true);
//       1800秒，也就是30分钟
        //ds.setRemoveAbandonedTimeout(removeAbandonedTimeout);
//       关闭abanded连接时输出错误日志
        ds.setLogAbandoned(true);
        return ds;
    }


}
