package org.springframework.boot.ioc.factorybeans;

public class Bike implements Traffic{
	@Override
	public void drive() {
		System.out.println("I am ride bike");
	}
}
