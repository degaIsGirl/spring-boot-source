package org.springframework.boot.ioc.conditions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan()
public class BinaryTeaConfig {
	@Bean
	public Boss boss() {
		return new Boss();
	}
}
