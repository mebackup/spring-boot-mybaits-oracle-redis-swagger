package com.my.conf;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@MapperScan(basePackages = "com.my.persistence")
public class DatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    @Value("${oracle.driverClass}")
    private String driverClass;

    @Value("${oracle.url}")
    private String url;

    @Value("${oracle.username}")
    private String username;

    @Value("${oracle.password}")
    private String password;

    @Bean
    public DataSource dataSource() throws SQLException {
        logger.error("---------> Set Oracle Data Pool");
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setURL(url);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        logger.error("---------> Set SqlSessionFactory");
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }

}
