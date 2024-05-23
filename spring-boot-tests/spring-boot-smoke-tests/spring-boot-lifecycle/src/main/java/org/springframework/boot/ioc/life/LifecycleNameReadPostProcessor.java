package org.springframework.boot.ioc.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class LifecycleNameReadPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof Person) {
			Person person = (Person) bean;
			System.out.println("LifecycleNameReadPostProcessor ------> " + person.getName());
		}
		return bean;
	}
}