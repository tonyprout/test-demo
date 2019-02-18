package org.demo;

import org.springframework.stereotype.Component;

import static java.lang.String.*;

@Component
public class DemoService {

    public String greet(String name) {
        return format("Hello %s",name);
    }
}
