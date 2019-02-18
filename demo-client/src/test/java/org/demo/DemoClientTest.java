package org.demo;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static java.lang.String.format;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomIntegers.someIntegerBetween;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaString;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class DemoClientTest {

    private Client client;
    private DemoClient demoClient;
    private String baseUrl;

    @Before
    public void setUp() {
        client = mock(Client.class, RETURNS_DEEP_STUBS);
        baseUrl = someAlphaString();
        demoClient = new DemoClient(client, baseUrl);
    }

    @Test
    public void Can_greet_user() {

        final WebTarget target = mock(WebTarget.class);
        final WebTarget greetPath = mock(WebTarget.class);
        final String name = someAlphaString(1,100);
        final WebTarget queryTarget = mock(WebTarget.class);
        final Invocation.Builder builder = mock(Invocation.Builder.class);
        final Response response = mock(Response.class);
        final String expected = format("Hello %s", name);

        // Given
        given(client.target(baseUrl)).willReturn(target);
        given(target.path("greet")).willReturn(greetPath);
        given(greetPath.queryParam("name", name)).willReturn(queryTarget);
        given(queryTarget.request(TEXT_PLAIN_TYPE)).willReturn(builder);
        given(builder.get()).willReturn(response);
        given(response.readEntity(String.class)).willReturn(expected);

        // When
        final String actual = demoClient.greet(name);

        // Then
        assertThat(actual, equalTo(expected));
    }

    // This is an alternative way to test the client
    @Test
    public void Can_greet_user_test_with_deep_stubs() {

        final String name = someAlphaString(1,100);
        final String expected = format("Hello %s", name);

        // Given
        given(client.target(baseUrl)
                .path("greet")
                .queryParam("name", name)
                .request(TEXT_PLAIN_TYPE)
                .get().readEntity(String.class)
        ).willReturn(expected);

        // When
        final String actual = demoClient.greet(name);

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_fail_to_greet_user() {

        final WebTarget target = mock(WebTarget.class);
        final WebTarget greetPath = mock(WebTarget.class);
        final String name = someAlphaString(1,100);
        final WebTarget queryTarget = mock(WebTarget.class);
        final Invocation.Builder builder = mock(Invocation.Builder.class);
        final Response response = mock(Response.class);
        final Integer status = someIntegerBetween(300, 500);
        final String body = someString();

        // Given
        given(client.target(baseUrl)).willReturn(target);
        given(target.path("greet")).willReturn(greetPath);
        given(greetPath.queryParam("name", name)).willReturn(queryTarget);
        given(queryTarget.request(TEXT_PLAIN_TYPE)).willReturn(builder);
        given(builder.get()).willReturn(response);
        given(response.getStatus()).willReturn(status);
        given(response.readEntity(String.class)).willReturn(body);

        // When
        final Throwable actual = catchThrowable(() -> demoClient.greet(name));

        // Then
        assertThat(actual, instanceOf(RuntimeException.class));
        assertThat(actual.getMessage(), equalTo(format("Status (%s). %s", status, body)));
    }
}