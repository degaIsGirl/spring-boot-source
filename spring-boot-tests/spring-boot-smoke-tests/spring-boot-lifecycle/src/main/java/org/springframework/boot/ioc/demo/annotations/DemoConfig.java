package org.springframework.boot.ioc.demo.annotations;

import org.springframework.boot.ioc.demo.common.imports.Menu;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {
		"org.springframework.boot.ioc.demo.annotations",
		"org.springframework.boot.ioc.demo.common.scans",
		"org.springframework.boot.ioc.demo.common.processors"
})
@Import(Menu.class)
public class DemoConfig {

}
