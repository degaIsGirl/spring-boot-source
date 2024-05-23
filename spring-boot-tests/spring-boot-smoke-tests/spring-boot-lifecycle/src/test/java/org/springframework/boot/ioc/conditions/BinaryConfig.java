package org.springframework.boot.ioc.conditions;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BinaryConfig {
	@Test
	public void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BinaryTeaConfig.class);
		Worker bean = context.getBean(Worker.class);
		System.out.println(bean);
	}
}
