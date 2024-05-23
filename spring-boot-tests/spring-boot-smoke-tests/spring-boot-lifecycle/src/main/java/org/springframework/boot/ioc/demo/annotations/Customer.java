package org.springframework.boot.ioc.demo.annotations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Monitory(time = true, memory = false)
public class Customer {

	@Value("${a}")
	private String debug;

	public Customer() {

	}
	@PostConstruct
	public void test() {
		System.out.println("sdf");
	}
}
