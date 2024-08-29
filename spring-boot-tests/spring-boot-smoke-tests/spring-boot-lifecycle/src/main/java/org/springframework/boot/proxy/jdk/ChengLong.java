package org.springframework.boot.proxy.jdk;

public class ChengLong implements SuperStar{
	@Override
	public Boolean signContract(int money) {
		System.out.println("我是成龙, 收到打款" + money + "w, 我要签合同了");
		return true;
	}

	@Override
	public void play() {
		System.out.println("我是成龙, 我要演戏了");
	}
}
