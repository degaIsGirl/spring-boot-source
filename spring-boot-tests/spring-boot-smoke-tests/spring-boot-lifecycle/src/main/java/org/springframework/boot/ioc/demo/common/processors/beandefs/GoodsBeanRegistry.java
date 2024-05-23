package org.springframework.boot.ioc.demo.common.processors.beandefs;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.ioc.demo.annotations.goods.ITea;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class GoodsBeanRegistry implements BeanDefinitionRegistryPostProcessor {

	/**
	 * 完成对beanDefinition的注册
	 * @param registry the bean definition registry used by the application context
	 * @throws BeansException
	 */
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		List<String> strings = SpringFactoriesLoader.loadFactoryNames(ITea.class, ITea.class.getClassLoader());
		if (!strings.isEmpty()) {
			strings.forEach(clazzName -> {
				AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(
						ClassUtils.resolveClassName(clazzName, this.getClass().getClassLoader()))
						.getBeanDefinition();
				String[] split = StringUtils.tokenizeToStringArray(clazzName, ".");
				registry.registerBeanDefinition(StringUtils.uncapitalize(split[split.length-1]), beanDefinition);
			});
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}
}
