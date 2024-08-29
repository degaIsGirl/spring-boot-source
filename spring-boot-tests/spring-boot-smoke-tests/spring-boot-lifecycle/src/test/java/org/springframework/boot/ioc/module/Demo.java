package org.springframework.boot.ioc.module;

import org.junit.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 自定义注解 + @Import 是模块装配的核心
 * @Import() 注解的主要作用是将导入的class到ioc容器中
 * 		可以导入的范围包含:
 * 						1、普通的class
 * 						2、被@Configuration 标注的配置class
 * 						3、实现了ImportSelector的class
 * 						4、实现了ImportBeanDefinitionRegistrar的class
 * 		第三种和第四种是动态添加class到bean的方法, 通常会配合spi来解决
 */
@SpringBootApplication
@ConditionalOnMissingBean
public class Demo {

	/**
	 * 条件注解, 满足指定的条件才会生效
	 * 我们常见的很多条件注解入
	 * 					   @ConditionalOnClass
	 * 					   @ConditionalOnMissingBea
	 * 					   @ConditionalOnBean
	 * 					   @ConditionalOnMissingBean
	 * 都是基于@Conditional 封装得来的, 改注解只有一个元素, 就是 Class<? extends Condition>[] value(),
	 * 我们写的所有的逻辑都在value指定class中, 我们看到这个value是一个数组, 支持传入多个class, 此时要求多个class都返回true,
	 * 如果有一个不返回true的话, 那么最终结果就是false
	 */
	@Test
	public void testCondition() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DebugDemo.class);
		ObjectProvider<DebugDemo> beanProvider = context.getBeanProvider(DebugDemo.class);
		beanProvider.ifAvailable(item -> {
			System.out.println(item);
			System.out.println("exec");
		});
	}
}
