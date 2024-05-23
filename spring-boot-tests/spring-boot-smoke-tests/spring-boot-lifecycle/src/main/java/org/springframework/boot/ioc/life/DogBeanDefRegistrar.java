package org.springframework.boot.ioc.life;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.ioc.life.dogs.MuBian;
import org.springframework.boot.ioc.life.dogs.Shizi;
import org.springframework.stereotype.Component;

/**
 * 完成对beanDefinition的注册行为
 */
@Component
@Data
public class DogBeanDefRegistrar implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		registry.registerBeanDefinition("muBian",
				BeanDefinitionBuilder.rootBeanDefinition(MuBian.class)
						.addPropertyValue("name", "mubian")
						.getBeanDefinition()
		);
		registry.registerBeanDefinition("shizi",
				BeanDefinitionBuilder.rootBeanDefinition(Shizi.class)
						.addPropertyValue("name", "shizi")
						.getBeanDefinition()
		);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}
}
