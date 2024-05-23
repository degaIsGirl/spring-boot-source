package org.springframework.boot.ioc.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;

@Component
@Log
public class Customer {

	public void order(String goodsName, long no) {
		System.out.println("start to add order");
		this.addGood("小龙虾");
		((Customer) AopContext.currentProxy()).addGood("小龙虾");
	}

	public void addGood(String godsName) {
		System.out.println("add goods " + godsName);
	}
}
