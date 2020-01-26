package com.example.api.client.demo;

import com.example.api.client.model.Agreements;
import com.example.api.client.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RequiredArgsConstructor
public class LoadDemo extends AbstractDemo implements Demo {

    private final int numberOfClients;

    @Override
    public void run() {

        Map<Long, Instant> callTimeElapsed = new HashMap<>();

        Instant start = Instant.now();
        final AtomicLong counter = new AtomicLong(0);

        getFlux("/customers", MediaType.APPLICATION_JSON, Customer.class)
                .take(numberOfClients)
                .map(Customer::getCustomerNo)
                .flatMap(custNo -> {
                    callTimeElapsed.put(custNo, Instant.now());
                    return getMono("/agreements/" + custNo, MediaType.APPLICATION_JSON, Agreements.class);
                })
                .subscribe(agreements -> {
                    counter.getAndIncrement();

                    log.info("<- GET 200 : custno '{}' time elaplsed -> {} ms", agreements.getCustomerNo(),
                            (Duration.between(callTimeElapsed.get(agreements.getCustomerNo()), Instant.now()).toMillis())
                    );

                    if (counter.get() == numberOfClients) {
                        log.info("Total elapsed time: {} sec", (Duration.between(start, Instant.now()).toMillis()) / 1000);
                    }
                });
    }

}
