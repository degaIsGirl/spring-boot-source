package org.springframework.boot.ioc.event;

import lombok.Data;

@Data
public class RegisterSuccessEvent {
	private String content;

	private int uid;
}
