package org.springframework.boot.ioc.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 完成对beanDefinition 的修改
 */
@Component
public class DogBeanDefProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		if (beanFactory.containsBeanDefinition("muBian")) {
			BeanDefinition muBian = beanFactory.getBeanDefinition("muBian");
			MutablePropertyValues propertyValues = muBian.getPropertyValues();
			propertyValues.add("name", "mubian-v2");
		}
	}
}
