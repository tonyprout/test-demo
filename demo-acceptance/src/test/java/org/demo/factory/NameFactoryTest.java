package org.demo.factory;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class NameFactoryTest {

    @Test
    public void Can_generate_name() {

        // When
        final String actual = new NameFactory().generateName();

        // Then
        assertThat(actual, is(notNullValue()));
    }
}