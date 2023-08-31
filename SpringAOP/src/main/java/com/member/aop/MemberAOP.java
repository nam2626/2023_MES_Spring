package com.member.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MemberAOP {
	//JoinPoint 이전에 실행
	//모든리턴타입 com.member패키지 안에있는 모든 클래스의 모든 메서드, 매개변수는 모든형태를 지원
	//서브패키지까지 전부 포함
	@Before("execution(* com.member..*.*(..))")
	public void beforeTest(JoinPoint joinPoint) {
		System.out.println("beforeTest : " + joinPoint.getSignature().getDeclaringType().getSimpleName()
				+ " / " + joinPoint.getSignature().getName());
		
		System.out.println("beforeTest - args : " + Arrays.toString(joinPoint.getArgs()));
	}
	
	//JoinPoint 이후에 실행
	@After("execution(* com.member.service.*.*(..))")
	public void afterTest(JoinPoint joinPoint) {
		System.out.println("afterTest : "+joinPoint.getSignature().getDeclaringType().getSimpleName()
				+ " / " + joinPoint.getSignature().getName());
	}
	
	//리턴된 데이터가 없으면 null 값을 받아옴
	@AfterReturning(pointcut = "execution(* com.member.service.*.*(..))",
			returning = "returnObj")
	public void afterReturningTest(JoinPoint joinPoint, Object returnObj) {
		System.out.println("afterReturningTest : " +
						joinPoint.getSignature().getDeclaringType().getSimpleName()
				+ " / " + 	joinPoint.getSignature().getName()
		);
		System.out.println("afterReturningTest : " + 
						(returnObj == null ? "리턴결과 없음" : returnObj.toString() ));
	}
	
	//메서드에서 Exception을 throws를 하는 경우 실행
	@AfterThrowing(pointcut = "execution(* com.member.service.*.*(..))",
			throwing = "exception")
	public void afterThrowingTest(JoinPoint joinPoint, Exception exception) {
		System.out.println("afterThrowingTest : " + joinPoint.getSignature().getName());
		System.out.println("afterThrowingTest : " + exception.getMessage());
	}
	
	//메서드 호출 전후에 수행
	@Around("execution(* com.member.mapper.*.*(..))")
	public Object aroundTest(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("Around Test Start");
		System.out.println(joinPoint.getSignature().getName());
		Object obj = joinPoint.proceed();//메서드 실행하는 부분
		if(obj != null)	System.out.println(obj.toString());
		System.out.println("Around Test End");
		return obj;
	}
}












