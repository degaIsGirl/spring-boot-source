package org.springframework.boot.ioc.factorybeans;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Weather {
	private String state = "rain";
}
