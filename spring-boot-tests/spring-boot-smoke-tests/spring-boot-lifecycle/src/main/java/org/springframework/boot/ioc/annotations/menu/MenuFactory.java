package org.springframework.boot.ioc.annotations.menu;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
public class MenuFactory implements FactoryBean<IMenu> {

	@Resource
	ApplicationContext applicationContext;

	@Override
	public IMenu getObject() throws Exception {
		boolean isInActivity = this.applicationContext.getEnvironment().getProperty("isInActivity", boolean.class, false);
		if (isInActivity) {
			return new ActivityMenu();
		} else {
			return new NormalMenu();
		}
	}

	@Override
	public Class<?> getObjectType() {
		return NormalMenu.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
}