package org.springframework.boot.ioc.life;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
@Component
public class Person implements InitializingBean, DisposableBean, Lifecycle {
	@Value("${person.name}")
	private String name;

	private Cat cat;

	private boolean state = false;

	public Person() {
		System.out.println("Person constructor run ......");
	}

	@Autowired
	public Person(Cat cat) {
		this.cat = cat;
		System.out.println("Person constructor with cat params run ......");
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("Person @PostConstruct run ......");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Person InitializingBean run ......");
	}

	public void initMethod() {
		System.out.println("Person initMethod run ......");
	}

	@PreDestroy
	public void preDestory() {
		System.out.println("Person @PreDestroy run ......");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("Person DisposableBean run ......");
	}

	public void destroyMethod() {
		System.out.println("Person destroyMethod run ......");
	}

	@Override
	public void start() {
		System.out.println("Person 睡醒起床了 ......");
		this.state = true;
	}

	@Override
	public void stop() {
		System.out.println("Person 睡觉去了 ......");
		this.state = false;
	}

	@Override
	public boolean isRunning() {
		return state;
	}
}
