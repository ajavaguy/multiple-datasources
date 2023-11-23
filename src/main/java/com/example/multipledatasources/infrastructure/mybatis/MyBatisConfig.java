package com.example.multipledatasources.infrastructure.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = { "com.example.multipledatasources.datasources" })
public class MyBatisConfig {

    @Primary
    @Bean("multipleDatasourcesDataSource")
    @ConfigurationProperties(prefix="spring.datasource.multiple-datasources")
    public DataSource multipleDatasourcesDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    @Bean("microservicesDemoDataSource")
    @ConfigurationProperties(prefix="spring.datasource.microservices-demo")
    public DataSource microservicesDemoDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    @Primary
    @Bean("multipleDatasourcesSQLSessionFactory")
    public SqlSessionFactory multipleDatasourcesSQLSessionFactory(@Qualifier("multipleDatasourcesDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean("microservicesDemoSQLSessionFactory")
    public SqlSessionFactory microservicesDemoSQLSessionFactory(@Qualifier("microservicesDemoDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Primary
    @Bean("multipleDatasourcesSQLSession")
    public SqlSessionTemplate multipleDatasourcesSQLSession(@Qualifier("multipleDatasourcesSQLSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("microservicesDemoSQLSession")
    public SqlSessionTemplate microservicesDemoSQLSession(@Qualifier("microservicesDemoSQLSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
