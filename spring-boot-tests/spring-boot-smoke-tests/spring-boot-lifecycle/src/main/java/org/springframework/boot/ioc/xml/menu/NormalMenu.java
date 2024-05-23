package org.springframework.boot.ioc.xml.menu;

import org.springframework.stereotype.Component;

@Component
public class NormalMenu implements IMenu {
	@Override
	public void list() {
		System.out.println("NormalMenu");
	}
}
