package org.springframework.boot.ioc.demo.common.scans;

import lombok.Data;
import lombok.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AliPay {
	private String name = "aliPay";

	private String fee;

//	public AliPay(String name) {
//		this.name = name;
//	}
}
