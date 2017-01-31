package com.ilyamur.osgi.topaz.service.impl;

import com.ilyamur.osgi.topaz.service.HelloService;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;

import java.util.Map;
import java.util.Optional;

@Component(immediate = true, metatype = true,
        label = "Hello world OSGi service",
        description = "Hello world OSGi service with configurable message mask")
@Service(HelloService.class)
public class HelloServiceImpl implements HelloService {

    private static final String DEFAULT_MESSAGE_MASK = "Hello, %s!";

    @Property(value = DEFAULT_MESSAGE_MASK,
            label = "Message mask",
            description = "Message mask, for example \"Hello, %s!\" - where %s will be replaced to name")
    private static final String MESSAGE_MASK = "com.ilyamur.osgi.topaz.service.message-mask";

    private String messageMask;

    @Activate
    public void activate(Map<String, Object> properties) {
        messageMask = Optional.ofNullable((String) properties.get(MESSAGE_MASK)).orElse(DEFAULT_MESSAGE_MASK);
    }

    public String getGreeting(String name) {
        return String.format(messageMask, name);
    }
}
