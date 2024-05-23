package org.springframework.boot.ioc.life.dogs;

import lombok.Data;

@Data
public class BasicDog {
	protected String introduce = "I am a dog!!! My name is ";

	protected String name;

	public void getDogIntroduce() {
		System.out.println(this.introduce + name);
	}
}
