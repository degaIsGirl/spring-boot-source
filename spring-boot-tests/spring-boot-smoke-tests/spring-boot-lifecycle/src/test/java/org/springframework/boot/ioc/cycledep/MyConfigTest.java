package org.springframework.boot.ioc.cycledep;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyConfigTest {
	@Test
	public void testCycleDep() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
		A bean = context.getBean(A.class);
		System.out.println(bean);
	}
}
