package org.springframework.boot.ioc.module;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * condition 判断
 */
public class ConditionDebugMatcher implements Condition{
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String debug = context.getEnvironment().getProperty("debug", "false");
		return debug.equalsIgnoreCase("true");
	}
}
