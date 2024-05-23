package org.springframework.boot.ioc.annotations.MyImport;

import org.springframework.boot.ioc.annotations.MyComponent;

@MyComponent
public class Worker {
	public void start() {
		System.out.println("工人开始干活了");
	}

	public Worker() {
		System.out.println("worker construct");
	}
}
