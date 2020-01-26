
package com.example.api.service.agreement;

import com.example.api.service.agreement.model.Agreements;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AgreementController {

    private final AgreementService agreementService;
    private static long numberOfRequests;

    @GetMapping(path = "/agreements/{customer_no}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Agreements> getAgreements(@PathVariable("customer_no") Long customerNo) {
        log.info("-> GET /agreements/" + customerNo);

        // log.info("Request number {}", numberOfRequests++);

        return agreementService.getAgreements(customerNo);
    }

}