package com.goodee.ex09.config;

import java.util.Collections;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Aspect                 // aspect 선언, 공통 모듈을 담당한다
@EnableAspectJAutoProxy // Aspect의 동작이 자동으로 진행된다.

@Configuration
public class TransactionConfig {

	@Autowired
	private TransactionManager transactionManager;  // DBConfig의 bean DI
	
	@Bean
	public TransactionInterceptor interceptor() {
		// 인터셉터 : 정상적인 흐름을 가로 채서 실행되는 것을 의미함
		
		
		// 모든 자바 예외(Exception)가 발생하면 Rollback을 수행하겠다.
		MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
		RuleBasedTransactionAttribute attribute = new RuleBasedTransactionAttribute();
		attribute.setName("*");
		attribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		source.setTransactionAttribute(attribute);
		return new TransactionInterceptor(transactionManager, source);
	}
	
	@Bean
	public Advisor advisor() {
		
		AspectJExpressionPointcut pointCut = new AspectJExpressionPointcut();
		pointCut.setExpression("execution(* com.goodee.ex09.service.*Impl.*(..))"); // 잘라 들어가는 시점, NoticeServiceImpl의 모든 메소드에서 transaction 처리가 됨.
		
		return new DefaultPointcutAdvisor(pointCut, interceptor()); // interceptor() 메소드 호출
	}
	
	
}
