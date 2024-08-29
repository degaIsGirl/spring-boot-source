package org.springframework.boot.ioc.program;

import org.junit.jupiter.api.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.beans.Introspector;
import java.util.Set;

@SpringBootTest
public class ProgramIoc {

	/**
	 * 通过手动注册的方式添加bean
	 */
	@Test
	public void testIoc() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.registerBeanDefinition("zhangsan",
				BeanDefinitionBuilder.genericBeanDefinition(Person.class)
						.addPropertyValue("name", "person")
						.getBeanDefinition()
		);

		context.registerBeanDefinition("dog",
				BeanDefinitionBuilder.genericBeanDefinition(Dog.class)
						.addPropertyValue("name", "dog")
						.addPropertyReference("person", "zhangsan")
						.getBeanDefinition()
		);

		context.registerBeanDefinition("cat",
				BeanDefinitionBuilder.genericBeanDefinition(Cat.class)
						.addPropertyValue("name", "cat")
						.addPropertyReference("person", "zhangsan")
						.getBeanDefinition()
		);

		context.refresh();

		Dog dog = context.getBean(Dog.class);
		System.out.println(dog);

		Cat cat = context.getBean(Cat.class);
		System.out.println(cat);
	}

	/**
	 * 通过扫描的方式去注册
	 * AnnotationTypeFilter是@Component生效的关键
	 */
	@Test
	public void testScanIoc() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);

		//number of beans registered
		int scan = scanner.scan("org.springframework.boot.ioc.program");
		context.refresh();

		Dog bean = context.getBean(Dog.class);
	}

	/**
	 * 扫描获取全部的beandefinition
	 * 然后我们手动去注册到ioc容器中
	 */
	@Test
	public void testScan() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);

		/**
		 * 此时获取了满足条件的bean定义, 即存在@Component注解
		 */
		Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents("org.springframework.boot.ioc.program");

		candidateComponents.forEach(beanDefinition -> {
			String beanClassName = beanDefinition.getBeanClassName();
			assert beanClassName != null;
			String substring = beanClassName.substring(
					beanClassName.lastIndexOf('.')
			);
			context.registerBeanDefinition(
					Introspector.decapitalize(
							substring
					),
					beanDefinition
			);
		});
		context.refresh();
		Dog bean = context.getBean(Dog.class);
		System.out.println(bean);
	}
}
