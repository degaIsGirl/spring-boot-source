package org.springframework.boot.ioc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component
public class LogAspect {
	@Pointcut("@annotation(org.springframework.boot.ioc.aop.Log)")
	public void cut() {}

	@Around("cut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Object[] args = joinPoint.getArgs();
		Method method = signature.getMethod();
		Parameter[] parameters = method.getParameters();
		Object target = joinPoint.getTarget();
		Object aThis = joinPoint.getThis();
		System.out.println("this is aop of " + method.getName());
		Object proceed = joinPoint.proceed();
		return proceed;
	}
}
