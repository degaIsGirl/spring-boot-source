package org.springframework.boot.ioc.demo.annotations.goods;

import lombok.Data;

@Data
public class FruitTea implements ITea{
	private String name = "果茶";
}
