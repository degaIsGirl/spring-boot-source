/*
 * Copyright 2012-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.ioc.xml;

import lombok.Data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.*;
import org.springframework.core.io.ResourceLoader;

import java.awt.*;

@Data
@Slf4j
public class BinaryTea extends Base implements BeanNameAware,
		BeanClassLoaderAware,
		BeanFactoryAware,
		ResourceLoaderAware,
		ApplicationEventPublisherAware,
		MessageSourceAware,
		ApplicationContextAware,
		InitializingBean,
		DisposableBean
{
	private Boss boss;

	private Customer customer;

	private ResourceLoader resourceLoader;

	private String beanName;

	private BeanFactory beanFactory;

	private ClassLoader classLoader;

	private Menu menu;

	private ApplicationContext applicationContext;

	private ApplicationEventPublisher applicationEventPublisher;

	private MessageSource messageSource;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
		log.info("setBeanFactory=> {}", beanFactory);
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
		log.info("setBeanName=> {}", this.beanName);
	}

	@Override
	public void destroy() {
		this.beanName = null;
		log.info("destroy=> {}", this.beanName);
	}

	@Override
	public void afterPropertiesSet() {
		log.info("afterPropertiesSet=> {}", this.beanName);
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
		log.info("setResourceLoader=> {}", resourceLoader);
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
		log.info("setBeanClassLoader=> {}", classLoader);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		log.info("setApplicationContext=> {}", applicationContext);
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
		log.info("setApplicationEventPublisher=> {}", applicationEventPublisher);
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
		log.info("setMessageSource=> {}", messageSource);
	}

}
