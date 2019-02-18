package org.demo.steps;

import cucumber.api.Scenario;
import org.demo.holder.GenericHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class HooksTest {

    @Mock
    private List<GenericHolder> holders;

    @InjectMocks
    private Hooks hooks;

    @Test
    @SuppressWarnings("unchecked")
    public void Setup_does_nothing() {

        // When
        hooks.setup();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Tear_down_will_not_log_any_holders_if_the_scenario_passes() {

        final Scenario scenario = mock(Scenario.class);

        // Given
        given(scenario.isFailed()).willReturn(false);

        // When
        hooks.tearDown(scenario);

        // Then
        verifyZeroInteractions(holders);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void Tear_down_will_log_all_holders_if_the_scenario_fails() {

        final Scenario scenario = mock(Scenario.class);

        // Given
        given(scenario.isFailed()).willReturn(true);
        willAnswer((Answer<Void>) invocation -> {
            ((Consumer) invocation.getArgument(0)).accept(mock(GenericHolder.class));
            return null;
        }).given(holders).forEach(any(Consumer.class));

        // When
        hooks.tearDown(scenario);

        // Then
        then(holders).should().forEach(any(Consumer.class));
    }
}
