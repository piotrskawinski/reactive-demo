
package com.example.backed.service.account;


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
public class AccountController {

    private final AccountService accountService;
    private static long numberOfRequests;

    @GetMapping(path = "/bank/accounts/{customer_no}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BankAccount>> getAccount(@PathVariable("customer_no") Long customerNo) {
        DelaySimulator.simulate(500);
        log.info("Account request number {}", numberOfRequests++);

        return ResponseEntity.ok(accountService.getAccounts(customerNo));
    }

}
