package org.springframework.boot.proxy.aop;


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
public class AgentAspect {

	/**
	 * 切入点, 解决了aop的where的问题,
	 * 切入点是要对哪些方法进行增强的描述, 表示一批连接点, 常用的有切入点表达式有2个:
	 * execution切入点表达式: 方法的访问修饰符 方法返回值 包名称.类名称.方法名称(参数)
	 * execution中的两个特殊的富豪需要我们牢记:
	 * 	符号1: * 表示1个:
	 * 		可以用在访问修饰符-表示任意访问修饰符
	 * 			* * org.springframework.boot.proxy.aop.ChengLong.*(..))
	 * 		用在返回值-表示任意返回类型
	 * 			public * org.springframework.boot.proxy.aop.ChengLong.*(..))
	 * 		用在包名称-表示任意一个包
	 * 			public boolean org.springframework.boot.proxy.*.ChengLong.*(..))
	 * 		用在class 表示任意一个类
	 * 			public boolean org.springframework.boot.proxy.aop.*.*(..))
	 * 		用在方法的位置表示任意一个方法名
	 * 			public boolean org.springframework.boot.proxy.aop.ChengLong.*(..))
	 * 		用在参数上表示任意一个参数
	 * 			public boolean org.springframework.boot.proxy.aop.ChengLong.signContract(*))
	 *
	 * 	符号2: .. 表示任意个
	 * 		用在包名称-表示任意包
	 * 			public boolean org.springframework.boot..ChengLong.*(*)), 表示org.springframework.boot下所有的包(含子包)
	 * 		用在参数上表示任意参数
	 * 			public boolean org.springframework.boot.proxy.aop.ChengLong.signContract(..)), 表示signContract(任意个数&任意类型的参数)
	 *
	 * annotation切入点表达式: 注解的全路径, 表示只要方法上出现了这个注解就进行增强
	 */
	@Pointcut(value = "execution(public * org.springframework.boot.proxy.aop.ChengLong.*(..))")
	public void proxy() {

	}

	@Around(value = "proxy()")
	public Object enhance(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// 方法参数
		Object[] args = proceedingJoinPoint.getArgs();

		// 代理对象
		Object proxy = proceedingJoinPoint.getThis();

		// 被代理对象
		Object target = proceedingJoinPoint.getTarget();

		// 获取方法签名
		MethodSignature methodSignature =  (MethodSignature)proceedingJoinPoint.getSignature();

		// 获取被代理的方法的反射对象
		Method method = methodSignature.getMethod();
		String methodName = method.getName();

		// 快捷方法
		//String methodName = proceedingJoinPoint.getSignature().getName();
		if ("signContract".equals(methodName)) {
			int money = (int)args[0];
			if (money < 100) {
				System.out.println("金额不足100万, 直接拒绝");
				return null;
			} else {
				System.out.println("金额超过100万, 开始搞");
			}
		}
		return null;
		//return proceedingJoinPoint.proceed();
	}
}
