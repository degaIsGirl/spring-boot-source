package org.springframework.boot.ioc.event;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听
 */
@Component
public class SubscribeEvent {

	@EventListener
	public void subRefresh(ContextRefreshedEvent contextRefreshedEvent) {
		System.out.println("sub event contextRefreshedEvent by annotation");
	}

	@EventListener
	public void subContextClose(ContextClosedEvent contextClosedEvent) {
		System.out.println("sub event contextClosedEvent by annotation");
	}

	/**
	 * org.springframework.beans.factory.BeanInitializationException:
	 * Failed to process @EventListener annotation on bean with name 'subscribeEvent';
	 * nested exception is java.lang.IllegalStateException:
	 *
	 * 如果我们尝试添加另一个参数, 发现报错了, 明确的告知我们最多只能有一个参数
	 * Maximum one parameter is allowed for event listener method:
	 * public void org.springframework.boot.ioc.event.SubscribeEvent.subRegisterSuccess(
	 * 		org.springframework.boot.ioc.event.RegisterSuccessEvent,
	 * 		int
	 * )
	 *
	 * @param registerSuccessEvent
	 * @param age
	 */
	@EventListener
	public void subRegisterSuccess(RegisterSuccessEvent registerSuccessEvent
			 //,int age
	) {
		System.out.println("register sucess : msg => " + registerSuccessEvent.getContent() + " uid:" + registerSuccessEvent.getUid());
	}
}
