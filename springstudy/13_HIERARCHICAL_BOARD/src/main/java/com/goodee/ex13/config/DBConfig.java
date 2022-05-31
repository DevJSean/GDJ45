package com.goodee.ex13.config;

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

// @Mapper를 사용하고 있는데, 여기에서 Mapper를 찾아라.
@MapperScan(basePackages = {"com.goodee.ex13.mapper"})

// mybatis/properties/db.properties 파일의 내용을 참조하겠다
@PropertySource(value={"classpath:mybatis/properties/db.properties"})

// TransactionManager를 사용하겠다.
@EnableTransactionManagement

@Configuration
public class DBConfig {

	// mybatis/properties/db.properties 파일에 등록된 프로퍼티 값을 변수에 저장한다.
	// 프로퍼티들은 ${} EL로 처리한다.
	// import 주의
	@Value(value="${hikariConfig.driverClassName}") private String driverClassName; // "oracle.jdbc.OracleDriver"
	@Value(value="${hikariConfig.jdbcUrl}") private String jdbcUrl; // "jdbc:oracle:thin:@localhost:1521:xe"
	@Value(value="${hikariConfig.username}") private String username; // "SCOTT"
	@Value(value="${hikariConfig.password}") private String password; // "1111"
	
	// HikariCP 환경 설정
	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driverClassName);
		hikariConfig.setJdbcUrl(jdbcUrl);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		return hikariConfig;
	}
	
	// HikariCP DataSource
	@Bean(destroyMethod="close") // destroyMethod 필수 옵션 작성
	public HikariDataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	// SqlSessionFactory
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource()); // HikariDataSource 전달
		sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("mybatis/config/mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mybatis/mapper/*.xml")); // 매퍼 여러 개 들어갈 것 (확장성) 대비
		return sqlSessionFactoryBean.getObject(); // SqlSessionFactory 반환
	}
	
	// SqlSessionTemplate : 이 위의 빈들은 모두 이 것을 위해서 만듦
	// SqlSessionTemplate은 SqlSession을 의미한다.
	// 모든 Repository에서 이 bean을 가져다 사용한다.
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 트랜잭션 매니저
	// 트랜잭션 매니저의 동작을 위해서 DBConfig에 @EnableTransactionManagement 애너테이션을 추가해야 한다.
	@Bean
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}
