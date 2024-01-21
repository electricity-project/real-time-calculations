package com.electricity.project.realtimecalculations.core.client.calcdbaccess;

import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationDTO;
import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationFilterDTO;
import com.electricity.project.realtimecalculations.api.production.PowerProductionDTO;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CalculationsAccessDbApiClient implements CalculationsAccessDbClient {
    private final WebClient client;

    public CalculationsAccessDbApiClient(@Value("${calculations.db.access.url}") String baseUrl) {
        HttpClient httpClient = HttpClient.create().responseTimeout(Duration.ofSeconds(10));
        client = WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Override
    public List<PowerProductionDTO> getPowerProductionByMinute(@NonNull ZonedDateTime time) {
        return client.get()
                .uri("/power-production/date", uriBuilder -> uriBuilder
                        .queryParam("time", time.format(DateTimeFormatter.ISO_INSTANT))
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToFlux(PowerProductionDTO.class)
                .collectList()
                .flatMap(body -> {
                    if (body.isEmpty()) {
                        return Mono.error(new Throwable());
                    }
                    return Mono.just(body);
                })
                .retryWhen(Retry.fixedDelay(5, Duration.ofSeconds(10)))
                .block();
    }

    @Override
    public List<PowerStationDTO> getFilteredStations(@NonNull PowerStationFilterDTO powerStationFilterDTO) {
        return client.post()
                .uri("/power-station/all_filter_list", UriBuilder::build)
                .body(BodyInserters.fromValue(powerStationFilterDTO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToFlux(PowerStationDTO.class)
                .collectList()
                .retry(3)
                .block();
    }
}
