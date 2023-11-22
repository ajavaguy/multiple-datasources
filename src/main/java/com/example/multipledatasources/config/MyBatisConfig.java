package com.example.multipledatasources.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = { "com.example.multipledatasources.mapper" })
public class MyBatisConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.multiple-datasources")
    public DataSource multipleDatasourcesDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.microservices-demo")
    public DataSource micorservicesDemoDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    @Primary
    @Bean
    public SqlSessionFactory multipleDatasourcesSQLSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionFactory microservicesDemoSQLSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Primary
    @Bean
    public SqlSessionTemplate multipleDatasourcesSQLSession(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public SqlSessionTemplate microservicesDemoSQLSession(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
