package org.springframework.boot.ioc.annotations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.boot.ioc.annotations.MyImport.Worker;
import org.springframework.boot.ioc.annotations.menu.IMenu;
import org.springframework.boot.ioc.annotations.menu.MenuFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.ClassMetadata;
import java.util.Arrays;

class BinaryTeaAnnotationTest {

	/**
	 * xml的方式逐渐被spring所抛弃, 我们日常开发中也多是使用注解, 这里我们用注解的方式来写一遍
	 * <p>
	 * 有了xml的例子, 我们可以通过对比来加深对注解方式的理解
	 * 1、这里的BinaryTeaConfig类(这个class被@Configuration标注), 相当于是一个LifeBeans.xml
	 * 2、BinaryTeaConfig类中被@Bean 注解标注的方法, 相当于是一个<bean></bean> 标签
	 * 3、BinaryTeaConfig类中@CmponentScan注解标注的类, 相当于是一个<context:component-scan></context:component-scan> 标签
	 * <p>
	 * FactoryBean, 本身也是一个bean对象, 但是这个bean对象是用来构建对象的
	 * 一般使用在较为复杂的构建场景中, 例如我们希望注入的IMenu对象在节假日的时候展示ActivityMenu, 但是在平常时现实NormalMenu
	 */
	@Test
	public void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BinaryTeaConfig.class);

		// @Bean 标注的方法生效
		BinaryTeaAnnotation binaryTea = context.getBean(BinaryTeaAnnotation.class);
		binaryTea.test();

		// @ComponentScan 标注的包扫描范围生效
		// 通过类型获取MenuFactory 得到是menuFactory本身
		MenuFactory menuFactory = context.getBean(MenuFactory.class);
		System.out.println(menuFactory);

		// 通过名字获取MenuFactory 得到是menuFactory 对应的getObject() 方法拿到的对象
		IMenu menuObj = (IMenu) context.getBean("menuFactory");
		System.out.println(menuObj);

		// 如果我们希望通过名字拿到MenuFactory这个bean对象, 需要在前面加上一个&符号
		Object bean = context.getBean("&menuFactory");
		System.out.println(bean);

		// @Import 标注的类生效
		Worker worker = context.getBean(Worker.class);
		System.out.println(worker);
	}


	/**
	 * 通过编程的方式手动注册BeanDefinition
	 */
	@Test
	public void programRegister() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.registerBeanDefinition("worker",
				BeanDefinitionBuilder.rootBeanDefinition(Worker.class)
						.setLazyInit(true)
						.getBeanDefinition()
		);

		context.registerBeanDefinition("boss",
				BeanDefinitionBuilder.rootBeanDefinition(Boss.class)
						.addPropertyValue("name", "我是boss")
						///.addPropertyReference("worker", "worker")
						.getBeanDefinition()
		);

		// prototype
		context.registerBeanDefinition("customer", BeanDefinitionBuilder.genericBeanDefinition(Customer.class)
				.setScope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
				.getBeanDefinition()
		);

		/**
		 * 要记得刷新
		 *
		 * 为什么要执行refresh(), 有什么用啊?
		 * 我们可以理解为执行refresh会为注册的beanDefinition, 创建对应的实例对象、完成属性的注入. 它是bean生命周期的核心所在
		 *
		 * 是否所有的beanDefinition都会创建对应的对象呢?答案是否定的, 只有满足
		 * 1、如果beanDefinition的scope为Single, 也就是单例
		 * 2、并非是lazy模式, 也就是说并非使用的时候才去创建
		 * */
		context.refresh();
		Boss bean = context.getBean(Boss.class);
		System.out.println(bean);
	}

	/**
	 * 一个个注册太慢了, 我们是否可以通过扫描注册呢?
	 */
	@Test
	public void scannerRegister() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
		//	boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
		scanner.addIncludeFilter((metadataReader, metadataReaderFactory) -> {
			ClassMetadata classMetadata = metadataReader.getClassMetadata();
			if (classMetadata.getClass().isInterface()) {
				return false;
			}
			if(classMetadata.getClass().isAnnotation()) {
				return false;
			}
			if (classMetadata.getClass().isAnnotationPresent(MyComponent.class)) {
				return true;
			} else {
				return false;
			}
		});

		int num = scanner.scan(Boss.class.getPackage().getName());
		context.refresh();
		Arrays.stream(context.getBeanDefinitionNames()).forEach(item -> {
			System.out.println(item);
		});
	}
}