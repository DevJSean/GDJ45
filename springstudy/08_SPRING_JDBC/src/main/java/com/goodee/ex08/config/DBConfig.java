package com.goodee.ex08.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DBConfig {

	// Spring JDBC 
	// 1. DataSource를 bean으로 등록한다.
	// 2. JdbcTemplate을 bean으로 등록한다.
	//    이 때, DataSource bean이 JdbcTemplate bean의 field로 사용된다.
	
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("scott");
		dataSource.setPassword("1111");
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource()); // 바로 위의 메소드( dataSource를 불러오는 것임)를 호출
		return jdbcTemplate;
	}
	
}
