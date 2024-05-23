package org.springframework.boot.ioc.spi;

public class NormalMenu extends IMenu {
	@Override
	public void list() {
		System.out.println("NormalMenu");
	}
}
