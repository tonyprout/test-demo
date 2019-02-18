package org.demo.factory;

import org.springframework.stereotype.Component;

import static shiver.me.timbers.data.random.RandomStrings.someAlphaString;

@Component
public class NameFactory {

    public String generateName() {
        return someAlphaString(1,10);
    }
}
