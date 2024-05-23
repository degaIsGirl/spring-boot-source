package org.springframework.boot.ioc.demo.xml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.boot.ioc.demo.annotations.DemoConfig;
import org.springframework.boot.ioc.demo.annotations.Monitory;
import org.springframework.boot.ioc.demo.common.imports.Menu;
import org.springframework.boot.ioc.demo.common.scans.AliPay;
import org.springframework.boot.ioc.demo.common.scans.Pay;
import org.springframework.boot.ioc.demo.common.scans.WxPay;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class BinaryTeaTest {
	@Test
	public void xmlDemo() {
		try {
			/**
			 * 创建ClassPathXmlApplicationContext 容器, 并加载demo.xml
			 */
			ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:/demo/xml/demo.xml");

			/**
			 * <bean></bean> 标签生效
			 */
			BinaryTea binaryTea = classPathXmlApplicationContext.getBean(BinaryTea.class);
			BinaryTea binaryTea1 = classPathXmlApplicationContext.getBean(BinaryTea.class);
			System.out.println(binaryTea);

			/**
			 * <context:component-scan> 标签生效
			 */
			AliPay bean = classPathXmlApplicationContext.getBean(AliPay.class);
			System.out.println(bean);

			/**
			 * <import></import> 标签生效
			 */
			Menu menu = classPathXmlApplicationContext.getBean(Menu.class);
			System.out.println(menu);
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	@Test
	public void showCommonBeanDef() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/demo/xml/demo.xml");
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

		// org.springframework.beans.factory.support.GenericBeanDefinition
		BeanDefinition boss = beanFactory.getBeanDefinition("boss");
		System.out.println(boss.getClass().getName());
		System.out.println(boss);

		// org.springframework.context.annotation.ScannedGenericBeanDefinition
		BeanDefinition aliPay = beanFactory.getBeanDefinition("aliPay");
		System.out.println(aliPay.getClass().getName());
		System.out.println(aliPay);
	}

	/**
	 * beanDefinition 具有层次性
	 */
	@Test
	public void genericBeanDef() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		AbstractBeanDefinition pay = BeanDefinitionBuilder.rootBeanDefinition(Pay.class)
				.addPropertyValue("fee", "免费")
				.getBeanDefinition();
		context.registerBeanDefinition("pay", pay);

		AbstractBeanDefinition wxPay = BeanDefinitionBuilder.genericBeanDefinition(WxPay.class)
				.setParentName("pay")
				.addPropertyValue("name", "移动支付")
				.getBeanDefinition();
		context.registerBeanDefinition("wxPay", wxPay);
		context.refresh();

		WxPay bean = context.getBean(WxPay.class);
		System.out.println(bean);
	}

	/**
	 * 含有注解处理功能的beanDefinition
	 */
	@Test
	public void annotationBeanDef() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		ScannedGenericBeanDefinition customer = (ScannedGenericBeanDefinition)context.getBeanDefinition("customer");
		System.out.println(customer);
		System.out.println(customer.getClass());
		AnnotationMetadata metadata = customer.getMetadata();
		if (metadata.isAnnotated(Monitory.class.getName())) {
			Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(Monitory.class.getName());
			System.out.println(annotationAttributes);
		}
		System.out.println(metadata);
	}
}
