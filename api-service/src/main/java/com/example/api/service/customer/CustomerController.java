
package com.example.api.service.customer;


import com.example.api.service.customer.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Customer> getCustomers() {
        log.info("-> GET /customers");

        return customerService.getCustomers();
    }

}