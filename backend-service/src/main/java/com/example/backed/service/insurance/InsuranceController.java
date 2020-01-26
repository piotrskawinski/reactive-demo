
package com.example.backed.service.insurance;


import com.example.backed.service.util.DelaySimulator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;
    private static long numberOfRequests;

    @GetMapping(path = "/insurance/policies/{customer_no}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InsurancePolicy>> getInsurancePolicies(@PathVariable("customer_no") Long customerNo) {
        log.info("Insurance request number {}", numberOfRequests++);
        DelaySimulator.simulate(500);
        return ResponseEntity.ok(insuranceService.getPolicies(customerNo));
    }

}
