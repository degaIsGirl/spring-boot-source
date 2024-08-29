package org.springframework.boot.ioc.program;

import lombok.Data;

@Data
public class Animal {
	private String name;

	private Person person;
}
