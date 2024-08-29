package org.springframework.boot.actuators;

import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "demo")
public class Demo {
	private Map<String, Feature> features = new ConcurrentHashMap<>();

	@ReadOperation
	public Map<String, Feature> features() {
		return features;
	}

	@ReadOperation
	public Feature feature(@Selector String name) {
		return features.get(name);
	}

	@WriteOperation
	public void configureFeature(@Selector String name, Feature feature) {
		features.put(name, feature);
	}

	@DeleteOperation
	public void deleteFeature(@Selector String name) {
		features.remove(name);
	}

	public static class Feature {
		private Boolean enabled;
	}
}
