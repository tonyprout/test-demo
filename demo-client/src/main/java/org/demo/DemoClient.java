package org.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import static java.lang.String.format;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;

@Component
public class DemoClient {

    private final Client client;
    private final String baseUrl;

    public DemoClient(Client client,
                      @Value("${base.url}") String baseUrl) {
        this.client = client;
        this.baseUrl = baseUrl;
    }

    public String greet(String name) {
        final Response response = client.target(baseUrl)
                .path("greet")
                .queryParam("name", name)
                .request(TEXT_PLAIN_TYPE)
                .get();
        if (response.getStatus() > 299) {
            throw new RuntimeException(format("Status (%s). %s", response.getStatus(), response.readEntity(String.class)));
        }
        return response.readEntity(String.class);
    }
}
