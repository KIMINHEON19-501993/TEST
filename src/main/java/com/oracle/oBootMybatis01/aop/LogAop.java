package com.oracle.oBootMybatis01.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAop {
	
	// DAO 성능 측정
	@Pointcut("within(com.oracle.oBootMybatis01.dao.EmpDaoImpl)") // com.oracle.oBootMybatis01.dao 패키지 안에 있는 모든 메소드
	private void pointcutMethod() {
		
	}
	
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {
		String signatureStr = joinPoint.getSignature().toShortString();
		System.out.println(signatureStr+" is start.");
		long st = System.currentTimeMillis();
		try {
			Object obj = joinPoint.proceed();
			return obj;
		}finally {
			long et = System.currentTimeMillis();
			System.out.println(signatureStr+" is finished.");
			System.out.println(signatureStr+" 경과시간 : "+(et-st));
		}
	}
	
}
