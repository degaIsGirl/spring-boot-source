package org.springframework.boot.ioc.spi;

public class ActivityMenu extends IMenu {
	@Override
	public void list() {
		System.out.println("activityMenu");
	}
}