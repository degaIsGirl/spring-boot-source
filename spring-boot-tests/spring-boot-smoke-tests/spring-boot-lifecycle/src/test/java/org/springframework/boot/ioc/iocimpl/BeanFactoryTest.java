package org.springframework.boot.ioc.iocimpl;

import org.junit.jupiter.api.Test;

public class BeanFactoryTest {

	/**
	 * 接口 org.springframework.beans.factory.BeanFactory
	 *
	 * 重要的实现class
	 *
	 * 1、org.springframework.beans.factory.support.AbstractBeanFactory
	 *
	 * 2、org.springframework.beans.factory.support.DefaultListableBeanFactory,
	 * 这里重点标记下DefaultListableBeanFactory实现了BeanDefinitionRegistry
	 *
	 *
	 */
	@Test
	public void testBeanFactory() {

	}

	/**
	 * 接口 org.springframework.context.ApplicationContext
	 *
	 * 按照是否支持重复刷新分为两大类:
	 *
	 * 不可以支持重复刷新的实现类:
	 * 		1、org.springframework.context.support.AbstractApplicationContext
	 * 		2、org.springframework.context.support.GenericApplicationContext, GenericApplicationContext组合了BeanFactory;
	 * GenericApplicationContext 虽然也实现了BeanDefinitionRegistry, 当时我们看待的时候它只是转发给了DefaultListableBeanFactory
	 * 实际上还是DefaultListableBeanFactory 在处理
	 * 		3、org.springframework.context.annotation.AnnotationConfigApplicationContext, 它继承了GenericApplicationContext.
	 * 	从名字上看AnnotationConfigApplicationContext多了关于注解的相关功能, 更符合我们日常的使用情况
	 *
	 * 支持重复刷新的实现类:
	 * 		1、org.springframework.context.support.AbstractRefreshableApplicationContext
	 *
	 * 		2、org.springframework.context.support.ClassPathXmlApplicationContext
	 */
	public void testApplication() {

	}
}
