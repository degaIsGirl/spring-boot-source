package org.springframework.boot.ioc.conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;

public class OnBossMatch implements Condition {

	/**
	 * @// TODO: 2024/5/8
	 * 发现会被调用多次, 而且每次context.getBeanFactory()
	 * @param context the condition context
	 * @param metadata the metadata of the {@link org.springframework.core.type.AnnotationMetadata class}
	 * or {@link org.springframework.core.type.MethodMetadata method} being checked
	 * @return
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		System.out.println(context.getRegistry().getBeanDefinitionNames());
		return context.getBeanFactory().containsBeanDefinition("boss");
	}
}
