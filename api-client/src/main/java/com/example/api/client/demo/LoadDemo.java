package com.example.api.client.demo;

import com.example.api.client.model.Agreements;
import com.example.api.client.model.Customer;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
public class LoadDemo extends AbstractDemo implements Demo {

    private final int numberOfClients;

    @Override
    public void run() {
        Instant start = Instant.now();
        final Counter counter = new Counter();

        getFlux("/customers", MediaType.APPLICATION_JSON, Customer.class)
                .take(numberOfClients)
                .map(Customer::getCustomerNo)
                .flatMap(custNo -> getMono("/agreements/" + custNo, MediaType.APPLICATION_JSON, Agreements.class))
                .subscribe(agreements -> {
                    counter.increment();
                    Instant end = Instant.now();
                    log.info("<- GET 200 : custno {}'", agreements.getCustomerNo());
                    if (counter.getCount() == numberOfClients) {
                        log.info("Elapsed time: {} sec", (Duration.between(start, end).toMillis()) / 1000);
                    }
                });
    }

    @Data
    static
    class Counter {
        long count = 0;

        void increment() {
            count++;
        }
    }


}
