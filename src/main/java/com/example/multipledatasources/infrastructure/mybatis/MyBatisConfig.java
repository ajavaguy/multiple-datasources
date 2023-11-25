package com.example.multipledatasources.infrastructure.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = { "com.example.multipledatasources.datasources" })
public class MyBatisConfig {

    @Primary
    @Bean(name="multipleDatasourcesDataSource")
    @ConfigurationProperties(prefix="spring.datasource.multiple-datasources")
    public DataSource multipleDatasourcesDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    @Bean(name="microservicesDemoDataSource")
    @ConfigurationProperties(prefix="spring.datasource.microservices-demo")
    public DataSource microservicesDemoDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    @Primary
    @Bean(name="multipleDatasourcesSQLSessionFactory")
    public SqlSessionFactory multipleDatasourcesSQLSessionFactory(@Qualifier("multipleDatasourcesDataSource") DataSource dataSource) throws Exception {
        return create(dataSource);
    }

    @Bean(name="microservicesDemoSQLSessionFactory")
    public SqlSessionFactory microservicesDemoSQLSessionFactory(@Qualifier("microservicesDemoDataSource") DataSource dataSource) throws Exception {
        return create(dataSource);
    }

    @Primary
    @Bean(name="multipleDatasourcesSQLSession")
    public SqlSessionTemplate multipleDatasourcesSQLSession(@Qualifier("multipleDatasourcesSQLSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name="microservicesDemoSQLSession")
    public SqlSessionTemplate microservicesDemoSQLSession(@Qualifier("microservicesDemoSQLSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Value("${mybatis.mapper-locations}")
    private String resource;

    private SqlSessionFactory create(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());

        //sessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis.xml"));
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(resolver.getResources(resource));

        return sessionFactoryBean.getObject();
    }
}
