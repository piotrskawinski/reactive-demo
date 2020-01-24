package com.example.backed.service.pension;

import com.example.backed.service.util.IdGenerator;
import com.example.backed.service.customer.Customer;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
public class PensionService {

    private Map<Long, List<PensionPolicy>> pensions = new HashMap<>();

    public void loadPensions(List<Customer> customers) {
        customers.forEach(customer -> {
            pensions.put(customer.getCustomerNo(), Collections.singletonList(PensionPolicy.newPolicy(customer.getCustomerNo(), IdGenerator.nextId())));
        });
    }

    public List<PensionPolicy> getPolicies(Long custNo) {
        if (pensions.containsKey(custNo)) {
            return new ArrayList<>(pensions.get(custNo));
        }
        return Collections.emptyList();
    }
}
