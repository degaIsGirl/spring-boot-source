package org.springframework.boot.ioc.xml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.ioc.demo.annotations.DemoConfig;
import org.springframework.boot.ioc.xml.MyImport.Worker;
import org.springframework.boot.ioc.xml.menu.ActivityMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
class BinaryTeaTest {

	@Test
	public void hello() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("LifeBeans.xml");

		// bean 标签生效
		BinaryTea binaryTea = classPathXmlApplicationContext.getBean(BinaryTea.class);
		binaryTea.hello("fuck you");

		// component-scan 生效
		ActivityMenu bean = classPathXmlApplicationContext.getBean(ActivityMenu.class);
		bean.list();

		// import标签生效
		Worker worker = classPathXmlApplicationContext.getBean(Worker.class);
		worker.start();
	}


	@Test
	public void showCommonBeanDef() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("LifeBeans.xml");

		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

		BeanDefinition binaryTea = beanFactory.getBeanDefinition("binaryTea");

		// Root Bean
		BeanDefinition boss = beanFactory.getBeanDefinition("boss");
		System.out.println(boss.getClass().getName());
		System.out.println(boss);

		// Generic bean
		BeanDefinition customer = beanFactory.getBeanDefinition("customer");
		System.out.println(customer.getClass().getName());
		System.out.println(customer);
	}
}