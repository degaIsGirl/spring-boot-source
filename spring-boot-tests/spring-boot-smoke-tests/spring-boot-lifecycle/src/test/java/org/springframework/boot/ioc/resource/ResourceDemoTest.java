package org.springframework.boot.ioc.resource;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

@SpringBootTest
public class ResourceDemoTest {

	@Test
	public void testFile() {
		try {
			/**
			 * 读取文件
			 */
			File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "knowledge.yml");
			if (file.exists() && file.canRead()) {
				List<String> strings = FileUtils.readLines(file);
				System.out.println(strings);
			}
			System.out.println(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testYaml() {
		DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
		Resource resource = defaultResourceLoader.getResource(ResourceUtils.CLASSPATH_URL_PREFIX + "knowledge.yml");
		YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
		try {
			List<PropertySource<?>> test = yamlPropertySourceLoader.load("test", resource);
			System.out.println(test);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
