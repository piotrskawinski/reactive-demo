package com.example.backed.service;

import com.example.backed.service.customer.CustomerService;
import com.example.backed.service.account.AccountService;
import com.example.backed.service.insurance.InsuranceService;
import com.example.backed.service.pension.PensionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

    private final CustomerService customerService;
    private final AccountService accountService;
    private final InsuranceService insuranceService;
    private final PensionService pensionService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        customerService.loadCustomers();
        accountService.loadAccounts(customerService.getCustomers());
        insuranceService.loadInsurances(customerService.getCustomers());
        pensionService.loadPensions(customerService.getCustomers());
    }
}

