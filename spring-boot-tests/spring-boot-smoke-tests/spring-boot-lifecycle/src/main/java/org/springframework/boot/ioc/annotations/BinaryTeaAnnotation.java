package org.springframework.boot.ioc.annotations;

import org.springframework.boot.ioc.annotations.menu.IMenu;
import org.springframework.boot.ioc.annotations.menu.MenuFactory;

import javax.annotation.Resource;

public class BinaryTeaAnnotation {
	@Resource
	Boss boss;

	@Resource
	Customer customer;

	/**
	 * 注入由MenuFactory的getObject()方法创建的对象
	 *
	 * 经过测试属性的类型也就是IMenu 要和MenuFactory的getObjectType方法返回的类型能够兼容
	 * 也就是说IMenu 能够转换为MenuFactory的getObject()方法返回的类型
	 *
	 * 由MenuFactory创建的对象都是延迟加载的, 也就是说只有在使用的时候才会被创建
	 *
	 * 由MenuFactory的isSingleton方法来决定返回的对象是否是单例的
	 *
	 * 最终效果为节假日的时候使用的是ActivityMenu, 非节假日的时候使用的是NormalMenu
	 */
	@Resource
	IMenu iMenu;

	/**
	 * 虽然MenuFactory也是一个被ioc管理的bean对象, 但是和正常的bean对象的注入还是有所区别的
	 *
	 * 1、如果我们希望注入MenuFactory这个本身的bean对象, 那么需要在前面加上&符号
	 * 否则, 注入的将是MenuFactory的getObject()方法创建的对象,也就是IMenu
	 */
	@Resource(name="&menuFactory")
	MenuFactory menuFactory;

	@Resource
	IMenu iMenu1;

	public void test() {
		System.out.println(this.boss);
		System.out.println(this.customer);
		System.out.println(this.iMenu);
		System.out.println(this.iMenu1);
		System.out.println(this.menuFactory);
		System.out.println(this.boss);
	}
}
