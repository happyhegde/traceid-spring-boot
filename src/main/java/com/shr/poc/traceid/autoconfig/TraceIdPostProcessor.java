package com.shr.poc.traceid.autoconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

public class TraceIdPostProcessor implements EnvironmentPostProcessor {

    public static final String PROPERTY_SOURCE_NAME = "defaultProperties";

    /**
     * The postProcessEnvironment function is to modify
     * the environment parameters, and include the property
     * logging.pattern.level, so that we can modify the pattern
     * of the logging level and include the x-trace-id while logging
     *
     * @param environment
     * @param application
     */

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> map = new HashMap<>();

        // making sure the tracing is not disabled
        if (Boolean.parseBoolean(environment.getProperty("tracing.enabled", "true"))) {
            // the pattern of the logging is changed to include trace-id
            map.put("logging.pattern.level", "%5p [X-TRACE-ID: %X{xtraceid}]");
        }
        map.put("spring.aop.proxyTargetClass", "true");
        // add the new set of properties to environment variables
        appendOrWrite(environment.getPropertySources(), map);
    }

    private void appendOrWrite(MutablePropertySources propertySources, Map<String, Object> map) {
        MapPropertySource target = null;
        if (propertySources.contains(PROPERTY_SOURCE_NAME)) {
            PropertySource<?> source = propertySources.get(PROPERTY_SOURCE_NAME);
            if (source instanceof MapPropertySource) {
                target = (MapPropertySource) source;
                for (String key : map.keySet()) {
                    if (!target.containsProperty(key)) {
                        target.getSource().put(key, map.get(key));
                    }
                }
            }
        }
        if (target == null) {
            target = new MapPropertySource(PROPERTY_SOURCE_NAME, map);
        }
        if (!propertySources.contains(PROPERTY_SOURCE_NAME)) {
            propertySources.addLast(target);
        }
    }
}
