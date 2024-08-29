package org.springframework.boot.ioc.factorybeans;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootTest
class WorkerTest {

	@Test
	public void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Worker.class);
		Worker bean = context.getBean(Worker.class);
		bean.go();

		Weather weather = context.getBean(Weather.class);
		weather.setState("sunny");
		bean.go();
	}
}