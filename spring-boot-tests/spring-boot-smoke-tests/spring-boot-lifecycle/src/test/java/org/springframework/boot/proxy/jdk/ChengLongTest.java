package org.springframework.boot.proxy.jdk;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class ChengLongTest {

	/**
	 * jdk 动态代理
	 *
	 */
	@Test
	public void testJdkProxy() {
		//origin
		ChengLong chengLong = new ChengLong();
		chengLong.signContract(100);

		SuperStar superStarProxy = (SuperStar)Proxy.newProxyInstance(
				chengLong.getClass().getClassLoader(),
				chengLong.getClass().getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						int money = (int) args[0];
						if (money < 100) {
							System.out.println("<100w, 不要谈了");
							return null;
						} else {
							System.out.println(">=100w, 可以继续");
							return method.invoke(chengLong, args);
						}
					}
				}
		);
		superStarProxy.signContract(10);
	}

	/**
	 * cglib动态代理, 和jdk动态代理不同的地方是, cglib是采用继承的方式, 所以被代理的class不可以是final的
	 * 看了下cglib是在的包是org.springframework.cglib.proxy, 我是不是可以认为cglib就是spring自己搞的
	 *
	 */
	@Test
	void testCglib() {
		ChengLong chengLong = new ChengLong();

		// 使用Enhancer 去创建动态代理对象
		Enhancer enhancer = new Enhancer();

		// 设置要代理的对象
		enhancer.setSuperclass(ChengLong.class);

		// 设置代理对象的回调方法, 也就是我们进行功能增强的部分
		// setCallback的参数类型为org.springframework.cglib.proxy.Callback
		// 我们一般用org.springframework.cglib.proxy.MethodInterceptor
		enhancer.setCallback(
				new MethodInterceptor() {
					@Override
					public Object intercept(Object proxy, // enhancer代理对象
											Method method, // 被代理的方法
											Object[] args, // 方法的参数
											MethodProxy methodProxy // 对应方法的代理
					) throws Throwable {
						int money = (int) args[0];
						if (money < 100) {
							System.out.println("<100w, 不要谈了");
							return null;
						} else {
							System.out.println(">=100w, 可以继续");

							// 执行代理对象的方法
							//method.invoke(proxy, args);
							// 执行原始对象(被代理对象)的方法
							//methodProxy.invokeSuper(proxy, args);

							// 切记, 避免使用method.invoke(o, args), 这样会进入死循环
							return methodProxy.invokeSuper(proxy, args);
						}
					}
				}
		);

		// 开始创建代理对象
		ChengLong chengLongProxy = (ChengLong)enhancer.create();
		boolean b = chengLongProxy.signContract(100);
	}
}