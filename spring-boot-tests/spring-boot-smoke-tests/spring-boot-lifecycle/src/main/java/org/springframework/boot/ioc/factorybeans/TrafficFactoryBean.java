package org.springframework.boot.ioc.factorybeans;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TrafficFactoryBean implements FactoryBean<Traffic> {
	@Resource
	Weather weather;;

	@Override
	public Traffic getObject() throws Exception {
		if (this.weather.getState().equals("rain")) {
			return new Car();
		} else {
			return new Bike();
		}
	}

	@Override
	public Class<?> getObjectType() {
		return Traffic.class;
	}

	public boolean isSingleton() {
		return false;
	}
}
