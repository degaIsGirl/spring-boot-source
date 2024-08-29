package org.springframework.boot.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.proxy.aop.ChengLong;
import org.springframework.boot.proxy.aop.DemoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * aop vs oop
 * 关联:
 * 1、aop 是面向切面编程的简称, 是对oop思想的补充, 两个目的是为了提高代码的复用率、降低维护成本
 *
 * 区别:
 * 1、核心不同, aop中的核心是切面, 而oop中的核心是对象
 * 2、代码的组织方式不同, oop思想下主要是通过父子继承、对象组合的方式完成功能的增强和代码的复用;
 *    而aop思想下主要是通过切面提取, 动态织入到切入点的方式完成功能的增强和代码的复用
 */
@SpringBootTest
public class DemoConfigurationTest {
	/**
	 * aop中涉及到的核心概念, 我们接下来一点点展开来讲? 刚开始学习aop的时候我往往会被各种概念吓到,但我理解我只需要把握住一个思路, 掌握起来还是比较容易起的.
	 *
	 * 具体思路如下: 在spring中aop设计的初衷就是对一批满足条件的方法进行功能增强. 所以aop必须解决3个问题:
	 * a、对哪些功能方法进行增强, 即解决where的问题
	 * b、什么时候对方法增强, 方法调用前、调用后、抛出一场的时候, 即解决了when的问题
	 * c、新增加的功能是干什么的, 即解决了what的问题
	 *
	 * 1、aop的核心是切面, 我们必须了解什么是切面?
	 * 切面 = 切入点(解决了where的问题) + 通知(解决了 when + what的问题)
	 *
	 * 2、什么是切入点?
	 * 切入点是对对多个连接点的描述. 那什么又是连接点呢? 连接点我们可以简单的认为是要增强的那个方法
	 *
	 * 什么是通知?
	 * 在spring中有5中类型的通知:
	 * a、前置通知 (在方法前执行新增的功能)
	 * b、后置通知 (在方法执行结束后, 无异常抛出, 执行新增的功能)
	 * c、环绕通知 (用来取代, 被代理方法的执行时机)
	 * d、返回通知 (方法执行结束后,无论是否有异常, 都会执行新增的功能)
	 * e、异常通知 (方法有异常抛出的时候, 执行新增的功能)
	 * 每种类型的通知都解决了when + what的问题
	 */
	@Test
	public void demoAop() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfiguration.class);
		ChengLong bean = (ChengLong) context.getBean("chengLong");
		bean.signContract(90);
	}
}
