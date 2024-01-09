package com.electricity.project.realtimecalculations.core.client.centralmodule;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Component
public class CentralApiClient implements CentralClient {
    private final WebClient client;

    @Value("${mediative.mapping.url}")
    private String mediativeMappingUrl;

    public CentralApiClient(@Value("${central.module.url}") String baseUrl) {
        HttpClient httpClient = HttpClient.create().responseTimeout(Duration.ofSeconds(10));
        client = WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Override
    public Void startPowerStation(@NonNull String ipv6Address) {
        return client.get()
                .uri(mediativeMappingUrl+"/start", uriBuilder -> uriBuilder
                        .queryParam("ipv6Address", ipv6Address)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(Void.class)
                .retry(3)
                .block();
    }

    @Override
    public Void stopPowerStation(@NonNull String ipv6Address) {
        return client.get()
                .uri(mediativeMappingUrl+"/stop", uriBuilder -> uriBuilder
                        .queryParam("ipv6Address", ipv6Address)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(Void.class)
                .retry(3)
                .block();
    }
}
