package org.springframework.boot.ioc.spi;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;
import java.util.List;
import java.util.ServiceLoader;

public class SpiTest {
	/**
	 * JDK SPI
	 * 要求实现类必须存在META-INF/services/接口全限定名文件
	 * 要符合一定的规则:
	 * 1、文件名称必须是接口或者抽象类的全限定名称
	 * 2、文件中每一行对应一个class的全限定名称, 这个class比是是接口的实现或者继承了这个抽象类
	 */
	@Test
	public void jdkSpiTest() {
		ServiceLoader<IMenu> load = ServiceLoader.load(IMenu.class);
		load.iterator().forEachRemaining(item -> {
			System.out.println(item);
		});
	}

	/**
	 * Spring SPI
	 * 要求文件的名称和位置必须是 classpath:/META-INF/spring.factories
	 * 要符合一定的规则:
	 * 1、文件中的内容是key value的形式, key是一个全限定名称, value可以是多个全限定名称(中间使用英文逗号隔开)
	 * 2、如果加载后要进行实例化的话, 要是要求key 接口或者抽象类, value 必须是接口或者抽象类兼容的全限定名称
	 * 3、如果仅仅是加载类名称的化, 那么key 可以是任意形式的: interface、 abstract class、annotation、enum,
	 * 而对应的value同样可以是任何的interface、 abstract class、annotation、enum, 最大名鼎鼎的就是@EnableAutoConfiguration注解 就是这种情况
	 */
	@Test
	public void springSpiTest() {
		/**
		 * 加载并实例化
		 * 这里我们将让NormalMenu不再实现IMenu, 出现了报错, 错误信息如下:
		 * Unable to instantiate factory class [org.springframework.boot.ioc.spi.NormalMenu] for factory type [org.springframework.boot.ioc.spi.IMenu]
		 * 这说明value的类型要能够转化为对应的key的类型
		 */
		List<IMenu> iMenus = SpringFactoriesLoader.loadFactories(IMenu.class, IMenu.class.getClassLoader());
		iMenus.forEach(item -> {
			System.out.println(item);
		});

		/**
		 * 只是获取名字,并没有实例化
		 */
		List<String> strings = SpringFactoriesLoader.loadFactoryNames(IMenu.class, IMenu.class.getClassLoader());
		strings.forEach(item -> {
			Class<?> aClass = ClassUtils.resolveClassName(item, IMenu.class.getClassLoader());
			if (aClass.isAnnotation()) {
				System.out.println("annotation => " + aClass);
			} else {
				if (IMenu.class.isAssignableFrom(aClass)) {
					System.out.println("IMnu => " + aClass);
				} else {
					System.out.println("other class => " + aClass);
				}
			}
		});
	}

	/**
	 * Spring Spi 更多是配合@Import 注解来实现BeanDefinition的动态加载
	 *
	 * 这里动态的加载了spring.factories文件中的value, 并生成BeanDefinition, 添加到BeanDefinitionRegistry中
	 */
	@Test
	public void springSpiWithImportTest() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfigure.class);
		ActivityMenu bean = context.getBean(ActivityMenu.class);
		System.out.println(bean);
	}
}
