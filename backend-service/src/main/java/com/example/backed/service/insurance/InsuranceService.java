package com.example.backed.service.insurance;

import com.example.backed.service.customer.Customer;
import com.example.backed.service.util.IdGenerator;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.LongStream;

@Component
@Data
public class InsuranceService {

    private Map<Long, List<InsurancePolicy>> insurances = new LinkedHashMap<>();

    public void loadInsurances(List<Customer> customers) {
        customers.forEach(customer -> {
            LongStream.rangeClosed(1, 10).forEach(ins -> {
                        List<InsurancePolicy> custInsurances = insurances.computeIfAbsent(customer.getCustomerNo(), k -> new ArrayList<>());
                        custInsurances.add(InsurancePolicy.newPolicy(customer.getCustomerNo(), IdGenerator.nextId()));
                    }
            );
        });
    }

    public List<InsurancePolicy> getPolicies(Long custNo) {
        if (insurances.containsKey(custNo)) {
            return new ArrayList<>(insurances.get(custNo));
        }
        return Collections.emptyList();
    }

}
