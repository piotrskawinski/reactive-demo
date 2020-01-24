
package com.example.api.service.customer;

import com.example.api.service.customer.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private static final String LEGACY_BASE_URL = "http://localhost:8082";
    private static final WebClient client = WebClient.create(LEGACY_BASE_URL);

    public Flux<Customer> getCustomers() {
        return client.get()
                .uri("/customers")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Customer.class);
    }

}