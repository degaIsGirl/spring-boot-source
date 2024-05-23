package org.springframework.boot.ioc.demo.annotations.goods;

import lombok.Data;

@Data
public class PureTea implements ITea{
	private String name = "纯茶";

	public PureTea() {
		System.out.println("debug");
	}
}
