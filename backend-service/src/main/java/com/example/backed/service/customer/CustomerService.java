package com.example.backed.service.customer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

@Component
public class CustomerService {

    private Map<Long, Customer> customers = new LinkedHashMap<>();

    public void loadCustomers() {
        LongStream.rangeClosed(1, 10000).forEach(customerNo -> {
            customers.put(customerNo, Customer.newCustomer(customerNo));
        });
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customers.values());
    }
}
