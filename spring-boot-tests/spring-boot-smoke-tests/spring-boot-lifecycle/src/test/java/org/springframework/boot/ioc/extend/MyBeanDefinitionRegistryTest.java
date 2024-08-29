package org.springframework.boot.ioc.extend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyBeanDefinitionRegistryTest {

	/**
	 * beanDefinition的扩展时机
	 * 我们可以认为有两个主要的扩展实际:
	 * 1、新增, 一般是通过实现BeanDefinitionRegistryPostProcessor接口完成beanDefinition的注册
	 * 2、修改, 一般是通过是在BeanFactoryPostProcessor 接口完成beanDefinition的修改
	 */
	@Test
	public void testExtend() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyBeanDefinitionRegistry.class);
		ZhangSan bean = context.getBean(ZhangSan.class);
		System.out.println(bean.getName());
	}
}