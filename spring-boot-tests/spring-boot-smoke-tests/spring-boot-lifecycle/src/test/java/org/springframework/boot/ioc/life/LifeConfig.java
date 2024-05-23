package org.springframework.boot.ioc.life;

import org.junit.jupiter.api.Test;
import org.springframework.boot.ioc.life.dogs.MuBian;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

class LifeConfig {

	/**
	 * bean life cycle
	 * @throws IOException
	 */
	@Test
	public void testInfo() throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		/**
		 * 扫描指定包下的组件进入容器
		 */
		context.scan(Person.class.getPackage().getName());

		/**
		 * 添加配置
		 */
		context.getEnvironment().getPropertySources().addLast(
				new ResourcePropertySource(new ClassPathResource("/life/config.properties"))
		);

		// refresh 是核心方法所在, 完成了bean的实例化、初始化
		context.refresh();

		context.start();
		System.out.println("================IOC容器启动完成==================");

		Person person = context.getBean(Person.class);
		System.out.println(person);
		Cat cat = context.getBean(Cat.class);
		System.out.println(cat);

		MuBian muBian = (MuBian)context.getBean("muBian");
		muBian.getDogIntroduce();

		System.out.println("================准备停止IOC容器==================");

		context.stop();

		System.out.println("================IOC容器停止成功==================");

		context.close();
	}


}