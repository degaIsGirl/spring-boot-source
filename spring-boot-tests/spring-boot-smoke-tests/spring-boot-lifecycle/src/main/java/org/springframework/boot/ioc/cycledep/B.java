package org.springframework.boot.ioc.cycledep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {
	@Autowired
	A a;
}
