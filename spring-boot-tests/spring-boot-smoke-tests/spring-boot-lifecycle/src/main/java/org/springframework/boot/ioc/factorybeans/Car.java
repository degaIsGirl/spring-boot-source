package org.springframework.boot.ioc.factorybeans;

public class Car implements Traffic{
	@Override
	public void drive() {
		System.out.println("I am driving car");
	}
}
