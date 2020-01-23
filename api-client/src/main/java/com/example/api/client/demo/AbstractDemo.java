package com.example.api.client.demo;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class AbstractDemo {

    static final String BASE_SERVER_URL = "http://localhost:8080";

    private static WebClient client = WebClient.create(BASE_SERVER_URL);

    static <T> Flux<T> getFlux(String path, MediaType mediaType, Class<T> clazz) {
        return retrieve(path, mediaType)
                .bodyToFlux(clazz);
    }

    static <T> Mono<T> getMono(String path, MediaType mediaType, Class<T> clazz) {
        return retrieve(path, mediaType)
                .bodyToMono(clazz);
    }

    private static WebClient.ResponseSpec retrieve(String path, MediaType mediaType) {
        return client.get().uri(path)
                .accept(mediaType)
                .retrieve();
    }

    static String currentThread() {
        return Thread.currentThread().getName();
    }

}
