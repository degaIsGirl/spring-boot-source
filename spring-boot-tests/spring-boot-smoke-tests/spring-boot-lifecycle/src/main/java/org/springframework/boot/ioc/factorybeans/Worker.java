package org.springframework.boot.ioc.factorybeans;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@ComponentScan
public class Worker {
	@Resource
	Traffic traffic;

	public void go() {
		traffic.drive();
	}
}
