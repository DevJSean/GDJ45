package com.goodee.ex01.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// mybatis 매퍼
@MapperScan(basePackages={"com.goodee.ex01.mapper"})

//mybatis/properties/db.properties에 있던 내용이 application.properties로 왔다.
@PropertySource(value={"classpath:application.properties"}) 

// 트랜잭션 매니저
@EnableTransactionManagement

@Configuration
public class DBConfig {

	// application.properties의 property와 이름을 맞춰야 한다.
	@Value(value="${spring.datasource.driver-class-name}") private String driverClassName;
	@Value(value="${spring.datasource.url}") private String jdbcUrl;
	@Value(value="${spring.datasource.username}") private String username;
	@Value(value="${spring.datasource.password}") private String password;
	// 아래의 sqlSessionFactory()에서 사용
	@Value(value="${mybatis.config-location}") private String configLocation;
	@Value(value="${mybatis.mapper-locations}") private String mapperLocations;
	
	
	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driverClassName);
		hikariConfig.setJdbcUrl(jdbcUrl);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		return hikariConfig;
	}
	
	@Bean(destroyMethod="close")
	public HikariDataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());						// 이곳 역시 application.properties와 맞춘다.
		sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}
