package org.springframework.boot.ioc.demo.common.scans;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class WxPay {
	private String name = "wxPay";

	private String fee;
}
