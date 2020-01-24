package com.example.api.client.demo;

import com.example.api.client.model.Customer;
import com.example.api.client.model.Agreements;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ParallelDemo extends AbstractDemo implements Demo {

    @Override
    public void run() {
        getFlux("/customers", MediaType.APPLICATION_JSON, Customer.class)
                .take(10)
                .parallel()
                .runOn(Schedulers.parallel())
                .flatMap(customer -> getAgreementsMono(customer.getCustomerNo())
                        // .subscribeOn(Schedulers.parallel())
                )
                .subscribe(agreements -> log.info("{} - Customer '{}' has {} agreements", currentThread(), agreements.getCustomerNo(), agreements.totalAgreements()));

    }

    private Mono<Agreements> getAgreementsMono(Long customerNo) {
        return Mono.fromCallable(() -> {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Agreements> entity = restTemplate.getForEntity(BASE_SERVER_URL + "/agreements/" + customerNo, Agreements.class);
            return entity.getBody();
        });
    }

}
