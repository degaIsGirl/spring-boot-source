package org.springframework.boot.ioc.annotations;

import lombok.Data;
import org.springframework.boot.ioc.annotations.MyImport.Worker;

@Data
@MyComponent
public class Boss {
	private String name;

	private Worker worker;
	public Boss() {
		System.out.println("Boss construct");
	}
}
