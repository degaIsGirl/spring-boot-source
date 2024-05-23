package org.springframework.boot.ioc.annotations;

@MyComponent
public class Customer {
	public Customer() {
		System.out.println("customer construct");
	}
}
