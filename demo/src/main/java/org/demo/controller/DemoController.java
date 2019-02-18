package org.demo.controller;

import org.demo.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping(path = "greet", params = "name")
    public String getGreeting(@RequestParam String name) {
        log.info(String.format("Attempting to greet %s", name));
        return demoService.greet(name);
    }
}
