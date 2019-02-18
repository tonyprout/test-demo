package org.demo;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Configuration
public class DemoClientConfiguration {

    @Bean
    public Client client() {
        return ClientBuilder.newBuilder().register(JacksonJsonProvider.class).build();
    }
}
