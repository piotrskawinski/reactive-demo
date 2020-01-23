package com.example.backed.service.account;

import com.example.backed.service.customer.Customer;
import com.example.backed.service.util.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.LongStream;

@Component
public class AccountService {

    private Map<Long, List<BankAccount>> accounts = new LinkedHashMap<>();

    public void loadAccounts(List<Customer> customers) {
        customers.forEach(customer -> {
            LongStream.rangeClosed(1, 2).forEach(ins -> {
                        List<BankAccount> custAccounts = accounts.computeIfAbsent(customer.getCustomerNo(), k -> new ArrayList<>());
                        custAccounts.add(BankAccount.newAccount(customer.getCustomerNo(), IdGenerator.nextId()));
                    }
            );
        });
    }

    public List<BankAccount> getAccounts(Long custNo) {
        if (accounts.containsKey(custNo)) {
            return new ArrayList<>(accounts.get(custNo));
        }
        return Collections.emptyList();
    }

}
