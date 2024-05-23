package org.springframework.boot.ioc.annotations;

import org.springframework.boot.ioc.annotations.MyImport.Worker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@Configuration
//@ComponentScan(basePackages = "org.springframework.boot.ioc.annotations.menu")
//@Import(Worker.class)
// @Import 注解可以引入如下类型,
// @Configuration, ImportSelector, ImportBeanDefinitionRegistrar, or regular component classes to import.
// 特别是ImportSelector, ImportBeanDefinitionRegistrar可以帮助我们实现动态引入beanDefinition
public class BinaryTeaConfig {
	@Bean
	public BinaryTeaAnnotation binaryTeaAnnotation()
	{
		return new BinaryTeaAnnotation();
	}

	@Bean
	public Boss boss() {
		return new Boss();
	}

	@Bean
	public Customer customer() {
		return new Customer();
	}
}
