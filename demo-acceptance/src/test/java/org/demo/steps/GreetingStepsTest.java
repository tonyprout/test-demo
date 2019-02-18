package org.demo.steps;

import org.demo.DemoClient;
import org.demo.factory.NameFactory;
import org.demo.holder.GreetingResponseHolder;
import org.demo.holder.NameHolder;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaString;

public class GreetingStepsTest {

    private GreetingSteps greetingSteps;
    private NameHolder nameHolder;
    private NameFactory nameFactory;
    private DemoClient demoClient;
    private GreetingResponseHolder responseHolder;

    @Before
    public void setUp() {
        nameFactory = mock(NameFactory.class);
        nameHolder = mock(NameHolder.class);
        demoClient = mock(DemoClient.class);
        responseHolder = mock(GreetingResponseHolder.class);
        greetingSteps = new GreetingSteps(nameFactory, nameHolder, demoClient, responseHolder);
    }

    @Test
    public void Can_save_a_valid_name_to_the_name_holder() {

        final String name = someAlphaString();

        // Given
        given(nameFactory.generateName()).willReturn(name);

        // When
        greetingSteps.theUserHasAValidName();

        // Then
        then(nameHolder).should().set(name);
    }

    @Test
    public void Can_call_greeting_endpoint_with_valid_name() {

        final String name = someAlphaString(1, 100);

        // Given
        given(nameHolder.get()).willReturn(name);

        // When
        greetingSteps.theGreetingEndpointIsUsedWithThisName();

        // Then
        then(demoClient).should().greet(name);
//        then(responseHolder).should().set(String.format("Hello %s", name));
    }

    @Test
    public void Can_check_for_response() {

        final String name = someAlphaString(1, 100);
        final String expected = String.format("Hello %s", name);

        // Given
        given(nameHolder.get()).willReturn(name);
        given(responseHolder.get()).willReturn(expected);

        // When
        greetingSteps.aGreetingIsReturnedToTheUser();

        // Then
        then(responseHolder).should().get();
    }
}