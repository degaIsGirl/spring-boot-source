package org.springframework.boot.ioc.annotations.menu;

public class ActivityMenu implements IMenu {
	@Override
	public void list() {
		System.out.println("activityMenu");
	}
}