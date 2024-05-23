package org.springframework.boot.ioc.conditions;

import org.springframework.stereotype.Component;

@Component
public class Boss {
	public Boss() {
		System.out.println("boss construct");
	}
}
