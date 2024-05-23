package org.springframework.boot.ioc.spi.annotations;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ioc.spi.IMenu;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class MyImportSelector implements ImportBeanDefinitionRegistrar {
	/**
	 * @param importingClassMetadata annotation metadata of the importing class
	 * @param registry               current bean definition registry
	 */
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		List<String> strings = SpringFactoriesLoader.loadFactoryNames(IMenu.class, IMenu.class.getClassLoader());
		strings.forEach(item -> {
			Class<?> aClass = ClassUtils.resolveClassName(item, IMenu.class.getClassLoader());
			// 判断aClass 是否是抽象类
			if (!ObjectUtils.isEmpty(aClass) && !aClass.isInterface()
					&& !Modifier.isAbstract(aClass.getModifiers())
					&& !aClass.isAnnotation()
			) {
				registry.registerBeanDefinition(aClass.getSimpleName(), BeanDefinitionBuilder.rootBeanDefinition(aClass)
						.getBeanDefinition()
				);
			}
		});
	}
}
