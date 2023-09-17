package com.example.demo.base.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Configuration
public class RestTemplateConfig {
    private final String username = "elastic";
    private final String password = "02#c1417";

    private final String encodedCredentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .rootUri("http://hz3.starwrap.net:9200")
                .defaultHeader("Authorization","Basic " + encodedCredentials)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36 Edg/116.0.1938.62")
                .build();
    }
}