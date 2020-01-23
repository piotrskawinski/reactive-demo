package com.example.api.client.demo;

import com.example.api.client.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

@Slf4j
public class SimpleDemo extends AbstractDemo implements Demo {

    @Override
    public void run() {
        getFlux("/customers/", MediaType.APPLICATION_JSON, Customer.class)
                .subscribe(customer -> log.info("{} - Retrieving customer : {}", currentThread(), customer.getCustomerNo()));
    }


}
