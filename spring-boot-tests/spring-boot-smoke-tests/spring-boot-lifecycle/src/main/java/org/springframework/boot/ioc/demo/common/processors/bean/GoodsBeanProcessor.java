package org.springframework.boot.ioc.demo.common.processors.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class GoodsBeanProcessor implements BeanFactoryPostProcessor {
	/**
	 * 完成对beanDefinition的修改
	 * @param beanFactory the bean factory used by the application context
	 * @throws BeansException
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		Arrays.asList(beanDefinitionNames).forEach(beanName -> {
			if (beanName.equals("pureTea")) {
				BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
				beanDefinition.setAttribute("name", "纯茶升级版");
				System.out.println(beanDefinition);
			}
		});
	}
}
