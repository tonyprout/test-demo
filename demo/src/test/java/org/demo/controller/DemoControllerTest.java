package org.demo.controller;

import org.demo.DemoService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class DemoControllerTest {

    private DemoController demoController;
    private DemoService demoService;

    @Before
    public void setUp() {
        demoService = mock(DemoService.class);
        demoController = new DemoController(demoService);
    }

    @Test
    public void Can_greet() {

        final String name = someString();
        final String expected = String.format("Hello %s", name);

        // Given
        given(demoService.greet(name)).willReturn(expected);

        // When
        final String actual = demoController.getGreeting(name);

        // Then
        assertThat(actual, is(expected));
    }
}