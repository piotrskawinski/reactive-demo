package com.example.api.client.demo;

import com.example.api.client.model.Customer;
import com.example.api.client.model.Agreements;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

@Slf4j
public class AsyncDemo extends AbstractDemo implements Demo {

    @Override
    public void run() {
        getFlux("/customers", MediaType.APPLICATION_JSON, Customer.class)
                // .log()
                .take(5000)
                .flatMap(id ->
                        getMono("/agreements/" + id.getCustomerNo(), MediaType.APPLICATION_JSON, Agreements.class)
                )
                // .log()
                .subscribe(o -> log.info("Customer '{}' has {} agreements", o.getCustomerNo(), o.totalAgreements()));

    }

}
