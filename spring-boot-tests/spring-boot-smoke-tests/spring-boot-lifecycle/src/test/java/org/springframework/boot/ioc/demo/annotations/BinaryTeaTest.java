package org.springframework.boot.ioc.demo.annotations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.ioc.demo.annotations.goods.FruitTea;
import org.springframework.boot.ioc.demo.annotations.goods.PureTea;
import org.springframework.boot.ioc.demo.common.imports.Menu;
import org.springframework.boot.ioc.demo.common.scans.AliPay;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class BinaryTeaTest {
	/**
	 * 基于注解
	 */
	@Test
	public void annotationDemo() {
		try {
			// 说明@Bean标注的方法生效
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
			Boss boss = context.getBean(Boss.class);


			// 说明@ComponentScan生效
			AliPay aliPay = context.getBean(AliPay.class);
			System.out.println(aliPay);

			Customer bean = context.getBean(Customer.class);
			System.out.println(bean);

			// 说明@Import生效
			Menu menu = context.getBean(Menu.class);
			System.out.println(menu);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 基于spi + BeanDefinitonRegistryPostProcessor完成编程式添加
	 */
	@Test
	public void programAddBeanDef() {
		try {
			// 说明我们动态注册生效
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
			FruitTea fruitTea = context.getBean(FruitTea.class);
			System.out.println(fruitTea);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 基于BeanFactoryPostProcessor
	 */
	@Test
	public void programModifyBeanDef() {
		try {
			// 说明我们动态注册生效
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
			PureTea pureTea = context.getBean(PureTea.class);
			System.out.println(pureTea);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
