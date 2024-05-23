package org.springframework.boot.ioc.xml;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Base {
	public void hello(String world) {
		try {
			log.info("content {}",  world);
			Thread.sleep(500);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
