package org.springframework.boot.ioc.annotations.menu;

public class NormalMenu implements IMenu{
	@Override
	public void list() {
		System.out.println("NormalMenu");
	}
}
