package org.springframework.boot.ioc.demo.annotations;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Data
@Component
public class BinaryTea {
	@Resource
	private Boss boss;

	@Resource
	private Customer customer;
}
