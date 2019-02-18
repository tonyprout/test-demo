package org.demo;

import org.junit.Test;

import static java.lang.String.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class DemoServiceTest {

    @Test
    public void Can_greet_a_user() {

        final String name = someString();

        // When
        final String actual = new DemoService().greet(name);

        // Then
        assertThat(actual, is(format("Hello %s",name)));
    }
}