package org.springframework.boot.ioc.event;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
public class SpringEventTest {

	/**
	 * 监听容器的内部事件
	 * 监听的方式有两种:
	 * 	1、接口继承的方式, 通过实现ApplicationListener<事件类型>
	 * 	2、通过注解的方式, 在指定方法上标注@EventListener, 方法的入参为要监听的事件类型
	 * 通过测试后发现, 注解方式实现的优先级会高于接口继承, 即注解标注的会先执行, 推荐使用注解, 原因是代码更加的简洁
	 */
	@Test
	public void testApplication() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				AppRefreshListener.class.getPackage().getName()
		);
		context.close();
	}

	/**
	 * 自定义事件的发布, 这里我们以注解方式为例子
	 *
	 * 如果希望通过接口的方式去监听的话, 事件对象需要 extends ApplicationEvent
	 *
	 * 这里我们需要说下事件的发布器:
	 * 这里的demo我们直接使用的是AnnotationConfigApplicationContext 容器去发送的, 但是实际上容器是组合了
	 * private ApplicationEventMulticaster applicationEventMulticaster; 通过它去完成的发布
	 * 具体的实现类是: SimpleApplicationEventMulticaster
	 */
	@Test
	public void testSelfEvent() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SubscribeEvent.class);
		RegisterSuccessEvent registerSuccessEvent = new RegisterSuccessEvent();
		registerSuccessEvent.setUid(1);
		registerSuccessEvent.setContent("this is a test");
		context.publishEvent(registerSuccessEvent);
	}
}
