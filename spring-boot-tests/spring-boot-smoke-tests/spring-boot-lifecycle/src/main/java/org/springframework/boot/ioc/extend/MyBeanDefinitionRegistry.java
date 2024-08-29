package org.springframework.boot.ioc.extend;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor {

	/**
	 * 用于新增beanDefinition
	 * @param registry the bean definition registry used by the application context
	 * @throws BeansException
	 */
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		registry.registerBeanDefinition(
				"zhangsan",
				BeanDefinitionBuilder.genericBeanDefinition(ZhangSan.class)
						.addPropertyValue("name", "zhangsan")
						.getBeanDefinition()
		);
	}

	/**
	 * 用于修改beanDefinition的定义
	 * @param beanFactory the bean factory used by the application context
	 * @throws BeansException
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		if (beanFactory.containsBean("zhangsan")) {
			BeanDefinition zhangsan = beanFactory.getBeanDefinition("zhangsan");
			zhangsan.getPropertyValues().add("age", 18);
		}
	}
}
