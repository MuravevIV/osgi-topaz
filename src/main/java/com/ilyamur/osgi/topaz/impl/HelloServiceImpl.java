package com.ilyamur.osgi.topaz.impl;

import com.ilyamur.osgi.topaz.HelloService;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;

import java.util.Map;
import java.util.Optional;

@Component(immediate = true, metatype = true,
        label = "Hello world OSGi service",
        description = "Hello world OSGi service with configurable message mask")
public class HelloServiceImpl implements HelloService {

    private static final String DEFAULT_MESSAGE_MASK = "Hello, %s!";

    @Property(value = DEFAULT_MESSAGE_MASK,
            label = "Message mask",
            description = "Message mask, for example \"Hello, %s!\" - where %s will be replaced to name")
    private static final String MESSAGE_MASK = "com.ilyamur.osgi.helloworld.service.message-mask";

    private String messageMask;

    @Activate
    public void activate(Map<String, Object> properties) {
        messageMask = Optional.ofNullable((String) properties.get(MESSAGE_MASK)).orElse(DEFAULT_MESSAGE_MASK);
    }

    public String getGreeting(String name) {
        return String.format(messageMask, name);
    }
}
